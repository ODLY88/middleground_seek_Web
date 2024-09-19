package com.msw.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.common.ErrorCode;
import com.msw.exception.BusinessException;
import com.msw.model.dto.picture.PictureQueryRequest;
import com.msw.model.vo.PictureVO;
import com.msw.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 帖子服务实现
 */
@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Override
    public Page<PictureVO> searchPictures(PictureQueryRequest pictureQueryRequest) {
        int size = pictureQueryRequest.getPageSize();
        int current = Math.max(0, pictureQueryRequest.getCurrent() - 1);
        int index = size * current + 1;
        String url = "https://cn.bing.com/images/?q=" + pictureQueryRequest.getSearchText()
                + "&go=%E6%90%9C%E7%B4%A2&qs=ds&form=QBIR&first=" + index;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据获取异常");
        }
        Elements elements = doc.select(".iuscp.isv");
        List<PictureVO> pictureList = new ArrayList<>();
        for (Element element : elements) {
            if (pictureList.size() >= size) {
                break;
            }
            // 获取图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map map = JSONUtil.toBean(m, Map.class);
            String murl = map.get("murl").toString();
            String mid = map.get("mid").toString();
            // 获取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
            PictureVO picture = new PictureVO();
            picture.setId(mid);
            picture.setTitle(title);
            picture.setUrl(murl);
            picture.setUserId(1000000000000000000L);
            picture.setCreateTime(new Date());
            picture.setUpdateTime(new Date());
            pictureList.add(picture);
        }
        Page<PictureVO> page = new Page<>(current + 1, size);
        page.setRecords(pictureList);
        return page;
    }
}




