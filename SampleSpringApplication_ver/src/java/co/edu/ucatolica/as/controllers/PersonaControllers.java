    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.controllers;

/**
 *
 * @author NixonD
 */
import co.edu.ucatolica.as.DAOs.FactoryDao;
import co.edu.ucatolica.as.DAOs.ObjectMySQLDAO;
import co.edu.ucatolica.as.DTOs.Form;
import co.edu.ucatolica.as.DTOs.FormValidate;
import co.edu.ucatolica.as.bds.MySqlDataSource;
import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/personas")
public class PersonaControllers implements Controller {

    /*@RequestMapping(method = RequestMethod.GET, value = "/monitorias")
    public String mostrarFormMonitorias(Model model){
        return "persona_evaluar";
    }
¨*/
    private FormValidate formValidate;

    public PersonaControllers() {
        this.formValidate = new FormValidate();
    }
    
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/monitorias/validate")
    public String validarDatos(
            @ModelAttribute("form") @Valid Form form, 
            HttpServletRequest req, 
            SessionStatus status, 
            Model model, 
            BindingResult br) {
        
        this.formValidate.validate(form, br);
        
        if (!br.hasErrors()) {
            try {
                int codigoEstudiante = Integer.parseInt(form.getCodigoEstudiante());
                String claveEstudiante = form.getClaveEstudiante();
                int codigoMateria = Integer.parseInt(form.getCodigoMateria());

                FactoryDao MySqlFactory = FactoryDao.getFactory(FactoryDao.MYSQL_FACTORY);
                ArrayList<String> errores = MySqlFactory.getPersonaDao().verificarMonitoria(MySqlDataSource.getConexionBD(), codigoEstudiante, codigoMateria, claveEstudiante);
                if (errores.size() == 0) {
                    return "redirect:/contrato/add/" + codigoEstudiante + "/" + codigoMateria;
                } else {
                    String aviso = "";

                    for (String error : errores) {
                        aviso = aviso + error + ", ";
                    }
                    model.addAttribute("aviso", aviso);
                    return "redirect:/";
                }
            } catch (Exception e) {
                Logger.getLogger(PersonaControllers.class.getName()).log(Level.SEVERE, null, e);
                model.addAttribute("aviso", "");
                System.out.println("Excepcion ocurrida");
                System.out.println(e.getMessage());
                return "redirect:/";
            }
        } else{
            System.out.println("Hay un error en la validacion");
            ArrayList<String> errores = this.formValidate.getErrores();
            model.addAttribute("errores", errores);
            
            for(String error : errores){
                System.out.println(error);
            }
            model.addAttribute("form", new Form());
            return "redirect:/personas/send/" + errores;
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/send/{list}")
    public String validarDatos(Model model, @PathVariable(value = "list") ArrayList<String> errores){
        model.addAttribute("errores", errores);
        return "redirect:/" + errores;
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
