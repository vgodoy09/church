package br.com.church.dao;

import java.sql.Connection;
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
	public void atualizar(Estado objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desativar(Estado objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Estado consultarPorId(int id, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estado> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Estado objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
