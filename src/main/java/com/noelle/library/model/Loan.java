package com.noelle.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    private String id;

    @Column(name="borrowedDate", nullable=false)
    private LocalDate borrowedDate;


    @Column(name="returnDate")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "person_cpf")
    private Person person;
}
