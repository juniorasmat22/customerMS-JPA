package com.everis.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.entity.Customer;
import com.everis.entity.Region;
import com.everis.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findCustomerAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
		return customerRepository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		customer.setState("CREATED");
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		
		Customer customerDB = getCustomer(customer.getId());
		if (customerDB == null) {
			return null;
		}
		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setEmail(customer.getEmail());
		customerDB.setPhotoUrl(customer.getPhotoUrl());
		return customerRepository.save(customerDB);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		Customer customerDB = getCustomer(customer.getId());
		if (customerDB == null) {
			return null;
		}
		customer.setState("DELETED");
		return customerRepository.save(customer);

	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

}
