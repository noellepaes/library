package com.noelle.library.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Column(name = "book_id")
    private String id;

    @Column(name = "bookName",  nullable=false)
    private String bookName;

    @Column(name = "bookUrl")
    private String bookUrl;

    @Column(name = "borrowed")
    private boolean borrowed;


}
