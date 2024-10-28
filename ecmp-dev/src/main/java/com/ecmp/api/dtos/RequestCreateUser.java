package com.ecmp.api.dtos;

import com.ecmp.api.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateUser {
    private String name;
    private String firstname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
