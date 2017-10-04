package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Pais;
import br.com.church.modelo.Usuario;
import br.com.church.util.Util;

public class DAOPais extends DAO<Pais>{

	@Override
	public Pais update(Pais objeto, EntityManager em) {
		Pais nova = new Pais();
		em.getTransaction().begin();
		nova = em.merge(objeto);
		em.getTransaction().commit();
		return nova;
	}

	@Override
	public void delete(Pais objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Pais searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Pais.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pais> listAll(EntityManager em) {
		List<Pais> Paiss = new ArrayList<Pais>();
		String sql = "select * from Pais";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql, Pais.class);
		Paiss = (List<Pais>) query.getResultList();
		return Paiss;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pais> listAll(Usuario usuario) throws ClassNotFoundException {
		List<Pais> Paiss = new ArrayList<Pais>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Pais";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Pais.class);
			query.setParameter(1, usuario.getId_usuario());
			Paiss = (List<Pais>) query.getResultList();
			return Paiss;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}

	@Override
	public void save(Pais objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	@Override
	public Pais search(Pais objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Pais.class, objeto.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public Pais searchByDescription(String description, EntityManager em) {
		String sql = "select * from Pais where nome = ?1";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql,Pais.class);
		query.setParameter(1, description);
		List<Pais> list = (List<Pais>) query.getResultList();
		return list.get(0);
	}

	@Override
	public Pais atualizar(Pais objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}

	@Override
	public void desativar(Pais objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pais consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = 
    			conexao.prepareStatement(                                      
    					"select p.cod_pais,  " +
	    				"    	p.nom_pais   " +
	    				"from pais p 		 " +
	    				"where cod_pais=?");
		sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Pais pa = null;
        if(resultado.next())
            pa = preencher(resultado);
      
        return pa;
	}

	@Override
	public List<Pais> listarTodos(Connection conexao) throws SQLException {
		PreparedStatement sql = 
    			conexao.prepareStatement(                                      
    					"select p.cod_pais,  " +
	    				"    	p.nom_pais   " +
	    				"from pais p ");
        ResultSet resultado = sql.executeQuery();
        List<Pais> paises = new ArrayList<Pais>();
        while(resultado.next())
        	paises.add(preencher(resultado));
 
        return paises;
	}
	
	/**
     * Este metodo preencher usuario do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Pais preencher(ResultSet resultado) throws SQLException {
        Pais pa = new Pais();
        pa.setId(resultado.getInt("cod_pais"));
        pa.setNome(resultado.getString("nom_pais"));
         return pa;
    }

	@Override
	public Pais salvar(Pais objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}
	
}
