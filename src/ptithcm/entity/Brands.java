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
	
	@OneToMany(mappedBy="brand", fetch=FetchType.EAGER)
	private Collection<Cars> cars;
	
	
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
