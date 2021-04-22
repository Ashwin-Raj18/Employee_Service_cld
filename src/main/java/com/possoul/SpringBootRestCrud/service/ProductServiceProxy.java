//this class communicates with another SB application
package com.possoul.SpringBootRestCrud.service;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.possoul.SpringBootRestCrud.ProxyDto.EmployeeCart;


@FeignClient(name="shopping-service")
@LoadBalancerClient(name="shopping-service")
public interface ProductServiceProxy {
	
	  @LoadBalanced
	  @GetMapping("/getCart/{custId}")
	  public EmployeeCart retrieveCart(@PathVariable("custId") int custId);
}
	