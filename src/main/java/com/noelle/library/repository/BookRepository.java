package com.noelle.library.repository;

import com.noelle.library.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByBorrowedTrue();
}
