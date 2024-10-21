/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class PreguntasSeguridad.
 */
public class PreguntasSeguridad extends MetodoAutenticacion {

	/** The cantidad preguntas. */
	private Integer cantidadPreguntas;

	/** The preguntas. */
	private List<PreguntaAutenticacion> preguntas;

	/** The indice pregunta. */
	private Integer indicePregunta;

	/** The id sesion. */
	private String idSesion;

	/** The cantidad ocurrencias. */
	private Long cantidadOcurrencias;

	/** The ciclo. */
	private Integer ciclo;

	/** The productos. */
	private List<ProductoEntity> productos;

	/** The dni. */
	private String dni;

	/** The fecha nacimiento. */
	private Date fechaNacimiento;


	/**
	 * Instantiates a new preguntas seguridad.
	 */
	public PreguntasSeguridad() {
		super();
		preguntas = new ArrayList<PreguntaAutenticacion>();
	}

	
	




	/**
	 * Gets the cantidad preguntas.
	 *
	 * @return the cantidadPreguntas
	 */
	public Integer getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	/**
	 * Sets the cantidad preguntas.
	 *
	 * @param cantidadPreguntas
	 *            the cantidadPreguntas to set
	 */
	public void setCantidadPreguntas(Integer cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

	/**
	 * Gets the preguntas.
	 *
	 * @return the preguntas
	 */
	public List<PreguntaAutenticacion> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 *
	 * @param preguntas
	 *            the preguntas to set
	 */
	public void setPreguntas(List<PreguntaAutenticacion> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Gets the indice pregunta.
	 *
	 * @return the indicePregunta
	 */
	public Integer getIndicePregunta() {
		return indicePregunta;
	}

	/**
	 * Sets the indice pregunta.
	 *
	 * @param indicePregunta
	 *            the indicePregunta to set
	 */
	public void setIndicePregunta(Integer indicePregunta) {
		this.indicePregunta = indicePregunta;
	}

	/**
	 * Gets the id sesion.
	 *
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * Sets the id sesion.
	 *
	 * @param idSesion
	 *            the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidadOcurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the cantidadOcurrencias to set
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public Integer getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo
	 *            the ciclo to set
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the productos.
	 *
	 * @return the productos
	 */
	public List<ProductoEntity> getProductos() {
		return productos;
	}

	/**
	 * Sets the productos.
	 *
	 * @param productos
	 *            the productos to set
	 */
	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
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

}
