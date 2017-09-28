package br.com.church.dao;

import static br.com.church.util.CheckInstanceObject.IsNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.church.modelo.EntidadeDominio;
import br.com.church.modelo.Usuario;
import br.com.church.modelo.enuns.Sexo;
import br.com.church.modelo.enuns.Status;
import br.com.church.util.Util;

public class DAOUsuario extends DAO<Usuario>{

	@Override
	public Usuario update(Usuario objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Usuario objeto, EntityManager em) {
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Usuario searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Usuario.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> listAll(EntityManager em) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from usuario where status ='ATIVO'";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		usuarios = (List<Usuario>) query.getResultList();
		return usuarios;
	}

	@Override
	public void save(Usuario objeto, EntityManager em) {
		objeto.setStatus(Status.ATIVO);
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}
	
	public boolean ControleAcesso(Usuario usuario) throws ClassNotFoundException {
        boolean retorno = true;
        Usuario resultado = null;
        try {
        	EntityManager em = Util.criaConexao();
        	String sql = "from Usuario u where u.status ='ATIVO' and u.senha = ?1 and u.login = ?2";
        	em.getTransaction().begin();
        	Query query = em.createQuery(sql);
        	query.setParameter(1, usuario.getSenha());
        	query.setParameter(2, usuario.getLogin());
        	resultado = (Usuario) query.getSingleResult();
        	usuario.setId_usuario(resultado.getId_usuario());
        	usuario.setNome(resultado.getNome());
//        	usuario.setPerfil(resultado.getPerfil());
        	usuario.setDataNascimento(resultado.getDataNascimento());
//        	usuario.setEstadoCivil(resultado.getEstadoCivil());
        	usuario.setIdCidade(resultado.getIdCidade());
        	usuario.setIdEstado(resultado.getIdEstado());
        	usuario.setIdPais(resultado.getIdPais());
        	usuario.setSexo(resultado.getSexo());
        	usuario.setStatus(resultado.getStatus());
        	if(resultado != null){
        		return retorno;
        	}
        } catch(NoResultException e){
		} catch (Exception e) {
			e.printStackTrace();
		}

        retorno = (resultado != null);
        return retorno;
    }
	
	/**
	 * para o controle de acesso de usuario no banco MySQL
	 * @param usuario 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean ControleAcessoMySQL(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement("select * from user u where u.status ='ATIVO' and u.senha = ? and u.login = ?");
            sql.setString(1, usuario.getSenha());
            sql.setString(2, usuario.getLogin());
            ResultSet resultado = sql.executeQuery();
            boolean retorno = false;
            if(resultado.next()){
                retorno = true;
                usuario = preencher(resultado);
            }
            return retorno;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }

    }
	
	/**
	 * para o controle de acesso de usuario no banco MySQL
	 * @param usuario 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Usuario ControleAcessoNovo(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        Usuario usu = null;
        try {
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement("select * from user u where u.status ='ATIVO' and u.senha = ? and u.login = ?");
            sql.setString(1, usuario.getSenha());
            sql.setString(2, usuario.getLogin());
            ResultSet resultado = sql.executeQuery();
            if(resultado.next()){
            	usu = preencher(resultado);
            }
            return usu;
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }

    }
	
	/**
     * Este metodo preencher usuario do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Usuario preencher(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(resultado.getInt("id"));
        usuario.setLogin(resultado.getString("login"));
        usuario.setSenha(resultado.getString("senha"));
        usuario.setNome(resultado.getString("name"));
        usuario.setStatus(getStatus(resultado.getString("status")));
         return usuario;
    }
    
    /**
	 * metodo que retorna o Perfil apartir da string
	 * @param type ADMGERAL ou USR_COMUM
	 * @return
	 */
//	private Perfil getType(String type){
//		if(Perfil.ADMGERAL.equals(type)){
//			return Perfil.ADMGERAL;
//		}else{
//			return Perfil.USR_COMUM;
//		}
//	}
	
	private Status getStatus(String status){
		if(Status.ATIVO.equals(status)){
			return Status.ATIVO;
		}else{
			return Status.INATIVO;
		}
	}
	
	private Sexo getSexo(String status){
		if(Sexo.MASCULINO.equals(status)){
			return Sexo.MASCULINO;
		}else{
			return Sexo.FEMININO;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean ValidarUsuarioLoginExistente(EntidadeDominio entidade) throws SQLException, ClassNotFoundException {
        Usuario usu = (Usuario)entidade;
        boolean retorno = true;
        List<Usuario> usuarios = null;
        EntityManager em = null;
        try {
        	em = Util.criaConexao();
        	usuarios = new ArrayList<Usuario>();
    		String sql = "select * from usuario where status ='ATIVO' and (login=?1)";
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql);
    		query.setParameter(1, usu.getLogin());
    		usuarios = (List<Usuario>) query.getResultList();
    		if(usuarios.size() != 0){
                return retorno;
	        }
        } finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
        retorno = (usuarios.size() != 0);
        return retorno;
    }
	
	public List<Usuario> getUser(String us) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
        	
        	conexao = Util.criaConexaoMySql();
        	PreparedStatement sql = 
        			conexao.prepareStatement(" select * from user u where u.status ='ATIVO' and ((? is null or u.name like ?) or (? is null or u.id = ?)) ");	
    		String name = "%" + (us == null ? null : us.replace(" ", "%")) + "%"; 
    		sql.setString(1, us);
    		sql.setString(2, name);
    		sql.setString(3, us);
    		sql.setString(4, us);
    		ResultSet resultado = sql.executeQuery();
    		List<Usuario> usuarios = new ArrayList<Usuario>();
            while(resultado.next()){
                usuarios.add(preencher(resultado));
            }
            return usuarios;
        } finally {
            try {
            	conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	@SuppressWarnings("unchecked")
	public boolean VerificaSenhaAtual(EntidadeDominio entidade) throws SQLException, ClassNotFoundException {
//        Usuario usu = (Usuario)entidade;
        boolean retorno = true;
        List<Usuario> usuarios = null;
        EntityManager em = null;
        try {
        	em = Util.criaConexao();
        	usuarios = new ArrayList<Usuario>();
    		String sql = "select * from Usuario u where u.status ='ATIVO' and u.senha = ?1 and u.login = ?2";
    		em.getTransaction().begin();
    		Query query = em.createNativeQuery(sql);
//    		query.setParameter(1, usu.getSenhaAtual());
//    		query.setParameter(2, usu.getLoginNovo());
    		usuarios = (List<Usuario>) query.getResultList();
    		if(usuarios.size() != 0){
                return retorno;
	        }
        } finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
        retorno = (usuarios.size() != 0);
        return retorno;
    }

	@Override
	public Usuario search(Usuario objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Usuario.class, objeto.getId_usuario());
	}

	@Override
	public Usuario searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario atualizar(Usuario objeto) throws SQLException, ClassNotFoundException {
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

	
	public void atualizarSenha(Usuario objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update user set senha =? where id =?");
		sql.setString(1, objeto.getSenha());
		sql.setInt(2, objeto.getId_usuario());
		sql.executeUpdate();
	}
	
	public void atualizarSenha(Usuario objeto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
            atualizarSenha(objeto, conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }

	@Override
	public Usuario atualizar(Usuario objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("update user set senha =? where id =?");
		sql.setString(1, objeto.getSenha());
		sql.setInt(2, objeto.getId_usuario());
		sql.executeUpdate();
		return objeto;
	}

	@Override
	public void desativar(Usuario objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Usuario consultarPorId(int id) throws SQLException, ClassNotFoundException {
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
	public Usuario consultarPorId(int id, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("select * from user u where u.status ='ATIVO' and u.id=?");
        sql.setInt(1, id);
        ResultSet resultado = sql.executeQuery();
        Usuario user = null;
        if(resultado.next()){
            user = preencherCompleto(resultado);
        }
		return user;
	}

	@Override
	public List<Usuario> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvar(Usuario objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}
	
	public List<Usuario> listarTodosUsuarios() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = Util.criaConexaoMySql();
           return listarTodosUsuarios(conexao);
        } finally {
            try {
                conexao.close();
            } catch (Exception erro) {
            }
        }
    }
	
	public List<Usuario> listarTodosUsuarios(Connection conexao) throws SQLException {
		PreparedStatement sql = 
    			conexao.prepareStatement(                                      
    					"select u.id,                                          " +
	    				"	 u.name,                                           " +
	    				"    u.login,                                          " +
	    				"    u.senha,                                          " +
	    				"    u.status,                                         " +
	    				"    u.dateBirth,                                      " +
	    				"    u.sexo,                                           " +
	    				"	 u.pais_id,										   " +	
	    				"    p.nom_pais,                                       " +
	    				"    u.estado_id,                                      " +
	    				"    e.nom_estado,                                     " +
	    				"    u.cidade_id,                                      " +
	    				"    c.nom_cidade,                                     " +
	    				"    u.endereco,                                       " +
	    				"    u.number                                          " +
	    				"from user u                                           " +
	    				"	 inner join pais p on p.cod_pais = u.pais_id       " +
	    				"    inner join estado e on e.cod_estado = u.estado_id " +
	    				"    inner join cidade c on c.cod_cidade = u.cidade_id"  );
        ResultSet resultado = sql.executeQuery();
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(resultado.next()){
        	usuarios.add(preencherCompleto(resultado));
        }
        return usuarios;
	}
	
	/**
     * Este metodo preencher usuario do banco de dados.
     * @param ResultSet a resultado que deve retornar um resultado do banco.
     * @throws SQLException caso ocorra alguma excessao na comunicacao com o banco.
     */
    public Usuario preencherCompleto(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(resultado.getInt("id"));
        usuario.setNome(resultado.getString("name"));
        usuario.setLogin(resultado.getString("login"));
        usuario.setSenha(resultado.getString("senha"));
        usuario.setStatus(getStatus(resultado.getString("status")));
        usuario.setStatusName(resultado.getString("status"));
        GregorianCalendar gc = new GregorianCalendar();
        Date date = resultado.getDate("dateBirth");
        if(!IsNull(date)) 
        	gc.setTime(date);
        usuario.setDataNascimento(gc);
        usuario.setSexo(getSexo(resultado.getString("sexo")));
        usuario.setSexoName(resultado.getString("sexo"));
        usuario.setIdPais(resultado.getInt("pais_id"));
        usuario.setNom_pais(resultado.getString("nom_pais"));
        usuario.setIdEstado(resultado.getInt("estado_id"));
        usuario.setNom_estado(resultado.getString("nom_estado"));
        usuario.setIdCidade(resultado.getInt("cidade_id"));
        usuario.setNom_cidade(resultado.getString("nom_cidade"));
        usuario.setEndereco(resultado.getString("endereco"));
        usuario.setNumero(resultado.getInt("number"));
         return usuario;
    }
}
