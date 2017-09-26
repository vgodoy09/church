/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.modelo;

import java.util.List;

/**
 *
 * @author Victor Godoy
 */
public class Resultado extends EntidadeDominio{
    private List<EntidadeDominio> entidades;
	private List<Mensagem> mensagens;
	
	public Resultado(List<Mensagem> mensagens, List<EntidadeDominio> entidades){
		this.entidades = entidades;
	}
	
	public Resultado(List<Mensagem> mensagens){
		this.mensagens = mensagens;
	}
	
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}
