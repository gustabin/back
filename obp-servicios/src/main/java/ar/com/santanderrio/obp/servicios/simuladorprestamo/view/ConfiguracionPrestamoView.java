/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;
import ar.com.santanderrio.obp.servicios.prestamos.view.CuentaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.LimitesPrestamoView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dto.ConfiguracionPrestamoDTO;

/**
 * The Class ConfiguracionPrestamoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfiguracionPrestamoView {

	/** The rango prestamo. */
	private List<RangoCuota> rangoPrestamo;

	/** The alias. */
	private String alias;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The numero. */
	private String numero;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The cbu. */
	private String cbu;

	/** The linea disponible. */
	private String lineaDisponible;

	/** The linea total. */
	private String lineaTotal;

	/** The fecha inicio pago min. */
	private String fechaInicioPagoMin;

	/** The fecha inicio pago max. */
	private String fechaInicioPagoMax;

	/** The cuota minima. */
	private String cuotaMinima;

	/** The cuota maxima. */
	private String cuotaMaxima;

	/** The mensaje alerta. */
	private String mensajeAlerta;

	/** The importe minimo. */
	private String importeMinimo;

	/** The importe maximo. */
	private String importeMaximo;

	/** The importe minimo formateado. */
	private String importeMinimoFormateado;

	/** The importe maximo formateado. */
	private String importeMaximoFormateado;

	/** The importe mensaje inhabilitado. */
	private String mensajeInhabilitado;

	/** The limites. */
	private List<LimitesPrestamoView> limites;
	
	/** cuentas*/
	private List<CuentaView> cuentas;
	
	/** mensaje preaprobado*/
	private String mensajePreaprobado;
	
	/** mensaje prestamos personales*/
	private String mensajeSuperPrestamos;
	
	
	/**
	 * Instantiates a new configuracion prestamo view.
	 */
	public ConfiguracionPrestamoView() {
		super();
	}

	/**
	 * Instantiates a new configuracion prestamo view.
	 *
	 * @param configuracionPrestamoDto
	 *            the configuracion prestamo dto
	 * @param cuenta
	 *            the cuenta
	 */
	public ConfiguracionPrestamoView(ConfiguracionPrestamoDTO configuracionPrestamoDto, Cuenta cuenta) {

		this.lineaDisponible = configuracionPrestamoDto.getLineaDisponible();
		this.lineaTotal = configuracionPrestamoDto.getLineaTotal();
		this.alias = cuenta.getAlias();
		this.cbu = cuenta.getCbu();
		this.descripcionTipoCuenta = cuenta.getTipoCuentaEnum().getDescripcionConMoneda();
		this.saldoPesos = cuenta.obtenerSaldoFormateado();
		this.numero = "Cuenta " + ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);

		this.importeMaximo = configuracionPrestamoDto.getImporteMaximo();
		this.importeMinimo = configuracionPrestamoDto.getImporteMinimo();
		this.importeMaximoFormateado = configuracionPrestamoDto.getImporteMaximoFormateado();
		this.importeMinimoFormateado = configuracionPrestamoDto.getImporteMinimoFormateado();
		this.fechaInicioPagoMin = configuracionPrestamoDto.getFechaInicioPagoMin();
		this.fechaInicioPagoMax = configuracionPrestamoDto.getFechaInicioPagoMax();

		this.rangoPrestamo = configuracionPrestamoDto.getRangosDeCuotas();
		this.cuotaMinima = configuracionPrestamoDto.getCuotaMinima();
		this.cuotaMaxima = configuracionPrestamoDto.getCuotaMaxima();
		this.mensajeInhabilitado = configuracionPrestamoDto.getMensajeInhabilitado();
		this.limites = configuracionPrestamoDto.getLimites();
	}
	
	/**
	 * Gets the rango prestamo.
	 *
	 * @return the rango prestamo
	 */
	public List<RangoCuota> getRangoPrestamo() {
		return rangoPrestamo;
	}

	/**
	 * Sets the rango prestamo.
	 *
	 * @param rangoPrestamo
	 *            the new rango prestamo
	 */
	public void setRangoPrestamo(List<RangoCuota> rangoPrestamo) {
		this.rangoPrestamo = rangoPrestamo;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcion tipo cuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the linea disponible.
	 *
	 * @return the linea disponible
	 */
	public String getLineaDisponible() {
		return lineaDisponible;
	}

	/**
	 * Sets the linea disponible.
	 *
	 * @param lineaDisponible
	 *            the new linea disponible
	 */
	public void setLineaDisponible(String lineaDisponible) {
		this.lineaDisponible = lineaDisponible;
	}

	/**
	 * Gets the linea total.
	 *
	 * @return the linea total
	 */
	public String getLineaTotal() {
		return lineaTotal;
	}

	/**
	 * Sets the linea total.
	 *
	 * @param lineaTotal
	 *            the new linea total
	 */
	public void setLineaTotal(String lineaTotal) {
		this.lineaTotal = lineaTotal;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the new importe maximo
	 */
	public void setImporteMaximo(String importeMaximo) {
		this.importeMaximo = importeMaximo;
	}

	/**
	 * Gets the fecha inicio pago min.
	 *
	 * @return the fecha inicio pago min
	 */
	public String getFechaInicioPagoMin() {
		return fechaInicioPagoMin;
	}

	/**
	 * Sets the fecha inicio pago min.
	 *
	 * @param fechaInicioPagoMin
	 *            the new fecha inicio pago min
	 */
	public void setFechaInicioPagoMin(String fechaInicioPagoMin) {
		this.fechaInicioPagoMin = fechaInicioPagoMin;
	}

	/**
	 * Gets the fecha inicio pago max.
	 *
	 * @return the fecha inicio pago max
	 */
	public String getFechaInicioPagoMax() {
		return fechaInicioPagoMax;
	}

	/**
	 * Sets the fecha inicio pago max.
	 *
	 * @param fechaInicioPagoMax
	 *            the new fecha inicio pago max
	 */
	public void setFechaInicioPagoMax(String fechaInicioPagoMax) {
		this.fechaInicioPagoMax = fechaInicioPagoMax;
	}

	/**
	 * Gets the cuota minima.
	 *
	 * @return the cuota minima
	 */
	public String getCuotaMinima() {
		return cuotaMinima;
	}

	/**
	 * Sets the cuota minima.
	 *
	 * @param cuotaMinima
	 *            the new cuota minima
	 */
	public void setCuotaMinima(String cuotaMinima) {
		this.cuotaMinima = cuotaMinima;
	}

	/**
	 * Gets the cuota maxima.
	 *
	 * @return the cuota maxima
	 */
	public String getCuotaMaxima() {
		return cuotaMaxima;
	}

	/**
	 * Sets the cuota maxima.
	 *
	 * @param cuotaMaxima
	 *            the new cuota maxima
	 */
	public void setCuotaMaxima(String cuotaMaxima) {
		this.cuotaMaxima = cuotaMaxima;
	}

	/**
	 * Gets the mensaje alerta.
	 *
	 * @return the mensaje alerta
	 */
	public String getMensajeAlerta() {
		return mensajeAlerta;
	}

	/**
	 * Sets the mensaje alerta.
	 *
	 * @param mensajeAlerta
	 *            the new mensaje alerta
	 */
	public void setMensajeAlerta(String mensajeAlerta) {
		this.mensajeAlerta = mensajeAlerta;
	}

	/**
	 * Gets the importe minimo formateado.
	 *
	 * @return the importe minimo formateado
	 */
	public String getImporteMinimoFormateado() {
		return importeMinimoFormateado;
	}

	/**
	 * Sets the importe minimo formateado.
	 *
	 * @param importeMinimoFormateado
	 *            the new importe minimo formateado
	 */
	public void setImporteMinimoFormateado(String importeMinimoFormateado) {
		this.importeMinimoFormateado = importeMinimoFormateado;
	}

	/**
	 * Gets the importe maximo formateado.
	 *
	 * @return the importe maximo formateado
	 */
	public String getImporteMaximoFormateado() {
		return importeMaximoFormateado;
	}

	/**
	 * Sets the importe maximo formateado.
	 *
	 * @param importeMaximoFormateado
	 *            the new importe maximo formateado
	 */
	public void setImporteMaximoFormateado(String importeMaximoFormateado) {
		this.importeMaximoFormateado = importeMaximoFormateado;
	}

	/**
	 * Gets the mensaje inhabilitado.
	 *
	 * @return the mensaje inhabilitado
	 */
	public String getMensajeInhabilitado() {
		return mensajeInhabilitado;
	}

	/**
	 * Sets the mensaje inhabilitado.
	 *
	 * @param mensajeInhabilitado
	 *            the new mensaje inhabilitado
	 */
	public void setMensajeInhabilitado(String mensajeInhabilitado) {
		this.mensajeInhabilitado = mensajeInhabilitado;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimitesPrestamoView> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the new limites
	 */
	public void setLimites(List<LimitesPrestamoView> limites) {
		this.limites = limites;
	}

	public List<CuentaView> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaView> cuentas) {
		this.cuentas = cuentas;
	}

	public String getMensajePreaprobado() {
		return mensajePreaprobado;
	}

	public void setMensajePreaprobado(String mensajePreaprobado) {
		this.mensajePreaprobado = mensajePreaprobado;
	}

	public String getMensajeSuperPrestamos() {
		return mensajeSuperPrestamos;
	}

	public void setMensajeSuperPrestamos(String mensajeSuperPrestamos) {
		this.mensajeSuperPrestamos = mensajeSuperPrestamos;
	}

}
