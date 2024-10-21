/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.TransferenciaFondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.ConfigTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteTransferenciaFondo;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoResumidoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;

/**
 * The Class TransferenciaFondoManagerImpl.
 *
 * @author
 */
@Component()
public class TransferenciaFondoManagerImpl extends BaseManager implements TransferenciaFondoManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaFondoManagerImpl.class);

	/** The Constant COD_CANAL. */
	private static final String COD_CANAL = "OBP";

	/** The Constant INVERSOR_RIESGO_MEDIO. */
	private static final Object INVERSOR_RIESGO_MEDIO = "1";

	/** The Constant INVERSOR_RIESGO_BLOQUEANTE. */
	private static final Object INVERSOR_RIESGO_BLOQUEANTE = "2";

	/** The Constant MENSAJE_LEGALES_COMPROBANTE_TRANSFERENCIA. */
	private static final String MENSAJE_LEGALES_COMPROBANTE_TRANSFERENCIA = "50007";

	/** The transferencia BO. */
	@Autowired
	private TransferenciaFondoBO transferenciaFondoBO;

	/** Dao de inversiones. */
	@Autowired
	private InversionDAO inversionDAO;

	/** The consulta fondo DAO. */
	@Autowired
	protected ConsultaFondoDAO consultaFondoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;
	
	/** The fondo BO. */
	@Autowired
	private FondoBO fondoBO;

	/** Fondos tenencias prototype */
	@Autowired
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> iniciarTransferencia(CuentasConsultaFondoView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<CuentasConsultaFondoView> respuesta = super.validate(viewRequest, new CuentasConsultaFondoView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
		LOGGER.info("Invocando al BO iniciarTransferencia");

		CuentasConsultaFondoDTO requestDTO = viewToDTO(viewRequest.getCuentasTitulo(), viewRequest.getTipoBanca());
		Respuesta<CuentasConsultaFondoDTO> respuestaBO = transferenciaFondoBO
				.obtenerFondosSuscriptosYDisponibles(requestDTO, sesionCliente.getCliente());

		respuestaBO=filtraFondosRepatriacion(respuestaBO);
		
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_FONDO_BPERS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class,
					dtoToView(respuestaBO.getRespuesta()));
		}

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if(TipoError.FUERADEHORARIO.toString().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_TRANSFERENCIA_PASO1_RTL,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			}
			return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class,
					new CuentasConsultaFondoView(), respuestaBO.getItemsMensajeRespuesta());
		} else {
			estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_FONDO_BPERS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(CuentasConsultaFondoView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}
	}

	//private Respuesta<CuentasConsultaFondoDTO> filtraRepatriacion(Respuesta<CuentasConsultaFondoDTO> respuestaBO) {
	private Respuesta<CuentasConsultaFondoDTO> filtraFondosRepatriacion(Respuesta<CuentasConsultaFondoDTO> respuestaBO) {
		try {
			List<FondoResumidoDTO> fondos=new ArrayList<FondoResumidoDTO>(respuestaBO.getRespuesta().getFondosTotales());
			for(FondoResumidoDTO fondo : fondos) {
				if(fondo.isRepatriacion()) {
					respuestaBO.getRespuesta().getFondosTotales().remove(fondo);
				}
			}
		}catch(Exception e){
			return respuestaBO;
		}
		
		return respuestaBO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<CuentasConsultaFondoView> iniciarTransferenciaBpriv(CuentasConsultaFondoView viewRequest) {
		sessionParametros.setContador(new ContadorIntentos(2));
		Respuesta<CuentasConsultaFondoView> respuesta = super.validate(viewRequest, new CuentasConsultaFondoView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
		LOGGER.info("Invocando al BO iniciarTransferencia");

		CuentasConsultaFondoDTO requestDTO = viewToDTO(viewRequest.getCuentasTitulo(), viewRequest.getTipoBanca());
		//SE FILTRAN LAS CUENTAS DE REPATRIACIÓN DE BP
		requestDTO = filtraRepatriacionBP(requestDTO);
		Respuesta<CuentasConsultaFondoDTO> respuestaBO = transferenciaFondoBO
				.obtenerFondosSuscriptosYDisponiblesBpriv(requestDTO, sesionCliente.getCliente());
		
		//respuestaBO=filtraRepatriacion(respuestaBO);
		respuestaBO=filtraFondosRepatriacion(respuestaBO);

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_FONDO_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class,
					dtoToView(respuestaBO.getRespuesta()));
		}

		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			if(TipoError.FUERADEHORARIO.toString().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				estadisticaManager.add(EstadisticasConstants.FUERA_HORARIO_TRANSFERENCIA_PASO1_BPRIV,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_FH);
			}
			return respuestaFactory.crearRespuestaWarning(CuentasConsultaFondoView.class,
					new CuentasConsultaFondoView(), respuestaBO.getItemsMensajeRespuesta());
		} else {
			estadisticaManager.add(EstadisticasConstants.INICIO_TRANSFERENCIA_FONDO_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(CuentasConsultaFondoView.class,
					respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		}
	}
	
	//SE AGREGA PARA FILTRAR LAS CUENTAS DE REPATRIACION DE BP
	private CuentasConsultaFondoDTO filtraRepatriacionBP(CuentasConsultaFondoDTO cuentasConsultaFondoDTO) {
		Cliente cliente = sesionCliente.getCliente();
		List<Cuenta> ctasTitBPRep=cliente.getCuentasTitBPRepatriacion();
		List<CuentaTituloDTO> ctasDTO = cuentasConsultaFondoDTO.getCuentasTitulo();
		
		for(Cuenta ctaTitRep : ctasTitBPRep) {
			for(CuentaTituloDTO ctaDto : ctasDTO) {
				if(Integer.parseInt(ctaDto.getCuentaOperativa().replaceAll("/",""))==ctaTitRep.getCuentaOPRepatriacion()) {
					cuentasConsultaFondoDTO.getCuentasTitulo().remove(ctaDto);
				}
			}
		}
		return cuentasConsultaFondoDTO;
	}

	/**
	 * Dto to view.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @return the cuentas consulta fondo view
	 */
	private CuentasConsultaFondoView dtoToView(CuentasConsultaFondoDTO respuestaBO) {
		CuentasConsultaFondoView retornoView = new CuentasConsultaFondoView();

		retornoView.setTipoBanca(respuestaBO.getTipoBanca());
		retornoView.setFondosTotales(new ArrayList<FondoResumidoView>());
		retornoView.setMensajeInformacion(respuestaBO.getMensajeInformacion());

		for (FondoResumidoDTO fondoResumidoDTO : respuestaBO.getFondosTotales()) {
			FondoResumidoView fondoResumidoView = new FondoResumidoView();
			fondoResumidoView.setImporteMaximo(fondoResumidoDTO.getImporteMaximo());
			fondoResumidoView.setImporteMinimo(fondoResumidoDTO.getImporteMinimo());
			fondoResumidoView.setMoneda(fondoResumidoDTO.getMoneda());
			fondoResumidoView.setNombreFondo(fondoResumidoDTO.getNombreFondo());
			fondoResumidoView.setSaldo(fondoResumidoDTO.getSaldo());
			fondoResumidoView.setGrupo(fondoResumidoDTO.getGrupo());
			fondoResumidoView.setCodigoFondo(fondoResumidoDTO.getCodigoFondo());
			fondoResumidoView.setHorario(fondoResumidoDTO.getHorario());
			fondoResumidoView.setDescripcionLarga(fondoResumidoDTO.getDescripcionLarga());
			if (fondoResumidoDTO.getIdMensajeGastos() != null) {
				fondoResumidoView.setIdMensajeGastos(fondoResumidoDTO.getIdMensajeGastos());
				fondoResumidoView.setTieneGastos(true);
			}
			fondoResumidoView.setRepatriacion(fondoResumidoDTO.isRepatriacion());
			retornoView.getFondosTotales().add(fondoResumidoView);
		}

		List<CuentaTituloView> cuentasTituloView = obtenerCuentasTituloView(respuestaBO);

		retornoView.setCuentasTitulo(cuentasTituloView);
		return retornoView;
	}

	/**
	 * crear un DTO para llamar al BO.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return dto.
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaDTO createDTO(SimulacionTransferenciaInView viewRequest) throws BusinessException {

		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		try {
			BeanUtils.copyProperties(transferenciaDTO, viewRequest);
			transferenciaDTO.setCuentaTitulo(viewRequest.getCuentaTitulo().trim());
		} catch (IllegalAccessException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		} catch (InvocationTargetException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		}
		return transferenciaDTO;

	}

	/**
	 * Creates the transferencia DTO.
	 *
	 * @param view
	 *            the view
	 * @return the transferencia DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaDTO createTransferenciaDTO(TransferenciaView view) throws BusinessException {
		TransferenciaDTO dto = new TransferenciaDTO();
		try {
			BeanUtils.copyProperties(dto, view);
		} catch (IllegalAccessException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		} catch (InvocationTargetException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		}
		return dto;
	}

	/**
	 * crear un View para retornar al TransferenciaSEI.
	 *
	 * @param dto
	 *            the dto
	 * @return view.
	 */
	private SimulacionTransferenciaView crearSimulacionTransferenciaView(TransferenciaDTO dto) {

		SimulacionTransferenciaView viewReturn = new SimulacionTransferenciaView();

		if (dto != null) {
			viewReturn.setUrlReglamento(dto.getUrlReglamento());
			viewReturn.setLegales(dto.getLegales());
			viewReturn.setLegalesInformacion(dto.getLegalesInformacion());
			viewReturn.setTerminosYCondiciones(dto.getTerminosYCondiciones());
		}
		return viewReturn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConfigTransferenciaView> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView) {
		Respuesta<ConfigTransferenciaDTO> respuestaConfigDTO = transferenciaFondoBO
				.obtenerDatosConfig(configTransferenciaInView, sesionCliente.getCliente());
		Respuesta<ConfigTransferenciaView> respuestaViewConfig = createRespuestaViewConfig(respuestaConfigDTO,
				configTransferenciaInView);
		return respuestaViewConfig;
	}

	/**
	 * Creates the respuesta view config.
	 *
	 * @param respuestaConfigDTO
	 *            the respuesta config DTO
	 * @param configTransferenciaInView
	 *            the config transferencia in view
	 * @return the respuesta
	 */
	private Respuesta<ConfigTransferenciaView> createRespuestaViewConfig(
			Respuesta<ConfigTransferenciaDTO> respuestaConfigDTO, ConfigTransferenciaInView configTransferenciaInView) {
		if (EstadoRespuesta.OK.equals(respuestaConfigDTO.getEstadoRespuesta())) {
			ConfigTransferenciaDTO configTransferenciaDTO = new ConfigTransferenciaDTO();
			ConfigTransferenciaView configTransferenciaView = new ConfigTransferenciaView();
			configTransferenciaDTO = respuestaConfigDTO.getRespuesta();
			try {
				configTransferenciaView.setUrlReglamento(fondoBO.obtenerReglamento(configTransferenciaInView.getCodigoFondo()));
			} catch (BusinessException e) {
				LOGGER.error("Error obteniendo la url Contrato", e);
				respuestaFactory.crearRespuestaError(ConfigFondoView.class, "Error obteniendo url Contrato", null);
			}
			
			try {
				BeanUtils.copyProperties(configTransferenciaView, configTransferenciaDTO);
				configTransferenciaView.setContratoAceptado("1".equals(configTransferenciaDTO.getContratoAceptado()));
				return respuestaFactory.crearRespuestaOk(configTransferenciaView);
			} catch (IllegalAccessException e) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} catch (InvocationTargetException e) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} else {
			return respuestaFactory.crearRespuestaError(ConfigTransferenciaView.class,
					respuestaConfigDTO.getItemsMensajeRespuesta());
		}
	}

	/**
	 * carga los datos para el request de confirmar orden.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @return the parametro datos confirmacion orden
	 */
	private ParametroDatosConfirmacionOrden cargarDatosConfirmarOrden(Respuesta<TransferenciaDTO> respuestaBO) {
		ParametroDatosConfirmacionOrden parametroDatos = new ParametroDatosConfirmacionOrden();
		parametroDatos.setCodCanal(COD_CANAL);
		parametroDatos.setIdEvaluacion(sessionParametros.getIdEv());
		parametroDatos.setNroOperacion(respuestaBO.getRespuesta().getNumeroOperacion());
		return parametroDatos;
	}

	/**
	 * Efectua logica error.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> efectuarLogicaError(Respuesta<TransferenciaDTO> respuestaBO,
			TransferenciaDTO transferenciaDTO) {
		guardarEstadisticaFeedback(transferenciaDTO, false);
		return respuestaFactory.crearRespuestaError(TransferenciaView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/**
	 * Llena los campos necesarios para grabar le estadistica para
	 * transferencia.
	 *
	 * @param nroCtaOrigen
	 *            the nro cta origen
	 * @param nroCtaDestino
	 *            the nro cta destino
	 * @param nroComprobante
	 *            the nro comprobante
	 * @param importe
	 *            the importe
	 * @param moneda
	 *            the moneda
	 * @return the estadistica
	 */
	public Estadistica crearEstadisticatransferencia(String nroCtaOrigen, String nroCtaDestino, String nroComprobante,
			String importe, String moneda) {
		String signoMoneda = "u$s";
		if ("ARS".equals(moneda)) {
			signoMoneda = "$";
		}
		Estadistica estadistica = new Estadistica();
		estadistica.setNroCtaOrigen(nroCtaOrigen);
		estadistica.setNroCtaDestino(nroCtaDestino);
		estadistica.setNroComprobante(nroComprobante);
		estadistica.setImporte(importe);
		estadistica.setMoneda(signoMoneda);
		return estadistica;
	}

	/**
	 * Guardar estadistica feedback.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param transferenciaOK
	 *            the transferencia OK
	 */
	private void guardarEstadisticaFeedback(TransferenciaDTO transferenciaDTO, boolean transferenciaOK) {
		ConsultaFondoOutEntity fondoOrigen;
		String cuentaTitulo = transferenciaDTO.getCuentaTitulo().replace("/", "");
		String certificado = transferenciaDTO.getNumeroCertificado();
		String importe = transferenciaDTO.getImporteNeto();

		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(transferenciaDTO.getCodigoFondo());
			String moneda = fondoOrigen.getMoneda();
			Estadistica estadistica = crearEstadisticatransferencia(cuentaTitulo, cuentaTitulo, certificado, importe,
					moneda);

			if ("ARS".equals(moneda)) {
				if (transferenciaOK) {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_PESOS);
				estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());

			} else {
				if (transferenciaOK) {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

				} else {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_DOLARES);
				estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			}
		} catch (DAOException e1) {
			LOGGER.error("Error al invocar consultaFondoDAO.", e1);
		}
	}

	/**
	 * Crear respuesta error transferencia fondo.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param finalizarTransferenciaBprivDTO
	 *            the finalizar transferencia bpriv DTO
	 * @return the respuesta
	 */
	private Respuesta<FinalizarTransferenciaFondoView> crearRespuestaErrorTransferenciaFondo(
			Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO,
			FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO) {
		guardarEstadisticaFeedbackBpriv(finalizarTransferenciaBprivDTO,
				finalizarTransferenciaBprivDTO.getCuentaTitulo(), false);
		return respuestaFactory.crearRespuestaError(FinalizarTransferenciaFondoView.class,
				respuestaBO.getItemsMensajeRespuesta().get(0).getMensaje(),
				respuestaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/**
	 * Guardar estadistica feedback bpriv.
	 *
	 * @param finalizarTransferenciaBprivDTO
	 *            the finalizar transferencia bpriv DTO
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @param transferenciaOK
	 *            the transferencia OK
	 */
	private void guardarEstadisticaFeedbackBpriv(FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO,
			String cuentaTitulo, boolean transferenciaOK) {
		ConsultaFondoOutEntity fondoOrigen;
		String cuentaTit = cuentaTitulo.replace("/", "");
		String comprobante = finalizarTransferenciaBprivDTO.getNroCertificado();
		String importe = finalizarTransferenciaBprivDTO.getImporte().replace(".", "");
		String importeFinal = importe.replace(",", ".");

		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(finalizarTransferenciaBprivDTO.getCodigoFondo());
			String moneda = fondoOrigen.getMoneda();
			Estadistica estadistica = crearEstadisticatransferencia(cuentaTit, cuentaTit, comprobante, importeFinal,
					moneda);

			if ("ARS".equals(moneda)) {
				if (transferenciaOK) {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_PESOS_BPRIV);
				estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			} else {
				if (transferenciaOK) {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

				} else {
					estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_DOLARES_BPRIV);
				estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
			}
		} catch (DAOException e1) {
			LOGGER.error("Error al invocar consultaFondoDAO.", e1);
		}
	}

	/**
	 * Efectuar logica ok.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param codigoFondoOrigen
	 *            the codigo fondo origen
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> efectuarLogicaOk(Respuesta<TransferenciaDTO> respuestaBO,
			String codigoFondoOrigen, String cuentaTitulo) {
		ConsultaFondoOutEntity fondoOrigen;
		String cuentaTit = cuentaTitulo.replace("/", "");
		String certificado = respuestaBO.getRespuesta().getNumeroCertificado();
		String importe = respuestaBO.getRespuesta().getImporteNeto().replace(".", "");
		String importeFinal = importe.replace(",", ".");

		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(codigoFondoOrigen);
			String moneda = fondoOrigen.getMoneda();
			Estadistica estadistica = crearEstadisticatransferencia(cuentaTit, cuentaTit, certificado, importeFinal,
					moneda);

			if ("ARS".equals(moneda)) {
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_PESOS);
			} else {
				estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_FINALIZAR_TRANSFERENCIA_DOLARES);
			}
			estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());
		} catch (DAOException e1) {
			LOGGER.error("Error al invocar consultaFondoDAO.", e1);
		}
		try {
			if (sessionParametros.getIdEv() != null) {
				inversionDAO.confirmacionOrden(cargarDatosConfirmarOrden(respuestaBO));
			}
		} catch (DAOException e) {
			LOGGER.error("Error al invocar el Ws.", e);
		}
		TransferenciaView view = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(TransferenciaView.class, view);
	}

	/**
	 * Creates the retorno view.
	 *
	 * @param dto
	 *            the dto
	 * @return the transferencia view
	 */
	private TransferenciaView createRetornoView(TransferenciaDTO dto) {
		TransferenciaView viewReturn = new TransferenciaView();
		if (dto != null) {
			viewReturn.setMensajeSuscripcion(dto.getMensajeSuscripcion());
			viewReturn.setImporteNeto(dto.getImporteNeto());
			viewReturn.setNumeroCertificado(dto.getNumeroCertificado());
			viewReturn.setFechaHora(dto.getFechaHora());
		}
		return viewReturn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<TransferenciaView> finalizarTransferenciaFondos(TransferenciaView viewRequest) {
		validarHashTransferenciaFondos(viewRequest);
		TransferenciaDTO transferenciaDTO;

		if (sesionCliente.getCliente() != null) {
			// Vaciar cache de la transaccion CNSTENVAL 110
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(sesionCliente.getCliente());
		}

		try {
			transferenciaDTO = createTransferenciaDTO(viewRequest);
		} catch (BusinessException e) {
			LOGGER.error("Error al crear el DTO {}.", viewRequest, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<TransferenciaDTO> respuestaBO = transferenciaFondoBO.finalizarTransferirFondos(transferenciaDTO,
				sesionCliente.getCliente());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			cargarHashDeLaVistaComprobante(viewRequest, respuestaBO.getRespuesta());
			return efectuarLogicaOk(respuestaBO, transferenciaDTO.getCodigoFondo(), transferenciaDTO.getCuentaTitulo());
		} else if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(TransferenciaView.class, viewRequest,
					respuestaBO.getItemsMensajeRespuesta());
		}
		return efectuarLogicaError(respuestaBO, transferenciaDTO);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimulacionTransferenciaView> simularTransferencia(SimulacionTransferenciaInView inView) {

		Respuesta<SimulacionTransferenciaView> respuesta = super.validate(inView, new SimulacionTransferenciaView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}
		crearHashSimularTransferenciaFondos(inView);
		TransferenciaDTO requestDTO;
		try {
			requestDTO = createDTO(inView);
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<TransferenciaDTO> respuestaBO = transferenciaFondoBO.simularTransferencia(requestDTO,
				sesionCliente.getCliente());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		SimulacionTransferenciaView viewResponse = crearSimulacionTransferenciaView(respuestaBO.getRespuesta());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			// WARNING POR FUERA DE HORARIO O IMPORTE MENOR AL MINIMO
			return respuestaFactory.crearRespuestaWarning(SimulacionTransferenciaView.class, viewResponse,
					respuestaBO.getItemsMensajeRespuesta());
		} else {
			// SI RESPUESTA OK HAGO EVALUACION DE RIESGO
			return ejecutarEvaluacionRiesgo(requestDTO, respuestaBO, viewResponse);

		}
	}

	/**
	 * Realizo la evaluacion de riesgo.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param viewResponse
	 *            the view response
	 * @return respuesta ok o erronea segun corresponda
	 */
	private Respuesta<SimulacionTransferenciaView> ejecutarEvaluacionRiesgo(TransferenciaDTO requestDTO,
			Respuesta<TransferenciaDTO> respuestaBO, SimulacionTransferenciaView viewResponse) {
		try {
			viewResponse.setLegales(respuestaBO.getRespuesta().getLegales());
			EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse = inversionDAO
					.evaluacionDeRiesgo(cargarDatosEvalRiesgo(requestDTO));
			// CARGO CABECERA Y PIE SI CORRESPONDE
			return manejarRespuestaEvaluacionRiesgo(evaluacionDeRiesgoResponse, viewResponse);
		} catch (DAOException e) {
			LOGGER.error("Servicio evaluacion de riesgo caido", e);
			return respuestaFactory.crearRespuestaWarning(viewResponse, "", TipoError.RIESGO_MEDIO,
					CodigoMensajeConstantes.SERVICIO_EVALUACION_RIESGO_CAIDO);
		}
	}

	/**
	 * carga los datos para el request de evaluacion de riesgo.
	 * 
	 * @param requestDTO
	 *            the request DTO
	 * @return the datos evaluacion riesgo
	 */
	private DatosEvaluacionRiesgo cargarDatosEvalRiesgo(TransferenciaDTO requestDTO) {
		DatosEvaluacionRiesgo parametroDatos = super.cargarDatosEvalRiesgo();

		parametroDatos.setTipoOperacion(TIPO_OPERACION_TRANSFERENCIA_FONDO);
		parametroDatos.setNroCuentaTitulo(requestDTO.getCuentaTitulo().replaceAll("/", ""));
		parametroDatos.setEspecie(requestDTO.getCodigoFondoDest());
		parametroDatos.setEspecieOrigen(requestDTO.getCodigoFondo());
		parametroDatos.setMonto(requestDTO.getImporteNeto());
		parametroDatos.setImporteDebCred(requestDTO.getImporteNeto());
		parametroDatos.setMoneda(TipoMonedaInversionEnum.fromSimboloString(requestDTO.getMoneda()).getCodigo2());

		return parametroDatos;
	}

	/**
	 * Crea el DTO para el llamado al BO a partir del view.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the simulacion in DTO
	 */
	private SimulacionInDTO createTransferenciaDTO(SimulacionInView viewRequest) {
		SimulacionInDTO simulacionDTO = new SimulacionInDTO();

		try {
			BeanUtils.copyProperties(simulacionDTO, viewRequest);
			simulacionDTO.setNroCuentaBancaPrivada(viewRequest.getNroCuentaBancaPrivada());
		} catch (Exception e) {
			LOGGER.error("Error creando DTO", e);
		}
		return simulacionDTO;
	}

	/**
	 * Creates the view.
	 *
	 * @param dto
	 *            the dto
	 * @return the simulacion transferencia out view
	 */
	private SimulacionTransferenciaView createTransferenciaView(SimularSuscripcionOutDTO dto) {
		SimulacionTransferenciaView view = new SimulacionTransferenciaView();
		try {
			BeanUtils.copyProperties(view, dto);
			view.setDentroDelPerfil(dto.getDentroDelPerfil());
			view.setDisclaimer(dto.getDisclaimer());

		} catch (Exception e) {
			LOGGER.error("Error creando VIEW", e);
		}
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimulacionTransferenciaView> simularTransferenciaBpriv(SimulacionInView viewRequest) {

		Respuesta<SimulacionInView> respuesta = super.validate(viewRequest, new SimulacionInView());
		if (!respuesta.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_VALIDACION_DATOS,
					CodigoMensajeConstantes.SUSCRIPCION_FONDO_FALLO_GENERICO);
		}

		crearHashSimularTransferenciaFondosBpriv(viewRequest);
		LOGGER.info("Invocando al BO para simulacion transferencia Banca Privada");
		Respuesta<SimularSuscripcionOutDTO> rtaBO = transferenciaFondoBO
				.simularTransferenciaBPriv(createTransferenciaDTO(viewRequest));

		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			return respuestaFactory.crearRespuestaOk(SimulacionTransferenciaView.class,
					createTransferenciaView(rtaBO.getRespuesta()));
		}

		if (rtaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			return respuestaFactory.crearRespuestaWarning(SimulacionTransferenciaView.class,
					createTransferenciaView(rtaBO.getRespuesta()), rtaBO.getItemsMensajeRespuesta());
		}

		return respuestaFactory.crearRespuestaError(SimulacionTransferenciaView.class,
				rtaBO.getItemsMensajeRespuesta().get(0).getMensaje(), rtaBO.getItemsMensajeRespuesta().get(0).getTag());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void grabarEstadisticaGastosBPriv(ConfigTransferenciaInView requestView) {
		if ("true".equals(requestView.getTieneGastos())) {
			estadisticaManager.add(EstadisticasConstants.TRANSFERENCIA_FONDO_BPRIV,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobante(DatosComprobante viewRequest) {
		ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE_TRANSFERENCIA);
		if (EstadoRespuesta.OK.equals(respuestaLegales.getEstadoRespuesta())) {
			comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());

			Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_TR_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
			estadistica.setImporte(viewRequest.getImporte());
			estadistica.setMoneda(viewRequest.getMoneda());
			estadistica.setNroCtaOrigen(viewRequest.getCuenta().replace("/", ""));
			estadistica.setNroCtaDestino(viewRequest.getCuenta().replace("/", ""));

			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());

			return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_TR_BANCA_PERSONAL,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteSuscripcionView> verComprobanteBPriv(DatosComprobante viewRequest) {
		ComprobanteSuscripcionView comprobanteSuscripcionView = new ComprobanteSuscripcionView();
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_COMPROBANTE_TRANSFERENCIA);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			comprobanteSuscripcionView.setLegales(respuestaLegales.getRespuesta());

			Estadistica estadistica = new Estadistica(EstadisticasConstants.VER_COMPROBANTE_TR_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadistica.setNroComprobante(viewRequest.getNumeroComprobante());
			estadistica.setImporte(viewRequest.getImporte());
			estadistica.setMoneda(viewRequest.getMoneda());
			estadistica.setNroCtaOrigen(viewRequest.getCuenta().replace("/", ""));
			estadistica.setNroCtaDestino(viewRequest.getCuenta().replace("/", ""));

			estadisticaManager.add(estadistica, sessionParametros.getRegistroSession(), sesionCliente.getCliente());

			return respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, comprobanteSuscripcionView);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_TR_BANCA_PRIVADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FinalizarTransferenciaFondoView> finalizarTransferenciaFondosBpriv(
			FinalizarTransferenciaFondoInView viewRequest) {

		validarHashTransferenciaFondosBpriv(viewRequest);
		FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO;
		try {
			finalizarTransferenciaBprivDTO = createTransferenciaDTOBpriv(viewRequest);

		} catch (BusinessException e) {
			LOGGER.error("Error al crear el DTO {}.", viewRequest, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, ERROR_GENERICO);
		}
		Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO = transferenciaFondoBO
				.finalizarTransferenciaBPriv(finalizarTransferenciaBprivDTO, sesionCliente.getCliente());
		if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			cargarHashDeLaVistaComprobanteBpriv(viewRequest, respuestaBO.getRespuesta());
			return crearRespuestaOkFinalizarTransferenciaBpriv(respuestaBO,
					finalizarTransferenciaBprivDTO.getCuentaTitulo());

		} else if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
			guardarEstadisticaFeedbackBpriv(finalizarTransferenciaBprivDTO,
					finalizarTransferenciaBprivDTO.getCuentaTitulo(), true);
			return respuestaFactory.crearRespuestaWarning(FinalizarTransferenciaFondoView.class,
					new FinalizarTransferenciaFondoView(), respuestaBO.getItemsMensajeRespuesta());
		}
		return crearRespuestaErrorTransferenciaFondo(respuestaBO, finalizarTransferenciaBprivDTO);
	}

	/**
	 * Creates the transferencia DTO bpriv.
	 *
	 * @param view
	 *            the view
	 * @return the finalizar transferencia bpriv DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private FinalizarTransferenciaBprivDTO createTransferenciaDTOBpriv(FinalizarTransferenciaFondoInView view)
			throws BusinessException {
		FinalizarTransferenciaBprivDTO dto = new FinalizarTransferenciaBprivDTO();
		try {
			BeanUtils.copyProperties(dto, view);
			dto.setImporte(view.getImporteNeto().replace(",", "."));
			dto.setCodigoFondo(view.getCodigoFondo());

		} catch (IllegalAccessException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		} catch (InvocationTargetException ex) {
			LOGGER.error("Error convirtiendo view en DTO. ", ex);
			throw new BusinessException();
		}
		return dto;
	}

	/**
	 * Crear respuesta ok finalizar transferencia bpriv.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @return the respuesta
	 */
	private Respuesta<FinalizarTransferenciaFondoView> crearRespuestaOkFinalizarTransferenciaBpriv(
			Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO, String cuentaTitulo) {
		guardarEstadisticaFeedbackBpriv(respuestaBO.getRespuesta(), cuentaTitulo, true);
		FinalizarTransferenciaFondoView view = crearFinalizarTransferenciaFondoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(FinalizarTransferenciaFondoView.class, view);
	}

	/**
	 * Crear finalizar transferencia fondo view.
	 *
	 * @param dto
	 *            the dto
	 * @return the finalizar transferencia fondo view
	 */
	private FinalizarTransferenciaFondoView crearFinalizarTransferenciaFondoView(FinalizarTransferenciaBprivDTO dto) {
		FinalizarTransferenciaFondoView viewReturn = new FinalizarTransferenciaFondoView();
		try {
			BeanUtils.copyProperties(viewReturn, dto);
			viewReturn.setNumeroCertificado(dto.getNroCertificado());
		} catch (Exception e) {
			LOGGER.error("Error creando DTO", e);
		}
		return viewReturn;
	}

	/**
	 * Manejo el resultado de la evalacion de riesgo.
	 *
	 * @param evaluacionDeRiesgoResponse
	 *            the evaluacion de riesgo response
	 * @param viewResponse
	 *            the view response
	 * @return the respuesta
	 */
	private Respuesta<SimulacionTransferenciaView> manejarRespuestaEvaluacionRiesgo(
			EvaluacionDeRiesgoResponse evaluacionDeRiesgoResponse, SimulacionTransferenciaView viewResponse) {
		if (evaluacionDeRiesgoResponse != null && !"0".equals(evaluacionDeRiesgoResponse.getTipoDisclaimer())
				&& evaluacionDeRiesgoResponse.getMensaje().getCantidadDeDisclaimers() > 0) {
			viewResponse.setCabecera(evaluacionDeRiesgoResponse.getCabecera());
			viewResponse.setPie(evaluacionDeRiesgoResponse.getPie());
		}
		// se setea el id evaluacion, necesario para el WS de finalizar
		// suscripcion.
		if (evaluacionDeRiesgoResponse != null) {
			sessionParametros.setIdEv(evaluacionDeRiesgoResponse.getIdEvaluacion());
		}
		if (evaluacionDeRiesgoResponse.getTipoDisclaimer().equals(INVERSOR_RIESGO_BLOQUEANTE)) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
					TipoError.RIESGO_BLOQUEANTE);

			return respuestaFactory.crearRespuestaWarning(SimulacionTransferenciaView.class, viewResponse,
					itemsMensajeRespuesta);

		} else if (evaluacionDeRiesgoResponse.getTipoDisclaimer().equals(INVERSOR_RIESGO_MEDIO)) {
			List<ItemMensajeRespuesta> itemsMensajeRespuesta = cargarMensajesPopup(evaluacionDeRiesgoResponse,
					TipoError.RIESGO_MEDIO);

			return respuestaFactory.crearRespuestaWarning(SimulacionTransferenciaView.class, viewResponse,
					itemsMensajeRespuesta);
		}
		return respuestaFactory.crearRespuestaOk(SimulacionTransferenciaView.class, viewResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.
	 * TransferenciaFondoManager#descargarComprobanteRescatePDF(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.view.
	 * ComprobanteTransferenciaFondo)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteTransferenciaPDF(ComprobanteTransferenciaFondo viewRequest) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		validarHashTransferenciaComprobante(viewRequest);
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_TRANSFERENCIA_FONDO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.COMPROBANTE_TRANSFERENCIA_FONDO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param view
	 *            the view
	 */
	private void crearHashSimularTransferenciaFondos(SimulacionTransferenciaInView view){
		String hash = HashUtils.obtenerHash(crearMapSimularTransferenciaFondos(view));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}
	
	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	 * puede cambiar desde la vista
	 *
	 * @author marcelo.ruiz
	 * @param fondoView
	 *            the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapSimularTransferenciaFondos(SimulacionTransferenciaInView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDest());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporteNeto().replaceAll("\\$", "").trim());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Validar hash flujo fondos.
	 *
	 * @param fondoView
	 *            the fondo view
	 */
	private void validarHashTransferenciaFondos(TransferenciaView fondoView) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarTransferenciaFondos(fondoView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}
	
	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param fondoView
	 *            the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarTransferenciaFondos(TransferenciaView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDest());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporteNeto().replaceAll("\\$", "").trim());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView
	 *            the in view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 */
	private void cargarHashDeLaVistaComprobante(TransferenciaView inView, TransferenciaDTO transferenciaDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobante(inView, transferenciaDTO));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}
	
	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * <P>
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	 * puede cambiar desde la vista
	 * </P>
	 *
	 * @author marcelo.ruiz
	 * @param fondoView
	 *            the fondo view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobante(TransferenciaView fondoView, TransferenciaDTO transferenciaDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDest());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo().trim());
		mapaAtributos.put("fechaHora", transferenciaDTO.getFechaHora());
		mapaAtributos.put("importe", ISBANStringUtils.formatearConComaYDosDecimales(fondoView.getImporteNeto()).replaceAll("\\$", "").trim());
		mapaAtributos.put("comprobante", transferenciaDTO.getNumeroCertificado());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Validar hash constitucion plazo fijo.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 */
	private void validarHashTransferenciaComprobante(ComprobanteTransferenciaFondo comprobanteView) {
		String inputHash = HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarRescateComprobante(comprobanteView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}
	
	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaFinalizarRescateComprobante(
			ComprobanteTransferenciaFondo comprobanteView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", comprobanteView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", comprobanteView.getCodigoFondoDest());
		mapaAtributos.put("cuentaTitulos", comprobanteView.getCuentaTitulos().trim());
		mapaAtributos.put("fechaHora", comprobanteView.getFechaActual());
		mapaAtributos.put("importe", formatearImporteHash(comprobanteView.getImporte()));
		mapaAtributos.put("comprobante", comprobanteView.getComprobante());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param view
	 *            the view
	 */
	private void crearHashSimularTransferenciaFondosBpriv(SimulacionInView view){
		String hash = HashUtils.obtenerHash(crearMapSimularTransferenciaFondosBpriv(view));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hash);
	}
	
	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	 * puede cambiar desde la vista
	 *
	 * @author marcelo.ruiz
	 * @param fondoView
	 *            the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapSimularTransferenciaFondosBpriv(SimulacionInView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDes());
		mapaAtributos.put("cuentaBancaPrivada", fondoView.getNroCuentaBancaPrivada());
		mapaAtributos.put("importe", String.valueOf(fondoView.getImporte()).replaceAll("\\$", "").trim());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Validar hash flujo fondos.
	 *
	 * @param fondoView
	 *            the fondo view
	 */
	private void validarHashTransferenciaFondosBpriv(FinalizarTransferenciaFondoInView fondoView) {
		String inputHash = HashUtils.obtenerHash(crearMapFinalizarRescateFondosBpriv(fondoView));
		LOGGER.info(MSJ_INFO_VALIDANDO_HASH, sessionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sessionParametros.getValidacionHash(), inputHash);
	}
	
	/**
	 * Crear mapa de la vista calcular intereses.
	 *
	 * @param fondoView
	 *            the fondo view
	 * @return the map
	 */
	private Map<String, Object> crearMapFinalizarRescateFondosBpriv(FinalizarTransferenciaFondoInView fondoView) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDest());
		mapaAtributos.put("cuentaBancaPrivada", fondoView.getCuentaTitulo());
		mapaAtributos.put("importe", fondoView.getImporteNeto().replaceAll("\\$", "").trim());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
	
	/**
	 * Cargar hash de la vista simulacion.
	 *
	 * @param inView
	 *            the in view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 */
	private void cargarHashDeLaVistaComprobanteBpriv(FinalizarTransferenciaFondoInView inView, FinalizarTransferenciaBprivDTO transferenciaDTO) {
		String hashView = HashUtils.obtenerHash(crearMapaDeLaVistaComprobanteBpriv(inView, transferenciaDTO));
		LOGGER.info(MSJ_INFO_GUARDANDO_HASH_EN_SESION);
		sessionParametros.setValidacionHash(hashView);
	}
	
	/**
	 * Cargar hash de la vista en sesion.
	 * 
	 * <P>
	 * Solo crea un hash de los datos estaticos, es decir, que el usuario no
	 * puede cambiar desde la vista
	 * </P>
	 *
	 * @author marcelo.ruiz
	 * @param fondoView
	 *            the fondo view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaComprobanteBpriv(FinalizarTransferenciaFondoInView fondoView, FinalizarTransferenciaBprivDTO transferenciaDTO) {
		LOGGER.info(MSJ_INFO_CREANDO_HASH_ATRIBUTOS);
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", fondoView.getCodigoFondo());
		mapaAtributos.put("codigoFondoDestino", fondoView.getCodigoFondoDest());
		mapaAtributos.put("cuentaTitulos", fondoView.getCuentaTitulo());
		mapaAtributos.put("fechaHora", transferenciaDTO.getFechaHora());
		mapaAtributos.put("importe", ISBANStringUtils.formatearConComaYDosDecimales(fondoView.getImporteNeto()).replaceAll("\\$", "").trim());
		mapaAtributos.put("comprobante", transferenciaDTO.getNroCertificado());
		LOGGER.info("String mapa vista: " + mapaAtributos.toString());
		return mapaAtributos;
	}
}
