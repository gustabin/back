package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import java.io.UnsupportedEncodingException;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ICanalesOrPagoService;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ICanalesOrPagoServiceProcesarOrPagoOBPBaseFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.FirmaRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales.ProcesarOrPagoOBPRequest;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.response.canales.ProcesarOrPagoOBPResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexOrdenPagoDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarOrPagoOBPInEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;

@Component
public class ComexOrdenPagoDAOImpl implements ComexOrdenPagoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexOrdenPagoDAOImpl.class);

	/** The wsOrdenPagoClient. */
	@Autowired
	@Qualifier("comexOrdenPagoGestor")
	private GestionarWS<ICanalesOrPagoService> wsOrdenPagoClient;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The Constant JKS_COMEX_ORDENPAGO. */
	private static final String JKS_COMEX_ORDENPAGO = "TRANSFEXT.ORDENPAGO";

	/** The Constant FIRMA_DATOS_DENTRO. */
	private static final String FIRMA_DATOS_DENTRO_S = "S";

	/** The Constant FIRMA_FORMATO_B64. */
	private static final String FIRMA_FORMATO_B64 = "B64";

	@Override
	public ProcesarOrPagoOBPResponse procesarOrdenPago(ProcesarOrPagoOBPInEntity inEntity) throws DAOException {
		ICanalesOrPagoService service = null;
		ProcesarOrPagoOBPResponse response;
		try {
			ProcesarOrPagoOBPRequest request = crearProcesarOrPagoOBPRequest(inEntity);
			service = wsOrdenPagoClient.obtenerPort();
			response = service.procesarOrPagoOBP(request);
			LOGGER.info("Respuesta del WS: {}.", response);
			return response;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (WebServiceException e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} catch (ICanalesOrPagoServiceProcesarOrPagoOBPBaseFaultFaultFaultMessage e) {
			LOGGER.error("Excepcion al consultar el WS", e);
			throw new DAOException(e);
		} finally {
			wsOrdenPagoClient.liberarPort(service);
		}
	}

	private ProcesarOrPagoOBPRequest crearProcesarOrPagoOBPRequest(ProcesarOrPagoOBPInEntity inEntity)
			throws UnsupportedEncodingException, DAOException {
		ProcesarOrPagoOBPRequest request = new ProcesarOrPagoOBPRequest();
		FirmaRequest firmaRequest = obtenerFirmaRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales.ObjectFactory objectFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.canales.ObjectFactory();
		request.setAceptaDdjj(objectFactory.createProcesarOrPagoOBPRequestAceptaDdjj(inEntity.getAceptaDdjj()));
		request.setConcepto(objectFactory.createProcesarOrPagoOBPRequestConcepto(inEntity.getConcepto()));
		request.setCuentaCredito(
				objectFactory.createProcesarOrPagoOBPRequestCuentaCredito(inEntity.getCuentaCredito()));
		request.setFirmaDatos(firmaRequest.getFirmaDatos());
		request.setFirmaDatosDentro(firmaRequest.getFirmaDatosDentro());
		request.setFirmaFormato(firmaRequest.getFirmaFormato());
		request.setFirmaHash(firmaRequest.getFirmaHash());
		request.setImportePago(objectFactory.createProcesarOrPagoOBPRequestImportePago(inEntity.getImportePago()));
		request.setNroDocCliente(
				objectFactory.createProcesarOrPagoOBPRequestNroDocCliente(inEntity.getNroDocCliente()));
		request.setNroForm(objectFactory.createProcesarOrPagoOBPRequestNroForm(inEntity.getNroForm()));
		request.setNroOperacion(objectFactory.createProcesarOrPagoOBPRequestNroOperacion(inEntity.getNroOperacion()));
		request.setNupCliente(objectFactory.createProcesarOrPagoOBPRequestNupCliente(inEntity.getNupCliente()));
		request.setRazonSocial(objectFactory.createProcesarOrPagoOBPRequestRazonSocial(inEntity.getRazonSocial()));
		request.setTipoDocCliente(
				objectFactory.createProcesarOrPagoOBPRequestTipoDocCliente(inEntity.getTipoDocCliente()));
		request.setEmpresaVinculada(objectFactory.createProcesarOrPagoOBPRequestEmpresaVinculada(inEntity.getEmpresaVinculada()));
		return request;
	}

	private FirmaRequest obtenerFirmaRequest() throws UnsupportedEncodingException, DAOException {
		FirmaRequest firmaRequest = new FirmaRequest();
		ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.ObjectFactory objectFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.contracts.request.ObjectFactory();
		firmaRequest.setFirmaDatos(objectFactory.createFirmaRequestFirmaDatos(obtenerFirmaDatosXML()));
		firmaRequest.setFirmaDatosDentro(objectFactory.createFirmaRequestFirmaDatosDentro(FIRMA_DATOS_DENTRO_S));
		firmaRequest.setFirmaFormato(objectFactory.createFirmaRequestFirmaFormato(FIRMA_FORMATO_B64));
		firmaRequest.setFirmaHash(objectFactory.createFirmaRequestFirmaHash(obtenerFirma(obtenerFirmaDatosXML())));
		return firmaRequest;
	}

	private String obtenerFirma(String param) throws UnsupportedEncodingException, DAOException {
		return new String(sign.buildB64Signature(param.getBytes(appEncoding), JKS_COMEX_ORDENPAGO, true));
	}

	private String obtenerFirmaDatosXML() {
		org.dom4j.Document doc = org.dom4j.DocumentHelper.createDocument();
		org.dom4j.Element datos = doc.addElement("datos");
		datos.addElement("nup").setText(sesionCliente.getCliente().getNup());
		datos.addElement("tipodocumento").setText(sesionCliente.getCliente().getTipoDocumento());
		datos.addElement("numerodocumento").setText(sesionCliente.getCliente().getDni());
		return datos.asXML();
	}

}
