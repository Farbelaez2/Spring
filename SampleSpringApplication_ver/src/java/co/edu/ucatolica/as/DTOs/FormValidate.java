/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FormValidate implements Validator{
    
    private ArrayList<String> errores;

    @Override
    public boolean supports(Class<?> type) {
        return Form.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        this.errores = new ArrayList<>();
        Form f = (Form)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, 
                "codigoEstudiante", 
                "required.codigoEstudiante", 
                "El codigo del estudiante es obligatorio"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, 
                "codigoMateria", 
                "required.codigoMateria", 
                "El codigo de la materia es obligatorio"
        );
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, 
                "claveEstudiante",
                "required.claveEstudiante",
                "La clave del estudiante es obligatorio"
        );

        String codigoEstudiante = f.getCodigoEstudiante();
        String claveEstudiante = f.getClaveEstudiante();
        String codigoMateria = f.getCodigoMateria();

        if (!this.verifyNumbers(codigoEstudiante)) {
            errors.rejectValue("codigoEstudiante", "codigoEstudiante.incorrect","El codigo del estudiante solamente puede contener caracteres numericos");
        }

        if (!this.verifyNumbers(codigoMateria)) {
            errors.rejectValue("codigoMateria", "codigoMateria.incorrect", "El codigo de la materia solamente puede contener caracteres numericos");
        }

        if (!this.verifySize(codigoEstudiante, 9, 5)) {
            errors.rejectValue("codigoEstudiante", "codigoEstudiante.incorrect", "El codigo del estudiante debe estar en un rango de caracteres entre 5 a 9");
        }

        if (!this.verifySize(codigoMateria, 3, 1)) {
            errors.rejectValue("codigoMateria", "codigoMateria.incorrect", "El codigo de la materia debe estar en un rango de caracteres entre 1 a 3");
        }
        
        if(!this.verifySize(claveEstudiante, 40, 1)){
            errors.rejectValue("claveEstudiante", "claveEstudiante.incorrect", "La clave del estudiante tiene un rango de caraceteres entre 1 a 40");
        }
        List<ObjectError> erroresTemp = errors.getAllErrors();
        for(ObjectError error : erroresTemp){
            this.errores.add(error.getDefaultMessage());
        }
    }

    private boolean verifyNumbers(String cad) {
        Pattern patron = Pattern.compile("^[0-9]+$");
        Matcher m = patron.matcher(cad);
        return m.find();
    }
    
    private boolean verifySize(String cad, int maxSize, int minSize){
        int length = cad.length();
        return length <= maxSize && length >= minSize;
        //return length <= maxSize && length >= minSize;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }
}
