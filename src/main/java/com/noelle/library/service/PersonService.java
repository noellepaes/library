package com.noelle.library.service;

import com.noelle.library.model.Person;
import com.noelle.library.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    public Person update(String id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setCpf(updatedPerson.getCpf());
                    person.setName(updatedPerson.getName());
                    person.setFined(updatedPerson.isFined());
                    person.setAllowedBorrow(updatedPerson.isAllowedBorrow());

                    return personRepository.save(person);
                })
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
    }

    public void delete(String id) {
        personRepository.deleteById(id);
    }
}
