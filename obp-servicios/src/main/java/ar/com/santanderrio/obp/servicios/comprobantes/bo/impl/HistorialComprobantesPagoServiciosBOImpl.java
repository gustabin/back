package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesPMCUnificadoBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.HistorialComprobantesPagoServiciosBO;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoDeComprasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

@Component
public class HistorialComprobantesPagoServiciosBOImpl implements HistorialComprobantesPagoServiciosBO {

	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private ComprobantesPMCUnificadoBO comprobantesPMCBO;

	/** The MediosPagoBO. */
	@Autowired
	private BuscadorEmpresaDAO buscadorEmpresaDAO;

	/** The sub empresa. */
	@Autowired
	private SubEmpresasDAO subEmpresa;

	/** The scomp BO. */
	@Autowired
	private ScompBO scompBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HistorialComprobantesPagoServiciosBOImpl.class);

	@Override
	public Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoServicios(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccionDTO) {
		switch (comprobanteSeleccionado.getHistorial()) {
		case PAGO_SERVICIOS_A:
			return obtenerHistorialPagoServiciosA(comprobanteSeleccionado, cliente, transaccionDTO);
		case PAGO_SERVICIOS_B:
			return obtenerHistorialPagoServiciosB(comprobanteSeleccionado, cliente, transaccionDTO);
		case PAGO_SERVICIOS_C:
			return obtenerHistorialPagoServiciosC(comprobanteSeleccionado, cliente, transaccionDTO);
		default:
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoServiciosA(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccion) {
		transaccion.setEmpresa(((DetalleComprobantePagoMisCuentasDTO) comprobanteSeleccionado.getDetalle()).getFiid());
		return obtenerRespuestaCNSPESPAGE(comprobanteSeleccionado, cliente, transaccion);
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoServiciosB(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccion) {
		String fiid = obtenerFiidPorNombreFantasia(comprobanteSeleccionado.getDestinatario());
		if (fiid == null) {
			LOGGER.info("No se encontró fiid por nombre de fantasía en MediosDePago.txt, se devuelve error");
			return armarRespuestaErrorMediosDePagoTxt();
		}
		String respuestaSubEmpresas = null;
		try {
			respuestaSubEmpresas = subEmpresa.obtenerSubEmpresa(fiid);
		} catch (DAOException e) {
			LOGGER.info(
					"Error al consultar PesSubEmpresas.txt, se continúa con el pes_fiid encontrado en MediosDePago.txt");
		}
		transaccion.setEmpresa(respuestaSubEmpresas != null ? respuestaSubEmpresas : fiid);
		return obtenerRespuestaCNSPESPAGE(comprobanteSeleccionado, cliente, transaccion);
	}

	private String obtenerFiidPorNombreFantasia(String nombreFantasiaEmpresa) {
		Set<MedioPago> setMedioPago = buscadorEmpresaDAO.searchEmpresaByNombreFantasia(nombreFantasiaEmpresa);
		List<MedioPago> mediosPago = new ArrayList<MedioPago>(setMedioPago);
		for (MedioPago medioPago : mediosPago) {
			if (StringUtils.isNotBlank(medioPago.getFiid())
					&& medioPago.getNombreFantasia().equals(nombreFantasiaEmpresa)) {
				return medioPago.getFiid();
			}
		}
		return null;
	}

	private Respuesta<List<ComprobanteDTO>> obtenerHistorialPagoServiciosC(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccion) {
		Respuesta<ComprobantesDTO> respuesta = scompBO.obtenerComprobantesPagoDeCompras(cliente.getNup(), transaccion);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuesta.getRespuesta().getComprobantes();
		filtrarComprobantesPagoCompras(comprobantes, comprobanteSeleccionado);
		return respuestaFactory.crearRespuestaOk(comprobantes);
	}

	private Respuesta<List<ComprobanteDTO>> obtenerRespuestaCNSPESPAGE(ComprobanteDTO comprobanteSeleccionado,
			Cliente cliente, TransaccionDTO transaccion) {
		Respuesta<ComprobantesDTO> respuesta = comprobantesPMCBO.obtenerComprobantesPMCPorEmpresa(cliente, transaccion);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())
				|| CollectionUtils.isEmpty(respuesta.getRespuesta().getComprobantes())) {
			LOGGER.error("Fallo en servicio o no se encontraron comprobantes en los ultimos doce meses, se arma respuesta error");
			return armarRespuestaErrorGenericoHistorialComprobantes();
		}
		List<ComprobanteDTO> comprobantes = respuesta.getRespuesta().getComprobantes();
		filtrarComprobantesPagoServiciosPorIdentificacion(comprobantes, comprobanteSeleccionado);
		return respuestaFactory.crearRespuestaOk(comprobantes);
	}

	private void filtrarComprobantesPagoCompras(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object;
				DetalleComprobantePagoDeComprasDTO detalle = (DetalleComprobantePagoDeComprasDTO) comprobante
						.getDetalle();
				DetalleComprobantePagoDeComprasDTO detalleSeleccionado = (DetalleComprobantePagoDeComprasDTO) comprobanteSeleccionado
						.getDetalle();
				return StringUtils.equals(detalle.getIdentificacion(), detalleSeleccionado.getIdentificacion())
						&& StringUtils.equals(detalle.getIdEmpresa(), detalleSeleccionado.getIdEmpresa());
			}
		});
	}

	private void filtrarComprobantesPagoServiciosPorIdentificacion(List<ComprobanteDTO> comprobantes,
			final ComprobanteDTO comprobanteSeleccionado) {
		CollectionUtils.filter(comprobantes, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ComprobanteDTO comprobante = (ComprobanteDTO) object; 
				if (StringUtils.length(comprobanteSeleccionado.getDetalle().obtenerIdentificacionHistorial()) == 11) {
					return StringUtils.equalsIgnoreCase(
							StringUtils.left(comprobante.getDetalle().obtenerIdentificacionHistorial(), 11),
							comprobanteSeleccionado.getDetalle().obtenerIdentificacionHistorial());
				}
				return StringUtils.equalsIgnoreCase(comprobante.getDetalle().obtenerIdentificacionHistorial(),
						comprobanteSeleccionado.getDetalle().obtenerIdentificacionHistorial());
			}
		});
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorMediosDePagoTxt() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.HISTORIAL_COMPROBANTES_ERROR_MEDIOSPAGOTXT);
	}

	private Respuesta<List<ComprobanteDTO>> armarRespuestaErrorGenericoHistorialComprobantes() {
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_COMPROBANTES,
				CodigoMensajeConstantes.ERROR_COMPROBANTES);
	}

}
