/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BookingDAO;
import daos.BookingDetailDAO;
import daos.DiscountCodeDAO;
import define.Define;
import dtos.CartItemDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DateUtils;
import utils.MailUtils;

/**
 *
 * @author DELL
 */
public class BookingController extends HttpServlet {

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
        String url = Define.INDEX_PAGE;
        HttpSession session = request.getSession();
        try{
            String txtTotalPrice = request.getParameter("txtTotalPrice");
            BigDecimal b = new BigDecimal(txtTotalPrice);
            Long totalPrice = b.longValue();
            String discountID = request.getParameter("txtDiscountID");
            String email = (String) session.getAttribute("EMAIL");
            ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("Cart");
            BookingDAO bookingDAO = new BookingDAO();
            int insertedBookingID = bookingDAO.insert(email, totalPrice);
            if(insertedBookingID != 0){
                DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
                discountCodeDAO.use(discountID, email);
                BookingDetailDAO bookingDetailDAO = new BookingDetailDAO();
                for (CartItemDTO item : cart) {
                    bookingDetailDAO.insert(insertedBookingID, item);
                }
                request.setAttribute("BOOKING_MESSAGE", "BOOKING SUCCESSFUL");
                session.removeAttribute("Cart");
                
                String subject = "Please verify your booking: " + insertedBookingID;
                DateUtils dateUtils = new DateUtils();
                String now = dateUtils.getCurrentDate();
                String verificationCode = insertedBookingID + "_" + now.replace("-", "");
                String content = "You verification code: " + verificationCode + "<br/>";
                        content += "Please confirm at http://localhost:8080/verify_booking.jsp"; 
                MailUtils mailUtils = new MailUtils();
                mailUtils.sendEmail(email, subject, content);
            }
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
