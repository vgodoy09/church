package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Estado;
import br.com.church.modelo.Usuario;
import br.com.church.util.Util;

public class DAOEstado extends DAO<Estado>{

	@Override
	public Estado update(Estado objeto, EntityManager em) {
		Estado nova = new Estado();
		em.getTransaction().begin();
		nova = em.merge(objeto);
		em.getTransaction().commit();
		return nova;
	}

	@Override
	public void delete(Estado objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Estado searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Estado.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Estado> listAll(EntityManager em) {
		List<Estado> Estados = new ArrayList<Estado>();
		String sql = "select * from Estado";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql, Estado.class);
		Estados = (List<Estado>) query.getResultList();
		return Estados;
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> listAll(Usuario usuario) throws ClassNotFoundException {
		List<Estado> Estados = new ArrayList<Estado>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Estado";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Estado.class);
			query.setParameter(1, usuario.getId_usuario());
			Estados = (List<Estado>) query.getResultList();
			return Estados;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}

	@Override
	public void save(Estado objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	@Override
	public Estado search(Estado objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Estado.class, objeto.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public Estado searchByDescription(String description, EntityManager em) {
		String sql = "select * from Estado where nome = ?1";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql,Estado.class);
		query.setParameter(1, description);
		List<Estado> list = (List<Estado>) query.getResultList();
		return list.get(0);
	}

	@Override
	public Estado atualizar(Estado objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}

	@Override
	public void desativar(Estado objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Estado consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = 
				conexao.prepareStatement(                                      
    					"select p.cod_estado,   " +
	    				"    	p.nom_estado,   " +
	    				"    	p.sgl_estado,   " +
	    				"    	p.cod_pais      " +
	    				"from estado p where p.cod_estado =?");
		sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Estado et = null;
        if(resultado.next())
        	et = preencher(resultado);
      
        return et;
	}

	@Override
	public List<Estado> listarTodos(Connection conexao) throws SQLException {
		PreparedStatement sql = 
				conexao.prepareStatement(                                      
    					"select p.cod_estado,   " +
	    				"    	p.nom_estado,   " +
	    				"    	p.sgl_estado,   " +
	    				"    	p.cod_pais      " +
	    				"from estado p ");
        ResultSet resultado = sql.executeQuery();
        List<Estado> estado = new ArrayList<Estado>();
        while(resultado.next())
        	estado.add(preencher(resultado));
 
        return estado;
	}
	
	public List<Estado> listarPeloPais(Integer idPais) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
           return listarPeloPais(idPais, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public List<Estado> listarPeloPais(Integer idPais, Connection conexao) throws SQLException {
		PreparedStatement sql = 
    			conexao.prepareStatement(                                      
    					"select p.cod_estado,   " +
	    				"    	p.nom_estado,   " +
	    				"    	p.sgl_estado,   " +
	    				"    	p.cod_pais      " +
	    				"from estado p where p.cod_pais = ?");
		sql.setInt(1, idPais);
        ResultSet resultado = sql.executeQuery();
        List<Estado> estado = new ArrayList<Estado>();
        while(resultado.next())
        	estado.add(preencher(resultado));
 
        return estado;
	}
	
	/**
     * Este metodo preencher usuario do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Estado preencher(ResultSet resultado) throws SQLException {
        Estado et = new Estado();
        et.setId(resultado.getInt("cod_estado"));
        et.setNome(resultado.getString("nom_estado"));
        et.setUf(resultado.getString("sgl_estado"));
        et.setFk_pais(resultado.getInt("cod_pais"));
         return et;
    }

	@Override
	public Estado salvar(Estado objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}
	
}
