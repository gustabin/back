/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaSelectorDetalleView.
 */
public class CuentaSelectorDetalleView extends CuentaSelectorView {

	/** The abreviatura tipo cuenta. */
	private String abreviaturaTipoCuenta;

	/** The cbu. */
	private String cbu;

	/** The Alias Cbu. */
	private String aliasCbu;

	/** The is traspaso automatico. */
	private Boolean isTraspasoAutomatico;

	/** The url reporte CBU. */
	private String urlReporteCBU;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The apellido cliente. */
	private String apellidoCliente;

	/** The tipo identificacion. */
	private String tipoIdentificacion;

	/** The numero identificacion. */
	private String numeroIdentificacion;

	/** The has alias. */
	private Boolean hasAlias;

	/** The fecha desde movimiento. */
	private String fechaDesdeMovimiento;

	/** The fecha hasta movimiento. */
	private String fechaHastaMovimiento;

	/** The has traspaso. */
	private Boolean hasTraspaso;

	/** The show realizar traspaso. */
	private Boolean showRealizarTraspaso;

	/** The show solicitar traspaso. */
	private Boolean showSolicitarTraspaso;

	/** The sucursal *. */
	private String sucursal;

	/** The sucursal *. */
	private String moneda;
	
	/** The motivo Cuenta Cerrada *. */
	private String motivoCuentaCerrada;

	/** The Constant PESO. */
	protected static final String PESO = "peso";

	/** The Constant DOLAR. */
	protected static final String DOLAR = "dolar";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSelectorView#
	 * build(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * ResumenDetalleCuenta)
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		if (StringUtils.isEmpty(resumenDetalleCuenta.getAlias())) {
			this.hasAlias = Boolean.FALSE;
		} else {
			this.hasAlias = Boolean.TRUE;
		}
	}

	/**
	 * Gets the abreviatura tipo cuenta.
	 *
	 * @return the abreviatura tipo cuenta
	 */
	public String getAbreviaturaTipoCuenta() {
		return abreviaturaTipoCuenta;
	}

	/**
	 * Sets the abreviatura tipo cuenta.
	 *
	 * @param abreviaturaTipoCuenta
	 *            the new abreviatura tipo cuenta
	 */
	public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
		this.abreviaturaTipoCuenta = abreviaturaTipoCuenta;
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
	 * Gets the checks if is traspaso automatico.
	 *
	 * @return the checks if is traspaso automatico
	 */
	public Boolean getIsTraspasoAutomatico() {
		return isTraspasoAutomatico;
	}

	/**
	 * Sets the checks if is traspaso automatico.
	 *
	 * @param isTraspasoAutomatico
	 *            the new checks if is traspaso automatico
	 */
	public void setIsTraspasoAutomatico(Boolean isTraspasoAutomatico) {
		this.isTraspasoAutomatico = isTraspasoAutomatico;
	}

	/**
	 * Gets the url reporte CBU.
	 *
	 * @return the url reporte CBU
	 */
	public String getUrlReporteCBU() {
		return urlReporteCBU;
	}

	/**
	 * Sets the url reporte CBU.
	 *
	 * @param urlReporteCBU
	 *            the new url reporte CBU
	 */
	public void setUrlReporteCBU(String urlReporteCBU) {
		this.urlReporteCBU = urlReporteCBU;
	}

	/**
	 * Gets the nombre cliente.
	 *
	 * @return the nombre cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the new nombre cliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Gets the apellido cliente.
	 *
	 * @return the apellido cliente
	 */
	public String getApellidoCliente() {
		return apellidoCliente;
	}

	/**
	 * Sets the apellido cliente.
	 *
	 * @param apellidoCliente
	 *            the new apellido cliente
	 */
	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	/**
	 * Gets the tipo identificacion.
	 *
	 * @return the tipo identificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Sets the tipo identificacion.
	 *
	 * @param tipoIdentificacion
	 *            the new tipo identificacion
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Gets the numero identificacion.
	 *
	 * @return the numero identificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * Sets the numero identificacion.
	 *
	 * @param numeroIdentificacion
	 *            the new numero identificacion
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * Gets the checks for alias.
	 *
	 * @return the checks for alias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Sets the checks for alias.
	 *
	 * @param hasAlias
	 *            the new checks for alias
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}

	/**
	 * Gets the fecha desde movimiento.
	 *
	 * @return the fecha desde movimiento
	 */
	public String getFechaDesdeMovimiento() {
		return fechaDesdeMovimiento;
	}

	/**
	 * Sets the fecha desde movimiento.
	 *
	 * @param fechaDesdeMovimiento
	 *            the new fecha desde movimiento
	 */
	public void setFechaDesdeMovimiento(String fechaDesdeMovimiento) {
		this.fechaDesdeMovimiento = fechaDesdeMovimiento;
	}

	/**
	 * Gets the fecha hasta movimiento.
	 *
	 * @return the fecha hasta movimiento
	 */
	public String getFechaHastaMovimiento() {
		return fechaHastaMovimiento;
	}

	/**
	 * Sets the fecha hasta movimiento.
	 *
	 * @param fechaHastaMovimiento
	 *            the new fecha hasta movimiento
	 */
	public void setFechaHastaMovimiento(String fechaHastaMovimiento) {
		this.fechaHastaMovimiento = fechaHastaMovimiento;
	}

	/**
	 * Gets the checks for traspaso.
	 *
	 * @return the checks for traspaso
	 */
	public Boolean getHasTraspaso() {
		return hasTraspaso;
	}

	/**
	 * Sets the checks for traspaso.
	 *
	 * @param hasTraspaso
	 *            the new checks for traspaso
	 */
	public void setHasTraspaso(Boolean hasTraspaso) {
		this.hasTraspaso = hasTraspaso;
	}

	/**
	 * Gets the show realizar traspaso.
	 *
	 * @return the show realizar traspaso
	 */
	public Boolean getShowRealizarTraspaso() {
		return showRealizarTraspaso;
	}

	/**
	 * Sets the show realizar traspaso.
	 *
	 * @param showRealizarTraspaso
	 *            the new show realizar traspaso
	 */
	public void setShowRealizarTraspaso(Boolean showRealizarTraspaso) {
		this.showRealizarTraspaso = showRealizarTraspaso;
	}

	/**
	 * Gets the show solicitar traspaso.
	 *
	 * @return the show solicitar traspaso
	 */
	public Boolean getShowSolicitarTraspaso() {
		return showSolicitarTraspaso;
	}

	/**
	 * Sets the show solicitar traspaso.
	 *
	 * @param showSolicitarTraspaso
	 *            the new show solicitar traspaso
	 */
	public void setShowSolicitarTraspaso(Boolean showSolicitarTraspaso) {
		this.showSolicitarTraspaso = showSolicitarTraspaso;
	}

	/**
	 * Gets the alias cbu.
	 *
	 * @return the alias cbu
	 */
	public String getAliasCbu() {
		return aliasCbu;
	}

	/**
	 * Sets the alias cbu.
	 *
	 * @param aliasCbu
	 *            the new alias cbu
	 */
	public void setAliasCbu(String aliasCbu) {
		this.aliasCbu = aliasCbu;
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
	 * Sets the descubierto.
	 *
	 * @param resumenDetalleCuenta
	 *            the new descubierto
	 */
	protected void setDescubierto(ResumenDetalleCuenta resumenDetalleCuenta) {
		setIsDescubiertoUtilizado(Boolean.FALSE);
		setShowDescubierto(Boolean.FALSE);
		if (SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())) {
			if (resumenDetalleCuenta.isCuentaUnica()) {
				setSaldoCuentaCorriente(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCuentaCorrientePesos(),
						resumenDetalleCuenta));
				setSaldoCuentaCorrienteValue(resumenDetalleCuenta.getSaldoCuentaCorrientePesos());
			}
            if (resumenDetalleCuenta.mostrarDescubierto()) {
                if (resumenDetalleCuenta.getSaldoDescubiertoDisponible().compareTo(BigDecimal.ZERO) < 0) {
                    setSaldoDescubierto(ISBANStringUtils.formatearSaldoSinAbs(BigDecimal.ZERO));
                    setSaldoDescubiertoValue(BigDecimal.ZERO);
                } else {
                    setSaldoDescubierto(ISBANStringUtils
                            .formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoDescubiertoDisponible()));
                    setSaldoDescubiertoValue(resumenDetalleCuenta.getSaldoDescubiertoDisponible());
                }
                setShowDescubierto(Boolean.TRUE);
            }
            if (resumenDetalleCuenta.isDescubiertoUtilizado()) {
                setIsDescubiertoUtilizado(Boolean.TRUE);
            }
		}
		if (resumenDetalleCuenta.getTraspasoAutomaticoActivo() == null && !resumenDetalleCuenta.isCuentaCerrada()) {
			if (!resumenDetalleCuenta.isCuentaUnica()) {
				setSaldoPesos(GUION);
				setSaldoCuentaCorrienteValue(BigDecimal.ZERO);
			}

		}
	}

	/**
	 * Formatear saldo sin abs.
	 *
	 * @param saldo
	 *            the saldo
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @return the string
	 */
	protected String formatearSaldoSinAbs(BigDecimal saldo, ResumenDetalleCuenta resumenDetalleCuenta) {
		if (resumenDetalleCuenta.getTraspasoAutomaticoActivo() == null
				&& resumenDetalleCuenta.getSolicitudPendienteTraspasoAutomatico() == null
				&& !resumenDetalleCuenta.isCuentaCerrada()) {
			return GUION;
		}
		return ISBANStringUtils.formatearSaldoSinAbs(saldo);
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the motivo cuenta cerrada.
	 *
	 * @return the motivo cuenta cerrada
	 */
	public String getMotivoCuentaCerrada() {
		return motivoCuentaCerrada;
	}

	/**
	 * Sets the motivo cuenta cerrada.
	 *
	 * @param motivoCuentaCerrada
	 *            the new motivo cuenta cerrada
	 */
	public void setMotivoCuentaCerrada(String motivoCuentaCerrada) {
		this.motivoCuentaCerrada = motivoCuentaCerrada;
	}

}
