/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.bo.BonificacionesVigentesBO;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo.ConsultaDeudaBO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.NotificacionComercialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.NotificacionesComercialesView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.logoutmobile.bo.LogoutMobileBO;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.mya.web.view.ConfirmarMailViewIn;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioNombreBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioNombreDTO;
import ar.com.santanderrio.obp.servicios.perfil.manager.PerfilManager;
import ar.com.santanderrio.obp.servicios.perfil.view.CambioNombreView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConfirmarMailView;
import ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.view.LogoutAppView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilAlertaGalaView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilDeudorView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilEjecutivoView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilPersonalView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilSucursalView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilDetalleDeudorView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;

/**
 * The Class PerfilManagerImpl.
 */
@Component
public class PerfilManagerImpl implements PerfilManager {

    /** The Constant GUION_SEPARADOR_SUCURSAL. */
    private static final String GUION_SEPARADOR_SUCURSAL = " - ";

    /** The Constant SUCURSAL_VACIA. */
    private static final String SUCURSAL_VACIA = "-";

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PerfilManagerImpl.class);

    /** The Constant SITUACION_DEUDOR_DOS. */
    private static final String SITUACION_DEUDOR_DOS = "2";

    /** The Constant SITUACION_DEUDOR_UNO. */
    private static final String SITUACION_DEUDOR_UNO = "1";

    /** The Constant SIN_INFORMACION. */
    private static final String SIN_INFORMACION = StringUtils.EMPTY;

    /** The Constant HS. */
    private static final String HS = " hs.";

    /** The Constant FORMATO_FECHA. */
    private static final String FORMATO_FECHA = "dd/MM/yyyy - HH:mm";
    
    private static final String TARJETA_DEBITO_ACTIVA = "01";
    
    private static final String CIERRE_SESION_CON_ELIMINACION_DATOS = "USER000730";
    
    private static final String CIERRE_SESION_SIN_ELIMINACION_DATOS = "USER000732";
    
    private static final String CODIGO_LEGAL_TERMINOS_CONDICIONES_LOGOUT_MOBILE = "15460";
    
    private static final String RESPUESTA_OK = "0";
    
    @Value("${INFOLEYGENERO.URL}")
    private String leyGeneroUrl;
    
	
    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The GestorEventosComerciales manager. */
    @Autowired
    private GestorEventosComercialesManager gestorEventosComercialesManager;
    /** The consultar sucursales bo. */
    @Autowired
    private ConsultarSucursalesBO consultarSucursalesBo;

    /** The consulta deuda BO. */
    @Autowired
    private ConsultaDeudaBO consultaDeudaBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;
    
    @Autowired
    private LogoutMobileBO logoutMobileBO;
    
    @Autowired
    private MensajeBO mensajeBO;
    
    @Autowired
    private LegalBO legalBO;
    
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;
 
	@Autowired
	private MyaBO myaBO;
	
	@Autowired
	private BonificacionesVigentesBO bonificacionesVigentesBO;
	
	@Autowired
	private CambioNombreBO cambioNombreBO;
	
	
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.PerfilManager#
     * obtenerPerfil( )
     */
    @Override
    public Respuesta<PerfilView> obtenerPerfil() {

        Respuesta<PerfilView> respuestaPerfilView = respuestaFactory.crearRespuestaOk(PerfilView.class);
        Cliente cliente = sesionCliente.getCliente();
        Segmento segmento = cliente.getSegmento();
        List<CajaPerfil> cajas = new ArrayList<CajaPerfil>();
        PerfilView perfilView = obtenerCabecera(cliente);
       
        perfilView.setSinProductos(cliente.getSinProductos());
        perfilView.setTieneTarjetaDebito(averiguarSiTieneDebito(cliente));
        perfilView.setMostrarSorpresa(isSorpresa(cliente));
        perfilView.setIsSelect(isClienteSelect(segmento));
        perfilView.setIsIU(segmento.isiU());

		ModuloPermiso moduloPermiso = moduloPermisoBO
				.obtenerModuloPermisoPorAccion(AccionController.LOGOUT_MOBILE);
		if (moduloPermiso.tienePermisoDeVisibilidad()) {
			perfilView.setMostrarCierreMobile(Boolean.TRUE);
		}
		
        cajas.add(obtenerCajasPerfilPersonal(cliente));

        if (cliente.getSinProductos()) {
            perfilView.setCajas(cajas);
            respuestaPerfilView.setRespuesta(perfilView);
            return respuestaPerfilView;
        }
        
        List<CajaPerfil> cajasPerfilSucursal = obtenerCajasPerfilSucursal(cliente);
        if (CollectionUtils.isNotEmpty(cajasPerfilSucursal)) {
            cajas.addAll(cajasPerfilSucursal);
        }

        CajaPerfil cajaPerfilEjecutivo = obtenerCajaPerfilEjecutivo(segmento);
        if (cajaPerfilEjecutivo != null) {
            cajas.add(cajaPerfilEjecutivo);
        }

        CajaPerfil cajaDeudor = obtenerCajaPerfilDeudor(cliente);
        if (cajaDeudor != null) {
            cajas.add(cajaDeudor);
        }
      
        CajaPerfil cajaFormularioUMAUFI = obtenerCajaFormularioUMAUIF(cliente);
        if (cajaFormularioUMAUFI != null) {
        perfilView.setMostrarAlertaGala(true);
        	cajas.add(cajaFormularioUMAUFI);
        }

        Respuesta<List<BonificacionVigenteView>> respuestaBonificaciones = obtenerBonificaciones();
        
        if (EstadoRespuesta.OK.equals(respuestaBonificaciones.getEstadoRespuesta())) {
        	perfilView.setMostrarBonificaciones(Boolean.TRUE);
        }
        
        perfilView.setCajas(cajas);
        respuestaPerfilView.setRespuesta(perfilView);

        return respuestaPerfilView;
    }
    
	private Boolean averiguarSiTieneDebito (Cliente cliente) {
    	
    	Boolean tieneDebito = Boolean.FALSE;
    	
    	for (Cuenta cuenta : cliente.getCuentas()) {
    		if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum()) && TARJETA_DEBITO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
    			tieneDebito = Boolean.TRUE;
    			break;
    		}
    	}
    	for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
            if (TipoCuenta.BANELCO.equals(cuenta.getTipoCuentaEnum())
                    && TARJETA_DEBITO_ACTIVA.equals(cuenta.getEstadoTarjetaCredito())) {
                tieneDebito = Boolean.TRUE;
                break;
            }
        }
    	return tieneDebito;
    }

    /**
	 * Checks if is sorpresa.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
    private Boolean isSorpresa(Cliente cliente) {
        boolean isSorpresa = false;
        List<Cuenta> tarjetas;
        try {
            tarjetas = TarjetaBOUtils.filtrarCuentasDeTipoNoCuentaTarjeta(cliente.getCuentas());
            tarjetas.addAll(TarjetaBOUtils.filtrarCuentasDeTipoBanelco(cliente.getCuentas()));
            for (Cuenta cuenta : tarjetas) {
                if (("TI".equals(cuenta.getCodigoTitularidad()))) {
                    isSorpresa = true;
                    break;
                }
            }
        } catch (TarjetaBOUtilsException e) {
            LOGGER.error("Error obteniendo las tarjetas para ver si el cliente es monoproducto tarjeta adicional", e);
            return true;
        }
        return isSorpresa;
    }
    
    /**
     * Obtener caja perfil deudor.
     *
     * @param cliente
     *            the cliente
     * @return the caja perfil
     */
    private CajaPerfil obtenerCajaPerfilDeudor(Cliente cliente) {

        Respuesta<ClasificacionDeuda> respuestaClasificacionDeuda = consultaDeudaBO.consultarDeuda(cliente.getNup());
        CajaPerfilDeudorView cajaPerfilDeudorView = null;
        ClasificacionDeuda clasificacionDeuda = null;

        if (EstadoRespuesta.OK.equals(respuestaClasificacionDeuda.getEstadoRespuesta())) {
            clasificacionDeuda = respuestaClasificacionDeuda.getRespuesta();
            estadisticaManager.add(EstadisticasConstants.PERFIL_DEUDORES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            if (clasificacionDeuda != null && !SITUACION_DEUDOR_UNO.equals(clasificacionDeuda.getSituacionBcra())
                    && !SITUACION_DEUDOR_DOS.equals(clasificacionDeuda.getSituacionBcra())
                    && !SIN_INFORMACION.equals(clasificacionDeuda.getSituacionBcra())) {
                cajaPerfilDeudorView = new CajaPerfilDeudorView();
                cajaPerfilDeudorView.setDescripcion(clasificacionDeuda.getDescripcion());
                cajaPerfilDeudorView.setSituacion(clasificacionDeuda.getSituacionBcra());
                sesionParametros.setClasificacionDeuda(clasificacionDeuda);

            }
        } else {
            estadisticaManager.add(EstadisticasConstants.PERFIL_DEUDORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return cajaPerfilDeudorView;
    }

  /**
     * Obtener caja formulario UMAUIF.
     *
     * @param cliente
     *            the cliente
     * @return the caja perfil
     */
    private CajaPerfil obtenerCajaFormularioUMAUIF(Cliente cliente) {
    	
    	Respuesta<NotificacionesComercialesView>  respuestanotificacionesPerfil = gestorEventosComercialesManager.obetenerNotificacionesPerfil(cliente);

    	CajaPerfilAlertaGalaView cajaPerfilAlertaGala = null;
        NotificacionesComercialesView notificacionesPerfil = null;

        if (EstadoRespuesta.OK.equals(respuestanotificacionesPerfil.getEstadoRespuesta())) {
        	notificacionesPerfil = respuestanotificacionesPerfil.getRespuesta();
            if (notificacionesPerfil != null) {
            	for (NotificacionComercialView notificacion : notificacionesPerfil.getNotificaciones()) {
	            	//agrego solo la primer notificacion que retorna el GEC
	            	cajaPerfilAlertaGala = new CajaPerfilAlertaGalaView();
	            	cajaPerfilAlertaGala.setDescripcion(notificacion.getDescripcion());
	            	cajaPerfilAlertaGala.setLinkFormPortal(notificacion.getLinkPortal());
	            	break;
            	}

            }
        } else {
            estadisticaManager.add(EstadisticasConstants.PERFIL_DEUDORES,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return cajaPerfilAlertaGala;
    }

    /**
     * Obtener caja perfil ejecutivo.
     *
     * @param segmento
     *            the segmento
     * @return the caja perfil
     */
    private CajaPerfil obtenerCajaPerfilEjecutivo(Segmento segmento) {

        String nombre = null;
        String mail = null;

        if (segmento == null || !segmento.isSelect()) {
            return null;
        } else if (StringUtils.isNotBlank(segmento.getEjecutivo())) {
            nombre = WordUtils.capitalizeFully(segmento.getEjecutivo());
            if (segmento.getOperadorEjecutivo() != null) {
                mail = segmento.getOperadorEjecutivo().getEmail();
            } 
        }

        CajaPerfilEjecutivoView cajaPerfilEjecutivoView = new CajaPerfilEjecutivoView();
        if (StringUtils.isNotBlank(nombre)) {
            cajaPerfilEjecutivoView.setHasError(false);
            cajaPerfilEjecutivoView.setDescripcion(nombre);
            cajaPerfilEjecutivoView.setMailEjecutivo(mail);
            estadisticaManager.add(EstadisticasConstants.PERFIL_EJECUTIVO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            cajaPerfilEjecutivoView.setHasError(true);
            estadisticaManager.add(EstadisticasConstants.PERFIL_EJECUTIVO,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }

        return cajaPerfilEjecutivoView;
    }

    /**
     * Obtener cajas perfil sucursal.
     *
     * @param cliente
     *            the cliente
     * @return the list
     */
    private List<CajaPerfil> obtenerCajasPerfilSucursal(Cliente cliente) {

        Segmento segmento = cliente.getSegmento();

        if (segmento == null || StringUtils.isEmpty(segmento.getPesucadm())) {
            return null;
        }

        Respuesta<Sucursal> respuestaSucursal = consultarSucursalesBo.consultarSucursalPorId(segmento.getPesucadm());

        String descripcionSucursal = "";
        String direccionSucursal = "";
        if (!EstadoRespuesta.OK.equals(respuestaSucursal.getEstadoRespuesta())
                || respuestaSucursal.getRespuesta() == null) {
            estadisticaManager.add(EstadisticasConstants.PERFIL_SUCURSAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            descripcionSucursal = SUCURSAL_VACIA;
            direccionSucursal = SUCURSAL_VACIA;
        } else {
            estadisticaManager.add(EstadisticasConstants.PERFIL_SUCURSAL, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            Sucursal sucursal = respuestaSucursal.getRespuesta();
            descripcionSucursal = WordUtils.capitalizeFully(sucursal.getDescripcion());
            direccionSucursal = WordUtils.capitalizeFully(sucursal.getDireccion());
        }
        List<CajaPerfil> cajas = new ArrayList<CajaPerfil>();
        CajaPerfilSucursalView cajaSucursal = new CajaPerfilSucursalView();

        String sucursalFormateada = StringUtils.leftPad(StringUtils.stripStart(segmento.getPesucadm(), "0"), 3, "0");
        cajaSucursal.setDescripcion(sucursalFormateada + GUION_SEPARADOR_SUCURSAL + descripcionSucursal);
        cajaSucursal.setDireccion(direccionSucursal);

        cajas.add(cajaSucursal);

        return cajas;
    }

    /**
     * Obtener cajas perfil personal.
     *
     * @param cliente
     *            the cliente
     * @return the caja perfil
     */
    private CajaPerfil obtenerCajasPerfilPersonal(Cliente cliente) {
        CajaPerfilPersonalView cajaPerfilPersonalView = new CajaPerfilPersonalView();
        cajaPerfilPersonalView.setDescripcion("50");
        return cajaPerfilPersonalView;
    }

    /**
     * Checks if is cliente select.
     *
     * @param segmento
     *            the segmento
     * @return the boolean
     */
    private Boolean isClienteSelect(Segmento segmento) {
        if (segmento != null) {
            return segmento.isSelect();
        }
        return false;
    }

    /**
     * Obtener cabecera.
     *
     * @param cliente
     *            the cliente
     * @return the perfil view
     */
    private PerfilView obtenerCabecera(Cliente cliente) {
        PerfilView perfilView = new PerfilView();
        String nombre = null;
        String apellido = null;

        if (StringUtils.isNotBlank(cliente.getNombre())) {
            nombre = WordUtils.capitalizeFully(cliente.getNombre().trim());
        }

        if (StringUtils.isNotBlank(cliente.getApellido1())) {
            apellido = WordUtils.capitalizeFully(cliente.getApellido1().trim());
        }

        perfilView.setNombreCliente(nombre + " " + apellido);
        perfilView.setUltimaConexion(obtenerUltimaConexion());

        estadisticaManager.add(EstadisticasConstants.ACCESO_A_PERFIL, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return perfilView;
    }

    /**
     * Obtener ultima conexion.
     *
     * @return the string
     */
    private String obtenerUltimaConexion() {
        Date fechaUltima = sesionParametros.getUltimoAcceso();
        if (fechaUltima != null) {
            return new SimpleDateFormat(FORMATO_FECHA).format(fechaUltima) + HS;
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.manager.PerfilManager#
     * obtenerDetalleDeudor(ar.com.santanderrio.obp.servicios.perfil.view.
     * ConsultaPerfil)
     */
    @Override
    public Respuesta<PerfilDetalleDeudorView> obtenerDetalleDeudor(ConsultaPerfil consultaPerfil) {
        PerfilDetalleDeudorView detalleDeudorView = new PerfilDetalleDeudorView();
        ClasificacionDeuda clasificacionDeuda = sesionParametros.getClasificacionDeuda();
        detalleDeudorView.setDetalleDeudor(clasificacionDeuda.getDescripcion());
        detalleDeudorView
                .setFecha(FechaUtils.parsearMesSeparadorAnio(clasificacionDeuda.getFechaClasificacion(), " de "));
        estadisticaManager.add(EstadisticasConstants.PERFIL_DETALLE_DEUDOR,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(detalleDeudorView);
    }

	@Override
	public Respuesta<LogoutAppView> logoutAppConfiguracion() {

		LogoutAppView logoutAppView = new LogoutAppView();
		String mensajeLegal = StringUtils.EMPTY;
		try {
			mensajeLegal = legalBO.obtenerLegal(CODIGO_LEGAL_TERMINOS_CONDICIONES_LOGOUT_MOBILE);
		} catch (DAOException e) {
			LOGGER.error("Error al recuperar el legal");
		}
		
		logoutAppView.setMensajeLegal(mensajeLegal);
		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_LOGOUT_MOBILE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(logoutAppView);
	}

	@Override
	public Respuesta<LogoutAppView> logoutAppFeedback() {

		Respuesta<LogoutAppView> respuesta = new Respuesta<LogoutAppView>();
		try {
			String respuestaLogout = logoutMobileBO.consumirServicioLogout(sesionCliente.getCliente());
			
			if (CIERRE_SESION_CON_ELIMINACION_DATOS.equals(respuestaLogout) ||
				CIERRE_SESION_SIN_ELIMINACION_DATOS.equals(respuestaLogout)) {
				
				LogoutAppView logoutAppView = new LogoutAppView();
				logoutAppView.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGOUT_AUTOMATICO_EXITOSO).getMensaje());
				respuesta = respuestaFactory.crearRespuestaOk(logoutAppView);
				estadisticaManager.add(EstadisticasConstants.FEEDBACK_LOGOUT_MOBILE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			} else {
				LogoutAppView logoutAppView = new LogoutAppView();
				respuesta = respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(logoutAppView, "WARNING_LOGOUT_NO_EXITOSO", CodigoMensajeConstantes.LOGOUT_AUTOMATICO_NO_EXITOSO, "");
				estadisticaManager.add(EstadisticasConstants.FEEDBACK_LOGOUT_MOBILE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
			
		} catch (BusinessException e) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.LOGOUT_AUTOMATICO_ERROR);
			estadisticaManager.add(EstadisticasConstants.FEEDBACK_LOGOUT_MOBILE, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuesta;
	}

	@Override
	public Respuesta<ConfirmarMailView> confirmarEmail(ConfirmarMailViewIn confirmarMailIn) {
		
		try {
			String respuesta = myaBO.confirmarEmail(sesionCliente.getCliente(), confirmarMailIn);
			if (RESPUESTA_OK.equals(respuesta)) {
				ConfirmarMailView confirmarMailView = new ConfirmarMailView();
				confirmarMailView.setMensajeConfirmacionDatosOK(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CONFIRMAR_MAIL_OK).getMensaje());
				return respuestaFactory.crearRespuestaOk(confirmarMailView);
			} else {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO.getDescripcion(), CodigoMensajeConstantes.CONFIRMAR_MAIL_ERROR);
			}
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO.getDescripcion(), CodigoMensajeConstantes.CONFIRMAR_MAIL_ERROR);
		}
	}

	@Override
	public Respuesta<List<BonificacionVigenteView>> obtenerBonificaciones() {
		if (sesionParametros.getListaBonificaciones() != null) {
			return sesionParametros.getListaBonificaciones();
		}
		
		Respuesta<List<BonificacionVigenteView>> respuesta = new Respuesta<List<BonificacionVigenteView>>();
		List<BonificacionVigenteView> bonificacionesView;
		
		try {
			bonificacionesView = bonificacionesVigentesBO.obtenerBonificaciones(sesionCliente.getCliente().getNup());
			
			if (bonificacionesView.isEmpty()) {
				estadisticaManager.add(EstadisticasConstants.ABRIR_BONIFICACIONES_VIGENTES, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO.getDescripcion(), CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
				sesionParametros.setListaBonificaciones(respuesta);
				return respuesta;
			}
			
		} catch (Exception e) {
			estadisticaManager.add(EstadisticasConstants.ABRIR_BONIFICACIONES_VIGENTES, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO.getDescripcion(), CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			sesionParametros.setListaBonificaciones(respuesta);
			return respuesta;
		}
		estadisticaManager.add(EstadisticasConstants.ABRIR_BONIFICACIONES_VIGENTES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		respuesta = respuestaFactory.crearRespuestaOk(bonificacionesView);
		sesionParametros.setListaBonificaciones(respuesta);
		return respuesta;
	}
	
	@Override
	public Respuesta<Void> grabarEstadisticaVerBonificaciones() {
		estadisticaManager.add(EstadisticasConstants.INGRESO_A_CARD_BONIFICACIONES_VIGENTES, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}

	@Override
	public Respuesta<CambioNombreView> cambioNombreConfig() {
		
		CambioNombreView cambioNombreView = new CambioNombreView();
		
		String mensaje1 = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_CONFIG1).getMensaje();
		String mensaje2 = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_CONFIG2).getMensaje();
		String mensaje3 = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_CONFIG3).getMensaje();
		String mensaje4 = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_CONFIG4).getMensaje();
		
		List<String> mensajesConfig = new ArrayList<String>();
		
		mensajesConfig.add(mensaje1);
		mensajesConfig.add(mensaje2);
		mensajesConfig.add(mensaje3);
		mensajesConfig.add(mensaje4);
		cambioNombreView.setMensajesConfig(mensajesConfig);
		cambioNombreView.setMensajeConfigLegal(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_LEGAL).getMensaje());
		
		cambioNombreView.setLink(leyGeneroUrl);
		cambioNombreView.setTextoLink(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_TEXTO_LINK_INFO).getMensaje());
		
		estadisticaManager.add(EstadisticasConstants.CAMBIO_NOMBRE_INGRESO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(cambioNombreView);
		
	}

	@Override
	public Respuesta<CambioNombreView> cambioNombreFeedback(String nombreElegido) {

		Respuesta<CambioNombreView> respuesta;
		Respuesta<CambioNombreDTO> respuestaDTO = cambioNombreBO.solicitarCambioNombreCliente(nombreElegido);
		
		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
			CambioNombreView cambioNombreView = new CambioNombreView();
			cambioNombreView.setMensajeFeedbackOk(respuestaDTO.getRespuesta().getMensajeOK());
			respuesta = respuestaFactory.crearRespuestaOk(cambioNombreView);
			estadisticaManager.add(EstadisticasConstants.CAMBIO_NOMBRE_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CAMBIO_NOMBRE_AUTOPERCIBIDO_FEEDBACK_ERROR);
			estadisticaManager.add(EstadisticasConstants.CAMBIO_NOMBRE_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		
		return respuesta;
	}
	
}
