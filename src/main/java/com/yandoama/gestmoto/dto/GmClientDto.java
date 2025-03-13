package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GmClientDto extends AbstractInfoDto{

    private String id;

    private String profession;

    private String ville;

    private String province;

    private String idUtilisateur;

    private GmUserDto userDto;

    private String idEntreprise;
}
