package com.atypon.dbproject.controllers.authentication;

import com.atypon.dbproject.controllers.authentication.handler.ILoginHandler;
import com.atypon.dbproject.controllers.authentication.handler.LoginHandler;
import com.atypon.dbproject.entity.User;
import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.dao.daoImp.UserDaoImp;
import com.atypon.dbproject.securityconf.SHA512HashAlgo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    private ILoginHandler loginHandler;

    @Override
    public void init() throws ServletException {
        loginHandler= new LoginHandler();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginHandler.handleLogin(request,response);
    }


}
