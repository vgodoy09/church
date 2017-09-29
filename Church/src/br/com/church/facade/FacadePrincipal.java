package br.com.church.facade;

import java.sql.SQLException;
import java.util.List;

import br.com.church.dao.DAO;
import br.com.church.facade.Messages.Status;

public class FacadePrincipal<T> extends FacadeSystem<T>{

	@Override
	protected T save(T object, DAO<T> dao, Messages messages) {
		try {
			object = dao.salvar(object);
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Salvo com sucesso");

		return object;
	}

	@Override
	protected T update(T object, DAO<T> dao, Messages messages) {
		try {
			object = dao.atualizar(object);
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Salvo com sucesso");

		return object;
	}

	@Override
	protected void delete(T object, DAO<T> dao, Messages messages) {
		try {
			dao.desativar(object);
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Salvo com sucesso");
	}

	@Override
	protected List<T> listAll(DAO<T> dao, Messages messages) {
		List<T> listarTodos = null;
		try {
			listarTodos = dao.listarTodos();
		} catch (ClassNotFoundException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		} catch (SQLException e) {
			messages.addResultMessage(Status.ERROR, e.getMessage());
		}
		messages.addResultMessage(Status.SUCCESS, "Listado com Sucesso");
		return listarTodos;
	}

}
