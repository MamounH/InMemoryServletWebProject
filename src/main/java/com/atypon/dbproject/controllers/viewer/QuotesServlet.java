package com.atypon.dbproject.controllers.viewer;

import com.atypon.dbproject.database.InMemoryDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Viewer/Quotes")
public class QuotesServlet extends HttpServlet {

    private InMemoryDB inMemoryDB;


    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", inMemoryDB.getAllQuotes());
        request.getRequestDispatcher("/WEB-INF/Viewer/Quotes.jsp").forward(request,response);
    }
}
