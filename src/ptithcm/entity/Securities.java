package ptithcm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Securities")
public class Securities {
	@Id
	private String token;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date expired;
	@ManyToOne
	@JoinColumn(name="orderId")
	private Orders order;
	
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	
	
}
