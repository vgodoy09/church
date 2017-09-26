package br.com.church.fachada;

import java.util.List;

import br.com.church.modelo.Aplicacao;
import br.com.church.modelo.Igreja;
import br.com.church.modelo.Menu;
import br.com.church.modelo.Perfil;
import br.com.church.modelo.Tela;
import br.com.church.modelo.Usuario;



public interface IFachadaSistema {
    public boolean ControleAcesso(Object objeto);
    public Usuario ControleAcessoNovo(Object objeto);
    public List<Aplicacao> ListarAplicacoe(Integer user_id);
	public List<Menu> ListarMenus(Integer application_id);
	List<Aplicacao> ListarTodasAplicacoe();
	List<Tela> ListarTodasTelas();
	Aplicacao consultaPorId(int id);
	Tela consultaTelaPorId(int id);
	void salvar(Aplicacao app);
	void atualizar(Aplicacao app);
	void salvar(Tela app);
	void atualizar(Tela app);
	Menu consultaMenuPorId(int id);
	void salvar(Menu menu);
	void atualizar(Menu menu);
	List<Menu> ListarTodosMenus(Integer application_id);
	List<Menu> ListarTodosMenusSemPai(Integer application_id);
	List<Perfil> ListarTodosPerfis();
	void atualizar(Perfil app);
	void salvar(Perfil app);
	Perfil consultaPerfilPorId(int id);
	List<Menu> ListarTodosMenusIsCheck(Integer application_id, Integer profile_id);
	List<Menu> ListMenuPorAplicaoId(Integer application_id);
	boolean hasProfileMenu(Integer menu_id, Integer profile_id);
	void createdProfileMenu(Integer profile_id, Integer menu_id);
	void deleteProfileMenu(Integer profile_id, Integer menu_id);
	Perfil salvarComRetorno(Perfil app);
	List<Menu> listTodosMenuCheckNotUpdate(Integer application_id);
	Perfil atualizarComRetorno(Perfil app);
	void deleteAllProfileMenu(Integer profile_id);
	List<Menu> listMenuPeloUsuario(Integer user_id, Integer application_id);
	List<Usuario> getUser(String us);
	Usuario consultaUsuarioPorId(int id);
	List<Perfil> ListarTodosPerfisIsCheck(Integer application_id,Integer user_id);
	void deleteAllUserProfile(Integer profile_id);
	boolean hasUserProfile(Integer user_id, Integer profile_id);
	void createdUserProfile(Integer profile_id, Integer user_id);
	List<Igreja> listarTodasIgrejas();
	List<Igreja> listarTodasIgrejasIsCheck(Integer user_id);
	void createdUserChurch(Integer church_id, Integer user_id);
	void deleteAllUserChurch(Integer church_id);
	boolean hasUserChurch(Integer user_id, Integer church_id);
	List<Igreja> listChurch(Integer user_id);
	void atualizarSenha(Usuario user);
	List<Usuario> listarTodosUsuarios();
}
