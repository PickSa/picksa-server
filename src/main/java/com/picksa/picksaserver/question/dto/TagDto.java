package com.picksa.picksaserver.question.dto;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TagDto {
    private Long id;
    private String content;
    private int generation;
    private Part part;

    public TagDto(Long id, String content, int generation, Part part) {
        this.id = id;
        this.content = content;
        this.generation = generation;
        this.part = part;
    }

    public TagDto(TagEntity tagEntity) {
        this.id = tagEntity.getId();
        this.content = tagEntity.getContent();
        this.generation = tagEntity.getGeneration();
        this.part = tagEntity.getPart();
    }
}
