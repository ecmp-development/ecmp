package com.api.ecmpdev.dtos;

import com.api.ecmpdev.enums.Roles;
import com.api.ecmpdev.models.Order;
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
public class ResponseUser {
    private String name;
    private String firstname;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;
}
