
package com.ecommerce.inventory_service.controller;

import com.ecommerce.inventory_service.model.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import com.ecommerce.inventory_service.service.InventoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/{productId}")
    //@GetMapping("/inventory/{productId}")
    public ResponseEntity<?> isInStock(@PathVariable Long productId) {
    	
    	System.out.println("Product Id is -----> "+productId);
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            return ResponseEntity.ok(inventory); // returns JSON with quantity, etc.
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("status", false);
            response.put("message", "Product not found in inventory");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory saved = inventoryService.saveInventory(inventory);
        return ResponseEntity.status(201).body(saved);
    }
    
    @PutMapping("/reduce")
    public void reduceStock(@RequestParam("productId") Long productId,@RequestParam("quantity") int quantity) {
    	String status=inventoryService.reduceStock(productId,quantity);
    	System.out.println();
    	System.out.println(status);
    }
    
    
    
    
    
    
    
    
}