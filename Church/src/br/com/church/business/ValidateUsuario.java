package br.com.church.business;

import br.com.church.facade.AbstractValidator;
import br.com.church.facade.Message;
import br.com.church.facade.Messages.Status;
import br.com.church.modelo.Usuario;
import static br.com.church.util.CheckInstanceObject.*;

public class ValidateUsuario extends AbstractValidator {

	@Override
	public Message validate(Object obj) {
		Usuario usuario = (Usuario) obj;
		
		if (IsNull(usuario))
			return new Message(Status.WARNING, "Usuario não informado, favor informar - lo.");

		return null;
	}

}
