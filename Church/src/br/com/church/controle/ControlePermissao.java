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
import br.com.church.modelo.Perfil;


@WebServlet("/ControlePermissao")
public class ControlePermissao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlePermissao() {
        super();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnCadastro") != null){
			try {
				FachadaSistema fs = new FachadaSistema();
				HttpSession session = request.getSession(true);
				
				String id = (String) session.getAttribute("app_id");
				if(id == null || id.isEmpty())
					id = request.getParameter("idApp");
				String user_id = (String) session.getAttribute("usuarioId");

				List<Perfil> listPerfil = fs.ListarTodosPerfisIsCheck(Integer.parseInt(id), Integer.parseInt(user_id));
				for(Perfil p : listPerfil){
					fs.deleteAllUserProfile(p.getId());
				}

				String selecionados[] = request.getParameterValues("idper");
				if(selecionados != null){
					for(String s : selecionados){
						if(!fs.hasUserProfile(Integer.parseInt(user_id), Integer.parseInt(s))){
							fs.createdUserProfile(Integer.parseInt(s), Integer.parseInt(user_id));
						}
					}
				}
				List<Perfil> listarTodosPerfisIsCheck = fs.ListarTodosPerfisIsCheck(Integer.parseInt(id), Integer.parseInt(user_id));
				request.setAttribute("listarPefisNew", listarTodosPerfisIsCheck);
				list(request, fs, user_id);
				request.getRequestDispatcher("/menu/todasaspermissoes.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} if(request.getParameter("btnAdd") != null) {
			try {
				FachadaSistema fs = new FachadaSistema();
				HttpSession session = request.getSession(true);
				String user_id = (String) session.getAttribute("usuarioId");
				List<Igreja> listarIgrejas = fs.listarTodasIgrejasIsCheck(Integer.parseInt(user_id));
				for(Igreja i : listarIgrejas){
					fs.deleteAllUserChurch(i.getId());
				}
				String selecionados[] = request.getParameterValues("idigr");
				if(selecionados != null){
					for(String s : selecionados){
						if(!fs.hasUserChurch(Integer.parseInt(user_id), Integer.parseInt(s))){
							fs.createdUserChurch(Integer.parseInt(s), Integer.parseInt(user_id));
						}
					}
				}
				list(request, fs, user_id);
				request.getRequestDispatcher("/menu/todasaspermissoes.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("irPerfil") != null) {
			String id = request.getParameter("idApp");
			String iduser = request.getParameter("ud");
			try {
				FachadaSistema fs = new FachadaSistema();
				HttpSession session = request.getSession(true);
				List<Perfil> listarTodosPerfisIsCheck = fs.ListarTodosPerfisIsCheck(Integer.parseInt(id), Integer.parseInt(iduser));
				request.setAttribute("listarPefisNew", listarTodosPerfisIsCheck);
				session.setAttribute("usuarioId", iduser);
				session.setAttribute("app_id", id);
				list(request, fs, iduser);
				request.getRequestDispatcher("/menu/todasaspermissoes.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void list(HttpServletRequest request, FachadaSistema fs, String user_id) {
		List<Aplicacao> listarTodasAplicacoe = fs.ListarTodasAplicacoe();
		request.setAttribute("listarAplicacoes", listarTodasAplicacoe);
		List<Igreja> listarTodasIgrejasIsCheck = fs.listarTodasIgrejasIsCheck(Integer.parseInt(user_id));
		request.setAttribute("listarIgre", listarTodasIgrejasIsCheck);
	}

}
