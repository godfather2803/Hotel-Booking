/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import define.Define;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class RegisterController extends HttpServlet {

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
        String url = Define.LOGIN_PAGE;
        try{
            String txtEmail = request.getParameter("txtEmail");
            UserDAO userDAO = new UserDAO();
            boolean isExisted = userDAO.isExisted(txtEmail);
            String msg = "";
            if(isExisted){
                msg = "This email is existed!";
                url = Define.REGISTER_PAGE;
            } else{
                String txtPassword = request.getParameter("txtPassword");
                String txtName = request.getParameter("txtName");
                String txtPhone = request.getParameter("txtPhone");
                String txtAddress = request.getParameter("txtAddress");
                UserDTO user = new UserDTO(txtEmail, txtPassword, txtPhone, txtName, txtAddress);
                boolean isSuccess = userDAO.insert(user);
                if(!isSuccess){
                   msg = "STH WRONG";
                   url = Define.REGISTER_PAGE;
                } 
            }
            request.setAttribute("REGISTER_MSG", msg);
        } catch(Exception e){
            e.printStackTrace();
            request.setAttribute("ERROR", e.getLocalizedMessage());
            url = Define.ERROR_PAGE;
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
