package ar.com.santanderrio.obp.servicios.nuevarecarga.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;

// TODO: Auto-generated Javadoc
/**
 * The Interface AgendaCelularBO.
 */
public interface AgendaCelularBO {

	/**
	 * Agregar celular.
	 *
	 * @param cliente the cliente
	 * @param nro the nro
	 * @param descripcion the descripcion
	 * @param compania the compania
	 * @return the respuesta
	 */
	public Respuesta<Void> agregarCelular(Cliente cliente, String nro, String descripcion, String compania);
	
	/**
	 * Eliminar celular.
	 *
	 * @param cliente the cliente
	 * @param nro the nro
	 * @return the respuesta
	 */
	public Respuesta<Void> eliminarCelular(Cliente cliente, String nro);
	
	/**
	 * Consultar celulares.
	 *
	 * @param cliente the cliente
	 * @return the respuesta
	 */
	public Respuesta<List<CelularView>> consultarCelulares(Cliente cliente);
	
	/**
	 * Obtener alias.
	 *
	 * @param cliente the cliente
	 * @param nro the nro
	 * @return the respuesta
	 */
	public Respuesta<String> obtenerAlias(Cliente cliente, String nro);
	
	/**
	 * Actualizar alias celular.
	 *
	 * @param cliente the cliente
	 * @param nro the nro
	 * @param descripcion the descripcion
	 * @return the respuesta
	 */
	public Respuesta<Void> actualizarAliasCelular(Cliente cliente, String nro, String descripcion);
	
	/**
	 * Existe celular.
	 *
	 * @param cliente the cliente
	 * @param nro the nro
	 * @return the respuesta
	 */
	public Respuesta<Void> existeCelular(Cliente cliente, String numero);

}
