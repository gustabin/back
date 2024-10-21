/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class PagosPendientesView.
 *
 * @author B039636
 * @LastUpdate Nov 11, 2016 by emilio.watemberg@itrsa.com.ar: se agrego el dto
 *             de cuentas.
 */
public class PagosPendientesView {

	/** The total pesos. */
	private String totalPesos;

	/** The total dolares. */
	private String totalDolares;

	/** The periodo actual. */
	private PeriodoView periodoActual;

	/** The periodo total. */
	private PeriodoView periodoTotal;

	/** The periodos. */
	private List<PeriodoView> periodos;

	/** The pagos pendientes con vencimiento. */
	private List<PagoPendienteView> pagosPendientesConVencimiento = new ArrayList<PagoPendienteView>();
	
	private List<PagoPendienteView> pagosPendientesVencidosProductosSantander = new ArrayList<PagoPendienteView>();

	/** The pagos pendientes sin vencimiento. */
	private List<PagoPendienteView> pagosPendientesSinVencimiento = new ArrayList<PagoPendienteView>();

	/** The total pesos N. */
	private BigDecimal totalPesosN = BigDecimal.ZERO;

	/** The total dolares N. */
	private BigDecimal totalDolaresN = BigDecimal.ZERO;

	/** The fecha actual. */
	private PeriodoView fechaActual = new PeriodoView();

	/** The habilitar pago prestamo. */
	private Boolean habilitarPagoPrestamo;

	/** campo para mensajes. */
	private String mensaje;

	/**
	 * Mensaje para UI de que una empresa no permite pago automatico. se le
	 * establece un valor por defecto
	 */
	// TODO: emilio.watemberg: sacar cuando mechi pase el error de la BD y
	// cargar en PagoManager.
	private String mensajeNoPermitePago = "Esta empresa no permite el pago automatico. Si queres podes adherir el pago de tu factura por debito automatico en cuenta";

	/**
	 * Mensaje para UI del importe limite de un debito automatico. se le establece
	 * un valor por defecto
	 */
	// TODO: emilio.watemberg: sacar cuando mechi pase el error de la BD y
	// cargar en PagoManager.
	private String mensajeImporteLimite = "Deja el importe en 0 para abonar el total de cualquier factura de esta empresa";

	/**
	 * Booleano para que frontend sepa si mostrar o no el acceso a la adhesion a
	 * debito automatico en tarjetas.
	 */
	private Boolean tieneTarjetaCredito;

	/** The mostrar opcion pago tarjeta credito. */
	private Boolean mostrarOpcionPagoTarjetaCredito;
	
	private String mensajeInformacionPagoAdebitar;

	/** The mensaje opcion pago tarjeta credito. */
	private String mensajeOpcionPagoTarjetaCredito;

	/**
	 * Gets the mensajes.
	 *
	 * @return the mensajes
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensajes.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the totalPesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the totalPesos to set
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the totalDolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * Gets the pagos pendientes con vencimiento.
	 *
	 * @return the pagosPendientesConVencimiento
	 */
	public List<PagoPendienteView> getPagosPendientesConVencimiento() {
		return pagosPendientesConVencimiento;
	}

	/**
	 * Gets the pagos pendientes sin vencimiento.
	 *
	 * @return the pagosPendientesSinVencimiento
	 */
	public List<PagoPendienteView> getPagosPendientesSinVencimiento() {
		return pagosPendientesSinVencimiento;
	}

	/**
	 * Adds the pago con vencimiento.
	 *
	 * @param pago
	 *            the pago
	 */
	public void addPagoConVencimiento(PagoPendienteView pago) {
		this.pagosPendientesConVencimiento.add(pago);
	}

	/**
	 * Adds the pago sin vencimiento.
	 *
	 * @param pago
	 *            the pago
	 */
	public void addPagoSinVencimiento(PagoPendienteView pago) {
		this.pagosPendientesSinVencimiento.add(pago);
	}

	/**
	 * Gets the periodo actual.
	 *
	 * @return the periodoActual
	 */
	public PeriodoView getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * Sets the periodo actual.
	 *
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(PeriodoView periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<PeriodoView> getPeriodos() {
		return periodos;
	}

	/**
	 * Sets the periodos.
	 *
	 * @param periodos
	 *            the periodos to set
	 */
	public void setPeriodos(List<PeriodoView> periodos) {
		this.periodos = periodos;
	}

	/**
	 * Gets the total pesos N.
	 *
	 * @return the totalPesosN
	 */
	public BigDecimal getTotalPesosN() {
		return totalPesosN;
	}

	/**
	 * Adds the total pesos N.
	 *
	 * @param totalPesosN
	 *            the totalPesosN to set
	 */
	public void addTotalPesosN(BigDecimal totalPesosN) {
		this.totalPesosN = this.totalPesosN.add(totalPesosN);
	}

	/**
	 * Gets the total dolares N.
	 *
	 * @return the totalDolaresN
	 */
	public BigDecimal getTotalDolaresN() {
		return totalDolaresN;
	}

	/**
	 * Adds the total dolares N.
	 *
	 * @param totalDolaresN
	 *            the totalDolaresN to set
	 */
	public void addTotalDolaresN(BigDecimal totalDolaresN) {
		this.totalDolaresN = this.totalDolaresN.add(totalDolaresN);
	}

	/**
	 * Sets the pagos pendientes con vencimiento.
	 *
	 * @param pagosPendientesConVencimiento
	 *            the pagosPendientesConVencimiento to set
	 */
	public void setPagosPendientesConVencimiento(List<PagoPendienteView> pagosPendientesConVencimiento) {
		this.pagosPendientesConVencimiento = pagosPendientesConVencimiento;
	}

	/**
	 * Sets the pagos pendientes sin vencimiento.
	 *
	 * @param pagosPendientesSinVencimiento
	 *            the pagosPendientesSinVencimiento to set
	 */
	public void setPagosPendientesSinVencimiento(List<PagoPendienteView> pagosPendientesSinVencimiento) {
		this.pagosPendientesSinVencimiento = pagosPendientesSinVencimiento;
	}

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fechaActual
	 */
	public PeriodoView getFechaActual() {
		return fechaActual;
	}

	/**
	 * Sets the fecha actual.
	 *
	 * @param fechaActual
	 *            the fechaActual to set
	 */
	public void setFechaActual(PeriodoView fechaActual) {
		this.fechaActual = fechaActual;
	}

	/**
	 * Gets the habilitar pago prestamo.
	 *
	 * @return the habilitar pago prestamo
	 */
	public Boolean getHabilitarPagoPrestamo() {
		return habilitarPagoPrestamo;
	}

	/**
	 * Sets the habilitar pago prestamo.
	 *
	 * @param habilitarPagoPrestamo
	 *            the new habilitar pago prestamo
	 */
	public void setHabilitarPagoPrestamo(Boolean habilitarPagoPrestamo) {
		this.habilitarPagoPrestamo = habilitarPagoPrestamo;
	}

	/**
	 * Gets the mensaje no permite pago.
	 *
	 * @return the mensaje no permite pago
	 */
	public String getMensajeNoPermitePago() {
		return mensajeNoPermitePago;
	}

	/**
	 * Sets the mensaje no permite pago.
	 *
	 * @param mensajeNoPermitePago
	 *            the new mensaje no permite pago
	 */
	public void setMensajeNoPermitePago(String mensajeNoPermitePago) {
		this.mensajeNoPermitePago = mensajeNoPermitePago;
	}

	/**
	 * Gets the mensaje importe limite.
	 *
	 * @return the mensaje importe limite
	 */
	public String getMensajeImporteLimite() {
		return mensajeImporteLimite;
	}

	/**
	 * Sets the mensaje importe limite.
	 *
	 * @param mensajeImporteLimite
	 *            the new mensaje importe limite
	 */
	public void setMensajeImporteLimite(String mensajeImporteLimite) {
		this.mensajeImporteLimite = mensajeImporteLimite;
	}

	/**
	 * Gets the tiene tarjeta credito.
	 *
	 * @return the tiene tarjeta credito
	 */
	public Boolean getTieneTarjetaCredito() {
		return tieneTarjetaCredito;
	}

	/**
	 * Sets the tiene tarjeta credito.
	 *
	 * @param tieneTarjetaCredito
	 *            the new tiene tarjeta credito
	 */
	public void setTieneTarjetaCredito(Boolean tieneTarjetaCredito) {
		this.tieneTarjetaCredito = tieneTarjetaCredito;
	}

	/**
	 * Gets the mostrar opcion pago tarjeta credito.
	 *
	 * @return the mostrar opcion pago tarjeta credito
	 */
	public Boolean getMostrarOpcionPagoTarjetaCredito() {
		return mostrarOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mostrar opcion pago tarjeta credito.
	 *
	 * @param mostrarOpcionPagoTarjetaCredito
	 *            the new mostrar opcion pago tarjeta credito
	 */
	public void setMostrarOpcionPagoTarjetaCredito(Boolean mostrarOpcionPagoTarjetaCredito) {
		this.mostrarOpcionPagoTarjetaCredito = mostrarOpcionPagoTarjetaCredito;
	}

	/**
	 * Gets the periodo total.
	 *
	 * @return the periodoTotal
	 */
	public PeriodoView getPeriodoTotal() {
		return periodoTotal;
	}

	/**
	 * Sets the periodo total.
	 *
	 * @param periodoTotal
	 *            the periodoTotal to set
	 */
	public void setPeriodoTotal(PeriodoView periodoTotal) {
		this.periodoTotal = periodoTotal;
	}
	
	
	
	public String getMensajeInformacionPagoAdebitar() {
		return mensajeInformacionPagoAdebitar;
	}

	public void setMensajeInformacionPagoAdebitar(String mensajeInformacionPagoAdebitar) {
		this.mensajeInformacionPagoAdebitar = mensajeInformacionPagoAdebitar;
	}
	
	public List<PagoPendienteView> getPagosPendientesVencidosProductosSantander() {
		return pagosPendientesVencidosProductosSantander;
	}

	public void setPagosPendientesVencidosProductosSantander(
			List<PagoPendienteView> pagosPendientesVencidosProductosSantander) {
		this.pagosPendientesVencidosProductosSantander = pagosPendientesVencidosProductosSantander;
	}

	/**
	 * Gets the mensaje opcion pago tarjeta credito.
	 *
	 * @return the mensaje opcion pago tarjeta credito
	 */
	public String getMensajeOpcionPagoTarjetaCredito() {
		return mensajeOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mensaje opcion pago tarjeta credito.
	 *
	 * @param mensajeOpcionPagoTarjetaCredito the new mensaje opcion pago tarjeta credito
	 */
	public void setMensajeOpcionPagoTarjetaCredito(String mensajeOpcionPagoTarjetaCredito) {
		this.mensajeOpcionPagoTarjetaCredito = mensajeOpcionPagoTarjetaCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechaActual);
		hcb.append(habilitarPagoPrestamo);
		hcb.append(pagosPendientesConVencimiento);
		hcb.append(pagosPendientesSinVencimiento);
		hcb.append(periodoActual);
		hcb.append(periodoTotal);
		hcb.append(periodos);
		hcb.append(totalDolares);
		hcb.append(totalDolaresN);
		hcb.append(totalPesos);
		hcb.append(totalPesosN);
		hcb.append(mensajeInformacionPagoAdebitar);
		hcb.append(mensajeOpcionPagoTarjetaCredito);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public boolean equals(Object obj) {
		PagosPendientesView other = (PagosPendientesView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(fechaActual, other.getFechaActual());
		eb.append(habilitarPagoPrestamo, other.getHabilitarPagoPrestamo());
		eb.append(pagosPendientesConVencimiento, other.getPagosPendientesConVencimiento());
		eb.append(pagosPendientesSinVencimiento, other.getPagosPendientesSinVencimiento());
		eb.append(periodoActual, other.getPeriodoActual());
		eb.append(periodoTotal, other.getPeriodoTotal());
		eb.append(periodos, other.getPeriodos());
		eb.append(totalDolares, other.getTotalDolares());
		eb.append(totalDolaresN, other.getTotalDolaresN());
		eb.append(totalPesos, other.getTotalPesos());
		eb.append(totalPesosN, other.getTotalPesosN());
		eb.append(mensajeInformacionPagoAdebitar, other.getMensajeInformacionPagoAdebitar());
		eb.append(mensajeOpcionPagoTarjetaCredito, other.getMensajeOpcionPagoTarjetaCredito());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagosPendientesView [totalPesos=" + totalPesos + ", totalDolares=" + totalDolares + ", periodoActual="
				+ periodoActual + ", periodoTotal=" + periodoTotal + ", periodos=" + periodos
				+ ", pagosPendientesConVencimiento=" + pagosPendientesConVencimiento
				+ ", pagosPendientesSinVencimiento=" + pagosPendientesSinVencimiento + ", totalPesosN=" + totalPesosN
				+ ", totalDolaresN=" + totalDolaresN + ", fechaActual=" + fechaActual + ", habilitarPagoPrestamo="
				+ habilitarPagoPrestamo + ", mensajeInformacionPagoAdebitar=" + mensajeInformacionPagoAdebitar
				+ ", mensajeOpcionPagoTarjetaCredito=" + mensajeOpcionPagoTarjetaCredito + "]";
	}

}
