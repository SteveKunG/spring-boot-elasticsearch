package com.stevekung.springboot.elasticsearch.utils;

import java.util.UUID;

public class Utils
{
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }
}