package com.code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.springdemo.entity.Customer;
import com.code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	//Need to inject CustomerService
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel)
	{
		//Get Customers from DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		//Add the customers to the Model
		theModel.addAttribute("customer",theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//Create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		//Save the customer using our service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel)
	{
		//Get the customer from the database
		Customer theCustomer = customerService.getCustomer(theId);
		//Set customer to the model attribute to pre-populate the form
		theModel.addAttribute("customer",theCustomer);
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model theModel)
	{
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String searchName, Model theModel)
	{
		List<Customer> theCustomer = customerService.getCustomerByName(searchName);
		
		theModel.addAttribute("customer",theCustomer);
		
		return "list-customers";
	}
	
	
	
}
