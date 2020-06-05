/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DTOs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MiguelPC
 */
@Embeddable
public class PersonaHasCursoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "persona_identifcacion")
    private int personaIdentifcacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Curso_CodigoCurso")
    private int cursoCodigoCurso;

    public PersonaHasCursoPK() {
    }

    public PersonaHasCursoPK(int personaIdentifcacion, int cursoCodigoCurso) {
        this.personaIdentifcacion = personaIdentifcacion;
        this.cursoCodigoCurso = cursoCodigoCurso;
    }

    public int getPersonaIdentifcacion() {
        return personaIdentifcacion;
    }

    public void setPersonaIdentifcacion(int personaIdentifcacion) {
        this.personaIdentifcacion = personaIdentifcacion;
    }

    public int getCursoCodigoCurso() {
        return cursoCodigoCurso;
    }

    public void setCursoCodigoCurso(int cursoCodigoCurso) {
        this.cursoCodigoCurso = cursoCodigoCurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personaIdentifcacion;
        hash += (int) cursoCodigoCurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaHasCursoPK)) {
            return false;
        }
        PersonaHasCursoPK other = (PersonaHasCursoPK) object;
        if (this.personaIdentifcacion != other.personaIdentifcacion) {
            return false;
        }
        if (this.cursoCodigoCurso != other.cursoCodigoCurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.ucatolica.as.DTOs.PersonaHasCursoPK[ personaIdentifcacion=" + personaIdentifcacion + ", cursoCodigoCurso=" + cursoCodigoCurso + " ]";
    }
    
}
