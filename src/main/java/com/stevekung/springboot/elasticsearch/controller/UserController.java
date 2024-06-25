package com.stevekung.springboot.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll()
    {
        return this.userService.findAll();
    }

    @GetMapping("/{name}")
    public List<User> getUsersByName(@PathVariable("name") String name)
    {
        return this.userService.userRepository.findByFirstNameLikeOrLastNameLike(name, name);
    }

    @GetMapping("/status/{status}")
    public List<User> getUsersByStatus(@PathVariable("status") String status)
    {
        return this.userService.userRepository.findByInsuranceForm_Status(status);
    }

    @GetMapping("/age")
    public List<User> getUsersByAgeRange(@RequestParam("start") int start, @RequestParam("end") int end)
    {
        return this.userService.findByAgeBetween(start, end);
    }
}