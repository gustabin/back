/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.comun.CodigoInstrumentoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.TitulosOperacionesBO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.enums.CanalIngresoEnum;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ComprobanteDetalleOperacionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionCompraVentaView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionLicitacionView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

/**
 * The Class TitulosOperacionesManagerImpl.
 *
 * @author B041964
 */
@Component
public class TitulosOperacionesManagerImpl implements TitulosOperacionesManager {

	/** The Constant GUION_CON_ESPACIO. */
	private static final String GUION_CON_ESPACIO = " -";

	/** The Constant FORMATO_PRECIO_2_DECIMALES. */
	private static final String FORMATO_PRECIO_2_DECIMALES = "##,###,##0.00";

	/** The Constant FORMATO_PRECIO_7_DECIMALES. */
	private static final String FORMATO_PRECIO_7_DECIMALES = "##,###,##0.00#####";

	/** The Constant PESOS_ARS. */
	private static final String PESOS_ARS = "ARS";

	/** The Constant TODAS_LAS_ESPECIES. */
	private static final String TODAS_LAS_ESPECIES = "todas";

	/** The Constant SIMBOLO_DOLARES_CON_ESPACIO. */
	private static final String SIMBOLO_DOLARES_CON_ESPACIO = "u$s ";

	/** The Constant SIMBOLO_PESOS_CON_ESPACIO. */
	private static final String SIMBOLO_PESOS_CON_ESPACIO = "$ ";

	/** The Constant DOLARES. */
	private static final String DOLARES = "Dólares";

	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";

	/** The Constant PESOS_P. */
	private static final String PESOS_P = "P";

	/** The Constant LICITACIONES_L. */
	private static final String LICITACIONES_L = "L";

	/** The Constant VENTA_V. */
	private static final String VENTA_V = "V";

	/** The Constant COMPRA_C. */
	private static final String COMPRA_C = "C";

	/** The Constant LICITACIONES. */
	private static final String LICITACIONES = "Licitaciones";

	/** The Constant VENTA. */
	private static final String VENTA = "Venta";
	private static final String CANJES = "canjes";

	/** The Constant COMPRA. */
	private static final String COMPRA = "Compra";
	
	/** The Constant CODIGO_BANCA_RETAIL. */
	private static final String CODIGO_BANCA_RETAIL = "BR";

	/** The titulos operaciones BO. */
	@Autowired
	private TitulosOperacionesBO titulosOperacionesBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The sesion titulos operaciones. */
	@Autowired
	private SesionTitulosOperaciones sesionTitulosOperaciones;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;
	
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager#obtenerOperacionesPrimeraVez(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<OperacionesTitulosView> obtenerOperacionesPrimeraVez(
			ParametrosOperacionesView parametrosOperacionesView) {
		sesionTitulosOperaciones.setNumeroConsulta(0);
		String codigoEstadistica = null;
		if (TipoBancaEnum.BANCA_PRIVADA.getCodigo().equals(parametrosOperacionesView.getBanca())) {
			codigoEstadistica = obtenerEstadisticaConsultaPrivada(parametrosOperacionesView);
		} else {
			codigoEstadistica = obtenerEstadisticaConsultaPersonal(parametrosOperacionesView);
		}
		Respuesta<OperacionesTitulosView> respuestaListaOperacionesTitulosDTO = obtenerOperaciones(
				parametrosOperacionesView);

		if (!EstadoRespuesta.OK.equals(respuestaListaOperacionesTitulosDTO.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaListaOperacionesTitulosDTO;
		}

		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaListaOperacionesTitulosDTO;
	}

	/**
	 * Obtener estadistica consulta personal.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return String codigoEstadistica Este método devuelve el código de
	 *         estadística para consultas de operaciones de banca PERSONAL. El
	 *         código va a variar dependiendo de si la consulta es de compra,
	 *         venta o licitación, y también si la consulta viene de una
	 *         búsqueda con filtro o de la carga inicial de la grilla
	 */
	private String obtenerEstadisticaConsultaPersonal(ParametrosOperacionesView parametrosOperacionesView) {
		if (COMPRA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_COMPRA_VENTA_RTL
					: EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_COMPRA;
		} else if (VENTA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_COMPRA_VENTA_RTL
					: EstadisticasConstants.ESTADISTICA_CONSULTAR_OPERACIONES_TITULOS_VENTA;
		} else {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_LICITACIONES_RTL
					: EstadisticasConstants.TITULOS_CONSULTAR_LICITACIONES;
		}
	}

	/**
	 * Obtener estadistica consulta privada.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return String codigoEstadistica Este método devuelve el código de
	 *         estadística para consultas de operaciones de banca PRIVADA. El
	 *         código va a variar dependiendo de si la consulta es de compra,
	 *         venta o licitación, y también si la consulta viene de una
	 *         búsqueda con filtro o de la carga inicial de la grilla
	 */
	private String obtenerEstadisticaConsultaPrivada(ParametrosOperacionesView parametrosOperacionesView) {
		if (COMPRA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_COMPRA_VENTA_BP
					: EstadisticasConstants.TITULOS_CONSULTAR_OPERACIONES_COMPRA_BP;
		} else if (VENTA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_COMPRA_VENTA_BP
					: EstadisticasConstants.TITULOS_CONSULTAR_OPERACIONES_VENTA_BP;
		} else {
			return parametrosOperacionesView.getBuscador()
					? EstadisticasConstants.TITULOS_OPERACIONES_BUSCADOR_LICITACIONES_BP
					: EstadisticasConstants.TITULOS_CONSULTAR_LICITACIONES_PRIVADA;
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager#obtenerMasOperaciones()
	 */
	@Override
	public Respuesta<OperacionesTitulosView> obtenerMasOperaciones() {
		ParametrosOperacionesView parametrosOperacionesView = sesionTitulosOperaciones.getParametrosOperacionesView();
		return obtenerOperaciones(parametrosOperacionesView);
	}

	/**
	 * Obtener operaciones.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the respuesta
	 */
	private Respuesta<OperacionesTitulosView> obtenerOperaciones(ParametrosOperacionesView parametrosOperacionesView) {

		Cliente cliente = sesionCliente.getCliente();
		if (limpioCache(parametrosOperacionesView)) {
			titulosOperacionesBO.limpiarCache(cliente);
		}
		sesionTitulosOperaciones.setParametrosOperacionesView(parametrosOperacionesView);

		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = null;
		if (LICITACIONES.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			respuestaListaOperacionesTitulosDTO = titulosOperacionesBO
					.obtenerOperacionesLicitacion(parametrosOperacionesView, cliente);
		} else {
			respuestaListaOperacionesTitulosDTO = titulosOperacionesBO
					.obtenerOperacionesCompraVenta(parametrosOperacionesView, cliente);
		}

		if (!EstadoRespuesta.OK.equals(respuestaListaOperacionesTitulosDTO.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(OperacionesTitulosView.class, "", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}

		
		List<OperacionTitulosDTO> listaOperacionesDTO = respuestaListaOperacionesTitulosDTO.getRespuesta();
		listaOperacionesDTO = filtrarLista(listaOperacionesDTO, parametrosOperacionesView);
		OperacionesTitulosView operacionesTitulosView = mapearListaView(listaOperacionesDTO);

		if (CODIGO_BANCA_RETAIL.equals(parametrosOperacionesView.getBanca())) {
			Respuesta<String> legales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_CONSULTA_OPERACIONES);
			if (!EstadoRespuesta.OK.equals(legales.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaError(OperacionesTitulosView.class, "", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_GENERICO);
			}
			operacionesTitulosView.setLegales(legales.getRespuesta());			
		}	
		
		if (esBusqueda(parametrosOperacionesView) && CollectionUtils.isEmpty(operacionesTitulosView.getOperaciones())) {
			operacionesTitulosView.setMensajeVacio(mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIN_OPERACIONES_BANCA_PRIVADA).getMensaje());
		}

		return respuestaFactory.crearRespuestaOk(operacionesTitulosView);
	}

	/**
	 * Es busqueda.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return true, if successful
	 */
	private boolean esBusqueda(ParametrosOperacionesView parametrosOperacionesView) {
		// metodo chancho que se usa para evitarle al front tener que determinar
		// si me estan llamando desde
		// la busqueda o desede el inicio del stack, para saber si mando o no el
		// mensaje de no se encontraron operaciones para los filtros
		boolean fechaVacia = parametrosOperacionesView.getFechaDesde() != null
				|| parametrosOperacionesView.getFechaHasta() != null;
		boolean importeVacio = parametrosOperacionesView.getNominalesDesde() != null
				|| parametrosOperacionesView.getNominalesHasta() != null;
		return fechaVacia || importeVacio || parametrosOperacionesView.getTipoEspecie() != null;
	}

	/**
	 * Limpio cache.
	 *
	 * @param parametrosRecibidos
	 *            the parametros recibidos
	 * @return true, if successful
	 */
	private boolean limpioCache(ParametrosOperacionesView parametrosRecibidos) {
		ParametrosOperacionesView parametrosSesion = sesionTitulosOperaciones.getParametrosOperacionesView();

		if (parametrosSesion == null) {
			return false;
		}
		if (!parametrosRecibidos.getNumeroCuenta().equals(parametrosSesion.getNumeroCuenta())) {
			return true;
		}
		if (!parametrosRecibidos.getBanca().equals(parametrosSesion.getBanca())) {
			return true;
		}

		boolean fechasRecibidasNull = parametrosRecibidos.getFechaDesde() == null
				&& parametrosRecibidos.getFechaHasta() == null;
		boolean fechasSesionNull = parametrosSesion.getFechaDesde() == null && parametrosSesion.getFechaHasta() == null;
		if (fechasRecibidasNull && fechasSesionNull) {
			return false;
		}
		if (fechasRecibidasNull != fechasSesionNull) {
			return true;
		}

		return (!fechasRecibidasNull && !fechasSesionNull)
				&& !(parametrosRecibidos.getFechaDesde().equals(parametrosSesion.getFechaDesde()))
				|| !(parametrosRecibidos.getFechaHasta().equals(parametrosSesion.getFechaHasta()));

	}

	/**
	 * Filtrar lista.
	 *
	 * @param listaOperacionesDTO
	 *            the lista operaciones DTO
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the list
	 */
	private List<OperacionTitulosDTO> filtrarLista(List<OperacionTitulosDTO> listaOperacionesDTO,
			ParametrosOperacionesView parametrosOperacionesView) {

		List<OperacionTitulosDTO> listaFiltrada = new ArrayList<OperacionTitulosDTO>();
		for (OperacionTitulosDTO operacionTitulosDTO : listaOperacionesDTO) {
			if (cumpleCondiciones(operacionTitulosDTO, parametrosOperacionesView)) {
				listaFiltrada.add(operacionTitulosDTO);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Cumple condiciones.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return true, if successful
	 */
	private boolean cumpleCondiciones(OperacionTitulosDTO operacionTitulosDTO,
			ParametrosOperacionesView parametrosOperacionesView) {
		boolean pertenece = filtroTipoOperacion(operacionTitulosDTO, parametrosOperacionesView.getTipoOperacion())
				&& filtroCuenta(operacionTitulosDTO, parametrosOperacionesView);
		// Si pertenece al tipo de operacion y cuenta, aplico los filtros
		return pertenece && filtroFecha(operacionTitulosDTO, parametrosOperacionesView)
				&& filtroNominales(operacionTitulosDTO, parametrosOperacionesView)
				&& filtroTipoEspecie(operacionTitulosDTO, parametrosOperacionesView.getTipoEspecie());
	}

	/**
	 * Filtro tipo especie.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param tipoEspecie
	 *            the tipo especie
	 * @return true, if successful
	 */
	private boolean filtroTipoEspecie(OperacionTitulosDTO operacionTitulosDTO, String tipoEspecie) {
		if (tipoEspecie == null || TODAS_LAS_ESPECIES.equalsIgnoreCase(tipoEspecie)) {
			return true;
		} else {
			return tipoEspecie.equalsIgnoreCase(operacionTitulosDTO.getCodigoTipoEspecie());
		}
	}

	/**
	 * Filtro nominales.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return true, if successful
	 */
	private boolean filtroNominales(OperacionTitulosDTO operacionTitulosDTO,
			ParametrosOperacionesView parametrosOperacionesView) {
		String nominalesDesde = parametrosOperacionesView.getNominalesDesde();
		String nominalesHasta = parametrosOperacionesView.getNominalesHasta();
		if (nominalesDesde == null && nominalesHasta == null) {
			return true;
		}
		Double doubleDesde = Double.valueOf(nominalesDesde);
		Double doubleHasta = Double.valueOf(nominalesHasta);
		Double nominalesOperacion = operacionTitulosDTO.getCantidadNominales();
		return doubleDesde <= nominalesOperacion && nominalesOperacion <= doubleHasta;
	}

	/**
	 * Filtro fecha.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return true, if successful
	 */
	private boolean filtroFecha(OperacionTitulosDTO operacionTitulosDTO,
			ParametrosOperacionesView parametrosOperacionesView) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		Date fechaDesde;
		Date fechaHasta;
		if (parametrosOperacionesView.getFechaHasta() == null) {
			fechaHasta = calendar.getTime();
		} else {
			try {
				fechaHasta = sdf.parse(parametrosOperacionesView.getFechaHasta());
			} catch (ParseException e) {
				fechaHasta = calendar.getTime();
			}
		}
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		if (parametrosOperacionesView.getFechaDesde() == null) {
			fechaDesde = calendar.getTime();
		} else {
			try {
				fechaDesde = sdf.parse(parametrosOperacionesView.getFechaDesde());
			} catch (ParseException e) {
				fechaDesde = calendar.getTime();
			}
		}
		return (operacionTitulosDTO.getFecha().before(fechaHasta)
				|| operacionTitulosDTO.getFecha().compareTo(fechaHasta) == 0)
				&& (operacionTitulosDTO.getFecha().after(fechaDesde)
						|| operacionTitulosDTO.getFecha().compareTo(fechaDesde) == 0);
	}

	/**
	 * Filtro cuenta.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return true, if successful
	 */
	private boolean filtroCuenta(OperacionTitulosDTO operacionTitulosDTO,
			ParametrosOperacionesView parametrosOperacionesView) {
		if (TipoBancaEnum.BANCA_PRIVADA.getCodigo().equals(parametrosOperacionesView.getBanca())) {
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
					parametrosOperacionesView.getNumeroCuenta());
			return operacionTitulosDTO.getCuenta()
					.equals(identificacionCuenta.getNroCuentaProducto().replace("//", ""));
		} else {
			return operacionTitulosDTO.getCuentaTitulos()
					.contains(parametrosOperacionesView.getNumeroCuenta().replace("/", ""))
					|| operacionTitulosDTO.getCuentaTitulos().contains(parametrosOperacionesView.getNumeroCuenta());
		}
	}

	/**
	 * Filtro tipo operacion.
	 *
	 * @param operacionTitulosDTO
	 *            the operacion titulos DTO
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return true, if successful
	 */
	private boolean filtroTipoOperacion(OperacionTitulosDTO operacionTitulosDTO, String tipoOperacion) {
		return obtenerTipoOperacion(tipoOperacion).equalsIgnoreCase(operacionTitulosDTO.getTipoOperacion());
	}

	/**
	 * Obtener tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTipoOperacion(String tipoOperacion) {
		if (COMPRA.equalsIgnoreCase(tipoOperacion)) {
			return COMPRA_C;
		} else if (VENTA.equalsIgnoreCase(tipoOperacion)) {
			return VENTA_V;
		} else {
			return LICITACIONES_L;
		}
	}

	/**
	 * Obtener tipo operacion nombre completo.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTipoOperacionNombreCompleto(String tipoOperacion) {
		if (COMPRA_C.equals(tipoOperacion)) {
			return COMPRA;
		} else if (VENTA_V.equals(tipoOperacion)) {
			return VENTA;
		} else {
			return LICITACIONES;
		}
	}

	/**
	 * Mapear lista view.
	 *
	 * @param listaOperacionesDTO
	 *            the lista operaciones DTO
	 * @return the operaciones titulos view
	 */
	private OperacionesTitulosView mapearListaView(List<OperacionTitulosDTO> listaOperacionesDTO) {

		OperacionesTitulosView operacionesTitulosView = new OperacionesTitulosView();
		List<OperacionTitulosView> operaciones = new ArrayList<OperacionTitulosView>();
		int numeroConsulta = sesionTitulosOperaciones.getNumeroConsulta();
		for (OperacionTitulosDTO operacionTitulosDTO : listaOperacionesDTO) {
			OperacionTitulosView operacionTitulosView = new OperacionTitulosView();
			if("CANJES".equalsIgnoreCase(operacionTitulosDTO.getTipoPliego())) {
				mapearOperacionLicitacionCanje(operacionTitulosDTO, operacionTitulosView);
			}else {
				mapearOperacionLicitacion(operacionTitulosDTO, operacionTitulosView);
			}
			operaciones.add(operacionTitulosView);
		}
		if (operaciones.size() > 10) {
			operacionesTitulosView.setOperaciones(operaciones.subList(numeroConsulta * 10,
					numeroConsulta * 10 + 10 > operaciones.size() ? operaciones.size() : numeroConsulta * 10 + 10));
			sesionTitulosOperaciones.setNumeroConsulta(numeroConsulta + 1);
			operacionesTitulosView.setHayMas(operaciones.size() > numeroConsulta * 10 + 10);
		} else {
			operacionesTitulosView.setOperaciones(operaciones);
			operacionesTitulosView.setHayMas(false);
		}

		return operacionesTitulosView;
	}

	private void mapearOperacionLicitacion(OperacionTitulosDTO operacionTitulosDTO,
			OperacionTitulosView operacionTitulosView) {
		operacionTitulosView.setCantidadNominales(
				formatearValor(operacionTitulosDTO.getCantidadNominales(), FORMATO_PRECIO_7_DECIMALES));
		operacionTitulosView.setDescripcion(operacionTitulosDTO.getDescripcion());
		operacionTitulosView.setFecha(parsearFecha(operacionTitulosDTO.getFecha()));
		operacionTitulosView.setMoneda(formatearMoneda(operacionTitulosDTO.getMoneda()));
		operacionTitulosView.setNumero(String.valueOf(operacionTitulosDTO.getNumeroOperacion()));
		operacionTitulosView.setTipoPliego(operacionTitulosDTO.getTipoPliego());
		if (StringUtils.isNotBlank(operacionTitulosDTO.getPrecioString())) {
			operacionTitulosView.setPrecio(operacionTitulosDTO.getPrecioString());
		} else {
			operacionTitulosView.setPrecio(formatearPrecio(operacionTitulosDTO.getPrecio(), null,
					operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, true, false));
		}
		String tipo = CodigoInstrumentoEnum.obtenerDescripcion(operacionTitulosDTO.getTipoCodigo());
		operacionTitulosView.setTipo(tipo != null ? tipo : operacionTitulosDTO.getTipo());
	}
	
	private void mapearOperacionLicitacionCanje(OperacionTitulosDTO operacionTitulosDTO,
			OperacionTitulosView operacionTitulosView) {
		operacionTitulosView.setCantidadNominales(
				formatearValor(operacionTitulosDTO.getCantidadNominales(), FORMATO_PRECIO_7_DECIMALES));
		operacionTitulosView.setDescripcion(operacionTitulosDTO.getDescripcion());
		operacionTitulosView.setFecha(parsearFecha(operacionTitulosDTO.getFecha()));
		operacionTitulosView.setMoneda(formatearMoneda(operacionTitulosDTO.getMoneda()));
		operacionTitulosView.setNumero(String.valueOf(operacionTitulosDTO.getNumeroOperacion()));
		operacionTitulosView.setTipoPliego(operacionTitulosDTO.getTipoPliego());
		operacionTitulosView.setPrecio(operacionTitulosDTO.getTipoPrecioDatoCanje());
		String tipo = CodigoInstrumentoEnum.obtenerDescripcion(operacionTitulosDTO.getTipoCodigo());
		operacionTitulosView.setTipo(tipo != null ? tipo : operacionTitulosDTO.getTipo());
	}

	/**
	 * Formatear moneda.
	 *
	 * @param codMoneda
	 *            the cod moneda
	 * @return the string
	 */
	private String formatearMoneda(String codMoneda) {
		if (PESOS_P.contains(codMoneda) || PESOS_ARS.contains(codMoneda))
			return PESOS;
		else
			return DOLARES;
	}

	/**
	 * Parsear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String parsearFecha(Date fecha) {
		if (fecha != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaParseada;
			fechaParseada = sdf.format(fecha);
			return fechaParseada;
		} else {
			return "";
		}
	}

	/**
	 * Formatear precio.
	 *
	 * @param precio
	 *            the precio
	 * @param tipoPrecio
	 *            the tipo precio
	 * @param moneda
	 *            the moneda
	 * @param formato
	 *            the formato
	 * @param aceptaCero
	 *            the acepta cero
	 * @param nulleoCero
	 *            the nulleo cero
	 * @return the string
	 */
	private String formatearPrecio(Double precio, String tipoPrecio, String moneda, String formato, Boolean aceptaCero,
			Boolean nulleoCero) {
		String simbolo = PESOS_P.equalsIgnoreCase(moneda) || PESOS_ARS.equalsIgnoreCase(moneda)
				? SIMBOLO_PESOS_CON_ESPACIO
				: SIMBOLO_DOLARES_CON_ESPACIO;
		if (nulleoCero && precio.equals(0D)) {
			return null;
		}
		if (!aceptaCero && precio.equals(0D)) {
			return simbolo + GUION_CON_ESPACIO;
		}
		if (tipoPrecio != null && !"Precio".equals(tipoPrecio)) {
			return formatearValor(precio, formato) + " %";
		}
		return simbolo + formatearValor(precio, formato);
	}

	/**
	 * Formatear valor.
	 *
	 * @param valor
	 *            the valor
	 * @param formato
	 *            the formato
	 * @return the string
	 */
	private String formatearValor(Double valor, String formato) {
		DecimalFormat df = new DecimalFormat(formato);
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
		return df.format(valor);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager#obtenerCuentasOperativas(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<List<CuentasOperativasView>> obtenerCuentasOperativas(
			ParametrosOperacionesView parametrosOperacionesView) {

		List<CuentasOperativasView> listaCuentasOperativasView = new ArrayList<CuentasOperativasView>();
		if (TipoBancaEnum.BANCA_PRIVADA.getCodigo().equals(parametrosOperacionesView.getBanca())) {
			List<CuentaBancaPrivada> cuentasOperativas = sesionCliente.getCliente().getCuentasBancaPrivada();
			for (CuentaBancaPrivada cuenta : cuentasOperativas) {
				CuentasOperativasView cuentasOperativasView = new CuentasOperativasView();
				Cuenta cuentaOperativa = cuenta.getCuentaOperativa();
				String nroCuenta = ISBANStringUtils.eliminarCeros(cuentaOperativa.getNroSucursal()) + "-"
						+ ISBANStringUtils.formatearNumeroCuenta(cuentaOperativa.getNroCuentaProducto());
				cuentasOperativasView.setNumeroCuenta(nroCuenta);
				cuentasOperativasView.setIntervinientes(obtenerIntervinientesView(cuentaOperativa.getIntervinientes()));
				listaCuentasOperativasView.add(cuentasOperativasView);
			}
		} else {
			List<CuentaTituloView> cuentasTitulo = sesionCliente.getCliente().getCuentasTituloRTL();
			for (CuentaTituloView cuentaTituloView : cuentasTitulo) {
				CuentasOperativasView cuentasOperativasView = new CuentasOperativasView();
				String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuentaTituloView.getNroCuenta());
				cuentasOperativasView.setNumeroCuenta(nroCuenta);
				cuentasOperativasView
						.setIntervinientes(obtenerIntervinientesView(cuentaTituloView.getIntervinientes()));
				listaCuentasOperativasView.add(cuentasOperativasView);
			}
		}
		return respuestaFactory.crearRespuestaOk(listaCuentasOperativasView);
	}

	/**
	 * Obtener intervinientes view.
	 *
	 * @param intervinientes
	 *            the intervinientes
	 * @return the list
	 */
	private List<IntervinienteView> obtenerIntervinientesView(List<Interviniente> intervinientes) {

		List<IntervinienteView> listaIntervinientes = new ArrayList<IntervinienteView>();
		for (Interviniente interviniente : intervinientes) {
			IntervinienteView intervinienteView = new IntervinienteView();
			intervinienteView.setNombre(interviniente.getNombre());
			intervinienteView.setApellido(interviniente.getApellido());
			listaIntervinientes.add(intervinienteView);
		}
		return listaIntervinientes;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager#obtenerDetalleOperacion(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<OperacionTitulosView> obtenerDetalleOperacion(
			ParametrosOperacionesView parametrosOperacionesView) {

		Cliente cliente = sesionCliente.getCliente();
		Respuesta<List<OperacionTitulosDTO>> respuestaListaOperacionesTitulosDTO = null;
		OperacionTitulosView detalleOperacionView;

		if (LICITACIONES.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion()) || "CANJES".equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			respuestaListaOperacionesTitulosDTO = titulosOperacionesBO
					.obtenerOperacionesLicitacion(parametrosOperacionesView, cliente);

		} else {
			respuestaListaOperacionesTitulosDTO = titulosOperacionesBO
					.obtenerOperacionesCompraVenta(parametrosOperacionesView, cliente);
		}
		String codigoEstadistica = obtenerEstadisticasConsultarDetalle(parametrosOperacionesView);

		if (!EstadoRespuesta.OK.equals(respuestaListaOperacionesTitulosDTO.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(OperacionTitulosView.class, "", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		List<OperacionTitulosDTO> listaOperacionesDTO = respuestaListaOperacionesTitulosDTO.getRespuesta();
		if (LICITACIONES.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
			detalleOperacionView = mapearDetalleOperacionLicitacionView(listaOperacionesDTO,
					parametrosOperacionesView.getNumero());
		} else if("CANJES".equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())){
			detalleOperacionView = mapearDetalleOperacionLicitacionCanjeView(listaOperacionesDTO,
					parametrosOperacionesView.getNumero());
		}else {
			detalleOperacionView = mapearDetalleOperacionCompraVentaView(listaOperacionesDTO,
					parametrosOperacionesView.getNumero());
			
		}

		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		sesionParametros.setDetalleComprobanteView(detalleOperacionView);
		return respuestaFactory.crearRespuestaOk(detalleOperacionView);
	}

	/**
	 * Obtener estadisticas consultar detalle.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the string
	 */
	private String obtenerEstadisticasConsultarDetalle(ParametrosOperacionesView parametrosOperacionesView) {
		if (TipoBancaEnum.BANCA_PRIVADA.getCodigo().equals(parametrosOperacionesView.getBanca())) {
			if (COMPRA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
				return EstadisticasConstants.TITULOS_CONSULTAR_DETALLE_OPERACION_COMPRA;
			} else if (VENTA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
				return EstadisticasConstants.TITULOS_CONSULTAR_DETALLE_OPERACION_VENTA;
			} else if (CANJES.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())){
				return EstadisticasConstants.VER_DETALLE_CONSULTA_OPERACIONES_CANJE_BPRIV;
			} else {
				return EstadisticasConstants.TITULOS_CONSULTAR_DETALLE_OPERACION_LICITACION;
			}
		} else {
			if (COMPRA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
				return EstadisticasConstants.ESTADISTICA_DETALLE_CONSULTAR_OPERACIONES_COMPRA;
			} else if (VENTA.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())) {
				return EstadisticasConstants.ESTADISTICA_DETALLE_CONSULTAR_OPERACIONES_VENTA;
			} else if (CANJES.equalsIgnoreCase(parametrosOperacionesView.getTipoOperacion())){
				return EstadisticasConstants.VER_DETALLE_CONSULTA_OPERACIONES_CANJE;
			} else {
				return EstadisticasConstants.TITULOS_CONSULTAR_DETALLE_OPERACION_LICITACION_RTL;
			}
		}
	}

	/**
	 * Mapear detalle operacion licitacion view.
	 *
	 * @param listaOperacionesDTO
	 *            the lista operaciones DTO
	 * @param numero
	 *            the numero
	 * @return the detalle operacion licitacion view
	 */
	private DetalleOperacionLicitacionView mapearDetalleOperacionLicitacionView(
			List<OperacionTitulosDTO> listaOperacionesDTO, String numero) {

		DetalleOperacionLicitacionView detalleOperacionLicitacionView = new DetalleOperacionLicitacionView();
		OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
		for (OperacionTitulosDTO operacionBuscada : listaOperacionesDTO) {
			if (operacionBuscada.getNumeroOperacion().toString().equals(numero)) {
				operacionTitulosDTO = operacionBuscada;
				break;
			}
		}
		String tipoPrecio = operacionTitulosDTO.getTipoPrecio();
		// Estas cantidades parecen seteadas al revés, pero es correcto
		// Están así porque en la grilla se usa un campo para mostrar los registros
		// mientras que en el detalle, se muestran como deberían
		detalleOperacionLicitacionView.setCantidadAdjudicada(
				formatearValor(operacionTitulosDTO.getCantidadNominales(), FORMATO_PRECIO_2_DECIMALES));
		detalleOperacionLicitacionView.setCantidadNominales(operacionTitulosDTO.getCantidad());
		String comisiones = formatearPrecio(operacionTitulosDTO.getComisiones(), null, operacionTitulosDTO.getMoneda(),
				FORMATO_PRECIO_2_DECIMALES, false, true);
		detalleOperacionLicitacionView.setComisiones(comisiones);
		detalleOperacionLicitacionView.setCuentaDebito(operacionTitulosDTO.getCuentaDestino());
		detalleOperacionLicitacionView.setTipoCuentaDebito(operacionTitulosDTO.getTipoCuentaDestino());
		detalleOperacionLicitacionView.setCuentaTitulos(operacionTitulosDTO.getCuentaTitulos());
		detalleOperacionLicitacionView.setDescripcion(operacionTitulosDTO.getDescripcion());
		detalleOperacionLicitacionView.setEstado(operacionTitulosDTO.getEstado());
		detalleOperacionLicitacionView.setFecha(parsearFecha(operacionTitulosDTO.getFecha()));
		detalleOperacionLicitacionView.setFechaAdjudicacion(operacionTitulosDTO.getFechaAdjudicacion());
		detalleOperacionLicitacionView.setFechaDebito(operacionTitulosDTO.getFechaDebito());
		detalleOperacionLicitacionView.setFechaHoraCierre(operacionTitulosDTO.getFechaCierre());
		detalleOperacionLicitacionView.setFechaLiquidacionTitulo(operacionTitulosDTO.getFechaLiquidacion());
		detalleOperacionLicitacionView.setImporteDebitar(operacionTitulosDTO.getImporteDebitar());
		String impuestos = formatearPrecio(operacionTitulosDTO.getImpuestos(), null, operacionTitulosDTO.getMoneda(),
				FORMATO_PRECIO_2_DECIMALES, false, true);
		detalleOperacionLicitacionView.setImpuestos(impuestos);
		detalleOperacionLicitacionView.setMoneda(operacionTitulosDTO.getMonedaEspecie());
		detalleOperacionLicitacionView.setMonedaLicitacion(formatearMoneda(operacionTitulosDTO.getMoneda()));
		detalleOperacionLicitacionView.setNumero(String.valueOf(operacionTitulosDTO.getNumeroOperacion()));
		if (operacionTitulosDTO.getPrecioAdjudicadoString() != null) {
			detalleOperacionLicitacionView.setPrecio(operacionTitulosDTO.getPrecioAdjudicadoString());
		} else {
			detalleOperacionLicitacionView.setPrecio(formatearPrecio(operacionTitulosDTO.getPrecioAdjudicado(),
					tipoPrecio, operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, false, false));
		}
		if (operacionTitulosDTO.getPrecioString() != null) {
			detalleOperacionLicitacionView.setPrecioAdjudicado(operacionTitulosDTO.getPrecioString());
		} else {
			detalleOperacionLicitacionView.setPrecioAdjudicado(formatearPrecio(operacionTitulosDTO.getPrecio(),
					tipoPrecio, operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, false, false));
		}
		String tipo = CodigoInstrumentoEnum.obtenerDescripcion(operacionTitulosDTO.getTipoCodigo());
		detalleOperacionLicitacionView.setTipo(tipo != null ? tipo : operacionTitulosDTO.getTipo());
		detalleOperacionLicitacionView.setTramo(operacionTitulosDTO.getTramo());
		detalleOperacionLicitacionView.setPrecioAdjudicadoLabel(obtenerPrecioAdjudicadoLabel(tipoPrecio));
		detalleOperacionLicitacionView.setPrecioLabel(tipoPrecio);
		detalleOperacionLicitacionView.setAbreviaturaCajaValores(operacionTitulosDTO.getAbreviaturaCajaValores());
		detalleOperacionLicitacionView.setLegal(operacionTitulosDTO.getLegal());
		return detalleOperacionLicitacionView;
	}

	/**
	 * Obtener precio adjudicado label.
	 *
	 * @param tipoPrecio
	 *            the tipo precio
	 * @return the string
	 */
	private String obtenerPrecioAdjudicadoLabel(String tipoPrecio) {
		if ("Tasa".equals(tipoPrecio)) {
			return tipoPrecio + " adjudicada";
		} else {
			return tipoPrecio + " adjudicado";
		}
	}

	/**
	 * Mapear detalle operacion compra venta view.
	 *
	 * @param listaOperacionesDTO
	 *            the lista operaciones DTO
	 * @param numero
	 *            the numero
	 * @return the detalle operacion compra venta view
	 */
	private DetalleOperacionCompraVentaView mapearDetalleOperacionCompraVentaView(
			List<OperacionTitulosDTO> listaOperacionesDTO, String numero) {

		DetalleOperacionCompraVentaView detalleOperacionView = new DetalleOperacionCompraVentaView();
		OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
		for (OperacionTitulosDTO operacionBuscada : listaOperacionesDTO) {
			if (operacionBuscada.getNumeroOperacion().toString().equals(numero)) {
				operacionTitulosDTO = operacionBuscada;
				break;
			}
		}
		detalleOperacionView.setDescripcion(operacionTitulosDTO.getDescripcion());
		detalleOperacionView.setDescripcionCodigo(operacionTitulosDTO.getCodigoTipoEspecie());
		detalleOperacionView.setFecha(parsearFecha(operacionTitulosDTO.getFecha()));
		detalleOperacionView.setNumero(operacionTitulosDTO.getNumero());
		detalleOperacionView.setNumeroOperacion(String.valueOf(operacionTitulosDTO.getNumeroOperacion()));
		detalleOperacionView.setAccion(obtenerTipoOperacionNombreCompleto(operacionTitulosDTO.getTipoOperacion()));
		detalleOperacionView.setEstado(obtenerEstadoOperacion(operacionTitulosDTO.getEstado()));
		String tipo = CodigoInstrumentoEnum.obtenerDescripcion(operacionTitulosDTO.getTipoCodigo());
		detalleOperacionView.setTipo(tipo != null ? tipo : operacionTitulosDTO.getTipo());
		detalleOperacionView.setMoneda(formatearMoneda(operacionTitulosDTO.getMoneda()));
		detalleOperacionView.setCantidadNominales(
				formatearValor(operacionTitulosDTO.getCantidadNominales(), FORMATO_PRECIO_7_DECIMALES));
		detalleOperacionView.setPlazoDeLiquidacion(obtenerPlazoLiquidacion(operacionTitulosDTO.getPlazoLiquidacion()));
		detalleOperacionView.setMercado(operacionTitulosDTO.getMercadoDescripcion());
		detalleOperacionView.setPrecio(formatearPrecio(operacionTitulosDTO.getPrecio(), null,
				operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, false, false));
		detalleOperacionView
				.setPrecioReferencia(formatearPrecio(Double.parseDouble(operacionTitulosDTO.getPrecioReferencia()),
						null, operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, false, false));
		detalleOperacionView.setPrecioLimite(formatearPrecio(operacionTitulosDTO.getPrecioLimite(), null,
				operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_7_DECIMALES, false, false));
		detalleOperacionView.setImporte(formatearPrecio(operacionTitulosDTO.getImporte(), null,
				operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_2_DECIMALES, false, true));
		detalleOperacionView
				.setCuentaTitulos(ISBANStringUtils.formatearNumeroCuenta(operacionTitulosDTO.getCuentaTitulos()));
		detalleOperacionView.setCuentaDestino(operacionTitulosDTO.getSucursalCuentaDestino().trim() + "-"
				+ ISBANStringUtils.formatearNumeroCuenta(operacionTitulosDTO.getCuentaDestino()));
		detalleOperacionView.setTipoCuentaDestino(obtenerTipoCuenta(operacionTitulosDTO.getTipoCuentaDestino()));
		detalleOperacionView.setComisiones(formatearPrecio(operacionTitulosDTO.getComisiones(), null,
				operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_2_DECIMALES, false, true));
		detalleOperacionView.setImpuestos(formatearPrecio(operacionTitulosDTO.getImpuestos(), null,
				operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_2_DECIMALES, false, true));
		detalleOperacionView.setDerechos(formatearPrecio(operacionTitulosDTO.getDerechos(), null,
                operacionTitulosDTO.getMoneda(), FORMATO_PRECIO_2_DECIMALES, false, true));
		detalleOperacionView.setCanalIngreso(obtenerCanalIngreso(operacionTitulosDTO.getCanalIngreso()));
		detalleOperacionView.setAbreviaturaCajaValores(operacionTitulosDTO.getAbreviaturaCajaValores());
		detalleOperacionView.setLegal(operacionTitulosDTO.getLegal());
		return detalleOperacionView;
	}

	/**
	 * Obtener plazo liquidacion.
	 *
	 * @param plazoLiquidacion
	 *            the plazo liquidacion
	 * @return the string
	 */
	private String obtenerPlazoLiquidacion(Integer plazoLiquidacion) {
		if (plazoLiquidacion.equals(0)) {
			return "Inmediato";
		}
		return String.valueOf(plazoLiquidacion) + " hrs";
	}

	/**
	 * Obtener canal ingreso.
	 *
	 * @param canalIngreso
	 *            the canal ingreso
	 * @return the string
	 */
	private String obtenerCanalIngreso(String canalIngreso) {
	    return CanalIngresoEnum.obtenerDescripcion(canalIngreso);
	}

	/**
	 * Obtener tipo cuenta.
	 *
	 * @param tipoCuentaDestino
	 *            the tipo cuenta destino
	 * @return the string
	 */
	private String obtenerTipoCuenta(String tipoCuentaDestino) {
		if ("00".equals(tipoCuentaDestino) || "03".equals(tipoCuentaDestino)) {
			return "Cuenta corriente";
		}
		if ("01".equals(tipoCuentaDestino) || "04".equals(tipoCuentaDestino)) {
			return "Caja de ahorros";
		}
		if ("02".equals(tipoCuentaDestino) || "09".equals(tipoCuentaDestino) || "10".equals(tipoCuentaDestino)) {
			return "Cuenta única";
		}
		return null;
	}

	/**
	 * Obtener estado operacion.
	 *
	 * @param estado
	 *            the estado
	 * @return the string
	 */
	private String obtenerEstadoOperacion(String estado) {
		if ("C".equals(estado)) {
			return "Cerrado";
		}
		if ("BG".equals(estado)) {
			return "Boleto generado";
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager#descargarComprobanteDetalleOperacion(java.lang.String)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacion(String tipoOperacion) {
		DetalleComprobanteView detalleView = sesionParametros.getDetalleComprobanteView();
		detalleView.setTituloComprobante(obtenerTituloComprobante(tipoOperacion));
		Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(detalleView);
		String codigoEstadistica = obtenerCodigoEstadisticaDescargaDetalleOperacion(tipoOperacion);
		if (EstadoRespuesta.ERROR.equals(reporte.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaErrorPersonalizado(ReporteView.class, obtenerMensajeErrorSinFormato(),
					TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
		}
		ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(reporteView);
	}

	/**
	 * Obtener mensaje error sin formato.
	 *
	 * @return the string
	 */
	private String obtenerMensajeErrorSinFormato() {
		Mensaje msj = mensajeBO.obtenerMensajePorCodigoConErrorGenerico(
				CodigoMensajeConstantes.ERROR_DESCARGA_COMPROBANTE_TRANSFERENCIA_BANCA_PRIVADA);
		return StringUtils.replaceEach(msj.getMensaje(), new String[] { "<p>", "</p>", "<P>" },
				new String[] { "", "", "" });
	}

	/**
	 * Obtener codigo estadistica descarga detalle operacion.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerCodigoEstadisticaDescargaDetalleOperacion(String tipoOperacion) {
		if (StringUtils.equalsIgnoreCase(tipoOperacion, COMPRA)) {
			return EstadisticasConstants.PDF_TITULOS_CONSULTA_OPERACION_COMPRA;
		} else if (StringUtils.equalsIgnoreCase(tipoOperacion, VENTA)) {
			return EstadisticasConstants.PDF_TITULOS_CONSULTA_OPERACION_VENTA;
		}
		return EstadisticasConstants.PDF_TITULOS_CONSULTA_OPERACION_LICITACION;
	}

	/**
	 * Obtener titulo comprobante.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the string
	 */
	private String obtenerTituloComprobante(String tipoOperacion) {
		if (StringUtils.equalsIgnoreCase(tipoOperacion, COMPRA)) {
			return CabeceraComprobantesEnum.CONSULTA_OPERACION_TITULO_COMPRA.getDetalle();
		} else if (StringUtils.equalsIgnoreCase(tipoOperacion, VENTA)) {
			return CabeceraComprobantesEnum.CONSULTA_OPERACION_TITULO_VENTA.getDetalle();
		}
		return CabeceraComprobantesEnum.CONSULTA_OPERACION_TITULO_LICITACION.getDetalle();
	}

	/**
	 * Mapear detalle operacion licitacion view.
	 *
	 * @param listaOperacionesDTO
	 *            the lista operaciones DTO
	 * @param numero
	 *            the numero
	 * @return the detalle operacion licitacion view
	 */
	private DetalleOperacionLicitacionView mapearDetalleOperacionLicitacionCanjeView(
			List<OperacionTitulosDTO> listaOperacionesDTO, String numero) {

		DetalleOperacionLicitacionView detalleOperacionLicitacionView = new DetalleOperacionLicitacionView();
		OperacionTitulosDTO operacionTitulosDTO = new OperacionTitulosDTO();
		for (OperacionTitulosDTO operacionBuscada : listaOperacionesDTO) {
			if (operacionBuscada.getNumeroOperacion().toString().equals(numero)) {
				operacionTitulosDTO = operacionBuscada;
				break;
			}
		}
		
		detalleOperacionLicitacionView.setFecha(parsearFecha(operacionTitulosDTO.getFecha()));
		detalleOperacionLicitacionView.setNumero(String.valueOf(operacionTitulosDTO.getNumeroOperacion()));
		detalleOperacionLicitacionView.setEstado(operacionTitulosDTO.getEstado());
		String tipo = CodigoInstrumentoEnum.obtenerDescripcion(operacionTitulosDTO.getTipoCodigo());
		detalleOperacionLicitacionView.setTipo(tipo != null ? tipo : operacionTitulosDTO.getTipo());
		detalleOperacionLicitacionView.setMonedaEspecieOrigen(operacionTitulosDTO.getMonedaEspecie());
		detalleOperacionLicitacionView.setMonedaEspecieDestino(operacionTitulosDTO.getMonedaEspecieDestino());
		detalleOperacionLicitacionView.setValorNominalACanjear(operacionTitulosDTO.getCantidad());
		detalleOperacionLicitacionView.setTipoPrecio(operacionTitulosDTO.getTipoPrecio());
		detalleOperacionLicitacionView.setTipoPrecioDato(operacionTitulosDTO.getTipoPrecioDatoCanje());
		detalleOperacionLicitacionView.setEspecieDestino(operacionTitulosDTO.getEspecieDestino());
		detalleOperacionLicitacionView.setDescripcion(operacionTitulosDTO.getDescripcion());
		detalleOperacionLicitacionView.setTramo(operacionTitulosDTO.getTramo());
		detalleOperacionLicitacionView.setImporteDebitar(operacionTitulosDTO.getImporteDebitar());
		detalleOperacionLicitacionView.setCuentaTitulos(operacionTitulosDTO.getCuentaTitulos());
		detalleOperacionLicitacionView.setCuentaDebito(operacionTitulosDTO.getCuentaDestino());
		detalleOperacionLicitacionView.setTipoCuentaDebito(operacionTitulosDTO.getTipoCuentaDestino());
		detalleOperacionLicitacionView.setCantidadAdjudicada(formatearValor(operacionTitulosDTO.getCantidadNominales(), FORMATO_PRECIO_2_DECIMALES));
		detalleOperacionLicitacionView.setFechaHoraCierre(operacionTitulosDTO.getFechaCierre());
		detalleOperacionLicitacionView.setFechaAdjudicacion(operacionTitulosDTO.getFechaAdjudicacion());
		detalleOperacionLicitacionView.setFechaLiquidacionTitulo(operacionTitulosDTO.getFechaLiquidacion());
		String comisiones = formatearPrecio(operacionTitulosDTO.getComisiones(), null, operacionTitulosDTO.getMoneda(),
				FORMATO_PRECIO_2_DECIMALES, false, true);
		detalleOperacionLicitacionView.setComisiones(comisiones);
		String impuestos = formatearPrecio(operacionTitulosDTO.getImpuestos(), null, operacionTitulosDTO.getMoneda(),
				FORMATO_PRECIO_2_DECIMALES, false, true);
		detalleOperacionLicitacionView.setImpuestos(impuestos);
		detalleOperacionLicitacionView.setLegal(operacionTitulosDTO.getLegal());
		detalleOperacionLicitacionView.setPrecioAdjudicado(operacionTitulosDTO.getPrecioAdjudicadoString());
		detalleOperacionLicitacionView.setFechaDebito(operacionTitulosDTO.getFechaDebito());
		
		return detalleOperacionLicitacionView;
	}

	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanje(
			ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(comprobanteDetalleOperacionLicitacionCanje);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_CONSULTA_OPERACIONES_CANJE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_CONSULTA_OPERACIONES_CANJE, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanjeBPriv(
			ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		reporte = reporteBO.obtenerComprobantePDF(comprobanteDetalleOperacionLicitacionCanje);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_CONSULTA_OPERACIONES_CANJE_BPRIV, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGAR_COMPROBANTE_CONSULTA_OPERACIONES_CANJE_BPRIV, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_OPERACIONES_COMPRA_VENTA)
	@Override
	public Respuesta<Boolean> vaciarCacheOperaciones() {
		return respuestaFactory.crearRespuestaOk(true);
	}
}
