package ar.com.santanderrio.obp.servicios.echeq.dao.impl;

import java.net.SocketTimeoutException;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.cxf.binding.soap.SoapFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.echeq.EcheqService;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqAmcoDAO;
import ar.com.santanderrio.obp.servicios.echeq.entities.BeneficiarioInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ConsultaImporteTotalesInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.DetalleECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEmitidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEndosadoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqRecibidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.EcheqCedidoInEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class ECheqAmcoDAOImpl.
 */
@Component("eCheqAmcoDAOImpl")
public class ECheqAmcoDAOImpl implements ECheqAmcoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ECheqAmcoDAOImpl.class);

	/** The Constant CANAL. */
	private static final String CANAL = "04";

	/** The Constant SUBCANAL. */
	private static final String SUBCANAL = "99";

	/** The ws echeq service client. */
	@Autowired
	@Qualifier("echeqGestor")
	private GestionarWS<EcheqService> wsEcheqServiceClient;

	@Override
	public List<ResponseFull> obtenerChequesEmitidos(ECheqEmitidoInEntity eCheqEmitidoEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq listIssued");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.listIssued(
				eCheqEmitidoEntity.getCuit(),
				eCheqEmitidoEntity.getChequeNumero(),
				eCheqEmitidoEntity.getEmisorCuit(), 
				eCheqEmitidoEntity.getBeneficiarioDocTipo(),
				eCheqEmitidoEntity.getBeneficiarioDocNro(),
				eCheqEmitidoEntity.getEmisorCbu(),
				eCheqEmitidoEntity.getEstado(),
				eCheqEmitidoEntity.getImporteDesde(), 
				eCheqEmitidoEntity.getImporteHasta(), 
				eCheqEmitidoEntity.getFechaEmisionDesde(), 
				eCheqEmitidoEntity.getFechaEmisionHasta(), 
				eCheqEmitidoEntity.getFechaPagoDesde(), 
				eCheqEmitidoEntity.getFechaPagoHasta(),
				eCheqEmitidoEntity.getNroPagina(), 
				eCheqEmitidoEntity.getCantidadRegistroPagina(), 
				eCheqEmitidoEntity.getOrderby(),
				CANAL,
				SUBCANAL,
				eCheqEmitidoEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssued", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : listIssued", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssued", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssued", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> obtenerChequesRecibidos(ECheqRecibidoInEntity eCheqRecibidoInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq listReceived");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.listReceived(
				eCheqRecibidoInEntity.getCuit(),
				eCheqRecibidoInEntity.getChequeNumero(),
				eCheqRecibidoInEntity.getEmisorCuit(), 
				eCheqRecibidoInEntity.getBeneficiarioDocTipo(),
				eCheqRecibidoInEntity.getBeneficiarioDocNro(),
				eCheqRecibidoInEntity.getEstado(),
				eCheqRecibidoInEntity.getFechaEmisionDesde(), 
				eCheqRecibidoInEntity.getFechaEmisionHasta(), 
				eCheqRecibidoInEntity.getFechaPagoDesde(), 
				eCheqRecibidoInEntity.getFechaPagoHasta(),
				eCheqRecibidoInEntity.getNroPagina(), 
				eCheqRecibidoInEntity.getCantidadRegistroPagina(), 
				eCheqRecibidoInEntity.getOrderby(),
				CANAL,
				SUBCANAL,
				eCheqRecibidoInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceived", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : listReceived", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceived", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceived", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> obtenerChequesEndosados(ECheqEndosadoInEntity eCheqEndosadoInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq listReceivedAndEndorsed");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.listReceivedAndEndorsed(
				eCheqEndosadoInEntity.getCuit(),
				eCheqEndosadoInEntity.getBeneficiarioDocTipo(),
				eCheqEndosadoInEntity.getBeneficiarioDocNro(),
				eCheqEndosadoInEntity.getFechaEmisionDesde(), 
				eCheqEndosadoInEntity.getFechaEmisionHasta(), 
				eCheqEndosadoInEntity.getFechaPagoDesde(), 
				eCheqEndosadoInEntity.getFechaPagoHasta(),
				eCheqEndosadoInEntity.getEstado(),
				eCheqEndosadoInEntity.getChequeNumero(),
				eCheqEndosadoInEntity.getEmisorCuit(),
				eCheqEndosadoInEntity.getCuitBeneficiarioOriginal(),
				eCheqEndosadoInEntity.getNroPagina(), 
				eCheqEndosadoInEntity.getCantidadRegistroPagina(), 
				eCheqEndosadoInEntity.getOrderby(),
				CANAL,
				SUBCANAL,
				eCheqEndosadoInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndEndorsed", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : listReceivedAndEndorsed", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndEndorsed", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndEndorsed", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}
	
	@Override
	public List<ResponseFull> obtenerChequesCedidos(EcheqCedidoInEntity eCheqCedidosInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq listReceivedAndAssignment");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			
			out = service.listReceivedAndAssignment(
				eCheqCedidosInEntity.getCuit(),
				eCheqCedidosInEntity.getFechaEmisionDesde(), 
				eCheqCedidosInEntity.getFechaEmisionHasta(), 
				eCheqCedidosInEntity.getFechaPagoDesde(), 
				eCheqCedidosInEntity.getFechaPagoHasta(),
				eCheqCedidosInEntity.getEstado(),
				eCheqCedidosInEntity.getChequeNumero(),
				eCheqCedidosInEntity.getEmisorCuit(),
				eCheqCedidosInEntity.getNroPagina(), 
				eCheqCedidosInEntity.getCantidadRegistroPagina(), 
				eCheqCedidosInEntity.getOrderby(),
				CANAL,
				SUBCANAL,
				eCheqCedidosInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndAssignment", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : listReceivedAndAssignment", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndAssignment", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listReceivedAndAssignment", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> verificarClienteBancario(BeneficiarioInEntity beneficiarioInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq verifyIsBankClient");
		EcheqService service = null;;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.verifyIsBankClient(
				beneficiarioInEntity.getTipoDocumento(), 
				beneficiarioInEntity.getDocumento(),
				CANAL,
				SUBCANAL,
				beneficiarioInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : verifyIsBankClient", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : verifyIsBankClient", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : verifyIsBankClient", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : verifyIsBankClient", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> consultaImporteTotales(ConsultaImporteTotalesInEntity consultaImporteTotalesInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq consImpTot");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.consImpTot(
				consultaImporteTotalesInEntity.getCuit(),
				consultaImporteTotalesInEntity.getBeneficiarioDocTipo(),
				consultaImporteTotalesInEntity.getBeneficiarioDocNro(),
				consultaImporteTotalesInEntity.getRangoFechaEmision(),
				CANAL,
				SUBCANAL,
				consultaImporteTotalesInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : consImpTot", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : consImpTot", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : consImpTot", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : consImpTot", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> obtenerDetalle(DetalleECheqInEntity detalleECheqInEntity) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq retriveById");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.retriveById(
				detalleECheqInEntity.getChequeId(),
				detalleECheqInEntity.getCuit(),
				CANAL,
				SUBCANAL,
				detalleECheqInEntity.getjSessionId()
				);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : retriveById", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : retriveById", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : retriveById", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : retriveById", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> obtenerChequesEnProceso(String cuit) throws DAOException {
		LOGGER.info("Inicio invocacion ECheq listIssuedExecStat");
		EcheqService service = null;
		List<ResponseFull> out = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			out = service.listIssuedExecStat(cuit);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssuedExecStat", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : listIssuedExecStat", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssuedExecStat", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : listIssuedExecStat", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
		return out;
	}

	@Override
	public List<ResponseFull> obtenerListaEntidadesHabilitadas(String jsessionid) throws DAOException {
		LOGGER.info("Inicio invocacion obtenerListaEntidadesHabilitadas");
		EcheqService service = null;
		try {
			service = wsEcheqServiceClient.obtenerPort();
			/**
			 * String, String, String, Holder<String>, Holder<String>, Holder<CCEStat>, Holder<String>, Holder<String>, Holder<String>, Holder<String>, 
			 * Holder<String>, Holder<Double>, Holder<Double>, Holder<Double>, Holder<Double>, Holder<Double>, Holder<Double>, Holder<Cheques>, Holder<List<Cuenta>>, 
			 * Holder<List<IMF>>
			 * */
			return service.retrieveMarketInfrastructure(CANAL, SUBCANAL, jsessionid);
		} catch (SoapFault sfe) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : obtenerListaEntidadesHabilitadas", sfe);
			throw new DAOException(sfe);
		} catch (WebServiceException wse) {
			if (wse.getCause() instanceof SocketTimeoutException) {
				LOGGER.error("Time Out Error en el WS Echeq AMCO metodo : obtenerListaEntidadesHabilitadas", wse);
				throw new TimeOutException(wse.getMessage());
			}
			LOGGER.error("Error en el WS Echeq AMCO metodo : obtenerListaEntidadesHabilitadas", wse);
			throw new DAOException(wse);
		} catch (Exception e) {
			LOGGER.error("Error en el WS Echeq AMCO metodo : obtenerListaEntidadesHabilitadas", e);
			throw new DAOException(e);
		} finally {
			wsEcheqServiceClient.liberarPort(service);
		}
	}
}
