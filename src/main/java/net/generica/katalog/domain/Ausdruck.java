package net.generica.katalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ausdruck.
 */
@Document(collection = "ausdruck")
public class Ausdruck implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("ausdruck")
    private String ausdruck;

    @DBRef
    @Field("sprache")
    private Sprache sprache;

    @DBRef
    @Field("gruppe")
    @JsonIgnoreProperties("ausdrucks")
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

    public String getAusdruck() {
        return ausdruck;
    }

    public Ausdruck ausdruck(String ausdruck) {
        this.ausdruck = ausdruck;
        return this;
    }

    public void setAusdruck(String ausdruck) {
        this.ausdruck = ausdruck;
    }

    public Sprache getSprache() {
        return sprache;
    }

    public Ausdruck sprache(Sprache sprache) {
        this.sprache = sprache;
        return this;
    }

    public void setSprache(Sprache sprache) {
        this.sprache = sprache;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public Ausdruck gruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
        return this;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Set<Wort> getEinzelworts() {
        return einzelworts;
    }

    public Ausdruck einzelworts(Set<Wort> worts) {
        this.einzelworts = worts;
        return this;
    }

    public Ausdruck addEinzelwort(Wort wort) {
        this.einzelworts.add(wort);
        wort.getAusdrucks().add(this);
        return this;
    }

    public Ausdruck removeEinzelwort(Wort wort) {
        this.einzelworts.remove(wort);
        wort.getAusdrucks().remove(this);
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
        Ausdruck ausdruck = (Ausdruck) o;
        if (ausdruck.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ausdruck.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ausdruck{" +
            "id=" + getId() +
            ", ausdruck='" + getAusdruck() + "'" +
            "}";
    }
}
