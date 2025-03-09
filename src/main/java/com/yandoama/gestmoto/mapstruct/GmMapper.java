package com.yandoama.gestmoto.mapstruct;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import com.yandoama.gestmoto.dto.GmEntrepriseDto;
import com.yandoama.gestmoto.entity.GmEntreprise;
import org.mapstruct.InheritInverseConfiguration;


@Mapper(componentModel = "spring")
public interface GmMapper {

    @Mappings({
    })
    GmEntreprise maps(GmEntrepriseDto dto);

    @InheritInverseConfiguration
    GmEntrepriseDto maps(GmEntreprise entity);

}
