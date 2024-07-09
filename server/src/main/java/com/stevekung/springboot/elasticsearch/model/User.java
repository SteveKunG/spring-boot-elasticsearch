package com.stevekung.springboot.elasticsearch.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
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
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String prefix;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Text)
    private String phoneNumber;

    @Field(type = FieldType.Date, format = DateFormat.strict_year_month_day, pattern = "uuuu-MM-dd")
    private LocalDate birthDate;

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