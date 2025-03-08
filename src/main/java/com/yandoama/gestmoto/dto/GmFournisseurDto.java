package com.yandoama.gestmoto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GmFournisseurDto {

    private String id;

    private String idEntreprise;

    private String idUser;

    private GmUserDto user;
}
