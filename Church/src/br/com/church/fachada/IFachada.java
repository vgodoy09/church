
package br.com.church.fachada;

import br.com.church.modelo.EntidadeDominio;
import br.com.church.modelo.Resultado;





public interface IFachada {

	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public EntidadeDominio consultar(EntidadeDominio entidade);
	
	
}
