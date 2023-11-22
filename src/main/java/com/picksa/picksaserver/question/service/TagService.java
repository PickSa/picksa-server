package com.picksa.picksaserver.question.service;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.TagDto;
import com.picksa.picksaserver.question.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagDto> getTagsByPartAndGeneration(Part part, int generation) {
        List<TagEntity> tagEntities = tagRepository.findByPartAndGeneration(part, generation);
        return tagEntities.stream()
                .map(TagDto::new)
                .collect(Collectors.toList());
    }

    public Optional<TagDto> getTagById(Long id) {
        return tagRepository.findById(id)
                .map(TagDto::new);
    }
}
