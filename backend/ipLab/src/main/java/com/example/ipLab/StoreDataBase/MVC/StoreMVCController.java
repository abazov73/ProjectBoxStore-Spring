package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.CustomerDTO;
import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.DTO.StoreDTO;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import com.example.ipLab.StoreDataBase.Service.StoreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreMVCController {
    private final StoreService storeService;
    private final ProductService productService;

    public StoreMVCController(StoreService storeService, ProductService productService) {
        this.storeService = storeService;
        this.productService = productService;
    }

    @GetMapping
    public String getStores(Model model) {
        model.addAttribute("stores",
                storeService.getAllStores().stream()
                        .map(StoreDTO::new)
                        .toList());
        return "store";
    }

    @GetMapping(value = {"/edit/", "/edit/{id}"})
    public String editStore(@PathVariable(required = false) Long id,
                               Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("storeDTO", new StoreDTO());
        } else {
            model.addAttribute("storeId", id);
            model.addAttribute("storeDTO", new StoreDTO(storeService.getStore(id)));
        }
        return "store-edit";
    }

    @GetMapping(value = "/addToStore")
    public String addToStore(@PathVariable(required = false) Long id,
                            Model model) {
        model.addAttribute("stores", storeService.getAllStores().stream().map(StoreDTO::new).toList());
        model.addAttribute("products", productService.getAllProductsWithoutStores().stream().map(ProductDTO::new).toList());
        return "addToStore";
    }

    @PostMapping(value = {"/", "/{id}"})
    public String saveStore(@PathVariable(required = false) Long id,
                               @ModelAttribute @Valid StoreDTO storeDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "store-edit";
        }
        if (id == null || id <= 0) {
            storeService.addStore(storeDto.getstoreName());
        } else {
            storeService.updateStore(id, storeDto.getstoreName());
        }
        return "redirect:/store";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(value = "storeId") Long storeId,
                             @RequestParam(value = "productId") Long  productId
                             ){
        storeService.addProduct(storeId, productId);
        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return "redirect:/store";
    }
}
