package com.example.ecommerce.web;

import com.example.ecommerce.dao.ProductRepo;
import com.example.ecommerce.entites.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueController {

    private ProductRepo productRepo;

    public CatalogueController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product p=productRepo.findById(id).get();
        return  Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Desktop/product/"+p.getPhotoName()));
     }
}
