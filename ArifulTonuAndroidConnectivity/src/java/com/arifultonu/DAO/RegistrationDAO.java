/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arifultonu.DAO;

import com.arifultonu.dbConnection.DBConnectionHandler;
import com.arifultonu.login.bo.RegistrationBO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArifulTonu
 */
public class RegistrationDAO {

    private Statement oStmt;

    public RegistrationDAO() {

    }

    public void insertDataToDB(String sUserName, String sPassword, String sEmail) throws Exception {
        Connection oConn = DBConnectionHandler.getConnection();
        oStmt = oConn.createStatement();
        String sqlAddQuery = null;

        try {
            sqlAddQuery = "INSERT INTO OCASMN.TESTT (USER_NAME, USER_PASSWORD, EMAIL_ID) values ('" + sUserName + "', '" + sPassword + "', '" + sEmail + "')";
            oStmt.executeUpdate(sqlAddQuery);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                oConn.rollback();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } finally {
            if (oStmt != null) {
                oStmt.close();
            }
            DBConnectionHandler.closeConnection(oConn);
        }

    }

    public List<RegistrationBO> getDataFromDB() throws Exception {
        Connection oConn = DBConnectionHandler.getConnection();
        List<RegistrationBO> list = new ArrayList<RegistrationBO>();
        String sql;
        PreparedStatement ps;
        ResultSet rs;

        try {
            sql = "SELECT USER_NAME, USER_PASSWORD, EMAIL_ID FROM TESTT";
            ps = oConn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            RegistrationBO oRegistrationBO = new RegistrationBO();
            oRegistrationBO.setUserName(rs.getString(1));
            oRegistrationBO.setPassword(rs.getString(2));
            oRegistrationBO.setEmail(rs.getString(3));
            list.add(oRegistrationBO);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                oConn.rollback();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } finally {
            if (oStmt != null) {
                oStmt.close();
            }
            DBConnectionHandler.closeConnection(oConn);
        }
        return list;

    }
}
