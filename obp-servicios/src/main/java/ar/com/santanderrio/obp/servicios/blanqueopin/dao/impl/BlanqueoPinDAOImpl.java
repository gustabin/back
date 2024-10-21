package ar.com.santanderrio.obp.servicios.blanqueopin.dao.impl;

import java.net.SocketTimeoutException;

import org.apache.cxf.binding.soap.SoapFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.banelco.ICentroDeServiciosClientPortType;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTarjetaDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalData;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSUserData;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.BlanqueoPinDAO;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinEnum;

@Component
public class BlanqueoPinDAOImpl implements BlanqueoPinDAO {

	/** The wsBanelco. */
	@Autowired
	@Qualifier("wsBanelco")
	private GestionarWS<ICentroDeServiciosClientPortType> wsBanelco;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BlanqueoPinDAOImpl.class);

	@Override
	public String blanquearPin(WSTerminalData terminalData, WSUserData userData, WSTarjetaDTO tarjetaDTO,
			BlanqueoPinEnum tipoBlanqueo) throws DAOException {
		LOGGER.info("Inicio operacion blanqueo de pin");
		ICentroDeServiciosClientPortType cliente = null;
		String response = null;
		try {
			cliente = wsBanelco.obtenerPort();

			LOGGER.info("Los datos a enviar al servicio son: datosTerminal: {} || ipOrigen: {} || numeroDocumento: {} || "
					+ "tipoDocumento: {} || miembro: {} || numero: {}", terminalData.getDatosTerminal().getValue(), 
					terminalData.getIpOrigen().getValue(), userData.getNumeroDocumento().getValue(), 
					userData.getTipoDocumento().getValue(), tarjetaDTO.getMiembro().getValue(), 
					tarjetaDTO.getNumero().getValue());

			response = llamarServicioSegunTipoBlanqueo(terminalData, userData, tarjetaDTO, tipoBlanqueo, cliente);
			LOGGER.info("La respuesta del servicio de blanqueo de pin de la tarjeta {} fue {}",
					tarjetaDTO.getNumero().getValue(), response);
		} catch (SoapFault e) {
			LOGGER.info("La respuesta del servicio de blanqueo de pin de la tarjeta {} fue erronea",
					tarjetaDTO.getNumero().getValue());
			LOGGER.error("Error en llamado a WS blanquearPin", e);
			throw new DAOException();
		} catch (Exception e) {
			LOGGER.error("Error en llamado a WS blanquearPin", e);
			if (e.getCause() == null) {
				throw new DAOException();
			} else if (e.getCause().getClass().equals(SocketTimeoutException.class)) {
				LOGGER.info("La respuesta del servicio de blanqueo de pin de la tarjeta {} fue TimeOut",
						tarjetaDTO.getNumero().getValue());
				throw new DAOException("TimeOut");
			} else {
				throw new DAOException();
			}
			
		} finally {
			wsBanelco.liberarPort(cliente);
		}
		LOGGER.info("Fin operacion blanqueo de pin numerico");
		return response;
	}

	private String llamarServicioSegunTipoBlanqueo(WSTerminalData terminalData, WSUserData userData,
			WSTarjetaDTO tarjetaDTO, BlanqueoPinEnum tipoBlanqueo, ICentroDeServiciosClientPortType cliente) {
		if (BlanqueoPinEnum.NUMERICO.equals(tipoBlanqueo)) {
			return cliente.blanquearPin(terminalData, userData, tarjetaDTO);
		} else if (BlanqueoPinEnum.ALFABETICO.equals(tipoBlanqueo)) {
			return cliente.blanquearAlfa(terminalData, userData, tarjetaDTO);
		} else {
			return cliente.blanquearPinAlfa(terminalData, userData, tarjetaDTO);
		}
	}

	@Override
	public String cambiarClase(WSTerminalDTO terminalDTO, WSUserData userData, WSTarjetaDTO tarjetaDTO,
			String clase, String ip, String userAgent) throws DAOException {
		LOGGER.info("Inicio operacion cambiarClase");
		ICentroDeServiciosClientPortType cliente = null;
		String response = null;
		try {
			cliente = wsBanelco.obtenerPort();

			LOGGER.info("Los datos a enviar al servicio son: datosTerminal: {} || ipOrigen: {} || numeroDocumento: {} || "
					+ "tipoDocumento: {} || miembro: {} || numero: {}", terminalDTO.getDatosTerminal().getValue(), 
					terminalDTO.getDireccionIP().getValue(), userData.getNumeroDocumento().getValue(), 
					userData.getTipoDocumento().getValue(), tarjetaDTO.getMiembro().getValue(), 
					tarjetaDTO.getNumero().getValue());

			response = cliente.cambiarClase(terminalDTO, userData, tarjetaDTO, clase);

			LOGGER.info("La respuesta del servicio cambiarClase de la tarjeta {} fue {}",
					tarjetaDTO.getNumero().getValue(), response);
		} catch (SoapFault e) {
			LOGGER.info("La respuesta del servicio cambiarClase de la tarjeta {} fue erronea",
					tarjetaDTO.getNumero().getValue());
			LOGGER.error("Error en llamado a WS cambiarClase", e);
			throw new DAOException();
		} catch (Exception e) {
			LOGGER.error("Error en llamado a WS cambiarClase", e);
			if (e.getCause() == null) {
				throw new DAOException();
			} else if (e.getCause().getClass().equals(SocketTimeoutException.class)) {
				LOGGER.info("La respuesta del servicio de cambiarClase de la tarjeta {} fue TimeOut",
						tarjetaDTO.getNumero().getValue());
				throw new DAOException("TimeOut");
			} else {
				throw new DAOException();
			}
		} finally {
			wsBanelco.liberarPort(cliente);
		}
		LOGGER.info("Fin operacion cambiarClase");
		return response;
	}

}
