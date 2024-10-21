/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaInhabilitadosInEntity.
 *
 * @author alejandro_leal
 */
public class ConsultaInhabilitadosInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** Apellido *. */
	private String apellido;

	/** Nombre *. */
	private String nombre;

	/** Sexo *. */
	private String sexo;

	/** fechaNac *. */
	private String fechaNac;

	/** tipoDoc *. */
	private String tipoDoc;

	/** nroDoc *. */
	private String nroDoc;

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fechaNac
	 *            the new fecha nac
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * Gets the tipo doc.
	 *
	 * @return the tipo doc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * Sets the tipo doc.
	 *
	 * @param tipoDoc
	 *            the new tipo doc
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * Gets the nro doc.
	 *
	 * @return the nro doc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * Sets the nro doc.
	 *
	 * @param nroDoc
	 *            the new nro doc
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
}
