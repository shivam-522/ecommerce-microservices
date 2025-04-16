package com.ecommerce.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.order_service.model.client.InventoryResponse;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @GetMapping("/api/inventory/{productId}")
    InventoryResponse checkStock(@PathVariable("productId") Long productId);

    @PutMapping("/api/inventory/reduce")
    void reduceStock(
        @RequestParam("productId") Long productId,
        @RequestParam("quantity") int quantity
    );
}