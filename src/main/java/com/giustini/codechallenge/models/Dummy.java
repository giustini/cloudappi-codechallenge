package com.giustini.codechallenge.models;

public class Dummy {

    private Integer id;
    private String name;

    public Dummy() {
        this.id = 1;
        this.name = "Hi! I'm a dummy object";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
