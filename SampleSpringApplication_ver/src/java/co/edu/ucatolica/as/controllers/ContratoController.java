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
@RequestMapping("/contrato")
public class ContratoController implements org.springframework.stereotype.Controller{
    
    @RequestMapping(method = RequestMethod.GET, value = "/add/{codigoEstudiante}/{codigoMateria}")
    public String reditectformMonitorias(@PathVariable (value = "codigoEstudiante") int codigoEstudiante, 
            @PathVariable (value = "codigoMateria") int codigoMateria,
            Model model){
        String aviso = "";
        try {
            FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
            Calendar calendar = new GregorianCalendar();
            String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
            String month = calendar.get(Calendar.MONTH) + "";
            String year = calendar.get(Calendar.YEAR) + "";
            String date = year + "-" + month + "-" + day;
            Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-25");
            
            Contrato c = new Contrato();
            c.setCodigoContrago(null);
            c.setFechaInicio(fechaInicio);
            c.setFechaFin(fechaFin);
            
            Persona p = MySqlFactory.getContratoDao().buscarPersona(MySqlDataSource.getConexionBD(), codigoEstudiante);
            String tipoPrograma = MySqlFactory.getContratoDao().obtenerTipoProgramaPersona(MySqlDataSource.getConexionBD(), codigoEstudiante);
            
            int salario = 0;
            if (tipoPrograma.equals("Pregrado")){
                salario = 500000;
            } else if (tipoPrograma.equals("Postgrado")){
                salario = 750000;
            }
            c.setPersonaIdentifcacion(p);
            c.setSalario(salario);           
            
            boolean agregado = MySqlFactory.getContratoDao().agregar(c, MySqlDataSource.getConexionBD());
            int esError = 0;
            if(agregado){
                
            }
            return "redirect:/curso/add/" + codigoEstudiante + "/" + codigoMateria;
        } catch (ParseException ex) {
            Logger.getLogger(ContratoController.class.getName()).log(Level.SEVERE, null, ex);
            return "redirect:/contrato/redirect/" + 1;
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
