package com.atypon.dbproject.controllers.viewer;

import com.atypon.dbproject.database.InMemoryDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Viewer/BookQuotes")
public class BookQuotesServlet extends HttpServlet {

    private InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("ID"));

        request.setAttribute("list", inMemoryDB.getBookQuotes(id));
        request.getRequestDispatcher("/WEB-INF/Viewer/BookQuotes.jsp").forward(request,response);
    }

}
