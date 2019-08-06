/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.ProfileDB;
import database.QRListDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Profile;
import model.Riddle;

/**
 *
 * @author MrAye
 */
public class Controller extends HttpServlet {

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
            out.println("<title>Servlet Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
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
        String page = request.getParameter("page");
        if (request.getSession().getAttribute("email") == null) {
            String email = request.getParameter("email");
            if (email == null) {
                //go back to log in
                String message = "You need to log in first to access this page";
                request.setAttribute("loginmessage", message);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("email", email);
            }
        }
        
        System.out.println("email");
        if (request.getSession().getAttribute("email") != null) {
            String email = (String) request.getSession().getAttribute("email");
            
            if (ProfileDB.isThere(email)) {
                //put welcomeback message
                String profEmail = ProfileDB.getProfile(email).getEmail();
                String message = "Welcome back, " + profEmail.substring(0, profEmail.indexOf('@'));
                if (request.getSession().getAttribute("message") == null) {
                    request.getSession().setAttribute("message", message);
                }
            } else {
                //create new email and say welcome new new email
                ProfileDB.addProfile(new Profile(email));
                String profEmail = ProfileDB.getProfile(email).getEmail();
                String message = "Welcome newcomer, " + profEmail.substring(0, profEmail.indexOf('@'));
                if (request.getSession().getAttribute("message") == null) {
                    request.getSession().setAttribute("message", message);
                }
            }
            System.out.println("SI admin:" + ProfileDB.getProfile(email).isAdmin());
            request.getSession().setAttribute("isAdmin", ProfileDB.getProfile(email).isAdmin());
            if (page == null || page.equals("")) {
                //redirect home
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            } else if (page.equals("scanner") || page.equals("map") || page.equals("leaderboard") || page.equals("logout") || page.equals("edit")) {
                switch (page) {
                    case "scanner":
                        boolean advance = request.getParameter("advance") != null;
                        System.out.println("advance is " + advance);
                        if (advance) {
                            Riddle r = ProfileDB.getProfile(email).getRiddles().next(request.getParameter("advance"));
                            System.out.println("riddlde code  is " + r.getCode());
                            switch (r.getCode()) {
                                case "empty":
                                    //empty message
                                    request.setAttribute("qrmessage", "You've finished the scavenger hunt! No need to scan more codes. Want to play again? Just hit redo.");
                                    break;
                                case "notnow":
                                    request.setAttribute("qrmessage", "You're not on this QR code yet! Try a different one!");
                                    break;
                                case "notours":
                                    request.setAttribute("qrmessage", "This is not one of our QR codes!");
                                    break;
                                case "alreadydone":
                                    request.setAttribute("qrmessage", "You've already done this one, try a different one!");
                                    break;
                                default:
                                    ProfileDB.getProfile(email).advance();
                                    break;
                            }
                        }
                        if (request.getSession().getAttribute("os") == null) {
                            request.getSession().setAttribute("os", request.getParameter("os"));
                        }
                        request.getSession().setAttribute("done", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).isDone());
                        request.setAttribute("riddle", ProfileDB.getProfile(email).getCurrentRiddle());
                        getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
                        break;
                    case "map":
                        request.setAttribute("map", ProfileDB.getProfile(email).getMapProgress());
                        getServletContext().getRequestDispatcher("/map.jsp").forward(request, response);
                        break;
                    case "leaderboard":
                        request.setAttribute("top10", ProfileDB.getTopTen());
                        request.setAttribute("profile", ProfileDB.getProfile(email));
                        getServletContext().getRequestDispatcher("/leaderboard.jsp").forward(request, response);
                        break;
                    case "edit":
                        if (!ProfileDB.isAdmin(email)) {
                            request.setAttribute("qrmessage", "You need to be an admin to do that!");
                            request.setAttribute("riddle", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).getCurrentRiddle());
                            getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
                        } else {
                            if (request.getParameter("qr") == null) {
                                request.setAttribute("qrcodes", QRListDB.getS1());
                                getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
                            } else if (QRListDB.isOurQRCode(request.getParameter("qr"))) {
                                request.setAttribute("theRiddle", QRListDB.find(request.getParameter("qr")));
                                getServletContext().getRequestDispatcher("/editRiddle.jsp").forward(request, response);
                            } else {
                                request.setAttribute("qrmessage", "Not one of our codes");
                                getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
                            }
                        }

                        break;

                    case "logout":
                        Enumeration<String> i = request.getAttributeNames();
                        request.getSession().invalidate();
                        while (i.hasMoreElements()) {
                            request.removeAttribute(i.nextElement());
                        }
                        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                        break;

                }
            } else {
                //redirect home
                getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
            }
        } else {
            String message = "You need to log in first to access this page";
            request.setAttribute("loginmessage", message);
            getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
        }
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
        System.out.println("here");
        if (request.getParameter("action") != null && request.getParameter("action").equals("redo")) {
            Profile p = ProfileDB.getProfile(request.getSession().getAttribute("email").toString());
            p.redo();
            request.setAttribute("riddle", p.getCurrentRiddle());
            request.getSession().setAttribute("done", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).isDone());
            getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("skip")) {
            Profile p = ProfileDB.getProfile(request.getSession().getAttribute("email").toString());
            p.getRiddles().next(p.getRiddles().getMyList().get(0).getCode());
            ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).skip();
            request.setAttribute("riddle", p.getCurrentRiddle());
            request.getSession().setAttribute("done", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).isDone());
            getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("edit")) {
            String code = request.getParameter("itemcode");
            String title = request.getParameter("title");
            String riddle = request.getParameter("riddle");
            QRListDB.editRiddle(code, title, riddle);
            request.setAttribute("qrmessage", "Your changed have been saved");
            request.setAttribute("riddle", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).getCurrentRiddle());
            request.getSession().setAttribute("done", ProfileDB.getProfile(request.getSession().getAttribute("email").toString()).isDone());
            getServletContext().getRequestDispatcher("/QR.jsp").forward(request, response);

        } else {
            if (request.getParameter("action") != null && request.getParameter("action").equals("login")) {
                Enumeration<String> i = request.getAttributeNames();
                request.getSession().invalidate();
                while (i.hasMoreElements()) {
                    request.removeAttribute(i.nextElement());
                }
            }
            doGet(request, response);
        }
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
