package com.epsi.spring.mg.demo.entities.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tmp_b")
public class B {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(
            name="ab",
            joinColumns = { @JoinColumn(name = "b_id") },
            inverseJoinColumns = { @JoinColumn(name = "a_id")
            }
    )
    private List<A> relA;

    @OneToMany(mappedBy = "relB1")
    private List<A> relA1;
}
