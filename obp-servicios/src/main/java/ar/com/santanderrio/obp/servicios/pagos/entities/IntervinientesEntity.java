/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author pablo.d.gargaglione
 *
 */
public class IntervinientesEntity {

	/** The nup. */
	@Field()
	private String nup;

	/** The calidad participacion. */
	@Field()
	private String calidadParticipacion;

	/** The orden participacion. */
	@Field()
	private String ordenParticipacion;

	/** The estado relacion. */
	@Field()
	private String estadoRelacion;

	/** The fecha baja rel. */
	@Field()
	private String fechaBajaRel;

	/** The motivo baja. */
	@Field()
	private String motivoBaja;

	/** The apellido. */
	@Field()
	private String apellido;

	/** The nombre. */
	@Field()
	private String nombre;

	/** The tipo documento. */
	@Field()
	private String tipoDocumento;

	/** The numeroDocumento. */
	@Field()
	private String numeroDocumento;

	/** The marca empleado. */
	@Field()
	private String marcaEmpleado;

	/** The tipo inscripcion. */
	@Field()
	private String tipoInscripcion;

	/** The numero inscripcion. */
	@Field()
	private String numeroInscripcion;

	/** The tipo persona. */
	@Field()
	private String tipoPersona;

	/** The fecha nacimiento. */
	@Field()
	private String fechaNacimiento;

	/** res interes. */
	@Field()
	private String resInteres;

	/** The marca paquete. */
	@Field()
	private String marcaPaquete;

	/** The time stamp. */
	@Field()
	private String timeStamp;

	/** The codigo condiciones. */
	@Field()
	private String codigoCondiciones;

	/** The formaOperar. */
	@Field()
	private String formaOperar;

	/** The categoria. */
	@Field()
	private String categoria;

	/** The grupo empresa. */
	@Field()
	private String grupoEmpresa;

	/** The marca verificacion. */
	@Field()
	private String marcaVerificacion;

	/** The adherido Rio. */
	@Field()
	private String adheridoRio;

	/** The fecha alta. */
	@Field()
	private String fechaAlta;

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
	 * Gets the calidad participacion.
	 *
	 * @return the calidad participacion
	 */
	public String getCalidadParticipacion() {
		return calidadParticipacion;
	}

	/**
	 * Sets the calidad participacion.
	 *
	 * @param calidadParticipacion
	 *            the new calidad participacion
	 */
	public void setCalidadParticipacion(String calidadParticipacion) {
		this.calidadParticipacion = calidadParticipacion;
	}

	/**
	 * Gets the orden participacion.
	 *
	 * @return the orden participacion
	 */
	public String getOrdenParticipacion() {
		return ordenParticipacion;
	}

	/**
	 * Sets the orden participacion.
	 *
	 * @param ordenParticipacion
	 *            the new orden participacion
	 */
	public void setOrdenParticipacion(String ordenParticipacion) {
		this.ordenParticipacion = ordenParticipacion;
	}

	/**
	 * Gets the estado relacion.
	 *
	 * @return the estado relacion
	 */
	public String getEstadoRelacion() {
		return estadoRelacion;
	}

	/**
	 * Sets the estado relacion.
	 *
	 * @param estadoRelacion
	 *            the new estado relacion
	 */
	public void setEstadoRelacion(String estadoRelacion) {
		this.estadoRelacion = estadoRelacion;
	}

	/**
	 * Gets the fecha baja rel.
	 *
	 * @return the fecha baja rel
	 */
	public String getFechaBajaRel() {
		return fechaBajaRel;
	}

	/**
	 * Sets the fecha baja rel.
	 *
	 * @param fechaBajaRel
	 *            the new fecha baja rel
	 */
	public void setFechaBajaRel(String fechaBajaRel) {
		this.fechaBajaRel = fechaBajaRel;
	}

	/**
	 * Gets the motivo baja.
	 *
	 * @return the motivo baja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Sets the motivo baja.
	 *
	 * @param motivoBaja
	 *            the new motivo baja
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
	 * Gets the marca empleado.
	 *
	 * @return the marca empleado
	 */
	public String getMarcaEmpleado() {
		return marcaEmpleado;
	}

	/**
	 * Sets the marca empleado.
	 *
	 * @param marcaEmpleado
	 *            the new marca empleado
	 */
	public void setMarcaEmpleado(String marcaEmpleado) {
		this.marcaEmpleado = marcaEmpleado;
	}

	/**
	 * Gets the tipo inscripcion.
	 *
	 * @return the tipo inscripcion
	 */
	public String getTipoInscripcion() {
		return tipoInscripcion;
	}

	/**
	 * Sets the tipo inscripcion.
	 *
	 * @param tipoInscripcion
	 *            the new tipo inscripcion
	 */
	public void setTipoInscripcion(String tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	/**
	 * Gets the numero inscripcion.
	 *
	 * @return the numero inscripcion
	 */
	public String getNumeroInscripcion() {
		return numeroInscripcion;
	}

	/**
	 * Sets the numero inscripcion.
	 *
	 * @param numeroInscripcion
	 *            the new numero inscripcion
	 */
	public void setNumeroInscripcion(String numeroInscripcion) {
		this.numeroInscripcion = numeroInscripcion;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipo persona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Sets the tipo persona.
	 *
	 * @param tipoPersona
	 *            the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
	 * Gets the res interes.
	 *
	 * @return the res interes
	 */
	public String getResInteres() {
		return resInteres;
	}

	/**
	 * Sets the res interes.
	 *
	 * @param resInteres
	 *            the new res interes
	 */
	public void setResInteres(String resInteres) {
		this.resInteres = resInteres;
	}

	/**
	 * Gets the marca paquete.
	 *
	 * @return the marca paquete
	 */
	public String getMarcaPaquete() {
		return marcaPaquete;
	}

	/**
	 * Sets the marca paquete.
	 *
	 * @param marcaPaquete
	 *            the new marca paquete
	 */
	public void setMarcaPaquete(String marcaPaquete) {
		this.marcaPaquete = marcaPaquete;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp
	 *            the new time stamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the codigo condiciones.
	 *
	 * @return the codigo condiciones
	 */
	public String getCodigoCondiciones() {
		return codigoCondiciones;
	}

	/**
	 * Sets the codigo condiciones.
	 *
	 * @param codigoCondiciones
	 *            the new codigo condiciones
	 */
	public void setCodigoCondiciones(String codigoCondiciones) {
		this.codigoCondiciones = codigoCondiciones;
	}

	/**
	 * Gets the forma operar.
	 *
	 * @return the forma operar
	 */
	public String getFormaOperar() {
		return formaOperar;
	}

	/**
	 * Sets the forma operar.
	 *
	 * @param formaOperar
	 *            the new forma operar
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
	 *            the new categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the grupo empresa.
	 *
	 * @return the grupo empresa
	 */
	public String getGrupoEmpresa() {
		return grupoEmpresa;
	}

	/**
	 * Sets the grupo empresa.
	 *
	 * @param grupoEmpresa
	 *            the new grupo empresa
	 */
	public void setGrupoEmpresa(String grupoEmpresa) {
		this.grupoEmpresa = grupoEmpresa;
	}

	/**
	 * Gets the marca verificacion.
	 *
	 * @return the marca verificacion
	 */
	public String getMarcaVerificacion() {
		return marcaVerificacion;
	}

	/**
	 * Sets the marca verificacion.
	 *
	 * @param marcaVerificacion
	 *            the new marca verificacion
	 */
	public void setMarcaVerificacion(String marcaVerificacion) {
		this.marcaVerificacion = marcaVerificacion;
	}

	/**
	 * Gets the adherido rio.
	 *
	 * @return the adherido rio
	 */
	public String getAdheridoRio() {
		return adheridoRio;
	}

	/**
	 * Sets the adherido rio.
	 *
	 * @param adheridoRio
	 *            the new adherido rio
	 */
	public void setAdheridoRio(String adheridoRio) {
		this.adheridoRio = adheridoRio;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("nup", nup).append("calidadParticipacion", calidadParticipacion)
				.append("ordenParticipacion", ordenParticipacion).append("estadoRelacion", estadoRelacion)
				.append("fechaBajaRel", fechaBajaRel).append("motivoBaja", motivoBaja).append("apellido", apellido)
				.append("nombre", nombre).append("tipoDocumento", tipoDocumento)
				.append("numeroDocumento", numeroDocumento).append("marcaEmpleado", marcaEmpleado)
				.append("tipoInscripcion", tipoInscripcion).append("numeroInscripcion", numeroInscripcion)
				.append("tipoPersona", tipoPersona).append("fechaNacimiento", fechaNacimiento)
				.append("resInteres", resInteres).append("marcaPaquete", marcaPaquete).append("timeStamp", timeStamp)
				.append("codigoCondiciones", codigoCondiciones).append("formaOperar", formaOperar)
				.append("categoria", categoria).append("grupoEmpresa", grupoEmpresa)
				.append("marcaVerificacion", marcaVerificacion).append("adheridoRio", adheridoRio)
				.append("fechaAlta", fechaAlta).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nup).append(calidadParticipacion).append(ordenParticipacion)
				.append(estadoRelacion).append(fechaBajaRel).append(motivoBaja).append(apellido).append(nombre)
				.append(tipoDocumento).append(numeroDocumento).append(marcaEmpleado).append(tipoInscripcion)
				.append(numeroInscripcion).append(tipoPersona).append(fechaNacimiento).append(resInteres)
				.append(marcaPaquete).append(timeStamp).append(codigoCondiciones).append(formaOperar).append(categoria)
				.append(grupoEmpresa).append(marcaVerificacion).append(adheridoRio).append(fechaAlta).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		IntervinientesEntity other = (IntervinientesEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(nup, other.nup).append(calidadParticipacion, other.calidadParticipacion)
				.append(ordenParticipacion, other.ordenParticipacion).append(estadoRelacion, other.estadoRelacion)
				.append(fechaBajaRel, other.fechaBajaRel).append(motivoBaja, other.motivoBaja)
				.append(apellido, other.apellido).append(nombre, other.nombre)
				.append(tipoDocumento, other.tipoDocumento).append(numeroDocumento, other.numeroDocumento)
				.append(marcaEmpleado, other.marcaEmpleado).append(tipoInscripcion, other.tipoInscripcion)
				.append(numeroInscripcion, other.numeroInscripcion).append(tipoPersona, other.tipoPersona)
				.append(fechaNacimiento, other.fechaNacimiento).append(resInteres, other.resInteres)
				.append(marcaPaquete, other.marcaPaquete).append(timeStamp, other.timeStamp)
				.append(codigoCondiciones, other.codigoCondiciones).append(formaOperar, other.formaOperar)
				.append(categoria, other.categoria).append(grupoEmpresa, other.grupoEmpresa)
				.append(marcaVerificacion, other.marcaVerificacion).append(adheridoRio, other.adheridoRio)
				.append(fechaAlta, other.fechaAlta).isEquals();
	}

}
