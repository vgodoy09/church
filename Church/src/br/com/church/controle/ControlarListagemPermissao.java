package br.com.church.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlarListagemPermissao
 */
@WebServlet("/ControlarListagemPermissao")
public class ControlarListagemPermissao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemPermissao() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/menu/permissao.jsp").forward(request, response);
	}

}
