package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.DTO.StoreDTO;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Service.CustomerService;
import com.example.ipLab.StoreDataBase.Service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService){
        this.storeService = storeService;
    }

    @GetMapping("/{id}")
    public StoreDTO getStore(@PathVariable Long id){
        return new StoreDTO(storeService.getStore(id));
    }

    @GetMapping
    public List<StoreDTO> getStores(){
        return  storeService.getAllStores().stream()
                .map(StoreDTO::new)
                .toList();
    }

    @PostMapping
    public StoreDTO createStore(@RequestParam("storeName") String storeName){
        final Store store = storeService.addStore(storeName);
        return new StoreDTO(store);
    }

    @PutMapping("/{id}")
    public StoreDTO updateStore(@RequestParam("storeName") String storeName,
                                      @PathVariable Long id){
        return new StoreDTO(storeService.updateStore(id, storeName));
    }

    @PutMapping("/add")
    public ProductDTO addProduct(@RequestParam("storeId") Long storeId,
                                 @RequestParam("productId") Long productId){
        return new ProductDTO(storeService.addProduct(storeId, productId));
    }

    @DeleteMapping("/{id}")
    public StoreDTO deleteStore(@PathVariable Long id){
        return new StoreDTO(storeService.deleteStore(id));
    }

    @DeleteMapping()
    public void deleteAllStores(){
        storeService.deleteAllStores();
    }
}
