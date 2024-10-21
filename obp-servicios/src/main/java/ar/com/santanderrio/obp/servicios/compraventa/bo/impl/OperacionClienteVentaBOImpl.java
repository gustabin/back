/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteVentaBO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.LimiteOperacionCompraVentaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.LimiteCompraVentaUSDEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.RequestBancaPrivadaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class OperacionClienteVentaBOImpl.
 */
@Component
public class OperacionClienteVentaBOImpl extends CompraVentaDolaresBOImpl implements OperacionClienteVentaBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionClienteCompraBOImpl.class);

	/** The OperacionCompraDAO. */
	@Autowired
	private OperacionClienteVendeDAO operacionVentaDAO;
	
	/** The OperacionBancaPrivadaDAO. */
	@Autowired
	private OperacionBancaPrivadaDAO operacionBancaPrivadaDAO;

	@Autowired
	private LimiteOperacionCompraVentaDAO limiteOperacionCompraVentaDAO;
	
	/**
	 * Obtiene la respuesta con los datos de la simuacion cuando Cliente Compra
	 * Dolares.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<ComprobanteCompraVentaDTO> operacionClienteVenta(ParametrosOperacion parametrosOperacion)
			throws BusinessException {
		try {
			OperacionCompraVentaDatosEntrada datosEntrada = crearDatosEntradaClienteVenta(parametrosOperacion);
			return obtenerRespuestaClienteVenta(parametrosOperacion, datosEntrada);
		} catch (CompraVentaDolaresException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		} catch (BusinessException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		} catch (NullPointerException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			throw new BusinessException(e);
		}
	}

	/**
	 * CPVTADOLCN 110 Realiza operaciones de venta (Banco Vende / Cliente
	 * Compra) de dólares.
	 *
	 * @param parametros
	 *            the parametros operacion
	 * @return the simulacion compra venta dolar dato entrada
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 * @throws BusinessException
	 *             the business exception
	 */
	private OperacionCompraVentaDatosEntrada crearDatosEntradaClienteVenta(ParametrosOperacion parametros)
			throws CompraVentaDolaresException, BusinessException {
		OperacionCompraVentaDatosEntrada operacionCompraVentaDatosEntrada = new OperacionCompraVentaDatosEntrada();
		operacionCompraVentaDatosEntrada
				.setAplicacionPesos(getCompraVentaDolaresUtil().obtenerAplicacionPesos(parametros.getCuentaDestino()));
		operacionCompraVentaDatosEntrada.setSucursalctapesos(
				getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaDestino()));
		operacionCompraVentaDatosEntrada.setNumeroctapesos(
				getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaDestino()));
		operacionCompraVentaDatosEntrada.setAplicacionDolar(getCompraVentaDolaresUtil()
				.obtenerAplicacionDolar(parametros.getCuentaOrigen(), CompraVentaStringUtil.OPERACION_VENTA));
		operacionCompraVentaDatosEntrada.setSucursalCtaDolar(
				getCompraVentaDolaresUtil().obtenerSucursalTresDigitos(parametros.getCuentaOrigen()));
		operacionCompraVentaDatosEntrada.setNumeroCtaDolar(
				getCompraVentaDolaresUtil().obtenerNumeroCtaDoceDigitos(parametros.getCuentaOrigen()));
		operacionCompraVentaDatosEntrada.setIndtuatesora(getCompraVentaDolaresUtil().obtenerIndTuAtesoraA());
		operacionCompraVentaDatosEntrada.setFechavalor(getCompraVentaDolaresUtil().obtenerFechVal());
		operacionCompraVentaDatosEntrada
				.setConcepdebito(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.CUATRO));
		operacionCompraVentaDatosEntrada
				.setConcepcred(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.CUATRO));
		operacionCompraVentaDatosEntrada.setCodigodebi(getCompraVentaDolaresUtil().obtenerCodigoDebiVenta());
		operacionCompraVentaDatosEntrada.setCodigocred(getCompraVentaDolaresUtil().obtenerCodigoCreVenta());
		operacionCompraVentaDatosEntrada.setImportedeb(getCompraVentaDolaresUtil()
				.obtenerImporteDebitoVenta(parametros.getImporteDebito(), parametros.getCotizacion(), true));
		operacionCompraVentaDatosEntrada.setImportecred(
				getCompraVentaDolaresUtil().obtenerImporteCredOperacionCompra(parametros.getImporteCredito()));
		operacionCompraVentaDatosEntrada
				.setImportecoti(getCompraVentaDolaresUtil().obtenerImporteCoti(parametros.getCotizacion()));
		operacionCompraVentaDatosEntrada
				.setIndiccompravta(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.UNO));
		operacionCompraVentaDatosEntrada
				.setAutorizafip(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.VEINTE));
		operacionCompraVentaDatosEntrada
				.setTipocambio(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.TRES));
		operacionCompraVentaDatosEntrada
				.setNumbolecvta(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.OCHO));
		operacionCompraVentaDatosEntrada.setCodtributa(getCompraVentaDolaresUtil().obtenerCodTributaVenta());
		operacionCompraVentaDatosEntrada
				.setFecingrpais(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.OCHO));
		operacionCompraVentaDatosEntrada
				.setNomapell(getCompraVentaDolaresUtil().obtenerNomApell(parametros.getCliente()));
		operacionCompraVentaDatosEntrada
				.setMarcaempleado(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.UNO));
		operacionCompraVentaDatosEntrada.setPaisemisdoc(StringUtils.repeat("0", 3));
		operacionCompraVentaDatosEntrada.setPaisnac(StringUtils.repeat("0", 3));
		operacionCompraVentaDatosEntrada.setCodigoconcepto(getCompraVentaDolaresUtil().obtenerCodigoConcepto());
		operacionCompraVentaDatosEntrada
				.setTpodocbcra(getCompraVentaDolaresUtil().obtenerDocBcra(parametros.getNupTipo()));
		operacionCompraVentaDatosEntrada.setNrodocbcra(parametros.getNupNumDoc());
		operacionCompraVentaDatosEntrada
				.setTiprefafip(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.TRES));
		operacionCompraVentaDatosEntrada
				.setNiocuentas(getCompraVentaDolaresUtil().setearEspacios(CompraVentaStringUtil.VEINTICUATRO));
		return operacionCompraVentaDatosEntrada;
	}

	/**
	 * Carga el objeto que tiene los datos de la respuesta para el paso tres y
	 * cuatro.
	 *
	 * @param entity
	 *            the entity
	 * @param parametros
	 *            the parametros
	 * @return the comprobante compra venta DTO
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private ComprobanteCompraVentaDTO crearDatosResponseClienteVenta(OperacionClienteVentaEntity entity,
			ParametrosOperacion parametros) throws CompraVentaDolaresException {
		ComprobanteCompraVentaDTO comprobanteCompraVenta = new ComprobanteCompraVentaDTO();
		comprobanteCompraVenta
				.setImportePesosAcreditado(ISBANStringUtils.formatearSaldoString(parametros.getImporteCredito()));
		comprobanteCompraVenta
				.setCuentaDestinoNumero(new IdentificacionCuenta(parametros.getCuentaDestino().getNroSucursal(),
						parametros.getCuentaDestino().getNroCuentaProducto()).toString());
		comprobanteCompraVenta.setCuentaDestinoTipo(obtenerDescripcionTipoCuenta(parametros.getCuentaDestino(), false));
		comprobanteCompraVenta
				.setCuentaOrigenNumero(new IdentificacionCuenta(parametros.getCuentaOrigen().getNroSucursal(),
						parametros.getCuentaOrigen().getNroCuentaProducto()).toString());
		comprobanteCompraVenta.setCuentaOrigenTipo(obtenerDescripcionTipoCuenta(parametros.getCuentaOrigen(), true));
		comprobanteCompraVenta.setLegalesCompra(parametros.getLegales());
		comprobanteCompraVenta.setNumeroOperacion(entity.getNroOperacion());
		comprobanteCompraVenta.setNumeroComprobante(entity.getNroBoleto());
		comprobanteCompraVenta
				.setImporteDolaresDebitado(ISBANStringUtils.formatearSaldoString(parametros.getImporteDebito()));
		comprobanteCompraVenta.setFecha(
				ISBANStringUtils.formatearFecha(getCompraVentaDolaresUtil().formatearFechaSinHora(entity.getFecha())));
		comprobanteCompraVenta.setHora(entity.getHora());
		comprobanteCompraVenta.setCotizacionAplicada(ISBANStringUtils.formatearSaldoString(parametros.getCotizacion()));
		comprobanteCompraVenta.setLegalCompraUno(obtenerLegal("1005"));
		comprobanteCompraVenta.setLegalCompraDos(obtenerLegal("1004"));
		comprobanteCompraVenta.setFechaHoraServicio(entity.getFechaHoraBody());
		return comprobanteCompraVenta;
	}

	/**
	 * Genera una respuesta con los datos de la simulacion para la compra de
	 * dolares.
	 *
	 * @param parametrosOperacion
	 *            the parametros operacion
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private Respuesta<ComprobanteCompraVentaDTO> obtenerRespuestaClienteVenta(ParametrosOperacion parametrosOperacion,
			OperacionCompraVentaDatosEntrada datosEntrada) throws BusinessException, CompraVentaDolaresException {
		try {
			boolean isBancaPrivada = CuentasBancaPrivadaUtil.isBancaPrivada(parametrosOperacion.getCuentaOrigen(),
					parametrosOperacion.getCuentaDestino());

			LOGGER.debug("Llamando al DAO para la realizar la operacion cliente venta: {}.", datosEntrada.toString());
			OperacionClienteVentaEntity entity = operacionVentaDAO
					.operacionClienteVende(parametrosOperacion.getCliente(), datosEntrada, isBancaPrivada);
			if (!entity.getTieneError()) {
				if (isBancaPrivada) {
					RequestBancaPrivadaEntity entityBancaPrivada = new RequestBancaPrivadaEntity(entity.getNroBoleto(),
							entity.getImportedebitar(), entity.getImporteacreditar(), entity.getImportecotizacion(),
							"V");
					operacionBancaPrivadaDAO.insertarOperacionCambio(parametrosOperacion, datosEntrada,
							entityBancaPrivada);
				}
				return casuistica.crearRespuestaOk(ComprobanteCompraVentaDTO.class,
						crearDatosResponseClienteVenta(entity, parametrosOperacion));
			}
			return crearRespuestaError(obtenerDatosDelError(entity.getCodError(), false),
					CompraVentaStringUtil.OPERACION_VENTA, parametrosOperacion.getCuentaOrigen(), entity.getMensajeError());
		} catch (DAOException e) {
			LOGGER.error(SERVICIO_DEVOLVIO_LOG_LABEL, e);
			return casuistica.crearRespuestaErrorServicio();
		}

	}

	@Override
	public LimiteCompraVentaUSDEntity limiteMaximoCompraVentaUSD(Cliente cliente,
			ParametrosOperacion parametrosOperacion, Boolean isBancaPrivada, boolean vende) throws DAOException {
		
		return limiteOperacionCompraVentaDAO.limiteMaximoCompraVentaUSD(cliente, parametrosOperacion, isBancaPrivada, vende, this.getCompraVentaDolaresUtil().obtenerCodigoConcepto());
	}

}
