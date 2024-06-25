package com.stevekung.springboot.elasticsearch.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stevekung.springboot.elasticsearch.model.User;
import com.stevekung.springboot.elasticsearch.service.UserService;
import com.stevekung.springboot.elasticsearch.utils.Utils;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(@RequestParam(value = "sort", required = false, defaultValue = "fullname") String sort, @RequestParam(value = "order", required = false, defaultValue = "asc") String order)
    {
        var list = this.userService.findAll();

        switch (sort)
        {
            case "fullname" -> list.sort(Comparator.comparing(User::getFullName));
            case "prefix" -> list.sort(Comparator.comparing(User::getPrefix));
            case "birthdate" -> list.sort(Comparator.comparing(User::getBirthDate));
            case "form_status" -> list.sort(Comparator.comparing(userx -> userx.getInsuranceForm() != null ? userx.getInsuranceForm().getStatus() : userx.getFullName()));
        }
        if (order.equals("desc"))
        {
            list = list.reversed();
        }
        return list;
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

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) throws IOException
    {
        if (user.getId() == null)
        {
            user.setId(Utils.randomUUID());
        }

        System.out.println("User saved");
        this.userService.userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}