package com.stevekung.springboot.elasticsearch.service;

import java.util.List;
import java.util.Optional;

import com.stevekung.springboot.elasticsearch.model.User;

public interface UserService
{
    User save(User entity);

    List<User> findAll();

    Optional<User> findById(String id);

    void deleteById(String id);

    List<User> findByAgeBetween(int startAge, int endAge);

    List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

    List<User> findByInsuranceForm_Status(String status);
}