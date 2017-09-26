package br.com.church.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Tela;

/**
 * Servlet implementation class ControlarListagemTela
 */
@WebServlet("/ControlarListagemTela")
public class ControlarListagemTela extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemTela() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FachadaSistema fs = new FachadaSistema();
			List<Tela> listarTodasTelas = fs.ListarTodasTelas();
			request.setAttribute("listarTelas", listarTodasTelas);
			request.getRequestDispatcher("/menu/listatela.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

}
