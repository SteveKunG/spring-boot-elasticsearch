package com.stevekung.springboot.elasticsearch.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "image")
@Document(indexName = "user")
public class User
{
    @Id
    @ReadOnlyProperty
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String prefix;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date birthDate;
    
    @Field(type = FieldType.Binary)
    private byte[] image;

    @Field(type = FieldType.Text)
    private String cardId;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Object)
    private InsuranceForm insuranceForm;

    public String getFullName()
    {
        return this.getPrefix() + " " + this.getFirstName() + " " + this.getLastName();
    }
}