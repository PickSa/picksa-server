package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByPartAndGeneration(Part part, int generation);

    default TagEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("해당 태그가 존재하지 않습니다."));
    }

    default List<TagEntity> findByPartAndGenerationOrThrow(Part part, int generation) {
        List<TagEntity> tagEntities = findByPartAndGeneration(part, generation);

        if (tagEntities.isEmpty()) {
            throw new IllegalArgumentException("해당 태그가 존재하지 않습니다.");
        }

        return tagEntities;
    }
}
