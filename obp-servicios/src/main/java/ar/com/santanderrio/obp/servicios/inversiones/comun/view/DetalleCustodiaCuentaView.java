/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class DetalleCustodiaCuentaView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleCustodiaCuentaView {

	/** The intervinientes. */
	private List<Interviniente> intervinientes;
	
	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The sucursal cuenta. */
	private String sucursalCuenta;
	
	/** The total pesos. */
	private String totalPesos;
	
	/** The total dolares. */
	private String totalDolares;
	
	/** The detalle custodia cuenta peso. */
	private List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaPeso;

	/** The detalle custodia cuenta dolar. */
	private List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaDolar;
	
	/** The tenencia expresada pesos. */
	private DetalleCustodiaTenenciaExperesada tenenciaExpresadaPesos;
	
	/** The tenencia expresada dolares. */
	private DetalleCustodiaTenenciaExperesada tenenciaExpresadaDolares;
	
	
	/**
	 * Gets the detalle custodia cuenta peso.
	 *
	 * @return the detalle custodia cuenta peso
	 */
	public List<DetalleCustodiaCuentaMonedaView> getDetalleCustodiaCuentaPeso() {
		return detalleCustodiaCuentaPeso;
	}

	/**
	 * Sets the detalle custodia cuenta peso.
	 *
	 * @param detalleCustodiaCuentaPeso
	 *            the new detalle custodia cuenta peso
	 */
	public void setDetalleCustodiaCuentaPeso(List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaPeso) {
		this.detalleCustodiaCuentaPeso = detalleCustodiaCuentaPeso;
	}

	/**
	 * Gets the detalle custodia cuenta dolar.
	 *
	 * @return the detalle custodia cuenta dolar
	 */
	public List<DetalleCustodiaCuentaMonedaView> getDetalleCustodiaCuentaDolar() {
		return detalleCustodiaCuentaDolar;
	}

	/**
	 * Sets the detalle custodia cuenta dolar.
	 *
	 * @param detalleCustodiaCuentaDolar
	 *            the new detalle custodia cuenta dolar
	 */
	public void setDetalleCustodiaCuentaDolar(List<DetalleCustodiaCuentaMonedaView> detalleCustodiaCuentaDolar) {
		this.detalleCustodiaCuentaDolar = detalleCustodiaCuentaDolar;
	}

	/**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the total pesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the new total pesos
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the total dolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the new total dolares
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
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

	/**
	 * Gets the tenencia expresada pesos.
	 *
	 * @return the tenencia expresada pesos
	 */
	public DetalleCustodiaTenenciaExperesada getTenenciaExpresadaPesos() {
		return tenenciaExpresadaPesos;
	}

	/**
	 * Sets the tenencia expresada pesos.
	 *
	 * @param tenenciaExpresadaPesos
	 *            the new tenencia expresada pesos
	 */
	public void setTenenciaExpresadaPesos(DetalleCustodiaTenenciaExperesada tenenciaExpresadaPesos) {
		this.tenenciaExpresadaPesos = tenenciaExpresadaPesos;
	}

	/**
	 * Gets the tenencia expresada dolares.
	 *
	 * @return the tenencia expresada dolares
	 */
	public DetalleCustodiaTenenciaExperesada getTenenciaExpresadaDolares() {
		return tenenciaExpresadaDolares;
	}

	/**
	 * Sets the tenencia expresada dolares.
	 *
	 * @param tenenciaExpresadaDolares
	 *            the new tenencia expresada dolares
	 */
	public void setTenenciaExpresadaDolares(DetalleCustodiaTenenciaExperesada tenenciaExpresadaDolares) {
		this.tenenciaExpresadaDolares = tenenciaExpresadaDolares;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}
				
}