package com.stevekung.springboot.elasticsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stevekung.springboot.elasticsearch.model.InsuranceForm;
import com.stevekung.springboot.elasticsearch.repo.InsuranceFormRepository;

@RestController
@RequestMapping("/api/v1/insurance_form")
public class InsuranceFormController
{
    @Autowired
    private InsuranceFormRepository insuranceFormRepository;

    @GetMapping("/")
    public List<InsuranceForm> getAll()
    {
        return StreamUtils.createStreamFromIterator(this.insuranceFormRepository.findAll().iterator()).toList();
    }

    @GetMapping("/status/{status}")
    public List<InsuranceForm> getFormsByStatus(@PathVariable("status") String status)
    {
        return this.insuranceFormRepository.findByStatus(status);
    }
}