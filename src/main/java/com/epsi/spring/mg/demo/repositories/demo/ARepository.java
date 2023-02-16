package com.epsi.spring.mg.demo.repositories.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsi.spring.mg.demo.entities.demo.A;

public interface ARepository  extends JpaRepository<A, Integer> {

}
