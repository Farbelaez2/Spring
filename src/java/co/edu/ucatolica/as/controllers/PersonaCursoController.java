package co.edu.ucatolica.as.controllers;

import co.edu.ucatolica.as.DAOs.FactoryDao;
import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.DTOs.Curso;
import co.edu.ucatolica.as.DTOs.PersonaHasCurso;
import co.edu.ucatolica.as.DTOs.PersonaHasCursoPK;
import co.edu.ucatolica.as.bds.MySqlDataSource;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@RequestMapping("/personaCurso")
public class PersonaCursoController implements org.springframework.stereotype.Controller{
    
    @RequestMapping(method = RequestMethod.GET, value = "/add/{codigoEstudiante}/{codigoMateria}")
    public String reditectformMonitorias(@PathVariable (value = "codigoEstudiante") int codigoEstudiante, 
            @PathVariable (value = "codigoMateria") int codigoMateria,
            Model model){
        String aviso = "";
        try {
            FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
            Persona p = MySqlFactory.getPersonaDao().buscarPersona(MySqlDataSource.getConexionBD(), codigoEstudiante);
            Curso curso = MySqlFactory.getCursoDao().buscarCurso(MySqlDataSource.getConexionBD(), codigoMateria);
            PersonaHasCurso pc = new PersonaHasCurso();
            
            pc.setNota(-1);
            pc.setPersona(p);
            pc.setCurso(curso);
                                  
            boolean agregado = MySqlFactory.getPersonaHasCursoDao().agregar(pc, MySqlDataSource.getConexionBD());
            int esError = 0;
            if(!agregado){
                esError = 1;
            }
            
            
            String[] caracteresMateria = String.valueOf(codigoMateria).split("");
            ArrayList<String> list = new ArrayList<>();
            for (String c : caracteresMateria){
                list.add(c);
            }
            
            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
            String cad = "";
            
            for(String c : list){
                cad = cad + c;
            }
            
            int codigoOriginalMateria = Integer.parseInt(cad);

            
            return "redirect:/matricula/add/" + codigoEstudiante + "/" + codigoOriginalMateria;
        } catch (Exception e) {
            Logger.getLogger(ContratoController.class.getName()).log(Level.SEVERE, null, e);
            return "redirect:/personaCurso/redirect/" + 1;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/redirect/{esError}")
    public String redirectRegister(@PathVariable(value = "esError") int esError, Model model){
        String aviso = "";
        if (esError == 1){
            aviso = "Hubo un error con el registro de persona_curso\n";   
        } else{
            aviso = "Se ha registrado el persona_curso exitosamente\n";
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
