/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.dao.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.GetClientSegmentSelectLaborableDataResponse;
import ar.com.santanderrio.obp.generated.webservices.segmento.IClientData;
import ar.com.santanderrio.obp.generated.webservices.segmento.IClientDataGetClientSegmentDataFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.segmento.IClientDataGetClientSelectSegmentDataFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.segmento.IClientDataGetClientSelectSegmentLaborableDataFaultFaultFaultMessage;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO;

/**
 * The Class SegmentoClienteDAOImpl.
 */
@Component
public class SegmentoClienteDAOImpl implements SegmentoClienteDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SegmentoClienteDAOImpl.class);

	/** The gestor plan V. */
	@Autowired
	@Qualifier("segmentoCliente")
	private GestionarWSAbstract<IClientData> gestorSegmentoCliente;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The keystore. */
	private static final String JKS = "SEGMENTO";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO#
	 * getClienteSegmento(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public GetClientSegmentDataResponse getClienteSegmento(String nup, String channel, String subchannel, String user,
			String password) throws DAOException {

		IClientData alertasCRM = null;
		try {

			Data j = new Data(new Integer(nup), new Integer(channel), new Integer(subchannel), user, password);

			String json = new ObjectMapper().writeValueAsString(j);

			byte[] dataToSign = new byte[(int) json.length()];

			dataToSign = json.getBytes();

			String tokenFirmado = new String(sign.buildB64Signature(dataToSign, JKS, true));

			alertasCRM = gestorSegmentoCliente.obtenerPort();

			GetClientSegmentDataResponse getClientSegmentDataResponse = alertasCRM.getClientSegment(tokenFirmado);

			return getClientSegmentDataResponse;

		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} catch (IClientDataGetClientSegmentDataFaultFaultFaultMessage sfe) {
			LOGGER.error("Error al consumir el ws, {}", sfe.getMessage(), sfe);
			throw new DAOException(sfe, sfe.getMessage());
		} catch (JsonGenerationException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("Error al mapear el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (RuntimeException ex) {
			LOGGER.error("Error al generar el objeto JSON, {}", ex.getMessage(), ex);
			throw new DAOException(ex, ex.getMessage());
		} finally {
			gestorSegmentoCliente.liberarPort(alertasCRM);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO#
	 * getClienteSegmento(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public GetClientSegmentSelectDataResponse getClientSelectSegment(String nup, String channel, String subchannel, String user,
			String password) throws DAOException {

		IClientData alertasCRM = null;
		try {

			Data j = new Data(new Integer(nup), new Integer(channel), new Integer(subchannel), user, password);

			String json = new ObjectMapper().writeValueAsString(j);

			byte[] dataToSign = new byte[(int) json.length()];

			dataToSign = json.getBytes();

			String tokenFirmado = new String(sign.buildB64Signature(dataToSign, JKS, true));

			alertasCRM = gestorSegmentoCliente.obtenerPort();

			GetClientSegmentSelectDataResponse getClientSegmentDataResponse = alertasCRM.getClientSelectSegment(tokenFirmado);

			return getClientSegmentDataResponse;

		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} catch (JsonGenerationException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("Error al mapear el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (RuntimeException ex) {
			LOGGER.error("Error al generar el objeto JSON, {}", ex.getMessage(), ex);
			throw new DAOException(ex, ex.getMessage());
		} catch (IClientDataGetClientSelectSegmentDataFaultFaultFaultMessage e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} finally {
			gestorSegmentoCliente.liberarPort(alertasCRM);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAO#
	 * getClienteSegmento(java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public GetClientSegmentSelectLaborableDataResponse getClientSelectSegmentLaborable(String nup, String channel, String subchannel, String user,
			String password) throws DAOException {

		IClientData alertasCRM = null;
		try {

			Data j = new Data(new Integer(nup), new Integer(channel), new Integer(subchannel), user, password);

			String json = new ObjectMapper().writeValueAsString(j);

			byte[] dataToSign = new byte[(int) json.length()];

			dataToSign = json.getBytes();

			String tokenFirmado = new String(sign.buildB64Signature(dataToSign, JKS, true));

			alertasCRM = gestorSegmentoCliente.obtenerPort();

			GetClientSegmentSelectLaborableDataResponse getClientSegmentLaborableDataResponse = alertasCRM.getClientSelectSegmentLaborable(tokenFirmado);

			

			
			return getClientSegmentLaborableDataResponse;

		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} catch (JsonGenerationException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error("Error al mapear el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		} catch (RuntimeException ex) {
			LOGGER.error("Error al generar el objeto JSON, {}", ex.getMessage(), ex);
			throw new DAOException(ex, ex.getMessage());
		} catch (IClientDataGetClientSelectSegmentLaborableDataFaultFaultFaultMessage e) {
			LOGGER.error("Error al generar el objeto JSON, {}", e.getMessage(), e);
			throw new DAOException(e, e.getMessage());
		}
		finally {
			gestorSegmentoCliente.liberarPort(alertasCRM);
		}
	}

	/**
	 * The Class Data.
	 */
	@SuppressWarnings("unused")
	private class Data {

		/** The nup. */
		private Integer nup;

		/** The channel. */
		private Integer channel;

		/** The subchannel. */
		private Integer subchannel;

		/** The user. */
		private String user;

		/** The password. */
		private String password;

		/**
		 * Instantiates a new data.
		 *
		 * @param nup
		 *            the nup
		 * @param channel
		 *            the channel
		 * @param subchannel
		 *            the subchannel
		 * @param user
		 *            the user
		 * @param password
		 *            the password
		 */
		public Data(Integer nup, Integer channel, Integer subchannel, String user, String password) {
			this.nup = nup;
			this.channel = channel;
			this.subchannel = subchannel;
			this.user = user;
			this.password = password;
		}

		/**
		 * Gets the nup.
		 *
		 * @return the nup
		 */
		public Integer getNup() {
			return nup;
		}

		/**
		 * Gets the channel.
		 *
		 * @return the channel
		 */
		public Integer getChannel() {
			return channel;
		}

		/**
		 * Gets the subchannel.
		 *
		 * @return the subchannel
		 */
		public Integer getSubchannel() {
			return subchannel;
		}

		/**
		 * Gets the user.
		 *
		 * @return the user
		 */
		public String getUser() {
			return user;
		}

		/**
		 * Gets the password.
		 *
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

	}

}
