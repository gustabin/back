/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ConsultaDetalleTrfOBPOutView.
 *
 * @author IT Resources
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConsultaDetalleTrfOBPOutView {
	
	/** The importe transferencia. */
	private String importeTransferencia;
	
	/** The estado transferencia. */
	private String estadoTransferencia;
	
	/** The cod moneda. */
	private String codMoneda;
	
	/** The descripcion moneda. */
	private String descripcionMoneda;
	
	/** The simbolo moneda. */
	private String simboloMoneda;
	
	/** The fecha operacion. */
	private String fechaOperacion;
		
	/** The datos beneficiario. */
	private DatosBeneficiarioView datosBeneficiario;
	
	/** The datos transferencia. */
	private DatosTransferenciaView datosTransferencia;
	
	/** The documentacion adjunta. */
	private DocumentacionAdjuntaView documentacionAdjuntaView;
	
	/** The conceptosValidos. */
	private List<String> conceptosValidos;
	
	/** The motivoRechazo. */
	private String motivoRechazo;
	
	/** The descripcionMotivo. */
	private String descripcionMotivo;

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
	 * Gets the datos beneficiario.
	 *
	 * @return the datosBeneficiario
	 */
	public DatosBeneficiarioView getDatosBeneficiario() {
		return datosBeneficiario;
	}

	/**
	 * Sets the datos beneficiario.
	 *
	 * @param datosBeneficiario
	 *            the datosBeneficiario to set
	 */
	public void setDatosBeneficiario(DatosBeneficiarioView datosBeneficiario) {
		this.datosBeneficiario = datosBeneficiario;
	}

	/**
	 * Gets the datos transferencia.
	 *
	 * @return the datosTransferencia
	 */
	public DatosTransferenciaView getDatosTransferencia() {
		return datosTransferencia;
	}

	/**
	 * Sets the datos transferencia.
	 *
	 * @param datosTransferencia
	 *            the datosTransferencia to set
	 */
	public void setDatosTransferencia(DatosTransferenciaView datosTransferencia) {
		this.datosTransferencia = datosTransferencia;
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
	 * Gets the documentacion adjunta view.
	 *
	 * @return the documentacionAdjuntaView
	 */
	public DocumentacionAdjuntaView getDocumentacionAdjuntaView() {
		return documentacionAdjuntaView;
	}

	/**
	 * Sets the documentacion adjunta view.
	 *
	 * @param documentacionAdjuntaView
	 *            the documentacionAdjuntaView to set
	 */
	public void setDocumentacionAdjuntaView(DocumentacionAdjuntaView documentacionAdjuntaView) {
		this.documentacionAdjuntaView = documentacionAdjuntaView;
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
	 * Gets the descripcion moneda.
	 *
	 * @return the descripcionMoneda
	 */
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	/**
	 * Sets the descripcion moneda.
	 *
	 * @param descripcionMoneda
	 *            the descripcionMoneda to set
	 */
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	/**
	 * Gets the simbolo moneda.
	 *
	 * @return the simboloMoneda
	 */
	public String getSimboloMoneda() {
		return simboloMoneda;
	}

	/**
	 * Sets the simbolo moneda.
	 *
	 * @param simboloMoneda
	 *            the simboloMoneda to set
	 */
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}

	/**
	 * Gets the conceptos validos.
	 * 
	 * @return the conceptosValidos
	 */
	public List<String> getConceptosValidos() {
		return conceptosValidos;
	}

	/**
	 * Sets the conceptos Validos.
	 * 
	 * @param conceptosValidos 
	 * 				the conceptosValidos to set
	 */
	public void setConceptosValidos(List<String> conceptosValidos) {
		this.conceptosValidos = conceptosValidos;
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
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}

	
}
