package com.example.slovaro_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name="sources")
public class Source {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    //@ManyToOne()
    //@JoinColumn(name="userid")
    @Column(name="userid")
    int userId;
    @Column(name = "name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
