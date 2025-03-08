package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class GmEmployeDto {

    private String id;

    private Float salaire;

    @NotBlank(message = "L'entreprise est obligatoire.")
    private String idEntreprise;


    private String idPoste;


    private String idUser;

    private GmUserDto userDto;
}
