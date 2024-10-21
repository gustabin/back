/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntaAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.view.TarjetaBanelcoView;

/**
 * The Interface ClaveOnlineBO.
 */
public interface ClaveOnlineBO {

	/**
	 * Validar credenciales.
	 *
	 * @param credencialesClaveOnline
	 *            the credenciales clave online
	 * @param identificacion
	 *            the identificacion
	 * @return the respuesta
	 */

	Respuesta<PreguntasSeguridad> obtenerPreguntasSeguridad(CredencialesClaveOnline credencialesClaveOnline,
			IdentificadorClienteOutEntity identificacion);

	/**
	 * Validar respuesta.
	 *
	 * @param preguntaIn
	 *            the pregunta in
	 * @return the respuesta
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 */
	Respuesta<IdentificadorClienteOutEntity> validarRespuestaTelefono(ValidacionPreguntaIn preguntaIn);
	
	/**
	 * Validar respuesta.
	 *
	 * @param preguntaIn
	 *            the pregunta in
	 * @return the respuesta
	 */
	Respuesta<Void> validarRespuesta(ValidacionPreguntaIn preguntaIn);

	/**
	 * Confirmar datos.
	 *
	 * @param identificadorClienteInEntity
	 *            the identificador cliente in entity
	 * @return the respuesta
	 */
	Respuesta<IdentificadorClienteOutEntity> confirmarDatos(IdentificadorClienteInEntity identificadorClienteInEntity);

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
	ValidacionPreguntaIn getPreguntaValidar(PreguntaAutenticacion preguntaAutenticacion, String opcion, Integer ciclo,
			String sesion);

	/**
	 * Validar clave.
	 *
	 * @param datosAutenticacion
	 *            the datos autenticacion
	 * @return the respuesta
	 */
	Respuesta<Void> validarClave(DatosAutenticacion datosAutenticacion);

	/**
	 * Obtener preguntas seguridad.
	 *
	 * @param identificadorCliente
	 *            the identificador cliente
	 * @return the respuesta
	 */
	Respuesta<PreguntasSeguridad> obtenerPreguntasSeguridad(IdentificadorClienteInEntity identificadorCliente);

	/**
	 * Alta SGI clave.
	 *
	 * @param in
	 *            the in
	 * @return the respuesta
	 */
	Respuesta<Void> altaSGIClave(AltaSGIClaveInEntity in);
	
	/**
	 * Obtener clave WhatsApp habilitado.
	 *
	 * @return the respuesta
	 */
	Boolean obtenerClaveWhatsapp();
	
	String obtenerTarjetaParaValidarPin (DatosAutenticacion datosAutenticacion, String ip) throws BusinessException;
	
	String validarPinBanelco (DatosAutenticacion datosAutenticacion, String ip, TarjetaBanelcoView banelcoView) throws BusinessException;

}
