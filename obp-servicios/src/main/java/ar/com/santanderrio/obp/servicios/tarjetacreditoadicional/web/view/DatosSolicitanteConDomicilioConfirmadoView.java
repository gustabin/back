/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class DatosSolicitanteConDomicilioConfirmadoView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosSolicitanteConDomicilioConfirmadoView extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6000295641091727166L;

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

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

	/** The localidad barrio. */
	private String localidadBarrio;

	/** The codigo postal. */
	private String codigoPostal;

	/** The provincia. */
	private String provincia;

	/** The telefono. */
	private String telefono;

	// ComprobanteAltaTarjCredAdicionalView

	/** The apellido nombre adicional. */
	private String apellidoNombreAdicional;

	/** The dni adicional. */
	private String dniAdicional;

	/** The tarjetas adicionales solicitadas. */
	private List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas;

	/** The fecha hora. */
	private String fechaHora;

	/** The legal. */
	private String legal;

	/**
	 * Instantiates a new datos solicitante con domicilio confirmado view.
	 */
	public DatosSolicitanteConDomicilioConfirmadoView() {
		super(OperacionesRSAEnum.SOLICITUD_TARJ_CREDITO_ADICIONAL);
	}

	/**
	 * Gets the apellido nombre adicional.
	 *
	 * @return the apellidoNombreAdicional
	 */
	public String getApellidoNombreAdicional() {
		return apellidoNombreAdicional;
	}

	/**
	 * Sets the apellido nombre adicional.
	 *
	 * @param apellidoNombreAdicional
	 *            the apellidoNombreAdicional to set
	 */
	public void setApellidoNombreAdicional(String apellidoNombreAdicional) {
		this.apellidoNombreAdicional = apellidoNombreAdicional;
	}

	/**
	 * Gets the dni adicional.
	 *
	 * @return the dniAdicional
	 */
	public String getDniAdicional() {
		return dniAdicional;
	}

	/**
	 * Sets the dni adicional.
	 *
	 * @param dniAdicional
	 *            the dniAdicional to set
	 */
	public void setDniAdicional(String dniAdicional) {
		this.dniAdicional = dniAdicional;
	}

	/**
	 * Gets the tarjetas adicionales solicitadas.
	 *
	 * @return the tarjetasAdicionalesSolicitadas
	 */
	public List<TarjetaAdicionalSolicitadaView> getTarjetasAdicionalesSolicitadas() {
		return tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Sets the tarjetas adicionales solicitadas.
	 *
	 * @param tarjetasAdicionalesSolicitadas
	 *            the tarjetasAdicionalesSolicitadas to set
	 */
	public void setTarjetasAdicionalesSolicitadas(List<TarjetaAdicionalSolicitadaView> tarjetasAdicionalesSolicitadas) {
		this.tarjetasAdicionalesSolicitadas = tarjetasAdicionalesSolicitadas;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
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
	 *            the tarjetas to set
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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the documento tipo.
	 *
	 * @return the documentoTipo
	 */
	public String getDocumentoTipo() {
		return documentoTipo;
	}

	/**
	 * Sets the documento tipo.
	 *
	 * @param documentoTipo
	 *            the documentoTipo to set
	 */
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	/**
	 * Gets the documento nro.
	 *
	 * @return the documentoNro
	 */
	public String getDocumentoNro() {
		return documentoNro;
	}

	/**
	 * Sets the documento nro.
	 *
	 * @param documentoNro
	 *            the documentoNro to set
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
	 *            the apellido to set
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
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
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
	 *            the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the pais nacimiento.
	 *
	 * @return the paisNacimiento
	 */
	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	/**
	 * Sets the pais nacimiento.
	 *
	 * @param paisNacimiento
	 *            the paisNacimiento to set
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
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Gets the estado civil.
	 *
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Sets the estado civil.
	 *
	 * @param estadoCivil
	 *            the estadoCivil to set
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
	 *            the nacionalidad to set
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
	 *            the calle to set
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
	 *            the nro to set
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
	 *            the piso to set
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
	 *            the depto to set
	 */
	public void setDepto(String depto) {
		this.depto = depto;
	}

	/**
	 * Gets the localidad barrio.
	 *
	 * @return the localidadBarrio
	 */
	public String getLocalidadBarrio() {
		return localidadBarrio;
	}

	/**
	 * Sets the localidad barrio.
	 *
	 * @param localidadBarrio
	 *            the localidadBarrio to set
	 */
	public void setLocalidadBarrio(String localidadBarrio) {
		this.localidadBarrio = localidadBarrio;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the codigoPostal to set
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
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 *            the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

}
