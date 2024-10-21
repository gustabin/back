package ar.com.santanderrio.obp.servicios.debinws.utils;

import java.text.MessageFormat;
import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.debin.CuentaDebinDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;
import ar.com.santanderrio.obp.servicios.debinws.common.DebinWSConstants;
import ar.com.santanderrio.obp.servicios.debinws.dto.CuentasAdheridasOutDTO;

/**
 * DebinWSUtils.
 */
public final class DebinWSUtils {
    
	/** The Constant ERROR_CODIGO_36. */
	public static final int ERROR_CODIGO_36 = 10000036;

	/** The Constant ERROR_CODIGO_57. */
	public static final int ERROR_CODIGO_57 = 10000057;

	/** The Constant ERROR_USUARIOTARJETA_INEXISTENTE_50. */
	public static final int ERROR_USUARIOTARJETA_INEXISTENTE_50 = 10000050;
	
	/** The Constant ERROR_USUARIOTARJETA_INEXISTENTE_56. */
	public static final int ERROR_USUARIOTARJETA_INEXISTENTE_56 = 10000056;

	/** The Constant ERROR_FLUJO_MANUAL_56. */
	public static final int ERROR_FLUJO_MANUAL_56 = 10000056;

	/** The Constant ERROR_FLUJO_MANUAL_66. */
	public static final int ERROR_FLUJO_MANUAL_66 = 10000066;

	/** The Constant ERROR_FLUJO_MANUAL_70. */
	public static final int ERROR_FLUJO_MANUAL_70 = 10000070;

	/** The Constant ERROR_FLUJO_MANUAL_74. */
	public static final int ERROR_FLUJO_MANUAL_74 = 10000074;

	/** The Constant ERROR_FLUJO_MANUAL_76. */
	public static final int ERROR_FLUJO_MANUAL_76 = 10000076;

	/** The Constant ERROR_SIN_REITENTO_505. */
	public static final int ERROR_SIN_REITENTO_505 = 10000505;

	/** The Constant ERROR_FLUJO_MANUAL_65. */
	public static final int ERROR_FLUJO_MANUAL_65 = 10000065;

	/** The Constant DESTINATARIO_NO_VERIFICADO_1. */
	public static final int DESTINATARIO_NO_VERIFICADO_1 = 1;

	/** The Constant DESTINATARIO_NO_VERIFICADO_2. */
	public static final int DESTINATARIO_NO_VERIFICADO_2 = 2;

	/** The Constant CODIGO_MONEDA_PESOS. */
	public static final String CODIGO_MONEDA_PESOS = "032";

	/** The Constant CODIGO_MONEDA_DOLARES. */
	public static final String CODIGO_MONEDA_DOLARES = "840";

	/** The Constant DESC_PESOS. */
	public static final String DESC_PESOS = "Pesos";

	/** The Constant DESC_DOLARES. */
	public static final String DESC_DOLARES = "Dolares";

	/** The Constant CODIGO_MONEDA_UNICA. */
	private static final String CODIGO_MONEDA_UNICA = "000";

    /**
     * Obtener primer cuenta adherida con moneda destino.
     *
     * @param cuentasAdheridasOutDTO the cuentas adheridas out DTO
     * @param monedaDestino the moneda destino
     * @return the cuenta debin DTO
     */
    public static CuentaDebinDTO obtenerPrimerCuentaAdheridaConMonedaDestino(CuentasAdheridasOutDTO cuentasAdheridasOutDTO, String monedaDestino) {
        List<CuentaDebinDTO> cuentasAdheridas = cuentasAdheridasOutDTO.getCuentasAdheridas();
        for (CuentaDebinDTO cuentaDTO : cuentasAdheridas) {
            if (CODIGO_MONEDA_UNICA.equals(cuentaDTO.getMoneda())) {
                return cuentaDTO;
            }
            if (cuentaDTO.getMoneda().equals(monedaDestino)) {
                return cuentaDTO;
            }
        }
        return null;
    }

	/**
	 * Obtener cbu cuenta continuadora debin.
	 *
	 * @param cuentas the cuentas
	 * @param cuentasCerradas the cuentas cerradas
	 * @param cbu the cbu
	 * @return the string
	 */
	public static String obtenerCbuCuentaContinuadoraDebin(List<Cuenta> cuentas, List<CuentaCerrada> cuentasCerradas, String cbu) {
		for (Cuenta cuenta : cuentas) {
			if (cbu.equals(cuenta.getCbu())) {
				return cuenta.getCbu();
			}
		}
		if (cuentasCerradas != null) {
			for (CuentaCerrada cuenta : cuentasCerradas) {
				if (cbu.equals(cuenta.getCbu())) {
					return encontrarCuentaPorSucursalYnumeroProducto(cuentas, cuenta.getNroCuentaProductoContinuador(), cuenta.getNroSucursalContinuadora()).getCbu();
				}
			}
		}
		return null;
	}
   
	/**
	 * Encontrar cuenta por sucursal y numero producto.
	 *
	 * @param cuentas the cuentas
	 * @param numeroCuentaProducto the numero cuenta producto
	 * @param sucursal the sucursal
	 * @return the cuenta
	 */
	public static Cuenta encontrarCuentaPorSucursalYnumeroProducto(List<Cuenta> cuentas, String numeroCuentaProducto,
			String sucursal) {
		int numeroCuentaProductoNumero = Integer.parseInt(numeroCuentaProducto);
		int sucursalNumero = Integer.parseInt(sucursal);
		if (cuentas != null) {
			for (Cuenta cuenta : cuentas) {
				if (numeroCuentaProductoNumero == Integer.parseInt(cuenta.getNroCuentaProducto())
						&& sucursalNumero == Integer.parseInt(cuenta.getNroSucursal())) {
					return cuenta;
				}
			}
		}
		return null;
	}

	/**
	 * Obtener mensaje consulta.
	 *
	 * @param mensaje the mensaje
	 * @param tipoConsulta the tipo consulta
	 * @return the string
	 */
	public static String obtenerMensajeConsulta(String mensaje, String tipoConsulta) {
		return MessageFormat.format(mensaje, DebinWSConstants.TIPO_CONSULTA_PLAZOFIJO.equals(tipoConsulta) ?
				DebinWSConstants.TIPO_PLAZOFIJO_NOMBRE : DebinWSConstants.TIPO_DEBIN_NOMBRE);
	}
}
