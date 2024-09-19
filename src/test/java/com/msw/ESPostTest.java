package com.msw;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msw.esdao.PostEsDao;
import com.msw.model.dto.post.PostEsDTO;
import com.msw.model.dto.post.PostQueryRequest;
import com.msw.model.entity.Post;
import com.msw.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class ESPostTest {
    @Resource
    private PostEsDao postEsDao;

    @Resource
    private PostService postService;

    @Test
    void testESPostTest(){
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        Page<Post> postPage = postService.queryPostsByPage(postQueryRequest);
        log.info("postPage : {}" , postPage);
    }

    @Test
    void selectTest() {
        log.info("postEsDao.count : {}" , postEsDao.count());
        org.springframework.data.domain.Page<PostEsDTO> page = postEsDao.findAll(
                PageRequest.of(0, 5, Sort.by("createTime"))
        );
        log.info("page.getContent() : {}" , page.getContent());
    }

    @Test
    void addAll() {
        PostEsDTO postEsDTO = new PostEsDTO();
        postEsDTO.setId(20240910L);
        postEsDTO.setTitle("自我介绍");
        postEsDTO.setContent("江南是小说作家，不是演说家");
        postEsDTO.setTags(Lists.newArrayList());
        postEsDTO.setUserId(1000000000000000000L);
        postEsDTO.setCreateTime(new Date());
        postEsDTO.setUpdateTime(new Date());
        postEsDTO.setIsDelete("N");
        postEsDao.save(postEsDTO);
        log.info("postEsDTO : {}" , postEsDTO);
    }

    @Test
    void addAll2() {
        PostEsDTO postEsDTO = new PostEsDTO();
        postEsDTO.setId(202409101816L);
        postEsDTO.setTitle("自我介绍");
        postEsDTO.setContent("马云是个商人，不是奥特曼");
        postEsDTO.setTags(Lists.newArrayList());
        postEsDTO.setUserId(1000000000000000000L);
        postEsDTO.setCreateTime(new Date());
        postEsDTO.setUpdateTime(new Date());
        postEsDTO.setIsDelete("N");
        postEsDao.save(postEsDTO);
        log.info("postEsDTO : {}" , postEsDTO);
    }

    @Test
    void testFindById() {
        List<PostEsDTO> byUserId = postEsDao.findByUserId(1000000000000000000L);
        log.info("byUserId : {}" , byUserId);
        Optional<PostEsDTO> postEsDTO = postEsDao.findById(20240907L);
        log.info("postEsDTO : {}", postEsDTO);
    }

    @Test
    void findTestCount() {
        long count = postEsDao.count();
        log.info("count : {}" , count);
    }

    @Test
    void testFindByCategory() {
        List<PostEsDTO> postEsDTOByUserId = postEsDao.findByUserId(1000000000000000000L);
        log.info("postEsDTOByUserId : {}" , postEsDTOByUserId);
    }

}
