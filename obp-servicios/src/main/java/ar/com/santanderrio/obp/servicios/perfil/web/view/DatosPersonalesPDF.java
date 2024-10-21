package ar.com.santanderrio.obp.servicios.perfil.web.view;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

import java.util.List;

public class DatosPersonalesPDF {

    private String responsableDatosNombre;

    private String responsableDatosCuit;

    private String responsableDatosDomicilio;

    private String nombreSolicitante;

    private String apellidoSolictante;

    private String documentoSolicitante;

    private String domcilioPrincipal;

    private String domicilioLaboral;

    private String numeroCelularPrimario;

    private String empresaCelularPrimario;

    private String numeroCelularSecundario;

    private String empresaCelularSecundario;

    private String emailPrimario;

    private String emailSecundario;

    private String legales;

    private String fechaComprobante;

    private AutentificacionDTO desafio;

    public DatosPersonalesPDF() {
        this.nombreSolicitante = ISBANStringUtils.GUION_STRING;
        this.apellidoSolictante = ISBANStringUtils.GUION_STRING;
        this.documentoSolicitante = ISBANStringUtils.GUION_STRING;
        this.domcilioPrincipal = ISBANStringUtils.GUION_STRING;
        this.domicilioLaboral = ISBANStringUtils.GUION_STRING;
        this.numeroCelularPrimario = ISBANStringUtils.GUION_STRING;
        this.empresaCelularPrimario = ISBANStringUtils.GUION_STRING;
        this.numeroCelularSecundario = ISBANStringUtils.GUION_STRING;
        this.empresaCelularSecundario = ISBANStringUtils.GUION_STRING;
        this.emailPrimario = ISBANStringUtils.GUION_STRING;
        this.emailSecundario = ISBANStringUtils.GUION_STRING;
    }

    public String getResponsableDatosNombre() {
        return responsableDatosNombre;
    }

    public void setResponsableDatosNombre(String responsableDatosNombre) {
        this.responsableDatosNombre = responsableDatosNombre;
    }

    public String getResponsableDatosCuit() {
        return responsableDatosCuit;
    }

    public void setResponsableDatosCuit(String responsableDatosCuit) {
        this.responsableDatosCuit = responsableDatosCuit;
    }

    public String getResponsableDatosDomicilio() {
        return responsableDatosDomicilio;
    }

    public void setResponsableDatosDomicilio(String responsableDatosDomicilio) {
        this.responsableDatosDomicilio = responsableDatosDomicilio;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getApellidoSolictante() {
        return apellidoSolictante;
    }

    public void setApellidoSolictante(String apellidoSolictante) {
        this.apellidoSolictante = apellidoSolictante;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public String getDomcilioPrincipal() {
        return domcilioPrincipal;
    }

    public void setDomcilioPrincipal(String domcilioPrincipal) {
        this.domcilioPrincipal = domcilioPrincipal;
    }

    public String getDomicilioLaboral() {
        return domicilioLaboral;
    }

    public void setDomicilioLaboral(String domicilioLaboral) {
        this.domicilioLaboral = domicilioLaboral;
    }

    public String getNumeroCelularPrimario() {
        return numeroCelularPrimario;
    }

    public void setNumeroCelularPrimario(String numeroCelularPrimario) {
        this.numeroCelularPrimario = numeroCelularPrimario;
    }

    public String getEmpresaCelularPrimario() {
        return empresaCelularPrimario;
    }

    public void setEmpresaCelularPrimario(String empresaCelularPrimario) {
        this.empresaCelularPrimario = empresaCelularPrimario;
    }

    public String getNumeroCelularSecundario() {
        return numeroCelularSecundario;
    }

    public void setNumeroCelularSecundario(String numeroCelularSecundario) {
        this.numeroCelularSecundario = numeroCelularSecundario;
    }

    public String getEmpresaCelularSecundario() {
        return empresaCelularSecundario;
    }

    public void setEmpresaCelularSecundario(String empresaCelularSecundario) {
        this.empresaCelularSecundario = empresaCelularSecundario;
    }

    public String getEmailPrimario() {
        return emailPrimario;
    }

    public void setEmailPrimario(String emailPrimario) {
        this.emailPrimario = emailPrimario;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public String getLegales() { return legales; }

    public void setLegales(String legales) { this.legales = legales; }

    public String getFechaComprobante() { return fechaComprobante; }

    public void setFechaComprobante(String fechaComprobante) { this.fechaComprobante = fechaComprobante; }

    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

}
