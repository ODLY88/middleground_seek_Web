package com.msw.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.annotation.DataSourceRegister;
import com.msw.common.ErrorCode;
import com.msw.exception.ThrowUtils;
import com.msw.model.dto.user.UserQueryRequest;
import com.msw.model.entity.User;
import com.msw.model.vo.UserVO;
import com.msw.service.DataSource;
import com.msw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户服务实现
 */
@Service
@Slf4j
@DataSourceRegister("user")
public class UserDataSource implements DataSource<UserVO> {
    @Resource
    private UserService userService;
    
    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 100, ErrorCode.PARAMS_ERROR);
        Page<User> userPage = userService.page(new Page<>(pageNum, pageSize),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(pageNum, pageSize, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return  userVOPage;
    }
}
