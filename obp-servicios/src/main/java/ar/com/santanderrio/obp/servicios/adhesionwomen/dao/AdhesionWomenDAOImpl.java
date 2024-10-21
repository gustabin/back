package ar.com.santanderrio.obp.servicios.adhesionwomen.dao;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.adhesionwomen.entities.TarjetaWomenEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

@Component
public class AdhesionWomenDAOImpl extends IatxBaseDAO implements AdhesionWomenDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionWomenDAOImpl.class);

	@Autowired
	private IatxComm iatxComm;
	
	private static final int LONGITUD_CUENTA = 11;
	
	private static final String OPCION = "A";

	private static final String CATEGORIA = "T";
	
	private static final String DIECISEIS_CEROS = "0000000000000000";
	
	private static final int OK_CODIGO_RETORNO = 0;
	
	private static final String HAY_MAS_DATOS = "S";

	
	public TarjetaWomenEntity consultaDatosTarjetas(Cuenta cuenta) throws DAOException {
		TarjetaWomenEntity respuesta = new TarjetaWomenEntity();
		IatxRequest request = buildIatxRequest(cuenta);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);
			if (iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				respuesta = buildDatosTarjeta(iatxResponse);
				revisarSiHayRellamada(respuesta, cuenta);
			} else {
				throw new DAOException("Error " + iatxResponse.getErrorCode());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException("Error inesperado");
		}
		
		return respuesta;
	}
	
	
	private IatxRequest buildIatxRequest(Cuenta cuenta) {
		IatxRequest requestIatx = new IatxRequest("CNSTJCDATC", "110");
		IatxRequestData requestDataIatx = new IatxRequestData(cuenta.getCliente());
		requestDataIatx.addBodyValue(revisarMarca(cuenta.getTipoCuenta()));
		requestDataIatx.addBodyValue(revisarTipoCuenta(cuenta.getTipoCuenta()));
		requestDataIatx.addBodyValue(StringUtils.leftPad(cuenta.getNroCuentaProducto().substring(5), LONGITUD_CUENTA, '0'));
		requestDataIatx.addBodyValue("    ");
		requestDataIatx.addBodyValue("000");
		requestDataIatx.addBodyValue("0");
		requestDataIatx.addBodyValue("0000000000000");
		requestDataIatx.addBodyValue("00");
		requestDataIatx.addBodyValue(DIECISEIS_CEROS);
		requestDataIatx.addBodyValue(OPCION);
		requestDataIatx.addBodyValue(CATEGORIA);
		requestDataIatx.addBodyValue(DIECISEIS_CEROS);
		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}
	
	private IatxRequest buildIatxRequestRellamada(Cuenta cuenta, TarjetaWomenEntity tarjetaWomenEntity) {
		IatxRequest requestIatx = new IatxRequest("CNSTJCDATC", "110");
		IatxRequestData requestDataIatx = new IatxRequestData(cuenta.getCliente());
		requestDataIatx.addBodyValue(tarjetaWomenEntity.getTipoCuenta());
		requestDataIatx.addBodyValue("1".equals(tarjetaWomenEntity.getTipoCuenta()) ? tarjetaWomenEntity.getTipoCuenta() : "3");
		requestDataIatx.addBodyValue(tarjetaWomenEntity.getNumeroCuentaTC());
		requestDataIatx.addBodyValue("    ");
		requestDataIatx.addBodyValue("000");
		requestDataIatx.addBodyValue("0");
		requestDataIatx.addBodyValue("0000000000000");
		requestDataIatx.addBodyValue("00");
		requestDataIatx.addBodyValue(DIECISEIS_CEROS);
		requestDataIatx.addBodyValue(OPCION);
		requestDataIatx.addBodyValue(CATEGORIA);
		requestDataIatx.addBodyValue(tarjetaWomenEntity.getClaveReenganche());
		requestIatx.setData(requestDataIatx);
		return requestIatx;
	}
	
	private String revisarMarca (String tipoCuenta) {
		if (tipoCuenta.equals("07")) {
			 return "1";
		} else if (tipoCuenta.equals("42")) {
			return "4";
		} else {
			return "3";
		}
	}
	
	private String revisarTipoCuenta (String tipoCuenta) {
		
		if (tipoCuenta.equals("07")) {
			 return "1";
		} else {
			return "3";
		}
	}
	
	private TarjetaWomenEntity buildDatosTarjeta(IatxResponse iatxResponse){
		
		TarjetaWomenEntity tarjetaWomenEntity = new TarjetaWomenEntity();
		
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			tarjetaWomenEntity = processTrama(iatxResponse.getTrama(),
					TarjetaWomenEntity.class);
		}
		return tarjetaWomenEntity;
	}
	
	private void revisarSiHayRellamada (TarjetaWomenEntity tarjetaWomenEntity, Cuenta cuenta) throws IatxException {
				
		if (HAY_MAS_DATOS.equals(tarjetaWomenEntity.getHayMasDatos())) {
			TarjetaWomenEntity tarjetaWomenEntityRellamada = new TarjetaWomenEntity();
			IatxRequest requestRellamada = buildIatxRequestRellamada(cuenta, tarjetaWomenEntity);
			IatxResponse iatxResponseRellamada = iatxComm.exec(requestRellamada);
			if (iatxResponseRellamada.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				tarjetaWomenEntityRellamada = buildDatosTarjeta(iatxResponseRellamada);
			}
			tarjetaWomenEntity.getListaTarjetas().addAll(tarjetaWomenEntityRellamada.getListaTarjetas());
			tarjetaWomenEntity.setHayMasDatos(tarjetaWomenEntityRellamada.getHayMasDatos());
			tarjetaWomenEntity.setClaveReenganche(tarjetaWomenEntityRellamada.getClaveReenganche());
			revisarSiHayRellamada(tarjetaWomenEntity, cuenta);
		} 
	}
	
}
