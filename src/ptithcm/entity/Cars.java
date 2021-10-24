package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Cars")
public class Cars {
	@Id
	private String name;
	private String img;
	private String video;
	private int amount;
	private String disc;
	private long price;
	
	@ManyToOne
	@JoinColumn(name="class")
	private Types type;
	
	@ManyToOne
	@JoinColumn(name="brand")
	private Brands brand;
	
	@OneToMany(mappedBy="car", fetch=FetchType.EAGER)
	private Collection<Orders> orders;
	
	
	public Collection<Orders> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	public Brands getBrand() {
		return brand;
	}
	public void setBrand(Brands brand) {
		this.brand = brand;
	}
	
	
}
