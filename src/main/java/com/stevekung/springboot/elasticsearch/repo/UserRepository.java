package com.stevekung.springboot.elasticsearch.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.stevekung.springboot.elasticsearch.model.User;

public interface UserRepository extends ElasticsearchRepository<User, String>
{
    List<User> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);

    Optional<User> findByFirstNameLike(String firstName);

    Optional<User> findByAddressContaining(String address);

    List<User> findByPrefixStartingWith(String prefix);

    List<User> findByEmailContains(String email);

    List<User> findByInsuranceFormIsNull();

    List<User> findByInsuranceForm_Status(String status);
}