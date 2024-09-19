package com.msw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.config.factory.DataSourceFactory;
import com.msw.model.dto.search.MSSearcheQueryRequest;
import com.msw.model.entity.Post;
import com.msw.model.enums.CommonSearchEnum;
import com.msw.model.vo.MSSearchVO;
import com.msw.model.vo.PictureVO;
import com.msw.model.vo.PostVO;
import com.msw.model.vo.UserVO;
import com.msw.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 帖子服务实现
 */
@Service
@Slf4j
public class MMSearchServiceImpl implements MMSearchService {
    @Resource
    private DataSourceFactory dataSourceFactory;

    @Override
    public MSSearchVO searChAll(MSSearcheQueryRequest searCheQueryRequest) {
        String searchText = searCheQueryRequest.getSearchText();
        long size = searCheQueryRequest.getPageSize();
        long current = searCheQueryRequest.getCurrent();
        String type = searCheQueryRequest.getType().toLowerCase(Locale.ROOT);
        CommonSearchEnum searchEnum = CommonSearchEnum.getEnumByValue(type);
        MSSearchVO msSearchVO = new MSSearchVO();
        if (ObjectUtils.isNotEmpty(searchEnum)) {
            DataSource<?> dataSource = dataSourceFactory.getDataSource(type);
            Page<?> page = dataSource.doSearch(searchText, current, size);
            msSearchVO.setDataPage(page);
        }else {
            setUserVO(searCheQueryRequest, msSearchVO);
            setPostVO(searCheQueryRequest, msSearchVO);
            setPictureVO(searCheQueryRequest, msSearchVO);
        }
        return msSearchVO;
    }

    /**
     * 填充图片信息
     *
     * @param searCheQueryRequest searCheQueryRequest
     * @param msSearchVO msSearchVO
     */
    private void setPictureVO(MSSearcheQueryRequest searCheQueryRequest, MSSearchVO msSearchVO) {
        DataSource<?> dataSource = dataSourceFactory.getDataSource(CommonSearchEnum.PICTURE.getValue());
        Page<?> pictureVOPage = dataSource.doSearch(searCheQueryRequest.getSearchText(),
                searCheQueryRequest.getCurrent(), searCheQueryRequest.getPageSize());
        msSearchVO.setPictureVOList((List<PictureVO>) pictureVOPage.getRecords());
    }

    /**
     * 填充帖子信息
     *
     * @param searCheQueryRequest searCheQueryRequest
     * @param msSearchVO msSearchVO
     */
    private void setPostVO(MSSearcheQueryRequest searCheQueryRequest, MSSearchVO msSearchVO) {
        DataSource<?> dataSource = dataSourceFactory.getDataSource(CommonSearchEnum.POST.getValue());
        Page<?> postPage = dataSource.doSearch(searCheQueryRequest.getSearchText(),
                searCheQueryRequest.getCurrent(), searCheQueryRequest.getPageSize());
        msSearchVO.setPostVOList((List<PostVO>) postPage.getRecords());
    }

    /**
     * 填充用户信息
     *
     * @param searCheQueryRequest searCheQueryRequest
     * @param msSearchVO msSearchVO
     */
    private void setUserVO(MSSearcheQueryRequest searCheQueryRequest, MSSearchVO msSearchVO) {
        DataSource<?> dataSource = dataSourceFactory.getDataSource(CommonSearchEnum.USER.getValue());
        Page<?> userVOPage = dataSource.doSearch(searCheQueryRequest.getSearchText(),
                searCheQueryRequest.getCurrent(), searCheQueryRequest.getPageSize());
        msSearchVO.setUserVOList((List<UserVO>) userVOPage.getRecords());
    }
}




