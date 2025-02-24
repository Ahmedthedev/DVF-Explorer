package com.example.dvfexplorer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bien_immobilier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BienImmobilier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Builder.Default
    private String reference = "ref-default";
    
    @NotNull
    @Builder.Default
    private String cour = "cour-default";
    
    @NotNull
    @Column(name = "sale_date_unix")
    @Builder.Default
    private java.sql.Timestamp saleDateUnix = new java.sql.Timestamp(System.currentTimeMillis());
    
    @NotNull
    @Column(name = "sale_date")
    @Builder.Default
    private String saleDate = "2025-01-01";
    
    @NotNull
    @Builder.Default
    private String type = "type-default";
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @NotNull
    @Builder.Default
    private String status = "status-default";
    
    private Double surface;
    
    @NotNull
    @Column(name = "starting_price")
    @Builder.Default
    private Double startingPrice = 100000.0;
    
    @Column(name = "ending_price")
    private Double endingPrice;
    
    @NotNull
    @Builder.Default
    private String commune = "commune-default";
    
    @Column(name = "code_postal")
    private String codePostal;
    
    @Column(name = "numero_voie")
    private String numeroVoie;
    
    @Column(name = "nom_voie")
    private String nomVoie;
    
    @NotNull
    @Builder.Default
    private String latitude = "0.0";
    
    @NotNull
    @Builder.Default
    private String longitude = "0.0";
    
    @Column(columnDefinition = "TEXT")
    private String visits;
    
    private String url;

    // Informations avocat
    @Column(name = "avocat_nom")
    private String avocatNom;
    
    @Column(name = "avocat_numero_voie")
    private String avocatNumeroVoie;
    
    @Column(name = "avocat_nom_voie")
    private String avocatNomVoie;
    
    @Column(name = "avocat_code_postal")
    private String avocatCodePostal;
    
    @Column(name = "avocat_commune")
    private String avocatCommune;
    
    @Column(name = "avocat_phone_number")
    private String avocatPhoneNumber;
    
    @Column(name = "avocat_email")
    private String avocatEmail;
    
    @Column(name = "avocat_nickname")
    private String avocatNickname;

    // Métadonnées
    @Column(columnDefinition = "TEXT")
    private String annexes;
    
    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
    @Column(name = "code_commune_insee")
    private String codeCommuneInsee;
    
    @Column(name = "nombre_chambre")
    private Integer nombreChambre;
    
    private String dpe;
    
    private Double estimation;
    
    @Column(name = "google_street_view")
    private String googleStreetView;
}




