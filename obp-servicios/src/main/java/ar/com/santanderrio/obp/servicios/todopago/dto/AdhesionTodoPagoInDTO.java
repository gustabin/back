/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Clase SolicitudTodoPagoDTO que representa una fila en la base de datos para
 * registrar la adhesion de un usuario.
 */
public class AdhesionTodoPagoInDTO {

	/** fecha solicitud. */
	private Date fechaSolicitud;

	/** The nombre. */
	private String nombre;

	/** The apellido. */
	private String apellido;

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

	/** email. */
	private String email;

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
	 * Instancia una nueva solicitud TodoPago DTO.
	 */
	public AdhesionTodoPagoInDTO() {
		// no implementado aun.
	}

	/**
	 * Retorna fecha solicitud.
	 *
	 * @return fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Setea fecha solicitud.
	 *
	 * @param fechaSolicitud
	 *            fechaSolicitud a setear
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Retorna nup.
	 *
	 * @return nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Setea nup.
	 *
	 * @param nup
	 *            nup a setear
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Retorna tipo documento.
	 *
	 * @return tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Setea tipo documento.
	 *
	 * @param tipoDocumento
	 *            tipoDocumento a setear
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Retorna numero documento.
	 *
	 * @return numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Setea numero documento.
	 *
	 * @param numeroDocumento
	 *            numeroDocumento a setear
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Retorna razon social.
	 *
	 * @return razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Setea razon social.
	 *
	 * @param razonSocial
	 *            razonSocial a setear
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * Retorna cuit dni.
	 *
	 * @return cuitDni
	 */
	public String getCuitDni() {
		return cuitDni;
	}

	/**
	 * Setea cuit dni.
	 *
	 * @param cuitDni
	 *            cuitDni a setear
	 */
	public void setCuitDni(String cuitDni) {
		this.cuitDni = cuitDni;
	}

	/**
	 * Retorna sexo.
	 *
	 * @return sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Setea sexo.
	 *
	 * @param sexo
	 *            sexo a setear
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Retorna pais origen.
	 *
	 * @return paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * Setea pais origen.
	 *
	 * @param paisOrigen
	 *            paisOrigen a setear
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * Retorna numero cuenta.
	 *
	 * @return numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Setea numero cuenta.
	 *
	 * @param numeroCuenta
	 *            numeroCuenta a setear
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Retorna cbu.
	 *
	 * @return cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Setea cbu.
	 *
	 * @param cbu
	 *            cbu a setear
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Retorna email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setea email.
	 *
	 * @param email
	 *            email a setear
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retorna telefono fijo.
	 *
	 * @return telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * Setea telefono fijo.
	 *
	 * @param telefonoFijo
	 *            telefonoFijo a setear
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * Retorna celular.
	 *
	 * @return celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Setea celular.
	 *
	 * @param celular
	 *            celular a setear
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Retorna empresa celular.
	 *
	 * @return empresaCelular
	 */
	public String getEmpresaCelular() {
		return empresaCelular;
	}

	/**
	 * Setea empresa celular.
	 *
	 * @param empresaCelular
	 *            empresaCelular a setear
	 */
	public void setEmpresaCelular(String empresaCelular) {
		this.empresaCelular = empresaCelular;
	}

	/**
	 * Retorna nombre contacto.
	 *
	 * @return nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * Setea nombre contacto.
	 *
	 * @param nombreContacto
	 *            nombreContacto a setear
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * Retorna apellido contacto.
	 *
	 * @return apellidoContacto
	 */
	public String getApellidoContacto() {
		return apellidoContacto;
	}

	/**
	 * Setea apellido contacto.
	 *
	 * @param apellidoContacto
	 *            apellidoContacto a setear
	 */
	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}

	/**
	 * Retorna condicion IVA.
	 *
	 * @return condicionIVA
	 */
	public String getCondicionIVA() {
		return condicionIVA;
	}

	/**
	 * Setea condicion IVA.
	 *
	 * @param condicionIVA
	 *            condicionIVA a setear
	 */
	public void setCondicionIVA(String condicionIVA) {
		this.condicionIVA = condicionIVA;
	}

	/**
	 * Retorna actividad.
	 *
	 * @return actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * Setea actividad.
	 *
	 * @param actividad
	 *            actividad a setear
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * Retorna condicion IIBB.
	 *
	 * @return condicionIIBB
	 */
	public String getCondicionIIBB() {
		return condicionIIBB;
	}

	/**
	 * Setea condicion IIBB.
	 *
	 * @param condicionIIBB
	 *            condicionIIBB a setear
	 */
	public void setCondicionIIBB(String condicionIIBB) {
		this.condicionIIBB = condicionIIBB;
	}

	/**
	 * Retorna domicilio legal.
	 *
	 * @return domicilioLegal
	 */
	public DomicilioDTO getDomicilioLegal() {
		return domicilioLegal;
	}

	/**
	 * Setea domicilio legal.
	 *
	 * @param domicilioLegal
	 *            domicilioLegal a setear
	 */
	public void setDomicilioLegal(DomicilioDTO domicilioLegal) {
		this.domicilioLegal = domicilioLegal;
	}

	/**
	 * Retorna domicilio facturacion.
	 *
	 * @return domicilioFacturacion
	 */
	public DomicilioDTO getDomicilioFacturacion() {
		return domicilioFacturacion;
	}

	/**
	 * Setea domicilio facturacion.
	 *
	 * @param domicilioFacturacion
	 *            domicilioFacturacion a setear
	 */
	public void setDomicilioFacturacion(DomicilioDTO domicilioFacturacion) {
		this.domicilioFacturacion = domicilioFacturacion;
	}

	/**
	 * Retorna envio mail satisfactorio.
	 *
	 * @return envioMailSatisfactorio
	 */
	public String getEnvioMailSatisfactorio() {
		return envioMailSatisfactorio;
	}

	/**
	 * Setea envio mail satisfactorio.
	 *
	 * @param envioMailSatisfactorio
	 *            envioMailSatisfactorio a setear
	 */
	public void setEnvioMailSatisfactorio(String envioMailSatisfactorio) {
		this.envioMailSatisfactorio = envioMailSatisfactorio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nup);
		hcb.append(tipoDocumento);
		hcb.append(numeroDocumento);
		hcb.append(numeroCuenta);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdhesionTodoPagoInDTO other = (AdhesionTodoPagoInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nup, other.getNup());
		eb.append(tipoDocumento, other.getTipoDocumento());
		eb.append(numeroDocumento, other.getNumeroDocumento());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("nup", nup).append("tipoDocumento", tipoDocumento)
				.append("numeroDocumento", numeroDocumento).append("numeroCuenta", numeroCuenta).toString();
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
