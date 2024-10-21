/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.bo.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clave.online.excepciones.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.AltaSGIClaveDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ClaveOnlineDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.PreguntasSeguridadDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidacionPinBanelcoDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidacionRespuestasDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidadorClaveDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.FuncionEnum;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridadResponse;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ProductoEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.RespuestaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.RespuestaEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.SeleccionTarjetaBanelcoInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.FormatCrUtil;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class LoginBOImpl.
 */
@Component
public class ClaveOnlineBOImpl implements ClaveOnlineBO {

    /** The Constant LETRA_A. */
    private static final String LETRA_A = "A";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaveOnlineBOImpl.class);

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "ddMMyyyy";
    
    private static final String FUNCION_SELECCION_TARJETA_BANELCO = "01";

    /** The preguntasSeguridad DAO. */
    @Autowired
    private PreguntasSeguridadDAO preguntasSeguridadDAO;

    /** The validacion respuestas DAO. */
    @Autowired
    private ValidacionRespuestasDAO validacionRespuestasDAO;

    /** The ClaveOnlineDAO. */
    @Autowired
    private ClaveOnlineDAO claveOnlineDAO;

    /** * The AltaSGIClaveDAO. */

    @Autowired
    private AltaSGIClaveDAO altaSGIClaveDAO;

    /** The Respuesta Factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    EstadisticaManager estadisticaManager;

    /** The validador clave DAO. */
    @Autowired
    ValidadorClaveDAO validadorClaveDAO;
    
    @Autowired
    ValidacionPinBanelcoDAO validacionPinBanelcoDAO;

    /** The registro sesion. */
    @Autowired
    RegistroSesion registroSesion;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

    @Autowired
    SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.bo.LoginBO#obtenerInicioLogin()
     */
    @Override
    public Respuesta<IdentificadorClienteOutEntity> confirmarDatos(
            IdentificadorClienteInEntity identificadorClienteInEntity) {
        Respuesta<IdentificadorClienteOutEntity> respuesta = respuestaFactory
                .crearRespuestaOk(IdentificadorClienteOutEntity.class);
        try {
            IdentificadorClienteOutEntity identificadorClienteOutEntity = claveOnlineDAO
                    .identificarCliente(identificadorClienteInEntity);
            respuesta.setRespuesta(identificadorClienteOutEntity);

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.TIMEOUT,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        } catch (DniInvalidoException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_NO_HEMOS_PODIDO_IDENTIFICARTE,
                    CodigoMensajeConstantes.MENSAJE_ERROR_LOGIN_DATOS_INCORRECTOS);
        } catch (ClienteBloqueadoException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.CLAVE_ONLINE_CLIENTE_BLOQUEADO,
                    CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_24_HORAS);
        } catch (ClienteBloqueado2Exception e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.CLAVE_ONLINE_CLIENTE_BLOQUEADO,
                    CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_SEMANA);
        } catch (ClienteSinonimoException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.DOCUMENTO_HOMONIMO, "");
        } catch (ClienteSinAutValidoException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CTE_SIN_MEDIO_AUTH_VALIDO,
                    CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_SIN_AUTENTICACION_VALIDA);
        } catch (ClaveOnlineNoEsPersonaFisicaException e) {
        	  LOGGER.error(e.getMessage(), e);
              respuesta = respuestaFactory.crearRespuestaError(null, TipoError.NO_ES_PERSONA_FISICA
            		  ,CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_NO_PERSONA_FISICA);
		} catch (IpBloqueadaException e) {
			  LOGGER.error(e.getMessage(), e);
              respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_IP_BLOQUEADA,
                      CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_IP_BLOQUEADA);
		} catch (SistemaClaveNoDisponibleException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIST_CLAVE_NO_DISPONIBLE,
                     CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_SISTEMA_AUTEGESTION_CLAVE_BLOQUEADO);
		} catch (CLienteSinContratoException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CLIENTE_SIN_CONTRATO,
                     CodigoMensajeConstantes.CLIENTE_SIN_CONTRATO);		
		} catch (SinTarjetaValidaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIN_TARJETA_DEBITO_VALIDA,
                     CodigoMensajeConstantes.SIN_TARJETA_DEBITO_VALIDA);	
		} catch (SinCelularRegistradoException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIN_CEL_REGISTRADO,
                     CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_CEL_NO_REGISTRADO);	
		} catch (AutenticarTarjetaDebitoException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_TARJ_DEBITO,
                     CodigoMensajeConstantes.ERROR_AUTENTICACION_TARJ_DEBITO);
		} catch (AutenticacionPinInactivaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_PIN_INACTIVA,
                     CodigoMensajeConstantes.ERROR_AUTENTICACION_PIN_INACTIVA);
		} catch (AutenticacionSmsOtpInactivaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_SMS_OTP_INACTIVA,
                     CodigoMensajeConstantes.ERROR_AUTENTICACION_SMS_OTP_INACTIVA);
		} catch (AutenticacionCvv2InactivaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_CVV2_INACTIVA,
                     CodigoMensajeConstantes.ERROR_AUTENTICACION_CVV2_INACTIVA);
		} catch (ErrorRutinaFechasException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS,
                     CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorModuloException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO,
                     CodigoMensajeConstantes.ERROR_MODULO);
		} catch (ErrorDb2Exception e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2,
                     CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (FuncionInvalidaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA,
                     CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorCicsException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS,
                     CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_HORARIO_NOCTURNO);
		}
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO#
     * obtenerPreguntasSeguridad(ar.com.santanderrio.obp.servicios.clave.online.
     * entities.CredencialesClaveOnline,
     * ar.com.santanderrio.obp.servicios.clave.online.entities.
     * IdentificadorClienteOutEntity)
     */
    @Override
    public Respuesta<PreguntasSeguridad> obtenerPreguntasSeguridad(CredencialesClaveOnline credencialesClaveOnline,
            IdentificadorClienteOutEntity identificacion) {

        IdentificadorClienteInEntity identificadorCliente = new IdentificadorClienteInEntity();

        identificadorCliente.setDni(credencialesClaveOnline.getDniOri());
        identificadorCliente.setNup(identificacion.getNup());
        identificadorCliente.setIdSesion(identificacion.getSesion());
        identificadorCliente.setCiclo(credencialesClaveOnline.getCiclo());
        identificadorCliente.setCantidadOcurrencias(identificacion.getCantidadOcurrencias());
        identificadorCliente.setFechaNacimiento(
                FechaUtils.parsearFechaNacimiento(credencialesClaveOnline.getFechaDeNacimiento(), FORMATO_FECHA));
        identificadorCliente.setIp(NetworkUtil.getHostAddress());
        identificadorCliente.setProductos(filtrarProductos(identificacion.getProductos()));

        Respuesta<PreguntasSeguridad> respuesta = obtenerPreguntasSeguridad(identificadorCliente);

        return respuesta;
    }
    
    /**
     * el metodo devuelve una lista de objetos que no tengan atributos vacio o nulos
     * @param lista
     * @return
     */
	private List<ProductoEntity> filtrarProductos(List<ProductoEntity> lista) {
		List<ProductoEntity> listaProductosFinal = new ArrayList<ProductoEntity>();
		for (ProductoEntity productoEntity : lista) {
    		if(!StringUtils.isBlank(productoEntity.getCalidadParticipacion()) && 
    		   !StringUtils.isBlank(productoEntity.getCodigoOficina()) && 
    		   !StringUtils.isBlank(productoEntity.getCodigoProducto()) && 
    		   !StringUtils.isBlank(productoEntity.getCodigoSubProducto()) && 
    		   !StringUtils.isBlank(productoEntity.getNroContrato() )) {
    			listaProductosFinal.add(productoEntity);
    		}
    	}
    	return lista;
   	
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO#
     * obtenerPreguntasSeguridad(ar.com.santanderrio.obp.servicios.clave.online.
     * entities.IdentificadorClienteInEntity)
     */
    @Override
    public Respuesta<PreguntasSeguridad> obtenerPreguntasSeguridad(IdentificadorClienteInEntity identificadorCliente) {

        Respuesta<PreguntasSeguridad> respuesta = respuestaFactory.crearRespuestaOk(PreguntasSeguridad.class);

        PreguntasSeguridad ps = null;
		try {
			PreguntasSeguridadResponse preguntas = preguntasSeguridadDAO
					.obtenerPreguntasSeguridad(identificadorCliente);
			ps = parsearPreguntas(preguntas, identificadorCliente);
			respuesta.setRespuesta(ps);
		} catch (ErrorDb2Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2,
					CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (ErrorRutinaFechasException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS,
					CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorCicsException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS,
					CodigoMensajeConstantes.ERROR_CICS);
		} catch (FuncionInvalidaException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA,
					CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorModuloException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO,
					CodigoMensajeConstantes.ERROR_MODULO);
		} catch (HayRespuestasErroneasException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_HAY_RESPUESTAS_ERRONEAS,
					CodigoMensajeConstantes.ERROR_RESPUESTAS_ERRONEAS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT,
					EstadisticasConstants.ERROR_VALIDACION_PREGUNTAS_TIMEOUT);
		}
		return respuesta;
    }

    /**
     * Parsear preguntas.
     *
     * @param preguntas
     *            the preguntas
     * @param identificadorCliente
     *            the identificador cliente
     * @return the preguntas seguridad
     */
    private PreguntasSeguridad parsearPreguntas(PreguntasSeguridadResponse preguntas,
            IdentificadorClienteInEntity identificadorCliente) {

        PreguntasSeguridad preguntasSeguridad = new PreguntasSeguridad();

        preguntasSeguridad.setCantidadPreguntas(preguntas.getCantidadPreguntas());

        /*
         * itero sobre las colecciones de preguntas y respuestas porque deberian ser del
         * mismo tamaño
         */
        for (int i = 0; i < preguntas.getCantidadPreguntas(); i++) {

            PreguntasEntity pregunta = preguntas.getPreguntas().get(i);
            RespuestaEntity respuesta = preguntas.getRespuestas().get(i);
            PreguntaAutenticacion preguntaAutenticacion = new PreguntaAutenticacion();
            preguntaAutenticacion.setPregunta(pregunta.getTexto());
            preguntaAutenticacion.setIdPregunta(pregunta.getIdentificadorPregunta());
            actualizarRespuestas(preguntaAutenticacion, respuesta);
            preguntasSeguridad.getPreguntas().add(preguntaAutenticacion);
        }

        preguntasSeguridad.setIdSesion(identificadorCliente.getIdSesion());
        preguntasSeguridad.setCantidadOcurrencias(identificadorCliente.getCantidadOcurrencias());
        preguntasSeguridad.setCiclo(identificadorCliente.getCiclo());
        preguntasSeguridad.setProductos(identificadorCliente.getProductos());
        preguntasSeguridad.setDni(identificadorCliente.getDni());
        preguntasSeguridad.setFechaNacimiento(identificadorCliente.getFechaNacimiento());
        return preguntasSeguridad;
    }

    /**
     * Actualizar respuestas.
     *
     * @param preguntaAutenticacion
     *            the pregunta autenticacion
     * @param respuesta
     *            the respuesta
     */
    private void actualizarRespuestas(PreguntaAutenticacion preguntaAutenticacion, RespuestaEntity respuesta) {

        if (!StringUtils.isEmpty(respuesta.getOpcion1())) {
            RespuestaAutenticacion resp = new RespuestaAutenticacion();
            resp.setOrden(1);
            resp.setRespuesta(respuesta.getOpcion1());
            preguntaAutenticacion.getRespuestas().add(resp);
        }

        if (!StringUtils.isEmpty(respuesta.getOpcion2())) {
            RespuestaAutenticacion resp2 = new RespuestaAutenticacion();
            resp2.setOrden(2);
            resp2.setRespuesta(respuesta.getOpcion2());
            preguntaAutenticacion.getRespuestas().add(resp2);
        }

        if (!StringUtils.isEmpty(respuesta.getOpcion3())) {
            RespuestaAutenticacion resp3 = new RespuestaAutenticacion();
            resp3.setOrden(3);
            resp3.setRespuesta(respuesta.getOpcion3());
            preguntaAutenticacion.getRespuestas().add(resp3);

        }

        if (!StringUtils.isEmpty(respuesta.getOpcion4())) {
            RespuestaAutenticacion resp4 = new RespuestaAutenticacion();
            resp4.setOrden(4);
            resp4.setRespuesta(respuesta.getOpcion4());
            preguntaAutenticacion.getRespuestas().add(resp4);
        }

        if (!StringUtils.isEmpty(respuesta.getOpcion5())) {
            RespuestaAutenticacion resp5 = new RespuestaAutenticacion();
            resp5.setOrden(5);
            resp5.setRespuesta(respuesta.getOpcion5());
            preguntaAutenticacion.getRespuestas().add(resp5);
        }
        preguntaAutenticacion.setOpcionCorrecta(respuesta.getOpcionCorrecta());

    }

    /**
     * Validar respuesta.
     *
     * @param preguntaIn
     *            the pregunta in
     * @return the respuesta
     */
    public Respuesta<Void> validarRespuesta(ValidacionPreguntaIn preguntaIn) {

        Respuesta<Void> respuesta = respuestaFactory.crearRespuestaOk(Void.class);
        try {
            validacionRespuestasDAO.validarRespuesta(preguntaIn);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT,
                    EstadisticasConstants.ERROR_VALIDACION_PREGUNTAS_TIMEOUT);
        } catch (ClienteBloqueado2Exception e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS,
                    CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_SEMANA);
        } catch (ValidacionRespuestaReintentarException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError("",
                    TipoError.ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_CON_REINTENTOS,
                    EstadisticasConstants.ERROR_VALIDACION_PREGUNTAS_NO_PUDIMOS_IDENTIFICARTE_REINTENTAR);
        } catch (ValidacionRespuestaNoReintentarException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError("",
                    TipoError.ERROR_RESPUESTAS_PREGUNTAS_NO_VALIDAS_SIN_REINTENTOS,
                    CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_24_HORAS);
        } catch (RobotException e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        } catch (ErrorRutinaFechasException e) {
        	 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS,
                     CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorModuloException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO,
                     CodigoMensajeConstantes.ERROR_MODULO);
		} catch (ErrorDb2Exception e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2,
                     CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (FuncionInvalidaException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA,
                     CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorCicsException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS,
                     CodigoMensajeConstantes.ERROR_CICS);
		}catch (HayRespuestasErroneasException e) {
			 LOGGER.error(e.getMessage(), e);
             respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_HAY_RESPUESTAS_ERRONEAS,
 					CodigoMensajeConstantes.ERROR_RESPUESTAS_ERRONEAS);
		}

        return respuesta;
    }

    /**
     * Gets the pregunta validar.
     *
     * @param preguntaAutenticacion
     *            the pregunta autenticacion
     * @param opcion
     *            the opcion
     * @param ciclo
     *            the ciclo
     * @param sesion
     *            the sesion
     * @return the pregunta validar
     */
    public ValidacionPreguntaIn getPreguntaValidar(PreguntaAutenticacion preguntaAutenticacion, String opcion,
            Integer ciclo, String sesion) {

        ValidacionPreguntaIn preguntaValidar = new ValidacionPreguntaIn();

        preguntaValidar.setIdPregunta(preguntaAutenticacion.getIdPregunta());
        preguntaValidar.setOpcionCorrecta(preguntaAutenticacion.getOpcionCorrecta());
        preguntaValidar.setRespuesta(opcion);
        preguntaValidar.setIp(NetworkUtil.getHostAddress());
        preguntaValidar.setIdSesion(sesion);
        preguntaValidar.setCiclo(ciclo);
        preguntaValidar.setOrdenPregunta(preguntaAutenticacion.getIndice());

        String validacion = "M";
        if (preguntaAutenticacion.getOpcionCorrecta().equals(opcion)) {

            validacion = "B";
        }
        preguntaValidar.setValidacion(validacion);

        
        return preguntaValidar;
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO#
     * validarClave(ar.com.santanderrio.obp.servicios.clave.online.view.
     * DatosAutenticacion)
     */
    @Override
    public Respuesta<Void> validarClave(DatosAutenticacion datosAutenticacion) {

        DatosAutenticacionInEntity datosAutenticacionInEntity = new DatosAutenticacionInEntity();
        datosAutenticacionInEntity.setCiclo(datosAutenticacion.getCiclo().toString());
        String clave = (datosAutenticacion.getClave().length() != 4) ? datosAutenticacion.getClave()
                : encriptar(datosAutenticacion.getClave());
        datosAutenticacionInEntity.setClave(clave);
        datosAutenticacionInEntity.setDni(datosAutenticacion.getDni());
        datosAutenticacionInEntity
                .setFecha(FechaUtils.parsearFechaNacimiento(datosAutenticacion.getFechaDeNacimiento(), "ddMMyyyy"));
        datosAutenticacionInEntity.setFuncionEnum(datosAutenticacion.getFuncionEnum());
        datosAutenticacionInEntity.setIp(datosAutenticacion.getIp());
        datosAutenticacionInEntity.setLetraA(encriptar(LETRA_A));
        datosAutenticacionInEntity.setNumero(encriptar(datosAutenticacion.getNumeroAEnviarEnSegSgiaut()));
        datosAutenticacionInEntity.setSesion(datosAutenticacion.getSesion());
        datosAutenticacionInEntity.setTipoAut(encriptar(datosAutenticacion.getTipoAut()));
        datosAutenticacionInEntity.setNup(datosAutenticacion.getNup());
        datosAutenticacionInEntity.setEmpresaCelularElegida(datosAutenticacion.getEmpresaCelular());
        try {
            validadorClaveDAO.validarClave(datosAutenticacionInEntity);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CONFIRMACION_CLAVE_TIMEOUT,
                    CodigoMensajeConstantes.CONFIRMACION_CLAVE_TIMEOUT);
        } catch (ErrorClaveOnlineConReintentoException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.PUEDE_REINTENTAR,
                    CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_METODO_VALIDACION);
        } catch (ClienteBloqueadoException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_INCORRECTA,
                    CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_METODO_VALIDACION_BLOQUEADO);
        } catch (ClienteBloqueado2Exception e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_INCORRECTA,
                    CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_METODO_VALIDACION_BLOQUEADO2);
        } catch (SinReintentosSMSException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaWarning("", TipoError.SIN_REINTENTOS_SMS,
                    CodigoMensajeConstantes.CLAVE_ONLINE_REENVIO_SMS);
        } catch (ErrorEnvioSMSException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        } catch (ErrorPinBanelcoException e) {
            LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PIN_BANELCO, 
					CodigoMensajeConstantes.ERROR_PIN_BANELCO);
		} catch (ErrorPinOtpException e) {
			   LOGGER.error(e.getMessage(), e);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_PIN_OTP, 
						CodigoMensajeConstantes.ERROR_PIN_OTP);
		} catch (ErrorRutinaFechasException e) {
			 LOGGER.error(e.getMessage(), e);
             return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS,
                     CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorModuloException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO,
                     CodigoMensajeConstantes.ERROR_MODULO);
		} catch (ErrorDb2Exception e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2,
                     CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (FuncionInvalidaException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA,
                     CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorCicsException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS,
                     CodigoMensajeConstantes.ERROR_CICS);
		} catch (ErrorLogicoOtpException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_LOGICO_OTP,
                     CodigoMensajeConstantes.ERROR_LOGICO_OTP);
		} catch (OtpVencidoException e){
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_OTP_VENCIDO,
                    CodigoMensajeConstantes.ERROR_OTP_VENCIDO);
        }
        return respuestaFactory.crearRespuestaOk(Void.class);
    }

    /**
     * Encriptar.
     *
     * @param string
     *            the string
     * @return the string
     */
    private String encriptar(String string) {
        FormatCrUtil formatCrUtil = new FormatCrUtil();

        return formatCrUtil.criptoEnc(string);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO#
     * altaSGIClave(ar.com.santanderrio.obp.servicios.clave.online.entities.
     * AltaSGIClaveInEntity)
     */
    @Override
    public Respuesta<Void> altaSGIClave(AltaSGIClaveInEntity in) {
        try {
        	if (moduloPermisoBO.tienePermisoMostrar(AccionController.PROXY_LOGIN)) {
        		altaSGIClaveDAO.altaSGIClaveProxyLogin(in);
        	} else {
        		altaSGIClaveDAO.altaSGIClave(in);
        	}
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CONFIRMACION_CLAVE_TIMEOUT,
                    CodigoMensajeConstantes.CONFIRMACION_CLAVE_TIMEOUT);
        } catch (ClaveOnlineClaveRepeException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR,
                    CodigoMensajeConstantes.ERROR_CLAVE_ONLINE_CLAVE_REPETIDA);
        } catch (ClaveOnlineUsuRepeException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR,
                    CodigoMensajeConstantes.ERROR_CLAVE_ONLINE_USUARIO_REPETIDO);
        } catch (ClaveOnlineUsuTrivialException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR,
                    CodigoMensajeConstantes.ERROR_CLAVE_ONLINE_USUARIO_TRIVIAL);
        } catch (ClaveOnlineClaveTrivialException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR,
                    CodigoMensajeConstantes.ERROR_CLAVE_ONLINE_CLAVE_TRIVIAL);
        } catch (ClienteBloqueadoException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.CLAVE_ONLINE_CLIENTE_BLOQUEADO,
                    CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_METODO_VALIDACION_BLOQUEADO);
        } catch (RobotException e) {
            LOGGER.error(e.getMessage(), e);
            return respuestaFactory.crearRespuestaError("", TipoError.LOGIN_ERROR_TOTAL,
                    CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
        } catch (ErrorRutinaFechasException e) {
        	 LOGGER.error(e.getMessage(), e);
        	 return  respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS,
                     CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorModuloException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO,
                    CodigoMensajeConstantes.ERROR_MODULO);
		} catch (ErrorDb2Exception e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2,
                    CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (FuncionInvalidaException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA,
                    CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorCicsException e) {
			 LOGGER.error(e.getMessage(), e);
			 return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS,
                    CodigoMensajeConstantes.ERROR_CICS);
		}
        return respuestaFactory.crearRespuestaOk(Void.class);
    }

	@Override
	public Respuesta<IdentificadorClienteOutEntity> validarRespuestaTelefono(ValidacionPreguntaIn preguntaIn) {
		Respuesta<IdentificadorClienteOutEntity> respuesta = respuestaFactory.crearRespuestaOk(IdentificadorClienteOutEntity.class);
		try {
			IdentificadorClienteOutEntity identificadorClienteOutEntity = validacionRespuestasDAO.validarRespuestaTelefono(preguntaIn);
			respuesta.setRespuesta(identificadorClienteOutEntity);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.TIMEOUT, EstadisticasConstants.ERROR_VALIDACION_PREGUNTAS_TIMEOUT);
		} catch (ValidacionRespuestaReintentarException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_CON_REINTENTOS, EstadisticasConstants.ERROR_VALIDACION_PREGUNTAS_NO_PUDIMOS_IDENTIFICARTE_REINTENTAR);
		} catch (ValidacionRespuestaNoReintentarException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_SIN_REINTENTOS, CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_24_HORAS);
		} catch (RobotException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.LOGIN_ERROR_TOTAL, CodigoMensajeConstantes.LOGIN_ERROR_TOTAL);
		} catch (ClienteBloqueado2Exception e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_RESPUESTAS_FLUJO_CELULAR_NO_VALIDAS_SIN_REINTENTOS, CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_SEMANA);	
		} catch (ErrorRutinaFechasException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_RUTINA_FECHAS, CodigoMensajeConstantes.ERROR_RUTINA_FECHAS);
		} catch (ErrorModuloException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODULO, CodigoMensajeConstantes.ERROR_MODULO);
		} catch (ErrorDb2Exception e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SQL_CODE_DB2, CodigoMensajeConstantes.ERROR_SQL_CODE_DB2);
		} catch (FuncionInvalidaException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_FUNCION_INVALIDA, CodigoMensajeConstantes.ERROR_FUNCION_INVALIDA);
		} catch (ErrorCicsException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CICS, CodigoMensajeConstantes.ERROR_CICS);
		} catch (ClienteBloqueado3Exception e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CLIENTE_BLOQUEADO_3,
					CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_24_HORAS);
		} catch (ClienteBloqueado4Exception e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CLIENTE_BLOQUEADO_4,
					CodigoMensajeConstantes.CLAVE_ONLINE_CLIENTE_BLOQUEADO_SEMANA);
		} catch (SistemaClaveNoDisponibleException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIST_CLAVE_NO_DISPONIBLE,
					CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_SISTEMA_AUTEGESTION_CLAVE_BLOQUEADO);
		} catch (SinTarjetaValidaException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIN_TARJETA_DEBITO_VALIDA,
					CodigoMensajeConstantes.SIN_TARJETA_DEBITO_VALIDA);
		} catch (SinCelularRegistradoException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SIN_CEL_REGISTRADO,
					CodigoMensajeConstantes.CLAVE_ONLINE_ERROR_ERROR_SIN_CEL_REGISTRADO);
		} catch (AutenticarTarjetaDebitoException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_TARJ_DEBITO,
					CodigoMensajeConstantes.ERROR_AUTENTICACION_TARJ_DEBITO);
		} catch (AutenticacionPinInactivaException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_PIN_INACTIVA,
					CodigoMensajeConstantes.ERROR_AUTENTICACION_PIN_INACTIVA);
		} catch (AutenticacionSmsOtpInactivaException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_SMS_OTP_INACTIVA,
					CodigoMensajeConstantes.ERROR_AUTENTICACION_SMS_OTP_INACTIVA);
		} catch (AutenticacionCvv2InactivaException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AUTENTICACION_CVV2_INACTIVA,
					CodigoMensajeConstantes.ERROR_AUTENTICACION_CVV2_INACTIVA);
		} catch (CLienteSinContratoException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_CLIENTE_SIN_CONTRATO,
					CodigoMensajeConstantes.CLIENTE_SIN_CONTRATO);
		} catch (HayRespuestasErroneasException e) {
			LOGGER.error(e.getMessage(), e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_HAY_RESPUESTAS_ERRONEAS,
					CodigoMensajeConstantes.ERROR_RESPUESTAS_ERRONEAS);
		}


		return respuesta;
	}

	@Override
	public Boolean obtenerClaveWhatsapp() {
		try {
			return claveOnlineDAO.obtenerClaveWhatsapp();
		}
		catch(DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public String obtenerTarjetaParaValidarPin(DatosAutenticacion datosAutenticacion, String ip) throws BusinessException {

		SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn = new SeleccionTarjetaBanelcoInEntity();
		
		seleccionTarjetaIn.setFuncion(FUNCION_SELECCION_TARJETA_BANELCO);
		seleccionTarjetaIn.setIdSesion(datosAutenticacion.getSesion());
		seleccionTarjetaIn.setIp(IpUtils.dottedIp2Hex(ip));
		seleccionTarjetaIn.setDni(datosAutenticacion.getDni());
		seleccionTarjetaIn.setFechaNacimiento(FechaUtils.parsearFechaNacimiento(datosAutenticacion.getFechaDeNacimiento(), "ddMMyyyy"));
		seleccionTarjetaIn.setNup(datosAutenticacion.getNup());
		
		try {
			String numeroTarjeta = validacionPinBanelcoDAO.obtenerTarjetaParaValidarPin(seleccionTarjetaIn);
			sesionParametros.setNumeroIntentoValidarPinBanelco(0);
			return numeroTarjeta;
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public String validarPinBanelco(DatosAutenticacion datosAutenticacion, String ip, TarjetaBanelcoView banelcoView) throws BusinessException {

		SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn = new SeleccionTarjetaBanelcoInEntity();
		seleccionTarjetaIn.setDni(datosAutenticacion.getDni());
		seleccionTarjetaIn.setFechaNacimiento(FechaUtils.parsearFechaNacimiento(datosAutenticacion.getFechaDeNacimiento(), "ddMMyyyy"));
		seleccionTarjetaIn.setNup(datosAutenticacion.getNup());
		
        DatosAutenticacionInEntity datosAutenticacionInEntity = new DatosAutenticacionInEntity();
        datosAutenticacionInEntity.setClave(encriptar(banelcoView.getPinTarjeta()));
        datosAutenticacionInEntity.setFuncionEnum(FuncionEnum.BANE);
        datosAutenticacionInEntity.setIp(IpUtils.dottedIp2Hex(ip));
        datosAutenticacionInEntity.setLetraA(encriptar(LETRA_A));
        datosAutenticacionInEntity.setNumero(encriptar(banelcoView.getNumeroTarjeta()));
        datosAutenticacionInEntity.setSesion(datosAutenticacion.getSesion());
        datosAutenticacionInEntity.setTipoAut(encriptar("BANE"));
        datosAutenticacionInEntity.setCiclo(banelcoView.getCiclo().toString());
		
		try {
			return validacionPinBanelcoDAO.validarPinBanelco(seleccionTarjetaIn, datosAutenticacionInEntity);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
	
}
