package br.com.church.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.church.fachada.FachadaSistema;
import br.com.church.modelo.Usuario;
import br.com.church.view.ViewHelperUsuario;

@WebServlet("/ControleUsuario")
public class ControleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperUsuario helperUsuario;  

       
    public ControleUsuario() {
        super();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAlterar") != null){
			try {
				helperUsuario = new ViewHelperUsuario();
		        helperUsuario.setDados(request);
				FachadaSistema fs = new FachadaSistema();
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario) session.getAttribute("usuarioLogado");
				Usuario newUser = helperUsuario.getDados();
				user.setSenha(newUser.getSenha());
				request.setAttribute("login", user.getLogin());
				request.setAttribute("senha", user.getSenha());
				session.setAttribute("usuarioLogado", user);
				fs.atualizarSenha(user);
				request.getRequestDispatcher("/usuarioConfig/usuarioconfig.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} if(request.getParameter("config") != null) {
			try {
				HttpSession session = request.getSession(true);
				Usuario user = (Usuario) session.getAttribute("usuarioLogado");
				request.setAttribute("login", user.getLogin());
				request.setAttribute("senha", user.getSenha());
				request.getRequestDispatcher("/usuarioConfig/usuarioconfig.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

}
