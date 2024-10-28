package com.ecmp.api.dtos;

import com.ecmp.api.enums.Types;
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
public class FilterItem {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Types type;
    private String description;
    private double price;
}
