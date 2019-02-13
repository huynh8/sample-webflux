package com.example.demo.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/employees")
public class EmployeeReactiveController {

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        Employee employee1 = Employee.builder().id("1").name("Bill").build();
        Employee employee2 = Employee.builder().id("2").name("Ivan").build();

        return Flux.just(employee1, employee2);
    }
}
