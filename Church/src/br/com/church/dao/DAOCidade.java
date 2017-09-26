package br.com.church.dao;

import java.sql.Connection;
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
	public void atualizar(Cidade objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desativar(Cidade objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade consultarPorId(int id, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Cidade objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
