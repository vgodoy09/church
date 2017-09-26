package br.com.church.facade;

import br.com.church.business.Command;
import br.com.church.modelo.EntidadeDominio;

public abstract class AbstractValidator implements Command{

	@Override
	public Object executar(EntidadeDominio obj) {
		return validate(obj);
	}
	
	public abstract Message validate(Object obj);

}
