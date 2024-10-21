/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class DatosConfirmadosDelSolicitanteView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class DatosConfirmadosDelSolicitanteView extends RsaDTOParaDesafio {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8134260422991122646L;

    /** The comprobante. */
    private ComprobanteAltaTarjCredAdicionalView comprobante;

    /** The tarjetas. */
    private List<TarjetaCandidataView> tarjetas;

    /** The nup. */
    private String nup;

    /** The documento tipo. */
    private String documentoTipo;

    /** The documento nro. */
    private String documentoNro;

    /** The apellido. */
    private String apellido;

    /** The nombre. */
    private String nombre;

    /** The fecha nacimiento. */
    private String fechaNacimiento;

    /** The cuit. */
    private String cuit;

    /** The pais nacimiento. */
    private String paisNacimiento;

    /** The sexo. */
    private String sexo;

    /** The estado civil. */
    private String estadoCivil;

    /** The nacionalidad. */
    private String nacionalidad;

    /** The calle. */
    private String calle;

    /** The nro. */
    private String nro;

    /** The piso. */
    private String piso;

    /** The depto. */
    private String depto;

    /** The provincia. */
    private String provincia;

    /** The telediscado. */
    private String telediscado;

    /** The telefono. */
    private String telefono;

    /** The cp patente. */
    private String cpPatente;

    /** The cp. */
    private String cp;

    /** The cp manzana. */
    private String cpManzana;

    /** The localidad. */
    private String localidad;

    /** The cod provincia. */
    private String codProvincia;

    /** The tipo cuit cuil. */
    private String tipoCuitCuil;

    /**
     * Instantiates a new datos confirmados del solicitante view.
     */
    public DatosConfirmadosDelSolicitanteView() {
        super(OperacionesRSAEnum.SOLICITUD_TARJ_CREDITO_ADICIONAL);
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public ComprobanteAltaTarjCredAdicionalView getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the new comprobante
     */
    public void setComprobante(ComprobanteAltaTarjCredAdicionalView comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Gets the tarjetas.
     *
     * @return the tarjetas
     */
    public List<TarjetaCandidataView> getTarjetas() {
        return tarjetas;
    }

    /**
     * Sets the tarjetas.
     *
     * @param tarjetas
     *            the new tarjetas
     */
    public void setTarjetas(List<TarjetaCandidataView> tarjetas) {
        this.tarjetas = tarjetas;
    }

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the nup.
     *
     * @param nup
     *            the new nup
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the documento tipo.
     *
     * @return the documento tipo
     */
    public String getDocumentoTipo() {
        return documentoTipo;
    }

    /**
     * Sets the documento tipo.
     *
     * @param documentoTipo
     *            the new documento tipo
     */
    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    /**
     * Gets the documento nro.
     *
     * @return the documento nro
     */
    public String getDocumentoNro() {
        return documentoNro;
    }

    /**
     * Sets the documento nro.
     *
     * @param documentoNro
     *            the new documento nro
     */
    public void setDocumentoNro(String documentoNro) {
        this.documentoNro = documentoNro;
    }

    /**
     * Gets the apellido.
     *
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the apellido.
     *
     * @param apellido
     *            the new apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the fecha nacimiento.
     *
     * @return the fecha nacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the fecha nacimiento.
     *
     * @param fechaNacimiento
     *            the new fecha nacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Gets the cuit.
     *
     * @return the cuit
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the cuit.
     *
     * @param cuit
     *            the new cuit
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    /**
     * Gets the pais nacimiento.
     *
     * @return the pais nacimiento
     */
    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Sets the pais nacimiento.
     *
     * @param paisNacimiento
     *            the new pais nacimiento
     */
    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
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
     * Gets the calle.
     *
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the calle.
     *
     * @param calle
     *            the new calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
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
     * Gets the piso.
     *
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the piso.
     *
     * @param piso
     *            the new piso
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Gets the depto.
     *
     * @return the depto
     */
    public String getDepto() {
        return depto;
    }

    /**
     * Sets the depto.
     *
     * @param depto
     *            the new depto
     */
    public void setDepto(String depto) {
        this.depto = depto;
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
     * Gets the telediscado.
     *
     * @return the telediscado
     */
    public String getTelediscado() {
        return telediscado;
    }

    /**
     * Sets the telediscado.
     *
     * @param telediscado
     *            the new telediscado
     */
    public void setTelediscado(String telediscado) {
        this.telediscado = telediscado;
    }

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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

    /**
     * Gets the cp patente.
     *
     * @return the cp patente
     */
    public String getCpPatente() {
        return cpPatente;
    }

    /**
     * Sets the cp patente.
     *
     * @param cpPatente
     *            the new cp patente
     */
    public void setCpPatente(String cpPatente) {
        this.cpPatente = cpPatente;
    }

    /**
     * Gets the cp.
     *
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets the cp.
     *
     * @param cp
     *            the new cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * Gets the cp manzana.
     *
     * @return the cp manzana
     */
    public String getCpManzana() {
        return cpManzana;
    }

    /**
     * Sets the cp manzana.
     *
     * @param cpManzana
     *            the new cp manzana
     */
    public void setCpManzana(String cpManzana) {
        this.cpManzana = cpManzana;
    }

    /**
     * Gets the localidad.
     *
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the localidad.
     *
     * @param localidad
     *            the new localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Gets the cod provincia.
     *
     * @return the cod provincia
     */
    public String getCodProvincia() {
        return codProvincia;
    }

    /**
     * Sets the cod provincia.
     *
     * @param codProvincia
     *            the new cod provincia
     */
    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    /**
     * Gets the tipo cuit cuil.
     *
     * @return the tipo cuit cuil
     */
    public String getTipoCuitCuil() {
        return tipoCuitCuil;
    }

    /**
     * Sets the tipo cuit cuil.
     *
     * @param tipoCuitCuil
     *            the new tipo cuit cuil
     */
    public void setTipoCuitCuil(String tipoCuitCuil) {
        this.tipoCuitCuil = tipoCuitCuil;
    }
}
