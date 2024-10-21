/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.EstadisticasCompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class CompraVentaDolaresUtil.
 *
 * @author sabrina.cis
 */
@Component("compraOVentaDolaresUtil")
public class CompraVentaDolaresUtil {

	/** The LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompraVentaDolaresUtil.class);

	/** The cuentas service. */
	@Autowired
	private CuentasService cuentasService;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/**
	 * La cuenta en dolares para estar habilitada no tiene que ser Cuenta
	 * Custodia que son las caja de ahorro u$s Especiales. Las cuentas en pesos
	 * estan todas habilitadas
	 *
	 * @param detalleCuentas
	 *            the detalle cuentas
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean tieneCuentasHabilitadas(List<CuentasAdhesionDebitoView> detalleCuentas, Cliente cliente)
			throws ServiceException {
		Boolean cuentaDolar = false;
		Boolean cuentaPesos = false;
		for (CuentasAdhesionDebitoView cuentaView : detalleCuentas) {
			Cuenta cuenta = (Cuenta) cuentaBO.buscarCuentaPorId(cliente,
					new IdentificacionCuenta(cuentaView.getNumero()));
			if (esCuentaUnica(cuenta) && esCuentaDolarHabilitada(cuenta)) {
				cuentaDolar = true;
				cuentaPesos = true;
			} else if (esCuentaDolares(cuenta) && esCuentaDolarHabilitada(cuenta)) {
				cuentaDolar = true;
			} else {
				if (esCuentaPesos(cuenta)) {
					cuentaPesos = true;
				}
			}
		}
		return cuentaDolar && cuentaPesos;

	}

	/**
	 * Es cuenta unica.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCuentaUnica(Cuenta cuenta) {
		if (cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA_EN_PESOS)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA_EN_DOLARES)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * /** Verifica si la cuenta esta habilitada para la compra venta de
	 * dolares. La cuenta en dolares para estar habilitada no tiene que ser
	 * Cuenta Custodia que son las caja de ahorro u$s Especiales. Las cuentas en
	 * pesos estan todas habilitadas
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean estaHabilitadaParaCompraVentaDolar(Cuenta cuenta) throws ServiceException {
		if (esCuentaDolares(cuenta)) {
			if (esCuentaDolarHabilitada(cuenta)) {
				return true;
			}
		} else {
			if (esCuentaPesos(cuenta)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * La cuenta en pesos: DECLTSDO.tipo_cuenta: 00: Cuenta Corriente en Pesos,
	 * 01:Caja de Ahorro en Pesos, 09: Cuenta Unica en pesos
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean esCuentaPesos(Cuenta cuenta) throws ServiceException {
		return cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_CORRIENTE_PESOS)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CAJA_AHORRO_EN_PESOS)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA_EN_PESOS)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA);
	}

	/**
	 * La cuenta en dolares: DECLTSDO.tipo_cuenta: 03: Cuenta Corriente en
	 * Dolares, 04:Caja de Ahorro en Dolares, 10: Cuenta Unica en dolares
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean esCuentaDolares(Cuenta cuenta) throws ServiceException {
		return cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_CORRIENTE_DOLARES)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CAJA_AHORRO_EN_DOLARES)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA_EN_DOLARES)
				|| cuenta.getTipoCuentaEnum().getCodigo().equals(CompraVentaStringUtil.CUENTA_UNICA);
	}

	/**
	 * La cuenta esta inhabilitada cuando IDECLTSDO.producto_altair=03 y
	 * IDECLTSDO.subproducto_altair=0001 La cuenta en dólares para estar
	 * habilitada no tiene que ser Cuenta Custodia que son las caja de ahorro
	 * u$s Especiales.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean esCuentaDolarHabilitada(Cuenta cuenta) throws ServiceException {
		return !esCuentaCustodia(cuenta);
	}

	/**
	 * Cuenta Custodia: DECLTSDO.tipo_cuenta: [04]: Caja de Ahorro en Dolares
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean esCuentaCustodia(Cuenta cuenta) throws ServiceException {
		return esCuentaProductosAltair(cuenta);
	}

	/**
	 * La cuenta en dolares esta habilitada para la compra/venta dolares cuando
	 * no es Cuenta Custodia, que son las caja de ahorro u$s Especiales. Estas
	 * cuenta inhabilitadas se identifican con (IDECLTSDO.producto_atairl=03 &&
	 * IDECLTSDO.subproducto_altair=0001)
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception
	 */
	public boolean esCuentaProductosAltair(Cuenta cuenta) throws ServiceException {
		return cuenta.getProductoAltair().equals(CompraVentaStringUtil.PRODUCTO_ALTAIR)
				&& cuenta.getSubproductoAltair().equals(CompraVentaStringUtil.SUB_PRODUCTO_ALTAIR);
	}

	/**
	 * Busca la cuenta a partir del numero de cuenta y las cuentas del cliente
	 * en sesion.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	public Cuenta buscarCuenta(String numeroCuenta, Cliente cliente) {
		IdentificacionCuenta identificacionCuenta = obtenerIdentificacionCuenta(numeroCuenta);
		Cuenta cuentaEncontrada = new Cuenta(); 
		if (CuentasBancaPrivadaUtil.isCuentaBP(identificacionCuenta.getNroSucursal())){
			cuentaEncontrada = CuentasBancaPrivadaUtil.buscarCuentaPorId(identificacionCuenta, cliente);
		} else {
			cuentaEncontrada = (Cuenta) cuentasService.getCuentaById(identificacionCuenta, cliente);

		}
			
		return cuentaEncontrada;
	}

	/**
	 * Busca la cuenta de origen al inicio de la operacion.
	 *
	 * @param listaCuentasView
	 *            the lista cuentas view
	 * @param cliente
	 *            the cliente
	 * @param isCompra
	 *            the is compra
	 * @return the cuenta
	 */
	public Cuenta obtenerCuentaOrigen(List<CuentasAdhesionDebitoView> listaCuentasView, Cliente cliente,
			boolean isCompra) {
		if (listaCuentasView != null && !listaCuentasView.isEmpty()) {
			for (CuentasAdhesionDebitoView cuenta : listaCuentasView) {
				if (isCompra && cuenta.getShowSaldoPesos() || (!isCompra && cuenta.getShowSaldoDolares())) {
					return buscarCuenta(cuenta.getNumero(), cliente);
				}
			}
		}

		return null;
	}

	/**
	 * Obtener identificacion cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta obtenerIdentificacionCuenta(String cuenta) {
		IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
		String[] numeroCuenta = cuenta.split("-");
		StringBuilder cuentaProducto = new StringBuilder(numeroCuenta[1]);
		cuentaProducto.deleteCharAt(numeroCuenta[1].length() - 2);
		identificacionCuenta.setNroSucursal(numeroCuenta[0]);
		identificacionCuenta.setNroCuentaProducto(cuentaProducto.toString());
		return identificacionCuenta;
	}

	/**
	 * Crear estadistica obtener cuentas error.
	 */
	/*
	 * Crea estadistica con estado Error para obtener las cuentas
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaObtenerCuentasError() {
		crearLogEstadistica(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_OBTENER_CTAS,
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica compra dolares CCOK.
	 */
	/*
	 * Crea estadistica con estado OK para Compra Dolares CC
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCCOK() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCC(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica compra dolares CC error.
	 */
	/*
	 * Crea estadistica con estado ERROR para Compra Dolares CC
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCCError() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCC(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica compra dolares CAOK.
	 */
	/*
	 * Crea estadistica con estado OK para Compra Dolares CA
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCAOK() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCA(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica compra dolares CA error.
	 */
	/*
	 * Crea estadistica con estado ERROR para Compra Dolares CA
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCAError() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCA(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica compra dolares CUOK.
	 */
	/*
	 * Crea estadistica con estado OK para Compra Dolares CU
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCUOK() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCU(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica compra dolares CU error.
	 */
	/*
	 * Crea estadistica con estado ERROR para Compra Dolares CU
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaCompraDolaresCUError() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaCompraDolaresCU(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear estadistica venta dolares CUOK.
	 */
	/*
	 * Crea estadistica con estado OK para Venta Dolares CU
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaVentaDolaresCUOK() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaVentaDolaresCU(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaOk());
	}

	/**
	 * Crear estadistica venta dolares CU error.
	 */
	/*
	 * Crea estadistica con estado ERROR para Venta Dolares CU
	 * 
	 * @author sabrina.cis
	 */
	public void crearEstadisticaVentaDolaresCUError() {
		crearLogEstadistica(EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaVentaDolaresCU(),
				EstadisticasCompraVentaDolaresUtil.getCodigoEstadisticaError());
	}

	/**
	 * Crear log estadistica.
	 *
	 * @param codTransaccion
	 *            the cod transaccion
	 * @param codigoError
	 *            the codigo error
	 */
	/*
	 * Crear log estadistica.
	 *
	 * @param codTransaccion the codigo transaccion
	 * 
	 * @param codigoError the codigo error
	 * 
	 * @author sabrina.cis
	 */
	private void crearLogEstadistica(String codTransaccion, String codigoError) {
		LOGGER.debug("Generando estadistica [" + codTransaccion + ", " + codigoError + "]");
		estadisticaManager.add(codTransaccion, codigoError);
	}

	/**
	 * Formatear saldos y tipo cuenta de cuentas list.
	 *
	 * @param cuentasView
	 *            the cuentas view
	 */
	public void formatearSaldosYTipoCuentaDeCuentasList(List<CuentasAdhesionDebitoView> cuentasView) {
		for (int i = 0; i < cuentasView.size(); i++) {
			CuentasAdhesionDebitoView cuenta = cuentasView.get(i);
			// String saldoPesosFormateado = cuenta.getSignoSaldoPesos() +
			// cuenta.getSaldoPesos()
			String saldoDolaresFormateado = cuenta.getSignoSaldoDolares() + cuenta.getSaldoDolares();
			cuenta.setSaldoPesos(cuenta.getSaldoPesos());
			cuenta.setSaldoDolares(saldoDolaresFormateado);
			reemplazarDescripcionTipoCuentaConMoneda(cuenta);
			isSaldoPesosNegativo(cuenta);
			isSaldoDolaresNegativo(cuenta);
		}
	}

	/**
	 * Checks if is saldo dolares negativo.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void isSaldoDolaresNegativo(CuentasAdhesionDebitoView cuenta) {
		if (isSaldoNegativo(cuenta.getSignoSaldoDolares())) {
			cuenta.setIsSaldoDolaresNegativo(Boolean.TRUE);
		} else {
			cuenta.setIsSaldoDolaresNegativo(Boolean.FALSE);
		}
	}

	/**
	 * Checks if is saldo pesos negativo.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void isSaldoPesosNegativo(CuentasAdhesionDebitoView cuenta) {
		if (isSaldoNegativo(cuenta.getSignoSaldoPesos())) {
			cuenta.setIsSaldoPesosNegativo(Boolean.TRUE);
		} else {
			cuenta.setIsSaldoPesosNegativo(Boolean.FALSE);
		}
	}

	/**
	 * Checks if is saldo negativo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the boolean
	 */
	private Boolean isSaldoNegativo(String saldo) {
		if (saldo != null && saldo.contains("-")) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Quitar simbolo moneda A importe.
	 *
	 * @param importe
	 *            the importe
	 * @return the string
	 */
	public String quitarSimboloMonedaAImporte(String importe) {
		importe = StringUtils.removeStartIgnoreCase(importe, "$");
		importe = StringUtils.removeStartIgnoreCase(importe, "u$s");
		return importe;
	}

	/**
	 * Reemplazar descripcion de tipo cuenta con descripcion + moneda.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void reemplazarDescripcionTipoCuentaConMoneda(CuentasAdhesionDebitoView cuenta) {
		verificarYCambiarTipoCuenta(cuenta);
	}

	/**
	 * Verificar y cambiar tipo cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void verificarYCambiarTipoCuenta(CuentasAdhesionDebitoView cuenta) {
		if (esCuentaCorrientePesos(cuenta) || esCajaAhorroPesos(cuenta)) {
			reemplazarStringPesosPorSigno(cuenta);
		}
		if (esCuentaCorrienteDolares(cuenta) || esCajaAhorroDolares(cuenta)) {
			reemplazarStringDolaresPorSigno(cuenta);
		}
	}

	/**
	 * Reemplazar string dolares por signo.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void reemplazarStringDolaresPorSigno(CuentasAdhesionDebitoView cuenta) {
		cuenta.setDescripcionTipoCuenta(StringUtils.replace(cuenta.getDescripcionTipoCuenta(),
				CompraVentaStringUtil.DOLARES, CompraVentaStringUtil.DOLARES_MONEDA));
	}

	/**
	 * Reemplazar string pesos por signo.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void reemplazarStringPesosPorSigno(CuentasAdhesionDebitoView cuenta) {
		cuenta.setDescripcionTipoCuenta(StringUtils.replace(cuenta.getDescripcionTipoCuenta(),
				CompraVentaStringUtil.PESOS, CompraVentaStringUtil.PESOS_MONEDA));
	}

	/**
	 * Es cuenta corriente dolares.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCuentaCorrienteDolares(CuentasAdhesionDebitoView cuenta) {
		return cuenta.getDescripcionTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getDescripcion());
	}

	/**
	 * Es caja ahorro dolares.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCajaAhorroDolares(CuentasAdhesionDebitoView cuenta) {
		return cuenta.getDescripcionTipoCuenta().equals(TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcion());
	}

	/**
	 * Es caja ahorro pesos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCajaAhorroPesos(CuentasAdhesionDebitoView cuenta) {
		return cuenta.getDescripcionTipoCuenta().equals(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcion());
	}

	/**
	 * Es cuenta corriente pesos.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the boolean
	 */
	private Boolean esCuentaCorrientePesos(CuentasAdhesionDebitoView cuenta) {
		return cuenta.getDescripcionTipoCuenta().equals(TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcion());
	}

}
