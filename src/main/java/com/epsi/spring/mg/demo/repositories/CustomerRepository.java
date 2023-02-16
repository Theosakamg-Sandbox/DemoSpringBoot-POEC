package com.epsi.spring.mg.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsi.spring.mg.demo.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
