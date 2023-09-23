package com.example.lab6.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationID", nullable = false)
    private Integer locationid;

    @Column(name = "City")
    private String city;

    @Column(name = "Country")
    private String country;

}
