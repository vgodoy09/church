/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.church.business.Command;
import br.com.church.dao.DAO;
import br.com.church.dao.DAOLogUsuario;
import br.com.church.dao.DAOUsuario;
import br.com.church.modelo.EntidadeDominio;
import br.com.church.modelo.Mensagem;
import br.com.church.modelo.Resultado;
import br.com.church.modelo.Usuario;
import br.com.church.modelo.logs.LogUsuario;

/**
 *
 * @author Victor Godoy
 */
public class Fachada implements IFachada {
	private Map<String, List<Command>> rns;
	@SuppressWarnings("rawtypes")
	private Map<String, DAO> daos;

	/**
	 * TODO:Construtor padrao/alternativo da classe
	 */
	@SuppressWarnings("rawtypes")
	public Fachada() {
		rns = new HashMap<String, List<Command>>();
		daos = new HashMap<String, DAO>();

		daos.put(Usuario.class.getName(), new DAOUsuario());
		daos.put(LogUsuario.class.getName(), new DAOLogUsuario());

		List<Command> rnsCliente = new ArrayList<Command>();
		rns.put(Usuario.class.getName(), rnsCliente);
		
		List<Command> rnsLogUsuario = new ArrayList<Command>();
		rns.put(LogUsuario.class.getName(), rnsLogUsuario);

	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		List<Command> cmds = rns.get(nmClasse);
		List<Mensagem> msgs = new ArrayList<Mensagem>();
		for (Command cmd : cmds) {
			String msg = (String) cmd.executar(entidade);
			if (msg != null)
				msgs.add(new Mensagem(msg));
		}

		if (msgs.size() == 0) {
			DAO dao = daos.get(nmClasse);
			try {
				dao.save(entidade);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return null;
		} else {

			return new Resultado(msgs);
		}
	}

	// public Resultado salvarLog(EntidadeDominio entidade) {
	// String nmClasse = entidade.getClass().getName();
	// DAO dao = daos.get(nmClasse);
	// try {
	// dao.salvar(entidade);
	// } catch (SQLException | ClassNotFoundException ex) {
	// Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null, ex);
	// }
	// return null;
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		List<Command> cmds = rns.get(nmClasse);
		List<Mensagem> msgs = new ArrayList<Mensagem>();
		for (Command cmd : cmds) {
			String msg = (String) cmd.executar(entidade);
			if (msg != null)
				msgs.add(new Mensagem(msg));
		}

		if (msgs.size() == 0) {
			DAO dao = daos.get(nmClasse);
			try {
				dao.update(entidade);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return null;
		} else {

			return new Resultado(msgs);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EntidadeDominio alterarNew(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();

		DAO dao = daos.get(nmClasse);
		try {
			return (EntidadeDominio) dao.update(entidade);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntidadeDominio alterarComValidacao(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		EntidadeDominio obj = null;
		List<Command> cmds = rns.get(nmClasse);
		List<Mensagem> msgs = new ArrayList<Mensagem>();
		for (Command cmd : cmds) {
			String msg = (String) cmd.executar(entidade);
			if (msg != null)
				msgs.add(new Mensagem(msg));
		}

		if (msgs.size() == 0) {
			DAO dao = daos.get(nmClasse);
			try {
				obj = (EntidadeDominio) dao.update(entidade);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return obj;
		} else {

			return new Resultado(msgs);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		try {
			dao.delete(entidade);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Resultado excluirComValidacao(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		List<Command> cmds = rns.get(nmClasse);
		List<Mensagem> msgs = new ArrayList<Mensagem>();
		for (Command cmd : cmds) {
			String msg = (String) cmd.executar(entidade);
			if (msg != null)
				msgs.add(new Mensagem(msg));
		}

		if (msgs.size() == 0) {

			try {
				dao.delete(entidade);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE,
						null, ex);
			}
			return null;
		} else {
			return new Resultado(msgs);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		EntidadeDominio result = null;
		try {
			result = (EntidadeDominio) dao.search(entidade);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public EntidadeDominio consultarById(Integer id, EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		EntidadeDominio result = null;
		try {
			result = (EntidadeDominio) dao.searchById(id);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public EntidadeDominio searchById(Integer id, EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		EntidadeDominio result = null;
		try {
			result = (EntidadeDominio) dao.searchById(id);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public EntidadeDominio consultarByDescricao(String descricao,
			EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		EntidadeDominio result = null;
		try {
			try {
				Integer id = Integer.parseInt(descricao);
				result = (EntidadeDominio) dao.searchById(id);
			} catch (Exception e) {
				result = (EntidadeDominio) dao.searchByDescription(descricao);
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public EntidadeDominio searchByDescricao(String descricao, Date dateInitial, Date dateFinal,
			EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		EntidadeDominio result = null;
		try {
			try {
				Integer id = Integer.parseInt(descricao);
				result = (EntidadeDominio) dao.searchById(id);
			} catch (Exception e) {
				result = (EntidadeDominio) dao.searchByDescription(descricao);
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return result;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EntidadeDominio> listAll(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		DAO dao = daos.get(nmClasse);
		List<EntidadeDominio> list = null;
		try {
			list = dao.listAll();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Fachada.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return list;
	}

}
