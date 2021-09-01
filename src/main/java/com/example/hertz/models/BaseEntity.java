package com.example.hertz.models;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID Id;

    //Getters and setters omitted for brevity

    public UUID getId() {
        return Id;
    }
}