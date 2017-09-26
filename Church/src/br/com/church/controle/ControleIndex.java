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
 * Servlet implementation class ControleIndex
 */
@WebServlet("/ControleIndex")
public class ControleIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleIndex() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(true);
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		Integer church_id = (Integer) session.getAttribute("church_id");
		if(church_id == null) {
			Integer id = null;
			try {
				id = Integer.parseInt(request.getParameter("church_id"));
			} catch (Exception e) {
				id = null;
			}
			church_id =  id;
		}
		try {
			FachadaSistema fs = new FachadaSistema();
			List<Igreja> listChurch = fs.listChurch(user == null ? null : user.getId_usuario());
			if((listChurch != null && !listChurch.isEmpty() && listChurch.size() == 1) || church_id != null){
				List<Aplicacao> listarAplicacoe = fs.ListarAplicacoe(user == null ? null : user.getId_usuario());
				request.setAttribute("listarAplicacoe", listarAplicacoe);
				session.removeAttribute("listarMenus");
				session.setAttribute("church_id", church_id == null ? listChurch.get(0).getId() : church_id);
				request.getRequestDispatcher("/pages/paginainicial.jsp").forward(request, response);
			} else if(listChurch != null && !listChurch.isEmpty() && listChurch.size() > 1 && church_id == null){
				request.setAttribute("listarChurch", listChurch);
				request.getRequestDispatcher("/menu/selecionarIgreja.jsp").forward(request, response);
			}
		} catch (Exception e) {
		}
	}

}
