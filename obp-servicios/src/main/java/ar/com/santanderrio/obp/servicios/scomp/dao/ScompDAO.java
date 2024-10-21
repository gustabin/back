/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.dao;

import java.util.concurrent.Future;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroDetalleComprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroListaComprobantesOrExt;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Filtros;

/**
 * The Interface ScompDAO.
 *
 * @author sergio.e.goldentair
 */
public interface ScompDAO {

    /**
     * Alta comprobante Asincronico.
     *
     * @param filtros
     *            the filtros
     * @return the future
     * @throws DAOException
     *             the DAO exception
     */
    Future<ComprobanteResponse> listarComprobantesAsync(Filtros filtros) throws DAOException;

    /**
     * Alta comprobante.
     *
     * @param altaComprobanteRequest
     *            the alta comprobante request
     * @return the comprobante response
     * @throws DAOException
     *             the DAO exception
     */
    ComprobanteResponse altaComprobante(AltaComprobanteRequest altaComprobanteRequest) throws DAOException;

    /**
     * Listar comprobantes.
     *
     * @param filtros
     *            el objeto que tiene los parametros variables de la llamada
     * @param version
     *            the version
     * @param subcanal
     *            the subcanal
     * @return the comprobante response
     * @throws DAOException
     *             the DAO exception
     */
    ComprobanteResponse listarComprobantes(Filtros filtros, String version, String subcanal) throws DAOException;

    /**
     * Obtener comprobante erroneo async.
     *
     * @return the future
     */
    Future<ComprobanteResponse> obtenerComprobanteErroneoAsync();

    /**
	 * Lista comprobantes or ext.
	 *
	 * @param filtro
	 *            the filtro
	 * @return the comprobante response
	 * @throws DAOException
	 *             the DAO exception
	 */
    ComprobanteResponse listaComprobantesOrExt(FiltroListaComprobantesOrExt filtro) throws DAOException;

    /**
	 * Detalle comprobante.
	 *
	 * @param filtro
	 *            the filtro
	 * @return the comprobante response
	 * @throws DAOException
	 *             the DAO exception
	 */

    ComprobanteResponse detalleComprobante(FiltroDetalleComprobante filtro) throws DAOException;

}
