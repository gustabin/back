/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaResumenTokenEntity.
 */
@XmlRootElement(name="Pagos")
public class ConsultaResumenTokenEntity {

	/** The usuario. */
	private UsuarioResumenTokenEntity usuario;

	/**
	 * Instantiates a new consulta resumen token entity.
	 */
	public ConsultaResumenTokenEntity() {
		super();
	}
	
	/**
	 * Instantiates a new consulta resumen token entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoDoc
	 *            the tipo doc
	 */
	public ConsultaResumenTokenEntity(Cliente cliente,String tipoDoc) {
		usuario = new UsuarioResumenTokenEntity(cliente,tipoDoc);
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public UsuarioResumenTokenEntity getUsuario() {
		return usuario;
	}
	
	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	@XmlElement(name="Usuario")
	public void setUsuario(UsuarioResumenTokenEntity usuario) {
		this.usuario = usuario;
	}
}
