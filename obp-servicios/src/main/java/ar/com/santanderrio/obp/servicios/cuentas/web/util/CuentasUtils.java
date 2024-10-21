/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.prestamos.view.CuentaView;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class CuentasUtils.
 */
public final class CuentasUtils {
    
	private static final String CUENTA = "Cuenta";
	
	private static final String ESPACIO = " ";
	
    /**
	 * Instantiates a new cuentas utils.
	 */
    private CuentasUtils() {

    }

    /**
     * Ordena las cuentas por 1)favotiva, 2)Titular ppal., 3)Titular, 4) Cotitular
     * Ppal, 5 ) Cotitular
     *
     * @param cuentas
     *            the cuentas
     */
    public static void ordenarCuentas(List<ResumenDetalleCuenta> cuentas) {
        Collections.sort(cuentas, new Comparator<ResumenDetalleCuenta>() {
            @Override
            public int compare(ResumenDetalleCuenta object1, ResumenDetalleCuenta object2) {

                CompraVentaDolaresCompareCaseA casoA = new CompraVentaDolaresCompareCaseA(object1, object2);
                CompraVentaDolaresCompareCaseB casoB = new CompraVentaDolaresCompareCaseB(object1, object2);
                CompraVentaDolaresCompareCaseC casoC = new CompraVentaDolaresCompareCaseC(object1, object2);

                if (casoA.getValorMenosUno()) {
                    return -1;
                } else if (casoB.getValorUno()) {
                    return 1;
                } else if (casoC.getValorCero()) {
                    return 0;
                }

                return 0;
            }
        });

    }

    /**
     * Obtener cuenta principal cliente.
     *
     * @param cliente
     *            the cliente
     * @return the cuenta
     */
    public static Cuenta obtenerCuentaPrincipalCliente(Cliente cliente) {
        List<Cuenta> cuentas = cliente.getCuentas();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isCuentaPrincipal()) {
                return cuenta;
            }
        }
        return new Cuenta();
    }
    
    /**
     * Ordenar cuentas.
     *
     * @param listaCuentas
     *            the lista cuentas
     * @return the list
     */
    public static List<Cuenta> ordenarCuentasMonetarias(List<Cuenta> listaCuentas) {
        List<Cuenta> nuevaListaCuentas = new ArrayList<Cuenta>();
        List<Cuenta> cotitulares = new ArrayList<Cuenta>();
        boolean hayFavorita = false;
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getIsFavorita()) {
                nuevaListaCuentas.add(0, cuenta);
                hayFavorita = true;
            } else if (cuenta.esCuentaPrincipal()) {
                nuevaListaCuentas.add(hayFavorita ? 1 : 0, cuenta);
            } else if (TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuenta.getCodigoTitularidad())) {
                nuevaListaCuentas.add(cuenta);
            } else {
                cotitulares.add(cuenta);
            }
        }
        nuevaListaCuentas.addAll(cotitulares);
        return nuevaListaCuentas;
    }
    
    /**
	 * Obtener cuentas view pesos.
	 *
	 * @param cuentasPesos the cuentas pesos
	 * @return the list
	 */
	public static List<CuentaView> obtenerCuentasViewPesos(List<Cuenta> cuentasPesos) {
		List<CuentaView> cuentasViewPesos = new ArrayList<CuentaView>();
		for (int i = 0; i < cuentasPesos.size(); i++) {
			int tipoCuenta = Integer.valueOf(cuentasPesos.get(i).getTipoCuenta());
			CuentaView cuentaView = new CuentaView();
			cuentaView.setId(i);
			cuentaView.setAlias(cuentasPesos.get(i).getAlias());
			cuentaView.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(tipoCuenta).getDescripcionConMoneda());
			cuentaView.setNumero(CUENTA.concat(ESPACIO).concat(ISBANStringUtils
					.formatearSucursal(cuentasPesos.get(i).getNroSucursal()).concat(ISBANStringUtils.GUION_STRING)
					.concat(ISBANStringUtils.formatearNumeroCuenta(cuentasPesos.get(i).getNroCuentaProducto()))));
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasPesos.get(i).getSaldoCUPesos())));
				cuentaView.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
			} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_PESOS.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(ISBANStringUtils
						.formatearConComaYDosDecimales(String.valueOf(cuentasPesos.get(i).getSaldoCuenta())));
			}
			cuentasViewPesos.add(cuentaView);
		}
		return cuentasViewPesos;
	}
	
	/**
	 * Obtiene desde la vista, la cuenta en pesos del cliente
	 * @param cuentaView
	 * @param cliente
	 * @return
	 */
	public static Cuenta obtenerCuentaDesdeVista(CuentaView cuentaView, Cliente cliente) {
		
		String[] cuentaPartes = cuentaView.getNumero().split("\\-");
		return cliente.getCuentaSiContieneNumero(cuentaPartes[1].replaceAll("/", ""));
			
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
}
