package br.com.church.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Aplicacao;
import br.com.church.view.ViewHelperAplicacao;

@WebServlet("/ControleApplication")
public class ControleApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperAplicacao helperAplicacao;
	
    public ControleApplication() {
        super();
    }

    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnNovo") != null) {
			request.getRequestDispatcher("/menu/cadastroaplicacao.jsp").forward(request, response);
		} else if(request.getParameter("antesAtualizar") != null){
			String id = request.getParameter("id");
			try {
				FachadaSistema fs = new FachadaSistema();
				Aplicacao aplicacao = fs.consultaPorId(Integer.parseInt(id));
				request.setAttribute("app", aplicacao);
				request.getRequestDispatcher("/menu/atualizaraplicacao.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnCadastro") != null){
			helperAplicacao = new ViewHelperAplicacao();
			helperAplicacao.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.salvar(helperAplicacao.getDados());
				request.getRequestDispatcher("ControlarListagemAplicacao").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnAtualizar") != null){
			helperAplicacao = new ViewHelperAplicacao();
			helperAplicacao.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.atualizar(helperAplicacao.getDados());
				request.getRequestDispatcher("ControlarListagemAplicacao").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
