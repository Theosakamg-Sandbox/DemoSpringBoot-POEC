package com.epsi.spring.mg.demo.services;

import org.springframework.stereotype.Service;

import com.epsi.spring.mg.demo.entities.Order;
import com.epsi.spring.mg.demo.entities.OrderItem;
import com.epsi.spring.mg.demo.entities.Product;
import com.epsi.spring.mg.demo.repositories.ProductRepository;

@Service
public class OrderService {

    private ProductRepository productRepo;

    public OrderService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void refreshCountSale(Order order) {
        for (final OrderItem item : order.getItems()) {
            if (item.getProduct() != null) {
                final Product product = this.productRepo.findById(item.getProduct().getId()).orElse(null);
                product.incrementSaleCount(item.getQuantity());

                this.productRepo.save(product);
            }
        }
    }

}
