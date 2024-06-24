package com.stevekung.springboot.elasticsearch.config;

import java.util.Comparator;

import org.springframework.context.annotation.Configuration;

import com.stevekung.springboot.elasticsearch.model.Item;
import com.stevekung.springboot.elasticsearch.repo.ItemRepository;
import com.stevekung.springboot.elasticsearch.service.CSVParser;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataSetup
{
    private final ItemRepository itemRepository;
    private final CSVParser csvParser;

    @PostConstruct
    public void setupData()
    {
        var itemList = this.csvParser.csvParser("items.csv");
        itemList.set(0, new Item(0, "aaa", 1000.0, "b", "c"));
        itemList.sort(Comparator.comparing(Item::getId));
        this.itemRepository.saveAll(itemList);
    }
}