package com.stevekung.springboot.elasticsearch.service;

import java.util.List;

import com.stevekung.springboot.elasticsearch.model.Item;

public interface ItemService
{
    List<Item> findByName(String itemName);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);
}