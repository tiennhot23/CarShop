package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Orders")
public class Orders {
	@Id
	@GeneratedValue
	private int id;
	@Generated( value = GenerationTime.ALWAYS )
	private String oid;
	private String customer;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date datebuy;
	private int stat;
	private String email;
	private String phone;
	private String addres;
	private int amount;
	private long total;
	
	@ManyToOne
	@JoinColumn(name="carId")
	private Cars car;
	
	public Orders() {
	}
	@OneToMany(mappedBy="order", fetch=FetchType.EAGER)
	private Collection<Securities> securities;
	
	public Collection<Securities> getSecurities() {
		return securities;
	}

	public void setSecurities(Collection<Securities> securities) {
		this.securities = securities;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public  String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getDatebuy() {
		return datebuy;
	}

	public void setDatebuy(Date datebuy) {
		this.datebuy = datebuy;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}
	
	
}
