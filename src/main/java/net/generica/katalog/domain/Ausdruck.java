package net.generica.katalog.domain;


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
 * A Ausdruck.
 */
@Entity
@Table(name = "ausdruck")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ausdruck implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ausdruck", nullable = false)
    private String ausdruck;

    @OneToOne
    @JoinColumn(unique = true)
    private Sprache sprache;

    @ManyToOne
    @JsonIgnoreProperties("ausdrucks")
    private Gruppe gruppe;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ausdruck_einzelwort",
               joinColumns = @JoinColumn(name = "ausdruck_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "einzelwort_id", referencedColumnName = "id"))
    private Set<Wort> einzelworts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
