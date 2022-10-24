/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class GetConnection {

    public static Connection getConnect() {
        Connection con = null;
        String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String Url = "jdbc:sqlserver://localhost:1433;databaseName=DatabaseRecipes";
        try {
            Class.forName(Driver);
            con = DriverManager.getConnection(Url, "sa", "1234$");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GetConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
}
