package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidacionPinBanelcoDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.SeleccionTarjetaBanelcoInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.SeleccionTarjetaBanelcoOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidarPinBanelcoOutEntity;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;

@Component
public class ValidacionPinBanelcoDAOImpl extends IatxBaseDAO implements ValidacionPinBanelcoDAO {

	@Value("${SERVICIO.PREFIJO.CNSSGITRJB}")
	private String servicioCnssgitrjb;

	@Value("${SERVICIO.VERSION.CNSSGITRJB}")
	private String versionCnssgitrjb;
	
	@Value("${SERVICIO.PREFIJO.VALSGIPINB}")
	private String servicioValsgipinb;

	@Value("${SERVICIO.VERSION.VALSGIPINB}")
	private String versionValsgipinb;
	
	@Autowired
	private IatxComm iatxComm;

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionPinBanelcoDAOImpl.class);

	private static final int OK_CODIGO_RETORNO = 0;
		
	private static final String LETRA_A = "A";
	
    @Autowired
    private EncryPines encryPines;
	
	@Override
	public String obtenerTarjetaParaValidarPin(SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn) throws DAOException {
		LOGGER.info("Buscamos tarjeta Banelco a validar pin");
		IatxRequest request = new IatxRequest(servicioCnssgitrjb, versionCnssgitrjb);

        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setNup(seleccionTarjetaIn.getNup());
        resumenCliente.setDni(seleccionTarjetaIn.getDni());
        resumenCliente.setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(seleccionTarjetaIn.getFechaNacimiento()));
        IatxRequestData requestData = new IatxRequestData(resumenCliente);
        requestData.setIndAutenticacion();
        requestData.setRacfInicial();
        
        requestData.addBodyValue(seleccionTarjetaIn.getFuncion());
        requestData.addBodyValue(seleccionTarjetaIn.getIdSesion());
        requestData.addBodyValue(seleccionTarjetaIn.getIp());
        
        request.setData(requestData);

		IatxResponse iatxResponse;
		try {
			iatxResponse = iatxComm.exec(request);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				SeleccionTarjetaBanelcoOutEntity respuesta = processTrama(iatxResponse.getTrama(), SeleccionTarjetaBanelcoOutEntity.class);
				LOGGER.info("Busqueda exitosa!");
				return respuesta.getNumeroTarjeta();
			} else {
				LOGGER.info("No corresponde validar tarjeta Banelco");
				throw new DAOException("Error al consultar la tarjeta Banelco a validar", String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			throw new DAOException("IATXException al consultar la tarjeta Banelco a validar");
		}

	}
	
	@Override
	public String validarPinBanelco(SeleccionTarjetaBanelcoInEntity seleccionTarjetaIn, DatosAutenticacionInEntity datosAutenticacionInEntity) throws DAOException {
		
		IatxRequest request = new IatxRequest(servicioValsgipinb, versionValsgipinb);

        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setNup(seleccionTarjetaIn.getNup());
        resumenCliente.setDni(seleccionTarjetaIn.getDni());
        resumenCliente.setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(seleccionTarjetaIn.getFechaNacimiento()));
        IatxRequestData requestData = new IatxRequestData(resumenCliente);
        requestData.setIndAutenticacion();
        requestData.setRacfInicial();
        
        String bloqueCifrado = obtenerDatosCifrados(datosAutenticacionInEntity);
        String bloqueCifradoConEspaciosAdicionados = String.format("%-256s", bloqueCifrado);

        requestData.addBodyValue(datosAutenticacionInEntity.getFuncionEnum().getCodigo());
        requestData.addBodyValue(datosAutenticacionInEntity.getIp());
        requestData.addBodyValue(datosAutenticacionInEntity.getSesion());
        requestData.addBodyValue(datosAutenticacionInEntity.getCiclo());
        requestData.addBodyValue(bloqueCifradoConEspaciosAdicionados);
        
        request.setData(requestData);

		IatxResponse iatxResponse;
		try {
			iatxResponse = iatxComm.exec(request);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				ValidarPinBanelcoOutEntity respuesta = processTrama(iatxResponse.getTrama(), ValidarPinBanelcoOutEntity.class);
				return respuesta.getCodigoRetornoExtendido();
			} else {
				return Integer.toString(errorCode);
			}
		} catch (IatxException e) {
			throw new DAOException("IATXException al intentar validar el pin Banelco");
		}

	}
	
    private String obtenerDatosCifrados(DatosAutenticacionInEntity datosAutenticacionInEntity) {

        String retorno = datosAutenticacionInEntity.getLetraA() + datosAutenticacionInEntity.getTipoAut()
                + datosAutenticacionInEntity.getNumero() + datosAutenticacionInEntity.getClave() + LETRA_A;

        String datosPorCifrar = StringUtils.rightPad(retorno, 128, " ");
        return encryPines.obtenerCadena3Des(datosPorCifrar);
    }
	
}
