package com.loan.grant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loan.grant.LoanGrantApplicationTests;
import com.loan.grant.dto.PersonDTO;
import com.loan.grant.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = LoanGrantApplicationTests.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCreatePerson() throws Exception {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("User test");
        personDTO.setIdentifier("12345678901");
        personDTO.setBirthDate(LocalDate.of(2000, 1, 1));

        mockMvc.perform(post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("User test"))
                .andExpect(jsonPath("$.identifier").value("12345678901"));
    }
}