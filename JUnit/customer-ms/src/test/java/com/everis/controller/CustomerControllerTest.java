package com.everis.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.everis.entity.Customer;
import com.everis.entity.Region;
import com.everis.service.CustomerService;

public class CustomerControllerTest {
	
	@InjectMocks
	private CustomerController controller;
	
	@Mock
	private CustomerService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void whenListCustomerReturnSuccessfull() {
	
	Mockito.when(service.findCustomerAll()).thenReturn(getListCustomer());
	Mockito.when(service.findCustomersByRegion(Mockito.any())).thenReturn(getListCustomer());
	
	ResponseEntity<List<Customer>> testObserver=controller.listAllCustomer(1L);
	
	assertEquals(testObserver.getBody().size(),1);
	}
	@Test
	public void whenCreateCustomerReturnSuccessful() {

		Mockito.when(service.createCustomer(Mockito.any())).thenReturn(customerBuilder());

		ResponseEntity<Customer> testObserver = controller.createCustomer(customerBuilder());

		assertEquals(testObserver.getBody().getId(), 1L);

	}
	@Test
	public void whenFindCustomerReturnSuccessfull() {
		
		Mockito.when(service.getCustomer(Mockito.anyLong())).thenReturn(customerBuilder());
		
		ResponseEntity<Customer> testObserver=controller.getCustomer(1L);
		
		assertEquals(testObserver.getBody().getId(), 1L);
		//assertEquals(testObserver.getBody().get(1),1);
		}
	
	
	
	@Test
	public void whenUpdateCustomerReturnSuccessful() {

		Mockito.when(service.updateCustomer(Mockito.any())).thenReturn(customerBuilder());

		ResponseEntity<Customer> testObserver = controller.updateCustomer(1L, customerBuilder());

		assertEquals(testObserver.getBody().getId(), 1L);

	}
	

	@Test
	public void whenDeleteCustomerReturnSuccessful() {

		Mockito.when(service.deleteCustomer(Mockito.any())).thenReturn(customerBuilder());

		ResponseEntity<Customer> testObserver = controller.deleteCustomer(1L);

		assertEquals(testObserver.getBody().getId(), 1L);

	}
	private List<Customer> getListCustomer() {
		List<Customer> customers =new ArrayList<>();
		customers.add(customerBuilder());
		return customers;
	}

	private Customer customerBuilder() {
		
		return Customer.builder()
				.id(2L)
				.firstName("jUAN")
				.lastName("Perez")
				.numberID("12345678")
				.email("jperesgmail.com")
				.state("CREATED")
				.region(Region.builder()
						.id(1L)
						.name("La libertad")
						.build())
				.build();
	}
	

	

}
