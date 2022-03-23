package business;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="children") // child != classname -> define name
public class Child {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="address_id", nullable=false)
	private Address address;
	
	@OneToMany(mappedBy="child", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Gift> gifts;
	

	public Child() {
		this.id = -1;
		this.name = "";
		this.address = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public Set<Gift> getGifts() {
		if (this.gifts == null) {
			this.gifts = new HashSet<Gift>();
		}
		return gifts;
	}

	public void setGifts(Set<Gift> gifts) {
		this.gifts = gifts;
	}
	
	public void addGift(Gift g) {
		g.setChild(this);
		this.getGifts().add(g);
	}
	
	public void removeGift(Gift g) {
		if (this.getGifts().contains(g)) {
			g.setChild(null);
			this.getGifts().remove(g);
		}
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Child other = (Child) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
