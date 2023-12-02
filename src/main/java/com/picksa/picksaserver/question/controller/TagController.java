package com.picksa.picksaserver.question.controller;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.dto.TagResponse;
import com.picksa.picksaserver.question.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @GetMapping
    public ResponseEntity<List<TagResponse>> getTagsByPartAndGeneration(@RequestParam Part part, @RequestParam int year) {
        List<TagResponse> tags = tagService.getTagsByPartAndGeneration(part, year);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TagResponse>> getTagById(@PathVariable Long id) {
        Optional<TagResponse> tag = tagService.getTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
