/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arifultonu.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArifulTonu
 */
public class DBConnectionHandler {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "tonu","tonu");
            con = DriverManager.getConnection("jdbc:oracle:thin:@10.11.201.161:1521:ORCL", "ocasmn", "ocasmn"); // database for stlbas

            //con = DriverManager.getConnection("jdbc:oracle:thin:@10.11.201.251:1521:stlbas", "mobibank", "mobibank"); // database for stlbas
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
