/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.bo.impl;

import java.text.SimpleDateFormat;

import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagoautomatico.bo.PagoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.dao.PagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.seguros.dto.EmisionOfertaIntegradaDTO;
import ar.com.santanderrio.obp.servicios.seguros.bo.SeguroTenenciasBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class PagoAutomaticoBOImpl.
 *
 * @author B041299
 */
/**
 * @author maximiliano_cuno
 *
 */
/**
 * @author maximiliano_cuno
 *
 */
@Component
public class PagoAutomaticoBOImpl implements PagoAutomaticoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoAutomaticoBOImpl.class);

	/** The pago automatico DAO. */
	@Autowired
	private PagoAutomaticoDAO pagoAutomaticoDAO;

	/** The mensaje dao. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The buscador empresa BO. */
	@Autowired
	private BuscadorEmpresaBO buscadorEmpresaBO;

	@Autowired
	private SeguroTenenciasBO seguroTenenciasBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	@Autowired
	private SesionCliente sesionCliente;

	/** The Constant ERROR_ADHESION_PAGO. */
	private static final String ERROR_ADHESION_PAGO = "10000044";

	/** The Constant ERROR_PAGO_AUTOMATICO_REINTENTAR. */
	private static final String ERROR_PAGO_AUTOMATICO_REINTENTAR = "ERROR_PAGO_AUTOMATICO_REINTENTAR";

	/** The Constant ERROR_PAGO_AUTOMATICO_CONTINUAR. */
	private static final String ERROR_PAGO_AUTOMATICO_CONTINUAR = "ERROR_PAGO_AUTOMATICO_CONTINUAR";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteAdhesion(ComprobanteDebitoAutomatico comprobanteDebitoAutomatico) {
		Reporte reporte = pagoAutomaticoDAO.generarComprobanteAdhesion(comprobanteDebitoAutomatico);
		return respuestaFactory.crearRespuestaOk(reporte);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<AdhesionPagoAutomatico> ejecutarAdhesionPagoAutomatico(Cliente cliente,
			AdhesionPagoAutomatico adhesionPagoAutomatico) {

		String nroError = CodigoMensajeConstantes.PAGO_AUTOMATICO_ADHESION_ERROR;
		String numeroPoliza = null;

		if (!adhesionPagoAutomatico.getIsFromCalendario()) {
			try {
				pagoAutomaticoDAO.adhierePagoMisCuentas(cliente, adhesionPagoAutomatico);
				LOGGER.info("Adherido a Pago mis cuentas en forma exitosa!");
			} catch (DAOException e) {
				LOGGER.error("Error en ejecucion servicio ALTPESADHE." + e.getMessage());
				if (ERROR_ADHESION_PAGO.equals(e.getErrorCode())) {
					nroError = CodigoMensajeConstantes.PAGO_AUTOMATICO_ADHESION_ERROR_PMC;
				}
				return armarRespuestaErronea(adhesionPagoAutomatico, nroError);
			}
		}

		try {
			ResultadoTransaccion resultadoALTPAUADHET = pagoAutomaticoDAO.adhierePagoAutomatico(cliente,
					adhesionPagoAutomatico);
			LOGGER.info("Adherido a Pago automatico en forma exitosa!");
			return armarRespuestaComprobanteAdhesion(resultadoALTPAUADHET, adhesionPagoAutomatico);
		} catch (DAOException e) {
			LOGGER.error("Error en ejecucion servicio de Pago Automatico ALTPAUADHE."
					+ " No se pudo adherir a Pago Automatico." + e.getMessage());
			if (ERROR_ADHESION_PAGO.equals(e.getErrorCode())) {
				nroError = CodigoMensajeConstantes.PAGO_AUTOMATICO_ADHESION_ERROR_PMC;
			}
			bajaPagoMisCuentas(cliente, adhesionPagoAutomatico);
		}
		return armarRespuestaErronea(adhesionPagoAutomatico, nroError);
	}

	/**
	 * Baja pago mis cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 */
	private void bajaPagoMisCuentas(Cliente cliente, AdhesionPagoAutomatico adhesionPagoAutomatico) {
		if (!adhesionPagoAutomatico.getIsFromCalendario()) {
			try {
				pagoAutomaticoDAO.bajaPagoMisCuentas(cliente, adhesionPagoAutomatico);
				LOGGER.info("Dado de baja de Pago mis cuentas en forma exitosa.");
			} catch (DAOException e) {
				LOGGER.error("Error al intentar dar de baja en PagoMisCuentas." + e.getMessage());
			}
		}
	}

	/**
	 * Armar respuesta comprobante adhesion.
	 *
	 * @param resultadoALTPAUADHET
	 *            the resultado ALTPAUADHET
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @return the respuesta
	 */
	private Respuesta<AdhesionPagoAutomatico> armarRespuestaComprobanteAdhesion(
			ResultadoTransaccion resultadoALTPAUADHET, AdhesionPagoAutomatico adhesionPagoAutomatico) {
		AdhesionPagoAutomatico comprobante = new AdhesionPagoAutomatico();
		comprobante.setNroDeComprobante(resultadoALTPAUADHET.getNumeroComprobante());
		comprobante.setImporteLimite(adhesionPagoAutomatico.getImporteLimite());
		comprobante.setFiid(adhesionPagoAutomatico.getFiid());
		comprobante.setCodigoPagoElectronico(adhesionPagoAutomatico.getCodigoPagoElectronico());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String fechaHoraActualFormateada = sdf.format(resultadoALTPAUADHET.getFechaTransaccion());
		comprobante.setFechaHora(fechaHoraActualFormateada);

		String mensajeFeedback = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_AUTOMATICO_ADHESION_OK,
				adhesionPagoAutomatico.getNombreEmpresa()).getMensaje();
		comprobante.setMensajeFeedback(mensajeFeedback);
		if(adhesionPagoAutomatico.getEmisionGastosProtegido() != null && adhesionPagoAutomatico.getEmisionGastosProtegido().getNumeroCotizacion() != null) {
			Cliente cliente = sesionCliente.getCliente();

			String numeroPoliza = null;
			comprobante.setAlias(null);
			comprobante.setNumero(null);
			comprobante.setDescripcionTipoCuenta(null);
			if (adhesionPagoAutomatico != null && adhesionPagoAutomatico.getEmisionGastosProtegido() != null) {
				Cuenta cuenta = cliente.getCuenta(adhesionPagoAutomatico.getEmisionGastosProtegido().getCbu());
				if (cuenta == null) {
					LOGGER.info("El Cbu no pertence a la cuenta");
					comprobante.setNroPolizaGastosProtegido(null);
				} else {

					Respuesta<EmisionOfertaIntegradaDTO> emitirGastosProtegidos = seguroTenenciasBO.emisionOfertaIntegradaGastoProtegido(adhesionPagoAutomatico.getEmisionGastosProtegido(), cliente, cuenta.getNroSucursal(), cuenta.getTipoCuenta(), cuenta.getNroCuentaProducto(), adhesionPagoAutomatico.getNombreEmpresa());

					if (emitirGastosProtegidos != null && emitirGastosProtegidos.getRespuesta() != null && emitirGastosProtegidos.getRespuesta().getNumeroPoliza() != null) {
						numeroPoliza = emitirGastosProtegidos.getRespuesta().getNumeroPoliza().toString();
					}
					if (adhesionPagoAutomatico.getAlias() != null) {
						comprobante.setAlias(adhesionPagoAutomatico.getAlias());
					}
					if (adhesionPagoAutomatico.getNumero() != null) {
						comprobante.setNumero(adhesionPagoAutomatico.getNumero());
					}
					if (adhesionPagoAutomatico.getDescripcionTipoCuenta() != null) {
						comprobante.setDescripcionTipoCuenta(adhesionPagoAutomatico.getDescripcionTipoCuenta());
					}

				}


				comprobante.setNroPolizaGastosProtegido(numeroPoliza != null ? numeroPoliza : null);
			}
		}
		return respuestaFactory.crearRespuestaOk(comprobante);
	}

	/**
	 * arma la respuesta de error.
	 *
	 * @param adhesionPagoAutomatico
	 *            the adhesion pago automatico
	 * @param nroError
	 *            the nro error
	 * @return the respuesta
	 */
	private Respuesta<AdhesionPagoAutomatico> armarRespuestaErronea(AdhesionPagoAutomatico adhesionPagoAutomatico,
			String nroError) {

		MedioPagoView medioPago = buscadorEmpresaBO.getByCodigo(adhesionPagoAutomatico.getFiid().trim()).getRespuesta();

		String mensaje = mensajeBO.obtenerMensajePorCodigo(nroError, medioPago.getNombreFantasia()).getMensaje();

		Respuesta<AdhesionPagoAutomatico> rta = new Respuesta<AdhesionPagoAutomatico>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje(mensaje);
		if (sessionParametros.getContador().permiteReintentar()) {
			itemMensajeRespuesta.setTipoError(ERROR_PAGO_AUTOMATICO_REINTENTAR);
		} else {
			itemMensajeRespuesta.setTipoError(ERROR_PAGO_AUTOMATICO_CONTINUAR);
		}

		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		rta.setRespuesta(adhesionPagoAutomatico);
		rta.add(itemMensajeRespuesta);

		return rta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoAutomaticoBO#
	 * eliminarPagoPuntual(ar.com.santanderrio.obp.servicios.pagos.entities.
	 * PagoPendiente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> eliminarPagoPuntual(PagoPendiente pagoPendiente, Cliente cliente) {
		try {
			Respuesta<ResultadoTransaccion> resultado = pagoAutomaticoDAO.eliminarPagoPuntual(pagoPendiente, cliente);
			if (EstadoRespuesta.OK.equals(resultado.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaOk(resultado.getRespuesta(), null,
						CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_OK_200);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return respuestaFactory.crearRespuestaWarning(null, TipoError.WARNING,
				CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_ERROR_201);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarBajaAdhesion(PagoPendiente pagoPendiente, Cliente cliente) {
		Respuesta<ResultadoTransaccion> respuesta = new Respuesta<ResultadoTransaccion>();
		try {
			ResultadoTransaccion resultado = pagoAutomaticoDAO.ejecutarBajaAdhesion(pagoPendiente, cliente);
			LOGGER.info("Dado de baja de adhesión en forma exitosa.");
			respuesta = respuestaFactory.crearRespuestaOk(resultado);
		} catch (DAOException e) {
			LOGGER.error("Error al intentar modificar adhesión", e);
			respuesta = respuestaFactory.crearRespuestaWarning("errorBajaAdhesion",
					TipoError.ERROR_CON_REINTENTOS, CodigoMensajeConstantes.DEBITO_AUTOMATICO_BAJA_ADHESION_ERROR);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.pagoautomatico.bo.PagoAutomaticoBO#generarComprobanteAdhesionPagoAutomatico(ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteAdhesionDebitoAutomatico)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteAdhesionPagoAutomatico(
			ComprobanteAdhesionDebitoAutomatico comprobanteAdhesionDebitoAutomatico) {
		Reporte reporte = pagoAutomaticoDAO.generarComprobanteAdhesionPagoAutomatico(comprobanteAdhesionDebitoAutomatico);
		return respuestaFactory.crearRespuestaOk(reporte);

	}

}
