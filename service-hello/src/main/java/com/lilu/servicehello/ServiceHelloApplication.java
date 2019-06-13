package com.lilu.servicehello;

import java.util.logging.Level;
import java.util.logging.Logger;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@RestController
public class ServiceHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHelloApplication.class, args);
	}

	public static final Logger LOGGER = Logger.getLogger(ServiceHelloApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@RequestMapping("/hello")
	public String callHome(){
		LOGGER.log(Level.INFO, "calling trace service-hello  ");
		return restTemplate.getForObject("http://localhost:5002/hello", String.class);
	}
	@RequestMapping("/info")
	public String info(){
		LOGGER.log(Level.INFO, "calling trace service-hello ");

		return "i'm service-hello";

	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

}












