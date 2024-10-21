/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoTarjetaCreditoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoMonedaPagoTCEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoPagoTCEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaAdheridaDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaPago;

/**
 * The Class PagoTarjetaCreditoBOImpl.
 */
@Component
public class PagoTarjetaCreditoBOImpl implements PagoTarjetaCreditoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoTarjetaCreditoBOImpl.class);

	/** The Constant CODIGO_MENSAJE_DEVOLUCION_PAGO. */
	private static final String CODIGO_MENSAJE_DEVOLUCION_PAGO = "1173";

	/** The Constant CODIGO_MENSAJE_ERROR_PAGO_STOP_DEBIT. */
	private static final String CODIGO_MENSAJE_ERROR_PAGO_STOP_DEBIT = "1176";

	/** The Constant CODIGO_MENSAJE_ERROR_PAGO. */
	private static final String CODIGO_MENSAJE_ERROR_PAGO = "1175";

	/** The Constant PROGRAMAR_PAGO. */
	private static final String PROGRAMAR_PAGO = "programar";

	/** The Constant REALIZAR_PAGO. */
	private static final String REALIZAR_PAGO = "realizar";

	/** The Constant CODIGO_RETORNO_SALDO_INSUF_CTA_UNIC. */
	private static final String CODIGO_RETORNO_SALDO_INSUF_CTA_UNIC = "10002122";

	/** The Constant CODIGO_RETORNO_SALDO_INSUF_CTA_CTE. */
	private static final String CODIGO_RETORNO_SALDO_INSUF_CTA_CTE = "10000515";

	/** The Constant CODIGO_RETORNO_DIA_NO_LABORAL. */
	private static final String CODIGO_RETORNO_DIA_NO_LABORAL = "10000031";
	
	/** The Constant COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD. */
	private static final String COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD = "10009079";
	
	/** The Constant COD_CUENTA_ALFA_NO_VERIFICADA. */
	private static final String COD_CUENTA_ALFA_NO_VERIFICADA = "10009080";

	/** The Constant CODIGO_LEGALES_SEUO. */
	private static final String CODIGO_LEGALES_SEUO = "1005";

	/** The Constant TEXTO_FINAL_PAGO_PUNTUAL. */
	private static final String TEXTO_FINAL_PAGO_PUNTUAL = "realizó con éxito";

	/** The Constant TEXTO_FINAL_PAGO_PROGRAMADO. */
	private static final String TEXTO_FINAL_PAGO_PROGRAMADO = "programó para el día ";

	/** The Constant PAGO_MINIMO. */
	private static final String PAGO_MINIMO = "Pago Mínimo";

	/** The Constant PAGO_TOTAL. */
	private static final String PAGO_TOTAL = "Pago Total";

	/** The Constant PAGO_PARCIAL. */
	private static final String PAGO_PARCIAL = "Pago Parcial";

	/** The Constant PAGO_SALDO. */
	private static final String PAGO_SALDO = "Saldo A Pagar";

	/** The Constant MENSAJE_SALDO_INSUFICIENTE. */
	private static final String MENSAJE_SALDO_INSUFICIENTE = "1178";

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The pago tarjeta credito DAO. */
	@Autowired
	private PagoTarjetaCreditoDAO pagoTarjetaCreditoDAO;

	/** The deuda pago automatico DAO. */
	@Autowired
	private DeudaPagoAutomaticoDAO deudaPagoAutomaticoDAO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The mensaje dao. */
	@Autowired
	private LegalDAO legalDAO;

	/** The datos tarjeta dao. */
	@Autowired
	private DatosTarjetaDAO datosTarjetaDAO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/**
	 * Pagar tarjeta de credito.
	 *
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobantePagoTarjeta> pagar(DatosPagoTC datosPagoTC, Cliente cliente) {
		try {
			String nroComprobante = pagoTarjetaCreditoDAO.pagar(cliente, datosPagoTC);
			return respuestaPagoOk(nroComprobante, datosPagoTC);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if ("TIME_OUT".equals(e.getMessage())) {
				Respuesta<ComprobantePagoTarjeta> errorTimeOut = respuestaFactory.crearRespuestaError(
						ComprobantePagoTarjeta.class,
						MessageFormat.format(obtenerMensajeErrorSegunStopDebit(datosPagoTC), REALIZAR_PAGO,
								datosPagoTC.getNroTarjetaFormateado()),
						null);
				errorTimeOut.getItemsMensajeRespuesta().get(0)
						.setTipoError(TipoError.ERROR_TIME_OUT_PAGO_TARJETA.getDescripcion());
				errorTimeOut.setRespuestaVacia(true);
				return errorTimeOut;
			} else {
				return respuestaPagoError(e.getErrorCode(), datosPagoTC);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobantePagoTarjeta> programarPago(DatosPagoTC pagoProgramado, Cliente cliente) {

		try {
			String nroComprobante = pagoTarjetaCreditoDAO.programarPago(cliente, pagoProgramado);
			return respuestaPagoOk(nroComprobante, pagoProgramado);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			if ("TIME_OUT".equals(e.getMessage())) {
				Respuesta<ComprobantePagoTarjeta> errorTimeOut = respuestaFactory.crearRespuestaError(
						ComprobantePagoTarjeta.class, "<p>La operación expiro por falta de actividad</p>", null);
				errorTimeOut.getItemsMensajeRespuesta().get(0)
						.setTipoError(TipoError.ERROR_TIME_OUT_PAGO_TARJETA.getDescripcion());
				errorTimeOut.setRespuestaVacia(true);
				return errorTimeOut;
			} else {
				return respuestaPagoError(e.getMessage(), pagoProgramado);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DebitoAutomatico> obtenerDeudasConDebitoAutomatico(Cliente cliente, Cuenta tarjetaActiva)
			throws BusinessException {
		try {
			return deudaPagoAutomaticoDAO.obtenerDeudasConDebitoAutomatico(cliente, tarjetaActiva);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO#actualizarStopDebit(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente, java.util.List)
	 */
	@Override
    public void actualizarStopDebit(Cliente cliente, List<PagoPendiente> pagosPendientes) {
	       List<PagoPendiente> listaPagosPendientesConDebit = new ArrayList<PagoPendiente>();
        for (PagoPendiente pagoPendiente : pagosPendientes) {
            if (pagoPendiente.getTipoPago().equals(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO)
                    || pagoPendiente.getTipoPago().equals(TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOTOTAL)) {
                String nroTarjetaEnmascarado = ISBANStringUtils.mascaraTarjetaCredito(pagoPendiente.getNroTarjetaCredito(),
                        pagoPendiente.getTipoCuentaTarjeta().getCodigo());
                Cuenta tarjetaElegida = cliente.getTarjeta(nroTarjetaEnmascarado);
                pagoPendiente.setNroCuentaProducto(tarjetaElegida.getNroCuentaProducto());
                listaPagosPendientesConDebit.add(pagoPendiente);
            }
        }
        if (listaPagosPendientesConDebit.isEmpty()) {
            return;
        }
	    
	    for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
	        try {
	            List<DebitoAutomatico> listaDebitoAutomatico = deudaPagoAutomaticoDAO.obtenerDeudasConDebitoAutomatico(cliente, cuenta);
                for (DebitoAutomatico debitoAutom : listaDebitoAutomatico) {
                    for (PagoPendiente pagoPendiente : listaPagosPendientesConDebit) {
	                    if (debitoAutom.esProductoAdherido(pagoPendiente.getNroCuentaProducto())) {
	                        pagoPendiente.setTieneStopDebit(debitoAutom.tieneStopDebit());
	                        listaPagosPendientesConDebit.remove(pagoPendiente);
	                        break;
	                    }
	                }
                }
	            
            } catch (DAOException e) {
                LOGGER.error(e.getMessage(), e);
            }
	        
	        if (listaPagosPendientesConDebit.isEmpty()) {
	            return;
	        }
	    }
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO#
	 * obtenerDetalleTarjetaPago(java.lang.String, java.util.Date,
	 * ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum)
	 */
	@Override
	public DetalleTarjetaPago obtenerDetalleTarjetaPago(String numeroTarjeta, Date fecha, TipoDePagoEnum tipoDePago)
			throws BusinessException {

		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaTarjeta = cliente.getTarjeta(numeroTarjeta);

		try {
			if (TipoDePagoEnum.PAGOPROGRAMADO.equals(tipoDePago)) {
				DatosTarjeta datosTarjeta = datosTarjetaDAO.obtenerPagoProgramado(cliente, cuentaTarjeta, fecha);
				return DetalleTarjetaPago.crearPagoProgramado(datosTarjeta,
						cliente.getCuentaPorNumero(datosTarjeta.getNumeroCuentaBancoPesos()),
						cliente.getCuentaPorNumero(datosTarjeta.getNumeroCuentaBancoDolares()), cuentaTarjeta,
						numeroTarjeta);
			}

			DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuentaTarjeta, true);
			DatosTarjeta datosTarjeta = datosTarjetaBuilder.build();
			if (TipoDePagoEnum.TARJETAPAGOPUNTUAL.equals(tipoDePago)) {
				return DetalleTarjetaPago.crearPagoPuntual(datosTarjeta, cuentaTarjeta, numeroTarjeta);
			}

			CuentaAdheridaDebitoAutomatico cuentaAdheridaDebitAutomatico = deudaPagoAutomaticoDAO
					.obtenerCuentaAdheridaDebitoAutomatico(cliente, numeroTarjeta);

			if (cuentaAdheridaDebitAutomatico != null) {
				return DetalleTarjetaPago.crearDebitoAutomatico(datosTarjeta, cuentaAdheridaDebitAutomatico, tipoDePago,
						cuentaTarjeta, numeroTarjeta);
			}

			LOGGER.info("No existen datos pertenecientes a la tarjeta: " + numeroTarjeta + " de tipo de pago: "
					+ tipoDePago);
		} catch (DAOException e) {
			LOGGER.error("Error al tratar de obtener datos de tarjeta", e);
		}
		throw new BusinessException();
	}

	/**
	 * Obtener datos tarjeta pago.
	 *
	 * @param numeroTarjeta the numero tarjeta
	 * @return the datos tarjeta
	 * @throws BusinessException the business exception
	 */
	@Override
	public DatosTarjeta obtenerDatosTarjetaPago(String numeroTarjeta) throws BusinessException {
		Cliente cliente = sesionCliente.getCliente();
		Cuenta cuentaTarjeta = cliente.getTarjeta(numeroTarjeta);
		try {
			DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(cliente, cuentaTarjeta, false);
			return datosTarjetaBuilder.build();
		} catch (DAOException e) {
			LOGGER.error("Error al tratar de obtener datos de tarjeta", e);
			throw new BusinessException();
		}
	}

	/**
	 * Ejecuta la baja de una tarjeta de credito de un pago programado.
	 *
	 * @author B041299. Manuel.Vargas
	 * @param pagoPendiente
	 *            the pago pendiente
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO#ejecutarBajaPagoProgramadoDeTarjetaCredito(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView,
	 *      ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> ejecutarBajaPagoProgramadoDeTarjetaCredito(PagoPendiente pagoPendiente,
			Cliente cliente) {
		Respuesta<ResultadoTransaccion> resultadoDAO = new Respuesta<ResultadoTransaccion>();
		Respuesta<ResultadoTransaccion> respuesta = new Respuesta<ResultadoTransaccion>();

		if (pagoPendiente.getImporte() == null) {
			pagoPendiente.setImporte(new BigDecimal(0));
		}
		if (pagoPendiente.getImporteUSS() == null) {
			pagoPendiente.setImporteUSS(new BigDecimal(0));
		}

		try {
			resultadoDAO = this.pagoTarjetaCreditoDAO.ejecutarBajaPagoProgramadoDeTarjetaCredito(pagoPendiente,
					cliente);
			if (EstadoRespuesta.OK.equals(resultadoDAO.getEstadoRespuesta())) {
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setItemMensajeRespuesta(
						this.getMessage(CodigoMensajeConstantes.CODIGO_BAJA_PAGOPROG_TCREDITO_OK));
				respuesta.setRespuesta(resultadoDAO.getRespuesta());
				respuesta.setRespuestaVacia(false);
			} else {
				respuestaConError(respuesta, EstadoRespuesta.WARNING);
			}
			return respuesta;
		} catch (DAOException daoEx) {
			LOGGER.error("Error o timeout al intentar dar de baja TCredito de pago programado", daoEx);
			return respuestaConError(respuesta, EstadoRespuesta.WARNING);
		} catch (Exception e) {
			LOGGER.error("Error al intentar dar de baja TCredito de pago programado", e);
			return respuestaConError(respuesta, EstadoRespuesta.ERROR);
		}
	}

	/**
	 * Respuesta pago ok.
	 *
	 * @param devolucionServicio
	 *            the devolucion servicio
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @return the respuesta
	 */
	private Respuesta<ComprobantePagoTarjeta> respuestaPagoOk(String devolucionServicio, DatosPagoTC datosPagoTC) {

		ComprobantePagoTarjeta comprobante = armarMensajePagoOkSinDebito(datosPagoTC);
		comprobante.setNroComprobante(devolucionServicio);
		Date fechaHoraActual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		String fechaHoraActualFormateada = sdf.format(fechaHoraActual);
		comprobante.setFechaHora(fechaHoraActualFormateada);
		try {
			String legal = legalDAO.obtenerLegal(CODIGO_LEGALES_SEUO);
			if (legal != null) {
				comprobante.setLegalesSEO(legal);
			}
		} catch (DAOException daoe) {
			LOGGER.error("Error al obtener el legal {}.", CODIGO_LEGALES_SEUO, daoe);
		}
		return respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class, comprobante);
	}

	/**
	 * Crea una Respuesta de un pago con ERROR.
	 *
	 * @param codigoError
	 *            the codigo error
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @return the respuesta
	 */
    private Respuesta<ComprobantePagoTarjeta> respuestaPagoError(String codigoError, DatosPagoTC datosPagoTC) {
        Respuesta<ComprobantePagoTarjeta> warninRst;
        if (StringUtils.equals(CODIGO_RETORNO_SALDO_INSUF_CTA_UNIC, codigoError)
                || StringUtils.equals(CODIGO_RETORNO_SALDO_INSUF_CTA_CTE, codigoError)) {
            warninRst = respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SALDO_INSUFICIENTE,
                    MENSAJE_SALDO_INSUFICIENTE);
        } else if (CODIGO_RETORNO_DIA_NO_LABORAL.equals(codigoError)) {
            warninRst = respuestaFactory.crearRespuestaError("", TipoError.ERROR_FECHA,
                    obtenerCodigoMensajeErrorSegunStopDebit(datosPagoTC),
                    datosPagoTC.getPagoProgramado() ? PROGRAMAR_PAGO : REALIZAR_PAGO,
                    datosPagoTC.getNroTarjetaFormateado());
            warninRst.setEstadoRespuesta(EstadoRespuesta.WARNING);
        } else if (COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD.equals(codigoError)) {
            warninRst = respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD);
        } else if (COD_CUENTA_ALFA_NO_VERIFICADA.equals(codigoError)) {
            warninRst = respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.ERROR_CUENTA_ALFA_NO_VERIFICADA);
        } else {
            boolean conReintentar = sessionParametros.getContador().permiteReintentar();
            warninRst = respuestaFactory.crearRespuestaError("",
                    conReintentar ? TipoError.ERROR_REINTENTAR_PAGO_TARJETA : TipoError.ERROR_PAGO_TARJETAS_GENERAL,
                    obtenerCodigoMensajeErrorSegunStopDebit(datosPagoTC),
                    datosPagoTC.getPagoProgramado() ? PROGRAMAR_PAGO : REALIZAR_PAGO,
                    datosPagoTC.getNroTarjetaFormateado());
            if (conReintentar) {
                warninRst.setEstadoRespuesta(EstadoRespuesta.WARNING);
            }
        }
        return warninRst;
    }

	/**
	 * Armar mensaje pago ok sin debito.
	 *
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @return the comprobante pago tarjeta
	 */
	private ComprobantePagoTarjeta armarMensajePagoOkSinDebito(DatosPagoTC datosPagoTC) {
		String importePesos = "";
		String importeDolares = "";
		String tipoPago = "";
		datosPagoTC.setTipoPagoTC(StringUtils.leftPad(datosPagoTC.getTipoPagoTC(), 2, "0"));
		if (TipoPagoTCEnum.MINIMO.getValorServicio().equals(datosPagoTC.getTipoPagoTC())) {
			tipoPago = PAGO_MINIMO;
			importePesos = datosPagoTC.getImporteMinimo();
		} else if (TipoPagoTCEnum.TOTAL.getValorServicio().equals(datosPagoTC.getTipoPagoTC())) {
			tipoPago = PAGO_TOTAL;
			importePesos = datosPagoTC.getPagoProgramado() ? datosPagoTC.getImportePagoTC() : datosPagoTC.getTotalAPagarEnPesos();
			importeDolares = datosPagoTC.getPagoProgramado() ? datosPagoTC.getImporteDolares() : datosPagoTC.getTotalAPagarEnDolares();
//			importePesos = datosPagoTC.getTotalAPagarEnPesos();
//			importeDolares = datosPagoTC.getTotalAPagarEnDolares();
//			if(!TipoMonedaPagoTCEnum.PESOS.getValue().equals(datosPagoTC.getCodigoMoneda())&&
//					!TipoMonedaPagoTCEnum.DOLARES.getValue().equals(datosPagoTC.getCodigoMoneda())) {
//				importePesos = datosPagoTC.getImportePagoTC();
//				importeDolares = datosPagoTC.getImporteDolares();
//			}
		} else { // TipoPagoTCEnum.OTRO.getValorServicio()
			tipoPago = datosPagoTC.getEsSaldoAPagar() ? PAGO_SALDO : PAGO_PARCIAL;
			if (datosPagoTC.getImportePagoPesos() != null) {
				importePesos = datosPagoTC.getImportePagoPesos().replaceAll("\\.", "\\,");
				importePesos = ISBANStringUtils.agregadorDecimales(importePesos);
				importePesos = ISBANStringUtils.agregadorPuntoDivisor(importePesos);
			}

			if (datosPagoTC.getImportePagoDolares() != null) {
				importeDolares = datosPagoTC.getImportePagoDolares().replaceAll("\\.", "\\,");
				importeDolares = ISBANStringUtils.agregadorDecimales(importeDolares);
				importeDolares = ISBANStringUtils.agregadorPuntoDivisor(importeDolares);
			}
		}

		String mensaje = mensajeDao.obtenerMensajePorCodigo(CODIGO_MENSAJE_DEVOLUCION_PAGO).getMensaje();
		String textoImporte = "";
		String textoFinal = datosPagoTC.getPagoProgramado()
				? TEXTO_FINAL_PAGO_PROGRAMADO + datosPagoTC.getFechaPagoProgramado() : TEXTO_FINAL_PAGO_PUNTUAL;

		if (TipoMonedaPagoTCEnum.PESOS.getValue().equals(datosPagoTC.getCodigoMoneda())) {
			textoImporte = "$" + importePesos;
		} else if (TipoMonedaPagoTCEnum.DOLARES.getValue().equals(datosPagoTC.getCodigoMoneda())) {
			textoImporte = "u$s" + importeDolares;
		} else { // TipoMonedaPagoTCEnum.AMBOS.getValue()
			textoImporte = "$" + importePesos + "</b> y por <b>u$s" + importeDolares;
		}
		mensaje = MessageFormat.format(mensaje, tipoPago, datosPagoTC.getNroTarjetaFormateado(), textoImporte,
				textoFinal);

		ComprobantePagoTarjeta comprobante = new ComprobantePagoTarjeta();
		comprobante.setMensaje(mensaje);
		return comprobante;
	}

	/**
	 * Obtiene el mensaje de error segun stop debit.
	 *
	 * @param datosPagoTC
	 *            the datos pago TC
	 * @return the string
	 */
	private String obtenerMensajeErrorSegunStopDebit(DatosPagoTC datosPagoTC) {
        return mensajeDao.obtenerMensajePorCodigo(obtenerCodigoMensajeErrorSegunStopDebit(datosPagoTC)).getMensaje();
	}
	
    /**
     * Obtener codigo mensaje error segun stop debit.
     *
     * @param datosPagoTC
     *            the datos pago TC
     * @return the string
     */
	private String obtenerCodigoMensajeErrorSegunStopDebit(DatosPagoTC datosPagoTC) {
        if (datosPagoTC.getStopDebit()) {
            return CODIGO_MENSAJE_ERROR_PAGO_STOP_DEBIT;
        } else {
            return CODIGO_MENSAJE_ERROR_PAGO;
        }
    }

	/**
	 * Respuesta pago puntual.
	 *
	 * @param respuestaPagoPuntual
	 *            the respuesta pago puntual
	 * @param estado
	 *            the estado
	 * @return the respuesta
	 */
	private Respuesta<ResultadoTransaccion> respuestaConError(Respuesta<ResultadoTransaccion> respuestaPagoPuntual,
			EstadoRespuesta estado) {
		respuestaPagoPuntual
				.setItemMensajeRespuesta(this.getMessage(CodigoMensajeConstantes.PAGO_PUNTUAL_ELIMINADO_ERROR_201));
		respuestaPagoPuntual.setRespuesta(null);
		respuestaPagoPuntual.setEstadoRespuesta(estado);
		respuestaPagoPuntual.setRespuestaVacia(true);
		return respuestaPagoPuntual;
	}

	/**
	 * Gets the message.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the message
	 */
	private List<ItemMensajeRespuesta> getMessage(String codigo) {
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		Mensaje mensaje = mensajeDao.obtenerMensajePorCodigo(codigo);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
		itemMensajeRespuesta.add(item);
		return itemMensajeRespuesta;
	}
}
