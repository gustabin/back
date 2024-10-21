/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * Clase que modela una cuenta titulo para ser representada en UI.
 *
 * @author marcelo.ruiz
 */
public class CuentaTituloView {

	/** The nro cuenta. */
	private String nroCuenta;
	
	/** The nro cuenta. */
	private String cuentaOperativaCSuc;

	/** The sucursal. */
	private String sucursal;

	/** The nro cuenta formateado. */
	private String nroCuentaFormateado;

	/** The fondos suscriptos. */
	private List<FondoResumidoView> fondosSuscriptos = new ArrayList<FondoResumidoView>();

	/** Cuenta operativa asociada a la cuenta titulo (solo B.PRIVADA) */
	private String cuentaOperativa;

	/** The cuenta operativa sin formatear. */
	private String cuentaOperativaSinFormatear;

	/** The intervinientes. titular y cotitulares */
	private List<Interviniente> intervinientes;

	/** "S" puede licitar, "N" no puede licitar. */
	private String puedeLicitar;
	
	/** true si es Repatriacion */
	private boolean repatriacion;

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the nro cuenta formateado.
	 *
	 * @return the nro cuenta formateado
	 */
	public String getNroCuentaFormateado() {
		return nroCuentaFormateado;
	}

	/**
	 * Sets the nro cuenta formateado.
	 *
	 * @param nroCuentaFormateado
	 *            the new nro cuenta formateado
	 */
	public void setNroCuentaFormateado(String nroCuentaFormateado) {
		this.nroCuentaFormateado = nroCuentaFormateado;
	}

	/**
	 * Gets the fondos suscriptos.
	 *
	 * @return the fondos suscriptos
	 */
	public List<FondoResumidoView> getFondosSuscriptos() {
		return fondosSuscriptos;
	}

	/**
	 * Sets the fondos suscriptos.
	 *
	 * @param fondosSuscriptos
	 *            the new fondos suscriptos
	 */
	public void setFondosSuscriptos(List<FondoResumidoView> fondosSuscriptos) {
		this.fondosSuscriptos = fondosSuscriptos;
	}

	/**
	 * Gets the cuenta operativa.
	 *
	 * @return the cuenta operativa
	 */
	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	/**
	 * Sets the cuenta operativa.
	 *
	 * @param cuentaOperativa
	 *            the new cuenta operativa
	 */
	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	/**
	 * Gets the cuenta operativa sin formatear.
	 *
	 * @return the cuenta operativa sin formatear
	 */
	public String getCuentaOperativaSinFormatear() {
		return cuentaOperativaSinFormatear;
	}

	/**
	 * Sets the cuenta operativa sin formatear.
	 *
	 * @param cuentaOperativaSinFormatear
	 *            the new cuenta operativa sin formatear
	 */
	public void setCuentaOperativaSinFormatear(String cuentaOperativaSinFormatear) {
		this.cuentaOperativaSinFormatear = cuentaOperativaSinFormatear;
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
	 * Gets the puede licitar.
	 *
	 * @return the puede licitar
	 */
	public String getPuedeLicitar() {
		return puedeLicitar;
	}

	/**
	 * Sets the puede licitar.
	 *
	 * @param puedeLicitar
	 *            the new puede licitar
	 */
	public void setPuedeLicitar(String puedeLicitar) {
		this.puedeLicitar = puedeLicitar;
	}

	/**
	 * Gets the cuenta operativa C suc.
	 *
	 * @return the cuenta operativa C suc
	 */
	public String getCuentaOperativaCSuc() {
		return cuentaOperativaCSuc;
	}

	/**
	 * Sets the cuenta operativa C suc.
	 *
	 * @param cuentaOperativaCSuc
	 *            the new cuenta operativa C suc
	 */
	public void setCuentaOperativaCSuc(String cuentaOperativaCSuc) {
		this.cuentaOperativaCSuc = cuentaOperativaCSuc;
	}

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}

	
	

}
