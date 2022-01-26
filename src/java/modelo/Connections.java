/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jurgen
 */
public class Connections {
    private Connection cone;
    private Statement estado;

    public Connections() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            this.cone = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bd_airlands",
                    "bustamante",
                    "12345678");
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error al conectar a la base de datos");
        } catch (SQLException ex) {
            System.err.println("Error al conectarse a la base de datos");
        }
    }

    //Metodo que permite modificar datos de una tabla
    public boolean setProcess(String sql) {
        try {
            this.estado = this.cone.createStatement();
            return this.estado.execute(sql);
        } catch (SQLException es) {
            return false;
        }
    }// Fin del metodo setProcess

    //Metodo que realiza una consulta en la tabla
    public ResultSet getConsult(String sql) {
        ResultSet result = null;
        try {
            this.estado = this.cone.createStatement();
            result = this.estado.executeQuery(sql);
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }//Fin del metodo ResultSet

    //Metodo que cierra la comunicacion con la base de datos para que 
    //nadie pueda ingresar luego de terminar la ejecucion del programa 
    public void close() {
        try {
            this.cone.close();
            this.estado.close();
        } catch (SQLException ex) {
        }
    }// Fin del metodo close
}
