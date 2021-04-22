package com.possoul.SpringBootRestCrud.dao;

import java.util.List;

import com.possoul.SpringBootRestCrud.entity.Employee;

public interface EmployeeDao {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	

}
