package com.example.dvfexplorer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bien_immobilier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BienImmobilier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String cour;
    private java.sql.Timestamp saleDateUnix;
    private String saleDate;
    private String type;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String status;
    private Double surface;
    private Double startingPrice;
    private Double endingPrice;
    private String commune;
    private String codePostal;
    private String numeroVoie;
    private String nomVoie;
    private String latitude;
    private String longitude;
    @Column(columnDefinition = "TEXT")
    private String visits;
    private String url;

    // Informations avocat
    private String avocatNom;
    private String avocatNumeroVoie;
    private String avocatNomVoie;
    private String avocatCodePostal;
    private String avocatCommune;
    private String avocatPhoneNumber;
    private String avocatEmail;
    private String avocatNickname;

    // Métadonnées
    @Column(columnDefinition = "TEXT")
    private String annexes;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String codeCommuneInsee;
    private Integer nombreChambre;
    private String googleStreetView;

}
