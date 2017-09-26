package br.com.church.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Igreja;

/**
 * Servlet implementation class ControlarListagemIgrejas
 */
@WebServlet("/ControlarListagemIgrejas")
public class ControlarListagemIgrejas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemIgrejas() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FachadaSistema fs = new FachadaSistema();
			List<Igreja> listarTodasIgrejas = fs.listarTodasIgrejas();
			request.setAttribute("listarIgreja", listarTodasIgrejas);
			request.getRequestDispatcher("/church/listaigreja.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

}
