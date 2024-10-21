package ar.com.santanderrio.obp.servicios.extraccionefectivo.bo;

import java.text.MessageFormat;

import javax.ws.rs.InternalServerErrorException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dao.ExtraccionEfectivoDAO;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.CardlessWithdrawalResponse;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@Component
@Qualifier("extraccionEfectivoBOImpl")
public class ExtraccionEfectivoBOImpl implements ExtraccionEfectivoBO {

	@Autowired
	public ExtraccionEfectivoDAO extraccionEfectivoDao;
	
	@Autowired
	protected EstadisticaManager estadisticaManager;
	
	@Autowired
	protected RespuestaFactory respuestaFactory;
	
	@Autowired
	SesionParametros sesionParametros;
	
	@Autowired
	private MensajeBO mensajeBO;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionEfectivoBOImpl.class);
	
	@Override
	public Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(Cuenta cuenta, Cliente cliente, int monto, String email) {
		try {
			inicializarReintentos();
			CardlessWithdrawalResponse solicitud = extraccionEfectivoDao.ejecutarSolicitud(cuenta, cliente, monto, email);
			ExtraccionEfectivoView feedback = new ExtraccionEfectivoView();
			String montoString = ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(monto));
			feedback.setMensajeOK(MessageFormat.format(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_FEEDBACK_OK).getMensaje(), montoString, solicitud.getCodigoExtraccion()));
			DateTime fechaHoy = new DateTime();
			feedback.setFechaHoraComprobante(fechaHoy.toString("dd/MM/yyyy") + " - " + fechaHoy.toString("HH:mm:ss") + " hs.");
			DateTime fechaExpiracion = new DateTime(solicitud.getFechaExpiracion());
			feedback.setFechaVencimiento(fechaExpiracion.toString("dd/MM/yyyy"));
			feedback.setNumeroComprobante(solicitud.getNumeroComprobante());
			feedback.setCodigoExtraccion(solicitud.getCodigoExtraccion());
			feedback.setCodigoTransaccion(String.valueOf(solicitud.getId()));
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_EXTRACCION_EFECTIVO, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			LOGGER.info("Se ha efectuado la extraccion sin tarjeta con exito. Cliente: {} - Cuenta: {} - Monto: {}", cliente.getDni(), cuenta.getNroCuentaProducto(), montoString);
			return this.respuestaFactory.crearRespuestaOk(feedback);
		}
		catch (InternalServerErrorException e) {
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_EXTRACCION_EFECTIVO,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("Error con servicio de tarjetas", e);
			if (sesionParametros.getContador().permiteReintentar()) {
				sesionParametros.setPrimerAcceso(false);
				String montoString = ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(monto));
				String mensajeError = MessageFormat.format(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_ADMITE_REINTENTOS).getMensaje(), montoString);
		    	return respuestaFactory.crearRespuestaErrorPersonalizado(ExtraccionEfectivoView.class, mensajeError, TipoError.EXTRACCION_EFECTIVO_ERROR_PERMITE_REINTENTOS.name());
			} else {
				sesionParametros.setPrimerAcceso(true);
		    	return respuestaFactory.crearRespuestaError("", TipoError.EXTRACCION_EFECTIVO_ERROR_REINTENTOS_AGOTADOS, CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO);
			}
		} catch(Exception e) {
			sesionParametros.setPrimerAcceso(true);
			estadisticaManager.add(EstadisticasConstants.SOLICITAR_EXTRACCION_EFECTIVO, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error("Error con servicio de tarjetas", e);
	    	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO);
		}
	}
	
	@Override
	public Respuesta<Reporte> generarComprobantePDF(DatosComprobanteExtraccionEfectivoView datosComprobante) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = extraccionEfectivoDao.generarComprobantePDF(datosComprobante);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}
	
    private void inicializarReintentos() {
        if (sesionParametros.getPrimerAcceso()) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }
    }
}