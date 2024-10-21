/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ComprobanteOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;

/**
 * interface ExtraccionComprasExteriorDAO.
 * 
 */
public interface ExtraccionYComprasExteriorDAO {

	/**
	 * Consulta de cuentas por tarjeta de debito.
	 *
	 * @param cuentasExteriorInEntity
	 *            the cuentas exterior in entity
	 * @return ConsultaCuentasOperaExteriorOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaCuentasOperaExteriorOutEntity consultarCuentasOperaExterior(
			ConsultaCuentasOperaExteriorInEntity cuentasExteriorInEntity) throws DAOException;

	/**
	 * Consulta de tarjetas de debito.
	 *
	 * @param tarjetasExteriorInEntity
	 *            the tarjetas exterior in entity
	 * @return ConsultaTarjetasOperaExteriorOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaTarjetasOperaExteriorOutEntity consultarTarjetasOperaExterior(
			ConsultaTarjetasOperaExteriorInEntity tarjetasExteriorInEntity) throws DAOException;

	/**
	 * Moficicaion de tarjeta para operar exterior.
	 *
	 * @param cambioTarjeta
	 *            the cambio tarjeta
	 * @return CambioTarjetaOperaExteriorOutEntity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CambioTarjetaOperaExteriorOutEntity cambioTarjetaOperaExterior(CambioTarjetaOperaExteriorInEntity cambioTarjeta)
			throws DAOException;

	/**
	 * Descarga de comprobante de modificacion para operar en el exterior.
	 *
	 * @param datos
	 *            the datos
	 * @return the reporte
	 */
	Reporte descargarComprobante(ComprobanteOperaExteriorInEntity datos);

}
