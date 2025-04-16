

package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.model.Inventory;
import com.ecommerce.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean isInStock(Long productId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);
        
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            return inventory.getQuantity() > 0;
        } else {
            return false;
        }
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    
    
    public String reduceStock(Long productId, int quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findByProductId(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            int currentQty = inventory.getQuantity();

            if (currentQty >= quantity) {
                inventory.setQuantity(currentQty - quantity);
                inventoryRepository.save(inventory);
                return "Stock reduced successfully";
            } else {
                throw new RuntimeException("Insufficient stock to reduce");
            }

        } else {
            throw new RuntimeException("Product not found in inventory");
        }
    }
}
