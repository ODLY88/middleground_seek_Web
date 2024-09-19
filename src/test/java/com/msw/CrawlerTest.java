package com.msw;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONArray;
import com.msw.model.entity.Post;
import com.msw.model.vo.PictureVO;
import com.msw.service.PostService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class CrawlerTest {
    @Resource
    private PostService postService;

    @SneakyThrows
    @Test
    void testFetchPicture(){
        String current = "2";
        String url = "https://cn.bing.com/images/search?q=海贼王&go=%E6%90%9C%E7%B4%A2&qs=ds&form=QBIR&first=" + current;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv");
        List<PictureVO> pictureList = new ArrayList<>();
        for (Element element : elements) {
            // 获取图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map map = JSONUtil.toBean(m, Map.class);
            String murl = map.get("murl").toString();
            String mid = map.get("mid").toString();
            // 获取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
            PictureVO pictureVO = new PictureVO();
            pictureVO.setId(mid);
            pictureVO.setTitle(title);
            pictureVO.setUrl(murl);
            pictureVO.setUserId(1000000000000000000L);
            pictureVO.setCreateTime(new Date());
            pictureVO.setUpdateTime(new Date());
            pictureList.add(pictureVO);
        }
        log.info("pictureList: {}", pictureList);
    }

    @Test
    void testFetchCrawler(){
        // 数据获取
        String json = "{\n" +
                "    \"sortField\": \"createTime\",\n" +
                "    \"sortOrder\": \"descend\",\n" +
                "    \"reviewStatus\": 1,\n" +
                "    \"current\": 1,\n" +
                "    \"hiddenContent\": true,\n" +
                "    \"showPost\": 0\n" +
                "}";
        String result = HttpRequest.post("https://api.code-nav.cn/api/post/list/page/vo")
                .body(json)
                .execute().body();
        // log.info("result" + result);
        // 数据转换
        if (StringUtils.isBlank(result)) {
            return;
        }
        Map<String, Object> map = JSONUtil.toBean(result, Map.class);
        log.info("map", map);
        JSONObject data = JSONObject.from(map.get("data"));
        JSONArray recordArray = JSONArray.from(data.get("records"));
        List<Post> postList = new ArrayList<>();
        for (Object tempRecord : recordArray) {
            JSONObject fromRecord = JSONObject.from(tempRecord);
            Post post = new Post();
            post.setId(Long.valueOf(fromRecord.get("id").toString()));
            post.setTitle(fromRecord.get("title").toString());
            post.setContent(fromRecord.get("content").toString());
            Object tagsObject = fromRecord.get("tags");
            List<String> tagsList = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(tagsObject)) {
                JSONArray fromTags = JSONArray.from(tagsObject);
                tagsList = fromTags.toList(String.class);
            }
            post.setTags(JSONUtil.toJsonStr(tagsList));
            post.setUserId(1000000000000000000L);
            postList.add(post);
        }
        boolean saveBatchStatus = postService.saveBatch(postList);
        log.info("Post initialization status (success：true/failure：false) :{}", saveBatchStatus);
    }
}
