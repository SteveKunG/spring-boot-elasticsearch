package com.stevekung.springboot.elasticsearch.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.stevekung.springboot.elasticsearch.model.Item;

@Service
public class CSVParser
{
    public List<Item> csvParser(String filePath)
    {
        List<Item> itemList = new ArrayList<>();
        try (var inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
                var inputStreamReader = new InputStreamReader(inputStream);
                var reader = new CSVReader(inputStreamReader);)
        {
            var headers = reader.readNext();
            String[] row;
            while ((row = reader.readNext()) != null)
            {
                var item = new Item();
                for (var i = 0; i < headers.length; i++)
                {
                    var header = headers[i];
                    var value = row[i];
                    if ("id".equalsIgnoreCase(header))
                    {
                        item.setId(Integer.parseInt(value));
                    }
                    if ("name".equalsIgnoreCase(header))
                    {
                        item.setName(value);
                    }
                    if ("price".equalsIgnoreCase(header))
                    {
                        item.setPrice(Double.valueOf(value));
                    }
                    if ("brand".equalsIgnoreCase(header))
                    {
                        item.setBrand(value);
                    }
                    if ("category".equalsIgnoreCase(header))
                    {
                        item.setCategory(value);
                    }
                }
                itemList.add(item);
            }
        }
        catch (CsvValidationException | IOException e)
        {
            throw new RuntimeException(e);
        }
        return itemList;
    }
}