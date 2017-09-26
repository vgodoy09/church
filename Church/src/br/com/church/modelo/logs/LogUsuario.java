package br.com.church.modelo.logs;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import br.com.church.modelo.EntidadeDominio;
import br.com.church.modelo.Usuario;

@Entity
@Table(name = "logusuario")
public class LogUsuario extends EntidadeDominio{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_logusuario")
    private int id_logusuario;
    //@Temporal(javax.persistence.TemporalType.DATE)
    private String horadeentrada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataentrada;
    //@Temporal(javax.persistence.TemporalType.DATE)
    private String horadesaida;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar datasaida;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
	public int getId_logusuario() {
		return id_logusuario;
	}
	public void setId_logusuario(int id_logusuario) {
		this.id_logusuario = id_logusuario;
	}
	public String getHoradeentrada() {
		return horadeentrada;
	}
	public void setHoradeentrada(String horadeentrada) {
		this.horadeentrada = horadeentrada;
	}
	public Calendar getDataentrada() {
		return dataentrada;
	}
	public void setDataentrada(Calendar dataentrada) {
		this.dataentrada = dataentrada;
	}
	public String getHoradesaida() {
		return horadesaida;
	}
	public void setHoradesaida(String horadesaida) {
		this.horadesaida = horadesaida;
	}
	public Calendar getDatasaida() {
		return datasaida;
	}
	public void setDatasaida(Calendar datasaida) {
		this.datasaida = datasaida;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    

}
