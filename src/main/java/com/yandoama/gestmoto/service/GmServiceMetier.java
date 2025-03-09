package com.yandoama.gestmoto.service;

import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.entity.GmEntreprise;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.mapstruct.GmMapper;
import com.yandoama.gestmoto.repository.GmEntrepriseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GmServiceMetier {

    private final GmMapper mapper;
    private final GmEntrepriseRepository entrepriseRepository;


    /**
     * Permet de retourner toutes les entreprises.
     *
     * @return List of enterprises
     */
    public List<GmEntrepriseDto> doGetEntreprises() {
        List<GmEntreprise> entreprises = this.entrepriseRepository.findAll();
        return entreprises.stream()
                .map(mapper::maps)
                .collect(Collectors.toList());

    }

    /**
     * Permet de retourner un element a partir de son id.
     *
     * @param id
     * @return a enterprise
     */

    public GmEntrepriseDto doGetEntrepriseById(final String id) {
        GmEntreprise entreprise = this.entrepriseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entreprise introuvable.")
        );
        return this.mapper.maps(entreprise);

    }

    /**
     * Permet de verifier si une entreprise existe déja.
     * Si l'entreprise ne possede pas encore de IFU(donc RCCM egalement), alors
     * je fais la verification avec le nom.
     *
     * @param dto
     */
    public void verifierUniciteEntreprise(GmEntrepriseDto dto) {
        if (dto.getIfu() == null && dto.getRccm() == null && !this.entrepriseRepository
                .existsByDenomination(dto.getDenomination())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette entreprise existe déja.");
        } else {
            if (this.entrepriseRepository
                    .existsByIfuAndRccmAndDenomination(dto.getIfu(), dto.getRccm(), dto.getDenomination())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette entreprise existe déja.");
            }
        }

    }

    /**
     * Permet d'enregistrer une entreprise.
     *
     * @param dto
     */
    public GmEntrepriseDto doPostEntreprise(final GmEntrepriseDto dto) {
        this.verifierUniciteEntreprise(dto);
        GmEntreprise entreprise = this.mapper.maps(dto);
        entreprise.setId(UUID.randomUUID().toString());
         entreprise = this.entrepriseRepository.save(entreprise);
         return this.mapper.maps(entreprise);
    }

    /**
     * Permet de modifier les informations d'une entreprise.
     *
     * @param id
     * @param dto
     */
    public void doPutEntreprise(final String id, GmEntrepriseDto dto) {
        GmEntreprise entreprise = this.mapper.maps(dto);
        entreprise.setId(id);
        this.entrepriseRepository.save(entreprise);

    }

    /**
     * Permet de supprimer une entreprise.
     *
     * @param id
     */
    public void doDeleteEnterprise(final String id) {
        this.entrepriseRepository.findById(id).ifPresent((entreprise) -> {
            entreprise.setStatut(EStatut.SUPPRIME);
            this.entrepriseRepository.save(entreprise);
        });

    }


}
