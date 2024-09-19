package com.msw.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.msw.model.entity.Post;
import com.msw.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 帖子数据初始化（项目每次启动执行一次）
 */
@Component
@Slf4j
public class InitializePostList implements CommandLineRunner {
    @Resource
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {
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
        UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("id", "value1");
        boolean saveBatchStatus = postService.saveOrUpdateBatch(postList);
        log.info("Post initialization status (success：true/failure：false) :{}", saveBatchStatus);
        log.info("Initialization data volume (pieces) :{}", postList.size());
    }
}
