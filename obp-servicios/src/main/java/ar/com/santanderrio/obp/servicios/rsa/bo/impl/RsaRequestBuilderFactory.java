/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.echeq.dto.EcheqRSADTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.generated.webservices.rsa.APIType;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.AuthorizationMethod;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.DeviceRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventDataList;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.IdentificationData;
import ar.com.santanderrio.obp.generated.webservices.rsa.MessageHeader;
import ar.com.santanderrio.obp.generated.webservices.rsa.RequestType;
import ar.com.santanderrio.obp.generated.webservices.rsa.RunRiskType;
import ar.com.santanderrio.obp.generated.webservices.rsa.SecurityHeader;
import ar.com.santanderrio.obp.generated.webservices.rsa.UserStatus;
import ar.com.santanderrio.obp.generated.webservices.rsa.WSUserType;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioRSADTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteBajaDestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraRSADTO;
import ar.com.santanderrio.obp.servicios.blanqueopin.dto.BlanqueoPinRSADTO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionRsaBanelcoRSADTO;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioClaveUsuarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioDatosContactoView;
import ar.com.santanderrio.obp.servicios.clientes.web.view.CambioUsuarioView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.compraventa.dto.VentaUSDDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.LogIn;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.DebinRecurrenteRSADTO;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.ExtraccionSinTarjetaRSADTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoRsa;
import ar.com.santanderrio.obp.servicios.inversiones.ventameprsa.dto.MepRsaDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTagRSA;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.NuevoPagoAutomatico;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;
import ar.com.santanderrio.obp.servicios.ofuscardato.dto.MostrarDatoDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasRSADTO;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.rsa.bo.AgendamientoTransferenciaRsaRequestBuilder;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendamientoTransferenciaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * A factory for creating Builder objects. Builder para agendar una
 * transferencia ejecutada @see AgendamientoTransferenciaView.
 *
 * @author ignacio.valek
 * @author manuel.vargas B041299
 * @since Nov 11, 2016.
 * @since Jul 26, 2017.
 */
@Component
public class RsaRequestBuilderFactory {

    /** The Constant FACT_TIPODESAFIO. */
    private static final String FACT_TIPODESAFIO = "tipodesafio";

    /** The Constant ORGNAME. */
    private static final String ORGNAME = "OBP";

    /** The Constant RSA_VERSION. */
    public static final String RSA_VERSION = "7.0";

    /** The Constant RSA_SECURITY_ID. */
    private static final String RSA_SECURITY_ID = "RSA";

    /** The Constant ERROR_RSA_CREDENTIALS. */
    private static final String ERROR_RSA_CREDENTIALS = "Error al obtener usuario y password para RSA";

    /** The mapa. */
    private Map<Class<? extends RsaDTO>, RsaRequestBuilder> mapa = new HashMap<Class<? extends RsaDTO>, RsaRequestBuilder>();

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaRequestBuilderFactory.class);

    /** The credential security. */
    @Autowired
    private CredentialSecurityFactory credentialSecurity;

    /**
     * Instantiates a new builder factory.
     *
     */
    public RsaRequestBuilderFactory() {
        mapa.put(TransferenciaView.class, new TransferenciaRsaRequestBuilder());
        mapa.put(LogIn.class, new LogInRsaRequestBuilder());
        mapa.put(Pago.class, new NuevoPagoRsaRequestBuilder());
        mapa.put(NuevoPagoAutomatico.class, new NuevoPagoAutomaticoRsaRequestBuilder());
        mapa.put(AgendaDestinatarioRSADTO.class, new AgendaDestinatarioRsaRequestBuilder());
        mapa.put(ComprobanteRecargaTarjetaView.class, new RecargaTarjetaBuilder());
        mapa.put(TransferenciaAgendadaDetalleView.class, new TransferenciaAgendadaRsaRequestBuilder());
        mapa.put(RescateFondoRsa.class, new RescateFondoRsaRequestBuilder());
        mapa.put(NuevaRecargaRSADTO.class, new NuevaRecargaRsaRequestBuilder());
        mapa.put(AltaTagRSA.class, new AltaTagRsaRequestBuilder());
        mapa.put(BilleteraRSADTO.class, new BilleteraRsaRequestBuilder());
        mapa.put(ComprobanteModificacionLimiteDebitoView.class, new ModificacionLimiteDebitoRequestBuilder());
        mapa.put(CambioClaveUsuarioView.class, new CambioClaveUsuarioInRsaRequestBuilder());
        mapa.put(CambioUsuarioView.class, new CambioUsuarioInRsaRequestBuilder());
        mapa.put(ComprobantePagoHaberesCBUEntity.class, new PagoHaberesCBURsaRequestBuilder());
        mapa.put(PagoInformadoView.class, new PagoHaberesSimpleMultipleRsaRequestBuilder());
        mapa.put(CambioDomicilioView.class, new CambioDomicilioRsaRequestBuilder());
        mapa.put(DatosConfirmadosDelSolicitanteView.class, new SolicitudTarjetaCreditoAdicionalRsaRequestBuilder());
        mapa.put(ComprobanteAltaSolicitudTarjetaRecargableView.class, new SolicitudTarjetaRecargarbleRsaRequestBuilder());
        mapa.put(AgendamientoTransferenciaView.class, new AgendamientoTransferenciaRsaRequestBuilder());
        mapa.put(PreguntasSeguridadView.class, new PreguntasSeguridadRsaRequestBuilder());
        mapa.put(DatosConfirmadosDelSolicitanteView.class, new SolicitudTarjetaCreditoAdicionalRsaRequestBuilder());
        mapa.put(DatosSolicitanteConfirmadoInOutView.class, new AltaTagMonederoRsaRequestBuilder());
        mapa.put(ComprobanteAltaDestinatarioView.class, new AliasInRsaRequestBuilder());
        mapa.put(ComprobanteBajaDestinatarioView.class, new AliasInRsaRequestBuilder());
        mapa.put(ModifTarjetaOperaExtraccionView.class, new ExtraccionYComprasExteriorRsaRequestBuilder());
        mapa.put(AumentoLimiteTransferenciaInOutView.class, new AumentoLimiteTransferenciaRsaRequestBuilder());
        mapa.put(AltaDatosReimpresionTarjetasView.class, new ReimpresionTrjInRsaRequestBuilder());
        mapa.put(DebinView.class, new DebinRsaRequestBuilder());
        mapa.put(PagarDebinWSView.class, new PagarDebinRsaRequestBuilder());
		mapa.put(GestionarAdhesionDebinesView.class, new GestionarAdhesionDebinRsaRequestBuilder());
        mapa.put(SolicitarDebinView.class, new SolicitudDebinRsaRequestBuilder());
        mapa.put(PagoComprasRSADTO.class, new PagoCompraRsaRequestBuilder());
		mapa.put(ProcesarTransferenciaComexView.class, new ProcesarTransferenciaComexRsaRequestBuilder());
		mapa.put(ConfirmarAltaEndosarECheqView.class, new ECheqRsaRequestBuilder());
    	mapa.put(BlanqueoPinRSADTO.class, new BlanqueoPinesRsaRequestBuilder());
    	mapa.put(CambioDatosContactoView.class, new CambioDatosContactoRsaRequestBuilder());
    	mapa.put(ExtraccionSinTarjetaRSADTO.class, new ExtraccionSinTarjetaRsaRequestBuilder());
    	mapa.put(SimuladorPrestamoDTO.class, new PrestamosRsaRequestBuilder());
    	mapa.put(VentaUSDDTO.class, new VentaUSDRsaRequestBuilder());
    	mapa.put(DebinRecurrenteRSADTO.class, new DebinRecurrenteRsaRequestBuilder());
    	mapa.put(MostrarDatoDTO.class, new OfuscacionMailRsaRequestBuilder());
    	mapa.put(ValidacionRsaBanelcoRSADTO.class, new ValidacionRsaBanelcoRequestBuilder());
        mapa.put(EcheqRSADTO.class, new ECheqRsaRequestBuilder());
        mapa.put(MepRsaDTO.class, new MepRsaRequestBuilder());
    }
    	
    /**
     * Gets the builder.
     *
     * @param operacionDeRiesgoClazz
     *            the operacion de riesgo clazz
     * @return the builder
     */
    public RsaRequestBuilder getBuilder(Class<? extends RsaDTO> operacionDeRiesgoClazz) {
        return mapa.get(operacionDeRiesgoClazz);
    }

    /**
     * Gets the request.
     *
     * @param rsaAnalyzeRequestData
     *            the rsa analyze request data
     * @param rsaRequestBuilder
     *            the rsa request builder
     * @return the request
     * @throws BusinessException
     *             the business exception
     */
    public AnalyzeRequest getRequest(RsaAnalyzeRequestData rsaAnalyzeRequestData, RsaRequestBuilder rsaRequestBuilder)
            throws BusinessException {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        fillGenericFields(analyzeRequest, rsaAnalyzeRequestData.getRsaGenericRequestData());
        EventDataList edl = new EventDataList();
        ResumenCliente resumenCliente = rsaAnalyzeRequestData.getRsaGenericRequestData().getResumenCliente();
        RsaDTO operacionDeRiesgo = rsaAnalyzeRequestData.getRsaDTO();
        EventData ed = rsaRequestBuilder.build(operacionDeRiesgo);
        TipoDesafioEnum desafioAplicable = rsaAnalyzeRequestData.getDesafioAplicable();
        if (desafioAplicable != null) {
        	List<ClientDefinedFact> facts;
        	if (ed.getClientDefinedAttributeList() == null) {
        		ed.setClientDefinedAttributeList(new FactList());
        	}
        	facts = ed.getClientDefinedAttributeList().getFact();
			facts.add(generarFactTipoDesafio(desafioAplicable));
        }
        edl.getEventData().add(ed);
        analyzeRequest.setEventDataList(edl);

        IdentificationData id = new IdentificationData();

        id.setUserName(resumenCliente.getNup());
        String userStatus = rsaAnalyzeRequestData.getUserStatus();
        if (userStatus != null && !userStatus.trim().isEmpty()) {
            id.setUserStatus(UserStatus.fromValue(userStatus));
        }
        String userType = rsaAnalyzeRequestData.getWsUserType();
        if (userType != null && !userType.trim().isEmpty()) {
            id.setUserType(WSUserType.fromValue(userType));
        }

        id.setOrgName(ORGNAME);

        id.setUserType(WSUserType.PERSISTENT);

        analyzeRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.ANALYZE);
        mh.setVersion(RSA_VERSION);
        analyzeRequest.setMessageHeader(mh);

        analyzeRequest.setRunRiskType(RunRiskType.ALL);
        return analyzeRequest;
    }

    /**
     * Fill generic fields.
     *
     * @param request
     *            the request
     * @param requestData
     *            the request data
     * @throws BusinessException
     *             the business exception
     */
    private void fillGenericFields(GenericRequest request, RsaGenericRequestData requestData) throws BusinessException {
        DeviceRequest dr = new DeviceRequest();

        dr.setHttpAccept(requestData.getHttpAccept());
        dr.setHttpReferrer(requestData.getHttpReferrer());
        dr.setHttpAcceptLanguage(requestData.getHttpAcceptLanguage());
        dr.setHttpAcceptEncoding(requestData.getHttpAcceptEncoding());
        dr.setHttpAcceptChars(requestData.getHttpAcceptChars());
        dr.setUserAgent(requestData.getUserAgent());
        dr.setDevicePrint(requestData.getDevicePrint());

        String pmdataCookie = requestData.getDeviceTokenCookie();

        if (pmdataCookie != null && !pmdataCookie.trim().isEmpty()) {
            dr.setDeviceTokenCookie(pmdataCookie);
        }

        dr.setIpAddress(requestData.getRemoteIp());
        request.setDeviceRequest(dr);

        SecurityHeader sh = new SecurityHeader();
        Credential credential = null;
        try {
            credential = credentialSecurity.getCredentialBySistema(RSA_SECURITY_ID);
            sh.setCallerCredential(credential.getPassword());
            sh.setCallerId(credential.getUsuario());
            sh.setMethod(AuthorizationMethod.PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("Error de SQL al invocar fillGenericFields", e);
            throw new BusinessException(e, ERROR_RSA_CREDENTIALS);
        }
        request.setSecurityHeader(sh);
    }

	/**
	 * Generar fact tipo desafio.
	 *
	 * @param tipoDesafio the tipo desafio
	 * @return the client defined fact
	 */
	private ClientDefinedFact generarFactTipoDesafio(TipoDesafioEnum tipoDesafio) {
		ClientDefinedFact factTipoDesafio = new ClientDefinedFact();
		factTipoDesafio.setName(FACT_TIPODESAFIO);
		factTipoDesafio.setValue(tipoDesafio.getId());
		factTipoDesafio.setDataType(DataType.STRING);
		return factTipoDesafio;
	}

}
