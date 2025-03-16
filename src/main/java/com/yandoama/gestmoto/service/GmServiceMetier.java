package com.yandoama.gestmoto.service;

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
import com.yandoama.gestmoto.entity.GmClient;
import com.yandoama.gestmoto.entity.GmEmploye;
import com.yandoama.gestmoto.entity.GmEntreprise;
import com.yandoama.gestmoto.entity.GmFacture;
import com.yandoama.gestmoto.entity.GmFournisseur;
import com.yandoama.gestmoto.entity.GmGenre;
import com.yandoama.gestmoto.entity.GmModele;
import com.yandoama.gestmoto.entity.GmMoto;
import com.yandoama.gestmoto.entity.GmPoste;
import com.yandoama.gestmoto.entity.GmUser;
import com.yandoama.gestmoto.entity.enums.EStatut;
import com.yandoama.gestmoto.mapstruct.GmMapper;
import com.yandoama.gestmoto.repository.GmClientRepository;
import com.yandoama.gestmoto.repository.GmEmployeRepository;
import com.yandoama.gestmoto.repository.GmEntrepriseRepository;
import com.yandoama.gestmoto.repository.GmFactureRepository;
import com.yandoama.gestmoto.repository.GmFournisseurRepository;
import com.yandoama.gestmoto.repository.GmGenreRepository;
import com.yandoama.gestmoto.repository.GmModeleRespository;
import com.yandoama.gestmoto.repository.GmMotoRepository;
import com.yandoama.gestmoto.repository.GmPosteRepository;
import com.yandoama.gestmoto.repository.GmUserRepository;
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
    private final GmUserRepository userRepository;
    private final GmClientRepository clientRepository;
    private final GmEmployeRepository employeRepository;
    private final GmModeleRespository modeleRespository;
    private final GmGenreRepository genreRepository;
    private final GmFournisseurRepository fournisseurRepository;
    private final GmPosteRepository posteRepository;
    private final GmMotoRepository motoRepository;
    private final GmFactureRepository factureRepository;

    /**
     * Permet de retourner toutes les entreprises.
     *
     * @return List of enterprises
     */
    public List<GmEntrepriseDto> doGetEntreprises() {
        List<GmEntreprise> entreprises = this.entrepriseRepository.findByStatut(EStatut.ACTIF);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette entreprise existe déja.");
        } else {
            if (this.entrepriseRepository
                    .existsByIfuAndRccmAndDenominationAndStatut(dto.getIfu(), dto.getRccm(),
                            dto.getDenomination(), EStatut.ACTIF)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette entreprise existe déja.");
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
        entreprise.setStatut(EStatut.ACTIF);
        entreprise = this.entrepriseRepository.save(entreprise);
        return this.mapper.maps(entreprise);
    }

    /**
     * Permet de modifier les informations d'une entreprise.
     *
     * @param id
     * @param dto
     */
    public GmEntrepriseDto doUpdateEntreprise(final String id, GmEntrepriseDto dto) {


       GmEntreprise entreprise = this.mapper.maps(dto);
        entreprise.setId(id);
        entreprise = this.entrepriseRepository.save(entreprise);
        return this.mapper.maps(entreprise);
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

    /**
     * Retourner les users
     *
     * @return List of GmUserDto
     */
    public List<GmUserDto> doGetUsers(final String idEntreprise) {
        List<GmUser> users = this.userRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return users.stream().map(mapper::maps).collect(Collectors.toList());

    }

    /**
     * Permet de renvoyer un utilisateur à partir de son id.
     *
     * @param id
     * @return a user
     */
    public GmUserDto doGetUserById(final String id) {
        GmUser user = this.userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable.")
        );
        return this.mapper.maps(user);

    }

    /**
     * Permet de s'assurer que l'utilisateur n'est pas en doublon.
     *
     * @param id
     * @param dto
     */
    public void checkDuplicateUser(final String id, final GmUserDto dto) {
        if (this.userRepository.checkDuplicateUser(id, dto.getNom(), dto.getPrenom(), dto.getStatut(), dto.getIdEntreprise(), dto.getTelephone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utilisateur existe déjà");
        }
    }

    /**
     * Permet de creer un utilisateur.
     *
     * @param dto
     * @return a user
     */
    public GmUserDto doPostUser(final GmUserDto dto) {
        this.checkDuplicateUser(null, dto);
        GmUser user = this.mapper.maps(dto);
        user.setId(UUID.randomUUID().toString());
        user.setStatut(EStatut.ACTIF);
        user = this.userRepository.save(user);
        return this.mapper.maps(user);
    }

    /**
     * Permet de mettre à jour les informations d'un utilisateur.
     *
     * @param id
     * @param dto
     * @return a user
     */
    public GmUserDto doPutUser(final String id, final GmUserDto dto) {
        this.checkDuplicateUser(id, dto);
        GmUser user = this.mapper.maps(dto);
        user.setId(id);
        user = this.userRepository.save(user);
        return this.mapper.maps(user);
    }

    /**
     * Permit to delete an user.
     *
     * @param id
     */
    public void doDeleteUser(final String id) {
        this.userRepository.findById(id).ifPresent((entreprise) -> {
            entreprise.setStatut(EStatut.SUPPRIME);
            this.userRepository.save(entreprise);
        });
    }

    /**
     * Return a customer according to the id.
     *
     * @param id
     * @return GmClientDto
     */
    public GmClientDto doGetClientById(final String id) {
        GmClient client = this.clientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le client n'existe pas"));
        return this.mapper.maps(client);
    }

    /**
     * Return a set of customer.
     *
     * @param idEntreprise
     * @return GmClientDto
     */
    public List<GmClientDto> doGetClients(final String idEntreprise) {
        List<GmClient> clients = this.clientRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return clients.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a customer.
     *
     * @param dto
     * @return a customer DTO
     */
    public GmClientDto doPostClient(final GmClientDto dto) {
        this.checkDuplicateUser(null, dto.getUserDto());
        GmUserDto user = this.doPostUser(dto.getUserDto());
        GmClient client = this.mapper.maps(dto);
        client.setId(UUID.randomUUID().toString());
        client.setStatut(EStatut.ACTIF);
        client.setUtilisateur(this.mapper.maps(user));
        client = this.clientRepository.save(client);
        return this.mapper.maps(client);
    }

    /**
     * Update customer's information.
     *
     * @param id
     * @param dto
     * @return customer DTO.
     */
    public GmClientDto doPutClient(final String id, final GmClientDto dto) {
        this.checkDuplicateUser(id, dto.getUserDto());
        this.doPutUser(dto.getIdUtilisateur(), dto.getUserDto());
        GmClient client = this.mapper.maps(dto);
        client.setId(id);
        client = this.clientRepository.save(client);
        return this.mapper.maps(client);
    }

    /**
     * Delete a customer.
     *
     * @param id
     */
    public void doDeleteClient(final String id) {
        this.clientRepository.findById(id).ifPresent((client) -> {
            client.setStatut(EStatut.SUPPRIME);
            this.clientRepository.save(client);
        });
    }

    /**
     * Get a employe information.
     *
     * @param id
     * @return employe DTO
     */
    public GmEmployeDto doGetEmployeById(final String id) {
        GmEmploye employe = this.employeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'employé n'existe pas."));
        return this.mapper.maps(employe);
    }

    /**
     * Get all employe.
     *
     * @return list of employe DTO
     */
    public List<GmEmployeDto> doGetEmployes(final String idEntreprise) {
        List<GmEmploye> employes = this.employeRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return employes.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save an employe.
     *
     * @param dto
     * @return employe DTO
     */
    public GmEmployeDto doPostEmploye(final GmEmployeDto dto) {
        this.checkDuplicateUser(null, dto.getUserDto());
        GmUserDto user = this.doPostUser(dto.getUserDto());
        GmEmploye employe = this.mapper.maps(dto);
        employe.setId(UUID.randomUUID().toString());
        employe.setStatut(EStatut.ACTIF);
        employe.setUtilisateur(this.mapper.maps(user));
        employe = this.employeRepository.save(employe);
        return this.mapper.maps(employe);
    }

    /**
     * Update employe information.
     *
     * @param id
     * @param dto
     * @return employe DTO
     */
    public GmEmployeDto doPutEmploye(final String id, final GmEmployeDto dto) {
        this.checkDuplicateUser(id, dto.getUserDto());
        this.doPutUser(dto.getIdUtilisateur(), dto.getUserDto());
        GmEmploye employe = this.mapper.maps(dto);
        employe.setId(id);
        employe = this.employeRepository.save(employe);
        return this.mapper.maps(employe);
    }

    /**
     * Delete an employe.
     *
     * @param id
     */
    public void doDeleteEmploye(final String id) {
        this.employeRepository.findById(id).ifPresent((employe) -> {
            employe.setStatut(EStatut.SUPPRIME);
            this.employeRepository.save(employe);
        });
    }

    /**
     * Verify existence information.
     *
     * @param dto
     */
    public void checkDuplicateGenre(final String id, final GmGenreDto dto) {
        if (this.genreRepository.checkDuplicateGenre(id, dto.getLibelle(),
                EStatut.ACTIF, dto.getIdEntreprise())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le genre existe déjà.");
        }
    }

    /**
     * Verify existence information.
     *
     * @param dto
     */
    public void checkDuplicateModele(final String id, final GmModeleDto dto) {
        if (this.modeleRespository.checkDuplicateModele(id, dto.getLibelle(),
                EStatut.ACTIF, dto.getIdEntreprise())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le modele existe déjà.");
        }
    }

    /**
     * Get a gender's motorbike information.
     *
     * @param id
     * @return a DTO
     */
    public GmGenreDto doGetGenreById(final String id) {
        GmGenre genre = this.genreRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le genre n'existe pas."));
        return this.mapper.maps(genre);
    }

    /**
     * Get all genres.
     *
     * @return a list of DTO
     */
    public List<GmGenreDto> doGetGenres(final String idEntreprise) {
        List<GmGenre> genres = this.genreRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return genres.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a genre.
     *
     * @param dto
     * @return a DTO
     */
    public GmGenreDto doPostGenre(final GmGenreDto dto) {
        this.checkDuplicateGenre(null, dto);
        GmGenre genre = this.mapper.maps(dto);
        genre.setId(UUID.randomUUID().toString());
        genre.setStatut(EStatut.ACTIF);
        genre = this.genreRepository.save(genre);
        return this.mapper.maps(genre);
    }

    /**
     * Update a genre information.
     *
     * @param id
     * @param dto
     * @return a DTO
     */
    public GmGenreDto doPutGenre(final String id, final GmGenreDto dto) {
        this.checkDuplicateGenre(id, dto);
        GmGenre genre = this.mapper.maps(dto);
        genre.setId(id);
        genre = this.genreRepository.save(genre);
        return this.mapper.maps(genre);
    }

    /**
     * Delete a genre.
     *
     * @param id
     */
    public void doDeleteGenre(final String id) {
        this.genreRepository.findById(id).ifPresent((genre) -> {
            genre.setStatut(EStatut.SUPPRIME);
            this.genreRepository.save(genre);
        });
    }

    /**
     * Get modele information.
     *
     * @param id
     * @return List of model
     */

    public GmModeleDto doGetModeleById(final String id) {
        GmModele modele = this.modeleRespository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le modèle n'existe pas."));
        return this.mapper.maps(modele);
    }

    /**
     * Get all models.
     *
     * @return List of model
     */
    public List<GmModeleDto> doGetModeles(final String idEntreprise) {
        List<GmModele> modeles = this.modeleRespository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return modeles.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a model.
     *
     * @param dto
     * @return DTO
     */
    public GmModeleDto doPostModele(final GmModeleDto dto) {
        this.checkDuplicateModele(null, dto);
        GmModele modele = this.mapper.maps(dto);
        modele.setId(UUID.randomUUID().toString());
        modele.setStatut(EStatut.ACTIF);
        modele = this.modeleRespository.save(modele);
        return this.mapper.maps(modele);
    }

    /**
     * Update model information.
     *
     * @param id
     * @param dto
     * @return DTO
     */
    public GmModeleDto doPutModele(final String id, final GmModeleDto dto) {
        this.checkDuplicateModele(id, dto);
        GmModele modele = this.mapper.maps(dto);
        modele.setId(id);
        modele = this.modeleRespository.save(modele);
        return this.mapper.maps(modele);
    }

    /**
     * Delete a model.
     *
     * @param id
     */
    public void doDeleteModele(final String id) {
        this.modeleRespository.findById(id).ifPresent((modele) -> {
            modele.setStatut(EStatut.SUPPRIME);
            this.modeleRespository.save(modele);
        });
    }

    /**
     * Get supplier information.
     *
     * @param id
     */
    public GmFournisseurDto doGetFournisseurById(final String id) {
        GmFournisseur fournisseur = this.fournisseurRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le fournisseur n'existe pas.")
        );
        return this.mapper.maps(fournisseur);
    }

    /**
     * Get all suppliers.
     */
    public List<GmFournisseurDto> doGetFournisseurs(final String idEntreprise) {
        List<GmFournisseur> fournisseurs = this.fournisseurRepository.findByStatutAndEntrepriseId(EStatut.ACTIF,
                idEntreprise);
        return fournisseurs.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a supplier.
     *
     * @param dto
     */
    public GmFournisseurDto doPostFournisseur(final GmFournisseurDto dto) {
        this.checkDuplicateUser(null, dto.getUser());
        GmUserDto user = this.doPostUser(dto.getUser());
        GmFournisseur fournisseur = this.mapper.maps(dto);
        fournisseur.setId(UUID.randomUUID().toString());
        fournisseur.setUtilisateur(this.mapper.maps(user));
        fournisseur.setStatut(EStatut.ACTIF);
        fournisseur = this.fournisseurRepository.save(fournisseur);
        return this.mapper.maps(fournisseur);
    }

    /**
     * Update supplier information.
     *
     * @param id
     * @param dto
     */
    public GmFournisseurDto doPutFournisseur(final String id, final GmFournisseurDto dto) {
        this.checkDuplicateUser(id, dto.getUser());
        GmFournisseur fournisseur = this.mapper.maps(dto);
        fournisseur.setId(id);
        fournisseur = this.fournisseurRepository.save(fournisseur);
        return this.mapper.maps(fournisseur);
    }

    /**
     * Delete a supplier.
     *
     * @param id
     */
    public void doDeleteFournisseur(final String id) {
        this.fournisseurRepository.findById(id).ifPresent((fournisseur) -> {
            fournisseur.setStatut(EStatut.SUPPRIME);
            this.fournisseurRepository.save(fournisseur);
        });
    }

    /**
     * Verify existence of work information.
     *
     * @param id
     * @param dto
     */
    public void checkDuplicatePoste(final String id, final GmPosteDto dto) {
        if (this.posteRepository.checkDuplicatePoste(id, dto.getLibelle(), dto.getStatut(), dto.getIdEntreprise())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ce poste existe déjà.");
        }
    }

    /**
     * Get work information.
     *
     * @param id
     * @return
     */
    public GmPosteDto doGetPosteById(final String id) {
        GmPoste poste = this.posteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le poste n'existe pas."));
        return this.mapper.maps(poste);
    }

    /**
     * Get all works.
     *
     * @return
     */
    public List<GmPosteDto> doGetPostes(final String idEntreprise) {
        List<GmPoste> postes = this.posteRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return postes.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a work.
     *
     * @param dto
     * @return
     */
    public GmPosteDto doPostPoste(final GmPosteDto dto) {
        GmPoste poste = this.mapper.maps(dto);
        poste.setId(UUID.randomUUID().toString());
        poste.setStatut(EStatut.ACTIF);
        poste = this.posteRepository.save(poste);
        return this.mapper.maps(poste);
    }

    /**
     * Update work information.
     *
     * @param id
     * @param dto
     * @return
     */
    public GmPosteDto doPutPoste(final String id, final GmPosteDto dto) {
        GmPoste poste = this.mapper.maps(dto);
        poste.setId(id);
        poste = this.posteRepository.save(poste);
        return this.mapper.maps(poste);
    }

    /**
     * Delete a work.
     *
     * @param id
     */
    public void doDeletePoste(final String id) {
        this.posteRepository.findById(id).ifPresent((poste) -> {
            poste.setStatut(EStatut.SUPPRIME);
            this.posteRepository.save(poste);
        });
    }

    /**
     * Verify existence of motorbike's information.
     *
     * @param id
     * @param dto
     */
    public void checkDuplicateMoto(final String id, final GmMotoDto dto) {
        if (this.motoRepository.checkDuplicateMoto(id, dto.getNumeroSerie(), EStatut.ACTIF, dto.getIdEntreprise())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Une moto existe deja avec cet numero de serie");
        }
    }

    /**
     * Get a motorbike information.
     *
     * @param id
     * @return GmMotoDto
     */
    public GmMotoDto doGetMotoById(final String id) {
        GmMoto moto = this.motoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La moto n'existe pas."));
        return this.mapper.maps(moto);
    }

    /**
     * Get a list of motorbike's informations.
     *
     * @param idEntreprise
     * @return List of motorbike
     */
    public List<GmMotoDto> doGetMotos(final String idEntreprise) {
        List<GmMoto> motos = this.motoRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return motos.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a motorbike's information.
     *
     * @param dto
     * @return a Moto DTO
     */
    public GmMotoDto doPostMoto(final GmMotoDto dto) {
        this.checkDuplicateMoto(null, dto);
        GmMoto moto = this.mapper.maps(dto);
        moto.setId(UUID.randomUUID().toString());
        moto.setStatut(EStatut.ACTIF);
        moto = this.motoRepository.save(moto);
        return this.mapper.maps(moto);
    }

    /**
     * Update motorbike's information.
     *
     * @param id
     * @param dto
     * @return a Moto DTO
     */
    public GmMotoDto doUpdateMoto(final String id, final GmMotoDto dto) {
        this.checkDuplicateMoto(id, dto);
        GmMoto moto = this.mapper.maps(dto);
        moto.setId(id);
        moto = this.motoRepository.save(moto);
        return this.mapper.maps(moto);
    }

    /**
     * Suppress motorbike.
     *
     * @param id
     */
    public void doDeleteMoto(final String id) {
        this.motoRepository.findById(id).ifPresent((entreprise) -> {
            entreprise.setStatut(EStatut.SUPPRIME);
            this.motoRepository.save(entreprise);
        });
    }

    /**
     * Get an invoice information.
     *
     * @param id
     * @return Invoice DTO
     */
    public GmFactureDto doGetFactureById(final String id) {
        GmFacture facture = this.factureRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cette facture n'existe pas."));
        return this.mapper.maps(facture);
    }

    /**
     * Get invoices informations.
     *
     * @param idEntreprise
     * @return List of invoice.
     */
    public List<GmFactureDto> doGetFactures(final String idEntreprise) {
        List<GmFacture> factures = this.factureRepository.findByStatutAndEntrepriseId(EStatut.ACTIF, idEntreprise);
        return factures.stream().map(mapper::maps).collect(Collectors.toList());
    }

    /**
     * Save a invoice information.
     *
     * @param dto
     * @return Invoice DTO
     */
    public GmFactureDto doPostFacture(final GmFactureDto dto) {
        GmFacture facture = this.mapper.maps(dto);
        if (dto.getIdClient() == null) {
            GmClientDto client = this.doPostClient(dto.getClient());
            facture.setClient(this.mapper.maps(client));
        }
        facture.setId(UUID.randomUUID().toString());
        facture.setStatut(EStatut.ACTIF);
        facture = this.factureRepository.save(facture);
        return this.mapper.maps(facture);

    }

    /**
     * Update invoice information.
     *
     * @param id
     * @param dto
     * @return Invoice DTO
     */
    public GmFactureDto doUpdateFacture(final String id, final GmFactureDto dto) {
        GmFacture facture = this.mapper.maps(dto);
        facture.setId(id);
        facture = this.factureRepository.save(facture);
        return this.mapper.maps(facture);
    }

    /**
     * Suppress an invoice information.
     *
     * @param id
     */
    public void doDeleteFacture(final String id) {
        this.factureRepository.findById(id).ifPresent((facture) -> {
            facture.setStatut(EStatut.SUPPRIME);
            this.factureRepository.save(facture);
        });
    }


}
