package com.noelle.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noelle.library.exeption.ResourceNotFoundException;
import com.noelle.library.model.Book;
import com.noelle.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Book update(String id, Book updatedBook) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setBookName(updatedBook.getBookName());
                book.setBookUrl(updatedBook.getBookUrl());
                book.setBorrowed(updatedBook.isBorrowed());
             
                return bookRepository.save(book);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com ID: " + id));
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findBorrowedBooks() {
        return bookRepository.findByBorrowedTrue();
    }
}
