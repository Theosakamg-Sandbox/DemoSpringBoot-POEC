package com.epsi.spring.mg.demo.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pim_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private BigDecimal price = new BigDecimal(10000);

    @Column(nullable = false)
    private int saleCount = 0;

    public void incrementSaleCount(int quantity) {
        this.saleCount += quantity;
    }
}
