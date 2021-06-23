package com.everis.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.everis.entity.Customer;

@RepositoryRestResource(path = "customers", collectionResourceRel = "customersJSON")
public interface CustomerRepository  extends JpaRepository<Customer, Long>{

	
	@Query(value = "Select * from tbl_customers c where c.region_id=:id",nativeQuery = true)
	public List<Customer> findByRegion(@Param("id")  int idRegion);
	
	@Query(value = "Select * from tbl_customers c where c.state=:state",nativeQuery = true)
	public List<Customer> findByStatus(@Param("state")  String status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tbl_customers SET state= 'DELETED' WHERE id=:id",nativeQuery = true)
	public void delete(@Param("id") int id);
	


}
