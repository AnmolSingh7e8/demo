package com.example.demo.presentation.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Long dni;
    private String nom;
    private String cognom1;
    private String cognom2;
    private Date dataNaix;
    private String telefon;
    private String mail;
}


