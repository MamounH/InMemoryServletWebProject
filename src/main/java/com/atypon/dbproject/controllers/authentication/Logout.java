package com.atypon.dbproject.controllers.authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.setAttribute("logout","You have been logged out.");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }


}
