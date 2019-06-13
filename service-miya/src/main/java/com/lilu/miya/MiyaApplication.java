package com.lilu.miya;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class MiyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiyaApplication.class, args);
	}

	public static final Logger LOG = Logger.getLogger(MiyaApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("hello")
	public String hello(){
		LOG.log(Level.INFO,"calling");

		return "Hi, i am miya";
	}

	@RequestMapping("/miya")
	public String info(){
		LOG.log(Level.INFO,"info is being called");
		return restTemplate.getForObject("http://localhost:5001/info",String.class);
	}

	@Bean
	public Sampler defualtSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}


}
