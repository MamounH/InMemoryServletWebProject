package com.atypon.dbproject.controllers.editor;

import com.atypon.dbproject.database.InMemoryDB;
import com.atypon.dbproject.entity.Book;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Editor/UpdateBook")
public class UpdateBookServlet extends HttpServlet {

    InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("ID"));
        request.setAttribute("book",inMemoryDB.getBook(id));
        request.getRequestDispatcher("/WEB-INF/Editor/UpdateBook.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book book = getBook(request);

        if (inMemoryDB.bookIsUpdated(book)){
            response.sendRedirect("/Editor/Books");
        } else {
            request.setAttribute("errorM","All fields are Required, please fill them all");
            request.getRequestDispatcher("/WEB-INF/Editor/UpdateBook.jsp").forward(request,response);
        }
    }

    private Book getBook(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String subject = req.getParameter("subject");
        String publisher = req.getParameter("publisher");
        String year = req.getParameter("year");

        return new Book.BookBuilder().ID(id).name(name).author(author)
                .subject(subject).publisher(publisher).year(year).build();
    }

}
