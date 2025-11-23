package com.rr.jprog_interface.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
public class Program {
    private int id;
    private String name;
    private String description;

    public Program(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
