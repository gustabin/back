/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.bo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO;
import ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.PreguntaSeguridadEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.RespuestaSeguridadEntity;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;

/**
 * Clase Perfil Alta/Modificacion PreguntasSeguridadBOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class PreguntasSeguridadBOImpl implements PreguntasSeguridadBO {

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The datos preguntasSeguridadDAO. */
	@Autowired
	private PreguntasSeguridadPerfilDAO preguntasSeguridadPerfilDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PreguntasSeguridadBOImpl.class);

	/** C el usuario ya tiene preguntas cargadas. */
	private static final String PREGUNTAS_CARGADAS = "C";

	/** NO_RECUPERO_RESPUESTAS. */
	private static final String NO_RECUPERO_RESPUESTAS = "N";

	/** The Constant MARCA_ANPH. */
	private static final String MARCA_ANPH = "T";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO#
	 * consultarPreguntasSeguridad()
	 */
	@Override
	public Respuesta<PreguntasSeguridadView> consultarPreguntasSeguridad() {

		LOGGER.info("PreguntasSeguridadBOImpl _ Iniciando metodo consultarPreguntasSeguridad ");
		Cliente cliente = sesionCliente.getCliente();
		if (cliente.getMarcaANPH().equals(MARCA_ANPH)) {
			return this.respuestaFactory.crearRespuestaError("", TipoError.PREGUNTAS_SEGURIDAD_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_PREGUNTAS_SEGURIDAD_GENERICO);
		}
		ConsultaPreguntasSeguridadInEntity consultaPreguntasSeguridadInEntity = new ConsultaPreguntasSeguridadInEntity();
		consultaPreguntasSeguridadInEntity.setCliente(cliente);
		consultaPreguntasSeguridadInEntity.setRecuperarRespuestas(NO_RECUPERO_RESPUESTAS);
		List<PreguntaSeguridadView> listaPreguntasView = new ArrayList<PreguntaSeguridadView>();
		PreguntasSeguridadView preguntasView = new PreguntasSeguridadView();
		try {
			LOGGER.debug("PreguntasSeguridadBOImpl _ Iniciando llamada a preguntasSeguridadDAO ");
			ConsultaPreguntasSeguridadOutEntity consultaPreguntasSeguridadOutEntity = preguntasSeguridadPerfilDAO
					.consultaPreguntasSeguridad(consultaPreguntasSeguridadInEntity);
			LOGGER.debug("PreguntasSeguridadBOImpl _ Finalizando llamada a preguntasSeguridadDAO ");
			if (consultaPreguntasSeguridadOutEntity.getEstado() != null) {
				preguntasView.setTienePreguntasCargadas(
						consultaPreguntasSeguridadOutEntity.getEstado().trim().equals(PREGUNTAS_CARGADAS));
			} else {
				preguntasView.setTienePreguntasCargadas(false);
			}
			if (consultaPreguntasSeguridadOutEntity.getPreguntasSeguridad() != null) {
				for (Iterator<PreguntaSeguridadEntity> iterator = consultaPreguntasSeguridadOutEntity
						.getPreguntasSeguridad().iterator(); iterator.hasNext();) {
					PreguntaSeguridadEntity preguntaEntity = iterator.next();
					PreguntaSeguridadView preguntaView = new PreguntaSeguridadView();
					preguntaView.setDescripcion(preguntaEntity.getDescripcionPregunta());
					preguntaView.setId(preguntaEntity.getIdPregunta());
					listaPreguntasView.add(preguntaView);
				}
			}

		} catch (DAOException e) {
			LOGGER.error("PreguntasSeguridadBOImpl", e);
			return this.respuestaFactory.crearRespuestaError("", TipoError.PREGUNTAS_SEGURIDAD_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_PREGUNTAS_SEGURIDAD_GENERICO);
		}
		LOGGER.debug("PreguntasSeguridadBOImpl _ Finalizando llamada a consultarPreguntasSeguridad ");
		preguntasView.setListaPreguntas(listaPreguntasView);
		return this.respuestaFactory.crearRespuestaOk(preguntasView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO#
	 * guardarPreguntasSeguridad(java.util.List)
	 */
	@Override
	public Respuesta<ModificacionPreguntasSeguridadOutEntity> guardarPreguntasSeguridad(
			List<PreguntaSeguridadView> preguntasSeguridadView) {
		ModificacionPreguntasSeguridadOutEntity modificacionPreguntasSeguridadOutEntity;
		LOGGER.info("PreguntasSeguridadBOImpl _ Iniciando metodo guardarPreguntasSeguridad ");
		Cliente cliente = sesionCliente.getCliente();
		ModificacionPreguntasSeguridadInEntity modificacionPreguntasSeguridadInEntity = new ModificacionPreguntasSeguridadInEntity();
		modificacionPreguntasSeguridadInEntity.setCliente(cliente);
		List<RespuestaSeguridadEntity> listaRespuestas = new ArrayList<RespuestaSeguridadEntity>();
		for (Iterator<PreguntaSeguridadView> iterator = preguntasSeguridadView.iterator(); iterator.hasNext();) {
			PreguntaSeguridadView preguntaView = iterator.next();
			RespuestaSeguridadEntity respuestaEntity = new RespuestaSeguridadEntity();
			respuestaEntity.setIdPregunta(preguntaView.getId());
			respuestaEntity.setDescripcionRespuesta(preguntaView.getRespuesta());
			listaRespuestas.add(respuestaEntity);
		}
		modificacionPreguntasSeguridadInEntity.setRespuestasSeguridad(listaRespuestas);
		modificacionPreguntasSeguridadInEntity.setCantidadPreguntas(String.valueOf(listaRespuestas.size()));

		try {
			LOGGER.debug("PreguntasSeguridadBOImpl _ Iniciando llamada a preguntasSeguridadDAO ");
			modificacionPreguntasSeguridadOutEntity = preguntasSeguridadPerfilDAO
					.guardarPreguntasSeguridad(modificacionPreguntasSeguridadInEntity);
			LOGGER.debug("PreguntasSeguridadBOImpl _ Finalizando llamada a preguntasSeguridadDAO ");

		} catch (DAOException e) {
			LOGGER.error("PreguntasSeguridadBOImpl", e);
			return this.respuestaFactory.crearRespuestaError("", TipoError.PREGUNTAS_SEGURIDAD_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_PREGUNTAS_SEGURIDAD_GENERICO);
		}
		LOGGER.debug("PreguntasSeguridadBOImpl _ Finalizando llamada a consultarPreguntasSeguridad ");
		return this.respuestaFactory.crearRespuestaOk(modificacionPreguntasSeguridadOutEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.perfil.bo.PreguntasSeguridadBO#
	 * descargarComprobante(ar.com.santanderrio.obp.servicios.perfil.entities.
	 * DatosComprobanteEntity)
	 */
	@Override
	public Respuesta<Reporte> descargarComprobante(DatosComprobanteEntity datos) {
		LOGGER.debug("PreguntasSeguridadBOImpl _ iniciando descargarComprobante");
		Respuesta<Reporte> rtaReporte = new Respuesta<Reporte>();
		try {
			Reporte reporte = preguntasSeguridadPerfilDAO.descargarComprobante(datos);
			rtaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			rtaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(e.getMessage());
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			rtaReporte.setEstadoRespuesta(EstadoRespuesta.ERROR);
			rtaReporte.add(itemMensajeRespuesta);
		}
		LOGGER.debug("PreguntasSeguridadBOImpl _ finalizando descargarComprobante");
		return rtaReporte;
	}
}
