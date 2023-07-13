/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Long
 */
public class DBConnect {

    public static Connection getConnection() throws SQLException {
        try {
            Connection cons = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            cons = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ShopSach");
           String url = "jdbc:sqlserver://localhost:1433;user=sa;password=12;databaseName=ShopSach;";

            cons = DriverManager.getConnection(url);
            return cons;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        System.out.println(c.toString());
        c.close();
    }
}
