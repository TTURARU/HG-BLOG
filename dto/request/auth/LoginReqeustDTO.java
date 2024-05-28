package com.hg.hgblogback.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginReqeustDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
