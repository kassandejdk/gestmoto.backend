package com.yandoama.gestmoto.controller;

import com.yandoama.gestmoto.dto.GmClientDto;
import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.dto.GmMotoDto;
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


    /**
     * Return a set instance of an motorbike.
     *
     * @return a list of motorbike
     */
    @GetMapping(GmConstants.URLS.MOTO + "/{id}")
    public ResponseEntity<List<GmMotoDto>> getMotos(final String id) {
        List<GmMotoDto> motos = this.serviceMetier.doGetMotos(id);
        return new ResponseEntity<>(motos, HttpStatus.OK);
    }

    /**
     * Permit to return an instance of motorbike.
     *
     * @param id
     * @return an motorbike
     */
    @GetMapping(GmConstants.URLS.MOTO + "/{id}")
    public ResponseEntity<GmMotoDto> getMoto(@PathVariable final String id) {
        GmMotoDto moto = this.serviceMetier.doGetMotoById(id);
        return new ResponseEntity<>(moto, HttpStatus.OK);
    }

    /**
     * Permit to save an motorbike's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.MOTO)
    public ResponseEntity<GmMotoDto> postMoto(@RequestBody final GmMotoDto dto) {
        GmMotoDto moto= this.serviceMetier.doPostMoto(dto);
        return new ResponseEntity<>(moto, HttpStatus.OK);
    }

    /**
     * Permit to modify motorbike's information.
     *
     * @param id
     * @param dto
     * @return a string
     */
    @PutMapping(GmConstants.URLS.MOTO)
    public ResponseEntity<String> updateMoto(@PathVariable final String id, @RequestBody final GmMotoDto dto) {
        this.serviceMetier.doUpdateMoto(id, dto);
        return new ResponseEntity<>("Moto modifiée avec succès", HttpStatus.OK);
    }

    /**
     * Permit to delete an motorbike.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.MOTO)
    public ResponseEntity<String> deleteMoto(@PathVariable final String id) {
        this.serviceMetier.doDeleteMoto(id);
        return new ResponseEntity<>("Moto supprimée avec succès", HttpStatus.OK);
    }

    /**
     * Return a set instance of an customer.
     *
     * @return a list of customer
     */
    @GetMapping(GmConstants.URLS.CLIENT + "/{id}")
    public ResponseEntity<List<GmClientDto>> getClients(final String id) {
        List<GmClientDto> clients = this.serviceMetier.doGetClients(id);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    /**
     * Permit to return an instance of customer.
     *
     * @param id
     * @return an customer
     */
    @GetMapping(GmConstants.URLS.CLIENT + "/{id}")
    public ResponseEntity<GmClientDto> getClient(@PathVariable final String id) {
        GmClientDto client = this.serviceMetier.doGetClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Permit to save an customer's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.CLIENT)
    public ResponseEntity<GmClientDto> postClient(@RequestBody final GmClientDto dto) {
        GmClientDto client= this.serviceMetier.doPostClient(dto);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    /**
     * Permit to modify customer's information.
     *
     * @param id
     * @param dto
     * @return a string
     */
    @PutMapping(GmConstants.URLS.CLIENT)
    public ResponseEntity<String> updateClient(@PathVariable final String id, @RequestBody final GmClientDto dto) {
        this.serviceMetier.doPutClient(id, dto);
        return new ResponseEntity<>("Client modifié avec succès", HttpStatus.OK);
    }

    /**
     * Permit to delete an customer.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.CLIENT)
    public ResponseEntity<String> deleteClient(@PathVariable final String id) {
        this.serviceMetier.doDeleteClient(id);
        return new ResponseEntity<>("Client supprimé avec succès", HttpStatus.OK);
    }


}
