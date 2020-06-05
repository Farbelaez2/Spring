/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DTOs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MiguelPC
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByCodigoContrago", query = "SELECT c FROM Contrato c WHERE c.codigoContrago = :codigoContrago"),
    @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Contrato.findBySalario", query = "SELECT c FROM Contrato c WHERE c.salario = :salario")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoContrago")
    private Integer codigoContrago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salario")
    private int salario;
    @JoinColumn(name = "persona_identifcacion", referencedColumnName = "identifcacion")
    @ManyToOne(optional = false)
    private Persona personaIdentifcacion;

    public Contrato() {
    }

    public Contrato(Integer codigoContrago) {
        this.codigoContrago = codigoContrago;
    }

    public Contrato(Integer codigoContrago, Date fechaInicio, Date fechaFin, int salario) {
        this.codigoContrago = codigoContrago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salario = salario;
    }

    public Integer getCodigoContrago() {
        return codigoContrago;
    }

    public void setCodigoContrago(Integer codigoContrago) {
        this.codigoContrago = codigoContrago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Persona getPersonaIdentifcacion() {
        return personaIdentifcacion;
    }

    public void setPersonaIdentifcacion(Persona personaIdentifcacion) {
        this.personaIdentifcacion = personaIdentifcacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoContrago != null ? codigoContrago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.codigoContrago == null && other.codigoContrago != null) || (this.codigoContrago != null && !this.codigoContrago.equals(other.codigoContrago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ucatolica.as.DTOs.Contrato[ codigoContrago=" + codigoContrago + " ]";
    }
    
}
