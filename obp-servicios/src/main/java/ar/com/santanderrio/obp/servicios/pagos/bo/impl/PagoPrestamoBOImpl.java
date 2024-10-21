/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.ImporteCuotaHipotecarioUVaException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.NoTieneFondosSuficientesException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoConAnterioridadException;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;

/**
 * The Class PagoPrestamoBOImpl.
 */
@Component
public class PagoPrestamoBOImpl implements PagoPrestamoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoPrestamoBOImpl.class);

	/** The Constant ERROR_EN_INTERVINIENTE. */
	private static final String ERROR_EN_INTERVINIENTE = "No podemos realizar esta operación en este momento. El horario de pago de cuota de préstamo es de Lunes a Domingos de 7.00hs a 21.00hs";

	/** The Constant ERROR_EN_HORARIO_DE_PAGO. */
	private static final String ERROR_EN_HORARIO_DE_PAGO = "No se puede realizar el pago en este momento. Intente más tarde";

	/** The hora inicio operaciones. */
	@Value("${CREDITOS.HORAINICIOOPERACIONES}")
	private String horaInicioOperaciones;

	/** The hora fin operaciones. */
	@Value("${CREDITOS.HORAFINOPERACIONES}")
	private String horaFinOperaciones;

	/** The pago prestamo DAO. */
	@Autowired
	private PagoPrestamoDAO pagoPrestamoDAO;

	/** The intervinientes DAO. */
	@Autowired
	private IntervinientesDAO intervinientesDAO;

	/** The respuesta BO. */
	@Autowired
	private RespuestaBO respuestaBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The simulador prestamo bo. */
	@Autowired
	private SimuladorPrestamoBO simuladorPrestamoBo;
	
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.bo.PagoPrestamoBO#pagar(ar.com.
	 * santanderrio.obp.servicios.pagos.entities.PagoPrestamo, int)
	 */
	@Override
	public Respuesta<ComprobantePrestamo> pagar(PagoPrestamo pagoPrestamo, int cantidadCuentasDebito) {

		if (!(pagoPrestamo.getCuentaSeleccionada() instanceof Cuenta)) {
			return null;
		}

		Prestamo prestamo = pagoPrestamo.getPagoPendientePrestamo().getPrestamo();
		Cuenta cuenta = (Cuenta) pagoPrestamo.getCuentaSeleccionada();

		boolean excedeReintentos = pagoPrestamo.getContadorIntentos().excedeReintentos();

		Respuesta<ComprobantePrestamo> respuesta = new Respuesta<ComprobantePrestamo>();
		try {
			// El cliente que se pasa al DAO es el que esta asociado a la cuenta
			// (por eso se extrae de la misma)
			Interviniente interviniente = intervinientesDAO
					.obtenerIntervinienteTitular(prestamo.getCuenta().getCliente(), prestamo.getCuenta());
			if (interviniente != null) {
				if (simuladorPrestamoBo.chequearSiEstaEnHorarioOperacion()) {
					if (!excedeReintentos) {
						ComprobantePrestamo comprobantePagoPrestamo = pagoPrestamoDAO.pagar(prestamo, cuenta);
						comprobantePagoPrestamo.setInterviniente(interviniente);
						comprobantePagoPrestamo.setCuenta(cuenta);
						respuesta.setRespuesta(comprobantePagoPrestamo);
						respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
					} else {
						respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_PAGO_PRESTAMO,
								CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO, null);
					}
				} else {
					LOGGER.debug(ERROR_EN_HORARIO_DE_PAGO);
					String[] parametros = { horaInicioOperaciones, horaFinOperaciones };
					respuestaBO.armarRespuestaErrorParametrizado(respuesta, TipoError.ERROR_HORARIO_DE_OPERACIONES,
							CodigoMensajeConstantes.CODIGO_ERROR_HORARIO_DE_OPERACIONES, "", parametros);
				}
			} else {
				LOGGER.debug(ERROR_EN_INTERVINIENTE);
				respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_PAGO_PRESTAMO,
						CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO, null);
			}
		} catch (PagoConAnterioridadException e) {
			LOGGER.error(e.getMessage(), e);
			respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_PAGO_CON_ANTERIORIDAD,
					CodigoMensajeConstantes.CODIGO_ERROR_PAGO_CON_ANTERIORIDAD, null);
		} catch (NoTieneFondosSuficientesException e) {
			LOGGER.error(e.getMessage(), e);
			if (cantidadCuentasDebito > 1) {
				respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS,
						CodigoMensajeConstantes.CODIGO_ERROR_SALDOS_INSUFICIENTES_VARIAS_CUENTAS, null);
			} else {
				respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_SALDOS_INSUFICIENTES,
						CodigoMensajeConstantes.CODIGO_ERROR_SALDOS_INSUFICIENTES, obtenerTagCuenta(cuenta));
			}
		} catch (ImporteCuotaHipotecarioUVaException e) {
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_PRESTAMO, CodigoMensajeConstantes.PRESTAMO_HIPOTECARIO_UVA_ERROR_IMPORTE);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			// validar reintentos y suma plusplus
			boolean permiteReintentar = pagoPrestamo.getContadorIntentos().permiteReintentar();
			setRespuestaError(respuesta, permiteReintentar);
		}
		return respuesta;
	}

	/**
	 * Sets the respuesta error.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param permiteReintentar
	 *            the permite reintentar
	 */
	private void setRespuestaError(Respuesta<ComprobantePrestamo> respuesta, boolean permiteReintentar) {
		if (permiteReintentar) {
			respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_PAGO_PRESTAMO_CON_REINTENTO,
					CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO, null);
		} else {
			respuestaBO.armarRespuestaError(respuesta, TipoError.ERROR_PAGO_PRESTAMO,
					CodigoMensajeConstantes.CODIGO_ERROR_PAGO_PRESTAMO, null);
		}
	}

	/**
	 * Obtener tag cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerTagCuenta(Cuenta cuenta) {
		String numero = ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto());
		String sucursal = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal());
		return sucursal + "-" + numero;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.pagos.bo.PagoPrestamoBO#obtenerMensajePagoOk(ar.
	 * com.santanderrio.obp.pagos.entities.Prestamo)
	 */
	@Override
	public String obtenerMensajePagoOk(Prestamo prestamo, ComprobantePrestamo comprobantePrestamo) {
		Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_PRESTAMO_OK);

		String descripcionTipoPrestamo = prestamo.getTipoPrestamoEnum().getDescripcion();
		String numeroPrestamo = ISBANStringUtils.formatearSucursal(prestamo.getCuenta().getNroSucursal()) + "-"
				+ ISBANStringUtils.agregarBarraNumeroPrestamo(
						ISBANStringUtils.formateadorConCerosIzq(prestamo.getNumeroCuentaProducto(), 12));

		String aliasPrestamo = prestamo.getCuenta().getAlias();
		String nroCuota = ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo());
		if (StringUtils.isNotBlank(aliasPrestamo)) {
			aliasPrestamo = "\"" + aliasPrestamo + "\"";
		}
		String importe = DivisaEnum.PESO.getSimbolo()
				+ ISBANStringUtils.formatearSaldoConSigno(comprobantePrestamo.getImporteDebito());
		return MessageFormat.format(mensaje.getMensaje(), nroCuota, descripcionTipoPrestamo, aliasPrestamo,
				numeroPrestamo, importe);
	}

}
