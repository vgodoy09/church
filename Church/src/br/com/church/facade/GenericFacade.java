package br.com.church.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.church.business.Command;
import br.com.church.modelo.EntidadeDominio;

import static br.com.church.util.CheckInstanceObject.*;

public class GenericFacade<T> implements Facade<T> {
private Map<String, List<Command>> mlc;
	
	/**
	 * Construtor
	 * @param obj objeto que sera pego o nome da classe como referencia para validação exemplo: 
	 * Person todos os commands que estiverem na lista serao para validar o Person
	 * @param listCommands lista de validadores que irão validar o objeto passado
	 */
	public GenericFacade(Object obj, List<Command> listCommands){
		mlc = new HashMap<String, List<Command>>();
		String name = obj.getClass().getName();
		mlc.put(name, listCommands);
	}
	
	/**
	 * metodo que valida o objeto passado
	 * @param obj objeto a ser validado exemplo Person o objeto person sera validado
	 * @return resultado são mensagens de erro caso tenha caso nao tenha retorna null
	 */
	public Result<T> validate(T obj) {
		if(IsNull(mlc))
			return null;
		String nmClasse = obj.getClass().getName();
		List<Command> cmds = mlc.get(nmClasse);
		List<Message> msgs = new ArrayList<Message>();
		for (Command cmd : cmds) {
			Message msg = (Message) cmd.executar((EntidadeDominio) obj);
			if (!IsNull(msg) && !IsNullOrIsEmpty(msg.getDescription()))
				msgs.add(msg);
		}
		
		if (msgs.size() == 0) 
			return null;
		else 
			return new Result<T>(obj, new Messages(msgs));
	}
}
