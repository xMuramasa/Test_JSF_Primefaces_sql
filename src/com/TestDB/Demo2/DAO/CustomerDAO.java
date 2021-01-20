package com.TestDB.Demo2.DAO;

import java.util.List;

import com.TestDB.Demo2.Model.Customer;

public interface CustomerDAO {
	List<Customer> getFilter(String Name) throws Exception;
	List<Customer> filterByAddressOrName(String Name) throws Exception;
	
	Customer getCustomer(Long id) throws Exception;
	
	Boolean AddCustomer(Customer customer) throws Exception;
	Boolean DeleteCustomer(Customer customer) throws Exception;

	Boolean UpdateCustomer(Customer customer) throws Exception;
	int countGenero(String gender) throws Exception;
	List<String> getColumn(String Name) throws Exception;

	
}
