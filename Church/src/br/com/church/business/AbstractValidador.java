
package br.com.church.business;

import br.com.church.modelo.EntidadeDominio;




public abstract class AbstractValidador implements Command{

        @Override
	public Object executar(EntidadeDominio entidade){
		return validar(entidade);
	}
	
	public abstract String validar(EntidadeDominio entidade);
}
