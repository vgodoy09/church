package br.com.church.facade;

import java.sql.SQLException;
import java.util.List;

import br.com.church.dao.DAOCidade;
import br.com.church.facade.Messages.Status;
import br.com.church.modelo.Cidade;

public class FacadeCidade extends FacadePrincipal<Cidade>{
	
	public final Result<Cidade> listByState(Integer estado_id, DAOCidade dao) {
		Messages resultMessages = new Messages();
		List<Cidade> list = listByState(estado_id, dao, resultMessages);
		return new Result<Cidade>(list, resultMessages); 
	}
	
	protected List<Cidade> listByState(Integer estado_id, DAOCidade dao, Messages messages) {
		List<Cidade> listarTodos = null;
		try {
			listarTodos = dao.listarPeloEstado(estado_id);
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Listado com Sucesso");
		return listarTodos;
	}
}
