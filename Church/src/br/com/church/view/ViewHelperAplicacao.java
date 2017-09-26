/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;

import br.com.church.modelo.Aplicacao;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperAplicacao extends ViewHelper<Aplicacao>{
    private Aplicacao app;
    @Override
    public void setDados(HttpServletRequest request) {
        String id = request.getParameter("txtid");
        Integer aplicacaoId = null;
        if(id != null && !id.isEmpty()){
	        try {
	        	aplicacaoId = Integer.parseInt(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
        String nomeAplicacao = request.getParameter("nome");
        String idButton = request.getParameter("idButton");
        String imagem1 = request.getParameter("imageAvailable");
        String imagem2 = request.getParameter("imageUnavailable");
        String link = request.getParameter("link");
        Boolean visible = Boolean.parseBoolean(request.getParameter("visible") == null ? "false" : "true");
        String message = request.getParameter("message");
        
        app = new Aplicacao();
        if(aplicacaoId != null)
        	app.setId(aplicacaoId);
        app.setNome(nomeAplicacao);
        app.setNamebutton(idButton);
        app.setImageLogoAvailable(imagem1);
        app.setImageLogoUnavailable(imagem2);
        app.setLink(link);
        app.setVisible(visible);
        app.setMessageUserActivation(message);
    }

    @Override
    public Aplicacao getDados() {
        return app;
    }
    
}
