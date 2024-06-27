package com.stevekung.springboot.elasticsearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.stevekung.springboot.elasticsearch.repo")
public class Config extends ElasticsearchConfiguration
{
    @Override
    public ClientConfiguration clientConfiguration()
    {
        return ClientConfiguration.builder().connectedTo("localhost:9200").withBasicAuth("elastic", "123456").build();
    }
}