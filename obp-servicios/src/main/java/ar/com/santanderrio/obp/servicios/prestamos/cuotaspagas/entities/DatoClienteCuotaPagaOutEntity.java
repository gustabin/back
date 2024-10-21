/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class DatoClienteCuotaPagaOutEntity.
 *
 * @author florencia.n.martinez
 */
@Record
public class DatoClienteCuotaPagaOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetorno;

	/** The domicilio calle. */
	@Field
	private String domicilioCalle;

	/** The domicilio numero. */
	@Field
	private String domicilioNumero;

	/** The domicilio dto. */
	@Field
	private String domicilioDto;

	/** The domicilio piso. */
	@Field
	private String domicilioPiso;

	/** The domicilio localidad. */
	@Field
	private String domicilioLocalidad;

	/** The domicilio cod postal. */
	@Field
	private String domicilioCodPostal;

	/** The domicilio patente. */
	@Field
	private String domicilioPatente;

	/** The domicilio manzana. */
	@Field
	private String domicilioManzana;

	/** The domicilio provincia. */
	@Field
	private String domicilioProvincia;

	/** The domicilio cod pais. */
	@Field
	private String domicilioCodPais;

	/** The domicilio telefono. */
	@Field
	private String domicilioTelefono;

	/** The domicilio actividad. */
	@Field
	private String domicilioActividad;

	/** The cantidad repeticiones. */
	@Field
	private Long cantidadRepeticiones;

	/** The nup. */
	// @Segment(occursRef = "cantidadRepeticiones")
	@Field
	private String nup;

	/** The calidad participacion. */
	@Field
	private String calidadParticipacion;

	/** The orden participacion. */
	@Field
	private String ordenParticipacion;

	/** The estado relacion. */
	@Field
	private String estadoRelacion;

	/** The fecha baja rel. */
	@Field
	private String fechaBajaRel;

	/** The motivo baja. */
	@Field
	private String motivoBaja;

	/** The apellido. */
	@Field
	private String apellido;

	/** The nombre. */
	@Field
	private String nombre;

	/** The tipo documento. */
	@Field
	private String tipoDocumento;

	/** The nro documento. */
	@Field
	private String nroDocumento;

	/** The marca empleado. */
	@Field
	private String marcaEmpleado;

	/** The tipo inscripcion. */
	@Field
	private String tipoInscripcion;

	/** The nro inscripcion. */
	@Field
	private String nroInscripcion;

	/** The tipo persona. */
	@Field
	private String tipoPersona;

	/** The fecha nacimiento. */
	@Field
	private String fechaNacimiento;

	/** The res interes. */
	@Field
	private String resInteres;

	/** The marca paquete. */
	@Field
	private String marcaPaquete;

	/** The time stamp. */
	@Field
	private String timeStamp;

	/** The codigo condiciones. */
	@Field
	private String codigoCondiciones;

	/** The forma operar. */
	@Field
	private String formaOperar;

	/** The categoria. */
	@Field
	private String categoria;

	/** The grupo empresa. */
	@Field
	private String grupoEmpresa;

	/** The marca verificacion. */
	@Field
	private String marcaVerificacion;

	/** The adherido rio. */
	@Field
	private String adheridoRio;

	/** The fecha alta. */
	@Field
	private String fechaAlta;

	/**
	 * Instancia un nuevo objeto DatoClienteCuotaPagaOutEntity con el codigo de
	 * retorno extendido del servicio "CNINTERVI 120".
	 *
	 * @param codigoRetorno
	 *            the codigo retorno
	 */
	public DatoClienteCuotaPagaOutEntity(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Instancia un nuevo objeto DatoClienteCuotaPagaOutEntity.
	 * 
	 */
	public DatoClienteCuotaPagaOutEntity() {
		super();
	}

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the domicilio calle.
	 *
	 * @return the domicilioCalle
	 */
	public String getDomicilioCalle() {
		return domicilioCalle;
	}

	/**
	 * Sets the domicilio calle.
	 *
	 * @param domicilioCalle
	 *            the domicilioCalle to set
	 */
	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}

	/**
	 * Gets the domicilio numero.
	 *
	 * @return the domicilioNumero
	 */
	public String getDomicilioNumero() {
		return domicilioNumero;
	}

	/**
	 * Sets the domicilio numero.
	 *
	 * @param domicilioNumero
	 *            the domicilioNumero to set
	 */
	public void setDomicilioNumero(String domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}

	/**
	 * Gets the domicilio dto.
	 *
	 * @return the domicilioDto
	 */
	public String getDomicilioDto() {
		return domicilioDto;
	}

	/**
	 * Sets the domicilio dto.
	 *
	 * @param domicilioDto
	 *            the domicilioDto to set
	 */
	public void setDomicilioDto(String domicilioDto) {
		this.domicilioDto = domicilioDto;
	}

	/**
	 * Gets the domicilio piso.
	 *
	 * @return the domicilioPiso
	 */
	public String getDomicilioPiso() {
		return domicilioPiso;
	}

	/**
	 * Sets the domicilio piso.
	 *
	 * @param domicilioPiso
	 *            the domicilioPiso to set
	 */
	public void setDomicilioPiso(String domicilioPiso) {
		this.domicilioPiso = domicilioPiso;
	}

	/**
	 * Gets the domicilio localidad.
	 *
	 * @return the domicilioLocalidad
	 */
	public String getDomicilioLocalidad() {
		return domicilioLocalidad;
	}

	/**
	 * Sets the domicilio localidad.
	 *
	 * @param domicilioLocalidad
	 *            the domicilioLocalidad to set
	 */
	public void setDomicilioLocalidad(String domicilioLocalidad) {
		this.domicilioLocalidad = domicilioLocalidad;
	}

	/**
	 * Gets the domicilio cod postal.
	 *
	 * @return the domicilioCodPostal
	 */
	public String getDomicilioCodPostal() {
		return domicilioCodPostal;
	}

	/**
	 * Sets the domicilio cod postal.
	 *
	 * @param domicilioCodPostal
	 *            the domicilioCodPostal to set
	 */
	public void setDomicilioCodPostal(String domicilioCodPostal) {
		this.domicilioCodPostal = domicilioCodPostal;
	}

	/**
	 * Gets the domicilio patente.
	 *
	 * @return the domicilioPatente
	 */
	public String getDomicilioPatente() {
		return domicilioPatente;
	}

	/**
	 * Sets the domicilio patente.
	 *
	 * @param domicilioPatente
	 *            the domicilioPatente to set
	 */
	public void setDomicilioPatente(String domicilioPatente) {
		this.domicilioPatente = domicilioPatente;
	}

	/**
	 * Gets the domicilio manzana.
	 *
	 * @return the domicilioManzana
	 */
	public String getDomicilioManzana() {
		return domicilioManzana;
	}

	/**
	 * Sets the domicilio manzana.
	 *
	 * @param domicilioManzana
	 *            the domicilioManzana to set
	 */
	public void setDomicilioManzana(String domicilioManzana) {
		this.domicilioManzana = domicilioManzana;
	}

	/**
	 * Gets the domicilio provincia.
	 *
	 * @return the domicilioProvincia
	 */
	public String getDomicilioProvincia() {
		return domicilioProvincia;
	}

	/**
	 * Sets the domicilio provincia.
	 *
	 * @param domicilioProvincia
	 *            the domicilioProvincia to set
	 */
	public void setDomicilioProvincia(String domicilioProvincia) {
		this.domicilioProvincia = domicilioProvincia;
	}

	/**
	 * Gets the domicilio telefono.
	 *
	 * @return the domicilioTelefono
	 */
	public String getDomicilioTelefono() {
		return domicilioTelefono;
	}

	/**
	 * Sets the domicilio telefono.
	 *
	 * @param domicilioTelefono
	 *            the domicilioTelefono to set
	 */
	public void setDomicilioTelefono(String domicilioTelefono) {
		this.domicilioTelefono = domicilioTelefono;
	}

	/**
	 * Gets the domicilio actividad.
	 *
	 * @return the domicilioActividad
	 */
	public String getDomicilioActividad() {
		return domicilioActividad;
	}

	/**
	 * Sets the domicilio actividad.
	 *
	 * @param domicilioActividad
	 *            the domicilioActividad to set
	 */
	public void setDomicilioActividad(String domicilioActividad) {
		this.domicilioActividad = domicilioActividad;
	}

	/**
	 * Gets the cantidad repeticiones.
	 *
	 * @return the cantidadRepeticiones
	 */
	public Long getCantidadRepeticiones() {
		return cantidadRepeticiones;
	}

	/**
	 * Sets the cantidad repeticiones.
	 *
	 * @param cantidadRepeticiones
	 *            the cantidadRepeticiones to set
	 */
	public void setCantidadRepeticiones(Long cantidadRepeticiones) {
		this.cantidadRepeticiones = cantidadRepeticiones;
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
	 * Gets the calidad participacion.
	 *
	 * @return the calidadParticipacion
	 */
	public String getCalidadParticipacion() {
		return calidadParticipacion;
	}

	/**
	 * Sets the calidad participacion.
	 *
	 * @param calidadParticipacion
	 *            the calidadParticipacion to set
	 */
	public void setCalidadParticipacion(String calidadParticipacion) {
		this.calidadParticipacion = calidadParticipacion;
	}

	/**
	 * Gets the orden participacion.
	 *
	 * @return the ordenParticipacion
	 */
	public String getOrdenParticipacion() {
		return ordenParticipacion;
	}

	/**
	 * Sets the orden participacion.
	 *
	 * @param ordenParticipacion
	 *            the ordenParticipacion to set
	 */
	public void setOrdenParticipacion(String ordenParticipacion) {
		this.ordenParticipacion = ordenParticipacion;
	}

	/**
	 * Gets the estado relacion.
	 *
	 * @return the estadoRelacion
	 */
	public String getEstadoRelacion() {
		return estadoRelacion;
	}

	/**
	 * Sets the estado relacion.
	 *
	 * @param estadoRelacion
	 *            the estadoRelacion to set
	 */
	public void setEstadoRelacion(String estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}

	/**
	 * Gets the fecha baja rel.
	 *
	 * @return the fechaBajaRel
	 */
	public String getFechaBajaRel() {
		return fechaBajaRel;
	}

	/**
	 * Sets the fecha baja rel.
	 *
	 * @param fechaBajaRel
	 *            the fechaBajaRel to set
	 */
	public void setFechaBajaRel(String fechaBajaRel) {
		this.fechaBajaRel = fechaBajaRel;
	}

	/**
	 * Gets the motivo baja.
	 *
	 * @return the motivoBaja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Sets the motivo baja.
	 *
	 * @param motivoBaja
	 *            the motivoBaja to set
	 */
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
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
	 * Gets the nro documento.
	 *
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento
	 *            the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the marca empleado.
	 *
	 * @return the marcaEmpleado
	 */
	public String getMarcaEmpleado() {
		return marcaEmpleado;
	}

	/**
	 * Sets the marca empleado.
	 *
	 * @param marcaEmpleado
	 *            the marcaEmpleado to set
	 */
	public void setMarcaEmpleado(String marcaEmpleado) {
		this.marcaEmpleado = marcaEmpleado;
	}

	/**
	 * Gets the tipo inscripcion.
	 *
	 * @return the tipoInscripcion
	 */
	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	/**
	 * Sets the tipo inscripcion.
	 *
	 * @param tipoInscripcion
	 *            the tipoInscripcion to set
	 */
	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	/**
	 * Gets the nro inscripcion.
	 *
	 * @return the nroInscripcion
	 */
	public String getNroInscripcion() {
		return nroInscripcion;
	}

	/**
	 * Sets the nro inscripcion.
	 *
	 * @param nroInscripcion
	 *            the nroInscripcion to set
	 */
	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
	 * Gets the res interes.
	 *
	 * @return the resInteres
	 */
	public String getResInteres() {
		return resInteres;
	}

	/**
	 * Sets the res interes.
	 *
	 * @param resInteres
	 *            the resInteres to set
	 */
	public void setResInteres(String resInteres) {
		this.resInteres = resInteres;
	}

	/**
	 * Gets the marca paquete.
	 *
	 * @return the marcaPaquete
	 */
	public String getMarcaPaquete() {
		return marcaPaquete;
	}

	/**
	 * Sets the marca paquete.
	 *
	 * @param marcaPaquete
	 *            the marcaPaquete to set
	 */
	public void setMarcaPaquete(String marcaPaquete) {
		this.marcaPaquete = marcaPaquete;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the codigo condiciones.
	 *
	 * @return the codigoCondiciones
	 */
	public String getCodigoCondiciones() {
		return codigoCondiciones;
	}

	/**
	 * Sets the codigo condiciones.
	 *
	 * @param codigoCondiciones
	 *            the codigoCondiciones to set
	 */
	public void setCodigoCondiciones(String codigoCondiciones) {
		this.codigoCondiciones = codigoCondiciones;
	}

	/**
	 * Gets the forma operar.
	 *
	 * @return the formaOperar
	 */
	public String getFormaOperar() {
		return formaOperar;
	}

	/**
	 * Sets the forma operar.
	 *
	 * @param formaOperar
	 *            the formaOperar to set
	 */
	public void setFormaOperar(String formaOperar) {
		this.formaOperar = formaOperar;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the grupo empresa.
	 *
	 * @return the grupoEmpresa
	 */
	public String getGrupoEmpresa() {
		return grupoEmpresa;
	}

	/**
	 * Sets the grupo empresa.
	 *
	 * @param grupoEmpresa
	 *            the grupoEmpresa to set
	 */
	public void setGrupoEmpresa(String grupoEmpresa) {
		this.grupoEmpresa = grupoEmpresa;
	}

	/**
	 * Gets the marca verificacion.
	 *
	 * @return the marcaVerificacion
	 */
	public String getMarcaVerificacion() {
		return marcaVerificacion;
	}

	/**
	 * Sets the marca verificacion.
	 *
	 * @param marcaVerificacion
	 *            the marcaVerificacion to set
	 */
	public void setMarcaVerificacion(String marcaVerificacion) {
		this.marcaVerificacion = marcaVerificacion;
	}

	/**
	 * Gets the adherido rio.
	 *
	 * @return the adheridoRio
	 */
	public String getAdheridoRio() {
		return adheridoRio;
	}

	/**
	 * Sets the adherido rio.
	 *
	 * @param adheridoRio
	 *            the adheridoRio to set
	 */
	public void setAdheridoRio(String adheridoRio) {
		this.adheridoRio = adheridoRio;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the domicilio cod pais.
	 *
	 * @return the domicilioCodPais
	 */
	public String getDomicilioCodPais() {
		return domicilioCodPais;
	}

	/**
	 * Sets the domicilio cod pais.
	 *
	 * @param domicilioCodPais
	 *            the domicilioCodPais to set
	 */
	public void setDomicilioCodPais(String domicilioCodPais) {
		this.domicilioCodPais = domicilioCodPais;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadRepeticiones);
		hcb.append(codigoRetorno);
		hcb.append(domicilioActividad);
		hcb.append(domicilioCalle);
		hcb.append(domicilioCodPostal);
		hcb.append(domicilioDto);
		hcb.append(domicilioLocalidad);
		hcb.append(domicilioManzana);
		hcb.append(domicilioNumero);
		hcb.append(domicilioPatente);
		hcb.append(domicilioPiso);
		hcb.append(domicilioProvincia);
		hcb.append(domicilioCodPais);
		hcb.append(domicilioTelefono);
		hcb.append(headerTrama);
		hcb.append(fechaBajaRel);
		hcb.append(adheridoRio);
		hcb.append(apellido);
		hcb.append(calidadParticipacion);
		hcb.append(categoria);
		hcb.append(codigoCondiciones);
		hcb.append(estadoRelacion);
		hcb.append(fechaAlta);
		hcb.append(fechaNacimiento);
		hcb.append(formaOperar);
		hcb.append(grupoEmpresa);
		hcb.append(marcaEmpleado);
		hcb.append(marcaPaquete);
		hcb.append(marcaVerificacion);
		hcb.append(motivoBaja);
		hcb.append(nombre);
		hcb.append(nroDocumento);
		hcb.append(nroInscripcion);
		hcb.append(nup);
		hcb.append(ordenParticipacion);
		hcb.append(resInteres);
		hcb.append(timeStamp);
		hcb.append(tipoDocumento);
		hcb.append(tipoInscripcion);
		hcb.append(tipoPersona);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatoClienteCuotaPagaOutEntity other = (DatoClienteCuotaPagaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadRepeticiones, other.getCantidadRepeticiones());
		eb.append(codigoRetorno, other.getCodigoRetorno());
		eb.append(domicilioActividad, other.getDomicilioActividad());
		eb.append(domicilioCalle, other.getDomicilioCalle());
		eb.append(domicilioCodPostal, other.getDomicilioCodPostal());
		eb.append(domicilioDto, other.getDomicilioDto());
		eb.append(domicilioLocalidad, other.getDomicilioLocalidad());
		eb.append(domicilioManzana, other.getDomicilioManzana());
		eb.append(domicilioNumero, other.getDomicilioNumero());
		eb.append(domicilioPatente, other.getDomicilioPatente());
		eb.append(domicilioPiso, other.getDomicilioPiso());
		eb.append(domicilioProvincia, other.getDomicilioProvincia());
		eb.append(domicilioCodPais, other.getDomicilioCodPais());
		eb.append(domicilioTelefono, other.getDomicilioTelefono());
		eb.append(headerTrama, other.getHeaderTrama());
		eb.append(fechaBajaRel, other.getFechaBajaRel());
		eb.append(adheridoRio, other.getAdheridoRio());
		eb.append(apellido, other.getApellido());
		eb.append(calidadParticipacion, other.getCalidadParticipacion());
		eb.append(categoria, other.getCategoria());
		eb.append(codigoCondiciones, other.getCodigoCondiciones());
		eb.append(estadoRelacion, other.getEstadoRelacion());
		eb.append(fechaAlta, other.getFechaAlta());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(formaOperar, other.getFormaOperar());
		eb.append(grupoEmpresa, other.getGrupoEmpresa());
		eb.append(marcaEmpleado, other.getMarcaEmpleado());
		eb.append(marcaPaquete, other.getMarcaPaquete());
		eb.append(marcaVerificacion, other.getMarcaVerificacion());
		eb.append(motivoBaja, other.getMotivoBaja());
		eb.append(nombre, other.getNombre());
		eb.append(nroDocumento, other.getNroDocumento());
		eb.append(nroInscripcion, other.getNroInscripcion());
		eb.append(nup, other.getNup());
		eb.append(ordenParticipacion, other.getOrdenParticipacion());
		eb.append(resInteres, other.getResInteres());
		eb.append(timeStamp, other.getTimeStamp());
		eb.append(tipoDocumento, other.getTipoDocumento());
		eb.append(tipoInscripcion, other.getTipoInscripcion());
		eb.append(tipoPersona, other.getTipoPersona());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("headerTrama", headerTrama).append("codigoRetorno", codigoRetorno)
				.append("domicilioCalle", domicilioCalle).append("domicilioNumero", domicilioNumero)
				.append("domicilioDto", domicilioDto).append("domicilioPiso", domicilioPiso)
				.append("domicilioLocalidad", domicilioLocalidad).append("domicilioCodPostal", domicilioCodPostal)
				.append("domicilioPatente", domicilioPatente).append("domicilioManzana", domicilioManzana)
				.append("domicilioProvincia", domicilioProvincia).append("domicilioCodPais", domicilioCodPais)
				.append("domicilioTelefono", domicilioTelefono).append("domicilioActividad", domicilioActividad)
				.append("cantidadRepeticiones", cantidadRepeticiones).append("nup", nup)
				.append("calidadParticipacion", calidadParticipacion).append("ordenParticipacion", ordenParticipacion)
				.append("estadoRelacion", estadoRelacion).append("fechaBajaRel", fechaBajaRel)
				.append("motivoBaja", motivoBaja).append("apellido", apellido).append("nombre", nombre)
				.append("tipoDocumento", tipoDocumento).append("nroDocumento", nroDocumento)
				.append("marcaEmpleado", marcaEmpleado).append("tipoInscripcion", tipoInscripcion)
				.append("nroInscripcion", nroInscripcion).append("tipoPersona", tipoPersona)
				.append("fechaNacimiento", fechaNacimiento).append("resInteres", resInteres)
				.append("marcaPaquete", marcaPaquete).append("timeStamp", timeStamp)
				.append("codigoCondiciones", codigoCondiciones).append("formaOperar", formaOperar)
				.append("categoria", categoria).append("grupoEmpresa", grupoEmpresa)
				.append("marcaVerificacion", marcaVerificacion).append("adheridoRio", adheridoRio)
				.append("fechaAlta", fechaAlta).toString();
	}

}