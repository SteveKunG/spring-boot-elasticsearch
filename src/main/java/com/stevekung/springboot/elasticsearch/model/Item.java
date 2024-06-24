package com.stevekung.springboot.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(indexName = "itemindex")
public class Item
{
    @Id
    private int id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Double, name = "price")
    private Double price;
    @Field(type = FieldType.Keyword, name = "brand")
    private String brand;
    @Field(type = FieldType.Keyword, name = "category")
    private String category;
}