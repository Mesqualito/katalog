package net.generica.katalog.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Wort entity.
 */
public class WortDTO implements Serializable {

    private Long id;

    @NotNull
    private Long intId;

    @NotNull
    private String eWort;


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

    public Long getIntId() {
        return intId;
    }

    public void setIntId(Long intId) {
        this.intId = intId;
    }

    public String geteWort() {
        return eWort;
    }

    public void seteWort(String eWort) {
        this.eWort = eWort;
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

        WortDTO wortDTO = (WortDTO) o;
        if (wortDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wortDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WortDTO{" +
            "id=" + getId() +
            ", intId=" + getIntId() +
            ", eWort='" + geteWort() + "'" +
            ", sprache=" + getSpracheId() +
            ", sprache='" + getSpracheSprachCode() + "'" +
            ", gruppe=" + getGruppeId() +
            ", gruppe='" + getGruppeGruppenCode() + "'" +
            "}";
    }
}
