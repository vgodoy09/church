package br.com.church.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.church.modelo.Categoria;
import br.com.church.modelo.EntidadeDominio;
import br.com.church.modelo.Usuario;
import br.com.church.util.Util;

public class DAOCategoria extends DAO<Categoria>{

	@Override
	public Categoria update(Categoria objeto, EntityManager em) {
		Categoria nova = new Categoria();
		em.getTransaction().begin();
		nova = em.merge(objeto);
//		objeto = em.find(Categoria.class, objeto.getId_categoria());
//		em.getTransaction().commit();
		return nova;
	}

	@Override
	public void delete(Categoria objeto, EntityManager em) {
		em.getTransaction().begin();
		em.remove(em.getReference(Categoria.class, objeto.getId_categoria()));
		em.getTransaction().commit();
	}

	@Override
	public Categoria searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Categoria.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Categoria> listAll(EntityManager em) {
		List<Categoria> Categorias = new ArrayList<Categoria>();
		String sql = "select * from Categoria";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Categorias = (List<Categoria>) query.getResultList();
		return Categorias;
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listAll(Usuario usuario) throws ClassNotFoundException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Categoria where (idcliente=?1) and idraiz is not null order by id_categoria";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Categoria.class);
			query.setParameter(1, usuario.getId_usuario());
			categorias = (List<Categoria>) query.getResultList();
			return categorias;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	@SuppressWarnings("unchecked")
	public List<Categoria> listTodas(Usuario usuario) throws ClassNotFoundException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Categoria where (idcliente=?1) order by id_categoria";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Categoria.class);
			query.setParameter(1, usuario.getId_usuario());
			categorias = (List<Categoria>) query.getResultList();
			return categorias;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	@SuppressWarnings("unchecked")
	public List<Categoria> listAllRaiz(Usuario usuario) throws ClassNotFoundException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Categoria where (idcliente=?1) and idraiz is null order by id_categoria";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Categoria.class);
			query.setParameter(1, usuario.getId_usuario());
			categorias = (List<Categoria>) query.getResultList();
			return categorias;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}

	@Override
	public void save(Categoria objeto, EntityManager em) {
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Categoria search(Categoria objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Categoria.class, objeto.getId_categoria());
	}
	
	@SuppressWarnings("unchecked")
	public boolean ValidarCategorias(EntidadeDominio entidade) throws SQLException, ClassNotFoundException {
		Categoria categoria = (Categoria)entidade;
        boolean retorno = true;
        List<Categoria> categorias = null;
        EntityManager em = null;
        try {
        	em = Util.criaConexao();
        	categorias = new ArrayList<Categoria>();
    		String sql = "select * from Categoria where (name=?1) and (idcliente=?2)";
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql);
    		query.setParameter(1, categoria.getName());
    		query.setParameter(2, categoria.getIdUsuario());
    		categorias = (List<Categoria>) query.getResultList();
    		if(categorias.size() != 0){
                return retorno;
	        }
        } finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
        retorno = (categorias.size() != 0);
        return retorno;
    }
	
	@SuppressWarnings("unchecked")
	public boolean ValidarExclusaoCategorias(EntidadeDominio entidade) throws SQLException, ClassNotFoundException {
		Categoria categoria = (Categoria)entidade;
        boolean retorno = true;
        List<Categoria> categorias = null;
        EntityManager em = null;
        try {
        	em = Util.criaConexao();
        	categorias = new ArrayList<Categoria>();
    		String sql = "select * from Categoria where id_categoria = ?1 and idcliente = ?2 and  "
    					+ " id_categoria in (select idraiz from Categoria where idcliente = ?2 and idraiz is not null)";
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql);
    		query.setParameter(1, categoria.getId_categoria());
    		query.setParameter(2, categoria.getIdUsuario());
    		categorias = (List<Categoria>) query.getResultList();
    		if(categorias.size() != 0){
                return retorno;
	        }
        } finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
        retorno = (categorias.size() != 0);
        return retorno;
    }
	
	
	@Override
	public Categoria searchByDescription(String description, EntityManager em){
    		String sql = "select * from Categoria where name = ?1";
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql,Categoria.class);
    		query.setParameter(1, description);
    		return (Categoria) query.getSingleResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Categoria> searchByDescription(String description, Usuario usuario) throws ClassNotFoundException {
		List<Categoria> transacoes = new ArrayList<Categoria>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Categoria c where c.name like ?1 and idcliente = ?2";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Categoria.class);
			query.setParameter(1, "%"+description+"%");
			query.setParameter(2, usuario.getId_usuario());
			transacoes = (List<Categoria>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            	erro.printStackTrace();
            }
        }
	}

	@Override
	public void atualizar(Categoria objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desativar(Categoria objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria consultarPorId(int id, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Categoria objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
