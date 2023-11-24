package com.picksa.picksaserver.question.repository;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.TagDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findByPartAndGeneration(Part part, int generation);

    default TagEntity findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("해당 박스가 존재하지 않습니다."));
    }

    default List<TagEntity> findByPartAndGenerationOrThrow(Part part, int generation) {
        List<TagEntity> tagEntities = findByPartAndGeneration(part, generation);

        if (tagEntities.isEmpty()) {
            throw new IllegalArgumentException("해당 박스가 존재하지 않습니다.");
        }

        return tagEntities;
    }
}
