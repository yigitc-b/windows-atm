package com.web_atm_denemesi.atm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
public class AtmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmBackendApplication.class, args);

        String localIp = getLocalIpAddress();

        System.out.println("\n----------------------------------------------------------");
        System.out.println("🚀 UYGULAMA BAŞARIYLA BAŞLATILDI!");
        System.out.println("🔗 Yerel (Local)     : http://localhost:8080");
        System.out.println("🌐 Ağ Erişimi (LAN)  : http://" + localIp + ":8080");
        System.out.println("----------------------------------------------------------\n");
    }

    private static String getLocalIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "127.0.0.1"; // Bir sorun oluşursa varsayılan loopback IP
        }
    }
}