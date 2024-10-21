/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import org.beanio.annotation.Field;

// TODO: Auto-generated Javadoc
/**
 * The Class PrestamoCanceladoOutEntity.
 */
public class PrestamoCanceladoOutEntity {

	/** The sucursal. */
	/* PE_COD_OFI A04 Sucursal */
	@Field
	private String sucursal;

	/** The numero de contrato. */
	/* PE_NUM_CON A12 Numero de contrato */
	@Field
	private String numeroDeContrato;

	/** The codigo producto altair. */
	/* PE_COD_PRO A02 Codigo de producto Altair */
	@Field
	private String codigoProductoAltair;

	/** The codigo subproducto altair. */
	/* PE_COD_SUB A04 Codigo de subproducto Altair */
	@Field
	private String codigoSubproductoAltair;

	/** The calidad participacion. */
	/* PE_CAL_PAR A02 Calidad participacion */
	@Field
	private String calidadParticipacion;

	/** The orden de participacion. */
	/* PE_ORD_PAR A03 Orden de participacion */
	@Field
	private String ordenDeParticipacion;

	/** The fecha baja del contrato. */
	/* FEC_BAJA_CON A10 Fecha de baja del contrato */
	@Field
	private String fechaBajaDelContrato;

	/** The estado relacion. */
	/* PE_ESTREL A01 Estado de la relacion */
	@Field
	private String estadoRelacion;

	/** The responsabilidad intervencion. */
	/* PE_RES_INT N3D2 Responsabilidad en la intervencion */
	@Field
	private String responsabilidadIntervencion;

	/** The baja motivo. */
	/* PE_MOT_BAJ A02 Motivo de baja */
	@Field
	private String bajaMotivo;

	/** The fecha cierre. */
	/* PE_HSTAMP A26 Timestamp */
	@Field
	private String fechaCierre;

	/** The jerarquia. */
	/* PE_MAR_PAQ A01 Jerarquia */
	@Field
	private String jerarquia;

	/** The fecha alta contrato. */
	/* FEC_ALTA_CON A10 Fecha de alta del contrato */
	@Field
	private String fechaAltaContrato;

	/** The tipo de cuenta. */
	/* TIPO_CTA N02 Tipo de cuenta */
	@Field
	private String tipoDeCuenta;

	/** The cbu. */
	/* CBU X22 CBU */
	@Field
	private String cbu;

	/** The moria. */
	/* MORIA A01 S o N (indica si paso por Moria) */
	@Field
	private String moria;

	/** The tipo de prestamo. */
	/* TIPO_PRESTAMO A01 0,1,2,3,4,5,6,7,8,9 */
	@Field
	private String tipoDePrestamo;

	/** The Sucursal continuadora. */
	/* Sucursal continuadora  A04*/
	@Field
	private String sucursalContinuadora;

	/** The Nro Contrato Continuador. */
	/* Nro Contrato Continuador  A12*/
	@Field
	private String nroContratoContinuador;
	
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
	 * Gets the numero de contrato.
	 *
	 * @return the numero de contrato
	 */
	public String getNumeroDeContrato() {
		return numeroDeContrato;
	}

	/**
	 * Sets the numero de contrato.
	 *
	 * @param numeroDeContrato
	 *            the new numero de contrato
	 */
	public void setNumeroDeContrato(String numeroDeContrato) {
		this.numeroDeContrato = numeroDeContrato;
	}

	/**
	 * Gets the codigo producto altair.
	 *
	 * @return the codigo producto altair
	 */
	public String getCodigoProductoAltair() {
		return codigoProductoAltair;
	}

	/**
	 * Sets the codigo producto altair.
	 *
	 * @param codigoProductoAltair
	 *            the new codigo producto altair
	 */
	public void setCodigoProductoAltair(String codigoProductoAltair) {
		this.codigoProductoAltair = codigoProductoAltair;
	}

	/**
	 * Gets the codigo subproducto altair.
	 *
	 * @return the codigo subproducto altair
	 */
	public String getCodigoSubproductoAltair() {
		return codigoSubproductoAltair;
	}

	/**
	 * Sets the codigo subproducto altair.
	 *
	 * @param codigoSubproductoAltair
	 *            the new codigo subproducto altair
	 */
	public void setCodigoSubproductoAltair(String codigoSubproductoAltair) {
		this.codigoSubproductoAltair = codigoSubproductoAltair;
	}

	/**
	 * Gets the calidad participacion.
	 *
	 * @return the calidad participacion
	 */
	public String getCalidadParticipacion() {
		return calidadParticipacion;
	}

	/**
	 * Sets the calidad participacion.
	 *
	 * @param calidadParticipacion
	 *            the new calidad participacion
	 */
	public void setCalidadParticipacion(String calidadParticipacion) {
		this.calidadParticipacion = calidadParticipacion;
	}

	/**
	 * Gets the orden de participacion.
	 *
	 * @return the orden de participacion
	 */
	public String getOrdenDeParticipacion() {
		return ordenDeParticipacion;
	}

	/**
	 * Sets the orden de participacion.
	 *
	 * @param ordenDeParticipacion
	 *            the new orden de participacion
	 */
	public void setOrdenDeParticipacion(String ordenDeParticipacion) {
		this.ordenDeParticipacion = ordenDeParticipacion;
	}

	/**
	 * Gets the fecha baja del contrato.
	 *
	 * @return the fecha baja del contrato
	 */
	public String getFechaBajaDelContrato() {
		return fechaBajaDelContrato;
	}

	/**
	 * Sets the fecha baja del contrato.
	 *
	 * @param fechaBajaDelContrato
	 *            the new fecha baja del contrato
	 */
	public void setFechaBajaDelContrato(String fechaBajaDelContrato) {
		this.fechaBajaDelContrato = fechaBajaDelContrato;
	}

	/**
	 * Gets the estado relacion.
	 *
	 * @return the estado relacion
	 */
	public String getEstadoRelacion() {
		return estadoRelacion;
	}

	/**
	 * Sets the estado relacion.
	 *
	 * @param estadoRelacion
	 *            the new estado relacion
	 */
	public void setEstadoRelacion(String estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}

	/**
	 * Gets the responsabilidad intervencion.
	 *
	 * @return the responsabilidad intervencion
	 */
	public String getResponsabilidadIntervencion() {
		return responsabilidadIntervencion;
	}

	/**
	 * Sets the responsabilidad intervencion.
	 *
	 * @param responsabilidadIntervencion
	 *            the new responsabilidad intervencion
	 */
	public void setResponsabilidadIntervencion(String responsabilidadIntervencion) {
		this.responsabilidadIntervencion = responsabilidadIntervencion;
	}

	/**
	 * Gets the baja motivo.
	 *
	 * @return the baja motivo
	 */
	public String getBajaMotivo() {
		return bajaMotivo;
	}

	/**
	 * Sets the baja motivo.
	 *
	 * @param bajaMotivo
	 *            the new baja motivo
	 */
	public void setBajaMotivo(String bajaMotivo) {
		this.bajaMotivo = bajaMotivo;
	}

	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fecha cierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre
	 *            the new fecha cierre
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the jerarquia.
	 *
	 * @return the jerarquia
	 */
	public String getJerarquia() {
		return jerarquia;
	}

	/**
	 * Sets the jerarquia.
	 *
	 * @param jerarquia
	 *            the new jerarquia
	 */
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	/**
	 * Gets the fecha alta contrato.
	 *
	 * @return the fecha alta contrato
	 */
	public String getFechaAltaContrato() {
		return fechaAltaContrato;
	}

	/**
	 * Sets the fecha alta contrato.
	 *
	 * @param fechaAltaContrato
	 *            the new fecha alta contrato
	 */
	public void setFechaAltaContrato(String fechaAltaContrato) {
		this.fechaAltaContrato = fechaAltaContrato;
	}

	/**
	 * Gets the tipo de cuenta.
	 *
	 * @return the tipo de cuenta
	 */
	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	/**
	 * Sets the tipo de cuenta.
	 *
	 * @param tipoDeCuenta
	 *            the new tipo de cuenta
	 */
	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
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
	 * Gets the moria.
	 *
	 * @return the moria
	 */
	public String getMoria() {
		return moria;
	}

	/**
	 * Sets the moria.
	 *
	 * @param moria
	 *            the new moria
	 */
	public void setMoria(String moria) {
		this.moria = moria;
	}

	/**
	 * Gets the tipo de prestamo.
	 *
	 * @return the tipo de prestamo
	 */
	public String getTipoDePrestamo() {
		return tipoDePrestamo;
	}

	/**
	 * Sets the tipo de prestamo.
	 *
	 * @param tipoDePrestamo
	 *            the new tipo de prestamo
	 */
	public void setTipoDePrestamo(String tipoDePrestamo) {
		this.tipoDePrestamo = tipoDePrestamo;
	}

	
	/**
	 * Gets the sucursal continuadora.
	 *
	 * @return the sucursal continuadora
	 */
	public String getSucursalContinuadora() {
		return sucursalContinuadora;
	}

	/**
	 * Sets the sucursal continuadora.
	 *
	 * @param sucursalContinuadora the new sucursal continuadora
	 */
	public void setSucursalContinuadora(String sucursalContinuadora) {
		this.sucursalContinuadora = sucursalContinuadora;
	}

	/**
	 * Gets the nro contrato continuador.
	 *
	 * @return the nro contrato continuador
	 */
	public String getNroContratoContinuador() {
		return nroContratoContinuador;
	}

	/**
	 * Sets the nro contrato continuador.
	 *
	 * @param nroContratoContinuador the new nro contrato continuador
	 */
	public void setNroContratoContinuador(String nroContratoContinuador) {
		this.nroContratoContinuador = nroContratoContinuador;
	}
	
}
