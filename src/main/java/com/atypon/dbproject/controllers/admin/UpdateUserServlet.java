package com.atypon.dbproject.controllers.admin;

import com.atypon.dbproject.entity.Role;
import com.atypon.dbproject.entity.User;
import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.dao.daoImp.UserDaoImp;
import com.atypon.dbproject.securityconf.SHA512HashAlgo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Admin/UpdateUser")
public class UpdateUserServlet extends HttpServlet {

    private UserDao usersDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usersDao = new UserDaoImp(new SHA512HashAlgo());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        User user = usersDao.getUser(email);

        request.setAttribute("user",user);
        request.getRequestDispatcher("/WEB-INF/Admin/users/UpdateUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);

        String oldEmail = request.getParameter("email");


        usersDao.updateUser(user,oldEmail);
        response.sendRedirect("/Admin/Users");
    }

    private User getUser(HttpServletRequest req) {
        String email = req.getParameter("email1");
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        return new User.UserBuilder().username(email).fName(fName).lName(lName).password(password)
                .role(Role.valueOf(role)).build();
    }


}
