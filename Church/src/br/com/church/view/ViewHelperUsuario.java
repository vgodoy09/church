/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;

import br.com.church.modelo.Usuario;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperUsuario extends ViewHelper<Usuario>{
    private Usuario usua;
    @Override
    public void setDados(HttpServletRequest request) {
        String usuario = request.getParameter("email");
        String senha = request.getParameter("password");
        usua = new Usuario();
        usua.setLogin(usuario);
        usua.setSenha(senha);
    }

    @Override
    public Usuario getDados() {
        return usua;
    }
    
}
