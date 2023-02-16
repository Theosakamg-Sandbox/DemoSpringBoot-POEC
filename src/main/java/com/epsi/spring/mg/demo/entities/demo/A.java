package com.epsi.spring.mg.demo.entities.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tmp_a")
public class A {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(mappedBy = "relA")
    private List<B> relB;

    @ManyToOne
    @JoinColumn(name = "relB1_ID", referencedColumnName = "id")
    private B relB1;
}
