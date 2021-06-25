package com.everis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.everis.entity.Customer;
import com.everis.entity.Region;
import com.everis.repository.CustomerRepository;

public class CustomerServiceTest {
	

	private CustomerService service;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		//customerRepository = Mockito.mock(CustomerRepository.class);
		service=new CustomerServiceImpl(customerRepository);
	}
	
	@Test
	public void whenListCustomerReturnSuccessful() throws IOException {

		Mockito.when(customerRepository.findAll()).thenReturn(getListCustomers());

		List<Customer> testObserver = service.findCustomerAll();

		assertEquals(testObserver.size(), 1);

	}
	
	@Test
	public void whencreateCustomerReturnSuccessful() {

		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(getListCustomers());

		Customer testObserver = service.createCustomer(customerBuilder());

		assertEquals(testObserver.getId(), 1L);

	}
	
	private List<Customer> getListCustomers() {
		List<Customer> customers =new ArrayList<>();
		customers.add(customerBuilder());
		return customers;
	}

	private Customer customerBuilder() {
		
		return Customer.builder()
				.id(1L)
				.firstName("JUAN")
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
