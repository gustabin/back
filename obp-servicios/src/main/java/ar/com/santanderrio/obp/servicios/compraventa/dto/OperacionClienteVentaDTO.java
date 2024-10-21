/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dto;

/**
 * The Class OperacionClienteVentaDTO.
 *
 * @author sabrina.cis
 */
public class OperacionClienteVentaDTO {

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

	/**
	 * Instantiates a new operacion cliente compra DTO.
	 */
	public OperacionClienteVentaDTO() {
		super();
	}

	/**
	 * Instantiates a new operacion cliente compra DTO.
	 *
	 * @param codError
	 *            the cod error
	 */
	public OperacionClienteVentaDTO(Integer codError) {
		super();
		this.setCodError(codError);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperacionClienteVentaDTO [cuentadebito=" + cuentadebito + ", divisacuentadebito=" + divisacuentadebito
				+ ", importedebitar=" + importedebitar + ", niodebito=" + niodebito + ", cuentacredito=" + cuentacredito
				+ ", divisacuentacredito=" + divisacuentacredito + ", importeacreditar=" + importeacreditar
				+ ", niocredito=" + niocredito + ", importecotizacion=" + importecotizacion + ", spredaplicado="
				+ spredaplicado + ", nroOperacion=" + nroOperacion + ", fecha=" + fecha + ", hora=" + hora
				+ ", nroBoleto=" + nroBoleto + ", codError=" + codError + "]";
	}

}
