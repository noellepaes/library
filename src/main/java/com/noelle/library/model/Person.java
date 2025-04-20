package com.noelle.library.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @Column(name = "person_cpf")
    private String cpf;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="fined")
    private boolean fined;

    @Column(name="allowedBorrow")
    private boolean allowedBorrow;
}
