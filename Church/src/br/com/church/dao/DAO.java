package br.com.church.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.church.util.Util;

import java.sql.*;

public abstract class DAO<T> {

	
	public void save(T objeto) throws ClassNotFoundException {
		EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            save(objeto, em);
        } finally {
            try {
//            	em.getTransaction().rollback();
                em.close();
            } catch (Exception erro) {
            	erro.printStackTrace();
            }
        }
    }
    public List<T> listAll() throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            return listAll(em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	erro.printStackTrace();
//            	em.getTransaction().rollback();
            }
        }
    }

    public T searchById(int id) throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            return searchById(id, em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	em.getTransaction().rollback();
            }
        }
    }

    public void delete(T objeto) throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            delete(objeto, em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	em.getTransaction().rollback();
            }
        }
    }

    public Object update(T objeto) throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            return update(objeto, em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	em.getTransaction().rollback();
            }
        }
    }
    
    public T search(T objeto) throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
           return search(objeto, em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	em.getTransaction().rollback();
            }
        }
    }
    
    public T searchByDescription(String description) throws ClassNotFoundException {
    	EntityManager em = null;
        try {
        	em = Util.criaConexao();	
            return searchByDescription(description, em);
        } finally {
            try {
                em.close();
            } catch (Exception erro) {
            	em.getTransaction().rollback();
            }
        }
    }
    
    // -------------- crud mysql
    /**
     * salvar sem hibernate
     * @param objeto
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void salvar(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            salvar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
    
    /**
     * listar todos sem hibernate
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<T> listarTodos() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            return listarTodos(conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    /**
     * consultar pelo id sem hibernate
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public T consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
    
    /**
     * desativar ou excluir sem hibernate
     * @param objeto
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void desativar(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            desativar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

    /**
     * atualizar sem hibernate
     * @param objeto
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void atualizar(T objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            atualizar(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
    
    /**
     * salvar pelo Mysql sem hibernate
     * @param objeto
     * @param conexao
     * @throws SQLException
     */
    public abstract void atualizar(T objeto, Connection conexao) throws SQLException;
    /**
     * desativar pelo Mysql sem hibernate
     * @param objeto
     * @param conexao
     * @throws SQLException
     */
    public abstract void desativar(T objeto, Connection conexao) throws SQLException;
    /**
     * consultar por id sem hibernate
     * @param id
     * @param conexao
     * @return
     * @throws SQLException
     */
    public abstract T consultarPorId(int id, Connection conexao) throws SQLException;
    /**
     * listar todos pelo Mysql sem hibernate
     * @param conexao
     * @return
     * @throws SQLException
     */
    public abstract List<T> listarTodos(Connection conexao) throws SQLException;
    
    /**
     * salvar no mysql sem hibernate
     * @param objeto
     * @param conexao
     * @throws SQLException
     */
    public abstract void salvar(T objeto, Connection conexao) throws SQLException;


    public abstract Object update(T objeto, EntityManager em);
    public abstract void delete(T objeto, EntityManager em);
    public abstract T searchById(int id, EntityManager em);
    public abstract T searchByDescription(String description, EntityManager em);
    public abstract T search(T objeto, EntityManager em);
    public abstract List<T> listAll(EntityManager em);
    public abstract void save(T objeto, EntityManager em);
}
