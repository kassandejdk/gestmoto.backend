package com.yandoama.gestmoto.controller;

import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.service.GmServiceMetier;
import com.yandoama.gestmoto.utils.GmConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(GmConstants.URLS.BASE_URL)
public class GestMotoController {

    private final GmServiceMetier serviceMetier;


    /**
     * Return a set instance of an enterprise.
     *
     * @return a list of entreprise
     */
    @GetMapping(GmConstants.URLS.ENTREPRISE)
    public ResponseEntity<List<GmEntrepriseDto>> getEntreprises() {
        List<GmEntrepriseDto> entreprises = this.serviceMetier.doGetEntreprises();
        return new ResponseEntity<>(entreprises, HttpStatus.OK);
    }

    /**
     * Permit to return an instance of enterprise.
     *
     * @param id
     * @return an enterprise
     */
    @GetMapping(GmConstants.URLS.ENTREPRISE + "/{id}")
    public ResponseEntity<GmEntrepriseDto> getEntreprise(@PathVariable final String id) {
        GmEntrepriseDto entreprise = this.serviceMetier.doGetEntrepriseById(id);
        return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    /**
     * Permit to save an enterprise.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.ENTREPRISE)
    public ResponseEntity<GmEntrepriseDto> postEntreprise(@RequestBody final GmEntrepriseDto dto) {
       GmEntrepriseDto entrepriseDto= this.serviceMetier.doPostEntreprise(dto);
        return new ResponseEntity<>(entrepriseDto, HttpStatus.OK);
    }

    /**
     * Permit to modify enterprise's information.
     *
     * @param id
     * @param dto
     * @return a string
     */
    @PutMapping(GmConstants.URLS.ENTREPRISE)
    public ResponseEntity<String> putEntreprise(@PathVariable final String id, @RequestBody final GmEntrepriseDto dto) {
        this.serviceMetier.doPutEntreprise(id, dto);
        return new ResponseEntity<>("Entreprise modifiée avec succès", HttpStatus.OK);
    }

    /**
     * Permit to delete an entreprise.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.ENTREPRISE)
    public ResponseEntity<String> deleteEntreprise(@PathVariable final String id) {
        this.serviceMetier.doDeleteEnterprise(id);
        return new ResponseEntity<>("Entreprise supprimée avec succès", HttpStatus.OK);
    }


}
