-- Borramos la tabla region si ya existe
drop table if exists tbl_region;
 -- Borramos la tabla customers si ya existe
drop table if exists tbl_customers;
  -- Creamos la tabla region
 create table tbl_region (
   id SERIAL PRIMARY KEY,
   name varchar(50)
 );
  -- Creamos la tabla customers 
 create table tbl_customers (
   id SERIAL PRIMARY KEY,
	numberId varchar(50),
	firstName varchar(50),
	 lastName varchar(50),
	 email varchar(50),
	 photoUrl varchar(50),
	 state varchar(50),
	 region_id INT,
	 CONSTRAINT fk_customers_region
      FOREIGN KEY(region_id) 
	  REFERENCES tbl_region(id)
 );
 