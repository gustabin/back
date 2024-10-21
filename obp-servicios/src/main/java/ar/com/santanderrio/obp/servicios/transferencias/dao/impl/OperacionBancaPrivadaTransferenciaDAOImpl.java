package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import java.net.SocketTimeoutException;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvc;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvcInsertarTransferenciaEntreBancosOBServiceFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvcInsertarTransferenciaRIORIOOBServiceFaultFaultFaultMessage;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaEntreBancosOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.InsertarTransferenciaRIORIOOBParameter;
import ar.com.santanderrio.obp.generated.webservices.bpriv.common.servicecontracts.ObjectFactory;
import ar.com.santanderrio.obp.servicios.transferencias.dao.OperacionBancaPrivadaTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.InsertarTransferenciaDTO;

@Component
public class OperacionBancaPrivadaTransferenciaDAOImpl implements OperacionBancaPrivadaTransferenciaDAO{

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionBancaPrivadaTransferenciaDAOImpl.class);

	@Autowired
	@Qualifier("gestionOperacionBPTransferencia")
	private GestionarWS<IBancaPrivadaSucursalSvc> client;
	
	/** time out code. */
	private static final String TIME_OUT_CODE = "099";
	
	@Override
	public String insertarTransferenciaRIORIOOB(InsertarTransferenciaDTO insertarTransferenciaDTO) throws DAOException {
		IBancaPrivadaSucursalSvc services = null;
		InsertarTransferenciaRIORIOOBParameter parameter = generarRequestWSInsertarTransferenciaRIORIOOB(insertarTransferenciaDTO);
		try {
			services = client.obtenerPort();
			String resp = services.insertarTransferenciaRIORIOOB(parameter);
			return resp;
		} catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaRIORIOOB con los datos {}.",
                    parameter, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
		} catch (IBancaPrivadaSucursalSvcInsertarTransferenciaRIORIOOBServiceFaultFaultFaultMessage e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaRIORIOOB con los datos {}.",
					parameter, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaRIORIOOB con los datos {}.",
					parameter, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}

	}

	@Override
	public String insertarTransferenciaEntreBancosOB(InsertarTransferenciaDTO insertarTransferenciaDTO) throws DAOException {
		IBancaPrivadaSucursalSvc services = null;
		InsertarTransferenciaEntreBancosOBParameter parameter = generarRequestWSInsertarTransferenciaEntreBancosOB(insertarTransferenciaDTO);
		try {
			services = client.obtenerPort();
			String resp = services.insertarTransferenciaEntreBancosOB(parameter);
			return resp;
		} catch (WebServiceException e) {
            LOGGER.error(
                    "WebServiceException Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaEntreBancosOB con los datos {}.",
                    parameter, e);
            if (e.getCause().getCause()  instanceof SocketTimeoutException) {
                throw new DAOException(e.getMessage(), TIME_OUT_CODE); 
            }
            throw new DAOException(e);
		} catch (IBancaPrivadaSucursalSvcInsertarTransferenciaEntreBancosOBServiceFaultFaultFaultMessage e) {
			LOGGER.error(
					"DebinApiException_Exception Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaEntreBancosOB con los datos {}.",
					parameter, e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error(
					"RuntimeException Hubo un error al invocar al ws de Banca Privada para la operacion insertarTransferenciaEntreBancosOB con los datos {}.",
					parameter, e);
			throw new DAOException(e);
		} finally {
			client.liberarPort(services);
		}
	}
	
	private InsertarTransferenciaEntreBancosOBParameter generarRequestWSInsertarTransferenciaEntreBancosOB(InsertarTransferenciaDTO insertarTransferenciaDTO) {
		InsertarTransferenciaEntreBancosOBParameter request = new InsertarTransferenciaEntreBancosOBParameter();
		ObjectFactory of = new ObjectFactory();
		
		request.setBeneficiario(of.createInsertarTransferenciaEntreBancosOBParameterBeneficiario(insertarTransferenciaDTO.getBeneficiario()));
		request.setCbuDest(of.createInsertarTransferenciaEntreBancosOBParameterCbuDest(insertarTransferenciaDTO.getCbuDestino()));
		request.setConcepto(of.createInsertarTransferenciaEntreBancosOBParameterConcepto(insertarTransferenciaDTO.getConcepto()));
		request.setCuentaAltairDest(of.createInsertarTransferenciaEntreBancosOBParameterCuentaAltairDest(insertarTransferenciaDTO.getCuentaAltairDest()));
		request.setCuentaAltairOrig(of.createInsertarTransferenciaEntreBancosOBParameterCuentaAltairOrig(insertarTransferenciaDTO.getCuentaAltairOrig()));
		request.setDocumentoDest(of.createInsertarTransferenciaEntreBancosOBParameterDocumentoDest(insertarTransferenciaDTO.getDocumentoDest()));
		request.setDocumentoOrig(of.createInsertarTransferenciaEntreBancosOBParameterDocumentoOrig(insertarTransferenciaDTO.getDocumentoOrig()));
		request.setEntidadDest(of.createInsertarTransferenciaEntreBancosOBParameterEntidadDest(insertarTransferenciaDTO.getEntidadDest()));
		request.setImporte(of.createInsertarTransferenciaEntreBancosOBParameterImporte(insertarTransferenciaDTO.getImporte()));
		request.setModoTransf(of.createInsertarTransferenciaEntreBancosOBParameterModoTransf(insertarTransferenciaDTO.getModoTranf()));
		request.setMoneda(of.createInsertarTransferenciaEntreBancosOBParameterMoneda(insertarTransferenciaDTO.getMoneda()));
		request.setObservaciones(of.createInsertarTransferenciaEntreBancosOBParameterObservaciones(insertarTransferenciaDTO.getObservaciones()));
		request.setOper(of.createInsertarTransferenciaEntreBancosOBParameterOper(insertarTransferenciaDTO.getOper()));
		request.setSucursalDest(of.createInsertarTransferenciaEntreBancosOBParameterSucursalDest(insertarTransferenciaDTO.getSucursalDest()));
		request.setTipoCuentaDest(of.createInsertarTransferenciaEntreBancosOBParameterTipoCuentaDest(insertarTransferenciaDTO.getTipoCuentaDest()));
		request.setTipoDocDest(of.createInsertarTransferenciaEntreBancosOBParameterTipoDocDest(insertarTransferenciaDTO.getTipoDocDest()));
		request.setTipoOrd(of.createInsertarTransferenciaEntreBancosOBParameterTipoOrd(insertarTransferenciaDTO.getTipoOrd()));

		return request;
	}
	
	private InsertarTransferenciaRIORIOOBParameter generarRequestWSInsertarTransferenciaRIORIOOB(InsertarTransferenciaDTO insertarTransferenciaDTO) {
		InsertarTransferenciaRIORIOOBParameter request = new InsertarTransferenciaRIORIOOBParameter();
		ObjectFactory of = new ObjectFactory();
		request.setBeneficiario(of.createInsertarTransferenciaRIORIOOBParameterBeneficiario(insertarTransferenciaDTO.getBeneficiario()));
		request.setCbuDestino(of.createInsertarTransferenciaRIORIOOBParameterCbuDestino(insertarTransferenciaDTO.getCbuDestino()));
		request.setComision(of.createInsertarTransferenciaRIORIOOBParameterComision(insertarTransferenciaDTO.getComicion()));
		request.setConcepto(of.createInsertarTransferenciaRIORIOOBParameterConcepto(insertarTransferenciaDTO.getConcepto()));
		request.setCuentaAltairDest(of.createInsertarTransferenciaRIORIOOBParameterCuentaAltairDest(insertarTransferenciaDTO.getCuentaAltairDest()));
		request.setCuentaAltairOrig(of.createInsertarTransferenciaRIORIOOBParameterCuentaAltairOrig(insertarTransferenciaDTO.getCuentaAltairOrig()));
		request.setCuitDestino(of.createInsertarTransferenciaRIORIOOBParameterCuitDestino(insertarTransferenciaDTO.getCuitDestino()));
		request.setImporte(of.createInsertarTransferenciaRIORIOOBParameterImporte(insertarTransferenciaDTO.getImporte()));
		request.setMoneda(of.createInsertarTransferenciaRIORIOOBParameterMoneda(insertarTransferenciaDTO.getMoneda()));
		request.setObservaciones(of.createInsertarTransferenciaRIORIOOBParameterObservaciones(insertarTransferenciaDTO.getObservaciones()));
		request.setOper(of.createInsertarTransferenciaRIORIOOBParameterOper(insertarTransferenciaDTO.getOper()));
		request.setSucursalDest(of.createInsertarTransferenciaRIORIOOBParameterSucursalDest(insertarTransferenciaDTO.getSucursalDest()));
		request.setSucursalOrig(of.createInsertarTransferenciaRIORIOOBParameterSucursalOrig(insertarTransferenciaDTO.getSucursalOrig()));
		request.setTipoOrd(of.createInsertarTransferenciaRIORIOOBParameterTipoOrd(insertarTransferenciaDTO.getTipoOrd()));
		return request;
	}
}
