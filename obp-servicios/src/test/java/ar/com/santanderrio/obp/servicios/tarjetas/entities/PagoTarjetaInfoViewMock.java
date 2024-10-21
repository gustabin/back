package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaView;

/**
 * The Class PagoTarjetaInfoViewMock.
 *
 * @author florencia.n.martinez
 */
public final class PagoTarjetaInfoViewMock {

    private PagoTarjetaInfoViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtiene el objeto PagoTarjetaInfoView OK.
     *
     * @return the pago tarjeta info view
     */
    public static PagoTarjetaInfoView obtenerPagoTarjetaInfoViewMockOK() {
        PagoTarjetaInfoView pagoTarjetaInfoView = new PagoTarjetaInfoView();
        pagoTarjetaInfoView.setContratoAceptado(Boolean.FALSE);
        pagoTarjetaInfoView.setCuentasDolares(obtenerCuentasDolares());
        pagoTarjetaInfoView.setCuentasPesos(obtenerCuentasPesos());
        pagoTarjetaInfoView.setTarjetas(obtenerTarjetas());
        return pagoTarjetaInfoView;
    }

    /**
     * Obtiene la lista de tarjetas.
     *
     * @return the list
     */
    private static List<PagoTarjetaView> obtenerTarjetas() {
        ArrayList<PagoTarjetaView> tarjetas = new ArrayList<PagoTarjetaView>();
        tarjetas.add(obtenerTarjeta("", "AD", "16,00", "10/11/2016", "03", "0,00", "629,30", "", "", "VISA XXXX- 5153",
                "0,00", "10.068,95", "600,00", "0,00", "0,00", "0,00", "10.068,95", "0,00", "0,00", "0000", "7",
                "629,30", "10.068,95"));
        tarjetas.trimToSize();
        return tarjetas;
    }

    /**
     * Obtiene cada tarjeta.
     *
     * @param alias
     *            the alias
     * @param codigoTitularidad
     *            the codigo titularidad
     * @param cotizacionDolares
     *            the cotizacion dolares
     * @param fechaVencimientoLiqTC
     *            the fecha vencimiento liq TC
     * @param formaPagoTC
     *            the forma pago TC
     * @param importeDolaresConvertidosAPesos
     *            the importe dolares convertidos A pesos
     * @param importePesosConvertidosADolares
     *            the importe pesos convertidos A dolares
     * @param mensajeDebitoAutomatico
     *            the mensaje debito automatico
     * @param mensajeStopDebit
     *            the mensaje stop debit
     * @param numeroTarjeta
     *            the numero tarjeta
     * @param pagoAcumuladoMensualDolares
     *            the pago acumulado mensual dolares
     * @param pagoAcumuladoMensualPesos
     *            the pago acumulado mensual pesos
     * @param pagoMinimoPesosTC
     *            the pago minimo pesos TC
     * @param saldoAPagarConvertidoADolares
     *            the saldo A pagar convertido A dolares
     * @param saldoAPagarConvertidoAPesos
     *            the saldo A pagar convertido A pesos
     * @param saldoDolaresTC
     *            the saldo dolares TC
     * @param saldoPesosTC
     *            the saldo pesos TC
     * @param saldoSinSiguienteCierreDolares
     *            the saldo sin siguiente cierre dolares
     * @param saldoSinSiguienteCierrePesos
     *            the saldo sin siguiente cierre pesos
     * @param sucursalCuentaTarjeta
     *            the sucursal cuenta tarjeta
     * @param tipoTarjeta
     *            the tipo tarjeta
     * @param totalAPagarEnDolares
     *            the total A pagar en dolares
     * @param totalAPagarEnPesos
     *            the total A pagar en pesos
     * @return the pago tarjeta view
     */
    private static PagoTarjetaView obtenerTarjeta(String alias, String codigoTitularidad, String cotizacionDolares,
            String fechaVencimientoLiqTC, String formaPagoTC, String importeDolaresConvertidosAPesos,
            String importePesosConvertidosADolares, String mensajeDebitoAutomatico, String mensajeStopDebit,
            String numeroTarjeta, String pagoAcumuladoMensualDolares, String pagoAcumuladoMensualPesos,
            String pagoMinimoPesosTC, String saldoAPagarConvertidoADolares, String saldoAPagarConvertidoAPesos,
            String saldoDolaresTC, String saldoPesosTC, String saldoSinSiguienteCierreDolares,
            String saldoSinSiguienteCierrePesos, String sucursalCuentaTarjeta, String tipoTarjeta,
            String totalAPagarEnDolares, String totalAPagarEnPesos) {
        PagoTarjetaView view = new PagoTarjetaView();
        view.setAlias(alias);
        view.setCodigoTitularidad(codigoTitularidad);
        view.setCotizacionDolares(cotizacionDolares);
        view.setFechaVencimientoLiquidacionTC(fechaVencimientoLiqTC);
        view.setFormaPagoTarjetaCredito(formaPagoTC);
        view.setImporteDolaresConvertidoAPesos(importeDolaresConvertidosAPesos);
        view.setImportePesosConvertidoADolares(importePesosConvertidosADolares);
        view.setMensajeDebitoAutomatico(mensajeDebitoAutomatico);
        view.setMensajeSwitchOn(mensajeStopDebit);
        view.setMensajeSwitchOff(mensajeStopDebit);
        view.setNumeroTarjeta(numeroTarjeta);
        view.setPagoAcumuladoMensualDolares(pagoAcumuladoMensualDolares);
        view.setPagoAcumuladoMensualPesos(pagoAcumuladoMensualPesos);
        view.setPagoMinimoPesosTC(pagoMinimoPesosTC);
        view.setSaldoAPagarConvertidoADolares(saldoAPagarConvertidoADolares);
        view.setSaldoAPagarConvertidoAPesos(saldoAPagarConvertidoAPesos);
        view.setSaldoDolaresTC(saldoDolaresTC);
        view.setSaldoPesosTC(saldoPesosTC);
        view.setSaldoSinSiguienteCierreDolares(saldoSinSiguienteCierreDolares);
        view.setSaldoSinSiguienteCierrePesos(saldoSinSiguienteCierrePesos);
        view.setSucursalCuentaTarjeta(sucursalCuentaTarjeta);
        view.setTipoTarjeta(tipoTarjeta);
        view.setTotalAPagarEnDolares(totalAPagarEnDolares);
        view.setTotalAPagarEnPesos(totalAPagarEnPesos);
        return view;
    }

    /**
     * Obtiene la lista de cuentas en pesos.
     *
     * @return the list
     */
    private static List<CuentasAdhesionDebitoView> obtenerCuentasPesos() {
        ArrayList<CuentasAdhesionDebitoView> cuentasPesos = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasPesos.add(
                obtenerCuenta("CU", "", "Conquista", "0720066388000010206672", "Cuenta única", "Vitoldo Sofanor Alain",
                        "066-102066/7", "21.767.058", "0,00", "0,00", "0,00", "39.659,11", "111.572.466,46", "", "", "",
                        "", "DNI", "cuentas/imprimir?numeroCuenta=0000000001020667&sucursal=0066"));
        cuentasPesos.add(obtenerCuenta("CU", "", "Conquista", "0720126088000036550168", "Cuenta única",
                "Vitoldo Sofanor Alain", "126-365501/6", "21.767.058", "0,00", "0,00", "0,00", "92.498,99", "22.571,65",
                "", "", "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000003655016&sucursal=0126"));
        cuentasPesos.add(obtenerCuenta("CU", "", "Conquista", "0720000788000040436272", "Cuenta única",
                "Vitoldo Sofanor Alain", "000-404362/7", "21.767.058", "0,00", "0,00", "0,00", "338.333,40",
                "350.000,00", "", "", "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000004043627&sucursal=0000"));
        cuentasPesos.trimToSize();
        return cuentasPesos;
    }

    /**
     * Obtiene la lista de cuentas en dolares.
     *
     * @return the list
     */
    private static List<CuentasAdhesionDebitoView> obtenerCuentasDolares() {
        ArrayList<CuentasAdhesionDebitoView> cuentasDolares = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasDolares.add(
                obtenerCuenta("CU", "", "Conquista", "0720066388000010206672", "Cuenta única", "Vitoldo Sofanor Alain",
                        "066-102066/7", "21.767.058", "0,00", "0,00", "0,00", "39.659,11", "111.572.466,46", "", "", "",
                        "", "DNI", "cuentas/imprimir?numeroCuenta=0000000001020667&sucursal=0066"));
        cuentasDolares.add(obtenerCuenta("CU", "", "Conquista", "0720126088000036550168", "Cuenta única",
                "Vitoldo Sofanor Alain", "126-365501/6", "21.767.058", "0,00", "0,00", "0,00", "92.498,99", "22.571,65",
                "", "", "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000003655016&sucursal=0126"));
        cuentasDolares.add(obtenerCuenta("CU", "", "Conquista", "0720000788000040436272", "Cuenta única",
                "Vitoldo Sofanor Alain", "000-404362/7", "21.767.058", "0,00", "0,00", "0,00", "338.333,40",
                "350.000,00", "", "", "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000004043627&sucursal=0000"));
        cuentasDolares.add(obtenerCuenta("CCP", "", "Conquista", "0720000720000040427236", "Cuenta corriente en $",
                "Vitoldo Sofanor Alain", "000-404272/3", "21.767.058", "0", "0", "0,00", "0,00", "945.389,23", "", "",
                "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000004042723&sucursal=0000"));
        cuentasDolares.add(obtenerCuenta("CAP", "", "Conquista", "0720000730000040427165", "Caja de ahorro en $",
                "Vitoldo Sofanor Alain", "000-404271/6", "21.767.058", "0", "0", "0,00", "0,00", "999.999.999,00", "",
                "", "", "", "DNI", "cuentas/imprimir?numeroCuenta=0000000004042716&sucursal=0000"));
        cuentasDolares.trimToSize();
        return cuentasDolares;
    }

    /**
     * Obtiene cada cuenta.
     *
     * @param abreviaturaTipoCuenta
     *            the abreviatura tipo cuenta
     * @param alias
     *            the alias
     * @param apellido
     *            the apellido
     * @param cbu
     *            the cbu
     * @param descripcionTipoCuenta
     *            the descripcion tipo cuenta
     * @param nombre
     *            the nombre
     * @param numeroCuenta
     *            the numero cuenta
     * @param numeroId
     *            the numero id
     * @param saldoCA
     *            the saldo CA
     * @param saldoCC
     *            the saldo CC
     * @param saldoDescubierto
     *            the saldo descubierto
     * @param saldoDolares
     *            the saldo dolares
     * @param saldoPesos
     *            the saldo pesos
     * @param signoSaldoCA
     *            the signo saldo CA
     * @param signoSaldoCC
     *            the signo saldo CC
     * @param signoSaldoDolares
     *            the signo saldo dolares
     * @param signoSaldoPesos
     *            the signo saldo pesos
     * @param tipoId
     *            the tipo id
     * @param urlReporteCBU
     *            the url reporte CBU
     * @return the cuentas adhesion debito view
     */
    private static CuentasAdhesionDebitoView obtenerCuenta(String abreviaturaTipoCuenta, String alias, String apellido,
            String cbu, String descripcionTipoCuenta, String nombre, String numeroCuenta, String numeroId,
            String saldoCA, String saldoCC, String saldoDescubierto, String saldoDolares, String saldoPesos,
            String signoSaldoCA, String signoSaldoCC, String signoSaldoDolares, String signoSaldoPesos, String tipoId,
            String urlReporteCBU) {
        CuentasAdhesionDebitoView view = new CuentasAdhesionDebitoView();
        view.setAbreviaturaTipoCuenta(abreviaturaTipoCuenta);
        view.setAlias(alias);
        view.setApellidoCliente(apellido);
        view.setCbu(cbu);
        view.setDescripcionTipoCuenta(descripcionTipoCuenta);
        view.setNombreCliente(nombre);
        view.setNumero(numeroCuenta);
        view.setNumeroIdentificacion(numeroId);
        view.setSaldoCajaAhorro(saldoCA);
        view.setSaldoCuentaCorriente(saldoCC);
        view.setSaldoDescubierto(saldoDescubierto);
        view.setSaldoDolares(saldoDolares);
        view.setSaldoPesos(saldoPesos);
        view.setSignoSaldoCajaAhorro(signoSaldoCA);
        view.setSignoSaldoCuentaCorriente(signoSaldoCC);
        view.setSignoSaldoDolares(signoSaldoDolares);
        view.setSignoSaldoPesos(signoSaldoPesos);
        view.setTipoIdentificacion(tipoId);
        view.setUrlReporteCBU(urlReporteCBU);
        return view;
    }
}
