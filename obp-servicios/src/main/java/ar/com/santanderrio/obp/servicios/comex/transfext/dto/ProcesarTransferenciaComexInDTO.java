/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.math.BigDecimal;

/**
 * The Class ProcesarTransferenciaComexInDTO.
 *
 * @author IT Resources
 */
public class ProcesarTransferenciaComexInDTO {

	/** The nro transferencia. */
	private Integer nroTransferencia;

	/** The tipo operacion. */
	private String tipoOperacion;

	/** The estado transferencia. */
	private String estadoTransferencia;

	/** The importe transferencia. */
	private BigDecimal importeTransferencia;

	/** The cod moneda. */
	private String codMoneda;

	/** The id concepto. */
	private String idConcepto;

	/** The acepta DDJJ. */
	private String aceptaDDJJ;

	/** DATOS TRANSFERENCIA. */

	/** The nombre beneficiario. */
	private String nombreBeneficiario;

	/** The vinculo. */
	private String vinculo;

	/** The domicilio calle. */
	private String domicilio;

	/** The cod pais. */
	private String codPais;

	/** DATOS TRANSFERENCIA. */

	/** The cod pais. */
	private String cuentaDebito;

	/** The cod pais. */
	private String cuentaAltair;

	/** The gasto a cargo. */
	private String gastoACargo;

	/** The banco beneficiario. */
	private String bancoBeneficiario;

	/** The banco intermediario. */
	private String bancoIntermediario;

	/** The cuenta bco intermediario. */
	private String cuentaBcoIntermediario;

	/** The cuenta beneficiario. */
	private String cuentaBeneficiario;

	/** The razon social. */
	private String razonSocial;

	/** The nro form rel. */
	private Long nroFormRel;

	private Boolean vinculante;

	/**
	 * Gets the nro transferencia.
	 *
	 * @return the nroTransferencia
	 */
	public Integer getNroTransferencia() {
		return nroTransferencia;
	}

	/**
	 * Sets the nro transferencia.
	 *
	 * @param nroTransferencia the nroTransferencia to set
	 */
	public void setNroTransferencia(Integer nroTransferencia) {
		this.nroTransferencia = nroTransferencia;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the estado transferencia.
	 *
	 * @return the estadoTransferencia
	 */
	public String getEstadoTransferencia() {
		return estadoTransferencia;
	}

	/**
	 * Sets the estado transferencia.
	 *
	 * @param estadoTransferencia the estadoTransferencia to set
	 */
	public void setEstadoTransferencia(String estadoTransferencia) {
		this.estadoTransferencia = estadoTransferencia;
	}

	/**
	 * Gets the importe transferencia.
	 *
	 * @return the importeTransferencia
	 */
	public BigDecimal getImporteTransferencia() {
		return importeTransferencia;
	}

	/**
	 * Sets the importe transferencia.
	 *
	 * @param importeTransferencia the importeTransferencia to set
	 */
	public void setImporteTransferencia(BigDecimal importeTransferencia) {
		this.importeTransferencia = importeTransferencia;
	}

	/**
	 * Gets the cod moneda.
	 *
	 * @return the codMoneda
	 */
	public String getCodMoneda() {
		return codMoneda;
	}

	/**
	 * Sets the cod moneda.
	 *
	 * @param codMoneda the codMoneda to set
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	/**
	 * Gets the nombre beneficiario.
	 *
	 * @return the nombreBeneficiario
	 */
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	/**
	 * Sets the nombre beneficiario.
	 *
	 * @param nombreBeneficiario the nombreBeneficiario to set
	 */
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	/**
	 * Gets the vinculo.
	 *
	 * @return the vinculo
	 */
	public String getVinculo() {
		return vinculo;
	}

	/**
	 * Sets the vinculo.
	 *
	 * @param vinculo the vinculo to set
	 */
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	/**
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Sets the domicilio.
	 *
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * Gets the cod pais.
	 *
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * Sets the cod pais.
	 *
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * Gets the id concepto.
	 *
	 * @return the idConcepto
	 */
	public String getIdConcepto() {
		return idConcepto;
	}

	/**
	 * Sets the id concepto.
	 *
	 * @param idConcepto the idConcepto to set
	 */
	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}

	/**
	 * Gets the acepta DDJJ.
	 *
	 * @return the aceptaDDJJ
	 */
	public String getAceptaDDJJ() {
		return aceptaDDJJ;
	}

	/**
	 * Sets the acepta DDJJ.
	 *
	 * @param aceptaDDJJ the aceptaDDJJ to set
	 */
	public void setAceptaDDJJ(String aceptaDDJJ) {
		this.aceptaDDJJ = aceptaDDJJ;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuentaDebito
	 */
	public String getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito the cuentaDebito to set
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the cuenta altair.
	 *
	 * @return the cuentaAltair
	 */
	public String getCuentaAltair() {
		return cuentaAltair;
	}

	/**
	 * Sets the cuenta altair.
	 *
	 * @param cuentaAltair the cuentaAltair to set
	 */
	public void setCuentaAltair(String cuentaAltair) {
		this.cuentaAltair = cuentaAltair;
	}

	/**
	 * Gets the gasto A cargo.
	 *
	 * @return the gastoACargo
	 */
	public String getGastoACargo() {
		return gastoACargo;
	}

	/**
	 * Sets the gasto A cargo.
	 *
	 * @param gastoACargo the gastoACargo to set
	 */
	public void setGastoACargo(String gastoACargo) {
		this.gastoACargo = gastoACargo;
	}

	/**
	 * Gets the banco beneficiario.
	 *
	 * @return the bancoBeneficiario
	 */
	public String getBancoBeneficiario() {
		return bancoBeneficiario;
	}

	/**
	 * Sets the banco beneficiario.
	 *
	 * @param bancoBeneficiario the bancoBeneficiario to set
	 */
	public void setBancoBeneficiario(String bancoBeneficiario) {
		this.bancoBeneficiario = bancoBeneficiario;
	}

	/**
	 * Gets the banco intermediario.
	 *
	 * @return the bancoIntermediario
	 */
	public String getBancoIntermediario() {
		return bancoIntermediario;
	}

	/**
	 * Sets the banco intermediario.
	 *
	 * @param bancoIntermediario the bancoIntermediario to set
	 */
	public void setBancoIntermediario(String bancoIntermediario) {
		this.bancoIntermediario = bancoIntermediario;
	}

	/**
	 * Gets the cuenta bco intermediario.
	 *
	 * @return the cuentaBcoIntermediario
	 */
	public String getCuentaBcoIntermediario() {
		return cuentaBcoIntermediario;
	}

	/**
	 * Sets the cuenta bco intermediario.
	 *
	 * @param cuentaBcoIntermediario the cuentaBcoIntermediario to set
	 */
	public void setCuentaBcoIntermediario(String cuentaBcoIntermediario) {
		this.cuentaBcoIntermediario = cuentaBcoIntermediario;
	}

	/**
	 * Gets the cuenta beneficiario.
	 *
	 * @return the cuentaBeneficiario
	 */
	public String getCuentaBeneficiario() {
		return cuentaBeneficiario;
	}

	/**
	 * Sets the cuenta beneficiario.
	 *
	 * @param cuentaBeneficiario the cuentaBeneficiario to set
	 */
	public void setCuentaBeneficiario(String cuentaBeneficiario) {
		this.cuentaBeneficiario = cuentaBeneficiario;
	}

	/**
	 * Gets the razon social.
	 *
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Sets the razon social.
	 *
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the nro form rel.
	 *
	 * @return the nroFormRel
	 */
	public Long getNroFormRel() {
		return nroFormRel;
	}

	/**
	 * Sets the nro form rel.
	 *
	 * @param nroFormRel the nroFormRel to set
	 */
	public void setNroFormRel(Long nroFormRel) {
		this.nroFormRel = nroFormRel;
	}

	/**
	 * @return the vinculante
	 */
	public Boolean getVinculante() {
		return vinculante;
	}

	/**
	 * @param vinculante the vinculante to set
	 */
	public void setVinculante(Boolean vinculante) {
		this.vinculante = vinculante;
	}

}
