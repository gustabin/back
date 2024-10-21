package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.Date;

/**
 * The Class DetalleTarjetaDTOMock.
 *
 * @author sabrina.cis
 */
public final class DetalleTarjetaDTOMock {

    /** The sucursal. */
    public static String SUCURSAL = "0221";

    /** The numerocuenta. */
    public static String NUMEROCUENTA = "2579806/6";

    /** The nrotarjetacredito. */
    public static String NROTARJETACREDITO = "00003777910057203120";

    /** The nrocuentaproducto. */
    public static String NROCUENTAPRODUCTO = "0000000025798066";

    private DetalleTarjetaDTOMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener detalle.
     *
     * @return the detalle tarjeta DTO
     */
    public static DetalleTarjetaDTO obtenerDetalle() {
        /**
         * sucursal: 221 | numeroCuenta: 2579806/6 | IdentificacionCuenta:
         * 221-579806/6
         */
        DetalleTarjetaDTO detalle = new DetalleTarjetaDTO();
        detalle.setAlias("Alias");
        detalle.setConsumoDolares("16,80");
        detalle.setConsumoPesos("16,80");
        detalle.setEsConsumoDolarCero(false);
        detalle.setEsConsumoPesosCero(false);
        detalle.setEsSaldoEnComprasCero(false);
        detalle.setEsSaldoEnCuotasCero(false);
        detalle.setFechaCierre("16/10/2016");
        detalle.setFechaDesde(new Date());
        detalle.setFechaVencimiento("16/10/2016");
        detalle.setHasLimiteUnificado(false);
        detalle.setIsAdicional(false);
        detalle.setIsFavorita(true);
        detalle.setIsRecargable(false);
        detalle.setIsTitular(true);
        detalle.setMarca("VISA");
        detalle.setMostrarSaldoEnCompras(true);
        detalle.setMostrarSaldoEnCuotas(true);
        detalle.setNroSucursal(SUCURSAL);
        detalle.setNroTarjeta(NROTARJETACREDITO);
        detalle.setNroCuentaProducto(NROCUENTAPRODUCTO);
        detalle.setTipoCuenta(null);
        return detalle;
    }
}
