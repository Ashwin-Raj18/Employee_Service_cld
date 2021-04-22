package com.possoul.SpringBootRestCrud.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.possoul.SpringBootRestCrud.ProxyDto.EmployeeCart;
import com.possoul.SpringBootRestCrud.ProxyDto.ProductDetails;
import com.possoul.SpringBootRestCrud.entity.Employee;
import com.possoul.SpringBootRestCrud.service.EmployeeService;
import com.possoul.SpringBootRestCrud.service.ProductServiceProxy;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EmployeeRestcontroller {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ProductServiceProxy productServiceProxy; 
	
	@Autowired
	@Qualifier("internal-api")
	RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("external-api")
	RestTemplate restTemplatExt;

	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("employee ID not found"+ employeeId);
		}
		
		return theEmployee;
		}
	
	@PostMapping("/employees")   //we are posting into database
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		//just in case if they pass the ID in json...set it to zero
		//this is force save of new  item instead of updating them
		
		theEmployee.setId(0);            
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public ErrorObject deleteEmployee(@PathVariable int employeeId) {
		
		//First get that employee from dataBase
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("no employee exists with id "+ employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return  new ErrorObject("Record deleted with EmpID "+employeeId);
	}
	
	
	@GetMapping("/empCart/feign/{empId}")
	public EmployeeCart getEmployeeAndCartFeign(@PathVariable("empId") int empId) {
		
		EmployeeCart ec = productServiceProxy.retrieveCart(empId);
		return ec;
	}
	
	
	//same as above but using restTemplete
	@GetMapping("/empCart/restTemp/{empId}")
	@HystrixCommand(fallbackMethod = "getEmployeeAndCartRestFallBack",
					commandProperties = {
							@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
							//there are many other 
					}
			)
	public EmployeeCart getEmployeeAndCartRest(@PathVariable("empId") int empId) {
		
		EmployeeCart ec = restTemplate.getForObject("http://shopping-service/getCart/"+empId+"/", EmployeeCart.class);
		return ec;
	}
	
	
	
	//fallback method
	public EmployeeCart getEmployeeAndCartRestFallBack(@PathVariable("empId") int empId) {
		
		EmployeeCart ec = new EmployeeCart("default name", 00, Arrays.asList(new ProductDetails(00, "default prod", 00)));
		return ec;
	}
	
	
	
	//to test timeouts
	@GetMapping("/empCart/timeout/post")
	public String getExtPost() {
		
		String ec = restTemplatExt.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
		return ec;
	}
	
}
