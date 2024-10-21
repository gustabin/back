/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.EspecieDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.IndiceDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.ParametroInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.TipoInstrumentoInfoMercadoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.VariacionInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.EspecieView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndiceView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InstrumentosEspecieView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.ListasEspeciesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.ParametroInfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.PlazoEspecieView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.VariacionInfoMercadoView;

/**
 * The Class InfoMercadoManagerImpl.
 */
@Component
public class InfoMercadoManagerImpl implements InfoMercadoManager {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The info mercado BO. */
	@Autowired
	private InfoMercadoBO infoMercadoBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The Constant ROJA. */
	private static final String ROJA = "flechita_roja";

	/** The Constant VERDE. */
	private static final String VERDE = "flechita_verde";

	/** The Constant IGUAL. */
	private static final String IGUAL = "igual";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant SIMBOLO_DOLAR. */
	private static final String SIMBOLO_DOLAR = "u$s ";

	/** The Constant SIMBOLO_PESOS. */
	private static final String SIMBOLO_PESOS = "$ ";

	/** The Constant SIMBOLO_PORCENTAJE. */
	private static final String SIMBOLO_PORCENTAJE = " %";

	/** The Constant DOLARES. */
	private static final String DOLARES = "Dólares";

	/** The Constant PESOS. */
	private static final String PESOS = "Pesos";

	/** The Constant EUROS. */
	private static final String EUROS = "Euros";

	/** The Constant ARS. */
	private static final String ARS = "ARS";

	/** The Constant USD. */
	private static final String USD = "USD";

	/** The Constant HS. */
	private static final String HS = " hs";

	/** The Constant INMEDIATO. */
	private static final String INMEDIATO = "Inmediato";

	/** The Constant FORMATO_PRECIO_2_DECIMALES. */
	private static final String FORMATO_PRECIO_2_DECIMALES = "##,###,##0.00";

	/** The Constant FORMATO_PRECIO_7_DECIMALES. */
	private static final String FORMATO_PRECIO_7_DECIMALES = "##,###,##0.00#####";

	/** The Constant EN_BAJA. */
	private static final String EN_BAJA = "en baja";

	/** The Constant EN_ALZA. */
	private static final String EN_ALZA = "en alza";

	/** The Constant TODAS. */
	private static final String TODAS = "todas";

	/** The Constant TODOS. */
	private static final String TODOS = "todos";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#inicioInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<InfoMercadoView> inicioInfoMercado(InfoMercadoViewIn view) {
		Respuesta<List<ParametroInfoMercadoDTO>> respuestaBO = infoMercadoBO
				.obtenerParametrosInfoMercado(sesionCliente.getCliente(), view.getEsBancaPrivada());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.INFOMERCADO_PARAMETROS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(InfoMercadoView.class, respuestaBO.getItemsMensajeRespuesta());
		}
		estadisticaManager.add(EstadisticasConstants.INFOMERCADO_PARAMETROS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		InfoMercadoView infoMercadoView = obtenerInfoMercadoView(respuestaBO.getRespuesta());
		InfoMercadoViewIn viewIn = new InfoMercadoViewIn();
		viewIn.setAgrupamientoId(obtenerIdDefault(infoMercadoView));
		viewIn.setEsBancaPrivada(view.getEsBancaPrivada());
		Respuesta<InfoMercadoView> respuestaGrilla = obtenerGrillaInfoMercado(viewIn, true);
		if (respuestaGrilla.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaWarning(infoMercadoView, respuestaGrilla.getItemsMensajeRespuesta());
		}
		infoMercadoView.setListasEspecies(respuestaGrilla.getRespuesta().getListasEspecies());
		infoMercadoView.setListaPlazos(respuestaGrilla.getRespuesta().getListaPlazos());
		infoMercadoView.setActualizacionAutomatica(respuestaGrilla.getRespuesta().getActualizacionAutomatica());
		infoMercadoView.setTiempoActualizacion(respuestaGrilla.getRespuesta().getTiempoActualizacion());
		return respuestaFactory.crearRespuestaOk(infoMercadoView);
	}

	/**
	 * Obtener info mercado view.
	 *
	 * @param listaParametrosDTO
	 *            the lista parametros DTO
	 * @return the info mercado view
	 */
	private InfoMercadoView obtenerInfoMercadoView(List<ParametroInfoMercadoDTO> listaParametrosDTO) {
		InfoMercadoView infoMercadoView = new InfoMercadoView();
		List<ParametroInfoMercadoView> listaParametrosView = new ArrayList<ParametroInfoMercadoView>();
		for (ParametroInfoMercadoDTO parametroDTO : listaParametrosDTO) {
			ParametroInfoMercadoView parametroView = new ParametroInfoMercadoView();
			parametroView.setAgrupamientoId(parametroDTO.getAgrupamientoId());
			parametroView.setDefecto(parametroDTO.getDefecto());
			parametroView.setDescripcion(parametroDTO.getDescripcion());
			parametroView.setLegal(parametroDTO.getLegal());
			listaParametrosView.add(parametroView);
		}
		infoMercadoView.setParametros(listaParametrosView);
		return infoMercadoView;
	}

	/**
	 * Obtener id default.
	 *
	 * @param infoMercadoView
	 *            the info mercado view
	 * @return the string
	 */
	private String obtenerIdDefault(InfoMercadoView infoMercadoView) {
		for (ParametroInfoMercadoView parametro : infoMercadoView.getParametros()) {
			if (parametro.getDefecto()) {
				return parametro.getAgrupamientoId();
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#obtenerIndices(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<IndicesView> obtenerIndices(InfoMercadoViewIn view) {
		Respuesta<List<IndiceDTO>> respuestaBO = infoMercadoBO.obtenerIndices(sesionCliente.getCliente(),
				view.getEsBancaPrivada());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.INFO_MERCADO_INDICES,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(IndicesView.class, respuestaBO.getItemsMensajeRespuesta());
		}
		IndicesView indicesView = obtenerIndicesView(respuestaBO.getRespuesta());
		estadisticaManager.add(EstadisticasConstants.INFO_MERCADO_INDICES,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(indicesView);
	}

	/**
	 * Obtener indices view.
	 *
	 * @param listIndicesDTO
	 *            the list indices DTO
	 * @return the indices view
	 */
	private IndicesView obtenerIndicesView(List<IndiceDTO> listIndicesDTO) {
		IndicesView indicesView = new IndicesView();
		List<IndiceView> lista = new ArrayList<IndiceView>();
		for (IndiceDTO indiceDTO : listIndicesDTO) {
			IndiceView indiceView = mapearDTOAView(indiceDTO);
			lista.add(indiceView);
		}
		indicesView.setListaIndices(lista);
		return indicesView;
	}

	/**
	 * Mapear DTOA view.
	 *
	 * @param indiceDTO
	 *            the indice DTO
	 * @return the indice view
	 */
	private IndiceView mapearDTOAView(IndiceDTO indiceDTO) {
		IndiceView indiceView = new IndiceView();
		indiceView.setNombre(indiceDTO.getNombre().trim());
		indiceView.setUltimo(formatearValor(indiceDTO.getUltimo(), FORMATO_PRECIO_7_DECIMALES));
		indiceView.setValor(formatearValor(indiceDTO.getValor(), FORMATO_PRECIO_2_DECIMALES) + SIMBOLO_PORCENTAJE);
		indiceView.setIndicador(obtenerSimboloIndicador(indiceDTO.getValor()));
		return indiceView;
	}

	/**
	 * Obtener simbolo indicador.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	private String obtenerSimboloIndicador(BigDecimal valor) {
		if (valor != null) {
			if (0 == valor.compareTo(BigDecimal.ZERO)) {
				return IGUAL;
			}
			if (valor.compareTo(BigDecimal.ZERO) > 0) {
				return VERDE;
			} else {
				return ROJA;
			}
		}
		return null;
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
	private String formatearValor(BigDecimal valor, String formato) {
		if (valor == null) {
			return GUION;
		}
		DecimalFormat df = new DecimalFormat(formato);
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
		return df.format(valor);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#obtenerGrillaInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn, java.lang.Boolean)
	 */
	@Override
	public Respuesta<InfoMercadoView> obtenerGrillaInfoMercado(InfoMercadoViewIn view, Boolean esPrimerIngreso) {
		if (llamaServicio(view, esPrimerIngreso)) {
			infoMercadoBO.limpiarCacheGrillaInformacionMercado(sesionCliente.getCliente());
		}
		Respuesta<List<EspecieDTO>> respuestaBO = infoMercadoBO.obtenerGrillaInfoMercado(sesionCliente.getCliente(),
				view);
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			estadisticaManager.add(EstadisticasConstants.INFOMERCADO_GRILLA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(InfoMercadoView.class, respuestaBO.getItemsMensajeRespuesta());
		}
		List<EspecieDTO> listaEspecies = respuestaBO.getRespuesta();
		CollectionUtils.filter(listaEspecies, predicatePalabraClave(view.getPalabraClave()));
		CollectionUtils.filter(listaEspecies, predicatePlazo(view.getPlazo()));
		CollectionUtils.filter(listaEspecies, predicateVariacion(view.getVariacion()));
		if (llamaServicio(view, esPrimerIngreso)) {
			estadisticaManager.add(EstadisticasConstants.INFOMERCADO_GRILLA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sesionParametros.setIdAgrupamientoInformacionMercado(view.getAgrupamientoId());
		} else if (listaEspecies.isEmpty()) {
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
					TipoError.SIN_RESULTADOS.getDescripcion(),
					CodigoMensajeConstantes.INVERSIONES_SIN_ORDENES_Y_OPERACIONES);
		}
		return respuestaFactory.crearRespuestaOk(obtenerListasEspeciesView(listaEspecies));
	}

	/**
	 * Llama servicio.
	 *
	 * @param view
	 *            the view
	 * @param esPrimerIngreso
	 *            the es primer ingreso
	 * @return the boolean
	 */
	private Boolean llamaServicio(InfoMercadoViewIn view, Boolean esPrimerIngreso) {
		return esPrimerIngreso || !StringUtils.equals(view.getAgrupamientoId(),
				sesionParametros.getIdAgrupamientoInformacionMercado());
	}

	/**
	 * Obtener listas especies view.
	 *
	 * @param listaEspeciesDTO
	 *            the lista especies DTO
	 * @return the info mercado view
	 */
	private InfoMercadoView obtenerListasEspeciesView(List<EspecieDTO> listaEspeciesDTO) {
		
		InfoMercadoView infoMercadoView = new InfoMercadoView();
		ListasEspeciesView listasEspeciesView = new ListasEspeciesView();
		List<String> listaPlazos = new ArrayList<String>();
		InstrumentosEspecieView especiesPesos = obtenerInstrumentosEspecieView(listaEspeciesDTO, true, listaPlazos);
		InstrumentosEspecieView especiesDolares = obtenerInstrumentosEspecieView(listaEspeciesDTO, false, listaPlazos);
		listasEspeciesView.setEspeciesPesos(especiesPesos);
		listasEspeciesView.setEspeciesDolares(especiesDolares);
		listasEspeciesView.setMensajeBuscador(mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.INVERSIONES_SIN_ORDENES_Y_OPERACIONES).getMensaje());
		infoMercadoView.setListasEspecies(listasEspeciesView);
		ordenarPlazos(listaPlazos);
		if (listaPlazos.size() > 1) {
			listaPlazos.add(0, StringUtils.capitalize(TODOS));
		}
		infoMercadoView.setListaPlazos(listaPlazos);
		infoMercadoView.setActualizacionAutomatica(sesionCliente.getActualizacionAutomatica());
		infoMercadoView.setTiempoActualizacion(sesionCliente.getTiempoActualizacion());
		return infoMercadoView;
	}

	/**
	 * Ordenar plazos.
	 *
	 * @param listaPlazos
	 *            the lista plazos
	 */
	private void ordenarPlazos(List<String> listaPlazos) {
		Collections.sort(listaPlazos, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				if (StringUtils.equals(INMEDIATO, str1)) {
					return -1;
				} else if (StringUtils.equals(INMEDIATO, str2)) {
					return 1;
				}
				return str1.compareTo(str2);
			}

		});
	}

	/**
	 * Obtener instrumentos especie view.
	 *
	 * @param listaEspeciesDTO
	 *            the lista especies DTO
	 * @param buscarEspeciesPesos
	 *            the buscar especies pesos
	 * @param listaPlazos
	 *            the lista plazos
	 * @return the instrumentos especie view
	 */
	private InstrumentosEspecieView obtenerInstrumentosEspecieView(List<EspecieDTO> listaEspeciesDTO,
			Boolean buscarEspeciesPesos, List<String> listaPlazos) {
		List<EspecieView> titulosPublicos = obtenerEspeciesViewPorInstrumento(listaEspeciesDTO, buscarEspeciesPesos,
				TipoInstrumentoInfoMercadoEnum.TITULOS_PUBLICOS);
		List<EspecieView> titulosPrivados = obtenerEspeciesViewPorInstrumento(listaEspeciesDTO, buscarEspeciesPesos,
				TipoInstrumentoInfoMercadoEnum.TITULOS_PRIVADOS);
		List<EspecieView> acciones = obtenerEspeciesViewPorInstrumento(listaEspeciesDTO, buscarEspeciesPesos,
				TipoInstrumentoInfoMercadoEnum.ACCIONES);
		List<EspecieView> cedears = obtenerEspeciesViewPorInstrumento(listaEspeciesDTO, buscarEspeciesPesos,
				TipoInstrumentoInfoMercadoEnum.CEDEARS);
		InstrumentosEspecieView instrumentosEspecieView = new InstrumentosEspecieView();
		instrumentosEspecieView.setTitulosPublicos(obtenerListaDePlazosPorInstrumento(titulosPublicos, listaPlazos));
		instrumentosEspecieView.setTitulosPrivados(obtenerListaDePlazosPorInstrumento(titulosPrivados, listaPlazos));
		instrumentosEspecieView.setAcciones(obtenerListaDePlazosPorInstrumento(acciones, listaPlazos));
		instrumentosEspecieView.setCedears(obtenerListaDePlazosPorInstrumento(cedears, listaPlazos));
		if (titulosPublicos.isEmpty() && titulosPrivados.isEmpty() && acciones.isEmpty() && cedears.isEmpty()) {
			return null;
		}
		return instrumentosEspecieView;
	}

	/**
	 * Obtener lista de plazos por instrumento.
	 *
	 * @param especies
	 *            the especies
	 * @param listaPlazos
	 *            the lista plazos
	 * @return the list
	 */
	private List<PlazoEspecieView> obtenerListaDePlazosPorInstrumento(List<EspecieView> especies,
			List<String> listaPlazos) {
		List<PlazoEspecieView> plazos = new ArrayList<PlazoEspecieView>();
		for (EspecieView especie : especies) {
			for (PlazoEspecieView plazo : especie.getPlazos()) {
				plazos.add(plazo);
				if (!listaPlazos.contains(plazo.getPlazo())) {
					listaPlazos.add(plazo.getPlazo());
				}
			}
		}
		return plazos;
	}

	/**
	 * Obtener especies view por instrumento.
	 *
	 * @param listaEspeciesDTO
	 *            the lista especies DTO
	 * @param buscarEspeciesPesos
	 *            the buscar especies pesos
	 * @param instrumento
	 *            the instrumento
	 * @return the list
	 */
	private List<EspecieView> obtenerEspeciesViewPorInstrumento(List<EspecieDTO> listaEspeciesDTO,
			Boolean buscarEspeciesPesos, TipoInstrumentoInfoMercadoEnum instrumento) {
		List<EspecieView> especiesView = new ArrayList<EspecieView>();
		String monedaNegociacion = buscarEspeciesPesos ? ARS : USD;
		for (EspecieDTO especieDTO : listaEspeciesDTO) {
			if (StringUtils.equals(monedaNegociacion, especieDTO.getMonedaNegociacion())
					&& especieDTO.getTipo().equals(instrumento)) {
				PlazoEspecieView plazoView = obtenerPlazoEspecieView(especieDTO,
						buscarEspeciesPesos ? SIMBOLO_PESOS : SIMBOLO_DOLAR, instrumento);
				EspecieView especieView = buscarEspeciePorCodigo(especieDTO.getCodigoEspecie(), especiesView);
				if (especieView != null) {
					especieView.getPlazos().add(plazoView);
				} else {
					especieView = obtenerEspecieView(plazoView, especieDTO);
					especiesView.add(especieView);
				}
			}
		}
		return especiesView;
	}

	/**
	 * Obtener especie view.
	 *
	 * @param plazoView
	 *            the plazo view
	 * @param especieDTO
	 *            the especie DTO
	 * @return the especie view
	 */
	private EspecieView obtenerEspecieView(PlazoEspecieView plazoView, EspecieDTO especieDTO) {
		EspecieView especieView = new EspecieView();
		especieView.setCodigoEspecie(especieDTO.getCodigoEspecie().trim());
		List<PlazoEspecieView> plazos = new ArrayList<PlazoEspecieView>();
		plazos.add(plazoView);
		especieView.setPlazos(plazos);
		return especieView;
	}

	/**
	 * Obtener plazo especie view.
	 *
	 * @param especieDTO
	 *            the especie DTO
	 * @param simboloMoneda
	 *            the simbolo moneda
	 * @param instrumento
	 *            the instrumento
	 * @return the plazo especie view
	 */
	private PlazoEspecieView obtenerPlazoEspecieView(EspecieDTO especieDTO, String simboloMoneda,
			TipoInstrumentoInfoMercadoEnum instrumento) {
		PlazoEspecieView plazoView = new PlazoEspecieView();
		plazoView.setTipo(instrumento.getDescripcion());
		plazoView.setDescripcion(especieDTO.getDescripcion().trim());
		plazoView.setCodigoAmigable(especieDTO.getCodigoAmigable().trim());
		plazoView.setMoneda(formatearMoneda(especieDTO.getMonedaEspecie()));
		plazoView.setPlazo(formatearPlazo(especieDTO.getPlazo()));
		plazoView.setUltimaCotizacion(
				simboloMoneda + formatearValor(especieDTO.getUltimaCotizacion(), FORMATO_PRECIO_7_DECIMALES));
		plazoView.setUltimaCotizacionFecha(formatearFecha(especieDTO.getUltimaCotizacionFecha()));
		plazoView.setUltimaCotizacionHora(formatearHora(especieDTO.getUltimaCotizacionHora()));
		plazoView.setVariacionValor(formatearVariacion(especieDTO.getVariacionValor(), FORMATO_PRECIO_2_DECIMALES));
		plazoView.setVariacionIcono(obtenerSimboloIndicador(especieDTO.getVariacionValor()));
		plazoView.setPrecioMinimo(
				simboloMoneda + formatearValor(especieDTO.getPrecioMinimo(), FORMATO_PRECIO_7_DECIMALES));
		plazoView.setPrecioMaximo(
				simboloMoneda + formatearValor(especieDTO.getPrecioMaximo(), FORMATO_PRECIO_7_DECIMALES));
		plazoView.setPrecioCierre(
				simboloMoneda + formatearValor(especieDTO.getPrecioCierre(), FORMATO_PRECIO_7_DECIMALES));
		plazoView.setValorNominal(formatearValor(especieDTO.getValorNominal(), FORMATO_PRECIO_7_DECIMALES));
		plazoView.setNegociable(especieDTO.getNegociable());
		plazoView.setCodigoEspecie(especieDTO.getCodigoEspecie());
		plazoView.setTipoEspecie(especieDTO.getTipoEspecie());
		return plazoView;
	}

	/**
	 * Buscar especie por codigo.
	 *
	 * @param codigoEspecie
	 *            the codigo especie
	 * @param listaEspecieView
	 *            the lista especie view
	 * @return the especie view
	 */
	private EspecieView buscarEspeciePorCodigo(String codigoEspecie, List<EspecieView> listaEspecieView) {
		for (EspecieView especieView : listaEspecieView) {
			if (StringUtils.equals(codigoEspecie, especieView.getCodigoEspecie())) {
				return especieView;
			}
		}
		return null;
	}

	/**
	 * Formatear variacion.
	 *
	 * @param valor
	 *            the valor
	 * @param formato
	 *            the formato
	 * @return the string
	 */
	private String formatearVariacion(BigDecimal valor, String formato) {
		String valorFormateado = formatearValor(valor, formato);
		if (StringUtils.equals(valorFormateado, GUION)) {
			return valorFormateado;
		}
		return valorFormateado + SIMBOLO_PORCENTAJE;
	}

	/**
	 * Formatear moneda.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the string
	 */
	private String formatearMoneda(String moneda) {
		if (StringUtils.equals(ARS, moneda)) {
			return PESOS;
		}
		if (StringUtils.equals(USD, moneda)) {
			return DOLARES;
		}
		return EUROS;
	}

	/**
	 * Formatear plazo.
	 *
	 * @param plazo
	 *            the plazo
	 * @return the string
	 */
	private String formatearPlazo(Integer plazo) {
		if (plazo == null) {
			return GUION;
		}
		if (plazo == 0) {
			return INMEDIATO;
		}
		return plazo.toString() + HS;
	}

	/**
	 * Formatear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String formatearFecha(String fecha) {
		if (fecha == null) {
			return GUION;
		}
		return StringUtils.left(fecha, 10);
	}

	/**
	 * Formatear hora.
	 *
	 * @param hora
	 *            the hora
	 * @return the string
	 */
	private String formatearHora(String hora) {
		if (hora == null) {
			return GUION;
		}
		return hora + HS;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#obtenerVariacionInfoMercado(ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<VariacionInfoMercadoView> obtenerVariacionInfoMercado(InfoMercadoViewIn view) {
		Respuesta<VariacionInfoMercadoDTO> respuestaDTO = infoMercadoBO
				.obtenerVariacionInfoMercado(sesionCliente.getCliente(), view);
		VariacionInfoMercadoView variacionview = new VariacionInfoMercadoView();
		if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			VariacionInfoMercadoDTO dto = respuestaDTO.getRespuesta();
			variacionview.setVariacionAnual(
					formatearValor(dto.getVariacionAnual(), FORMATO_PRECIO_2_DECIMALES) + SIMBOLO_PORCENTAJE);
			variacionview.setVariacionMensual(
					formatearValor(dto.getVariacionMensual(), FORMATO_PRECIO_2_DECIMALES) + SIMBOLO_PORCENTAJE);
			variacionview.setVariacionHoy(
					formatearValor(dto.getVariacionHoy(), FORMATO_PRECIO_2_DECIMALES) + SIMBOLO_PORCENTAJE);
			variacionview.setFlechaAnual(obtenerSimboloIndicador(dto.getVariacionAnual()));
			variacionview.setFlechaMensual(obtenerSimboloIndicador(dto.getVariacionMensual()));
			variacionview.setFlechaHoy(obtenerSimboloIndicador(dto.getVariacionHoy()));
			estadisticaManager.add(EstadisticasConstants.INFOMERCADO_DETALLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			variacionview.setVariacionAnual(GUION + SIMBOLO_PORCENTAJE);
			variacionview.setVariacionMensual(GUION + SIMBOLO_PORCENTAJE);
			variacionview.setVariacionHoy(GUION + SIMBOLO_PORCENTAJE);
			estadisticaManager.add(EstadisticasConstants.INFOMERCADO_DETALLE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
		}
		return respuestaFactory.crearRespuestaOk(variacionview);
	}

	/**
	 * Predicate palabra clave.
	 *
	 * @param palabraClave
	 *            the palabra clave
	 * @return the predicate
	 */
	private Predicate predicatePalabraClave(final String palabraClave) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				if (StringUtils.isBlank(palabraClave)) {
					return true;
				}
				EspecieDTO especieDTO = (EspecieDTO) obj;
				return StringUtils.containsIgnoreCase(especieDTO.getDescripcion(), palabraClave)
						|| StringUtils.containsIgnoreCase(especieDTO.getCodigoAmigable(), palabraClave);
			}
		};
	}

	/**
	 * Predicate plazo.
	 *
	 * @param plazo
	 *            the plazo
	 * @return the predicate
	 */
	private Predicate predicatePlazo(final String plazo) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				if (StringUtils.equalsIgnoreCase(TODOS, plazo) || StringUtils.isBlank(plazo)) {
					return true;
				}
				EspecieDTO especieDTO = (EspecieDTO) obj;
				return StringUtils.equalsIgnoreCase(formatearPlazo(especieDTO.getPlazo()), plazo);
			}
		};
	}

	/**
	 * Predicate variacion.
	 *
	 * @param variacion
	 *            the variacion
	 * @return the predicate
	 */
	private Predicate predicateVariacion(final String variacion) {
		return new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				if (StringUtils.equalsIgnoreCase(TODAS, variacion) || StringUtils.isBlank(variacion)) {
					return true;
				}
				EspecieDTO especieDTO = (EspecieDTO) obj;
				BigDecimal variacionValor = especieDTO.getVariacionValor();
				if (variacionValor != null) {
					if (StringUtils.equalsIgnoreCase(EN_ALZA, variacion)) {
						return variacionValor.compareTo(BigDecimal.ZERO) > 0;
					}
					if (StringUtils.equalsIgnoreCase(EN_BAJA, variacion)) {
						return variacionValor.compareTo(BigDecimal.ZERO) < 0;
					}
					return variacionValor.equals(BigDecimal.ZERO);
				}
				return false;
			}
		};
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#grabarEstadisticaGoToOrdenCompraBancaPersonal()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaGoToOrdenCompraBancaPersonal() {
		estadisticaManager.add(EstadisticasConstants.INFOMERCADO_GOTO_ORDENCOMPRA_BANCAPERSONAL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager#grabarEstadisticaGoToOrdenCompraBancaPrivada()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaGoToOrdenCompraBancaPrivada() {
		estadisticaManager.add(EstadisticasConstants.INFOMERCADO_GOTO_ORDENCOMPRA_BANCAPRIVADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

}
