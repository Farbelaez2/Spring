package co.edu.ucatolica.as.controllers;

import co.edu.ucatolica.as.DAOs.FactoryDao;
import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.DTOs.Curso;
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
@RequestMapping("/curso")
public class CursoController implements org.springframework.stereotype.Controller{
    
    @RequestMapping(method = RequestMethod.GET, value = "/add/{codigoEstudiante}/{codigoMateria}")
    public String reditectformMonitorias(@PathVariable (value = "codigoEstudiante") int codigoEstudiante, 
            @PathVariable (value = "codigoMateria") int codigoMateria,
            Model model){
        String aviso = "";
        try {
            FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
            
            Curso cu = MySqlFactory.getCursoDao().buscarCurso(MySqlDataSource.getConexionBD(), codigoMateria);
            
            String codigoMonitoria = Integer.toString(codigoMateria);
            codigoMonitoria = codigoMonitoria+"001";
            
            int codigoMonitoriaEntero = Integer.parseInt(codigoMonitoria);
            
            String nombreCurso = "mon-"+ (cu.getNombreCurso());
                       
            Curso c = new Curso();     
            
            c.setCodigoCurso(codigoMonitoriaEntero);
            c.setNombreCurso(nombreCurso);
            c.setNivel(cu.getNivel());
            c.setCreditos(0);
            c.setHorario(cu.getHorario());
            c.setSalon(cu.getSalon());
           
            boolean agregado = MySqlFactory.getCursoDao().agregar(c, MySqlDataSource.getConexionBD());
            
            int esError = 0;
            if(!agregado){
                esError = 1;
            }
            return "redirect:/personaCurso/add/" + codigoEstudiante + "/" + codigoMonitoriaEntero;
        } catch (Exception e) {
            Logger.getLogger(ContratoController.class.getName()).log(Level.SEVERE, null, e);
            return "redirect:/curso/redirect/" + 1;
        }
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
