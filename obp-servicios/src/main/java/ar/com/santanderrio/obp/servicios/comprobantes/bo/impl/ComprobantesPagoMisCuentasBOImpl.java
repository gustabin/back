/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoMisCuentasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobantePMCEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * BO de comprobantes pago mis cuentas.
 */
@Component
public class ComprobantesPagoMisCuentasBOImpl extends ComprobantesBOImpl implements ComprobantesPagoMisCuentasBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesPagoMisCuentasBOImpl.class);

	/** The ComprobantesPagoMisCuentasDAO. */
	@Autowired
	private ComprobantesPagoMisCuentasDAO comprobantesPagoMisCuentasDAO;

	/** The diasFechaDesde. */
	@Value("${COMPROBANTES.FECHA.ANTERIOR}")
	private String diasFechaDesde;

	/** The MediosPagoBO. */
	@Autowired
	private MediosPagoBO medioPagoBO;

	/** The sub empresa. */
	@Autowired
	private SubEmpresasDAO subEmpresa;

	/** The Constant codigoAplicacion. */
	private static final String CODIGO_APLICACION = "ABAE";

	/** The Constant obteniendoComprobantes. */
	private static final String OBTENIENDO_COMPROBANTES = "Obteniendo comprobantes de PMC";

	/** The Constant errorServicio. */
	private static final String ERROR_SERVICIO = "Error en servicio";

	/** The Constant TIPO_CUENTA_TARJETA. */
	private static final String TIPO_CUENTA_TARJETA = "31";
	
	/** The Constant TIPO_CUENTA_TARJETA. */
	private static final Integer TIPO_PAGO_CON_DEUDA = 3;

	/** The Constant TIPO_CUENTA_TARJETA_MAP. */
	private static final Map<String, TipoCuenta> TIPO_CUENTA_TARJETA_MAP = new HashMap<String, TipoCuenta>();
	static {
		TIPO_CUENTA_TARJETA_MAP.put("01", TipoCuenta.MASTERCARD);
		TIPO_CUENTA_TARJETA_MAP.put("02", TipoCuenta.AMEX);
		TIPO_CUENTA_TARJETA_MAP.put("04", TipoCuenta.VISA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPagoMisCuentasBO#obtenerComprobantesPMCAsync(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	@Async
	public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMCAsync(Cliente cliente, TransaccionDTO transaccion) {
		return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesPagoMisCuentas(cliente, transaccion));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * ComprobantesPagoMisCuentasBO#obtenerComprobantesPagoMisCuentas(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesPagoMisCuentas(Cliente cliente, TransaccionDTO transaccion) {
		if (cliente.esMonoProductoTarjeta()) {
			return respuestaFactory.crearRespuestaOk(new ComprobantesDTO());
		}
		LOGGER.info(OBTENIENDO_COMPROBANTES);
		ConsultaComprobanteOutEntity outEntity;
		try {
		    ConsultaComprobanteInEntity inEntity = obtenerInEntity(cliente, transaccion.getFechaDesde(), transaccion.getFechaHasta());
		    if (inEntity == null) {
		        LOGGER.error("Sin Tarjeta Banelco asociada a cuentas.");
		        return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
		    }
			outEntity = comprobantesPagoMisCuentasDAO
					.consultar(obtenerInEntity(cliente, transaccion.getFechaDesde(), transaccion.getFechaHasta()));
		} catch (DAOException e) {
			LOGGER.error(ERROR_SERVICIO, e);
			return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
		}
		return obtenerRespuesta(outEntity, transaccion);
	}

	/**
	 * Obtener in entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @return the consulta comprobante in entity
	 */
	private ConsultaComprobanteInEntity obtenerInEntity(Cliente cliente, Date fechaDesde, Date fechaHasta) {
		Cuenta cuentaPMC = obtenerCuentaValidaParaPMC(cliente.getCuentas());
		if (cuentaPMC == null) {
		    return null;
		}
		Cuenta cuentaBanelco = obtenerCuentaBanelco(cliente.getCuentas(), cuentaPMC);
		ConsultaComprobanteInEntity inEntity = new ConsultaComprobanteInEntity();
		inEntity.setCliente(cliente);
		inEntity.setTipoCuenta(StringUtils.leftPad(cuentaPMC.getTipoCuentaEnum().getCodigo().toString(), 2,
				ComprobantesBOEnum.CERO.getId()));
		inEntity.setSucursalCuenta(cuentaPMC.getNroSucursal());
		inEntity.setNroCuenta(cuentaPMC.getNroCuentaProducto());
		inEntity.setNroOrdenFirmante(cuentaPMC.getNroOrdenFirmante());
		setearNroTarjetaBanelco(cuentaBanelco, inEntity);
		setearFechas(inEntity, fechaDesde, fechaHasta);
		return inEntity;
	}

	/**
	 * Obtener cuenta valida para PMC.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaValidaParaPMC(List<Cuenta> cuentas) {
		List<TipoCuenta> tiposCuentaPMC = new ArrayList<TipoCuenta>(Arrays.asList(TipoCuenta.CUENTA_CORRIENTE_PESOS,
				TipoCuenta.CAJA_AHORRO_PESOS, TipoCuenta.CUENTA_CORRIENTE_DOLARES, TipoCuenta.CAJA_AHORRO_PESOS,
				TipoCuenta.CUENTA_UNICA_PESOS, TipoCuenta.CUENTA_UNICA_DOLARES));
		for (Cuenta cuenta : cuentas) {
			if (tiposCuentaPMC.contains(cuenta.getTipoCuentaEnum())) {
			    if(obtenerCuentaBanelco(cuentas, cuenta)!= null) {
			        return cuenta;
			    }
			}
		}
		return null;
	}

	/**
	 * Obtener cuenta banelco.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaBanelco(List<Cuenta> cuentas, Cuenta cuentaPMC) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)
					&& StringUtils.equals(CODIGO_APLICACION, cuenta.getCodigoAplicacion())
					&& cuenta.getContratoAltair().equals(cuentaPMC.getNroCuentaProducto())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Setear nro tarjeta banelco.
	 *
	 * @param cuentaBanelco
	 *            the cuenta banelco
	 * @param inEntity
	 *            the in entity
	 */
	private void setearNroTarjetaBanelco(Cuenta cuentaBanelco, ConsultaComprobanteInEntity inEntity) {
		if (cuentaBanelco != null) {
			inEntity.setNroTarjetaBanelco(cuentaBanelco.getNroTarjetaCredito());
		} else {
			inEntity.setNroTarjetaBanelco(StringUtils.repeat(ComprobantesBOEnum.CERO.getId(), 20));
		}
	}

	/**
	 * Setear fechas.
	 *
	 * @param inEntity
	 *            the in entity
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 */
	private void setearFechas(ConsultaComprobanteInEntity inEntity, Date fechaDesde, Date fechaHasta) {
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_IATX);
		if (fechaDesde == null || fechaHasta == null) {
			Date date = new Date();
			inEntity.setFechaHasta(sdf.format(date));
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt(ComprobantesBOEnum.MENOS.getId() + diasFechaDesde));
			date = cal.getTime();
			inEntity.setFechaDesde(sdf.format(date));
		} else {
			inEntity.setFechaHasta(sdf.format(fechaHasta));
			inEntity.setFechaDesde(sdf.format(fechaDesde));
		}
	}

	/**
	 * Obtener respuesta.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param transaccion
	 *            the transaccion
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> obtenerRespuesta(ConsultaComprobanteOutEntity outEntity,
			TransaccionDTO transaccion) {
		if (!StringUtils.containsOnly(outEntity.getCodigoRetornoExtendido(), "0")) {
			return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
		}
		ComprobantesDTO comprobantesDTO = obtenerComprobantesDTO(outEntity, transaccion);
		if (comprobantesDTO.getTieneError()) {
			return respuestaFactory.crearRespuestaWarning(new ArrayList<DatoItemMensaje>(), comprobantesDTO);
		}
		return respuestaFactory.crearRespuestaOk(comprobantesDTO);
	}

	/**
	 * Obtener comprobantes DTO.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param transaccion
	 *            the transaccion
	 * @return the comprobantes DTO
	 */
	private ComprobantesDTO obtenerComprobantesDTO(ConsultaComprobanteOutEntity outEntity, TransaccionDTO transaccion) {
		List<ComprobanteDTO> listaComprobantes = new LinkedList<ComprobanteDTO>();
		Boolean error = false;
		for (ComprobantePMCEntity comprobanteEntity : outEntity.getDestinatarios()) {
			ComprobanteDTO comprobanteDTO = obtenerComprobanteDTO(comprobanteEntity);
			if (comprobanteDTO.getTieneError()) {
				error = true;
			} else {
				listaComprobantes.add(comprobanteDTO);
			}
		}
		if (transaccion.getImporteDesde() != null && transaccion.getImporteHasta() != null) {
			listaComprobantes = filtrarComprobantesPorImporte(listaComprobantes, transaccion.getImporteDesde(),
					transaccion.getImporteHasta());
		}
		return new ComprobantesDTO(listaComprobantes, error);
	}

	/**
	 * Obtener comprobante DTO.
	 *
	 * @param comprobanteEntity
	 *            the comprobante entity
	 * @return the comprobante DTO
	 */
	private ComprobanteDTO obtenerComprobanteDTO(ComprobantePMCEntity comprobanteEntity) {
		ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
		try {
			comprobanteDTO
					.setFecha(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(comprobanteEntity.getFechaPago()));
		} catch (ParseException e) {
			LOGGER.error("Hubo un error al parsear la fecha del comprobante: {}", comprobanteEntity, e);
			comprobanteDTO.setTieneError(true);
			return comprobanteDTO;
		}
		try {
			setearMoneda(comprobanteDTO, comprobanteEntity);
		} catch (ImporteConvertException e) {
			LOGGER.debug("Excepcion al parsear el importe del comprobante, se continua igual: {}", comprobanteEntity,
					e);
		}
		comprobanteDTO.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_SERVICIOS);
		comprobanteDTO.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_A);
		setearMedioDePago(comprobanteDTO, comprobanteEntity);
		comprobanteDTO.setHoraOperacion(StringUtils.left(comprobanteEntity.getHoraPago(), 2) + ":"
				+ StringUtils.right(comprobanteEntity.getHoraPago(), 2));
		comprobanteDTO.setDestinatario(
				ISBANStringUtils.convertirStringToCamelcase(obtenerDestinatarioPorSubEmpresa(comprobanteEntity)));

		try {
			comprobanteDTO.setDetalle(obtenerDetallePMC(comprobanteEntity));
		} catch (ImporteConvertException e) {
			LOGGER.error("ImporteConvertException: {}", comprobanteEntity, e);
			comprobanteDTO.setTieneError(true);
			return comprobanteDTO;
		} catch (ParseException e) {
			LOGGER.error("Hubo un error al parsear la fecha del comprobante: {}", comprobanteEntity, e);
			comprobanteDTO.setTieneError(true);
			return comprobanteDTO;
		}
		return comprobanteDTO;
	}


	/**
	 * Obtener detalle PMC.
	 *
	 * @param comprobanteEntity
	 *            the comprobante entity
	 * @return the detalle comprobante DTOc
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws ParseException
	 *             the parse exception
	 */
	private DetalleComprobanteDTO obtenerDetallePMC(ComprobantePMCEntity comprobanteEntity)
			throws ImporteConvertException, ParseException {
		DetalleComprobantePagoMisCuentasDTO detalle = new DetalleComprobantePagoMisCuentasDTO();
		detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_PAGO_PUNTUAL);
		detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());
		detalle.setCodigoValidacion(obtenerCodigoValidacion(comprobanteEntity));
		detalle.setEmpresa(
				ISBANStringUtils.convertirStringToCamelcase(obtenerDestinatarioPorSubEmpresa(comprobanteEntity)));
		detalle.setFiid(esSubEmpresa(comprobanteEntity.getEmpresa()));
		detalle.setEsPagoConDeuda(verificarPagoConDeuda(comprobanteEntity.getEmpresa()));
		detalle.setNroControl(comprobanteEntity.getNroControlTicket());
		detalle.setNroComprobante(comprobanteEntity.getNroTransaccion());
		detalle.setHoraDePago(StringUtils.left(comprobanteEntity.getHoraPago(), 2) + ":"
				+ StringUtils.right(comprobanteEntity.getHoraPago(), 2));
		detalle.setIdentificacion(comprobanteEntity.getIdClienteEmpresa());
		detalle.setIdClienteEmpresa(comprobanteEntity.getIdClienteEmpresa());
		if (StringUtils.equals(ComprobantesBOEnum.CERO.getId(), comprobanteEntity.getMoneda())) {
			detalle.setImporte(ISBANStringUtils.convertirStrToBigDecimal(comprobanteEntity.getImporte(), 2));
		}
		detalle.setFechaDePago(
				new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(comprobanteEntity.getFechaPago()));
		if (StringUtils.isNotEmpty(comprobanteEntity.getFechaVencimiento())) {
			detalle.setFechaVencimiento(
					new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(comprobanteEntity.getFechaVencimiento()));
		}
		return detalle;
	}

	/**
	 * Obtener destinatario por sub empresa.
	 *
	 * @param comprobanteEntity
	 *            the comprobante entity
	 * @return the string
	 */
	private String obtenerDestinatarioPorSubEmpresa(ComprobantePMCEntity comprobanteEntity) {
		try {
			String codigoEmpresa = subEmpresa.obtenerSubEmpresa(comprobanteEntity.getEmpresa());
			if (codigoEmpresa != null) {
				return obtenerMedioDePagoPorFIID(codigoEmpresa);
			}
		} catch (DAOException e) {
			LOGGER.error("DAOException: ", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException: ", e);
		}
		return obtenerMedioDePagoPorFIID(comprobanteEntity.getEmpresa());
	}

	/**
	 * retorna el codigo de empresa si el codigo de empresa del comprobante se
	 * encuentra en el archivo de subempresas pessubempresas.txt
	 *
	 * @param empresa
	 *            the comprobante entity
	 * @return the string
	 */
	private String esSubEmpresa(String empresa) {
		try {
			String codigoEmpresa = subEmpresa.obtenerSubEmpresa(empresa);
			if (codigoEmpresa != null) {
				return codigoEmpresa;
			}
		} catch (DAOException e) {
			LOGGER.error("DAOException: ", e);
			return empresa;
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException: ", e);
			return empresa;
		}
		return empresa;
	}
	/**
	 * Setear moneda.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @param comprobanteEntity
	 *            the comprobante entity
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private void setearMoneda(ComprobanteDTO comprobanteDTO, ComprobantePMCEntity comprobanteEntity)
			throws ImporteConvertException {
		if (StringUtils.equals(ComprobantesBOEnum.CERO.getId(), comprobanteEntity.getMoneda())) {
			comprobanteDTO
					.setImportePesos(ISBANStringUtils.convertirStrToBigDecimal(comprobanteEntity.getImporte(), 2));
		} else {
			comprobanteDTO
					.setImporteDolares(ISBANStringUtils.convertirStrToBigDecimal(comprobanteEntity.getImporte(), 2));
		}
	}

	/**
	 * Setear medio de pago.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @param comprobanteEntity
	 *            the comprobante entity
	 */
	private void setearMedioDePago(ComprobanteDTO comprobanteDTO, ComprobantePMCEntity comprobanteEntity) {

		String nroCuenta;
		TipoCuenta tipoCuenta;
		if (TIPO_CUENTA_TARJETA.equals(comprobanteEntity.getTipoCuenta())) {
			tipoCuenta = TIPO_CUENTA_TARJETA_MAP
					.get(StringUtils.substring(comprobanteEntity.getNroCuenta().trim(), -2));
			nroCuenta = new StringBuilder().append(StringUtils.upperCase(tipoCuenta.getAbreviatura())).append(" XXXX-")
					.append(comprobanteEntity.getSucursalCuenta()).toString();
		} else {
			tipoCuenta = TipoCuenta.fromCodigo(comprobanteEntity.getTipoCuenta());
			nroCuenta = new IdentificacionCuenta(StringUtils.right(comprobanteEntity.getSucursalCuenta(), 3),
					comprobanteEntity.getNroCuenta()).toString();
		}

		if (comprobanteDTO.getImportePesos() != null) {
			comprobanteDTO.setTipoCtaMedioDePagoPesos(tipoCuenta);
			comprobanteDTO.setCtaMedioDePagoPesos(nroCuenta);
		} else {
			comprobanteDTO.setTipoCtaMedioDePagoDolares(tipoCuenta);
			comprobanteDTO.setCtaMedioDePagoDolares(nroCuenta);
		}
	}

	/**
	 * Obtener medio de pago por FIID.
	 *
	 * @param empresa
	 *            the empresa
	 * @return the string
	 */
	private String obtenerMedioDePagoPorFIID(String empresa) {
		Respuesta<MedioPago> medioPago = medioPagoBO.getByCodigo(empresa);
		if (!medioPago.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return empresa;
		}
		return medioPago.getRespuesta().getNombreFantasia();
	}

	/**
	 * Setear codigo validacion.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @return the string
	 */
	private String obtenerCodigoValidacion(ComprobantePMCEntity comprobante) {
		StringBuilder sb = new StringBuilder();
		sb.append(comprobante.getIdClienteEmpresa());
		sb.append(comprobante.getFechaPago());
		sb.append(comprobante.getHoraPago());
		if (StringUtils.equals(ComprobantesBOEnum.CERO.getId(), comprobante.getMoneda())) {
			sb.append(ComprobantesBOEnum.MONEDA_ARG.getId());
		} else {
			sb.append(ComprobantesBOEnum.MONEDA_USD.getId());
		}
		sb.append(comprobante.getImporte());
		sb.append(comprobante.getNroControlTicket());
		sb.append(comprobante.getNroTransaccion().trim());
		return sb.toString();
	}

	private boolean verificarPagoConDeuda(String empresa) {
		Respuesta<MedioPago> medioPago = medioPagoBO.getByCodigo(empresa);
		if (medioPago != null && medioPago.getRespuesta() != null && !"SUSS".equals(medioPago.getRespuesta().getFiid())
				&& TIPO_PAGO_CON_DEUDA.equals(medioPago.getRespuesta().getTipoPago())
				&& !"F".equals(medioPago.getRespuesta().getTipoEmpresa())) {
			return true;
		}
		return false;
	}


}
