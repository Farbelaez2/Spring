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
public class OracleFactoryDao extends FactoryDao{

    @Override
    public ObjectMySQLDAO getPersonaDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectMySQLDAO<Contrato> getContratoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectMySQLDAO<Curso> getCursoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectMySQLDAO<PersonaHasCurso> getPersonaHasCursoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObjectMySQLDAO<Matricula> getMatriculaDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
