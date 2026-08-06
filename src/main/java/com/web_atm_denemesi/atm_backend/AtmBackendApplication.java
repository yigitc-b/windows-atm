package com.web_atm_denemesi.atm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmBackendApplication.class, args);
        System.out.println("\n----------------------------------------------------------");
            System.out.println("🚀 UYGULAMA BAŞARIYLA BAŞLATILDI!");
            System.out.println("🔗 ANASAyfa: http://localhost:8080");
            System.out.println("----------------------------------------------------------\n");
	}

}
