package net.generica.katalog.service.mapper;

import net.generica.katalog.domain.*;
import net.generica.katalog.service.dto.SpracheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sprache and its DTO SpracheDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SpracheMapper extends EntityMapper<SpracheDTO, Sprache> {



    default Sprache fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sprache sprache = new Sprache();
        sprache.setId(id);
        return sprache;
    }
}
