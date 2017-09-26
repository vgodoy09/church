package br.com.church.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import br.com.church.modelo.enuns.Sexo;
import br.com.church.modelo.enuns.Status;
import static br.com.church.util.CheckInstanceObject.*;
import br.com.church.util.UtilsDate;


@Entity
@Table(name = "user")
public class Usuario extends EntidadeDominio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id_usuario;
	@Column
	private String nome;
	@Column
	private String login;
	@Column
	private String senha;
//	@Enumerated(EnumType.STRING)
//    private Perfil perfil;
    @Enumerated(EnumType.STRING)
    private Status status;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Calendar dataNascimento;
	@Enumerated(EnumType.STRING)
    private Sexo sexo;
//	@Enumerated(EnumType.STRING)
//	private EstadoCivil estadoCivil;
	@Column
	private Integer idPais;
	@Column
	private Integer idEstado;
	@Column
	private Integer idCidade;
	private String endereco;
	private Integer numero;
	
	private transient String nom_pais;
	private transient String nom_estado;
	private transient String nom_cidade;
	private transient String enderecoCompleto;
	private transient String dataFormatada;
	
//    @ONETOMANY(MAPPEDBY = "USUARIO", TARGETENTITY = LOGUSUARIO.CLASS, FETCH = FETCHTYPE.LAZY, CASCADE = CASCADETYPE.ALL)
//    Private List<LogUsuario> logusuario;
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	public String getNom_pais() {
		return nom_pais;
	}
	public void setNom_pais(String nom_pais) {
		this.nom_pais = nom_pais;
	}
	public String getNom_estado() {
		return nom_estado;
	}
	public void setNom_estado(String nom_estado) {
		this.nom_estado = nom_estado;
	}
	public String getNom_cidade() {
		return nom_cidade;
	}
	public void setNom_cidade(String nom_cidade) {
		this.nom_cidade = nom_cidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getEnderecoCompleto() {
		enderecoCompleto = endereco + " N° " + numero;
		return enderecoCompleto;
	}
	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	public String getDataFormatada() {
		dataFormatada = UtilsDate.dateFormats(!IsNull(dataNascimento) ? null : dataNascimento.getTime(), "dd/MM/yyyy");
		return dataFormatada;
	}
	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}
}
