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
    @Field("sprache")
    private Sprache sprache;

    @DBRef
    @Field("gruppe")
    @JsonIgnoreProperties("bezeichnungs")
    private Gruppe gruppe;

    @DBRef
    @Field("einzelworts")
    private Set<Wort> einzelworts = new HashSet<>();

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

    public Sprache getSprache() {
        return sprache;
    }

    public Bezeichnung sprache(Sprache sprache) {
        this.sprache = sprache;
        return this;
    }

    public void setSprache(Sprache sprache) {
        this.sprache = sprache;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public Bezeichnung gruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
        return this;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Set<Wort> getEinzelworts() {
        return einzelworts;
    }

    public Bezeichnung einzelworts(Set<Wort> worts) {
        this.einzelworts = worts;
        return this;
    }

    public Bezeichnung addEinzelwort(Wort wort) {
        this.einzelworts.add(wort);
        wort.getBezeichnungs().add(this);
        return this;
    }

    public Bezeichnung removeEinzelwort(Wort wort) {
        this.einzelworts.remove(wort);
        wort.getBezeichnungs().remove(this);
        return this;
    }

    public void setEinzelworts(Set<Wort> worts) {
        this.einzelworts = worts;
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
