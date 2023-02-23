/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoomDAO;
import define.Define;
import dtos.RoomDTO;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "SearchRoomController", urlPatterns = {"/SearchRoomController"})
public class SearchRoomController extends HttpServlet {

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
        RoomDAO roomDAO = new RoomDAO();
        try{
            String ssCheckInDate =(String) session.getAttribute("CHECK_IN_DATE");
            String ssCheckOutDate =(String) session.getAttribute("CHECK_OUT_DATE");
            String txtCheckInDate = request.getParameter("txtCheckInDate");
            String txtCheckOutDate = request.getParameter("txtCheckOutDate");
            if(txtCheckInDate != null  && txtCheckOutDate != null){
                Integer selectedRoomType = 0;
                if(request.getParameter("selectRoomType") != null)
                {
                    selectedRoomType = Integer.parseInt(request.getParameter("selectRoomType"));
                }
                ArrayList<RoomDTO> listRoom = roomDAO.getByParams(selectedRoomType, txtCheckInDate, txtCheckOutDate);
                request.setAttribute("LIST_ROOM", listRoom);
                session.setAttribute("CHECK_IN_DATE", txtCheckInDate);
                session.setAttribute("CHECK_OUT_DATE", txtCheckOutDate);
                
            }else if(ssCheckInDate != null && ssCheckOutDate != null && !ssCheckInDate.isEmpty() && !ssCheckOutDate.isEmpty()){
                ArrayList<RoomDTO> listRoom = roomDAO.getByParams(0, ssCheckInDate, ssCheckOutDate);
                request.setAttribute("LIST_ROOM", listRoom);
            }
        } catch(Exception e){
            e.printStackTrace();
            request.setAttribute("ERROR:", e.getLocalizedMessage());
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
