package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.device.SmartEnvironment;
/**
 * Rappresenta un client. POJO+JPA annotations (usate per il mapping).
 * Il campo email e' usato come primary key. Per mappare la relativa
 * lista di SmartEnvironment è stato usato il metodo OneToMany
 * unidirezionale.
 */

@Entity
@Table(name="CLIENT")
public class Client {
	private String email;
	private String passw;
	private String hostname;
	private List<SmartEnvironment> environments;
	
	public Client() {
		
	}
	
	public Client(String email, String passw) {
		this.email = email;
		this.passw = passw;
	}
	
	@Id
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}
	
	
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	@OneToMany
	public List<SmartEnvironment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<SmartEnvironment> environments) {
		this.environments = environments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((environments == null) ? 0 : environments.hashCode());
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((passw == null) ? 0 : passw.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (environments == null) {
			if (other.environments != null)
				return false;
		} else if (!environments.equals(other.environments))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (passw == null) {
			if (other.passw != null)
				return false;
		} else if (!passw.equals(other.passw))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", passw=" + passw + ", hostname=" + hostname + ", environments="
				+ environments + "]";
	}
	
	

}
