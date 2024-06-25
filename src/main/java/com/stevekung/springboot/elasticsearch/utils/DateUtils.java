package com.stevekung.springboot.elasticsearch.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils
{
    public static Date date(String dateString)
    {
        return Date.from(LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}