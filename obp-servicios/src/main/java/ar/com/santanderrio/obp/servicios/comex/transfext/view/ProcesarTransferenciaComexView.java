/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.math.BigDecimal;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class ProcesarTransferenciaComexOutView.
 *
 * @author IT Resources
 */
@SuppressWarnings("serial")
@JsonSerialize(include = Inclusion.NON_NULL)
public class ProcesarTransferenciaComexView extends RsaDTOParaDesafio {

    /** The mensaje info. */
    private String mensajeInfo;

    /** The mensaje feedback. */
    private String mensajeFeedback;

    /** The nro transferencia. */
    private Integer nroTransferencia;

    /** The cod motivo. */
    private String codMoneda;

    /** The monto. */
    private BigDecimal monto;

    /** The acepta DDJJ. */
    private Boolean aceptaDDJJ = Boolean.FALSE;

    /** The datos beneficiario. */
    private DatosBeneficiarioView datosBeneficiario;

    /** The datos transferencia. */
    private DatosTransferenciaView datosTransferencia;

    /** The documentacion adjunta. */
    private DocumentacionAdjuntaView documentacionAdjuntaView;

    /** The nro form rel. */
    private Long nroFormRel;

    /** The fecha. */
    private String fecha;
    
    /** The vinculante. */
    private Boolean vinculante;

    /**
     * Instantiates a new procesar transferencia comex view.
     */
    public ProcesarTransferenciaComexView() {
        super(OperacionesRSAEnum.TRANSFERENCIA_COMEX);
    }

    /**
     * Instantiates a new procesar transferencia comex view.
     *
     * @param tipoOperacion
     *            the tipo operacion
     */
    public ProcesarTransferenciaComexView(OperacionesRSAEnum tipoOperacion) {
        super(OperacionesRSAEnum.TRANSFERENCIA_COMEX);
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
     * Gets the monto.
     *
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Sets the monto.
     *
     * @param monto
     *            the monto to set
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Gets the acepta DDJJ.
     *
     * @return the aceptaDDJJ
     */
    public Boolean getAceptaDDJJ() {
        return aceptaDDJJ;
    }

    /**
     * Sets the acepta DDJJ.
     *
     * @param aceptaDDJJ
     *            the aceptaDDJJ to set
     */
    public void setAceptaDDJJ(Boolean aceptaDDJJ) {
        this.aceptaDDJJ = aceptaDDJJ;
    }

    /**
     * Gets the mensaje info.
     *
     * @return the mensajeInfo
     */
    public String getMensajeInfo() {
        return mensajeInfo;
    }

    /**
     * Sets the mensaje info.
     *
     * @param mensajeInfo
     *            the mensajeInfo to set
     */
    public void setMensajeInfo(String mensajeInfo) {
        this.mensajeInfo = mensajeInfo;
    }

    /**
     * Gets the mensaje feedback.
     *
     * @return the mensajeFeedback
     */
    public String getMensajeFeedback() {
        return mensajeFeedback;
    }

    /**
     * Sets the mensaje feedback.
     *
     * @param mensajeFeedback
     *            the mensajeFeedback to set
     */
    public void setMensajeFeedback(String mensajeFeedback) {
        this.mensajeFeedback = mensajeFeedback;
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
     * Gets the fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
