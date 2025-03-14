package com.yandoama.gestmoto.mapstruct;

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
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface GmMapper {

    @Mappings({

    })
    GmEntreprise maps(GmEntrepriseDto dto);

    @InheritInverseConfiguration
    GmEntrepriseDto maps(GmEntreprise entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise"),

    })
    GmUser maps(GmUserDto dto);

    @InheritInverseConfiguration
    GmUserDto maps(GmUser entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise"),
            @Mapping(target = "utilisateur.id", source = "idUtilisateur")
    })
    GmClient maps(GmClientDto dto);

    @InheritInverseConfiguration
    GmClientDto maps(GmClient entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise"),
            @Mapping(target = "utilisateur.id", source = "idUtilisateur"),
            @Mapping(target = "poste.id", source = "idPoste")
    })
    GmEmploye maps(GmEmployeDto dto);

    @InheritInverseConfiguration
    GmEmployeDto maps(GmEmploye entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise")
    })
    GmGenre maps(GmGenreDto dto);

    @InheritInverseConfiguration
    GmGenreDto maps(GmGenre entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise")
    })
    GmModele maps(GmModeleDto dto);

    @InheritInverseConfiguration
    GmModeleDto maps(GmModele entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise"),
            @Mapping(target = "utilisateur.id", source = "idUtilisateur")
    })
    GmFournisseur maps(GmFournisseurDto dto);

    @InheritInverseConfiguration
    GmFournisseurDto maps(GmFournisseur entity);

    @Mappings({
            @Mapping(target = "entreprise.id", source = "idEntreprise")
    })
    GmPoste maps(GmPosteDto dto);

    @InheritInverseConfiguration
    GmPosteDto maps(GmPoste entity);

    @Mappings({
            @Mapping(target = "genre.id", source = "idGenre"),
            @Mapping(target = "entreprise.id", source = "idEntreprise"),
            @Mapping(target = "modele.id", source = "idModele"),
            @Mapping(target = "fournisseur.id", source = "idFournisseur")
    })
    GmMoto maps(GmMotoDto dto);

    @InheritInverseConfiguration
    GmMotoDto maps(GmMoto entity);

    @Mappings({
            @Mapping(target = "client.id", source = "idClient"),
            @Mapping(target = "entreprise.id", source = "idEntreprise"),
            @Mapping(target = "moto.id", source = "idMoto")
    })
    GmFacture maps(GmFactureDto dto);

    @InheritInverseConfiguration
    GmFactureDto maps(GmFacture entity);

}
