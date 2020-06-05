/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucatolica.as.DAOs;

import co.edu.ucatolica.as.DTOs.Persona;
import co.edu.ucatolica.as.DTOs.Contrato;
import co.edu.ucatolica.as.DTOs.Curso;
import co.edu.ucatolica.as.DTOs.Matricula;
import co.edu.ucatolica.as.DTOs.PersonaHasCurso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nixoduaa
 */
public class ObjectMySQLDAO<T extends Object> implements DAOImpl<T> {

    @Override
    public boolean agregar(T obj, Connection con) {
        PreparedStatement pstmt = null;
        try {
            if(obj instanceof Contrato){
                Contrato c = (Contrato) obj;
                this.agregarContrato(c, con, pstmt);
            }
            else if(obj instanceof Curso){
                Curso cu = (Curso) obj;
                this.agregarCurso(cu, con, pstmt);
            }
            else if(obj instanceof PersonaHasCurso){
                PersonaHasCurso pc = (PersonaHasCurso) obj;
                this.agregarPersonaCurso(pc, con, pstmt);
            }
            else if(obj instanceof Matricula){
                Matricula m = (Matricula) obj;
                this.actualizarMatricula(m, con, pstmt);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList<String> verificarMonitoria(Connection con, int codigoEstudiante, int codigoMateria, String clave){
        ArrayList<String> errores = new ArrayList<>();
        try {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando evaluar requerimientos monitoria...");
                      
            String sentence = "select* from persona where identifcacion = " + codigoEstudiante + "";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sentence);
            boolean verificacion = true;
            int contador = 0;
            while(rs.next()){
                contador++;
            }
            
            if (contador == 0){
                errores.add("La estudiante con el codigo " + codigoEstudiante + " no esta registrado\n");
                verificacion = false;
            }else{
            
                sentence = "select password from persona where identifcacion = " + codigoEstudiante + "";
                rs = s.executeQuery(sentence);
                String pass = "";
                while (rs.next()) {
                    pass = rs.getString(1);
                }

                if (!clave.equals(pass)) {
                    verificacion = false; 
                    errores.add("La clave del estudiante no coincide");
                }
            } 
            
            if(verificacion != false) {
                //MIRAR SI ES DE PREGRADO O POSTGRADO
                sentence = "SELECT a.tipoPrograma FROM persona p INNER JOIN programa a ON p.Programa_codigoPrograma = a.codigoPrograma AND p.identifcacion = " + codigoEstudiante + "";
                
                rs = s.executeQuery(sentence);
                String tipoPrograma = "";
                while (rs.next()) {
                    tipoPrograma = rs.getString(1);
                }

                if (tipoPrograma.equals("") || tipoPrograma == null) {
                    errores.add("El estudiante no tiene asignado un programa\n");
                }

                //VALIDAR SI LA MATERIA EXISTE
                sentence = "SELECT CodigoCurso FROM curso WHERE CodigoCurso = " + codigoMateria + "";
                rs = s.executeQuery(sentence);
                contador = 0;
                while (rs.next()) {
                    contador++;
                }

                if (contador == 0) {
                    errores.add("La materia con el codigo " + codigoMateria + " no esta registrada\n");
                }

                //SI VIO EL CURSO
                sentence = "SELECT * FROM persona_has_curso WHERE persona_identifcacion = " + codigoEstudiante + " AND Curso_CodigoCurso = " + codigoMateria;
                rs = s.executeQuery(sentence);
                contador = 0;
                while (rs.next()) {
                    contador++;
                }
                boolean vioMateria = false;
                if (contador >= 1) {
                    //VIO LA MATERIA
                    vioMateria = true;
                } else {
                    //NO VIO LA MATERIA
                    errores.add("El estudiante no ha visto la materia con el codigo " + codigoMateria + ". Por lo tanto no puede verla\n");
                    vioMateria = false;
                }

                sentence = "SELECT nota FROM persona_has_curso WHERE persona_identifcacion = " + codigoEstudiante + "";
                rs = s.executeQuery(sentence);
                ArrayList<Double> notas = new ArrayList<>();
                while (rs.next()) {
                    notas.add(Double.parseDouble(rs.getInt(1) + ""));
                }
                
                //CALCULAR PROMEDIO
                
                int tamañoNotas = 0;
                double promedio = 0;
                
                for(int i = 0; i < notas.size(); i++){
                    if(notas.get(i)!= -1){
                        System.out.println(notas.get(i));
                        promedio = promedio + notas.get(i);
                        tamañoNotas++;
                    }
                }

                promedio = ((double) (promedio)) / ((double) tamañoNotas);

                //VERIFICA EL PROMEDIO, PARA EL ESTUDIANTE DE PREGRADO DEBE SER MAYOR A 76,
                //SI ES DE POSTGRADO DEBE SER 84
                if (tipoPrograma.equals("Pregrado")) {

                    if (promedio < 76) {
                        errores.add("El estudiante no cumple con el promedio estipulado. El promedio es de " + promedio + "\n");
                    }

                    //EL ESTUDIANTE DE PREGRADO DEBE ESTAR INSCRITO EN MENOS DE 22 CREDITOS (SUMATORIA)
                    sentence = "SELECT SUM(c.Creditos) from curso c INNER JOIN persona_has_curso p ON p.Curso_CodigoCurso = c.CodigoCurso AND persona_identifcacion = " + codigoEstudiante + "";
                    rs = s.executeQuery(sentence);
                    int cantidadCreditos = 0;
                    while (rs.next()) {
                        cantidadCreditos = rs.getInt(1);
                    }

                    if (cantidadCreditos >= 22) {
                        errores.add("El estudiante sobrepasa el numero de creditos (22) para poder ser monitor. Actualmente suma " + cantidadCreditos + "\n");
                    }

                } else if (tipoPrograma.equals("Postgrado")) {
                    //PARA POSTGRADO, NO TENGA ASIGNADO OTRA MONITORIA
                    sentence = "select * from persona_has_curso WHERE persona_identifcacion = " + codigoEstudiante + " and nota = -1";
                    rs = s.executeQuery(sentence);
                    contador = 0;
                    while (rs.next()) {
                        contador++;
                    }

                    if (contador >= 1) {
                        //EL ESTUDIANTE DE POSTGRADO YA TIENE UNA MONITORIA
                        errores.add("El estudiante de postgrado ya tiene una monitoria vigente. No puede ser monitor" + "\n");
                    }

                    if (promedio < 84) {
                        errores.add("El estudiante no cumple con el promedio estipulado. El promedio es de" + promedio + "\n");
                    }
                    System.out.println("El promedio es " + promedio);
                }

                String codigoMonitoria = codigoMateria + "001";

                //LA MATERIA NO TIENE QUE TENER MONITOR FIJADO.
                sentence = "select * from persona_has_curso WHERE Curso_CodigoCurso = " + codigoMonitoria + "";
                rs = s.executeQuery(sentence);
                contador = 0;
                while (rs.next()) {
                    contador++;
                }

                if (contador >= 1) {
                    //LA MATERIA TIENE MONITOR
                    errores.add("La materia escogida ya tiene un monitor asignado\n");
                }

            } 
            con.close();
            return errores;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return errores;
        }
    }

    private void agregarContrato(Contrato c, Connection con, PreparedStatement pstmt) throws SQLException {

        Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando crear contrato...");

        pstmt = con.prepareStatement("INSERT INTO contrato (fecha_inicio ,fecha_fin , "
                + " salario, persona_identifcacion)"
                + " VALUES (?,?,?,?)");

        java.sql.Date sqlDateFin = new java.sql.Date(c.getFechaFin().getTime());
        java.sql.Date sqlDateInicio = new java.sql.Date(c.getFechaInicio().getTime());
        pstmt.setDate(1, sqlDateInicio);
        pstmt.setDate(2, sqlDateFin);
        pstmt.setInt(3, c.getSalario());
        pstmt.setInt(4, c.getPersonaIdentifcacion().getIdentifcacion());

        pstmt.execute();

        con.close();

    }

    public Persona buscarPersona(Connection con, int codigoEstudiante) {

        try {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando buscar persona...");
            
            String sentence = "select* from persona where identifcacion = " + codigoEstudiante + "";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sentence);
            
            Persona per = new Persona();
            while (rs.next()) {
                per.setIdentifcacion(rs.getInt(1));
                per.setNombre1(rs.getString(2));
                per.setNombre2(rs.getString(3));
                per.setApellido1(rs.getString(4));
                per.setApellido2(rs.getString(5));
                per.setGenero(rs.getString(6));
                per.setTelefono(rs.getString(7));
                per.setEmail(rs.getString(8));
                per.setFechaNacimiento(rs.getDate(9));
                per.setTipoPersona(rs.getString(10));
            }
            return per;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String obtenerTipoProgramaPersona(Connection con, int codigoEstudiante){
        try {
            Statement s = con.createStatement();
            String sentence = "SELECT a.tipoPrograma FROM persona p INNER JOIN programa a ON p.Programa_codigoPrograma = a.codigoPrograma AND p.identifcacion = " + codigoEstudiante + "";
            ResultSet rs = s.executeQuery(sentence);
            String tipoPrograma = "";
            while(rs.next()){
                tipoPrograma = rs.getString(1);
            }
            return tipoPrograma;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void agregarCurso(Curso cu, Connection con, PreparedStatement pstmt)throws SQLException {
        Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando crear curso...");

        pstmt = con.prepareStatement("INSERT INTO curso (CodigoCurso ,NombreCurso , "
                + " nivel, Creditos, horario, salon)"
                + " VALUES (?,?,?,?,?,?)");

        pstmt.setInt(1, cu.getCodigoCurso());
        pstmt.setString(2, cu.getNombreCurso());
        pstmt.setInt(3, cu.getNivel());
        pstmt.setInt(4, cu.getCreditos());
        pstmt.setString(5, cu.getHorario());
        pstmt.setString(6, cu.getSalon());
        pstmt.execute();

        con.close();
    }
    
    public Curso buscarCurso(Connection con, int codigoMateria) {

        try {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando buscar curso...");
            
            String sentence = "select* from curso where CodigoCurso = " + codigoMateria + "";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sentence);
            
            Curso cur = new Curso();
            while (rs.next()) {
                
                cur.setCodigoCurso(rs.getInt(1));
                cur.setNombreCurso(rs.getString(2));
                cur.setNivel(rs.getInt(3));
                cur.setCreditos(rs.getInt(4));
                cur.setHorario(rs.getString(5));
                cur.setSalon(rs.getString(6));
            }
            return cur;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void agregarPersonaCurso(PersonaHasCurso pc, Connection con, PreparedStatement pstmt)throws SQLException {
        Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando crear Persona Curso...");
        
        
        pstmt = con.prepareStatement("INSERT INTO persona_has_curso (persona_identifcacion ,Curso_CodigoCurso , "
                + " nota)"
                + " VALUES (?,?,?)");
        
        pstmt.setInt(1, pc.getPersona().getIdentifcacion());
        pstmt.setInt(2, pc.getCurso().getCodigoCurso());
        pstmt.setInt(3, pc.getNota());
               
        
        pstmt.execute();

        con.close();
    }
    public Matricula buscarMatricula(Connection con, int codigoEstudiante) {

        try {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando buscar matricula...");
            
            String sentence = "select* from matricula where persona_identifcacion = " + codigoEstudiante+ "";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sentence);
            
            Matricula ma = new Matricula();
            while (rs.next()) {
                
                ma.setCodigoMatricula(rs.getInt(1));
                ma.setCosto(rs.getInt(2));
                ma.setEstado(rs.getString(3));
                ma.setFechaPago(rs.getDate(4));
                ma.setFechaExtraordinaria(rs.getDate(5));
                ma.setFechaExtraordianaria2(rs.getDate(6));
            }
            
            System.out.println(ma.getCodigoMatricula());
            
            return ma;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void actualizarMatricula(Matricula m, Connection con, PreparedStatement pstmt) throws SQLException {
        
        Logger.getLogger(ObjectMySQLDAO.class.getName()).log(Level.INFO, "Ejecutando actualizar matricula..");
        pstmt = con.prepareStatement("UPDATE matricula"
                + " SET"
                + " codigoMatricula=?,"
                + " costo=?,"
                + " estado=?,"
                + " fecha_pago=?,"
                + " fecha_extraordinaria=?,"
                + " fecha_extraordianaria2=?,"
                + " persona_identifcacion=?"
                + " WHERE codigoMatricula="+ m.getCodigoMatricula());
        
        java.sql.Date sqlDateFechaPago = new java.sql.Date(m.getFechaPago().getTime());
        java.sql.Date sqlDateFechaExtraordinaria = new java.sql.Date(m.getFechaExtraordinaria().getTime());
        java.sql.Date sqlDateFechaExtraordinaria2 = new java.sql.Date(m.getFechaExtraordianaria2().getTime());
        pstmt.setInt(1, m.getCodigoMatricula());
        pstmt.setInt(2, m.getCosto());
        pstmt.setString(3, m.getEstado());
        pstmt.setDate(4, sqlDateFechaPago);
        pstmt.setDate(5, sqlDateFechaExtraordinaria);
        pstmt.setDate(6, sqlDateFechaExtraordinaria2);
        pstmt.setInt(7, m.getPersonaIdentifcacion().getIdentifcacion());
        
        pstmt.executeUpdate();
            
        con.close();
    }
    
    
    
}
