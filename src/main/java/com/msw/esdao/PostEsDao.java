package com.msw.esdao;

import com.msw.model.dto.post.PostEsDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * 帖子 ES 操作
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    List<PostEsDTO> findByUserId(Long userId);

    @NotNull Optional<PostEsDTO> findById(@NotNull Long Id);
}