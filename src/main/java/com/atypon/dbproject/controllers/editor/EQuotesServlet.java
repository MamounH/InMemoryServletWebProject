package com.atypon.dbproject.controllers.editor;

import com.atypon.dbproject.database.InMemoryDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Editor/EQuotes")
public class EQuotesServlet extends HttpServlet {


    private InMemoryDB inMemoryDB;


    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", inMemoryDB.getAllQuotes());
        request.getRequestDispatcher("/WEB-INF/Editor/EQuotes.jsp").forward(request,response);
    }
}
