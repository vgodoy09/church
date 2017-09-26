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
import br.com.church.view.ViewHelperMenu;

@WebServlet("/ControleMenu")
public class ControleMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperMenu helperMenu;
       
    public ControleMenu() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnNovo") != null) {
			try {
				HttpSession session = request.getSession(true);
				String idapp = (String) session.getAttribute("idAp");
				Integer application_id = Integer.parseInt(idapp);
				FachadaSistema fs = new FachadaSistema();
				List<Menu> listarMenus = fs.ListarTodosMenusSemPai(application_id);
				request.setAttribute("listarMen", listarMenus);
				request.getRequestDispatcher("/menu/cadastromenu.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("selecionarAplicacao") != null){
			String idapp = request.getParameter("selecionarAplicacao");
			try {
				FachadaSistema fs = new FachadaSistema();
				List<Menu> listarMenus = fs.ListMenuPorAplicaoId(Integer.parseInt(idapp));
				HttpSession session = request.getSession(true);
				request.setAttribute("listarM", listarMenus);
				session.setAttribute("idAp", idapp);
				request.getRequestDispatcher("ControlarListagemMenu").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("antesAtualizar") != null){
			String id = request.getParameter("id");
			try {
				HttpSession session = request.getSession(true);
				String idapp = (String) session.getAttribute("idAp");
				Integer application_id = Integer.parseInt(idapp);
				FachadaSistema fs = new FachadaSistema();
				List<Menu> listarMenus = fs.ListarTodosMenusSemPai(application_id);
				request.setAttribute("listarMen", listarMenus);
				Menu menu = fs.consultaMenuPorId(Integer.parseInt(id));
				request.setAttribute("men", menu);
				request.getRequestDispatcher("/menu/atualizarmenu.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnCadastro") != null){
			helperMenu = new ViewHelperMenu();
			helperMenu.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.salvar(helperMenu.getDados());
				request.getRequestDispatcher("ControlarListagemMenu").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnAtualizar") != null){
			helperMenu = new ViewHelperMenu();
			helperMenu.setDados(request);
			try {
				FachadaSistema fs = new FachadaSistema();
				fs.atualizar(helperMenu.getDados());
				request.getRequestDispatcher("ControlarListagemMenu").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
