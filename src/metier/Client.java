package metier;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "CLIENT")
@DiscriminatorValue("2")
@NamedQueries({
		@NamedQuery(name = "Client.findAll",
			query = "SELECT c FROM Client c"),
		@NamedQuery(name = "Client.findById",
			query = "SELECT c FROM Client c WHERE c.id = :id"),
		@NamedQuery(name = "Client.findByDemand",
			query = "SELECT c FROM Client c WHERE c.demand = :demand"),
		@NamedQuery(name = "Client.findByPosition",
			query = "SELECT c FROM Client c WHERE c.position = :position"),
		@NamedQuery(name = "Client.findNotServed",
			query = "SELECT c FROM Client c WHERE c.nvehicule = :null")
})
public class Client extends Point implements Serializable {

	@Basic(optional = false)
	@Column(name = "DEMAND")
	private int demand;
	@Column(name = "POSITION")
	private Integer position;
	@JoinColumn(name = "NVEHICULE", referencedColumnName = "ID")
	@ManyToOne
	private Vehicule nvehicule;

	public Client() {
		super(-1, 0, 0);
		this.demand = 0;
	}

	public Client(double x, double y, Integer demande) {
		super(x, y);
		this.demand = demande;
	}

	public Client(int id, double x, double y, Integer demande) {
		super(id, x, y);
		this.demand = demande;
	}

	protected void clear() {
		this.nvehicule = null;
		this.position = -1;
	}

	/**
	 * TODO.
	 * @param v
	 * @return 
	 */
	public boolean setVehicule(Vehicule v) {
		if (v == null) {
			return false;
		}
		if (this.nvehicule != null) {
			return false;
		}
		this.nvehicule = v;
		return true;
	}

	public int getDemand() {
		return demand;
	}

	public int getPosition() {
		return position;
	}

	/**
	 * TODO.
	 * @param pos 
	 */
	public void setPosition(int pos) {
		if (pos < 0) {
			return;
		}
		this.position = pos;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (super.getId() != null ? super.getId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		if ((super.getId() == null && other.getId() != null) || 
				(this.getId() != null && !this.getId().equals(other.getId()))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "metier.Client[ id=" + getId() + " ]";
	}

}
