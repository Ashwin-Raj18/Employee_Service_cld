//this project is spring JPA with hibernate implementation. Spring data provides additional layer of abstraction.

package com.possoul.SpringBootRestCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.possoul.SpringBootRestCrud")
@EnableCircuitBreaker
@EnableHystrixDashboard
public class FeignRestTempApp {

	public static void main(String[] args) {
		SpringApplication.run(FeignRestTempApp.class, args);
	}
	
	//get RestTempleteBean so that we can autowire
	@Bean(name="internal-api")
	@LoadBalanced                               //Important 
	public RestTemplate getRestTemplate1() {
		HttpComponentsClientHttpRequestFactory hccrhf = new HttpComponentsClientHttpRequestFactory();
		hccrhf.setConnectTimeout(3000);
	    return new RestTemplate();
	}
	
	@Bean(name="external-api")               //no loadbalancing
	public RestTemplate getRestTemplate2() {
		HttpComponentsClientHttpRequestFactory hccrhf = new HttpComponentsClientHttpRequestFactory();
		hccrhf.setConnectTimeout(3000);
	    return new RestTemplate();
	}

}
