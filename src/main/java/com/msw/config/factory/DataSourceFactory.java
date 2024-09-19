package com.msw.config.factory;

import com.msw.service.DataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceFactory {
    private final Map<String, DataSource<?>> dataSourceMap = new HashMap<>();

    // 将构造函数改为公共
    public DataSourceFactory() {
    }

    public DataSource<?> getDataSource(String key) {
        return dataSourceMap.get(key);
    }

    public void registerDataSource(String key, DataSource<?> dataSource) {
        dataSourceMap.put(key, dataSource);
    }
}