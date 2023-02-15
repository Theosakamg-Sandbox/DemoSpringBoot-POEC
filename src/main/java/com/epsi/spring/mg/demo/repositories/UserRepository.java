package com.epsi.spring.mg.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsi.spring.mg.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByLastname(String lastname);

    List<User> findByLastnameAndFirstname(String lastname, String Firstname);

}
