package br.com.church.facade;

import static br.com.church.util.CheckInstanceObject.IsNull;
import static br.com.church.util.CheckInstanceObject.IsNullOrIsEmpty;
import static br.com.church.util.CheckInstanceObject.IsNullOrIsEmptyList;

import java.util.ArrayList;
import java.util.List;

import br.com.church.facade.Messages.Status;

public class Result<T> {
	private Messages messages;
	private T object;
	private List<T> list;
	
	public Result(T object, Messages messages) {
		this.object = object;
		this.messages = messages;
	}
	
	public Result(List<T> list, Messages messages) {
		this.list = list;
		this.messages = messages;
	}
	
	public Result(Messages messages) {
		this.messages = messages;
	}
	
	public T getResultObject() {
		return object;
	}
	
	public List<T> getResultList(){
		return list;
	}
	
	private Boolean messagesIsNull(Messages messages) {
		if(IsNull(messages))
			return true;
		if(IsNullOrIsEmptyList(messages.getListMessage()))
			return true;
		if(IsNullOrIsEmpty(messages.getListMessage().toString()))
			return true;
		return false;
	}
	
	/**
	 * messagem com apenas as descrição da mensagem
	 * @return messagens
	 */
	public String getResultMessage() {
		
		return messagesIsNull(messages) ? null : 
			messages.getListMessage().toString().replace("[", "").replace("]", "").equals("\nnull") ? null : 
			messages.getListMessage().toString().replace("[", "").replace("]", "").equals("null") ? null :
			messages.getListMessage().toString().replace("[", "").replace("]", "").equals(" null") ? null :
			messages.getListMessage().toString().replace("[", "").replace("]", "").equals("null ") ? null :
			messages.getListMessage().toString().replace("[", "").replace("]", "").replace("\nnull,", "").replace(", \nnull", "").replace(" \n", "");
	}
	
	/**
	 * messagem formatada para utilização
	 * @param status
	 * @return
	 */
	public String beutify(Status status) {
		if(messagesIsNull(messages))
			return null;
		String result = "";
		for(Message m : messages.getListMessage()) {
			if(status.equals(Status.ALL))
				result += m.getDescription();
			else if (status.equals(m.getStatus()))
				result += m.getDescription();
		}
		return result;
	}

	public List<Message> getListMessages() {
		return IsNull(messages) ? null : IsNullOrIsEmptyList(messages.getListMessage()) ? null : messages.getListMessage(); 
	}
	
	public List<Message> listMessages(Status status) {
		if(messagesIsNull(messages))
			return null;
		
		if(status.equals(Status.ALL))
			return messages.getListMessage();
		
		List<Message> listMessages = new ArrayList<Message>();
		for(Message m : messages.getListMessage()) {
			if(status.equals(m.getStatus()))
				listMessages.add(m);
		}
		
		if(IsNullOrIsEmptyList(listMessages))
			return null;
		
		return listMessages; 
	}
}
