package br.com.church.util;

import br.com.church.modelo.enuns.Sexo;
import br.com.church.modelo.enuns.Status;

public class UtilsEnuns {
	
	public final static Status getStatus(String status){
		if(Status.ATIVO.equals(status)){
			return Status.ATIVO;
		}else{
			return Status.INATIVO;
		}
	}
	
	public final static Sexo getSexo(String status){
		if(Sexo.MASCULINO.equals(status)){
			return Sexo.MASCULINO;
		}else{
			return Sexo.FEMININO;
		}
	}
}
