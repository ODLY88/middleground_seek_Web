package com.msw.controller;

import com.msw.common.BaseResponse;
import com.msw.common.ResultUtils;
import com.msw.model.dto.search.MSSearcheQueryRequest;
import com.msw.model.vo.MSSearchVO;
import com.msw.service.MMSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 中台（聚合）搜索管理
 */
@RestController
@RequestMapping("/search")
@Api(tags = "中台搜索管理")
@Slf4j
public class MSSearchController {
    @Resource
    private MMSearchService mmSearchService;

    /**
     * 图片列表获取
     *
     * @param searCheQueryRequest searCheQueryRequest
     * @return BaseResponse
     */
    @PostMapping("/all")
    @ApiOperation(value = "图片列表获取")
    public BaseResponse<MSSearchVO> searChAll(@RequestBody MSSearcheQueryRequest searCheQueryRequest) {
        MSSearchVO msSearchVO = mmSearchService.searChAll(searCheQueryRequest);
        return ResultUtils.success(msSearchVO);
    }
}
