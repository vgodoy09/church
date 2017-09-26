package br.com.church.business;

import br.com.church.modelo.EntidadeDominio;

public interface Command {
	public Object executar(EntidadeDominio obj);
}
