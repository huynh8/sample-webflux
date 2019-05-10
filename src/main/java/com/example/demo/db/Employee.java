package com.example.demo.db;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@JsonDeserialize(builder = Employee.EmployeeBuilder.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("employees")
public class Employee {
    @Id
    Integer id;
    String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class EmployeeBuilder {

    }
}
