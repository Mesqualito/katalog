package net.generica.katalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Bezeichnung.
 */
@Document(collection = "bezeichnung")
public class Bezeichnung implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("bezeichnung")
    private String bezeichnung;

    @DBRef
    @Field("sprachCode")
    private Sprache sprachCode;

    @DBRef
    @Field("gruppenCode")
    @JsonIgnoreProperties("bezeichnungs")
    private Gruppe gruppenCode;

    @DBRef
    @Field("singles")
    private Set<Single> singles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Bezeichnung bezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        return this;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Sprache getSprachCode() {
        return sprachCode;
    }

    public Bezeichnung sprachCode(Sprache sprache) {
        this.sprachCode = sprache;
        return this;
    }

    public void setSprachCode(Sprache sprache) {
        this.sprachCode = sprache;
    }

    public Gruppe getGruppenCode() {
        return gruppenCode;
    }

    public Bezeichnung gruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
        return this;
    }

    public void setGruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
    }

    public Set<Single> getSingles() {
        return singles;
    }

    public Bezeichnung singles(Set<Single> singles) {
        this.singles = singles;
        return this;
    }

    public Bezeichnung addSingle(Single single) {
        this.singles.add(single);
        single.getBezeichnungs().add(this);
        return this;
    }

    public Bezeichnung removeSingle(Single single) {
        this.singles.remove(single);
        single.getBezeichnungs().remove(this);
        return this;
    }

    public void setSingles(Set<Single> singles) {
        this.singles = singles;
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
        Bezeichnung bezeichnung = (Bezeichnung) o;
        if (bezeichnung.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bezeichnung.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bezeichnung{" +
            "id=" + getId() +
            ", bezeichnung='" + getBezeichnung() + "'" +
            "}";
    }
}
