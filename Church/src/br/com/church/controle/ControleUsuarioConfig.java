package br.com.church.controle;

import static br.com.church.util.CheckInstanceObject.IsNull;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.church.dao.DAOCidade;
import br.com.church.dao.DAOEstado;
import br.com.church.dao.DAOPais;
import br.com.church.dao.DAOPerfilUsuario;
import br.com.church.dao.DAOUsuario;
import br.com.church.facade.FacadeCidade;
import br.com.church.facade.FacadeEstado;
import br.com.church.facade.FacadePrincipal;
import br.com.church.facade.Result;
import br.com.church.modelo.Cidade;
import br.com.church.modelo.Estado;
import br.com.church.modelo.Pais;
import br.com.church.modelo.PerfilUsuario;
import br.com.church.modelo.Usuario;
import br.com.church.util.Constants;
import br.com.church.view.ViewHelperUsuario;

@WebServlet("/ControleUsuarioConfig")
public class ControleUsuarioConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewHelperUsuario helperUsuario;
	private FacadePrincipal<Usuario> fs = new FacadePrincipal<Usuario>();
	private FacadePrincipal<PerfilUsuario> fsp = new FacadePrincipal<PerfilUsuario>();
	private FacadePrincipal<Pais> fp = new FacadePrincipal<Pais>();
	private FacadeEstado fe = new FacadeEstado();
	private FacadeCidade fc = new FacadeCidade();
       
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
				populateCombos(request, response, usuar.getResultObject());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("estados") != null){
			String ides = request.getParameter("selecionarEstado");
			try {
				helperUsuario = new ViewHelperUsuario();
				helperUsuario.setDados(request);
				Usuario dados = helperUsuario.getDados();
				request.setAttribute("usuar", dados);
				populateCombos(request, response, dados);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("paises") != null){
//			String idps = request.getParameter("selecionarPais");
			try {
				helperUsuario = new ViewHelperUsuario();
				helperUsuario.setDados(request);
				Usuario dados = helperUsuario.getDados();
//				dados.setIdCidade(null);
//				dados.setIdEstado(null);
//				dados.setNom_cidade(null);
//				dados.setNom_estado(null);
//				request.setAttribute("usuar", dados);
				populateCombos(request, response, dados);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("btnCadastro") != null){
			helperUsuario = new ViewHelperUsuario();
			helperUsuario.setDados(request);
			try {
				Result<Usuario> result = fs.save(helperUsuario.getDados(), new DAOUsuario());
				fsp.save(new PerfilUsuario(result.getResultObject().getId_usuario(), Constants.USERPROFILECOURSE), new DAOPerfilUsuario());
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

	private void populateCombos(HttpServletRequest request, HttpServletResponse response, Usuario usuar)
			throws ServletException, IOException {
		Result<Pais> listAll = fp.listAll(new DAOPais());
		request.setAttribute("listarPais", listAll.getResultList());
		Result<Estado> listByCountry = null;
		if(!IsNull(usuar) && !IsNull(usuar.getIdPais())) 
			listByCountry = fe.listByCountry(usuar.getIdPais(), new DAOEstado());
		request.setAttribute("listarEstado", IsNull(listByCountry) ? "" : listByCountry.getResultList());
		Result<Cidade> listByState = null;
		if(!IsNull(usuar) && !IsNull(usuar.getIdEstado())) 
			listByState = fc.listByState(usuar.getIdEstado(), new DAOCidade());
		request.setAttribute("listarCidade", IsNull(listByState) ? "" : listByState.getResultList());
		request.getRequestDispatcher("/usuarioConfig/atualizarusuario.jsp").forward(request, response);
	}
   

}
