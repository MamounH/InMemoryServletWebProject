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
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


// move adding config to another class responsibility

@WebServlet(urlPatterns = "/Admin/AddUser")
public class AddUserServlet extends HttpServlet {

    private UserDao usersDao;

    ReentrantLock lock = new ReentrantLock();

    private final Logger logger = Logger.getLogger("AddUserServlet");

    @Override
    public void init() throws ServletException {
        super.init();
        usersDao = new UserDaoImp(new SHA512HashAlgo());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Admin/users/AddUser.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getUser(request);

        if (userIsAdded(user)){
            response.sendRedirect("/Admin/Users");
        } else
        {
            request.setAttribute("errorM","User Already Exist, try again with a unique name.");
            request.getRequestDispatcher("/WEB-INF/Admin/users/AddUser.jsp").forward(request,response);
        }
    }

    private User getUser(HttpServletRequest request) {
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        return new User.UserBuilder().username(username).fName(fName).lName(lName)
                .password(password).role(Role.valueOf(role)).build();
    }

    private boolean userIsAdded(User user){
        lock.lock();
        try {
            return AddUser(user);
        } catch (Exception e){
            logError(e);
        }finally {
            lock.unlock();
        }
        return false;
    }

    private boolean AddUser(User user) {
        if (usersDao.userExists(user.getUsername())){
            return false;
        }
        else{
            usersDao.createUser(user);
            return true;
        }
    }

    protected void logError (Exception e){
        logger.log(Level.SEVERE,e.getMessage());
    }


}
