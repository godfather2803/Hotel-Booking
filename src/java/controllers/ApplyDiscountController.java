/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DiscountCodeDAO;
import define.Define;
import dtos.DiscountCodeDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ApplyDiscountController extends HttpServlet {

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
        String url = Define.CART_PAGE;
        HttpSession session = request.getSession();
        try {
            String email = (String) session.getAttribute("EMAIL");
            String discountID = request.getParameter("txtDiscountID");
            DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
            DiscountCodeDTO discountCode = new DiscountCodeDTO();
            discountCode = discountCodeDAO.isExist(discountID);
            String msg = "This discount code is not existed or expired";
            if(discountCode.getDiscountID() != null){
                boolean isUsed = discountCodeDAO.isUsed(discountCode.getDiscountID(), email);
                discountCode.setIsUsed(isUsed);
                if(discountCode.getIsUsed()){
                    msg = "You can only use this discount code once :(";
                }else{
                    request.setAttribute("DISCOUNT_CODE", discountCode);
                    msg = "Discount code applied :D";
                }
            }
            request.setAttribute("APPLY_DISCOUNT_MSG", msg);
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
