package net.generica.katalog.service.mapper;

import net.generica.katalog.domain.*;
import net.generica.katalog.service.dto.GruppeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Gruppe and its DTO GruppeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GruppeMapper extends EntityMapper<GruppeDTO, Gruppe> {


    @Mapping(target = "singles", ignore = true)
    @Mapping(target = "bezeichnungs", ignore = true)
    @Mapping(target = "ausdrucks", ignore = true)
    Gruppe toEntity(GruppeDTO gruppeDTO);

    default Gruppe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gruppe gruppe = new Gruppe();
        gruppe.setId(id);
        return gruppe;
    }
}
