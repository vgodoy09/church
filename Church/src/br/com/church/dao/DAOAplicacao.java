package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Aplicacao;
import br.com.church.util.Util;

public class DAOAplicacao extends DAO<Aplicacao>{

	@Override
	public Aplicacao update(Aplicacao objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Aplicacao objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Aplicacao searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Aplicacao.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Aplicacao> listAll(EntityManager em) {
		List<Aplicacao> aplicacaos = new ArrayList<Aplicacao>();
		String sql = "select * from Aplicacao";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		aplicacaos = (List<Aplicacao>) query.getResultList();
		return aplicacaos;
	}

	@Override
	public void save(Aplicacao objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	/**
	 * listar todas aplicacoes
	 * @return (List<Aplicacao>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Aplicacao> listAllApplication() throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select * from application a ");
            ResultSet resultado = sql.executeQuery();
            List<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
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
	 * listar as aplicacoes pelo usuario e pela igreja
	 * @param church_id id da igreja
	 * @param user_id id do usuario
	 * @return (List<Aplicacao>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Aplicacao> listAplicacao(Integer user_id) throws ClassNotFoundException, SQLException{
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
            List<Aplicacao> aplicacoes = new ArrayList<Aplicacao>();
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
     * Este metodo preencher Aplicacao do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Aplicacao preencher(ResultSet resultado)
    throws SQLException {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setId(resultado.getInt("id"));
        aplicacao.setNome(resultado.getString("name"));
        aplicacao.setImageLogoAvailable(resultado.getString("imageLogoAvailable"));
        aplicacao.setImageLogoUnavailable(resultado.getString("imageLogoUnavailable"));
        aplicacao.setLink(resultado.getString("link"));
        aplicacao.setVisible(resultado.getShort("visible") == 1 ? true : false);
        aplicacao.setCheck(aplicacao.getVisible() ? "checked" : "");
        aplicacao.setMessageUserActivation(resultado.getString("messageUserActivation"));
         return aplicacao;
    }
    
    private PreparedStatement setSql(PreparedStatement sql, Aplicacao obj) throws SQLException{
    	sql.setString(1, obj.getNome());
    	sql.setString(2, obj.getImageLogoAvailable());
    	sql.setString(3, obj.getImageLogoUnavailable());
    	sql.setString(4, obj.getLink());
    	sql.setBoolean(5, obj.getVisible());
    	sql.setString(6, obj.getMessageUserActivation());
    	sql.setString(7, obj.getNamebutton());
    	return sql;
    }

	@Override
	public Aplicacao search(Aplicacao objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Aplicacao.class, objeto.getId());
	}

	@Override
	public Aplicacao searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	public Aplicacao atualizar(Aplicacao objeto) throws SQLException, ClassNotFoundException {
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
	public Aplicacao atualizar(Aplicacao objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update application set name=?, imageLogoAvailable=?, "
				+ "imageLogoUnavailable=?, link=?, visible=?, messageUserActivation=?, namebutton=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(8, objeto.getId());
		sql.executeUpdate();
		return objeto;
	}
	

	@Override
	public void desativar(Aplicacao objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Aplicacao consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
	public Aplicacao consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from application a where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Aplicacao aplicacao = null;
        if(resultado.next()){
            aplicacao = preencher(resultado);
        }
		return aplicacao;
	}

	@Override
	public List<Aplicacao> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Aplicacao salvar(Aplicacao objeto) throws SQLException, ClassNotFoundException {
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
	public Aplicacao salvar(Aplicacao objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into application (name,imageLogoAvailable,imageLogoUnavailable,"
				+ "link,visible,messageUserActivation,namebutton) values (?, ?, ?, ?, ?, ?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
		return objeto;
	}

}
