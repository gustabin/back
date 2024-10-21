/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenAnterior;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAnteriorBOImpl.
 *
 * @author
 */
@Component("resumenAnteriorBO")
public class ResumenAnteriorBOImpl extends TarjetasBOImpl implements ResumenAnteriorBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenAnteriorBOImpl.class);

	/** The Constant SIN_RESUMENES_ANTERIORES. */
	public static final String SIN_RESUMENES_ANTERIORES = "sinResumenesAnteriores";

	/** The Constant SIN_RESUMEN_ACTUAL. */
	public static final String SIN_RESUMEN_ACTUAL = "sinResumenActual";

	/** The Constant ERROR_CARGA_RESUMENES_ANTERIORES. */
	public static final String ERROR_CARGA_RESUMENES_ANTERIORES = "errorCargaCuotasPendiente";

	/** The Constant TIPO_ERROR_RESUMENES_ANTERIORES. */
	public static final String TIPO_ERROR_CARGA_RESUMENES_ANTERIORES = "ERROR_CARGA_RESUMENES_ANTERIORES";

	/** The Constant TIPO_ERROR_RESUMENES_ANTERIORES. */
	public static final String TIPO_ERROR_SIN_RESUMENES_ANTERIORES = "ERROR_SIN_RESUMENES_ANTERIORES";

	/** The ondemand DAO. */
	@Autowired
	private OndemandDAO ondemandDAO;

	/** The resumenes citi DAO. */
	@Autowired
	private ResumenesCitiDAO resumenesCitiDAO;

	/** The resumen mensual cuenta BO impl. */
	@Autowired
	private ResumenMensualCuentaBO resumenMensualCuentaBOImpl;

	/** The cantidad meses. */
	@Value("${RESUMENONDEMANDTRJ.MESES}")
	private String cantidadMeses;

	/** The cantidad dias fecha hasta. */
	@Value("${RESUMENONDEMANDTRJ.DIAS.HASTA}")
	private String cantidadDiasFechaHasta;

	/** The sesion tarjetas. */
	@Autowired
	private SesionTarjetas sesionTarjetas;

	/** The mensaje DAO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yy";

	/** The Constant CERO_ENTERO. */
	public static final String CERO_ENTERO = "0";

	/** The Constant DIEZ_ENTERO. */
	public static final Integer DIEZ_ENTERO = 10;

	/** The Constant CUATRO_ENTERO. */
	public static final Integer CUATRO_ENTERO = 4;

	// /**
	// * Obtener resumenes anteriores.
	// *
	// * @param identificacionCuenta
	// * the identificacion cuenta
	// * @param cliente
	// * the cliente
	// * @return the respuesta
	// * @throws BusinessException
	// * the business exception
	// */
	// @Override
	// public Respuesta<ResumenAnteriorViewResponse>
	// obtenerResumenesAnteriores(IdentificacionCuenta identificacionCuenta,
	// Cliente cliente) throws BusinessException {
	// final Format formatter = new SimpleDateFormat("dd/MM/yy");
	// List<ReporteSeleccionado> listaReporteSeleccionado = new
	// ArrayList<ReporteSeleccionado>();
	// try {
	// Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente,
	// identificacionCuenta);
	// String marca = TarjetaUtils.getMarca(cuenta);
	// List<ResumenMensualTarjetaDTO> resumenAnteriorDTO =
	// obtenerDatosResumenMensualTarjeta(cuenta, marca);
	// for (int i = 0; i < resumenAnteriorDTO.size(); i++) {
	// ReporteSeleccionado reporteSeleccionado = new ReporteSeleccionado();
	// reporteSeleccionado.setFechaPuntual(formatter.format(resumenAnteriorDTO.get(i).getFecha()));
	// reporteSeleccionado.setFolder(resumenAnteriorDTO.get(i).getCarpeta());
	// reporteSeleccionado.setId(i);
	// reporteSeleccionado.setFolder(resumenAnteriorDTO.get(i).getCarpeta());
	// reporteSeleccionado.setNumeroCuenta(cuenta.getNroCuentaProducto());
	// reporteSeleccionado.setProveedorTarjeta(marca);
	// listaReporteSeleccionado.add(reporteSeleccionado);
	// }
	// Collections.sort(listaReporteSeleccionado, new
	// Comparator<ReporteSeleccionado>() {
	// @Override
	// public int compare(ReporteSeleccionado o1, ReporteSeleccionado o2) {
	// return
	// formatter.format(o1.getFechaPuntual()).compareTo(formatter.format(o2.getFechaPuntual()));
	// }
	//
	//
	// });
	// sesionTarjetas.setReportesSelccionados(listaReporteSeleccionado);
	// return crearRespuestaOk(obtenerResumenAnteriorViewResponse(cuenta, marca,
	// resumenAnteriorDTO));
	// } catch (OnDemandDAOException e) {
	// LOGGER.error("Error al llamar al DAO. Armando respuesta error.", e);
	// return crearRespuestaErroneaCargaResumenesAnteriores();
	// } catch (Exception e) {
	// LOGGER.error("Error en BO.", e.getMessage());
	// throw new BusinessException(e);
	// }
	// }

	/**
	 * Obtener resumenes anteriores.
	 *
	 * @param identificacionCuenta
	 *            the identificacion cuenta
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@Override
	public Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(IdentificacionCuenta identificacionCuenta,
			Cliente cliente, String numeroCuenta) throws BusinessException {
		Format formatter = new SimpleDateFormat("dd/MM/yy");
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			String marca = TarjetaUtils.getMarcaAux(cuenta);
			Respuesta<List<ResumenMensualTarjetaDTO>> respuestaResumenAnteriorDTO = obtenerDatosResumenMensualTarjeta(
					cuenta, marca);
			List<ResumenMensualTarjetaDTO> resumenAnteriorDTO = obtenerListaDeResumenesOrdenada(
					respuestaResumenAnteriorDTO.getRespuesta());
			if (resumenAnteriorDTO.isEmpty()) {
				return crearRespuestaErroneaSinResumenesAnteriores(cuenta);
			}
			sesionTarjetas.setReportesSelccionados(
					generarListaReporteSeleccionado(resumenAnteriorDTO, cuenta, marca, formatter));
			if (respuestaResumenAnteriorDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return crearRespuestaOk(
						obtenerResumenAnteriorViewResponse(cuenta, marca, resumenAnteriorDTO, numeroCuenta));
			} else {
				ItemMensajeRespuesta item = respuestaResumenAnteriorDTO.getItemsMensajeRespuesta().get(0);
				List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item));
				ResumenAnteriorViewResponse responseView = obtenerResumenAnteriorViewResponse(cuenta, marca,
						resumenAnteriorDTO, numeroCuenta);
				return this.getRespuestaFactory().crearRespuestaWarning(responseView, items);
			}
		} catch (OnDemandDAOException e) {
			LOGGER.error("Error al llamar al DAO. Armando respuesta error.", e);
			return crearRespuestaErroneaCargaResumenesAnteriores();
		} catch (Exception e) {
			LOGGER.error("Error en BO.", e);
			throw new BusinessException(e);
		}
	}

	/**
	 * Obtener lista de resumenes ordenada.
	 *
	 * @param listaResumenes
	 *            the lista resumenes
	 * @return the list
	 */
	private List<ResumenMensualTarjetaDTO> obtenerListaDeResumenesOrdenada(
			List<ResumenMensualTarjetaDTO> listaResumenes) {
		Collections.sort(listaResumenes, new Comparator<ResumenMensualTarjetaDTO>() {
			@Override
			public int compare(ResumenMensualTarjetaDTO o1, ResumenMensualTarjetaDTO o2) {
				return (o2.getFecha()).compareTo(o1.getFecha());
			}
		});
		return listaResumenes;
	}

	/**
	 * Generar lista reporte seleccionado.
	 *
	 * @param resumenAnteriorDTO
	 *            the resumen anterior DTO
	 * @param cuenta
	 *            the cuenta
	 * @param marca
	 *            the marca
	 * @param formatter
	 *            the formatter
	 * @return the list
	 */
	private List<ReporteSeleccionado> generarListaReporteSeleccionado(List<ResumenMensualTarjetaDTO> resumenAnteriorDTO,
			Cuenta cuenta, String marca, Format formatter) {
		List<ReporteSeleccionado> listaReporteSeleccionado = new ArrayList<ReporteSeleccionado>();
		for (int i = 0; i < resumenAnteriorDTO.size(); i++) {
			ReporteSeleccionado reporteSeleccionado = new ReporteSeleccionado();
			reporteSeleccionado.setFechaPuntual(formatter.format(resumenAnteriorDTO.get(i).getFecha()));
			reporteSeleccionado.setFolder(resumenAnteriorDTO.get(i).getCarpeta());
			reporteSeleccionado.setDocId(resumenAnteriorDTO.get(i).getDocId());
			reporteSeleccionado.setId(i);
			reporteSeleccionado.setFolder(resumenAnteriorDTO.get(i).getCarpeta());
			reporteSeleccionado.setNumeroCuenta(cuenta.getNroCuentaProducto());
			reporteSeleccionado.setProveedorTarjeta(marca);
			listaReporteSeleccionado.add(reporteSeleccionado);
		}
		return listaReporteSeleccionado;
	}

	/**
	 * Obtener datos resumen mensual tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param marca
	 *            the marca
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 */
	private Respuesta<List<ResumenMensualTarjetaDTO>> obtenerDatosResumenMensualTarjeta(Cuenta cuenta, String marca)
			throws BusinessException, OnDemandDAOException {

		ResumenParams params = configurarParamentrosConsultaLista(cuenta, marca);
		Respuesta<List<ResumenMensualTarjetaDTO>> listaDeResumenes = new Respuesta<List<ResumenMensualTarjetaDTO>>();
		List<ItemMensajeRespuesta> itemsMensajes = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		int erroresEnLlamadas = 0;
		if (TarjetaUtils.MARCA_MASTERCARD_AUX.equals(marca) || TarjetaUtils.MARCA_VISA.equals(marca)) {
			List<ResumenMensualTarjetaDTO> resumenMensualTarjetaList = new ArrayList<ResumenMensualTarjetaDTO>();
			try {
				FechasResumenCitiIn fechasResumenCitiIn = new FechasResumenCitiIn();

				String formatearNumeroTarjeta = cuenta.getNroTarjetaCredito().substring(CUATRO_ENTERO);
				fechasResumenCitiIn.setTarjeta(formatearNumeroTarjeta);
				fechasResumenCitiIn.setDocumento(cuenta.getCliente().getDni());
				fechasResumenCitiIn.setFechaCierreDesde(params.getFechaDesde());
				fechasResumenCitiIn.setFechaCierreHaste(params.getFechaActual());
				fechasResumenCitiIn.setCuenta(cuenta.getNroCuentaProducto());

				resumenMensualTarjetaList.addAll(resumenesCitiDAO.consultarFechasTarjetas(fechasResumenCitiIn, marca));
			} catch (DAOException e) {
				erroresEnLlamadas = erroresEnLlamadas + 1;
			}

			try {
				resumenMensualTarjetaList.addAll(ondemandDAO.obtenerListaResumenesAnterioresTarjeta(params));
			} catch (Exception e) {
				erroresEnLlamadas = erroresEnLlamadas + 1;
			}
			if (erroresEnLlamadas == 0) {
				listaDeResumenes.setRespuesta(resumenMensualTarjetaList);
				listaDeResumenes.setEstadoRespuesta(EstadoRespuesta.OK);
				return listaDeResumenes;
			} else if (erroresEnLlamadas == 1) {
				listaDeResumenes.setRespuesta(resumenMensualTarjetaList);
				listaDeResumenes.setEstadoRespuesta(EstadoRespuesta.WARNING);
				String mensajeError = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PARCIAL_LLAMADA_SERVICIO_RESUMEN)
						.getMensaje();
				item.setMensaje(mensajeError);
				item.setTipoError(TipoError.ERROR_PARCIAL_LLAMADA_SERVICIO_RESUMEN.getDescripcion());
				itemsMensajes.add(item);
				listaDeResumenes.setItemMensajeRespuesta(itemsMensajes);
				return listaDeResumenes;
			} else {
				throw new BusinessException();
			}

		} else {
			List<ResumenMensualTarjetaDTO> resumenMensualTarjetaList = null;
			try {
				resumenMensualTarjetaList = ondemandDAO.obtenerListaResumenesAnterioresTarjeta(params);
			} catch (WSODException e) {
				throw new BusinessException(e);
			}
			listaDeResumenes.setRespuesta(resumenMensualTarjetaList);
			listaDeResumenes.setEstadoRespuesta(EstadoRespuesta.OK);
			return listaDeResumenes;
		}
	}

	/**
	 * Configurar paramentros consulta lista.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param marca
	 *            the marca
	 * @return the resumen params
	 */
	private ResumenParams configurarParamentrosConsultaLista(Cuenta cuenta, String marca) {
		ResumenParams params = new ResumenParams();
		params.setUsuarioConsulta(cuenta.getCliente().getNup());

		params.setProveedorTarjeta(marca);

		Cuenta cuentaAUX = new Cuenta();
		cuentaAUX.setCbu(cuenta.getCbu());
		cuentaAUX.setTipoCuentaEnum(cuenta.getTipoCuentaEnum());
		cuentaAUX.setCliente(cuenta.getCliente());
		cuentaAUX.setNroCuentaProducto(cuenta.getNroCuentaProducto());
		cuentaAUX.setNroSucursal(StringUtils.substring(cuenta.getNroSucursal(), -3));

		if (!TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum())) {
			String nroCuentaConcat = StringUtils.stripStart(concatenarCBUNroCuentaProd(cuentaAUX),
					ISBANStringUtils.ZERO_STR);
			cuentaAUX.setNroCuentaProducto(nroCuentaConcat);
		}

		params.setCuenta(cuentaAUX);

		Date fechaActual = new Date();
		Date fechaHasta = sumarDias(fechaActual, Integer.parseInt(cantidadDiasFechaHasta));
		Date fechaDesde = restarMeses(fechaActual, Integer.parseInt(cantidadMeses));
		if (validarFechas(fechaDesde, fechaHasta)) {
			params.setFechaDesde(ISBANStringUtils.formatearFecha(fechaDesde, FORMATO_FECHA));
			params.setFechaHasta(ISBANStringUtils.formatearFecha(fechaHasta, FORMATO_FECHA));
			params.setFechaActual(ISBANStringUtils.formatearFecha(fechaActual, FORMATO_FECHA));
		} else {
			return null;
		}
		return params;
	}

	/**
	 * Concatenar CBU nro cuenta prod.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String concatenarCBUNroCuentaProd(AbstractCuenta cuenta) {
		String cbu = cuenta.getCbu();
		String concatenado = cuenta.getNroCuentaProducto().concat(cbu.substring(18, 19));

		return concatenado.substring(1, 17);
	}

	/**
	 * Restar meses.
	 *
	 * @param fecha
	 *            the fecha
	 * @param cantMeses
	 *            the cant meses
	 * @return the date
	 */
	private Date restarMeses(Date fecha, int cantMeses) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.MONTH, -cantMeses);
		return fechaCalendar.getTime();
	}

	/**
	 * Sumar dias.
	 *
	 * @param fecha
	 *            the fecha
	 * @param cantidadDias
	 *            the cantidad dias
	 * @return the date
	 */
	private Date sumarDias(Date fecha, int cantidadDias) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		fechaCalendar.add(Calendar.DATE, cantidadDias);
		return fechaCalendar.getTime();
	}

	/**
	 * Validar fechas.
	 *
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @return true, if successful
	 */
	private boolean validarFechas(Date fechaDesde, Date fechaHasta) {
		boolean result = false;
		if (fechaDesde != null && fechaHasta != null && fechaDesde.compareTo(fechaHasta) <= 0) {
			result = true;
		}
		return result;
	}

	/**
	 * Genera una respuesta con datos de prueba.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param marca
	 *            the marca
	 * @param resumenAnteriorDTO
	 *            the resumen anterior DTO
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return Respuesta<ResumenAnteriorViewResponse>
	 */
	private ResumenAnteriorViewResponse obtenerResumenAnteriorViewResponse(Cuenta cuenta, String marca,
			List<ResumenMensualTarjetaDTO> resumenAnteriorDTO, String numeroCuenta) {
		ResumenAnteriorViewResponse resumenAnteriorViewResponse = new ResumenAnteriorViewResponse();
		resumenAnteriorViewResponse.setMarca(marca);
		resumenAnteriorViewResponse
				.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), marca));
		resumenAnteriorViewResponse.setNumeroCuenta(cuenta.getNroCuentaProducto());
		List<ResumenAnterior> resumenes = new ArrayList<ResumenAnterior>();
		for (int i = 0; i < resumenAnteriorDTO.size(); i++) {
			ResumenMensualTarjetaDTO detalle = resumenAnteriorDTO.get(i);
			ResumenAnterior resumenAnterior = new ResumenAnterior();
			resumenAnterior.setFecha(TarjetaBOUtils.convertirFechaAlEspaniol(detalle.getFecha()));
			resumenAnterior.setId(i);
			if (sesionTarjetas.getListaVistos() != null && sesionTarjetas.getListaVistos().containsKey(numeroCuenta)) {
				resumenAnterior.setIsVisto(sesionTarjetas.getListaVistos().get(numeroCuenta).contains(i));
			} else {
				resumenAnterior.setIsVisto(false);
			}
			resumenes.add(resumenAnterior);
		}
		resumenAnteriorViewResponse.setResumenes(resumenes);
		return resumenAnteriorViewResponse;
	}

	/**
	 * Respuesta OK.
	 *
	 * @param resumenAnteriorViewResponse
	 *            the resumen anterior view response
	 * @return the respuesta
	 */
	public Respuesta<ResumenAnteriorViewResponse> crearRespuestaOk(
			ResumenAnteriorViewResponse resumenAnteriorViewResponse) {
		return this.getRespuestaFactory().crearRespuestaOk(ResumenAnteriorViewResponse.class,
				resumenAnteriorViewResponse);
	}

	/**
	 * Respuesta ERROR TipoError.CODIGO_ERROR_SIN_CUOTAS_PENDIENTE (1156)
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	public Respuesta<ResumenAnteriorViewResponse> crearRespuestaErroneaSinResumenesAnteriores(Cuenta cuenta) {
		Respuesta<ResumenAnteriorViewResponse> respuesta = this.getRespuestaFactory().crearRespuestaWarning(
				SIN_RESUMENES_ANTERIORES, TipoError.SIN_RESUMENES,
				CodigoMensajeConstantes.CODIGO_ERROR_SIN_RESUMENES_ANTERIORES);
		ResumenAnteriorViewResponse resumen = new ResumenAnteriorViewResponse();
		resumen.setMarca(obtenerMarcaDeTarjeta(cuenta));
		resumen.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), resumen.getMarca()));
		resumen.setNumeroCuenta(cuenta.getNroCuentaProducto());
		respuesta.setRespuesta(resumen);
		return respuesta;
	}

	/**
	 * Respuesta ERROR TIPO_ERROR_CARGA_RESUMENES_ANTERIORES. (1157)
	 *
	 * @return the respuesta
	 */
	public Respuesta<ResumenAnteriorViewResponse> crearRespuestaErroneaCargaResumenesAnteriores() {
		return this.getRespuestaFactory().crearRespuestaError(ERROR_CARGA_RESUMENES_ANTERIORES,
				TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_CARGA_RESUMENES_ANTERIORES);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.ResumenAnteriorBO#obtenerResumenAnterior(ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta, ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ReporteView> obtenerResumenAnterior(IdentificacionCuenta identificacionCuenta, Cliente cliente) {
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		Format formatter = new SimpleDateFormat("dd/MM/yy");
		try {
			Cuenta cuenta = (Cuenta) getCuentaBO().buscarCuentaPorId(cliente, identificacionCuenta);
			String marca = TarjetaUtils.getMarcaAux(cuenta);
			Respuesta<List<ResumenMensualTarjetaDTO>> respuestaResumenAnteriorDTO = obtenerDatosResumenMensualTarjeta(
					cuenta, marca);
			List<ResumenMensualTarjetaDTO> resumenAnteriorDTO = obtenerListaDeResumenesOrdenada(
					respuestaResumenAnteriorDTO.getRespuesta());
			if (CollectionUtils.isEmpty(resumenAnteriorDTO)) {
				return respuestaErrorResumenActual();
			}
			List<ReporteSeleccionado> resumenesSeleccionados = generarListaReporteSeleccionado(resumenAnteriorDTO,
					cuenta, marca, formatter);
			if (!resumenesSeleccionados.isEmpty()) {
				Respuesta<ReporteResumenPuntual> reportePuntual = resumenMensualCuentaBOImpl
						.obtenerResumenPuntualPDF(resumenesSeleccionados, identificacionCuenta, cliente, 0);
				respuestaView = Respuesta.copy(ReporteView.class, reportePuntual);
				respuestaView.setRespuesta(ReporteView.fromReporte(reportePuntual.getRespuesta()));
				return respuestaView;
			}
			return this.getRespuestaFactory().crearRespuestaError(SIN_RESUMEN_ACTUAL, TipoError.SIN_RESUMEN_ACTUAL,
					CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_ACTUAL);
		} catch (OnDemandDAOException e) {
			LOGGER.error("Error al llamar al DAO de OnDemand. Armando respuesta error.", e);
			return respuestaErrorResumenActual();
		} catch (BusinessException bex) {
			LOGGER.error("Error al exportar pdf en la tarjeta {}.", identificacionCuenta.getNroCuentaProducto(), bex);
			return respuestaErrorResumenActual();
		}
	}

	/**
	 * Respuesta error resumen actual.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ReporteView> respuestaErrorResumenActual() {
		return this.getRespuestaFactory().crearRespuestaError(SIN_RESUMEN_ACTUAL, TipoError.SIN_RESUMEN_ACTUAL,
				CodigoMensajeConstantes.ERROR_DESCARGA_RESUMEN_ACTUAL);
	}
}
