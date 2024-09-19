package com.msw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 数据源查询接口
 */
public interface DataSource<T> {

    /**
     * 搜索
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText, long pageNum, long pageSize);
}
