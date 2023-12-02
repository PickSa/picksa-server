package com.picksa.picksaserver.question.dto;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagResponse {
    private Long id;
    private String content;
    private int generation;
    private Part part;

    private TagResponse(TagEntity tagEntity) {
        this.id = tagEntity.getId();
        this.content = tagEntity.getContent();
        this.generation = tagEntity.getGeneration();
        this.part = tagEntity.getPart();
    }

    public static TagResponse from(TagEntity tagEntity) {
        return new TagResponse(tagEntity);
    }

}
