package com.epsi.spring.mg.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.epsi.spring.mg.demo.entities.Customer;
import com.epsi.spring.mg.demo.entities.Order;
import com.epsi.spring.mg.demo.entities.Product;
import com.epsi.spring.mg.demo.entities.User;
import com.epsi.spring.mg.demo.repositories.CustomerRepository;
import com.epsi.spring.mg.demo.repositories.OrderRepository;
import com.epsi.spring.mg.demo.repositories.ProductRepository;
import com.epsi.spring.mg.demo.repositories.UserRepository;
import com.epsi.spring.mg.demo.services.OrderService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initFixtures(
            OrderService orderService,
            UserRepository userRepo,
            ProductRepository productRepo,
            OrderRepository orderRepo,
            CustomerRepository customerRepo) {
        return (args) -> {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Initial Fixture
            final User user = new User()
                                    .setEmail("mick.g@domain.tld")
                                    .setPassword(passwordEncoder.encode("toor"))
                                    .setFirstname("Mickael")
                                    .setLastname("Gaillard")
                                    .setDob(LocalDateTime.parse("2000-08-08T00:00:00"));

            userRepo.save(user);
            userRepo.save(new User().setEmail("dav@domain.tld").setPassword(passwordEncoder.encode("toor"))
                                .setFirstname("David").setLastname("Cuomo"));
            userRepo.save(new User().setEmail("flo@domain.tld").setPassword(passwordEncoder.encode("toor"))
                                .setFirstname("Florian").setLastname("Sibois"));
            userRepo.save(new User().setEmail("ant@domain.tld").setPassword(passwordEncoder.encode("toor"))
                                .setFirstname("Antoine").setLastname("Castel"));

            final Product product = new Product().setName("Product 1").setPrice(new BigDecimal("24.455"));
            productRepo.save(product);
            productRepo.save(new Product().setName("Product 2").setPrice(new BigDecimal("240.00")));
            productRepo.save(new Product().setName("Product 3").setPrice(new BigDecimal("1.00")));

            final Customer customer = new Customer()
                                            .setName("EPSI")
                                            .setAddress("3 rue XXXX, 35000 Rennes");
            customerRepo.save(customer);

            final Order order = new Order(customer)
                .addItem(product, 1)
                .addItem(product, 2);
            orderRepo.save(order);

            orderService.refreshCountSale(order);

      };
    }
}
