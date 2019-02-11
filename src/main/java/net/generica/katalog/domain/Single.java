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
 * A Single.
 */
@Document(collection = "single")
public class Single implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("wort")
    private String wort;

    @DBRef
    @Field("sprachCode")
    private Sprache sprachCode;

    @DBRef
    @Field("gruppenCode")
    @JsonIgnoreProperties("singles")
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

    public String getWort() {
        return wort;
    }

    public Single wort(String wort) {
        this.wort = wort;
        return this;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public Sprache getSprachCode() {
        return sprachCode;
    }

    public Single sprachCode(Sprache sprache) {
        this.sprachCode = sprache;
        return this;
    }

    public void setSprachCode(Sprache sprache) {
        this.sprachCode = sprache;
    }

    public Gruppe getGruppenCode() {
        return gruppenCode;
    }

    public Single gruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
        return this;
    }

    public void setGruppenCode(Gruppe gruppe) {
        this.gruppenCode = gruppe;
    }

    public Set<Single> getSingles() {
        return singles;
    }

    public Single singles(Set<Single> singles) {
        this.singles = singles;
        return this;
    }

    public Single addSingle(Single single) {
        this.singles.add(single);
        single.getSingles().add(this);
        return this;
    }

    public Single removeSingle(Single single) {
        this.singles.remove(single);
        single.getSingles().remove(this);
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
        Single single = (Single) o;
        if (single.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), single.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Single{" +
            "id=" + getId() +
            ", wort='" + getWort() + "'" +
            "}";
    }
}
