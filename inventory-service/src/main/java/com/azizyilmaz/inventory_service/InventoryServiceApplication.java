package com.azizyilmaz.inventory_service;

import com.azizyilmaz.inventory_service.model.Inventory;
import com.azizyilmaz.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("SKU-001");
            inventory.setQuantity(100);
            inventoryRepository.save(inventory);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("SKU-002");
            inventory2.setQuantity(0);
            inventoryRepository.save(inventory2);
        };
    }

}
