package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmFournisseurDto extends AbstractInfoDto{

    private String id;

    private String idEntreprise;

    private String idUtilisateur;

    private GmUserDto user;
}
