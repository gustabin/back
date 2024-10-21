/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class OperacionClienteVentaEntity.
 *
 * @author sabrina.cis
 */
/**
 * @author florencia.n.martinez
 *
 */
public class OperacionClienteVentaEntity {

	/** The cuentadebito. */
	private String cuentadebito;

	/** The divisacuentadebito. */
	private String divisacuentadebito;

	/** The importedebitar. */
	private String importedebitar;

	/** The niodebito. */
	private String niodebito;

	/** The cuentacredito. */
	private String cuentacredito;

	/** The divisacuentacredito. */
	private String divisacuentacredito;

	/** The importeacreditar. */
	private String importeacreditar;

	/** The niocredito. */
	private String niocredito;

	/** The importecotizacion. */
	private String importecotizacion;

	/** The spredaplicado. */
	private String spredaplicado;

	/** The nroOperacion. */
	private String nroOperacion;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The nro boleto. */
	private String nroBoleto;

	/** The cod error. */
	private Integer codError;

	/** The tiene error. */
	private Boolean tieneError = Boolean.FALSE;

	/** The fecha hora body. */
	private String fechaHoraBody;

	private String mensajeError;
	
	/**
	 * Instantiates a new operacion cliente compra Entity.
	 */
	public OperacionClienteVentaEntity() {
		super();
	}

	/**
	 * Instantiates a new operacion cliente compra Entity.
	 *
	 * @param codError
	 *            the cod error
	 * @param tieneError
	 *            the tiene error
	 */
	public OperacionClienteVentaEntity(Integer codError, Boolean tieneError) {
		super();
		this.setCodError(codError);
		this.setTieneError(tieneError);
	}
	
	/**
	 * Instantiates a new operacion cliente compra Entity.
	 *
	 * @param codError
	 *            the cod error
	 * @param tieneError
	 *            the tiene error
	 */
	public OperacionClienteVentaEntity(Integer codError, Boolean tieneError, String mensajeError) {
		super();
		this.setCodError(codError);
		this.setTieneError(tieneError);
		this.mensajeError = mensajeError;
	}

	/**
	 * Gets the cuentadebito.
	 *
	 * @return the cuentadebito
	 */
	public String getCuentadebito() {
		return cuentadebito;
	}

	/**
	 * Sets the cuentadebito.
	 *
	 * @param cuentadebito
	 *            the cuentadebito to set
	 */
	public void setCuentadebito(String cuentadebito) {
		this.cuentadebito = cuentadebito;
	}

	/**
	 * Gets the divisacuentadebito.
	 *
	 * @return the divisacuentadebito
	 */
	public String getDivisacuentadebito() {
		return divisacuentadebito;
	}

	/**
	 * Sets the divisacuentadebito.
	 *
	 * @param divisacuentadebito
	 *            the divisacuentadebito to set
	 */
	public void setDivisacuentadebito(String divisacuentadebito) {
		this.divisacuentadebito = divisacuentadebito;
	}

	/**
	 * Gets the importedebitar.
	 *
	 * @return the importedebitar
	 */
	public String getImportedebitar() {
		return importedebitar;
	}

	/**
	 * Sets the importedebitar.
	 *
	 * @param importedebitar
	 *            the importedebitar to set
	 */
	public void setImportedebitar(String importedebitar) {
		this.importedebitar = importedebitar;
	}

	/**
	 * Gets the niodebito.
	 *
	 * @return the niodebito
	 */
	public String getNiodebito() {
		return niodebito;
	}

	/**
	 * Sets the niodebito.
	 *
	 * @param niodebito
	 *            the niodebito to set
	 */
	public void setNiodebito(String niodebito) {
		this.niodebito = niodebito;
	}

	/**
	 * Gets the cuentacredito.
	 *
	 * @return the cuentacredito
	 */
	public String getCuentacredito() {
		return cuentacredito;
	}

	/**
	 * Sets the cuentacredito.
	 *
	 * @param cuentacredito
	 *            the cuentacredito to set
	 */
	public void setCuentacredito(String cuentacredito) {
		this.cuentacredito = cuentacredito;
	}

	/**
	 * Gets the divisacuentacredito.
	 *
	 * @return the divisacuentacredito
	 */
	public String getDivisacuentacredito() {
		return divisacuentacredito;
	}

	/**
	 * Sets the divisacuentacredito.
	 *
	 * @param divisacuentacredito
	 *            the divisacuentacredito to set
	 */
	public void setDivisacuentacredito(String divisacuentacredito) {
		this.divisacuentacredito = divisacuentacredito;
	}

	/**
	 * Gets the importeacreditar.
	 *
	 * @return the importeacreditar
	 */
	public String getImporteacreditar() {
		return importeacreditar;
	}

	/**
	 * Sets the importeacreditar.
	 *
	 * @param importeacreditar
	 *            the importeacreditar to set
	 */
	public void setImporteacreditar(String importeacreditar) {
		this.importeacreditar = importeacreditar;
	}

	/**
	 * Gets the niocredito.
	 *
	 * @return the niocredito
	 */
	public String getNiocredito() {
		return niocredito;
	}

	/**
	 * Sets the niocredito.
	 *
	 * @param niocredito
	 *            the niocredito to set
	 */
	public void setNiocredito(String niocredito) {
		this.niocredito = niocredito;
	}

	/**
	 * Gets the importecotizacion.
	 *
	 * @return the importecotizacion
	 */
	public String getImportecotizacion() {
		return importecotizacion;
	}

	/**
	 * Sets the importecotizacion.
	 *
	 * @param importecotizacion
	 *            the importecotizacion to set
	 */
	public void setImportecotizacion(String importecotizacion) {
		this.importecotizacion = importecotizacion;
	}

	/**
	 * Gets the spredaplicado.
	 *
	 * @return the spredaplicado
	 */
	public String getSpredaplicado() {
		return spredaplicado;
	}

	/**
	 * Sets the spredaplicado.
	 *
	 * @param spredaplicado
	 *            the spredaplicado to set
	 */
	public void setSpredaplicado(String spredaplicado) {
		this.spredaplicado = spredaplicado;
	}

	/**
	 * Gets the nro operacion.
	 *
	 * @return the nro operacion
	 */
	public String getNroOperacion() {
		return nroOperacion;
	}

	/**
	 * Sets the nro operacion.
	 *
	 * @param nroOperacion
	 *            the new nro operacion
	 */
	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
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
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 *
	 * @param hora
	 *            the new hora
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Gets the nro boleto.
	 *
	 * @return the nro boleto
	 */
	public String getNroBoleto() {
		return nroBoleto;
	}

	/**
	 * Sets the nro boleto.
	 *
	 * @param nroBoleto
	 *            the new nro boleto
	 */
	public void setNroBoleto(String nroBoleto) {
		this.nroBoleto = nroBoleto;
	}

	/**
	 * Gets the cod error.
	 *
	 * @return the cod error
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * Sets the cod error.
	 *
	 * @param codError
	 *            the new cod error
	 */
	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	/**
	 * Gets the tiene error.
	 *
	 * @return the tiene error
	 */
	public Boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Sets the tiene error.
	 *
	 * @param tieneError
	 *            the new tiene error
	 */
	public void setTieneError(Boolean tieneError) {
		this.tieneError = tieneError;
	}

	/**
	 * Gets the fecha hora body.
	 *
	 * @return the fecha hora body
	 */
	public String getFechaHoraBody() {
		return fechaHoraBody;
	}

	/**
	 * Sets the fecha hora body.
	 *
	 * @param fechaHoraBody
	 *            the new fecha hora body
	 */
	public void setFechaHoraBody(String fechaHoraBody) {
		this.fechaHoraBody = fechaHoraBody;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuentacredito);
		hcb.append(cuentadebito);
		hcb.append(divisacuentacredito);
		hcb.append(divisacuentadebito);
		hcb.append(fecha);
		hcb.append(hora);
		hcb.append(importeacreditar);
		hcb.append(importecotizacion);
		hcb.append(importedebitar);
		hcb.append(niocredito);
		hcb.append(niodebito);
		hcb.append(nroBoleto);
		hcb.append(nroOperacion);
		hcb.append(spredaplicado);
		hcb.append(codError);
		hcb.append(tieneError);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		OperacionClienteVentaEntity other = (OperacionClienteVentaEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentacredito, other.getCuentacredito());
		eb.append(cuentadebito, other.getCuentadebito());
		eb.append(divisacuentacredito, other.getDivisacuentacredito());
		eb.append(divisacuentadebito, other.getDivisacuentadebito());
		eb.append(fecha, other.getFecha());
		eb.append(hora, other.getHora());
		eb.append(importeacreditar, other.getImporteacreditar());
		eb.append(importecotizacion, other.getImportecotizacion());
		eb.append(importedebitar, other.getImportedebitar());
		eb.append(niocredito, other.getNiocredito());
		eb.append(niodebito, other.getNiodebito());
		eb.append(nroBoleto, other.getNroBoleto());
		eb.append(nroOperacion, other.getNroOperacion());
		eb.append(spredaplicado, other.getSpredaplicado());
		eb.append(tieneError, other.getTieneError());
		eb.append(codError, other.getCodError());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cuentadebito", cuentadebito)
				.append("divisacuentadebito", divisacuentadebito).append("importedebitar", importedebitar)
				.append("niodebito", niodebito).append("cuentacredito", cuentacredito)
				.append("divisacuentacredito", divisacuentacredito).append("importeacreditar", importeacreditar)
				.append("niocredito", niocredito).append("importecotizacion", importecotizacion)
				.append("spredaplicado", spredaplicado).append("nroOperacion", nroOperacion).append("fecha", fecha)
				.append("hora", hora).append("nroBoleto", nroBoleto).append("tieneError", tieneError)
				.append("codError", codError).toString();
	}

}