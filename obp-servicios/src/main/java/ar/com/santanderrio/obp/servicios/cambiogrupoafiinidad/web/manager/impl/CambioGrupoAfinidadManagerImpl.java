/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.bo.CambioGrupoAfinidadBO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaComprobanteDTO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto.TarjetaAsociadaDTO;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.AltaSolicitudAdhesionView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ComprobanteSolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConsultaGrupoAfinidadView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.SolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CambioGrupoAfinidadManagerImpl.
 */
@Component
public class CambioGrupoAfinidadManagerImpl implements CambioGrupoAfinidadManager {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioGrupoAfinidadManagerImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;

	/** The cambio grupo afinidad BO. */
	@Autowired
	private CambioGrupoAfinidadBO cambioGrupoAfinidadBO;

	/** The Constant AADVANTAGE. */
	private static final String AADVANTAGE = "AADVANTAGE";

	/** The Constant SUPERCLUB. */
	private static final String SUPERCLUB = "SUPERCLUB";

	/** The Constant FEEDBACK_ADVANTAGE. */
	private static final String FEEDBACK_ADVANTAGE = "AAdvantage®";

	/** The Constant FEEDBACK_SUPERCLUB. */
	private static final String FEEDBACK_SUPERCLUB = "SuperClub";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.
	 * CambioGrupoAfinidadManager#consultarGrupoAfinidad()
	 */
	public Respuesta<ConsultaGrupoAfinidadView> consultarGrupoAfinidad() {
		LOGGER.info("Se consulta el grupo afinidad del cliente");
		Respuesta<ConsultaGrupoAfinidadView> respuestaFinal;
		ConsultaGrupoAfinidadView outView = new ConsultaGrupoAfinidadView();

		if (isUsuarioAdvantage()) {
			outView.setGrupoAfinidadCliente(AADVANTAGE);
			respuestaFinal = respuestaFactory.crearRespuestaOk(outView);
		} else {
			outView.setGrupoAfinidadCliente(SUPERCLUB);
			respuestaFinal = respuestaFactory.crearRespuestaOk(outView);
		}

		return respuestaFinal;
	}

	/**
	 * Checks if is usuario advantage.
	 *
	 * @return true, if is usuario advantage
	 */
	private boolean isUsuarioAdvantage() {
		return sesionParametros.getNumeroSocioAadvantage() != null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.
	 * CambioGrupoAfinidadManager#obtenerDatosInicialesSolicitud()
	 */
	public Respuesta<SolicitudCambioAfinidadView> obtenerDatosInicialesSolicitud() {
		LOGGER.info(
				"Se obtienen datos iniciales para la solicitud del grupo de afinidad - CambioGrupoAfinidadManagerImpl");
		Respuesta<SolicitudCambioAfinidadView> respuestaFinal = new Respuesta<SolicitudCambioAfinidadView>();
		SolicitudCambioAfinidadView datosIniciales = new SolicitudCambioAfinidadView();

		boolean isUsuarioAadvantage = false;
		if (isUsuarioAdvantage()) {
			isUsuarioAadvantage = true;
		}

		if (sesionParametros.getSolucitudCambioAfinidadRealizada() != null
				&& sesionParametros.getSolucitudCambioAfinidadRealizada()) {
			List<String> mensajeParam = Arrays.asList(isUsuarioAadvantage ? FEEDBACK_SUPERCLUB : FEEDBACK_ADVANTAGE);
			datosIniciales.setMensaje(String.format(mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_AFINIDAD_WARNING).getMensaje(),
					mensajeParam.get(0)));

			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.WARNING);
			respuestaFinal.setRespuesta(datosIniciales);
			return respuestaFinal;
		}

		Cliente cliente = obtenerCliente();
		Respuesta<List<Cuenta>> cuentasTarjetasCredito = cambioGrupoAfinidadBO
				.obtenerCuentasTarjetasCredito(isUsuarioAadvantage);
		Respuesta<List<TarjetaAsociadaDTO>> tarjetasAsociadas = new Respuesta<List<TarjetaAsociadaDTO>>();
		if (EstadoRespuesta.OK.equals(cuentasTarjetasCredito.getEstadoRespuesta())
				&& cuentasTarjetasCredito.getRespuesta().size() > 0) {
			tarjetasAsociadas = cambioGrupoAfinidadBO.buildTarjetasAsociadas(cuentasTarjetasCredito.getRespuesta(),
					cliente);
			if (EstadoRespuesta.OK.equals(tarjetasAsociadas.getEstadoRespuesta())) {
				datosIniciales.setTarjetasAsociadas(tarjetasAsociadas.getRespuesta());

				if (!isUsuarioAadvantage) {
					datosIniciales.setInfoCabecera(
							this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_INFO_CABECERA_SOLICITUD));
				}

				datosIniciales.setInfoPie(this.obtenerInfoPie(isUsuarioAadvantage));
				datosIniciales.setTyc(this.obtenerLegalesTyc());
				respuestaFinal = respuestaFactory.crearRespuestaOk(datosIniciales);
			} else {
				throw new RobotException(new Throwable());
			}
		} else {
			throw new RobotException(new Throwable());
		}

		LOGGER.info("Retornando respuesta del manager con datos iniciales para cambio de grupo de afinidad");
		return respuestaFinal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.
	 * CambioGrupoAfinidadManager#altaSolicitudAdhesion(java.lang.String)
	 */
	public Respuesta<AltaSolicitudAdhesionView> altaSolicitudAdhesion(String nroSocioAdvantage) {

		boolean isUsuarioAdvantage = this.isUsuarioAdvantage();

		Respuesta<String> respuestaBO = cambioGrupoAfinidadBO.altaSolicitudAdhesion(nroSocioAdvantage,
				obtenerCliente().getNup(), isUsuarioAdvantage);
		Respuesta<AltaSolicitudAdhesionView> respuestaFinal = new Respuesta<AltaSolicitudAdhesionView>();
		AltaSolicitudAdhesionView respuestaManager = new AltaSolicitudAdhesionView();

		List<String> mensajeParam = Arrays.asList(isUsuarioAdvantage ? FEEDBACK_SUPERCLUB : FEEDBACK_ADVANTAGE);
		String mensajeRespuesta;

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())
				&& !ISBANStringUtils.isEmptyOrNull(respuestaBO.getRespuesta())) {
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaManager.setNumeroGestion(respuestaBO.getRespuesta());

			if (isUsuarioAdvantage) {
				respuestaManager
						.setInfoPie(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_INFO_PIE_COMPROBANTE));
			} else {
				respuestaManager
						.setInfoPie(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_FEEDBACK_CAMBIO_OK));
			}

			respuestaManager.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
			sesionParametros.setSolucitudCambioAfinidadRealizada(true);
			mensajeRespuesta = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_AFINIDAD_FEEDBACK_CAMBIO_OK).getMensaje();

			this.guardarDatosComprobante(respuestaBO.getRespuesta(), isUsuarioAdvantage, nroSocioAdvantage);
			sesionParametros.setSolucitudCambioAfinidadRealizada(true);
			// SE AGREGA ESTADISTICA 11648 14_04_2018
			estadisticaManager.add(EstadisticasConstants.CAMBIO_GRUPO_AFINIDAD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			// SE AGREGA ESTADISTICA 11648 14_04_2018
			estadisticaManager.add(EstadisticasConstants.CAMBIO_GRUPO_AFINIDAD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
			mensajeRespuesta = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_AFINIDAD_FEEDBACK_CAMBIO_ERROR)
					.getMensaje();
			respuestaFinal.setRespuesta(respuestaManager);
			return respuestaFactory.crearRespuestaError(AltaSolicitudAdhesionView.class,
					String.format(mensajeRespuesta, mensajeParam.toArray()), null);
		}

		respuestaManager.setMensajeFeedback(String.format(mensajeRespuesta, mensajeParam.toArray()));
		respuestaFinal.setRespuesta(respuestaManager);
		return respuestaFinal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.
	 * CambioGrupoAfinidadManager#generarComprobanteCambioGrupoAfinidad()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteCambioGrupoAfinidad() {
		ComprobanteSolicitudCambioAfinidadView datosComprobante = new ComprobanteSolicitudCambioAfinidadView();
		datosComprobante = sesionParametros.getComprobanteSolicitudCambioAfinidadView();

		Respuesta<Reporte> reporteRespuesta = cambioGrupoAfinidadBO
				.generarComprobanteCambioGrupoAfinidad(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		return respuestaView;
	}

	/**
	 * Guardar datos comprobante.
	 *
	 * @param numeroGestion      the numero gestion
	 * @param isUsuarioAdvantage the is usuario advantage
	 * @param nroSocioAdvantage  the nro socio advantage
	 */
	private void guardarDatosComprobante(String numeroGestion, boolean isUsuarioAdvantage, String nroSocioAdvantage) {

		ComprobanteSolicitudCambioAfinidadView comprobante = new ComprobanteSolicitudCambioAfinidadView();
		Respuesta<List<Cuenta>> cuentasTarjetasCredito = cambioGrupoAfinidadBO
				.obtenerCuentasTarjetasCredito(isUsuarioAdvantage);
		Respuesta<List<TarjetaAsociadaDTO>> tarjetasAsociadas = new Respuesta<List<TarjetaAsociadaDTO>>();

		if (EstadoRespuesta.OK.equals(cuentasTarjetasCredito.getEstadoRespuesta())
				&& cuentasTarjetasCredito.getEstadoRespuesta() != null) {
			tarjetasAsociadas = cambioGrupoAfinidadBO.buildTarjetasAsociadas(cuentasTarjetasCredito.getRespuesta(),
					this.obtenerCliente());
			comprobante.setTarjetasAsociadas(this.procesarTarjetasComprobante(tarjetasAsociadas.getRespuesta()));
		}

		comprobante.setNumeroGestion(numeroGestion);

		// Cuando el usuario no ingresa el numero de socio
		String nroSocio = !isUsuarioAdvantage && nroSocioAdvantage == null ? StringUtils.EMPTY : nroSocioAdvantage;
		comprobante.setNroSocioAdvantage(nroSocio);
		comprobante.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		comprobante.setInfoPie(this.obtenerInfoPie(isUsuarioAdvantage));
		sesionParametros.setComprobanteSolicitudCambioAfinidadView(comprobante);
	}

	/**
	 * Procesar tarjetas comprobante.
	 *
	 * @param tarjetas the tarjetas
	 * @return the list
	 */
	private List<TarjetaAsociadaComprobanteDTO> procesarTarjetasComprobante(List<TarjetaAsociadaDTO> tarjetas) {

		List<TarjetaAsociadaComprobanteDTO> listaComprobante = new ArrayList<TarjetaAsociadaComprobanteDTO>();

		for (TarjetaAsociadaDTO tarjeta : tarjetas) {
			TarjetaAsociadaComprobanteDTO tarjetaComprobante = new TarjetaAsociadaComprobanteDTO();
			tarjetaComprobante.setTipoNumero(tarjeta.getTipoTarjeta().concat(ISBANStringUtils.ESPACIO_STRING)
					.concat(tarjeta.getNumeroFormateado()));
			tarjetaComprobante.setTitular("Titular: ".concat(tarjeta.getTitular()));
			listaComprobante.add(tarjetaComprobante);
		}
		return listaComprobante;
	}

	/**
	 * Obtener cliente.
	 *
	 * @return the cliente
	 */
	private Cliente obtenerCliente() {
		Cliente cliente = sesionCliente.getCliente();
		return cliente;
	}

	/**
	 * Obtener legales.
	 *
	 * @param codigoMensaje the codigo mensaje
	 * @return the string
	 */
	private String obtenerLegales(String codigoMensaje) {
		try {
			return legalBO.obtenerLegal(codigoMensaje);
		} catch (DAOException e) {
			LOGGER.error("Falla al obtenr legales solicitud cambio grupo afinidad.");
			return StringUtils.EMPTY;
		}
	}

	/**
	 * Obtener info pie.
	 *
	 * @param isAfinidadAadvantage the is afinidad aadvantage
	 * @return the string
	 */
	private String obtenerInfoPie(boolean isAfinidadAadvantage) {
		return this.obtenerLegales(
				isAfinidadAadvantage ? CodigoMensajeConstantes.CAMBIO_AFINIDAD_INFO_PIE_SOLICITUD_SUPERCLUB
						: CodigoMensajeConstantes.CAMBIO_AFINIDAD_INFO_PIE_SOLICITUD_ADVANTAGE);
	}

	/**
	 * Obtener legales tyc.
	 *
	 * @return the string
	 */
	private String obtenerLegalesTyc() {

		StringBuilder mensajeBuilder = new StringBuilder();
		mensajeBuilder
				.append(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_TERMINOS_Y_CONDICIONES_PARTE1));
		mensajeBuilder
				.append(this.obtenerLegales(CodigoMensajeConstantes.CAMBIO_AFINIDAD_TERMINOS_Y_CONDICIONES_PARTE2));
		return mensajeBuilder.toString();
	}

	@Override
	public Respuesta<String> obtenerGrupoAfinidadParaFlujos() {
		return cambioGrupoAfinidadBO.obtenerGrupoAfinidadParaFlujos(sesionParametros.getNumeroSocioAadvantage(),
				sesionParametros.getOfertasComerciales());
	}

}
