/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.dbflute.kvs.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.helper.mapstring.MapListString;
import org.dbflute.util.DfCollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FreeGen
 */
public class KvsCacheConverterHandler {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(KvsCacheConverterHandler.class);

    // ===================================================================================
    //                                                                        to MapString
    //                                                                        ============
    /**
     * Convert an Entity object to a MapString in KVS represented by String.
     * @param entity Entity of DBFlute (NotNull)
     * @param <ENTITY> Entity class of DBFlute
     * @return A String value representing MapString in KVS (NotNull)
     */
    public <ENTITY extends Entity> String toMapString(ENTITY entity) {
        return createMapListString().buildMapString(entity.asDBMeta().extractAllColumnMap(entity));
    }

    /** 
     * Convert Entity objects in the assigned list to MapString values in KVS represented by String.
     * @param entityList A list of Entity objects (NotNull)
     * @param <ENTITY> Entity class of DBFlute
     * @return A list of String values representing MapString in KVS (NotNull)
     */
    public <ENTITY extends Entity> List<String> toMapStringList(List<ENTITY> entityList) {
        List<String> list = entityList.stream().map(entity -> {
            return createMapListString().buildMapString(entity.asDBMeta().extractAllColumnMap(entity));
        }).collect(Collectors.toList());

        return list;
    }

    // ===================================================================================
    //                                                                           to Entity
    //                                                                           =========
    /**
     * Convert A MapString in KVS represented by Java String to an Entity object.
     * @param mapString A String value representing MapString (NotNull)
     * @param dbmeta DBMeta (NotNull)
     * @param <ENTITY> Entity class of DBFlute
     * @return An Entity object of DBFlute (NotNull)
     */
    @SuppressWarnings("unchecked")
    public <ENTITY extends Entity> ENTITY toEntity(String mapString, DBMeta dbmeta) {
        Map<String, Object> columnValueMap = createMapListString().generateMap(mapString);

        // Only if all columns of the table to which the result Entity belongs exist
        if (existsAllColumn(dbmeta, columnValueMap)) {
            final Entity foundEntity = dbmeta.newEntity();
            try {
                dbmeta.acceptAllColumnMap(foundEntity, columnValueMap);
                return (ENTITY) foundEntity;
            } catch (Exception e) {
                logger.info("DDL changes. deserialize error. reload RDB.mapString={}, exceptionMessage={}", mapString, e.getMessage());
            }
        } else {
            logger.info("DDL changes. column added. reload RDB.mapString={}", mapString);
        }
        return null;
    }

    /**
     * Convert a list of String value to an Entity object.
     * @param value A list of String values (NotNull)
     * @param dbmeta DBMeta (NotNull)
     * @param specifiedColumnDbNames specifiedColumnDbNames (NotNull)
     * @param <ENTITY> Entity class of DBFlute
     * @return An Entity object of DBFlute (NotNull)
     */
    @SuppressWarnings("unchecked")
    public <ENTITY extends Entity> ENTITY toEntity(List<String> value, DBMeta dbmeta, Set<String> specifiedColumnDbNames) {
        Map<String, Object> columnValueMap = DfCollectionUtil.newHashMap();
        List<String> specifiedList = DfCollectionUtil.newArrayList(specifiedColumnDbNames);
        IntStream.range(0, specifiedList.size()).forEach(index -> {
            if (value.get(index) != null) {
                columnValueMap.put(specifiedList.get(index), value.get(index));
            }
        });
        if (!columnValueMap.isEmpty()) {
            final Entity foundEntity = dbmeta.newEntity();
            try {
                dbmeta.acceptAllColumnMap(foundEntity, columnValueMap);
                return (ENTITY) foundEntity;
            } catch (Exception e) {
                logger.info("DDL changes. deserialize error. reload RDB.columnValueMap={}, exceptionMessage={}", columnValueMap,
                        e.getMessage());
            }
        }
        return null;
    }

    /**
     * Convert Mapstrings in KVS represented by Java String to Entity objects.
     * @param mapStringList A list of String values representing MapString (NotNull)
     * @param dbmeta DBMeta (NotNull)
     * @param <ENTITY> Entity class of DBFlute
     * @return A list of Entity objects of DBFlute (NotNull)
     */
    public <ENTITY extends Entity> List<ENTITY> toEntityList(List<String> mapStringList, DBMeta dbmeta) {
        // Only if all columns of the table to which the result Entity belongs exist
        // (examine only the first element of the list because all elements should have the same field).
        if (existsAllColumn(dbmeta, createMapListString().generateMap(mapStringList.get(0)))) {
            final List<ENTITY> foundList = new ArrayList<ENTITY>();
            final boolean success = mapStringList.stream().allMatch(value -> {
                return matchesAll(mapStringList, dbmeta, foundList, value);
            });
            if (success) {
                return foundList;
            }
        } else {
            logger.info("DDL changes. column added. reload RDB.mapStringList={}", mapStringList);
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    protected <ENTITY extends Entity> boolean matchesAll(List<String> mapStringList, DBMeta dbmeta, List<ENTITY> foundList, String value) {
        final Entity foundEntity = dbmeta.newEntity();
        try {
            dbmeta.acceptAllColumnMap(foundEntity, createMapListString().generateMap(value));
        } catch (RuntimeException e) {
            // Reload from RDB if deserialization failed
            logger.info("DDL changes. deserialize error. reload RDB.mapStringList={}, exceptionMessage={}", mapStringList, e.getMessage());
            return false;
        }
        foundList.add((ENTITY) foundEntity);
        return true;
    }

    /**
     * Return {@code true} if all columns of specified table are contained in the keys in assigned MapString.
     * @param dbmeta Metadata for a table (NotNull)
     * @param columnValueMap A String value representing a MapString of column-value pairs (NotNull)
     * @return {@code true} if all of column names in specified table are contained in the keys in MapString
     */
    protected boolean existsAllColumn(DBMeta dbmeta, Map<String, Object> columnValueMap) {
        Set<String> columnNameSet = dbmeta.getColumnInfoList().stream().map(columnInfo -> {
            return columnInfo.getColumnDbName();
        }).collect(Collectors.toSet());
        return columnValueMap.keySet().containsAll(columnNameSet);
    }

    protected MapListString createMapListString() {
        return new MapListString(); // Instantiate everytime because of the statefulness of...
    }
}
