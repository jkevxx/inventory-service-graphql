package com.app.ecom;

import com.app.ecom.entity.Category;
import com.app.ecom.entity.Product;
import com.app.ecom.repository.ICategoryRepository;
import com.app.ecom.repository.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceGraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ICategoryRepository iCategoryRepository, IProductRepository iProductRepository){
		Random random = new Random();

		return args -> {
			List.of("Computers", "Printers", "Smartphones").forEach(
					cat -> {
						Category category = Category.builder().name(cat).build();
						iCategoryRepository.save(category);
					});
			iCategoryRepository.findAll().forEach(category -> {
				for(int i=0; i<10; i++){
					Product product = Product.builder()
							.id(UUID.randomUUID().toString())
							.name("Computer "+ i)
							.price(100  + Math.random()*4000)
							.amount(random.nextInt(100))
							.category(category)
							.build();
					iProductRepository.save(product);
				}
			});
		};
	}
}
