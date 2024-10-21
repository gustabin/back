/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Disclaimer;


/**
 * The Class ConfirmacionVentaTitulosView.
 */
public class ConfirmacionVentaTitulosView {

	/** The permite operar. */
	private Boolean permiteOperar;
	
	/** The mostrar ERI. */
	private Boolean mostrarERI;
	
	/** The legales ERI. */
	private List<Disclaimer> legalesERI;
	
	/** The comisiones. */
	private String comisiones;
	
	/** The iva sobre comision. */
	private String ivaSobreComision;
	
	/** The derecho de mercado. */
	private String derechoDeMercado;
	
	/** The iva sobre derecho de mercado. */
	private String ivaSobreDerechoDeMercado;
	
	/** The legales. */
	private String legales;

	/** The poder de compra. */
	private Boolean poderDeCompra = false;
	
	/** The numero orden. */
	private String numeroOrden;
	
	/** The numero comprobante. */
	private String numeroComprobante;
	
	/** The fecha hora operacion. */
	private String fechaHoraOperacion;
	
	/** The fecha liquidacion formateada. */
	private String fechaLiquidacionFormateada;
	
	/** The mensaje OK. */
	private String mensajeOK;
	
	private String bonificacion;
	
	private String comisionOriginal;
	
	private String comision;
	
	private String informacion;
	
	private boolean tieneBonificacion;
	
	/**
	 * Gets the legales ERI.
	 *
	 * @return the legales ERI
	 */
	public List<Disclaimer> getLegalesERI() {
		return legalesERI;
	}

	/**
	 * Sets the legales ERI.
	 *
	 * @param legalesERI
	 *            the new legales ERI
	 */
	public void setLegalesERI(List<Disclaimer> legalesERI) {
		this.legalesERI = legalesERI;
	}

	/**
	 * Gets the mostrar ERI.
	 *
	 * @return the mostrar ERI
	 */
	public Boolean getMostrarERI() {
		return mostrarERI;
	}

	/**
	 * Sets the mostrar ERI.
	 *
	 * @param mostrarERI
	 *            the new mostrar ERI
	 */
	public void setMostrarERI(Boolean mostrarERI) {
		this.mostrarERI = mostrarERI;
	}

	/**
	 * Gets the comisiones.
	 *
	 * @return the comisiones
	 */
	public String getComisiones() {
		return comisiones;
	}

	/**
	 * Sets the comisiones.
	 *
	 * @param comisiones
	 *            the new comisiones
	 */
	public void setComisiones(String comisiones) {
		this.comisiones = comisiones;
	}

	/**
	 * Gets the iva sobre comision.
	 *
	 * @return the iva sobre comision
	 */
	public String getIvaSobreComision() {
		return ivaSobreComision;
	}

	/**
	 * Sets the iva sobre comision.
	 *
	 * @param ivaSobreComision
	 *            the new iva sobre comision
	 */
	public void setIvaSobreComision(String ivaSobreComision) {
		this.ivaSobreComision = ivaSobreComision;
	}

	/**
	 * Gets the derecho de mercado.
	 *
	 * @return the derecho de mercado
	 */
	public String getDerechoDeMercado() {
		return derechoDeMercado;
	}

	/**
	 * Sets the derecho de mercado.
	 *
	 * @param derechoDeMercado
	 *            the new derecho de mercado
	 */
	public void setDerechoDeMercado(String derechoDeMercado) {
		this.derechoDeMercado = derechoDeMercado;
	}

	/**
	 * Gets the iva sobre derecho de mercado.
	 *
	 * @return the iva sobre derecho de mercado
	 */
	public String getIvaSobreDerechoDeMercado() {
		return ivaSobreDerechoDeMercado;
	}

	/**
	 * Sets the iva sobre derecho de mercado.
	 *
	 * @param ivaSobreDerechoDeMercado
	 *            the new iva sobre derecho de mercado
	 */
	public void setIvaSobreDerechoDeMercado(String ivaSobreDerechoDeMercado) {
		this.ivaSobreDerechoDeMercado = ivaSobreDerechoDeMercado;
	}

	/**
	 * Gets the permite operar.
	 *
	 * @return the permite operar
	 */
	public Boolean getPermiteOperar() {
		return permiteOperar;
	}

	/**
	 * Sets the permite operar.
	 *
	 * @param permiteOperar
	 *            the new permite operar
	 */
	public void setPermiteOperar(Boolean permiteOperar) {
		this.permiteOperar = permiteOperar;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the poder de compra.
	 *
	 * @return the poder de compra
	 */
	public Boolean getPoderDeCompra() {
		return poderDeCompra;
	}

	/**
	 * Sets the poder de compra.
	 *
	 * @param poderDeCompra
	 *            the new poder de compra
	 */
	public void setPoderDeCompra(Boolean poderDeCompra) {
		this.poderDeCompra = poderDeCompra;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha hora operacion.
	 *
	 * @return the fecha hora operacion
	 */
	public String getFechaHoraOperacion() {
		return fechaHoraOperacion;
	}

	/**
	 * Sets the fecha hora operacion.
	 *
	 * @param fechaHoraOperacion
	 *            the new fecha hora operacion
	 */
	public void setFechaHoraOperacion(String fechaHoraOperacion) {
		this.fechaHoraOperacion = fechaHoraOperacion;
	}

	/**
	 * Gets the mensaje OK.
	 *
	 * @return the mensaje OK
	 */
	public String getMensajeOK() {
		return mensajeOK;
	}

	/**
	 * Sets the mensaje OK.
	 *
	 * @param mensajeOK
	 *            the new mensaje OK
	 */
	public void setMensajeOK(String mensajeOK) {
		this.mensajeOK = mensajeOK;
	}

	/**
	 * Gets the fecha liquidacion formateada.
	 *
	 * @return the fecha liquidacion formateada
	 */
	public String getFechaLiquidacionFormateada() {
		return fechaLiquidacionFormateada;
	}

	/**
	 * Sets the fecha liquidacion formateada.
	 *
	 * @param fechaLiquidacionFormateada
	 *            the new fecha liquidacion formateada
	 */
	public void setFechaLiquidacionFormateada(String fechaLiquidacionFormateada) {
		this.fechaLiquidacionFormateada = fechaLiquidacionFormateada;
	}
	
	/**
	 * Gets the bonificacion.
	 *
	 * @return the bonificacion
	 */
	public String getBonificacion() {
		return bonificacion;
	}

	/**
	 * Sets the bonificacion.
	 *
	 * @param bonificacion
	 *            the bonificacion to set
	 */
	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	/**
	 * Gets the comisionOriginal.
	 *
	 * @return the comisionOriginal
	 */
	public String getComisionOriginal() {
		return comisionOriginal;
	}

	/**
	 * Sets the comisionOriginal.
	 *
	 * @param comisionOriginal
	 *            the comisionOriginal to set
	 */
	public void setComisionOriginal(String comisionOriginal) {
		this.comisionOriginal = comisionOriginal;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public String getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the comision to set
	 */
	public void setComision(String comision) {
		this.comision = comision;
	}

	/**
	 * Gets the informacion.
	 *
	 * @return the informacion
	 */
	public String getInformacion() {
		return informacion;
	}

	/**
	 * Sets the informacion.
	 *
	 * @param informacion
	 *            the informacion to set
	 */
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	/**
	 * Gets the tieneBonificacion.
	 *
	 * @return the tieneBonificacion
	 */
	public boolean getTieneBonificacion() {
		return tieneBonificacion;
	}

	/**
	 * Sets the tieneBonificacion.
	 *
	 * @param tieneBonificacion
	 *            the tieneBonificacion to set
	 */
	public void setTieneBonificacion(boolean tieneBonificacion) {
		this.tieneBonificacion = tieneBonificacion;
	}

			
}