package com.fullmoon.study.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jingping.liu
 * @date 2020-11-22
 * @description 多数据源
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    /**
     * 返回当前线程是有的数据源的 Key
     *
     * @return dataSourceKey
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceKey();
    }
}
