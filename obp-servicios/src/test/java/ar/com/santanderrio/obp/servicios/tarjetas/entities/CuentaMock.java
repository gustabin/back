package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentaMock.
 *
 * @author florencia.n.martinez
 */
public final class CuentaMock {

    /**
     * Instantiates a new cuenta mock.
     */
    private CuentaMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /** The sucursal. */
    public static final String SUCURSAL = "0221";

    /** The nrotarjetacredito. */
    public static final String NROTARJETACREDITO = "00003777910057203120";

    /** The nrocuentaproducto. */
    public static final String NROCUENTAPRODUCTO = "0000000025798066";


    public static Cuenta completarInfoCorporateRecargable() {
        Cuenta cuenta = completarInfoCuenta();
        cuenta.setClaseCuenta("N");
        return cuenta;
    }

    public static Cuenta completarInfoOro() {
        Cuenta cuenta = completarInfoCuenta();
        cuenta.setClaseCuenta("2");
        return cuenta;
    }

    /**
     * Completa la información de una cuenta VISA titular.
     *
     * @return the cuenta
     */
    public static Cuenta completarInfoCuenta() { 
        /**
         * sucursal: | numeroCuenta: 2579806/6 | IdentificacionCuenta:
         * 221-579806/6
         */
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("");
        cuenta.setClaseCuenta("L");
        cuenta.setClasePaquete("00");
        cuenta.setCliente(completarInfoCliente());
        cuenta.setCodigoAplicacion("AEXP");
        cuenta.setTipoCuenta("42");
        cuenta.setCodigoTitularidad("TI");
        cuenta.setCodigoPaquete("0000");
        cuenta.setContratoAltair("0000000007703822");
        cuenta.setConvenioPAS(false);
        cuenta.setDepositoEfectivo("00000001054800");
        cuenta.setEstadoTarjetaCredito("20");
        cuenta.setFormaDeOperar("99");
        cuenta.setGrupoAfinidad("000113");
        cuenta.setHabilitadaParaTransferir(false);
        cuenta.setIndex(6);
        cuenta.setIsFavorita(true);
        cuenta.setNroOrdenFirmante("001");
        cuenta.setNroTarjetaCredito(NROTARJETACREDITO);
        cuenta.setNumeroPaquete("000000000000000");
        cuenta.setNroCuentaProducto(NROCUENTAPRODUCTO);
        cuenta.setNroSucursal(SUCURSAL);
        cuenta.setOficinaAltair("0000");
        cuenta.setProductoAltair("42");
        cuenta.setSaldoCuenta("3208.53");
        cuenta.setSaldoParaConformar("00000000210488+");
        cuenta.setSucursalPaquete("0000");
        cuenta.setSubproductoAltair("PLAT");
        cuenta.setTipoCuentaSinUnificar("42");
        cuenta.setTipoCuentaEnum(TipoCuenta.AMEX);
        cuenta.setCbu("487478447838738382");
        return cuenta;
    }

    /**
     * Completa la informacion de una cuenta de tipo amex.
     *
     * @return the cuenta
     */
    public static Cuenta completarInfoCuentaAmex() {
        Cuenta cuenta = completarInfoCuenta();
        cuenta.setNroTarjetaCredito("00003777920020216960");
        return cuenta;
    }

    /**
     * Completa la informacion de una cuenta de tipo visa.
     *
     * @return the cuenta
     */
    public static Cuenta completarInfoCuentaVisa() {
        /**
         * sucursal: | numeroCuenta: 2579806/6 | IdentificacionCuenta:
         * 221-579806/6
         */
        Cuenta cuenta = completarInfoCuenta();
        cuenta.setNroTarjetaCredito("00004050710080531234");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setTipoCuenta("07");
        return cuenta;
    }

    public static Cuenta completarInfoCuentaVisaCorporateRecargable() {
        /**
         * sucursal: | numeroCuenta: 2579806/6 | IdentificacionCuenta:
         * 221-579806/6
         */
        Cuenta cuenta = completarInfoCorporateRecargable();
        cuenta.setNroTarjetaCredito("00004050710080531234");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setTipoCuenta("07");
        return cuenta;
    }

    public static Cuenta completarInfoCuentaVisaOro() {
        /**
         * sucursal: | numeroCuenta: 2579806/6 | IdentificacionCuenta:
         * 221-579806/6
         */
        Cuenta cuenta = completarInfoOro();
        cuenta.setNroTarjetaCredito("00004050710080531234");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setTipoCuenta("07");
        return cuenta;
    }

    /**
     * Completa la informacion de una cuenta de tipo prestamo.
     *
     * @return the cuenta
     */
    public static Cuenta completarInfoCuentaPrestamo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setIndex(0);
        cuenta.setClaseCuenta("4");
        cuenta.setNroSucursal("0254");
        cuenta.setNroCuentaProducto("0000054879654500");
        return cuenta;
    }

    /**
     * Completar info cuenta.
     *
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @param nroSucursal
     *            the nro sucursal
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the cuenta
     */
    public static Cuenta completarInfoCuenta(String nroCuentaProducto, String nroSucursal, TipoCuenta tipoCuenta) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto(nroCuentaProducto);
        cuenta.setNroSucursal(nroSucursal);
        cuenta.setTipoCuentaEnum(tipoCuenta);
        return cuenta;
    }
    
    public static Cuenta completarInfoCuentaUnicaPesos() {
        Cuenta cuenta = completarInfoCuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setTipoCuenta("02");
        cuenta.setSaldoCUPesos("1234.56");
        cuenta.setSaldoCUDls("1234.56");
        return cuenta;
    }

    /**
     * Completa la informacion del cliente.
     *
     * @return the cliente
     */
    private static Cliente completarInfoCliente() {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Pérez");
        cliente.setClientePlatinum(Boolean.TRUE);
        cliente.setNombre("Alan");
        return cliente;
    }
}
