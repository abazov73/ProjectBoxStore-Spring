package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.DTO.StoreDTO;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Service.StoreService;
import com.example.ipLab.WebConfiguration;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/store")
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
    public StoreDTO createStore(@RequestBody @Valid StoreDTO storeDTO){
        final Store store = storeService.addStore(storeDTO.getstoreName());
        return new StoreDTO(store);
    }

    @PutMapping("/{id}")
    public StoreDTO updateStore(@RequestBody @Valid StoreDTO storeDTO,
                                      @PathVariable Long id){
        return new StoreDTO(storeService.updateStore(id, storeDTO.getstoreName()));
    }

    @PutMapping("{id}/add")
    public ProductDTO addProduct(@PathVariable Long id,
                                 @RequestBody @Valid CustomerDTO customerDTO){
        return new ProductDTO(storeService.addProduct(id, customerDTO.getId()));
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
