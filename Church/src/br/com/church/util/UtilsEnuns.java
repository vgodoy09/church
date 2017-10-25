package br.com.church.util;

import br.com.church.modelo.enuns.Sexo;
import br.com.church.modelo.enuns.Status;

public class UtilsEnuns {
	
	public final static Status getStatus(String status){
		if(CheckInstanceObject.IsNullOrIsEmpty(status))
			return null;
		status = status.toUpperCase();
		if(Status.ATIVO.name().toUpperCase().equals(status)){
			return Status.ATIVO;
		}else{
			return Status.INATIVO;
		}
	}
	
	public final static Sexo getSexo(String sexo){
		if(CheckInstanceObject.IsNullOrIsEmpty(sexo))
			return null;
		sexo = sexo.toUpperCase();
		if(Sexo.MASCULINO.name().toUpperCase().equals(sexo)){
			return Sexo.MASCULINO;
		}else{
			return Sexo.FEMININO;
		}
	}
}
