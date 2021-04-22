package com.possoul.SpringBootRestCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.possoul.SpringBootRestCrud.ProxyDto.EmployeeCart;
import com.possoul.SpringBootRestCrud.dao.EmployeeDao;
import com.possoul.SpringBootRestCrud.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Override
	@Transactional
	public List<Employee> findAll() {
	
		return employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
	
		return employeeDao.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		
		employeeDao.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDao.deleteById(theId);

	}

}
