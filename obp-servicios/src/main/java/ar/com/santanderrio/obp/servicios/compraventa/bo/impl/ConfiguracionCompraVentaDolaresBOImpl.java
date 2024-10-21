/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.ConfiguracionCompraVentaDolaresBO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.CotizacionDolaresDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ConsultaCotizacionEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.HabilitacionCompraVentaDolaresBPEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.bo.CuentasBancaPrivadaBO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dto.CuentaIntermedioDTO;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;

/**
 * The Class ConfiguracionCompraVentaDolaresBOImpl.
 *
 * @author sabrina.cis
 */
@Component("compraVentaDolaresBO")
public class ConfiguracionCompraVentaDolaresBOImpl extends CompraVentaDolaresBOImpl
		implements ConfiguracionCompraVentaDolaresBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionCompraVentaDolaresBOImpl.class);

	/** The detalle tarjeta dao. */
	@Autowired
	private CotizacionDolaresDAO compraVentaDolaresDAO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	/** The operaciones banca privada DAO. */
	@Autowired
	private OperacionBancaPrivadaDAO operacionesBancaPrivadaDAO;
	
	/** The cuentas banca privada BO. */
	@Autowired
	private CuentasBancaPrivadaBO cuentasBancaPrivadaBO;
	
	/** The Constant COMPRA. */
	private static final String COMPRA = "C";
	
	/** The Constant VENTA. */
	private static final String VENTA = "V";
	
	private static final String COD_ERROR_SERVICIO_INACTIVO = "10099906";

	/**
	 * Obtiene las cuentas con saldos actualizados y ordenadas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param identificacionCuentaDestino
	 *            the identificacion cuenta destino
	 * @return the cuentas saldo ordenadas
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadas(Cliente cliente,
			IdentificacionCuenta identificacionCuenta, IdentificacionCuenta identificacionCuentaDestino)
			throws BusinessException {
		List<Cuenta> cuentasCompraVenta = filtrarCuentasHabilitadasCompraVenta(cliente);
		List<Cuenta> cuentasCompraVentaCopia = new ArrayList<Cuenta>();
		copiarArrayCuenta(cuentasCompraVenta, cuentasCompraVentaCopia);
		actualizarFavorita(cuentasCompraVentaCopia, cliente, identificacionCuenta);
		Respuesta<List<ResumenDetalleCuenta>> cuentas = this.getCuentaBO().getCuentasSaldo(cliente,
				cuentasCompraVentaCopia);
		CuentasUtils.ordenarCuentas(cuentas.getRespuesta());
		return cuentas;

	}

	/**
	 * Copiar array cuenta.
	 *
	 * @param cuentasCompraVenta
	 *            the cuentas compra venta
	 * @param cuentasCompraVentaCopia
	 *            the cuentas compra venta copia
	 */
	private void copiarArrayCuenta(List<Cuenta> cuentasCompraVenta, List<Cuenta> cuentasCompraVentaCopia) {
		for (Cuenta cuenta : cuentasCompraVenta) {
			Cuenta cuentaCopia = new Cuenta();
			cuentaCopia.setAlias(cuenta.getAlias());
			cuentaCopia.setCalidadDeParticipacion(cuenta.getCalidadDeParticipacion());
			cuentaCopia.setCbu(cuenta.getCbu());
			cuentaCopia.setClaseCuenta(cuenta.getClaseCuenta());
			cuentaCopia.setClasePaquete(cuenta.getClasePaquete());
			cuentaCopia.setCliente(cuenta.getCliente());
			cuentaCopia.setCodigoAplicacion(cuenta.getCodigoAplicacion());
			cuentaCopia.setCodigoPaquete(cuenta.getCodigoPaquete());
			cuentaCopia.setCodigoTitularidad(cuenta.getCodigoTitularidad());
			cuentaCopia.setContratoAltair(cuenta.getContratoAltair());
			cuentaCopia.setConvenioPAS(cuenta.isConvenioPAS());
			cuentaCopia.setDepositoEfectivo(cuenta.getDepositoEfectivo());
			cuentaCopia.setEstadoTarjetaCredito(cuenta.getEstadoTarjetaCredito());
			cuentaCopia.setFormaDeOperar(cuenta.getFormaDeOperar());
			cuentaCopia.setGrupoAfinidad(cuenta.getGrupoAfinidad());
			cuentaCopia.setHabilitadaParaTransferir(cuenta.isHabilitadaParaTransferir());
			cuentaCopia.setId(cuenta.getId());
			cuentaCopia.setIndex(cuenta.getIndex());
			cuentaCopia.setIsFavorita(cuenta.getIsFavorita());
			cuentaCopia.setJerarquiaCuenta(cuenta.getJerarquiaCuenta());
			cuentaCopia.setMonedaAltair(cuenta.getMonedaAltair());
			cuentaCopia.setNroCuentaProducto(cuenta.getNroCuentaProducto());
			cuentaCopia.setNroOrdenFirmante(cuenta.getNroOrdenFirmante());
			cuentaCopia.setNroSucursal(cuenta.getNroSucursal());
			cuentaCopia.setNroTarjetaCredito(cuenta.getNroTarjetaCredito());
			cuentaCopia.setNumeroPaquete(cuenta.getNumeroPaquete());
			cuentaCopia.setOficinaAltair(cuenta.getOficinaAltair());
			cuentaCopia.setProductoAltair(cuenta.getProductoAltair());
			cuentaCopia.setSaldoCUDls(cuenta.getSaldoCUDls());
			cuentaCopia.setSaldoCuenta(cuenta.getSaldoCuenta());
			cuentaCopia.setSaldoCUPesos(cuenta.getSaldoCUPesos());
			cuentaCopia.setSaldoParaConformar(cuenta.getSaldoParaConformar());
			cuentaCopia.setSubproductoAltair(cuenta.getSubproductoAltair());
			cuentaCopia.setSucursalPaquete(cuenta.getSucursalPaquete());
			cuentaCopia.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaCopia.setTipoCuentaEnum(cuenta.getTipoCuentaEnum());
			cuentaCopia.setTipoCuentaSinUnificar(cuenta.getTipoCuentaSinUnificar());
			cuentaCopia.setTipoCuentaSinUnificarDls(cuenta.getTipoCuentaSinUnificarDls());
			cuentasCompraVentaCopia.add(cuentaCopia);
		}

	}

	/**
	 * Actualizar favorita.
	 *
	 * @param cuentasCompraVenta
	 *            the cuentas compra venta
	 * @param cliente
	 *            the cliente
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 */
	private void actualizarFavorita(List<Cuenta> cuentasCompraVenta, Cliente cliente,
			IdentificacionCuenta identificacionCuenta) {
		if (identificacionCuenta != null) {
			Cuenta cuentaDefault = obtenerCuentaPorId(identificacionCuenta, cliente);
			if (cuentaDefault != null) {
				actualizarFavorita(cuentasCompraVenta, cuentaDefault);
			}
		}
	}

	/**
	 * Resetear favorita.
	 *
	 * @param cuentasCompraVenta
	 *            the cuentas compra venta
	 */
	private void resetearFavorita(List<Cuenta> cuentasCompraVenta) {
		for (Cuenta cuenta : cuentasCompraVenta) {
			if (cuenta.getIsFavorita()) {
				cuenta.setIsFavorita(false);
			}
		}
	}

	/**
	 * Actualizar favorita.
	 *
	 * @param cuentasCompraVenta
	 *            the cuentas compra venta
	 * @param cuentaDefault
	 *            the cuenta default
	 */
	private void actualizarFavorita(List<Cuenta> cuentasCompraVenta, Cuenta cuentaDefault) {
		resetearFavorita(cuentasCompraVenta);
		for (Cuenta cuenta : cuentasCompraVenta) {
			if (cuenta.getNroCuentaProducto().equals(cuentaDefault.getNroCuentaProducto())) {
				cuenta.setIsFavorita(true);
				break;
			}
		}
	}

	/**
	 * Genera la respuesta con los datos de la cotizacion, de la cuanta origen.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoCotizacion
	 *            the tipo cotizacion
	 * @param fromCuentas
	 *            the from cuentas
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<CompraVentaInicioDTO> obtenerCotizacion(Cliente cliente, Cuenta cuentaOrigen,
			String tipoCotizacion, boolean fromCuentas, boolean canalBancaPrivada) throws BusinessException {

		try {
			Respuesta<CompraVentaInicioDTO> respuesta = crearRespuestaCompraVentaDolarInicio(cliente, cuentaOrigen,
					tipoCotizacion, canalBancaPrivada);

			grabarEstadistica(tipoCotizacion, fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

			return respuesta;
		} catch (DAOException e) {
			grabarEstadistica(tipoCotizacion, fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("ERROR al consultar la Cotizacion: {}. ", cuentaOrigen.toString(), e);
			if (COD_ERROR_SERVICIO_INACTIVO.equals(e.getErrorCode())) {
				return casuistica.crearRespuestaErrorCotizacionInactivo();	
			}else {
				return casuistica.crearRespuestaErrorCotizacion();
			}
			
		} catch (CompraVentaDolaresException e) {
			grabarEstadistica(tipoCotizacion, fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("ERROR al consultar la Cotizacion: {}. ", e);
			throw new BusinessException(e);
		} catch (NullPointerException e) {
			grabarEstadistica(tipoCotizacion, fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("ERROR al obtener los datos de la cotizacion {}. {}.", cliente, e);
			throw new BusinessException(e);
		} catch (BotonPanicoCompraventaException e) {
			return casuistica.crearRespuestaErrorPanico(e.getMessage());
		}
	}

	/**
	 * Crear respuesta compra venta dolar inicio.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuentaOrigen
	 *            the cuenta origen
	 * @param tipoCotizacion
	 *            the tipo cotizacion
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 * @throws BotonPanicoCompraventaException 
	 */
	private Respuesta<CompraVentaInicioDTO> crearRespuestaCompraVentaDolarInicio(Cliente cliente, Cuenta cuentaOrigen,
			String tipoCotizacion, boolean canalBancaPrivada) throws DAOException, BusinessException, CompraVentaDolaresException, BotonPanicoCompraventaException {
		LOGGER.debug("Llamando al DAO para obtener la cotizacion: {}.", cuentaOrigen.toString());
		ConsultaCotizacionEntity entity = compraVentaDolaresDAO.obtenerCotizacion(cliente, cuentaOrigen,
				tipoCotizacion, canalBancaPrivada);
		CompraVentaInicioDTO compraVentaDolarInicio = generarCompraVentaInicioDTO(entity, tipoCotizacion);
		return casuistica.crearRespuestaOk(CompraVentaInicioDTO.class, compraVentaDolarInicio);
	}

	/**
	 * Generar compra venta inicio DTO.
	 *
	 * @param entity
	 *            the respuesta consulta cotizacion
	 * @param tipoCotizacion
	 *            the tipo cotizacion
	 * @return the compra venta inicio DTO
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	private CompraVentaInicioDTO generarCompraVentaInicioDTO(ConsultaCotizacionEntity entity, String tipoCotizacion)
			throws CompraVentaDolaresException {
		CompraVentaInicioDTO compraVentaInicioDTO = new CompraVentaInicioDTO();
		String cotizacionCuatroDecimales = this.getCompraVentaDolaresUtil()
				.parcearCotizacionCuatroDecimales(entity.getCotizacionSalida());
		compraVentaInicioDTO.setCotizacion(Double.valueOf(cotizacionCuatroDecimales));
		compraVentaInicioDTO.setCotizacionString(
				this.getCompraVentaDolaresUtil().obtenerCotizacionString(entity.getCotizacionSalida()));
		if (tipoCotizacion != null) {
			compraVentaInicioDTO.setTipoOperacionInicial(tipoCotizacion);
		}
		return compraVentaInicioDTO;
	}

	/**
	 * Filtrar cuentas habilitadas compra venta.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
	private List<Cuenta> filtrarCuentasHabilitadasCompraVenta(Cliente cliente) {
		List<Cuenta> cuentasHabilitadas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (esCajaAhorroEspecial(cuenta) || isCuentaRepatriacion(cuenta)) {
				continue;
			} else {
				cuentasHabilitadas.add(cuenta);
			}
		}
		return cuentasHabilitadas;
	}

	/**
	 * Es caja ahorro especial.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCajaAhorroEspecial(Cuenta cuenta) {
		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(cuenta.getTipoCuentaEnum()) && "03".equals(cuenta.getProductoAltair())
				&& "0001".equals(cuenta.getSubproductoAltair())) {
			return true;
		}
		return false;
	}

	private Boolean isCuentaRepatriacion(Cuenta cuenta) {
		return TipoCuenta.CAJA_AHORRO_DOLARES.equals(cuenta.getTipoCuentaEnum()) &&
			"03".equals(cuenta.getProductoAltair()) &&
				"0007".equals(cuenta.getSubproductoAltair());
	}

	/**
	 * Grabar estadistica.
	 *
	 * @param tipoCotizacion the tipo cotizacion
	 * @param fromCuentas the from cuentas
	 * @param resultado the resultado
	 */
	private void grabarEstadistica(String tipoCotizacion, boolean fromCuentas, String resultado) {
		if (COMPRA.equals(tipoCotizacion)) {
			grabarEstadisticaSegunOrigen(fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICA_COMPRA_DE_DOLARES_DESDE_CUENTAS,
					EstadisticasConstants.CODIGO_ESTADISTICA_COMPRA_DE_DOLARES_DESDE_TRANSACCIONES,
					resultado);
		} else if (VENTA.equals(tipoCotizacion)) {
			grabarEstadisticaSegunOrigen(fromCuentas, EstadisticasConstants.CODIGO_ESTADISTICA_VENTA_DE_DOLARES_DESDE_CUENTAS,
					EstadisticasConstants.CODIGO_ESTADISTICA_VENTA_DE_DOLARES_DESDE_TRANSACCIONES,
					resultado);
		}
	}
	
	/**
	 * Grabar estadistica segun origen.
	 *
	 * @param fromCuentas the from cuentas
	 * @param estadisticaFromCentas the estadistica from centas
	 * @param estadisticaFromTrans the estadistica from trans
	 * @param resultado the resultado
	 */
	private void grabarEstadisticaSegunOrigen(boolean fromCuentas, String estadisticaFromCentas, String estadisticaFromTrans,
			String resultado) {
		if (fromCuentas) {
			estadisticaManager.add(estadisticaFromCentas, resultado);
		} else {
			estadisticaManager.add(estadisticaFromTrans, resultado);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.compraventa.bo.ConfiguracionCompraVentaDolaresBO#getCuentasSaldoOrdenadasBP(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	public Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoOrdenadasBP(Cliente cliente)
			throws BusinessException, SQLException, ImporteConvertException {
		List<Cuenta> cuentasCompraVentaBancaPrivada = filtrarCuentasHabilitadasCompraVentaBancaPrivada(cliente);
		List<Cuenta> cuentasCompraVentaBancaPrivadaCopia = new ArrayList<Cuenta>();
		copiarArrayCuenta(cuentasCompraVentaBancaPrivada, cuentasCompraVentaBancaPrivadaCopia);
		Respuesta<List<ResumenDetalleCuenta>> cuentas = getCuentasSaldoBP(cuentasCompraVentaBancaPrivada, cliente);
		return cuentas;
	}

	/**
	 * Gets the cuentas saldo BP.
	 *
	 * @param cuentasCompraVentaBancaPrivadaCopia
	 *            the cuentas compra venta banca privada copia
	 * @param cliente
	 *            the cliente
	 * @return the cuentas saldo BP
	 * @throws SQLException
	 *             the SQL exception
	 * @throws BusinessException
	 *             the business exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	// llama al bo de banca privada para consultar los saldos
	private Respuesta<List<ResumenDetalleCuenta>> getCuentasSaldoBP(List<Cuenta> cuentasCompraVentaBancaPrivadaCopia,
			Cliente cliente) throws SQLException, BusinessException, ImporteConvertException {
		List<ItemMensajeRespuesta> itemsMensajesErrores = new ArrayList<ItemMensajeRespuesta>();
		List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
		List<CuentaIntermedioDTO> saldosCuenta = cuentasBancaPrivadaBO.obtenerSaldoServicioIatx(cliente);
		for (Cuenta cuenta : cuentasCompraVentaBancaPrivadaCopia) {
			ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
			ConsultaSaldoCtasConAperturaOutEntity saldoCuenta = null;
			for (CuentaIntermedioDTO cuentaIntermedioDTO : saldosCuenta) {
				if (cuenta.obtenerNroCuentaFormateado().equals(cuentaIntermedioDTO.getNumeroCuenta())) {
					saldoCuenta = cuentaIntermedioDTO.getSaldosServicioIatx();
					resumenDetalleCuenta = initResumenDetalleCuentaBP(cuenta, saldoCuenta);
				}
			}
			respuestaDetalleCuenta.add(resumenDetalleCuenta);
			resumenDetalleCuenta.setAlias(null);
			resumenDetalleCuenta.setFavorita(Boolean.FALSE);
		}

		Respuesta<List<ResumenDetalleCuenta>> repuesta = new Respuesta<List<ResumenDetalleCuenta>>();
		repuesta.setRespuesta(respuestaDetalleCuenta);
		repuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		repuesta.setRespuestaVacia(false);
		repuesta.setItemMensajeRespuesta(itemsMensajesErrores);
		return repuesta;
	}

	/**
	 * Inits the resumen detalle cuenta BP.
	 *
	 * @param abstractCuenta
	 *            the abstract cuenta
	 * @param saldoCuenta
	 *            the saldo cuenta
	 * @return the resumen detalle cuenta
	 */
	private ResumenDetalleCuenta initResumenDetalleCuentaBP(AbstractCuenta abstractCuenta,
			ConsultaSaldoCtasConAperturaOutEntity saldoCuenta) {
		ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
		resumenDetalleCuenta.setCuentaPrincipal(abstractCuenta.isCuentaPrincipal());
		resumenDetalleCuenta.setNroCuentaProducto(abstractCuenta.getNroCuentaProducto());
		resumenDetalleCuenta.setNroSucursal(abstractCuenta.getNroSucursal());
		resumenDetalleCuenta.setTipoCuenta(abstractCuenta.getTipoCuenta());
		resumenDetalleCuenta.setCuentaUnica(abstractCuenta.isCuentaUnica());
		resumenDetalleCuenta.setCuentaCerrada(abstractCuenta.isCuentaCerrada());
		resumenDetalleCuenta.setFavorita(abstractCuenta.getIsFavorita());
		resumenDetalleCuenta.setAlias(abstractCuenta.getAlias());

		if ("02".equals(abstractCuenta.getTipoCuenta())) {
			resumenDetalleCuenta.setSaldoCajaAhorroDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAD()));
			resumenDetalleCuenta.setSaldoCajaAhorroPesos(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAH()));
			resumenDetalleCuenta.setSaldoPesos(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAH()));
			resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAD()));
			resumenDetalleCuenta.setCuentaTraspasoAutomatico(true);
		}

		if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(abstractCuenta.getTipoCuentaEnum())) {
			resumenDetalleCuenta.setSaldoCuentaCorrientePesos(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACTE()));
		}
		if (TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(abstractCuenta.getTipoCuentaEnum())) {
			resumenDetalleCuenta.setSaldoDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACCD()));
		}
		if (TipoCuenta.CAJA_AHORRO_PESOS.equals(abstractCuenta.getTipoCuentaEnum())) {
			resumenDetalleCuenta.setSaldoCajaAhorroPesos(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAH()));
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(abstractCuenta.getTipoCuentaEnum())) {
			resumenDetalleCuenta.setSaldoCajaAhorroDolares(getSaldoBigDecimal(saldoCuenta.getSaldoDispoACAD()));
		}

		if (!abstractCuenta.isCuentaCerrada()) {

			resumenDetalleCuenta.setConvenioPAS(((Cuenta) abstractCuenta).isConvenioPAS());
		}
		resumenDetalleCuenta.setCodigoTitularidad(abstractCuenta.getCodigoTitularidad());
		resumenDetalleCuenta.setCliente(abstractCuenta.getCliente());

		return resumenDetalleCuenta;
	}

	/**
	 * Filtrar cuentas habilitadas compra venta banca privada. filtra las
	 * cuentas de banca privada y llama al servicio CNSMICEL
	 * 
	 * @param cliente
	 *            the cliente
	 * @return the list
	 */
	private List<Cuenta> filtrarCuentasHabilitadasCompraVentaBancaPrivada(Cliente cliente) {
		List<Cuenta> cuentasHabilitadas = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentasBancaPrivadaBO.obtenerCuentasBancaPrivada(cliente)) {
			// llama al servicio CNMISCEL_ si CNSMICEL.Codigo de Miscelaneo = 89
			// la cuenta no esta hablitada para mostrarse en el selector
			boolean cuentaHabilitada = cuentaBancaPrivadaHabilitada(cliente, cuenta);
			if (esCajaAhorroEspecial(cuenta) || !cuentaHabilitada || isCuentaRepatriacion(cuenta)) {
				continue;
			} else {
				cuentasHabilitadas.add(cuenta);
			}
		}
		return cuentasHabilitadas;
	}

	/**
	 * Cuenta banca privada habilitada.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 */
	private boolean cuentaBancaPrivadaHabilitada(Cliente cliente, Cuenta cuenta) {
		LOGGER.debug("Llamando al DAO para obtener habilitacion de cuenta BP: {}.", cuenta.toString());
		Boolean habilitarCuentaCompraVenta = true;
		try {
			HabilitacionCompraVentaDolaresBPEntity habilitarCuentaBP = operacionesBancaPrivadaDAO
					.obtenerHabilitacionCuentaBP(cliente, cuenta);
			if (habilitarCuentaBP != null && "89".equals(habilitarCuentaBP.getCodErrorDeMisceleano())) {
				habilitarCuentaCompraVenta = false;
			}
		} catch (IatxException e) {
			habilitarCuentaCompraVenta = false;
			e.printStackTrace();
		} catch (DAOException e) {
			habilitarCuentaCompraVenta = false;
			e.printStackTrace();
		}
		return habilitarCuentaCompraVenta;
	}

	/**
	 * Gets the saldo big decimal.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the saldo big decimal
	 */
	private BigDecimal getSaldoBigDecimal(String saldo) {
		try {
			return ISBANStringUtils.convertirStrToBigDecimal(saldo, 2);
		} catch (ImporteConvertException e) {
			LOGGER.info("Error al parsear importe o no existe importe", e);
		}
		return null;
	}
}