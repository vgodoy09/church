package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Tela;
import br.com.church.util.Util;

public class DAOTela extends DAO<Tela>{

	@Override
	public Tela update(Tela objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Tela objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Tela searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Tela.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tela> listAll(EntityManager em) {
		List<Tela> Telas = new ArrayList<Tela>();
		String sql = "select * from Tela";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Telas = (List<Tela>) query.getResultList();
		return Telas;
	}

	@Override
	public void save(Tela objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	/**
	 * listar todas aplicacoes
	 * @return (List<Tela>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Tela> listAllTelas() throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select * from screen s ");
            ResultSet resultado = sql.executeQuery();
            List<Tela> telas = new ArrayList<Tela>();
            while(resultado.next()){
            	telas.add(preencher(resultado));
            }
            return telas;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar as aplicacoes pelo usuario e pela igreja
	 * @param church_id id da igreja
	 * @param user_id id do usuario
	 * @return (List<Tela>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Tela> listTela(Integer user_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        									" select * from application a                            " +
											" where a.visible = 1 and exists                         " +      
											" (                                                      " +      
											"	select * from userprofile up                         " +
											"	inner join profile p  on p.id = up.profile_id        " +
											"	where up.user_id = ? and p.application_id = a.id)  	 " );
            sql.setInt(1, user_id);
            ResultSet resultado = sql.executeQuery();
            List<Tela> aplicacoes = new ArrayList<Tela>();
            while(resultado.next()){
                aplicacoes.add(preencher(resultado));
            }
            return aplicacoes;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
     * Este metodo preencher Tela do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Tela preencher(ResultSet resultado)
    throws SQLException {
        Tela Tela = new Tela();
        Tela.setId(resultado.getInt("id"));
        Tela.setName(resultado.getString("name"));
        Tela.setFilename(resultado.getString("fileName"));
        Tela.setApplication_id(resultado.getInt("application_id"));
         return Tela;
    }
    
    private PreparedStatement setSql(PreparedStatement sql, Tela obj) throws SQLException{
    	sql.setString(1, obj.getName());
    	sql.setString(2, obj.getFilename());
    	sql.setInt(3, obj.getApplication_id());
    	return sql;
    }

	@Override
	public Tela search(Tela objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Tela.class, objeto.getId());
	}

	@Override
	public Tela searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tela atualizar(Tela objeto) throws SQLException, ClassNotFoundException {
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
	public Tela atualizar(Tela objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update screen set name=?, fileName=?, "
				+ "application_id=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(4, objeto.getId());
		sql.executeUpdate();
		return objeto;
	}
	

	@Override
	public void desativar(Tela objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Tela consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
	public Tela consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from screen s where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Tela Tela = null;
        if(resultado.next()){
            Tela = preencher(resultado);
        }
		return Tela;
	}

	@Override
	public List<Tela> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tela salvar(Tela objeto) throws SQLException, ClassNotFoundException {
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
	public Tela salvar(Tela objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into screen (name,fileName,application_id) "
				+ " values (?, ?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
		return objeto;
	}

}
