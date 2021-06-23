package com.everis.service;

import java.util.List;

import com.everis.entity.Customer;

public interface CustomerService {
	public List<Customer> findCustomerAll();
	public List<Customer> findCustomersByRegion(int idRegion);
	public List<Customer> findCustomersByStatus(String state);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	public Customer getCustomer(Long id);
}
