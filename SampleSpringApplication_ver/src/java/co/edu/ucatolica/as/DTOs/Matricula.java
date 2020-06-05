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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MiguelPC
 */
@Entity
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByCodigoMatricula", query = "SELECT m FROM Matricula m WHERE m.codigoMatricula = :codigoMatricula"),
    @NamedQuery(name = "Matricula.findByCosto", query = "SELECT m FROM Matricula m WHERE m.costo = :costo"),
    @NamedQuery(name = "Matricula.findByEstado", query = "SELECT m FROM Matricula m WHERE m.estado = :estado"),
    @NamedQuery(name = "Matricula.findByFechaPago", query = "SELECT m FROM Matricula m WHERE m.fechaPago = :fechaPago"),
    @NamedQuery(name = "Matricula.findByFechaExtraordinaria", query = "SELECT m FROM Matricula m WHERE m.fechaExtraordinaria = :fechaExtraordinaria"),
    @NamedQuery(name = "Matricula.findByFechaExtraordianaria2", query = "SELECT m FROM Matricula m WHERE m.fechaExtraordianaria2 = :fechaExtraordianaria2")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoMatricula")
    private Integer codigoMatricula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private int costo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_extraordinaria")
    @Temporal(TemporalType.DATE)
    private Date fechaExtraordinaria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_extraordianaria2")
    @Temporal(TemporalType.DATE)
    private Date fechaExtraordianaria2;
    @JoinColumn(name = "persona_identifcacion", referencedColumnName = "identifcacion")
    @ManyToOne(optional = false)
    private Persona personaIdentifcacion;

    public Matricula() {
    }

    public Matricula(Integer codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public Matricula(Integer codigoMatricula, int costo, String estado, Date fechaPago, Date fechaExtraordinaria, Date fechaExtraordianaria2) {
        this.codigoMatricula = codigoMatricula;
        this.costo = costo;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.fechaExtraordinaria = fechaExtraordinaria;
        this.fechaExtraordianaria2 = fechaExtraordianaria2;
    }

    public Integer getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(Integer codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaExtraordinaria() {
        return fechaExtraordinaria;
    }

    public void setFechaExtraordinaria(Date fechaExtraordinaria) {
        this.fechaExtraordinaria = fechaExtraordinaria;
    }

    public Date getFechaExtraordianaria2() {
        return fechaExtraordianaria2;
    }

    public void setFechaExtraordianaria2(Date fechaExtraordianaria2) {
        this.fechaExtraordianaria2 = fechaExtraordianaria2;
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
        hash += (codigoMatricula != null ? codigoMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.codigoMatricula == null && other.codigoMatricula != null) || (this.codigoMatricula != null && !this.codigoMatricula.equals(other.codigoMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ucatolica.as.DTOs.Matricula[ codigoMatricula=" + codigoMatricula + " ]";
    }
    
}
