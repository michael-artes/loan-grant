package com.loan.grant.service;

import com.loan.grant.dto.PersonDTO;
import com.loan.grant.exceptions.PersonNotFoundException;
import com.loan.grant.model.Person;
import com.loan.grant.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setIdentifier(personDTO.getIdentifier());
        person.setBirthDate(personDTO.getBirthDate());
        person.setIdentifierType(personDTO.getIdentifier());

        person = personRepository.save(person);
        return convertToDTO(person);
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));

        person.setName(personDTO.getName());
        person.setIdentifier(personDTO.getIdentifier());
        person.setBirthDate(personDTO.getBirthDate());
        person.setIdentifierType(personDTO.getIdentifier());

        person = personRepository.save(person);
        return convertToDTO(person);
    }

    public PersonDTO getPerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return convertToDTO(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    private PersonDTO convertToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setIdentifier(person.getIdentifier());
        personDTO.setBirthDate(person.getBirthDate());
        personDTO.setIdentifierType(person.getIdentifierType());
        personDTO.setMinimumInstallmentValue(person.getMinimumInstallmentValue());
        personDTO.setMaximumLoanValue(person.getMaximumLoanValue());
        return personDTO;
    }
}
