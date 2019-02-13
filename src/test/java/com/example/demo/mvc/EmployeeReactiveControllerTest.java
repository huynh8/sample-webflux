package com.example.demo.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
class EmployeeReactiveControllerTest {

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(new EmployeeReactiveController()).build();
    }

    @Test
    void getAllEmployees() throws IOException {
        FluxExchangeResult<Employee> employeeFluxExchangeResult = webTestClient.get().uri("/employees")
                .exchange()
                .returnResult(Employee.class);

        assertEquals(HttpStatus.OK, employeeFluxExchangeResult.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();

        List<Employee> employees = objectMapper.readValue(employeeFluxExchangeResult.getResponseBodyContent(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));

        assertEquals("1", employees.get(0).getId());
        assertEquals("Bill", employees.get(0).getName());
    }
}