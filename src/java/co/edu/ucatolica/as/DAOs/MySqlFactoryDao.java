/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Curso;
import co.edu.ucatolica.as.DTOs.Matricula;
import co.edu.ucatolica.as.DTOs.PersonaHasCurso;

/**
 *
 * @author sala5
 */
public class MySqlFactoryDao extends FactoryDao {

    @Override
    public ObjectMySQLDAO getPersonaDao() {
        return new ObjectMySQLDAO();
    } 

    @Override
    public ObjectMySQLDAO<Contrato> getContratoDao() {
        return new ObjectMySQLDAO();
    }

    @Override
    public ObjectMySQLDAO<Curso> getCursoDao() {
        return new ObjectMySQLDAO();
    }
    @Override
    public ObjectMySQLDAO<PersonaHasCurso> getPersonaHasCursoDao() {
        return new ObjectMySQLDAO();
    }
    
    @Override
    public ObjectMySQLDAO<Matricula> getMatriculaDao(){
        return new ObjectMySQLDAO();
    }
    
}
