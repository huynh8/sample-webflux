package com.example.demo.mvc;

import com.example.demo.db.Employee;
import com.example.demo.db.EmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeReactiveController {

    EmployeeRepository employeeRepository;

    public EmployeeReactiveController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeRepository.save(Employee.builder().name(employeeRequest.getName()).build());
    }
}
