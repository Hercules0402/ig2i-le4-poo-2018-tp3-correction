package metier;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "DEPOT")
@DiscriminatorValue("1")
@NamedQueries({
	@NamedQuery(name = "Depot.findAll", query = "SELECT d FROM Depot d"),
	@NamedQuery(name = "Depot.findById", query = "SELECT d FROM Depot d WHERE d.id = :id")}
)
public class Depot extends Point implements Serializable {

	public Depot() {
		super(-1, 0, 0);
	}

	public Depot(int id, double x, double y) {
		super(id, x, y);
	}

	public Depot(double x, double y) {
		super(x, y);
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (getId() != null ? getId().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Depot)) {
			return false;
		}
		Depot other = (Depot) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "metier.Depot[ id=" + getId() + " ]";
	}

}
