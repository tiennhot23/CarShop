package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Brands")
public class Brands {
	@Id
	private String name;
	private String disc;
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@OneToMany(mappedBy="brand", fetch=FetchType.EAGER)
	private Collection<Cars> cars;
	
	public Brands() {
		
	}
	public Brands(String name, String disc) {
		super();
		this.name = name;
		this.disc = disc;
	}
	public Collection<Cars> getCars() {
		return cars;
	}
	public void setCars(Collection<Cars> cars) {
		this.cars = cars;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	
	
}
