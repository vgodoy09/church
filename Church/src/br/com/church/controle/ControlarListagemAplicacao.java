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
 * Servlet implementation class ControlarListagemAplicacao
 */
@WebServlet("/ControlarListagemAplicacao")
public class ControlarListagemAplicacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemAplicacao() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FachadaSistema fs = new FachadaSistema();
			List<Aplicacao> listarTodasAplicacoe = fs.ListarTodasAplicacoe();
			request.setAttribute("listarAplicacoes", listarTodasAplicacoe);
			request.getRequestDispatcher("/menu/listaaplicacao.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

}
