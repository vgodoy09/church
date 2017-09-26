package br.com.church.facade;

import br.com.church.facade.Messages.Status;
import static br.com.church.util.CheckInstanceObject.*;

public class Message {
	private Status status;
	private Integer code;
	private String description;
	
	public Message() {
	}
	
	public Message(Status status, String description) {
		this.status = status;
		this.description = description;
	}
	
	public Message(Status status, Integer code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public Integer getCode() {
		return code;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "\n" + description;
	}
	
	/**
	 * metodo que traz a mensagem de erro com descrição apenas ex.: Codigo de Erro: 00, Descrição do Erro: Campo Obrigatorio.
	 * @return a messagem
	 */
	public String getMessageResult() {
		return "\n" + "Codigo de Erro: " + (IsNull(code) ? "" : code)  + ", " + "Descrição do Erro: " + description; 
	}
	
	/**
	 * metodo que traz a mensagem de erro sem nenhuma descrição apenas ex.: 00 Campo Obrigatorio.
	 * @return a messagem
	 */
	public String getMessageResultClean() {
		return "\n" +  (IsNull(code) ? "" : code) + " "  + description; 
	}
}
