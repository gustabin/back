package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import java.math.BigDecimal;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvc;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvcInsertarOperacionCambioServiceFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarOperacionCambioParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.ObjectFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionBancaPrivadaDAO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.HabilitacionCompraVentaDolaresBPEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.RequestBancaPrivadaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * OperacionBancaPrivadaDAOImpl
 */
@Component
public class OperacionBancaPrivadaDAOImpl extends IatxBaseDAO implements OperacionBancaPrivadaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionBancaPrivadaDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	@Autowired
	@Qualifier("gestionOperacionBP")
	private GestionarWS<IBancaPrivadaSucursalSvc> client;

	/** The servicio consulta cotizacion. */
	@Value("${SERVICIO.PREFIJO.CNSMISCEL}")
	private String servicioConsultaHabilitacionBP;

	/** The version 100 cons cotizacion. */
	@Value("${SERVICIO.VERSION.CNSMISCEL}")
	private String version110HabilitaccionBP;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	private String canalBancaPrivada = "79";

	private String subCcanalBancaPrivada = "00";

	/** time out code. */
	private static final String TIME_OUT_CODE = "099";
	
	/** The detalle CompraVentaDolaresUtil. */
	@Autowired
	private CompraVentaDolaresUtil compraVentaDolaresUtil;

	@Override
	public String insertarOperacionCambio(ParametrosOperacion parametrosOperacion,
			OperacionCompraVentaDatosEntrada datosEntrada, RequestBancaPrivadaEntity entity)  {
		IBancaPrivadaSucursalSvc services = null;
		InsertarOperacionCambioParameter parameter = generarRequestWSInsertarOperacionCambio(parametrosOperacion,datosEntrada,entity);
		String respuesta = null;
		
		try {
			services = client.obtenerPort();
		 respuesta = services.insertarOperacionCambio(parameter);
		} catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Banca Privada para la operacion insertarOperacionCambio con los datos {}.",
                    parameter, e);
		}  catch (IBancaPrivadaSucursalSvcInsertarOperacionCambioServiceFaultFaultFaultMessage e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Banca Privada para la operacion insertarOperacionCambio con los datos {}.",
					parameter, e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Banca Privada para la operacion insertarOperacionCambio con los datos {}.",
					parameter, e);
 		} catch (DAOException e) {
 			LOGGER.error(
					"DAOException Hubo un error al invocar al ws de Banca Privada para la operacion insertarOperacionCambio con los datos {}.",
					parameter, e);
			e.printStackTrace();
		} finally {
			try {
				client.liberarPort(services);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return respuesta;

	}
	private InsertarOperacionCambioParameter generarRequestWSInsertarOperacionCambio(ParametrosOperacion parametrosOperacion,
			OperacionCompraVentaDatosEntrada datosEntrada, RequestBancaPrivadaEntity entity){
		InsertarOperacionCambioParameter request = new InsertarOperacionCambioParameter();
		ObjectFactory of = new ObjectFactory();
		request.setBoleto(of.createInsertarOperacionCambioParameterBoleto(entity.getBoleto()));
		try {
			if("V".equals(entity.getTipoOrd())){
				request.setCantTitu8(of.createInsertarOperacionCambioParameterCantTitu8(ISBANStringUtils.convertirStrToBigDecimal(entity.getCanTitu(),2).negate()));

			} else{
				request.setCantTitu8(of.createInsertarOperacionCambioParameterCantTitu8(ISBANStringUtils.convertirStrToBigDecimal(entity.getCanTitu(),2)));

			}
		} catch (ImporteConvertException e1) {
		} 
		try {
			request.setCapital(of.createInsertarOperacionCambioParameterCapital(ISBANStringUtils.convertirStrToBigDecimal(entity.getCapital(),2)));
		} catch (ImporteConvertException e1) {
		} 
		request.setCuentaDest(of.createInsertarOperacionCambioParameterCuentaDest(ISBANStringUtils.sacarCerosYBlancosIzq(parametrosOperacion.getCuentaDestino().getNroCuentaProducto()))); 
		request.setCuentaOri(of.createInsertarOperacionCambioParameterCuentaOri(ISBANStringUtils.sacarCerosYBlancosIzq(parametrosOperacion.getCuentaOrigen().getNroCuentaProducto())));
		request.setCuitCuil(of.createInsertarOperacionCambioParameterCuitCuil("CUIT"));
		request.setEspecie(of.createInsertarOperacionCambioParameterEspecie("70021"));
		try {
			
			if ("V".equals(entity.getTipoOrd())) {
				String preUni = ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),7).toString().substring(0, 7);
				request.setFciTipoCambio(of.createInsertarOperacionCambioParameterFciTipoCambio(new BigDecimal(preUni)));	
			} else {
				request.setFciTipoCambio(of.createInsertarOperacionCambioParameterFciTipoCambio(ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),4)));	
			}
		} catch (ImporteConvertException e) {
		}
		request.setMoneLiq(of.createInsertarOperacionCambioParameterMoneLiq("P"));
		request.setNroCuitCuil(of.createInsertarOperacionCambioParameterNroCuitCuil(ISBANStringUtils.eliminarGuionesDeCuil(parametrosOperacion.getCliente().getNumeroCUILCUIT())));
		request.setNumMercap(of.createInsertarOperacionCambioParameterNumMercap(datosEntrada.getCodigoconcepto().trim()));
		request.setNup(of.createInsertarOperacionCambioParameterNup(parametrosOperacion.getCliente().getNup()));
		request.setOper(of.createInsertarOperacionCambioParameterOper("BCAI"));
		request.setOrigenOrden(of.createInsertarOperacionCambioParameterOrigenOrden("SI"));
		try {
			if ("V".equals(entity.getTipoOrd())) {
				String preUni = ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),7).toString().substring(0, 7);
				request.setPrecUni8(of.createInsertarOperacionCambioParameterPrecUni8(new BigDecimal(preUni)));	
			} else {
				request.setPrecUni8(of.createInsertarOperacionCambioParameterPrecUni8(ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),4)));	
			}	
		} catch (ImporteConvertException e) {
			
		} 
		request.setSucuDest(of.createInsertarOperacionCambioParameterSucuDest(ISBANStringUtils.sacarCerosYBlancosIzq(parametrosOperacion.getCuentaDestino().getNroSucursal())));
		request.setSucuOri(of.createInsertarOperacionCambioParameterSucuOri(ISBANStringUtils.sacarCerosYBlancosIzq(parametrosOperacion.getCuentaOrigen().getNroSucursal())));
		request.setTipoOrd(of.createInsertarOperacionCambioParameterTipoOrd(entity.getTipoOrd())); 
		try {
			if ("V".equals(entity.getTipoOrd())) {
				String preUni = ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),7).toString().substring(0, 7);
				request.setValorInterno8(of.createInsertarOperacionCambioParameterValorInterno8(new BigDecimal(preUni)));	
			} else {
				request.setValorInterno8(of.createInsertarOperacionCambioParameterValorInterno8(ISBANStringUtils.convertirStrToBigDecimal(entity.getPrecUni(),4)));	
			}
		} catch (ImporteConvertException e) {
			
		}
		return request;
	}

	public HabilitacionCompraVentaDolaresBPEntity obtenerHabilitacionCuentaBP(Cliente cliente, Cuenta cuenta)
			throws DAOException {
		IatxRequest iatxRequest = new IatxRequest(servicioConsultaHabilitacionBP, version110HabilitaccionBP);
		IatxRequestData requestData = new IatxRequestData(cliente);
		
		String indicador = "B";
		requestData.setCanalTipo(canalBancaPrivada);
		requestData.setSubCanalTipo(subCcanalBancaPrivada);
		requestData.addBodyValue(cuenta.getSucursalPaquete());
		requestData.addBodyValue(compraVentaDolaresUtil.obtenerAplicacion(cuenta, CompraVentaStringUtil.OPERACION_COMPRA));
		requestData.addBodyValue(StringUtils.right(cuenta.getNroCuentaProducto(), 12));
		requestData.addBodyValue(indicador);
		iatxRequest.setData(requestData);
		IatxResponse iatxResponse = null;
		try {
			iatxResponse = iatxComm.exec(iatxRequest);
		} catch (IatxException e) {
			e.printStackTrace();
		}
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			HabilitacionCompraVentaDolaresBPEntity response = processTrama(iatxResponse.getTrama(),
					HabilitacionCompraVentaDolaresBPEntity.class);
			return response;
		}
		return null;
	}

}
