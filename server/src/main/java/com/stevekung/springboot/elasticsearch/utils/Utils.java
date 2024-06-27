package com.stevekung.springboot.elasticsearch.utils;

import java.util.Random;
import java.util.UUID;

public class Utils
{
    public static String randomUUID()
    {
        String aString = "TEST_RANDOM_UUID" + new Random(69420);
        return UUID.nameUUIDFromBytes(aString.getBytes()).toString();
        // return UUID.randomUUID().toString();
    }
}