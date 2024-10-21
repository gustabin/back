/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosParticipantesEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleCuotasPrestamosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleImpuestoMonedaExtranjeraEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleMensualImpuestosEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetallePlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DetalleTarjetaCreditoEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.ResumenCuentaInversionesEntity;

/**
 * The Interface TenenciasDetalleDAO.
 */
public interface TenenciasDetalleDAO {

	/**
	 * Obtener detalle plazo fijo.
	 *
	 * @param nup
	 *            the nup
	 * @param anioDesde
	 *            the anio desde
	 * @param anioHasta
	 *            the anio hasta
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetallePlazoFijoEntity> obtenerDetallePlazoFijo(String nup, String anioDesde, String anioHasta)
			throws DAOException;

	/**
	 * Obtener resumen cuenta inversiones.
	 *
	 * @param nup
	 *            the nup
	 * @param anio
	 *            the anio
	 * @param espeTipo
	 *            the espe tipo
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ResumenCuentaInversionesEntity> obtenerResumenCuentaInversiones(String nup, String anio, String espeTipo)
			throws DAOException;

	/**
	 * Obtener detalle cuotas prestamos.
	 *
	 * @param nup
	 *            the nup
	 * @param anio
	 *            the anio
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleCuotasPrestamosEntity> obtenerDetalleCuotasPrestamos(String nup, String anio) throws DAOException;

	/**
	 * Obtener detalle mensual impuestos.
	 *
	 * @param nup
	 *            the nup
	 * @param anio
	 *            the anio
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleMensualImpuestosEntity> obtenerDetalleMensualImpuestos(String nup, String anio) throws DAOException;

	/**
	 * Obtener detalle impuesto moneda extranjera.
	 *
	 * @param nup
	 *            the nup
	 * @param anio
	 *            the anio
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleImpuestoMonedaExtranjeraEntity> obtenerDetalleImpuestoMonedaExtranjera(String nup, String anio)
			throws DAOException;

	/**
	 * Obtener detalle tarjeta credito.
	 *
	 * @param nup
	 *            the nup
	 * @param anio
	 *            the anio
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DetalleTarjetaCreditoEntity> obtenerDetalleTarjetaCredito(String nup, String anio) throws DAOException;
	
	 /**
     * Se invoca al servicio ObtenerParticipantes <br>
     * 
     *
     * @param nup
     *            the nup
     * @param anio
     *            the anio desde
     * @return the List<participantes> out entity
     * @throws DAOException
     *             the DAO exception
     */
    List<DatosParticipantesEntity> consultarFirmantesPL(String anio, String nup) throws DAOException ;
    
    /**
     * Consulta si el nup se encuentra habilitado a realizar operaciones de compra y venta de USD
     * @param nup
     * @return
     * @throws DAOException 
     */
    DatosRespuestaHabilitaCompraVentaUSDEntity consultarHabilitacionCompraVtaUSD(String nup) throws DAOException;

}
