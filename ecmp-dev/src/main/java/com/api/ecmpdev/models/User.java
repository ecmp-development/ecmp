package com.api.ecmpdev.models;

import com.api.ecmpdev.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String firstname;

    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> order;
}
