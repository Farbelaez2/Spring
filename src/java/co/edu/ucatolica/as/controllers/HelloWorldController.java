/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.controllers;

import co.edu.ucatolica.as.DTOs.Form;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
@RequestMapping("/")
public class HelloWorldController implements Controller{
 
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(ModelMap modelMap, Model model) {
        //model.addAttribute("errores", "mensaje");
        model.addAttribute("aviso", "mensaje");
        model.addAttribute("form", new Form("", null, ""));
        return "persona_evaluar";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{list}")
    public String ind(ModelMap modelMap, Model model, @PathVariable(value = "list")ArrayList<String> errores) {
        model.addAttribute("errores", errores);
        model.addAttribute("aviso", null);
        model.addAttribute("form", new Form("", null, ""));
        return "persona_evaluar";
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