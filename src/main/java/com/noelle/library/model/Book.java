package com.noelle.library.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "book_id")
    private String id;

    @Column(name = "bookName",  nullable=false)
    private String bookName;

    @Column(name = "bookUrl")
    private String bookUrl;

    @Column(name = "borrowed")
    private boolean borrowed;


}
