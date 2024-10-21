/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeqapi.model.Client;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.Form;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.CasoSFServiceConnector;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.monedero.service.DatosSolicitanteService;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO;
import ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager;
import ar.com.santanderrio.obp.servicios.solicitudes.view.BeneficioTransferiTuSueldoView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.CuentaMontoBeneficioView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.MensajeFeedbackOk;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

import javax.crypto.ExemptionMechanism;

import static ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta.CUENTA_UNICA;


/**
 * The Class SolicitudesManagerImpl.
 */
@Component
public class SolicitudesManagerImpl implements SolicitudesManager {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudesManagerImpl.class);

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The solicitudes BO. */
	@Autowired
	private SolicitudesBO solicitudesBO;

	/** The segmento cliente BO. */
	@Autowired
	private SegmentoClienteBO segmentoClienteBO;
	
	 /**Flag tracking tarjetas. */
    @Value("${TRACKINGTARJETAS.HABILITADO}")
    private boolean tieneTracking;

    /** The datos solicitante service. */
    @Autowired
    private DatosSolicitanteService datosSolicitanteService;
    
    @Autowired
    private CambioGrupoAfinidadManager cambioGrupoAfinidadManager;

	@Autowired
    private CasoSFServiceConnector casoSFServiceConnector;

	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	@Autowired
	MensajeBO mensajeBO;
	
	@Autowired
	LegalBO legalBO;

	@Autowired
	private final static String TYPIFICATION = "TRANSFERI";

	@Autowired
	private final static String RESOLUTION_TYPE = "RPA";
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager#
	 * obtenerCuentasYPaquetes()
	 */
	@Override
	public Respuesta<SolicitudesView> obtenerCuentasYPaquetes() {
		LOGGER.info("Entro al manager para obtener Cuentas y Paquetes");

		Cliente cliente = sesionCliente.getCliente();
		Segmento segmentoCliente = segmentoClienteBO.obtenerSegmento(sesionCliente.getCliente()).getRespuesta();
		SolicitudesView solicitudesView = new SolicitudesView(segmentoCliente.isSelect(),
				solicitudesBO.obtenerCuentasYPaquetes(cliente.getCuentas()));

		estadisticaManager.add("13568", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(solicitudesView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager#
	 * obtenerTarjetas()
	 */
	@Override
	public Respuesta<SolicitudesView> obtenerTarjetas() {
		LOGGER.info("Entro al manager para obtener Tarjetas");

		Cliente cliente = sesionCliente.getCliente();
		Segmento segmentoCliente = segmentoClienteBO.obtenerSegmento(sesionCliente.getCliente()).getRespuesta();
		SolicitudesView solicitudesView = new SolicitudesView(segmentoCliente.isSelect(),
				solicitudesBO.obtenerTarjetas(cliente, cliente.getCuentas()));
		// VER HISTORIAL PARA CODIGO DE TRACKING! Y TESTS
		solicitudesView.setProductosEnTracking("-");
	    solicitudesView.setTieneTracking(tieneTracking);
	    solicitudesView.setGrupoAfinidad(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos().getRespuesta());
	    estadisticaManager.add("13569", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		if (moduloPermisoBO.tienePermisoMostrar(AccionController.CONSUMO_API_CASOSF))
		{
			LOGGER.info("LLamada servicio gestion de casos");
			solicitudesView.addCardGestionDeCasos(casoSFServiceConnector.getObpCards());
		}
		
		return respuestaFactory.crearRespuestaOk(solicitudesView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager#
	 * obtenerDatosBasicosACCT()
	 */
	public Respuesta<Map<String, String>> obtenerDatosBasicosACCT() {
		Cliente cliente = sesionCliente.getCliente();
		CredencialesMya credenciales = sesionParametros.getCredencialesMya();
		Map<String, String> datosBasicos = new HashMap<String, String>();
		datosBasicos.put("nup", cliente.getNup());
		datosBasicos.put("mailMya", credenciales.getEmail());

		String telMya = credenciales.getCodigoArea() != null ? credenciales.getCodigoArea() + "-" : "";
		telMya = credenciales.getCelular() != null ? telMya + credenciales.getCelular() : "";
		datosBasicos.put("telMya", telMya);

		if (credenciales.getCompaniaSeleccionada() == null) {
			datosBasicos.put("empresaCelMya", "");
		} else {
			datosBasicos.put("empresaCelMya",
					TipoCompaniaEnum.fromDescripcion(credenciales.getCompaniaSeleccionada()).getCodigo());
		}

		datosBasicos.put("monoTC", solicitudesBO.soloUnaTarjetaDeCredito(cliente.getCuentas()) ? "1" : "0");
		estadisticaManager.add(EstadisticasConstants.FORMULARIO_DE_APERTURA_DE_CUENTA_TITULO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(datosBasicos);
	}

	/*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager#
     * obtenerOtrosMediosPago()
     */
    @Override
    public Respuesta<SolicitudesView> obtenerOtrosMediosPago() {
        LOGGER.info("Entro al manager para obtener Otros Medios de Pago");

        Cliente cliente = sesionCliente.getCliente();
        Segmento segmentoCliente = segmentoClienteBO.obtenerSegmento(sesionCliente.getCliente()).getRespuesta();
        List<MonederoDTO> monederosInactivos = datosSolicitanteService.getDatosTarjetaMonedero(cliente, "I")
                .getRespuesta();
        SolicitudesView solicitudesView = new SolicitudesView(segmentoCliente.isSelect(),
                solicitudesBO.obtenerOtrosMediosPago(cliente.getCuentas(), monederosInactivos));

        return respuestaFactory.crearRespuestaOk(solicitudesView);
    }

	@Override
	public Respuesta<ValidaAltaView> solicitudCtaTit() {
		ValidaAltaView view = solicitudesBO.solicitudCtaTit();
		return respuestaFactory.crearRespuestaOk(view);
	}

	@Override
	public Respuesta<BeneficioTransferiTuSueldoView> inicioBeneficioTransferiTuSueldo() {

		BeneficioTransferiTuSueldoView beneficioTransferiTuSueldoView = new BeneficioTransferiTuSueldoView();
		beneficioTransferiTuSueldoView.setInicio(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.INICIO_BENEFICIO_TRANSFERI_SUELDO).getMensaje());
		beneficioTransferiTuSueldoView.setCuerpo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUERPO_BENEFICIO_TRANSFERI_SUELDO).getMensaje());
		String beneficioMonto = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTAS_MONTO_BENEFICIO_TRANSFERI_SUELDO).getMensaje();
		
        List<CuentaMontoBeneficioView> listacuentaMontoBeneficio = new ArrayList<CuentaMontoBeneficioView>();
		if (beneficioMonto != null && !beneficioMonto.isEmpty()) {
			String[] montoPremios = beneficioMonto.split("\\|");
			for (String montoPremio : montoPremios) {
				String[] valores = montoPremio.split("\\,");
				CuentaMontoBeneficioView cuentaMontoBeneficio = new CuentaMontoBeneficioView();
				cuentaMontoBeneficio.setCuenta(valores[0]);
				cuentaMontoBeneficio.setMonto(valores[1]);
				listacuentaMontoBeneficio.add(cuentaMontoBeneficio);
			}
		}
		
		beneficioTransferiTuSueldoView.setCuentasMontos(listacuentaMontoBeneficio);
		
        try {
        	beneficioTransferiTuSueldoView.setLegales(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_BENEFICIO_TRANSFERI_SUELDO));
		} catch (DAOException e) {
			LOGGER.info("No se pudo recuperar el legal " + CodigoMensajeConstantes.LEGAL_BENEFICIO_TRANSFERI_SUELDO);
	        estadisticaManager.add(EstadisticasConstants.INICIO_BENEFICIO_TRANSFERI_SUELDO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO_BENEFICIO_TRANSFERI_SUELDO);
		}

        estadisticaManager.add(EstadisticasConstants.INICIO_BENEFICIO_TRANSFERI_SUELDO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(beneficioTransferiTuSueldoView);	
    }

	@Override
	public Respuesta<BeneficioTransferiTuSueldoView> solicitarBeneficioTransferiTuSueldo() {
		Respuesta<BeneficioTransferiTuSueldoView> respuestaBeneficio = new Respuesta<BeneficioTransferiTuSueldoView>();
		LOGGER.info("Se realiza el envío del request a la API de Transferencia de Sueldo");
		try {
			LOGGER.info("Se forma el request para enviar");
			boolean respuesta = solicitudesBO.transferiSueldo(generarTransferiSueldoRequestDTO());
			LOGGER.info("Request API: {}.", respuesta);
			if (respuesta) {
				MensajeFeedbackOk mensajeFeedbackOk = new MensajeFeedbackOk();
				mensajeFeedbackOk.setTitulo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_TITULO_BENEFICIO_TRANSFERI_SUELDO).getMensaje());
				mensajeFeedbackOk.setParrafo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_PARRAFO_BENEFICIO_TRANSFERI_SUELDO).getMensaje());
				mensajeFeedbackOk.setPie(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_PIE_BENEFICIO_TRANSFERI_SUELDO).getMensaje());

				BeneficioTransferiTuSueldoView beneficioTransferiTuSueldoView = new BeneficioTransferiTuSueldoView();
				beneficioTransferiTuSueldoView.setMensajeFeedbackOk(mensajeFeedbackOk);
				respuestaBeneficio = respuestaFactory.crearRespuestaOk(beneficioTransferiTuSueldoView);
		        estadisticaManager.add(EstadisticasConstants.SOLICITUD_BENEFICIO_TRANSFERI_SUELDO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaBeneficio;
			} else {
				estadisticaManager.add(EstadisticasConstants.SOLICITUD_BENEFICIO_TRANSFERI_SUELDO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO_BENEFICIO_TRANSFERI_SUELDO);
			}
		} catch (Exception e) {
			LOGGER.error("Error al ", e);
	        estadisticaManager.add(EstadisticasConstants.SOLICITUD_BENEFICIO_TRANSFERI_SUELDO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO_BENEFICIO_TRANSFERI_SUELDO);
		}
	}

	private TransferiSueldoRequestDTO generarTransferiSueldoRequestDTO() {

		Cliente cliente = sesionCliente.getCliente();
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();

		TransferiSueldoRequestDTO transferiSueldoRequestDTO = new TransferiSueldoRequestDTO();

		transferiSueldoRequestDTO.setTypification(TYPIFICATION);
		transferiSueldoRequestDTO.setResolutionType(RESOLUTION_TYPE);

		transferiSueldoRequestDTO.setForms(new ArrayList<Form>());

		Form form = new Form();
		form.setNup(cliente.getNup());
		form.setDni(cliente.getDni());
		form.setFullName(getFullName(cliente));
		form.setEmail(credencialesMya.getEmail());

		Product product = new Product();

		for (Cuenta cuenta : cliente.getCuentas()) {
			if (Cuenta.TIPOCTA_CU.equals(cuenta.getTipoCuenta()) && cuenta.getIsFavorita()) {
				product.setAccountId(cuenta.getCbu());
				product.setBranch(cuenta.getNroSucursal());
				String nroSucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
				String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
				String nroCuentaCompleto = nroSucursal + ISBANStringUtils.GUION_STRING + nroCuenta;
				product.setAccountNumber(nroCuentaCompleto);
			}
		}

		if (product.getAccountNumber() == null) {
			for (Cuenta cuenta : cliente.getCuentas()) {
				if (Cuenta.TIPOCTA_CU.equals(cuenta.getTipoCuenta())) {
					product.setAccountId(cuenta.getCbu());
					product.setBranch(cuenta.getNroSucursal());
					String nroSucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
					String nroCuenta = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
					String nroCuentaCompleto = nroSucursal + ISBANStringUtils.GUION_STRING + nroCuenta;
					product.setAccountNumber(nroCuentaCompleto);
					break;
				}
			}
		}

		form.setProduct(product);
		transferiSueldoRequestDTO.getForms().add(form);

		return transferiSueldoRequestDTO;

	}

	private static String getFullName(Cliente cliente) {
		return cliente.getNombre()
				.concat(ISBANStringUtils.ESPACIO_STRING)
				.concat(cliente.getApellido1()).concat(ISBANStringUtils.ESPACIO_STRING)
				.concat(cliente.getApellido2()).trim();
	}

}
