package com.atypon.dbproject.controllers.editor;

import com.atypon.dbproject.database.InMemoryDB;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Editor/DeleteQuote")
public class DeleteQuoteServlet extends HttpServlet {



    private InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        inMemoryDB.removeQuote(Integer.parseInt(request.getParameter("ID")));
        response.sendRedirect("/Editor/EQuotes");
    }


}
