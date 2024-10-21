/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadInEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.AltaSolicitudCambioAfinidadOutEntity;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;

/**
 * The Interface CambioGrupoAfinidadDAO.
 */
public interface CambioGrupoAfinidadDAO {

//	Respuesta<GrupoAfinidadClienteDTO> comprobarGrupoAfinidadCliente(Cliente cliente) throws DAOException;
	
	/**
 * Alta solicitud adhesion.
 *
 * @param inEntity
 *            the in entity
 * @return the alta solicitud cambio afinidad out entity
 * @throws DAOException
 *             the DAO exception
 */
AltaSolicitudCambioAfinidadOutEntity altaSolicitudAdhesion(AltaSolicitudCambioAfinidadInEntity inEntity) throws DAOException;

	/**
	 * Generar comprobante cambio grupo afinidad.
	 *
	 * @param datosComprobante
	 *            the datos comprobante
	 * @return the reporte
	 */
	Reporte generarComprobanteCambioGrupoAfinidad(ComprobanteSolicitudCambioAfinidadView datosComprobante);
}
