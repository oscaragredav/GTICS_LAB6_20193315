package com.example.lab6.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siteID", nullable = false)
    private Integer sideid;

    @Column(name = "sitename")
    private String sitename;

    @ManyToOne
    @JoinColumn(name = "locationID")
    private Location location;

    @Column(name = "installationdate")
    private LocalDate installationdate;

    @Column(name = "latitude", length = 45)
    private String latitude;

    @Column(name = "longitude", length = 45)
    private String longitude;

}