package br.com.church.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.logs.LogUsuario;

public class DAOLogUsuario extends DAO<LogUsuario>{

	@Override
	public LogUsuario update(LogUsuario objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(LogUsuario objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public LogUsuario searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(LogUsuario.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LogUsuario> listAll(EntityManager em) {
		List<LogUsuario> LogUsuarios = new ArrayList<LogUsuario>();
		String sql = "select * from LogUsuario where status ='ATIVO'";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		LogUsuarios = (List<LogUsuario>) query.getResultList();
		return LogUsuarios;
	}

	@Override
	public void save(LogUsuario objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}

	@Override
	public LogUsuario search(LogUsuario objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(LogUsuario.class, objeto.getId_logusuario() );
	}

	@Override
	public LogUsuario searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogUsuario atualizar(LogUsuario objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}

	@Override
	public void desativar(LogUsuario objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LogUsuario consultarPorId(int id, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogUsuario> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogUsuario salvar(LogUsuario objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}

}
