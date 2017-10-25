/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import static br.com.church.util.CheckInstanceObject.IsNull;
import static br.com.church.util.CheckInstanceObject.IsNullOrIsEmpty;
import static br.com.church.util.UtilsEnuns.getSexo;
import static br.com.church.util.UtilsEnuns.getStatus;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import br.com.church.facade.FacadeCidade;
import br.com.church.facade.FacadeEstado;
import br.com.church.facade.FacadePrincipal;
import br.com.church.modelo.Pais;
import br.com.church.modelo.Usuario;
import static br.com.church.util.UtilsDate.*;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperUsuario extends ViewHelper<Usuario>{
    private Usuario usua;
//    private FacadePrincipal<Pais> fp = new FacadePrincipal<Pais>();
//	private FacadeEstado fe = new FacadeEstado();
//	private FacadeCidade fc = new FacadeCidade();
    @Override
    public void setDados(HttpServletRequest request) {
    	
    	String id = request.getParameter("txtid");
        Integer usuarioId = null;
        if(!IsNullOrIsEmpty(id)){
	        try {
	        	usuarioId = Integer.parseInt(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
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
        String paisname = request.getParameter("txtpais");
        String estadoname = request.getParameter("txtestado");
        String cidadename = request.getParameter("txtcidade");
        
        usua = new Usuario();
        if(!IsNull(usuarioId))
        	usua.setId_usuario(usuarioId);
        usua.setLogin(usuario);
        usua.setSenha(senha);
        usua.setNome(nome);
        usua.setEndereco(endereco);
        if(!IsNullOrIsEmpty(numero))
        	usua.setNumero(Integer.parseInt(numero));
        if(!IsNullOrIsEmpty(dataFormatada)) {
        	Date date = dtDateformat(dataFormatada, "dd/MM/yyyy");
        	GregorianCalendar gc = new GregorianCalendar();
        	gc.setTime(date);
        	usua.setDataNascimento(gc);
        }
        if(!IsNullOrIsEmpty(sexo))
        	usua.setSexo(getSexo(sexo));
        if(!IsNullOrIsEmpty(status))
        	usua.setStatus(getStatus(status));
        if(!IsNullOrIsEmpty(paises))
        	usua.setIdPais(Integer.parseInt(paises));
        if(!IsNullOrIsEmpty(estados))
        	usua.setIdEstado(Integer.parseInt(estados));
        if(!IsNullOrIsEmpty(cidades))
        	usua.setIdCidade(Integer.parseInt(cidades));
        if(!IsNullOrIsEmpty(paisname))
        	usua.setNom_pais(paisname);
        if(!IsNullOrIsEmpty(estadoname))
        	usua.setNom_estado(estadoname);
        if(!IsNullOrIsEmpty(cidadename))
        	usua.setNom_cidade(cidadename);
    }

    @Override
    public Usuario getDados() {
        return usua;
    }
    
}
