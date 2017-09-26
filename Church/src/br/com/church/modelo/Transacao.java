package br.com.church.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import br.com.church.modelo.enuns.Status;
import br.com.church.modelo.enuns.TipoTransacao;

@Entity
@Table(name = "transacao")
public class Transacao extends EntidadeDominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transacao")
	private Integer id_transacao;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date registryDate;
	@Column
	private String descricao;
	@Column
	private String nota;
	@Column
	private Integer idCategoria;
	@Column
	private Integer idusuario;
	@Column
	private Double valor;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Transacao() {

	}

	public Transacao(Integer id_transacao, Date registryDate, String descricao,
			String nota, Integer idCategoria, Integer idConta, Double valor,
			Integer idLembrete, TipoTransacao tipoTransacao, Status status) {

	}

	public Integer getId_transacao() {
		return id_transacao;
	}

	public void setId_transacao(Integer id_transacao) {
		this.id_transacao = id_transacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	
}
