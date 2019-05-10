package com.example.demo.mvc;

import com.example.demo.db.Employee;
import com.example.demo.db.EmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@FieldDefaults(level = AccessLevel.PRIVATE)
class EmployeeReactiveControllerTest {

    WebTestClient webTestClient;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        webTestClient = WebTestClient.bindToController(new EmployeeReactiveController(employeeRepository)).build();
    }

    @Test
    void getAllEmployees() {
        String name1 = "Tom";
        String name2 = "Jerry";
        Mockito.when(employeeRepository.findAll()).thenReturn(Flux.just(Employee.builder().id(1).name(name1).build(), Employee.builder().id(2).name(name2).build()));

        webTestClient.get().uri("/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("Tom")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].name").isEqualTo("Jerry");
    }

    @Test
    void create() {
        String name = "Ivan";
        Mockito.when(employeeRepository.save(any()))
                .thenReturn(Mono.just(Employee.builder().id(1).name(name).build()));

        EmployeeRequest employeeRequest = new EmployeeRequest(name);
        webTestClient.post().uri("/employees")
                .body(BodyInserters.fromObject(employeeRequest))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo(name);

        verify(employeeRepository).save(any());
    }
}