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
package org.dbflute.kvs.store;

import java.util.List;
import java.util.stream.Collectors;

import org.dbflute.helper.mapstring.MapListString;
import org.dbflute.kvs.store.entity.KvsStoreEntity;
import org.dbflute.kvs.store.entity.dbmeta.KvsStoreDBMeta;

/**
 * @author FreeGen
 */
public class KvsStoreConverterHandler {

    /**
     * エンティティをマップ文字列に変換します。
     * @param entity エンティティ (NotNull)
     * @param <ENTITY> エンティティ
     * @return マップ文字列 (NotNull)
     */
    public <ENTITY extends KvsStoreEntity> String toMapString(ENTITY entity) {
        return createMapListString().buildMapString(entity.asDBMeta().extractAllColumnMap(entity));
    }

    /**
     * エンティティリストをマップ文字列リストに変換します。
     * @param entityList エンティティリスト (NotNull)
     * @param <ENTITY> エンティティ
     * @return マップ文字列リスト (NotNull)
     */
    public <ENTITY extends KvsStoreEntity> List<String> toMapStringList(List<ENTITY> entityList) {
        List<String> list = entityList.stream().map(entity -> {
            return createMapListString().buildMapString(entity.asDBMeta().extractAllColumnMap(entity));
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * マップ文字列をエンティティに変換します。
     * @param mapString マップ文字列 (NotNull)
     * @param kvsStoreDBMeta KVSストアメタ (NotNull)
     * @param <ENTITY> エンティティ
     * @return エンティティ (NotNull)
     */
    @SuppressWarnings("unchecked")
    public <ENTITY extends KvsStoreEntity> ENTITY toEntity(String mapString, KvsStoreDBMeta kvsStoreDBMeta) {
        KvsStoreEntity entity = kvsStoreDBMeta.newKvsStoreEntity();
        kvsStoreDBMeta.acceptAllColumnMap(entity, createMapListString().generateMap(mapString));
        return (ENTITY) entity;
    }

    /**
     * マップ文字列リストをエンティティリストに変換します。
     * @param mapStringList マップ文字列リスト (NotNull)
     * @param kvsStoreDBMeta KVSストアメタ (NotNull)
     * @param <ENTITY> エンティティ
     * @return エンティティリスト (NotNull)
     */
    @SuppressWarnings("unchecked")
    public <ENTITY extends KvsStoreEntity> List<ENTITY> toEntityList(List<String> mapStringList, KvsStoreDBMeta kvsStoreDBMeta) {
        List<KvsStoreEntity> entityList = mapStringList.stream().map(mapString -> {
            KvsStoreEntity entity = kvsStoreDBMeta.newKvsStoreEntity();
            kvsStoreDBMeta.acceptAllColumnMap(entity, createMapListString().generateMap(mapString));
            return entity;
        }).collect(Collectors.toList());
        return (List<ENTITY>) entityList;
    }

    protected MapListString createMapListString() {
        return new MapListString(); // ステートフルなので毎回new
    }
}
