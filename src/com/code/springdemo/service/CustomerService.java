package com.code.springdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.code.springdemo.entity.Customer;

@Service
public interface CustomerService 
{
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> getCustomerByName(String searchName);
}
