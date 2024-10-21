/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class AbstractDeuda.
 */
public abstract class AbstractDeudaEntity extends Entity implements Comparable<AbstractDeudaEntity> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The empresa. */
	private String empresa = "";

	/** The id cliente empresa. */
	private String idClienteEmpresa = "";

	/** The vencimiento. */
	private Date vencimiento = null;

	/** The divisa. */
	private DivisaEnum divisa = null;

	/** The importe. */
	private BigDecimal importe = null;

	/** The factura. */
	private String factura = "";

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the id cliente empresa.
	 *
	 * @return the id cliente empresa
	 */
	public String getIdClienteEmpresa() {
		return idClienteEmpresa;
	}

	/**
	 * Sets the id cliente empresa.
	 *
	 * @param idClienteEmpresa
	 *            the new id cliente empresa
	 */
	public void setIdClienteEmpresa(String idClienteEmpresa) {
		this.idClienteEmpresa = idClienteEmpresa;
	}

	/**
	 * Gets the vencimiento.
	 *
	 * @return the vencimiento
	 */
	public Date getVencimiento() {
		return vencimiento == null ? null : new Date(vencimiento.getTime());
	}

	/**
	 * Sets the vencimiento.
	 *
	 * @param vencimiento
	 *            the new vencimiento
	 */
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento == null ? null : new Date(vencimiento.getTime());
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public DivisaEnum getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the new divisa
	 */
	public void setDivisa(DivisaEnum divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the factura.
	 *
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * Sets the factura.
	 *
	 * @param factura
	 *            the new factura
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractDeudaEntity otro) {
		// se toma que null es mayor
		if (otro == null) {
			return -1;
		}
		// comienzo
		int resultado = this.getEmpresa().trim().compareToIgnoreCase(otro.getEmpresa().trim());

		// misma empresa, comparo factura
		if (resultado == 0) {
			int facturas;
			int idClienteEmpresa;
			facturas = this.getFactura().trim().compareToIgnoreCase(otro.getFactura().trim());
			idClienteEmpresa = this.getIdClienteEmpresa().trim().compareToIgnoreCase(otro.getIdClienteEmpresa().trim());
			resultado = facturas == 0 && idClienteEmpresa == 0 ? 0 : -1;

		}
		return resultado;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o.getClass().equals(this.getClass()))) {
			return false;
		}
		AbstractDeudaEntity cpmc = AbstractDeudaEntity.class.cast(o);
		return empresa.equals(cpmc.empresa);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (empresa).hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractDeuda [empresa=");
		builder.append(empresa);
		builder.append(", idClienteEmpresa=");
		builder.append(idClienteEmpresa);
		builder.append(", vencimiento=");
		builder.append(vencimiento);
		builder.append(", divisa=");
		builder.append(divisa);
		builder.append(", importe=");
		builder.append(importe);
		builder.append(", factura=");
		builder.append(factura);
		builder.append("]");
		return builder.toString();
	}

}
