/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author stein
 */
@WebServlet(name = "CotroladorRegistro", urlPatterns = {"/CotroladorRegistro"})
public class CotroladorRegistro extends HttpServlet {

    private HttpSession session;

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

        String nombre = request.getParameter("nombre");
        String pass = request.getParameter("contrasenia");
        if (nombre == null || pass == null) {

        }
        if (request.getParameter("enviar") != null) {
            RequestDispatcher despachador = getServletContext().getRequestDispatcher("/index.xhtml");
            session = request.getSession(true);
            UsuarioDao us = new UsuarioDao();
            List<Usuario> usuarios = us.obtenListaUsuarios();
            Usuario usuario = us.getUsuario(nombre, pass);
            if (usuario != null) {
                session.setAttribute("usuario", usuario);
                despachador.forward(request, response);

            } else {
                request.setAttribute("inicio", "Usuario o contraseña no validos");
                despachador.forward(request, response);

            }

        }
        if (request.getParameter("registrar") != null && request.getParameter("registrar").equals("Registrar")) {
            RequestDispatcher despachador = getServletContext().getRequestDispatcher("/Registro.html");
            despachador.forward(request, response);
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
