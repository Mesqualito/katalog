package net.generica.katalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Wort.
 */
@Entity
@Table(name = "wort")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Wort implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "int_id", nullable = false)
    private Long intId;

    @NotNull
    @Column(name = "e_wort", nullable = false)
    private String eWort;

    @OneToOne
    @JoinColumn(unique = true)
    private Sprache sprache;

    @ManyToOne
    @JsonIgnoreProperties("singles")
    private Gruppe gruppe;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "wort_einzelwort",
               joinColumns = @JoinColumn(name = "wort_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "einzelwort_id", referencedColumnName = "id"))
    private Set<Wort> einzelworts = new HashSet<>();

    @ManyToMany(mappedBy = "einzelworts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Wort> worts = new HashSet<>();

    @ManyToMany(mappedBy = "einzelworts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Bezeichnung> bezeichnungs = new HashSet<>();

    @ManyToMany(mappedBy = "einzelworts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Ausdruck> ausdrucks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIntId() {
        return intId;
    }

    public Wort intId(Long intId) {
        this.intId = intId;
        return this;
    }

    public void setIntId(Long intId) {
        this.intId = intId;
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
            ", intId=" + getIntId() +
            ", eWort='" + geteWort() + "'" +
            "}";
    }
}
