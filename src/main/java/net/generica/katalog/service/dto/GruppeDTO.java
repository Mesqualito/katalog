package net.generica.katalog.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Gruppe entity.
 */
public class GruppeDTO implements Serializable {

    private Long id;

    @NotNull
    private String gruppenCode;

    private String gruppenBezeichnung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGruppenCode() {
        return gruppenCode;
    }

    public void setGruppenCode(String gruppenCode) {
        this.gruppenCode = gruppenCode;
    }

    public String getGruppenBezeichnung() {
        return gruppenBezeichnung;
    }

    public void setGruppenBezeichnung(String gruppenBezeichnung) {
        this.gruppenBezeichnung = gruppenBezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GruppeDTO gruppeDTO = (GruppeDTO) o;
        if (gruppeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gruppeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GruppeDTO{" +
            "id=" + getId() +
            ", gruppenCode='" + getGruppenCode() + "'" +
            ", gruppenBezeichnung='" + getGruppenBezeichnung() + "'" +
            "}";
    }
}
