package br.com.church.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Aplicacao;

/**
 * Servlet implementation class ControlarListagemMenu
 */
@WebServlet("/ControlarListagemMenu")
public class ControlarListagemMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemMenu() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FachadaSistema fs = new FachadaSistema();
			List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
			request.setAttribute("listarAplicacoes", listarAplicacoes);
			request.getRequestDispatcher("/menu/listamenu.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
