package co.edu.ucatolica.as.DAOs;

import java.sql.Connection;


public interface DAOImpl<T> {
    public abstract boolean agregar(T obj, Connection con);
}
