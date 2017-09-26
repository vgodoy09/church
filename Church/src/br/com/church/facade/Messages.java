package br.com.church.facade;

import java.util.ArrayList;
import java.util.List;

import static br.com.church.util.CheckInstanceObject.*;

public class Messages {
	public enum Status {
		SUCCESS, ERROR, ALL, WARNING
	}
	
	private String MESSAGE_ERRO_DESCRIPTION = "Necessario inserir uma descrição";
	private String MESSAGE_ERRO_STATUS = "Necessario inserir um estatus";
	private List<Message> listMessage;
	
	public Messages() {
	}

	public Messages(List<Message> listMessage) {
		this.listMessage = listMessage;
	}

	public List<Message> getListMessage() {
		return listMessage;
	}
	
	public void addResultMessage(Status status, int code, String description) {
		boolean statusIsNull = statusIsNull(status);
		boolean descriptionIsNull = descriptionIsNull(description);
		status = verifyStatus(status, statusIsNull, descriptionIsNull);
		description = verifyDescription(description, statusIsNull, descriptionIsNull);
		if(IsNullOrIsEmptyList(listMessage))
			listMessage = new ArrayList<Message>();
		Message message = new Message(status, code, description);
		listMessage.add(message);
	}
	
	public void addResultMessage(Status status, String description) {
		boolean statusIsNull = statusIsNull(status);
		boolean descriptionIsNull = descriptionIsNull(description);
		status = verifyStatus(status, statusIsNull, descriptionIsNull);
		description = verifyDescription(description, statusIsNull, descriptionIsNull);
		if(IsNullOrIsEmptyList(listMessage))
			listMessage = new ArrayList<Message>();
		Message message = new Message(status, description);
		listMessage.add(message);
	}

	private Status verifyStatus(Status status, boolean statusIsNull, boolean descriptionIsNull) {
		if(statusIsNull || descriptionIsNull)
			status = Status.ERROR;
		return status;
	}

	private String verifyDescription(String description, boolean statusIsNull, boolean descriptionIsNull) {
		if(statusIsNull)
			description = MESSAGE_ERRO_STATUS;
		
		if(statusIsNull && descriptionIsNull)
			description += "\n " + MESSAGE_ERRO_DESCRIPTION;
		else if(descriptionIsNull)
			description = MESSAGE_ERRO_DESCRIPTION;
		return description;
	}
	
	private boolean statusIsNull(Status status) {
		if(IsNull(status))
			return true;
		return false;
	}
	
	private boolean descriptionIsNull(String description) {
		if(IsNullOrIsEmpty(description))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return listMessage + "";
	}
}
