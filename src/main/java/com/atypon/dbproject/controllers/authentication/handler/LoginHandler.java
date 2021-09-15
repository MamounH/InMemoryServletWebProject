package com.atypon.dbproject.controllers.authentication.handler;

import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.dao.daoImp.UserDaoImp;
import com.atypon.dbproject.entity.User;
import com.atypon.dbproject.securityconf.SHA512HashAlgo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandler implements ILoginHandler{

    private final UserDao userDao;

    public LoginHandler(){
        userDao = new UserDaoImp(new SHA512HashAlgo());
    }

    @Override
    public void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = getUser(request);
        directUser(request, response, user);
    }

    private void directUser(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, ServletException {
        switch (user.getRole()){
            case ADMIN-> response.sendRedirect("/Admin/Users");
            case EDITOR-> response.sendRedirect("/Editor/Books");
            case VIEWER-> response.sendRedirect("/Viewer/Books");
            default-> {
                request.setAttribute("errorM","Invalid User");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }

        }
    }

    private User getUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.verifyCredentials(email,password);
        request.getSession().setAttribute("id",user.getUsername());
        request.getSession().setAttribute("role",user.getRole());
        return user;
    }


}
