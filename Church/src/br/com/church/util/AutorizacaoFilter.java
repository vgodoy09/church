package br.com.church.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(
		urlPatterns = {"/menu/*", "/pages/*", "/templates/*", "/usuario/*","/administrador/*","/popup/*" }, 
			servletNames = {"/ControlarAcesso", "/ControleIndex"})
public class AutorizacaoFilter implements Filter {
	int i = 0;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Boolean isLogado = false;  
        isLogado = (Boolean)req.getSession().getAttribute("isLogado");
        if(isLogado == null || isLogado == false) {
            res.sendRedirect(req.getContextPath() + "/exptions/acessoNegado.jsp");
        }
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {
	}

    
}
