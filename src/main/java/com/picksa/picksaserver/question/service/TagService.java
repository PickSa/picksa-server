package com.picksa.picksaserver.question.service;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.TagResponse;
import com.picksa.picksaserver.question.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.picksa.picksaserver.global.domain.Generation.getGenerationOfThisYear;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagResponse> getTagsByPartAndGeneration(Part part) {
        int generation = getGenerationOfThisYear();
        List<TagEntity> tagEntities = tagRepository.findByPartAndGeneration(part, generation);
        return tagEntities.stream().map(TagResponse::from)
                .collect(Collectors.toList());
    }

    public TagResponse getTagById(Long id) {
        TagEntity tag = tagRepository.findByIdOrThrow(id);
        return TagResponse.from(tag);
    }

}
