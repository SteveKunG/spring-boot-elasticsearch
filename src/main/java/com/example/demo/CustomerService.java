package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	public List<Customer> retrieveCustomers() {
		return repository.findAll().getContent();
	}

	public Optional<Customer> retrieveCustomers(String id) {
		return repository.findById(id);
	}

	public List<Customer> retrieveCustomersByName(String name) {
		return repository.findByFirstName(name);
	}

	public Customer createCustomer(Customer customer) {
		return repository.save(customer);
	}

	public Optional<Customer> updateCustomer(String id, Customer customer) {
		Optional<Customer> customerOpt = repository.findById(id);
		if (!customerOpt.isPresent()) {
			return customerOpt;
		}
		customer.setId(id);
		return Optional.of(repository.save(customer));
	}

	public boolean deleteCustomer(String id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}