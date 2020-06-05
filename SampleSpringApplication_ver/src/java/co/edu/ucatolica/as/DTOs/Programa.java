/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DTOs;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MiguelPC
 */
@Entity
@Table(name = "programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByCodigoPrograma", query = "SELECT p FROM Programa p WHERE p.codigoPrograma = :codigoPrograma"),
    @NamedQuery(name = "Programa.findByNombrePrograma", query = "SELECT p FROM Programa p WHERE p.nombrePrograma = :nombrePrograma"),
    @NamedQuery(name = "Programa.findByTipoPrograma", query = "SELECT p FROM Programa p WHERE p.tipoPrograma = :tipoPrograma")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoPrograma")
    private Integer codigoPrograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombrePrograma")
    private String nombrePrograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoPrograma")
    private String tipoPrograma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacodigoPrograma")
    private Collection<Persona> personaCollection;

    public Programa() {
    }

    public Programa(Integer codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public Programa(Integer codigoPrograma, String nombrePrograma, String tipoPrograma) {
        this.codigoPrograma = codigoPrograma;
        this.nombrePrograma = nombrePrograma;
        this.tipoPrograma = tipoPrograma;
    }

    public Integer getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(Integer codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getTipoPrograma() {
        return tipoPrograma;
    }

    public void setTipoPrograma(String tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPrograma != null ? codigoPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.codigoPrograma == null && other.codigoPrograma != null) || (this.codigoPrograma != null && !this.codigoPrograma.equals(other.codigoPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ucatolica.as.DTOs.Programa[ codigoPrograma=" + codigoPrograma + " ]";
    }
    
}
