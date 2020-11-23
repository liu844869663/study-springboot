package com.fullmoon.study.datasource;

import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jingping.liu
 * @date 2020-11-22
 * @description 多数据源的上下文
 */
@Log4j2
public class DataSourceContextHolder {

    /**
     * 线程本地遍历
     */
    private static final ThreadLocal<String> DATASOURCE_KEY = new ThreadLocal<>();

    /**
     * 配置的所有数据源的 Key 值
     */
    public static Set<Object> ALL_DATASOURCE_KEY = new HashSet<>();

    /**
     * 设置当前线程的数据源的 Key
     *
     * @param dataSourceKey 数据源的 Key 值
     */
    public static void setDataSourceKey(String dataSourceKey) {
        if (ALL_DATASOURCE_KEY.contains(dataSourceKey)) {
            DATASOURCE_KEY.set(dataSourceKey);
        } else {
            log.warn("the datasource [{}] does not exist", dataSourceKey);
        }
    }

    /**
     * 获取当前线程的数据源的 Key 值
     *
     * @return 数据源的 Key 值
     */
    public static String getDataSourceKey() {
        return DATASOURCE_KEY.get();
    }

    /**
     * 移除当前线程持有的数据源的 Key 值
     */
    public static void clear() {
        DATASOURCE_KEY.remove();
    }
}
