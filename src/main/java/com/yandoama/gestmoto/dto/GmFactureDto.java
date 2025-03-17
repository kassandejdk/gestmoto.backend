package com.yandoama.gestmoto.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GmFactureDto extends AbstractInfoDto {
    private String id;

    private LocalDate date;

    private String idEntreprise;

    private String idClient;

    @Valid
    @NotNull(message = "La facture doit être liée à un client.")
    private GmClientDto client;

    private String idMoto;

    @Valid
    @NotNull(message = "La facture doit être liée à une moto.")
    private GmMotoDto moto;
}
