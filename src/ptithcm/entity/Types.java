package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Classs")
public class Types{
	@Id
	private String name;
	private String disc;
	
	@OneToMany(mappedBy="type", fetch=FetchType.EAGER)
	private Collection<Cars> cars;
	
	public Types() {
		
	}
	public Types(String name, String disc) {
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
