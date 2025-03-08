package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmClientDto {

    private String id;

    private String profession;

    private String ville;

    private String province;

    private String idUser;

    private GmUserDto userDto;

    private String idEntreprise;
}
