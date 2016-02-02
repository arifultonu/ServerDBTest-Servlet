/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arifultonu.DAO;

import com.arifultonu.dbConnection.DBConnectionHandler;
import com.arifultonu.login.bo.LoginBO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArifulTonu
 */
public class LoginDAO {

    public static List<LoginBO> getLoginInfo() throws Exception {

        List<LoginBO> list = new ArrayList<LoginBO>();
        Connection oConn = DBConnectionHandler.getConnection();
        Statement oStmt = null;
        ResultSet oRs = null;
        oStmt = oConn.createStatement();
        StringBuilder sql = new StringBuilder();

        try {
            sql.append("SELECT NVL(USER_NAME, ' '), NVL(PASSWORD, ' ') ");
            sql.append("FROM UserInfo ");
            oRs = oStmt.executeQuery(sql.toString());
            
            while(oRs.next()){
             LoginBO oLoginBO = new LoginBO();
             oLoginBO.setUserName(oRs.getString(1));
             oLoginBO.setPassword(oRs.getString(2));
             list.add(oLoginBO);
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
