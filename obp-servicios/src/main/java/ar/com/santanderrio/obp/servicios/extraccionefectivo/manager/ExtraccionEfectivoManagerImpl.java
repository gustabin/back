package ar.com.santanderrio.obp.servicios.extraccionefectivo.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.bo.ExtraccionEfectivoBO;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.ExtraccionSinTarjetaRSADTO;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosClienteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

@Component	
public class ExtraccionEfectivoManagerImpl implements ExtraccionEfectivoManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionEfectivoManagerImpl.class);
	
	@Autowired
	SesionParametros sesionParametros;	
	
	@Autowired
	SesionCliente sesionCliente;
	
	@Autowired
	RespuestaFactory respuestaFactory;
	
    @Autowired
    private MyaBO myaBO;
	
    @Autowired
    private ExtraccionEfectivoBO extraccionEfectivoBO;
    
	@Autowired
	EstadisticaManager estadisticaManager;
	
	@Autowired
	LegalBO legalBO;
	
	@Autowired
	CuentaBO cuentaBO;
	
    @Autowired
    private AutentificacionManager autentificacionManager;
    
    @Value("${TRJCOORD.OPERAINDISTINTO.EXTRACCION_SIN_TARJETA}")
    private String valorDesafio;
	
	
	@Override
	public Respuesta<DatosClienteExtraccionEfectivoView> obtenerConfiguracion() {
		
		Cliente cliente = sesionCliente.getCliente();
		MyaDTOIn myaDTOIn = new MyaDTOIn();	
        myaDTOIn.setSoloPrincipales(false);
        MyaDTOOut datosClienteMya = myaBO.consultaWsStatusCliente(cliente, myaDTOIn);
		DatosClienteExtraccionEfectivoView datosView = new DatosClienteExtraccionEfectivoView();
		
		if (!ClienteEstadoEnum.TIMEOUT.equals(datosClienteMya.getClienteEstadoEnum())) {
			if (StringUtils.isNotEmpty(datosClienteMya.getEmail())) {
				datosView.setMail(datosClienteMya.getEmail());
			} else if (StringUtils.isNotEmpty(datosClienteMya.getEmailSecundario())) {
				datosView.setMail(datosClienteMya.getEmailSecundario());
			}
		}
		
		datosView.setNombreTitular(cliente.getNombre() + " " + cliente.getApellido1() + " " + cliente.getApellido2());
		datosView.setTipoDocumentoTitular(TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocumento()).getDescripcion());
		String dni = ISBANStringUtils.eliminarCeros(cliente.getDni());
		dni = ISBANStringUtils.formatearDocumento(dni);
		datosView.setNroDocumentoTitular(dni);
		
		try {
			datosView.setTooltip(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_CONFIGURACION_EXTRACCION_EFECTIVO));
		} catch (DAOException e) {
			LOGGER.info("No se pudo recuperar el legal " + CodigoMensajeConstantes.LEGAL_CONFIGURACION_EXTRACCION_EFECTIVO);
		}
		
		List<CuentaView> listaCuentasPesos = new ArrayList<CuentaView>();
		
		try {
			List<Cuenta> cuentasBanelco = cuentaBO.obtenerCuentasBanelcoPesos(cliente);
			
			for (Cuenta cuenta : cuentasBanelco) {
				CuentaView cuentaPesos = new CuentaView();
				cuentaPesos.setAlias(cuenta.getAlias());
				cuentaPesos.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
				cuentaPesos.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
				cuentaPesos.setSaldoPesos(TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString().equals(ISBANStringUtils.eliminarCeros(cuenta.getTipoCuentaSinUnificar())) ? 
						ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldoCUPesos()) : ISBANStringUtils.formatearConComaYDosDecimales2(cuenta.getSaldoCuenta()));
				listaCuentasPesos.add(cuentaPesos);
			}
			
		} catch (BusinessException e) {
			estadisticaManager.add(EstadisticasConstants.CONFIGURACION_EXTRACCION_EFECTIVO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS);
		}
		
		datosView.setListaCuentas(listaCuentasPesos);
		estadisticaManager.add(EstadisticasConstants.CONFIGURACION_EXTRACCION_EFECTIVO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(datosView);
	}

	@Override
	public Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(ExtraccionEfectivoView solicitarEfectivoView) {
		Cliente cliente = sesionCliente.getCliente();
		
		Respuesta<ExtraccionEfectivoView> respuestaRSA = ejecutarValidacionRSA(solicitarEfectivoView);
		
        LOGGER.info("Ejecutando validacion de RSA con respuesta: {}.", respuestaRSA);
        if (!EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
            return respuestaRSA;
        }
        
        Cuenta cuenta = cliente.getCuentaSiContieneNumero(formatearNumeroCuenta(solicitarEfectivoView.getNroCuenta()));
        Respuesta<ExtraccionEfectivoView> respuesta = extraccionEfectivoBO.ejecutarSolicitud(cuenta, cliente, solicitarEfectivoView.getMonto(), solicitarEfectivoView.getEmail() );
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
        	sesionParametros.setDatosComprobanteExtraccionEfectivoView(generarDatosParaPDF(solicitarEfectivoView, cuenta, respuesta.getRespuesta()));
        }
        return respuesta;
	}
	
	private Respuesta<ExtraccionEfectivoView> ejecutarValidacionRSA(ExtraccionEfectivoView extraccionEfectivoView) {
		
        AutentificacionDTO autentificacionDTO;

        Respuesta<ExtraccionEfectivoView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(extraccionEfectivoView.getDesafio(),
                Integer.parseInt(valorDesafio));
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = extraccionEfectivoView.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            autentificacionDTO = generarAutentificacionDTO();
        } else {
            return estadoDesafio;
        }

        Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        extraccionEfectivoView.setDesafio(rstaAutentificacion.getRespuesta());

        return respuestaFactory.transformar(extraccionEfectivoView, rstaAutentificacion);
		
	}
	
	
    private AutentificacionDTO generarAutentificacionDTO() {
        Integer operacion = Integer.parseInt(valorDesafio);
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(operacion);
        autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
                new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

        // Seteo de estadisticas de RSA
        autentificacionDTO
        	.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.SOLUCITUD_COORDENADAS_EXTRACCION_EFECTIVO);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.VALIDACION_COORDENADAS_EXTRACCION_EFECTIVO);
        autentificacionDTO
        .setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOLUCITUD_TOKEN_EXTRACCION_EFECTIVO);
        autentificacionDTO
        	.setCodigoEstadisticaValidacionToken(EstadisticasConstants.VALIDACION_TOKEN_EXTRACCION_EFECTIVO);
        
        // Datos informados a RSA
        autentificacionDTO.setRsaDTO(new ExtraccionSinTarjetaRSADTO(operacion));

        return autentificacionDTO;
    }
    

	@Override
	public Respuesta<Void> grabarEstadisticaVisualizacionComprobante() {
		
		estadisticaManager.add(EstadisticasConstants.EXTRACCION_EFECTIVO_VER_COMPROBANTE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(Void.class);
	}
	
	private String formatearNumeroCuenta (String numeroCuentaView) {
		
		String[] numeroCuentaParticionado = numeroCuentaView.split("-");
		String numeroCuentaSinSucursal = numeroCuentaParticionado[1];
		return numeroCuentaSinSucursal.replaceAll("/", "");
	}
	    
	private DatosComprobanteExtraccionEfectivoView generarDatosParaPDF(ExtraccionEfectivoView solicitarEfectivoView, Cuenta cuenta, ExtraccionEfectivoView respuesta) {
		
		DatosComprobanteExtraccionEfectivoView datosComprobante = new DatosComprobanteExtraccionEfectivoView();
		datosComprobante.setMonto(ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(solicitarEfectivoView.getMonto())));
		datosComprobante.setNroCuentaOrigen(solicitarEfectivoView.getNroCuenta());
		datosComprobante.setTipoCuentaOrigen(cuenta.getTipoCuentaEnum().getDescripcion());
		datosComprobante.setNombreDestinatario(sesionCliente.getCliente().getNombre() + " " + sesionCliente.getCliente().getApellido1() + " " + sesionCliente.getCliente().getApellido2());
		datosComprobante.setDniDestinatario(ISBANStringUtils.eliminarCeros(sesionCliente.getCliente().getDni()));
		datosComprobante.setEmailDestinatario(solicitarEfectivoView.getEmail());
		datosComprobante.setCodigoExtraccion(respuesta.getCodigoExtraccion());
		datosComprobante.setFechaVencimiento(respuesta.getFechaVencimiento());
		datosComprobante.setCodigoTransaccion(respuesta.getCodigoTransaccion());
		datosComprobante.setNroComprobante(respuesta.getNumeroComprobante());
		datosComprobante.setFechaHoraComprobante(respuesta.getFechaHoraComprobante());
		
		return datosComprobante;
	}

	@Override
	public Respuesta<ReporteView> generarComprobantePDF() {
		DatosComprobanteExtraccionEfectivoView datosComprobante = sesionParametros.getDatosComprobanteExtraccionEfectivoView();

		Respuesta<Reporte> reporteRespuesta = extraccionEfectivoBO.generarComprobantePDF(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
			if (!sesionParametros.getEstadisticaOKDescargaPDFExtraccionEfectivoGrabada()) {
				estadisticaManager.add(EstadisticasConstants.EXTRACCION_EFECTIVO_DESCARGA_PDF, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				sesionParametros.setEstadisticaOKDescargaPDFExtraccionEfectivoGrabada(Boolean.TRUE);
			}
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, "ERROR_GENERICO", CodigoMensajeConstantes.EXTRACCION_EFECTIVO_DESCARGA_PDF_ERROR);
			estadisticaManager.add(EstadisticasConstants.EXTRACCION_EFECTIVO_DESCARGA_PDF, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
}
