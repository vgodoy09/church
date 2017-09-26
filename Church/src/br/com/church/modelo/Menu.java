package br.com.church.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu extends EntidadeDominio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	@Column
	private String link;
	@Column
	private String name;
	@Column
	private Integer fathermenu_id;
	@Column
	private Integer application_id;
	@Column
	private Integer element_id;
	@Column
	private Integer ordination;
	@Column
	private String image;
	@Column
	private Boolean active;
	private transient List<Menu> listmenu;
	private transient boolean ischeck;
	private transient String check;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFathermenu_id() {
		return fathermenu_id;
	}
	public void setFathermenu_id(Integer fathermenu_id) {
		this.fathermenu_id = fathermenu_id;
	}
	public Integer getOrdination() {
		return ordination;
	}
	public void setOrdination(Integer ordination) {
		this.ordination = ordination;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}
	public Integer getElement_id() {
		return element_id;
	}
	public void setElement_id(Integer element_id) {
		this.element_id = element_id;
	}
	
	public List<Menu> getListmenu() {
		return listmenu;
	}
	public void setListmenu(List<Menu> listmenu) {
		this.listmenu = listmenu;
	}
	public boolean isIscheck() {
		return ischeck;
	}
	public void setIscheck(boolean ischeck) {
		this.ischeck = ischeck;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", link=" + link + ", name=" + name
				+ ", fathermenu_id=" + fathermenu_id + ", ordination="
				+ ordination + ", image=" + image + ", active=" + active + "]";
	}
}
