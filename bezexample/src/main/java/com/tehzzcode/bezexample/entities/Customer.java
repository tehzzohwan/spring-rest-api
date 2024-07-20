package com.tehzzcode.bezexample.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "subscribe")
    private boolean subscribe;

    public Customer(String name, int age, boolean subscribe) {
        this.name = name;
        this.age = age;
        this.subscribe = subscribe;
    }
}
