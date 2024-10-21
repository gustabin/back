/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.SimulacionClienteCompraBO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class SimulacionCompraVentaDolarBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionClienteCompraBOImpl extends CompraVentaDolaresBOImpl implements SimulacionClienteCompraBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionClienteCompraBOImpl.class);

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant QUINCE_CEROS. */
	private static final String QUINCE_CEROS = "000000000000000";

	/** The Constant ESPACIO. */
	private static final String ESPACIO = " ";

	/** The Constant VEINTE_ESPACIOS. */
	private static final String VEINTE_ESPACIOS = "                    ";

	/** The Constant TRES_ESPACIOS. */
	private static final String TRES_ESPACIOS = "   ";

	/** The Constant OCHO_ESPACIOS. */
	private static final String OCHO_ESPACIOS = "        ";

	/** The simulacionClienteCompraDAO. */
	@Autowired
	private SimulacionClienteCompraDAO simulacionClienteCompraDAO;
	
	/** The EstadisticaManager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Obtiene la respuesta con los datos de la simuacion cuando Cliente Compra
	 * Dolares.
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDTO> obtenerSimulacionClienteCompra(ParametrosSimulacion parametrosSimulacion)
			throws BusinessException {
		DetalleDocumentoDTO detalleDocumentoDTO;
		try {
			detalleDocumentoDTO = obtenerDocumentoValido(parametrosSimulacion.getCliente());
			if (!detalleDocumentoDTO.getTieneError()) {
				parametrosSimulacion.setNupTipo(detalleDocumentoDTO.getTipoDocumento());
				parametrosSimulacion.setNupNumDoc(detalleDocumentoDTO.getNroDocumento());

				SimulacionDatosEntrada datosEntrada = crearDatosEntradaClienteCompra(parametrosSimulacion);
				return obtenerRespuestaClienteCompra(parametrosSimulacion.getCliente(), datosEntrada,
						parametrosSimulacion);
			}
			return casuistica.crearRespuestaError(detalleDocumentoDTO.getError());
		} catch (DAOException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErrorServicio();
		} catch (Exception e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		}
	}

	/**
	 * carga de datos para la el llamado del servicio de Simulacion para la
	 * Compra de dolares SIMCPVTACN110.
	 *
	 * @param parametros
	 *            the parametros simulacion
	 * @return the simulacion compra venta dolar dato entrada
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private SimulacionDatosEntrada crearDatosEntradaClienteCompra(ParametrosSimulacion parametros)
			throws CompraVentaDolaresException {
		SimulacionDatosEntrada simulacionCompraVentaDolarData = new SimulacionDatosEntrada();
		simulacionCompraVentaDolarData
				.setAplicacionPesos(getCompraVentaDolaresUtil().obtenerAplicacionPesos(parametros.getCuentaOrigen()));
		simulacionCompraVentaDolarData.setSucursalCtaPesos(
				getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaOrigen()));
		simulacionCompraVentaDolarData.setNumeroCtaPesos(
				getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaOrigen()));
		simulacionCompraVentaDolarData.setAplicacionDolar(getCompraVentaDolaresUtil()
				.obtenerAplicacionDolar(parametros.getCuentaDestino(), CompraVentaStringUtil.OPERACION_COMPRA));
		simulacionCompraVentaDolarData.setSucursalCtaDolar(
				getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaDestino()));
		simulacionCompraVentaDolarData.setNuemroCtaDolar(
				getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaDestino()));
		simulacionCompraVentaDolarData.setIndTuAtesora(getCompraVentaDolaresUtil().obtenerIndTuAtesora());
		simulacionCompraVentaDolarData.setFechaValor(getCompraVentaDolaresUtil().obtenerFechaValor());
		simulacionCompraVentaDolarData.setConcepDebito(CUATRO_ESPACIOS);
		simulacionCompraVentaDolarData.setConcepCred(CUATRO_ESPACIOS);
		simulacionCompraVentaDolarData.setCodigoDebi(getCompraVentaDolaresUtil().obtenerCodigoDebi());
		simulacionCompraVentaDolarData.setCodigoCre(getCompraVentaDolaresUtil().obtenerCodigoCre());

		simulacionCompraVentaDolarData.setImporteDeb(getCompraVentaDolaresUtil()
				.obtenerImporteDebito(parametros.getImporte(), parametros.getCotizacion(), parametros.getIsDolar()));

		simulacionCompraVentaDolarData.setImporteCred(QUINCE_CEROS);
		simulacionCompraVentaDolarData
				.setImporteCoti(getCompraVentaDolaresUtil().obtenerImporteCoti(parametros.getCotizacion()));
		simulacionCompraVentaDolarData.setIndicCompraVta(ESPACIO);
		simulacionCompraVentaDolarData.setAutorizaAfip(VEINTE_ESPACIOS);
		simulacionCompraVentaDolarData.setTipoCambio(TRES_ESPACIOS);
		simulacionCompraVentaDolarData.setNumBoleCvta(OCHO_ESPACIOS);
		simulacionCompraVentaDolarData.setFecIngrPais(CompraVentaStringUtil.OCHO_CEROS);
		simulacionCompraVentaDolarData
				.setNomApell(getCompraVentaDolaresUtil().obtenerNomApell(parametros.getCliente()));
		simulacionCompraVentaDolarData.setMarcaEmpleado(ESPACIO);
		simulacionCompraVentaDolarData.setPaisEmisDoc(CompraVentaStringUtil.TRES_CEROS);
		simulacionCompraVentaDolarData.setPaisNac(CompraVentaStringUtil.TRES_CEROS);
		simulacionCompraVentaDolarData.setCodigoConcepto(getCompraVentaDolaresUtil().obtenerCodigoConcepto());
		simulacionCompraVentaDolarData.setTpoDocBcra(parametros.getNupTipo());
		simulacionCompraVentaDolarData.setNroDocBcra(parametros.getNupNumDoc());
		simulacionCompraVentaDolarData.setCondCliente(getCompraVentaDolaresUtil().obtenerCndCliente());
		simulacionCompraVentaDolarData.setTipRefAfip(TRES_ESPACIOS);
		return simulacionCompraVentaDolarData;
	}

	/**
	 * Genera una respuesta con los datos de la simulacion para la compra de
	 * dolares.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<SimulacionCompraVentaDTO> obtenerRespuestaClienteCompra(Cliente cliente,
			SimulacionDatosEntrada datosEntrada, ParametrosSimulacion parametrosSimulacion)
			throws BusinessException, CompraVentaDolaresException {
		try {
			LOGGER.debug("Llamando al DAO para la simulacion cliente compra: {}.", datosEntrada.toString());
			SimulacionClienteCompraEntity entity = simulacionClienteCompraDAO.obtenerSimulacionClienteCompra(cliente,
					datosEntrada, CuentasBancaPrivadaUtil.isBancaPrivada(parametrosSimulacion.getCuentaOrigen(), parametrosSimulacion.getCuentaDestino()));
			if (entity.getTieneError()) {
				ErrorCompraVentaEnum errorCompraVentaEnum = obtenerDatosDelError(entity.getCodError(), true);
				armarEstadisticaSimulacionCompra(errorCompraVentaEnum, parametrosSimulacion);
				return crearRespuestaError(errorCompraVentaEnum, CompraVentaStringUtil.OPERACION_COMPRA,
						parametrosSimulacion.getCuentaOrigen(), entity.getMensajeError());
			}
			SimulacionCompraVentaDTO simulacionCompraVentaDolar = crearDatosResponseClienteCompra(entity,
					parametrosSimulacion);
			return casuistica.crearRespuestaOk(SimulacionCompraVentaDTO.class, simulacionCompraVentaDolar);
		} catch (DAOException e) {
			LOGGER.error("ERROR_SERVICIO (1389)  {}. {}.", e);
			return casuistica.crearRespuestaErrorServicio();
		}
	}

	private void armarEstadisticaSimulacionCompra(ErrorCompraVentaEnum errorCompraVentaEnum,
			ParametrosSimulacion parametrosSimulacion) {
		String CodigoTipoCuenta = obtenerCodigoPorTipoCuenta(
				parametrosSimulacion.getCuentaOrigen().getTipoCuentaEnum());
		if (!ISBANStringUtils.isEmptyOrNull(CodigoTipoCuenta)) {
			estadisticaManager.add(CodigoTipoCuenta, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	private String obtenerCodigoPorTipoCuenta(TipoCuenta tipoCuentaEnum) {
		String tipoCuenta = null;

		if (tipoCuentaEnum.equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
			tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CA_PESOS;
		}
		if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
			tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CC_PESOS;
		}
		if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
			tipoCuenta = EstadisticasConstants.COMPRA_DOLARES_CU_PESOS;
		}
		return tipoCuenta;
	}

	/**
	 * Carga el objeto que tiene los datos de la respuesta para el paso dos.
	 *
	 * @param entity
	 *            the entity
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the simulacion compra venta DTO
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private SimulacionCompraVentaDTO crearDatosResponseClienteCompra(SimulacionClienteCompraEntity entity,
			ParametrosSimulacion parametrosSimulacion) throws BusinessException, CompraVentaDolaresException {
		SimulacionCompraVentaDTO simulacionCompraDTO = new SimulacionCompraVentaDTO();
		simulacionCompraDTO.setImporteCompraDolar(obtenerImporteFormateado(
				getCompraVentaDolaresUtil().convertirSimulacionImporteAComprar(entity.getImpabon())));
		simulacionCompraDTO.setNumeroCuentaOrigen(parametrosSimulacion.getNumeroCuentaOrigen());
		simulacionCompraDTO
				.setTipoCuentaOrigen(obtenerDescripcionTipoCuenta(parametrosSimulacion.getCuentaOrigen(), false));
		simulacionCompraDTO.setAliasOrigen(aliasGetter(parametrosSimulacion.getCuentaOrigen()));
		simulacionCompraDTO.setNumeroCuentaDestino(parametrosSimulacion.getNumeroCuentaDestino());
		simulacionCompraDTO
				.setTipoCuentaDestino(obtenerDescripcionTipoCuenta(parametrosSimulacion.getCuentaDestino(), true));
		simulacionCompraDTO.setAliasDestino(aliasGetter(parametrosSimulacion.getCuentaDestino()));
		simulacionCompraDTO.setCotizacion(
				obtenerImporteFormateado(getCompraVentaDolaresUtil().obtenerCotizacionAplicable(entity.getImpcoti())));
		simulacionCompraDTO.setImporteDebitarPesos(obtenerImporteFormateado(
				getCompraVentaDolaresUtil().convertirSimulacionImporteDebitar(entity.getTotCarg())));
		simulacionCompraDTO.setFecha(DateTime.now().toDate());
		simulacionCompraDTO.setLegales(obtenerLegal(CompraVentaStringUtil.COD_LEGAL_COMPRA_DOLARES));
		simulacionCompraDTO.setLegales2(obtenerLegal(CompraVentaStringUtil.COD_LEGAL_COMPRA_DOLARES_2));
		simulacionCompraDTO.setNupNumDoc(parametrosSimulacion.getNupNumDoc());
		simulacionCompraDTO.setNupTipo(parametrosSimulacion.getNupTipo());
		simulacionCompraDTO.setImporteImpuesto(obtenerImporteFormateado(
				getCompraVentaDolaresUtil().convertirSimulacionImporteDebitar(entity.getImpimpu())));
		simulacionCompraDTO.setMuestraImpuestos(!simulacionCompraDTO.getImporteImpuesto().equals(BigDecimal.ZERO));
		simulacionCompraDTO.setImporteImpuesto2(obtenerImporteFormateado(
                getCompraVentaDolaresUtil().convertirSimulacionImporteAComprar(entity.getImporteImpuesto2())));
		simulacionCompraDTO.setConceptoImpuesto2(entity.getConceptoImpuesto2());
		simulacionCompraDTO.setImpuesto2(entity.getImpuesto2());
		simulacionCompraDTO.setRegimenImpositivo2(entity.getRegimenImpositivo2());
		simulacionCompraDTO.setPorcentajeImpuesto2(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getPorcentajeImpuesto2(),2));
		simulacionCompraDTO.setImpuestoBienes(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpuestoBienes(),2));
		sesionParametros.setCompraDolaresImpcarg(entity.getImpcarg());
		return simulacionCompraDTO;
	}

	/**
	 * Obtener importe formateado.
	 *
	 * @param importe
	 *            the importe
	 * @return the big decimal
	 */
	private BigDecimal obtenerImporteFormateado(String importe) {
		String importeFormateado = ISBANStringUtils.agregadorPuntoDivisor(importe);
		return ISBANStringUtils.formatearSaldoString(importeFormateado);
	}
}