package com.stevekung.springboot.elasticsearch.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.repo.UserRepository;

@Service
public class UserService
{
    @Autowired
    public UserRepository userRepository;

    public List<User> findAll()
    {
        return StreamUtils.createStreamFromIterator(this.userRepository.findAll().iterator()).toList();
    }

    public List<User> findByAgeBetween(int startAge, int endAge)
    {
        return this.findAll().stream().filter(userx ->
        {
            var start = userx.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            var end = LocalDate.now();
            var years = ChronoUnit.YEARS.between(start, end);
            return years >= startAge && years <= endAge;
        }).toList();
    }
}