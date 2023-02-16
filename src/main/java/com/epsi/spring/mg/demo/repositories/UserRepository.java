package com.epsi.spring.mg.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsi.spring.mg.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastname(String lastname);

    List<User> findByLastnameAndFirstname(String lastname, String Firstname);

    Optional<User> findByEmail(String email);

}
