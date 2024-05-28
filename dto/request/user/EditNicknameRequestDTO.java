package com.hg.hgblogback.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditNicknameRequestDTO {
    @NotBlank
    private String nickname;
}
