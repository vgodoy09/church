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
import br.com.church.modelo.Menu;
import br.com.church.modelo.Perfil;
import br.com.church.view.ViewHelperPerfil;

@WebServlet("/ControlePerfil")
public class ControlePerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ViewHelperPerfil helperPerfil;   
    public ControlePerfil() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnNovo") != null) {
			FachadaSistema fs = new FachadaSistema();
			List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
			request.setAttribute("listarAplicacoes", listarAplicacoes);
			request.getRequestDispatcher("/menu/cadastroperfil.jsp").forward(request, response);
		}  else if(request.getParameter("btnCadastro") != null){
			helperPerfil = new ViewHelperPerfil();
			helperPerfil.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				Perfil perfil = fs.salvarComRetorno(helperPerfil.getDados());
				String selecionados[] = request.getParameterValues("idmen");
				if(selecionados != null){
					for(String s : selecionados){
						if(!fs.hasProfileMenu(Integer.parseInt(s), perfil.getId())){
							fs.createdProfileMenu(perfil.getId(), Integer.parseInt(s));
						}
					}
				}
				String selecionadosSu[] = request.getParameterValues("idsubmen");
				if(selecionadosSu != null){
					for(String s : selecionadosSu){
						if(!fs.hasProfileMenu(Integer.parseInt(s), perfil.getId())){
							fs.createdProfileMenu(perfil.getId(), Integer.parseInt(s));
						}
					}
				}
				String selecionadosSu1[] = request.getParameterValues("idsubmen1");
				if(selecionadosSu1 != null){
					for(String s : selecionadosSu1){
						if(!fs.hasProfileMenu(Integer.parseInt(s), perfil.getId())){
							fs.createdProfileMenu(perfil.getId(), Integer.parseInt(s));
						}
					}
				}
				request.getRequestDispatcher("ControlarListagemPerfil").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnAtualizar") != null){
			helperPerfil = new ViewHelperPerfil();
			helperPerfil.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.atualizar(helperPerfil.getDados());
				fs.deleteAllProfileMenu(helperPerfil.getDados().getId());
				String selecionados[] = request.getParameterValues("idmen");
				if(selecionados != null){
					for(String s : selecionados){
						if(!fs.hasProfileMenu(Integer.parseInt(s), helperPerfil.getDados().getId())){
							fs.createdProfileMenu(helperPerfil.getDados().getId(), Integer.parseInt(s));
						}
					}
				}
				String selecionadosSu[] = request.getParameterValues("idsubmen");
				if(selecionadosSu != null){
					for(String s : selecionadosSu){
						if(!fs.hasProfileMenu(Integer.parseInt(s), helperPerfil.getDados().getId())){
							fs.createdProfileMenu(helperPerfil.getDados().getId(), Integer.parseInt(s));
						}
					}
				}
				String selecionadosSu1[] = request.getParameterValues("idsubmen1");
				if(selecionadosSu1 != null){
					for(String s : selecionadosSu1){
						if(!fs.hasProfileMenu(Integer.parseInt(s), helperPerfil.getDados().getId())){
							fs.createdProfileMenu(helperPerfil.getDados().getId(), Integer.parseInt(s));
						}
					}
				}
				request.getRequestDispatcher("ControlarListagemPerfil").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("selecionarAplicacao") != null){
			String idapp = request.getParameter("selecionarAplicacao");
			request = selecionarAplicacao(request, idapp, false);
			request.getRequestDispatcher("/menu/cadastroperfil.jsp").forward(request, response);			
		} else if(request.getParameter("selecionarAplicacaoAtualizar") != null){
			String idapp = request.getParameter("selecionarAplicacaoAtualizar");
			request = selecionarAplicacao(request, idapp, true);
			request.getRequestDispatcher("/menu/atualizarperfil.jsp").forward(request, response);
		} else if(request.getParameter("antesAtualizar") != null){
			String id = request.getParameter("id");
			try {
				FachadaSistema fs = new FachadaSistema();
				Perfil perfil = fs.consultaPerfilPorId(Integer.parseInt(id));
				Aplicacao app = fs.consultaPorId(perfil.getApplication_id());
				request.setAttribute("apli", app);
				request.setAttribute("per", perfil);
				List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
				request.setAttribute("listarAplicacoes", listarAplicacoes);
				List<Menu> listarMenus = fs.ListarTodosMenusIsCheck(perfil.getApplication_id(), perfil.getId());
				request.setAttribute("listarM", listarMenus);
				request.getRequestDispatcher("/menu/atualizarperfil.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private HttpServletRequest selecionarAplicacao(HttpServletRequest request, String idapp, boolean isUpdate) {
		try {
			FachadaSistema fs = new FachadaSistema();
			Aplicacao app = fs.consultaPorId(Integer.parseInt(idapp));
			request.setAttribute("apli", app);
			List<Aplicacao> listarAplicacoes = fs.ListarTodasAplicacoe();
			request.setAttribute("listarAplicacoes", listarAplicacoes);
			List<Menu> listarMenus = null;
			if(isUpdate){
				String parameter = request.getParameter("txtid");
				listarMenus = fs.ListarTodosMenusIsCheck(Integer.parseInt(idapp), Integer.parseInt(parameter));
			} else {
				listarMenus = fs.listTodosMenuCheckNotUpdate(Integer.parseInt(idapp));
			}
			HttpSession session = request.getSession(true);
			request.setAttribute("listarM", listarMenus);
			session.setAttribute("idAp", idapp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

}
