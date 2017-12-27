package com.whu.ybatis.datasource;

/**
 * 用来切换上下文数据源名称
 * Created by hulichao on 2017/12/20
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    public static DataSourceType getDataSourceType() {
        return (DataSourceType) contextHolder.get();
    }

    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }

}

