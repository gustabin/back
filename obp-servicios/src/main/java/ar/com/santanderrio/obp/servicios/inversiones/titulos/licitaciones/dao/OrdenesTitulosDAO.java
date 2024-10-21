/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaComisionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarEspeciesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FinalizarReversaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RespuestaDatosOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaDeTenenciaResponse;

/**
 * Dao para consulta de ordenes (titulos).
 *
 * @author juan.pablo.picate
 */
public interface OrdenesTitulosDAO {

	/**
	 * Devuelve el estado de las Cuentas titulos.
	 *
	 * @param request
	 *            the request
	 * @return the consulta cuenta titulos response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaCuentaTitulosResponse consultaCuentaTitulos(ConsultaCuentaTitulosRequestEntity request) throws DAOException;

	/**
	 * Consulta especies.
	 *
	 * @param request
	 *            the request
	 * @return the consultar especies response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarEspeciesResponse consultaEspecies(ConsultarEspeciesRequest request) throws DAOException;

	/**
	 * Consulta de tenencia.
	 *
	 * @param request
	 *            the request
	 * @return the consulta de tenencia response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaDeTenenciaResponse consultaDeTenencia(ConsultaDeTenenciaRequestEntity request) throws DAOException;

	/**
	 * Consulta apertura especie.
	 *
	 * @param request
	 *            the request
	 * @return the consulta apertura especie response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaAperturaEspecieResponse consultaAperturaEspecie(ConsultaAperturaEspecieRequestEntity request)
			throws DAOException;

	/**
	 * Consultar el poder de compra.
	 *
	 * @param request
	 *            the request
	 * @return the consulta suscripcion saldo PDC response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaSuscripcionSaldoPDCResponse consultaSuscripcionSaldoPDC(ConsultaSuscripcionSaldoPDCRequest request)
			throws DAOException;

	/**
	 * Servicio para simular y confirmar la compra/venta de un titulo.
	 *
	 * @param request
	 *            the request
	 * @return the compra vta titulos response
	 * @throws DAOException
	 *             the DAO exception
	 */
	CompraVtaTitulosResponse compraVtaTitulos(ComptaVtaTitulosRequestEntity request) throws DAOException;

	/**
	 * Consultar operaciones text.
	 *
	 * @param requestConsultaOperacionesEntity
	 *            the request consulta operaciones entity
	 * @return the consultar operaciones text response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarOperacionesTextResponse consultarOperacionesText(
			RequestConsultarOperacionesTextEntity requestConsultaOperacionesEntity) throws DAOException;

	/**
	 * Consultar ordenes.
	 *
	 * @param request
	 *            the request
	 * @return the datos consulta ordenes
	 * @throws DAOException
	 *             the DAO exception
	 */
	DatosConsultaOrdenes consultarOrdenes(ConsultaOrdenesRequestEntity request) throws DAOException;

	/**
	 * Finalizar reversar ordenes.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta datos orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	RespuestaDatosOrdenResponse finalizarReversarOrdenes(FinalizarReversaOrdenesRequestEntity request)
			throws DAOException;

	/**
	 * Consulta indices.
	 *
	 * @param request
	 *            the request
	 * @return the consulta indices response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaIndicesResponse consultaIndices(ConsultaIndicesRequest request) throws DAOException;

	/**
	 * Consulta filtro info mercado.
	 *
	 * @param request
	 *            the request
	 * @return the consulta filtro informacion mercado response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaFiltroInformacionMercadoResponse consultaFiltroInfoMercado(
			ConsultaFiltroInformacionMercadoRequestEntity request) throws DAOException;

	/**
	 * Consulta informacion mercado.
	 *
	 * @param request
	 *            the request
	 * @return the consulta informacion mercado response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultaInformacionMercadoResponse consultaInformacionMercado(ConsultaInformacionMercadoRequest request)
			throws DAOException;
	
	/**
	 * Variacion infomercado.
	 *
	 * @param request
	 *            the request
	 * @return the variacion info mercado response
	 * @throws DAOException
	 *             the DAO exception
	 */
	VariacionInfoMercadoResponse variacionInfomercado(VariacionInfoMercadoRequest request) throws DAOException;

	ConsultaComisionResponse consultaComision(ConsultaComisionRequestEntity requestComision) throws DAOException;


}