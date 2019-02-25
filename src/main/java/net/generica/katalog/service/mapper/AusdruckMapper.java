package net.generica.katalog.service.mapper;

import net.generica.katalog.domain.*;
import net.generica.katalog.service.dto.AusdruckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ausdruck and its DTO AusdruckDTO.
 */
@Mapper(componentModel = "spring", uses = {SpracheMapper.class, GruppeMapper.class, WortMapper.class})
public interface AusdruckMapper extends EntityMapper<AusdruckDTO, Ausdruck> {

    @Mapping(source = "sprache.id", target = "spracheId")
    @Mapping(source = "sprache.sprachCode", target = "spracheSprachCode")
    @Mapping(source = "gruppe.id", target = "gruppeId")
    @Mapping(source = "gruppe.gruppenCode", target = "gruppeGruppenCode")
    AusdruckDTO toDto(Ausdruck ausdruck);

    @Mapping(source = "spracheId", target = "sprache")
    @Mapping(source = "gruppeId", target = "gruppe")
    Ausdruck toEntity(AusdruckDTO ausdruckDTO);

    default Ausdruck fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ausdruck ausdruck = new Ausdruck();
        ausdruck.setId(id);
        return ausdruck;
    }
}
