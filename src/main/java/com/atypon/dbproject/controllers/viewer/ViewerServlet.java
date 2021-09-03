package com.atypon.dbproject.controllers.viewer;

import com.atypon.dbproject.database.InMemoryDB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Viewer/Books")
public class ViewerServlet extends HttpServlet {

    private InMemoryDB inMemoryDB;

    @Override
    public void init() throws ServletException {
        inMemoryDB= InMemoryDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", inMemoryDB.getAllBooks());
        request.getRequestDispatcher("/WEB-INF/Viewer/Books.jsp").forward(request,response);
    }


}
