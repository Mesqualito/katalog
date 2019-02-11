package net.generica.katalog.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Gruppe.
 */
@Document(collection = "gruppe")
public class Gruppe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("gruppen_code")
    private String gruppenCode;

    @Field("gruppen_bezeichnung")
    private String gruppenBezeichnung;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGruppenCode() {
        return gruppenCode;
    }

    public Gruppe gruppenCode(String gruppenCode) {
        this.gruppenCode = gruppenCode;
        return this;
    }

    public void setGruppenCode(String gruppenCode) {
        this.gruppenCode = gruppenCode;
    }

    public String getGruppenBezeichnung() {
        return gruppenBezeichnung;
    }

    public Gruppe gruppenBezeichnung(String gruppenBezeichnung) {
        this.gruppenBezeichnung = gruppenBezeichnung;
        return this;
    }

    public void setGruppenBezeichnung(String gruppenBezeichnung) {
        this.gruppenBezeichnung = gruppenBezeichnung;
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
        Gruppe gruppe = (Gruppe) o;
        if (gruppe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gruppe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gruppe{" +
            "id=" + getId() +
            ", gruppenCode='" + getGruppenCode() + "'" +
            ", gruppenBezeichnung='" + getGruppenBezeichnung() + "'" +
            "}";
    }
}
