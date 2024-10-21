/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class SesionCompraVenta.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionCompraVenta {

	/** The tipo documento. */
	private String tipoDocumento;

	/** The nro documento. */
	private String nroDocumento;

	/** The cuentas pesos. */
	private List<CuentasAdhesionDebitoView> cuentasPesos;

	/** The cuentas dolares. */
	private List<CuentasAdhesionDebitoView> cuentasDolares;

	/** The cuenta origen compra venta. */
	private Cuenta cuentaOrigenCompraVenta;

	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The contador intentos compra. */
	private ContadorIntentos contadorCompra;

	/** The contador intentos venta. */
	private ContadorIntentos contadorVenta;

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the new nro documento
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the cuentas pesos.
	 *
	 * @return the cuentas pesos
	 */
	public List<CuentasAdhesionDebitoView> getCuentasPesos() {
		return cuentasPesos;
	}

	/**
	 * Sets the cuentas pesos.
	 *
	 * @param cuentasPesos
	 *            the new cuentas pesos
	 */
	public void setCuentasPesos(List<CuentasAdhesionDebitoView> cuentasPesos) {
		this.cuentasPesos = cuentasPesos;
	}

	/**
	 * Gets the cuentas dolares.
	 *
	 * @return the cuentas dolares
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDolares() {
		return cuentasDolares;
	}

	/**
	 * Sets the cuentas dolares.
	 *
	 * @param cuentasDolares
	 *            the new cuentas dolares
	 */
	public void setCuentasDolares(List<CuentasAdhesionDebitoView> cuentasDolares) {
		this.cuentasDolares = cuentasDolares;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numeroCuentaOrigen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the numeroCuentaOrigen to set
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numeroCuentaDestino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the numeroCuentaDestino to set
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Gets the contador compra.
	 *
	 * @return the contador compra
	 */
	public ContadorIntentos getContadorCompra() {
		return contadorCompra;
	}

	/**
	 * Sets the contador compra.
	 *
	 * @param contadorCompra
	 *            the new contador compra
	 */
	public void setContadorCompra(ContadorIntentos contadorCompra) {
		this.contadorCompra = contadorCompra;
	}

	/**
	 * Gets the contador venta.
	 *
	 * @return the contador venta
	 */
	public ContadorIntentos getContadorVenta() {
		return contadorVenta;
	}

	/**
	 * Sets the contador venta.
	 *
	 * @param contadorVenta
	 *            the new contador venta
	 */
	public void setContadorVenta(ContadorIntentos contadorVenta) {
		this.contadorVenta = contadorVenta;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cuentasPesos", cuentasPesos).append("cuentasDolares", cuentasDolares)
				.append("numeroCuentaOrigen", numeroCuentaOrigen).append("numeroCuentaDestino", numeroCuentaDestino)
				.append("cuentaOrigenCompraVenta", cuentaOrigenCompraVenta).toString();
	}

}
