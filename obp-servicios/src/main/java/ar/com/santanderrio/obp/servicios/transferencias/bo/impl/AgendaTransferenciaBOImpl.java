/**
 *
 */
package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AgendaTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ComprobanteStopDebit;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaAgendaTransferencias;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaCancelTotal;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaStopDebit;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.BancoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DatosOrigenTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;

/**
 * The Class AgendaTransferenciaBOImpl.
 *
 * @author B039637
 */
@Component
public class AgendaTransferenciaBOImpl implements AgendaTransferenciaBO {

	/** The Constant TIPO_REC_E. */
	private static final String TIPO_REC_E = "E";

	/** The Constant TIPO_REC_I. */
	private static final Object TIPO_REC_I = "I";

	/** The Constant FRECUENCIA_REC_NO_REPITE. */
	private static final String FRECUENCIA_REC_NO_REPITE = "NR";

	/** The Constant N. */
	private static final String N = "N";

	/** The Constant TRANSF_BCO_RIO. */
	private static final String TRANSF_BCO_RIO = "TRANSF_BCO_RIO";

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTransferenciaBOImpl.class);

	/** The Constant ERROR_AGENDA_TRANSFERENCIA. */
	private static final String ERROR_AGENDA_TRANSFERENCIA = "Ha ocurrido un error al agendar la transferencia";

	/** The Constant ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS. */
	private static final String ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS = "ERROR STOP DEBIT AGENDA TRANSFERENCIAS";

	/** The Constant ERROR_OBTENER_DATOS_CLIENTE. */
	private static final String ERROR_OBTENER_DATOS_CLIENTE = "ERROR OBTENER DATOS CLIENTE";

	/** The Constant ERROR_ELIMINAR_TRANSFERENCIA. */
	private static final String ERROR_ELIMINAR_TRANSFERENCIA = "ERROR AL ELIMINAR TRANSFERENCIA";

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant FORMATO_FECHA_HORA. */
	private static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";

	/** The agenda transferencia DAO. */
	@Autowired
	private AgendaTransferenciaDAO agendaTransferenciaDAO;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The detalle cuenta DAO. */
	@Autowired
	private DetalleCuentaDAO detalleCuentaDAO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant REALIZO. */
	private static final String REALIZAR = "realizar";

	/** The Constant PROGRAMO. */
	private static final String PROGRAMAR = "programar";

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #obtenerTransferenciasAgendadasRioRio(ar.com.santanderrio.obp.servicios.
	 * clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<TransferenciaAgendadaDTO>> obtenerTransferenciasAgendadasRioRio(Cliente cliente) {
		Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO = new Respuesta<List<TransferenciaAgendadaDTO>>();
		List<TransferenciaAgendada> trasnferenciaAgendadaList;
		try {
			ConsultaAgendaTransferencias consultaAgendaTransferencias = armarRequestAgenda(cliente);
			trasnferenciaAgendadaList = agendaTransferenciaDAO
					.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, true);
			List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList = parsearTransferenciaAgendadaToDto(
					trasnferenciaAgendadaList, cliente);
			if (trasnferenciaAgendadaDTOList == null) {
				armarRespuestaListaNula(respuestaTransferenciaAgendadaDTO);
			} else if (trasnferenciaAgendadaDTOList.isEmpty()) {
				armarRespuestaListaVacia(respuestaTransferenciaAgendadaDTO);
			} else {
				armarRespuesta(respuestaTransferenciaAgendadaDTO, trasnferenciaAgendadaDTOList);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (ParseException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (ImporteConvertException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (BusinessException ex) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, ex);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		}
		return respuestaTransferenciaAgendadaDTO;
	}

	/**
	 * Armado de error en caso de excepciones.
	 *
	 * @param respuestaTransferenciaAgendadaDTO
	 *            the respuesta transferencia agendada DTO
	 */
	private void armarRespuestaError(Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_AGENDA_TRANSFERENCIAS);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(null);
		item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		item.setMensaje(mensaje.getMensaje());
		respuestaTransferenciaAgendadaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaTransferenciaAgendadaDTO.setRespuestaVacia(true);
		respuestaTransferenciaAgendadaDTO.setRespuesta(null);
		respuestaTransferenciaAgendadaDTO.add(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #obtenerTransferenciasAgendadasOtrosBancos(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<TransferenciaAgendadaDTO>> obtenerTransferenciasAgendadasOtrosBancos(Cliente cliente) {
		Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO = new Respuesta<List<TransferenciaAgendadaDTO>>();

		List<TransferenciaAgendada> trasnferenciaAgendadaList;
		try {
			ConsultaAgendaTransferencias consultaAgendaTransferencias = armarRequestAgenda(cliente);

			trasnferenciaAgendadaList = agendaTransferenciaDAO
					.obtenerTransferenciasAgendadas(consultaAgendaTransferencias, false);

			List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList = parsearTransferenciaAgendadaToDto(
					trasnferenciaAgendadaList, cliente);

			if (trasnferenciaAgendadaDTOList == null) {
				armarRespuestaListaNula(respuestaTransferenciaAgendadaDTO);
			} else if (trasnferenciaAgendadaDTOList.isEmpty()) {
				armarRespuestaListaVacia(respuestaTransferenciaAgendadaDTO);
			} else {
				armarRespuesta(respuestaTransferenciaAgendadaDTO, trasnferenciaAgendadaDTOList);
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (ParseException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (ImporteConvertException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		} catch (BusinessException ex) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, ex);
			armarRespuestaError(respuestaTransferenciaAgendadaDTO);
		}
		return respuestaTransferenciaAgendadaDTO;
	}

	/**
	 * Armar respuesta.
	 *
	 * @param respuestaTransferenciaAgendadaDTO
	 *            the respuesta transferencia agendada DTO
	 * @param trasnferenciaAgendadaDTOList
	 *            the trasnferencia agendada DTO list
	 */
	protected void armarRespuesta(Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO,
			List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList) {
		ordenarListaPorFecha(trasnferenciaAgendadaDTOList);
		respuestaTransferenciaAgendadaDTO.setRespuesta(trasnferenciaAgendadaDTOList);
		respuestaTransferenciaAgendadaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaAgendadaDTO.setRespuestaVacia(false);
	}

	/**
	 * Armar respuesta lista vacia.
	 *
	 * @param respuestaTransferenciaAgendadaDTO
	 *            the respuesta transferencia agendada DTO
	 */
	protected void armarRespuestaListaVacia(
			Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO) {
		Mensaje mensaje = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_SIN_TRANSFERENCIAS_AGENDADAS);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(null);
		item.setTipoError(TipoError.SIN_TRANSFERENCIAS_AGENDADAS.getDescripcion());
		item.setMensaje(mensaje.getMensaje());
		respuestaTransferenciaAgendadaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaTransferenciaAgendadaDTO.setRespuestaVacia(true);
		respuestaTransferenciaAgendadaDTO.setRespuesta(new ArrayList<TransferenciaAgendadaDTO>());
		respuestaTransferenciaAgendadaDTO.add(item);
	}

	/**
	 * Armar respuesta lista nula.
	 *
	 * @param respuestaTransferenciaAgendadaDTO
	 *            the respuesta transferencia agendada DTO
	 */
	protected void armarRespuestaListaNula(
			Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CANTIDAD_PARCIAL);
		respuestaTransferenciaAgendadaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		respuestaTransferenciaAgendadaDTO.setRespuestaVacia(true);
		respuestaTransferenciaAgendadaDTO.setRespuesta(null);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(null);
		item.setTipoError(TipoError.ERROR_PARCIAL_AGENDA_TRANSFERENCIAS.getDescripcion());
		item.setMensaje(mensaje.getMensaje());
		respuestaTransferenciaAgendadaDTO.add(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #ordenarListaPorFecha(java.util.List)
	 */
	@Override
	public void ordenarListaPorFecha(List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOList) {
		Collections.sort(trasnferenciaAgendadaDTOList, new Comparator<TransferenciaAgendadaDTO>() {
			@Override
			public int compare(TransferenciaAgendadaDTO trasnferenciaAgendadaDTO1,
					TransferenciaAgendadaDTO trasnferenciaAgendadaDTO2) {
				int value = trasnferenciaAgendadaDTO1.getFechaEjecucion()
						.compareTo(trasnferenciaAgendadaDTO2.getFechaEjecucion());
				if (value != 0) {
					return value;
				} else {
					// Orden: Transferencia Automática sin
					// recurrencia,(programada) Transferencia Automática con
					// recurrencia y Recordatorio
					return returnTipoTransferencia(trasnferenciaAgendadaDTO1, trasnferenciaAgendadaDTO2);
				}
			}

			/**
			 * @param trasnferenciaAgendadaDTO1
			 * @param trasnferenciaAgendadaDTO2
			 * @return
			 */
			protected int returnTipoTransferencia(TransferenciaAgendadaDTO trasnferenciaAgendadaDTO1,
					TransferenciaAgendadaDTO trasnferenciaAgendadaDTO2) {
				if (isTransferenciaAutomaticaProgramada(trasnferenciaAgendadaDTO1)) {
					return -1;
				} else if (isTransferenciaAutomaticaProgramada(trasnferenciaAgendadaDTO2)) {
					return 1;
				}

				if (isTransferenciaAutomaticaRecurrente(trasnferenciaAgendadaDTO1)) {
					return -1;
				} else if (isTransferenciaAutomaticaRecurrente(trasnferenciaAgendadaDTO2)) {
					return 1;
				}

				if (isTransferenciaRecordatorio(trasnferenciaAgendadaDTO1)) {
					return -1;
				} else if (isTransferenciaRecordatorio(trasnferenciaAgendadaDTO2)) {
					return 1;
				}
				return 0;
			};
		});

	};

	/**
	 * Checks if is transferencia automatica programada.
	 *
	 * @param trasnferenciaAgendadaDTO
	 *            the trasnferencia agendada DTO
	 * @return true, if is transferencia automatica programada
	 */
	private boolean isTransferenciaAutomaticaProgramada(TransferenciaAgendadaDTO trasnferenciaAgendadaDTO) {
		if (trasnferenciaAgendadaDTO.getTipoTransferenciaAgendada().equals(TipoTransferenciaAgendada.PROGRAMADA)) {
			return true;
		} else {
			return false;
		}
	};

	/**
	 * Checks if is transferencia automatica recurrente.
	 *
	 * @param trasnferenciaAgendadaDTO
	 *            the trasnferencia agendada DTO
	 * @return true, if is transferencia automatica recurrente
	 */
	private boolean isTransferenciaAutomaticaRecurrente(TransferenciaAgendadaDTO trasnferenciaAgendadaDTO) {
		if (trasnferenciaAgendadaDTO.getTipoTransferenciaAgendada().equals(TipoTransferenciaAgendada.RECURRENTE)) {
			return true;
		} else {
			return false;
		}
	};

	/**
	 * Checks if is transferencia recordatorio.
	 *
	 * @param trasnferenciaAgendadaDTO
	 *            the trasnferencia agendada DTO
	 * @return true, if is transferencia recordatorio
	 */
	private boolean isTransferenciaRecordatorio(TransferenciaAgendadaDTO trasnferenciaAgendadaDTO) {
		if (trasnferenciaAgendadaDTO.getTipoTransferenciaAgendada().equals(TipoTransferenciaAgendada.RECORDATORIO)) {
			return true;
		} else {
			return false;
		}
	};

	/**
	 * Armar request agenda.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the consulta agenda transferencias
	 */
	private ConsultaAgendaTransferencias armarRequestAgenda(Cliente cliente) {
		ConsultaAgendaTransferencias consultaAgendaTransferencias = new ConsultaAgendaTransferencias();
		consultaAgendaTransferencias.setCliente(cliente);
		setFechaDesdeHasta(consultaAgendaTransferencias);
		return consultaAgendaTransferencias;
	}

	/**
	 * Sets the fecha desde hasta.
	 *
	 * @param consultaAgendaTransferencias
	 *            the new fecha desde hasta
	 */
	private void setFechaDesdeHasta(ConsultaAgendaTransferencias consultaAgendaTransferencias) {
		Date fechaHoy = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int anioParaSumar = 1;
		calendar.add(Calendar.YEAR, anioParaSumar);
		Date fechaHoyMasUnAnio = calendar.getTime();
		consultaAgendaTransferencias.setFechaDesde(fechaHoy);
		consultaAgendaTransferencias.setFechaHasta(fechaHoyMasUnAnio);
	}

	/**
	 * Parsear transferencia agendada to dto.
	 *
	 * @param transferenciaAgendadaList
	 *            the trasnferencia agendada list
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws ParseException
	 *             the parse exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws BusinessException
	 *             the business exception
	 */
	public List<TransferenciaAgendadaDTO> parsearTransferenciaAgendadaToDto(
			List<TransferenciaAgendada> transferenciaAgendadaList, Cliente cliente)
			throws ParseException, ImporteConvertException, BusinessException {
		List<TransferenciaAgendadaDTO> transferenciaAgendadaDTOList = new ArrayList<TransferenciaAgendadaDTO>();
		for (TransferenciaAgendada transferenciaAgendada : transferenciaAgendadaList) {
			TransferenciaAgendadaDTO dto = mapearTransferenciaAgendada(transferenciaAgendada, cliente);
			if (dto != null) {
				transferenciaAgendadaDTOList.add(dto);
			}
		}

		return transferenciaAgendadaDTOList;
	}

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 * @param nroSucursal
	 *            the nro sucursal
	 * @return the identificacion cuenta
	 */
	private IdentificacionCuenta getIdentificacionCuenta(String nroCuentaProducto, String nroSucursal) {
		IdentificacionCuenta id = new IdentificacionCuenta();
		id.setNroCuentaProducto(nroCuentaProducto);
		id.setNroSucursal(nroSucursal);
		return id;
	}

	/**
	 * Mapear transferencia agendada.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param cliente
	 *            the cliente
	 * @return the transferencia agendada DTO
	 * @throws ParseException
	 *             the parse exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaAgendadaDTO mapearTransferenciaAgendada(TransferenciaAgendada transferenciaAgendada,
			Cliente cliente) throws ParseException, ImporteConvertException, BusinessException {

		AbstractCuenta cuentaOrigen = cuentaBO.getCuentaById(getIdentificacionCuenta(
				transferenciaAgendada.getNroCtaDebito(), transferenciaAgendada.getSucCtaDebito()), cliente);
		AbstractCuenta cuentaDestino = cuentaBO.getCuentaById(getIdentificacionCuenta(
				transferenciaAgendada.getNroCtaCredito(), transferenciaAgendada.getSucCtaCredito()), cliente);

		TransferenciaAgendadaDTO dto = null;

		if (transferenciaAgendada.getTipoAgendamiento() == null) {
			return dto;
		}

		dto = new TransferenciaAgendadaDTO();
		if ("I".equals(transferenciaAgendada.getTipoAgendamiento())) {
			dto.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
			dto.setFrecuencia(obtenerRecurrencia(transferenciaAgendada));
		} else if ("E".equals(transferenciaAgendada.getTipoAgendamiento())) {
			if ("I".equals(transferenciaAgendada.getTipoRecurrencia())) {
				dto.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
			} else {
				dto.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
				dto.setFrecuencia(obtenerRecurrencia(transferenciaAgendada));
			}
		}

		if (dto != null) {
			dto.setId(transferenciaAgendada.getId());
			dto.setIdDef(transferenciaAgendada.getIdDef());
			dto.setIdEvento(transferenciaAgendada.getIdEvento());
			dto.setIdDatosRec(transferenciaAgendada.getIdDatosRec());

			dto.setFechaEjecucion(limpiarHoras(parsearFecha(transferenciaAgendada.getFechaEjecucion())));

			if (transferenciaAgendada.isRioRio()) {
				metodoParcial(transferenciaAgendada, dto);
			} else {
				metodoParcialOtrosBancos(transferenciaAgendada, dto);
			}

			if (cuentaOrigen != null) {
				dto.setCuentaAliasOrigen(cuentaOrigen.getAlias());
			}
			if (cuentaDestino != null) {
				dto.setCuentaAliasDestino(cuentaDestino.getAlias());
			}

			dto.setDatosOrigen(mapearDatosOrigen(transferenciaAgendada));
		}
		dto.setId(transferenciaAgendada.getId());
		return dto;
	}

	/**
	 * Metodo parcial.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param dto
	 *            the dto
	 * @throws BusinessException
	 *             the business exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 */
	private void metodoParcial(TransferenciaAgendada transferenciaAgendada, TransferenciaAgendadaDTO dto)
			throws BusinessException, ImporteConvertException {

		String concepto = transferenciaAgendada.getConcepto();
		if (concepto != null) {
			ConceptoTransferenciaEnum conceptoTransferenciaEnum = ConceptoTransferenciaEnum
					.getConceptoFromCodigo(concepto);
			if (conceptoTransferenciaEnum == null) {
				conceptoTransferenciaEnum = ConceptoTransferenciaEnum.getConceptoFromDescripcion(concepto);
			}
			dto.setConcepto(conceptoTransferenciaEnum);
			dto.setDescripcionConcepto(transferenciaAgendada.getDescripcionConcepto());
		} else {
			dto.setConcepto(ConceptoTransferenciaEnum.VARIOS);
		}

		if (transferenciaAgendada.getSucCtaCredito() != null && transferenciaAgendada.getNroCtaCredito() != null) {
			dto.setCuentaDestino(ISBANStringUtils.formatearSucursal(transferenciaAgendada.getSucCtaCredito())
					+ SEPARATOR + ISBANStringUtils.formatearNumeroCuenta(transferenciaAgendada.getNroCtaCredito()));
		}
		if (transferenciaAgendada.getSucCtaDebito() != null && transferenciaAgendada.getNroCtaDebito() != null) {
			dto.setNroCuentaOrigen(ISBANStringUtils.formatearSucursal(transferenciaAgendada.getSucCtaDebito())
					+ SEPARATOR + ISBANStringUtils.formatearNumeroCuenta(transferenciaAgendada.getNroCtaDebito()));
		}
		dto.setMoneda(getDivisa(obtenerTipoCuentaEnum(transferenciaAgendada.getTipoCtaDebito())));

		TipoCuenta tipoCuentaOrigen = obtenerTipoCuentaEnum(transferenciaAgendada.getTipoCtaDebito());
		dto.setCuentaOrigenTipo(fixTipoCuenta(tipoCuentaOrigen));
		dto.setCuentaOrigenTipoSinUnificar(tipoCuentaOrigen);
		dto.setDestinatario(obtenerDestinatario(transferenciaAgendada));
		TipoCuenta tipoCuentaDestino = obtenerTipoCuentaEnum(transferenciaAgendada.getTipoCtaCredito());
		dto.setCuentaDestinoTipo(fixTipoCuenta(tipoCuentaDestino));
		dto.setCuentaDestinoTipo(tipoCuentaDestino);
		dto.setImporte(obtenerImporteDebito(transferenciaAgendada));
		dto.setHaciaOtroBanco(!transferenciaAgendada.isRioRio());
		if ("N".equals(transferenciaAgendada.getIndTransfDiferida())) {
			dto.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
		} else {
			dto.setPlazoAcreditacion(PlazoAcreditacion.PLAZO_48_HS);
		}
		dto.setEmail(transferenciaAgendada.getMailCredito());
		dto.setEmailMensaje(transferenciaAgendada.getComentario());

	}

	/**
	 * Metodo parcial otros bancos.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @param dto
	 *            the dto
	 * @throws BusinessException
	 *             the business exception
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @versio 2. Manuel Vargas. Deja de usarse la estructura
	 *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
	 */
	private void metodoParcialOtrosBancos(TransferenciaAgendada transferenciaAgendada, TransferenciaAgendadaDTO dto)
			throws BusinessException, ImporteConvertException {

		if (transferenciaAgendada.getSucCtaDebito() != null && transferenciaAgendada.getNroCtaDebito() != null) {
			dto.setNroCuentaOrigen(ISBANStringUtils.formatearSucursal(transferenciaAgendada.getSucCtaDebito())
					+ SEPARATOR + ISBANStringUtils.formatearNumeroCuenta(transferenciaAgendada.getNroCtaDebito()));
		}

		dto.setMoneda(getDivisa(obtenerTipoCuentaEnum(transferenciaAgendada.getTipoCtaDebito())));

		TipoCuenta tipoCuentaOrigen = obtenerTipoCuentaEnum(transferenciaAgendada.getTipoCtaDebito());
		dto.setCuentaOrigenTipo(fixTipoCuenta(tipoCuentaOrigen));
		dto.setCuentaOrigenTipoSinUnificar(tipoCuentaOrigen);

		String concepto = transferenciaAgendada.getConcepto();
		if (StringUtils.isNotEmpty(concepto)) {
			ConceptoTransferenciaEnum conceptoTransferenciaEnum = ConceptoTransferenciaEnum
					.getConceptoFromCodigo(concepto);
			if (conceptoTransferenciaEnum == null) {
				conceptoTransferenciaEnum = ConceptoTransferenciaEnum.getConceptoFromDescripcion(concepto);
			}
			dto.setConcepto(conceptoTransferenciaEnum);
			dto.setDescripcionConcepto(transferenciaAgendada.getDescConcepto());
		} else {
			dto.setConcepto(ConceptoTransferenciaEnum.VARIOS);
		}

		dto.setEmail(transferenciaAgendada.getMailCredito());
		dto.setEmailMensaje(transferenciaAgendada.getComentario());
		dto.setDestinatario(obtenerDestinatarioOtrosBancos(transferenciaAgendada));

		dto.setImporte(obtenerImporteTransferencia(transferenciaAgendada));
		dto.setCuitCuil(transferenciaAgendada.getIdentificBeneficiario());
		Integer plazoInmediato = Integer.valueOf(1);
		if (plazoInmediato.equals(Integer.parseInt(transferenciaAgendada.getPlazoAcreditacion()))) {
			dto.setPlazoAcreditacion(PlazoAcreditacion.PLAZO_48_HS);
		} else {
			dto.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
		}

		dto.setHaciaOtroBanco(!transferenciaAgendada.isRioRio());

		dto.setCbuCuenta(transferenciaAgendada.getCbu());
	}

	/**
	 * Mapear datos origen.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the datos origen transferencia agendada DTO
	 */
	private DatosOrigenTransferenciaAgendadaDTO mapearDatosOrigen(TransferenciaAgendada transferenciaAgendada) {
		DatosOrigenTransferenciaAgendadaDTO datosOrigen = new DatosOrigenTransferenciaAgendadaDTO();
		datosOrigen.setTransferenciaAgendada(transferenciaAgendada);
		return datosOrigen;
	}

	/**
	 * Fix tipo cuenta.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the tipo cuenta
	 */
	private TipoCuenta fixTipoCuenta(TipoCuenta tipo) {
		return (TipoCuenta.CUENTA_UNICA_PESOS.equals(tipo) || TipoCuenta.CUENTA_UNICA_DOLARES.equals(tipo))
				? TipoCuenta.CUENTA_UNICA : tipo;
	}

	/**
	 * Gets the divisa.
	 *
	 * @param obtenerTipoCuentaEnum
	 *            the obtener tipo cuenta enum
	 * @return the divisa
	 * @throws BusinessException
	 *             the business exception
	 */
	private DivisaEnum getDivisa(TipoCuenta obtenerTipoCuentaEnum) throws BusinessException {
		if (TipoCuenta.CAJA_AHORRO_PESOS.equals(obtenerTipoCuentaEnum)
				|| TipoCuenta.CUENTA_UNICA_PESOS.equals(obtenerTipoCuentaEnum)
				|| TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(obtenerTipoCuentaEnum)) {
			return DivisaEnum.PESO;
		}
		if (TipoCuenta.CAJA_AHORRO_DOLARES.equals(obtenerTipoCuentaEnum)
				|| TipoCuenta.CUENTA_CORRIENTE_DOLARES.equals(obtenerTipoCuentaEnum)
				|| TipoCuenta.CUENTA_UNICA_DOLARES.equals(obtenerTipoCuentaEnum)) {
			return DivisaEnum.DOLAR;
		}
		throw new BusinessException("Tipo cuenta invalida");
	}

	/**
	 * Limpiar horas.
	 *
	 * @param parsearFecha
	 *            the parsear fecha
	 * @return the date
	 */
	public Date limpiarHoras(Date parsearFecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parsearFecha);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Obtener importe debito.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @versio 2. Manuel Vargas. Deja de usarse la estructura
	 *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
	 */
	private BigDecimal obtenerImporteDebito(TransferenciaAgendada transferenciaAgendada)
			throws ImporteConvertException {
		// PedidoTransferenciaAgendada pedido =
		// transferenciaAgendada.getPedidoTransferenciaAgendada();
		// DatosTransferenciaAgendadaRioRioEntity
		// datosTransferenciaAgendadaRioRio =
		// (DatosTransferenciaAgendadaRioRioEntity) pedido
		// .getDatosTransferenciaAgendada();
		// return
		// ISBANStringUtils.convertirImporte(datosTransferenciaAgendadaRioRio.getImporteDebito());
		return ISBANStringUtils.convertirImporte(transferenciaAgendada.getImporteDebito());
	}

	/**
	 * Obtener importe transferencia.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the big decimal
	 * @throws ImporteConvertException
	 *             the importe convert exception
	 * @versio 2. Manuel Vargas. Deja de usarse la estructura
	 *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
	 */
	private BigDecimal obtenerImporteTransferencia(TransferenciaAgendada transferenciaAgendada)
			throws ImporteConvertException {
		// PedidoTransferenciaAgendada pedido =
		// transferenciaAgendada.getPedidoTransferenciaAgendada();
		// DatosTransferenciaAgendadaOtrosBancosEntity
		// datosTransferenciaAgendadaOtrosBancos =
		// (DatosTransferenciaAgendadaOtrosBancosEntity) pedido
		// .getDatosTransferenciaAgendada();
		return ISBANStringUtils.convertirImporte(transferenciaAgendada.getImporteTransferencia());
	}

	/**
	 * Obtener destinatario.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the destinatario DTO
	 * @versio 2. Manuel Vargas. Deja de usarse la estructura
	 *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
	 */
	private DestinatarioDTO obtenerDestinatario(TransferenciaAgendada transferenciaAgendada) {
		DestinatarioDTO dto = new DestinatarioDTO();
		if (!"S".equals(transferenciaAgendada.getCuentaPropia())) {
			dto.setDescripcion(transferenciaAgendada.getNombreCtaCredito());
			dto.setNombre(transferenciaAgendada.getTitularCredito());
		} else {
			dto.setNombre(transferenciaAgendada.getTitularDebito());
		}
		String detalleBanco;
		if (TRANSF_BCO_RIO.equals(transferenciaAgendada.getNombre())
				&& N.equals(transferenciaAgendada.getCuentaPropia())) {
			detalleBanco = "Terceros Santander";
		} else {
			detalleBanco = "Cuenta Propia";
		}
		BancoDTO bancoDTO = new BancoDTO();
		bancoDTO.setNombre(BancoEnum.SANTANDER_RIO.getDescripcion());
		bancoDTO.setDetalle(detalleBanco);
		dto.setBanco(bancoDTO);
		return dto;
	}

	/**
	 * Obtener destinatario otros bancos.
	 *
	 * @param transferenciaAgendada
	 *            the transferencia agendada
	 * @return the destinatario DTO
	 * @versio 2. Manuel Vargas. Deja de usarse la estructura
	 *         PedidoTransferenciaAgendada y DatosTransferenciaAgendada
	 */
	private DestinatarioDTO obtenerDestinatarioOtrosBancos(TransferenciaAgendada transferenciaAgendada) {
		DestinatarioDTO dto = new DestinatarioDTO();

		// PedidoTransferenciaAgendada pedido =
		// transferenciaAgendada.getPedidoTransferenciaAgendada();
		// DatosTransferenciaAgendadaOtrosBancosEntity transferenciaAgendada =
		// (DatosTransferenciaAgendadaOtrosBancosEntity) pedido
		// .getDatosTransferenciaAgendada();

		dto.setDescripcion(transferenciaAgendada.getNombreCtaCredito());
		dto.setNombre(transferenciaAgendada.getTitular());
		BancoDTO bancoDTO = new BancoDTO();
		bancoDTO.setNombre(transferenciaAgendada.getBancoDestino());
		bancoDTO.setDetalle(transferenciaAgendada.getBancoDestino());
		dto.setBanco(bancoDTO);

		return dto;
	}

	/**
	 * Parsear fecha.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the date
	 * @throws ParseException
	 *             the parse exception
	 */
	public Date parsearFecha(String fecha) throws ParseException {
		if (fecha == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(fecha);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #obtenerRecurrencia(ar.com.santanderrio.obp.servicios.transferencias.
	 * entities.TransferenciaAgendada)
	 */
	@Override
	public FrecuenciaTransferenciaAgendada obtenerRecurrencia(TransferenciaAgendada transferenciaAgendada) {
		FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendada = null;
		String frecuenciaRec = transferenciaAgendada.getFrecuenciaRec();

		if (StringUtils.isNotBlank(frecuenciaRec) && !FRECUENCIA_REC_NO_REPITE.equals(frecuenciaRec)) {
			if (TIPO_REC_E.equals(transferenciaAgendada.getTipoRec())) {
				if ("S".equals(frecuenciaRec)) {
					frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.FRECUENCIA_E;
				}

			}
			if (TIPO_REC_I.equals(transferenciaAgendada.getTipoRec())) {
				if ("S".equals(frecuenciaRec)) {
					frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.FRECUENCIA_I;
				}
			}
			if (frecuenciaRec.startsWith("S1")) {
				frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.MISMO_DIA_TODAS_SEMANAS;
			}
			if (frecuenciaRec.startsWith("S2")) {
				frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.MISMO_DIA_2_SEMANAS;
			}
			if (frecuenciaRec.startsWith("M1")) {
				frecuenciaTransferenciaAgendada = obtenerFrecuenciaMeses(frecuenciaRec);
			}
			if (frecuenciaRec.startsWith("A1")) {
				frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.CADA_ANIO;
			}
		}
		return frecuenciaTransferenciaAgendada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #stopDebit(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * StopDebitTransferenciaAgendadaDTO)
	 */
	public Respuesta<FeedbackStopDebitView> stopDebit(Cliente cliente,
			TransferenciaAgendadaDTO transferenciaAgendadaDTO) {

		Respuesta<FeedbackStopDebitView> respuesta = respuestaFactory.crearRespuestaOk(FeedbackStopDebitView.class);

		ConsultaStopDebit consultaStopDebit = new ConsultaStopDebit();
		consultaStopDebit.setCliente(cliente);
		consultaStopDebit.setIdDatosRec(transferenciaAgendadaDTO.getIdDatosRec());
		consultaStopDebit.setIdDef(transferenciaAgendadaDTO.getIdDef());
		consultaStopDebit.setIdEvento(transferenciaAgendadaDTO.getIdEvento());

		try {
			ComprobanteStopDebit comprobanteStopDebit = agendaTransferenciaDAO.stopDebit(transferenciaAgendadaDTO,
					cliente);
			FeedbackStopDebitView feedbackStopDebitView = mapearComprobanteStopDebitDTO(comprobanteStopDebit,
					transferenciaAgendadaDTO.getDestinatario());
			respuesta.setRespuesta(feedbackStopDebitView);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS, e);
			Mensaje mensajeError = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS);
			String mensajeErrorFormateado = MessageFormat.format(mensajeError.getMensaje(),
					transferenciaAgendadaDTO.getDestinatario().getNombre());
			respuesta = new Respuesta<FeedbackStopDebitView>();
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta
					.setTipoError(TipoError.ERROR_STOP_DEBIT_AGENDA_TRANSFERENCIAS_CON_REINTENTO.getDescripcion());
			itemMensajeRespuesta.setMensaje(mensajeErrorFormateado);
			respuesta.add(itemMensajeRespuesta);
			return respuesta;
		}

	}

	/**
	 * Mapear comprobante stop debit DTO.
	 *
	 * @param resp
	 *            the resp
	 * @param destinatario
	 *            the destinatario
	 * @return the feedback stop debit view
	 */
	private FeedbackStopDebitView mapearComprobanteStopDebitDTO(ComprobanteStopDebit resp,
			DestinatarioDTO destinatario) {
		// Mensaje mensajeLegales =
		// mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMPROBANTE_STOP_DEBIT_MENSAJE_LEGALES);
		FeedbackStopDebitView feedbackStopDebitView = new FeedbackStopDebitView();
		feedbackStopDebitView.setMensaje(obtenerMensajeTransferenciaOkStopDebit(destinatario));
		feedbackStopDebitView.setNumeroComprobante(resp.getIdRec());
		// feedbackStopDebitView.setTextoLegales(mensajeLegales.getMensaje());
		feedbackStopDebitView.setFechaComprobante(ISBANStringUtils.formatearFecha(new Date(), FORMATO_FECHA_HORA));
		return feedbackStopDebitView;
	}

	/**
	 * Obtener mensaje transferencia ok stop debit.
	 *
	 * @param destinatario
	 *            the destinatario
	 * @return the string
	 */
	private String obtenerMensajeTransferenciaOkStopDebit(DestinatarioDTO destinatario) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_STOP_DEBIT_OK);
		String titular = WordUtils.capitalizeFully(destinatario.getNombre());
		String mensajeStr = mensaje.getMensaje();
		return MessageFormat.format(mensajeStr, titular);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #eliminar(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * EliminarTransferenciaAgendadaDTO)
	 */
	@Override
	public Respuesta<FeedbackEliminarView> eliminarTransferencia(Cliente cliente,
			TransferenciaAgendadaDTO transferenciaAgendadaDTO) {

		Respuesta<FeedbackEliminarView> respuesta = respuestaFactory.crearRespuestaOk(FeedbackEliminarView.class);

		ConsultaCancelTotal consultaCancelTotal = new ConsultaCancelTotal();
		consultaCancelTotal.setCliente(cliente);
		consultaCancelTotal.setIdDatosRec(transferenciaAgendadaDTO.getIdDatosRec());
		consultaCancelTotal.setIdDef(transferenciaAgendadaDTO.getIdDef());
		consultaCancelTotal.setIdEvento(transferenciaAgendadaDTO.getIdEvento());
		consultaCancelTotal.setDatosOrigen(transferenciaAgendadaDTO.getDatosOrigen());
		try {

			agendaTransferenciaDAO.cancelTotal(consultaCancelTotal, cliente);
			Mensaje mensaje = null;
			if (isTransferenciaRecordatorio(transferenciaAgendadaDTO)) {
				mensaje = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ELIMINAR_TRANSFERENCIA_RECORDATORIO_OK);
			} else {
				mensaje = mensajeBO
						.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ELIMINAR_TRANSFERENCIA_AUTOMATICA_OK);
			}

			FeedbackEliminarView feedbackEliminarView = new FeedbackEliminarView();
			feedbackEliminarView.setMensaje(mensaje.getMensaje());
			respuesta.setRespuesta(feedbackEliminarView);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(ERROR_ELIMINAR_TRANSFERENCIA, e);
			return respuestaFactory.crearRespuestaError(FeedbackEliminarView.class, null,
					TipoError.ERROR_ELIMINAR_TRANSFERENCIA_CON_REINTENTO,
					CodigoMensajeConstantes.CODIGO_ERROR_ELIMINAR_TRANSFERENCIA);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #obtenerDatosCliente(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaAgendadaDTO)
	 */
	@Override
	public DatosCliente obtenerDatosCliente(Cliente cliente, TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		DatosCliente datosCliente = new DatosCliente();
		try {
			IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(
					transferenciaAgendadaDTO.getNroCuentaOrigen());
			datosCliente = detalleCuentaDAO.obtenerDatosCliente(cliente,
					transferenciaAgendadaDTO.getCuentaOrigenTipoSinUnificar(), identificacionCuenta.getNroSucursal(),
					identificacionCuenta.getNroCuentaProducto());
			formatearCUITCUIL(datosCliente);
		} catch (DAOException e) {
			LOGGER.error(ERROR_OBTENER_DATOS_CLIENTE, e);
			datosCliente.setNumeroCUILCUIT(SEPARATOR);
			datosCliente.setNumeroCUILCUIT(SEPARATOR);
		}
		return datosCliente;
	}

	/**
	 * Formatear CUITCUIL.
	 *
	 * @param datosCliente
	 *            the datos cliente
	 */
	private void formatearCUITCUIL(DatosCliente datosCliente) {
		if (datosCliente != null && datosCliente.getNumeroCUILCUIT().trim() != null
				&& datosCliente.getNumeroCUILCUIT().trim() != "") {
			datosCliente.setNumeroCUILCUIT(ISBANStringUtils.formatearCuil(datosCliente.getNumeroCUILCUIT()));

		}
	}

	/**
	 * Obtener frecuencia meses.
	 *
	 * @param frecuenciaRec
	 *            the frecuencia rec
	 * @return the frecuencia transferencia agendada
	 */
	private FrecuenciaTransferenciaAgendada obtenerFrecuenciaMeses(String frecuenciaRec) {
		FrecuenciaTransferenciaAgendada frecuenciaTransferenciaAgendada = null;
		if (frecuenciaRec.endsWith("01")) {
			frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.TODOS_MESES_MISMO_DIA;
		}
		if (frecuenciaRec.endsWith("02")) {
			frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.CADA_2_MESES;
		}
		if (frecuenciaRec.endsWith("03")) {
			frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.CADA_3_MESES;
		}
		if (frecuenciaRec.endsWith("06")) {
			frecuenciaTransferenciaAgendada = FrecuenciaTransferenciaAgendada.CADA_6_MESES;
		}
		return frecuenciaTransferenciaAgendada;
	}

	/**
	 * Obtener tipo cuenta enum.
	 *
	 * @param tipoCuentaString
	 *            the tipo cuenta string
	 * @return the tipo cuenta
	 */
	private TipoCuenta obtenerTipoCuentaEnum(String tipoCuentaString) {
		if (tipoCuentaString == null || tipoCuentaString.trim().isEmpty()) {
			return null;
		}
		return TipoCuenta.fromCodigo(tipoCuentaString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #ejecutarAgendaTransferencia(ar.com.santanderrio.obp.servicios.cuentas.
	 * web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaEjecutadaDTO> ejecutarTransferenciaAgendada(
			TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente) {
		LOGGER.info("Agendando una transferencia...");
		Respuesta<TransferenciaEjecutadaDTO> respuestaEjecucionTransferenciaDTO;
		TransferenciaEjecutadaDTO transferenciaEjecutadaDTO;
		try {
			transferenciaEjecutadaDTO = agendaTransferenciaDAO.ejecutarTransferenciaAgendada(transferenciaAgendadaDTO,
					cliente);
			if (transferenciaEjecutadaDTO != null) {
				respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaOk(transferenciaEjecutadaDTO);
				respuestaEjecucionTransferenciaDTO.setRespuesta(transferenciaEjecutadaDTO);
			} else {
				throw new DAOException(TipoError.ERROR_GENERICO.getDescripcion());
			}
		} catch (DAOException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaEjecutadaDTO>();
			respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
			ItemMensajeRespuesta item = getMensajePorCodigo(e.getMessage(), transferenciaAgendadaDTO);
			respuestaEjecucionTransferenciaDTO.add(item);
		} catch (ImporteConvertException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaEjecutadaDTO>();
			respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
			respuestaEjecucionTransferenciaDTO.add(getMensajePorCodigo(e.getMessage(), transferenciaAgendadaDTO));
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_AGENDA_TRANSFERENCIA, e);
			respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaEjecutadaDTO>();
			respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
			ItemMensajeRespuesta item = getMensajePorCodigo(TipoError.ERROR_INTERNO_SIN_REINTENTO.getDescripcion(),
					transferenciaAgendadaDTO);
			respuestaEjecucionTransferenciaDTO.add(item);
		}
		return respuestaEjecucionTransferenciaDTO;
	}

	/**
	 * Gets the mensaje por codigo.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @return the mensaje por codigo
	 */
	private ItemMensajeRespuesta getMensajePorCodigo(String tipoError,
			TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		String monedaConImporte = new String();
		BigDecimal importeDto = transferenciaAgendadaDTO.getImporte();
		String importe = importeDto.toString();
		monedaConImporte = transferenciaAgendadaDTO.getMoneda().getSimbolo()
				+ ISBANStringUtils.formatearConComaYDosDecimales(importe);
		Mensaje mensaje = null;

		if (tipoError != null && tipoError.equals(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion())) {
			itemMensajeRespuesta.setTipoError(tipoError);
			mensaje = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_SALDO_INSUFICIENTE);
			itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		} else if (tipoError != null && tipoError.equals(TipoError.ERROR_CUENTA_180_DIAS_SIN_OPERAR.getDescripcion())) {
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_PARCIAL_TRANSFERENCIA.getDescripcion());
			mensaje = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CUENTA_ORIGEN_MAS_180_DIAS_SIN_OPERAR);
			itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		} else if (tipoError != null && tipoError.equals(TipoError.ERROR_BANELCO_TIMEOUT.getDescripcion())) {
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_PARCIAL_TRANSFERENCIA.getDescripcion());
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_BANELCO_TIMEOUT);
			itemMensajeRespuesta.setMensaje(mensaje.getMensaje());
		} else if (tipoError != null && tipoError.equals(TipoError.ERROR_INTERNO_SIN_REINTENTO.getDescripcion())) {
			// TODO: sacar?
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO);
			String mensajeFormateado = MessageFormat.format(mensaje.getMensaje(),
					isProgramada(
							new SimpleDateFormat(FORMATO_FECHA).format(transferenciaAgendadaDTO.getFechaEjecucion())),
					transferenciaAgendadaDTO.getDestinatario().getNombre(), monedaConImporte);
			itemMensajeRespuesta.setMensaje(mensajeFormateado);
		} else {
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_TRANSFERENCIA_ERROR_GENERICO);

			String mensajeFormateado = MessageFormat.format(mensaje.getMensaje(),
					isProgramada(
							new SimpleDateFormat(FORMATO_FECHA).format(transferenciaAgendadaDTO.getFechaEjecucion())),
					transferenciaAgendadaDTO.getDestinatario().getNombre(), monedaConImporte);
			itemMensajeRespuesta.setMensaje(mensajeFormateado);
		}
		return itemMensajeRespuesta;
	}

	/**
	 * Checks if is programada y devuelve la palabra indicada para el mensaje.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String isProgramada(String fecha) {
		Date fechaActual = Calendar.getInstance().getTime();
		Date fechaTransaccion;
		try {
			fechaTransaccion = new SimpleDateFormat(FORMATO_FECHA).parse(fecha);
			if (fechaActual.before(fechaTransaccion)) {
				return PROGRAMAR;
			} else {
				if (fechaTransaccion.before(fechaActual)) {
					return REALIZAR;
				} else {
					return REALIZAR;
				}
			}
		} catch (ParseException e) {
			LOGGER.error("Ocurrio un error al parsear la fecha", e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #ejecutarModificacionDeTransferenciaAgendada(ar.com.santanderrio.obp.
	 * servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO,
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDTO> ejecutarModificacionDeTransferenciaAgendada(
			TransferenciaAgendadaDTO transferenciaDTO, String tipoAgendamiento, Cliente cliente) {
		Respuesta<TransferenciaAgendadaDTO> respuestaEjecucionTransferenciaDTO;
		String mensajeFormateado = null;
		try {
			TransferenciaAgendadaDTO transferencia = agendaTransferenciaDAO
					.ejecutarModificacionDeTransferenciaAgendada(transferenciaDTO, tipoAgendamiento, cliente);
			respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaOk(transferencia);
			mensajeFormateado = MessageFormat.format(
					this.mensajeBO.obtenerMensajePorCodigo(
							CodigoMensajeConstantes.CODIGO_MODIFICACION_TRANSFERENCIA_AGENDADA_OK).getMensaje(),
					transferenciaDTO.getDatosOrigen().getTransferenciaAgendada().getTitularCredito());
			transferencia.setMensaje(mensajeFormateado);
			respuestaEjecucionTransferenciaDTO.setRespuesta(transferencia);
		} catch (DAOException e) {
			LOGGER.error("ERROR_AGENDA_TRANSFERENCIA", e);
			respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaAgendadaDTO>();
			respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
			mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MODIFICACION_TRANSFERENCIA_AGENDADA_ERROR)
					.getMensaje(), transferenciaDTO.getDestinatario().getNombre());
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeFormateado);
			respuestaEjecucionTransferenciaDTO.add(itemMensajeRespuesta);
		}
		return respuestaEjecucionTransferenciaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #ejecutarAgendamientoAutomaticoTransferencia(ar.com.santanderrio.obp.
	 * servicios.transferencias.entities.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TransferenciaDTO> ejecutarAgendamientoAutomaticoTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente) {
		Respuesta<TransferenciaDTO> respuestaEjecucionTransferenciaDTO;
		String mensajeFormateado = null;
		try {
			cargarDestinatarioEnTransferenciaDTO(transferenciaDTO);
			TransferenciaDTO transferencia = agendaTransferenciaDAO
					.ejecutarAgendamientoAutomaticoTransferencia(transferenciaDTO, cliente);
			respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaOk(transferencia);
			mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AGENDAMIENTO_TRANSFERENCIA_AUTOMATICO_OK)
					.getMensaje(), transferenciaDTO.getNombreReceptor());
			transferencia.setMensaje(mensajeFormateado);
			respuestaEjecucionTransferenciaDTO.setRespuesta(transferencia);
		} catch (DAOException e) {
			LOGGER.error("ERROR_AGENDA_TRANSFERENCIA", e);
			mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_AGENDAMIENTO_TRANSFERENCIA)
					.getMensaje(), transferenciaDTO.getNombreReceptor());
			respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(
					TransferenciaDTO.class, mensajeFormateado, TipoError.ERROR_GENERICO.getDescripcion());
		}
		return respuestaEjecucionTransferenciaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO
	 * #ejecutarAgendamientoRecordatorioTransferencia(ar.com.santanderrio.obp.
	 * servicios.transferencias.entities.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TransferenciaDTO> ejecutarAgendamientoRecordatorioTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente) {
		Respuesta<TransferenciaDTO> respuestaEjecucionTransferenciaDTO;
		try {
			cargarDestinatarioEnTransferenciaDTO(transferenciaDTO);
			TransferenciaDTO transferencia = agendaTransferenciaDAO
					.ejecutarAgendamientoRecordatorioTransferencia(transferenciaDTO, cliente);
			respuestaEjecucionTransferenciaDTO = respuestaFactory.crearRespuestaOk(transferencia);
			transferencia.setMensaje(armarMensajeFormateado(transferenciaDTO));
			respuestaEjecucionTransferenciaDTO.setRespuesta(transferencia);
		} catch (DAOException e) {
			LOGGER.error("ERROR_AGENDA_TRANSFERENCIA", e);
			respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaDTO>();
			respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
			String mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_AGENDAMIENTO_TRANSFERENCIA)
					.getMensaje(), transferenciaDTO.getNombreReceptor());
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensajeFormateado);
			respuestaEjecucionTransferenciaDTO.add(itemMensajeRespuesta);
		}
		return respuestaEjecucionTransferenciaDTO;
	}

	/**
	 * Arma mensaje de feedback según si transferencia es automatica o no
	 * (recordatorio).
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @return mensaje
	 */
	private String armarMensajeFormateado(TransferenciaDTO transferenciaDTO) {
		String mensajeFormateado;
		if (transferenciaDTO.getIsAutomatica()) {
			mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AGENDAMIENTO_TRANSFERENCIA_AUTOMATICO_OK)
					.getMensaje(), transferenciaDTO.getNombreReceptor());
		} else {
			mensajeFormateado = MessageFormat.format(this.mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AGENDAMIENTO_TRANSFERENCIA_RECORDATORIO_OK)
					.getMensaje(), transferenciaDTO.getNombreReceptor());
		}
		return mensajeFormateado;
	}

	/**
	 * Cargar destinatario en transferencia DTO.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 */
	private void cargarDestinatarioEnTransferenciaDTO(TransferenciaDTO transferenciaDTO) {
		if (sesionParametros.getDestinatarioView() != null) {
			transferenciaDTO.setBancoReceptor(sesionParametros.getDestinatarioView().getBancoReceptor());
			transferenciaDTO.setCuentaDestinoBanelco(sesionParametros.getDestinatarioView().getCuentaDestinoBanelco());
			transferenciaDTO.setCuit(sesionParametros.getDestinatarioView().getCuit());
			transferenciaDTO.setCuit2(sesionParametros.getDestinatarioView().getCuit2());
			transferenciaDTO.setCuit3(sesionParametros.getDestinatarioView().getCuit3());
			transferenciaDTO.setFiid(sesionParametros.getDestinatarioView().getFiid());
			transferenciaDTO.setLongitudCuentaDestinoBanelco(
					sesionParametros.getDestinatarioView().getLongCuentaDestinoBanelco());
			transferenciaDTO
					.setTipoDeCuentaFromBanelco(sesionParametros.getDestinatarioView().getTipoDeCuentaFromBanelco());
			transferenciaDTO
					.setTipoDeCuentaToBanelco(sesionParametros.getDestinatarioView().getTipoDeCuentaToBanelco());
			transferenciaDTO.setBancoDestino(sesionParametros.getDestinatarioView().getBancoDestino());
			if (sesionParametros.getDestinatarioView().getTitular() != null) {
				transferenciaDTO.setTitular(sesionParametros.getDestinatarioView().getTitular().trim());
			}
			transferenciaDTO.setUser(sesionParametros.getDestinatarioView().getUser());
		}
	}

}
