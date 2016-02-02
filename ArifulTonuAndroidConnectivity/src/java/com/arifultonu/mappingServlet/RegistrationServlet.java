/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arifultonu.mappingServlet;

import com.arifultonu.DAO.RegistrationDAO;
import com.arifultonu.login.bo.RegistrationBO;
import javax.servlet.annotation.WebServlet;
import static org.apache.jasper.compiler.ELFunctionMapper.map;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author ArifulTonu
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
             List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            JSONObject json = new JSONObject();
            RegistrationDAO registrationDAO = new RegistrationDAO();
            for(RegistrationBO oRegistrationBO:registrationDAO.getDataFromDB()){
                Map<String, Object> map = new HashMap<String, Object>();
                
                map.put("UserName", oRegistrationBO.getUserName());
                map.put("Email", oRegistrationBO.getEmail());                
                map.put("Password", oRegistrationBO.getPassword());                
                list.add(map);
                json.put("UserDataList", list);
                
            }
           // System.out.println("Data List: "+ list);
            System.out.println("Json Nodes >>>: "+ json);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
                    } catch (Exception e) {
                     Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uname = request.getParameter("username");
        System.out.println("username = " + uname);
        String email = request.getParameter("email");
        System.out.println("email = " + email);
        String pass = request.getParameter("pass");
        System.out.println("pass = " + pass);
        

        try {
            RegistrationDAO oRegistrationDAO = new RegistrationDAO();            
            oRegistrationDAO.insertDataToDB(uname, pass, email);
            
        } catch (Exception ex) {
             Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
