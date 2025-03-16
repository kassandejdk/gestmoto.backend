package com.yandoama.gestmoto.controller;

import com.yandoama.gestmoto.dto.GmClientDto;
import com.yandoama.gestmoto.dto.GmEmployeDto;
import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.dto.GmFactureDto;
import com.yandoama.gestmoto.dto.GmFournisseurDto;
import com.yandoama.gestmoto.dto.GmGenreDto;
import com.yandoama.gestmoto.dto.GmModeleDto;
import com.yandoama.gestmoto.dto.GmMotoDto;
import com.yandoama.gestmoto.dto.GmPosteDto;
import com.yandoama.gestmoto.dto.GmUserDto;
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
        GmEntrepriseDto entrepriseDto = this.serviceMetier.doPostEntreprise(dto);
        return new ResponseEntity<>(entrepriseDto, HttpStatus.OK);
    }

    /**
     * Permit to modify enterprise's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.ENTREPRISE + "/{id}")
    public ResponseEntity<GmEntrepriseDto> putEntreprise(@PathVariable final String id, @RequestBody final GmEntrepriseDto dto) {
        GmEntrepriseDto entreprise = this.serviceMetier.doUpdateEntreprise(id, dto);
        return new ResponseEntity<>(entreprise, HttpStatus.OK);
    }

    /**
     * Permit to delete an entreprise.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.ENTREPRISE + "/{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable final String id) {
        this.serviceMetier.doDeleteEnterprise(id);
        return new ResponseEntity<>("Entreprise supprimée avec succès", HttpStatus.OK);
    }


    /**
     * Return a set instance of an motorbike.
     *
     * @return a list of motorbike
     */
    @GetMapping(GmConstants.URLS.MOTO + "entreprise/{idEntreprise}")
    public ResponseEntity<List<GmMotoDto>> getMotos(@PathVariable final String idEntreprise) {
        List<GmMotoDto> motos = this.serviceMetier.doGetMotos(idEntreprise);
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
        GmMotoDto moto = this.serviceMetier.doPostMoto(dto);
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
    @GetMapping(GmConstants.URLS.CLIENT + "/entreprise/{id}")
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
        GmClientDto client = this.serviceMetier.doPostClient(dto);
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


    /**
     * Return a set instance of an employe.
     *
     * @return a list of employe
     */
    @GetMapping(GmConstants.URLS.EMPLOYE + "entreprise/{id}")
    public ResponseEntity<List<GmEmployeDto>> getEmployes(final String id) {
        List<GmEmployeDto> employes = this.serviceMetier.doGetEmployes(id);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    /**
     * Permit to return an instance of employe.
     *
     * @param id
     * @return an employe
     */
    @GetMapping(GmConstants.URLS.EMPLOYE + "/{id}")
    public ResponseEntity<GmEmployeDto> getEmploye(@PathVariable final String id) {
        GmEmployeDto employe = this.serviceMetier.doGetEmployeById(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    /**
     * Permit to save an employe's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.EMPLOYE)
    public ResponseEntity<GmEmployeDto> postEmploye(@RequestBody final GmEmployeDto dto) {
        GmEmployeDto employe = this.serviceMetier.doPostEmploye(dto);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    /**
     * Permit to modify employe's information.
     *
     * @param id
     * @param dto
     * @return a string
     */
    @PutMapping(GmConstants.URLS.EMPLOYE)
    public ResponseEntity<String> updateEmploye(@PathVariable final String id, @RequestBody final GmEmployeDto dto) {
        this.serviceMetier.doPutEmploye(id, dto);
        return new ResponseEntity<>("Employé modifié avec succès", HttpStatus.OK);
    }

    /**
     * Permit to delete an employe.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.EMPLOYE)
    public ResponseEntity<String> deleteEmploye(@PathVariable final String id) {
        this.serviceMetier.doDeleteEmploye(id);
        return new ResponseEntity<>("Employé supprimé avec succès", HttpStatus.OK);
    }

    /**
     * Return a set instance of an invoice.
     *
     * @return a list of invoice
     */
    @GetMapping(GmConstants.URLS.FACTURE + "entreprise/{id}")
    public ResponseEntity<List<GmFactureDto>> getFactures(final String id) {
        List<GmFactureDto> factures = this.serviceMetier.doGetFactures(id);
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    /**
     * Permit to return an instance of invoice.
     *
     * @param id
     * @return an invoice
     */
    @GetMapping(GmConstants.URLS.FACTURE + "/{id}")
    public ResponseEntity<GmFactureDto> getFacture(@PathVariable final String id) {
        GmFactureDto facture = this.serviceMetier.doGetFactureById(id);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    /**
     * Permit to save an invoice's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.FACTURE)
    public ResponseEntity<GmFactureDto> postFacture(@RequestBody final GmFactureDto dto) {
        GmFactureDto facture = this.serviceMetier.doPostFacture(dto);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    /**
     * Permit to modify invoice's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.FACTURE)
    public ResponseEntity<GmFactureDto> updateFacture(@PathVariable final String id, @RequestBody final GmFactureDto dto) {
        GmFactureDto facture = this.serviceMetier.doUpdateFacture(id, dto);
        return new ResponseEntity<>(facture, HttpStatus.OK);
    }

    /**
     * Permit to delete an invoice.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.FACTURE)
    public ResponseEntity<String> deleteFacture(@PathVariable final String id) {
        this.serviceMetier.doDeleteFacture(id);
        return new ResponseEntity<>("Facture supprimée avec succès", HttpStatus.OK);
    }

    /**
     * Return a set instance of an supplier.
     *
     * @return a list of supplier
     */
    @GetMapping(GmConstants.URLS.FOURNISSEUR + "entreprise/{id}")
    public ResponseEntity<List<GmFournisseurDto>> getFournisseurs(final String id) {
        List<GmFournisseurDto> fournisseurs = this.serviceMetier.doGetFournisseurs(id);
        return new ResponseEntity<>(fournisseurs, HttpStatus.OK);
    }

    /**
     * Permit to return an supplier of invoice.
     *
     * @param id
     * @return an supplier
     */
    @GetMapping(GmConstants.URLS.FOURNISSEUR + "/{id}")
    public ResponseEntity<GmFournisseurDto> getFournisseur(@PathVariable final String id) {
        GmFournisseurDto fournisseur = this.serviceMetier.doGetFournisseurById(id);
        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

    /**
     * Permit to save an supplier's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.FOURNISSEUR)
    public ResponseEntity<GmFournisseurDto> postFournisseur(@RequestBody final GmFournisseurDto dto) {
        GmFournisseurDto fournisseur = this.serviceMetier.doPostFournisseur(dto);
        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

    /**
     * Permit to modify supplier's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.FOURNISSEUR)
    public ResponseEntity<GmFournisseurDto> updateFournisseur(@PathVariable final String id,
                                                              @RequestBody final GmFournisseurDto dto) {
        GmFournisseurDto fournisseur = this.serviceMetier.doPutFournisseur(id, dto);
        return new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

    /**
     * Permit to delete an supplier.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.FOURNISSEUR)
    public ResponseEntity<String> deleteFournisseur(@PathVariable final String id) {
        this.serviceMetier.doDeleteFournisseur(id);
        return new ResponseEntity<>("Fournisseur supprimé avec succès", HttpStatus.OK);
    }

    /**
     * Return a set instance of a gender.
     *
     * @return a list of supplier
     */
    @GetMapping(GmConstants.URLS.GENRE + "entreprise/{id}")
    public ResponseEntity<List<GmGenreDto>> getGenres(final String id) {
        List<GmGenreDto> genres = this.serviceMetier.doGetGenres(id);
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    /**
     * Permit to return an gender of invoice.
     *
     * @param id
     * @return an gender
     */
    @GetMapping(GmConstants.URLS.GENRE + "/{id}")
    public ResponseEntity<GmGenreDto> getGenre(@PathVariable final String id) {
        GmGenreDto genre = this.serviceMetier.doGetGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    /**
     * Permit to save an gender's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.GENRE)
    public ResponseEntity<GmGenreDto> postGenre(@RequestBody final GmGenreDto dto) {
        GmGenreDto genre = this.serviceMetier.doPostGenre(dto);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    /**
     * Permit to modify gender's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.GENRE)
    public ResponseEntity<GmGenreDto> updateFournisseur(@PathVariable final String id,
                                                        @RequestBody final GmGenreDto dto) {
        GmGenreDto genre = this.serviceMetier.doPutGenre(id, dto);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    /**
     * Permit to delete an gender.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.GENRE)
    public ResponseEntity<String> deleteGenre(@PathVariable final String id) {
        this.serviceMetier.doDeleteGenre(id);
        return new ResponseEntity<>("Genre supprimé avec succès", HttpStatus.OK);
    }

    /**
     * Return a set instance of an model.
     *
     * @return a list of model
     */
    @GetMapping(GmConstants.URLS.MODELE + "entreprise/{id}")
    public ResponseEntity<List<GmModeleDto>> getModeles(final String id) {
        List<GmModeleDto> modeles = this.serviceMetier.doGetModeles(id);
        return new ResponseEntity<>(modeles, HttpStatus.OK);
    }

    /**
     * Permit to return an model of invoice.
     *
     * @param id
     * @return an model
     */
    @GetMapping(GmConstants.URLS.MODELE + "/{id}")
    public ResponseEntity<GmModeleDto> getModele(@PathVariable final String id) {
        GmModeleDto modele = this.serviceMetier.doGetModeleById(id);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }

    /**
     * Permit to save an model's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.MODELE)
    public ResponseEntity<GmModeleDto> postModele(@RequestBody final GmModeleDto dto) {
        GmModeleDto modele = this.serviceMetier.doPostModele(dto);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }

    /**
     * Permit to modify model's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.MODELE)
    public ResponseEntity<GmModeleDto> updateModele(@PathVariable final String id,
                                                    @RequestBody final GmModeleDto dto) {
        GmModeleDto modele = this.serviceMetier.doPutModele(id, dto);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }

    /**
     * Permit to delete an model.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.MODELE)
    public ResponseEntity<String> deleteModele(@PathVariable final String id) {
        this.serviceMetier.doDeleteModele(id);
        return new ResponseEntity<>("Modèle supprimé avec succès", HttpStatus.OK);
    }


    /**
     * Return a set instance of an work.
     *
     * @return a list of work
     */
    @GetMapping(GmConstants.URLS.POSTE + "entreprise/{id}")
    public ResponseEntity<List<GmPosteDto>> getPostes(final String id) {
        List<GmPosteDto> postes = this.serviceMetier.doGetPostes(id);
        return new ResponseEntity<>(postes, HttpStatus.OK);
    }

    /**
     * Permit to return an work of invoice.
     *
     * @param id
     * @return an work
     */
    @GetMapping(GmConstants.URLS.POSTE + "/{id}")
    public ResponseEntity<GmPosteDto> getPoste(@PathVariable final String id) {
        GmPosteDto poste = this.serviceMetier.doGetPosteById(id);
        return new ResponseEntity<>(poste, HttpStatus.OK);
    }

    /**
     * Permit to save an work's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.POSTE)
    public ResponseEntity<GmPosteDto> postPoste(@RequestBody final GmPosteDto dto) {
        GmPosteDto poste = this.serviceMetier.doPostPoste(dto);
        return new ResponseEntity<>(poste, HttpStatus.OK);
    }

    /**
     * Permit to modify work's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.POSTE)
    public ResponseEntity<GmPosteDto> updatePoste(@PathVariable final String id,
                                                  @RequestBody final GmPosteDto dto) {
        GmPosteDto poste = this.serviceMetier.doPutPoste(id, dto);
        return new ResponseEntity<>(poste, HttpStatus.OK);
    }

    /**
     * Permit to delete an work.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.POSTE)
    public ResponseEntity<String> deletePoste(@PathVariable final String id) {
        this.serviceMetier.doDeletePoste(id);
        return new ResponseEntity<>("Poste supprimé avec succès", HttpStatus.OK);
    }


    /**
     * Return a set instance of an user.
     *
     * @return a list of user
     */
    @GetMapping(GmConstants.URLS.USER + "entreprise/{id}")
    public ResponseEntity<List<GmUserDto>> getUsers(final String id) {
        List<GmUserDto> users = this.serviceMetier.doGetUsers(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Permit to return an user information.
     *
     * @param id
     * @return an user
     */
    @GetMapping(GmConstants.URLS.USER + "/{id}")
    public ResponseEntity<GmUserDto> getUser(@PathVariable final String id) {
        GmUserDto user = this.serviceMetier.doGetUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Permit to save an user's information.
     *
     * @param dto
     * @return a sentence
     */
    @PostMapping(GmConstants.URLS.USER)
    public ResponseEntity<GmUserDto> postUser(@RequestBody final GmUserDto dto) {
        GmUserDto user = this.serviceMetier.doPostUser(dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Permit to modify user's information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    @PutMapping(GmConstants.URLS.USER)
    public ResponseEntity<GmUserDto> updateUser(@PathVariable final String id,
                                                @RequestBody final GmUserDto dto) {
        GmUserDto user = this.serviceMetier.doPutUser(id, dto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Permit to delete an user.
     *
     * @param id
     * @return String
     */
    @DeleteMapping(GmConstants.URLS.USER)
    public ResponseEntity<String> deleteUser(@PathVariable final String id) {
        this.serviceMetier.doDeleteUser(id);
        return new ResponseEntity<>("Utilisateur supprimé avec succès", HttpStatus.OK);
    }

}
