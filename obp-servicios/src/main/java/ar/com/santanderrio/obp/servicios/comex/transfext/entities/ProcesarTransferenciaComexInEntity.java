/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.entities;

import java.math.BigDecimal;

/**
 * The Class ProcesarTransferenciaComexInEntity.
 *
 * @author IT Resources
 */
public class ProcesarTransferenciaComexInEntity {

	/** The importe transferencia. */
	private BigDecimal importeTransferencia;
	
	/** The cuenta debito. */
	private String cuentaDebito;
	
	/** The cta altair. */
	private String ctaAltair;
	
	/** The moneda. */
	private String moneda;
	
	/** The concepto. */
	private String concepto;
	
	/** The beneficiario pais. */
	private String beneficiarioPais;
	
	/** The beneficiario domicilio. */
	private String beneficiarioDomicilio;
	
	/** The vinculo. */
	private String vinculo;
	
	/** The nro transferencia. */
	private Integer nroTransferencia;
	
	/** The banco beneficiario. */
	private String bancoBeneficiario;
	
	/** The banco intermediario. */
	private String bancoIntermediario;
	
	/** The beneficiario. */
	private String beneficiario;
	
	/** The cuenta bco intermediario. */
	private String cuentaBcoIntermediario;
	
	/** The cuenta beneficiario. */
	private String cuentaBeneficiario;
	
	/** The tipo operacion. */
	private String tipoOperacion;
		
	/** The estado transferencia. */
	private String estadoTransferencia;
	
	/** The acepta DDJJ. */
	private String aceptaDDJJ;
	
	/** The gasto a cargo. */
	private String gastoACargo;
	
	/** The razon social. */
	private String razonSocial;
	
	/** The razon social. */
	private Long nroFormRel;
	
	private Short vinculante;

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
	 * @param importeTransferencia
	 *            the importeTransferencia to set
	 */
	public void setImporteTransferencia(BigDecimal importeTransferencia) {
		this.importeTransferencia = importeTransferencia;
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
	 * @param cuentaDebito
	 *            the cuentaDebito to set
	 */
	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the cta altair.
	 *
	 * @return the ctaAltair
	 */
	public String getCtaAltair() {
		return ctaAltair;
	}

	/**
	 * Sets the cta altair.
	 *
	 * @param ctaAltair
	 *            the ctaAltair to set
	 */
	public void setCtaAltair(String ctaAltair) {
		this.ctaAltair = ctaAltair;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the beneficiario pais.
	 *
	 * @return the beneficiarioPais
	 */
	public String getBeneficiarioPais() {
		return beneficiarioPais;
	}

	/**
	 * Sets the beneficiario pais.
	 *
	 * @param beneficiarioPais
	 *            the beneficiarioPais to set
	 */
	public void setBeneficiarioPais(String beneficiarioPais) {
		this.beneficiarioPais = beneficiarioPais;
	}

	/**
	 * Gets the beneficiario domicilio.
	 *
	 * @return the beneficiarioDomicilio
	 */
	public String getBeneficiarioDomicilio() {
		return beneficiarioDomicilio;
	}

	/**
	 * Sets the beneficiario domicilio.
	 *
	 * @param beneficiarioDomicilio
	 *            the beneficiarioDomicilio to set
	 */
	public void setBeneficiarioDomicilio(String beneficiarioDomicilio) {
		this.beneficiarioDomicilio = beneficiarioDomicilio;
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
	 * @param vinculo
	 *            the vinculo to set
	 */
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

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
	 * @param nroTransferencia
	 *            the nroTransferencia to set
	 */
	public void setNroTransferencia(Integer nroTransferencia) {
		this.nroTransferencia = nroTransferencia;
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
	 * @param bancoBeneficiario
	 *            the bancoBeneficiario to set
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
	 * @param bancoIntermediario
	 *            the bancoIntermediario to set
	 */
	public void setBancoIntermediario(String bancoIntermediario) {
		this.bancoIntermediario = bancoIntermediario;
	}

	/**
	 * Gets the beneficiario.
	 *
	 * @return the beneficiario
	 */
	public String getBeneficiario() {
		return beneficiario;
	}

	/**
	 * Sets the beneficiario.
	 *
	 * @param beneficiario
	 *            the beneficiario to set
	 */
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
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
	 * @param cuentaBcoIntermediario
	 *            the cuentaBcoIntermediario to set
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
	 * @param cuentaBeneficiario
	 *            the cuentaBeneficiario to set
	 */
	public void setCuentaBeneficiario(String cuentaBeneficiario) {
		this.cuentaBeneficiario = cuentaBeneficiario;
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
	 * @param tipoOperacion
	 *            the tipoOperacion to set
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
	 * @param estadoTransferencia
	 *            the estadoTransferencia to set
	 */
	public void setEstadoTransferencia(String estadoTransferencia) {
		this.estadoTransferencia = estadoTransferencia;
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
	 * @param aceptaDDJJ
	 *            the aceptaDDJJ to set
	 */
	public void setAceptaDDJJ(String aceptaDDJJ) {
		this.aceptaDDJJ = aceptaDDJJ;
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
	 * @param gastoACargo
	 *            the gastoACargo to set
	 */
	public void setGastoACargo(String gastoACargo) {
		this.gastoACargo = gastoACargo;
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
	 * @param razonSocial
	 *            the razonSocial to set
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
	 * @param nroFormRel
	 *            the nroFormRel to set
	 */
	public void setNroFormRel(Long nroFormRel) {
		this.nroFormRel = nroFormRel;
	}

	/**
	 * @return the vinculante
	 */
	public Short getVinculante() {
		return vinculante;
	}

	/**
	 * @param vinculante the vinculante to set
	 */
	public void setVinculante(Short vinculante) {
		this.vinculante = vinculante;
	}
	
	
	
	
}
