/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @http://rinconprogramadorcito.blogspot.com.co/2010/11/patron-dao-data-access-object.html
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Curso;
import co.edu.ucatolica.as.DTOs.Matricula;
import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.DTOs.PersonaHasCurso;
/**
 *
 * @author sala5
 */
public abstract class FactoryDao {
    public static final int Oracle_FACTORY = 1;
    public static final int MYSQL_FACTORY = 2;

    public abstract ObjectMySQLDAO<Persona> getPersonaDao();
    public abstract ObjectMySQLDAO<Contrato> getContratoDao();
    public abstract ObjectMySQLDAO<Curso> getCursoDao();
    public abstract ObjectMySQLDAO<PersonaHasCurso> getPersonaHasCursoDao();
    public abstract ObjectMySQLDAO<Matricula> getMatriculaDao();
    
    

    public static FactoryDao getFactory(int claveFactory){
        switch(claveFactory){
            case Oracle_FACTORY:
                return new OracleFactoryDao();
            case MYSQL_FACTORY:
                return new MySqlFactoryDao();
            default:
                throw new IllegalArgumentException();
        }
    }    
}
