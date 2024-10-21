/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo.AnalisisCarteraBO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.AperturaGraficaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleRentabilidadTotalDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleSubclasificionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.GrillasProductosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.RentabilidadPeriodoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DivisaEnumCartera;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.CuentaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DetalleGrillaRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.DistribucionRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ListaFiltradaPorFecha;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.MonedaACView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.OpcionDetalleMenuView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ProductoDetalleView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ProductoFiltroRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ProductoGraficoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResponseDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ResultadoCarteraCliente;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.SubproductoFiltroRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.TipoEspecieDetalleView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.ValoresRentabilidadCabeceraView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaResultado;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorAgrupacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetallePorInstrumentoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentPorSubclasifEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPorClasificacion;

/**
 * The Class AnalisisCarteraManagerImpl.
 */
@Component
public class AnalisisCarteraManagerImpl implements AnalisisCarteraManager {

	/** The analisis cartera BO. */
	@Autowired
	AnalisisCarteraBO analisisCarteraBO;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	SesionParametros sesionParametros;

	/** The mensaje BO. */
	@Autowired
	MensajeBO mensajeBO;

	/** The rendimiento tenencia DAO. */
	@Autowired
	RendimientoTenenciaDAO rendimientoTenenciaDAO;
	
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AnalisisCarteraManagerImpl.class);

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant CODIGO_ERROR_INICIO_BASE_ANALISIS_CARTERA. */
	private static final String CODIGO_ERROR_INICIO_BASE_ANALISIS_CARTERA = "10485";

	/** The Constant LOG_CODIGO_MENSAJE. */
	private static final String LOG_CODIGO_MENSAJE = "Recuperando mensaje con el codigo {}";

	/** The Constant CODIGO_CUENTA. */
	private static final String CODIGO_CUENTA = "CTA";

	/** The Constant CODIGO_CUENTA_Y_PF. */
	private static final String CODIGO_CUENTA_Y_PF = "PAR";

	/** The Constant SI. */
	private static final String SI = "S";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerRentabilidadTotal(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView)
	 */
	@Override
	public Respuesta<RentabilidadTotalView> obtenerRentabilidadTotal(RentabilidadTotalInView rentabilidadView) {

		RentabilidadTotalView rentabilidadTotalView = new RentabilidadTotalView();
		DetalleRentabilidadTotalDTO rentabilidadTotalDTORetail;
		DetalleRentabilidadTotalDTO rentabilidadTotalDTOPrivada;

		Boolean retailOK = Boolean.FALSE;
		Boolean privadaOK = Boolean.FALSE;

		String mensajeRentabilidadPesos = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AYUDA_RENTABILIDAD_PESOS).getMensaje();	
		String mensajeRentabilidadDolares = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_AYUDA_RENTABILIDAD_DOLARES).getMensaje();	

		rentabilidadTotalDTOPrivada = analisisCarteraBO.obtenerRentabilidadTotal(sesionCliente.getCliente(),
				rentabilidadView, true);
		rentabilidadTotalDTORetail = analisisCarteraBO.obtenerRentabilidadTotal(sesionCliente.getCliente(),
				rentabilidadView, false);

		if (rentabilidadTotalDTORetail.getNoTieneRentabilidad()
				&& rentabilidadTotalDTOPrivada.getNoTieneRentabilidad()) {
			return respuestaFactory.crearRespuestaError("", TipoError.WARNING_SIN_INVERSIONES,
					CodigoMensajeConstantes.MENSAJE_CLIENTE_SIN_INVERSIONES);
		}

		if (rentabilidadTotalDTORetail.getHayError() && rentabilidadTotalDTOPrivada.getHayError()) {
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_RETAIL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.WARNING_RENTABILIDAD_TOTAL,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		if (!rentabilidadTotalDTORetail.getNoTieneRentabilidad() && !rentabilidadTotalDTORetail.getHayError()) {
			ValoresRentabilidadCabeceraView valoresRentabilidadCabeceraView = armarRentabilidadTotalView(rentabilidadTotalDTORetail,
					rentabilidadTotalView, TipoBancaEnum.BANCA_PERSONAL, mensajeRentabilidadPesos, mensajeRentabilidadDolares);
			rentabilidadTotalView.setValoresSelectorRetail(valoresRentabilidadCabeceraView);
			retailOK = Boolean.TRUE;
		} else if (!rentabilidadTotalDTORetail.getNoTieneRentabilidad() && rentabilidadTotalDTORetail.getHayError()
				|| rentabilidadTotalDTORetail.getNoTieneRentabilidad()) {
			ValoresRentabilidadCabeceraView cabeceraError = armarCabeceraError();
			rentabilidadTotalView.setValoresSelectorRetail(cabeceraError);
		}

		if (!rentabilidadTotalDTOPrivada.getNoTieneRentabilidad() && !rentabilidadTotalDTOPrivada.getHayError()) {
			ValoresRentabilidadCabeceraView valoresRentabilidadCabeceraView = armarRentabilidadTotalView(rentabilidadTotalDTOPrivada,
					rentabilidadTotalView, TipoBancaEnum.BANCA_PRIVADA, mensajeRentabilidadPesos, mensajeRentabilidadDolares);
			rentabilidadTotalView.setValoresSelectorPrivada(valoresRentabilidadCabeceraView);
			privadaOK = Boolean.TRUE;
		} else if (!rentabilidadTotalDTOPrivada.getNoTieneRentabilidad() && rentabilidadTotalDTOPrivada.getHayError()
				|| rentabilidadTotalDTOPrivada.getNoTieneRentabilidad()) {
			ValoresRentabilidadCabeceraView cabeceraError = armarCabeceraError();
			rentabilidadTotalView.setValoresSelectorPrivada(cabeceraError);
		}

		if (retailOK && privadaOK) {
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_RETAIL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);			
			return respuestaFactory.crearRespuestaOk(rentabilidadTotalView);
		}

		return armarRespuestaWarning(rentabilidadTotalDTORetail, rentabilidadTotalDTOPrivada, rentabilidadTotalView);
	}

	/**
	 * Armar rentabilidad total view.
	 *
	 * @param rentabilidadTotalDTO
	 *            the rentabilidad total DTO
	 * @param rentabilidadTotalView
	 *            the rentabilidad total view
	 * @param tipoBanca
	 *            the tipo banca
	 * @param mensajeRentabilidadPesos
	 *            the mensaje rentabilidad pesos
	 * @param mensajeRentabilidadDolares
	 *            the mensaje rentabilidad dolares
	 * @return the valores rentabilidad cabecera view
	 */
	private ValoresRentabilidadCabeceraView armarRentabilidadTotalView(
			DetalleRentabilidadTotalDTO rentabilidadTotalDTO, RentabilidadTotalView rentabilidadTotalView, TipoBancaEnum tipoBanca,
			String mensajeRentabilidadPesos, String mensajeRentabilidadDolares) {

		ValoresRentabilidadCabeceraView valoresRentabilidadCabeceraView = new ValoresRentabilidadCabeceraView();

		if (!rentabilidadTotalDTO.getCorrespondeBanca()) {
			return null;
		}

		BigDecimal rentabilidadPesos = rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getRentabilidadPesos();
		BigDecimal rentabilidadDolares = rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getRentabilidadDolares();

		if (rentabilidadPesos != null) {
			valoresRentabilidadCabeceraView.setTotalPesos(
					DivisaEnumCartera.PESO.getSimbolo() + " " + ISBANStringUtils.formatearSaldoConSigno(rentabilidadPesos));
			valoresRentabilidadCabeceraView.setBigTotalPesos(rentabilidadPesos);
			if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
				rentabilidadTotalView.setMensajeAyudaRentabilidadPesosRTL(MessageFormat.format(mensajeRentabilidadPesos, rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getFechaInfoDisponible()));
			} else {
				rentabilidadTotalView.setMensajeAyudaRentabilidadPesosBP(MessageFormat.format(mensajeRentabilidadPesos, rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getFechaInfoDisponible()));
			}
		} else {
			valoresRentabilidadCabeceraView.setTotalPesos(DivisaEnumCartera.PESO.getSimbolo() + " " + GUION);
			valoresRentabilidadCabeceraView.setBigTotalPesos(null);
		}

		if (rentabilidadDolares != null) {
			valoresRentabilidadCabeceraView.setTotalDolares(
					DivisaEnumCartera.DOLAR.getSimbolo() + " " + ISBANStringUtils.formatearSaldoConSigno(rentabilidadDolares));
			valoresRentabilidadCabeceraView.setBigTotalDolares(rentabilidadDolares);
			if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)) {
				rentabilidadTotalView.setMensajeAyudaRentabilidadDolaresRTL(MessageFormat.format(mensajeRentabilidadDolares, rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getFechaInfoDisponible()));
			} else {
				rentabilidadTotalView.setMensajeAyudaRentabilidadDolaresBP(MessageFormat.format(mensajeRentabilidadDolares, rentabilidadTotalDTO.getRentTotalPeriodoMoneda().getFechaInfoDisponible()));
			}
		} else {
			valoresRentabilidadCabeceraView.setTotalDolares(DivisaEnumCartera.DOLAR.getSimbolo() + " " + GUION);
			valoresRentabilidadCabeceraView.setBigTotalDolares(null);
		}

		return valoresRentabilidadCabeceraView;
	}

	/**
	 * Armar cabecera error.
	 *
	 * @return the valores rentabilidad cabecera view
	 */
	private ValoresRentabilidadCabeceraView armarCabeceraError() {

		ValoresRentabilidadCabeceraView valoresRentabilidadCabeceraView = new ValoresRentabilidadCabeceraView();
		valoresRentabilidadCabeceraView.setTotalPesos(DivisaEnumCartera.PESO.getSimbolo() + " " + GUION);
		valoresRentabilidadCabeceraView.setBigTotalPesos(null);
		valoresRentabilidadCabeceraView.setTotalDolares(DivisaEnumCartera.DOLAR.getSimbolo() + " " + GUION);
		valoresRentabilidadCabeceraView.setBigTotalDolares(null);

		return valoresRentabilidadCabeceraView;
	}

	/**
	 * Armar respuesta warning.
	 *
	 * @param rentabilidadTotalDTORetail
	 *            the rentabilidad total DTO retail
	 * @param rentabilidadTotalDTOPrivada
	 *            the rentabilidad total DTO privada
	 * @param rentabilidadTotalView
	 *            the rentabilidad total view
	 * @return the respuesta
	 */
	private Respuesta<RentabilidadTotalView> armarRespuestaWarning(
			DetalleRentabilidadTotalDTO rentabilidadTotalDTORetail,
			DetalleRentabilidadTotalDTO rentabilidadTotalDTOPrivada, RentabilidadTotalView rentabilidadTotalView) {

		int cantidadBancas = 0;
		Boolean retail = Boolean.FALSE;

		if (rentabilidadTotalDTORetail.getCorrespondeBanca()) {
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_RETAIL,
					rentabilidadTotalDTORetail.getHayError() ? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
							: EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			cantidadBancas++;
			retail = Boolean.TRUE;

		}

		if (rentabilidadTotalDTOPrivada.getCorrespondeBanca()) {
			estadisticaManager.add(EstadisticasConstants.INICIO_BASE_ANALISIS_CARTERA_PRIVADA,
					rentabilidadTotalDTOPrivada.getHayError() ? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
							: EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			cantidadBancas++;
		}

		LOGGER.info(LOG_CODIGO_MENSAJE, CODIGO_ERROR_INICIO_BASE_ANALISIS_CARTERA);
		if (cantidadBancas == 1) {
			TipoError tipoError = revisarSiErrorOSinInversionesUnaBanca(
					retail ? rentabilidadTotalDTORetail : rentabilidadTotalDTOPrivada);
			return respuestaFactory.crearRespuestaError("", tipoError, CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		} else {
			List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
			if (rentabilidadTotalDTORetail.getHayError() || rentabilidadTotalDTORetail.getNoTieneRentabilidad()) {
				TipoError tipoError = revisarSiErrorOSinInversionesDosBancas(rentabilidadTotalDTORetail,
						TipoBancaEnum.BANCA_PERSONAL);
				ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
				itemMensaje
						.setMensaje(
								mensajeBO
										.obtenerMensajePorCodigo(TipoError.WARNING_SIN_INVERSIONES_RTL.equals(tipoError)
												? CodigoMensajeConstantes.MENSAJE_CLIENTE_SIN_INVERSIONES
												: CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA)
										.getMensaje());
				itemMensaje.setTipoError(tipoError.getDescripcion());
				listaItemMensaje.add(itemMensaje);
			}

			if (rentabilidadTotalDTOPrivada.getHayError() || rentabilidadTotalDTOPrivada.getNoTieneRentabilidad()) {
				TipoError tipoError = revisarSiErrorOSinInversionesDosBancas(rentabilidadTotalDTOPrivada,
						TipoBancaEnum.BANCA_PRIVADA);
				ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
				itemMensaje
						.setMensaje(
								mensajeBO
										.obtenerMensajePorCodigo(
												TipoError.WARNING_SIN_INVERSIONES_BPRIV.equals(tipoError)
														? CodigoMensajeConstantes.MENSAJE_CLIENTE_SIN_INVERSIONES
														: CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA)
										.getMensaje());
				itemMensaje.setTipoError(tipoError.getDescripcion());
				listaItemMensaje.add(itemMensaje);
			}

			return respuestaFactory.crearRespuestaWarning(rentabilidadTotalView, listaItemMensaje);
		}
	}

	/**
	 * Revisar si error O sin inversiones una banca.
	 *
	 * @param rentabilidadTotalDTO
	 *            the rentabilidad total DTO
	 * @return the tipo error
	 */
	private TipoError revisarSiErrorOSinInversionesUnaBanca(DetalleRentabilidadTotalDTO rentabilidadTotalDTO) {

		if (rentabilidadTotalDTO.getHayError()) {
			return TipoError.WARNING_RENTABILIDAD_TOTAL;
		}
		return TipoError.WARNING_SIN_INVERSIONES;
	}

	/**
	 * Revisar si error O sin inversiones dos bancas.
	 *
	 * @param rentabilidadTotalDTO
	 *            the rentabilidad total DTO
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the tipo error
	 */
	private TipoError revisarSiErrorOSinInversionesDosBancas(DetalleRentabilidadTotalDTO rentabilidadTotalDTO,
			TipoBancaEnum tipoBanca) {

		TipoError tipoError;

		if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) && rentabilidadTotalDTO.getHayError()) {
			tipoError = TipoError.WARNING_RENTABILIDAD_TOTAL_RTL;
		} else if (TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca) && rentabilidadTotalDTO.getNoTieneRentabilidad()) {
			tipoError = TipoError.WARNING_SIN_INVERSIONES_RTL;
		} else if (TipoBancaEnum.BANCA_PRIVADA.equals(tipoBanca) && rentabilidadTotalDTO.getHayError()) {
			tipoError = TipoError.WARNING_RENTABILIDAD_TOTAL_BPRIV;
		} else {
			tipoError = TipoError.WARNING_SIN_INVERSIONES_BPRIV;
		}

		return tipoError;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerDashboard(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<ResponseDashboard> obtenerDashboard(RequestDashboard request) {
		ResponseDashboard responseDashboard = new ResponseDashboard();
		RentabilidadPeriodoRequestView requestRentabilidad;
		Respuesta<FiltroPorFechaDTO> responseFiltroFecha;

		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_DASHBOARD_ANALISIS_CARTERA);
		if(EstadoRespuesta.ERROR.equals(respuestaLegales.getEstadoRespuesta())){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}
		
		responseDashboard.setLegales(respuestaLegales.getRespuesta());
		
		if (request.isDefecto()) {
			if (request.getCarteraAConsultar() == null) {
				Respuesta<FiltroCarteraDTO> responseCartera = analisisCarteraBO
						.obtenerFiltroCartera(sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL);

				if (EstadoRespuesta.ERROR.equals(responseCartera.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}
				responseDashboard.getFiltroCarteraView()
						.setMultiseleccion(responseCartera.getRespuesta().getMultiseleccion());
				responseDashboard.getFiltroCarteraView()
						.setResultadoCarteraCliente(responseCartera.getRespuesta().getResultadoCarteraCliente());
				responseFiltroFecha = obtenerFiltrosFechaDefecto(
						responseDashboard.getFiltroCarteraView().getResultadoCarteraCliente(),
						TipoBancaEnum.BANCA_PERSONAL);
				if (EstadoRespuesta.OK.equals(responseFiltroFecha.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					responseDashboard.getFiltrosPorFecha()
							.setPeriodos(responseFiltroFecha.getRespuesta().getPeriodos());
					requestRentabilidad = armarRequestRentabilidadPeriodo(
							responseFiltroFecha.getRespuesta().getPeriodos(),
							responseDashboard.getFiltroCarteraView().getResultadoCarteraCliente(),
							TipoBancaEnum.BANCA_PERSONAL);
				} else {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}
			} else {
				responseFiltroFecha = analisisCarteraBO.obtenerFiltroPorFecha(sesionCliente.getCliente(),
						armarFiltroPorFechaRequest(request), TipoBancaEnum.BANCA_PERSONAL);
				if (EstadoRespuesta.ERROR.equals(responseFiltroFecha.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}

				estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				requestRentabilidad = armarRequestRentabilidadPeriodo(request, TipoBancaEnum.BANCA_PERSONAL);
			}
			responseDashboard.getFiltrosPorFecha().setPeriodos(responseFiltroFecha.getRespuesta().getPeriodos());

		} else {
			estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			requestRentabilidad = armarRequestRentabilidadPeriodo(request, TipoBancaEnum.BANCA_PERSONAL);
		}

		Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = analisisCarteraBO.obtenerRentabilidadPeriodo(
				sesionCliente.getCliente(), requestRentabilidad, TipoBancaEnum.BANCA_PERSONAL);

		if (EstadoRespuesta.ERROR.equals(rentabilidadPeriodoDTO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		responseDashboard
				.setRentabilidadPeriodo(armarResponseRentabilidadPeriodo(rentabilidadPeriodoDTO.getRespuesta()));
		responseDashboard.setDistribucionRentabilidadView(
				armarDistribucionRentabilidadView(rentabilidadPeriodoDTO.getRespuesta()));

		return respuestaFactory.crearRespuestaOk(ResponseDashboard.class, responseDashboard);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerDashboardBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<ResponseDashboard> obtenerDashboardBPriv(RequestDashboard request) {
		ResponseDashboard responseDashboard = new ResponseDashboard();
		RentabilidadPeriodoRequestView requestRentabilidad;
		Respuesta<FiltroPorFechaDTO> responseFiltroFecha;

		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGAL_DASHBOARD_ANALISIS_CARTERA);
		if(EstadoRespuesta.ERROR.equals(respuestaLegales.getEstadoRespuesta())){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}
		
		responseDashboard.setLegales(respuestaLegales.getRespuesta());
		
		if (request.isDefecto()) {
			if (request.getCarteraAConsultar() == null) {
				Respuesta<FiltroCarteraDTO> responseCartera = analisisCarteraBO
						.obtenerFiltroCartera(sesionCliente.getCliente(), TipoBancaEnum.BANCA_PRIVADA);

				if (EstadoRespuesta.ERROR.equals(responseCartera.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}
				responseDashboard.getFiltroCarteraView()
						.setMultiseleccion(responseCartera.getRespuesta().getMultiseleccion());
				responseDashboard.getFiltroCarteraView()
						.setResultadoCarteraCliente(responseCartera.getRespuesta().getResultadoCarteraCliente());
				responseFiltroFecha = obtenerFiltrosFechaDefecto(
						responseDashboard.getFiltroCarteraView().getResultadoCarteraCliente(),
						TipoBancaEnum.BANCA_PRIVADA);
				if (EstadoRespuesta.OK.equals(responseFiltroFecha.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					requestRentabilidad = armarRequestRentabilidadPeriodo(
							responseFiltroFecha.getRespuesta().getPeriodos(),
							responseDashboard.getFiltroCarteraView().getResultadoCarteraCliente(),
							TipoBancaEnum.BANCA_PRIVADA);
				} else {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}
			} else {
				responseFiltroFecha = analisisCarteraBO.obtenerFiltroPorFecha(sesionCliente.getCliente(),
						armarFiltroPorFechaRequest(request), TipoBancaEnum.BANCA_PRIVADA);
				if (EstadoRespuesta.ERROR.equals(responseFiltroFecha.getEstadoRespuesta())) {
					estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
				}
				estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				requestRentabilidad = armarRequestRentabilidadPeriodo(request, TipoBancaEnum.BANCA_PRIVADA);
			}

			responseDashboard.getFiltrosPorFecha().setPeriodos(responseFiltroFecha.getRespuesta().getPeriodos());
		} else {
			estadisticaManager.add(EstadisticasConstants.FILTRO_ANALISIS_CARTERA_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			requestRentabilidad = armarRequestRentabilidadPeriodo(request, TipoBancaEnum.BANCA_PRIVADA);
		}
		Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = analisisCarteraBO.obtenerRentabilidadPeriodo(
				sesionCliente.getCliente(), requestRentabilidad, TipoBancaEnum.BANCA_PRIVADA);
		if (EstadoRespuesta.ERROR.equals(rentabilidadPeriodoDTO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}
		responseDashboard
				.setRentabilidadPeriodo(armarResponseRentabilidadPeriodo(rentabilidadPeriodoDTO.getRespuesta()));
		responseDashboard.setDistribucionRentabilidadView(
				armarDistribucionRentabilidadView(rentabilidadPeriodoDTO.getRespuesta()));

		return respuestaFactory.crearRespuestaOk(ResponseDashboard.class, responseDashboard);
	}

	/**
	 * Obtener filtros fecha defecto.
	 *
	 * @param lista
	 *            the lista
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	private Respuesta<FiltroPorFechaDTO> obtenerFiltrosFechaDefecto(List<ResultadoCarteraCliente> lista,
			TipoBancaEnum tipoBancaEnum) {
		Respuesta<FiltroPorFechaDTO> responseFiltroFecha = new Respuesta<FiltroPorFechaDTO>();
		responseFiltroFecha.setEstadoRespuesta(EstadoRespuesta.ERROR);
		for (ResultadoCarteraCliente resultadoCartera : lista) {
			if (resultadoCartera.getPorDefecto()) {
				FiltroPorFechaRequestView filtroFechaRequestView = armarFiltroFechaViewPorDefecto(resultadoCartera,
						false);
				responseFiltroFecha = analisisCarteraBO.obtenerFiltroPorFecha(sesionCliente.getCliente(),
						filtroFechaRequestView, tipoBancaEnum);
			}
		}
		return responseFiltroFecha;
	}

	/**
	 * Armar filtro fecha view por defecto.
	 *
	 * @param resultadoCartera
	 *            the resultado cartera
	 * @param request
	 *            the request
	 * @return the filtro por fecha request view
	 */
	private FiltroPorFechaRequestView armarFiltroFechaViewPorDefecto(ResultadoCarteraCliente resultadoCartera,
			boolean request) {

		FiltroPorFechaRequestView filtroRequest = new FiltroPorFechaRequestView();
		filtroRequest.setCarteraAConsultar(resultadoCartera.getCodigoCartera());
		filtroRequest.setEsBancaPrivada(request);

		if (CODIGO_CUENTA.equals(resultadoCartera.getCodigoCartera())
				|| CODIGO_CUENTA_Y_PF.equals(resultadoCartera.getCodigoCartera())) {
			List<CuentaACView> listaCuentas = new ArrayList<CuentaACView>();
			listaCuentas.add(resultadoCartera.getCuenta());
			filtroRequest.setListaCuentas(listaCuentas);
		}

		return filtroRequest;
	}

	/**
	 * Armar filtro por fecha request.
	 *
	 * @param request
	 *            the request
	 * @return the filtro por fecha request view
	 */
	private FiltroPorFechaRequestView armarFiltroPorFechaRequest(RequestDashboard request) {
		FiltroPorFechaRequestView response = new FiltroPorFechaRequestView();
		response.setCarteraAConsultar(request.getCarteraAConsultar());
		response.setListaCuentas(request.getListaCuentas());
		response.setMoneda(request.getMoneda());
		response.setEsBancaPrivada(false);
		return response;
	}

	/**
	 * Armar request rentabilidad periodo.
	 *
	 * @param periodos
	 *            the periodos
	 * @param resultadoCartera
	 *            the resultado cartera
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the rentabilidad periodo request view
	 */
	private RentabilidadPeriodoRequestView armarRequestRentabilidadPeriodo(List<ListaFiltradaPorFecha> periodos,
			List<ResultadoCarteraCliente> resultadoCartera, TipoBancaEnum tipoBancaEnum) {
		RentabilidadPeriodoRequestView response = new RentabilidadPeriodoRequestView();
		for (ListaFiltradaPorFecha periodo : periodos) {
			if (periodo.getPorDefecto()) {
				response.setMoneda(obtenerMonedaDefecto(periodo.getListaMonedas()));
				response.setPeriodo(periodo.getCodigoPeriodo());
				response.setPeriodoFechaFin(periodo.getFechaFin());
				response.setPeriodoFechaInicial(periodo.getFechaInicio());
			}
		}

		for (ResultadoCarteraCliente cartera : resultadoCartera) {
			if (cartera.getPorDefecto()) {
				response.setCarteraAConsultar(cartera.getCodigoCartera());
				response.getListaCuentas().add(cartera.getCuenta());
			}
		}

		response.setTipoBancaEnum(tipoBancaEnum);
		return response;
	}

	/**
	 * Obtener moneda defecto.
	 *
	 * @param monedas
	 *            the monedas
	 * @return the string
	 */
	private String obtenerMonedaDefecto(List<MonedaACView> monedas) {
		for (MonedaACView moneda : monedas) {
			if (moneda.getDefecto()) {
				return moneda.getId();
			}
		}
		return "";
	}

	/**
	 * Armar request rentabilidad periodo.
	 *
	 * @param request
	 *            the request
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the rentabilidad periodo request view
	 */
	private RentabilidadPeriodoRequestView armarRequestRentabilidadPeriodo(RequestDashboard request,
			TipoBancaEnum tipoBancaEnum) {
		RentabilidadPeriodoRequestView response = new RentabilidadPeriodoRequestView();
		response.setMoneda(request.getMoneda());
		response.setPeriodo(request.getPeriodo());
		response.setPeriodoFechaFin(request.getPeriodoFechaFin());
		response.setPeriodoFechaInicial(request.getPeriodoFechaInicial());
		response.setCarteraAConsultar(request.getCarteraAConsultar());
		response.setListaCuentas(request.getListaCuentas());
		response.setTipoBancaEnum(tipoBancaEnum);
		return response;
	}

	/**
	 * Armar response rentabilidad periodo.
	 *
	 * @param responseService
	 *            the response service
	 * @return the rentabilidad periodo view
	 */
	private RentabilidadPeriodoView armarResponseRentabilidadPeriodo(RentabilidadPeriodoDTO responseService) {
		RentabilidadPeriodoView resultado = new RentabilidadPeriodoView();

		if (responseService.getDatos().getMoneda() != null) {
			resultado.setRentabilidadRealizada(DivisaEnumCartera.fromCodigoString(responseService.getDatos().getMoneda())
					.getSimbolo() + " "
					+ ISBANStringUtils.formatearSaldoConSigno(responseService.getDatos().getRentabilidadRealizada()));

			resultado.setRentabilidadNoRealizada(DivisaEnumCartera.fromCodigoString(responseService.getDatos().getMoneda())
					.getSimbolo() + " "
					+ ISBANStringUtils.formatearSaldoConSigno(responseService.getDatos().getRentabilidadNoRealizada()));

			resultado.setRentabilidadTotal(DivisaEnumCartera.fromCodigoString(responseService.getDatos().getMoneda())
					.getSimbolo() + " "
					+ ISBANStringUtils.formatearSaldoConSigno(responseService.getDatos().getRentabilidadTotal()));
		}

		resultado.setTooltipRentabilidadRealizada(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_REALIZADA).getMensaje());
		resultado.setTooltipRentabilidadNoRealizada(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_NO_REALIZADA).getMensaje());
		resultado.setTooltipRentabilidadTotal(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TOOLTIP_RENTABILIDAD_TOTAL).getMensaje());

		return resultado;
	}

	/**
	 * Armar distribucion rentabilidad view.
	 *
	 * @param rentabilidadPeriodo
	 *            the rentabilidad periodo
	 * @return the distribucion rentabilidad view
	 */
	DistribucionRentabilidadView armarDistribucionRentabilidadView(RentabilidadPeriodoDTO rentabilidadPeriodo) {

		DistribucionRentabilidadView distribucionRentabilidadView = new DistribucionRentabilidadView();

		distribucionRentabilidadView.setTotalRentabilidad(DivisaEnumCartera
				.fromCodigoString(rentabilidadPeriodo.getDatos().getMoneda()).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno(rentabilidadPeriodo.getDatos().getRentabilidadTotal()));
		distribucionRentabilidadView.setTna(rentabilidadPeriodo.getDatos().getTna());
		distribucionRentabilidadView
				.setTnaTxt(rentabilidadPeriodo.getDatos().getTna().toString().replace(".", ",") + " %");

		List<ProductoGraficoView> listaProductos = new ArrayList<ProductoGraficoView>();
		BigDecimal escala = new BigDecimal("0");
		for (RentabilidadPorClasificacion rentabilidadPorClasificacion : rentabilidadPeriodo.getDatos()
				.getResultadoPorClasificacion()) {
			if (rentabilidadPorClasificacion.getRentabilidadNeta() != null) {
				ProductoGraficoView productoGraficoView = new ProductoGraficoView();
				productoGraficoView.setValor(rentabilidadPorClasificacion.getRentabilidadNeta());
				productoGraficoView.setValorTxt(DivisaEnumCartera.fromCodigoString(rentabilidadPeriodo.getDatos().getMoneda())
						.getSimbolo() + " "
						+ ISBANStringUtils.formatearSaldoConSigno(rentabilidadPorClasificacion.getRentabilidadNeta()));
				productoGraficoView.setProducto(rentabilidadPorClasificacion.getDescripcionSubclasificacion());
				productoGraficoView.setCodigo(rentabilidadPorClasificacion.getCodigoSubclasificacion());

				if (rentabilidadPorClasificacion.getRentabilidadNeta().compareTo(BigDecimal.ZERO) == (-1)) {
					rentabilidadPorClasificacion
							.setRentabilidadNeta(rentabilidadPorClasificacion.getRentabilidadNeta().negate());
				}

				if (escala.compareTo(rentabilidadPorClasificacion.getRentabilidadNeta()) == (-1)) {
					escala = rentabilidadPorClasificacion.getRentabilidadNeta();
				}
				listaProductos.add(productoGraficoView);
			} else {
				ProductoGraficoView productoGraficoView = new ProductoGraficoView();
				productoGraficoView.setValor(null);
				productoGraficoView.setValorTxt(
						DivisaEnumCartera.fromCodigoString(rentabilidadPeriodo.getDatos().getMoneda()).getSimbolo() + " "
								+ "-");
				productoGraficoView.setProducto(rentabilidadPorClasificacion.getDescripcionSubclasificacion());
				productoGraficoView.setCodigo(rentabilidadPorClasificacion.getCodigoSubclasificacion());
				listaProductos.add(productoGraficoView);
			}
		}

		distribucionRentabilidadView.setEscala(escala);
		distribucionRentabilidadView
				.setListaEscalaTxt(analisisCarteraBO.armarListaEscalaGraficoDistribucionRentabilidad(escala));

		distribucionRentabilidadView.setListaProductos(listaProductos);
		return distribucionRentabilidadView;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#grabarEstadisticaRentabilidadMobileRTL()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRentabilidadMobileRTL() {
		estadisticaManager.add(EstadisticasConstants.VISUALIZACION_GRAFICO_RENDIMIENTO_MOBILE_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#grabarEstadisticaRentabilidadMobileBP()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaRentabilidadMobileBP() {
		estadisticaManager.add(EstadisticasConstants.VISUALIZACION_GRAFICO_RENDIMIENTO_MOBILE_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerRentabilidadRendimiento(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimiento(RequestDashboard request) {

		Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO;
		RentabilidadRendimientoView rentabilidadRendimientoView = new RentabilidadRendimientoView();

		if (request.getArmarFiltro()) {
			respuestaAperturaGraficaDTO = analisisCarteraBO.obtenerFiltroRentabilidad(sesionCliente.getCliente(),
					request, TipoBancaEnum.BANCA_PERSONAL);
			if (EstadoRespuesta.ERROR.equals(respuestaAperturaGraficaDTO.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				estadisticaManager.add(EstadisticasConstants.FILTRO_GRAFICO_GRILLAS_RTL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
			} else {

				List<ProductoFiltroRentabilidad> filtro = armarFiltroGrillas(
						respuestaAperturaGraficaDTO.getRespuesta().getListaResultado(), request);
				rentabilidadRendimientoView.setListaProductos(filtro);
			}
		}

		if (!sesionParametros.getRegistroSession().isMobile()) {
			estadisticaManager.add(EstadisticasConstants.FILTRO_GRAFICO_GRILLAS_RTL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		Respuesta<GrillasProductosDTO> resp = analisisCarteraBO.obtenerGrillasDTO(sesionCliente.getCliente(), request,
				TipoBancaEnum.BANCA_PERSONAL, sesionParametros.getRegistroSession().isMobile());

		if (EstadoRespuesta.ERROR.equals(resp.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		rentabilidadRendimientoView.setListaGrillas(resp.getRespuesta().getListaProductos());
		rentabilidadRendimientoView.setListaGraficoCircular(resp.getRespuesta().getListaGraficoCircular());
		rentabilidadRendimientoView.setLlamarRendimiento(resp.getRespuesta().isLlamarRendimiento());
		rentabilidadRendimientoView.setLlamarRentabilidad(resp.getRespuesta().isLlamarRentabilidad());
		if(!rentabilidadRendimientoView.isLlamarRendimiento() && !rentabilidadRendimientoView.isLlamarRentabilidad()){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(rentabilidadRendimientoView);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerRentabilidadRendimientoBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<RentabilidadRendimientoView> obtenerRentabilidadRendimientoBPriv(RequestDashboard request) {

		Respuesta<AperturaGraficaDTO> respuestaAperturaGraficaDTO;
		RentabilidadRendimientoView rentabilidadRendimientoView = new RentabilidadRendimientoView();

		if (request.getArmarFiltro()) {
			respuestaAperturaGraficaDTO = analisisCarteraBO.obtenerFiltroRentabilidad(sesionCliente.getCliente(),
					request, TipoBancaEnum.BANCA_PRIVADA);
			if (EstadoRespuesta.ERROR.equals(respuestaAperturaGraficaDTO.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				estadisticaManager.add(EstadisticasConstants.FILTRO_GRAFICO_GRILLAS_BP,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
			} else {

				List<ProductoFiltroRentabilidad> filtro = armarFiltroGrillas(
						respuestaAperturaGraficaDTO.getRespuesta().getListaResultado(), request);
				rentabilidadRendimientoView.setListaProductos(filtro);
			}
		}

		if (!sesionParametros.getRegistroSession().isMobile()) {
			estadisticaManager.add(EstadisticasConstants.FILTRO_GRAFICO_GRILLAS_BP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		
		Respuesta<GrillasProductosDTO> resp = analisisCarteraBO.obtenerGrillasDTO(sesionCliente.getCliente(), request,
				TipoBancaEnum.BANCA_PRIVADA, sesionParametros.getRegistroSession().isMobile());

		if (EstadoRespuesta.ERROR.equals(resp.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		rentabilidadRendimientoView.setListaGrillas(resp.getRespuesta().getListaProductos());
		rentabilidadRendimientoView.setListaGraficoCircular(resp.getRespuesta().getListaGraficoCircular());
		rentabilidadRendimientoView.setLlamarRendimiento(resp.getRespuesta().isLlamarRendimiento());
		rentabilidadRendimientoView.setLlamarRentabilidad(resp.getRespuesta().isLlamarRentabilidad());
		if(!rentabilidadRendimientoView.isLlamarRendimiento() && !rentabilidadRendimientoView.isLlamarRentabilidad()){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(rentabilidadRendimientoView);
	}

	/**
	 * Armar filtro grillas.
	 *
	 * @param listaProductosDTO
	 *            the lista productos DTO
	 * @param request
	 *            the request
	 * @return the list
	 */
	private List<ProductoFiltroRentabilidad> armarFiltroGrillas(List<AperturaGraficaResultado> listaProductosDTO,
			RequestDashboard request) {

		List<ProductoFiltroRentabilidad> listaProductosView = new ArrayList<ProductoFiltroRentabilidad>();

		String nombreProducto = StringUtils.EMPTY;
		Boolean hayDefault = Boolean.FALSE; 
		
		for (int i = 0; i < listaProductosDTO.size(); i++) {
			if (productoNoFiguraEnLista(listaProductosView, nombreProducto)) {
				ProductoFiltroRentabilidad producto = new ProductoFiltroRentabilidad();
				producto.setId(listaProductosDTO.get(i).getCodigoClasificacion());
				producto.setDescripcion(listaProductosDTO.get(i).getDescripcionClasificacion());
				producto.setListaSubproductos(armarListaSubproductos(listaProductosDTO, listaProductosDTO.get(i)));
				revisarSiProductoEsDefault(producto);
				listaProductosView.add(producto);
			}
			
			if (i < listaProductosDTO.size()-1) {
				nombreProducto = listaProductosDTO.get(i+1).getCodigoClasificacion();
			}
		}

		if (!revisarSiNoHayDefault(listaProductosDTO, request, hayDefault)) {
			listaProductosView.get(0).setPorDefecto(Boolean.TRUE);
			listaProductosView.get(0).getListaSubproductos().get(0).setPorDefecto(Boolean.TRUE);
			request.setClasificacion(listaProductosView.get(0).getId());
			request.setSubclasificacion(listaProductosView.get(0).getListaSubproductos().get(0).getId());
		}
		
		for (ProductoFiltroRentabilidad productoView : listaProductosView ) {
			Boolean subproductoDefault = Boolean.FALSE;
			for (SubproductoFiltroRentabilidad subproductoView : productoView.getListaSubproductos()) {
				if (subproductoView.getPorDefecto()) {
					subproductoDefault = Boolean.TRUE;
				}
			}
			
			if (!subproductoDefault) {
				productoView.getListaSubproductos().get(0).setPorDefecto(Boolean.TRUE);
			}
			
		}
		
		return listaProductosView;
	}
	
	/**
	 * Producto no figura en lista.
	 *
	 * @param listaProductosView
	 *            the lista productos view
	 * @param nombreProducto
	 *            the nombre producto
	 * @return the boolean
	 */
	private Boolean productoNoFiguraEnLista (List<ProductoFiltroRentabilidad> listaProductosView, String nombreProducto) {
		
		for (ProductoFiltroRentabilidad productoView : listaProductosView) {
			if (productoView.getId().equals(nombreProducto)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
		
	}

	/**
	 * Armar lista subproductos.
	 *
	 * @param listaProductosDTO
	 *            the lista productos DTO
	 * @param productoDTO
	 *            the producto DTO
	 * @return the list
	 */
	private List<SubproductoFiltroRentabilidad> armarListaSubproductos(List<AperturaGraficaResultado> listaProductosDTO,
			AperturaGraficaResultado productoDTO) {

		List<SubproductoFiltroRentabilidad> listaSubproductos = new ArrayList<SubproductoFiltroRentabilidad>();

		for (AperturaGraficaResultado subproductoDTO : listaProductosDTO) {
			if (productoDTO.getCodigoClasificacion().equals(subproductoDTO.getCodigoClasificacion())) {
				SubproductoFiltroRentabilidad subproducto = new SubproductoFiltroRentabilidad();
				subproducto.setId(subproductoDTO.getCodigoSubclasificacion());
				subproducto.setDescripcion(subproductoDTO.getDescripcionSubclasificacion());
				subproducto.setPorDefecto(SI.equals(subproductoDTO.getPorDefecto()) ? Boolean.TRUE : Boolean.FALSE);
				listaSubproductos.add(subproducto);
			}
		}

		return listaSubproductos;
	}

	/**
	 * Revisar si producto es default.
	 *
	 * @param producto
	 *            the producto
	 */
	private void revisarSiProductoEsDefault(ProductoFiltroRentabilidad producto) {
		for (SubproductoFiltroRentabilidad subproducto : producto.getListaSubproductos()) {
			if (subproducto.getPorDefecto()) {
				producto.setPorDefecto(Boolean.TRUE);
				break;
			}

		}
	}

	/**
	 * Revisar si no hay default.
	 *
	 * @param listaProductosDTO
	 *            the lista productos DTO
	 * @param request
	 *            the request
	 * @param hayDefault
	 *            the hay default
	 * @return the boolean
	 */
	private Boolean revisarSiNoHayDefault(List<AperturaGraficaResultado> listaProductosDTO, RequestDashboard request,
			Boolean hayDefault) {

		for (AperturaGraficaResultado producto : listaProductosDTO) {
			if (SI.equals(producto.getPorDefecto())) {
				hayDefault = Boolean.TRUE;
				request.setClasificacion(producto.getCodigoClasificacion());
				request.setSubclasificacion(producto.getCodigoSubclasificacion());
			}
		}

		return hayDefault;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerGraficoRendimiento(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(RequestDashboard request) {
		Respuesta<GraficoRendimientoView> rta = analisisCarteraBO.obtenerGraficoRendimiento(sesionCliente.getCliente(),
				request, TipoBancaEnum.BANCA_PERSONAL);
		if(EstadoRespuesta.OK.equals(rta.getEstadoRespuesta())){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}else{
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerGraficoRendimientoBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRendimientoView> obtenerGraficoRendimientoBPriv(RequestDashboard request) {
		Respuesta<GraficoRendimientoView> rta = analisisCarteraBO.obtenerGraficoRendimiento(sesionCliente.getCliente(),
				request, TipoBancaEnum.BANCA_PRIVADA);
		if(EstadoRespuesta.OK.equals(rta.getEstadoRespuesta())){
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}else {
			estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#recargarGraficoRentabilidadRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadRTL(RequestDashboard request) {

		return recargarGraficoRentabilidadAmbasBancas(request, TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#recargarGraficoRentabilidadBP(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadBP(RequestDashboard request) {

		return recargarGraficoRentabilidadAmbasBancas(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/**
	 * Recargar grafico rentabilidad ambas bancas.
	 *
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	private Respuesta<DistribucionRentabilidadView> recargarGraficoRentabilidadAmbasBancas(RequestDashboard request,
			TipoBancaEnum tipoBanca) {

		RentabilidadPeriodoRequestView requestRentabilidad = armarRequestRentabilidadPeriodo(request, tipoBanca);
		requestRentabilidad.setClasificacion(request.getClasificacion());
		Respuesta<RentabilidadPeriodoDTO> rentabilidadPeriodoDTO = analisisCarteraBO
				.obtenerRentabilidadPeriodo(sesionCliente.getCliente(), requestRentabilidad, tipoBanca);

		if (EstadoRespuesta.ERROR.equals(rentabilidadPeriodoDTO.getEstadoRespuesta())) {
			if(TipoBancaEnum.BANCA_PERSONAL.equals(tipoBanca)){
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}else{
				estadisticaManager.add(EstadisticasConstants.ESTADISTICA_GENERAL_DASHBOARD_CARTERA_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}

		DistribucionRentabilidadView distribucionRentabilidadView = armarDistribucionRentabilidadView(
				rentabilidadPeriodoDTO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(distribucionRentabilidadView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerDetalleGrillaRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaRTL(RequestDashboard request) {
		return armarDetalleGrillas(request, TipoBancaEnum.BANCA_PERSONAL,
				EstadisticasConstants.DETALLE_GRILLA_ANALISIS_CARTERA_RTL);
	}
 
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerDetalleGrillaBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<DetalleGrillaRentabilidadView> obtenerDetalleGrillaBPriv(RequestDashboard request) {
		return armarDetalleGrillas(request, TipoBancaEnum.BANCA_PRIVADA,
				EstadisticasConstants.DETALLE_GRILLA_ANALISIS_CARTERA_BP);
	}

	/**
	 * Armar detalle grillas.
	 *
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @return the respuesta
	 */
	private Respuesta<DetalleGrillaRentabilidadView> armarDetalleGrillas(RequestDashboard request,
			TipoBancaEnum tipoBanca, String codigoEstadistica) {

		if (sesionParametros.getRegistroSession().isMobile()) {
			request.setClasificacion("PROD");
		}
		
		 Respuesta<DetalleSubclasificionDTO> respuestaDTO =
		 analisisCarteraBO.obtenerDetalleSubClasificacion(sesionCliente.getCliente(),
		 request, tipoBanca);
 
		if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_ANALISIS_CARTERA);
		}
		DetalleRentPorSubclasifEntity detalleDTO = respuestaDTO.getRespuesta().getResultado();
		DetalleGrillaRentabilidadView detalleGrilla = new DetalleGrillaRentabilidadView();
		detalleGrilla.setFechas(detalleDTO.getPeriodoFechaInicial() + " al " + detalleDTO.getPeriodoFechaFinal());
		List<OpcionDetalleMenuView> menuProductos = new ArrayList<OpcionDetalleMenuView>();

		for (DetallePorAgrupacionEntity agrupacion : detalleDTO.getResultadoAgrupacion()) {
			OpcionDetalleMenuView opcionMenu = new OpcionDetalleMenuView();
			opcionMenu.setId(agrupacion.getCodigoAgrupacion());
			opcionMenu.setDescripcion(agrupacion.getDescripcionAgrupacion());
			menuProductos.add(opcionMenu);
		}

		if (menuProductos.size() > 1) {
			OpcionDetalleMenuView opcionMenu = new OpcionDetalleMenuView();
			opcionMenu.setId("TOTAL");
			opcionMenu.setDescripcion("Total");
			menuProductos.add(0, opcionMenu);
		}
		detalleGrilla.setListaProductos(menuProductos);
		List<TipoEspecieDetalleView> listaEspecies = new ArrayList<TipoEspecieDetalleView>();
		if ("TOTAL".equals(request.getProducto())) {
			detalleGrilla.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(detalleDTO.getMoneda()).getSimbolo() + " "
					+ ISBANStringUtils.formatearSaldoConSigno(detalleDTO.getRentabilidadNeta()));
			detalleGrilla.setRendimientoTxt(detalleDTO.getRendimiento().toString().replace(".", ",") + " %");
			detalleGrilla.setRendimiento(detalleDTO.getRendimiento());
			detalleGrilla.setTna(detalleDTO.getTna().toString().replace(".", ",") + " %");
			for (DetallePorAgrupacionEntity agrupacion : detalleDTO.getResultadoAgrupacion()) {
				TipoEspecieDetalleView especieView = new TipoEspecieDetalleView();
				especieView.setCabecera(armarCabeceraProducto(agrupacion, detalleDTO.getMoneda()));
				List<ProductoDetalleView> listaProductos = armarListaProductos(agrupacion.getResultadoInstrumento(), detalleDTO.getMoneda());
				especieView.setListaProductos(listaProductos);
				listaEspecies.add(especieView);
			}
		} else {
			for (DetallePorAgrupacionEntity agrupacion : detalleDTO.getResultadoAgrupacion()) {
				if (request.getProducto().equals(agrupacion.getCodigoAgrupacion())) {
					detalleGrilla.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(detalleDTO.getMoneda()).getSimbolo() + " "
							+ ISBANStringUtils.formatearSaldoConSigno(agrupacion.getRentabilidadNeta()));
					detalleGrilla.setRendimientoTxt(agrupacion.getRendimiento().toString().replace(".", ",") + " %");
					detalleGrilla.setRendimiento(agrupacion.getRendimiento());
					detalleGrilla.setTna(agrupacion.getTna().toString().replace(".", ",") + " %");
					TipoEspecieDetalleView especieView = new TipoEspecieDetalleView();
					especieView.setCabecera(armarCabeceraProducto(agrupacion, detalleDTO.getMoneda()));
					List<ProductoDetalleView> listaProductos = armarListaProductos(
							agrupacion.getResultadoInstrumento(), detalleDTO.getMoneda());
					especieView.setListaProductos(listaProductos);
					listaEspecies.add(especieView);
				}
			}
		}
		detalleGrilla.setListaEspecies(listaEspecies);
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(detalleGrilla);

	}
	
	/**
	 * Armar cabecera producto.
	 *
	 * @param agrupacion
	 *            the agrupacion
	 * @param moneda
	 *            the moneda
	 * @return the producto detalle view
	 */
	private ProductoDetalleView armarCabeceraProducto(DetallePorAgrupacionEntity agrupacion, String moneda) {
		
		ProductoDetalleView cabecera = new ProductoDetalleView();
		cabecera.setDescripcion(agrupacion.getDescripcionAgrupacion());
		cabecera.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno(agrupacion.getRentabilidadNeta()));
		cabecera.setRendimientoTxT(agrupacion.getRendimiento().toString().replaceAll("-",  "").replace(".", ",") + " %");
		cabecera.setRendimiento(agrupacion.getRendimiento());
		cabecera.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
				+ ISBANStringUtils.formatearSaldoConSigno(agrupacion.getRentabilidadNeta()));
		cabecera.setTna(agrupacion.getTna().toString().replace(".", ",") + " %");
		
		return cabecera;
	}

	/**
	 * Armar lista productos.
	 *
	 * @param listaInstrumentosDTO
	 *            the lista instrumentos DTO
	 * @param moneda
	 *            the moneda
	 * @return the list
	 */
	private List<ProductoDetalleView> armarListaProductos(List<DetallePorInstrumentoEntity> listaInstrumentosDTO, String moneda) {

		List<ProductoDetalleView> listaProductos = new ArrayList<ProductoDetalleView>();

		for (DetallePorInstrumentoEntity detalleInstrumento : listaInstrumentosDTO) {
			ProductoDetalleView instrumento = new ProductoDetalleView();
			instrumento.setDescripcion(detalleInstrumento.getDescripcionInstrumento());
			instrumento.setRendimientoTxT(detalleInstrumento.getRendimiento().toString().replace(".", ",") + " %");
			instrumento.setRendimientoTxTMobile(detalleInstrumento.getRendimiento().toString().replace(".", ",") + "%");
			instrumento.setRendimiento(detalleInstrumento.getRendimiento());
			instrumento.setRentabilidadNeta(DivisaEnumCartera.fromCodigoString(moneda).getSimbolo() + " "
						+ ISBANStringUtils.formatearSaldoConSigno(detalleInstrumento.getRentabilidadNeta()));
			instrumento.setTna(detalleInstrumento.getTna().toString().replace(".", ",") + " %");
			instrumento.setTnaMobile(detalleInstrumento.getTna().toString().replace(".", ",") + "%");
			
			listaProductos.add(instrumento);
		}

		return listaProductos;
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerGraficoRentabilidadRTL(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadRTL(RequestDashboard request) {
		return analisisCarteraBO.obtenerGraficoRentabilidad(sesionCliente.getCliente(), request, TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager#obtenerGraficoRentabilidadBPriv(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard)
	 */
	@Override
	public Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidadBPriv(RequestDashboard request) {
		return analisisCarteraBO.obtenerGraficoRentabilidad(sesionCliente.getCliente(), request, TipoBancaEnum.BANCA_PRIVADA);
	}

}
