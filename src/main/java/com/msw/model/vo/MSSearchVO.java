package com.msw.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.model.entity.Post;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子
 */
@Data
public class MSSearchVO implements Serializable {

   /**
    * 用户信息
    */
   List<UserVO> userVOList;

   /**
    * 图片信息
    */
   List<PictureVO> pictureVOList;

   /**
    * 帖子
    */
   List<PostVO> postVOList;

   /**
    *
    */
   Page<?> dataPage;
}