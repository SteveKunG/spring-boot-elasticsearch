package com.stevekung.springboot.elasticsearch.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.stevekung.springboot.elasticsearch.model.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Integer>
{
    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(Double low, Double high);
}