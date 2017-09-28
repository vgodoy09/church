package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Perfil;
import br.com.church.util.Util;

public class DAOPerfil extends DAO<Perfil>{

	@Override
	public Perfil update(Perfil objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Perfil objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Perfil searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Perfil.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Perfil> listAll(EntityManager em) {
		List<Perfil> Perfils = new ArrayList<Perfil>();
		String sql = "select * from Perfil";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Perfils = (List<Perfil>) query.getResultList();
		return Perfils;
	}

	@Override
	public void save(Perfil objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	/**
	 * listar todas aplicacoes
	 * @return (List<Perfil>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Perfil> listAllPerfis() throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select * from profile s ");
            ResultSet resultado = sql.executeQuery();
            List<Perfil> Perfils = new ArrayList<Perfil>();
            while(resultado.next()){
            	Perfils.add(preencher(resultado));
            }
            return Perfils;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
		
	public List<Perfil> listTodosPerfisCheck(Integer application_id, Integer user_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					"select * ,                                                                          " +
							"case when (                                                                         " +
							"	select up.id from userprofile up                                                 " +
							"	inner join profile pp  on pp.id = up.profile_id                                  " +
							"	where up.user_id = ? and pp.application_id = p.application_id and pp.id = p.id   " +
							"    )                                                                               " +
							"then 1 else 0 end as ischeck                                                        " +
							"from profile p where p.application_id = ?" );
        	sql.setInt(1, user_id);
            sql.setInt(2, application_id);
            ResultSet resultado = sql.executeQuery();
            List<Perfil> perfis = new ArrayList<Perfil>();
            while(resultado.next()){
            	Perfil p = preencher(resultado);
            	p.setCheck(resultado.getInt("ischeck") == 1 ? "checked" : "");
            	perfis.add(p);
            }
            return perfis;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar todas aplicacoes
	 * @return (List<Perfil>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Perfil> listAllPerfisAplicacao() throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select p.*, a.name nameapplication from profile p "
        					+ " inner join application a on a.id = p.application_id");
            ResultSet resultado = sql.executeQuery();
            List<Perfil> Perfils = new ArrayList<Perfil>();
            while(resultado.next()){
            	Perfils.add(preencher(resultado, true));
            }
            return Perfils;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
     * Este metodo preencher Perfil do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Perfil preencher(ResultSet resultado) throws SQLException {
        return preencher(resultado, false);
    }

    /**
     * Este metodo preencher Perfil do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @param isApplicationName se false nao traz o nome da aplicacao se for true traz o nome da aplicacao
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
	private Perfil preencher(ResultSet resultado, boolean isApplicationName) throws SQLException {
		Perfil perfil = new Perfil();
        perfil.setId(resultado.getInt("id"));
        perfil.setName(resultado.getString("name"));
        perfil.setApplication_id(resultado.getInt("application_id"));
        if(isApplicationName)
        	perfil.setNameapplication(resultado.getString("nameapplication"));
        return perfil;
	}
    
    private PreparedStatement setSql(PreparedStatement sql, Perfil obj) throws SQLException{
    	sql.setString(1, obj.getName());
    	sql.setInt(2, obj.getApplication_id());
    	return sql;
    }

	@Override
	public Perfil search(Perfil objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Perfil.class, objeto.getId());
	}

	@Override
	public Perfil searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	public Perfil atualizar(Perfil objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            return atualizar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	@Override
	public Perfil atualizar(Perfil objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update profile set name=?, "
				+ "application_id=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(3, objeto.getId());
		sql.executeUpdate();
		return objeto;
	}
	

	@Override
	public void desativar(Perfil objeto, Connection conexao) throws SQLException {
	}

	public Perfil consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
	
	@Override
	public Perfil consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from profile s where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Perfil Perfil = null;
        if(resultado.next()){
            Perfil = preencher(resultado);
        }
		return Perfil;
	}

	@Override
	public List<Perfil> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Perfil salvar(Perfil objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            return salvar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	@Override
	public Perfil salvar(Perfil objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into profile (name,application_id) "
				+ " values (?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
		return objeto;
	}
	
	public Perfil atualizarComRetorno(Perfil objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
           return atualizarComRetorno(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public Perfil atualizarComRetorno(Perfil objeto, Connection conexao) throws SQLException, ClassNotFoundException {
		PreparedStatement sql = conexao.prepareStatement("update profile set name=?, "
				+ "application_id=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(3, objeto.getId());
		sql.executeUpdate();
		Perfil perfil = consultarPorId(objeto.getId());
        return perfil;
	}
	
	public Perfil salvarComRetorno(Perfil objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
           return salvarComRetorno(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public Perfil salvarComRetorno(Perfil objeto, Connection conexao) throws SQLException, ClassNotFoundException {
		PreparedStatement sql = conexao.prepareStatement("insert into profile (name,application_id) "
				+ " values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
		sql = setSql(sql, objeto);
		sql.executeUpdate();
		ResultSet resultado = sql.getGeneratedKeys();
		Perfil perfil = null;
		Integer id = null;
        if(resultado.next()){
        	id = resultado.getInt(1);
        }
        if(id != null){
        	perfil = consultarPorId(id);
        }
        return perfil;
	}
	
	public void createdUserProfile(Integer profile_id, Integer user_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            createdUserProfile(profile_id, user_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void createdUserProfile(Integer profile_id, Integer user_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into userprofile (profile_id,user_id) values (?,?)");
		sql.setInt(1, profile_id);
    	sql.setInt(2, user_id);
		sql.execute();
	}
	
	public void deleteUserProfile(Integer profile_id, Integer user_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            deleteUserProfile(profile_id, user_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void deleteUserProfile(Integer profile_id, Integer user_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("delete from userprofile where profile_id = ? and user_id = ?");
		sql.setInt(1, profile_id);
    	sql.setInt(2, user_id);
		sql.execute();
	}
	
	public void deleteAllUserProfile(Integer profile_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            deleteAllUserProfile(profile_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void deleteAllUserProfile(Integer profile_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("delete from userprofile where profile_id = ?");
		sql.setInt(1, profile_id);
		sql.execute();
	}
	
	public boolean hasUserProfile(Integer user_id, Integer profile_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select pm.id from userprofile pm where pm.user_id = ? and pm.profile_id = ?" );
            sql.setInt(1, user_id);
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

}
