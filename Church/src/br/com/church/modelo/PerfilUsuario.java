package br.com.church.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class PerfilUsuario extends EntidadeDominio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	@Column
	private Integer user_id;
	@Column
	private Integer profile_id;
	
	public PerfilUsuario(Integer user_id, Integer profile_id) {
		this.user_id = user_id;
		this.profile_id = profile_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(Integer profile_id) {
		this.profile_id = profile_id;
	}
	
	
}
