/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;

/**
 * The Interface SolicitudProductoDAO.
 */
public interface SolicitudProductoDAO {

	/**
	 * Alta gestion.
	 *
	 * @param codAsociacion
	 *            the cod asociacion
	 * @param tipoPersona
	 *            the tipo persona
	 * @param nup
	 *            the nup
	 * @param codSector
	 *            the cod sector
	 * @param codUser
	 *            the cod user
	 * @param medioIngreso
	 *            the medio ingreso
	 * @param comentario
	 *            the comentario
	 * @param infoRequerida
	 *            the info requerida
	 * @return the resultado alta WS
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResultadoAltaWS altaGestion(Integer codAsociacion, String tipoPersona, Integer nup, String codSector,
			String codUser, Integer medioIngreso, String comentario,
			ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida) throws DAOException;

	/**
	 * Generar comprobante baja.
	 *
	 * @param tituloTipoBaja
	 *            the titulo tipo baja
	 * @param descripcionProducto
	 *            the descripcion producto
	 * @param fechaDeBaja
	 *            the fecha de baja
	 * @param fechaOperacion
	 *            the fecha operacion
	 * @param nroComprobante
	 *            the nro comprobante
	 * @param isPaquete
	 *            the isPaquete
	 * @param numero
	 *            the numero
	 * @param mantieneCajaAhorro
	 *            the mantieneCaja
	 * @return the reporte
	 */
	Reporte generarComprobanteBaja(ComprobanteBajaProductoView comprobanteBajaProducto);
}
