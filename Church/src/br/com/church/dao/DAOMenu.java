package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Menu;
import br.com.church.util.Util;

public class DAOMenu extends DAO<Menu>{

	@Override
	public Menu update(Menu objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Menu objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Menu searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Menu.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> listAll(EntityManager em) {
		List<Menu> Menus = new ArrayList<Menu>();
		String sql = "select * from Menu";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Menus = (List<Menu>) query.getResultList();
		return Menus;
	}

	@Override
	public void save(Menu objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	/**
	 * listar menu pela aplicacao
	 * @param application_id id da aplicao
	 * @return (List<Menu>) uma lista de menus
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Menu> listMenu(Integer application_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					" select * from menu m                                " +
        					" inner join profilemenu pm on pm.menu_id = m.id      " +
        					" where application_id = ?  and fatherMenu_id is null " );
            sql.setInt(1, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	menus.add(preencher(resultado));
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar menu pela aplicacao
	 * @param application_id id da aplicao
	 * @return (List<Menu>) uma lista de menus
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Menu> listMenuPeloUsuario(Integer user_id, Integer application_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        				"select                                                                " +
        				"	m.*                                                  		       " +
        				"from userprofile up                                                   " +
        				"	inner join profilemenu pm on pm.profile_id = up.profile_id         " +
        				"	inner join menu m on m.id = pm.menu_id                             " +
        				"	inner join application a on a.id = m.application_id                " +
        				"where up.user_id = ? and a.id = ? and fatherMenu_id is null	       " +
        				"order by m.ordination												   ");
            sql.setInt(1, user_id);
            sql.setInt(2, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	menus.add(preencher(resultado));
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar todos menu pela aplicacao
	 * @param application_id id da aplicao
	 * @return (List<Menu>) uma lista de menus
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Menu> listTodosMenu(Integer application_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					" select * from menu m                                " +
        					" where application_id = ?" );
            sql.setInt(1, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	menus.add(preencher(resultado));
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar todos menu pela aplicacao
	 * @param application_id id da aplicao
	 * @return (List<Menu>) uma lista de menus
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Menu> listTodosMenuCheck(Integer application_id, Integer profile_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					"select m.* from (select m.*, "
        					+ "case when (select pmm.id from profilemenu pmm where pmm.profile_id = ? and pmm.menu_id = m.id) is null then 0 else 1 end as ischeck "
        					+ "from menu m  " +
							"where application_id = ?) as m" );
        	sql.setInt(1, profile_id);
            sql.setInt(2, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	Menu m = preencher(resultado, true);
            	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
            		List<Menu> listmenu = ListMenuPeloPaiIdCheck(m.getId(), profile_id, conexao);
            		m.setListmenu(listmenu);
            	}
            	menus.add(m);
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public List<Menu> listTodosMenuCheckNotUpdate(Integer application_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					"select m.* from (select m.* "
        					+"from menu m  " +
							"left join profilemenu pm on pm.menu_id = m.id								" +
							"where application_id = ?) as m" );
            sql.setInt(1, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	Menu m = preencher(resultado, true);
            	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
            		List<Menu> listmenu = ListMenuPeloPaiIdCheckNotUpdate(m.getId(), conexao);
            		m.setListmenu(listmenu);
            	}
            	menus.add(m);
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar todos menus sem pai pela aplicacao
	 * @param application_id id da aplicao
	 * @return (List<Menu>) uma lista de menus
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Menu> listTodosMenuSemPai(Integer application_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					" select * from menu m                                " +
        					" where application_id = ? and fatherMenu_id is null and active = ?" );
            sql.setInt(1, application_id);
            sql.setBoolean(2, true);
            ResultSet resultado = sql.executeQuery();
            List<Menu> menus = new ArrayList<Menu>();
            while(resultado.next()){
            	menus.add(preencher(resultado));
            }
            return menus;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public boolean hasProfile(Integer menu_id, Integer profile_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select pm.id from profilemenu pm where pm.menu_id = ? and pm.profile_id = ?" );
            sql.setInt(1, menu_id);
            sql.setInt(2, profile_id);
            ResultSet resultado = sql.executeQuery();
            Integer id = null;
            boolean hasProfile = false;
            if(resultado.next()){
            	id = resultado.getInt("id");
            }
            if(id != null){
            	hasProfile = true;
            }
            return hasProfile;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
     * Este metodo preencher Menu do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Menu preencher(ResultSet resultado) throws SQLException {
        return preencher(resultado, false);
    }

    /**
     * Este metodo preencher Menu do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @param ischeck true traz a opçao de check e false de nao check
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
	private Menu preencher(ResultSet resultado, boolean ischeck) throws SQLException {
		Menu menu = new Menu();
        menu.setId(resultado.getInt("id"));
        menu.setName(resultado.getString("name"));
        menu.setApplication_id(resultado.getInt("application_id"));
        menu.setFathermenu_id(resultado.getInt("fatherMenu_id"));
        menu.setLink(resultado.getString("link"));
        menu.setOrdination(resultado.getInt("ordination"));
        menu.setElement_id(resultado.getInt("element_id"));
        menu.setImage(resultado.getString("image"));
        menu.setActive(resultado.getInt("active") == 1 ? true : false);
        if(ischeck){
        	menu.setIscheck(resultado.getInt("ischeck") == 1 ? true : false);
        	menu.setCheck(menu.isIscheck() ? "checked" : "");
        }
        return menu;
	}

	@Override
	public Menu search(Menu objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Menu.class, objeto.getId());
	}

	@Override
	public Menu searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void atualizar(Menu objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            atualizar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

	@Override
	public void atualizar(Menu objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update menu set link =? ,name =?,fatherMenu_id=? ,ordination=? ,"
				+ "image=? ,element_id=? ,active=? ,application_id=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(9, objeto.getId());
		sql.executeUpdate();
		
	}
	
	private PreparedStatement setSql(PreparedStatement sql, Menu obj) throws SQLException{
    	sql.setString(1, obj.getLink());
    	sql.setString(2, obj.getName());
    	sql.setObject(3, obj.getFathermenu_id());
    	sql.setObject(4, obj.getOrdination());
    	sql.setString(5, obj.getImage());
    	sql.setInt(6, obj.getElement_id() == null ? 1 : obj.getElement_id());
    	sql.setBoolean(7, obj.getActive() == null ? true : obj.getActive());
    	sql.setInt(8, obj.getApplication_id());
    	return sql;
    }

	@Override
	public void desativar(Menu objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public Menu consultarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            return consultarPorId(id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public List<Menu> ListMenuPorAplicaoId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            return ListMenuPorAplicaoId(id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public List<Menu> ListMenuPorAplicaoId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from menu a where application_id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        List<Menu> menus = new ArrayList<Menu>();
        while(resultado.next()){
        	Menu m = preencher(resultado);
        	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
        		List<Menu> listmenu = ListMenuPeloPaiId(m.getId(), conexao);
        		m.setListmenu(listmenu);
        	}
        	menus.add(m);
        	
        }
		return menus;
	}
	
	public List<Menu> ListMenuPeloPaiId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from menu a where fatherMenu_id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        List<Menu> menus = new ArrayList<Menu>();
        while(resultado.next()){
        	Menu m = preencher(resultado);
        	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
        		List<Menu> listmenu = ListMenuPeloPaiId(m.getId(), conexao);
        		m.setListmenu(listmenu);
        	}
        	menus.add(m);
        }
		return menus;
	}
	
	public List<Menu> ListMenuPeloPaiIdCheckNotUpdate(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select m.* from (select m.* "
				+ " from menu m "
				+ " left join profilemenu pm on pm.menu_id = m.id "
				+ " where fatherMenu_id=?) as m");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        List<Menu> menus = new ArrayList<Menu>();
        while(resultado.next()){
        	Menu m = preencher(resultado, true);
        	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
        		List<Menu> listmenu = ListMenuPeloPaiIdCheckNotUpdate(m.getId(), conexao);
        		m.setListmenu(listmenu);
        	}
        	menus.add(m);
        }
		return menus;
	}
	
	public List<Menu> ListMenuPeloPaiIdCheck(int id, Integer profile_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select m.* from (select m.*, "
				+ "case when (select pmm.id from profilemenu pmm where pmm.profile_id = ? and pmm.menu_id = m.id) is null then 0 else 1 end as ischeck from menu m "
				+ " left join profilemenu pm on pm.menu_id = m.id "
				+ " where fatherMenu_id=?) as m");
        sql.setInt(1, profile_id);
        sql.setInt(2, id);
        ResultSet resultado = sql.executeQuery();
        List<Menu> menus = new ArrayList<Menu>();
        while(resultado.next()){
        	Menu m = preencher(resultado, true);
        	if(m.getLink() != null && !m.getLink().isEmpty() && m.getLink().equals("#")){
        		List<Menu> listmenu = ListMenuPeloPaiIdCheck(m.getId(), profile_id, conexao);
        		m.setListmenu(listmenu);
        	}
        	menus.add(m);
        }
		return menus;
	}

	@Override
	public Menu consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from menu a where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Menu menu = null;
        if(resultado.next()){
            menu = preencher(resultado);
        }
		return menu;
	}

	@Override
	public List<Menu> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void salvar(Menu objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            salvar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	@Override
	public void salvar(Menu objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into menu (link,name,fatherMenu_id,ordination,image,element_id,"
				+ " active,application_id) values (?,?,?,?,?,?,?,?)");
		sql = setSql(sql, objeto);
		sql.execute();
	}
	
	public void createdProfileMenu(Integer profile_id, Integer menu_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            createdProfileMenu(profile_id, menu_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void createdProfileMenu(Integer profile_id, Integer menu_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into profilemenu (profile_id,menu_id) values (?,?)");
		sql.setInt(1, profile_id);
    	sql.setInt(2, menu_id);
		sql.execute();
	}
	
	public void deleteProfileMenu(Integer profile_id, Integer menu_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            deleteProfileMenu(profile_id, menu_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void deleteProfileMenu(Integer profile_id, Integer menu_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("delete from profilemenu where profile_id = ? and menu_id = ?");
		sql.setInt(1, profile_id);
    	sql.setInt(2, menu_id);
		sql.execute();
	}
	
	public void deleteAllProfileMenu(Integer profile_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            deleteAllProfileMenu(profile_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void deleteAllProfileMenu(Integer profile_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("delete from profilemenu where profile_id = ?");
		sql.setInt(1, profile_id);
		sql.execute();
	}

}
