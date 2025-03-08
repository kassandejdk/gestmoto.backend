package com.yandoama.gestmoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmPosteDto {

    private String id;

    @NotBlank(message = "Le libelle est obligatoire.")
    private String libelle;

    @NotBlank(message = "L'entreprise est obligatoire.")
    private String idEntreprise;
}
