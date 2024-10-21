/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.ComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.TransaccionViewIn;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ComprobantesManagerImpl.
 * 
 * @author sabrina.cis
 */
@Component
public class ComprobantesManagerImpl implements ComprobantesManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesManagerImpl.class);

	/** The Constant OBTENIENDO_COMPROBANTES. */
	private static final String OBTENIENDO_COMPROBANTES = "Obteniendo comprobantes..";

	/** The Constant OBTENIENDO_COMPROBANTES_PAGINA. */
	private static final String OBTENIENDO_COMPROBANTES_PAGINA = "Coomprobantes pagina {} {}";
	
	/** The comprobantes BO. */
	@Autowired
	private CoordinadorComprobantesBO coordinadorBO;

	/** The limites filtros BO. */
	@Autowired
	private LimitesFiltrosBO limitesFiltrosBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The registro sesion. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/**
	 * Obtiene el listado de comprobantes del cliente.
	 * 
	 * @param viewIn
	 *            the in view. Si view.recargaPagina true si entramos al módulo de
	 *            comprobantes mediante el menú o por presionar F5 en browser
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobantesView> obtenerComprobantes(ComprobantesViewIn viewIn) {
		LOGGER.info(OBTENIENDO_COMPROBANTES);
		if (viewIn.getRecargaPagina()) {
			sesionParametros.setContadorPaginaComprobantes(0);
		}
		if (sesionParametros.getContadorPaginaComprobantes() == 0) {
			coordinadorBO.limpiarCache(sesionCliente.getCliente());
		}
	
		FiltroComprobanteDTO dto = obtenerFiltroDTO(viewIn);

		Respuesta<ComprobantesDTO> respuestaDTO = obtenerComprobantes(dto);

		return procesarCasuisticasYGenerarRespuesta(respuestaDTO);

	}
	
	/**
	 * Obtener filtro DTO.
	 * 
	 * @param viewIn
	 *            the view in
	 * @return the filtro comprobante DTO
	 */
	private FiltroComprobanteDTO obtenerFiltroDTO(ComprobantesViewIn viewIn) {
		boolean hayFiltros = hayFiltros(viewIn);
		if (viewIn.getFechaDesde() == null || viewIn.getFechaHasta() == null) {
			viewIn.setFechaHasta(ISBANStringUtils.formatearFecha(new Date()));
			viewIn.setFechaDesde(obtenerFechaSeisMesesAtras());
		}
		FiltroComprobanteDTO dto = limitesFiltrosBO.obtenerFiltroComprobanteDTO(viewIn, sesionCliente.getCliente());
		dto.setFiltroActivo(hayFiltros);
		return dto;
	}

	/**
	 * Hay filtros.
	 * 
	 * @param viewIn
	 *            the view in
	 * @return true, if successful
	 */
	private boolean hayFiltros(ComprobantesViewIn viewIn) {
		return viewIn.getFechaDesde() != null || viewIn.getFechaHasta() != null || viewIn.getImporteDesde() != null
				|| viewIn.getImporteHasta() != null || viewIn.getEmpresa() != null;
	}

	/**
	 * Obtener fecha seis meses atras.
	 * 
	 * @return the string
	 */
	private String obtenerFechaSeisMesesAtras() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		return ISBANStringUtils.formatearFecha(calendar.getTime());
	}

	/**
	 * Obtener todos los comprobantes.
	 * 
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	public Respuesta<ComprobantesDTO> obtenerComprobantes(FiltroComprobanteDTO dto) {
		if (dto != null) {
			return coordinadorBO.obtenerComprobantes(dto.getCliente(), dto);
		}
		return null;
	}

	/**
	 * Procesar casuisticas Y generar respuesta.
	 * 
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param transaccionDTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesView> procesarCasuisticasYGenerarRespuesta(Respuesta<ComprobantesDTO> respuestaDTO) {

		this.grabarEstadistica(respuestaDTO.getRespuesta().getCodigoEstadistica(),
				respuestaDTO.getRespuesta().getCodigoError());

		if (EstadoRespuesta.ERROR.equals(respuestaDTO.getEstadoRespuesta())) {
			return crearRespuestaERROR(respuestaDTO);
		}
		List<ComprobanteDTO> paginado = obtenerComprobantesPaginados(respuestaDTO);
		Boolean hayMasComprobantes = hayMasComprobantes(paginado, respuestaDTO);
		Respuesta<ComprobantesView> res;
		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())
				|| sesionParametros.getContadorPaginaComprobantes() > 0) {
			res = crearRespuestaOK(paginado, hayMasComprobantes);
		} else {
			res = crearRespuestaWARNING(respuestaDTO, paginado, hayMasComprobantes);
		}
		buscarDebitosAutomaticos(res);
		incrementarPaginador();
		return res;
	}

	private void buscarDebitosAutomaticos(Respuesta<ComprobantesView> respuesta) {
		for (ComprobanteView comprobante : respuesta.getRespuesta().getComprobantes()) {
			comprobante.setMostrarDevolucionDA(comprobante.getTransaccion().equals(TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA.getEtiqueta()));
		}
	}
	
	/**
	 * Crear respuesta OK.
	 * 
	 * @param paginado
	 *            the paginado
	 * @param hayMasComprobantes
	 *            the hay mas comprobantes
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesView> crearRespuestaOK(List<ComprobanteDTO> paginado, Boolean hayMasComprobantes) {
		Respuesta<ComprobantesView> respuesta = respuestaFactory.crearRespuestaOk(ComprobantesView.class,
				new ComprobantesView(paginado, hayMasComprobantes, sesionParametros.getRegistroSession().isMobile()));
		LOGGER.info("Retornando Respuesta OK: ", respuesta);
		return respuesta;
	}

	/**
	 * Crear respuesta WARNING.
	 * 
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param paginado
	 *            the paginado
	 * @param hayMasComprobantes
	 *            the hay mas comprobantes
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesView> crearRespuestaWARNING(Respuesta<ComprobantesDTO> respuestaDTO,
			List<ComprobanteDTO> paginado, Boolean hayMasComprobantes) {
		Respuesta<ComprobantesView> respuesta = respuestaFactory.crearRespuestaWarning(ComprobantesView.class,
				new ComprobantesView(paginado, hayMasComprobantes, sesionParametros.getRegistroSession().isMobile()),
				respuestaDTO.getItemsMensajeRespuesta());
		LOGGER.info("Retornando Respuesta Warning: ", respuesta);
		respuesta.getRespuesta().setLimitePagoTarjetas(respuestaDTO.getRespuesta().getLimitePagoTarjetas());
		respuesta.getRespuesta().setLimitesPMC(respuestaDTO.getRespuesta().getLimitesPMC());
		respuesta.getRespuesta().setLimiteTransferencias(respuestaDTO.getRespuesta().getLimiteTransferencias());
		return respuesta;
	}

	/**
	 * Crear respuesta ERROR.
	 * 
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesView> crearRespuestaERROR(Respuesta<ComprobantesDTO> respuestaDTO) {
		Respuesta<ComprobantesView> respuesta = respuestaFactory
				.crearRespuestaErrorPersonalizado(ComprobantesView.class, respuestaDTO.getItemsMensajeRespuesta());
		if (respuesta.getItemsMensajeRespuesta() != null) {
			List<String> listaDeLimites = limitesFiltrosBO.obtenerLimitesDeFecha();
			respuesta.getItemsMensajeRespuesta().get(0).setExtra(listaDeLimites.toString());
		}
		return respuesta;
	}

	/**
	 * Hay mas comprobantes.
	 * 
	 * @param paginado
	 *            the paginado
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the boolean
	 */
	private Boolean hayMasComprobantes(List<ComprobanteDTO> paginado, Respuesta<ComprobantesDTO> respuestaDTO) {
		return coordinadorBO.hayMasComprobantes(paginado, respuestaDTO.getRespuesta().getComprobantes(),
				sesionParametros.getRegistroSession().isMobile(), sesionParametros.getContadorPaginaComprobantes());
	}

	/**
	 * Obtener comprobantes paginados.
	 * 
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the list
	 */
	private List<ComprobanteDTO> obtenerComprobantesPaginados(Respuesta<ComprobantesDTO> respuestaDTO) {
		List<ComprobanteDTO> paginado = coordinadorBO.filtrar(sesionParametros.getContadorPaginaComprobantes(),
				sesionParametros.getRegistroSession().isMobile(), respuestaDTO.getRespuesta().getComprobantes());
		LOGGER.info(OBTENIENDO_COMPROBANTES_PAGINA, sesionParametros.getContadorPaginaComprobantes(), paginado);
		return paginado;
	}

	/**
	 * Incrementar paginador.
	 */
	private void incrementarPaginador() {
		sesionParametros.setContadorPaginaComprobantes(sesionParametros.getContadorPaginaComprobantes() + 1);
	}

	/**
	 * Grabar estadistica.
	 * 
	 * @param codigoEstadistica
	 *            the codigo estadistica
	 * @param codigoError
	 *            the codigo error
	 */
	public void grabarEstadistica(String codigoEstadistica, String codigoError) {
		estadisticaManager.add(codigoEstadistica, codigoError);
	}

	@Override
	public Respuesta<TransaccionViewIn> solapaPorDefecto() {
		LOGGER.info("Obteniendo solapa de comprobantes por defecto.");
		TransaccionEnum transaccionEnum = coordinadorBO.solapaPorDefecto(sesionCliente.getCliente());
		return respuestaFactory.crearRespuestaOk(new TransaccionViewIn(transaccionEnum.getId()));
	}

	
	
}
