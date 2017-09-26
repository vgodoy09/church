package br.com.church.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "church")
public class Igreja extends EntidadeDominio implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	@Column
	private String name;
	@Column
	private String logo;
	@Column
	private Date dateBirth;
	@Column
	private Integer pais_id;
	@Column 
	private Integer estado_id;
	@Column 
	private Integer cidade_id;
	@Column
	private String endereco;
	@Column
	private Integer number;
	private transient String check;
	private transient String paisNome;
	private transient String estadoNome;
	private transient String cidadeNome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public Integer getPais_id() {
		return pais_id;
	}
	public void setPais_id(Integer pais_id) {
		this.pais_id = pais_id;
	}
	public Integer getEstado_id() {
		return estado_id;
	}
	public void setEstado_id(Integer estado_id) {
		this.estado_id = estado_id;
	}
	public Integer getCidade_id() {
		return cidade_id;
	}
	public void setCidade_id(Integer cidade_id) {
		this.cidade_id = cidade_id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getPaisNome() {
		return paisNome;
	}
	public void setPaisNome(String paisNome) {
		this.paisNome = paisNome;
	}
	public String getEstadoNome() {
		return estadoNome;
	}
	public void setEstadoNome(String estadoNome) {
		this.estadoNome = estadoNome;
	}
	public String getCidadeNome() {
		return cidadeNome;
	}
	public void setCidadeNome(String cidadeNome) {
		this.cidadeNome = cidadeNome;
	}
}
