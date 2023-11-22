package com.picksa.picksaserver.question.controller;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import com.picksa.picksaserver.question.dto.TagDto;
import com.picksa.picksaserver.question.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDto> getTagsByPartAndGeneration(@RequestParam Part part, @RequestParam int year) {
        return tagService.getTagsByPartAndGeneration(part, year);
    }

    @GetMapping("/{id}")
    public Optional<TagDto> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }
}
