/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesDebitosAutomaticosUnificadosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPMCUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPagoTarjetasBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesSietePorVenticuatroBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesTransferenciasUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.CoordinadorComprobantesBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.LimitesFiltrosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.FiltroComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;

/**
 * The Class ComprobantesBOImpl.
 * 
 * @author sabrina.cis
 */
@Component
public class CoordinadorComprobantesBOImpl extends ComprobantesBOImpl implements CoordinadorComprobantesBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CoordinadorComprobantesBOImpl.class);

	/** The Constant ORDENANDO_COMPROBANTES. */
	private static final String ORDENANDO_COMPROBANTES = "Ordenando Comprobantes...";

	/** The Constant FILTRANDO_COMPROBANTES. */
	private static final String FILTRANDO_COMPROBANTES = "Filtrando Comprobantes. Pagina: ";
	
    /** The Constant LIMPIANDO_CACHE. */
	private static final String LIMPIANDO_CACHE = "Limpiando cache {}.";

	/** The paginado desktop. */
	@Value("${COMPROBANTES.PAGINADO.DESKTOP:10}")
	private String paginadoDesktop;

	/** The paginado mobile. */
	@Value("${COMPROBANTES.PAGINADO.MOBILE:50}")
	private String paginadoMobile;

	/** The paginado mobile. */
	@Value("${TOPE.CANTIDAD.COMPROBANTES}")
	private String topeComprobantes;

	@Value("${COMPROBANTES.SOLAPADEFAULT:1}")
	private int solapaDefault;

	/** The comprobantes 7x24 BO. */
	@Autowired
	private ComprobantesSietePorVenticuatroBO comprobantes7x24BO;

	/** The debitos unificados BO. */
	@Autowired
	private ComprobantesDebitosAutomaticosUnificadosBO debitosUnificadosBO;

	/** The pago tarjetas BO. */
	@Autowired
	private ComprobantesPagoTarjetasBO pagoTarjetasBO;

	/** The comprobantes PMC unificado BO. */
	@Autowired
	private ComprobantesPMCUnificadoBO comprobantesPMCUnificadoBO;

	/** The transferencias. */
	@Autowired
	private ComprobantesTransferenciasUnificadoBO transferencias;

	/** The Limites filtros. */
	@Autowired
	private LimitesFiltrosBO limitesFiltros;

	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesBO#
	 * limpiarCache(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_COMPROBANTES_SCOMP, key = "#cliente.nup")
	@Override
	public void limpiarCache(Cliente cliente) {
		LOGGER.info(LIMPIANDO_CACHE, CacheConstants.CACHE_COMPROBANTES_SCOMP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * CoordinadorComprobantesBO #
	 * obtenerComprobantesFiltrados(ar.com.santanderrio.obp.servicios. comprobantes
	 * .dto.FiltroComprobanteDTO)
	 */
	@Cacheable(value = CacheConstants.Names.CACHE_COMPROBANTES_SCOMP, key = "#cliente.nup")
	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantes(Cliente cliente, FiltroComprobanteDTO dto) {
		LOGGER.info(INICIO_COMPROBANTES);
		try {
			List<Future<Respuesta<ComprobantesDTO>>> respuesta = new ArrayList<Future<Respuesta<ComprobantesDTO>>>();
			for (TransaccionDTO i : dto.getTransacciones()) {
				if (i != null && !dto.getFechaError()) {
					Respuesta<ComprobantesDTO> error = llenarListaConThreadsOError(cliente, dto, i, respuesta);
					if (error != null) {
						return error;
					}
				}
			}
			sleepWhileExecute(respuesta);
			List<Respuesta<ComprobantesDTO>> respuestas = obtenerListaDeRespuestas(respuesta);
			return obtenerRespuestaComprobantesFiltrados(respuestas, dto);
		} catch (NullPointerException e) {
			LOGGER.error("Error al obtener los comprobantes para el cliente {} con el filtro {}", cliente.getNup(), dto,
					e);
			return crearRespuestaErrorOtraCasuistica(dto.getFiltroActivo(), generarCodigoEstadistica(dto));
		}
	}

	/**
	 * Llenar lista con threads O error.
	 * 
	 * @param cliente
	 *            the cliente
	 * @param dto
	 *            the dto
	 * @param i
	 *            the i
	 * @param respuesta
	 *            the respuesta
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> llenarListaConThreadsOError(Cliente cliente, FiltroComprobanteDTO dto,
			TransaccionDTO i, List<Future<Respuesta<ComprobantesDTO>>> respuesta) {
		switch (i.getTransaccion().getId()) {
		case 0:
		case 7:
		case 10:
			// Transaccion:: Pago de Servicios
			respuesta.add(obtenerComprobantesPMC(dto.getCliente(), i));
			break;
		case 1:
			// Transaccion:: Debitos Automaticos
			respuesta.add(debitosUnificadosBO.obtenerComprobantesAsync(cliente, i));
			break;
		case 2:
		case 9:
			// Transaccion:: Transferencias
			respuesta.add(transferencias.obtenerComprobantesTransferenciaAsync(cliente, i));
			break;
		case 3:
			// Transaccion:: Pago Tarjetas
			respuesta.add(pagoTarjetasBO.obtenerComprobantesPagoTarjetasAsync(cliente, i, Boolean.FALSE));
			break;
		case 4:
			// Transaccion:: Otros (Pago de sueldos y honorarios + compra venta dolares)
			respuesta.add(comprobantes7x24BO.obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(cliente, i));
//			respuesta.add(scompBO.obtenerComprobantesCompraVentaDolarAsync(cliente.getNup(), i));
			break;
		case 5:
			// Transaccion:: Pago de sueldos y honorarios
			respuesta.add(comprobantes7x24BO.obtenerComprobantesPagoHaberesYHonorariosUnificadosAsync(cliente, i));
			break;
//		case 6:
//			// Transaccion:: compra venta dolares
//			respuesta.add(scompBO.obtenerComprobantesCompraVentaDolarAsync(cliente.getNup(), i));
//			break;
		case 8:
			// Transaccion:: pago de compras
			respuesta.add(scompBO.obtenerComprobantesPagoDeComprasAsync(cliente.getNup(), i));
			break;
		default:
			return crearRespuestaErrorTotal(dto.getFiltroActivo(), generarCodigoEstadistica(dto));
		}
		return null;
	}

	/**
	 * Obtener respuesta comprobantes filtrados.
	 * 
	 * @param respuestas
	 *            the respuesta
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> obtenerRespuestaComprobantesFiltrados(
			List<Respuesta<ComprobantesDTO>> respuestas, FiltroComprobanteDTO dto) {

		List<ComprobanteDTO> comprobantesDTO = new LinkedList<ComprobanteDTO>();
		List<EstadoRespuesta> estadosRespuesta = new ArrayList<EstadoRespuesta>();
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();

		procesarRespuestas(comprobantesDTO, estadosRespuesta, respuestas, items);

		comprobantesDTO = generarListaDentroDelLimiteEnCache(comprobantesDTO, dto);

		EstadoRespuesta estadoRespuestaFinal = calcularEstadoRespuestaFinal(estadosRespuesta, items);

		return crearRespuestaComprobantes(comprobantesDTO, estadoRespuestaFinal, dto, items);
	}

	/**
	 * Completar listas estado respuesta Y comprobantes DTO.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param estadosRespuesta
	 *            the estados respuesta
	 * @param respuestas
	 *            the respuestas
	 * @param items
	 *            the items
	 */
	private void procesarRespuestas(List<ComprobanteDTO> comprobantesDTO, List<EstadoRespuesta> estadosRespuesta,
			List<Respuesta<ComprobantesDTO>> respuestas, List<ItemMensajeRespuesta> items) {
		for (Respuesta<ComprobantesDTO> respuesta : respuestas) {
			estadosRespuesta.add(respuesta.getEstadoRespuesta());
			if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				comprobantesDTO.addAll(respuesta.getRespuesta().getComprobantes());
			}
			if (respuesta.getItemsMensajeRespuesta() != null && !respuesta.getItemsMensajeRespuesta().isEmpty()) {
				items.addAll(respuesta.getItemsMensajeRespuesta());
			}
		}
		ordenarComprobantes(comprobantesDTO);
	}

	/**
	 * Ordenar comprobantes.
	 * 
	 * @param comprobantes
	 *            the comprobantes
	 */
	private void ordenarComprobantes(List<ComprobanteDTO> comprobantes) {
		LOGGER.info(ORDENANDO_COMPROBANTES);
		Collections.sort(comprobantes, new Comparator<ComprobanteDTO>() {
			@Override
			public int compare(ComprobanteDTO comprobante1, ComprobanteDTO comprobante2) {
				int i = comprobante2.getFecha().compareTo(comprobante1.getFecha());
				if (i != 0) {
					return i;
				}
				return comprobante1.getTipoOperacion().getPrioridad()
						.compareTo(comprobante2.getTipoOperacion().getPrioridad());
			}
		});
	}

	/**
	 * Generar lista dentro del limite en cache.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param dto
	 *            the dto
	 * @return the list
	 */
	private List<ComprobanteDTO> generarListaDentroDelLimiteEnCache(List<ComprobanteDTO> comprobantesDTO,
			FiltroComprobanteDTO dto) {
		actualizarDTOConComprobantesExcedidos(dto, comprobantesDTO, Integer.valueOf(topeComprobantes));
		if (dto.getComprobantesExcedidos()) {
			return comprobantesDTO.subList(0, Integer.valueOf(topeComprobantes));
		}
		return comprobantesDTO;
	}

	/**
	 * Supera limite comprobantes.
	 * 
	 * @param dto
	 *            the dto
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param cant
	 *            the cant
	 * @return true, if successful
	 */
	private boolean actualizarDTOConComprobantesExcedidos(FiltroComprobanteDTO dto,
			List<ComprobanteDTO> comprobantesDTO, int cant) {
		if (comprobantesDTO.size() > cant) {
			dto.setComprobantesExcedidos(true);
			return true;
		}
		dto.setComprobantesExcedidos(false);
		return false;
	}

	/**
	 * Obtener comprobantes PMC.
	 * 
	 * @param cliente
	 *            the cliente
	 * @param i
	 *            the i
	 * @return the future
	 */
	private Future<Respuesta<ComprobantesDTO>> obtenerComprobantesPMC(Cliente cliente, TransaccionDTO i) {
		if (i.getEmpresa() == null) {
			return comprobantesPMCUnificadoBO.obtenerComprobantesPMCAsync(cliente, i);
		} else {
			return comprobantesPMCUnificadoBO.obtenerComprobantesPMCPorEmpresaAsync(cliente, i);
		}
	}

	/**
	 * Sleep while execute.
	 * 
	 * @param threads
	 *            the threads
	 */
	private void sleepWhileExecute(List<Future<Respuesta<ComprobantesDTO>>> threads) {
		while (!terminaronLosHilos(threads)) {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error("error" + e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
	 * CoordinadorComprobantesBO#hayMasComprobantes(java.util.List,
	 * ar.com.santanderrio.obp.base.respuesta.entities.Respuesta, java.lang.Boolean,
	 * java.lang.Integer)
	 */
	@Override
	public Boolean hayMasComprobantes(List<ComprobanteDTO> paginado, List<ComprobanteDTO> comprobantes,
			Boolean isMobile, Integer contadorPagina) {
		if ((!isMobile && paginado.size() == Integer.valueOf(paginadoDesktop))
				|| (isMobile && paginado.size() == Integer.valueOf(paginadoMobile))) {
			List<ComprobanteDTO> comprobantesFiltradosSigPagina = filtrar(contadorPagina + 1, isMobile, comprobantes);
			if (!comprobantesFiltradosSigPagina.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesBO#filtrar(
	 * java.lang.Integer, java.lang.Boolean, java.util.LinkedList)
	 */
	@Override
	public List<ComprobanteDTO> filtrar(Integer pagina, Boolean isMobile, List<ComprobanteDTO> comprobantes) {
		LOGGER.info(FILTRANDO_COMPROBANTES, pagina);
		Integer paginado;
		Integer sizeLista = comprobantes.size();
		if (isMobile) {
			paginado = Integer.valueOf(paginadoMobile);
		} else {
			paginado = Integer.valueOf(paginadoDesktop);
		}
		Integer posicionInicial = pagina * paginado;
		Integer posicionFinal = posicionInicial + paginado;
		if (posicionFinal > sizeLista) {
			posicionFinal = sizeLista;
		}
		if (posicionInicial > posicionFinal) {
			return new ArrayList<ComprobanteDTO>();
		}
		return comprobantes.subList(posicionInicial, posicionFinal);
	}

	/**
	 * Terminaron los hilos.
	 * 
	 * @param threads
	 *            the threads
	 * @return true, if successful
	 */
	private boolean terminaronLosHilos(List<Future<Respuesta<ComprobantesDTO>>> threads) {
		boolean termino = true;
		for (Future<Respuesta<ComprobantesDTO>> thread : threads) {
			if (!thread.isDone()) {
				termino = false;
			}
		}
		return termino;
	}

	/**
	 * Obtener lista de respuestas.
	 * 
	 * @param threads
	 *            the threads
	 * @return the list
	 */
	private List<Respuesta<ComprobantesDTO>> obtenerListaDeRespuestas(
			List<Future<Respuesta<ComprobantesDTO>>> threads) {
		List<Respuesta<ComprobantesDTO>> respuestas = new ArrayList<Respuesta<ComprobantesDTO>>();
		for (Future<Respuesta<ComprobantesDTO>> thread : threads) {
			try {
				respuestas.add(thread.get());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.error(ERROR, e);
				respuestas.add(respuestaFactory.crearRespuestaError(ComprobantesDTO.class,
						new ArrayList<ItemMensajeRespuesta>()));
			} catch (ExecutionException e) {
				LOGGER.error(ERROR, e);
				respuestas.add(respuestaFactory.crearRespuestaError(ComprobantesDTO.class,
						new ArrayList<ItemMensajeRespuesta>()));
			} catch (NullPointerException e) {
				LOGGER.error(ERROR, e);
				respuestas.add(respuestaFactory.crearRespuestaError(ComprobantesDTO.class,
						new ArrayList<ItemMensajeRespuesta>()));
			}
		}
		return respuestas;
	}

	/**
	 * Analiza los estados respuesta de los BO para determinar un estado respuesta
	 * final.
	 * 
	 * @param estadosRespuesta
	 *            the estados respuesta
	 * @param items
	 *            the items
	 * @return the estado respuesta
	 */
	private EstadoRespuesta calcularEstadoRespuestaFinal(List<EstadoRespuesta> estadosRespuesta,
			List<ItemMensajeRespuesta> items) {
		if (estadosRespuesta.contains(EstadoRespuesta.WARNING) || (estadosRespuesta.contains(EstadoRespuesta.OK)
				&& estadosRespuesta.contains(EstadoRespuesta.ERROR))) {
			return EstadoRespuesta.WARNING;
		}
		if (estadosRespuesta.contains(EstadoRespuesta.OK)) {
			return EstadoRespuesta.OK;
		}
		if (estadosRespuesta.contains(EstadoRespuesta.OK) && items != null && !items.isEmpty()) {
			return EstadoRespuesta.WARNING;
		}
		return EstadoRespuesta.ERROR;
	}

	/**
	 * Crea la respuesta final. Casos en orden de retorno: OK, sin comprobantes,
	 * parcial con comprobantes, parcial sin comprobantes, error total, error limite
	 * en cache, error limite de fechas.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param estadoRespuestaFinal
	 *            the estado respuesta final
	 * @param dto
	 *            the dto
	 * @param items
	 *            the items
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaComprobantes(List<ComprobanteDTO> comprobantesDTO,
			EstadoRespuesta estadoRespuestaFinal, FiltroComprobanteDTO dto, List<ItemMensajeRespuesta> items) {
		Boolean filtroActivo = dto.getFiltroActivo();
		String codigoEstadistica = generarCodigoEstadistica(dto);
		switch (estadoRespuestaFinal) {
		case OK:
			if (!comprobantesDTO.isEmpty()) { // (Con Comprobantes)
				if (dto.getComprobantesExcedidos()) {
					// caso 4
					return crearRespuestaErrorLimiteEnCache(comprobantesDTO, filtroActivo, codigoEstadistica);
				}
				if (dto.getExcedidoLimiteParcial()) {
					// caso 5
					return crearRespuestaErrorLimiteDeFechas(comprobantesDTO, filtroActivo, codigoEstadistica);
				}
				if (filtroActivo) {
					return getRespuestaFactory().crearRespuestaOk(new ComprobantesDTO(comprobantesDTO,
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK)); // OK
				}
				return getRespuestaFactory().crearRespuestaOk(new ComprobantesDTO(comprobantesDTO, codigoEstadistica,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK)); // OK
			}
			if (dto.getExcedidoLimiteParcial()) {
				return crearRespuestaSinComprobantesLimiteHistorico(filtroActivo, codigoEstadistica);
			}
			// caso 1 (Sin Comprobantes)
			return crearRespuestaSinComprobantes(filtroActivo, dto.getTransacciones(), codigoEstadistica);
		case WARNING:
			return crearRespuestaConAdvertenciaYComprobantes(comprobantesDTO, dto, items, codigoEstadistica);
		case ERROR:
			return crearRespuestaErrorTotal(filtroActivo, codigoEstadistica);// caso 2
		default:
			return crearRespuestaErrorOtraCasuistica(filtroActivo, codigoEstadistica);// caso 6
		}
	}

	private String generarCodigoEstadistica(FiltroComprobanteDTO dto) {
		if (dto.getTransacciones().size() > 1) {
			return EstadisticasConstants.SOLAPA_COMPROBANTES_TRANSACCIONES;
		}
		return dto.getTransacciones().get(0).getTransaccion().getCodigoEstadistica();
	}

	/**
	 * Crear respuesta sin comprobantes limite historico.
	 *
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaSinComprobantesLimiteHistorico(Boolean filtroActivo,
			String codigoEstadistica) {
		LOGGER.info(CREANDO_RESPUESTA_ERROR);
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		List<String> listaDeLimites = limitesFiltros.obtenerLimitesDeFecha();
		if (filtroActivo) {
			datos.add(new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_LIMITE_MINIMO_FECHA_SUPERADO,
					TipoError.SIN_COMPROBANTES_LIMITE, ""));
			return getRespuestaFactory().crearRespuestaError(datos,
					new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK),
					true);
		}
		datos.add(new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_LIMITE_MINIMO_FECHA_SUPERADO,
				TipoError.SIN_COMPROBANTES_LIMITE, listaDeLimites.toString()));
		return getRespuestaFactory().crearRespuestaError(datos, new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
				codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK), true);
	}

	/**
	 * Crear respuesta con advertencia Y comprobantes.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param dto
	 *            the dto
	 * @param items
	 *            the items
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaConAdvertenciaYComprobantes(List<ComprobanteDTO> comprobantesDTO,
			FiltroComprobanteDTO dto, List<ItemMensajeRespuesta> items, String codigoEstadistica) {
		Boolean filtroActivo = dto.getFiltroActivo();
		if (!comprobantesDTO.isEmpty()) { // (Con Comprobantes)
			if (dto.getExcedidoLimiteParcial() && dto.getComprobantesExcedidos()) {
				// (caso 4 y caso 5)
				return crearRespuestaErrorLimiteDeFechasYLimiteDeComprobantes(comprobantesDTO, filtroActivo,
						codigoEstadistica);
			}
			if (tieneErrorParcial(items) && dto.getComprobantesExcedidos()) {
				// (caso 3 y caso 4)
				return crearRespuestaErrorParcialYLimiteDeComprobantes(comprobantesDTO, filtroActivo,
						codigoEstadistica);
			}
			if (tieneErrorParcial(items) && dto.getExcedidoLimiteParcial()) {
				// (caso 3 y caso 5)
				return crearRespuestaErrorParcialYLimiteDeFechas(comprobantesDTO, filtroActivo, codigoEstadistica);
			}
			if (dto.getExcedidoLimiteParcial() && tieneErrorSinComprobantes(items)) {
				// (caso 1 y caso 5)
				return crearRespuestaErrorSinComprobantesYLimiteDeFecha(comprobantesDTO, filtroActivo,
						codigoEstadistica);
			}
			// caso 3
			return crearRespuestaErrorParcialConComprobantes(comprobantesDTO, filtroActivo, codigoEstadistica);
		} else {// (Sin Comprobantes)
			// (caso 1 y caso 3)
			return crearRespuestaErrorParcialYSinComprobantes(filtroActivo, codigoEstadistica);
		}
	}

	/**
	 * Tiene error sin comprobantes.
	 * 
	 * @param items
	 *            the items
	 * @return true, if successful
	 */
	private boolean tieneErrorSinComprobantes(List<ItemMensajeRespuesta> items) {
		for (ItemMensajeRespuesta item : items) {
			if (TipoError.SIN_COMPROBANTES.getDescripcion().equals(item.getTipoError())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tiene error parcial.
	 * 
	 * @param items
	 *            the items
	 * @return true, if successful
	 */
	private boolean tieneErrorParcial(List<ItemMensajeRespuesta> items) {
		for (ItemMensajeRespuesta item : items) {
			if (TipoError.ALERTA_COMPROBANTES.getDescripcion().equals(item.getTipoError())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * CASO_1: Inicio: Los servicios funcionaron OK pero el cliente no tiene ningun
	 * comprobante generado. Buscador: Crear respuesta error caso 1 sin
	 * comprobantes.
	 * 
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaSinComprobantes(Boolean filtroActivo,
			List<TransaccionDTO> transacciones, String codigoEstadistica) {
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		if (filtroActivo) {
			datos.add(new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_SIN_COMPROBANTES,
					TipoError.SIN_COMPROBANTES_EN_BUSCADOR, ""));
			return getRespuestaFactory().crearRespuestaError(datos,
					new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_OK),
					true);
		}
		String codigoMensaje = generarMensajeSinComprobantes(transacciones);
		datos.add(new DatoItemMensaje(codigoMensaje, TipoError.SIN_COMPROBANTES, ""));
		return getRespuestaFactory().crearRespuestaError(datos, new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
				codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK), true);

	}

	private String generarMensajeSinComprobantes(List<TransaccionDTO> transacciones) {
		if (transacciones.size() > 1) {
			return CodigoMensajeConstantes.SIN_COMPROBANTES_OTROS;
		}
		TransaccionEnum transaccion = transacciones.get(0).getTransaccion();
		switch (transaccion) {
		case PAGOS_DE_SERVICIOS:
		case GOTO_PAGOSERVICIOS:
			return CodigoMensajeConstantes.SIN_COMPROBANTES_PMC;
		case DEBITO_AUTOMATICO:
			return CodigoMensajeConstantes.SIN_COMPROBANTES_DEBITOS_AUTOMATICOS;
		case TRANSFERENCIAS:
		case GOTO_TRANSFERENCIAS:
			return CodigoMensajeConstantes.SIN_COMPROBANTES_TRANSFERENCIAS;
		case PAGO_DE_TARJETA:
			return CodigoMensajeConstantes.SIN_COMPROBANTES_PAGO_DE_TARJETAS;
		case OTROS:
		case PAGO_HABERES_HONORARIOS:
		case COMPRA_VENTA:
		case PAGO_SERVICIOS:
		case PAGO_COMPRAS:
			return CodigoMensajeConstantes.SIN_COMPROBANTES_OTROS;
		}
		return null;
	}

	/**
	 * CASO_2: Inicio: Crear respuesta error total. Buscador: Error Total. Los
	 * servicios que se consultaron para la busqueda, fallaron en su totalidad.
	 * 
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorTotal(Boolean filtroActivo, String codigoEstadistica) {
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		if (filtroActivo) {
			datos.add(
					new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_ERROR_TOTAL, TipoError.ERROR_COMPROBANTES, ""));
			return getRespuestaFactory().crearRespuestaError(datos,
					new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR),
					true);
		}
		datos.add(new DatoItemMensaje(CodigoMensajeConstantes.ERROR_COMPROBANTES, TipoError.ERROR_COMPROBANTES, ""));
		return getRespuestaFactory().crearRespuestaError(datos, new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
				codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR), true);
	}

	/**
	 * CASO_3: Inicio: Algunos servicios fallaron pero otros dieron OK y devolvieron
	 * comprobantes. Buscador: WARNING: Busqueda con error parcial. De los servicios
	 * que fueron consultados para realizar la búsqueda, alguno retorno con fallas.
	 * Advertencia sobre la grilla de inicio.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorParcialConComprobantes(List<ComprobanteDTO> comprobantesDTO,
			Boolean filtroActivo, String codigoEstadistica) {
		if (filtroActivo) {
			return respuestaFactory.crearRespuestaWarning(
					new ComprobantesDTO(comprobantesDTO, EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL),
					"", TipoError.ALERTA_COMPROBANTES, CodigoMensajeConstantes.FILTROS_ERROR_PARCIAL);
		}
		return getRespuestaFactory().crearRespuestaWarning(
				new ComprobantesDTO(comprobantesDTO, codigoEstadistica,
						EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL),
				"", TipoError.ALERTA_COMPROBANTES, CodigoMensajeConstantes.ERROR_PARCIAL_COMPROBANTES);
	}

	/**
	 * CASO_4: Inicio y Buscador: Limite de comprobantes. La consulta que se realizo
	 * para la búsqueda, retorno una cantidad de comprobantes que supera el máximo
	 * permitido. Advertencia debajo de la grilla de inicio.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorLimiteEnCache(List<ComprobanteDTO> comprobantesDTO,
			Boolean filtroActivo, String codigoEstadistica) {
		LOGGER.info(CREANDO_RESPUESTA_ERROR);
		List<String> listaDeLimites = limitesFiltros.obtenerLimitesDeFecha();
		if (filtroActivo) {
			return respuestaFactory.crearRespuestaWarning(
					new ComprobantesDTO(comprobantesDTO, EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING, listaDeLimites),
					"excedidoDeLimiteDeComprobantes", TipoError.EXCEDIDO_CANTIDAD_COMPROBANTES,
					CodigoMensajeConstantes.FILTROS_LIMITE_COMPROBANTES_SUPERADO);
		}
		return respuestaFactory.crearRespuestaWarning(
				new ComprobantesDTO(comprobantesDTO, codigoEstadistica,
						EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING, listaDeLimites),
				"excedidoDeLimiteDeComprobantes", TipoError.EXCEDIDO_CANTIDAD_COMPROBANTES,
				CodigoMensajeConstantes.FILTROS_LIMITE_COMPROBANTES_SUPERADO);
	}

	/**
	 * CASO_5: Inicio y Buscador: Limite minimo de fecha superado. Los parametros de
	 * fecha enviados a por lo menos uno de los servicios a invocar, supera el
	 * limite de fecha Desde permitido. Advertencia sobre la grilla de inicio.
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorLimiteDeFechas(List<ComprobanteDTO> comprobantesDTO,
			Boolean filtroActivo, String codigoEstadistica) {
		LOGGER.info(CREANDO_RESPUESTA_ERROR);
		List<String> listaDeLimites = limitesFiltros.obtenerLimitesDeFecha();
		if (filtroActivo) {
			return respuestaFactory.crearRespuestaWarning(
					new ComprobantesDTO(comprobantesDTO, EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING, listaDeLimites),
					"excedidoDeLimiteDeFecha", TipoError.EXCEDIDO_FECHA_COMPROBANTES,
					CodigoMensajeConstantes.FILTROS_LIMITE_MINIMO_FECHA_SUPERADO);
		}
		return respuestaFactory.crearRespuestaWarning(
				new ComprobantesDTO(comprobantesDTO, codigoEstadistica,
						EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING, listaDeLimites),
				"excedidoDeLimiteDeFecha", TipoError.EXCEDIDO_FECHA_COMPROBANTES,
				CodigoMensajeConstantes.FILTROS_LIMITE_MINIMO_FECHA_SUPERADO);
	}

	/**
	 * CASO_6: Inicio y Buscador: Cualquier casuística que no esta contemplada.
	 * Pantalla completa.
	 * 
	 * @param filtroActivo
	 *            the filtro activo
	 * @return the respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorOtraCasuistica(Boolean filtroActivo,
			String codigoEstadistica) {
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		if (filtroActivo) {
			datos.add(
					new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_ERROR_TOTAL, TipoError.ERROR_COMPROBANTES, ""));
			return getRespuestaFactory().crearRespuestaError(datos,
					new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL),
					true);
		}
		datos.add(new DatoItemMensaje(CodigoMensajeConstantes.ERROR_COMPROBANTES, TipoError.ERROR_COMPROBANTES, ""));
		return getRespuestaFactory().crearRespuestaError(datos, new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
				codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL), true);
	}

	/**
	 * CASO_1 y CASO_3: Inicio: Algunos servicios fallaron y otros respondieron OK
	 * pero el cliente no tiene comprobantes.
	 * 
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorParcialYSinComprobantes(Boolean filtroActivo,
			String codigoEstadistica) {
		List<DatoItemMensaje> datos = new ArrayList<DatoItemMensaje>();
		if (filtroActivo) {
			datos.add(new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_ERROR_PARCIAL, TipoError.ALERTA_COMPROBANTES,
					""));
			datos.add(new DatoItemMensaje(CodigoMensajeConstantes.FILTROS_SIN_COMPROBANTES,
					TipoError.SIN_COMPROBANTES_EN_BUSCADOR, ""));
			return getRespuestaFactory().crearRespuestaError(datos,
					new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
							EstadisticasConstants.ESTADISTICA_BUSCADOR_COMPROBANTES,
							EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL),
					true);
		}
		datos.add(new DatoItemMensaje(CodigoMensajeConstantes.ERROR_PARCIAL_COMPROBANTES, TipoError.ALERTA_COMPROBANTES,
				""));
		datos.add(new DatoItemMensaje(CodigoMensajeConstantes.SIN_COMPROBANTES, TipoError.SIN_COMPROBANTES, ""));
		return getRespuestaFactory().crearRespuestaError(datos, new ComprobantesDTO(new ArrayList<ComprobanteDTO>(),
				codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL), true);

	}

	/**
	 * CASO_1 y CASO_5: Inicio y Buscador: Combinacion de CASO_1 y CASO_5.
	 * Advertencia y pantalla (Sin comprobantes)
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorSinComprobantesYLimiteDeFecha(
			List<ComprobanteDTO> comprobantesDTO, Boolean filtroActivo, String codigoEstadistica) {
		return crearRespuestaErrorLimiteDeFechas(comprobantesDTO, filtroActivo, codigoEstadistica);
	}

	/**
	 * CASO_3 y CASO_4: Inicio y Buscador: Advertencia y pantalla (Grilla de
	 * comprobantes).
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorParcialYLimiteDeComprobantes(
			List<ComprobanteDTO> comprobantesDTO, Boolean filtroActivo, String codigoEstadistica) {
		return crearRespuestaErrorLimiteEnCache(comprobantesDTO, filtroActivo, codigoEstadistica);
	}

	/**
	 * CASO_3 y CASO_5:Inicio y Buscador: Advertencia y pantalla (Grilla de
	 * comprobantes).
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorParcialYLimiteDeFechas(List<ComprobanteDTO> comprobantesDTO,
			Boolean filtroActivo, String codigoEstadistica) {
		return crearRespuestaErrorParcialConComprobantes(comprobantesDTO, filtroActivo, codigoEstadistica);
	}

	/**
	 * CASO_4 y CASO_5: Inicio y Buscador: Advertencia y pantalla (Grilla de
	 * comprobantes).
	 * 
	 * @param comprobantesDTO
	 *            the comprobantes DTO
	 * @param filtroActivo
	 *            the filtro activo
	 * @return respuesta
	 */
	private Respuesta<ComprobantesDTO> crearRespuestaErrorLimiteDeFechasYLimiteDeComprobantes(
			List<ComprobanteDTO> comprobantesDTO, Boolean filtroActivo, String codigoEstadistica) {
		return crearRespuestaErrorLimiteEnCache(comprobantesDTO, filtroActivo, codigoEstadistica);
	}

	@Override
	public TransaccionEnum solapaPorDefecto(Cliente cliente) {
		if (cliente.esMonoProductoTarjeta()) {
			LOGGER.info("Cliente con mono producto de tarjeta.");
			return TransaccionEnum.PAGO_DE_TARJETA;
		} else {
			TransaccionEnum transaccion = TransaccionEnum.getTransaccionByConfiguracion(solapaDefault);
			return transaccion != null ? transaccion : TransaccionEnum.PAGOS_DE_SERVICIOS;
		}
	}


}
