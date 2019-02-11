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
 * A Wort.
 */
@Document(collection = "wort")
public class Wort implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @NotNull
    @Field("e_wort")
    private String eWort;

    @DBRef
    @Field("sprache")
    private Sprache sprache;

    @DBRef
    @Field("gruppe")
    @JsonIgnoreProperties("singles")
    private Gruppe gruppe;

    @DBRef
    @Field("einzelworts")
    private Set<Wort> einzelworts = new HashSet<>();

    @DBRef
    @Field("worts")
    @JsonIgnore
    private Set<Wort> worts = new HashSet<>();

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

    public String geteWort() {
        return eWort;
    }

    public Wort eWort(String eWort) {
        this.eWort = eWort;
        return this;
    }

    public void seteWort(String eWort) {
        this.eWort = eWort;
    }

    public Sprache getSprache() {
        return sprache;
    }

    public Wort sprache(Sprache sprache) {
        this.sprache = sprache;
        return this;
    }

    public void setSprache(Sprache sprache) {
        this.sprache = sprache;
    }

    public Gruppe getGruppe() {
        return gruppe;
    }

    public Wort gruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
        return this;
    }

    public void setGruppe(Gruppe gruppe) {
        this.gruppe = gruppe;
    }

    public Set<Wort> getEinzelworts() {
        return einzelworts;
    }

    public Wort einzelworts(Set<Wort> worts) {
        this.einzelworts = worts;
        return this;
    }

    public Wort addEinzelwort(Wort wort) {
        this.einzelworts.add(wort);
        wort.getWorts().add(this);
        return this;
    }

    public Wort removeEinzelwort(Wort wort) {
        this.einzelworts.remove(wort);
        wort.getWorts().remove(this);
        return this;
    }

    public void setEinzelworts(Set<Wort> worts) {
        this.einzelworts = worts;
    }

    public Set<Wort> getWorts() {
        return worts;
    }

    public Wort worts(Set<Wort> worts) {
        this.worts = worts;
        return this;
    }

    public Wort addWort(Wort wort) {
        this.worts.add(wort);
        wort.getEinzelworts().add(this);
        return this;
    }

    public Wort removeWort(Wort wort) {
        this.worts.remove(wort);
        wort.getEinzelworts().remove(this);
        return this;
    }

    public void setWorts(Set<Wort> worts) {
        this.worts = worts;
    }

    public Set<Bezeichnung> getBezeichnungs() {
        return bezeichnungs;
    }

    public Wort bezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
        return this;
    }

    public Wort addBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.add(bezeichnung);
        bezeichnung.getEinzelworts().add(this);
        return this;
    }

    public Wort removeBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.remove(bezeichnung);
        bezeichnung.getEinzelworts().remove(this);
        return this;
    }

    public void setBezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
    }

    public Set<Ausdruck> getAusdrucks() {
        return ausdrucks;
    }

    public Wort ausdrucks(Set<Ausdruck> ausdrucks) {
        this.ausdrucks = ausdrucks;
        return this;
    }

    public Wort addAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.add(ausdruck);
        ausdruck.getEinzelworts().add(this);
        return this;
    }

    public Wort removeAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.remove(ausdruck);
        ausdruck.getEinzelworts().remove(this);
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
        Wort wort = (Wort) o;
        if (wort.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wort.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Wort{" +
            "id=" + getId() +
            ", eWort='" + geteWort() + "'" +
            "}";
    }
}
