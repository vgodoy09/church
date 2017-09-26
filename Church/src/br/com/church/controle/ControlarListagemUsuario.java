package br.com.church.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.fachada.FachadaSistema;
import br.com.church.fachada.IFachadaSistema;
import br.com.church.modelo.Usuario;

@WebServlet("/ControlarListagemUsuario")
public class ControlarListagemUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemUsuario() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			IFachadaSistema fs = new FachadaSistema();
			List<Usuario> listarTodosUsuarios = fs.listarTodosUsuarios();
			request.setAttribute("listarTodosUsuarios", listarTodosUsuarios);
			request.getRequestDispatcher("/usuarioConfig/listausuario.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

}
