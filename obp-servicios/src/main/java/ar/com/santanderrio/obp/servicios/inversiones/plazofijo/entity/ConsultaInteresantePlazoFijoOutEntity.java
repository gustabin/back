/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * Objeto utilizado para retornaro datos del DAO.
 * 
 * @author juan.pablo.picate
 *
 */
@Record
public class ConsultaInteresantePlazoFijoOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The vencimiento impuesto rell 13 enteros, 2 decimales. */
	@Field
	private String vencimientoImpuestoRell;

	/** The signo vencimiento impuesto rell. */
	@Field
	private String signoVencimientoImpuestoRell;

	/** The fecha ultima liquidacion rell. */
	@Field
	private String fechaUltimaLiquidacionRell;

	/** The fecha proxima liquidacion rell. */
	@Field
	private String fechaProximaLiquidacionRell;

	/** The plazo rell. */
	@Field
	private String plazoRell;

	/** The indicador mas datos rell. */
	@Field
	private String indicadorMasDatosRell;

	/** The indicadorMasDatosRell2. */
	@Field
	private String indicadorMasDatosRell2;

	/** The cantidad repeticiones. */
	@Field()
	private Long cantRepeticiones;

	/** The tipo plazo fijo. */
	@Segment(occursRef = "cantRepeticiones")
	private List<PFInteresanteEntity> agrElementosLiquidacion = new ArrayList<PFInteresanteEntity>();

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the vencimiento impuesto rell.
	 *
	 * @return the vencimientoImpuestoRell
	 */
	public String getVencimientoImpuestoRell() {
		return vencimientoImpuestoRell;
	}

	/**
	 * Sets the vencimiento impuesto rell.
	 *
	 * @param vencimientoImpuestoRell
	 *            the vencimientoImpuestoRell to set
	 */
	public void setVencimientoImpuestoRell(String vencimientoImpuestoRell) {
		this.vencimientoImpuestoRell = vencimientoImpuestoRell;
	}

	/**
	 * Gets the signo vencimiento impuesto rell.
	 *
	 * @return the signoVencimientoImpuestoRell
	 */
	public String getSignoVencimientoImpuestoRell() {
		return signoVencimientoImpuestoRell;
	}

	/**
	 * Sets the signo vencimiento impuesto rell.
	 *
	 * @param signoVencimientoImpuestoRell
	 *            the signoVencimientoImpuestoRell to set
	 */
	public void setSignoVencimientoImpuestoRell(String signoVencimientoImpuestoRell) {
		this.signoVencimientoImpuestoRell = signoVencimientoImpuestoRell;
	}

	/**
	 * Gets the fecha ultima liquidacion rell.
	 *
	 * @return the fechaUltimaLiquidacionRell
	 */
	public String getFechaUltimaLiquidacionRell() {
		return fechaUltimaLiquidacionRell;
	}

	/**
	 * Sets the fecha ultima liquidacion rell.
	 *
	 * @param fechaUltimaLiquidacionRell
	 *            the fechaUltimaLiquidacionRell to set
	 */
	public void setFechaUltimaLiquidacionRell(String fechaUltimaLiquidacionRell) {
		this.fechaUltimaLiquidacionRell = fechaUltimaLiquidacionRell;
	}

	/**
	 * Gets the fecha proxima liquidacion rell.
	 *
	 * @return the fechaProximaLiquidacionRell
	 */
	public String getFechaProximaLiquidacionRell() {
		return fechaProximaLiquidacionRell;
	}

	/**
	 * Sets the fecha proxima liquidacion rell.
	 *
	 * @param fechaProximaLiquidacionRell
	 *            the fechaProximaLiquidacionRell to set
	 */
	public void setFechaProximaLiquidacionRell(String fechaProximaLiquidacionRell) {
		this.fechaProximaLiquidacionRell = fechaProximaLiquidacionRell;
	}

	/**
	 * Gets the plazo rell.
	 *
	 * @return the plazoRell
	 */
	public String getPlazoRell() {
		return plazoRell;
	}

	/**
	 * Sets the plazo rell.
	 *
	 * @param plazoRell
	 *            the plazoRell to set
	 */
	public void setPlazoRell(String plazoRell) {
		this.plazoRell = plazoRell;
	}

	/**
	 * Gets the indicador mas datos rell.
	 *
	 * @return the indicadorMasDatosRell
	 */
	public String getIndicadorMasDatosRell() {
		return indicadorMasDatosRell;
	}

	/**
	 * Sets the indicador mas datos rell.
	 *
	 * @param indicadorMasDatosRell
	 *            the indicadorMasDatosRell to set
	 */
	public void setIndicadorMasDatosRell(String indicadorMasDatosRell) {
		this.indicadorMasDatosRell = indicadorMasDatosRell;
	}

	/**
	 * Gets the indicador mas datos rell 2.
	 *
	 * @return the indicadorMasDatosRell2
	 */
	public String getIndicadorMasDatosRell2() {
		return indicadorMasDatosRell2;
	}

	/**
	 * Sets the indicador mas datos rell 2.
	 *
	 * @param indicadorMasDatosRell2
	 *            the indicadorMasDatosRell2 to set
	 */
	public void setIndicadorMasDatosRell2(String indicadorMasDatosRell2) {
		this.indicadorMasDatosRell2 = indicadorMasDatosRell2;
	}

	/**
	 * Gets the cant repeticiones.
	 *
	 * @return the cantRepeticiones
	 */
	public Long getCantRepeticiones() {
		return cantRepeticiones;
	}

	/**
	 * Sets the cant repeticiones.
	 *
	 * @param cantRepeticiones
	 *            the cantRepeticiones to set
	 */
	public void setCantRepeticiones(Long cantRepeticiones) {
		this.cantRepeticiones = cantRepeticiones;
	}

	/**
	 * Gets the agr elementos liquidacion.
	 *
	 * @return the agrElementosLiquidacion
	 */
	public List<PFInteresanteEntity> getAgrElementosLiquidacion() {
		return agrElementosLiquidacion;
	}

	/**
	 * Sets the agr elementos liquidacion.
	 *
	 * @param agrElementosLiquidacion
	 *            the agrElementosLiquidacion to set
	 */
	public void setAgrElementosLiquidacion(List<PFInteresanteEntity> agrElementosLiquidacion) {
		this.agrElementosLiquidacion = agrElementosLiquidacion;
	}

	/**
	 * Gets the delimiter.
	 *
	 * @return the delimiter
	 */
	public static String getDelimiter() {
		return DELIMITER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("headerTrama", headerTrama)
				.append("codigoRetornoExtendido", codigoRetornoExtendido)
				.append("vencimientoImpuestoRell", vencimientoImpuestoRell)
				.append("signoVencimientoImpuestoRell", signoVencimientoImpuestoRell)
				.append("fechaUltimaLiquidacionRell", fechaUltimaLiquidacionRell)
				.append("fechaProximaLiquidacionRell", fechaProximaLiquidacionRell).append("plazoRell", plazoRell)
				.append("indicadorMasDatosRell", indicadorMasDatosRell)
				.append("indicadorMasDatosRell2", indicadorMasDatosRell2).append("cantRepeticiones", cantRepeticiones)
				// .append("agrElementosLiquidacion", agrElementosLiquidacion)
				.toString();
	}
}
