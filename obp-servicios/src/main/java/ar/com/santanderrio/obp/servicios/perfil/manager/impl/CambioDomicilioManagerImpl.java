/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.DatosDomTelOutDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ModificacionCambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ResultadoModificacionDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.TipoDomicilioEnum;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobanteCambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ProvinciaView;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;

/**
 * Clase CambioDomicilioManagerImpl.
 *
 * @author Silvina_Luque
 */

@Component("cambioDomicilioManager")
public class CambioDomicilioManagerImpl implements CambioDomicilioManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioDomicilioManagerImpl.class);

	/** The cambioDomicilio BO. */
	@Autowired
	private CambioDomicilioBO cambioDomicilioBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The datos selectores service. */
	@Autowired
	private DatosSelectoresService datosSelectoresService;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	@Autowired
	private SesionCliente sesionCliente;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The valor desafio cambioDomicilio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.CAMBIODOMICILIO}")
	private Integer valorDesafioPerfilCambioDomicilio;
	
	/** The valor desafio cambioDomicilio para clientes monoproductos. */
	@Value("${TRJCOORD.OPERAINDISTINTO.CAMBIODOMICILIO.CLIENTE.MONOPRODUCTO}")
	private Integer valorDesafioPerfilCambioDomicilioMonoproducto;

	/** The valor desafio cambioDomicilio. */
	private static final String VALOR_DESAFIO_DEFAULT = "3";

	/** The Constant TIPO_DOMICILIO_LABORAL. */
	private static final String TIPO_DOMICILIO_LABORAL = "LAB";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * obtenerInfoAdicionalDomTel(ar.com.santanderrio.obp.servicios.perfil.web.
	 * view.CambioDomicilioView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> obtenerInfoAdicionalDomTel(CambioDomicilioView cambioDomicilioView) {
		LOGGER.debug("CambioDomicilioManager obtenerInfoAdicionalDomTel");
		String cambioDomicilioId = "";
		if (cambioDomicilioView != null) {
			cambioDomicilioId = cambioDomicilioView.getDomicilioId();
		}
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
		Mensaje mensaje;

		Respuesta<DatosDomTelOutDTO> rta = cambioDomicilioBO.obtenerInfoAdicionalDomTel(cambioDomicilioId);
		if (rta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
			feedbackMensajeView.setMensaje(mensaje.getMensaje());
			respuesta.setRespuesta(feedbackMensajeView);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			estadisticaManager.add(EstadisticasConstants.CAMBIO_DOMICILIO_SERVICIO_CBMTELCRM,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuesta;
		}

		feedbackMensajeView.setMensaje("");
		respuesta.setRespuesta(feedbackMensajeView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		estadisticaManager.add(EstadisticasConstants.CAMBIO_DOMICILIO_SERVICIO_CBMTELCRM,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		LOGGER.debug("CambioDomicilioManager Salida ObtenerInfoAdicionalDomTel");
		return respuesta;
	}

	/**
	 * Guardar cambio domicilio.
	 *
	 * @param cambioDomicilioView the cambio domicilio view
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * guardarCambioDomicilio(ar.com.santanderrio.obp.servicios.perfil.web.view.
	 * CambioDomicilioView)
	 */
	@Override
	public Respuesta<CambioDomicilioView> guardarCambioDomicilio(CambioDomicilioView cambioDomicilioView) {
		LOGGER.debug("CambioDomicilioManager guardarCambioDomicilio DomId %s:", cambioDomicilioView);
		Respuesta<CambioDomicilioView> respuestaFinal = new Respuesta<CambioDomicilioView>();

		if (sesionParametros.getContador() == null) {
			sesionParametros.setContador(new ContadorIntentos(2));
			cambioDomicilioView.setPermiteReintentar(true);
		}
		respuestaFinal = validarOperacionRSA(cambioDomicilioView);
		if (respuestaFinal == null || !EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())){
			if(EstadoRespuesta.WARNING.equals(respuestaFinal.getEstadoRespuesta()) && sesionCliente.getCliente().esMonoProductoTarjeta()) {
				respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				return respuestaFinal;
			}
		}
		Respuesta<ResultadoModificacionDomicilioDTO> respuestaBo = cambioDomicilioBO
				.guardarCambioDomicilio(cambioDomicilioView);
		String mensajeFeedBack;
		Mensaje mensaje;

		validaTokenCambioDomicilio(respuestaBo);

		if (respuestaBo.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.AVISO_CAMBIO_DOMICILIO_OK);
			mensajeFeedBack = mensaje.getMensaje();
			cambioDomicilioView.setMensajeFeedback(mensajeFeedBack);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaFinal.setRespuesta(cambioDomicilioView);
			sesionParametros.setContador(null);
			estadisticaManager.add(EstadisticasConstants.CAMBIO_DOMICILIO_CMBDOMIYTEL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		} else {
			if (!sesionParametros.getContador().permiteReintentar()) {
				sesionParametros.setContador(null);
				cambioDomicilioView.setPermiteReintentar(false);
			}
			mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_CMBDOMYTEL);
			mensajeFeedBack = mensaje.getMensaje();
			cambioDomicilioView.setMensajeFeedback(mensajeFeedBack);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);

			List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setTipoError(TipoError.CAMBIO_DOMICILIO_ERROR_GENERICO.getDescripcion());
			itemMensajeRespuesta.setMensaje(mensajeFeedBack);
			mensajesList.add(itemMensajeRespuesta);
			respuestaFinal.setItemMensajeRespuesta(mensajesList);
			respuestaFinal.setRespuesta(cambioDomicilioView);
			estadisticaManager.add(EstadisticasConstants.CAMBIO_DOMICILIO_CMBDOMIYTEL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		LOGGER.debug("CambioDomicilioManager Salida guardarCambioDomicilio");
		return respuestaFinal;
	}

	/**
	 * Valida token cambio domicilio.
	 *
	 * @param respuestaBo the respuesta bo
	 */
	private void validaTokenCambioDomicilio(Respuesta<ResultadoModificacionDomicilioDTO> respuestaBo) {

		if (sesionParametros.getDesafioEnCurso() != null) {
			if ("11492".equals(sesionParametros.getDesafioEnCurso().getCodigoEstadisticaValidacion())) {
				if (respuestaBo.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
					estadisticaManager.add(EstadisticasConstants.VALIDACION_TOKEN_CAMBIO_DOMICILIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				} else {
					estadisticaManager.add(EstadisticasConstants.VALIDACION_TOKEN_CAMBIO_DOMICILIO,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * normalizarDomicilio(ar.com.santanderrio.obp.servicios.perfil.web.view.
	 * CambioDomicilioView)
	 */
	@Override
	public Respuesta<List<CambioDomicilioView>> normalizarDomicilio(CambioDomicilioView domicilioModificadoView) {
		LOGGER.debug("CambioDomicilioManager normalizarDomicilio DomId %s:", domicilioModificadoView);
		Respuesta<List<CambioDomicilioDTO>> respuestaMerlinDTO;
		Respuesta<List<CambioDomicilioView>> respuestaView = new Respuesta<List<CambioDomicilioView>>();
		List<CambioDomicilioView> listaView = new ArrayList<CambioDomicilioView>();
		try {
			respuestaMerlinDTO = cambioDomicilioBO.normalizarDomicilio(domicilioModificadoView);
		} catch (MerlinError1Exception e) {
			listaView.add(domicilioModificadoView);
			respuestaView.setRespuesta(listaView);
			respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
			LOGGER.debug("CambioDomicilioManager Salida normalizarDomicilio DomId %s:",
					domicilioModificadoView.getDomicilioId());
			return respuestaView;
		}
		listaView = crearResultadoMerlinView(respuestaMerlinDTO);

		if (respuestaMerlinDTO.getItemsMensajeRespuesta() != null) {
			respuestaView.addAll(respuestaMerlinDTO.getItemsMensajeRespuesta());
		}
		respuestaView.setRespuesta(listaView);
		respuestaView.setEstadoRespuesta(respuestaMerlinDTO.getEstadoRespuesta());
		LOGGER.debug("CambioDomicilioManager Salida normalizarDomicilio DomId %s:",
				domicilioModificadoView.getDomicilioId());
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * obtenerDomiciliosContacto()
	 */
	@Override
	public Respuesta<DatosDomicilioView> obtenerDomiciliosContacto(Boolean isFromReimpresion) {
		LOGGER.debug("CambioDomicilioManager - obtenerDomiciliosContacto");
		Respuesta<List<CambioDomicilioDTO>> lCambiosDomicilio = cambioDomicilioBO.consultarDomiciliosRegistrados();
		List<CambioDomicilioView> listCdVIew = new ArrayList<CambioDomicilioView>();
		if (lCambiosDomicilio.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				|| lCambiosDomicilio.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {

			ordenarListaDeDomicilios(lCambiosDomicilio.getRespuesta(), isFromReimpresion);

			for (CambioDomicilioDTO cambioDomicilioFiltradoDTO : lCambiosDomicilio.getRespuesta()) {
				if ((cambioDomicilioFiltradoDTO.getTipoDomicilio() != null) && !(cambioDomicilioFiltradoDTO
						.getTipoDomicilio().equals(TipoDomicilioEnum.TIPO_SIN_DOMICLIO.getCampo()))) {

					CambioDomicilioView cdView = new CambioDomicilioView();

					buildDomicilioView(cdView, cambioDomicilioFiltradoDTO);
					cdView.setTitulo(TipoDomicilioEnum.getTipoDomicilio(cdView.getTipoDomicilio()).getDescripcion());

					listCdVIew.add(cdView);
				}
			}

		} else {
			LOGGER.debug("CambioDomicilioManager - obtenerDomiciliosContacto error obteniendo domicilios");
			return respuestaFactory.crearRespuestaErrorPersonalizado(DatosDomicilioView.class,
					lCambiosDomicilio.getItemsMensajeRespuesta().get(0).getMensaje(),
					lCambiosDomicilio.getItemsMensajeRespuesta().get(0).getTipoError());
		}
		LOGGER.debug("CambioDomicilioManager - finalizando obtenerDomiciliosContacto");
		DatosDomicilioView datosDomicilio = new DatosDomicilioView();

		CambioDomicilioView domlab = null;
		List<CambioDomicilioView> domlabs = new ArrayList<CambioDomicilioView>();

		// Recorro los domicilios, y obtengo unicamente el último de los
		// laborales
		for (CambioDomicilioView domicilioItem : listCdVIew) {
			String domicilioLaboral = domicilioItem.getTipoDomicilio();
			if (TIPO_DOMICILIO_LABORAL.equals(domicilioLaboral)) {
				domlab = domicilioItem;
				domlabs.add(domicilioItem);
			}
		}

		listCdVIew.removeAll(domlabs);
		if (domlab != null) {
			listCdVIew.add(domlab);
		}

		ordenarListaDeDomicilios(listCdVIew);
		datosDomicilio.setDomicilios(listCdVIew);
		List<ProvinciaView> provincias = dtoToViewProvincias(datosSelectoresService.obtenerProvincias());
		datosDomicilio.setProvincias(provincias);
		Respuesta<DatosDomicilioView> respuestaDatosDomicilio = respuestaFactory.crearRespuestaOk(datosDomicilio);
		respuestaDatosDomicilio.setItemMensajeRespuesta(lCambiosDomicilio.getItemsMensajeRespuesta());
		return respuestaDatosDomicilio;
	}

	/**
	 * Ordenar lista de domicilios.
	 *
	 * @param lCambiosDomicilio the l cambios domicilio
	 * @param isFromReimpresion the is from reimpresion
	 */
	private void ordenarListaDeDomicilios(List<CambioDomicilioDTO> lCambiosDomicilio, Boolean isFromReimpresion) {
		if (isFromReimpresion) {
			ordenarListaDeDomicliosDesdeReimpresion(lCambiosDomicilio);
		} else {
			ordenarListaDeDomiciliosDesdeCambioDomicilio(lCambiosDomicilio);
		}
	}

	/**
	 * Ordenar lista de domiclios desde reimpresion.
	 *
	 * @param listaDomicilios the lista domicilios
	 */
	private void ordenarListaDeDomicliosDesdeReimpresion(List<CambioDomicilioDTO> listaDomicilios) {
		HashMap<String, CambioDomicilioDTO> domicilioPorTipo = new HashMap<String, CambioDomicilioDTO>();
		for (CambioDomicilioDTO cambioDomicilioDTO : listaDomicilios) {
			if ((cambioDomicilioDTO.getTipoDomicilio() != null)) {
				agregarSiMenorOInexistente(cambioDomicilioDTO, domicilioPorTipo);
			}
		}
	}

	/**
	 * Ordenar lista de domicilios desde cambio domicilio.
	 *
	 * @param listaDomicilios the lista domicilios
	 */
	private void ordenarListaDeDomiciliosDesdeCambioDomicilio(List<CambioDomicilioDTO> listaDomicilios) {
		Collections.sort(listaDomicilios, new Comparator<CambioDomicilioDTO>() {
			@Override
			public int compare(CambioDomicilioDTO dom1, CambioDomicilioDTO dom2) {
				int i = dom1.getSecuenciaDomicilio().compareTo(dom2.getSecuenciaDomicilio());
				if (i != 0) {
					return i;
				}
				return dom1.consultarPrioridad().compareTo(dom2.consultarPrioridad());
			}
		});
	}

	/**
	 * Ordenar lista de domicilios.
	 *
	 * @param domiciliosView the domicilios view
	 */
	private void ordenarListaDeDomicilios(List<CambioDomicilioView> domiciliosView) {
		Collections.sort(domiciliosView, new Comparator<CambioDomicilioView>() {
			@Override
			public int compare(CambioDomicilioView domicilio, CambioDomicilioView domicilio2) {
				return domicilio.consultarPrioridad().compareTo(domicilio2.consultarPrioridad());
			}
		});
	}

	/**
	 * Agregar si menor O inexistente.
	 *
	 * @param cambioDomicilioDTO the cambio domicilio DTO
	 * @param domicilioPorTipo   the domicilio por tipo
	 */
	private void agregarSiMenorOInexistente(CambioDomicilioDTO cambioDomicilioDTO,
			HashMap<String, CambioDomicilioDTO> domicilioPorTipo) {
		if (domicilioPorTipo.containsKey(cambioDomicilioDTO.getTipoDomicilio())
				&& secuenciaMenor(cambioDomicilioDTO, domicilioPorTipo)) {
			domicilioPorTipo.put(cambioDomicilioDTO.getTipoDomicilio(), cambioDomicilioDTO);
		} else if (!domicilioPorTipo.containsKey(cambioDomicilioDTO.getTipoDomicilio())) {
			domicilioPorTipo.put(cambioDomicilioDTO.getTipoDomicilio(), cambioDomicilioDTO);
		}

	}

	/**
	 * Secuencia menor.
	 *
	 * @param cambioDomicilioDTO the cambio domicilio DTO
	 * @param domicilioPorTipo   the domicilio por tipo
	 * @return true, if successful
	 */
	private boolean secuenciaMenor(CambioDomicilioDTO cambioDomicilioDTO,
			HashMap<String, CambioDomicilioDTO> domicilioPorTipo) {
		return Integer.valueOf(cambioDomicilioDTO.getSecuenciaDomicilio()) < Integer
				.valueOf(domicilioPorTipo.get(cambioDomicilioDTO.getTipoDomicilio()).getSecuenciaDomicilio());
	}

	/**
	 * Ver comprobante.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * verComprobante()
	 */
	@Override
	public Respuesta<ComprobanteCambioDomicilioView> verComprobante() {
		LOGGER.debug("CambioDomicilioManager _  iniciando verComprobante");
		ComprobanteCambioDomicilioView comprobante;
		ModificacionCambioDomicilioDTO domicilioModif = sesionParametros.getDomicilioModificado();
		if (domicilioModif != null) {
			CambioDomicilioDTO domicilioOriginal = getCambioDomicilioById(domicilioModif.getDomicilioId());
			if (domicilioOriginal != null && domicilioModif.getResultadoModificacion() != null) {
				comprobante = new ComprobanteCambioDomicilioView(domicilioOriginal, domicilioModif);
			} else {
				LOGGER.debug("CambioDomicilioManager _ No se encontro domicilio original en sesion");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
			}
		} else {
			LOGGER.debug("CambioDomicilioManager _ No se encontro domicilio modificado en sesion");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
		}
		LOGGER.debug("CambioDomicilioManager _  finalizando verComprobante");
		return respuestaFactory.crearRespuestaOk(comprobante);
	}

	/**
	 * Descargar comprobante.
	 *
	 * @return the respuesta
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager#
	 * descargarComprobante()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobante() {
		LOGGER.debug("CambioDomicilioManager _  iniciando descargarComprobante");
		DatosComprobanteEntity datos;
		Respuesta<ReporteView> respuestaView = new Respuesta<ReporteView>();
		ModificacionCambioDomicilioDTO domicilioModif = sesionParametros.getDomicilioModificado();
		if (domicilioModif != null) {
			CambioDomicilioDTO domicilioOriginal = getCambioDomicilioById(domicilioModif.getDomicilioId());
			if (domicilioOriginal != null && domicilioModif.getResultadoModificacion() != null) {
				datos = new DatosComprobanteEntity(domicilioOriginal, domicilioModif);
				Respuesta<Reporte> respuestaReporte = cambioDomicilioBO.descargarComprobante(datos);
				respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
				if (respuestaReporte.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
					ReporteView reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
					respuestaView.setRespuesta(reporteView);
				}
			} else {
				LOGGER.debug("CambioDomicilioManager _ No se encontro domicilio original en sesion");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
			}
		} else {
			LOGGER.debug("CambioDomicilioManager _ No se encontro domicilio modificado en sesion");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CAMBIO_DOMICILIO_GENERICO);
		}
		LOGGER.debug("CambioDomicilioManager _  finalizando descargarComprobante");
		return respuestaView;
	}

	/**
	 * Gets the cambio domicilio by id.
	 *
	 * @param cambioDomicilioID the cambio domicilio ID
	 * @return the cambio domicilio by id
	 */
	private CambioDomicilioDTO getCambioDomicilioById(String cambioDomicilioID) {
		List<CambioDomicilioDTO> lCdDTO = sesionParametros.getDomiciliosCliente();
		if (lCdDTO != null) {
			for (Iterator<CambioDomicilioDTO> iterator = lCdDTO.iterator(); iterator.hasNext();) {
				CambioDomicilioDTO cambioDomicilioDTO = iterator.next();
				if (cambioDomicilioDTO.getDomicilioId().equals(cambioDomicilioID)) {
					return cambioDomicilioDTO;
				}
			}
		}
		LOGGER.debug("CambioDomicilioManager _ No se encontro domicilio en sesion");
		return null;
	}

	/**
	 * Builds the domicilio view.
	 *
	 * @param cdView             the cd view
	 * @param cambioDomicilioDTO the cambio domicilio DTO
	 */
	private void buildDomicilioView(CambioDomicilioView cdView, CambioDomicilioDTO cambioDomicilioDTO) {
		cdView.setApt(cambioDomicilioDTO.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", ""));
		cdView.setCalle(cambioDomicilioDTO.getCalle().replaceAll("\\s+$", "").replaceAll("#", "Ñ"));
		cdView.setCaracteristica(cambioDomicilioDTO.getCaracteristica().replaceAll("\\s", "").replaceAll("-", ""));
		cdView.setPrefijo(cambioDomicilioDTO.getPrefijo().replaceAll("\\s", "").replaceAll("-", ""));
		cdView.setNumeroTelefono(cambioDomicilioDTO.getNumeroTelefono().replaceAll("\\s", "").replaceAll("-", ""));
		cdView.setTelefono(cambioDomicilioDTO.getTelefono().replaceAll("\\s", ""));
		cdView.setLocalidad(cambioDomicilioDTO.getLocalidad().replaceAll("\\s+$", "").replaceAll("#", "Ñ"));
		cdView.setDescProvincia(cambioDomicilioDTO.getDescProvincia());
		cdView.setProvincia(cambioDomicilioDTO.getProvincia().replaceAll("\\s", ""));
		cdView.setCodigoPostal(cambioDomicilioDTO.getCodigoPostal().replaceAll("\\s", ""));
		cdView.setDepartamento(cambioDomicilioDTO.getDepartamento());
		cdView.setPiso(cambioDomicilioDTO.getPiso());
		cdView.setDescPais(cambioDomicilioDTO.getDescPais());
		cdView.setTipoDomicilio(cambioDomicilioDTO.getTipoDomicilio().replaceAll("\\s", ""));
		cdView.setDomicilioId(cambioDomicilioDTO.getDomicilioId());
		cdView.setSecuenciaDomicilio(cambioDomicilioDTO.getSecuenciaDomicilio());
		cdView.setDomicilioOProductoPrendario(cambioDomicilioDTO.getDomicilioOProductoPrendario());
		cdView.setDomicilioOProductoPrivado(cambioDomicilioDTO.getDomicilioOProductoPrivado());
		for (ProductoDTO pDTO : cambioDomicilioDTO.getListaProductos()) {
			cdView.getDescripcionesProductos().add(pDTO.getDescripcionProducto());
		}
	}

	/**
	 * Crear resultado merlin view.
	 *
	 * @param resultadoMerlinDTO the resultado merlin DTO
	 * @return the list
	 */
	private List<CambioDomicilioView> crearResultadoMerlinView(Respuesta<List<CambioDomicilioDTO>> resultadoMerlinDTO) {
		List<CambioDomicilioView> listaView = new ArrayList<CambioDomicilioView>();
		if (EstadoRespuesta.OK.equals(resultadoMerlinDTO.getEstadoRespuesta())) {
			List<CambioDomicilioDTO> listaDTO = resultadoMerlinDTO.getRespuesta();
			if (listaDTO != null) {
				for (CambioDomicilioDTO cdDTO : listaDTO) {
					CambioDomicilioView cdView = new CambioDomicilioView();
					cdView.setApt(cdDTO.getApt().replaceAll("\\s", "").replaceFirst("^0+(?!$)", ""));
					cdView.setCalle(cdDTO.getCalle().replaceAll("\\s+$", ""));
					cdView.setLocalidad(cdDTO.getLocalidad().replaceAll("\\s+$", ""));
					cdView.setDescProvincia(cdDTO.getDescProvincia());
					cdView.setProvincia(cdDTO.getProvincia().replaceAll("\\s", ""));
					cdView.setCodigoPostal(cdDTO.getCodigoPostal().replaceAll("\\s", ""));
					cdView.setDepartamento(cdDTO.getDepartamento().replaceAll("\\s", ""));
					cdView.setPiso(cdDTO.getPiso().replaceAll("\\s", ""));
					cdView.setNormalizar(Boolean.TRUE);
					listaView.add(cdView);
				}
			}
		}
		return listaView;
	}

	/**
	 * Validar operacion RSA.
	 *
	 * @param cambioDomicilioView the cambio domicilio view
	 * @return the respuesta
	 */
	private Respuesta<CambioDomicilioView> validarOperacionRSA(CambioDomicilioView cambioDomicilioView) {
		AutentificacionDTO autentificacionDTO;

		Respuesta<CambioDomicilioView> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(cambioDomicilioView.getDesafio(), sesionCliente.getCliente().esMonoProductoTarjeta() ? valorDesafioPerfilCambioDomicilioMonoproducto : valorDesafioPerfilCambioDomicilio);
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = cambioDomicilioView.getDesafio();
		} else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = cargarAutentificacionDTO(cambioDomicilioView);
		} else {
			return estadoDesafio;
		}

		Respuesta<CambioDomicilioView> respuesta = new Respuesta<CambioDomicilioView>();
		Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		cambioDomicilioView.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
		respuesta.setEstadoRespuesta(respEjecucionMetodoAutentificacion.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta());
		respuesta.setRespuesta(cambioDomicilioView);
		estadisticaManager.add(EstadisticasConstants.CAMBIO_USUARIO_ANTER_RSA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}

	/**
	 * Cargar autentificacion DTO.
	 *
	 * @param cambioDomicilioView the cambio domicilio view
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO cargarAutentificacionDTO(CambioDomicilioView cambioDomicilioView) {
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		if (valorDesafioPerfilCambioDomicilio != null && !sesionCliente.getCliente().esMonoProductoTarjeta()) {
			autentificacionDTO.setOperacion(valorDesafioPerfilCambioDomicilio);
		} else if(valorDesafioPerfilCambioDomicilioMonoproducto != null && sesionCliente.getCliente().esMonoProductoTarjeta()){
			autentificacionDTO.setOperacion(valorDesafioPerfilCambioDomicilioMonoproducto);
		}else {
			autentificacionDTO.setOperacion(Integer.parseInt(VALOR_DESAFIO_DEFAULT));
		}

		if (cambioDomicilioView.getDesafio() != null) {
			autentificacionDTO = cambioDomicilioView.getDesafio();
		}
		PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
		pedidoCoordenada.setIp("192168001001");
		if (autentificacionDTO.getCoordenadasOperacion() != null) {
			autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
		} else {
			CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
			coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
			autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
		}
		autentificacionDTO.setRsaDTO(cambioDomicilioView);
		return autentificacionDTO;
	}

	/**
	 * Dto to view provincias.
	 *
	 * @param lista the lista
	 * @return the list
	 */
	private List<ProvinciaView> dtoToViewProvincias(List<Opcion> lista) {
		List<ProvinciaView> listaRespuesta = new ArrayList<ProvinciaView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			ProvinciaView pciaView = new ProvinciaView();
			pciaView.setId(opcion.getId());
			pciaView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(pciaView);
		}
		return listaRespuesta;
	}

	/**
	 * Consulta de domicilio principal y laboral.
	 *
	 * @return DomiciliosDisponiblesView
	 */
	@Override
	public Respuesta<DomiciliosDisponiblesView> consultarDomicilioPrincipalLaboral() {
		if (sesionParametros.getDomiciliosCliente() == null) {
			Respuesta<List<CambioDomicilioDTO>> domiciliosSesion = cambioDomicilioBO.consultarDomiciliosRegistrados();
			if (!domiciliosSesion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_SERVICIO_MYA,
						CodigoMensajeConstantes.CODIGO_ERROR_FALLO_TOTAL_CAMBIO_DOMICILIO);
			} else {
				sesionParametros.setDomiciliosCliente(domiciliosSesion.getRespuesta());
			}
		}
		List<CambioDomicilioDTO> domicilios = sesionParametros.getDomiciliosCliente();
		sesionParametros.setDomiciliosCliente(domicilios);
		DomiciliosDisponiblesView domiciliosDisponibles = buildDomicilioView(domicilios);
		return respuestaFactory.crearRespuestaOk(domiciliosDisponibles);
	}

	/**
	 * Builds the domicilio view.
	 *
	 * @param datosDomicilioView the datos domicilio view
	 * @return the domicilios disponibles view
	 */
	private DomiciliosDisponiblesView buildDomicilioView(List<CambioDomicilioDTO> domicilios) {
		DomiciliosDisponiblesView domiciliosDisponiblesView = new DomiciliosDisponiblesView();
		List<DomicilioView> domiciliosDisponibles = new ArrayList<DomicilioView>();
		Boolean existeDomPrincipal = false;
		Boolean existeDomLaboral = false;

		DomicilioView domicilioView = new DomicilioView();

		for (CambioDomicilioDTO cambioDomicilioDTO : domicilios) {

			TipoDomicilioEnum tipoDomicilio = TipoDomicilioEnum.getTipoDomicilio(cambioDomicilioDTO.getTipoDomicilio());

			if (muestraDomicilio(tipoDomicilio)) {
				// Se debe cargar 1 solodomicilio Principal
				if (TipoDomicilioEnum.TIPO_DOMICILIO_PRINCIPAL.equals(tipoDomicilio) && !existeDomPrincipal) {
					domicilioView = new DomicilioView(cambioDomicilioDTO);
					domicilioView.setTipoDomicilio(tipoDomicilio.getDescripcion());

					// Se setea true porque los dom Principal y laboral siempre son modificables
					domicilioView.setModificable(true);
					domicilioView.setEsSucursal(false);
					domiciliosDisponibles.add(domicilioView);
					existeDomPrincipal = true;
				}

				// Se debe cargar el ultimo domicilio laboral
				if (TipoDomicilioEnum.TIPO_DOMICLIO_LABORAL.equals(tipoDomicilio)) {
					domicilioView = new DomicilioView(cambioDomicilioDTO);
					domicilioView.setTipoDomicilio(tipoDomicilio.getDescripcion());

					// Se setea true porque los dom Principal y Laboral siempre son modificables
					domicilioView.setModificable(true);
					domicilioView.setEsSucursal(false);
					existeDomLaboral = true;
				}

			}

		}

		// si existe domicilio laboral agrego
		if (existeDomLaboral) {
			domiciliosDisponibles.add(domicilioView);
		}

		domiciliosDisponiblesView.setDomiciliosDisponibles(domiciliosDisponibles);
		List<ProvinciaView> provincias = dtoToViewProvincias(datosSelectoresService.obtenerProvincias());
		domiciliosDisponiblesView.setProvincias(provincias);
		return domiciliosDisponiblesView;
	}

	private boolean muestraDomicilio(TipoDomicilioEnum tipoDomicilio) {
		return TipoDomicilioEnum.TIPO_DOMICILIO_PRINCIPAL.equals(tipoDomicilio)
				|| TipoDomicilioEnum.TIPO_DOMICLIO_LABORAL.equals(tipoDomicilio);
	}

}