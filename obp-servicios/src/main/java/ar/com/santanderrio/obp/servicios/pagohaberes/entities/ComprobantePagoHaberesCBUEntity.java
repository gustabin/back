/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.entities;

import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import com.sun.istack.Nullable;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The Class ComprobantePagoHaberesCBUEntity.
 */
public class ComprobantePagoHaberesCBUEntity extends RsaDTOParaDesafio {

    /** Serial version. */
    private static final long serialVersionUID = 3105667423190327173L;

    /** The datos Empleado Pago Haberes. */
    private DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes;

    /** The Datos Destinatario View. */
    private DatosDestinatarioView datosDestinatarioView;

    /** The nro de comprobante. */
    private String nroDeComprobante;

    /** The Modo ejecucion. */
    private Boolean modoEjecucion;

    /** The fecha hora. */
    private String fechaHora;

    /** The mensaje. */
    private String mensaje;

    /** The legales SEO. */
    private String legalesSEO;
    
    /** Se usa en RSA */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;

    /** Se usa en RSA. */
    @JsonIgnore
    private BiocatchResponseDataDTO biocatchResponseData;

    @JsonIgnore
    private float scoringDestinatario;

    @JsonIgnore
    private Integer cantDiasUltimoCambioMail;


    /**
     * Instantiates a new comprobante pago haberes CBU entity.
     */
    public ComprobantePagoHaberesCBUEntity() {
        super(OperacionesRSAEnum.PAGODESUELDOS);
    }

    /**
     * get the nro de comprobante.
     *
     * @return the nroDeComprobante
     */
    public String getNroDeComprobante() {
        return nroDeComprobante;
    }

    /**
     * set the nro de comprobante.
     *
     * @param nroDeComprobante
     *            the nroDeComprobante to set
     */
    public void setNroDeComprobante(String nroDeComprobante) {
        this.nroDeComprobante = nroDeComprobante;
    }

    /**
     * get the fecha hora.
     *
     * @return the fechaHora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * set the fecha hora.
     *
     * @param fechaHora
     *            the fechaHora to set
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * get the mensaje.
     *
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * set the mensaje.
     *
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * get the legales SEO.
     *
     * @return legalesSEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * set the legales SEO.
     *
     * @param legalesSEO
     *            the new legales SEO
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }

    /**
     * get the datos empleado pago haberes.
     *
     * @return the datos empleado pago haberes
     */
    public DatosEmpleadoPagoHaberesEntity getDatosEmpleadoPagoHaberes() {
        return datosEmpleadoPagoHaberes;
    }

    /**
     * set the datos empleado pago haberes.
     *
     * @param datosEmpleadoPagoHaberes
     *            the new datos empleado pago haberes
     */
    public void setDatosEmpleadoPagoHaberes(DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes) {
        this.datosEmpleadoPagoHaberes = datosEmpleadoPagoHaberes;
    }

    /**
     * get the datos destinatario view.
     *
     * @return the datos destinatario view
     */
    public DatosDestinatarioView getDatosDestinatarioView() {
        return datosDestinatarioView;
    }

    /**
     * set the datos destinatario view.
     *
     * @param datosDestinatarioView
     *            the new datos destinatario view
     */
    public void setDatosDestinatarioView(DatosDestinatarioView datosDestinatarioView) {
        this.datosDestinatarioView = datosDestinatarioView;
    }

    /**
     * Gets the modo ejecucion.
     *
     * @return the modoEjecucion
     */
    public Boolean getModoEjecucion() {
        return modoEjecucion;
    }

    /**
     * Sets the modo ejecucion.
     *
     * @param modoEjecucion
     *            the modoEjecucion to set
     */
    public void setModoEjecucion(Boolean modoEjecucion) {
        this.modoEjecucion = modoEjecucion;
    }

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

    public BiocatchResponseDataDTO getBiocatchResponseData() {
        return biocatchResponseData;
    }

    public void setBiocatchResponseData(BiocatchResponseDataDTO biocatchResponseData) {
        this.biocatchResponseData = biocatchResponseData;
    }

    public float getScoringDestinatario() {
        return scoringDestinatario;
    }

    public void setScoringDestinatario(float scoringDestinatario) {
        this.scoringDestinatario = scoringDestinatario;
    }

    public Integer getCantDiasUltimoCambioMail() {
        return cantDiasUltimoCambioMail;
    }

    public void setCantDiasUltimoCambioMail(Integer cantDiasUltimoCambioMail) {
        this.cantDiasUltimoCambioMail = cantDiasUltimoCambioMail;
    }
}