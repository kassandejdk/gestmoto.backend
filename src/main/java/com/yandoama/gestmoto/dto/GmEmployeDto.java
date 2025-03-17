package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GmEmployeDto extends AbstractInfoDto{

    private String id;

    private Float salaire;

    @NotBlank(message = "L'entreprise est obligatoire.")
    private String idEntreprise;


    private String idPoste;


    private String idUtilisateur;

    private GmUserDto userDto;
}
