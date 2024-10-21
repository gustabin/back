/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.AperturaGraficaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleRentabilidadTotalDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleSubclasificionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.GrillasProductosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.RentabilidadPeriodoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DetalleRentabilidadTotalEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DivisaEnumCartera;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.CuentaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DatosGraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleItemProducto;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleProductoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoCircularView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ListaFiltradaPorFecha;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.MonedaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ProductosGraficoRendimiento;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTooltip;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResultadoCarteraCliente;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ValoresProductos;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.CarteraAConsultar;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosAperturaGraficaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosDetalleRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosGraficoRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorCabecera;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorItem;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorSubCabecera;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendPorFecha;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendimientoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendimientoRespuesta;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRespuesta;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ResultadoDetalleRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ResultadoFiltroPorFechaEntity;

/**
 * The Class AnalisisCarteraBOImpl.
 */
@Component
public class AnalisisCarteraBOImpl implements AnalisisCarteraBO {

	/** The rendimiento tenencia DAO. */
	@Autowired
	RendimientoTenenciaDAO rendimientoTenenciaDAO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The canal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALTIPO}")
	private String canalTipo;

	/** The canal tipo bp. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALTIPO.BP}")
	private String canalTipoBP;

	/** The subcanal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.SUBCANALTIPO2}")
	private String subcanalTipo;

	/** The subcanal tipo bp. */
	@Value("${INVERSIONES.RENDIMIENTO.SUBCANALTIPO.BP2}")
	private String subcanalTipoBP;

	/** The subcanal tipo bp. */
	@Value("${INVERSIONES.RENDIMIENTO.CLASIFICACION.PRODUCTO.MOBILE:PROD}")
	private String clasificacionGrillaMobile;

	/** The Constant BANCA_PERSONAL. */
	private static final String BANCA_PERSONAL = "RTL";

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BP";

	/** The Constant CODIGO_CUENTA. */
	private static final String CODIGO_CUENTA = "CTA";

	/** The Constant CODIGO_PLAZO_FIJO. */
	private static final String CODIGO_PLAZO_FIJO = "PF";

	/** The Constant CODIGO_CUENTA_Y_PF. */
	private static final String CODIGO_CUENTA_Y_PF = "PAR";

	/** The Constant CODIGO_FECHA_PERIODO. */
	private static final String CODIGO_FECHA_PERIODO = "PER";

	/** The Constant PARTE_FINAL_STRING_FECHA_SERVICIO. */
	private static final String PARTE_FINAL_STRING_FECHA_SERVICIO = "T00:00:00";

	/** The Constant PRUEBA. */
	private static final String PRUEBA = "Prueba";

	/** The Constant TENENCIA_PESOS. */
	private static final String TENENCIA_PESOS = "ARS";

	/** The Constant TENENCIA_DOLARES. */
	private static final String TENENCIA_DOLARES = "USD";

	/** The Constant TOTAL_REEXPRESADO_PESOS. */
	private static final String TOTAL_REEXPRESADO_PESOS = "RARS";

	/** The Constant CODIGO_30_DIAS. */
	private static final String CODIGO_30_DIAS = "30D";

	/** The Constant CODIGO_60_DIAS. */
	private static final String CODIGO_60_DIAS = "60D";

	/** The Constant CODIGO_90_DIAS. */
	private static final String CODIGO_90_DIAS = "90D";

	/** The Constant CODIGO_365_DIAS. */
	private static final String CODIGO_365_DIAS = "365D";

	/** The Constant CODIGO_ANIO. */
	private static final String CODIGO_ANIO = "ANIO";

	/** The Constant MILLON. */
	private static final String MILLON = "1000000";

	/** The Constant MIL. */
	private static final String MIL = "1000";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalisisCarteraBOImpl.class);

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerRentabilidadTotal(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView, java.lang.Boolean)
	 */
	@Override
	public DetalleRentabilidadTotalDTO obtenerRentabilidadTotal(Cliente cliente,
			RentabilidadTotalInView rentabilidadView, Boolean esBancaPrivada) {

		DetalleRentabilidadTotalDTO rentabilidadTotalDTO = new DetalleRentabilidadTotalDTO();

		if (esBancaPrivada && cliente.getCuentasPrivadas().isEmpty()
				|| !esBancaPrivada && cliente.getCuentasRetail().isEmpty()) {
			return rentabilidadTotalDTO;
		}

		RentabilidadTotalRequestEntity request = armarRequestObtenerRentabilidadTotal(cliente, rentabilidadView,
				esBancaPrivada);

		try {
			DetalleRentabilidadTotalEntity detalleRentabilidadTotalEntity = rendimientoTenenciaDAO
					.obtenerRentabilidadTotal(request);
			if (detalleRentabilidadTotalEntity.getDatos().getRentTotalPeriodoMoneda() == null) {
				rentabilidadTotalDTO.setCorrespondeBanca(true);
				rentabilidadTotalDTO.setHayError(true);
			} else {
				rentabilidadTotalDTO.setRentTotalPeriodoMoneda(
						detalleRentabilidadTotalEntity.getDatos().getRentTotalPeriodoMoneda());
				rentabilidadTotalDTO.getRentTotalPeriodoMoneda().setFechaInfoDisponible(fechaInicioFin(rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getFechaInfoDisponible()));
				rentabilidadTotalDTO.setCorrespondeBanca(true);
			}
		} catch (DAOException e) {
			if ("NO_HAY_INVERSIONES".equals(e.getMessage())) {
				rentabilidadTotalDTO.setCorrespondeBanca(true);
				rentabilidadTotalDTO.setNoTieneRentabilidad(true);
			} else {
				rentabilidadTotalDTO.setCorrespondeBanca(true);
				rentabilidadTotalDTO.setHayError(true);
			}
		}

		return rentabilidadTotalDTO;
	}

	/**
	 * Armar request obtener rentabilidad total.
	 *
	 * @param cliente
	 *            the cliente
	 * @param rentabilidadView
	 *            the rentabilidad view
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the rentabilidad total request entity
	 */
	private RentabilidadTotalRequestEntity armarRequestObtenerRentabilidadTotal(Cliente cliente,
			RentabilidadTotalInView rentabilidadView, Boolean esBancaPrivada) {

		RentabilidadTotalRequestEntity request = new RentabilidadTotalRequestEntity();
		request.setCanal(esBancaPrivada ? canalTipoBP : canalTipo);
		request.setSubCanal(esBancaPrivada ? subcanalTipoBP : subcanalTipo);
		request.setDato(PRUEBA);
		request.setDatosFirmados(0);
		request.getDatos().setNup(cliente.getNup());
		request.getDatos().setSegmento(esBancaPrivada ? "BP" : "RTL");
		request.getDatos().setCarteraConsulta(rentabilidadView.getCarteraAConsultar());
		request.getDatos().setCodigoPeriodo(null);
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setListaCuentas(null);
		request.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return request;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerFiltroCartera(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<FiltroCarteraDTO> obtenerFiltroCartera(Cliente cliente, TipoBancaEnum tipoBancaEnum) {

		FiltroCarteraDTO resultado = new FiltroCarteraDTO();

		try {
			FiltroCarteraEntity rta = rendimientoTenenciaDAO
					.obtenerFiltroCartera(createRequestFiltroCartera(cliente, tipoBancaEnum));
			resultado = crearDTO(rta);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(FiltroCarteraDTO.class, resultado);
	}

	/**
	 * Creates the request filtro cartera.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the obtener filtro cartera request entity
	 */
	private ObtenerFiltroCarteraRequestEntity createRequestFiltroCartera(Cliente cliente, TipoBancaEnum tipoBancaEnum) {

		ObtenerFiltroCarteraRequestEntity request = new ObtenerFiltroCarteraRequestEntity();
		request.setCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? canalTipoBP : canalTipo);
		request.setSubCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? subcanalTipoBP : subcanalTipo);
		request.setDato(PRUEBA);
		request.setDatosFirmados(0);
		request.getDatos().setNup(cliente.getNup());
		request.getDatos()
				.setSegmento(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? BANCA_PRIVADA : BANCA_PERSONAL);
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		request.getDatos().setIp(NetworkUtil.getHostAddress());

		request.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return request;
	}

	/**
	 * Crear DTO.
	 *
	 * @param rta
	 *            the rta
	 * @return the filtro cartera DTO
	 */
	/*
	 * Cargar el DTO del Filtro de Cartera
	 */
	private FiltroCarteraDTO crearDTO(FiltroCarteraEntity rta) {

		FiltroCarteraDTO dto = new FiltroCarteraDTO();

		dto.setMultiseleccion("S".equalsIgnoreCase(rta.getDatos().getResultado().getMultiseleccion()) ? true : false);

		for (CarteraAConsultar item : rta.getDatos().getResultado().getResultadoCarteraCliente()) {

			ResultadoCarteraCliente itemFinal = new ResultadoCarteraCliente();
			itemFinal.setCuenta(new CuentaACView());

			itemFinal.setCodigoCartera(item.getCodigoCartera());
			itemFinal.setDescripcionCartera(formatearDescripcionCartera(item));
			if (item.getCuenta() != null) {
				itemFinal.getCuenta().setNumeroCuenta(formatearNumeroCuenta(item.getCuenta().getNumeroCuenta()));
				itemFinal.getCuenta().setSucursal(item.getCuenta().getSucursal());
			}
			itemFinal.setPorDefecto("S".equalsIgnoreCase(item.getPorDefecto()) ? true : false);

			dto.getResultadoCarteraCliente().add(itemFinal);
		}

		return dto;

	}

	/**
	 * Formatear descripcion cartera.
	 *
	 * @param item
	 *            the item
	 * @return the string
	 */
	private String formatearDescripcionCartera(CarteraAConsultar item) {
		String descripcionCarteraFormateado;
		if (item.getCodigoCartera().equals(CODIGO_CUENTA)) {
			descripcionCarteraFormateado = "Cuenta título";
		} else if (item.getCodigoCartera().equals(CODIGO_PLAZO_FIJO)) {
			descripcionCarteraFormateado = "Plazo Fijo";
		} else {
			descripcionCarteraFormateado = "Cartera total";
		}
		return descripcionCarteraFormateado;
	}

	/**
	 * Formatear numero cuenta.
	 *
	 * @param numeroCuentaSinFormato
	 *            the numero cuenta sin formato
	 * @return the string
	 */
	private String formatearNumeroCuenta(String numeroCuentaSinFormato) {

		String numeroCuentaPrimeraParte = numeroCuentaSinFormato.substring(0, numeroCuentaSinFormato.length() - 1);
		String numeroCuentaSegundaParte = numeroCuentaSinFormato.substring(numeroCuentaSinFormato.length() - 1,
				numeroCuentaSinFormato.length());

		return numeroCuentaPrimeraParte + "/" + numeroCuentaSegundaParte;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerFiltroPorFecha(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<FiltroPorFechaDTO> obtenerFiltroPorFecha(Cliente cliente, FiltroPorFechaRequestView request,
			TipoBancaEnum tipoBancaEnum) {

		FiltroPorFechaDTO resultado = new FiltroPorFechaDTO();

		try {
			FiltroPorFechaEntity rta = rendimientoTenenciaDAO
					.obtenerFiltroPorFecha(crearRequestFiltroPorFecha(cliente, request, tipoBancaEnum));
			resultado = crearDTO(rta);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(FiltroPorFechaDTO.class, resultado);
	}

	/**
	 * Crear request filtro por fecha.
	 *
	 * @param cliente
	 *            the cliente
	 * @param requestView
	 *            the request view
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the obtener filtro por fecha request entity
	 */
	private ObtenerFiltroPorFechaRequestEntity crearRequestFiltroPorFecha(Cliente cliente,
			FiltroPorFechaRequestView requestView, TipoBancaEnum tipoBancaEnum) {

		ObtenerFiltroPorFechaRequestEntity request = new ObtenerFiltroPorFechaRequestEntity();
		request.setCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? canalTipoBP : canalTipo);
		request.setSubCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? subcanalTipoBP : subcanalTipo);
		request.setDato(PRUEBA);
		request.setDatosFirmados(0);
		request.getDatos().setNup(cliente.getNup());
		request.getDatos()
				.setSegmento(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBancaEnum) ? BANCA_PRIVADA : BANCA_PERSONAL);
		request.getDatos().setCarteraAConsultar(requestView.getCarteraAConsultar());
		request.getDatos().setMoneda(requestView.getMoneda());
		request.getDatos()
				.setListaCuentas(armarListaCuentas(requestView.getCarteraAConsultar(), requestView.getListaCuentas()));
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		request.getDatos().setIp(NetworkUtil.getHostAddress());

		request.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return request;
	}

	/**
	 * Crear DTO.
	 *
	 * @param rta
	 *            the rta
	 * @return the filtro por fecha DTO
	 */
	/*
	 * Cargar el DTO del Filtro de Fecha
	 */
	private FiltroPorFechaDTO crearDTO(FiltroPorFechaEntity rta) {

		FiltroPorFechaDTO dto = new FiltroPorFechaDTO();
		List<ListaFiltradaPorFecha> listaItemResultado = new ArrayList<ListaFiltradaPorFecha>();

		for (ResultadoFiltroPorFechaEntity item : rta.getDatos().getResultado()) {
			if ("S".equalsIgnoreCase(item.getDisponibilidadInformacion())) {
				ListaFiltradaPorFecha itemResultado = setearLabelsPeriodo(item);
				itemResultado.setCodigoPeriodo(item.getCodigoPeriodo());
				itemResultado.setDisponibilidadInformacion(
						"S".equalsIgnoreCase(item.getDisponibilidadInformacion()) ? true : false);
				itemResultado.setDisponibilidadPeriodo(item.getDisponibilidadPeriodo());
				itemResultado.setFechaInicio(fechaInicioFin(item.getFechaInicio()));
				itemResultado.setFechaFin(fechaInicioFin(item.getFechaFin()));
				itemResultado.setLeyenda(item.getLeyenda());
				itemResultado.setListaMonedas(armarListaMonedas(item.getListaMonedas(), item.getMonedaDefecto()));
				itemResultado.setPorDefecto("S".equalsIgnoreCase(item.getPorDefecto()) ? true : false);
				itemResultado.setFechaRentabilidad(fechaInicioFin(item.getFechaInfoDisponible()));
				listaItemResultado.add(itemResultado);
			}
		}
		dto.setPeriodos(listaItemResultado);
		return dto;
	}

	/**
	 * Setear labels periodo.
	 *
	 * @param item
	 *            the item
	 * @return the lista filtrada por fecha
	 */
	private ListaFiltradaPorFecha setearLabelsPeriodo(ResultadoFiltroPorFechaEntity item) {
		ListaFiltradaPorFecha itemResultado = new ListaFiltradaPorFecha();

		if (CODIGO_30_DIAS.equals(item.getCodigoPeriodo())) {
			itemResultado.setDescripcionPeriodo("Últimos 30 días");
			itemResultado.setEtiquetaPeriodoCombo("30 días");
		} else if (CODIGO_60_DIAS.equals(item.getCodigoPeriodo())) {
			itemResultado.setDescripcionPeriodo("Últimos 60 días");
			itemResultado.setEtiquetaPeriodoCombo("60 días");
		} else if (CODIGO_90_DIAS.equals(item.getCodigoPeriodo())) {
			itemResultado.setDescripcionPeriodo("Últimos 90 días");
			itemResultado.setEtiquetaPeriodoCombo("90 días");
		} else if (CODIGO_365_DIAS.equals(item.getCodigoPeriodo())) {
			itemResultado.setDescripcionPeriodo("Últimos 365 días");
			itemResultado.setEtiquetaPeriodoCombo("365 días");
		} else if (CODIGO_ANIO.equals(item.getCodigoPeriodo())) {
			itemResultado.setDescripcionPeriodo("Año en curso");
			itemResultado.setEtiquetaPeriodoCombo("Año en curso");
		} else {
			itemResultado.setDescripcionPeriodo("Otro intervalo");
		}

		return itemResultado;
	}

	/**
	 * Fecha inicio fin.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String fechaInicioFin(String fecha) {

		try {
			fecha = fecha.substring(0, 10);
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			return outputFormat.format(date1);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Armar lista monedas.
	 *
	 * @param listaMonedas
	 *            the lista monedas
	 * @param monedaDefecto
	 *            the moneda defecto
	 * @return the list
	 */
	private List<MonedaACView> armarListaMonedas(List<String> listaMonedas, String monedaDefecto) {

		List<MonedaACView> listaMonedasView = new ArrayList<MonedaACView>();

		for (String moneda : listaMonedas) {
			MonedaACView monedaView = armarObjetoMonedaView(moneda);
			if (moneda.equals(monedaDefecto)) {
				monedaView.setDefecto(Boolean.TRUE);
			}
			listaMonedasView.add(monedaView);
		}
		return listaMonedasView;
	}

	/**
	 * Armar objeto moneda view.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the moneda AC view
	 */
	private MonedaACView armarObjetoMonedaView(String moneda) {
		MonedaACView monedaView = new MonedaACView();
		monedaView.setId(moneda);
		if (TENENCIA_PESOS.equals(moneda)) {
			monedaView.setDescripcion("Tenencia en pesos");
		} else if (TENENCIA_DOLARES.equals(moneda)) {
			monedaView.setDescripcion("Tenencia en dólares");
		} else if (TOTAL_REEXPRESADO_PESOS.equals(moneda)) {
			monedaView.setDescripcion("Total reexpresado en pesos");
		} else {
			monedaView.setDescripcion("Total reexpresado en dólares");
		}
		return monedaView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerRentabilidadPeriodo(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<RentabilidadPeriodoDTO> obtenerRentabilidadPeriodo(Cliente cliente,
			RentabilidadPeriodoRequestView rentabilidadPeriodoView, TipoBancaEnum tipoBanca) {

		RentabilidadPeriodoDTO rentabilidadPeriodoDTO = new RentabilidadPeriodoDTO();
		if (rentabilidadPeriodoView.getPeriodoFechaFin() == null) {
			rentabilidadPeriodoView.setPeriodoFechaFin(rentabilidadPeriodoView.getPeriodoFechaInicial());
		}
		RentabilidadPeriodoRequestEntity request = armarRequestObtenerRentabilidadPeriodo(cliente,
				rentabilidadPeriodoView, tipoBanca);
		RentabilidadPeriodoEntity rentabilidadPeriodoEntity = new RentabilidadPeriodoEntity();

		try {
			rentabilidadPeriodoEntity = rendimientoTenenciaDAO.obtenerRentabilidadPeriodo(request);
			rentabilidadPeriodoDTO.setDatos(rentabilidadPeriodoEntity.getDatos().getResultado());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(RentabilidadPeriodoDTO.class, rentabilidadPeriodoDTO);
	}

	/**
	 * Armar request obtener rentabilidad periodo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param rentabilidadPeriodoRequestView
	 *            the rentabilidad periodo request view
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the rentabilidad periodo request entity
	 */
	private RentabilidadPeriodoRequestEntity armarRequestObtenerRentabilidadPeriodo(Cliente cliente,
			RentabilidadPeriodoRequestView rentabilidadPeriodoRequestView, TipoBancaEnum tipoBanca) {

		RentabilidadPeriodoRequestEntity request = new RentabilidadPeriodoRequestEntity();

		request.setCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? canalTipoBP : canalTipo);
		request.setSubCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? subcanalTipoBP : subcanalTipo);
		request.setDato(PRUEBA);
		request.setDatosFirmados(0);
		request.getDatos().setNup(cliente.getNup());
		request.getDatos()
				.setSegmento(TipoBancaEnum.BANCA_PRIVADA.equals(rentabilidadPeriodoRequestView.getTipoBancaEnum())
						? BANCA_PRIVADA : BANCA_PERSONAL);
		request.getDatos().setCarteraConsulta(rentabilidadPeriodoRequestView.getCarteraAConsultar());
		request.getDatos().setListaCuentas(armarListaCuentas(rentabilidadPeriodoRequestView.getCarteraAConsultar(),
				rentabilidadPeriodoRequestView.getListaCuentas()));
		request.getDatos().setMoneda(rentabilidadPeriodoRequestView.getMoneda());
		request.getDatos().setCodigoPeriodo(rentabilidadPeriodoRequestView.getPeriodo());
		request.getDatos().setClasificacion(rentabilidadPeriodoRequestView.getClasificacion());
		if (CODIGO_FECHA_PERIODO.equals(rentabilidadPeriodoRequestView.getPeriodo())) {
			request.getDatos().setPeriodoFechaInicial(
					rentabilidadPeriodoRequestView.getPeriodoFechaInicial() + PARTE_FINAL_STRING_FECHA_SERVICIO);
			request.getDatos().setPeriodoFechaFinal(
					rentabilidadPeriodoRequestView.getPeriodoFechaFin() + PARTE_FINAL_STRING_FECHA_SERVICIO);
			request.getDatos()
					.setPeriodoFechaInicial(formatearFecha(rentabilidadPeriodoRequestView.getPeriodoFechaInicial()));
			request.getDatos()
					.setPeriodoFechaFinal(formatearFecha(rentabilidadPeriodoRequestView.getPeriodoFechaFin()));
		}
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return request;

	}

	/**
	 * Armar datos servicios.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the datos servicios entity
	 */
	private DatosServiciosEntity armarDatosServicios(Cliente cliente) {

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();

		datosServicios.setUsuarioId(cliente.getUsuarioRacf());
		datosServicios.setUsuarioPwd(cliente.getPasswordRacf());
		datosServicios.setTipoId(cliente.getTipoDocumento());
		datosServicios.setIdCliente(cliente.getDni());
		datosServicios.setFechaNac(cliente.getFechaNacimiento());

		return datosServicios;

	}

	/**
	 * Formatear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String formatearFecha(String fecha) {

		return fecha.substring(6, fecha.length()) + "-" + fecha.substring(3, 5) + "-" + fecha.substring(0, 2)
				+ PARTE_FINAL_STRING_FECHA_SERVICIO;
	}

	/**
	 * Armar lista cuentas.
	 *
	 * @param carteraAConsultar
	 *            the cartera A consultar
	 * @param listaCuentas
	 *            the lista cuentas
	 * @return the list
	 */
	private List<CuentaOPEntity> armarListaCuentas(String carteraAConsultar, List<CuentaACView> listaCuentas) {

		List<CuentaOPEntity> listaCuentasRequest = new ArrayList<CuentaOPEntity>();
		if (CODIGO_CUENTA.equals(carteraAConsultar) || CODIGO_CUENTA_Y_PF.equals(carteraAConsultar)) {
			for (CuentaACView cuenta : listaCuentas) {
				CuentaOPEntity cuentaEntity = new CuentaOPEntity();
				cuentaEntity.setNroCuentaOP(cuenta.getNumeroCuenta().replaceAll("/", ""));
				cuentaEntity.setSucursal("0" + cuenta.getSucursal());
				listaCuentasRequest.add(cuentaEntity);
			}
		}

		if (listaCuentasRequest.isEmpty()) {
			listaCuentasRequest = null;
		}

		return listaCuentasRequest;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#armarListaEscalaGraficoDistribucionRentabilidad(java.math.BigDecimal)
	 */
	@Override
	public List<String> armarListaEscalaGraficoDistribucionRentabilidad(BigDecimal valorMaximo) {

		List<BigDecimal> bigListaEscala = new ArrayList<BigDecimal>();

		bigListaEscala.add(valorMaximo);
		bigListaEscala.add(valorMaximo.divide(new BigDecimal("2")));

		List<String> listaEscalas = new ArrayList<String>();
		String numeroEscala;

		for (BigDecimal bigNumeroEscala : bigListaEscala) {

			if (bigNumeroEscala.compareTo(new BigDecimal(MILLON)) == (1)
					|| bigNumeroEscala.compareTo(new BigDecimal(MILLON)) == (0)) {
				BigDecimal valor = bigNumeroEscala.divide(new BigDecimal(MILLON)).setScale(1, RoundingMode.DOWN);
				String[] valorArray = valor.toString().split("\\.");
				if ("0".equals(valorArray[1])) {
					numeroEscala = valorArray[0] + "M";
				} else {
					numeroEscala = valor.toString() + "M";
				}
			} else if (bigNumeroEscala.compareTo(new BigDecimal(MIL)) == (1)
					|| bigNumeroEscala.compareTo(new BigDecimal(MIL)) == (0)) {
				BigDecimal valor = bigNumeroEscala.divide(new BigDecimal(MIL)).setScale(1, RoundingMode.DOWN);
				String[] valorArray = valor.toString().split("\\.");
				if ("0".equals(valorArray[1])) {
					numeroEscala = valorArray[0] + "K";
				} else {
					numeroEscala = valor.toString() + "K";
				}
			} else {
				String[] valorArray = bigNumeroEscala.toString().split("\\.");
				numeroEscala = valorArray[0];
			}
			listaEscalas.add(numeroEscala);
		}
		listaEscalas.add("0");
		listaEscalas.add("-" + listaEscalas.get(1));
		listaEscalas.add("-" + listaEscalas.get(0));

		return listaEscalas;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerFiltroRentabilidad(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<AperturaGraficaDTO> obtenerFiltroRentabilidad(Cliente cliente,
			RequestDashboard requestDashboardView, TipoBancaEnum tipoBanca) {

		AperturaGraficaDTO aperturaGraficaDTO = new AperturaGraficaDTO();
		AperturaGraficaRequestEntity aperturaGraficaRequestEntity = armarRequestAperturaGrafica(cliente,
				requestDashboardView, tipoBanca);
		try {
			AperturaGraficaEntity aperturaGraficaEntity = rendimientoTenenciaDAO
					.obtenerAperturaGrafica(aperturaGraficaRequestEntity);
			aperturaGraficaDTO.setListaResultado(aperturaGraficaEntity.getDatos().getResultado());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(aperturaGraficaDTO);

	}

	/**
	 * Armar request apertura grafica.
	 *
	 * @param cliente
	 *            the cliente
	 * @param requestDashboardView
	 *            the request dashboard view
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the apertura grafica request entity
	 */
	private AperturaGraficaRequestEntity armarRequestAperturaGrafica(Cliente cliente,
			RequestDashboard requestDashboardView, TipoBancaEnum tipoBanca) {

		AperturaGraficaRequestEntity request = new AperturaGraficaRequestEntity();

		request.setCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? canalTipoBP : canalTipo);
		request.setSubCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? subcanalTipoBP : subcanalTipo);
		request.setDato(PRUEBA);
		request.setDatosFirmados(0);
		request.getDatos().setNup(cliente.getNup());
		request.getDatos().setSegmento(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? BANCA_PRIVADA : BANCA_PERSONAL);
		request.getDatos().setCarteraAConsultar(requestDashboardView.getCarteraAConsultar());
		request.getDatos().setListaCuentas(
				armarListaCuentas(requestDashboardView.getCarteraAConsultar(), requestDashboardView.getListaCuentas()));
		request.getDatos().setMoneda(requestDashboardView.getMoneda());
		request.getDatos().setCodigoPeriodo(requestDashboardView.getPeriodo());
		if (CODIGO_FECHA_PERIODO.equals(requestDashboardView.getPeriodo())) {
			request.getDatos().setPeriodoFechaInicial(formatearFecha(requestDashboardView.getPeriodoFechaInicial()));
			request.getDatos().setPeriodoFechaFinal(formatearFecha(requestDashboardView.getPeriodoFechaFin()));
		}
		request.getDatos().setUsuario(cliente.getUsuarioRacf());
		request.getDatos().setIp(NetworkUtil.getHostAddress());
		request.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return request;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerGrillasDTO(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum, boolean)
	 */
	@Override
	public Respuesta<GrillasProductosDTO> obtenerGrillasDTO(Cliente cliente, RequestDashboard request,
			TipoBancaEnum tipoBanca, boolean isMobile) {
		DetalleRentabilidadEntity responseDao;
		if (isMobile) {
			request.setClasificacion(clasificacionGrillaMobile);
			request.setSubclasificacion(null);
		}
		try {
			responseDao = rendimientoTenenciaDAO
					.obtenerDetalleRentabilidad(armarRequestDetalleRentabilidad(request, tipoBanca, cliente));
		} catch (DAOException e) {
			LOGGER.error("Error al consultar el servicio de rentabilidad", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}
		return respuestaFactory.crearRespuestaOk(armarRespuestaGrillas(responseDao.getDatos().getResultado(), isMobile));
	}

	/**
	 * Armar request detalle rentabilidad.
	 *
	 * @param requestView
	 *            the request view
	 * @param tipoBanca
	 *            the tipo banca
	 * @param cliente
	 *            the cliente
	 * @return the detalle rentabilidad request entity
	 */
	private DetalleRentabilidadRequestEntity armarRequestDetalleRentabilidad(RequestDashboard requestView,
			TipoBancaEnum tipoBanca, Cliente cliente) {
		DetalleRentabilidadRequestEntity request = new DetalleRentabilidadRequestEntity();
		request.setCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? canalTipo : canalTipoBP);
		request.setSubCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? subcanalTipo : subcanalTipoBP);
		DatosDetalleRentabilidad datos = new DatosDetalleRentabilidad();
		datos.setNup(cliente.getNup());
		datos.setSegmento(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? BANCA_PERSONAL : BANCA_PRIVADA);
		datos.setListaCuentas(armarListaCuentas(requestView.getCarteraAConsultar(), requestView.getListaCuentas()));
		datos.setCarteraConsulta(requestView.getCarteraAConsultar());
		datos.setCodigoPeriodo(requestView.getPeriodo());
		if (CODIGO_FECHA_PERIODO.equals(requestView.getPeriodo())) {
			datos.setPeriodoFechaInicial(formatearFecha(requestView.getPeriodoFechaInicial()));
			datos.setPeriodoFechaFinal(formatearFecha(requestView.getPeriodoFechaFin()));
		}
		datos.setMoneda(requestView.getMoneda());
		datos.setClasificacion(requestView.getClasificacion());
		datos.setSubClasificacion(requestView.getSubclasificacion());
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(System.getProperty("user.name"));
		datos.setDatosServicios(armarDatosServicios(cliente));
		request.setDatos(datos);
		return request;
	}

	/**
	 * Armar respuesta grillas.
	 *
	 * @param responseDao
	 *            the response dao
	 * @param isMobile
	 *            the is mobile
	 * @return the grillas productos DTO
	 */
	private GrillasProductosDTO armarRespuestaGrillas(ResultadoDetalleRentabilidadEntity responseDao, boolean isMobile) {
		GrillasProductosDTO grillasDTO = new GrillasProductosDTO();
		
		if(!isMobile){
			grillasDTO = armarListaGraficoCircular(responseDao);
		}

		for (DetallePorSubCabecera detalle : responseDao.getResultadoSubCabecera()) {
			DetalleProductoView detalleProductoGrilla = new DetalleProductoView();
			detalleProductoGrilla.setCodigoProducto(detalle.getCodigoSubCabecera());
			detalleProductoGrilla.setDescripcionProducto(detalle.getDescripcionSubCabecera());
			detalleProductoGrilla.setRendimientoCabecera(detalle.getRendimiento());
			detalleProductoGrilla.setRendimientoCabeceraTxt(
					ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(detalle.getRendimiento())) + " %");

			detalleProductoGrilla
					.setRentabilidadCabecera(DivisaEnumCartera.fromCodigoString(responseDao.getMoneda()).getSimbolo() + " "
							+ ISBANStringUtils.formatearSaldoConSigno((detalle.getRentabilidad())));

			for (DetallePorItem detalleSubCabecera : detalle.getResultadoDetalle()) {
				DetalleItemProducto detalleItem = new DetalleItemProducto();
				detalleItem.setCodigoProducto(detalleSubCabecera.getCodigoItem());
				detalleItem.setDescripcionProducto(detalleSubCabecera.getDescripcionItem());
				detalleItem.setRendimiento(detalleSubCabecera.getRendimiento());
				detalleItem.setRendimientoTxt(ISBANStringUtils
						.formatearSaldoConSigno(detalleSubCabecera.getRendimiento()) + " %");
				detalleItem.setRendimientoTxTMobile(ISBANStringUtils
						.formatearSaldoConSigno(detalleSubCabecera.getRendimiento()) + "%");
				detalleItem.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(responseDao.getMoneda()).getSimbolo() + " "
						+ ISBANStringUtils.formatearSaldoConSigno(detalleSubCabecera.getRentabilidadNeta()));
				detalleItem.setRentabilidadNoRealizada(DivisaEnumCartera.fromCodigoString(responseDao.getMoneda()).getSimbolo()
						+ " "
						+ ISBANStringUtils.formatearSaldoConSigno(detalleSubCabecera.getRentabilidadNoRealizada()));
				detalleItem.setRentabilidadRealizada(DivisaEnumCartera.fromCodigoString(responseDao.getMoneda()).getSimbolo()
						+ " " + ISBANStringUtils.formatearSaldoConSigno(detalleSubCabecera.getRentabilidadRealizada()));
				detalleItem.setTna(ISBANStringUtils.formatearSaldoConSigno(detalleSubCabecera.getTna()) + " %");
				detalleItem.setTnaMobile(ISBANStringUtils.formatearSaldoConSigno(detalleSubCabecera.getTna()) + "%");
				detalleItem.setPorcentaje(
						ISBANStringUtils.formatearConComaYDosDecimales(detalleSubCabecera.getDistribucion()) + " %");
				detalleProductoGrilla.getListDetalleProducto().add(detalleItem);
			}
			grillasDTO.getListaProductos().add(detalleProductoGrilla);
		}
		return grillasDTO;

	}

	/**
	 * Armar lista grafico circular.
	 *
	 * @param responseDao
	 *            the response dao
	 * @return the grillas productos DTO
	 */
	private GrillasProductosDTO armarListaGraficoCircular(ResultadoDetalleRentabilidadEntity responseDao) {
		GrillasProductosDTO dto = new GrillasProductosDTO();
		List<GraficoCircularView> lista = new ArrayList<GraficoCircularView>();
		for (DetallePorCabecera cabecera : responseDao.getResultadoCabecera()) {
			if("TOT".equals(cabecera.getCodigoCabecera())){
				dto = setearLogicaLlamados(dto, cabecera.getGraficoDisponibleRend(), cabecera.getGraficoDisponibleRent());
				if (responseDao.getResultadoCabecera().size() == 2) {
					continue;
				}
			}
			GraficoCircularView graficoCircular = new GraficoCircularView();
			graficoCircular.setIdProducto(cabecera.getCodigoCabecera());
			graficoCircular.setDescripcionProducto(cabecera.getDescripcionCabecera());
			graficoCircular.setRentabilidad(DivisaEnumCartera.fromCodigoString(responseDao.getMoneda()).getSimbolo() + " "
					+ ISBANStringUtils.formatearSaldoConSigno(cabecera.getRentabilidadNeta()));
			graficoCircular.setRendimiento(cabecera.getRendimiento());
			graficoCircular.setRendimientoTxt(
					ISBANStringUtils.formatearSaldoConSigno(cabecera.getRendimiento()) + " %");
			graficoCircular.setPorcentajeGrafico(cabecera.getDistribucionRent());
			lista.add(graficoCircular);
		}
		dto.setListaGraficoCircular(lista);
		return dto;
	}

	/**
	 * Setear logica llamados.
	 *
	 * @param dto
	 *            the dto
	 * @param graficoDisponibleRend
	 *            the grafico disponible rend
	 * @param graficoDisponibleRent
	 *            the grafico disponible rent
	 * @return the grillas productos DTO
	 */
	private GrillasProductosDTO setearLogicaLlamados(GrillasProductosDTO dto, String graficoDisponibleRend,
			String graficoDisponibleRent) {
		if("S".equalsIgnoreCase(graficoDisponibleRend)){
			dto.setLlamarRendimiento(true);
		}
		if("S".equalsIgnoreCase(graficoDisponibleRent)){
			dto.setLlamarRentabilidad(true);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerGraficoRendimiento(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(Cliente cliente, RequestDashboard request,
			TipoBancaEnum tipoBanca) {
		GraficoRendimientoEntity rta = new GraficoRendimientoEntity();
		try {
			rta = rendimientoTenenciaDAO
					.obtenerGraficoRendimiento(armarRequestGraficoRendimiento(cliente, request, tipoBanca));
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		return respuestaFactory.crearRespuestaOk(armarRespuestaGraficoRendimiento(rta));
	}

	/**
	 * Armar respuesta grafico rendimiento.
	 *
	 * @param rta
	 *            the rta
	 * @return the grafico rendimiento view
	 */
	private GraficoRendimientoView armarRespuestaGraficoRendimiento(GraficoRendimientoEntity rta) {
		GraficoRendimientoView graficoRendimiento = new GraficoRendimientoView();
		Date fechaMin = null;
		Date fechaMax = null;
		for (GraficoRendimientoRespuesta graficoRespuesta : rta.getDatos().getResultado()) {

			if (rta.getDatos().getResultado().size() == 2 && "TOT".equals(graficoRespuesta.getCodigoCabecera())) {
				continue;
			}

			ProductosGraficoRendimiento prod = new ProductosGraficoRendimiento();
			prod.setProducto(graficoRespuesta.getDescripcionCabecera());
			prod.setIdProducto(graficoRespuesta.getCodigoCabecera());

			for (GraficoRendPorFecha fecha : graficoRespuesta.getDetalle()) {
				ValoresProductos values = new ValoresProductos();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = format.parse(fecha.getFecha().substring(0, 10));
					if (fechaMin == null || fechaMin.compareTo(date) > 0) {
						fechaMin = date;
					}

					if (fechaMax == null || fechaMax.compareTo(date) < 0) {
						fechaMax = date;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

				values.setT(fecha.getFecha());
				values.setY(fecha.getRendimiento());
				prod.getValues().add(values);
			}
			graficoRendimiento.getListaProductos().add(prod);

		}
		graficoRendimiento.setFechas(generarFechas(fechaMin, fechaMax));
		return graficoRendimiento;
	}

	/**
	 * Generar fechas.
	 *
	 * @param fechaMin
	 *            the fecha min
	 * @param fechaMax
	 *            the fecha max
	 * @return the list
	 */
	private List<String> generarFechas(Date fechaMin, Date fechaMax) {
		List<String> lista = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int dias = (int) ((fechaMax.getTime() - fechaMin.getTime()) / 86400000);
		for (int i = 0; i <= dias; i++) {
			lista.add(sdf.format(calcularDias(fechaMin, i)));
		}
		return lista;
	}

	/**
	 * Calcular dias.
	 *
	 * @param fecha
	 *            the fecha
	 * @param cantDias
	 *            the cant dias
	 * @return the date
	 */
	private Date calcularDias(Date fecha, int cantDias) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		fechaCalendar.set(Calendar.HOUR, 0);
		fechaCalendar.set(Calendar.MINUTE, 0);
		fechaCalendar.set(Calendar.SECOND, 0);
		fechaCalendar.set(Calendar.MILLISECOND, 0);
		fechaCalendar.add(Calendar.DAY_OF_YEAR, cantDias);
		return fechaCalendar.getTime();
	}

	/**
	 * Armar request grafico rendimiento.
	 *
	 * @param cliente
	 *            the cliente
	 * @param requestView
	 *            the request view
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the apertura grafica request entity
	 */
	AperturaGraficaRequestEntity armarRequestGraficoRendimiento(Cliente cliente, RequestDashboard requestView,
			TipoBancaEnum tipoBanca) {
		AperturaGraficaRequestEntity request = new AperturaGraficaRequestEntity();
		request.setCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? canalTipo : canalTipoBP);
		request.setSubCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? subcanalTipo : subcanalTipoBP);
		DatosAperturaGraficaRequest datos = new DatosAperturaGraficaRequest();
		datos.setNup(cliente.getNup());
		datos.setSegmento(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? BANCA_PERSONAL : BANCA_PRIVADA);
		datos.setListaCuentas(armarListaCuentas(requestView.getCarteraAConsultar(), requestView.getListaCuentas()));
		datos.setCarteraAConsultar(requestView.getCarteraAConsultar());
		datos.setCodigoPeriodo(requestView.getPeriodo());
		if (CODIGO_FECHA_PERIODO.equals(requestView.getPeriodo())) {
			datos.setPeriodoFechaInicial(formatearFecha(requestView.getPeriodoFechaInicial()));
			datos.setPeriodoFechaFinal(formatearFecha(requestView.getPeriodoFechaFin()));
		}
		datos.setMoneda(requestView.getMoneda());
		datos.setClasificacion(requestView.getClasificacion());
		datos.setSubclasificacion(requestView.getSubclasificacion());
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(System.getProperty("user.name"));
		datos.setDatosServicios(armarDatosServicios(cliente));
		request.setDatos(datos);
		return request;

	}

	/**
	 * Armar request grafico rentabilidad.
	 *
	 * @param cliente
	 *            the cliente
	 * @param requestView
	 *            the request view
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the grafico rentabilidad request entity
	 */
	GraficoRentabilidadRequestEntity armarRequestGraficoRentabilidad(Cliente cliente, RequestDashboard requestView,
			TipoBancaEnum tipoBanca) {
		GraficoRentabilidadRequestEntity request = new GraficoRentabilidadRequestEntity();
		request.setCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? canalTipo : canalTipoBP);
		request.setSubCanal(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? subcanalTipo : subcanalTipoBP);
		DatosGraficoRentabilidad datos = new DatosGraficoRentabilidad();
		datos.setNup(cliente.getNup());
		datos.setSegmento(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) ? BANCA_PERSONAL : BANCA_PRIVADA);
		datos.setListaCuentas(armarListaCuentas(requestView.getCarteraAConsultar(), requestView.getListaCuentas()));
		datos.setCarteraAConsultar(requestView.getCarteraAConsultar());
		datos.setCodigoPeriodo(requestView.getPeriodo());
		if (CODIGO_FECHA_PERIODO.equals(requestView.getPeriodo())) {
			datos.setPeriodoFechaInicial(formatearFecha(requestView.getPeriodoFechaInicial()));
			datos.setPeriodoFechaFinal(formatearFecha(requestView.getPeriodoFechaFin()));
		}
		datos.setMoneda(requestView.getMoneda());
		datos.setClasificacion(requestView.getClasificacion());
		datos.setSubclasificacion(requestView.getSubclasificacion());
		datos.setIp(NetworkUtil.getHostAddress());
		datos.setUsuario(System.getProperty("user.name"));
		datos.setDatosServicios(armarDatosServicios(cliente));
		request.setDatos(datos);
		return request;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerDetalleSubClasificacion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<DetalleSubclasificionDTO> obtenerDetalleSubClasificacion(Cliente cliente, RequestDashboard request,
			TipoBancaEnum tipoBanca) {

		DetalleSubclasificionDTO detalleSubclasificacionDTO = new DetalleSubclasificionDTO();
		DetalleSubclasificacionRequestEntity requestEntity = armarRequestDetalleSubclasificacion(cliente, request,
				tipoBanca);

		try {
			DetalleSubclasificacionEntity respuestaEntity = rendimientoTenenciaDAO
					.obtenerDetalleSubclasificacion(requestEntity);
			respuestaEntity.getDatos().getResultado().setPeriodoFechaInicial(
					fechaInicioFin(respuestaEntity.getDatos().getResultado().getPeriodoFechaInicial()));
			respuestaEntity.getDatos().getResultado().setPeriodoFechaFinal(
					fechaInicioFin(respuestaEntity.getDatos().getResultado().getPeriodoFechaFinal()));

			detalleSubclasificacionDTO.setResultado(respuestaEntity.getDatos().getResultado());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
		}

		return respuestaFactory.crearRespuestaOk(detalleSubclasificacionDTO);

	}

	/**
	 * Armar request detalle subclasificacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the detalle subclasificacion request entity
	 */
	private DetalleSubclasificacionRequestEntity armarRequestDetalleSubclasificacion(Cliente cliente,
			RequestDashboard request, TipoBancaEnum tipoBanca) {

		DetalleSubclasificacionRequestEntity requestEntity = new DetalleSubclasificacionRequestEntity();

		requestEntity.setCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? canalTipoBP : canalTipo);
		requestEntity.setSubCanal(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? subcanalTipoBP : subcanalTipo);
		requestEntity.setDato(PRUEBA);
		requestEntity.setDatosFirmados(0);
		requestEntity.getDatos().setNup(cliente.getNup());
		requestEntity.getDatos()
				.setSegmento(TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) ? BANCA_PRIVADA : BANCA_PERSONAL);
		requestEntity.getDatos().setCarteraAConsultar(request.getCarteraAConsultar());
		requestEntity.getDatos()
				.setListaCuentas(armarListaCuentas(request.getCarteraAConsultar(), request.getListaCuentas()));
		requestEntity.getDatos().setMoneda(request.getMoneda());
		requestEntity.getDatos().setCodigoPeriodo(request.getPeriodo());
		requestEntity.getDatos().setClasificacion(request.getClasificacion());
		requestEntity.getDatos().setSubclasificacion(request.getCategoria());
		if (CODIGO_FECHA_PERIODO.equals(request.getPeriodo())) {
			requestEntity.getDatos().setPeriodoFechaInicial(formatearFecha(request.getPeriodoFechaInicial()));
			requestEntity.getDatos().setPeriodoFechaFinal(formatearFecha(request.getPeriodoFechaFin()));
		}
		requestEntity.getDatos().setUsuario(cliente.getUsuarioRacf());
		requestEntity.getDatos().setIp(NetworkUtil.getHostAddress());
		requestEntity.getDatos().setDatosServicios(armarDatosServicios(cliente));

		return requestEntity;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO#obtenerGraficoRentabilidad(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard, ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum)
	 */
	@Override
	public Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidad(Cliente cliente, RequestDashboard request,
			TipoBancaEnum tipoBanca) {
		GraficoRentabilidadEntity rta = new GraficoRentabilidadEntity();
		try {
			rta = rendimientoTenenciaDAO
					.obtenerGraficoRentabilidad(armarRequestGraficoRentabilidad(cliente, request, tipoBanca));
		} catch (DAOException e) {
			LOGGER.error("Error al consultar el servicio de graficos", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}
		return armarRespuestaGraficoRentabilidadOk(rta.getDatos().getResultado(), request.getMoneda());
	}

	/**
	 * Armar respuesta grafico rentabilidad ok.
	 *
	 * @param listaDatosRentabilidad
	 *            the lista datos rentabilidad
	 * @param moneda
	 *            the moneda
	 * @return the respuesta
	 */
	private Respuesta<GraficoRentabilidadView> armarRespuestaGraficoRentabilidadOk(
			List<GraficoRentabilidadRespuesta> listaDatosRentabilidad, String moneda) {
		LinkedHashMap<String, GraficoRentabilidad> map = new LinkedHashMap<String, GraficoRentabilidad>();
		for (GraficoRentabilidadRespuesta datoRentabilidad : listaDatosRentabilidad) {
			if (map.get(datoRentabilidad.getDescripcionPeriodoAgrup()) == null) {
				map.put(datoRentabilidad.getDescripcionPeriodoAgrup(), new GraficoRentabilidad());
				map.get(datoRentabilidad.getDescripcionPeriodoAgrup()).getDatos()
						.add(armarItemGrafico(datoRentabilidad, moneda));
			} else {
				map.get(datoRentabilidad.getDescripcionPeriodoAgrup()).getDatos()
						.add(armarItemGrafico(datoRentabilidad, moneda));
			}
		}

		GraficoRentabilidadView graficoRentabilidadView = new GraficoRentabilidadView();
		for (Entry<String, GraficoRentabilidad> entry : map.entrySet()) {
			GraficoRentabilidad graficoRentabilidad = new GraficoRentabilidad();
			graficoRentabilidad.setPeriodo((entry.getKey().replaceAll("-", "al")));
			graficoRentabilidad.setDatos(entry.getValue().getDatos());
			if(graficoRentabilidad.getDatos().size() == 2){
				for(DatosGraficoRentabilidadView dato : graficoRentabilidad.getDatos()){
					if("TOT".equals(dato.getCodigoCabecera())){
						graficoRentabilidad.getDatos().remove(dato);
					}
				}
			}
			graficoRentabilidadView.getListaProductos().add(graficoRentabilidad);
		}
		return respuestaFactory.crearRespuestaOk(graficoRentabilidadView);
	}

	/**
	 * Armar item grafico.
	 *
	 * @param datoRentabilidad
	 *            the dato rentabilidad
	 * @param moneda
	 *            the moneda
	 * @return the datos grafico rentabilidad view
	 */
	DatosGraficoRentabilidadView armarItemGrafico(GraficoRentabilidadRespuesta datoRentabilidad, String moneda) {
		DatosGraficoRentabilidadView itemGrafico = new DatosGraficoRentabilidadView();
		itemGrafico.setRentabilidadNeta(datoRentabilidad.getRentabilidadNeta());
		itemGrafico.setCodigoCabecera(datoRentabilidad.getCodigoCabecera());
		RentabilidadTooltip rentabilidadTooltip = new RentabilidadTooltip();
		rentabilidadTooltip.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno((datoRentabilidad.getRentabilidadNeta())));
		rentabilidadTooltip.setRentabilidadNoRealizada(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno((datoRentabilidad.getRentabilidadNoRealizada())));
		rentabilidadTooltip.setRentabilidadRealizada(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno((datoRentabilidad.getRentabilidadRealizada())));
		itemGrafico.setRentabilidadTooltip(rentabilidadTooltip);
		return itemGrafico;
	}

}
