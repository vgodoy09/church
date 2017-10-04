package br.com.church.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.church.modelo.PerfilUsuario;

public class DAOPerfilUsuario extends DAO<PerfilUsuario>{

	@Override
	public PerfilUsuario atualizar(PerfilUsuario objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desativar(PerfilUsuario objeto, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PerfilUsuario consultarPorId(int id, Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerfilUsuario> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilUsuario salvar(PerfilUsuario objeto, Connection conexao) throws SQLException {
		PreparedStatement sql = conexao.prepareStatement("insert into userprofile (user_id,profile_id) values (?, ?)");
		sql = setSql(sql, objeto);
		sql.execute();
		return objeto;
	}
	
	private PreparedStatement setSql(PreparedStatement sql, PerfilUsuario obj) throws SQLException{
    	sql.setInt(1, obj.getUser_id());
    	sql.setInt(2, obj.getProfile_id());
    	return sql;
    }

	@Override
	public Object update(PerfilUsuario objeto, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PerfilUsuario objeto, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PerfilUsuario searchById(int id, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilUsuario searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilUsuario search(PerfilUsuario objeto, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PerfilUsuario> listAll(EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PerfilUsuario objeto, EntityManager em) {
		// TODO Auto-generated method stub
		
	}

}
