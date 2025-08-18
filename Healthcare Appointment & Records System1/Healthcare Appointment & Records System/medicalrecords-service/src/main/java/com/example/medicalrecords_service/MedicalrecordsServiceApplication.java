    package com.example.medicalrecords_service;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

    @SpringBootApplication
    @EnableDiscoveryClient
    public class MedicalrecordsServiceApplication {
    	public static void main(String[] args) {
    		SpringApplication.run(MedicalrecordsServiceApplication.class, args);
    	}
    }
    