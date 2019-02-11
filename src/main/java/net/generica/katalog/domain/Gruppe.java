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

    @DBRef
    @Field("single")
    private Set<Wort> singles = new HashSet<>();
    @DBRef
    @Field("bezeichnung")
    private Set<Bezeichnung> bezeichnungs = new HashSet<>();
    @DBRef
    @Field("ausdruck")
    private Set<Ausdruck> ausdrucks = new HashSet<>();
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

    public Set<Wort> getSingles() {
        return singles;
    }

    public Gruppe singles(Set<Wort> worts) {
        this.singles = worts;
        return this;
    }

    public Gruppe addSingle(Wort wort) {
        this.singles.add(wort);
        wort.setGruppe(this);
        return this;
    }

    public Gruppe removeSingle(Wort wort) {
        this.singles.remove(wort);
        wort.setGruppe(null);
        return this;
    }

    public void setSingles(Set<Wort> worts) {
        this.singles = worts;
    }

    public Set<Bezeichnung> getBezeichnungs() {
        return bezeichnungs;
    }

    public Gruppe bezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
        return this;
    }

    public Gruppe addBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.add(bezeichnung);
        bezeichnung.setGruppe(this);
        return this;
    }

    public Gruppe removeBezeichnung(Bezeichnung bezeichnung) {
        this.bezeichnungs.remove(bezeichnung);
        bezeichnung.setGruppe(null);
        return this;
    }

    public void setBezeichnungs(Set<Bezeichnung> bezeichnungs) {
        this.bezeichnungs = bezeichnungs;
    }

    public Set<Ausdruck> getAusdrucks() {
        return ausdrucks;
    }

    public Gruppe ausdrucks(Set<Ausdruck> ausdrucks) {
        this.ausdrucks = ausdrucks;
        return this;
    }

    public Gruppe addAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.add(ausdruck);
        ausdruck.setGruppe(this);
        return this;
    }

    public Gruppe removeAusdruck(Ausdruck ausdruck) {
        this.ausdrucks.remove(ausdruck);
        ausdruck.setGruppe(null);
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
