package br.com.church.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria extends EntidadeDominio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id_categoria;
	@Column
	private String name;
	private transient String description;
	@Column
	private Integer idRaiz;
	@Column 
	private Integer idUsuario;
	private transient String descricao;

	public Categoria(){
		
	}
	public Categoria(int id_categoria, String name, Integer idRaiz){
		this.id_categoria = id_categoria;
		this.name = name;
		this.idRaiz = idRaiz;
	}
	
	
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIdRaiz() {
		return idRaiz;
	}
	public void setIdRaiz(Integer idRaiz) {
		this.idRaiz = idRaiz;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
