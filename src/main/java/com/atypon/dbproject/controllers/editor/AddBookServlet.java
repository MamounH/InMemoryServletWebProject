package com.atypon.dbproject.controllers.editor;

import com.atypon.dbproject.database.InMemoryDB;
import com.atypon.dbproject.entity.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Editor/AddBook")
public class AddBookServlet extends HttpServlet {

    private InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Editor/AddBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = getBook(request);

        if (inMemoryDB.bookIsAdded(book)){
            response.sendRedirect("/Editor/Books");
        } else {
            request.setAttribute("errorM","All fields are Required, please fill them all");
            request.getRequestDispatcher("/WEB-INF/Editor/AddBook.jsp").forward(request,response);
        }

    }

    private Book getBook(HttpServletRequest request) {

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String subject = request.getParameter("subject");
        String publisher = request.getParameter("publisher");
        String year = request.getParameter("year");

        return new Book.BookBuilder().name(name).author(author).subject(subject)
                .publisher(publisher).year(year).build();
    }


}
