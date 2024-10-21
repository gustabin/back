/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.AltaChequesBO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.CodigosBancariosDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EfectuarAltaChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EntidadesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.SimulacionAltaChequeDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.AltaChequeEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequeSimuladoDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesASimularDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesAltaDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.ChequesSimuladosDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EfectivizacionAltaChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.MensajeErrorEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequeEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.SimulacionAltaChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequeViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewIn;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.CuitIn;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaChequesBOImpl.
 */
@Component
public class AltaChequesBOImpl implements AltaChequesBO {

	/** El conector con el servicio de simulacion. */
	@Autowired
	private SimulacionAltaChequeDAO simulador;

	/** El conector con el servicio de efectivizacion. */
	@Autowired
	private EfectuarAltaChequesDAO efectivizacion;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The codigos DAO. */
	@Autowired
	private CodigosBancariosDAO codigosDAO;

	/** The entidades DAO. */
	@Autowired
	private EntidadesDAO entidadesDAO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaChequesBOImpl.class);

	/** The Constant ERROR_CODE_SUPERA_LIMITE_DIARIO. */
	private static final String ERROR_CODE_SUPERA_LIMITE_DIARIO = "20000005";

	/** The Constant ERROR_MESSAGE_SUPERA_LIMITE_DIARIO. */
	private static final String ERROR_MESSAGE_SUPERA_LIMITE_DIARIO = "ZYA0005";

	/** The Constant ERROR_SYSTEM_SUPERA_LIMITE_DIARIO. */
	private static final String ERROR_SYSTEM_SUPERA_LIMITE_DIARIO = "AZY";


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.AltaChequesBO#
	 * simularAltaCheques(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 * ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.
	 * AltaChequesViewIn)
	 */
	@Override
	public Respuesta<ChequesSimuladosDTO> simularAltaCheques(Cliente cliente, String subProdPaquete,
			DatosCesionDTO datoCesion, Cuenta cuentaSeleccionada, AltaChequesViewIn chequesView) {
		String cuentaHabilitadaReparseada = parsearCuentaHab(cuentaSeleccionada);
		ChequesASimularDTO cheques = obtenerChequeASimular(subProdPaquete, datoCesion, cuentaHabilitadaReparseada,
				chequesView);
		SimulacionAltaChequesEntity entity = null;
		try {
			entity = simulador.simularAltaChequesDAO(cheques, cliente);
		} catch (DAOException e) {
			LOGGER.error("Error en el llamado del servicio:" + e);
		}
		try{
		if (chequesView.getNumeroTramite() != null) {
			return obtenerRespuestaSimulacionTasas(entity, cuentaSeleccionada,
					chequesView.getFechaAlta().replace("-", ""));
		}
		if (entity == null) {
			return respuestaFactory.crearRespuestaError("errorGenerico", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_ERROR_GENERICO);
		} else if (excedeLimiteDiario(entity)) {
			return respuestaFactory.crearRespuestaWarning(obtenerRespuestaDTO(entity, cuentaSeleccionada, false, null),
					"", TipoError.WARNING_SUPERA_LIMITE_CHEQUES,
					CodigoMensajeConstantes.DESCUENTO_CHEQUES_EXCEDE_PROCESAMIENTO_DIARIO);
		} else if (0 != Integer.valueOf(entity.getCodigoRetornoExtendido())
				|| (("19".equals(entity.getEstTramite())) && 0 < Integer.valueOf(entity.getCantChqAcep()))) {
			return procesarError(entity);
		} else if (Integer.valueOf(entity.getCantChqRech()) > 0) {
			return obtenerWarning(entity, cuentaSeleccionada);
		}
		return respuestaFactory.crearRespuestaOk(ChequesSimuladosDTO.class,
				obtenerRespuestaDTO(entity, cuentaSeleccionada, false, null));
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("errorLegales", TipoError.ERROR_LEGALES,
					CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
		}
	}

	/**
	 * Obtener respuesta simulacion tasas.
	 *
	 * @param entity
	 *            the entity
	 * @param cuentaSeleccionada
	 *            the cuenta seleccionada
	 * @param fechaAlta
	 *            the fecha alta
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<ChequesSimuladosDTO> obtenerRespuestaSimulacionTasas(SimulacionAltaChequesEntity entity,
			Cuenta cuentaSeleccionada, String fechaAlta) throws DAOException {
		if (entity != null) {
			if (10000083 == Integer.valueOf(entity.getCodigoRetornoExtendido())) {
				return respuestaFactory.crearRespuestaError("fueraDeHorario", TipoError.ERROR_FUERA_HORARIO_TASAS,
						CodigoMensajeConstantes.SIMULACION_CHEQUES_FUERA_HORARIO);
			}else if (0 != Integer.valueOf(entity.getCodigoRetornoExtendido())
						|| (("19".equals(entity.getEstTramite())) && 0 <= Integer.valueOf(entity.getCantChqAcep()))) {
				 return respuestaFactory.crearRespuestaError("errorGenerico", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_SIMULACION_TASAS_GENERICO);
			 } else if (0 == Integer.valueOf(entity.getCodigoRetornoExtendido())) {
				return respuestaFactory.crearRespuestaOk(ChequesSimuladosDTO.class,
						obtenerRespuestaDTO(entity, cuentaSeleccionada, true, fechaAlta));
			}
		}
		return respuestaFactory.crearRespuestaError("errorGenericoConTimeOut", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_SIMULACION_TASAS_GENERICO);
	}

	/**
	 * Obtiene un string con la informacion de la cuenta que necesito enviar al
	 * servicio.
	 *
	 * @param cuentaSeleccionada
	 *            the cuenta seleccionada
	 * @return the string
	 */
	private String parsearCuentaHab(Cuenta cuentaSeleccionada) {
		return cuentaSeleccionada.getTipoCuentaSinUnificar() + "-" + cuentaSeleccionada.getNroSucursal() + "-"
				+ cuentaSeleccionada.getNroCuentaProducto();
	}

	/**
	 * Obtiene la respuesta warning indicada revisando la cantidad de
	 * rechazados.
	 *
	 * @param entity
	 *            the entity
	 * @param cuentaSeleccionada
	 *            the cuenta habilitada
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<ChequesSimuladosDTO> obtenerWarning(SimulacionAltaChequesEntity entity,
			Cuenta cuentaSeleccionada) throws DAOException {
		Respuesta<ChequesSimuladosDTO> res;
		if (Long.valueOf(entity.getCantChqRech()).equals(entity.getCantidadRegistros())) {
			res = respuestaFactory.crearRespuestaWarning(obtenerRespuestaDTO(entity, cuentaSeleccionada, false, null),
					"todosRechazados", TipoError.SIMULACION_ALTA_TODOS_RECHAZADOS,
					CodigoMensajeConstantes.SIMULACION_TODOS_CHEQUES_RECHAZADOS);
		} else {
			res = respuestaFactory.crearRespuestaWarning(obtenerRespuestaDTO(entity, cuentaSeleccionada, false, null),
					"algunosRechazados", TipoError.SIMULACION_ALTA_ALGUNOS_RECHAZADOS,
					CodigoMensajeConstantes.SIMULACION_ALGUNOS_CHEQUES_RECHAZADOS);
			int cantRechazados = Integer.valueOf(entity.getCantChqRech());
			res.getItemsMensajeRespuesta().get(0).setMensaje(StringUtils.replace(
					res.getItemsMensajeRespuesta().get(0).getMensaje(), "{0}", String.valueOf(cantRechazados)));
		}
		return res;
	}

	/**
	 * Crea el objeto de respuesta ChequesSimuladosDTO aplicando la logica de
	 * negocio necesaria.
	 *
	 * @param entity
	 *            the entity
	 * @param cuentaSeleccionada
	 *            the cuenta habilitada
	 * @param esSimulacion
	 *            the es simulacion
	 * @param fechaAlta
	 *            the fecha alta
	 * @return the cheques simulados DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ChequesSimuladosDTO obtenerRespuestaDTO(SimulacionAltaChequesEntity entity, Cuenta cuentaSeleccionada,
			boolean esSimulacion, String fechaAlta) throws DAOException {
		ChequesSimuladosDTO chequesSimulados = new ChequesSimuladosDTO();
		chequesSimulados.setImporteAcreditar(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpNetoChqAcep(), 2));
		chequesSimulados
				.setImporteTotal(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpuChqAcep(), 2));
		chequesSimulados.setCuentaDebito(new IdentificacionCuenta(cuentaSeleccionada.getNroSucursal(),
				cuentaSeleccionada.getNroCuentaProducto()));
		chequesSimulados.setNumeroDeOperacion(BigInteger.valueOf(Long.valueOf(entity.getNroTramite())));
		chequesSimulados.setChequesDescontados(Integer.valueOf(entity.getCantChqAcep()));
		chequesSimulados
				.setImporteCheque(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpuChqAcep(), 2));
		chequesSimulados.setImporteImpuestos(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpuesChqAcep(), 2));
		chequesSimulados.setImporteIntereses(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumIntChqAcep(), 2));
		chequesSimulados
				.setImporteNeto(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpNetoChqAcep(), 2));
		for (SimulacionAltaChequeEntity cheque : entity.getOperaciones()) {
			if ("1".equals(cheque.getEstChq())) {
				chequesSimulados.getListaAceptados().add(obtenerCheque(cheque, true, esSimulacion));
			} else if (!esSimulacion) {
				chequesSimulados.getListaRechazados().add(obtenerCheque(cheque, false, esSimulacion));
			}
		}
		chequesSimulados
				.setComisionAdic(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getComisionAdic(), 2));
		chequesSimulados.setTasaAplicada(parsearPorcentaje(entity.getTasaAplicada()));
		chequesSimulados.setTasaEfectivaAnual(parsearPorcentaje(entity.getTasaTem()));
		chequesSimulados.setCostoFinancieroTotal(parsearPorcentaje(entity.getTasaCft()));
		if(!Long.valueOf(entity.getCantChqRech()).equals(entity.getCantidadRegistros())){
			setearLegales(esSimulacion, chequesSimulados, fechaAlta);
		}
		
		return chequesSimulados;
	}

	/**
	 * Setear legales.
	 *
	 * @param esSimulacion
	 *            the es simulacion
	 * @param chequesSimulados
	 *            the cheques simulados
	 * @param fechaAlta
	 *            the fecha alta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void setearLegales(boolean esSimulacion, ChequesSimuladosDTO chequesSimulados, String fechaAlta) throws DAOException {

		if (esSimulacion) {
			chequesSimulados.setMensajeFeedback(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SIMULACION_TASAS_OK).getMensaje());
			try {
				chequesSimulados.setFechaAlta(ISBANStringUtils.parsearFechaIATX(fechaAlta));
			} catch (ParseException e) {
				LOGGER.error("Error al parsear la fecha de alta: " + e);
			}
			chequesSimulados.setLegal1(legalBO.obtenerLegal("1025"));
			chequesSimulados.setLegal2(legalBO.obtenerLegal("1026"));
			chequesSimulados.setLegal3(legalBO.obtenerLegal("1027"));
			chequesSimulados.setLegal4(legalBO.obtenerLegal("1028"));
		} else {
			chequesSimulados.setLegal1(legalBO.obtenerLegal("1026"));
			chequesSimulados.setLegal2(legalBO.obtenerLegal("1027"));
			chequesSimulados.setLegal3(legalBO.obtenerLegal("1028"));
		}
		chequesSimulados.setLegal5(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_DESCUENTO_CHEQUES_5));
		
	}

	/**
	 * Parsear porcentaje.
	 *
	 * @param valor
	 *            the valor
	 * @return the string
	 */
	private String parsearPorcentaje(String valor) {
		//2 ceros al final del string para evitar el redondeo
		String valorSinRedondeo = valor.substring(0, valor.length() - 2) + "00";
		return ISBANStringUtils.formatearSaldo(ISBANStringUtils.convertirStrToBigDecimalSinException(valorSinRedondeo, 4)) + " %";
	}

	/**
	 * Obtener cheque.
	 *
	 * @param cheque
	 *            the cheque
	 * @param esAceptado
	 *            the es aceptado
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the cheque simulado DTO
	 */
	private ChequeSimuladoDTO obtenerCheque(SimulacionAltaChequeEntity cheque, boolean esAceptado,
			boolean esSimulacion) {
		ChequeSimuladoDTO chequeSimulado = new ChequeSimuladoDTO();
		String numeroCheque = cheque.getBcoGirado() + cheque.getSucGirada();
		numeroCheque = numeroCheque + cheque.getCodPostal() + cheque.getDigVerif1();
		numeroCheque = numeroCheque + cheque.getNroCheque() + cheque.getDigVerif2();
		numeroCheque = numeroCheque + cheque.getCtaGirada() + cheque.getDigVerif3();
		chequeSimulado.setNumeroCheque(numeroCheque);
		chequeSimulado
				.setImporteCheque(ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpCheque(), 2));
		if (esAceptado) {
			chequeSimulado.setBanco(obtenerBanco(cheque.getBcoGirado()));
			chequeSimulado.setImporteImpuestos(
					ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpImpuesto(), 2));
			chequeSimulado.setImporteIntereses(
					ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpInteres(), 2));
			chequeSimulado
					.setImporteNeto(ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpNeto(), 2));
			try {
				chequeSimulado
						.setFechaDePago(ISBANStringUtils.parsearFechaIATX(cheque.getFecVtoChq().replace("-", "")));
			} catch (ParseException e) {
				LOGGER.error("Error en el parseo de la fecha:" + e);
			}
			if (!esSimulacion) {
				chequeSimulado.setDiasAAdelantar(Integer.valueOf(cheque.getDiasAdelantar()));
			}

		} else {
			chequeSimulado.setFirmante(obtenerFirmante(cheque));
			try {
				chequeSimulado
						.setFechaDePago(ISBANStringUtils.parsearFechaIATX(cheque.getFecVtoChq().replace("-", "")));
			} catch (ParseException e) {
				LOGGER.error("Error en el parseo de la fecha:" + e);
			}
		}
		return chequeSimulado;
	}

	/**
	 * Obtener firmante.
	 *
	 * @param cheque
	 *            the cheque
	 * @return the string
	 */
	private String obtenerFirmante(SimulacionAltaChequeEntity cheque) {
		String tipoDocumento = "";
		if ("D".equals(cheque.getTpoDocLibr1())) {
			tipoDocumento = "CDI";
		} else if ("T".equals(cheque.getTpoDocLibr1())) {
			tipoDocumento = "CUIT";
		}
		if ("L".equals(cheque.getTpoDocLibr1())) {
			tipoDocumento = "CUIL";
		}
		String numeroDocumento;
		if ("N".equals(cheque.getTpoDocLibr1())) {
			numeroDocumento = ISBANStringUtils.formatearDocumento(cheque.getNroDocLibr1());
		} else {
			numeroDocumento = ISBANStringUtils.formatearCuil(cheque.getNroDocLibr1());
		}
		return tipoDocumento + " " + numeroDocumento;
	}

	/**
	 * Obtener banco.
	 *
	 * @param bcoGirado
	 *            the bco girado
	 * @return the string
	 */
	private String obtenerBanco(String bcoGirado) {
		int codigoBancario = codigosDAO.obtenerIndiceCodigoBancario(bcoGirado);
		if (codigoBancario == -1) {
			return "";
		}
		return entidadesDAO.obtenerIndiceCodigoBancario(codigoBancario);
	}

	/**
	 * Crea la respuesta error correspondiente.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	private Respuesta<ChequesSimuladosDTO> procesarError(SimulacionAltaChequesEntity entity) {
		// Fuera de horario permitido
		if ("10000083".equals(entity.getCodigoRetornoExtendido())) {
			return respuestaFactory.crearRespuestaError("fueraDeHorario", TipoError.FUERA_DE_HORARIO,
					CodigoMensajeConstantes.SIMULACION_CHEQUES_FUERA_HORARIO);
			// Fecha no válida
		} else if ("10000088".equals(entity.getCodigoRetornoExtendido())) {
			return respuestaFactory.crearRespuestaError("fechaInvalida", TipoError.ERROR_FECHA,
					CodigoMensajeConstantes.SIMULACION_CHEQUES_FECHA_INVALIDA);
		} else if (("19".equals(entity.getEstTramite())) && 0 < Integer.valueOf(entity.getCantChqAcep())) {
			return respuestaFactory.crearRespuestaError("simulacionAltaRechazado",
					TipoError.SIMULACION_ALTA_CHEQUES_RECHAZADO, CodigoMensajeConstantes.SIMULACION_CHEQUES_RECHAZADO);
		} else if ("10000005".equals(entity.getCodigoRetornoExtendido())) {
			return respuestaFactory.crearRespuestaError("simulacionAltaRechazado",
					TipoError.SIMULACION_ALTA_CHEQUES_RECHAZADO, CodigoMensajeConstantes.SIMULACION_CHEQUES_RECHAZADO_COM_7001);
		}
		return respuestaFactory.crearRespuestaError("errorGenerico", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_OP_PRECARGADAS_ERROR_GENERICO);
	}

	/**
	 * Excede limite diario.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	private boolean excedeLimiteDiario(SimulacionAltaChequesEntity entity) {
		return ERROR_CODE_SUPERA_LIMITE_DIARIO.equals(entity.getCodigoRetornoExtendido()) && ERROR_SYSTEM_SUPERA_LIMITE_DIARIO.equals(entity.getErrorSystem())
				&& contieneWarningLimiteDiario(entity);
	}
	
	/**
	 * Contiene warning limite diario.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	private boolean contieneWarningLimiteDiario(SimulacionAltaChequesEntity entity) {
		List<MensajeErrorEntity> errors = entity.getErrorMessages();
		if (errors != null) {
			for (MensajeErrorEntity error : errors) {
				if (error.getDescripcionError() != null 
						&& error.getDescripcionError().contains(ERROR_MESSAGE_SUPERA_LIMITE_DIARIO)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtener cheque A simular.
	 *
	 * @param subProdPaquete
	 *            the sub prod paquete
	 * @param datoCesion
	 *            the dato cesion
	 * @param cuentaHabilitada
	 *            the cuenta habilitada
	 * @param chequesView
	 *            the cheques view
	 * @return the cheques A simular DTO
	 */
	private ChequesASimularDTO obtenerChequeASimular(String subProdPaquete, DatosCesionDTO datoCesion,
			String cuentaHabilitada, AltaChequesViewIn chequesView) {
		ChequesASimularDTO cheques = new ChequesASimularDTO();
		String[] cuenta = cuentaHabilitada.split("-");
		cheques.setSubprodByP(subProdPaquete);
		cheques.setTipoCuenta(cuenta[0]);
		cheques.setSucCuenta(cuenta[1]);
		cheques.setNroCuenta(cuenta[2].substring(cuenta[2].length() - 7));
		cheques.setCalificado("S");
		cheques.setDisponible(ISBANStringUtils.ajustadorBigDecimalIatx(datoCesion.getMontoDisponibleS(), 15));
		cheques.setLinea(ISBANStringUtils.ajustadorBigDecimalIatx(datoCesion.getLineaS(), 15));
		cheques.setTipoLinea(datoCesion.getTipoLineaS());
		cheques.setNroTramite(chequesView.getNumeroTramite());
		for (AltaChequeViewIn cheque : chequesView.getListaCheques()) {
			cheques.getListaCheques().add(obtenerChequePrecargado(cheque, chequesView.getNumeroTramite() != null));
		}
		while (cheques.getListaCheques().size() < 10) {
			cheques.getListaCheques().add(obtenerChequeVacio());
		}
		return cheques;
	}

	/**
	 * Obtener cheque vacio.
	 *
	 * @return the cheque A simular DTO
	 */
	private ChequeASimularDTO obtenerChequeVacio() {
		ChequeASimularDTO cheque = new ChequeASimularDTO();
		cheque.setBcoGirado(ISBANStringUtils.repeat(" ", 3));
		cheque.setSucGirada(ISBANStringUtils.repeat(" ", 3));
		cheque.setCodPostal(ISBANStringUtils.repeat(" ", 4));
		cheque.setDigVerificador1(ISBANStringUtils.repeat(" ", 1));
		cheque.setNroCheque(ISBANStringUtils.repeat(" ", 8));
		cheque.setDigVerificador2(ISBANStringUtils.repeat(" ", 1));
		cheque.setCuentaGirada(ISBANStringUtils.repeat(" ", 11));
		cheque.setDigVerificador3(ISBANStringUtils.repeat(" ", 1));
		cheque.setImpCheque(ISBANStringUtils.repeat("0", 15));
		cheque.setFechaVencimiento(ISBANStringUtils.repeat(" ", 10));
		cheque.setTipoDocLibrador1(ISBANStringUtils.repeat(" ", 1));
		cheque.setNroDocLibrador1(ISBANStringUtils.repeat(" ", 11));
		return cheque;
	}

	/**
	 * Obtener cheque precargado.
	 *
	 * @param cheque
	 *            the cheque
	 * @param esSimulacion
	 *            the es simulacion
	 * @return the cheque A simular DTO
	 */
	private ChequeASimularDTO obtenerChequePrecargado(AltaChequeViewIn cheque, boolean esSimulacion) {
		ChequeASimularDTO chequeDTO = new ChequeASimularDTO();
		if (cheque.getDatosCheque() == null) {
			cheque.setDatosCheque(new CuitIn(cheque.getNroCheque()));
		}
		chequeDTO.setBcoGirado(cheque.getDatosCheque().getInputBanco());
		chequeDTO.setSucGirada(cheque.getDatosCheque().getInputSucursal());
		chequeDTO.setCodPostal(cheque.getDatosCheque().getInputCP());
		chequeDTO.setDigVerificador1(cheque.getDatosCheque().getInputVerificador1());
		chequeDTO.setNroCheque(cheque.getDatosCheque().getInputCheque());
		chequeDTO.setDigVerificador2(cheque.getDatosCheque().getInputVerificador2());
		chequeDTO.setCuentaGirada(cheque.getDatosCheque().getInputCuenta());
		chequeDTO.setDigVerificador3(cheque.getDatosCheque().getInputVerificador3());
		if (esSimulacion) {
			chequeDTO.setImpCheque(ISBANStringUtils
					.ajustadorBigDecimalIatx(new BigDecimal(cheque.getValor().replace(".", "").replace(",", ".")), 15));
			chequeDTO.setFechaVencimiento(obtenerFechaPago(cheque.getFechaPago()));
			chequeDTO.setTipoDocLibrador1(cheque.getTipo());
		} else {
			chequeDTO.setImpCheque(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(cheque.getValor()), 15));
			String[] fecha = cheque.getFechaPago().split("/");
			chequeDTO.setFechaVencimiento(fecha[2] + "-" + fecha[1] + "-" + fecha[0]);
			chequeDTO.setTipoDocLibrador1(obtenerDeTipoDoc(cheque.getTipo()));
		}
		chequeDTO.setNroDocLibrador1(cheque.getDni());
		return chequeDTO;
	}

	/**
	 * Obtener fecha pago.
	 *
	 * @param fechaPago
	 *            the fecha pago
	 * @return the string
	 */
	private String obtenerFechaPago(String fechaPago) {
		if (fechaPago.contains("-")) {
			return fechaPago;
		}
		String fechaParseada = fechaPago.substring(0, 4) + "-";
		fechaParseada = fechaParseada + fechaPago.substring(4, 6) + "-";
		fechaParseada = fechaParseada + fechaPago.substring(6);
		return fechaParseada;
	}

	/**
	 * Obtener de tipo doc.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the string
	 */
	private String obtenerDeTipoDoc(String tipo) {
		if ("CDI".equals(tipo)) {
			return "D";
		} else if ("CUIT".equals(tipo)) {
			return "T";
		} else if ("CUIL".equals(tipo)) {
			return "L";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.descuento.cheques.bo.AltaChequesBO#
	 * efectuarAltaCheques(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ChequesAltaDTO> efectuarAltaCheques(Cliente cliente, String nroTramite, String nroCuenta) {
		EfectivizacionAltaChequesEntity entity = null;
		try {
			entity = efectivizacion.efectuarAltaChequesDAO(ISBANStringUtils.formateadorConCerosIzq(nroTramite, 10),
					cliente);
		} catch (DAOException e) {
			LOGGER.error("Error en llamado al servicio:" + e);
		}
		if (entity == null || Integer.valueOf(entity.getCodigoRetornoExtendido()) != 0
				|| noTieneChequesAceptados(entity)) {
			return respuestaFactory.crearRespuestaError("errorGenerico", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ALTA_CHEQUES_ERROR);
		}
		try{
			ChequesAltaDTO dto = obtenerDTO(entity, nroTramite, nroCuenta);
			return respuestaFactory.crearRespuestaOk(ChequesAltaDTO.class, dto);
			
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("errorLegales", TipoError.ERROR_LEGALES,
					CodigoMensajeConstantes.ERROR_DETALLE_OPERACIONES_PRECARGADAS);
		}
	}

	/**
	 * Obtener DTO.
	 *
	 * @param entity
	 *            the entity
	 * @param nroTramite
	 *            the nro tramite
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the cheques alta DTO
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ChequesAltaDTO obtenerDTO(EfectivizacionAltaChequesEntity entity, String nroTramite, String nroCuenta) throws DAOException {
		ChequesAltaDTO chequesEfectuados = new ChequesAltaDTO();
		chequesEfectuados.setImporteAcreditar(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpTotalAcred(), 2));
		chequesEfectuados
				.setImporteTotal(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpChqAcep(), 2));
		chequesEfectuados.setCuentaCredito(new IdentificacionCuenta(nroCuenta));
		chequesEfectuados.setChequesDescontados(Integer.valueOf(entity.getCantChqAcep()));
		chequesEfectuados.setImporteTotalCheque(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpChqAcep(), 2));
		chequesEfectuados.setImporteImpuestos(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumImpuChqAcep(), 2));
		chequesEfectuados.setImporteIntereses(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getSumIntChqAcep(), 2));
		chequesEfectuados.setImporteAAcreditar(
				ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getImpTotalAcred(), 2));
		for (AltaChequeEntity cheque : entity.getOperaciones()) {
			if ("1".equals(cheque.getEstCheque())) {
				chequesEfectuados.getListaAceptados().add(obtenerCheque(cheque));
			}
		}
		chequesEfectuados
				.setComisionAdic(ISBANStringUtils.convertirStrToBigDecimalSinException(entity.getComisionAdic(), 2));
		chequesEfectuados.setTasaAplicada(parsearPorcentaje(entity.getTasaAplic()));
		chequesEfectuados.setTasaEfectivaAnual(parsearPorcentaje(entity.getTasaTEA()));
		chequesEfectuados.setCostoFinancieroTotal(parsearPorcentaje(entity.getTasaCFT()));
		try {
			chequesEfectuados.setFechaAlta(ISBANStringUtils.parsearFechaIATX(entity.getFechaAlta().replace("-", "")));
		} catch (ParseException e) {
			LOGGER.error("Error parseando la fecha de alta: " + e);
		}
		chequesEfectuados.setNumeroDeOperacion(BigInteger.valueOf(Long.valueOf(nroTramite)));
		chequesEfectuados.setLegal1(legalBO.obtenerLegal("1025"));
		chequesEfectuados.setLegal2(legalBO.obtenerLegal("1026"));
		chequesEfectuados.setLegal3(legalBO.obtenerLegal("1027"));
		chequesEfectuados.setLegal4(legalBO.obtenerLegal("1028"));
		chequesEfectuados.setMensajeFeedback(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ALTA_CHEQUES_OK).getMensaje());

		return chequesEfectuados;
	}

	/**
	 * Obtener cheque.
	 *
	 * @param cheque
	 *            the cheque
	 * @return the cheque simulado DTO
	 */
	private ChequeSimuladoDTO obtenerCheque(AltaChequeEntity cheque) {
		ChequeSimuladoDTO chequeDTO = new ChequeSimuladoDTO();
		String numeroCheque = cheque.getBcoGirado() + cheque.getSucGirada();
		numeroCheque = numeroCheque + cheque.getCodPostal() + cheque.getDigVerif1();
		numeroCheque = numeroCheque + cheque.getNroCheque() + cheque.getDigVerif2();
		numeroCheque = numeroCheque + cheque.getCtaGirada() + cheque.getDigVerif3();
		chequeDTO.setNumeroCheque(numeroCheque);
		chequeDTO.setBanco(obtenerBanco(cheque.getBcoGirado()));
		try {
			chequeDTO.setFechaDePago(ISBANStringUtils.parsearFechaIATX(cheque.getFechaPresentacion().replace("-", "")));
		} catch (ParseException e) {
			LOGGER.error("Error en el parseo de la fecha:" + e);
		}
		chequeDTO.setImporteTotal(ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpCheque(), 2));
		chequeDTO
				.setImporteImpuestos(ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpImpuesto(), 2));
		chequeDTO.setImporteIntereses(
				ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpIntereses(), 2));
		chequeDTO.setImporteAAcreditar(ISBANStringUtils.convertirStrToBigDecimalSinException(cheque.getImpNeto(), 2));
		return chequeDTO;
	}

	/**
	 * Cheques si la respuesta tiene o no cheques aceptados.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if successful
	 */
	private boolean noTieneChequesAceptados(EfectivizacionAltaChequesEntity entity) {
		boolean esRechazado = true;
		if (0 < entity.getCantidadRegistros()) {
			for (AltaChequeEntity cheque : entity.getOperaciones()) {
				esRechazado = esRechazado && ("2".equals(cheque.getEstCheque()));
			}
		}
		return esRechazado;
	}

}
