package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ClienteMock.
 *
 * @author florencia.n.martinez
 */
public final class ClienteMock {

    /**
     * Instantiates a new cliente mock.
     */
    private ClienteMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Info cliente.
     *
     * @return the cliente
     */
    public static Cliente infoCliente() {
        Cliente cliente = new Cliente();
        cliente.setApellido1("Godoy");
        cliente.setApellido2("");
        cliente.setNombre("Silvia");
        cliente.setUsuarioRacf("");
        cliente.setPasswordRacf("");
        cliente.setTipoRacf("");
        cliente.setNup("02534805");
        cliente.setValorSinonimo("");
        cliente.setDni("00004075137");
        cliente.setFechaNacimiento("19311020");
        cliente.setTipoDocumento("N");
        return cliente;
    }

    /**
     * Obtener cliente con cuentas.
     *
     * @param cuentas
     *            the cuentas
     * @return the cliente
     */
    public static Cliente obtenerClienteConCuentas(List<Cuenta> cuentas) {
        Cliente cliente = infoCliente();
        cliente.setCuentas(cuentas);
        return cliente;
    }

    /**
     * Completa la informacion del cliente.
     *
     * @return the cliente
     */
    public static Cliente completarInfoCliente() {
        Cliente cliente = infoCliente();
        cliente.setCuentas(completarInfoCuentas());
        return cliente;
    }

    /**
     * Completar info cliente visa Y amex.
     *
     * @return the cliente
     */
    public static Cliente completarInfoClienteVisaYAmex() {
        Cliente cliente = infoCliente();
        cliente.setCuentas(completarInfoCuentasVisaYAmex());
        return cliente;
    }

    public static Cliente completarInfoClienteAmex() {
        Cliente cliente = infoCliente();
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaAmex());
        cuentas.trimToSize();
        cliente.setCuentas(cuentas);
        return cliente;
    }

    /**
     * Completar info cliente prestamo.
     *
     * @return the cliente
     */
    public static Cliente completarInfoClientePrestamo() {
        Cliente cliente = infoCliente();
        cliente.setCuentas(completarInfoCuentaPrestamo());
        return cliente;
    }

    /**
     * Completar info cuentas visa Y amex.
     *
     * @return the list
     */
    private static List<Cuenta> completarInfoCuentasVisaYAmex() {
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaAmex());
        cuentas.add(CuentaMock.completarInfoCuentaVisa());
        cuentas.trimToSize();
        return cuentas;
    }

    /**
     * Completa la informacion de la lista de cuentas.
     *
     * @return the list
     */
    private static List<Cuenta> completarInfoCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuenta());
        cuentas.trimToSize();
        return cuentas;
    }

    /**
     * Completar info cuenta prestamo.
     *
     * @return the list
     */
    private static List<Cuenta> completarInfoCuentaPrestamo() {
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaPrestamo());
        cuentas.trimToSize();
        return cuentas;
    }

    /**
     * Completar info cliente visa.
     *
     * @return the cliente
     */
    public static Cliente completarInfoClienteVisa() {
        Cliente cliente = infoCliente();
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaVisa());
        cuentas.add(CuentaMock.completarInfoCuentaVisa());
        cuentas.trimToSize();
        cliente.setCuentas(cuentas);
        return cliente;
    }

    public static Cliente completarInfoClienteVisaCorporateRecargable() {
        Cliente cliente = infoCliente();
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaVisaCorporateRecargable());
        cuentas.trimToSize();
        cliente.setCuentas(cuentas);
        return cliente;
    }

    public static Cliente completarInfoClienteVisaOro() {
        Cliente cliente = infoCliente();
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(CuentaMock.completarInfoCuentaVisaOro());
        cuentas.trimToSize();
        cliente.setCuentas(cuentas);
        return cliente;
    }

    public static Cliente clienteConCuentas() {
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
    
        cuenta1.setTipoCuenta("02");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta1.setNroCuentaProducto("0000000001600148");
        cuenta1.setNroSucursal("0099");
        cuenta1.setNroTarjetaCredito("00000000000000000000");
        cuenta1.setSaldoCuenta("");
        cuenta1.setSaldoCUPesos("8457707.60");
        cuenta1.setSaldoCUDls("9490505.58");
    
        cuenta2.setTipoCuenta("05");
        cuenta2.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta2.setNroCuentaProducto("0000000000000000");
        cuenta2.setNroSucursal("0099");
        cuenta2.setNroTarjetaCredito("00004517660046063029");
        cuenta2.setSaldoCuenta("0.00");
        cuenta2.setSaldoCUPesos("");
        cuenta2.setSaldoCUDls("");
    
        cuenta3.setTipoCuenta("07");
        cuenta3.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta3.setNroCuentaProducto("0000000016216611");
        cuenta3.setNroSucursal("0099");
        cuenta3.setNroTarjetaCredito("00004509950008806378");
        cuenta3.setSaldoCuenta("0.00");
        cuenta3.setSaldoCUPesos("");
        cuenta3.setSaldoCUDls("");
    
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cuentas.add(cuenta3);
        cliente.setCuentas(cuentas);
    
        return cliente;
    }
}
