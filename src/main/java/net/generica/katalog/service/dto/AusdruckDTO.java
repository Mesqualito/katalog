package net.generica.katalog.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Ausdruck entity.
 */
public class AusdruckDTO implements Serializable {

    private Long id;

    @NotNull
    private String ausdruck;


    private Long spracheId;

    private String spracheSprachCode;

    private Long gruppeId;

    private String gruppeGruppenCode;

    private Set<WortDTO> einzelworts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAusdruck() {
        return ausdruck;
    }

    public void setAusdruck(String ausdruck) {
        this.ausdruck = ausdruck;
    }

    public Long getSpracheId() {
        return spracheId;
    }

    public void setSpracheId(Long spracheId) {
        this.spracheId = spracheId;
    }

    public String getSpracheSprachCode() {
        return spracheSprachCode;
    }

    public void setSpracheSprachCode(String spracheSprachCode) {
        this.spracheSprachCode = spracheSprachCode;
    }

    public Long getGruppeId() {
        return gruppeId;
    }

    public void setGruppeId(Long gruppeId) {
        this.gruppeId = gruppeId;
    }

    public String getGruppeGruppenCode() {
        return gruppeGruppenCode;
    }

    public void setGruppeGruppenCode(String gruppeGruppenCode) {
        this.gruppeGruppenCode = gruppeGruppenCode;
    }

    public Set<WortDTO> getEinzelworts() {
        return einzelworts;
    }

    public void setEinzelworts(Set<WortDTO> worts) {
        this.einzelworts = worts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AusdruckDTO ausdruckDTO = (AusdruckDTO) o;
        if (ausdruckDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ausdruckDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AusdruckDTO{" +
            "id=" + getId() +
            ", ausdruck='" + getAusdruck() + "'" +
            ", sprache=" + getSpracheId() +
            ", sprache='" + getSpracheSprachCode() + "'" +
            ", gruppe=" + getGruppeId() +
            ", gruppe='" + getGruppeGruppenCode() + "'" +
            "}";
    }
}
