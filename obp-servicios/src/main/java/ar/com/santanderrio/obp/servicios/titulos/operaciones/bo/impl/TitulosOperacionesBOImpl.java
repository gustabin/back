/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesTextRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

/**
 * The Class TitulosOperacionesBOImpl.
 */
@Component
public class TitulosOperacionesBOImpl extends InversionesAbstractBO implements TitulosOperacionesBO {

	/** The Constant FORMATO_FECHA_JAPON_GUIONES. */
	private static final String FORMATO_FECHA_JAPON_GUIONES = "yyyy-MM-dd";

	/** The Constant FORMATO_FECHA_JAPON. */
	private static final String FORMATO_FECHA_JAPON = "yyyy/MM/dd";

	/** The Constant FORMATO_FECHA_COMUN. */
	private static final String FORMATO_FECHA_COMUN = "dd/MM/yyyy";

	/** The ordenes titulos DAO. */
	@Autowired
	private OrdenesTitulosDAO ordenesTitulosDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The titulos BO. */
	@Autowired
	private TitulosBO titulosBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TitulosOperacionesBOImpl.class);

	/** The dato firmado tipo. */
	@Value("${INVERSIONES.LICITACIONES.DATOFIRMADO}")
	private String dato;

	/** The datoMercadoCanal firmado tipo. */
	@Value("${MERCADOSCANAL.LICITACIONES.DATOFIRMADO}")
	private String datoMercadoCanal;

	/** The Constant LIMPIANDO_CACHE. */
	private static final String LIMPIANDO_CACHE = "Limpiando cache {}.";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO
	 * #limpiarCache(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_OPERACIONES_COMPRA_VENTA, key = "#cliente.nup")
	@Override
	public void limpiarCache(Cliente cliente) {
		LOGGER.info(LIMPIANDO_CACHE, CacheConstants.CACHE_OPERACIONES_COMPRA_VENTA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO
	 * #obtenerOperacionesCompraVenta(ar.com.santanderrio.obp.servicios.titulos.
	 * operaciones.view.ParametrosOperacionesView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Cacheable(value = CacheConstants.Names.CACHE_OPERACIONES_COMPRA_VENTA, key = "#cliente.nup")
	@Override
	public Respuesta<List<OperacionTitulosDTO>> obtenerOperacionesCompraVenta(
			ParametrosOperacionesView parametrosOperacionesView, Cliente cliente) {

		List<OperacionTitulosDTO> listaOperaciones = null;
		RequestConsultarOperacionesTextEntity requestConsultaOperacionesEntity = null;
		try {
			requestConsultaOperacionesEntity = crearRequestConsultaOperacionesEntity(parametrosOperacionesView,
					cliente);
		} catch (BusinessException ex) {
			LOGGER.error("Error al consultar el servicio", ex);
		}
		ConsultarOperacionesTextResponse response = null;
		try {
			response = ordenesTitulosDAO.consultarOperacionesText(requestConsultaOperacionesEntity);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar el servicio", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		listaOperaciones = mapearResponseCompraVentaListaDTO(response);
		Collections.sort(listaOperaciones, new OperacionDTOFechaComparator());

		return respuestaFactory.crearRespuestaOk(listaOperaciones);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO
	 * #obtenerOperacionesLicitacion(ar.com.santanderrio.obp.servicios.titulos.
	 * operaciones.view.ParametrosOperacionesView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Cacheable(value = CacheConstants.Names.CACHE_OPERACIONES_LICITACION, key = "#cliente.nup")
	@Override
	public Respuesta<List<OperacionTitulosDTO>> obtenerOperacionesLicitacion(
			ParametrosOperacionesView parametrosOperacionesView, Cliente cliente) {

		List<OperacionTitulosDTO> listaOperaciones = null;
		Respuesta<ConsultarOrdenesOutDTO> respuestaConsultarOrdenesDTOOut = titulosBO.consultarLicitaciones(cliente,
				TipoBancaEnum.fromCodigoString(parametrosOperacionesView.getBanca()), true);
		if (EstadoRespuesta.ERROR.equals(respuestaConsultarOrdenesDTOOut.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		listaOperaciones = mapearResponseLicitacionesListaDTO(respuestaConsultarOrdenesDTOOut.getRespuesta());
		Collections.sort(listaOperaciones, new OperacionDTOFechaComparator());

		return respuestaFactory.crearRespuestaOk(listaOperaciones);
	}

	/**
	 * Mapear response licitaciones lista DTO.
	 *
	 * @param consultarOrdenesOutDTO the consultar ordenes out DTO
	 * @return the list
	 */
	private List<OperacionTitulosDTO> mapearResponseLicitacionesListaDTO(
			ConsultarOrdenesOutDTO consultarOrdenesOutDTO) {
		List<OperacionTitulosDTO> listaADevolver = new ArrayList<OperacionTitulosDTO>();
		List<ConsultarOrdenesDTO> listaResponseDAO = consultarOrdenesOutDTO.getList();
		for (ConsultarOrdenesDTO consultarOrdenesDTO : listaResponseDAO) {
			OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
			if ("CANJES".equalsIgnoreCase(consultarOrdenesDTO.getTipoPliego())) {
				mapearLicitacionCanje(consultarOrdenesDTO, operacionTitulosDTO);
			} else {
				mapearLicitacion(consultarOrdenesDTO, operacionTitulosDTO);
			}
			listaADevolver.add(operacionTitulosDTO);
		}
		return listaADevolver;
	}

	private void mapearLicitacion(ConsultarOrdenesDTO consultarOrdenesDTO, OperacionTitulosDTO operacionTitulosDTO) {
		// Estas cantidades parecen seteadas al revés, pero es correcto
		// Están así porque en la grilla se usa un campo para mostrar los registros
		// mientras que en el detalle, se muestran como deberían
		String cantidadAdjudicada = "";
		if (consultarOrdenesDTO.getCantidadAdjudicada() != null) {
			cantidadAdjudicada = consultarOrdenesDTO.getCantidadAdjudicada();
		}
		operacionTitulosDTO.setCantidadNominales(Double.valueOf(cantidadAdjudicada));
		operacionTitulosDTO.setCantidad(consultarOrdenesDTO.getCantidadNominal());
		operacionTitulosDTO.setCuenta(consultarOrdenesDTO.getCuentaOperativa());
		operacionTitulosDTO.setDescripcion(consultarOrdenesDTO.getDescripcion());
		SimpleDateFormat df = new SimpleDateFormat(FORMATO_FECHA_COMUN);
		Date fechaOrden;
		try {
			fechaOrden = df.parse(consultarOrdenesDTO.getFecha());
		} catch (ParseException e) {
			fechaOrden = null;
		}
		operacionTitulosDTO.setFecha(fechaOrden);
		operacionTitulosDTO.setMoneda(consultarOrdenesDTO.getMoneda());
		String precioAdjudicado = null;
		String precio = null;
		if (consultarOrdenesDTO.getPrecioAdjudicada() != null) {
			if (StringUtils.isNumeric(consultarOrdenesDTO.getPrecioAdjudicada().replace("$", "").replace("U$D", "")
					.replace("%", "").trim())) {
				precio = consultarOrdenesDTO.getPrecioAdjudicada().replace("$", "").replace("U$D", "").replace("%", "")
						.trim();
				operacionTitulosDTO.setPrecio(Double.valueOf(precio));
			} else {
				operacionTitulosDTO.setPrecioString(consultarOrdenesDTO.getPrecioAdjudicada());
			}
		}
		if (consultarOrdenesDTO.getPrecio() != null) {
			if (StringUtils.isNumeric(
					consultarOrdenesDTO.getPrecio().replace("$", "").replace("U$D", "").replace("%", "").trim())) {
				precioAdjudicado = consultarOrdenesDTO.getPrecio().replace("$", "").replace("U$D", "").replace("%", "")
						.trim();
				operacionTitulosDTO.setPrecioAdjudicado(Double.valueOf(precioAdjudicado));
			} else {
				operacionTitulosDTO.setPrecioAdjudicadoString(consultarOrdenesDTO.getPrecio());
			}
		}
		operacionTitulosDTO.setTipo(consultarOrdenesDTO.getDescInstrumento());
		operacionTitulosDTO.setTipoCodigo(consultarOrdenesDTO.getCodInstrumento());
		operacionTitulosDTO.setTipoOperacion("L");
		operacionTitulosDTO.setNumeroOperacion(Long.valueOf(consultarOrdenesDTO.getNumeroOrden()));
		operacionTitulosDTO.setCodigoTipoEspecie(consultarOrdenesDTO.getCodInstrumento());
		operacionTitulosDTO.setFechaDebito(consultarOrdenesDTO.getFechaDebProv());
		operacionTitulosDTO.setFechaCierre(consultarOrdenesDTO.getFechaCierre());
		operacionTitulosDTO.setFechaAdjudicacion(consultarOrdenesDTO.getFechaAdjudicacion());
		operacionTitulosDTO.setFechaLiquidacion(consultarOrdenesDTO.getFechaLiquidacion());
		operacionTitulosDTO.setTramo(consultarOrdenesDTO.getTramo());
		operacionTitulosDTO.setTipoPrecio(consultarOrdenesDTO.getTipoPrecioLabel());
		operacionTitulosDTO.setImporteDebitar(consultarOrdenesDTO.getImporteDebitar());
		operacionTitulosDTO.setImpuestos(consultarOrdenesDTO.getImpuesto());
		operacionTitulosDTO.setComisiones(consultarOrdenesDTO.getComision());
		operacionTitulosDTO.setCuentaDestino(consultarOrdenesDTO.getCuentaDebito());
		operacionTitulosDTO.setTipoCuentaDestino(consultarOrdenesDTO.getTipoCuentaDebito());
		operacionTitulosDTO.setCuentaTitulos(consultarOrdenesDTO.getCuentaTitulo());
		operacionTitulosDTO.setEstado(consultarOrdenesDTO.getEstado());
		operacionTitulosDTO.setMonedaEspecie(consultarOrdenesDTO.getMonedaEspecie());
		operacionTitulosDTO.setLegal(consultarOrdenesDTO.getLegalPliegue());
	}

	private void mapearLicitacionCanje(ConsultarOrdenesDTO consultarOrdenesDTO,
			OperacionTitulosDTO operacionTitulosDTO) {
		operacionTitulosDTO.setDescripcion(consultarOrdenesDTO.getDescripcion());
		SimpleDateFormat df = new SimpleDateFormat(FORMATO_FECHA_COMUN);
		Date fechaOrden;
		try {
			fechaOrden = df.parse(consultarOrdenesDTO.getFecha());
		} catch (ParseException e) {
			fechaOrden = null;
		}
		operacionTitulosDTO.setFecha(fechaOrden);
		operacionTitulosDTO.setNumeroOperacion(Long.valueOf(consultarOrdenesDTO.getNumeroOrden()));
		operacionTitulosDTO.setEstado(consultarOrdenesDTO.getEstado());
		operacionTitulosDTO.setTipoCodigo(consultarOrdenesDTO.getCodInstrumento());
		operacionTitulosDTO.setMonedaEspecie(consultarOrdenesDTO.getMonedaEspecie());
		operacionTitulosDTO.setEspecieDestino(consultarOrdenesDTO.getEspecieDestino());
		operacionTitulosDTO.setMonedaEspecieDestino(consultarOrdenesDTO.getMonedaEspecieDestino());
		operacionTitulosDTO.setCantidad(consultarOrdenesDTO.getCantidadNominal());
		operacionTitulosDTO.setTramo(consultarOrdenesDTO.getTramo());
		operacionTitulosDTO.setTipoPrecio(consultarOrdenesDTO.getTipoPrecioLabel());
		operacionTitulosDTO.setTipoPrecioDatoCanje(consultarOrdenesDTO.getPrecio());
		operacionTitulosDTO.setImporteDebitar(consultarOrdenesDTO.getImporteDebitar());
		operacionTitulosDTO.setCuentaTitulos(consultarOrdenesDTO.getCuentaTitulo());
		operacionTitulosDTO.setCuentaDestino(consultarOrdenesDTO.getCuentaDebito());
		operacionTitulosDTO.setTipoCuentaDestino(consultarOrdenesDTO.getTipoCuentaDebito());
		operacionTitulosDTO.setCantidadNominales(consultarOrdenesDTO.getCantidadAdjudicada() == null ? null
				: Double.valueOf(consultarOrdenesDTO.getCantidadAdjudicada()));
		operacionTitulosDTO.setFechaDebito(consultarOrdenesDTO.getFechaDebProv());
		operacionTitulosDTO.setFechaCierre(consultarOrdenesDTO.getFechaCierre());
		operacionTitulosDTO.setFechaAdjudicacion(consultarOrdenesDTO.getFechaAdjudicacion());
		operacionTitulosDTO.setFechaLiquidacion(consultarOrdenesDTO.getFechaLiquidacion());
		operacionTitulosDTO.setComisiones(consultarOrdenesDTO.getComision());
		operacionTitulosDTO.setImpuestos(consultarOrdenesDTO.getImpuesto());
		operacionTitulosDTO.setLegal(consultarOrdenesDTO.getLegalPliegue());
		operacionTitulosDTO.setTipoOperacion("L");
		operacionTitulosDTO.setMoneda(consultarOrdenesDTO.getMoneda());
		operacionTitulosDTO.setPrecioAdjudicadoString(consultarOrdenesDTO.getPrecioAdjudicada());
		operacionTitulosDTO.setTipoPliego(consultarOrdenesDTO.getTipoPliego());
		operacionTitulosDTO.setCuenta(consultarOrdenesDTO.getCuentaOperativa());
	}

	/**
	 * Mapear response compra venta lista DTO.
	 *
	 * @param response the response
	 * @return the list
	 */
	private List<OperacionTitulosDTO> mapearResponseCompraVentaListaDTO(ConsultarOperacionesTextResponse response) {

		DatosConsultarOperacionesTextResponse datos = response.getDatos();
		List<OperacionesTextResponse> listaResponse = null;
		if (datos != null) {
			listaResponse = datos.getListaOperacionesText();
		} else {
			return new ArrayList<OperacionTitulosDTO>();
		}
		List<OperacionTitulosDTO> listaOperacionesTitulosDTO = new ArrayList<OperacionTitulosDTO>();

		for (OperacionesTextResponse operacionesTextResponse : listaResponse) {
			OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
			operacionTitulosDTO.setEstado(operacionesTextResponse.getEstado());
			operacionTitulosDTO.setMonedaOperacion(operacionesTextResponse.getCodigo());
			operacionTitulosDTO.setPlazoLiquidacion(operacionesTextResponse.getPlazo());
			operacionTitulosDTO.setMercadoDescripcion(operacionesTextResponse.getMercadoDescripcion());
			operacionTitulosDTO.setPrecioLimite(operacionesTextResponse.getPrecioLimite());
			operacionTitulosDTO.setPrecioReferencia(operacionesTextResponse.getPrecioReferencia());
			operacionTitulosDTO.setImporte(Double.parseDouble(operacionesTextResponse.getNeto()));
			operacionTitulosDTO.setCuentaTitulos(operacionesTextResponse.getCuentaTitulos());
			operacionTitulosDTO.setSucursalCuentaDestino(operacionesTextResponse.getSucCtaOper());
			operacionTitulosDTO.setCuentaDestino(operacionesTextResponse.getNroCtaOper());
			operacionTitulosDTO.setTipoCuentaDestino(operacionesTextResponse.getTipoCtaOper());
			operacionTitulosDTO.setComisiones(operacionesTextResponse.getComision());
			operacionTitulosDTO.setImpuestos(operacionesTextResponse.getImpuesto());
			operacionTitulosDTO.setCanalIngreso(operacionesTextResponse.getCanal());
			operacionTitulosDTO.setCantidadNominales(operacionesTextResponse.getCantidad());
			operacionTitulosDTO.setDescripcion(operacionesTextResponse.getEspecie());
			operacionTitulosDTO.setAbreviaturaCajaValores(operacionesTextResponse.getAbreviaturaCajaValores());
			SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA_JAPON_GUIONES);
			Date parsedDate;
			try {
				parsedDate = formatter.parse(operacionesTextResponse.getFechaOrden());
			} catch (ParseException e) {
				parsedDate = null;
			}
			operacionTitulosDTO.setFecha(parsedDate);
			operacionTitulosDTO.setMoneda(operacionesTextResponse.getMoneLiq());
			operacionTitulosDTO.setNumero(operacionesTextResponse.getNumOrdenOrigen());
			operacionTitulosDTO.setNumeroOperacion(operacionesTextResponse.getOrdenId());
			operacionTitulosDTO.setPrecio(operacionesTextResponse.getPrecio());
			operacionTitulosDTO.setTipo(operacionesTextResponse.getInstrumento());
			operacionTitulosDTO.setTipoCodigo(operacionesTextResponse.getInstrumentoCodigo());
			operacionTitulosDTO.setTipoOperacion(operacionesTextResponse.getTipoOperacion());
			operacionTitulosDTO.setCuenta(operacionesTextResponse.getNroCtaOper());
			operacionTitulosDTO.setCodigoTipoEspecie(operacionesTextResponse.getInstrumentoCodigo());
			operacionTitulosDTO.setLegal(operacionesTextResponse.getLeyenda());
			operacionTitulosDTO.setDerechos(operacionesTextResponse.getDerechos());
			listaOperacionesTitulosDTO.add(operacionTitulosDTO);
		}

		return listaOperacionesTitulosDTO;
	}

	/**
	 * Crear request consulta operaciones entity.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @param cliente                   the cliente
	 * @return the request consultar operaciones text entity
	 * @throws BusinessException the business exception
	 */
	private RequestConsultarOperacionesTextEntity crearRequestConsultaOperacionesEntity(
			ParametrosOperacionesView parametrosOperacionesView, Cliente cliente) throws BusinessException {

		RequestConsultarOperacionesTextEntity requestConsultarOperacionesTextEntity = new RequestConsultarOperacionesTextEntity();
		DatosConsultaOperacionesTextRequestEntity datosRequest = requestConsultarOperacionesTextEntity.getDatos();
		Boolean esBancaPrivada = "BP".equals(parametrosOperacionesView.getBanca());
		setearCanalesRequestMWCanales(requestConsultarOperacionesTextEntity, esBancaPrivada);
		setearDatosRequestMWCanales(datosRequest, cliente, esBancaPrivada);
		if ("BP".equals(parametrosOperacionesView.getBanca())) {
			datosRequest.setCanal(canalOrdenesBPriv);
			datosRequest.setSubCanal(subCanalOrdenesBPriv);
			datosRequest.setCuentasTitulo(
					obtenerCuentaTituloDesdeOperativa(parametrosOperacionesView.getNumeroCuenta(), cliente));
			datosRequest.setSegmento(TipoBancaEnum.BANCA_PRIVADA.getCodigo());
		} else {
			datosRequest.setCanal(canalOrdenesRTL);
			datosRequest.setSubCanal(subCanalOrdenesRTL);
			datosRequest.setCuentasTitulo(parametrosOperacionesView.getNumeroCuenta().replace("/", ""));
			datosRequest.setSegmento("RTL");
		}
		requestConsultarOperacionesTextEntity.setDato("OBP");
		requestConsultarOperacionesTextEntity.setDatosFirmados(0);
		requestConsultarOperacionesTextEntity.setTipoHash("0");
		requestConsultarOperacionesTextEntity.setFirma(generarFirma(datoMercadoCanal));

		Calendar calendario = Calendar.getInstance();
		Date fechaHasta = calendario.getTime();
		calendario.add(Calendar.DATE, -30);
		Date fechaDesde = calendario.getTime();
		SimpleDateFormat dfEnviado = new SimpleDateFormat(FORMATO_FECHA_JAPON);
		SimpleDateFormat dfRecibido = new SimpleDateFormat(FORMATO_FECHA_COMUN);
		if (parametrosOperacionesView.getFechaDesde() != null) {
			try {
				fechaDesde = dfRecibido.parse(parametrosOperacionesView.getFechaDesde());
			} catch (ParseException e) {
			}
		}
		if (parametrosOperacionesView.getFechaHasta() != null) {
			try {
				fechaHasta = dfRecibido.parse(parametrosOperacionesView.getFechaHasta());
			} catch (ParseException e) {
			}
		}
		datosRequest.setFechaDesde(dfEnviado.format(fechaDesde));
		datosRequest.setFechaHasta(dfEnviado.format(fechaHasta));
		datosRequest.setNup(cliente.getNup());
		datosRequest.setCno("");

		return requestConsultarOperacionesTextEntity;
	}

	/**
	 * Obtener cuenta titulo desde operativa.
	 *
	 * @param numeroCuenta the numero cuenta
	 * @param cliente      the cliente
	 * @return the string
	 */
	private String obtenerCuentaTituloDesdeOperativa(String numeroCuenta, Cliente cliente) {
		String numeroCuentaSinFormato = new IdentificacionCuenta(numeroCuenta).getNroCuentaProducto();
		List<CuentaBancaPrivada> cuentasPrivadas = cliente.getCuentasBancaPrivada();
		String cuentaTitulo = null;
		for (CuentaBancaPrivada cuentaBancaPrivada : cuentasPrivadas) {
			if (cuentaBancaPrivada.getCuentaOperativa().getNroCuentaProducto().contains(numeroCuentaSinFormato)) {
				cuentaTitulo = cuentaBancaPrivada.getCuentaTitulo().getNroCuentaProducto();
			}
		}
		return cuentaTitulo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO#
	 * getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	private int obtenerDecimalesValoresCanje(BigDecimal valor) {
		String[] saldoSeparado = String.valueOf(valor).split("\\.");
		if (saldoSeparado.length == 1) {
			return 2;
		}

		if (saldoSeparado[1].length() > 2 && saldoSeparado[1].length() <= 7) {
			return saldoSeparado[1].length();
		}

		if (saldoSeparado[1].length() > 7) {
			return 7;
		}

		return 2;
	}
}
