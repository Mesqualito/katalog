package net.generica.katalog.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Sprache entity.
 */
public class SpracheDTO implements Serializable {

    private Long id;

    @NotNull
    private String sprachCode;

    private String sprachBezeichnung;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprachCode() {
        return sprachCode;
    }

    public void setSprachCode(String sprachCode) {
        this.sprachCode = sprachCode;
    }

    public String getSprachBezeichnung() {
        return sprachBezeichnung;
    }

    public void setSprachBezeichnung(String sprachBezeichnung) {
        this.sprachBezeichnung = sprachBezeichnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpracheDTO spracheDTO = (SpracheDTO) o;
        if (spracheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), spracheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SpracheDTO{" +
            "id=" + getId() +
            ", sprachCode='" + getSprachCode() + "'" +
            ", sprachBezeichnung='" + getSprachBezeichnung() + "'" +
            "}";
    }
}
