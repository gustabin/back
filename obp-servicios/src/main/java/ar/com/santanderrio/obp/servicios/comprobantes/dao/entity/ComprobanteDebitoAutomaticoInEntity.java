/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ComprobanteDebitoAutomaticoInEntity.
 */
public class ComprobanteDebitoAutomaticoInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The empresa. */
	private EmpresaAdheridaEntity empresa;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The fecha continuacion. */
	private String fechaContinuacion;

	/** The ID debito DDI cont. */
	private String iDDebitoDDICont;

	private boolean flujoDDI;

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public EmpresaAdheridaEntity getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa the new empresa
	 */
	public void setEmpresa(EmpresaAdheridaEntity empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the fecha continuacion.
	 *
	 * @return the fecha continuacion
	 */
	public String getFechaContinuacion() {
		return fechaContinuacion;
	}

	/**
	 * Sets the fecha continuacion.
	 *
	 * @param fechaContinuacion the new fecha continuacion
	 */
	public void setFechaContinuacion(String fechaContinuacion) {
		this.fechaContinuacion = fechaContinuacion;
	}

	/**
	 * Gets the ID debito DDI cont.
	 *
	 * @return the ID debito DDI cont
	 */
	public String getIDDebitoDDICont() {
		return iDDebitoDDICont;
	}

	/**
	 * Sets the ID debito DDI cont.
	 *
	 * @param iDDebitoDDICont the new ID debito DDI cont
	 */
	public void setIDDebitoDDICont(String iDDebitoDDICont) {
		this.iDDebitoDDICont = iDDebitoDDICont;
	}

	/**
	 * @return the iDDebitoDDICont
	 */
	public String getiDDebitoDDICont() {
		return iDDebitoDDICont;
	}

	/**
	 * @param iDDebitoDDICont the iDDebitoDDICont to set
	 */
	public void setiDDebitoDDICont(String iDDebitoDDICont) {
		this.iDDebitoDDICont = iDDebitoDDICont;
	}

	/**
	 * @return the flujoDDI
	 */
	public boolean isFlujoDDI() {
		return flujoDDI;
	}

	/**
	 * @param flujoDDI the flujoDDI to set
	 */
	public void setFlujoDDI(boolean flujoDDI) {
		this.flujoDDI = flujoDDI;
	}

	/**
	 * Armar clave para la cache de cnsddidere
	 * 
	 * @return
	 */
	public String obtenerCacheKey() {
		return new StringBuilder(cliente.getNup()).append(empresa.getCuitEmpresa()).append(empresa.getNombreServicioEmpresa())
		        .append(empresa.getNroPartidaServicioEmpresa()).append(fechaDesde).append(fechaHasta)
		        .append(iDDebitoDDICont).append(fechaContinuacion).append(isFlujoDDI()).toString();
	}

	@Override
	public String toString() {
		return "ComprobanteDebitoAutomaticoInEntity [cliente=" + cliente + ", empresa=" + empresa + ", fechaDesde="
		        + fechaDesde + ", fechaHasta=" + fechaHasta + ", fechaContinuacion=" + fechaContinuacion
		        + ", iDDebitoDDICont=" + iDDebitoDDICont + ", flujoDDI=" + flujoDDI + "]";
	}
}
