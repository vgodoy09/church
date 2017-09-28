package br.com.church.facade;

import br.com.church.business.Command;
import br.com.church.dao.DAO;

import static br.com.church.util.CheckInstanceObject.*;

import java.util.Arrays;

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
		return new Result<T>(null, resultMessages); 
	}
	
	private Result<T> validate(T object, Command... validations) {
		Facade<T> f = new GenericFacade<T>(object, Arrays.asList(validations));
		Result<T> validate = f.validate(object);
		return validate;
	}
	
	protected abstract T save(T object, DAO<T> dao, Messages messages);
	protected abstract T update(T object, DAO<T> dao, Messages messages);
	protected abstract void delete(T object, DAO<T> dao, Messages messages);
}
