package net.generica.katalog.service.mapper;

import net.generica.katalog.domain.*;
import net.generica.katalog.service.dto.BezeichnungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Bezeichnung and its DTO BezeichnungDTO.
 */
@Mapper(componentModel = "spring", uses = {SpracheMapper.class, GruppeMapper.class, WortMapper.class})
public interface BezeichnungMapper extends EntityMapper<BezeichnungDTO, Bezeichnung> {

    @Mapping(source = "sprache.id", target = "spracheId")
    @Mapping(source = "sprache.sprachCode", target = "spracheSprachCode")
    @Mapping(source = "gruppe.id", target = "gruppeId")
    @Mapping(source = "gruppe.gruppenCode", target = "gruppeGruppenCode")
    BezeichnungDTO toDto(Bezeichnung bezeichnung);

    @Mapping(source = "spracheId", target = "sprache")
    @Mapping(source = "gruppeId", target = "gruppe")
    Bezeichnung toEntity(BezeichnungDTO bezeichnungDTO);

    default Bezeichnung fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bezeichnung bezeichnung = new Bezeichnung();
        bezeichnung.setId(id);
        return bezeichnung;
    }
}
