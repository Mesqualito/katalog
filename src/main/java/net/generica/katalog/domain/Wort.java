package net.generica.katalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Field("wort")
    private String wort;

    @DBRef
    @Field("sprachCode")
    private Sprache sprachCode;

    @DBRef
    @Field("gruppenCode")
    private Set<Gruppe> gruppenCodes = new HashSet<>();
    @DBRef
    @Field("worts")
    private Set<Wort> worts = new HashSet<>();

    @DBRef
    @Field("worts")
    @JsonIgnore
    private Set<Wort> worts = new HashSet<>();

    @DBRef
    @Field("worts")
    @JsonIgnore
    private Set<Bezeichnung> worts = new HashSet<>();

    @DBRef
    @Field("worts")
    @JsonIgnore
    private Set<Ausdruck> worts = new HashSet<>();

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

    public Wort wort(String wort) {
        this.wort = wort;
        return this;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public Sprache getSprachCode() {
        return sprachCode;
    }

    public Wort sprachCode(Sprache sprache) {
        this.sprachCode = sprache;
        return this;
    }

    public void setSprachCode(Sprache sprache) {
        this.sprachCode = sprache;
    }

    public Set<Gruppe> getGruppenCodes() {
        return gruppenCodes;
    }

    public Wort gruppenCodes(Set<Gruppe> gruppes) {
        this.gruppenCodes = gruppes;
        return this;
    }

    public Wort addGruppenCode(Gruppe gruppe) {
        this.gruppenCodes.add(gruppe);
        gruppe.setWort(this);
        return this;
    }

    public Wort removeGruppenCode(Gruppe gruppe) {
        this.gruppenCodes.remove(gruppe);
        gruppe.setWort(null);
        return this;
    }

    public void setGruppenCodes(Set<Gruppe> gruppes) {
        this.gruppenCodes = gruppes;
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
        wort.getWorts().add(this);
        return this;
    }

    public Wort removeWort(Wort wort) {
        this.worts.remove(wort);
        wort.getWorts().remove(this);
        return this;
    }

    public void setWorts(Set<Wort> worts) {
        this.worts = worts;
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
        wort.getWorts().add(this);
        return this;
    }

    public Wort removeWort(Wort wort) {
        this.worts.remove(wort);
        wort.getWorts().remove(this);
        return this;
    }

    public void setWorts(Set<Wort> worts) {
        this.worts = worts;
    }

    public Set<Bezeichnung> getWorts() {
        return worts;
    }

    public Wort worts(Set<Bezeichnung> bezeichnungs) {
        this.worts = bezeichnungs;
        return this;
    }

    public Wort addWort(Bezeichnung bezeichnung) {
        this.worts.add(bezeichnung);
        bezeichnung.getWorts().add(this);
        return this;
    }

    public Wort removeWort(Bezeichnung bezeichnung) {
        this.worts.remove(bezeichnung);
        bezeichnung.getWorts().remove(this);
        return this;
    }

    public void setWorts(Set<Bezeichnung> bezeichnungs) {
        this.worts = bezeichnungs;
    }

    public Set<Ausdruck> getWorts() {
        return worts;
    }

    public Wort worts(Set<Ausdruck> ausdrucks) {
        this.worts = ausdrucks;
        return this;
    }

    public Wort addWort(Ausdruck ausdruck) {
        this.worts.add(ausdruck);
        ausdruck.getWorts().add(this);
        return this;
    }

    public Wort removeWort(Ausdruck ausdruck) {
        this.worts.remove(ausdruck);
        ausdruck.getWorts().remove(this);
        return this;
    }

    public void setWorts(Set<Ausdruck> ausdrucks) {
        this.worts = ausdrucks;
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
            ", wort='" + getWort() + "'" +
            "}";
    }
}
