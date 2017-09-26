/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;

import br.com.church.modelo.Perfil;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperPerfil extends ViewHelper<Perfil>{
    private Perfil perfil;
    @Override
    public void setDados(HttpServletRequest request) {
        String id = request.getParameter("txtid");
        Integer perfilId = null;
        if(id != null && !id.isEmpty()){
	        try {
	        	perfilId = Integer.parseInt(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        String nome = request.getParameter("name");
        Integer application_id = Integer.parseInt(request.getParameter("selecionarAplicacaoAtualizar"));
        perfil = new Perfil();
        if(perfilId != null)
        	perfil.setId(perfilId);
        perfil.setName(nome);
        perfil.setApplication_id(application_id);
    }

    @Override
    public Perfil getDados() {
        return perfil;
    }
    
}
