package com.epsi.spring.mg.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "crm_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @OneToMany(mappedBy = "customer")
    private final List<Order> orders = new ArrayList<>();

}
