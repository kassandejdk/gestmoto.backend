package com.yandoama.gestmoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmUserDto {
    private String id;

    @NotBlank(message="Le nom de famille est obligatoire.")
    private String nom;

    @NotBlank(message="Le prénom est obligatoire.")
    private String prenom;

    private String telephone;

    private String email;

    private String password;

    private String adresse;

    @NotBlank(message = "L'entreprise est obligatoire.")
    private String idEntreprise;
}
