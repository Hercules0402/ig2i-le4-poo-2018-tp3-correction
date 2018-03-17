package metier;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "ROUTE")
@NamedQueries({
		@NamedQuery(name = "Route.findAll",
			query = "SELECT r FROM Route r"),
		@NamedQuery(name = "Route.findById",
			query = "SELECT r FROM Route r WHERE r.id = :id"),
		@NamedQuery(name = "Route.findByDistance",
			query = "SELECT r FROM Route r WHERE r.distance = :distance")
})
public class Route implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "DISTANCE")
	private Double distance;
	@JoinColumn(name = "NARRIVEE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Point narrivee;
	@JoinColumn(name = "NDEPART", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Point ndepart;

	/**
	 * TODO.
	 */
	public Route() {
		this.ndepart = null;
		this.narrivee = null;
		this.distance = Double.MAX_VALUE;
	}

	/**
	 * TODO.
	 * @param depart
	 * @param arrivee
	 * @param distance 
	 */
	public Route(Point depart, Point arrivee, double distance) {
		this.ndepart = depart;
		this.narrivee = arrivee;
		this.distance = distance;
	}

	public Integer getId() {
		return id;
	}

	public Double getDistance() {
		return distance;
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
		if (!(object instanceof Route)) {
			return false;
		}
		Route other = (Route) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "metier.Route[ id=" + id + " ]";
	}

}
