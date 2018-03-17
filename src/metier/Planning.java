package metier;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "PLANNING")
@NamedQueries({
	@NamedQuery(name = "Planning.findAll", query = "SELECT p FROM Planning p"),
	@NamedQuery(name = "Planning.findById", query = "SELECT p FROM Planning p WHERE p.id = :id"),
	@NamedQuery(name = "Planning.findByCout", query = "SELECT p FROM Planning p WHERE p.cout = :cout")}
)
public class Planning implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "COUT")
	private Double cout;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "nplanning")
	private Set<Vehicule> vehiculeSet;
	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@OneToOne
	private Instance instance;

	public Planning() {
		this.vehiculeSet = new HashSet<>();
		this.cout = 0.0;
	}

	protected void clear(){
		this.vehiculeSet.clear();
		this.cout = 0.0;
	}

	public boolean addVehicule(Vehicule v){
		if (v == null) {
			return false;
		}
		if (this.vehiculeSet.contains(v)) {
			return false;
		}
		if (!v.setPlanning(this)) {
			return false;
		}
		this.vehiculeSet.add(v);
		return true;
	}

	public int getNbVehicules () {
		return this.vehiculeSet.size();
	}

	public void updatePositionClients(){
		for (Vehicule v : vehiculeSet) {
			v.updatePositionClients();
		}
	}

	public void updateCout(double deltaCout){
		this.cout += deltaCout;
	}

	public Integer getId() {
		return id;
	}

	public Double getCout() {
		return cout;
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
		if (!(object instanceof Planning)) {
			return false;
		}
		Planning other = (Planning) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		for (Vehicule v : vehiculeSet) {
			str += v.toString();
		}
		return str;
	}

}
