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
package org.docksidestage.kvs.cache.maihamadb.cbean.cq.bs;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.dbflute.dbmeta.info.ColumnInfo;
import org.dbflute.util.DfCollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.docksidestage.dbflute.bsentity.dbmeta.ProductDbm;
import org.docksidestage.dbflute.exentity.Product;

/**
 * The base condition-query of (Product)Product.
 * @author FreeGen
 */
public abstract class KvsBsProductCQ {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(KvsBsProductCQ.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected ProductDbm dbMeta = ProductDbm.getInstance();
    protected Comparator<Product> orderBy;
    protected Map<ColumnInfo, Object> columnEqualValue = DfCollectionUtil.newHashMap();
    protected Map<ColumnInfo, Boolean> columnOrderBy = DfCollectionUtil.newHashMap();

    // ===================================================================================
    //                                                                           Condition
    //                                                                           =========
    public void setProductId_Equal(Integer productId) {
        columnEqualValue.put(dbMeta.columnProductId(), productId);
    }

    public void addOrderBy_ProductId_Asc() {
        columnOrderBy.put(dbMeta.columnProductId(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductId, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getProductId, Comparator.naturalOrder());
    }

    public void addOrderBy_ProductId_Desc() {
        columnOrderBy.put(dbMeta.columnProductId(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductId, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getProductId, Comparator.reverseOrder());
    }

    public void setProductName_Equal(String productName) {
        columnEqualValue.put(dbMeta.columnProductName(), productName);
    }

    public void addOrderBy_ProductName_Asc() {
        columnOrderBy.put(dbMeta.columnProductName(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductName, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getProductName, Comparator.naturalOrder());
    }

    public void addOrderBy_ProductName_Desc() {
        columnOrderBy.put(dbMeta.columnProductName(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductName, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getProductName, Comparator.reverseOrder());
    }

    public void setProductHandleCode_Equal(String productHandleCode) {
        columnEqualValue.put(dbMeta.columnProductHandleCode(), productHandleCode);
    }

    public void addOrderBy_ProductHandleCode_Asc() {
        columnOrderBy.put(dbMeta.columnProductHandleCode(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductHandleCode, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getProductHandleCode, Comparator.naturalOrder());
    }

    public void addOrderBy_ProductHandleCode_Desc() {
        columnOrderBy.put(dbMeta.columnProductHandleCode(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductHandleCode, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getProductHandleCode, Comparator.reverseOrder());
    }

    public void setProductCategoryCode_Equal(String productCategoryCode) {
        columnEqualValue.put(dbMeta.columnProductCategoryCode(), productCategoryCode);
    }

    public void addOrderBy_ProductCategoryCode_Asc() {
        columnOrderBy.put(dbMeta.columnProductCategoryCode(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductCategoryCode, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getProductCategoryCode, Comparator.naturalOrder());
    }

    public void addOrderBy_ProductCategoryCode_Desc() {
        columnOrderBy.put(dbMeta.columnProductCategoryCode(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductCategoryCode, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getProductCategoryCode, Comparator.reverseOrder());
    }

    public void setProductStatusCode_Equal(String productStatusCode) {
        columnEqualValue.put(dbMeta.columnProductStatusCode(), productStatusCode);
    }

    public void addOrderBy_ProductStatusCode_Asc() {
        columnOrderBy.put(dbMeta.columnProductStatusCode(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductStatusCode, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getProductStatusCode, Comparator.naturalOrder());
    }

    public void addOrderBy_ProductStatusCode_Desc() {
        columnOrderBy.put(dbMeta.columnProductStatusCode(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getProductStatusCode, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getProductStatusCode, Comparator.reverseOrder());
    }

    public void setRegularPrice_Equal(Integer regularPrice) {
        columnEqualValue.put(dbMeta.columnRegularPrice(), regularPrice);
    }

    public void addOrderBy_RegularPrice_Asc() {
        columnOrderBy.put(dbMeta.columnRegularPrice(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegularPrice, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getRegularPrice, Comparator.naturalOrder());
    }

    public void addOrderBy_RegularPrice_Desc() {
        columnOrderBy.put(dbMeta.columnRegularPrice(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegularPrice, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getRegularPrice, Comparator.reverseOrder());
    }

    public void setRegisterDatetime_Equal(java.time.LocalDateTime registerDatetime) {
        columnEqualValue.put(dbMeta.columnRegisterDatetime(), registerDatetime);
    }

    public void addOrderBy_RegisterDatetime_Asc() {
        columnOrderBy.put(dbMeta.columnRegisterDatetime(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegisterDatetime, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getRegisterDatetime, Comparator.naturalOrder());
    }

    public void addOrderBy_RegisterDatetime_Desc() {
        columnOrderBy.put(dbMeta.columnRegisterDatetime(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegisterDatetime, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getRegisterDatetime, Comparator.reverseOrder());
    }

    public void setRegisterUser_Equal(String registerUser) {
        columnEqualValue.put(dbMeta.columnRegisterUser(), registerUser);
    }

    public void addOrderBy_RegisterUser_Asc() {
        columnOrderBy.put(dbMeta.columnRegisterUser(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegisterUser, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getRegisterUser, Comparator.naturalOrder());
    }

    public void addOrderBy_RegisterUser_Desc() {
        columnOrderBy.put(dbMeta.columnRegisterUser(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getRegisterUser, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getRegisterUser, Comparator.reverseOrder());
    }

    public void setUpdateDatetime_Equal(java.time.LocalDateTime updateDatetime) {
        columnEqualValue.put(dbMeta.columnUpdateDatetime(), updateDatetime);
    }

    public void addOrderBy_UpdateDatetime_Asc() {
        columnOrderBy.put(dbMeta.columnUpdateDatetime(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getUpdateDatetime, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getUpdateDatetime, Comparator.naturalOrder());
    }

    public void addOrderBy_UpdateDatetime_Desc() {
        columnOrderBy.put(dbMeta.columnUpdateDatetime(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getUpdateDatetime, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getUpdateDatetime, Comparator.reverseOrder());
    }

    public void setUpdateUser_Equal(String updateUser) {
        columnEqualValue.put(dbMeta.columnUpdateUser(), updateUser);
    }

    public void addOrderBy_UpdateUser_Asc() {
        columnOrderBy.put(dbMeta.columnUpdateUser(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getUpdateUser, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getUpdateUser, Comparator.naturalOrder());
    }

    public void addOrderBy_UpdateUser_Desc() {
        columnOrderBy.put(dbMeta.columnUpdateUser(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getUpdateUser, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getUpdateUser, Comparator.reverseOrder());
    }

    public void setVersionNo_Equal(Long versionNo) {
        columnEqualValue.put(dbMeta.columnVersionNo(), versionNo);
    }

    public void addOrderBy_VersionNo_Asc() {
        columnOrderBy.put(dbMeta.columnVersionNo(), true);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getVersionNo, Comparator.naturalOrder())
                : orderBy.thenComparing(Product::getVersionNo, Comparator.naturalOrder());
    }

    public void addOrderBy_VersionNo_Desc() {
        columnOrderBy.put(dbMeta.columnVersionNo(), false);

        orderBy = (orderBy == null) ? //
                Comparator.comparing(Product::getVersionNo, Comparator.reverseOrder())
                : orderBy.thenComparing(Product::getVersionNo, Comparator.reverseOrder());
    }

    // ===================================================================================
    //                                                                        Assist Logic
    //                                                                        ============
    public Comparator<Product> getOrderByComparator() {
        return orderBy;
    }

    public Predicate<Product> getWherePredicate() {
        return entity -> {
            for (Entry<ColumnInfo, Object> entry : columnEqualValue.entrySet()) {
                Object value = entry.getKey().read(entity);
                logger.debug("filter [{}] CB[{}] = RECORD[{}]\n", entry.getKey().getColumnDbName(), entry.getValue(), value);

                if (value == null) {
                    return false;
                }

                if (!value.equals(entry.getValue())) {
                    return false;
                }
            }

            return true;
        };
    }

    public Map<ColumnInfo, Object> xdfgetColumnEqualValue() {
        return columnEqualValue;
    }

    public Map<ColumnInfo, Boolean> xdfgetColumnOrderBy() {
        return columnOrderBy;
    }
}