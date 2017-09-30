package br.com.church.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.dao.DAOUsuario;
import br.com.church.facade.FacadePrincipal;
import br.com.church.facade.Result;
import br.com.church.modelo.Usuario;

@WebServlet("/ControlarListagemUsuario")
public class ControlarListagemUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlarListagemUsuario() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FacadePrincipal<Usuario> fs = new FacadePrincipal<Usuario>();
			Result<Usuario> list = fs.listAll(new DAOUsuario());
			request.setAttribute("listarTodosUsuarios", list.getResultList());
			request.getRequestDispatcher("/usuarioConfig/listausuario.jsp").forward(request, response);
		} catch (Exception e) {
		}
	}

}
