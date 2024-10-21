/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.view;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class SolicitarDebinView.
 */
@SuppressWarnings("serial")
public class SolicitarDebinView extends RsaDTOParaDesafio  {
    
    /**
	 * Instantiates a new solicitar debin view.
	 */
    //RSA
    public SolicitarDebinView() {
        super(OperacionesRSAEnum.SOLICITUD_DEBIN);
    }
    
    /** The desafio. */
    @JsonIgnore
    @JsonManagedReference
    private AutentificacionDTO desafio;
    

    /** importe. */
    private String importe;
    
    /** idCuenta. */
    private String cbuOrigen;
    
    /** descripcion. */
    private String descripcion;
    
    /** idConcepto. */
    private String idConcepto;
    
    /** fechaVencimiento. */ 
    private String fechaVencimiento;
    
    /** fechaComprobante. */
    private String fechaHoraComprobante;
    
    /** nroComprobante. */
    private String nroComprobante;
    
    /** mensaje feedback. */
    private String mensajeFeedback;
    

    /** The tiene celular my A. */
    private boolean tieneCelularMyA = false;
    
    private String cuitVendedor;
    
    private String cuitComprador;
    
    private Integer cantDiasUltimoCambioClave;
	
	private Integer cantDiasUltimoCambioToken;

    /**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    /**
	 * Sets the desafio.
	 *
	 * @param desafio
	 *            the desafio to set
	 */
    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
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
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fechaHoraComprobante
	 */
    public String getFechaHoraComprobante() {
        return fechaHoraComprobante;
    }

    /**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the fechaHoraComprobante to set
	 */
    public void setFechaHoraComprobante(String fechaHoraComprobante) {
        this.fechaHoraComprobante = fechaHoraComprobante;
    }

    /**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    /**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
    public String getImporte() {
        return importe;
    }

    /**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
    public void setImporte(String importe) {
        this.importe = importe;
    }



    /**
	 * Gets the cbu origen.
	 *
	 * @return the cbuOrigen
	 */
    public String getCbuOrigen() {
        return cbuOrigen;
    }

    /**
	 * Sets the cbu origen.
	 *
	 * @param cbuOrigen
	 *            the cbuOrigen to set
	 */
    public void setCbuOrigen(String cbuOrigen) {
        this.cbuOrigen = cbuOrigen;
    }

    /**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
    public String getDescripcion() {
        return descripcion;
    }

    /**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
	 * @param idConcepto
	 *            the idConcepto to set
	 */
    public void setIdConcepto(String idConcepto) {
        this.idConcepto = idConcepto;
    }

    /**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
	 * Checks if is tiene celular my A.
	 *
	 * @return the tieneCelularMyA
	 */
    public boolean isTieneCelularMyA() {
        return tieneCelularMyA;
    }

    /**
	 * Sets the tiene celular my A.
	 *
	 * @param tieneCelularMyA
	 *            the tieneCelularMyA to set
	 */
    public void setTieneCelularMyA(boolean tieneCelularMyA) {
        this.tieneCelularMyA = tieneCelularMyA;
    }

	public String getCuitVendedor() {
		return cuitVendedor;
	}

	public void setCuitVendedor(String cuitVendedor) {
		this.cuitVendedor = cuitVendedor;
	}

	public String getCuitComprador() {
		return cuitComprador;
	}

	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
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

}
