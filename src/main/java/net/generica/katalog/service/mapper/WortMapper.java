package net.generica.katalog.service.mapper;

import net.generica.katalog.domain.*;
import net.generica.katalog.service.dto.WortDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Wort and its DTO WortDTO.
 */
@Mapper(componentModel = "spring", uses = {SpracheMapper.class, GruppeMapper.class})
public interface WortMapper extends EntityMapper<WortDTO, Wort> {

    @Mapping(source = "sprache.id", target = "spracheId")
    @Mapping(source = "sprache.sprachCode", target = "spracheSprachCode")
    @Mapping(source = "gruppe.id", target = "gruppeId")
    @Mapping(source = "gruppe.gruppenCode", target = "gruppeGruppenCode")
    WortDTO toDto(Wort wort);

    @Mapping(source = "spracheId", target = "sprache")
    @Mapping(source = "gruppeId", target = "gruppe")
    @Mapping(target = "worts", ignore = true)
    @Mapping(target = "bezeichnungs", ignore = true)
    @Mapping(target = "ausdrucks", ignore = true)
    Wort toEntity(WortDTO wortDTO);

    default Wort fromId(Long id) {
        if (id == null) {
            return null;
        }
        Wort wort = new Wort();
        wort.setId(id);
        return wort;
    }
}
