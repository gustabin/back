/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenDetalleCuenta.
 */
public class ResumenDetalleCuenta {

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";

	/** The nro sucursal. */
	private String nroSucursal;

	/** The nro cuenta producto. */
	private String nroCuentaProducto;

	/** The saldo pesos. */
	private BigDecimal saldoPesos = BigDecimal.ZERO;

	/** The saldo dolares. */
	private BigDecimal saldoDolares = BigDecimal.ZERO;

	/** The saldo unificado. */
	private BigDecimal saldoUnificado = BigDecimal.ZERO;

	/** The saldo caja ahorro pesos. */
	private BigDecimal saldoCajaAhorroPesos = BigDecimal.ZERO;

	/** The saldo cuenta sueldo pesos. */
	private BigDecimal saldoCuentaSueldoPesos = BigDecimal.ZERO;

	/** The saldo cuenta corriente pesos. */
	private BigDecimal saldoCuentaCorrientePesos = BigDecimal.ZERO;

	/** The saldo caja ahorro dolares. */
	private BigDecimal saldoCajaAhorroDolares = BigDecimal.ZERO;

	/** The disponible descubierto. */
	private BigDecimal disponibleDescubierto = BigDecimal.ZERO;

	/** The limite descubierto. */
	private BigDecimal limiteDescubierto = BigDecimal.ZERO;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The cbu. */
	private String cbu;

	/** The cuenta traspaso automatico. */
	private boolean cuentaTraspasoAutomatico;

	/** The cuenta principal. */
	private boolean cuentaPrincipal;

	/** The cuenta cerrada. */
	private boolean cuentaCerrada;

	/** The cuenta unica. */
	private boolean cuentaUnica;

	/** The convenio pas. */
	private boolean convenioPAS = false;

	/** The favorita. */
	private boolean favorita;

	/** The alias. */
	private String alias;

	/** The codigo titularidad. */
	private String codigoTitularidad;

	/** The cliente. */
	private Cliente cliente;

	/** The fecha Desde Movimiento. */
	private String fechaDesdeMovimiento;

	/** The fecha Hasta Movimiento. */
	private String fechaHastaMovimiento;

	/** The The Solicitud Traspaso Activo. */
	private Boolean showSolicitarTraspaso = Boolean.FALSE;

	/** The Realizacion Traspaso Activo. */
	private Boolean showRealizarTraspaso = Boolean.FALSE;

	/** The Modo Nocturno / diurno. */
	private Boolean modoNocturno = Boolean.FALSE;

	/** The Traspaso Automatico Activo. */
	private Boolean traspasoAutomaticoActivo;

	/** The Traspaso Automatico Solicitud Pendiente. */
	private Boolean solicitudPendienteTraspasoAutomatico;

	/** The disponible cuenta pesos. */
	private BigDecimal disponibleCuentaPesos;

	/** The indicador sobregiro. */
	private String indicadorSobregiro;
	
	/** The motivo cuenta cerrada. */
	private String motivoCuentaCerrada;
	
	/** The motivo cuenta cerrada. */
	private String codigoRetornoExtendido;

	private Boolean isRepatriacion;

	private String productoAlt;

	private String subProductoAlt;

	/**
	 * Gets the saldo descubierto disponible.
	 *
	 * @return the saldo descubierto disponible
	 */
	public BigDecimal getSaldoDescubiertoDisponible() {
		BigDecimal saldoDescubierto = limiteDescubierto;
		if (BigDecimal.ZERO.compareTo(saldoPesos) > 0 || BigDecimal.ZERO.compareTo(saldoUnificado) > 0) {
			saldoDescubierto = disponibleCuentaPesos;
		}
		return saldoDescubierto;
	}

	/**
	 * Checks if is descubierto utilizado.
	 *
	 * @return the boolean
	 */
	public Boolean isDescubiertoUtilizado() {
		Boolean isDescubiertoUtilizado = false;
		if (disponibleDescubierto.compareTo(limiteDescubierto) != 0) {
			isDescubiertoUtilizado = true;
		}
		return isDescubiertoUtilizado;
	}

	/**
	 * Mostrar descubierto.
	 *
	 * @return the boolean
	 */
	public Boolean mostrarDescubierto() {
		Boolean mostrarDescubierto = true;
		if (limiteDescubierto.compareTo(BigDecimal.ZERO) == 0) {
			mostrarDescubierto = false;
		}
		return mostrarDescubierto;
	}

	/**
	 * Checks if is favorita.
	 *
	 * @return the favorita
	 */
	public boolean isFavorita() {
		return favorita;
	}

	/**
	 * Sets the favorita.
	 *
	 * @param favorita
	 *            the favorita to set
	 */
	public void setFavorita(boolean favorita) {
		this.favorita = favorita;
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
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the limite descubierto.
	 *
	 * @return the limiteDescubierto
	 */
	public BigDecimal getLimiteDescubierto() {
		return limiteDescubierto;
	}

	/**
	 * Setter para limite descubierto.
	 *
	 * @param limiteDescubierto
	 *            the limiteDescubierto to set
	 */
	public void setLimiteDescubierto(BigDecimal limiteDescubierto) {
		this.limiteDescubierto = limiteDescubierto;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Setter para cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Checks if is cuenta unica.
	 *
	 * @return the cuentaUnica
	 */
	public boolean isCuentaUnica() {
		return cuentaUnica;
	}

	/**
	 * Setter para cuenta unica.
	 *
	 * @param cuentaUnica
	 *            the cuentaUnica to set
	 */
	public void setCuentaUnica(boolean cuentaUnica) {
		this.cuentaUnica = cuentaUnica;
	}

	/**
	 * Checks if is cuenta cerrada.
	 *
	 * @return the cuentaCerrada
	 */
	public boolean isCuentaCerrada() {
		return cuentaCerrada;
	}

	/**
	 * Setter para cuenta cerrada.
	 *
	 * @param cuentaCerrada
	 *            the cuentaCerrada to set
	 */
	public void setCuentaCerrada(boolean cuentaCerrada) {
		this.cuentaCerrada = cuentaCerrada;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nroSucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Gets the numero cuenta Y sucursal.
	 *
	 * @return the numero cuenta Y sucursal
	 */
	public String getNumeroCuentaYSucursal() {
		String sucursal = ISBANStringUtils.formatearSucursal(nroSucursal);
		String numeroCuenta = ISBANStringUtils.formatearNumeroCuenta(nroCuentaProducto);

		return sucursal + SEPARATOR + numeroCuenta;
	}

	/**
	 * Setter para nro sucursal.
	 *
	 * @param nroSucursal
	 *            the nroSucursal to set
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nroCuentaProducto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Setter para nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the nroCuentaProducto to set
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public BigDecimal getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Setter para saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(BigDecimal saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldoDolares
	 */
	public BigDecimal getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Setter para saldo dolares.
	 *
	 * @param saldoDolares
	 *            the saldoDolares to set
	 */
	public void setSaldoDolares(BigDecimal saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the saldo unificado.
	 *
	 * @return the saldoUnificado
	 */
	public BigDecimal getSaldoUnificado() {
		return saldoUnificado;
	}

	/**
	 * Setter para saldo unificado.
	 *
	 * @param saldoUnificado
	 *            the saldoUnificado to set
	 */
	public void setSaldoUnificado(BigDecimal saldoUnificado) {
		this.saldoUnificado = saldoUnificado;
	}

	/**
	 * Gets the saldo caja ahorro pesos.
	 *
	 * @return the saldoCajaAhorroPesos
	 */
	public BigDecimal getSaldoCajaAhorroPesos() {
		return saldoCajaAhorroPesos;
	}

	/**
	 * Setter para saldo caja ahorro pesos.
	 *
	 * @param saldoCajaAhorroPesos
	 *            the saldoCajaAhorroPesos to set
	 */
	public void setSaldoCajaAhorroPesos(BigDecimal saldoCajaAhorroPesos) {
		this.saldoCajaAhorroPesos = saldoCajaAhorroPesos;
	}

	/**
	 * Gets the saldo cuenta sueldo pesos.
	 *
	 * @return the saldoCuentaSueldoPesos
	 */
	public BigDecimal getSaldoCuentaSueldoPesos() {
		return saldoCuentaSueldoPesos;
	}

	/**
	 * Setter para saldo cuenta sueldo pesos.
	 *
	 * @param saldoCuentaSueldoPesos
	 *            the saldoCuentaSueldoPesos to set
	 */
	public void setSaldoCuentaSueldoPesos(BigDecimal saldoCuentaSueldoPesos) {
		this.saldoCuentaSueldoPesos = saldoCuentaSueldoPesos;
	}

	/**
	 * Gets the saldo cuenta corriente pesos.
	 *
	 * @return the saldoCuentaCorrientePesos
	 */
	public BigDecimal getSaldoCuentaCorrientePesos() {
		return saldoCuentaCorrientePesos;
	}

	/**
	 * Setter para saldo cuenta corriente pesos.
	 *
	 * @param saldoCuentaCorrientePesos
	 *            the saldoCuentaCorrientePesos to set
	 */
	public void setSaldoCuentaCorrientePesos(BigDecimal saldoCuentaCorrientePesos) {
		this.saldoCuentaCorrientePesos = saldoCuentaCorrientePesos;
	}

	/**
	 * Gets the saldo caja ahorro dolares.
	 *
	 * @return the saldoCajaAhorroDolares
	 */
	public BigDecimal getSaldoCajaAhorroDolares() {
		return saldoCajaAhorroDolares;
	}

	/**
	 * Setter para saldo caja ahorro dolares.
	 *
	 * @param saldoCajaAhorroDolares
	 *            the saldoCajaAhorroDolares to set
	 */
	public void setSaldoCajaAhorroDolares(BigDecimal saldoCajaAhorroDolares) {
		this.saldoCajaAhorroDolares = saldoCajaAhorroDolares;
	}

	/**
	 * Gets the disponible descubierto.
	 *
	 * @return the disponibleDescubierto
	 */
	public BigDecimal getDisponibleDescubierto() {
		return disponibleDescubierto;
	}

	/**
	 * Setter para disponible descubierto.
	 *
	 * @param disponibleDescubierto
	 *            the disponibleDescubierto to set
	 */
	public void setDisponibleDescubierto(BigDecimal disponibleDescubierto) {
		this.disponibleDescubierto = disponibleDescubierto;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Setter para tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * Setter para cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Checks if is cuenta traspaso automatico.
	 *
	 * @return the cuentaTraspasoAutomatico
	 */
	public boolean isCuentaTraspasoAutomatico() {
		return cuentaTraspasoAutomatico;
	}

	/**
	 * Setter para cuenta traspaso automatico.
	 *
	 * @param cuentaTraspasoAutomatico
	 *            the cuentaTraspasoAutomatico to set
	 */
	public void setCuentaTraspasoAutomatico(boolean cuentaTraspasoAutomatico) {
		this.cuentaTraspasoAutomatico = cuentaTraspasoAutomatico;
	}

	/**
	 * Setter para cuenta principal.
	 *
	 * @param cuentaPrincipal
	 *            the cuentaPrincipal to set
	 */
	public void setCuentaPrincipal(boolean cuentaPrincipal) {
		this.cuentaPrincipal = cuentaPrincipal;
	}

	/**
	 * Checks if is convenio pas.
	 *
	 * @return the convenioPAS
	 */
	public boolean isConvenioPAS() {
		return convenioPAS;
	}

	/**
	 * Setter para convenio pas.
	 *
	 * @param convenioPAS
	 *            the convenioPAS to set
	 */
	public void setConvenioPAS(boolean convenioPAS) {
		this.convenioPAS = convenioPAS;
	}

	/**
	 * Gets the codigo titularidad.
	 *
	 * @return the codigo titularidad
	 */
	public String getCodigoTitularidad() {
		return codigoTitularidad;
	}

	/**
	 * Setter para codigo titularidad.
	 *
	 * @param codigoTitularidad
	 *            el nuevo codigo titularidad
	 */
	public void setCodigoTitularidad(String codigoTitularidad) {
		this.codigoTitularidad = codigoTitularidad;
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
	 * Gets the show solicitar traspaso.
	 *
	 * @return the showSolicitarTraspaso
	 */
	public Boolean getShowSolicitarTraspaso() {
		return showSolicitarTraspaso;
	}

	/**
	 * Sets the show solicitar traspaso.
	 *
	 * @param showSolicitarTraspaso
	 *            the showSolicitarTraspaso to set
	 */
	public void setShowSolicitarTraspaso(Boolean showSolicitarTraspaso) {
		this.showSolicitarTraspaso = showSolicitarTraspaso;
	}

	/**
	 * Gets the show realizar traspaso.
	 *
	 * @return the showRealizarTraspaso
	 */
	public Boolean getShowRealizarTraspaso() {
		return showRealizarTraspaso;
	}

	/**
	 * Sets the show realizar traspaso.
	 *
	 * @param showRealizarTraspaso
	 *            the showRealizarTraspaso to set
	 */
	public void setShowRealizarTraspaso(Boolean showRealizarTraspaso) {
		this.showRealizarTraspaso = showRealizarTraspaso;
	}

	/**
	 * Gets the modo nocturno.
	 *
	 * @return the modoNocturno
	 */
	public Boolean getModoNocturno() {
		return modoNocturno;
	}

	/**
	 * Sets the modo nocturno.
	 *
	 * @param modoNocturno
	 *            the modoNocturno to set
	 */
	public void setModoNocturno(Boolean modoNocturno) {
		this.modoNocturno = modoNocturno;
	}

	/**
	 * Gets the solicitud pendiente traspaso automatico.
	 *
	 * @return the solicitudPendienteTraspasoAutomatico
	 */
	public Boolean getSolicitudPendienteTraspasoAutomatico() {
		return solicitudPendienteTraspasoAutomatico;
	}

	/**
	 * Sets the solicitud pendiente traspaso automatico.
	 *
	 * @param solicitudPendienteTraspasoAutomatico
	 *            the solicitudPendienteTraspasoAutomatico to set
	 */
	public void setSolicitudPendienteTraspasoAutomatico(Boolean solicitudPendienteTraspasoAutomatico) {
		this.solicitudPendienteTraspasoAutomatico = solicitudPendienteTraspasoAutomatico;
	}

	/**
	 * Gets the traspaso automatico activo.
	 *
	 * @return the traspasoAutomaticoActivo
	 */
	public Boolean getTraspasoAutomaticoActivo() {
		return traspasoAutomaticoActivo;
	}

	/**
	 * Sets the traspaso automatico activo.
	 *
	 * @param traspasoAutomaticoActivo
	 *            the traspasoAutomaticoActivo to set
	 */
	public void setTraspasoAutomaticoActivo(Boolean traspasoAutomaticoActivo) {
		this.traspasoAutomaticoActivo = traspasoAutomaticoActivo;
	}

	/**
	 * Gets the indicador sobregiro.
	 *
	 * @return the indicador sobregiro
	 */
	public String getIndicadorSobregiro() {
		return indicadorSobregiro;
	}

	/**
	 * Sets the indicador sobregiro.
	 *
	 * @param indicadorSobregiro
	 *            the new indicador sobregiro
	 */
	public void setIndicadorSobregiro(String indicadorSobregiro) {
		this.indicadorSobregiro = indicadorSobregiro;
	}

	/**
	 * Checks if is cuenta principal.
	 *
	 * @return the cuentaPrincipal
	 */
	public boolean isCuentaPrincipal() {
		return cuentaPrincipal;
	}

	public Boolean isRepatriacion() {
		return isRepatriacion;
	}

	public void setIsRepatriacion(Boolean isRepatriacion) {
		this.isRepatriacion = isRepatriacion;
	}

	public String getProductoAlt() {
		return productoAlt;
	}

	public void setProductoAlt(String productoAlt) {
		this.productoAlt = productoAlt;
	}

	public String getSubProductoAlt() {
		return subProductoAlt;
	}

	public void setSubProductoAlt(String subProductoAlt) {
		this.subProductoAlt = subProductoAlt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nroSucursal=", nroSucursal)
				.append(" nroCuentaProducto=", nroCuentaProducto).append(" saldoPesos=", saldoPesos)
				.append(" saldoDolares=", saldoDolares).append(" saldoUnificado=", saldoUnificado)
				.append(" saldoCajaAhorroPesos=", saldoCajaAhorroPesos)
				.append(" saldoCuentaSueldoPesos=", saldoCuentaSueldoPesos)
				.append(" saldoCuentaCorrientePesos=", saldoCuentaCorrientePesos)
				.append(" saldoCajaAhorroDolares=", saldoCajaAhorroDolares)
				.append(" disponibleDescubierto=", disponibleDescubierto)
				.append(" limiteDescubierto=", limiteDescubierto).append(" tipoCuenta=", tipoCuenta)
				.append(" cbu=", cbu).append(" cuentaTraspasoAutomatico=", cuentaTraspasoAutomatico)
				.append(" cuentaPrincipal=", cuentaPrincipal).append(" cuentaCerrada=", cuentaCerrada)
				.append(" cuentaUnica=", cuentaUnica).append(" convenioPAS=", convenioPAS)
				.append(" favorita=", favorita).append(" alias=", alias)
				.append(" codigoTitularidad=", codigoTitularidad).append(" cliente=", cliente)
				.append(" fechaDesdeMovimiento=", fechaDesdeMovimiento)
				.append(" fechaHastaMovimiento=", fechaHastaMovimiento).toString();
	}

	/**
	 * Gets the disponible cuenta pesos.
	 *
	 * @return the disponible cuenta pesos
	 */
	public BigDecimal getDisponibleCuentaPesos() {
		return disponibleCuentaPesos;
	}

	/**
	 * Sets the disponible cuenta pesos.
	 *
	 * @param disponibleCuentaPesos
	 *            the new disponible cuenta pesos
	 */
	public void setDisponibleCuentaPesos(BigDecimal disponibleCuentaPesos) {
		this.disponibleCuentaPesos = disponibleCuentaPesos;
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

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

}
