package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "VEHICULE")
@NamedQueries({
		@NamedQuery(name = "Vehicule.findAll",
			query = "SELECT v FROM Vehicule v"),
		@NamedQuery(name = "Vehicule.findById",
			query = "SELECT v FROM Vehicule v WHERE v.id = :id"),
		@NamedQuery(name = "Vehicule.findByCout",
			query = "SELECT v FROM Vehicule v WHERE v.cout = :cout"),
		@NamedQuery(name = "Vehicule.findByCapaciteutilisee",
			query = "SELECT v FROM Vehicule v WHERE v.capaciteutilisee = :capaciteutilisee"),
		@NamedQuery(name = "Vehicule.findByCapacite",
			query = "SELECT v FROM Vehicule v WHERE v.capacite = :capacite"),
		@NamedQuery(name = "Vehicle.findNotUsed",
			query = "SELECT v FROM Vehicule v WHERE v.nplanning = :null")
})
public class Vehicule implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "COUT")
	private Double cout;
	@Column(name = "CAPACITEUTILISEE")
	private Integer capaciteutilisee;
	@Column(name = "CAPACITE")
	private Integer capacite;
	@OneToMany(mappedBy = "nvehicule")
	@OrderBy("position ASC")
	private List<Client> clientList;
	@JoinColumn(name = "NDEPOT", referencedColumnName = "ID")
	@ManyToOne
	private Depot ndepot;
	@JoinColumn(name = "NPLANNING", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Planning nplanning;
	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@ManyToOne
	private Instance instance;

	/**
	 * TODO.
	 */
	public Vehicule() {
		this.id = -1;
		this.clientList = new ArrayList<>();
		this.ndepot = null;
		this.cout = 0.0;
		this.capaciteutilisee = 0;
		this.capacite = 0;
	}

	/**
	 * TODO.
	 * @param depot TODO.
	 * @param capacite TODO.
	 */
	public Vehicule(Depot depot, int capacite) {
		this.clientList = new ArrayList<>();
		this.ndepot = depot;
		this.cout = 0.0;
		this.capaciteutilisee = 0;
		this.capacite = capacite;
	}

	/**
	 * TODO.
	 * @param id TODO.
	 * @param depot TODO.
	 * @param capacite TODO.
	 */
	public Vehicule(int id, Depot depot, int capacite) {
		this.id = id;
		this.clientList = new ArrayList<>();
		this.ndepot = depot;
		this.cout = 0.0;
		this.capaciteutilisee = 0;
		this.capacite = capacite;
	}

	protected void clear() {
		this.capaciteutilisee = 0;
		this.cout = 0.0;
		this.clientList.clear();
		this.nplanning = null;
	}

	/**
	 * TODO.
	 * @param c TODO.
	 * @return 
	 */
	public boolean addClient(Client c) {
		if (c == null) {
			return false;
		}
		if (this.capaciteutilisee + c.getDemand() > this.capacite) {
			return false;
		}
		if (!c.setVehicule(this)) {
			return false;
		}
		double deltaCost = calculateDeltaCost(c);
		this.clientList.add(c);
		this.capaciteutilisee += c.getDemand();
		this.cout += deltaCost;
		this.nplanning.updateCout(deltaCost);
		return true;
	}

	private double calculateDeltaCost(Client c) {
		if (this.clientList.size() == 0) {
			return this.ndepot.getDistanceTo(c) + c.getDistanceTo(ndepot);
		}
		return clientList.get(clientList.size() - 1).getDistanceTo(c)
				+ c.getDistanceTo(ndepot) 
				- clientList.get(clientList.size() - 1).getDistanceTo(ndepot);
	}

	/**
	 * TODO.
	 * @param p TODO.
	 * @return 
	 */
	public boolean setPlanning(Planning p) {
		if (p == null) {
			return false;
		}
		if (this.nplanning != null) {
			return false;
		}
		this.nplanning = p;
		return true;
	}

	/**
	 * TODO.
	 */
	public void updatePositionClients() {
		int pos = 0;
		for (Client c : clientList) {
			c.setPosition(pos);
			pos++;
		}
	}

	public Integer getId() {
		return id;
	}

	public Double getCout() {
		return cout;
	}

	public Integer getCapaciteutilisee() {
		return capaciteutilisee;
	}

	public Integer getCapacite() {
		return capacite;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Vehicule)) {
			return false;
		}
		Vehicule other = (Vehicule) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "metier.Vehicule[ id=" + id + " cost=" + cout 
				+ " capa utilisee=" + capaciteutilisee + "]\n";
		for (Client c : clientList) {
			str += "\tid=" + c.getId() + " pos=" + c.getPosition() + "\n";
		}
		return str;
	}

}
