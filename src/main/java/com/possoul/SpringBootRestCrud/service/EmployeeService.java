package com.possoul.SpringBootRestCrud.service;

import java.util.List;

import com.possoul.SpringBootRestCrud.ProxyDto.EmployeeCart;
import com.possoul.SpringBootRestCrud.entity.Employee;


public interface EmployeeService {
	
public List<Employee> findAll();

public Employee findById(int theId);

public void save(Employee theEmployee);

public void deleteById(int theId);

}
