package com.atypon.dbproject.webfilter;



import com.atypon.dbproject.entity.Role;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class AuthorizationFilter implements Filter {

    private FilterConfig filterConfig = null;
    private Role role;
    private HttpServletRequest request;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) serverReq;
        role = (Role) request.getSession().getAttribute("role");

        if (role == null){
            forwardToLoginPage(serverReq,serverRes);
        }
        else {
            handleUserRequest(serverReq, serverRes, filterChain);
        }
    }

    private void handleUserRequest(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws ServletException, IOException {
        switch (role) {
            case VIEWER -> checkViewerRequest(serverReq, serverRes, filterChain);
            case EDITOR -> checkEditorRequest(serverReq, serverRes, filterChain);
            case ADMIN -> forwardToRequestedPage(serverReq, serverRes, filterChain);
            default -> forwardToLoginPage(serverReq, serverRes);
        }
    }

    private void forwardToRequestedPage(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(serverReq, serverRes);
    }

    private void forwardToLoginPage(ServletRequest serverReq, ServletResponse serverRes) throws ServletException, IOException {
        request.getRequestDispatcher("/login").forward(serverReq,serverRes);
    }

    private void checkEditorRequest(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/Admin/")){
            request.getRequestDispatcher("/Editor/Books").forward(serverReq, serverRes);
        }  else forwardToRequestedPage(serverReq,serverRes,filterChain);
    }

    private void checkViewerRequest(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.startsWith("/Admin/") || path.startsWith("/Editor/") ){
            request.getRequestDispatcher("/Viewer/Books").forward(serverReq, serverRes);
        } else forwardToRequestedPage(serverReq, serverRes, filterChain);

    }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
}
