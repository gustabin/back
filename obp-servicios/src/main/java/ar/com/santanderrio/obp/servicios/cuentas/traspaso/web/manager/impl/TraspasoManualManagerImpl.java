/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
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
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.TraspasoManualBO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ComprobanteTraspasoManualDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.IndicadorDebitoCreditoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoManualDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoManualManager;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.CuentasTraspasoView;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view.TraspasoManualView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class TraspasoManualManagerImpl.
 */
@Component("traspasoManual")
public class TraspasoManualManagerImpl implements TraspasoManualManager {

	/** The Constant CUENTA_CORRIENTE. */
	private static final String CUENTA_CORRIENTE = "Cuenta corriente en $";

	/** The Constant CAJA_DE_AHORRO. */
	private static final String CAJA_DE_AHORRO = "Caja de ahorro en $";

	/** The Constant MONEDA. */
	private static final String MONEDA = "ARS";

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	EstadisticaManager estadisticaManager;

	/** The cuenta manager. */
	@Autowired
	CuentaManager cuentaManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The traspaso manual BO. */
	@Autowired
	private TraspasoManualBO traspasoManualBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoManualManager#realizarTraspasoManual(ar.com.santanderrio.obp.
	 * servicios.cuentas.traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> realizarTraspasoManual(TraspasoManualView traspaso) {

		if (!sesionParametros.getValidacionHash()
				.equals(HashUtils.obtenerHash(crearMapaDeLaVista(traspaso.getNumeroCuenta())))) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

		TraspasoManualDTO traspasoManualDTO = new TraspasoManualDTO();
		traspasoManualDTO.setImporte(new BigDecimal(traspaso.getImporte()));

		if (CUENTA_CORRIENTE.equals(traspaso.getCuentaOrigen())) {
			traspasoManualDTO.setIndicadorCC(IndicadorDebitoCreditoEnum.INDICADOR_DEBITO);
			traspasoManualDTO.setIndicadorCA(IndicadorDebitoCreditoEnum.INDICADOR_CREDITO);
		} else if (CAJA_DE_AHORRO.equals(traspaso.getCuentaOrigen())) {
			traspasoManualDTO.setIndicadorCC(IndicadorDebitoCreditoEnum.INDICADOR_CREDITO);
			traspasoManualDTO.setIndicadorCA(IndicadorDebitoCreditoEnum.INDICADOR_DEBITO);
		} else {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}

		Cuenta cuenta = obtenerCuenta(sesionCliente.getCliente(), traspaso.getNumeroCuenta());
		if (cuenta == null) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		traspasoManualDTO.setNumeroCuenta(cuenta.getContratoAltair());
		traspasoManualDTO.setSucursalCuenta(cuenta.getOficinaAltair());

		Respuesta<ComprobanteTraspasoManualDTO> respuestaBO = traspasoManualBO
				.realizarTraspasoManual(sesionCliente.getCliente(), traspasoManualDTO);
		if (EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {

			estadisticaManager.add(EstadisticasConstants.TRASPASO_MANUAL_FEEDBACK,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR, traspaso.getNumeroCuenta(), traspaso.getImporte(),
					MONEDA);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.TRASPASO_SALDO_INSUFICIENTE,
					StringUtils.EMPTY);

		}
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TRASPASO_MANUAL_FEEDBACK,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (TipoError.TRASPASO_SALDO_INSUFICIENTE.getDescripcion()
					.equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return Respuesta.copy(TraspasoManualView.class, respuestaBO);
			}
			if (sesionParametros.getContador().permiteReintentar()) {
				return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_TRASPASO_MANUAL_INTENTOS,
						CodigoMensajeConstantes.TRASPASO_MANUAL_FEEDBACK_ERROR, traspaso.getImporte());
			} else {
				return Respuesta.copy(TraspasoManualView.class, respuestaBO);
			}
		}
		estadisticaManager.add(EstadisticasConstants.TRASPASO_MANUAL_FEEDBACK,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK, traspaso.getNumeroCuenta(), traspaso.getImporte(),
				MONEDA);

		TraspasoManualView traspasoManualView = armarComprobanteView(respuestaBO.getRespuesta(), traspaso);

		return respuestaFactory.crearRespuestaOk(traspasoManualView);
	}

	/**
	 * Armar comprobante view.
	 *
	 * @param comprobante
	 *            the comprobante
	 * @param traspaso
	 *            the traspaso
	 * @return the traspaso manual view
	 */
	private TraspasoManualView armarComprobanteView(ComprobanteTraspasoManualDTO comprobante,
			TraspasoManualView traspaso) {
		TraspasoManualView traspasoManualView = new TraspasoManualView();

		Mensaje mensajeOK = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRASPASO_MANUAL_FEEDBACK_OK);
		BigDecimal importe = new BigDecimal(traspaso.getImporte());
		String importeFormateado = ISBANStringUtils.formatearSaldo(importe);
		traspasoManualView.setMensaje(MessageFormat.format(mensajeOK.getMensaje(), importeFormateado));
		traspasoManualView.setFecha(comprobante.getFecha());
		traspasoManualView.setHora(comprobante.getHora());
		traspasoManualView.setNroComprobante(comprobante.getComprobante());

		return traspasoManualView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoManualManager#configurarTraspasoManual(ar.com.santanderrio.obp.
	 * servicios.cuentas.traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> configurarTraspasoManual(TraspasoManualView traspaso) {

		TraspasoManualView traspasoManualView = new TraspasoManualView();
		Cuenta cuenta = obtenerCuenta(sesionCliente.getCliente(), traspaso.getNumeroCuenta());
		if (cuenta == null) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		CuentasTraspasoView cajaAhorro = new CuentasTraspasoView();
		cajaAhorro.setAlias(cuenta.getAlias());
		cajaAhorro.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
		cajaAhorro.setDescripcionTipoCuenta(CAJA_DE_AHORRO);
		cajaAhorro.setSaldoPesos(ISBANStringUtils.formatearSaldo(cuenta.getSaldoAperCajaAhorroPesos().abs()));
		cajaAhorro.setIsSaldoPesosNegativo(cuenta.getSaldoAperCajaAhorroPesos().signum() < 0);

		CuentasTraspasoView cuentaCorriente = new CuentasTraspasoView();
		cuentaCorriente.setAlias(cuenta.getAlias());
		cuentaCorriente.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
		cuentaCorriente.setDescripcionTipoCuenta(CUENTA_CORRIENTE);
		cuentaCorriente.setSaldoPesos(ISBANStringUtils.formatearSaldo(cuenta.getSaldoAperCuentaCorrientePesos().abs()));
		cuentaCorriente.setIsSaldoPesosNegativo(cuenta.getSaldoAperCuentaCorrientePesos().signum() < 0);

		List<CuentasTraspasoView> cuentas = new ArrayList<CuentasTraspasoView>();
		cuentas.add(cajaAhorro);
		cuentas.add(cuentaCorriente);
		traspasoManualView.setCuentas(cuentas);

		estadisticaManager.add(EstadisticasConstants.TRASPASO_MANUAL_SOLICITUD_TRASPASO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		sesionParametros.setContador(new ContadorIntentos(2));
		sesionParametros.setValidacionHash(HashUtils.obtenerHash(crearMapaDeLaVista(cuentaCorriente.getNumero())));

		return respuestaFactory.crearRespuestaOk(traspasoManualView);
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuenta
	 */
	private Cuenta obtenerCuenta(Cliente cliente, String numeroCuenta) {

		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta(numeroCuenta);

		for (Cuenta cuenta : cliente.getCuentas()) {
			if (StringUtils.stripStart(identificacionCuenta.getNroCuentaProducto(), "0")
					.equals(StringUtils.stripStart(cuenta.getNroCuentaProducto(), "0"))) {
				return cuenta;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoManualManager#confirmarTraspaspManual(ar.com.santanderrio.obp.
	 * servicios.cuentas.traspaso.web.view.TraspasoManualView)
	 */
	@Override
	public Respuesta<TraspasoManualView> confirmarTraspasoManual(TraspasoManualView traspaso) {

		// chequear hash

		return respuestaFactory.crearRespuestaOk(new TraspasoManualView());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.
	 * TraspasoManualManager#obtenerComprobante()
	 */
	@Override
	public void obtenerComprobante() {

		estadisticaManager.add(EstadisticasConstants.TRASPASO_MANUAL_COMPROBANTE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Cargar hash de la vista en sesion.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVista(String nroCuenta) {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("nroCuenta", nroCuenta);
		return mapaAtributos;
	}

}
