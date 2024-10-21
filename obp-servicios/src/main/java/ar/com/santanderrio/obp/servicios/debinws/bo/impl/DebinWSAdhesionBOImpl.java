package ar.com.santanderrio.obp.servicios.debinws.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.generated.webservices.debin.Error;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestAdhesion;
import ar.com.santanderrio.obp.generated.webservices.debin.RequestConsulta;
import ar.com.santanderrio.obp.generated.webservices.debin.Response;
import ar.com.santanderrio.obp.generated.webservices.debin.ResponseVendedor;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debin.entities.ElementoComprobanteAdhesionReporte;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSAdhesionBO;
import ar.com.santanderrio.obp.servicios.debinws.common.DebinWSConstants;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSAdhesionDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ComprobanteAdhesionEntity;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class DebinWSAdhesionBOImpl.
 */
@Component
@Qualifier("debinWSAdhesionBOImpl")
public class DebinWSAdhesionBOImpl extends DebinWSBOImpl implements DebinWSAdhesionBO {

	/** The Constant CODIGOS_DE_PAQUETE_SINCERAMIENTO. */
	private static final String[] CODIGOS_DE_PAQUETE_SINCERAMIENTO = {"2011", "2016", "2017", "2018", "2019"};

	/** The Constant CUENTAS_ANSES. */
	private static final String[] CUENTAS_ANSES = {"02-0009", "02-0001"};

	/** The Constant TITULO_COMPROBANTE_ALTA. */
	private static final String TITULO_COMPROBANTE_ALTA = "Comprobante de adhesión de cuentas a DEBIN";

	/** The Constant SUBTITULO_COMPROBANTE_ALTA. */
	private static final String SUBTITULO_COMPROBANTE_ALTA = "Adhesión de cuentas a DEBIN";

	/** The Constant TITULO_COMPROBANTE_BAJA. */
	private static final String TITULO_COMPROBANTE_BAJA = "Comprobante de baja de adhesión de cuentas a DEBIN";

	/** The Constant SUBTITULO_COMPROBANTE_BAJA. */
	private static final String SUBTITULO_COMPROBANTE_BAJA = "Baja de adhesión de cuentas a DEBIN";

	/** The Constant TITULO_COMPROBANTE_MODIFICACION. */
	private static final String TITULO_COMPROBANTE_MODIFICACION = "Comprobante modificación de adhesión de cuentas a DEBIN";

	/** The Constant SUBTITULO_COMPROBANTE_MODIFICACION. */
	private static final String SUBTITULO_COMPROBANTE_MODIFICACION = "Modificación adhesión de cuentas a DEBIN";

	/** The Constant LABEL_CUENTA_PESOS. */
	private static final String LABEL_CUENTA_PESOS = "Cuenta en pesos";

	/** The Constant LABEL_CUENTA_DOLARES. */
	private static final String LABEL_CUENTA_DOLARES = "Cuenta en dólares";

	/** The Constant CUENTA. */
	private static final String CUENTA = "Cuenta";

	/** The Constant TAG_BR. */
	private static final String TAG_BR = "<br>";

	/** The mensaje manager. */
	@Autowired
	protected MensajeManager mensajeManager;

	/** The debinws adhesion DAO. */
	@Autowired
	@Qualifier("debinWSAdhesionDAOImpl")
	private DebinWSAdhesionDAO debinwsAdhesionDAO;

	/**
	 * Buscar cuentas para adhesion debines.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConsultarAdhesionDebinesView> buscarCuentasParaAdhesionDebines() {
		ConsultarAdhesionDebinesView gestionarAdhesionView = new ConsultarAdhesionDebinesView();

		ResponseVendedor responseConsulta = null;
		try {
			LOGGER.info("DebinWSBOImpl Generando request para consulta de cuentas adheridas a debin DAO");
			RequestConsulta requestConsulta = generarRequestConsultaVendedor();
			LOGGER.info("DebinWSBOImpl Iniciando llamada consulta de cuentas adheridas a debin DAO");
			responseConsulta = debinwsAdhesionDAO.consultaVendedor(requestConsulta);
			if (responseConsulta.getError() != null) {
				String codigoError = responseConsulta.getError().getCodigo();
				if (!DebinWSConstants.CONSULTA_VENDEDOR_NO_ADHERIDO_CODIGO_ERROR.equals(codigoError) && 
						!DebinWSConstants.RESPUESTA_OK_00.equals(codigoError)) {
					estadisticaManager.add(EstadisticasConstants.CONSULTAR_ADHESION_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
				}
			}
			LOGGER.info("DebinWSBOImpl Finaliza consulta de cuentas adheridas a debin respuesta OK");
			estadisticaManager.add(EstadisticasConstants.CONSULTAR_ADHESION_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
			estadisticaManager.add(EstadisticasConstants.CONSULTAR_ADHESION_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO); 
		}

		List<CuentaAdhesionDTO> cuentasPesos = new ArrayList<CuentaAdhesionDTO>();
		List<CuentaAdhesionDTO> cuentasDolares = new ArrayList<CuentaAdhesionDTO>();

		cargarListasCuentas(responseConsulta.getCuentas(), cuentasPesos, cuentasDolares);

		gestionarAdhesionView.setCuentasPesos(cuentasPesos);
		gestionarAdhesionView.setCuentasDolares(cuentasDolares);

		return respuestaFactory.crearRespuestaOk(gestionarAdhesionView);
	}

	/**
	 * Generar request consulta vendedor.
	 *
	 * @return the request consulta
	 * @throws DAOException the DAO exception
	 */
	private RequestConsulta generarRequestConsultaVendedor() throws DAOException {
		RequestConsulta request = new RequestConsulta();
		request.setCanal(CANAL);
		request.setCuit(obtenerCuitCliente());
		request.setIp(sesionCliente.getIpCliente());
		request.setNroDocumento(sesionCliente.getCliente().getDni());
		request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero());
		return request;
	}

	/**
	 * Cargar listas cuentas.
	 *
	 * @param cuentasAdheridas the cuentas adheridas
	 * @param cuentasPesos the cuentas pesos
	 * @param cuentasDolares the cuentas dolares
	 */
	private void cargarListasCuentas(final List<CuentaDebinDTO> cuentasAdheridas, List<CuentaAdhesionDTO> cuentasPesos, List<CuentaAdhesionDTO> cuentasDolares) {
		List<Cuenta> cuentasCliente = sesionCliente.getCliente().getCuentas();
		sesionParametros.setIdCuentasDebin(new HashMap<Integer, String>());
		int id = 1;
		for (int i = 0; i < cuentasCliente.size(); i++) {
			Cuenta cuentaCliente = cuentasCliente.get(i);
			CuentaAdhesionDTO cuentaAdhesion = new CuentaAdhesionDTO();
			sesionParametros.getIdCuentasDebin().put(id, cuentaCliente.getCbu());
			cuentaAdhesion.setIdCuenta(id++);
			if (contieneCuenta(cuentasAdheridas, cuentaCliente.getCbu())) {
				cuentaAdhesion.setAdherida(true);
			} else {
				cuentaAdhesion.setAdherida(false);
			}
			cuentaAdhesion.setCuentaNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
			cuentaAdhesion.setCuentaDescripcion(TipoCuenta.fromCodigo(cuentasCliente.get(i).getTipoCuenta()).getDescripcion());
			cuentaAdhesion.setCuentaAlias(cuentaCliente.getAlias());
			cuentaAdhesion.setEspecial(esCuentaEspecial(cuentaCliente));
			if (CUENTA_BIMONETARIA.equals(cuentaCliente.getTipoCuenta())) {
				cuentaAdhesion.setLabelCuenta(LABEL_CUENTA_DOLARES);
				cuentasDolares.add(cuentaAdhesion);
				cuentasPesos.add(obtenerCuentaAdhesionPesos(cuentaAdhesion));
			} else if (TransferenciaUtil.esCuentaPesos(Integer.parseInt(cuentaCliente.getTipoCuenta()))) {
				cuentaAdhesion.setLabelCuenta(LABEL_CUENTA_PESOS);
				cuentasPesos.add(cuentaAdhesion);
			} else if (TransferenciaUtil.esCuentaDolar(Integer.parseInt(cuentaCliente.getTipoCuenta()))) {
				cuentaAdhesion.setLabelCuenta(LABEL_CUENTA_DOLARES);
				cuentasDolares.add(cuentaAdhesion);
			}
		}
	}

	/**
	 * Obtener cuenta adhesion pesos.
	 *
	 * @param cuentaAdhesion the cuenta adhesion
	 * @return the cuenta adhesion DTO
	 */
	private CuentaAdhesionDTO obtenerCuentaAdhesionPesos(CuentaAdhesionDTO cuentaAdhesion) {
		CuentaAdhesionDTO retorno = new CuentaAdhesionDTO();
		retorno.setAdherida(cuentaAdhesion.isAdherida());
		retorno.setCuentaAlias(cuentaAdhesion.getCuentaAlias());
		retorno.setCuentaDescripcion(cuentaAdhesion.getCuentaDescripcion());
		retorno.setCuentaNumero(cuentaAdhesion.getCuentaNumero());
		retorno.setEspecial(cuentaAdhesion.isEspecial());
		retorno.setGestionOk(cuentaAdhesion.isGestionOk());
		retorno.setIdCuenta(cuentaAdhesion.getIdCuenta());
		retorno.setLabelCuenta(LABEL_CUENTA_PESOS);
		return retorno;
	}

	/**
	 * Es cuenta especial.
	 *
	 * @param cuentaCliente the cuenta cliente
	 * @return true, if successful
	 */
	private boolean esCuentaEspecial(Cuenta cuentaCliente) {
		if (Arrays.asList(CODIGOS_DE_PAQUETE_SINCERAMIENTO).contains(cuentaCliente.getCodigoPaquete())) {
			return true;
		}
		//puede venir una palabra en el subproducto y dentro del formateador lo convierte en Integer
		try {
			String producto = ISBANStringUtils.formateadorConCerosIzq(cuentaCliente.getProductoAltair(), 2);
			String subproducto = ISBANStringUtils.formateadorConCerosIzq(cuentaCliente.getSubproductoAltair(), 4);
			if (Arrays.asList(CUENTAS_ANSES).contains(producto + "-" + subproducto)) {
				return true;
			}
		} catch(NumberFormatException e) {
			return false;
		}
		return false;
	}

	/**
	 * Contiene cuenta.
	 *
	 * @param lista the lista
	 * @param cbu the cbu
	 * @return true, if successful
	 */
	private boolean contieneCuenta(List<CuentaDebinDTO> lista, String cbu) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCbu().equals(cbu)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gestionar adhesion debines.
	 *
	 * @param gestionAdhesionView the gestion adhesion view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<GestionarAdhesionDebinesView> gestionarAdhesionDebines(GestionarAdhesionDebinesView gestionAdhesionView) {
		GestionarAdhesionDebinesView gestionarAdhesionDebinesView = gestionAdhesionView.clone();
		int contadorFallos = 0;
		int cantCuentasAlta = 0;
		int cantCuentasBaja = 0;
		int cantidadDeOperaciones = gestionAdhesionView.getCuentasCambiadas().size();

		for (CuentaAdhesionDTO cuenta : gestionarAdhesionDebinesView.getCuentasCambiadas()) {
			String cbu = sesionParametros.getIdCuentasDebin().get(cuenta.getIdCuenta());
			Error error = null;
			try {
				//flag seteado en el frontend para las cuentas que son para adherir
				if (cuenta.isAdherida()) {
					RequestAdhesion requestAdhesion = generarRequestAdhesion(cbu);
					Response responseAdhesion = debinwsAdhesionDAO.adhesionVendedor(requestAdhesion);
					error = responseAdhesion.getError();
					cantCuentasAlta++;
				} else {
					RequestAdhesion requestBaja = generarRequestAdhesion(cbu);
					Response responseBaja = debinwsAdhesionDAO.bajaCuentaVendedor(requestBaja);
					error = responseBaja.getError();
					cantCuentasBaja++;
				}
				cuenta.setGestionOk(true);
				if (error != null && !CODIGO_DEBIN_OK.equals(error.getCodigo())) { 
					//mensajes 63 09 97 99 pendiente a definir
					estadisticaManager.add(EstadisticasConstants.GESTIONAR_CUENTAS_DEBINWS,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					cuenta.setGestionOk(false);
					contadorFallos++;
				}
			} catch(DAOException e) {
				cuenta.setGestionOk(false);
				contadorFallos++;
				if (CODIGO_ERROR_TIME_OUT.equals(e.getErrorCode())) {
					estadisticaManager.add(EstadisticasConstants.GESTIONAR_CUENTAS_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
				} else {
					estadisticaManager.add(EstadisticasConstants.GESTIONAR_CUENTAS_DEBINWS, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				}
			}
		}
		String mensajeFeedback = obtenerMensajeFeedback(contadorFallos,cantidadDeOperaciones,cantCuentasBaja,cantCuentasAlta,gestionarAdhesionDebinesView);
		return obtenerRespuestaAdhesion(gestionarAdhesionDebinesView,contadorFallos,cantidadDeOperaciones,mensajeFeedback);
	}

	/**
	 * Obtener mensaje feedback.
	 *
	 * @param contadorFallos the contador fallos
	 * @param cantidadDeOperaciones the cantidad de operaciones
	 * @param cantCuentasBaja the cant cuentas baja
	 * @param cantCuentasAlta the cant cuentas alta
	 * @param gestionarAdhesionDebinesView the gestionar adhesion debines view
	 * @return the string
	 */
	private String obtenerMensajeFeedback(int contadorFallos, int cantidadDeOperaciones, int cantCuentasBaja,
			int cantCuentasAlta, GestionarAdhesionDebinesView gestionarAdhesionDebinesView) {
		if (contadorFallos == cantidadDeOperaciones) {
			if (cantCuentasAlta == cantidadDeOperaciones) {
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_ERROR_TOTAL).getMensaje();        		
			} else if (cantCuentasBaja == cantidadDeOperaciones) {
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_BAJA_ERROR_TOTAL).getMensaje();
			} else{
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_MODIFICACION_ERROR_TOTAL).getMensaje();
			}
		} else if (contadorFallos == 0) {
			if (cantCuentasAlta == cantidadDeOperaciones) {
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_ALTA);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_ALTA);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_OK).getMensaje();
			} else if (cantCuentasBaja == cantidadDeOperaciones) {
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_BAJA);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_BAJA);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_BAJA_OK).getMensaje();
			} else{
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_MODIFICACION);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_MODIFICACION);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_MODIFICACION_OK).getMensaje();
			}
		} else {
			int totalOperaciones = cantCuentasAlta + cantCuentasBaja;
			if (totalOperaciones == cantCuentasAlta) {
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_ALTA);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_ALTA);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_ERROR_PARCIAL).getMensaje();
			} else if (totalOperaciones == cantCuentasBaja) {
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_BAJA);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_BAJA);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_BAJA_ERROR_PARCIAL).getMensaje();
			} else {
				gestionarAdhesionDebinesView.setTituloComprobante(TITULO_COMPROBANTE_MODIFICACION);
				gestionarAdhesionDebinesView.setSubtituloComprobante(SUBTITULO_COMPROBANTE_MODIFICACION);
				return mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINWS_ADHESION_MODIFICACION_ERROR_PARCIAL).getMensaje();        		
			}
		}
	}

	/**
	 * Obtener respuesta adhesion.
	 *
	 * @param gestionarAdhesionDebinesView the gestionar adhesion debines view
	 * @param contadorFallos the contador fallos
	 * @param cantidadDeOperaciones the cantidad de operaciones
	 * @param mensajeFeedback the mensaje feedback
	 * @return the respuesta
	 */
	private Respuesta<GestionarAdhesionDebinesView> obtenerRespuestaAdhesion(GestionarAdhesionDebinesView gestionarAdhesionDebinesView, int contadorFallos, int cantidadDeOperaciones, String mensajeFeedback) {
		Date fechaComprobante = new Date();
		gestionarAdhesionDebinesView.setFechaComprobante(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		gestionarAdhesionDebinesView.setNumeroComprobante(obtenerNumeroComprobante(fechaComprobante));
		gestionarAdhesionDebinesView.setMensajeFeedback(mensajeFeedback);
		sesionParametros.setGestionarAdhesionDebinesView(gestionarAdhesionDebinesView);
		if (contadorFallos == cantidadDeOperaciones) {
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeFeedback, TipoError.DEBINWS_ERROR_ADHESION_COMPRADOR.getDescripcion());
		} else if (contadorFallos == 0) {
			return respuestaFactory.crearRespuestaOk(gestionarAdhesionDebinesView);
		} else {
			gestionarAdhesionDebinesView.setFeedbackParcial(true);
			return respuestaFactory.crearRespuestaOk(gestionarAdhesionDebinesView);
		}        
	}

	/**
	 * Generar request adhesion.
	 *
	 * @param cbu the cbu
	 * @return the request adhesion
	 * @throws DAOException the DAO exception
	 */
	private RequestAdhesion generarRequestAdhesion(String cbu) throws DAOException {
		RequestAdhesion request = new RequestAdhesion();
		request.setCanal(CANAL);
		request.setCuit(obtenerCuitCliente());
		request.setIp(sesionCliente.getIpCliente());
		request.setNroDocumento(sesionCliente.getCliente().getDni());
		request.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero());
		request.setCuenta(new CuentaDebinDTO());
		request.getCuenta().setCbu(cbu);
		return request;
	}

	/**
	 * Descargar comprobante adhesion.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Reporte> descargarComprobanteAdhesion() {
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		ComprobanteAdhesionEntity comprobanteAdhesionEntity = crearRequestComprobanteAdhesionEntity();
		Reporte reporte = debinwsAdhesionDAO.generarComprobante(comprobanteAdhesionEntity);
		respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaReporte.setRespuesta(reporte);
		return respuestaReporte;
	}

	/**
	 * Crear request comprobante adhesion entity.
	 *
	 * @return the comprobante adhesion entity
	 */
	private ComprobanteAdhesionEntity crearRequestComprobanteAdhesionEntity() {
		ComprobanteAdhesionEntity comprobanteAdhesionEntity = new ComprobanteAdhesionEntity();
		comprobanteAdhesionEntity.setFecha(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		List<ElementoComprobanteAdhesionReporte> cuentasAdhesion = new ArrayList<ElementoComprobanteAdhesionReporte>();
		List<ElementoComprobanteAdhesionReporte> cuentasBaja = new ArrayList<ElementoComprobanteAdhesionReporte>();
		GestionarAdhesionDebinesView gestionarAdhesionDebinesView = sesionParametros.getGestionarAdhesionDebinesView();
		comprobanteAdhesionEntity.setSubtitulo(gestionarAdhesionDebinesView.getSubtituloComprobante());
		comprobanteAdhesionEntity.setTitulo(gestionarAdhesionDebinesView.getTituloComprobante());
		comprobanteAdhesionEntity.setNumeroComprobante(gestionarAdhesionDebinesView.getNumeroComprobante());
		for (CuentaAdhesionDTO cuentaAdhesionDTO : gestionarAdhesionDebinesView.getCuentasCambiadas()) {
			String aliasNumeroCuenta = cuentaAdhesionDTO.getCuentaNumero();
			if (!StringUtils.isBlank(cuentaAdhesionDTO.getCuentaAlias())) {
				aliasNumeroCuenta = CUENTA +" \"" + cuentaAdhesionDTO.getCuentaAlias() + "\"" + TAG_BR +cuentaAdhesionDTO.getCuentaNumero();
			}
			//cuentas de alta
			if (cuentaAdhesionDTO.isGestionOk() && cuentaAdhesionDTO.isAdherida()) {
				cuentasAdhesion.add(new ElementoComprobanteAdhesionReporte(cuentaAdhesionDTO.getLabelCuenta(), cuentaAdhesionDTO.getCuentaDescripcion(), aliasNumeroCuenta));
			//cuentas de baja
			} else if (cuentaAdhesionDTO.isGestionOk() && !cuentaAdhesionDTO.isAdherida()) {
				cuentasBaja.add(new ElementoComprobanteAdhesionReporte(cuentaAdhesionDTO.getLabelCuenta(), cuentaAdhesionDTO.getCuentaDescripcion(), aliasNumeroCuenta));
			}
		}  
		comprobanteAdhesionEntity.setCuentasAdhesion(cuentasAdhesion);
		comprobanteAdhesionEntity.setCuentasBaja(cuentasBaja);	
		return comprobanteAdhesionEntity;
	}

}
