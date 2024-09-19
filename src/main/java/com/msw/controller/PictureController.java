package com.msw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.common.BaseResponse;
import com.msw.common.ErrorCode;
import com.msw.common.ResultUtils;
import com.msw.exception.ThrowUtils;
import com.msw.model.dto.picture.PictureQueryRequest;
import com.msw.model.vo.PictureVO;
import com.msw.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 图片管理接口
 */
@RestController
@RequestMapping("/picture")
@Api(tags = "图片管理")
@Slf4j
public class PictureController {
    @Resource
    private PictureService pictureService;

    /**
     * 图片列表获取
     *
     * @param pictureQueryRequest pictureQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @ApiOperation(value = "图片列表获取")
    public BaseResponse<Page<PictureVO>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest) {
        ThrowUtils.throwIf(pictureQueryRequest.getPageSize() > 100, ErrorCode.PARAMS_ERROR);
        Page<PictureVO> pictures = pictureService.searchPictures(pictureQueryRequest);
        return ResultUtils.success(pictures);
    }
}
