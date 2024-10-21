/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.SimulacionClienteVendeBO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteVendeEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class SimulacionClienteVendeBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class SimulacionClienteVendeBOImpl extends CompraVentaDolaresBOImpl implements SimulacionClienteVendeBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionClienteVendeBOImpl.class);

	/** The SimulacionClienteVendeDAO. */
	@Autowired
	private SimulacionClienteVendeDAO simulacionClienteVendeDAO;

	@Autowired
	private EstadisticaManager estadisticaManager;
	
	/**
	 * Obtiene la respuesta con los datos de la simuacion cuando Cliente Vende
	 * Dolares.
	 *
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<SimulacionCompraVentaDTO> obtenerSimulacionClienteVende(ParametrosSimulacion parametrosSimulacion)
			throws BusinessException {
		DetalleDocumentoDTO detalleDocumentoDTO;
		try {
			detalleDocumentoDTO = obtenerDocumentoValido(parametrosSimulacion.getCliente());
			if (!detalleDocumentoDTO.getTieneError()) {
				parametrosSimulacion.setNupTipo(detalleDocumentoDTO.getTipoDocumento());
				parametrosSimulacion.setNupNumDoc(detalleDocumentoDTO.getNroDocumento());

				SimulacionDatosEntrada datosEntrada = crearDatosEntradaClienteVende(parametrosSimulacion);
				return obtenerRespuestaClienteVende(parametrosSimulacion.getCliente(), datosEntrada,
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
	 * venta de dolares SIMDOLCNLS100.
	 *
	 * @param parametros
	 *            the parametros simulacion
	 * @return the simulacion compra venta dolar dato entrada
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private SimulacionDatosEntrada crearDatosEntradaClienteVende(ParametrosSimulacion parametros)
			throws CompraVentaDolaresException {
		SimulacionDatosEntrada simulacionCompraVentaDolarData = new SimulacionDatosEntrada();
		simulacionCompraVentaDolarData.setAplicacionPesos(
				this.getCompraVentaDolaresUtil().obtenerAplicacionPesos(parametros.getCuentaDestino()));
		simulacionCompraVentaDolarData.setSucursalCtaPesos(
				this.getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaDestino()));
		simulacionCompraVentaDolarData.setNumeroCtaPesos(
				this.getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaDestino()));
		simulacionCompraVentaDolarData.setAplicacionDolar(this.getCompraVentaDolaresUtil()
				.obtenerAplicacionDolar(parametros.getCuentaOrigen(), CompraVentaStringUtil.OPERACION_VENTA));
		simulacionCompraVentaDolarData.setSucursalCtaDolar(
				this.getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaOrigen()));
		simulacionCompraVentaDolarData.setNuemroCtaDolar(
				this.getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaOrigen()));
		simulacionCompraVentaDolarData.setIndTuAtesora(this.getCompraVentaDolaresUtil().obtenerIndTuAtesoraA());
		simulacionCompraVentaDolarData.setFechaValor(this.getCompraVentaDolaresUtil().obtenerFechaValor());
		simulacionCompraVentaDolarData.setConcepDebito(StringUtils.repeat(" ", 4));
		simulacionCompraVentaDolarData.setConcepCred(StringUtils.repeat(" ", 4));
		simulacionCompraVentaDolarData.setCodigoDebi(this.getCompraVentaDolaresUtil().obtenerCodigoDebiVenta());
		simulacionCompraVentaDolarData.setCodigoCre(this.getCompraVentaDolaresUtil().obtenerCodigoCreVenta());
		simulacionCompraVentaDolarData.setImporteDeb(this.getCompraVentaDolaresUtil().obtenerImporteDebitoVenta(
				parametros.getImporte(), parametros.getCotizacion(), parametros.getIsDolar()));
		simulacionCompraVentaDolarData.setImporteCred(StringUtils.repeat("0", 15));
		simulacionCompraVentaDolarData
				.setImporteCoti(this.getCompraVentaDolaresUtil().obtenerImporteCoti(parametros.getCotizacion()));
		simulacionCompraVentaDolarData.setIndicCompraVta(StringUtils.repeat(" ", 1));
		simulacionCompraVentaDolarData.setAutorizaAfip(StringUtils.repeat(" ", 20));
		simulacionCompraVentaDolarData.setTipoCambio(StringUtils.repeat(" ", 3));
		simulacionCompraVentaDolarData.setNumBoleCvta(StringUtils.repeat(" ", 8));
		simulacionCompraVentaDolarData.setCodTributa(this.getCompraVentaDolaresUtil().obtenerCodTributaVenta());
		simulacionCompraVentaDolarData.setFecIngrPais(StringUtils.repeat("0", 8));
		simulacionCompraVentaDolarData
				.setNomApell(this.getCompraVentaDolaresUtil().obtenerNomApell(parametros.getCliente()));
		simulacionCompraVentaDolarData.setMarcaEmpleado("  ");
		simulacionCompraVentaDolarData.setPaisEmisDoc(StringUtils.repeat("0", 3));
		simulacionCompraVentaDolarData.setPaisNac(StringUtils.repeat("0", 3));
		simulacionCompraVentaDolarData.setCodigoConcepto(this.getCompraVentaDolaresUtil().obtenerCodigoConcepto());
		simulacionCompraVentaDolarData.setTpoDocBcra(parametros.getNupTipo());
		simulacionCompraVentaDolarData.setNroDocBcra(parametros.getNupNumDoc());
		simulacionCompraVentaDolarData.setCondCliente(this.getCompraVentaDolaresUtil().obtenerCndCliente());
		simulacionCompraVentaDolarData.setTipRefAfip(StringUtils.repeat(" ", 3));

		return simulacionCompraVentaDolarData;
	}

	/**
	 * Obtener respuesta cliente vende.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @param parametrosSimulacion
	 *            the parametros simulacion
	 * @return the respuesta
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<SimulacionCompraVentaDTO> obtenerRespuestaClienteVende(Cliente cliente,
			SimulacionDatosEntrada datosEntrada, ParametrosSimulacion parametrosSimulacion)
			throws CompraVentaDolaresException {
		try {
			LOGGER.debug("Llamando al DAO para la simulacion: {}.", datosEntrada.toString());
			SimulacionClienteVendeEntity entity = simulacionClienteVendeDAO.obtenerSimulacionClienteVende(cliente,
					datosEntrada, CuentasBancaPrivadaUtil.isBancaPrivada(parametrosSimulacion.getCuentaOrigen(), parametrosSimulacion.getCuentaDestino()));

			if (entity.getTieneError()) {
				ErrorCompraVentaEnum errorCompraVentaEnum = obtenerDatosDelError(entity.getCodError(), false);
				armarEstadisticaSimulacionVenta(errorCompraVentaEnum, parametrosSimulacion);
				return crearRespuestaError(errorCompraVentaEnum,CompraVentaStringUtil.OPERACION_VENTA, parametrosSimulacion.getCuentaOrigen(), entity.getMensajeError());
			} else {

				SimulacionCompraVentaDTO simulacionCompraVentaDolar = crearDatosResponseClienteVende(entity,
						parametrosSimulacion);
				return casuistica.crearRespuestaOk(SimulacionCompraVentaDTO.class, simulacionCompraVentaDolar);
			}
		} catch (DAOException e) {
			LOGGER.error("ERROR_SERVICIO (1389)  {}. {}.", e);
			return casuistica.crearRespuestaErrorServicio();
		}
	}

	private void armarEstadisticaSimulacionVenta(ErrorCompraVentaEnum errorCompraVentaEnum,
			ParametrosSimulacion parametrosSimulacion) {
		String CodigoTipoCuenta = obtenerCodigoPorTipoCuenta(parametrosSimulacion.getCuentaOrigen().getTipoCuentaEnum());
		if (!ISBANStringUtils.isEmptyOrNull(CodigoTipoCuenta)) {
			estadisticaManager.add(CodigoTipoCuenta, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);			
		}
	}

	private String obtenerCodigoPorTipoCuenta(TipoCuenta tipoCuentaEnum) {
		String tipoCuenta = null;
		
		if (tipoCuentaEnum.equals(TipoCuenta.CAJA_AHORRO_PESOS)) {
			tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CA_PESOS;
		}
		if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)) {
			tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CC_PESOS;
		}
		if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
			tipoCuenta = EstadisticasConstants.VENTA_DOLARES_CU_PESOS;
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
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private SimulacionCompraVentaDTO crearDatosResponseClienteVende(SimulacionClienteVendeEntity entity,
			ParametrosSimulacion parametrosSimulacion) throws CompraVentaDolaresException {
		SimulacionCompraVentaDTO simulacionVentaDTO = new SimulacionCompraVentaDTO();
		simulacionVentaDTO.setEsCompra(false);
		simulacionVentaDTO.setEsVenta(true);
		simulacionVentaDTO.setNumeroCuentaOrigen(parametrosSimulacion.getNumeroCuentaOrigen());
		simulacionVentaDTO
				.setTipoCuentaOrigen(obtenerDescripcionTipoCuenta(parametrosSimulacion.getCuentaOrigen(), true));
		simulacionVentaDTO.setAliasOrigen(aliasGetter(parametrosSimulacion.getCuentaOrigen()));
		simulacionVentaDTO.setNumeroCuentaDestino(parametrosSimulacion.getNumeroCuentaDestino());
		simulacionVentaDTO
				.setTipoCuentaDestino(obtenerDescripcionTipoCuenta(parametrosSimulacion.getCuentaDestino(), false));
		simulacionVentaDTO.setAliasDestino(aliasGetter(parametrosSimulacion.getCuentaDestino()));
		simulacionVentaDTO.setImporteVentaDolar(new BigDecimal(
				getCompraVentaDolaresUtil().convertirSimulacionImporteAComprarConPunto(entity.getImporteDebito())));
		simulacionVentaDTO.setCotizacion(new BigDecimal(
				getCompraVentaDolaresUtil().obtenerCotizacionAplicableVentaConPunto(entity.getImporteCotizacion())));
		simulacionVentaDTO.setImporteAcreditarPesos(new BigDecimal(
				getCompraVentaDolaresUtil().convertirSimulacionImporteDebitarConPunto(entity.getImporteCredito())));
		simulacionVentaDTO.setNupNumDoc(parametrosSimulacion.getNupNumDoc());
		simulacionVentaDTO.setNupTipo(parametrosSimulacion.getNupTipo());
		simulacionVentaDTO.setFecha(DateTime.now().toDate());
		simulacionVentaDTO.setLegales(obtenerLegal(CompraVentaStringUtil.COD_LEGAL_VENTA_DOLARES));
		return simulacionVentaDTO;
	}
}