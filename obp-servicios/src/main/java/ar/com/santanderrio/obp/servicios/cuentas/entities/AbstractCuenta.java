/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class AbstractCuenta.
 */
public abstract class AbstractCuenta extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TIPO_CUENTA_VISA. */
	private static final String TIPO_CUENTA_VISA = "07";

	/** The Constant TIPO_CUENTA_AMEX. */
	private static final String TIPO_CUENTA_AMEX = "42";

	/** The Constatn TIPO_CUENTA_MASTER. */
	private static final String TIPO_CUENTA_MASTER = "06";
	
	/** The Constant JERARQUIA_PRINCIPAL. */
	private static final String JERARQUIA_PRINCIPAL = "P";

	public static final Integer SUBPRODUCTO_ALTAIR_REPATRIACION = 7;

	public static final Integer PRODUCTO_ALTAIR_REPATRIACION = 3;

	/** The Constant TAG_FIN. */
	private static final String TAG_FIN = "]";

	/** The Constant CUENTAS_TAG. */
	private static final String CUENTAS_TAG = "cuentas[";

	/** The cliente. */
	private Cliente cliente;

	/** The jerarquia cuenta. */
	private String jerarquiaCuenta = "";

	/** The tipo cuenta. */
	private String tipoCuenta = "";

	/** The tipo cuenta enum. */
    protected TipoCuenta tipoCuentaEnum;

	/** The cbu. */
	private String cbu = "";

	/** The nro sucursal. */
    protected String nroSucursal = "";

	/** The nro cuenta producto. */
    protected String nroCuentaProducto = "";

	/** The producto altair. */
	private String productoAltair = "";

	/** The alias. */
	private String alias = "";

	/** The is favorita. */
	private Boolean isFavorita = false;

	private Boolean isRepatriacion = false;

	/** The is estado Traspaso Automatico. */
	private Boolean solicitudPendienteTraspasoAutomatico;

	/** The is estado solicitud Traspaso Automatico. */
	private Boolean traspasoAutomaticoActivo;

	/** The indicador sobregiro. */
	private String indicadorSobregiro;

	/**
	 * Gets the producto altair.
	 *
	 * @return the productoAltair
	 */
	public String getProductoAltair() {
		return productoAltair;
	}

	/**
	 * Sets the producto altair.
	 *
	 * @param productoAltair
	 *            the productoAltair to set
	 */
	public void setProductoAltair(String productoAltair) {
		this.productoAltair = productoAltair;
	}

    /** es el primer caracter de calidadDeParticipacion. */
    protected String codigoTitularidad = "";

	/**
	 * Checks if is cuenta cerrada.
	 *
	 * @return the boolean
	 */
	public abstract Boolean isCuentaCerrada();

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
	 * Gets the nro sucursal.
	 *
	 * @return the nroSucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 * @deprecated: Utilizar getTipoCuentaEnum() Gets the tipo cuenta.
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 * @deprecated: Utilizar setTipoCuentaEnum(TipoCuenta tipoCuentaEnum) Setter
	 *              para tipo cuenta.
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
	 * Gets the jerarquia cuenta.
	 *
	 * @return the jerarquiaCuenta
	 */
	public String getJerarquiaCuenta() {
		return jerarquiaCuenta;
	}

	/**
	 * Setter para jerarquia cuenta.
	 *
	 * @param jerarquiaCuenta
	 *            the jerarquiaCuenta to set
	 */
	public void setJerarquiaCuenta(String jerarquiaCuenta) {
		this.jerarquiaCuenta = jerarquiaCuenta;
	}

	/**
	 * Gets the codigo titularidad.
	 *
	 * @return the codigoTitularidad
	 */
	public String getCodigoTitularidad() {
		return codigoTitularidad;
	}

	/**
	 * Setter para codigo titularidad.
	 *
	 * @param codigoTitularidad
	 *            the codigoTitularidad to set
	 */
	public void setCodigoTitularidad(String codigoTitularidad) {
		this.codigoTitularidad = codigoTitularidad;
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
	 * Gets the checks if is favorita.
	 *
	 * @return the isFavorita
	 */
	public Boolean getIsFavorita() {
		return isFavorita;
	}

	/**
	 * Sets the checks if is favorita.
	 *
	 * @param isFavorita
	 *            the isFavorita to set
	 */
	public void setIsFavorita(Boolean isFavorita) {
		this.isFavorita = isFavorita;
	}

	public Boolean isRepatriacion() {
		return isRepatriacion;
	}

	public void setRepatriacion(Boolean repatriacion) {
		isRepatriacion = repatriacion;
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
	 * @return the estadoTraspasoAutomaticoActivo
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractCuenta [cliente=");
		builder.append(cliente);
		builder.append(", tipoCuenta=");
		builder.append(tipoCuenta);
		builder.append(", nroSucursal=");
		builder.append(nroSucursal);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nroCuentaProducto == null) ? 0 : nroCuentaProducto.hashCode());
		result = prime * result + ((tipoCuenta == null) ? 0 : tipoCuenta.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractCuenta other = (AbstractCuenta) obj;
		if (nroCuentaProducto == null) {
			if (other.nroCuentaProducto != null) {
				return false;
			}
		} else if (!nroCuentaProducto.equals(other.nroCuentaProducto)) {
			return false;
		}
		if (tipoCuenta == null) {
			if (other.tipoCuenta != null) {
				return false;
			}
		} else if (!tipoCuenta.equals(other.tipoCuenta)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the tipo cuenta enum.
	 *
	 * @return the tipo cuenta enum
	 */
	public TipoCuenta getTipoCuentaEnum() {
		return tipoCuentaEnum;
	}

	/**
	 * Sets the tipo cuenta enum.
	 *
	 * @param tipoCuentaEnum
	 *            the new tipo cuenta enum
	 */
	public void setTipoCuentaEnum(TipoCuenta tipoCuentaEnum) {
		this.tipoCuentaEnum = tipoCuentaEnum;
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
	 * Checks if is cuenta unica.
	 *
	 * @return true, if is cuenta unica
	 */
	public boolean isCuentaUnica() {

		return TipoCuenta.CUENTA_UNICA_PESOS.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_UNICA.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(this.getTipoCuentaEnum());
	}

	/**
	 * Checks if is cuenta principal.
	 *
	 * @return true, if is cuenta principal
	 */
	public boolean isCuentaPrincipal() {
		if (!TIPO_CUENTA_VISA.equals(this.getTipoCuenta()) && !TIPO_CUENTA_AMEX.equals(this.getTipoCuenta()) 
				&& !TIPO_CUENTA_MASTER.equals(this.getTipoCuenta())) {
			return this.getJerarquiaCuenta().equalsIgnoreCase(JERARQUIA_PRINCIPAL);
		}
		return false;
	}

	/**
	 * Gets the numero cuenta tag.
	 *
	 * @return the numero cuenta tag
	 */
	public String getNumeroCuentaTag() {
		String sucursal = ISBANStringUtils.formatearSucursal(nroSucursal);
		String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(nroCuentaProducto);
		return CUENTAS_TAG + sucursal + "-" + nroCuenta + TAG_FIN;
	}

	/**
	 * Checks if is tipo cuenta monetaria.
	 *
	 * @return true, if is tipo cuenta monetaria
	 */
	public boolean isTipoCuentaMonetaria() {

		return TipoCuenta.CUENTA_UNICA_PESOS.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CAJA_AHORRO_DOLARES.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_UNICA.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CAJA_AHORRO_PESOS.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(this.getTipoCuentaEnum());
	}

	/**
	 * Checks if is tipo tarjeta valida.
	 *
	 * @return true, if is tipo tarjeta valida
	 */
	public Boolean isTipoTarjetaValida() {

		if (TipoCuenta.AMEX.equals(this.getTipoCuentaEnum()) || TipoCuenta.VISA.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.MASTERCARD.equals(this.getTipoCuentaEnum())
				|| TipoCuenta.VISA_RECARGABLE.equals(this.getTipoCuentaEnum())) {
			return true;
		}
		return false;

	}
}
