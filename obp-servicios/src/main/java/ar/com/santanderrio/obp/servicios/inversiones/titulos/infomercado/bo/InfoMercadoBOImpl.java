/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.EspecieDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.IndiceDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.ParametroInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.TipoInstrumentoInfoMercadoEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto.VariacionInfoMercadoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.IndiceResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.InfoMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ParametroFiltroInformacionMercado;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;

/**
 * The Class InfoMercadoBOImpl.
 */
@Component
public class InfoMercadoBOImpl extends InversionesAbstractBO implements InfoMercadoBO {

	/** The ordenes titulos dao. */
	@Autowired
	private OrdenesTitulosDAO ordenesTitulosDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant SEGMENTO. */
	private static final String SEGMENTO_RTL = "RTL";

	/** The Constant SEGMENTO. */
	private static final String SEGMENTO_BP = "BP";

	/** The Constant SI. */
	private static final String SI = "S";

	/** The Constant TIPOBUSQUEDA_TODAS. */
	private static final String TIPOBUSQUEDA_TODAS = "T";

	/** The Constant USD. */
	private static final String USD = "USD";

	/** The Constant ARS. */
	private static final String ARS = "ARS";

	/** The Constant PESOS. */
	private static final String PESOS = "pesos";

	/** The Constant HS. */
	private static final String HS = "hs";

	/** The Constant CERO. */
	private static final String CERO = "0";

	/** The Constant INMEDIATO. */
	private static final String INMEDIATO = "inmediato";

	/** The Constant LIMPIANDO_CACHE. */
	private static final String LIMPIANDO_CACHE = "Limpiando cache {}.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(InfoMercadoBOImpl.class);
	
	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO#obtenerParametrosInfoMercado(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.Boolean)
	 */
	@Override
	public Respuesta<List<ParametroInfoMercadoDTO>> obtenerParametrosInfoMercado(Cliente cliente,
			Boolean esBancaPrivada) {
		ConsultaFiltroInformacionMercadoRequestEntity request = armarRequestFiltroInfoMercado(cliente, esBancaPrivada);
		try {
			ConsultaFiltroInformacionMercadoResponse response = ordenesTitulosDAO.consultaFiltroInfoMercado(request);
			if (CollectionUtils.isEmpty(response.getDatos().getListaParametros())) {
				return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
						TipoError.ERROR_SERVICIO.getDescripcion(),
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			return respuestaFactory.crearRespuestaOk(obtenerListaParametros(response));
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
					TipoError.ERROR_SERVICIO.getDescripcion(), CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Armar request filtro info mercado.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the consulta filtro informacion mercado request entity
	 */
	private ConsultaFiltroInformacionMercadoRequestEntity armarRequestFiltroInfoMercado(Cliente cliente,
			Boolean esBancaPrivada) {
		ConsultaFiltroInformacionMercadoRequestEntity request = new ConsultaFiltroInformacionMercadoRequestEntity();
		setearCanalesRequestMWCanales(request, esBancaPrivada);
		setearDatosRequestMWCanales(request.getDatos(), cliente, esBancaPrivada);
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(esBancaPrivada ? SEGMENTO_BP : SEGMENTO_RTL);
		return request;
	}

	/**
	 * Obtener lista parametros.
	 *
	 * @param response
	 *            the response
	 * @return the list
	 */
	private List<ParametroInfoMercadoDTO> obtenerListaParametros(ConsultaFiltroInformacionMercadoResponse response) {
		List<ParametroInfoMercadoDTO> listaParametros = new ArrayList<ParametroInfoMercadoDTO>();
		for (ParametroFiltroInformacionMercado parametroResponse : response.getDatos().getListaParametros()) {
			ParametroInfoMercadoDTO parametroDTO = new ParametroInfoMercadoDTO();
			parametroDTO.setAgrupamientoId(parametroResponse.getAgrupamientoId());
			parametroDTO.setDescripcion(parametroResponse.getDescripcionParametro());
			parametroDTO.setLegal(parametroResponse.getLeyendaLegal());
			parametroDTO.setDefecto(StringUtils.equals(parametroResponse.getDefecto(), SI));
			parametroDTO.setOrden(parametroResponse.getOrden());
			listaParametros.add(parametroDTO);
		}
		ordenarLista(listaParametros);
		return listaParametros;
	}

	/**
	 * Ordenar lista.
	 *
	 * @param listaParametros
	 *            the lista parametros
	 */
	private void ordenarLista(List<ParametroInfoMercadoDTO> listaParametros) {
		Collections.sort(listaParametros, new Comparator<ParametroInfoMercadoDTO>() {
			@Override
			public int compare(ParametroInfoMercadoDTO parametro1, ParametroInfoMercadoDTO parametro2) {
				return parametro1.getOrden().compareTo(parametro2.getOrden());
			}
		});
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO#obtenerIndices(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.lang.Boolean)
	 */
	@Override
	public Respuesta<List<IndiceDTO>> obtenerIndices(Cliente cliente, Boolean esBancaPrivada) {
		ConsultaIndicesRequest request = armarRequestConsultaIndices(cliente, esBancaPrivada);
		try {
			ConsultaIndicesResponse response = ordenesTitulosDAO.consultaIndices(request);
			return respuestaFactory.crearRespuestaOk(obtenerListaIndices(response));
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
					TipoError.ERROR_SERVICIO.getDescripcion(), CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Obtener lista indices.
	 *
	 * @param response
	 *            the response
	 * @return the list
	 */
	private List<IndiceDTO> obtenerListaIndices(ConsultaIndicesResponse response) {
		List<IndiceDTO> listaIndices = new ArrayList<IndiceDTO>();
		DatosConsultaIndicesResponse datos = response.getDatos();
		List<IndiceResponse> listaIndicesResponse = datos.getListaIndices();
		for (IndiceResponse indiceResponse : listaIndicesResponse) {
			IndiceDTO indiceDTO = new IndiceDTO();
			indiceDTO.setNombre(indiceResponse.getNombreIndice());
			indiceDTO.setUltimo(indiceResponse.getUltimoPrecioOperado());
			indiceDTO.setValor(indiceResponse.getVariacion());
			listaIndices.add(indiceDTO);
		}
		return listaIndices;
	}

	/**
	 * Armar request consulta indices.
	 *
	 * @param cliente
	 *            the cliente
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the consulta indices request
	 */
	private ConsultaIndicesRequest armarRequestConsultaIndices(Cliente cliente, Boolean esBancaPrivada) {
		ConsultaIndicesRequest request = new ConsultaIndicesRequest();
		setearCanalesRequestMWCanales(request, esBancaPrivada);
		setearDatosRequestMWCanales(request.getDatos(), cliente, esBancaPrivada);
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(esBancaPrivada ? SEGMENTO_BP : SEGMENTO_RTL);
		return request;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO#obtenerGrillaInfoMercado(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Cacheable(value = CacheConstants.Names.CACHE_INFOMERCADO, key = "#cliente.nup")
	@Override
	public Respuesta<List<EspecieDTO>> obtenerGrillaInfoMercado(Cliente cliente, InfoMercadoViewIn view) {
		try {
			ConsultaInformacionMercadoResponse response = ordenesTitulosDAO
					.consultaInformacionMercado(obtenerConsultaInformacionMercadoRequest(cliente, view));
			if (CollectionUtils.isEmpty(response.getDatos().getListaInfoMercado())) {
				return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
						TipoError.ERROR_GRILLA_INFOMERCADO.getDescripcion(),
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
			sesionCliente.setActualizacionAutomatica(response.getDatos().getActualizacionAutomatica());
			sesionCliente.setTiempoActualizacion(response.getDatos().getTiempoActualizacion());
			List<EspecieDTO> listasEspeciesDTO = obtenerListaEspeciesDTO(response);
			return respuestaFactory.crearRespuestaOk(listasEspeciesDTO);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
					TipoError.ERROR_GRILLA_INFOMERCADO.getDescripcion(),
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Obtener consulta informacion mercado request.
	 *
	 * @param cliente
	 *            the cliente
	 * @param view
	 *            the view
	 * @return the consulta informacion mercado request
	 */
	private ConsultaInformacionMercadoRequest obtenerConsultaInformacionMercadoRequest(Cliente cliente,
			InfoMercadoViewIn view) {
		ConsultaInformacionMercadoRequest request = new ConsultaInformacionMercadoRequest();
		setearCanalesRequestMWCanales(request, view.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, view.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(view.getEsBancaPrivada() ? SEGMENTO_BP : SEGMENTO_RTL);
		request.getDatos().setAgrupamientoId(view.getAgrupamientoId());
		request.getDatos().setTipoBusqueda(TIPOBUSQUEDA_TODAS);
		return request;
	}

	/**
	 * Obtener lista especies DTO.
	 *
	 * @param response
	 *            the response
	 * @return the list
	 */
	private List<EspecieDTO> obtenerListaEspeciesDTO(ConsultaInformacionMercadoResponse response) {
		List<EspecieDTO> listaEspeciesDTO = new ArrayList<EspecieDTO>();
		for (InfoMercadoResponse infoMercadoResponse : response.getDatos().getListaInfoMercado()) {
			EspecieDTO especieDTO = new EspecieDTO();
			especieDTO.setCodigoEspecie(infoMercadoResponse.getCodigoEspecieRossi().trim());
			especieDTO.setTipo(
					TipoInstrumentoInfoMercadoEnum.obtenerTipoPorCodigo(infoMercadoResponse.getInstrumentoCodigo()));
			especieDTO.setDescripcion(infoMercadoResponse.getDescripcionEspecie());
			especieDTO.setCodigoAmigable(infoMercadoResponse.getCodigoAmigable());
			especieDTO.setMonedaEspecie(infoMercadoResponse.getEmisionMonedaEspecie());
			especieDTO.setMonedaNegociacion(infoMercadoResponse.getMonedaDeNegociacion());
			especieDTO.setPlazo((infoMercadoResponse.getPlazo()));
			especieDTO.setUltimaCotizacion(infoMercadoResponse.getPrecioReferencia());
			especieDTO.setUltimaCotizacionFecha(infoMercadoResponse.getFechaPrecioRef());
			especieDTO.setUltimaCotizacionHora(infoMercadoResponse.getHoraPrecioRef());
			especieDTO.setVariacionValor(infoMercadoResponse.getVariacion());
			especieDTO.setPrecioMaximo(infoMercadoResponse.getPrecioMaximo());
			especieDTO.setPrecioMinimo(infoMercadoResponse.getPrecioMinimo());
			especieDTO.setPrecioCierre(infoMercadoResponse.getPrecioCierreDiaAnterior());
			especieDTO.setValorNominal(infoMercadoResponse.getVolumenNominal());
			especieDTO.setNegociable(StringUtils.equals(SI, infoMercadoResponse.getHabilitado()));
			especieDTO.setTipoEspecie(infoMercadoResponse.getTipoEspecie());
			listaEspeciesDTO.add(especieDTO);
		}
		return listaEspeciesDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO#limpiarCacheGrillaInformacionMercado(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_INFOMERCADO, key = "#cliente.nup")
	@Override
	public void limpiarCacheGrillaInformacionMercado(Cliente cliente) {
		LOGGER.info(LIMPIANDO_CACHE, CacheConstants.CACHE_INFOMERCADO);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.bo.InfoMercadoBO#obtenerVariacionInfoMercado(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn)
	 */
	@Override
	public Respuesta<VariacionInfoMercadoDTO> obtenerVariacionInfoMercado(Cliente cliente, InfoMercadoViewIn view) {
		VariacionInfoMercadoRequest request = obtenerRequestVariacion(cliente, view);
		VariacionInfoMercadoResponse variacionResponse;
		try {
			variacionResponse = ordenesTitulosDAO.variacionInfomercado(request);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(VariacionInfoMercadoDTO.class, null);
		}
		return respuestaFactory.crearRespuestaOk(obtenerVariacionDTO(variacionResponse));
	}

	/**
	 * Obtener request variacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param view
	 *            the view
	 * @return the variacion info mercado request
	 */
	private VariacionInfoMercadoRequest obtenerRequestVariacion(Cliente cliente, InfoMercadoViewIn view) {
		VariacionInfoMercadoRequest request = new VariacionInfoMercadoRequest();
		setearCanalesRequestMWCanales(request, view.getEsBancaPrivada());
		setearDatosRequestMWCanales(request.getDatos(), cliente, view.getEsBancaPrivada());
		request.setDatosFirmados(0);
		request.getDatos().setSegmento(view.getEsBancaPrivada() ? SEGMENTO_BP : SEGMENTO_RTL);
		request.getDatos().setCodigoEspecieRossi(view.getCodigoEspecie());
		request.getDatos().setTipoEspecie(view.getTipoEspecie());
		request.getDatos().setMonedaCotizacion(StringUtils.equalsIgnoreCase(view.getMoneda(), PESOS) ? ARS : USD);
		request.getDatos().setPlazo(formatearPlazo(view.getPlazo()));
		return request;
	}

	/**
	 * Formatear plazo.
	 *
	 * @param plazo
	 *            the plazo
	 * @return the string
	 */
	private String formatearPlazo(String plazo) {
		if (plazo != null) {
			if (StringUtils.equalsIgnoreCase(INMEDIATO, plazo)) {
				return CERO;
			} else {
				return StringUtils.removeEndIgnoreCase(plazo, HS).trim();
			}
		}
		return null;
	}

	/**
	 * Obtener variacion DTO.
	 *
	 * @param variacionResponse
	 *            the variacion response
	 * @return the variacion info mercado DTO
	 */
	private VariacionInfoMercadoDTO obtenerVariacionDTO(VariacionInfoMercadoResponse variacionResponse) {
		VariacionInfoMercadoDTO variacionDTO = new VariacionInfoMercadoDTO();
		variacionDTO.setVariacionHoy(variacionResponse.getDatos().getVariacionDiaHoy());
		variacionDTO.setVariacionMensual(variacionResponse.getDatos().getVariacion30Dias());
		variacionDTO.setVariacionAnual(variacionResponse.getDatos().getVariacion365Dias());
		return variacionDTO;
	}

}
