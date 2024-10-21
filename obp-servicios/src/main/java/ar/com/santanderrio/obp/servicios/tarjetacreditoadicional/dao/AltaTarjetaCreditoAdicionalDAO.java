/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.AltaTarjetaCreditoAdicionalOutResponseEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;

/**
 * The Interface AltaTarjetaCreditoAdicionalDAO.
 */
public interface AltaTarjetaCreditoAdicionalDAO {

	/**
	 * Alta tarjeta credito adicional.
	 *
	 * @param altaTarjetaCreditoAdicionalInEntity
	 *            the alta tarjeta credito adicional in entity
	 * @return the alta tarjeta credito adicional out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	AltaTarjetaCreditoAdicionalOutResponseEntity altaTarjetaCreditoAdicional(
			AltaTarjetaCreditoAdicionalInEntity altaTarjetaCreditoAdicionalInEntity) throws DAOException;

	/**
	 * Generar comprobante.
	 *
	 * @param view
	 *            the view
	 * @return the reporte
	 */
	Reporte generarComprobante(ComprobanteAltaTarjCredAdicionalView view);

}
