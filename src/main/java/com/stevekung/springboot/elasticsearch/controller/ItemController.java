package com.stevekung.springboot.elasticsearch.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stevekung.springboot.elasticsearch.model.Item;
import com.stevekung.springboot.elasticsearch.repo.ItemRepository;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController
{
    @Autowired
    private ItemRepository itemService;

    @GetMapping("/")
    public List<Item> getAll()
    {
        return StreamSupport.stream(this.itemService.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public List<Item> getItemByName(@PathVariable("name") String name)
    {
        return this.itemService.findByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Item> getItemsByCategory(@PathVariable("category") String category)
    {
        return this.itemService.findByCategory(category);
    }

    @GetMapping("/prices/{low}/{high}")
    public List<Item> getItemsByPriceRange(@PathVariable("low") double low, @PathVariable("high") double high)
    {
        return this.itemService.findByPriceBetween(low, high);
    }
}