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
import br.com.church.modelo.Menu;
import br.com.church.modelo.Usuario;

/**
 * Servlet implementation class ControleAplicacoes
 */
@WebServlet("/ControleAplicacoes")
public class ControleAplicacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleAplicacoes() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String i  = request.getParameter("id");
			HttpSession session = (HttpSession) request.getSession(true);
			Usuario user = (Usuario) session.getAttribute("usuarioLogado");
			FachadaSistema fs = new FachadaSistema();
			List<Menu> listarMenus = fs.listMenuPeloUsuario(user.getId_usuario(), Integer.parseInt(i));
			session.setAttribute("listarMenus", listarMenus);
			request.getRequestDispatcher("/pages/inicial.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	

}
