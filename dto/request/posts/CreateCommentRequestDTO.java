package com.hg.hgblogback.dto.request.posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentRequestDTO {
    @NotBlank
    private String content;
}
