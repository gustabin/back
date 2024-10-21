/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
/**
 * The Class ConsultaDetalleTrfOBPOutDTO.
 *
 * @author IT Resources
 */
public class ConsultaDetalleTrfOBPOutDTO {
	
	/** The nombre beneficiario. */
	private String nombreBeneficiario;
	
	/** The importe transferencia. */
	private String importeTransferencia;
	
	/** The estado transferencia. */
	private String estadoTransferencia;
		
	/** The vinculo. */
	private String vinculo;
		
	/** The domicilio calle. */
	private String domicilioCalle;
	
	/** The domicilio numero. */
	private String domicilioNumero;
	
	/** The domicilio localidad. */
	private String domicilioLocalidad;
	
	/** The domicilio pais. */
	private String domicilioPais;
	
	/** The concepto codigo. */
	private String conceptoCodigo;
	
	/** The concepto descripcion. */
	private String conceptoDescripcion;
	
	/** The origen. */
	private String origen;
	
	/** The tipo cuenta origen. */
	private String tipoCuentaOrigen;
	
	/** The destino. */
	private String destino;
		
	/** The nombre archivo. */
	private List<ReporteView> archivos = new ArrayList<ReporteView>();
	
	/** The gasto a cargo. */
	private String gastoACargo;
	
	/** The cod moneda. */
	private String codMoneda;
	
	/** The descripcion banco. */
	private String descripcionBanco;

	/** The codigo banco SWIFT O ABA. */
	private String codigoBanco;
	
	/** The descripcion banco intermediario. */
	private String descripcionBancoIntermediario;

	/** The codigo banco intermediario SWIFT O ABA. */
	private String codigoBancoIntermediario;
	
	/** The destino banco intermediario. */
	private String destinoBancoIntermediario;
	
	/** The fecha operacion. */
	private String fechaOperacion;
	
	/** The cod pais. */
	private String codPais;
	
	/** The cod tipo cuenta. */
	private String codTipoCuenta;
	
	/** The motivo rechazo. */
	private String motivoRechazo;
	
	/** The descripcionRechazo. */
	private String descripcionMotivo;
	
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
	 * @param nombreBeneficiario
	 *            the nombreBeneficiario to set
	 */
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	/**
	 * Gets the importe transferencia.
	 *
	 * @return the importeTransferencia
	 */
	public String getImporteTransferencia() {
		return importeTransferencia;
	}

	/**
	 * Sets the importe transferencia.
	 *
	 * @param importeTransferencia
	 *            the importeTransferencia to set
	 */
	public void setImporteTransferencia(String importeTransferencia) {
		this.importeTransferencia = importeTransferencia;
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
	 * Gets the origen.
	 *
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Sets the origen.
	 *
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * Gets the destino.
	 *
	 * @return the destino
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Sets the destino.
	 *
	 * @param destino
	 *            the destino to set
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}




	/**
	 * Gets the archivos.
	 *
	 * @return the archivos
	 */
	public List<ReporteView> getArchivos() {
		return archivos;
	}

	/**
	 * Sets the archivos.
	 *
	 * @param archivos
	 *            the archivos to set
	 */
	public void setArchivos(List<ReporteView> archivos) {
		this.archivos = archivos;
	}

	/**
	 * Gets the domicilio calle.
	 *
	 * @return the domicilioCalle
	 */
	public String getDomicilioCalle() {
		return domicilioCalle;
	}

	/**
	 * Sets the domicilio calle.
	 *
	 * @param domicilioCalle
	 *            the domicilioCalle to set
	 */
	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}

	/**
	 * Gets the domicilio numero.
	 *
	 * @return the domicilioNumero
	 */
	public String getDomicilioNumero() {
		return domicilioNumero;
	}

	/**
	 * Sets the domicilio numero.
	 *
	 * @param domicilioNumero
	 *            the domicilioNumero to set
	 */
	public void setDomicilioNumero(String domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}

	/**
	 * Gets the domicilio localidad.
	 *
	 * @return the domicilioLocalidad
	 */
	public String getDomicilioLocalidad() {
		return domicilioLocalidad;
	}

	/**
	 * Sets the domicilio localidad.
	 *
	 * @param domicilioLocalidad
	 *            the domicilioLocalidad to set
	 */
	public void setDomicilioLocalidad(String domicilioLocalidad) {
		this.domicilioLocalidad = domicilioLocalidad;
	}

	/**
	 * Gets the domicilio pais.
	 *
	 * @return the domicilioPais
	 */
	public String getDomicilioPais() {
		return domicilioPais;
	}

	/**
	 * Sets the domicilio pais.
	 *
	 * @param domicilioPais
	 *            the domicilioPais to set
	 */
	public void setDomicilioPais(String domicilioPais) {
		this.domicilioPais = domicilioPais;
	}

	/**
	 * Gets the concepto codigo.
	 *
	 * @return the conceptoCodigo
	 */
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * Sets the concepto codigo.
	 *
	 * @param conceptoCodigo
	 *            the conceptoCodigo to set
	 */
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

	/**
	 * Gets the concepto descripcion.
	 *
	 * @return the conceptoDescripcion
	 */
	public String getConceptoDescripcion() {
		return conceptoDescripcion;
	}

	/**
	 * Sets the concepto descripcion.
	 *
	 * @param conceptoDescripcion
	 *            the conceptoDescripcion to set
	 */
	public void setConceptoDescripcion(String conceptoDescripcion) {
		this.conceptoDescripcion = conceptoDescripcion;
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
	 * @param codMoneda
	 *            the codMoneda to set
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	/**
	 * Gets the codigo banco.
	 *
	 * @return the codigoBanco
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * Sets the codigo banco.
	 *
	 * @param codigoBanco
	 *            the codigoBanco to set
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	/**
	 * Gets the descripcion banco.
	 *
	 * @return the descripcionBanco
	 */
	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	/**
	 * Sets the descripcion banco.
	 *
	 * @param descripcionBanco
	 *            the descripcionBanco to set
	 */
	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	/**
	 * Gets the descripcion banco intermediario.
	 *
	 * @return the descripcionBancoIntermediario
	 */
	public String getDescripcionBancoIntermediario() {
		return descripcionBancoIntermediario;
	}

	/**
	 * Sets the descripcion banco intermediario.
	 *
	 * @param descripcionBancoIntermediario
	 *            the descripcionBancoIntermediario to set
	 */
	public void setDescripcionBancoIntermediario(String descripcionBancoIntermediario) {
		this.descripcionBancoIntermediario = descripcionBancoIntermediario;
	}

	/**
	 * Gets the codigo banco intermediario.
	 *
	 * @return the codigoBancoIntermediario
	 */
	public String getCodigoBancoIntermediario() {
		return codigoBancoIntermediario;
	}

	/**
	 * Sets the codigo banco intermediario.
	 *
	 * @param codigoBancoIntermediario
	 *            the codigoBancoIntermediario to set
	 */
	public void setCodigoBancoIntermediario(String codigoBancoIntermediario) {
		this.codigoBancoIntermediario = codigoBancoIntermediario;
	}

	/**
	 * Gets the tipo cuenta origen.
	 *
	 * @return the tipoCuentaOrigen
	 */
	public String getTipoCuentaOrigen() {
		return tipoCuentaOrigen;
	}

	/**
	 * Sets the tipo cuenta origen.
	 *
	 * @param tipoCuentaOrigen
	 *            the tipoCuentaOrigen to set
	 */
	public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
		this.tipoCuentaOrigen = tipoCuentaOrigen;
	}

	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the destino banco intermediario.
	 *
	 * @return the destinoBancoIntermediario
	 */
	public String getDestinoBancoIntermediario() {
		return destinoBancoIntermediario;
	}

	/**
	 * Sets the destino banco intermediario.
	 *
	 * @param destinoBancoIntermediario
	 *            the destinoBancoIntermediario to set
	 */
	public void setDestinoBancoIntermediario(String destinoBancoIntermediario) {
		this.destinoBancoIntermediario = destinoBancoIntermediario;
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
	 * @param codPais
	 *            the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * Gets the cod tipo cuenta.
	 *
	 * @return the codTipoCuenta
	 */
	public String getCodTipoCuenta() {
		return codTipoCuenta;
	}

	/**
	 * Sets the cod tipo cuenta.
	 *
	 * @param codTipoCuenta
	 *            the codTipoCuenta to set
	 */
	public void setCodTipoCuenta(String codTipoCuenta) {
		this.codTipoCuenta = codTipoCuenta;
	}

	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * @return the descripcionMotivo
	 */
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}

	/**
	 * @param descripcionMotivo the descripcionMotivo to set
	 */
	public void setDescripcionMotivo(String descripcionRechazo) {
		this.descripcionMotivo = descripcionRechazo;
	}

}
