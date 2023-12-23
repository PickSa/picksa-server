package com.picksa.picksaserver.question.controller;

import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.dto.TagResponse;
import com.picksa.picksaserver.question.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping
    public ResponseEntity<List<TagResponse>> getTagsByPartAndGeneration(@RequestParam Part part) {
        List<TagResponse> tags = tagService.getTagsByPartAndGeneration(part);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getTagById(@PathVariable Long id) {
        TagResponse tag = tagService.getTagById(id);
        return ResponseEntity.ok(tag);
    }

}
