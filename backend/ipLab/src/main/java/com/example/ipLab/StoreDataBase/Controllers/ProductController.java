package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id){
        return new ProductDTO(productService.getProduct(id));
    }

    @GetMapping
    public List<ProductDTO> getProducts(){
        return  productService.getAllProducts().stream()
                .map(ProductDTO::new)
                .toList();
    }

    @PostMapping
    public ProductDTO createProduct(@RequestParam("productName") String productName){
        final Product product = productService.addProduct(productName);
        return new ProductDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestParam("productName") String productName,
                                      @PathVariable Long id){
        return new ProductDTO(productService.updateProduct(id, productName));
    }

    @DeleteMapping("/{id}")
    public ProductDTO deleteProduct(@PathVariable Long id){
        return new ProductDTO(productService.deleteProduct(id));
    }

    @DeleteMapping()
    public void deleteAllProducts(){
        productService.deleteAllProducts();
    }
}
