/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.web.view;

import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.billetera.web.view.DesafioView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.MedioDePagoComprobanteView;
import ar.com.santanderrio.obp.servicios.todopago.dto.DomicilioDTO;

/**
 * The Class ComprobanteAdhesionTodoPagoView.
 */
public class ComprobanteAdhesionTodoPagoView extends DesafioView {

	/** fecha solicitud. */
	private Date fechaSolicitud;

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
	private String paisOrigen;

	/** numero cuenta. */
	private String numeroCuenta;
	
    /** The descripcion tipo cuenta. */
    private String descripcionTipoCuenta;

	/** cbu. */
	private String cbu;

	/** telefono fijo. */
	private String telefonoFijo;

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

	/** domicilio legal. */
	private DomicilioDTO domicilioLegal;

	/** domicilio facturacion. */
	private DomicilioDTO domicilioFacturacion;

	/** envio mail satisfactorio. */
	private String envioMailSatisfactorio;

	/**
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            the new fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento
	 *            the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the numero documento.
	 *
	 * @return the numero documento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Sets the numero documento.
	 *
	 * @param numeroDocumento
	 *            the new numero documento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Gets the razon social.
	 *
	 * @return the razon social
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Sets the razon social.
	 *
	 * @param razonSocial
	 *            the new razon social
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the cuit dni.
	 *
	 * @return the cuit dni
	 */
	public String getCuitDni() {
		return cuitDni;
	}

	/**
	 * Sets the cuit dni.
	 *
	 * @param cuitDni
	 *            the new cuit dni
	 */
	public void setCuitDni(String cuitDni) {
		this.cuitDni = cuitDni;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
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
	 *            the new sexo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the pais origen.
	 *
	 * @return the pais origen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * Sets the pais origen.
	 *
	 * @param paisOrigen
	 *            the new pais origen
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
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
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the telefono fijo.
	 *
	 * @return the telefono fijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * Sets the telefono fijo.
	 *
	 * @param telefonoFijo
	 *            the new telefono fijo
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
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
	 *            the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the empresa celular.
	 *
	 * @return the empresa celular
	 */
	public String getEmpresaCelular() {
		return empresaCelular;
	}

	/**
	 * Sets the empresa celular.
	 *
	 * @param empresaCelular
	 *            the new empresa celular
	 */
	public void setEmpresaCelular(String empresaCelular) {
		this.empresaCelular = empresaCelular;
	}

	/**
	 * Gets the nombre contacto.
	 *
	 * @return the nombre contacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * Sets the nombre contacto.
	 *
	 * @param nombreContacto
	 *            the new nombre contacto
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * Gets the apellido contacto.
	 *
	 * @return the apellido contacto
	 */
	public String getApellidoContacto() {
		return apellidoContacto;
	}

	/**
	 * Sets the apellido contacto.
	 *
	 * @param apellidoContacto
	 *            the new apellido contacto
	 */
	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}

	/**
	 * Gets the condicion IVA.
	 *
	 * @return the condicion IVA
	 */
	public String getCondicionIVA() {
		return condicionIVA;
	}

	/**
	 * Sets the condicion IVA.
	 *
	 * @param condicionIVA
	 *            the new condicion IVA
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
	 *            the new actividad
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * Gets the condicion IIBB.
	 *
	 * @return the condicion IIBB
	 */
	public String getCondicionIIBB() {
		return condicionIIBB;
	}

	/**
	 * Sets the condicion IIBB.
	 *
	 * @param condicionIIBB
	 *            the new condicion IIBB
	 */
	public void setCondicionIIBB(String condicionIIBB) {
		this.condicionIIBB = condicionIIBB;
	}

	/**
	 * Gets the domicilio legal.
	 *
	 * @return the domicilio legal
	 */
	public DomicilioDTO getDomicilioLegal() {
		return domicilioLegal;
	}

	/**
	 * Sets the domicilio legal.
	 *
	 * @param domicilioLegal
	 *            the new domicilio legal
	 */
	public void setDomicilioLegal(DomicilioDTO domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}

	/**
	 * Gets the domicilio facturacion.
	 *
	 * @return the domicilio facturacion
	 */
	public DomicilioDTO getDomicilioFacturacion() {
		return domicilioFacturacion;
	}

	/**
	 * Sets the domicilio facturacion.
	 *
	 * @param domicilioFacturacion
	 *            the new domicilio facturacion
	 */
	public void setDomicilioFacturacion(DomicilioDTO domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}

	/**
	 * Gets the envio mail satisfactorio.
	 *
	 * @return the envio mail satisfactorio
	 */
	public String getEnvioMailSatisfactorio() {
		return envioMailSatisfactorio;
	}

	/**
	 * Sets the envio mail satisfactorio.
	 *
	 * @param envioMailSatisfactorio
	 *            the new envio mail satisfactorio
	 */
	public void setEnvioMailSatisfactorio(String envioMailSatisfactorio) {
		this.envioMailSatisfactorio = envioMailSatisfactorio;
	}

	/** The codigo area. */
	private String codigoArea;

	/** The cuenta. */
	private Integer cuenta;

	/** The email. */
	private String email;

	/** The empresa seleccionada. */
	private String empresaSeleccionada;

	/** The mensaje. */
	private String fecha;

	/** The legales SEO. */
	private String legalesSEO;

	/** The nroComprobante. */
	private String nroComprobante;

	/** The tarjetas ok. */
	private List<MedioDePagoComprobanteView> tarjetasOk;

	/** The telefono. */
	private String telefono;

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Integer getCuenta() {
		return cuenta;
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
	 * Gets the empresa seleccionada.
	 *
	 * @return the empresaSeleccionada
	 */
	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
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
	 * Gets the legales SEO.
	 *
	 * @return the legalesSEO
	 */
	public String getLegalesSEO() {
		return legalesSEO;
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
	 * Gets the tarjetas ok.
	 *
	 * @return the tarjetasOk
	 */
	public List<MedioDePagoComprobanteView> getTarjetasOk() {
		return tarjetasOk;
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
	 * Sets the codigo area.
	 *
	 * @param codigoArea
	 *            the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
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
	 * Sets the empresa seleccionada.
	 *
	 * @param empresaSeleccionada
	 *            the empresaSeleccionada to set
	 */
	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
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
	 * Sets the legales SEO.
	 *
	 * @param legalesSEO
	 *            the legalesSEO to set
	 */
	public void setLegalesSEO(String legalesSEO) {
		this.legalesSEO = legalesSEO;
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
	 * Sets the tarjetas ok.
	 *
	 * @param tarjetasOk
	 *            the tarjetasOk to set
	 */
	public void setTarjetasOk(List<MedioDePagoComprobanteView> tarjetasOk) {
		this.tarjetasOk = tarjetasOk;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
