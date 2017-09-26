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
import br.com.church.modelo.Tela;
import br.com.church.view.ViewHelperTela;

/**
 * Servlet implementation class ControleTela
 */
@WebServlet("/ControleTela")
public class ControleTela extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperTela helperTela;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnNovo") != null) {
			FachadaSistema fs = new FachadaSistema();
			List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
			request.setAttribute("listarAplicacoes", listarAplicacoes);
			request.getRequestDispatcher("/menu/cadastrotela.jsp").forward(request, response);
		} else if(request.getParameter("antesAtualizar") != null){
			String id = request.getParameter("id");
			try {
				FachadaSistema fs = new FachadaSistema();
				Tela tela = fs.consultaTelaPorId(Integer.parseInt(id));
				request.setAttribute("tel", tela);
				List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
				request.setAttribute("listarAplicacoes", listarAplicacoes);
				request.getRequestDispatcher("/menu/atualizartela.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnCadastro") != null){
			helperTela = new ViewHelperTela();
			helperTela.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.salvar(helperTela.getDados());
				request.getRequestDispatcher("ControlarListagemTela").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnAtualizar") != null){
			helperTela = new ViewHelperTela();
			helperTela.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.atualizar(helperTela.getDados());
				request.getRequestDispatcher("ControlarListagemTela").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
