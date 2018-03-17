package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO.
 * @author Admin
 */
@Entity
@Table(name = "INSTANCE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Instance.findAll", 
			query = "SELECT i FROM Instance i"),
		@NamedQuery(name = "Instance.findById",
			query = "SELECT i FROM Instance i WHERE i.id = :id"),
		@NamedQuery(name = "Instance.findByNom",
			query = "SELECT i FROM Instance i WHERE i.nom = :nom")
})
public class Instance implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "NOM")
	private String nom;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "instance")
	private List<Point> points;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "instance")
	private List<Vehicule> vehicules;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "instance")
	private Planning planning;

	public Instance() {
		this.points = new ArrayList<>();
		this.vehicules = new ArrayList<>();
	}

	public Instance(String nom) {
		this();
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getNbPoints() {
		return this.points.size();
	}

	public double getCost(int i, int j) {
		return this.points.get(i).getDistanceTo(this.points.get(j));
	}

	/**
	 * TODO.
	 */
	public void clear() {
		for (Point p : points) {
			if (p instanceof Client) {
				((Client)p).clear();
			}
		}
		for (Vehicule v : vehicules) {
			v.clear();
		}
		this.planning.clear();
	}

	public int getNbClients() {
		return this.getClients().size();
	}

	/**
	 * TODO.
	 * @return 
	 */
	public Depot getDepot() {
		for (Point p : points) {
			if (p instanceof Depot) {
				return (Depot)p;
			}
		}
		return null;
	}

	/**
	 * TODO.
	 * @return 
	 */
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<>();
		for (Point p : points) {
			if (p instanceof Client) {
				clients.add((Client)p);
			}
		}
		return clients;
	}

	public List<Vehicule> getVehicules() {
		return new ArrayList<>(vehicules);
	}

	public Planning getPlanning() {
		return this.planning;
	}

	public void addVehiculeInPlanning(Vehicule v) {
		this.planning.addVehicule(v);
	}

	public void updatePositions() {
		this.planning.updatePositionClients();
	}

	public double getCoutPlanning() {
		return this.planning.getCout();
	}

	public int getNbVehiculesPlannning() {
		return this.planning.getNbVehicules();
	}

	public void printPlanning() {
		System.out.println(this.planning);
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
		if (!(object instanceof Instance)) {
			return false;
		}
		Instance other = (Instance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "Instance : id = " + id;
		s += "\nPoints : ";
		for (Point p : points) {
			s += "\n\t" + p;
		}
		s += "\nVehicules : ";
		for (Vehicule v : vehicules) {
			s += "\n\t" + v;
		}
		return s;
	}

}
