package br.com.geosapiens.purchaseorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.geosapiens")
public class PurchaseOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseOrdersApplication.class, args);
    }

}
