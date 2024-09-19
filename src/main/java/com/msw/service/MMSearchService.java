package com.msw.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.model.dto.picture.PictureQueryRequest;
import com.msw.model.dto.search.MSSearcheQueryRequest;
import com.msw.model.vo.MSSearchVO;
import com.msw.model.vo.PictureVO;

/**
 * 帖子服务
 */
public interface MMSearchService {
    /**
     * 中台查询
     *
     * @param searCheQueryRequest searCheQueryRequest
     * @return MSSearchVO
     */
    MSSearchVO searChAll(MSSearcheQueryRequest searCheQueryRequest);
}
