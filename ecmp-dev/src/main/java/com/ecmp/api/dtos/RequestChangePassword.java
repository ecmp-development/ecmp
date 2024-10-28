package com.ecmp.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangePassword {
    private String email;
    private String oldPassword;
    private String newPassword;
}
