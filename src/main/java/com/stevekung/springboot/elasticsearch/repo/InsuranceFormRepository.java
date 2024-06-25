package com.stevekung.springboot.elasticsearch.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.stevekung.springboot.elasticsearch.model.InsuranceForm;

public interface InsuranceFormRepository extends ElasticsearchRepository<InsuranceForm, String>
{
    List<InsuranceForm> findByStatus(String status);
}