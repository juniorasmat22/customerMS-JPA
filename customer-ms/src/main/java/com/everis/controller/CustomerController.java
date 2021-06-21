package com.everis.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.everis.entity.Customer;
import com.everis.entity.Region;
import com.everis.service.CustomerService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> listAllCustomer(@RequestParam(name= "regionId" , required = false) Long regionId){
		List<Customer> customers= new ArrayList<Customer>();
		if (regionId==null) {
			customers=customerService.findCustomerAll();
		}else {
			Region region=new Region();
			region.setId(regionId);
			customers=customerService.findCustomersByRegion(region);
			if (null == customers) {
				log.error("Customers with Region id {} not found.", regionId);
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(customers);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
		log.info("Feching customer with id {}",id);
		Customer customer=customerService.getCustomer(id);
		if (customer==null) {
			log.error("Customer with Id {} not found",id);
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer,BindingResult result){
		log.info("Creating Customer : {}", customer);
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Customer customerBD= customerService.createCustomer(customer);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(customerBD);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id,@RequestBody Customer customer){
		log.info("Updating Customer with id {}",id);
		Customer currentCustomer=customerService.getCustomer(id);
		if (currentCustomer==null) {
			log.error("Unable to update. Customer with id {} not found");
			return ResponseEntity.notFound().build();
		}
		customer.setId(id);
		currentCustomer=customerService.updateCustomer(customer);
		return ResponseEntity.ok(currentCustomer);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
		log.info("Feching & Deleting Customer with id {}",id);
		Customer customer=customerService.getCustomer(id);
		if (customer==null) {
			log.error("Unable to delete. Customer with id {} not found",id);
			return ResponseEntity.notFound().build();
		}
		customer=customerService.deleteCustomer(customer);
		return ResponseEntity.ok(customer);

	}
	
	/*private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());
		ErrorMessages errorMessage = ErrorMessages.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}*/
}
