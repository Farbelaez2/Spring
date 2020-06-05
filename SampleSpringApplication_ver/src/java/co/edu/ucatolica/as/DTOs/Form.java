
package co.edu.ucatolica.as.DTOs;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "nombre")
@XmlRootElement
public class Form implements Serializable{
    
    @Id
    private String codigoEstudiante;
    private String claveEstudiante;
    private String codigoMateria;

    public Form() {
    }

    public Form(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public Form(String codigoEstudiante, String claveEstudiante, String codigoMateria) {
        this.codigoEstudiante = codigoEstudiante;
        this.claveEstudiante = claveEstudiante;
        this.codigoMateria = codigoMateria;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getClaveEstudiante() {
        return claveEstudiante;
    }

    public void setClaveEstudiante(String claveEstudiante) {
        this.claveEstudiante = claveEstudiante;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigoEstudiante);
        hash = 59 * hash + Objects.hashCode(this.claveEstudiante);
        hash = 59 * hash + Objects.hashCode(this.codigoMateria);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Form other = (Form) obj;
        if (!Objects.equals(this.codigoEstudiante, other.codigoEstudiante)) {
            return false;
        }
        if (!Objects.equals(this.claveEstudiante, other.claveEstudiante)) {
            return false;
        }
        if (!Objects.equals(this.codigoMateria, other.codigoMateria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonaValidate{" + "codigoEstudiante=" + codigoEstudiante + ", claveEstudiante=" + claveEstudiante + ", CodigoMateria=" + codigoMateria + '}';
    }
    
    
    
}
