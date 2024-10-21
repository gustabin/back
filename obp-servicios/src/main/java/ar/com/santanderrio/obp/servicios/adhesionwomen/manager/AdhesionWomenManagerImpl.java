package ar.com.santanderrio.obp.servicios.adhesionwomen.manager;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.adhesionwomen.bo.AdhesionWomenBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ConfirmacionBajaAdhesionView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdhesionWomenManagerImpl implements AdhesionWomenManager{

	@Autowired
	SesionCliente sesionCliente;
	
	@Autowired
	AdhesionWomenBO adhesionWomenBO;
	
	@Autowired
	RespuestaFactory respuestaFactory;
	
	@Autowired
	SesionParametros sesionParametros;
	
	@Autowired
	MensajeBO mensajeBO;
	
	@Autowired
	LegalBO legalBO;
	
	@Autowired
	EstadisticaManager estadisticaManager;
	
    @Value("${WOMEN.ALTA.TARJETASPARAADHERIR}")
	private String tarjetasAptasAdhesion;
	
	private static final String ERROR_ARQUITECTURA = "Error inesperado";
	
	private static final String SOLICITUD_EN_CURSO_WOMEN = "10000002";
	
	private static final String MOTIVO_REIMPRESION_ALTA = "12";
	
	private static final String MOTIVO_REIMPRESION_BAJA = "13";
					
	@Override
	public AdhesionWomenView recuperarTarjetasAdhesionWomen() {
		
		AdhesionWomenView inicioAdhesionWomenView = new AdhesionWomenView();
		List<Cuenta> listaTarjetasCliente = sesionCliente.getCliente().getCuentasTarjetaDeCredito();
		
		try {
			List<DatosTarjetaWomen> listaTarjetasWomen = adhesionWomenBO.obtenerListadoWomen(listaTarjetasCliente);
			inicioAdhesionWomenView.setListaTarjetasAdheridas(listaTarjetasWomen);
		} catch (BusinessException e) {
			inicioAdhesionWomenView = new AdhesionWomenView();
			inicioAdhesionWomenView.setErrorServicio(listaTarjetasCliente.isEmpty() ? "ERROR_SIN_TARJETAS" : e.getMessage());
		}
		sesionParametros.setTarjetasHabilitadasWomen(inicioAdhesionWomenView);

		return inicioAdhesionWomenView;

	}

	@Override
	public Respuesta<AdhesionWomenView> inicioAdhesionWomen() {
		Respuesta<AdhesionWomenView> respuesta;
		
		AdhesionWomenView inicioAdhesionWomenViewSesion = sesionParametros.getTarjetasHabilitadasWomen();
		if (StringUtils.isEmpty(inicioAdhesionWomenViewSesion.getErrorServicio())) {
			AdhesionWomenView inicioAdhesionWomenView = new AdhesionWomenView();
			inicioAdhesionWomenView.setListaTarjetasAdheridas(armarListaTarjetasAdheridasWomen(inicioAdhesionWomenViewSesion.getListaTarjetasAdheridas()));
			if (sesionParametros.getTarjetasPosiblesHabilitacion().isEmpty()) {
				inicioAdhesionWomenView.setTodasLasTarjetasAdheridas(Boolean.TRUE);
			}
			inicioAdhesionWomenView.setMensajeWomen1(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_INICIO_ADHESION_WOMEN_PRINCIPAL).getMensaje());
			inicioAdhesionWomenView.setMensajeWomen2(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_INICIO_ADHESION_WOMEN_SECUNDARIO).getMensaje());
			respuesta = respuestaFactory.crearRespuestaOk(inicioAdhesionWomenView);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_INICIO_FLUJO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK );
		} else if (ERROR_ARQUITECTURA.equals(inicioAdhesionWomenViewSesion.getErrorServicio())) {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_ARQUITECTURA", CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_INICIO_FLUJO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else if ("ERROR_SIN_TARJETAS".equals(inicioAdhesionWomenViewSesion.getErrorServicio())) {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_SIN_TARJETAS", CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_SIN_TARJETAS_WOMEN);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_INICIO_FLUJO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_SERVICIO", CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_INICIO_FLUJO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		
		return respuesta;
	}

	
	private List<DatosTarjetaWomen> armarListaTarjetasAdheridasWomen(List<DatosTarjetaWomen> listaTarjetasWomen) {
		
		List<DatosTarjetaWomen> listaTarjetasView = new ArrayList<DatosTarjetaWomen>();
		for (DatosTarjetaWomen tarjeta : listaTarjetasWomen) {
			listaTarjetasView.add(new DatosTarjetaWomen(tarjeta));
		}
		return listaTarjetasView;
	}
	
	@Override
	public Respuesta<AdhesionWomenView> configuracionAdhesionWomen() {

		AdhesionWomenView inicioAdhesionWomenView = new AdhesionWomenView();
		inicioAdhesionWomenView.setMensajeLimiteTarjeta(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_LIMITE_CANTIDAD_TARJETAS_ADHESION_WOMEN).getMensaje());
		List<DatosTarjetaWomen> tarjetasNoHabilitadas = sesionParametros.getTarjetasPosiblesHabilitacion();
		List<DatosTarjetaWomen> listaTarjetasParaAdherirView = new ArrayList<DatosTarjetaWomen>();
		
		if (StringUtils.isNotEmpty(tarjetasAptasAdhesion)) {
			String[] tarjetas = this.tarjetasAptasAdhesion.split("\\|");
			for (String tarjetaApta : tarjetas) {
				for (DatosTarjetaWomen tarjetaDTO : tarjetasNoHabilitadas) {
					if (tarjetaApta.equals(tarjetaDTO.getMarcaTarjeta())) {
						listaTarjetasParaAdherirView.add(new DatosTarjetaWomen(tarjetaDTO));
					}
				}
			}		
		}

		inicioAdhesionWomenView.setListaTarjetasParaAdherir(listaTarjetasParaAdherirView);
		inicioAdhesionWomenView.setMensajeConfiguracion(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CONFIGURACION_ADHESION_WOMEN).getMensaje());
		estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_CONFIGURACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(inicioAdhesionWomenView);
	}

	@Override
	public Respuesta<AdhesionWomenView> confirmacionAdhesionWomen() {

		Respuesta<AdhesionWomenView> respuesta = new Respuesta<AdhesionWomenView>();
		AdhesionWomenView inicioAdhesionWomenView = new AdhesionWomenView();
		inicioAdhesionWomenView.setMensajeConfirmacionStack(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CONFIRMACION_ADHESION_WOMEN).getMensaje());
		inicioAdhesionWomenView.setMensajeConfirmacionInformativo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_CONFIRMACION_INFORMATIVO_ADHESION_WOMEN).getMensaje());
		try {
			inicioAdhesionWomenView.setLegalConfirmacion(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CONFIRMACION_ADHESION_WOMEN));
			respuesta = respuestaFactory.crearRespuestaOk(inicioAdhesionWomenView);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_CONFIRMACION, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} catch (DAOException e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_CONFIRMACION, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	@Override
	public Respuesta<AdhesionWomenView> ejecutarAdhesionWomen(AdhesionWomenView tarjetasParaHabilitar) {

		inicializarReintentos();
		
		List<DatosTarjetaWomen> tarjetasNoHabilitadas = sesionParametros.getTarjetasPosiblesHabilitacion();
		Respuesta<AdhesionWomenView> respuesta;
		
		AdhesionWomenView adhesionWomenViewIn = adhesionWomenBO.ejecutarAltaBajaWomen(tarjetasParaHabilitar.getListaTarjetasParaAdherir(), tarjetasNoHabilitadas, sesionCliente.getCliente(), MOTIVO_REIMPRESION_ALTA);
		AdhesionWomenView adhesionWomenViewOut = new AdhesionWomenView();
		
		if (StringUtils.isEmpty(adhesionWomenViewIn.getErrorServicio())) {
			sesionParametros.setPrimerAcceso(true);
			adhesionWomenViewOut.setMensajeFeedbackOK(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FEEDBACK_ADHESION_WOMEN_OK).getMensaje());
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_ACEPTA_TERMINOS_CONDICIONES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaOk(adhesionWomenViewOut);
		} else if (SOLICITUD_EN_CURSO_WOMEN.equals(adhesionWomenViewIn.getErrorServicio())) {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_ALTA_YA_SOLICITADA", CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_SOLICITUD_YA_PEDIDA_ADHESION_WOMEN);
		} else if ("ERROR_ARQUITECTURA".equals(adhesionWomenViewIn.getErrorServicio())) {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_GENERICO", CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (sesionParametros.getContador().permiteReintentar()){
			sesionParametros.setPrimerAcceso(false);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_TIENE_REINTENTOS", CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_ADHESION_WOMEN);
		} else {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_REINTENTOS_AGOTADOS", CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_ADHESION_WOMEN);
		}
		
		return respuesta;
	}
	
    private void inicializarReintentos() {
        if (sesionParametros.getPrimerAcceso()) {
            sesionParametros.setContador(new ContadorIntentos(3));
        }
    }

    
    
	@Override
	public Respuesta<AdhesionWomenView> ejecutarBajaAdhesionWomen(AdhesionWomenView tarjetasParaDarDeBaja) {
			
		String mensajeFeedbackBajaOk = null;
		String codigoEstadisticaBaja = null;
		String codigoErrorServicio = null;
		
		inicializarReintentos();	
		List<DatosTarjetaWomen> tarjetasAdheridadDeSesion = sesionParametros.getTarjetasHabilitadasWomen().getListaTarjetasAdheridas();
		Respuesta<AdhesionWomenView> respuesta;
		
		AdhesionWomenView adhesionWomenViewIn = adhesionWomenBO.ejecutarAltaBajaWomen(tarjetasParaDarDeBaja.getListaTarjetasAdheridas(), tarjetasAdheridadDeSesion, sesionCliente.getCliente(), MOTIVO_REIMPRESION_BAJA);
		AdhesionWomenView adhesionWomenViewOut = new AdhesionWomenView();
		adhesionWomenViewOut.setMensajeFeedbackInformativo(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SOLICITUDES_BAJA_WOMEN).getMensaje());
		
		if (tarjetasParaDarDeBaja.getListaTarjetasAdheridas().size() == 1) {
			mensajeFeedbackBajaOk = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FEEDBACK_BAJA_WOMEN_INDIVIDUAL_OK).getMensaje();
			codigoEstadisticaBaja = EstadisticasConstants.WOMEN_SOLICITUDES_BAJA_FEEDBACK_INDIVIDUAL;
			codigoErrorServicio = CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_BAJA_ADHESION_WOMEN_INDIVIDUAL;
			
		}else if (tarjetasParaDarDeBaja.getListaTarjetasAdheridas().size() > 1) {
			mensajeFeedbackBajaOk = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_FEEDBACK_BAJA_WOMEN_OK_TOTAL).getMensaje();
			codigoEstadisticaBaja = EstadisticasConstants.WOMEN_SOLICITUDES_BAJA_FEEDBACK_TOTAL;
			codigoErrorServicio =CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_BAJA_ADHESION_WOMEN_TOTAL;
	
		}
		if (StringUtils.isEmpty(adhesionWomenViewIn.getErrorServicio())) {		
			sesionParametros.setPrimerAcceso(true);
			adhesionWomenViewOut.setMensajeFeedbackOK(mensajeFeedbackBajaOk);
			estadisticaManager.add(codigoEstadisticaBaja, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = respuestaFactory.crearRespuestaOk(adhesionWomenViewOut);
		} 
		else if (SOLICITUD_EN_CURSO_WOMEN.equals(adhesionWomenViewIn.getErrorServicio())) {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(codigoEstadisticaBaja, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_BAJA_YA_SOLICITADA", CodigoMensajeConstantes.MENSAJE_FEEDBACK_ERROR_SOLICITUD_YA_PEDIDA_BAJA_ADHESION_WOMEN);
		} 
		else if ("ERROR_ARQUITECTURA".equals(adhesionWomenViewIn.getErrorServicio())) {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(codigoEstadisticaBaja, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_GENERICO", CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} 
		else if (sesionParametros.getContador().permiteReintentar()){
			sesionParametros.setPrimerAcceso(false);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_TIENE_REINTENTOS", codigoErrorServicio);
		} else {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", "ERROR_WOMEN_REINTENTOS_AGOTADOS", codigoErrorServicio);
		}
		return respuesta;
	}

	
	@Override
	public Respuesta<ConfirmacionBajaAdhesionView> confirmacionBajaAdhesionWomen() {
		
		estadisticaManager.add(EstadisticasConstants.WOMEN_SOLICITUDES_CONFIRMACION_BAJA,EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		String mensajeInformativo = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SOLICITUDES_BAJA_WOMEN).getMensaje();
		ConfirmacionBajaAdhesionView confirmacionBajaAdhesionView = new ConfirmacionBajaAdhesionView();
		confirmacionBajaAdhesionView.setMensajeInformativo(mensajeInformativo);
		return respuestaFactory.crearRespuestaOk(confirmacionBajaAdhesionView);

	}
		

}
