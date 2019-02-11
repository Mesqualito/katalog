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
    @Field("sprachCode")
    private Sprache sprachCode;

    @DBRef
    @Field("gruppenCode")
    @JsonIgnoreProperties("ausdrucks")
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

    public Sprache getSprachCode() {
        return sprachCode;
    }

    public Ausdruck sprachCode(Sprache sprache) {
        this.sprachCode = sprache;
        return this;
    }

    public void setSprachCode(Sprache sprache) {
        this.sprachCode = sprache;
    }

    public Gruppe getGruppenCode() {
        return gruppenCode;
    }

    public Ausdruck gruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
        return this;
    }

    public void setGruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
    }

    public Set<Single> getSingles() {
        return singles;
    }

    public Ausdruck singles(Set<Single> singles) {
        this.singles = singles;
        return this;
    }

    public Ausdruck addSingle(Single single) {
        this.singles.add(single);
        single.getAusdrucks().add(this);
        return this;
    }

    public Ausdruck removeSingle(Single single) {
        this.singles.remove(single);
        single.getAusdrucks().remove(this);
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
