package net.generica.katalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Field("sprache")
    private Sprache sprache;

    @DBRef
    @Field("gruppe")
    @JsonIgnoreProperties("singles")
    private Gruppe gruppe;

    @DBRef
    @Field("worts")
    private Set<Single> worts = new HashSet<>();

    @DBRef
    @Field("singles")
    @JsonIgnore
    private Set<Single> singles = new HashSet<>();

    @DBRef
    @Field("bezeichnungs")
    @JsonIgnore
    private Set<Bezeichnung> bezeichnungs = new HashSet<>();

    @DBRef
    @Field("ausdrucks")
    @JsonIgnore
    private Set<Ausdruck> ausdrucks = new HashSet<>();

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

    public Sprache getSprache() {
        return sprache;
    }

    public Single sprache(Sprache sprache) {
        this.sprache = sprache;
        return this;
    }

    public void setSprache(Sprache sprache) {
        this.sprache = sprache;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public Single gruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
        return this;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Set<Single> getWorts() {
        return worts;
    }

    public Single worts(Set<Single> singles) {
        this.worts = singles;
        return this;
    }

    public Single addWort(Single single) {
        this.worts.add(single);
        single.getSingles().add(this);
        return this;
    }

    public Single removeWort(Single single) {
        this.worts.remove(single);
        single.getSingles().remove(this);
        return this;
    }

    public void setWorts(Set<Single> singles) {
        this.worts = singles;
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
        single.getWorts().add(this);
        return this;
    }

    public Single removeSingle(Single single) {
        this.singles.remove(single);
        single.getWorts().remove(this);
        return this;
    }

    public void setSingles(Set<Single> singles) {
        this.singles = singles;
    }

    public Set<Bezeichnung> getBezeichnungs() {
        return bezeichnungs;
    }

    public Single bezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
        return this;
    }

    public Single addBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.add(bezeichnung);
        bezeichnung.getWorts().add(this);
        return this;
    }

    public Single removeBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.remove(bezeichnung);
        bezeichnung.getWorts().remove(this);
        return this;
    }

    public void setBezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
    }

    public Set<Ausdruck> getAusdrucks() {
        return ausdrucks;
    }

    public Single ausdrucks(Set<Ausdruck> ausdrucks) {
        this.ausdrucks = ausdrucks;
        return this;
    }

    public Single addAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.add(ausdruck);
        ausdruck.getWorts().add(this);
        return this;
    }

    public Single removeAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.remove(ausdruck);
        ausdruck.getWorts().remove(this);
        return this;
    }

    public void setAusdrucks(Set<Ausdruck> ausdrucks) {
        this.ausdrucks = ausdrucks;
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
