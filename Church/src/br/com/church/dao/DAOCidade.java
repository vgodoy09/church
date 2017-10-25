package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Cidade;
import br.com.church.util.Util;

public class DAOCidade extends DAO<Cidade>{

	@Override
	public Cidade update(Cidade objeto, EntityManager em) {
		Cidade nova = new Cidade();
		em.getTransaction().begin();
		nova = em.merge(objeto);
		em.getTransaction().commit();
		return nova;
	}

	@Override
	public void delete(Cidade objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Cidade searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Cidade.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cidade> listAll(EntityManager em) {
		List<Cidade> Cidades = new ArrayList<Cidade>();
		String sql = "select * from Cidade";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql, Cidade.class);
		Cidades = (List<Cidade>) query.getResultList();
		return Cidades;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> listAll(Integer idEstado) throws ClassNotFoundException {
		List<Cidade> Cidades = new ArrayList<Cidade>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Cidade where fk_estado = ?1";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Cidade.class);
			query.setParameter(1, idEstado);
			Cidades = (List<Cidade>) query.getResultList();
			return Cidades;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}

	@Override
	public void save(Cidade objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	@Override
	public Cidade search(Cidade objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Cidade.class, objeto.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public Cidade searchByDescription(String description, EntityManager em) {
		String sql = "select * from Cidade where nome = ?1";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql,Cidade.class);
		query.setParameter(1, description);
		List<Cidade> list = (List<Cidade>) query.getResultList();
		return list.get(0);
	}

	@Override
	public Cidade atualizar(Cidade objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
		
	}

	@Override
	public void desativar(Cidade objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = 
				conexao.prepareStatement(                                      
    					"select p.cod_cidade,   " +
	    				"    	p.nom_cidade,   " +
	    				"    	p.cod_estado    " +
	    				"from cidade p where p.cod_cidade =?");
		sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Cidade cd = null;
        if(resultado.next())
        	cd = preencher(resultado);
      
        return cd;
	}

	@Override
	public List<Cidade> listarTodos(Connection conexao) throws SQLException {
		PreparedStatement sql = 
				conexao.prepareStatement(                                      
    					"select p.cod_cidade,   " +
	    				"    	p.nom_cidade,   " +
	    				"    	p.cod_estado    " +
	    				"from cidade p ");
        ResultSet resultado = sql.executeQuery();
        List<Cidade> cidade = new ArrayList<Cidade>();
        while(resultado.next())
        	cidade.add(preencher(resultado));
        
        return cidade;
	}
	
	public List<Cidade> listarPeloEstado(Integer idEstado) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
           return listarPeloEstado(idEstado, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public List<Cidade> listarPeloEstado(Integer idEstado, Connection conexao) throws SQLException {
		PreparedStatement sql = 
    			conexao.prepareStatement(                                      
    					"select p.cod_cidade,   " +
	    				"    	p.nom_cidade,   " +
	    				"    	p.cod_estado      " +
	    				"from cidade p where p.cod_estado = ?");
		sql.setInt(1, idEstado);
        ResultSet resultado = sql.executeQuery();
        List<Cidade> cidade = new ArrayList<Cidade>();
        while(resultado.next())
        	cidade.add(preencher(resultado));
 
        return cidade;
	}
	
	/**
     * Este metodo preencher usuario do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Cidade preencher(ResultSet resultado) throws SQLException {
        Cidade cd = new Cidade();
        cd.setId(resultado.getInt("cod_cidade"));
        cd.setNome(resultado.getString("nom_cidade"));
        cd.setFk_estado(resultado.getInt("cod_estado"));
         return cd;
    }

	@Override
	public Cidade salvar(Cidade objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}
	
}
