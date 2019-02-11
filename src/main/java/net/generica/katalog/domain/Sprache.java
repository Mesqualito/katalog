package net.generica.katalog.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Sprache.
 */
@Entity
@Table(name = "sprache")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sprache implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sprach_code", nullable = false)
    private String sprachCode;

    @Column(name = "sprach_bezeichnung")
    private String sprachBezeichnung;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprachCode() {
        return sprachCode;
    }

    public Sprache sprachCode(String sprachCode) {
        this.sprachCode = sprachCode;
        return this;
    }

    public void setSprachCode(String sprachCode) {
        this.sprachCode = sprachCode;
    }

    public String getSprachBezeichnung() {
        return sprachBezeichnung;
    }

    public Sprache sprachBezeichnung(String sprachBezeichnung) {
        this.sprachBezeichnung = sprachBezeichnung;
        return this;
    }

    public void setSprachBezeichnung(String sprachBezeichnung) {
        this.sprachBezeichnung = sprachBezeichnung;
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
        Sprache sprache = (Sprache) o;
        if (sprache.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sprache.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sprache{" +
            "id=" + getId() +
            ", sprachCode='" + getSprachCode() + "'" +
            ", sprachBezeichnung='" + getSprachBezeichnung() + "'" +
            "}";
    }
}
