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
package org.dbflute.kvs.cache.delegator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dbflute.kvs.cache.KvsCacheConverterHandler;
import org.dbflute.kvs.core.delegator.AbstractKvsRedisDelegator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FreeGen
 */
public class KvsCacheRedisDelegator extends AbstractKvsRedisDelegator {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** Slf4jのロガー。 */
    private static final Logger LOG = LoggerFactory.getLogger(KvsCacheConverterHandler.class);

    // ===================================================================================
    //                                                                                Get
    //                                                                               =====
    // -----------------------------------------------------
    //                                                String
    //                                                ------
    @Override
    public String findString(String key) {
        try {
            return super.findString(key);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}", key, e);
            return null;
        }
    }

    @Override
    public List<String> findMultiString(List<String> keyList) {
        try {
            return super.findMultiString(keyList);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyList={}", keyList, e);
            return new ArrayList<String>();
        }
    }

    // -----------------------------------------------------
    //                                                  List
    //                                                  ----
    @Override
    public List<String> findList(String key) {
        try {
            return super.findList(key);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}", key, e);
            return new ArrayList<String>();
        }
    }

    @Override
    public List<List<String>> findMultiList(List<String> keyList) {
        try {
            return super.findMultiList(keyList);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyList={}", keyList, e);
            return new ArrayList<List<String>>();
        }
    }

    // -----------------------------------------------------
    //                                                  Hash
    //                                                  ----
    @Override
    public List<String> findHash(String key, Set<String> fieldList) {
        try {
            return super.findHash(key, fieldList);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}", key, e);
            return new ArrayList<String>();
        }
    }

    @Override
    public List<List<String>> findMultiHash(List<String> keyList, Set<String> fieldList) {
        try {
            return super.findMultiHash(keyList, fieldList);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyList={}, fieldList={}", keyList, fieldList, e);
            return new ArrayList<List<String>>();
        }
    }

    // ===================================================================================
    //                                                                            Register
    //                                                                            ========
    // -----------------------------------------------------
    //                                                String
    //                                                ------
    @Override
    public void registerString(String key, String value) {
        try {
            super.registerString(key, value);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, value={}", key, value, e);
            return;
        }
    }

    @Override
    public void registerString(String key, String value, LocalDateTime availableDateTime) {
        try {
            super.registerString(key, value, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, value={}, availableDateTime={}", key, value, availableDateTime, e);
            return;
        }
    }

    @Override
    public void registerMultiString(Map<String, String> keyValueMap) {
        try {
            super.registerMultiString(keyValueMap);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}", keyValueMap, e);
            return;
        }
    }

    @Override
    public void registerMultiString(Map<String, String> keyValueMap, LocalDateTime availableDateTime) {
        try {
            super.registerMultiString(keyValueMap, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}, availableDateTime={}", keyValueMap, availableDateTime, e);
            return;
        }
    }

    // -----------------------------------------------------
    //                                                  List
    //                                                  ----
    @Override
    public void registerList(String key, List<String> value) {
        try {
            super.registerList(key, value);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, value={}", key, value, e);
            return;
        }
    }

    @Override
    public void registerList(String key, List<String> value, LocalDateTime availableDateTime) {
        try {
            super.registerList(key, value, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, value={}, availableDateTime={}", key, value, availableDateTime, e);
            return;
        }
    }

    @Override
    public void registerMultiList(Map<String, List<String>> keyValueMap) {
        try {
            super.registerMultiList(keyValueMap);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}", keyValueMap, e);
            return;
        }
    }

    @Override
    public void registerMultiList(Map<String, List<String>> keyValueMap, LocalDateTime availableDateTime) {
        try {
            super.registerMultiList(keyValueMap, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}, availableDateTime={}", keyValueMap, availableDateTime, e);
            return;
        }
    }

    // -----------------------------------------------------
    //                                                  Hash
    //                                                  ----
    @Override
    public void registerHash(String key, Map<String, String> fieldValueMap) {
        try {
            super.registerHash(key, fieldValueMap);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, fieldValueMap={}", key, fieldValueMap, e);
            return;
        }
    }

    @Override
    public void registerHash(String key, Map<String, String> fieldValueMap, LocalDateTime availableDateTime) {
        try {
            super.registerHash(key, fieldValueMap, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}, fieldValueMap={}, availableDateTime={}", key, fieldValueMap, availableDateTime, e);
            return;
        }
    }

    @Override
    public void registerMultiHash(Map<String, Map<String, String>> keyValueMap) {
        try {
            super.registerMultiHash(keyValueMap);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}", keyValueMap, e);
            return;
        }
    }

    @Override
    public void registerMultiHash(Map<String, Map<String, String>> keyValueMap, LocalDateTime availableDateTime) {
        try {
            super.registerMultiHash(keyValueMap, availableDateTime);
        } catch (Exception e) {
            LOG.warn("kvs access error. keyValueMap={}, availableDateTime={}", keyValueMap, availableDateTime, e);
            return;
        }
    }

    // ===================================================================================
    //                                                                              Delete
    //                                                                              ======
    @Override
    public void delete(String key) {
        try {
            super.delete(key);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}", key, e);
            return;
        }
    }

    @Override
    public void delete(String... keys) {
        try {
            super.delete(keys);
        } catch (Exception e) {
            LOG.warn("kvs access error. key={}", Arrays.asList(keys), e);
            return;
        }
    }
}
