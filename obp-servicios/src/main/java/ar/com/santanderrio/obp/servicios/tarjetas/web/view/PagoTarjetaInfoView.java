/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class PagoTarjetaInfoView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PagoTarjetaInfoView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8284621286586231163L;

	/** The contrato aceptado. */
	private Boolean contratoAceptado;

	/** The tarjetas. */
	private List<PagoTarjetaView> tarjetas;

	/** The cuentas pesos. */
	private List<CuentasAdhesionDebitoView> cuentasPesos;

	/** The cuentas dolares. */
	private List<CuentasAdhesionDebitoView> cuentasDolares;

	/**
	 * Instantiates a new pago tarjeta info view.
	 */
	public PagoTarjetaInfoView() {
		super();
	}

	/**
	 * Instantiates a new pago tarjeta info view.
	 *
	 * @param contratoAceptado
	 *            the contrato aceptado
	 * @param tarjetas
	 *            the tarjetas
	 * @param cuentas
	 *            the cuentas
	 */
	public PagoTarjetaInfoView(Boolean contratoAceptado, List<PagoTarjetaView> tarjetas,
			List<CuentasAdhesionDebitoView> cuentas) {
		this.cuentasPesos = new ArrayList<CuentasAdhesionDebitoView>();
		this.cuentasDolares = new ArrayList<CuentasAdhesionDebitoView>();
		for (CuentasAdhesionDebitoView cuenta : cuentas) {
			if (cuenta.getShowSaldoPesos()) {
				this.cuentasPesos.add(cuenta);
			}
			if (cuenta.getShowSaldoDolares()) {
				this.cuentasDolares.add(cuenta);
			}
		}
		this.contratoAceptado = contratoAceptado;
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the contrato aceptado.
	 *
	 * @return the contrato aceptado
	 */
	public Boolean getContratoAceptado() {
		return contratoAceptado;
	}

	/**
	 * Sets the contrato aceptado.
	 *
	 * @param contratoAceptado
	 *            the new contrato aceptado
	 */
	public void setContratoAceptado(Boolean contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<PagoTarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<PagoTarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
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
}
