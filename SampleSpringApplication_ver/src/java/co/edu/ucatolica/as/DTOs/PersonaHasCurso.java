/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DTOs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MiguelPC
 */
@Entity
@Table(name = "persona_has_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaHasCurso.findAll", query = "SELECT p FROM PersonaHasCurso p"),
    @NamedQuery(name = "PersonaHasCurso.findByPersonaIdentifcacion", query = "SELECT p FROM PersonaHasCurso p WHERE p.personaHasCursoPK.personaIdentifcacion = :personaIdentifcacion"),
    @NamedQuery(name = "PersonaHasCurso.findByCursoCodigoCurso", query = "SELECT p FROM PersonaHasCurso p WHERE p.personaHasCursoPK.cursoCodigoCurso = :cursoCodigoCurso"),
    @NamedQuery(name = "PersonaHasCurso.findByNota", query = "SELECT p FROM PersonaHasCurso p WHERE p.nota = :nota")})
public class PersonaHasCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaHasCursoPK personaHasCursoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota")
    private int nota;
    @JoinColumn(name = "Curso_CodigoCurso", referencedColumnName = "CodigoCurso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Curso curso;
    @JoinColumn(name = "persona_identifcacion", referencedColumnName = "identifcacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public PersonaHasCurso() {
    }

    public PersonaHasCurso(PersonaHasCursoPK personaHasCursoPK) {
        this.personaHasCursoPK = personaHasCursoPK;
    }

    public PersonaHasCurso(PersonaHasCursoPK personaHasCursoPK, int nota) {
        this.personaHasCursoPK = personaHasCursoPK;
        this.nota = nota;
    }

    public PersonaHasCurso(int personaIdentifcacion, int cursoCodigoCurso) {
        this.personaHasCursoPK = new PersonaHasCursoPK(personaIdentifcacion, cursoCodigoCurso);
    }

    public PersonaHasCursoPK getPersonaHasCursoPK() {
        return personaHasCursoPK;
    }

    public void setPersonaHasCursoPK(PersonaHasCursoPK personaHasCursoPK) {
        this.personaHasCursoPK = personaHasCursoPK;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaHasCursoPK != null ? personaHasCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaHasCurso)) {
            return false;
        }
        PersonaHasCurso other = (PersonaHasCurso) object;
        if ((this.personaHasCursoPK == null && other.personaHasCursoPK != null) || (this.personaHasCursoPK != null && !this.personaHasCursoPK.equals(other.personaHasCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ucatolica.as.DTOs.PersonaHasCurso[ personaHasCursoPK=" + personaHasCursoPK + " ]";
    }
    
}
