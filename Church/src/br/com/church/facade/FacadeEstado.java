package br.com.church.facade;

import java.sql.SQLException;
import java.util.List;

import br.com.church.dao.DAOEstado;
import br.com.church.facade.Messages.Status;
import br.com.church.modelo.Estado;

public class FacadeEstado extends FacadePrincipal<Estado>{
	
	public final Result<Estado> listByCountry(Integer pais_id, DAOEstado dao) {
		Messages resultMessages = new Messages();
		List<Estado> list = listByCountry(pais_id, dao, resultMessages);
		return new Result<Estado>(list, resultMessages); 
	}
	
	protected List<Estado> listByCountry(Integer pais_id, DAOEstado dao, Messages messages) {
		List<Estado> listarTodos = null;
		try {
			listarTodos = dao.listarPeloPais(pais_id);
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Listado com Sucesso");
		return listarTodos;
	}
}
