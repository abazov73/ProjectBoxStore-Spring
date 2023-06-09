package com.example.ipLab.StoreDataBase.Controllers;

import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.Model.CustomUser;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Service.ProductService;
import com.example.ipLab.WebConfiguration;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/product")
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
    public List<ProductDTO> getProducts(@AuthenticationPrincipal CustomUser user){
        if (user.getRole() == UserRole.USER){
            return  productService.getAllProductsWithStores().stream()
                    .map(ProductDTO::new)
                    .toList();
        }
        else{
            return  productService.getAllProducts().stream()
                    .map(ProductDTO::new)
                    .toList();
        }

    }

    @GetMapping("/getWithStores")
    public List<ProductDTO> getProductsWithStores(){
        return  productService.getAllProductsWithStores().stream()
                .map(ProductDTO::new)
                .toList();
    }

    @GetMapping("/getWithoutStores")
    public List<ProductDTO> getProductsWithoutStores(){
        return  productService.getAllProductsWithoutStores().stream()
                .map(ProductDTO::new)
                .toList();
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO){
        final Product product = productService.addProduct(productDTO.getproductName());
        return new ProductDTO(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDTO,
                                      @PathVariable Long id){
        return new ProductDTO(productService.updateProduct(id, productDTO.getproductName()));
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
