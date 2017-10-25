package br.com.church.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.church.facade.FacadeEstado;

@Entity
@Table(name = "pais")
public class Pais extends EntidadeDominio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	@Column
	private String nome;
	@Column
	private String sigla;
	private transient List<Estado> listEstados;
	private transient FacadeEstado fe = new FacadeEstado();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Estado> getListEstados() {
		return listEstados;
	}
	public void setListEstados(List<Estado> listEstados) {
		this.listEstados = listEstados;
	}
	
}
