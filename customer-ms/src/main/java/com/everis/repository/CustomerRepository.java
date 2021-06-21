package com.everis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.entity.Customer;
import com.everis.entity.Region;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

	public List<Customer> findByRegion(Region region);

}
