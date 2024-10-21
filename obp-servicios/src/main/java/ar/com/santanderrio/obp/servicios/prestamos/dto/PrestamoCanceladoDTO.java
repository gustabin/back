/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.sun.istack.NotNull;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaPrestamosEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoCanceladoOutEntity;

/**
 * The Class PrestamoCanceladoDTO.
 */
public class PrestamoCanceladoDTO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoCanceladoDTO.class);

	/** The sucursal. */
	@NotNull
	private String sucursal;

	/** The numero de contrato. */
	/* PE_NUM_CON A12 Numero de contrato */
	@NotNull
	private String numeroDeContrato;

	/** The codigo producto altair. */
	/* PE_COD_PRO A02 Codigo de producto Altair */
	@NotNull
	private String codigoProductoAltair;
	/* PE_COD_SUB A04 Codigo de subproducto Altair */

	/** The codigo subproducto altair. */
	@NotNull
	private String codigoSubproductoAltair;

	/** The calidad participacion. */
	/* PE_CAL_PAR A02 Calidad participacion */
	@NotNull
	private String calidadParticipacion;

	/** The orden de participacion. */
	/* PE_ORD_PAR A03 Orden de participacion */
	@NotNull
	private String ordenDeParticipacion;

	/** The fecha baja del contrato. */
	/* FEC_BAJA_CON A10 Fecha de baja del contrato */
	@NotNull
	private String fechaBajaDelContrato;

	/** The fecha baja del contrato mobile. */
	private String fechaBajaDelContratoMobile;

	/** The estado relacion. */
	/* PE_ESTREL A01 Estado de la relacion */
	@NotNull
	private String estadoRelacion;

	/** The responsabilidad intervencion. */
	/* PE_RES_INT N3D2 Responsabilidad en la intervencion */
	@NotNull
	private String responsabilidadIntervencion;

	/** The baja motivo. */
	/* PE_MOT_BAJ A02 Motivo de baja */
	@NotNull
	private String bajaMotivo;

	/** The fecha cierre. */
	/* PE_HSTAMP A26 Timestamp */
	@NotNull
	private String fechaCierre;

	/** The jerarquia. */
	/* PE_MAR_PAQ A01 Jerarquia */
	@NotNull
	private String jerarquia;

	/** The fecha alta contrato. */
	/* FEC_ALTA_CON A10 Fecha de alta del contrato */
	@NotNull
	private String fechaAltaContrato;

	/** The fecha alta contrato mobile. */
	private String fechaAltaContratoMobile;

	/** The tipo de cuenta. */
	/* TIPO_CTA N02 Tipo de cuenta */
	@NotNull
	private TipoCuentaPrestamosEnum tipoDeCuenta;

	/** The cbu. */
	/* CBU X22 CBU */
	@NotNull
	private String cbu;

	/** The moria. */
	/* MORIA A01 S o N (indica si paso por Moria) */
	@NotNull
	private String moria;

	/** The tipo de prestamo. */
	/* TIPO_PRESTAMO A01 0,1,2,3,4,5,6,7,8,9 */
	@NotNull
	private TipoPrestamoEnum tipoDePrestamo;

	/** The plazo. */
	private Integer plazo;

	/** The importe total. */
	private BigDecimal importeTotal;

	/** The motivo prestamo. */
	private String motivoPrestamo;

	/**
	 * Instantiates a new prestamo cancelado DTO.
	 */
	public PrestamoCanceladoDTO() {
		super();
	}

	/**
	 * Instantiates a new prestamo cancelado DTO.
	 *
	 * @param prestamoCanceladoEntity
	 *            the prestamo cancelado entity
	 * @param preformalizacion
	 *            the preformalizacion
	 * @param motivoPrestamo
	 *            the motivo prestamo
	 */
	public PrestamoCanceladoDTO(PrestamoCanceladoOutEntity prestamoCanceladoEntity, PreFormalizacion preformalizacion,
			String motivoPrestamo) {
		BeanUtils.copyProperties(prestamoCanceladoEntity, this);

		String fechaBajaContratoString = prestamoCanceladoEntity.getFechaBajaDelContrato().replace("-", "").replace("/", "");
		this.fechaBajaDelContrato = fechaBajaContratoString.substring(6, fechaBajaContratoString.length()) + "/" + 
				fechaBajaContratoString.substring(4, 6) + "/" + fechaBajaContratoString.substring(0, 4); 

		this.fechaBajaDelContratoMobile = fechaBajaContratoString.substring(6, fechaBajaContratoString.length()) + "/" +
				fechaBajaContratoString.substring(4, 6) + "/" + fechaBajaContratoString.substring(2, 4);

		String fechaAltaContratoString = prestamoCanceladoEntity.getFechaAltaContrato().replace("-", "").replace("/", "");
		this.fechaAltaContrato = fechaAltaContratoString.substring(6, fechaAltaContratoString.length()) + "/" + 
				fechaAltaContratoString.substring(4, 6) + "/" + fechaAltaContratoString.substring(0, 4);
		this.fechaAltaContratoMobile = fechaAltaContratoString.substring(6, fechaAltaContratoString.length()) + "/" + 
				fechaAltaContratoString.substring(4, 6) + "/" + fechaAltaContratoString.substring(2, 4);

		this.tipoDePrestamo = TipoPrestamoEnum.fromIdString(prestamoCanceladoEntity.getTipoDePrestamo());
		this.tipoDeCuenta = TipoCuentaPrestamosEnum.fromCodigo(prestamoCanceladoEntity.getTipoDeCuenta());
		this.motivoPrestamo = motivoPrestamo;

		if (preformalizacion != null) {
			this.plazo = Integer.valueOf(preformalizacion.getPlazo());
			try {
				this.importeTotal = ISBANStringUtils
						.convertirStrToBigDecimal(preformalizacion.getPrestamoDebitoAdherido().getImpconce(), 4);
			} catch (ImporteConvertException e) {
				LOGGER.error("Error al tratar de convertir importe total.", e);
			}
		}
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
	 * Et jerarquia.
	 *
	 * @return the string
	 */
	public String etJerarquia() {
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
	 * Gets the jerarquia.
	 *
	 * @return the jerarquia
	 */
	public String getJerarquia() {
		return jerarquia;
	}

	/**
	 * Gets the tipo de cuenta.
	 *
	 * @return the tipo de cuenta
	 */
	public TipoCuentaPrestamosEnum getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	/**
	 * Sets the tipo de cuenta.
	 *
	 * @param tipoDeCuenta
	 *            the new tipo de cuenta
	 */
	public void setTipoDeCuenta(TipoCuentaPrestamosEnum tipoDeCuenta) {
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
	public TipoPrestamoEnum getTipoDePrestamo() {
		return tipoDePrestamo;
	}

	/**
	 * Sets the tipo de prestamo.
	 *
	 * @param tipoDePrestamo
	 *            the new tipo de prestamo
	 */
	public void setTipoDePrestamo(TipoPrestamoEnum tipoDePrestamo) {
		this.tipoDePrestamo = tipoDePrestamo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return "Super " + tipoDePrestamo.getDescripcion();
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the importe total.
	 *
	 * @return the importe total
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
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
	 * Gets the fecha alta contrato mobile.
	 *
	 * @return the fecha alta contrato mobile
	 */
	public String getFechaAltaContratoMobile() {
		return fechaAltaContratoMobile;
	}

	/**
	 * Sets the fecha alta contrato mobile.
	 *
	 * @param fechaAltaContratoMobile
	 *            the new fecha alta contrato mobile
	 */
	public void setFechaAltaContratoMobile(String fechaAltaContratoMobile) {
		this.fechaAltaContratoMobile = fechaAltaContratoMobile;
	}

	/**
	 * Gets the numer prestamo.
	 *
	 * @return the numer prestamo
	 */
	public String getNumerPrestamo() {
		int length = this.getNumeroDeContrato().length();
		return this.getSucursal() + "-" + this.getNumeroDeContrato().substring(0, length - 1) + "/"
				+ this.getNumeroDeContrato().substring(length - 1, length);
	}

	/**
	 * Gets the fecha baja del contrato mobile.
	 *
	 * @return the fecha baja del contrato mobile
	 */
	public String getFechaBajaDelContratoMobile() {
		return fechaBajaDelContratoMobile;
	}

	/**
	 * Sets the fecha baja del contrato mobile.
	 *
	 * @param fechaBajaDelContratoMobile
	 *            the new fecha baja del contrato mobile
	 */
	public void setFechaBajaDelContratoMobile(String fechaBajaDelContratoMobile) {
		this.fechaBajaDelContratoMobile = fechaBajaDelContratoMobile;
	}

	/**
	 * Gets the motivo prestamo.
	 *
	 * @return the motivo prestamo
	 */
	public String getMotivoPrestamo() {
		return motivoPrestamo;
	}

	/**
	 * Sets the motivo prestamo.
	 *
	 * @param destinoFondo
	 *            the new motivo prestamo
	 */
	public void setMotivoPrestamo(String destinoFondo) {
		this.motivoPrestamo = destinoFondo;

	}

}
