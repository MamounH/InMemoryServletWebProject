package com.atypon.dbproject.controllers.admin;

import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.dao.daoImp.UserDaoImp;
import com.atypon.dbproject.securityconf.SHA512HashAlgo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Admin/Users")
public class UsersServlet extends HttpServlet {

    private UserDao usersDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usersDao = new UserDaoImp(new SHA512HashAlgo());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", usersDao.getAll());
        request.getRequestDispatcher("/WEB-INF/Admin/users/Users.jsp").forward(request,response);
    }




}
