package com.yandoama.gestmoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmGenreDto extends AbstractInfoDto{

    private String id;

    @NotBlank(message = "Le libellé est obligatoire.")
    private String libelle;

    @NotBlank(message = "L'entreprise est obligatoire.")
    private String idEntreprise;
}
