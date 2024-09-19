package com.msw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.annotation.DataSourceRegister;
import com.msw.common.ErrorCode;
import com.msw.exception.ThrowUtils;
import com.msw.model.dto.post.PostQueryRequest;
import com.msw.model.entity.Post;
import com.msw.model.vo.PostVO;
import com.msw.service.DataSource;
import com.msw.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务实现
 */
@Service
@Slf4j
@DataSourceRegister("post")
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 100, ErrorCode.PARAMS_ERROR);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(requestAttributes) || ObjectUtils.isEmpty(postPage)) {
            return new Page<PostVO>();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        return postService.getPostVOPage(postPage, request);
    }
}




