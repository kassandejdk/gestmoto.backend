package com.yandoama.gestmoto.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GmFactureDto extends AbstractInfoDto{
    private String id;

    private LocalDate date;

    private String idEntreprise;

    private String idClient;

    @Valid
    @NotNull(message = "La facture doit être liée à un client.")
    private GmClientDto client;

    private String idMoto;

    @Valid
    @NotNull(message="La facture doit être liée à une moto.")
    private GmMotoDto moto;
}
