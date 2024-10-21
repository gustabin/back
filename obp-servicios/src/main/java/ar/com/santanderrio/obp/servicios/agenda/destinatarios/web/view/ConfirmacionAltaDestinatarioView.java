/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class ConfirmacionAltaDestinatarioView.
 */
public class ConfirmacionAltaDestinatarioView extends AltaDestinatarioInView {

    /** The id. */
    private String id;

    /** The tipo documento destinatario. */
    private Boolean isCuit;

    /** The documento destinatario. */
    private String numeroDocumento;

    /** The tipo documento destinatario. */
    private String tipoDocumento;

    /** The descripcion cuenta destinatario. */
    private String referencia;

    /** The titular. */
    private String titular;

    /** The direccion correo. */
    private String direccionCorreo;

    /** The CBU. */
    private String cbu;

    /** The banco destino. */
    private String bancoDestino;

    /** The is Pesos. */
    private Boolean isPesos;

    /** The mensaje efectivizacion. */
    private String mensajeEfectivizacion;

    /** The nroComprobante. */
    private String nroComprobante;

    /** The fecha. */
    private String fecha;

    /** The hora. */
    private String hora;

    /** The desafio. */
    private AutentificacionDTO desafio;

    /** The mensaje. */
    private String mensaje;

    /** The alias. */
    private String alias;

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The is rio. */
    private Boolean isRio;

    /** The cod tipo cuenta. */
    private String codTipoCuenta;

    /** The tipo destinatario. */
    private String tipoDestinatario;

    /** Se usa en RSA. */
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA. */
    private Integer cantDiasUltimoCambioToken;

    /**
     * Instantiates a new comprobante alta destinatario view.
     *
     * @param dto
     *            the dto
     */
    public ConfirmacionAltaDestinatarioView(ComprobanteAltaDestinatarioDTO dto) {
        cargaCompronbanteDTO(dto);
    }


    /**
     * Instantiates a new confirmacion alta destinatario view.
     */
    public ConfirmacionAltaDestinatarioView() {
        super();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the checks if is cuit.
     *
     * @return the isCuit
     */
    public Boolean getIsCuit() {
        return isCuit;
    }

    /**
     * Sets the checks if is cuit.
     *
     * @param isCuit
     *            the isCuit to set
     */
    public void setIsCuit(Boolean isCuit) {
        this.isCuit = isCuit;
    }

    /**
     * Gets the numero documento.
     *
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Sets the numero documento.
     *
     * @param numeroDocumento
     *            the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * Gets the tipo documento.
     *
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the tipo documento.
     *
     * @param tipoDocumento
     *            the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Gets the referencia.
     *
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Sets the referencia.
     *
     * @param referencia
     *            the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }


    public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
        this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
    }


    public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
        this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
    }

    public Integer getCantDiasUltimoCambioClave() {
        return cantDiasUltimoCambioClave;
    }

    public Integer getCantDiasUltimoCambioToken() {
        return cantDiasUltimoCambioToken;
    }

    /**
     * Gets the titular.
     *
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Sets the titular.
     *
     * @param titular
     *            the titular to set
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Gets the direccion correo.
     *
     * @return the direccionCorreo
     */
    public String getDireccionCorreo() {
        return direccionCorreo;
    }

    /**
     * Sets the direccion correo.
     *
     * @param direccionCorreo
     *            the direccionCorreo to set
     */
    public void setDireccionCorreo(String direccionCorreo) {
        this.direccionCorreo = direccionCorreo;
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
     *            the cbu to set
     */
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    /**
     * Gets the banco destino.
     *
     * @return the bancoDestino
     */
    public String getBancoDestino() {
        return bancoDestino;
    }

    /**
     * Sets the banco destino.
     *
     * @param bancoDestino
     *            the bancoDestino to set
     */
    public void setBancoDestino(String bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    /**
     * Gets the checks if is pesos.
     *
     * @return the isPesos
     */
    public Boolean getIsPesos() {
        return isPesos;
    }

    /**
     * Sets the checks if is pesos.
     *
     * @param isPesos
     *            the isPesos to set
     */
    public void setIsPesos(Boolean isPesos) {
        this.isPesos = isPesos;
    }

    /**
     * Gets the mensaje efectivizacion.
     *
     * @return the mensajeEfectivizacion
     */
    public String getMensajeEfectivizacion() {
        return mensajeEfectivizacion;
    }

    /**
     * Sets the mensaje efectivizacion.
     *
     * @param mensajeEfectivizacion
     *            the mensajeEfectivizacion to set
     */
    public void setMensajeEfectivizacion(String mensajeEfectivizacion) {
        this.mensajeEfectivizacion = mensajeEfectivizacion;
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
     * Gets the hora.
     *
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Sets the hora.
     *
     * @param hora
     *            the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

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
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param mensaje
     *            the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias
     *            the new alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipo cuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the new tipo cuenta
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the checks if is rio.
     *
     * @return the checks if is rio
     */
    public Boolean getIsRio() {
        return isRio;
    }

    /**
     * Sets the checks if is rio.
     *
     * @param isRio
     *            the new checks if is rio
     */
    public void setIsRio(Boolean isRio) {
        this.isRio = isRio;
    }

    /**
     * Gets the cod tipo cuenta.
     *
     * @return the cod tipo cuenta
     */
    public String getCodTipoCuenta() {
        return codTipoCuenta;
    }

    /**
     * Sets the cod tipo cuenta.
     *
     * @param codTipoCuenta
     *            the new cod tipo cuenta
     */
    public void setCodTipoCuenta(String codTipoCuenta) {
        this.codTipoCuenta = codTipoCuenta;
    }

    /**
     * Gets the tipo destinatario.
     *
     * @return the tipoDestinatario
     */
    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    /**
     * Sets the tipo destinatario.
     *
     * @param tipoDestinatario
     *            the tipoDestinatario to set
     */
    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    /**
     * Obtener numero agendado.
     *
     * @return the string
     */
    public String obtenerNumeroAgendado() {
        if (getNroCuenta() != null) {
            return getNroCuenta();
        } else if (cbu != null) {
            return cbu;
        } else if (numeroDocumento != null) {
            return numeroDocumento;
        }
        return null;
    }

    /**
	 * Cargar comprobante.
	 *
	 * @param comprobante
	 *            the comprobante
	 */
    public void cargarComprobante(ComprobanteAltaDestinatarioDTO comprobante) {
    	cargaCompronbanteDTO(comprobante);
    }
    
	/**
	 * Carga compronbante DTO.
	 *
	 * @param dto
	 *            the dto
	 */
	private void cargaCompronbanteDTO(ComprobanteAltaDestinatarioDTO dto) {
		this.setMensajeEfectivizacion(dto.getMensajeEfectivizacion());
        this.setNroComprobante(dto.getNroComprobante());
        if (dto.getFecha() != null) {
            this.setFecha(ISBANStringUtils.formatearFecha(dto.getFecha()));
        }
        this.setHora(dto.getHora());
	}

    /**
     * Obtener referencia O titular.
     *
     * @return the string
     */
    public String obtenerReferenciaOTitular() {
        if (StringUtils.isNotBlank(referencia)) {
            return referencia;
        } else {
            return titular;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConfirmacionAltaDestinatarioView [id=" + id + ", isCuit=" + isCuit + ", numeroDocumento="
                + numeroDocumento + ", tipoDocumento=" + tipoDocumento + ", referencia=" + referencia + ", titular="
                + titular + ", direccionCorreo=" + direccionCorreo + ", cbu=" + cbu + ", bancoDestino=" + bancoDestino
                + ", isPesos=" + isPesos + ", mensajeEfectivizacion=" + mensajeEfectivizacion + ", nroComprobante="
                + nroComprobante + ", fecha=" + fecha + ", hora=" + hora + ", desafio=" + desafio + ", mensaje="
                + mensaje + "tipoDestinatario=" + tipoDestinatario + "]";
    }


}
