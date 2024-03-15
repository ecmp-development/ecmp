package com.api.ecmpdev.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @ToString.Exclude
    @JoinColumn(name = "item_name")
    private List<Item> item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;
}
