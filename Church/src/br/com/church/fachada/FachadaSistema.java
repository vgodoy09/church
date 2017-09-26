package br.com.church.fachada;

import java.sql.SQLException;
import java.util.List;

import br.com.church.dao.DAOAplicacao;
import br.com.church.dao.DAOIgreja;
import br.com.church.dao.DAOMenu;
import br.com.church.dao.DAOPerfil;
import br.com.church.dao.DAOTela;
import br.com.church.dao.DAOUsuario;
import br.com.church.modelo.Aplicacao;
import br.com.church.modelo.Igreja;
import br.com.church.modelo.Menu;
import br.com.church.modelo.Perfil;
import br.com.church.modelo.Tela;
import br.com.church.modelo.Usuario;

public class FachadaSistema implements IFachadaSistema{

	@Override
	public boolean ControleAcesso(Object objeto) {
		Usuario usuario = (Usuario)objeto; 
        DAOUsuario dao = new DAOUsuario();
        try {        
			if(dao.ControleAcessoMySQL(usuario)){
			        return true;
			}
			else{
			    return false;
			}
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}
	
	@Override
	public Usuario ControleAcessoNovo(Object objeto) {
		Usuario usuario = (Usuario)objeto; 
        DAOUsuario dao = new DAOUsuario();
        Usuario controleAcessoNovo = null;
        try {        
        	controleAcessoNovo = dao.ControleAcessoNovo(usuario);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return controleAcessoNovo;
	}

	@Override
	public List<Aplicacao> ListarAplicacoe(Integer user_id) {
		DAOAplicacao dao = new DAOAplicacao();
		List<Aplicacao> listAplicacao = null;
		try {
			listAplicacao = dao.listAplicacao(user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listAplicacao;
	}
	
	@Override
	public List<Aplicacao> ListarTodasAplicacoe() {
		DAOAplicacao dao = new DAOAplicacao();
		List<Aplicacao> listAplicacao = null;
		try {
			listAplicacao = dao.listAllApplication();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listAplicacao;
	}
	
	@Override
	public List<Igreja> listarTodasIgrejas() {
		DAOIgreja dao = new DAOIgreja();
		List<Igreja> listIgreja = null;
		try {
			listIgreja = dao.listAllChurch();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listIgreja;
	}
	
	@Override
	public List<Tela> ListarTodasTelas() {
		DAOTela dao = new DAOTela();
		List<Tela> listTela = null;
		try {
			listTela = dao.listAllTelas();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listTela;
	}
	
	@Override
	public List<Perfil> ListarTodosPerfis() {
		DAOPerfil dao = new DAOPerfil();
		List<Perfil> listPerfil = null;
		try {
			listPerfil = dao.listAllPerfisAplicacao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listPerfil;
	}
	
	@Override
	public Aplicacao consultaPorId(int id) {
		DAOAplicacao dao = new DAOAplicacao();
		Aplicacao aplicacao = null;
		try {
			aplicacao = dao.consultarPorId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return aplicacao;
	}
	
	@Override
	public Menu consultaMenuPorId(int id) {
		DAOMenu dao = new DAOMenu();
		Menu menu = null;
		try {
			menu = dao.consultarPorId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}
	
	@Override
	public Usuario consultaUsuarioPorId(int id) {
		DAOUsuario dao = new DAOUsuario();
		Usuario user = null;
		try {
			user = dao.consultarPorId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public Tela consultaTelaPorId(int id) {
		DAOTela dao = new DAOTela();
		Tela tela = null;
		try {
			tela = dao.consultarPorId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return tela;
	}
	
	@Override
	public Perfil consultaPerfilPorId(int id) {
		DAOPerfil dao = new DAOPerfil();
		Perfil perfil = null;
		try {
			perfil = dao.consultarPorId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return perfil;
	}
	
	@Override
	public void salvar(Aplicacao app) {
		DAOAplicacao dao = new DAOAplicacao();
		try {
			dao.salvar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Menu menu) {
		DAOMenu dao = new DAOMenu();
		try {
			dao.salvar(menu);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Tela app) {
		DAOTela dao = new DAOTela();
		try {
			dao.salvar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Perfil app) {
		DAOPerfil dao = new DAOPerfil();
		try {
			dao.salvar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Perfil salvarComRetorno(Perfil app) {
		DAOPerfil dao = new DAOPerfil();
		Perfil perfil = null;
		try {
			perfil = dao.salvarComRetorno(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return perfil;
	}
	
	@Override
	public Perfil atualizarComRetorno(Perfil app) {
		DAOPerfil dao = new DAOPerfil();
		Perfil perfil = null;
		try {
			perfil = dao.atualizarComRetorno(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return perfil;
	}
	
	@Override
	public void atualizar(Aplicacao app) {
		DAOAplicacao dao = new DAOAplicacao();
		try {
			dao.atualizar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void atualizar(Perfil app) {
		DAOPerfil dao = new DAOPerfil();
		try {
			dao.atualizar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void atualizar(Menu menu) {
		DAOMenu dao = new DAOMenu();
		try {
			dao.atualizar(menu);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteProfileMenu(Integer profile_id, Integer menu_id) {
		DAOMenu dao = new DAOMenu();
		try {
			dao.deleteProfileMenu(profile_id, menu_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createdProfileMenu(Integer profile_id, Integer menu_id) {
		DAOMenu dao = new DAOMenu();
		try {
			dao.createdProfileMenu(profile_id, menu_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createdUserProfile(Integer profile_id, Integer user_id) {
		DAOPerfil dao = new DAOPerfil();
		try {
			dao.createdUserProfile(profile_id, user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createdUserChurch(Integer church_id, Integer user_id) {
		DAOIgreja dao = new DAOIgreja();
		try {
			dao.createdUserChurch(church_id, user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Menu> ListarMenus(Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listMenu(application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Menu> listMenuPeloUsuario(Integer user_id, Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listMenuPeloUsuario(user_id, application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Menu> ListarTodosMenus(Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listTodosMenu(application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Menu> ListMenuPorAplicaoId(Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.ListMenuPorAplicaoId(application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Menu> ListarTodosMenusIsCheck(Integer application_id,Integer profile_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listTodosMenuCheck(application_id,profile_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Perfil> ListarTodosPerfisIsCheck(Integer application_id,Integer user_id) {
		DAOPerfil dao = new DAOPerfil();
		List<Perfil> listPerfil = null;
		try {
			listPerfil = dao.listTodosPerfisCheck(application_id,user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listPerfil;
	}
	
	@Override
	public List<Igreja> listarTodasIgrejasIsCheck(Integer user_id) {
		DAOIgreja dao = new DAOIgreja();
		List<Igreja> listIgreja = null;
		try {
			listIgreja = dao.listTodasIgrejasCheck(user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listIgreja;
	}
	
	@Override
	public List<Menu> listTodosMenuCheckNotUpdate(Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listTodosMenuCheckNotUpdate(application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public boolean hasProfileMenu(Integer menu_id , Integer profile_id){
		DAOMenu dao = new DAOMenu();
		boolean hasProfile = false;
		try {
			hasProfile = dao.hasProfile(menu_id, profile_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return hasProfile;
	}
	
	@Override
	public boolean hasUserProfile(Integer user_id , Integer profile_id){
		DAOPerfil dao = new DAOPerfil();
		boolean hasProfile = false;
		try {
			hasProfile = dao.hasUserProfile(user_id, profile_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return hasProfile;
	}
	
	@Override
	public boolean hasUserChurch(Integer user_id , Integer church_id){
		DAOIgreja dao = new DAOIgreja();
		boolean hasChurch = false;
		try {
			hasChurch = dao.hasUserChurch(user_id, church_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return hasChurch;
	}
	
	@Override
	public List<Menu> ListarTodosMenusSemPai(Integer application_id) {
		DAOMenu dao = new DAOMenu();
		List<Menu> listMenu = null;
		try {
			listMenu = dao.listTodosMenuSemPai(application_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listMenu;
	}
	
	@Override
	public List<Usuario> getUser(String us) {
		DAOUsuario dao = new DAOUsuario();
		List<Usuario> listUser = null;
		try {
			listUser = dao.getUser(us);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listUser;
	}

	@Override
	public void atualizar(Tela app) {
		DAOTela dao = new DAOTela();
		try {
			dao.atualizar(app);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void atualizarSenha(Usuario user) {
		DAOUsuario dao = new DAOUsuario();
		try {
			dao.atualizarSenha(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteAllProfileMenu(Integer profile_id) {
		DAOMenu dao = new DAOMenu();
		try {
			dao.deleteAllProfileMenu(profile_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteAllUserProfile(Integer profile_id) {
		DAOPerfil dao = new DAOPerfil();
		try {
			dao.deleteAllUserProfile(profile_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteAllUserChurch(Integer church_id) {
		DAOIgreja dao = new DAOIgreja();
		try {
			dao.deleteAllUserChurch(church_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Igreja> listChurch(Integer user_id) {
		DAOIgreja dao = new DAOIgreja();
		List<Igreja> church = null;
		try {
			church = dao.getChurch(user_id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return church;
	}
	
	@Override
	public List<Usuario> listarTodosUsuarios() {
		DAOUsuario dao = new DAOUsuario();
		List<Usuario> usuarios = null;
		try {
			usuarios = dao.listarTodosUsuarios();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

}
