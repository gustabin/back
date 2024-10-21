/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class ReporteSeleccionado.
 */
public class ReporteSeleccionado {

	/** The fecha puntual. */
	private String fechaPuntual;

	/** The folder. */
	private String folder;

	/** The proveedor tarjeta. */
	private String proveedorTarjeta;

	/** The usuario consulta. */
	private String usuarioConsulta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The numero cuenta. */
	private Integer id;

	/** The doc id. */
	private String docId;

	/**
	 * Gets the fecha puntual.
	 *
	 * @return the fecha puntual
	 */
	public String getFechaPuntual() {
		return fechaPuntual;
	}

	/**
	 * Sets the fecha puntual.
	 *
	 * @param fechaPuntual
	 *            the new fecha puntual
	 */
	public void setFechaPuntual(String fechaPuntual) {
		this.fechaPuntual = fechaPuntual;
	}

	/**
	 * Gets the folder.
	 *
	 * @return the folder
	 */
	public String getFolder() {
		return folder;
	}

	/**
	 * Sets the folder.
	 *
	 * @param folder
	 *            the new folder
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}

	/**
	 * Gets the proveedor tarjeta.
	 *
	 * @return the proveedor tarjeta
	 */
	public String getProveedorTarjeta() {
		return proveedorTarjeta;
	}

	/**
	 * Sets the proveedor tarjeta.
	 *
	 * @param proveedorTarjeta
	 *            the new proveedor tarjeta
	 */
	public void setProveedorTarjeta(String proveedorTarjeta) {
		this.proveedorTarjeta = proveedorTarjeta;
	}

	/**
	 * Gets the usuario consulta.
	 *
	 * @return the usuario consulta
	 */
	public String getUsuarioConsulta() {
		return usuarioConsulta;
	}

	/**
	 * Sets the usuario consulta.
	 *
	 * @param usuarioConsulta
	 *            the new usuario consulta
	 */
	public void setUsuarioConsulta(String usuarioConsulta) {
		this.usuarioConsulta = usuarioConsulta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechaPuntual);
		hcb.append(folder);
		hcb.append(proveedorTarjeta);
		hcb.append(usuarioConsulta);
		hcb.append(numeroCuenta);
		hcb.append(id);
		return hcb.toHashCode();
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
		ReporteSeleccionado other = (ReporteSeleccionado) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(fechaPuntual, other.getFechaPuntual());
		eb.append(folder, other.getFolder());
		eb.append(proveedorTarjeta, other.getProveedorTarjeta());
		eb.append(usuarioConsulta, other.getUsuarioConsulta());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		eb.append(id, other.getId());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReporteSeleccionado [fechaPuntual=" + fechaPuntual + ", folder=" + folder + ", proveedorTarjeta="
				+ proveedorTarjeta + ", usuarioConsulta=" + usuarioConsulta + ", numeroCuenta=" + numeroCuenta + ", id="
				+ id + "]";
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the doc id.
	 *
	 * @return the doc id
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * Sets the doc id.
	 *
	 * @param docId
	 *            the new doc id
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

}