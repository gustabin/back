/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.view;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GestionView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GestionView {

	/** The fecha. */
	private String fecha;

	/** The tipo. */
	private String tipo;

	/** The sucursal. */
	private String sucursal;

	/** The direccion sucursal. */
	private String direccionSucursal;

	/** The ejecutivo. */
	private String ejecutivo;

	/** The fraccion. */
	private String fraccionHoraria;

	/** The Constant TURNO_PLATAFORMA. */
	public final static String TURNO_PLATAFORMA = "turnoPlataforma";

	/** The Constant TURNO_CAJA. */
	public final static String TURNO_CAJA = "turnoCaja";

	/**
	 * Instantiates a new gestion view.
	 */
	public GestionView() {
		super();
	}

	/**
	 * Instantiates a new gestion view.
	 *
	 * @param tipo
	 *            the tipo
	 */
	public GestionView(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the direccion sucursal.
	 *
	 * @return the direccion sucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Sets the direccion sucursal.
	 *
	 * @param direccionSucursal
	 *            the new direccion sucursal
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	/**
	 * Gets the ejecutivo.
	 *
	 * @return the ejecutivo
	 */
	public String getEjecutivo() {
		return ejecutivo;
	}

	/**
	 * Sets the ejecutivo.
	 *
	 * @param ejecutivo
	 *            the new ejecutivo
	 */
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	/**
	 * Gets the fraccion.
	 *
	 * @return the fraccion
	 */
	public String getFraccionHoraria() {
		return fraccionHoraria;
	}

	/**
	 * Sets the fraccion.
	 *
	 * @param fraccionHorario
	 *            the new fraccion
	 */
	public void setFraccionHoraria(String fraccionHorario) {
		this.fraccionHoraria = fraccionHorario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hC = new HashCodeBuilder();
		hC.append(direccionSucursal);
		hC.append(ejecutivo);
		hC.append(fecha);
		hC.append(fraccionHoraria);
		hC.append(sucursal);
		hC.append(tipo);

		return hC.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		EqualsBuilder eB = new EqualsBuilder();
		GestionView other = (GestionView) obj;
		eB.append(direccionSucursal, other.getDireccionSucursal());
		eB.append(ejecutivo, other.getEjecutivo());
		eB.append(fecha, other.getFecha());
		eB.append(fraccionHoraria, other.getFraccionHoraria());
		eB.append(sucursal, other.getSucursal());
		eB.append(tipo, other.getTipo());

		return eB.build();
	}

}
