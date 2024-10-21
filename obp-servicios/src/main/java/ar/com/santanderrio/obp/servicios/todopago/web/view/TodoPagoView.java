/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.web.view;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.billetera.web.view.CuentaAcreditacionView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.DesafioView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.EmpresaCelularView;
import ar.com.santanderrio.obp.servicios.todopago.dto.DomicilioDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.PaisOrigenDTO;

/**
 * The Class TodoPagoView.
 */
public class TodoPagoView extends DesafioView {

    /** fecha solicitud. */
    private Date fechaSolicitud;

    /** The nombre. */
    private String nombre;

    /** The apellido. */
    private String apellido;

    /** The tyc url. */
    private String tycUrl;

    /** nup. */
    private String nup;

    /** tipo documento. */
    private String tipoDocumento;

    /** numero documento. */
    private String numeroDocumento;

    /** razon social. */
    private String razonSocial;

    /** cuit dni. */
    private String cuitDni;

    /** fecha nacimiento. */
    private Date fechaNacimiento;

    /** sexo. */
    private String sexo;

    /** pais origen. */
    private PaisOrigenDTO paisOrigen;

    /** numero cuenta. */
    private String numeroCuenta;
    
    /** The descripcion tipo cuenta. */
    private String descripcionTipoCuenta;

    /** cbu. */
    private String cbu;

    /** email. */
    private String email;

    /** telefono fijo. */
    private String telefonoFijo;

    /** The cod area celular. */
    private String codAreaCelular;

    /** celular. */
    private String celular;

    /** empresa celular. */
    private String empresaCelular;

    /** nombre contacto. */
    private String nombreContacto;

    /** apellido contacto. */
    private String apellidoContacto;

    /** condicion IVA. */
    private String condicionIVA;

    /** actividad. */
    private String actividad;

    /** condicion IIBB. */
    private String condicionIIBB;

    /** The numero IIBB. */
    private String numeroIIBB;

    /** domicilio legal. */
    private DomicilioDTO domicilioLegal;

    /** domicilio facturacion. */
    private DomicilioDTO domicilioFacturacion;

    /** envio mail satisfactorio. */
    private String envioMailSatisfactorio;

    /** The combo pais. */
    private List<ComboView> comboPais;

    /** The combo provincia. */
    private List<ComboView> comboProvincia;

    /** The combo sexo. */
    private List<ComboView> comboSexo;

    /** The combo empresa. */
    private List<EmpresaCelularView> comboEmpresa;

    /** The combo iibb. */
    private List<ComboView> comboIibb;

    /** The combo iva. */
    private List<ComboView> comboIva;

    /** The cuentas destino. */
    private List<CuentaAcreditacionView> cuentasDestino;

    /** The url AC. */
    private String urlAC;

    /**
	 * Gets the fecha solicitud.
	 *
	 * @return the fechaSolicitud
	 */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the fechaSolicitud to set
	 */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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
	 *            the nombre to set
	 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
	 *            the apellido to set
	 */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
	 * Gets the tyc url.
	 *
	 * @return the tycUrl
	 */
    public String getTycUrl() {
        return tycUrl;
    }

    /**
	 * Sets the tyc url.
	 *
	 * @param tycUrl
	 *            the tycUrl to set
	 */
    public void setTycUrl(String tycUrl) {
        this.tycUrl = tycUrl;
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
	 *            the nup to set
	 */
    public void setNup(String nup) {
        this.nup = nup;
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
	 * Gets the cuit dni.
	 *
	 * @return the cuitDni
	 */
    public String getCuitDni() {
        return cuitDni;
    }

    /**
	 * Sets the cuit dni.
	 *
	 * @param cuitDni
	 *            the cuitDni to set
	 */
    public void setCuitDni(String cuitDni) {
        this.cuitDni = cuitDni;
    }

    /**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
	 *            the sexo to set
	 */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
	 * Gets the pais origen.
	 *
	 * @return the paisOrigen
	 */
    public PaisOrigenDTO getPaisOrigen() {
        return paisOrigen;
    }

    /**
	 * Sets the pais origen.
	 *
	 * @param paisOrigen
	 *            the paisOrigen to set
	 */
    public void setPaisOrigen(PaisOrigenDTO paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    /**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
    public String getEmail() {
        return email;
    }

    /**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
	 * Gets the telefono fijo.
	 *
	 * @return the telefonoFijo
	 */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
	 * Sets the telefono fijo.
	 *
	 * @param telefonoFijo
	 *            the telefonoFijo to set
	 */
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    /**
	 * Gets the cod area celular.
	 *
	 * @return the codAreaCelular
	 */
    public String getCodAreaCelular() {
        return codAreaCelular;
    }

    /**
	 * Sets the cod area celular.
	 *
	 * @param codAreaCelular
	 *            the codAreaCelular to set
	 */
    public void setCodAreaCelular(String codAreaCelular) {
        this.codAreaCelular = codAreaCelular;
    }

    /**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
    public String getCelular() {
        return celular;
    }

    /**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the celular to set
	 */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
	 * Gets the empresa celular.
	 *
	 * @return the empresaCelular
	 */
    public String getEmpresaCelular() {
        return empresaCelular;
    }

    /**
	 * Sets the empresa celular.
	 *
	 * @param empresaCelular
	 *            the empresaCelular to set
	 */
    public void setEmpresaCelular(String empresaCelular) {
        this.empresaCelular = empresaCelular;
    }

    /**
	 * Gets the nombre contacto.
	 *
	 * @return the nombreContacto
	 */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
	 * Sets the nombre contacto.
	 *
	 * @param nombreContacto
	 *            the nombreContacto to set
	 */
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
	 * Gets the apellido contacto.
	 *
	 * @return the apellidoContacto
	 */
    public String getApellidoContacto() {
        return apellidoContacto;
    }

    /**
	 * Sets the apellido contacto.
	 *
	 * @param apellidoContacto
	 *            the apellidoContacto to set
	 */
    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    /**
	 * Gets the condicion IVA.
	 *
	 * @return the condicionIVA
	 */
    public String getCondicionIVA() {
        return condicionIVA;
    }

    /**
	 * Sets the condicion IVA.
	 *
	 * @param condicionIVA
	 *            the condicionIVA to set
	 */
    public void setCondicionIVA(String condicionIVA) {
        this.condicionIVA = condicionIVA;
    }

    /**
	 * Gets the actividad.
	 *
	 * @return the actividad
	 */
    public String getActividad() {
        return actividad;
    }

    /**
	 * Sets the actividad.
	 *
	 * @param actividad
	 *            the actividad to set
	 */
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    /**
	 * Gets the condicion IIBB.
	 *
	 * @return the condicionIIBB
	 */
    public String getCondicionIIBB() {
        return condicionIIBB;
    }

    /**
	 * Sets the condicion IIBB.
	 *
	 * @param condicionIIBB
	 *            the condicionIIBB to set
	 */
    public void setCondicionIIBB(String condicionIIBB) {
        this.condicionIIBB = condicionIIBB;
    }

    /**
	 * Gets the numero IIBB.
	 *
	 * @return the numeroIIBB
	 */
    public String getNumeroIIBB() {
        return numeroIIBB;
    }

    /**
	 * Sets the numero IIBB.
	 *
	 * @param numeroIIBB
	 *            the numeroIIBB to set
	 */
    public void setNumeroIIBB(String numeroIIBB) {
        this.numeroIIBB = numeroIIBB;
    }

    /**
	 * Gets the domicilio legal.
	 *
	 * @return the domicilioLegal
	 */
    public DomicilioDTO getDomicilioLegal() {
        return domicilioLegal;
    }

    /**
	 * Sets the domicilio legal.
	 *
	 * @param domicilioLegal
	 *            the domicilioLegal to set
	 */
    public void setDomicilioLegal(DomicilioDTO domicilioLegal) {
        this.domicilioLegal = domicilioLegal;
    }

    /**
	 * Gets the domicilio facturacion.
	 *
	 * @return the domicilioFacturacion
	 */
    public DomicilioDTO getDomicilioFacturacion() {
        return domicilioFacturacion;
    }

    /**
	 * Sets the domicilio facturacion.
	 *
	 * @param domicilioFacturacion
	 *            the domicilioFacturacion to set
	 */
    public void setDomicilioFacturacion(DomicilioDTO domicilioFacturacion) {
        this.domicilioFacturacion = domicilioFacturacion;
    }

    /**
	 * Gets the envio mail satisfactorio.
	 *
	 * @return the envioMailSatisfactorio
	 */
    public String getEnvioMailSatisfactorio() {
        return envioMailSatisfactorio;
    }

    /**
	 * Sets the envio mail satisfactorio.
	 *
	 * @param envioMailSatisfactorio
	 *            the envioMailSatisfactorio to set
	 */
    public void setEnvioMailSatisfactorio(String envioMailSatisfactorio) {
        this.envioMailSatisfactorio = envioMailSatisfactorio;
    }

    /**
	 * Gets the combo pais.
	 *
	 * @return the comboPais
	 */
    public List<ComboView> getComboPais() {
        return comboPais;
    }

    /**
	 * Sets the combo pais.
	 *
	 * @param comboPais
	 *            the comboPais to set
	 */
    public void setComboPais(List<ComboView> comboPais) {
        this.comboPais = comboPais;
    }

    /**
	 * Gets the combo provincia.
	 *
	 * @return the comboProvincia
	 */
    public List<ComboView> getComboProvincia() {
        return comboProvincia;
    }

    /**
	 * Sets the combo provincia.
	 *
	 * @param comboProvincia
	 *            the comboProvincia to set
	 */
    public void setComboProvincia(List<ComboView> comboProvincia) {
        this.comboProvincia = comboProvincia;
    }

    /**
	 * Gets the combo sexo.
	 *
	 * @return the comboSexo
	 */
    public List<ComboView> getComboSexo() {
        return comboSexo;
    }

    /**
	 * Sets the combo sexo.
	 *
	 * @param comboSexo
	 *            the comboSexo to set
	 */
    public void setComboSexo(List<ComboView> comboSexo) {
        this.comboSexo = comboSexo;
    }

    /**
	 * Gets the combo empresa.
	 *
	 * @return the comboEmpresa
	 */
    public List<EmpresaCelularView> getComboEmpresa() {
        return comboEmpresa;
    }

    /**
	 * Sets the combo empresa.
	 *
	 * @param comboEmpresa
	 *            the comboEmpresa to set
	 */
    public void setComboEmpresa(List<EmpresaCelularView> comboEmpresa) {
        this.comboEmpresa = comboEmpresa;
    }

    /**
	 * Gets the combo iibb.
	 *
	 * @return the comboIibb
	 */
    public List<ComboView> getComboIibb() {
        return comboIibb;
    }

    /**
	 * Sets the combo iibb.
	 *
	 * @param comboIibb
	 *            the comboIibb to set
	 */
    public void setComboIibb(List<ComboView> comboIibb) {
        this.comboIibb = comboIibb;
    }

    /**
	 * Gets the combo iva.
	 *
	 * @return the comboIva
	 */
    public List<ComboView> getComboIva() {
        return comboIva;
    }

    /**
	 * Sets the combo iva.
	 *
	 * @param comboIva
	 *            the comboIva to set
	 */
    public void setComboIva(List<ComboView> comboIva) {
        this.comboIva = comboIva;
    }

    /**
	 * Gets the cuentas destino.
	 *
	 * @return the cuentasDestino
	 */
    public List<CuentaAcreditacionView> getCuentasDestino() {
        return cuentasDestino;
    }

    /**
	 * Sets the cuentas destino.
	 *
	 * @param cuentasDestino
	 *            the cuentasDestino to set
	 */
    public void setCuentasDestino(List<CuentaAcreditacionView> cuentasDestino) {
        this.cuentasDestino = cuentasDestino;
    }

    /**
	 * Gets the url AC.
	 *
	 * @return the urlAC
	 */
    public String getUrlAC() {
        return urlAC;
    }

    /**
	 * Sets the url AC.
	 *
	 * @param urlAC
	 *            the urlAC to set
	 */
    public void setUrlAC(String urlAC) {
        this.urlAC = urlAC;
    }

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the descripcionTipoCuenta to set
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}
    
    

}
