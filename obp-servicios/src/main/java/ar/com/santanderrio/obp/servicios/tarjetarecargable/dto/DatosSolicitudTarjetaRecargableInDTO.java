/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.dto;

import org.apache.commons.lang.StringUtils;

/**
 * The Class DatosSolicitudTarjetaRecargableInDTO.
 */
public class DatosSolicitudTarjetaRecargableInDTO {

	/** The apellido adic. */
	private String apellidoAdic;

	/** The nombre adic. */
	private String nombreAdic;

	/** The nombre cliente. */
	private String nombreCliente;

	/** The apellido 1 cliente. */
	private String apellido1Cliente;

	/** The apellido 2 cliente. */
	private String apellido2Cliente;

	/** The nup cliente. */
	private String nupCliente;

	/** The tipo documento cliente. */
	private String tipoDocumentoCliente;

	/** The nro documento cliente. */
	private String nroDocumentoCliente;

	/** The tipo documento adic. */
	private String tipoDocumentoAdic;

	/** The nro documento adic. */
	private String nroDocumentoAdic;

	/** The fecha nacimiento adic. */
	private String fechaNacimientoAdic;

	/** The fecha nacimiento cliente. */
	private String fechaNacimientoCliente;

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

	/** The comentario. */
	private String comentario;

	/** The circuito. */
	private String circuito;

	/** The email. */
	private String email;

	/** The codigo aplicacion. */
	private String codigoAplicacion;

	/** The nro sucursal 4. */
	private String nroSucursal4;

	/** The nro cuenta producto. */
	private String nroCuentaProducto;

	/** The moneda altair. */
	private String monedaAltair;

	/** The nro orden firmante. */
	private String nroOrdenFirmante;

	/** The producto altair. */
	private String productoAltair;

	/** The subproducto altair. */
	private String subproductoAltair;

	/** The Constant CIRCUITO. */
	private static final String CIRCUITO = "29786";

	/** The Constant NRO_CUENTA_PRODUCTO. */
	private static final String NRO_CUENTA_PRODUCTO = "0000000000000000";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant NRO_ORDEN_FIRMANTE. */
	private static final String NRO_ORDEN_FIRMANTE = "00";

	/**
	 * Instantiates a new datos solicitud tarjeta recargable in DTO.
	 */
	public DatosSolicitudTarjetaRecargableInDTO() {
		this.circuito = CIRCUITO;
		this.email = StringUtils.EMPTY;
		this.codigoAplicacion = ESPACIO;
		this.nroSucursal4 = ESPACIO;
		this.nroCuentaProducto = NRO_CUENTA_PRODUCTO;
		this.monedaAltair = ESPACIO;
		this.nroOrdenFirmante = NRO_ORDEN_FIRMANTE;
		this.productoAltair = ESPACIO;
		this.subproductoAltair = ESPACIO;
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
	 * Gets the nombre cliente.
	 *
	 * @return the nombre cliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Sets the nombre cliente.
	 *
	 * @param nombreCliente
	 *            the new nombre cliente
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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
	 * Gets the fecha nacimiento cliente.
	 *
	 * @return the fecha nacimiento cliente
	 */
	public String getFechaNacimientoCliente() {
		return fechaNacimientoCliente;
	}

	/**
	 * Sets the fecha nacimiento cliente.
	 *
	 * @param fechaNacimientoCliente
	 *            the new fecha nacimiento cliente
	 */
	public void setFechaNacimientoCliente(String fechaNacimientoCliente) {
		this.fechaNacimientoCliente = fechaNacimientoCliente;
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

	/**
	 * Gets the comentario.
	 *
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Sets the comentario.
	 *
	 * @param comentario
	 *            the new comentario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Gets the circuito.
	 *
	 * @return the circuito
	 */
	public String getCircuito() {
		return circuito;
	}

	/**
	 * Sets the circuito.
	 *
	 * @param circuito
	 *            the new circuito
	 */
	public void setCircuito(String circuito) {
		this.circuito = circuito;
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
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the codigo aplicacion.
	 *
	 * @return the codigo aplicacion
	 */
	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	/**
	 * Sets the codigo aplicacion.
	 *
	 * @param codigoAplicacion
	 *            the new codigo aplicacion
	 */
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	/**
	 * Gets the nro sucursal 4.
	 *
	 * @return the nro sucursal 4
	 */
	public String getNroSucursal4() {
		return nroSucursal4;
	}

	/**
	 * Sets the nro sucursal 4.
	 *
	 * @param nroSucursal4
	 *            the new nro sucursal 4
	 */
	public void setNroSucursal4(String nroSucursal4) {
		this.nroSucursal4 = nroSucursal4;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the moneda altair.
	 *
	 * @return the moneda altair
	 */
	public String getMonedaAltair() {
		return monedaAltair;
	}

	/**
	 * Sets the moneda altair.
	 *
	 * @param monedaAltair
	 *            the new moneda altair
	 */
	public void setMonedaAltair(String monedaAltair) {
		this.monedaAltair = monedaAltair;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nro orden firmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the new nro orden firmante
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

	/**
	 * Gets the producto altair.
	 *
	 * @return the producto altair
	 */
	public String getProductoAltair() {
		return productoAltair;
	}

	/**
	 * Sets the producto altair.
	 *
	 * @param productoAltair
	 *            the new producto altair
	 */
	public void setProductoAltair(String productoAltair) {
		this.productoAltair = productoAltair;
	}

	/**
	 * Gets the subproducto altair.
	 *
	 * @return the subproducto altair
	 */
	public String getSubproductoAltair() {
		return subproductoAltair;
	}

	/**
	 * Sets the subproducto altair.
	 *
	 * @param subproductoAltair
	 *            the new subproducto altair
	 */
	public void setSubproductoAltair(String subproductoAltair) {
		this.subproductoAltair = subproductoAltair;
	}

	/**
	 * Gets the nup cliente.
	 *
	 * @return the nup cliente
	 */
	public String getNupCliente() {
		return nupCliente;
	}

	/**
	 * Sets the nup cliente.
	 *
	 * @param nupCliente
	 *            the new nup cliente
	 */
	public void setNupCliente(String nupCliente) {
		this.nupCliente = nupCliente;
	}

	/**
	 * Gets the tipo documento cliente.
	 *
	 * @return the tipo documento cliente
	 */
	public String getTipoDocumentoCliente() {
		return tipoDocumentoCliente;
	}

	/**
	 * Sets the tipo documento cliente.
	 *
	 * @param tipoDocumentoCliente
	 *            the new tipo documento cliente
	 */
	public void setTipoDocumentoCliente(String tipoDocumentoCliente) {
		this.tipoDocumentoCliente = tipoDocumentoCliente;
	}

	/**
	 * Gets the nro documento cliente.
	 *
	 * @return the nro documento cliente
	 */
	public String getNroDocumentoCliente() {
		return nroDocumentoCliente;
	}

	/**
	 * Sets the nro documento cliente.
	 *
	 * @param nroDocumentoCliente
	 *            the new nro documento cliente
	 */
	public void setNroDocumentoCliente(String nroDocumentoCliente) {
		this.nroDocumentoCliente = nroDocumentoCliente;
	}

	/**
	 * Gets the apellido 1 cliente.
	 *
	 * @return the apellido 1 cliente
	 */
	public String getApellido1Cliente() {
		return apellido1Cliente;
	}

	/**
	 * Sets the apellido 1 cliente.
	 *
	 * @param apellido1Cliente
	 *            the new apellido 1 cliente
	 */
	public void setApellido1Cliente(String apellido1Cliente) {
		this.apellido1Cliente = apellido1Cliente;
	}

	/**
	 * Gets the apellido 2 cliente.
	 *
	 * @return the apellido 2 cliente
	 */
	public String getApellido2Cliente() {
		return apellido2Cliente;
	}

	/**
	 * Sets the apellido 2 cliente.
	 *
	 * @param apellido2Cliente
	 *            the new apellido 2 cliente
	 */
	public void setApellido2Cliente(String apellido2Cliente) {
		this.apellido2Cliente = apellido2Cliente;
	}

}
