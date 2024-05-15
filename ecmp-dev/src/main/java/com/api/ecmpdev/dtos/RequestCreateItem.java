package com.api.ecmpdev.dtos;


import com.api.ecmpdev.enums.Types;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateItem {
    private String name;
    @Enumerated(EnumType.STRING)
    private Types type;
    private String description;
    @Lob
    private byte[] image;
    private double price;
}
