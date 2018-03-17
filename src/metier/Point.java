package metier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TODO.
 * @author dcattaru
 */
@Entity
@Table(name = "POINT")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="POINTTYPE",
	discriminatorType = DiscriminatorType.INTEGER)
@NamedQueries({
		@NamedQuery(name = "Point.findAll",
			query = "SELECT p FROM Point p"),
		@NamedQuery(name = "Point.findById",
			query = "SELECT p FROM Point p WHERE p.id = :id"),
		@NamedQuery(name = "Point.findByPointtype",
			query = "SELECT p FROM Point p WHERE p.pointtype = :pointtype"),
		@NamedQuery(name = "Point.findByX",
			query = "SELECT p FROM Point p WHERE p.x = :x"),
		@NamedQuery(name = "Point.findByY",
			query = "SELECT p FROM Point p WHERE p.y = :y")
})
public abstract  class Point implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "POINTTYPE")
	private Integer pointtype;
	@Basic(optional = false)
	@Column(name = "X")
	//CHECKSTYLE:OFF: MemberNameCheck
	private double x;
	//CHECKSTYLE:ON
	@Basic(optional = false)
	@Column(name = "Y")
	//CHECKSTYLE:OFF: MemberNameCheck
	private double y;
	//CHECKSTYLE:ON
	@JoinColumn(name = "NINSTANCE", referencedColumnName = "ID")
	@ManyToOne
	private Instance instance;
	@MapKey(name = "narrivee")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ndepart")
	private Map<Point, Route> routes;

	/**
	 * TODO.
	 */
	public Point() {
		this.routes = new HashMap<>();
		this.x = 0;
		this.y = 0;
		this.id = -1;
	}

	/**
	 * TODO.
	 * @param x
	 * @param y 
	 */
	public Point(double x, double y) {
		this.routes = new HashMap<>();
		this.x = x;
		this.y = y;
	}

	/**
	 * TODO.
	 * @param id
	 * @param x
	 * @param y 
	 */
	public Point(int id, double x, double y) {
		this();
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * TODO.
	 * @param p
	 * @param distance
	 * @return 
	 */
	public boolean addDestination(Point p, double distance) {
		if (p == null) {
			return false;
		}
		if (this.routes.containsKey(p)) {
			return false;
		}
		Route r = new Route(this, p, distance);
		this.routes.put(p, r);
		return true;
	}

	public Integer getId() {
		return id;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * TODO.
	 * @param p
	 * @return 
	 */
	public double getDistanceTo(Point p) {
		if (this.routes.containsKey(p)) {
			return this.routes.get(p).getDistance();
		}
		return Double.MAX_VALUE;
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
		if (!(object instanceof Point)) {
			return false;
		}
		Point other = (Point) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "metier.Point[ id=" + id + " ]";
	}

}
