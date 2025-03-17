package com.yandoama.gestmoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GmUserDto extends AbstractInfoDto{
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
