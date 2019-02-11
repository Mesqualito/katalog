package net.generica.katalog.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Sprache.
 */
@Document(collection = "sprache")
public class Sprache implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("sprach_code")
    private String sprachCode;

    @Field("sprach_bezeichnung")
    private String sprachBezeichnung;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSprachCode() {
        return sprachCode;
    }

    public Sprache sprachCode(String sprachCode) {
        this.sprachCode = sprachCode;
        return this;
    }

    public void setSprachCode(String sprachCode) {
        this.sprachCode = sprachCode;
    }

    public String getSprachBezeichnung() {
        return sprachBezeichnung;
    }

    public Sprache sprachBezeichnung(String sprachBezeichnung) {
        this.sprachBezeichnung = sprachBezeichnung;
        return this;
    }

    public void setSprachBezeichnung(String sprachBezeichnung) {
        this.sprachBezeichnung = sprachBezeichnung;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sprache sprache = (Sprache) o;
        if (sprache.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sprache.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sprache{" +
            "id=" + getId() +
            ", sprachCode='" + getSprachCode() + "'" +
            ", sprachBezeichnung='" + getSprachBezeichnung() + "'" +
            "}";
    }
}
