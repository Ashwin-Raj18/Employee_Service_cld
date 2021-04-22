//This is only for building purpose, not recommended for testing, It runs the whole application
package com.possoul.SpringBootRestCrud;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.possoul.SpringBootRestCrud.dao.EmployeeDao;
import com.possoul.SpringBootRestCrud.entity.Employee;
import com.possoul.SpringBootRestCrud.service.EmployeeServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestCrudApplicationTests {
	
	@MockBean
	EmployeeDao employeeDao;
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	@Test
	public void getUsersTest() {
		when(employeeDao.findAll()).thenReturn(Stream
				.of(new Employee("Danile", "Vergeze", "USA@fff"), new Employee("Huy", "donnu", "UK@qqq"))
				.collect(Collectors.toList()));
		assertEquals(2, employeeService.findAll().size());
	}
	


}
