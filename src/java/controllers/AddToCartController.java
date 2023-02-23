/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import define.Define;
import dtos.CartItemDTO;
import dtos.RoomDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import utils.DateUtils;
/**
 *
 * @author DELL
 */
public class AddToCartController extends HttpServlet {

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
        String url = Define.SEARCH_ROOM_CONTROLLER;
        HttpSession session = request.getSession();
        DateUtils dateUtils = new DateUtils();
        try {
            Integer roomID = Integer.parseInt(request.getParameter("txtRoomID"));
            ArrayList<CartItemDTO> cart = (ArrayList<CartItemDTO>) session.getAttribute("Cart");
            String msg = "";
            if(cart != null){
                boolean isExisted = false;
                for(CartItemDTO room : cart){
                    if(room.getRoomID() == roomID){
                        msg = "Existed";
                        isExisted = true;
                        break;
                    }
                }
                if(!isExisted){
                    RoomDAO roomDAO = new RoomDAO();
                    RoomDTO room = roomDAO.getByID(roomID);
                    CartItemDTO cartItem = new CartItemDTO();
                    cartItem.setRoomID(roomID);
                    cartItem.setOneDayPrice(room.getPrice());
                    cartItem.setRoomType(room.getRoomType());
                    String txtCheckInDate = (String) session.getAttribute("CHECK_IN_DATE");
                    String txtCheckOutDate = (String) session.getAttribute("CHECK_OUT_DATE");
                    cartItem.setCheckInDate(txtCheckInDate);
                    cartItem.setCheckOutDate(txtCheckOutDate);
                    int dayDiffs = dateUtils.dayDiff(txtCheckInDate, txtCheckOutDate);
                    cartItem.setPrice((long) cartItem.getOneDayPrice() * dayDiffs);
                    cart.add(cartItem);
                    msg = "Added";
                }
            } else{
                cart = new ArrayList<>();
                RoomDAO roomDAO = new RoomDAO();
                RoomDTO room = roomDAO.getByID(roomID);
                CartItemDTO cartItem = new CartItemDTO();
                cartItem.setRoomID(roomID);
                cartItem.setOneDayPrice(room.getPrice());
                cartItem.setRoomType(room.getRoomType());
                String txtCheckInDate = (String) session.getAttribute("CHECK_IN_DATE");
                String txtCheckOutDate = (String) session.getAttribute("CHECK_OUT_DATE");
                cartItem.setCheckInDate(txtCheckInDate);
                cartItem.setCheckOutDate(txtCheckOutDate);
                int dayDiffs = dateUtils.dayDiff(txtCheckInDate, txtCheckOutDate);
                cartItem.setPrice((long) cartItem.getOneDayPrice() * dayDiffs);
                cart.add(cartItem);
                msg = "Added";
            }
            request.setAttribute("ADD_TO_CART_MSG", msg);
            session.setAttribute("Cart", cart);
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
