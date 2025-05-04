package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class PersonaEntity {
    @Id
    private Long dni;
    private String nom;
    private String cognom1;
    private String cognom2;

    @Column(name = "data_naix")
    private Date dataNaix;
    private String telefon;
    private String mail;
}