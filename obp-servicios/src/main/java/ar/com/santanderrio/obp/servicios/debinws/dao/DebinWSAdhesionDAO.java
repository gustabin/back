package ar.com.santanderrio.obp.servicios.debinws.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.debinws.entities.ComprobanteAdhesionEntity;

/**
 * The Interface DebinWSAdhesionDAO.
 */
public interface DebinWSAdhesionDAO {

    /**
     * Consulta de cuentas adheridas a debin vendedor.
     *
     * @param request the request
     * @return the response vendedor
     * @throws DAOException the DAO exception
     */
    public abstract ResponseVendedor consultaVendedor(RequestConsulta request) throws DAOException;

    /**
     * Adhesion de cuentas.
     *
     * @param request the request
     * @return the response
     * @throws DAOException the DAO exception
     */
    public abstract Response adhesionVendedor(RequestAdhesion request) throws DAOException;

    /**
     * Baja de cuentas.
     *
     * @param request the request
     * @return the response
     * @throws DAOException the DAO exception
     */
    public abstract Response bajaCuentaVendedor(RequestAdhesion request) throws DAOException;

	/**
	 * Generar comprobante.
	 *
	 * @param comprobanteAdhesionEntity the comprobante adhesion entity
	 * @return the reporte
	 */
	Reporte generarComprobante(ComprobanteAdhesionEntity comprobanteAdhesionEntity);
}
