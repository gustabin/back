/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class ComprobanteAltaSolicitudTarjetaRecargableView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComprobanteAltaSolicitudTarjetaRecargableView extends RsaDTOParaDesafio {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1373253325735502206L;

    /** The comprobante. */
    private String comprobante;

    /** The mensaje. */
    private String mensaje;

    /** The fecha hora. */
    private String fechaHora;

    /** The legales SEO. */
    private String legalesSEO;

    /** The apellido adic. */
    private String apellidoAdic;

    /** The nombre adic. */
    private String nombreAdic;

    /** The tipo documento adic. */
    private String tipoDocumentoAdic;

    /** The nro documento adic. */
    private String nroDocumentoAdic;

    /** The fecha nacimiento adic. */
    private String fechaNacimientoAdic;

    /** The nacionalidad. */
    private String nacionalidad;

    /** The sexo. */
    private String sexo;

    /** The estado civil. */
    private String estadoCivil;

    /** The domicilio. */
    private String domicilio;

    /** The nro. */
    private String nro;

    /** The piso depto. */
    private String pisoDepto;

    /** The localidad barrio. */
    private String localidadBarrio;

    /** The codigo postal. */
    private String codigoPostal;

    /** The provincia. */
    private String provincia;

    /** The cod area. */
    private String codArea;

    /** The telefono. */
    private String telefono;

    /**
     * Instantiates a new comprobante alta solicitud tarjeta recargable view.
     */
    public ComprobanteAltaSolicitudTarjetaRecargableView() {
        super(OperacionesRSAEnum.SOLICITUD_TARJ_RECARGABLE);
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the new comprobante
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
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
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fecha hora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the new fecha hora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Gets the legales SEO.
     *
     * @return the legales SEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * Sets the legales SEO.
     *
     * @param legalesSEO
     *            the new legales SEO
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }

    /**
     * Gets the apellido adic.
     *
     * @return the apellido adic
     */
    public String getApellidoAdic() {
        return apellidoAdic;
    }

    /**
     * Sets the apellido adic.
     *
     * @param apellidoAdic
     *            the new apellido adic
     */
    public void setApellidoAdic(String apellidoAdic) {
        this.apellidoAdic = apellidoAdic;
    }

    /**
     * Gets the nombre adic.
     *
     * @return the nombre adic
     */
    public String getNombreAdic() {
        return nombreAdic;
    }

    /**
     * Sets the nombre adic.
     *
     * @param nombreAdic
     *            the new nombre adic
     */
    public void setNombreAdic(String nombreAdic) {
        this.nombreAdic = nombreAdic;
    }

    /**
     * Gets the tipo documento adic.
     *
     * @return the tipo documento adic
     */
    public String getTipoDocumentoAdic() {
        return tipoDocumentoAdic;
    }

    /**
     * Sets the tipo documento adic.
     *
     * @param tipoDocumentoAdic
     *            the new tipo documento adic
     */
    public void setTipoDocumentoAdic(String tipoDocumentoAdic) {
        this.tipoDocumentoAdic = tipoDocumentoAdic;
    }

    /**
     * Gets the nro documento adic.
     *
     * @return the nro documento adic
     */
    public String getNroDocumentoAdic() {
        return nroDocumentoAdic;
    }

    /**
     * Sets the nro documento adic.
     *
     * @param nroDocumentoAdic
     *            the new nro documento adic
     */
    public void setNroDocumentoAdic(String nroDocumentoAdic) {
        this.nroDocumentoAdic = nroDocumentoAdic;
    }

    /**
     * Gets the fecha nacimiento adic.
     *
     * @return the fecha nacimiento adic
     */
    public String getFechaNacimientoAdic() {
        return fechaNacimientoAdic;
    }

    /**
     * Sets the fecha nacimiento adic.
     *
     * @param fechaNacimientoAdic
     *            the new fecha nacimiento adic
     */
    public void setFechaNacimientoAdic(String fechaNacimientoAdic) {
        this.fechaNacimientoAdic = fechaNacimientoAdic;
    }

    /**
     * Gets the nacionalidad.
     *
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the nacionalidad.
     *
     * @param nacionalidad
     *            the new nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Gets the sexo.
     *
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the sexo.
     *
     * @param sexo
     *            the new sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Gets the estado civil.
     *
     * @return the estado civil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the estado civil.
     *
     * @param estadoCivil
     *            the new estado civil
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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
     * @param domicilio
     *            the new domicilio
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Gets the nro.
     *
     * @return the nro
     */
    public String getNro() {
        return nro;
    }

    /**
     * Sets the nro.
     *
     * @param nro
     *            the new nro
     */
    public void setNro(String nro) {
        this.nro = nro;
    }

    /**
     * Gets the piso depto.
     *
     * @return the piso depto
     */
    public String getPisoDepto() {
        return pisoDepto;
    }

    /**
     * Sets the piso depto.
     *
     * @param pisoDepto
     *            the new piso depto
     */
    public void setPisoDepto(String pisoDepto) {
        this.pisoDepto = pisoDepto;
    }

    /**
     * Gets the localidad barrio.
     *
     * @return the localidad barrio
     */
    public String getLocalidadBarrio() {
        return localidadBarrio;
    }

    /**
     * Sets the localidad barrio.
     *
     * @param localidadBarrio
     *            the new localidad barrio
     */
    public void setLocalidadBarrio(String localidadBarrio) {
        this.localidadBarrio = localidadBarrio;
    }

    /**
     * Gets the codigo postal.
     *
     * @return the codigo postal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the codigo postal.
     *
     * @param codigoPostal
     *            the new codigo postal
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia
     *            the new provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the cod area.
     *
     * @return the cod area
     */
    public String getCodArea() {
        return codArea;
    }

    /**
     * Sets the cod area.
     *
     * @param codArea
     *            the new cod area
     */
    public void setCodArea(String codArea) {
        this.codArea = codArea;
    }

    /**
     * Gets the telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono.
     *
     * @param telefono
     *            the new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
