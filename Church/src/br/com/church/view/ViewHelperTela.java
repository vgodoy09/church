/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;

import br.com.church.modelo.Tela;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperTela extends ViewHelper<Tela>{
    private Tela tela;
    @Override
    public void setDados(HttpServletRequest request) {
        String id = request.getParameter("txtid");
        Integer telaId = null;
        if(id != null && !id.isEmpty()){
	        try {
	        	telaId = Integer.parseInt(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
        String nome = request.getParameter("name");
        String filename = request.getParameter("filename");
        Integer application_id = Integer.parseInt(request.getParameter("application"));
        tela = new Tela();
        if(telaId != null)
        	tela.setId(telaId);
        tela.setName(nome);
        tela.setFilename(filename);
        tela.setApplication_id(application_id);
    }

    @Override
    public Tela getDados() {
        return tela;
    }
    
}
