package com.picksa.picksaserver.question.controller;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.dto.TagResponse;
import com.picksa.picksaserver.question.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping
    public List<TagResponse> getTagsByPartAndGeneration(@RequestParam Part part, @RequestParam int year) {
        return tagService.getTagsByPartAndGeneration(part, year);
    }

    @GetMapping("/{id}")
    public Optional<TagResponse> getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }
}
