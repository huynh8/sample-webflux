package com.example.demo.mvc;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@JsonDeserialize(builder = Employee.EmployeeBuilder.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class Employee {
    String id;
    String name;

    @JsonPOJOBuilder(withPrefix = "")
    static final class EmployeeBuilder {

    }
}
