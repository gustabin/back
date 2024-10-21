/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;

/**
 * The Class AdjuntarArchivosInEntity.
 */
public class StopDebitPrestamosInEntity {

	/** The cod asociacion. */
	private Integer codAsociacion;
	
	/** The tipo persona. */
	private String tipoPersona;
	
	/** The nup. */
	private Integer nup;
	
	/** The cod sector. */
	private String codSector;
	
	/** The cod user. */
	private String codUser;
	
	/** The medio ingreso. */
	private Integer medioIngreso;
	
	/** The comentario. */
	private String comentario;
	
	/** The codigo. */
	private String codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The valor. */
	private List<String> valor;
	
	/** The info requerida. */
	private ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida;

	/**
	 * Gets the cod asociacion.
	 *
	 * @return the cod asociacion
	 */
	public Integer getCodAsociacion() {
		return codAsociacion;
	}

	/**
	 * Sets the cod asociacion.
	 *
	 * @param codAsociacion the new cod asociacion
	 */
	public void setCodAsociacion(Integer codAsociacion) {
		this.codAsociacion = codAsociacion;
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
	 * @param tipoPersona the new tipo persona
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public Integer getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup the new nup
	 */
	public void setNup(Integer nup) {
		this.nup = nup;
	}

	/**
	 * Gets the cod sector.
	 *
	 * @return the cod sector
	 */
	public String getCodSector() {
		return codSector;
	}

	/**
	 * Sets the cod sector.
	 *
	 * @param codSector the new cod sector
	 */
	public void setCodSector(String codSector) {
		this.codSector = codSector;
	}

	/**
	 * Gets the cod user.
	 *
	 * @return the cod user
	 */
	public String getCodUser() {
		return codUser;
	}

	/**
	 * Sets the cod user.
	 *
	 * @param codUser the new cod user
	 */
	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}

	/**
	 * Gets the medio ingreso.
	 *
	 * @return the medio ingreso
	 */
	public Integer getMedioIngreso() {
		return medioIngreso;
	}

	/**
	 * Sets the medio ingreso.
	 *
	 * @param medioIngreso the new medio ingreso
	 */
	public void setMedioIngreso(Integer medioIngreso) {
		this.medioIngreso = medioIngreso;
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
	 * @param comentario the new comentario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public List<String> getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(List<String> valor) {
		this.valor = valor;
	}

	/**
	 * Gets the info requerida.
	 *
	 * @return the info requerida
	 */
	public ArrayOf158770343432493182NillableInfoRequeridaWS getInfoRequerida() {
		return infoRequerida;
	}

	/**
	 * Sets the info requerida.
	 *
	 * @param infoRequerida the new info requerida
	 */
	public void setInfoRequerida(ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida) {
		this.infoRequerida = infoRequerida;
	};
	
}
