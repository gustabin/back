/**
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ConfirmarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EchoViajeResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EliminarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesResponse;

/**
 * The Interface MarcaViajeroDAO.
 *
 * @author sergio.e.goldentair
 */
public interface MarcaViajeroDAO {

    /**
     * Probar la api.
     *
     * @param message
     *            the message
     * @return the echo viaje response
     * @throws DAOException
     *             the DAO exception
     */
    EchoViajeResponse echoViaje(String message) throws DAOException;

    /**
     * Obtener viajes de prisma WS.
     *
     * @param request
     *            the request
     * @return the obtener viajes response
     * @throws DAOException
     *             the DAO exception
     */
    ObtenerViajesResponse obtenerViajesDePrismaWS(ObtenerViajesRequest request)
            throws DAOException;

    /**
     * Obtener tarjetas del socio.
     *
     * @param request
     *            the request
     * @return the obtener tarjetas del socio response
     * @throws DAOException
     *             the DAO exception
     */
    ObtenerTarjetasDelSocioResponse obtenerTarjetasDelSocio(
            ObtenerTarjetasDelSocioRequest request) throws DAOException;

    /**
     * Obtener paises.
     *
     * @return the obtener paises response
     * @throws DAOException
     *             the DAO exception
     */
    ObtenerPaisesResponse obtenerPaises() throws DAOException;

    /**
     * Confirmar viaje WS.
     *
     * @param confirmarViajeRequest
     *            the confirmar viaje request
     * @return the confirmar viaje response
     * @throws DAOException
     *             the DAO exception
     */
    void confirmarViajeWS(ConfirmarViajeRequest confirmarViajeRequest)
            throws DAOException;

    /**
     * Limpiar cache paises marcacion.
     */
    void limpiarCachePaisesMarcacion();

    /**
	 * Eliminar viaje.
	 *
	 * @param viaje
	 *            the viaje
	 * @return the eliminar viaje response
	 * @throws DAOException
	 *             the DAO exception
	 */
    void eliminarViaje(EliminarViajeRequest viaje) throws DAOException;

}
