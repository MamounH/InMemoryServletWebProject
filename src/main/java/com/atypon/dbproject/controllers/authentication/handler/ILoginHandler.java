package com.atypon.dbproject.controllers.authentication.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ILoginHandler {

    void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
