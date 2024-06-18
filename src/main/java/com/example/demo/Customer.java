package com.example.demo;

import javax.annotation.Nonnull;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(indexName = "customer")
@Getter
@Setter
public class Customer {
    @Id
    private String id;

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @Nonnull
    private Integer age;

    @Nonnull
    private String email;
  
}