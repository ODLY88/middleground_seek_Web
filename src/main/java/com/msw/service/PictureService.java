package com.msw.service;

import com.msw.model.dto.picture.PictureQueryRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.model.vo.PictureVO;

/**
 * 帖子服务
 */
public interface PictureService {

    /**
     * 查询图片
     *
     * @param pictureQueryRequest pictureQueryRequest
     * @return list
     */
    Page<PictureVO> searchPictures(PictureQueryRequest pictureQueryRequest);
}
