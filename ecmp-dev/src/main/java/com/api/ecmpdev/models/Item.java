package com.api.ecmpdev.models;

import com.api.ecmpdev.enums.Types;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Types type;

    private int quantity;

    private String description;

    @Lob
    private byte[] image;

    private double price;
}
