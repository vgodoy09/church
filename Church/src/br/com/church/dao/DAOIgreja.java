package br.com.church.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Igreja;
import br.com.church.util.Util;

public class DAOIgreja extends DAO<Igreja>{

	@Override
	public Igreja update(Igreja objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Igreja objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Igreja searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Igreja.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Igreja> listAll(EntityManager em) {
		List<Igreja> Igrejas = new ArrayList<Igreja>();
		String sql = "select * from Igreja";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Igrejas = (List<Igreja>) query.getResultList();
		return Igrejas;
	}

	@Override
	public void save(Igreja objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	/**
	 * listar todas igrejas
	 * @return (List<Igreja>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Igreja> listAllChurch() throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select c.*, p.nom_pais, e.nom_estado, cd.nom_cidade from church c "
        					+ " inner join pais p on p.cod_pais = c.pais_id"
        					+ " inner join estado e on e.cod_estado = c.estado_id"
        					+ " inner join cidade cd on cd.cod_cidade = c.cidade_id");
            ResultSet resultado = sql.executeQuery();
            List<Igreja> igrejas = new ArrayList<Igreja>();
            while(resultado.next()){
            	Igreja preencher = preencher(resultado);
            	preencher.setPaisNome(resultado.getString("nom_pais"));
            	preencher.setEstadoNome(resultado.getString("nom_estado"));
            	preencher.setCidadeNome(resultado.getString("nom_cidade"));
            	igrejas.add(preencher);
            }
            return igrejas;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
	 * listar as igrejas pelo usuario e pela igreja
	 * @param church_id id da igreja
	 * @param user_id id do usuario
	 * @return (List<Igreja>) uma lista de aplicaçoes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Igreja> listIgrejaByUser(Integer user_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					" select c.* from church c                        " +
        					" inner join userchurch uc on uc.church_id = c.id " +
        					" where uc.user_id = ? ");
            sql.setInt(1, user_id);
            ResultSet resultado = sql.executeQuery();
            List<Igreja> igrejas = new ArrayList<Igreja>();
            while(resultado.next()){
                igrejas.add(preencher(resultado));
            }
            return igrejas;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public List<Igreja> listTodasIgrejasCheck(Integer user_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(
        					"select * ,                                               " +
		        			"case when (                                              " +               
		        			"	select uc.id from userchurch uc                       " +
		        			"	where uc.user_id = ? and uc.church_id = c.id          " +
		        			"    )                                                    " +
		        			"then 1 else 0 end as ischeck							  " +
		        			"from church c ; " );
        	sql.setInt(1, user_id);
            ResultSet resultado = sql.executeQuery();
            List<Igreja> igrejas = new ArrayList<Igreja>();
            while(resultado.next()){
            	Igreja i = preencher(resultado);
            	i.setCheck(resultado.getInt("ischeck") == 1 ? "checked" : "");
            	igrejas.add(i);
            }
            return igrejas;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	/**
     * Este metodo preencher Igreja do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Igreja preencher(ResultSet resultado)
    throws SQLException {
        Igreja igreja = new Igreja();
        igreja.setId(resultado.getInt("id"));
        igreja.setName(resultado.getString("name"));
        igreja.setLogo(resultado.getString("logo"));
        igreja.setDateBirth(resultado.getDate("dateBirth"));
        igreja.setPais_id(resultado.getInt("pais_id"));
        igreja.setEstado_id(resultado.getInt("estado_id"));
        igreja.setCidade_id(resultado.getInt("cidade_id"));
        igreja.setEndereco(resultado.getString("endereco"));
        igreja.setNumber(resultado.getInt("number"));
        return igreja;
    }
    
    private PreparedStatement setSql(PreparedStatement sql, Igreja obj) throws SQLException{
    	sql.setString(1, obj.getName());
    	sql.setString(2, obj.getLogo());
    	sql.setDate(3, (Date) obj.getDateBirth());
    	sql.setInt(4, obj.getPais_id());
    	sql.setInt(5, obj.getEstado_id());
    	sql.setInt(6, obj.getCidade_id());
    	sql.setString(7, obj.getEndereco());
    	sql.setInt(8, obj.getNumber());
    	return sql;
    }

	@Override
	public Igreja search(Igreja objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Igreja.class, objeto.getId());
	}

	@Override
	public Igreja searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	public Igreja atualizar(Igreja objeto) throws SQLException, ClassNotFoundException {
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
	public Igreja atualizar(Igreja objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update church set name=?, logo=?, "
				+ "dateBirth=?, pais_id=?, estado_id=?, cidade_id=?, endereco=?, number=? where id =?");
		sql = setSql(sql, objeto);
		sql.setInt(9, objeto.getId());
		sql.executeUpdate();
		return objeto;
	}
	

	@Override
	public void desativar(Igreja objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Igreja consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
	public Igreja consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from church c where id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Igreja igreja = null;
        if(resultado.next()){
            igreja = preencher(resultado);
        }
		return igreja;
	}

	@Override
	public List<Igreja> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Igreja salvar(Igreja objeto) throws SQLException, ClassNotFoundException {
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
	public Igreja salvar(Igreja objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into church (name,logo,dateBirth,"
				+ "pais_id,estado_id,cidade_id,endereco,number) values (?, ?, ?, ?, ?, ?, ?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
		return objeto;
	}
	
	public void deleteAllUserChurch(Integer church_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            deleteAllUserChurch(church_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void deleteAllUserChurch(Integer church_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("delete from userchurch where church_id = ?");
		sql.setInt(1, church_id);
		sql.execute();
	}
	
	public void createdUserChurch(Integer church_id, Integer user_id) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            createdUserChurch(church_id, user_id, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public void createdUserChurch(Integer church_id, Integer user_id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into userchurch (church_id,user_id) values (?,?)");
		sql.setInt(1, church_id);
    	sql.setInt(2, user_id);
		sql.execute();
	}
	
	public boolean hasUserChurch(Integer user_id, Integer church_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select pm.id from userchurch pm where pm.user_id = ? and pm.church_id = ?" );
            sql.setInt(1, user_id);
            sql.setInt(2, church_id);
            ResultSet resultado = sql.executeQuery();
            Integer id = null;
            boolean hasChurch = false;
            if(resultado.next()){
            	id = resultado.getInt("id");
            }
            if(id != null){
            	hasChurch = true;
            }
            return hasChurch;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public List<Igreja> getChurch(Integer user_id) throws ClassNotFoundException, SQLException{
		Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select c.* from userchurch uc "
        					+ " inner join church c on c.id = uc.church_id "
        					+ " where uc.user_id = ?" );
            sql.setInt(1, user_id);
            ResultSet resultado = sql.executeQuery();
            List<Igreja> churchsId = new ArrayList<Igreja>();
            while(resultado.next()){
            	Igreja i = preencher(resultado);
            	churchsId.add(i);
            }
            return churchsId;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
	}

}
