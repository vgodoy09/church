/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.modelo;

/**
 *
 * @author Victor Godoy
 */
public class Mensagem {
    private String descricao;
	
	public Mensagem(String descricao){
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		
		return descricao;
	}
}
