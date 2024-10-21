/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class InformacionAdicional.
 *
 * @author B039636
 */
public class InformacionAdicional extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3938577952546490291L;

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The identificacion. */
	private String identificacion;

	/** The mensaje. */
	private String mensaje;

	/** The accion. */
	private String accion;

	/**
	 * Gets the codigo empresa.
	 *
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa
	 *            the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the identificacion.
	 *
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Sets the identificacion.
	 *
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accion == null) ? 0 : accion.hashCode());
		result = prime * result + ((codigoEmpresa == null) ? 0 : codigoEmpresa.hashCode());
		result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		InformacionAdicional other = (InformacionAdicional) obj;

		EqualsBuilder eb = new EqualsBuilder().append(accion, other.accion).append(codigoEmpresa, other.codigoEmpresa)
				.append(identificacion, other.identificacion).append(identificacion, other.identificacion)
				.append(mensaje, other.mensaje);
		return eb.isEquals();

	}

}
