package co.edu.ucatolica.as.controllers;

import co.edu.ucatolica.as.DAOs.FactoryDao;
import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.DTOs.Curso;
import co.edu.ucatolica.as.DTOs.Matricula;
import co.edu.ucatolica.as.DTOs.PersonaHasCurso;
import co.edu.ucatolica.as.DTOs.PersonaHasCursoPK;
import co.edu.ucatolica.as.bds.MySqlDataSource;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@org.springframework.stereotype.Controller
@RequestMapping("/matricula")
public class MatriculaController implements org.springframework.stereotype.Controller{
    
    @RequestMapping(method = RequestMethod.GET, value = "/add/{codigoEstudiante}/{codigoMateria}")
    public String reditectformMonitorias(@PathVariable (value = "codigoEstudiante") int codigoEstudiante, 
            @PathVariable (value = "codigoMateria") int codigoMateria,
            Model model){
        String aviso = "";
        try {
            FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
            Persona p = MySqlFactory.getMatriculaDao().buscarPersona(MySqlDataSource.getConexionBD(), codigoEstudiante);
            Matricula ma = MySqlFactory.getMatriculaDao().buscarMatricula(MySqlDataSource.getConexionBD(), codigoEstudiante);
            String tipoPrograma = MySqlFactory.getMatriculaDao().obtenerTipoProgramaPersona(MySqlDataSource.getConexionBD(), codigoEstudiante);
            
            int costoNuevo = ma.getCosto();

            if (tipoPrograma.equals("Pregrado")) {
                costoNuevo = costoNuevo - 500000;
            } else if (tipoPrograma.equals("Postgrado")) {
                costoNuevo = costoNuevo - 750000;
            }
                  
            Matricula m = new Matricula();
            
            m.setCodigoMatricula(ma.getCodigoMatricula());
            m.setCosto(costoNuevo);
            m.setEstado(ma.getEstado());
            m.setFechaPago(ma.getFechaPago());
            m.setFechaExtraordinaria(ma.getFechaExtraordinaria());
            m.setFechaExtraordianaria2(ma.getFechaExtraordianaria2());
            m.setPersonaIdentifcacion(p);
            
            boolean agregado = MySqlFactory.getMatriculaDao().agregar(m, MySqlDataSource.getConexionBD());
            int esError = 0;
            if(!agregado){
                esError = 1;
            }
            return "redirect:/matricula/redirect/" + esError + "/" + codigoMateria;
        } catch (Exception e) {
            Logger.getLogger(ContratoController.class.getName()).log(Level.SEVERE, null, e);
            return "redirect:/matricula/redirect/" + 1;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/redirect/{esError}/{codigoMateria}")
    public String redirectRegister(@PathVariable(value = "esError") int esError,
            @PathVariable (value = "codigoMateria") int codigoMateria,
            Model model){
        String aviso = "";
        if (esError == 1){
            aviso = "Hubo un error actualizando la matricula\n";   
        } else{
            aviso = "EL ESTUDIANTE CUMPLE CON LOS REQUISITOS PARA SER MONITOR, HA SIDO POSTULADO CORRECTAMENTE COMO MONITOR EN LA MATERIA CON CODIGO "+ codigoMateria + "\n";
        }
        
        model.addAttribute("aviso", aviso);
        return "redirect:/";
    }

    @Override
    public String value() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
