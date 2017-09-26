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

/**
 * Servlet implementation class ControlarAcesso
 */
@WebServlet(description = "Controlar Acesso dos Usuarios", urlPatterns = { "/ControlarAcesso" })
public class ControlarAcesso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperUsuario helperUsuario;  

	public ControlarAcesso() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("isLogado", false);
		session.invalidate();
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		helperUsuario = new ViewHelperUsuario();
        helperUsuario.setDados(request);
		FachadaSistema fs = new FachadaSistema();
		Usuario controleAcessoNovo = fs.ControleAcessoNovo(helperUsuario.getDados());
		HttpSession session = (HttpSession) request.getSession(true);
		session.setAttribute("usuarioLogado", controleAcessoNovo);
		boolean isLogado = false;
		if (controleAcessoNovo != null) {
			isLogado = true;
//			LogUsuario lu = new LogUsuario();
//			Calendar calendar = new GregorianCalendar();
//			SimpleDateFormat formathora = new SimpleDateFormat("HH:mm:ss");
//			Date date = new Date();
//			calendar.setTime(date);
//			lu.setDataentrada(calendar);
//			lu.setHoradeentrada(formathora.format(calendar.getTime()));
//			Usuario usuario = helperUsuario.getDados();
//			lu.setUsuario(usuario);
//			Fachada fachada = new Fachada();
//			fachada.salvar(lu);
			try {
				request.getRequestDispatcher("/ControleIndex").forward(request, response);
//				List<Aplicacao> listarAplicacoe = fs.ListarAplicacoe(controleAcessoNovo.getId_usuario());
//				request.setAttribute("listarAplicacoe", listarAplicacoe);
//				request.getRequestDispatcher("/paginainicial.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
				request.getRequestDispatcher("exptions/acessoNegado.jsp").forward(request, response);
			}
		} else {
			isLogado = false;
		}
		session.setAttribute("isLogado", isLogado);
	}

}
