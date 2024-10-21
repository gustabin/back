package ar.com.santanderrio.obp.servicios.blanqueopin.bo.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mq.webservices.GestionarMQ;
import ar.com.santanderrio.obp.base.mq.webservices.ISBANMQOperacion;
import ar.com.santanderrio.obp.generated.webservices.banelco.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTarjetaDTO;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSTerminalData;
import ar.com.santanderrio.obp.generated.webservices.banelco.WSUserData;
import ar.com.santanderrio.obp.servicios.blanqueopin.bo.BlanqueoPinBO;
import ar.com.santanderrio.obp.servicios.blanqueopin.dao.BlanqueoPinDAO;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinEnum;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.BlanqueoPinMensajeMQ;
import ar.com.santanderrio.obp.servicios.blanqueopin.entities.TipoDocumentoBlanqueoPin;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class BlanqueoPinBOImpl.
 */
@Component
public class BlanqueoPinBOImpl implements BlanqueoPinBO {

	/** The blanqueo pin DAO. */
	@Autowired
	private BlanqueoPinDAO blanqueoPinDAO;

    /** The gestionar MQ. */
    @Autowired
    private GestionarMQ gestionarMQ;

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BlanqueoPinBOImpl.class);
    
	/** The Constant ESTADO_TARJETA. */
	private static final String ESTADO_TARJETA = "01";

	/** The Constant VISA. */
	private static final String VISA = "VISA";

	private static final String ENTITLEMENT_MQ = "Rio/Seguridad_BlanqueoPIN_BANELCO";
	
	private static final String TTL_MQ = "086400";
	
	private static final String LEGAL_TYPE_MQ = "INDIVIDUO";
	
	private static final String FORMATO_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.blanqueopin.bo.BlanqueoPinBO#obtenerTarjetasBanelco(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public List<DetalleTarjetaDTO> obtenerTarjetasBanelco(Cliente cliente) {
		List<DetalleTarjetaDTO> tarjetas = new ArrayList<DetalleTarjetaDTO>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
					&& StringUtils.equals(ESTADO_TARJETA, cuenta.getEstadoTarjetaCredito())) {
				DetalleTarjetaDTO tarjeta = new DetalleTarjetaDTO();
				tarjeta.setMarca(VISA);
				tarjeta.setNroTarjetaConFormato(
						TarjetaUtils.cortarYFormatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), VISA));
				tarjetas.add(tarjeta);
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && StringUtils.equals(ESTADO_TARJETA, cuenta.getEstadoTarjetaCredito())) {
                DetalleTarjetaDTO tarjeta = new DetalleTarjetaDTO();
                tarjeta.setMarca(VISA);
                tarjeta.setNroTarjetaConFormato(
                        TarjetaUtils.cortarYFormatearNumeroTarjeta(cuenta.getNroTarjetaCredito(), VISA));
                tarjetas.add(tarjeta);
            }
        }
		return tarjetas;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.blanqueopin.bo.BlanqueoPinBO#blanquearPin(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView, java.lang.String)
	 */
	@Override
	public Boolean blanquearPin(Cliente cliente, BlanqueoPinView blanqueoPin, String ip) throws BusinessException {
		Cuenta cuentaSeleccionada = obtenerCuentaBanelcoSeleccionada(blanqueoPin.getNumeroTarjeta(), cliente);
		if (cuentaSeleccionada == null) {
			return false;
		}
		ObjectFactory objectFactory = new ObjectFactory();
		WSTerminalData terminalData = obtenerTerminalData(objectFactory, ip);
		WSUserData userData = obtenerUserData(objectFactory, cliente);
		WSTarjetaDTO tarjetaDTO = obtenerTarjetaDTO(objectFactory, cuentaSeleccionada);
		String respuesta;
		try {
			respuesta = blanqueoPinDAO.blanquearPin(terminalData, userData, tarjetaDTO, BlanqueoPinEnum.buscarEnumPorTipo(blanqueoPin.getTipoBlanqueo()));
			if (!StringUtils.equals(respuesta, "00")) {
				return false;
			}
		} catch (DAOException e) {
			if ("TimeOut".equals(e.getMessage())) {
				throw new BusinessException();
			}
			return false;
		}
		enviarMensajeSeguridad(cliente, cuentaSeleccionada);
		return true;
	}

	/**
	 * Obtener cuenta banelco seleccionada.
	 *
	 * @param numeroTarjeta the numero tarjeta
	 * @param cliente the cliente
	 * @return the cuenta
	 * @throws BusinessException the business exception
	 */
	private Cuenta obtenerCuentaBanelcoSeleccionada(String numeroTarjeta, Cliente cliente) throws BusinessException {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
					&& StringUtils.equals(ESTADO_TARJETA, cuenta.getEstadoTarjetaCredito()) && StringUtils.equals(
							StringUtils.right(numeroTarjeta, 4), StringUtils.right(cuenta.getNroTarjetaCredito(), 4))) {
				return cuenta;
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && ESTADO_TARJETA.equals(cuenta.getEstadoTarjetaCredito()) && StringUtils.equals(
                            StringUtils.right(numeroTarjeta, 4), StringUtils.right(cuenta.getNroTarjetaCredito(), 4))) {
                return cuenta;
            }
		}
		return null;
	}

	/**
	 * Obtener tarjeta DTO.
	 *
	 * @param objectFactory the object factory
	 * @param cuentaSeleccionada the cuenta seleccionada
	 * @return the WS tarjeta DTO
	 */
	private WSTarjetaDTO obtenerTarjetaDTO(ObjectFactory objectFactory, Cuenta cuentaSeleccionada) {
		WSTarjetaDTO tarjetaDTO = objectFactory.createWSTarjetaDTO();
		tarjetaDTO.setMiembro(objectFactory.createWSTarjetaDTOMiembro("001"));
		String numeroTarjeta = cuentaSeleccionada.getNroTarjetaCredito()
		.substring(cuentaSeleccionada.getNroTarjetaCredito().length()-16, cuentaSeleccionada.getNroTarjetaCredito().length());
		tarjetaDTO.setNumero(objectFactory.createWSTarjetaDTONumero(numeroTarjeta));
		return tarjetaDTO;
	}

	/**
	 * Obtener user data.
	 *
	 * @param objectFactory the object factory
	 * @param cliente the cliente
	 * @return the WS user data
	 */
	private WSUserData obtenerUserData(ObjectFactory objectFactory, Cliente cliente) {
		WSUserData userData = objectFactory.createWSUserData();
		userData.setNumeroDocumento(objectFactory.createWSUserDataNumeroDocumento(cliente.getDni()));
		userData.setTipoDocumento(objectFactory.createWSUserDataTipoDocumento(
				TipoDocumentoBlanqueoPin.obtenerDatoParaBanelco(cliente.getTipoDocumento())));
		return userData;
	}

	/**
	 * Obtener terminal data.
	 *
	 * @param objectFactory the object factory
	 * @param ip the ip
	 * @return the WS terminal data
	 */
	private WSTerminalData obtenerTerminalData(ObjectFactory objectFactory, String ip) {
		WSTerminalData terminalData = objectFactory.createWSTerminalData();
		terminalData.setDatosTerminal(objectFactory.createWSTerminalDataDatosTerminal("04"));
		terminalData.setIpOrigen(objectFactory.createWSTerminalDataIpOrigen(ip));
		return terminalData;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		String numeroTarjeta = "00004517660063696412";
		
		System.out.println(numeroTarjeta.substring(numeroTarjeta.length()-16, numeroTarjeta.length()));
		
	}

	/**
	 * Enviar mensaje seguridad.
	 */
	private void enviarMensajeSeguridad (Cliente cliente, Cuenta cuentaSeleccionada) {
		try {
			String nroTrj = cuentaSeleccionada.getNroTarjetaCredito().substring(
					cuentaSeleccionada.getNroTarjetaCredito().length()-4, cuentaSeleccionada.getNroTarjetaCredito().length());
			nroTrj = "XXXX-"+nroTrj;
			
            Date fechaHoy = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat(FORMATO_TIMESTAMP);  
            String strFechaHoy = dateFormat.format(fechaHoy);  
	
			BlanqueoPinMensajeMQ mensaje = new BlanqueoPinMensajeMQ();			
			mensaje.setNup(cliente.getNup());
			mensaje.setDni(cliente.getDni());
			mensaje.setEntitlement(ENTITLEMENT_MQ);
			mensaje.setTimestamp(strFechaHoy);
			mensaje.setLegal_type(LEGAL_TYPE_MQ);
			mensaje.setTtl(TTL_MQ);
			mensaje.setNombre(cliente.getNombre());
			mensaje.setApellido(cliente.getApellido1());
			mensaje.setNro_tarjeta(nroTrj);
						
			gestionarMQ.sendMessage(ISBANMQOperacion.BLANQUEOPIN, mensaje.getAsDOM().asXML());
		} catch (Exception e) {
			LOGGER.error("Error en llamado a WS blanquearPin", e);
		}
	} 
}
