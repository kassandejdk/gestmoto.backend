package com.yandoama.gestmoto.dto;

import com.yandoama.gestmoto.entity.enums.EStatut;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AbstractInfoDto {

    private EStatut statut;
}
