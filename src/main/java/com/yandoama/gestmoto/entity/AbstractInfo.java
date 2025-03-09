package com.yandoama.gestmoto.entity;

import com.yandoama.gestmoto.entity.enums.EStatut;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class AbstractInfo {

    @Column(name="statut")
    @Enumerated(EnumType.STRING)
    private EStatut statut;
}
