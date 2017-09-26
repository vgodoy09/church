package br.com.church.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Aplicacao;
import br.com.church.modelo.Igreja;
import br.com.church.modelo.Usuario;

/**
 * Servlet implementation class ControleUser
 */
@WebServlet("/ControleUser")
public class ControleUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleUser() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btSearch") != null || request.getParameter("search") != null){
			String idOrName = request.getParameter("search");
			FachadaSistema fs = new FachadaSistema();
			List<Usuario> users = fs.getUser(idOrName);
			request.setAttribute("listarUser", users);
			request.getRequestDispatcher("/menu/permissao.jsp").forward(request, response);
		} else if(request.getParameter("proximaTela") != null){
			String id = request.getParameter("id");
			try {
				HttpSession session = request.getSession(true);
				FachadaSistema fs = new FachadaSistema();
//				Usuario user = fs.consultaUsuarioPorId(Integer.parseInt(id));
				List<Aplicacao> listarTodasAplicacoe = fs.ListarTodasAplicacoe();
//				List<Igreja> listarTodasIgrejas = fs.listarTodasIgrejas();
				List<Igreja> listarTodasIgrejasIsCheck = fs.listarTodasIgrejasIsCheck(Integer.parseInt(id));
				request.setAttribute("listarAplicacoes", listarTodasAplicacoe);
//				request.setAttribute("listarIgrejas", listarTodasIgrejas);
				request.setAttribute("listarIgre", listarTodasIgrejasIsCheck);
				session.setAttribute("usuarioId", id);
				request.getRequestDispatcher("/menu/todasaspermissoes.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
