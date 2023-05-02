package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductMVCController {
    private final ProductService productService;

    public ProductMVCController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products",
                productService.getAllProducts().stream()
                        .map(ProductDTO::new)
                        .toList());
        return "product";
    }

    @GetMapping(value = {"/edit/", "/edit/{id}"})
    public String editProduct(@PathVariable(required = false) Long id,
                            Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("productDTO", new ProductDTO());
        } else {
            model.addAttribute("productId", id);
            model.addAttribute("productDTO", new ProductDTO(productService.getProduct(id)));
        }
        return "product-edit";
    }

    @PostMapping(value = {"/", "/{id}"})
    public String saveProduct(@PathVariable(required = false) Long id,
                            @ModelAttribute @Valid ProductDTO productDto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "product-edit";
        }
        if (id == null || id <= 0) {
            productService.addProduct(productDto.getproductName());
        } else {
            productService.updateProduct(id, productDto.getproductName());
        }
        return "redirect:/product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
}
