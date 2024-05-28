package com.hg.hgblogback.dto.request.posts;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditPostsRequestDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
    
    @NotNull
    private List<String> postsImageList;
}
