package com.example.ecommerce;

import com.example.ecommerce.dao.CategoryRepo;
import com.example.ecommerce.dao.ProductRepo;
import com.example.ecommerce.entites.Category;
import com.example.ecommerce.entites.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class EcommerceApplication  implements CommandLineRunner {
    @Autowired

    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {

        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
        categoryRepo.save(new Category(null, "ordinateurs", null, null,null));
        categoryRepo.save(new Category(null, "prints", null, null,null));
        categoryRepo.save(new Category(null, "smart phones", null, null,null));

        categoryRepo.findAll().forEach(c -> {
            for (int i=0;i<10;i++){
            Product p=new Product();
            Random rnd=new Random();
            p.setName(RandomString.make(18));
            p.setCurrentPrice(100+rnd.nextInt(1000));
            p.setAvailable(rnd.nextBoolean());
            p.setPromotion(rnd.nextBoolean());
            p.setSelected(rnd.nextBoolean());
            p.setPhotoName("uknown.png");
            p.setCategory(c);
            productRepo.save(p);}
        });
    }
}