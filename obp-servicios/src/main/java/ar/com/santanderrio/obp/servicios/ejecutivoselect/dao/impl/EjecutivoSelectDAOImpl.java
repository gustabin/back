package ar.com.santanderrio.obp.servicios.ejecutivoselect.dao.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.omnicanalidad.IOmnicanalidad;
import ar.com.santanderrio.obp.servicios.chat.dao.impl.ChatDAOImpl;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.dao.EjecutivoSelectDAO;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorInEntity;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorOutEntity;

/**
 * The Class EjecutivoSelectDAOImpl.
 */
@Component
public class EjecutivoSelectDAOImpl implements EjecutivoSelectDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDAOImpl.class);

	/** The ws ejecutivo select. */
	@Autowired
	@Qualifier("ejucutivoSelectGestor")
	private GestionarWS<IOmnicanalidad> wsEjecutivoSelect;
	/** The sign. */
	@Autowired
	private Sign sign;

	/** The Constant JKS. */
	private static final String JKS = "OPERADOREJECUTIVO";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.ejecutivoselect.dao.EjecutivoSelectDAO#
	 * notificarLlamadaPorTel(ar.com.santanderrio.obp.servicios.ejecutivoselect.
	 * entities.ConsultaTelefonoOperadorInEntity)
	 */
	@Override
	public ConsultaTelefonoOperadorOutEntity notificarLlamadaPorTel(ConsultaTelefonoOperadorInEntity entityIn)
			throws DAOException {
		String respuesta = null;
		ConsultaTelefonoOperadorOutEntity outEntity = null;
		IOmnicanalidad service = null;
        LOGGER.info("EjecutivoSelectDAOImpl _ Iniciando metodo notificarLlamadaPorTel");

		try {

			Data j = new Data(entityIn.getSessionId(), entityIn.getNupCliente(), entityIn.getTelefono(),
					entityIn.getCanal(), entityIn.getUsuarioRacf(), entityIn.getPasswordRacf(),
					entityIn.getCodigoAccion(), entityIn.getNroDoc(), entityIn.getTipoPersona());
	           LOGGER.info("EjecutivoSelectDAOImpl _ Iniciando llamada WS Onmicanalidad RegistrarClickToCall con los siguientes datos {}" , j.toString());

			String json = new ObjectMapper().writeValueAsString(j);
			byte[] data = new byte[(int) json.length()];
			data = json.getBytes();
			String tokenFirmado = new String(sign.buildB64Signature(data, JKS, true));
			service = wsEjecutivoSelect.obtenerPort();
			respuesta = service.registrarClickToCall(tokenFirmado);

			outEntity = new ObjectMapper().readValue(respuesta,
					new ObjectMapper().constructType(ConsultaTelefonoOperadorOutEntity.class));
            LOGGER.info("EjecutivoSelectDAOImpl _ Finalizando llamada WS Onmicanalidad RegistrarClickToCall");
			return outEntity;


		} catch (JsonParseException e) {
			LOGGER.error(
                    "JsonParseException Hubo un error al invocar al ws de operador ejecutivo para la operacion notificarLlamadaPorTel con los datos {}.",
                    entityIn, e);
			throw new DAOException();

		} catch (JsonMappingException e) {
			LOGGER.error(
                    "JsonMappingException Hubo un error al invocar al ws de operador ejecutivo para la operacion notificarLlamadaPorTel con los datos {}.",
                    entityIn, e);
			throw new DAOException();

		} catch (IOException e) {
			LOGGER.error(
                    "IOException Hubo un error al invocar al ws de operador ejecutivo para la operacion notificarLlamadaPorTel con los datos {}.",
                    entityIn, e);
			throw new DAOException();
		}
		catch (Exception e) {
			 LOGGER.error(
                     "Exception Hubo un error al invocar al ws de operador ejecutivo para la operacion notificarLlamadaPorTel con los datos {}.",
                     entityIn, e);			
			throw new DAOException();
		}finally {
		
	        LOGGER.info("EjecutivoSelectDAOImpl _ Finalizando llamada WS Onmicanalidad RegistrarClickToCall");
			wsEjecutivoSelect.liberarPort(service);
		}

	}

	/**
	 * The Class Data.
	 */
	@SuppressWarnings("unused")
	private class Data {

		/** The Session id. */
		private String Session_id;

		/** The NU P cliente. */
		private String NUP_Cliente;

		/** The Telefono. */
		private String Telefono;

		/** The Canal. */
		private String Canal;

		/** The usr racf. */
		private String USR_RACF;

		/** The pwd racf. */
		private String PWD_RACF;

		/** The codigo accion. */
		private String CODIGO_ACCION;

		/** The nro doc. */
		private String NRO_DOC;

		/** The tipo persona. */
		private String TIPO_PERSONA;

		/**
		 * Instantiates a new data.
		 *
		 * @param session_id
		 *            the session id
		 * @param nup_cliente
		 *            the nup cliente
		 * @param telefono
		 *            the telefono
		 * @param canal
		 *            the canal
		 * @param uSR_RACF
		 *            the u S R RACF
		 * @param pWD_RACF
		 *            the wd racf
		 * @param cODIGO_ACCION
		 *            the c ODIG O ACCION
		 * @param nRO_DOC
		 *            the n R O DOC
		 * @param tIPO_PERSONA
		 *            the t IP O PERSONA
		 */
		public Data(String session_id, String nup_cliente, String telefono, String canal, String uSR_RACF,
				String pWD_RACF, String cODIGO_ACCION, String nRO_DOC, String tIPO_PERSONA) {
			super();
			Session_id = session_id;
			NUP_Cliente = nup_cliente;
			Telefono = telefono;
			Canal = canal;
			USR_RACF = uSR_RACF;
			PWD_RACF = pWD_RACF;
			CODIGO_ACCION = cODIGO_ACCION;
			NRO_DOC = nRO_DOC;
			TIPO_PERSONA = tIPO_PERSONA;
		}

		/**
		 * Gets the session id.
		 *
		 * @return the session id
		 */
		public String getSession_id() {
			return Session_id;
		}

		/**
		 * Sets the session id.
		 *
		 * @param session_id
		 *            the new session id
		 */
		public void setSession_id(String session_id) {
			Session_id = session_id;
		}

		/**
		 * Gets the telefono.
		 *
		 * @return the telefono
		 */
		public String getTelefono() {
			return Telefono;
		}

		/**
		 * Sets the telefono.
		 *
		 * @param telefono
		 *            the new telefono
		 */
		public void setTelefono(String telefono) {
			Telefono = telefono;
		}

		/**
		 * Gets the canal.
		 *
		 * @return the canal
		 */
		public String getCanal() {
			return Canal;
		}

		/**
		 * Sets the canal.
		 *
		 * @param canal
		 *            the new canal
		 */
		public void setCanal(String canal) {
			Canal = canal;
		}

		/**
		 * Gets the usr racf.
		 *
		 * @return the usr racf
		 */
		public String getUSR_RACF() {
			return USR_RACF;
		}

		/**
		 * Sets the usr racf.
		 *
		 * @param uSR_RACF
		 *            the new usr racf
		 */
		public void setUSR_RACF(String uSR_RACF) {
			USR_RACF = uSR_RACF;
		}

		/**
		 * Gets the pwd racf.
		 *
		 * @return the pwd racf
		 */
		public String getPWD_RACF() {
			return PWD_RACF;
		}

		/**
		 * Sets the pwd racf.
		 *
		 * @param pWD_RACF
		 *            the new pwd racf
		 */
		public void setPWD_RACF(String pWD_RACF) {
			PWD_RACF = pWD_RACF;
		}

		/**
		 * Gets the codigo accion.
		 *
		 * @return the codigo accion
		 */
		public String getCODIGO_ACCION() {
			return CODIGO_ACCION;
		}

		/**
		 * Sets the codigo accion.
		 *
		 * @param cODIGO_ACCION
		 *            the new codigo accion
		 */
		public void setCODIGO_ACCION(String cODIGO_ACCION) {
			CODIGO_ACCION = cODIGO_ACCION;
		}

		/**
		 * Gets the nro doc.
		 *
		 * @return the nro doc
		 */
		public String getNRO_DOC() {
			return NRO_DOC;
		}

		/**
		 * Sets the nro doc.
		 *
		 * @param nRO_DOC
		 *            the new nro doc
		 */
		public void setNRO_DOC(String nRO_DOC) {
			NRO_DOC = nRO_DOC;
		}

		/**
		 * Gets the tipo persona.
		 *
		 * @return the tipo persona
		 */
		public String getTIPO_PERSONA() {
			return TIPO_PERSONA;
		}

		/**
		 * Sets the tipo persona.
		 *
		 * @param tIPO_PERSONA
		 *            the new tipo persona
		 */
		public void setTIPO_PERSONA(String tIPO_PERSONA) {
			TIPO_PERSONA = tIPO_PERSONA;
		}

		/**
		 * Gets the NU P cliente.
		 *
		 * @return the NU P cliente
		 */
		public String getNUP_Cliente() {
			return NUP_Cliente;
		}

		/**
		 * Sets the NU P cliente.
		 *
		 * @param nup_cliente
		 *            the new NU P cliente
		 */
		public void setNUP_Cliente(String nup_cliente) {
			NUP_Cliente = nup_cliente;
		}

	}

}
