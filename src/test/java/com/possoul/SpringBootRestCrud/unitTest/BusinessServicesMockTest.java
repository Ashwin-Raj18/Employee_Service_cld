//This method is recommended as entire application wont run
package com.possoul.SpringBootRestCrud.unitTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.possoul.SpringBootRestCrud.dao.EmployeeDao;
import com.possoul.SpringBootRestCrud.entity.Employee;
import com.possoul.SpringBootRestCrud.service.EmployeeServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class BusinessServicesMockTest {
	
	@Mock
	EmployeeDao employeeDao;
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	@Test
	public void getUsersTest() {
		when(employeeDao.findAll()).thenReturn(Stream
				.of(new Employee("Danile", "Vergeze", "USA@fff"), new Employee("Huy", "donnu", "UK@qqq"))
				.collect(Collectors.toList()));
		assertEquals(2, employeeService.findAll().size());
	}
	@Test
	public void getUsersTest2() {
		when(employeeDao.findAll()).thenReturn(Stream
				.of(new Employee("Danile", "Vergeze", "USA@fff"), new Employee("Huy", "donnu", "UK@qqq"), new Employee("naga", "baba", "Unb@qqq"))
				.collect(Collectors.toList()));
		assertEquals(3, employeeService.findAll().size());
	}
}

