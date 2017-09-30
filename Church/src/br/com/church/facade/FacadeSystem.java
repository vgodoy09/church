package br.com.church.facade;

import static br.com.church.util.CheckInstanceObject.IsNull;

import java.util.Arrays;
import java.util.List;

import br.com.church.business.Command;
import br.com.church.dao.DAO;

public abstract class FacadeSystem<T> {
	public final Result<T> save(T object, DAO<T> dao, Command... validations) {
		Result<T> validate = validate(object, validations);
		
		if(!IsNull(validate))
			return validate;
		
		Messages resultMessages = new Messages();
		T resultObject = save(object, dao, resultMessages);
		return new Result<T>(resultObject, resultMessages); 
	}
	
	public final Result<T> update(T object, DAO<T> dao, Command... validations) {
		Result<T> validate = validate(object, validations);
		
		if(!IsNull(validate))
			return validate;
		
		Messages resultMessages = new Messages();
		T resultObject = update(object, dao, resultMessages);
		return new Result<T>(resultObject, resultMessages); 
	}
	
	public final Result<T> delete(T object, DAO<T> dao, Command... validations) {
		Result<T> validate = validate(object, validations);
		
		if(!IsNull(validate))
			return validate;
		
		Messages resultMessages = new Messages();
		delete(object, dao, resultMessages);
		return new Result<T>(resultMessages); 
	}
	
	public final Result<T> save(T object, DAO<T> dao) {
		Messages resultMessages = new Messages();
		T resultObject = save(object, dao, resultMessages);
		return new Result<T>(resultObject, resultMessages); 
	}
	
	public final Result<T> update(T object, DAO<T> dao) {
		Messages resultMessages = new Messages();
		T resultObject = update(object, dao, resultMessages);
		return new Result<T>(resultObject, resultMessages); 
	}
	
	public final Result<T> delete(T object, DAO<T> dao) {
		Messages resultMessages = new Messages();
		delete(object, dao, resultMessages);
		return new Result<T>(resultMessages); 
	}
	
	public final Result<T> searchById(Integer id, DAO<T> dao) {
		Messages resultMessages = new Messages();
		T resultObject = searchById(id, dao, resultMessages);
		return new Result<T>(resultObject, resultMessages); 
	}
	
	public final Result<T> listAll(DAO<T> dao) {
		Messages resultMessages = new Messages();
		listAll(dao, resultMessages);
		return new Result<T>(resultMessages); 
	}
	
	private Result<T> validate(T object, Command... validations) {
		Facade<T> f = new GenericFacade<T>(object, Arrays.asList(validations));
		Result<T> validate = f.validate(object);
		return validate;
	}
	
	protected abstract T save(T object, DAO<T> dao, Messages messages);
	protected abstract T update(T object, DAO<T> dao, Messages messages);
	protected abstract T searchById(Integer id, DAO<T> dao, Messages messages);
	protected abstract void delete(T object, DAO<T> dao, Messages messages);
	protected abstract List<T> listAll(DAO<T> dao, Messages messages);
}
