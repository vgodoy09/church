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
import br.com.church.view.ViewHelperUsuario;

@WebServlet("/ControleUsuarioConfig")
public class ControleUsuarioConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperUsuario helperUsuario;
	private FacadePrincipal<Usuario> fs = new FacadePrincipal<Usuario>();
       
    public ControleUsuarioConfig() {
        super();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnNovo") != null) {
			request.getRequestDispatcher("/usuarioConfig/cadastrousuario.jsp").forward(request, response);
		} else if(request.getParameter("antesAtualizar") != null){
			String id = request.getParameter("id");
			try {
				Result<Usuario> usuar = fs.searchById(Integer.parseInt(id), new DAOUsuario());
				request.setAttribute("usuar", usuar.getResultObject());
				request.getRequestDispatcher("/usuarioConfig/atualizarusuario.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnCadastro") != null){
			helperUsuario = new ViewHelperUsuario();
			helperUsuario.setDados(request);
			try {
				fs.save(helperUsuario.getDados(), new DAOUsuario());
				request.getRequestDispatcher("ControlarListagemUsuario").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnAtualizar") != null){
			helperUsuario = new ViewHelperUsuario();
			helperUsuario.setDados(request);
			try {
				fs.update(helperUsuario.getDados(), new DAOUsuario());
				request.getRequestDispatcher("ControlarListagemUsuario").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
   

}
