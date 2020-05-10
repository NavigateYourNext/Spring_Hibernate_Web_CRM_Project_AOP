package com.code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.springdemo.dao.CustomerDAO;
import com.code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService
{
	//Need to inject Customer DAO
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() 
	{
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDAO.saveCustomer(theCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId)
	{
		Customer theCustomer = customerDAO.getCustomer(theId);
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) 
	{
		customerDAO.deleteCustomer(theId);
		
	}
	
	@Override
	@Transactional
	public List<Customer> getCustomerByName(String searchName)
	{
		List<Customer> theCustomer = customerDAO.getCustomerByName(searchName);
		
		return theCustomer;
	}

}
