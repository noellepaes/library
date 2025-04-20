package com.noelle.library.service;


import com.noelle.library.exeption.ResourceNotFoundException;
import com.noelle.library.model.Book;
import com.noelle.library.model.Loan;
import com.noelle.library.model.Person;
import com.noelle.library.repository.BookRepository;
import com.noelle.library.repository.LoanRepository;
import com.noelle.library.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {


    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;


    @Transactional
    public Loan createLoan(Loan loan) {

        Book book = bookRepository.findById(loan.getBook().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado: " 
            + loan.getBook().getId()));
       
        Person person = personRepository.findById(loan.getPerson().getCpf())
            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada: " 
                + loan.getPerson().getCpf()));
       
        loan.setBorrowedDate(LocalDate.now());
        loan.setReturnDate(loan.getBorrowedDate().plusMonths(1));


        book.setBorrowed(true);
        bookRepository.save(book);
        person.setAllowedBorrow(false);
        personRepository.save(person);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(String id) {
        return loanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado com ID: " + id));
    }

    @Transactional
    public Loan updateLoan(String id, Loan updated) {
        return loanRepository.findById(id)
            .map(loan -> {
                loan.setBorrowedDate(updated.getBorrowedDate());
                loan.setReturnDate(updated.getReturnDate());
                return loanRepository.save(loan);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado com ID: " + id));
    }

    @Transactional
    public void deleteLoan(String id) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado com ID: " + id));
        Book book = loan.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);
        Person person = loan.getPerson();
        person.setAllowedBorrow(true);
        personRepository.save(person);
        loanRepository.delete(loan);
    }

    @Transactional
    public Loan returnLoan(String id) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Empréstimo não encontrado com ID: " + id));

        Book book = loan.getBook();
        book.setBorrowed(false);
        bookRepository.save(book);

        Person person = loan.getPerson();
        if (LocalDate.now().isAfter(loan.getReturnDate())) {
            person.setFined(true);
            person.setAllowedBorrow(false);
        } else {
            person.setAllowedBorrow(true);
        }
        personRepository.save(person);

        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);
    }
}

