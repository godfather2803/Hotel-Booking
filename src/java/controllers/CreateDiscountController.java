/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DiscountCodeDAO;
import daos.UserDAO;
import define.Define;
import dtos.DiscountCodeDTO;
import dtos.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateDiscountController", urlPatterns = {"/CreateDiscountController"})
public class CreateDiscountController extends HttpServlet {

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
        HttpSession session = request.getSession();
        try{
            String txtDiscountID = request.getParameter("txtDiscountID");
            Integer percent = Integer.parseInt(request.getParameter("txtPercent"));
            String txtExpireDate = request.getParameter("txtExpireDate");
            DiscountCodeDTO discountCode = new DiscountCodeDTO(txtDiscountID, percent, java.sql.Date.valueOf(txtExpireDate));
            DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
            boolean isSuccess = discountCodeDAO.insert(discountCode);
            String msg = "";
            if(isSuccess){
                msg = "Create success";
            } else msg = "This DiscountID isExisted!!!";
            request.setAttribute("CREATE_MSG", msg);
            url = Define.CREATE_DISCOUNT_PAGE;
        } catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", e.getLocalizedMessage());
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
