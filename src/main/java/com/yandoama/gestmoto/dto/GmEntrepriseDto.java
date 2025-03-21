package com.yandoama.gestmoto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GmEntrepriseDto extends AbstractInfoDto{

    private String id;

    @NotBlank(message = "Nom de l'entreprise obligatoire.")
    private String denomination;

    private String adresse;

    private String logoUrl;

    private String telephone;

    private String ifu;

    private String rccm;
}
