/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import java.util.Date;
import java.util.GregorianCalendar;

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
    	String nome = request.getParameter("nome");
    	String endereco = request.getParameter("endereco");
    	String numero = request.getParameter("numero");
    	String dataFormatada = request.getParameter("dataFormatada");
    	String sexo = request.getParameter("sexo");
    	String status = request.getParameter("status");
    	String paises = request.getParameter("paises");
    	String estados = request.getParameter("estados");
    	String cidades = request.getParameter("cidades");
        String usuario = request.getParameter("email");
        String senha = request.getParameter("password");
        usua = new Usuario();
        usua.setLogin(usuario);
        usua.setSenha(senha);
        usua.setNome(nome);
        usua.setEndereco(endereco);
        usua.setNumero(Integer.parseInt(numero));
        usua.setDataNascimento(new GregorianCalendar());
    }

    @Override
    public Usuario getDados() {
        return usua;
    }
    
}
