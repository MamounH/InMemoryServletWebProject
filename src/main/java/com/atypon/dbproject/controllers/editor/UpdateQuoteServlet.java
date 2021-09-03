package com.atypon.dbproject.controllers.editor;

import com.atypon.dbproject.database.InMemoryDB;
import com.atypon.dbproject.entity.Quote;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Editor/UpdateQuote")
public class UpdateQuoteServlet extends HttpServlet {


    private InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("ID"));
        request.setAttribute("quote",inMemoryDB.getQuote(id));
        request.getRequestDispatcher("/WEB-INF/Editor/UpdateQuote.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Quote quote = getQuote(request);
        if (inMemoryDB.quoteIsUpdated(quote)){
            response.sendRedirect("/Editor/EQuotes");
        } else {
            request.setAttribute("errorM","All fields are Required, please fill them all");
            request.getRequestDispatcher("/WEB-INF/Editor/UpdateQuote.jsp").forward(request,response);
        }
    }

    private Quote getQuote(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String quote = req.getParameter("quote");
        return new Quote.QuoteBuilder().id(id).bookId(bookId).quote(quote).bookName("name").build();
    }


}
