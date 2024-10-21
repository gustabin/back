package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;

/**
 * The Class PrestamoMock.
 *
 * @author florencia.n.martinez
 */
public final class PrestamoMock {

    private PrestamoMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completa la informacion del objeto Prestamo con el numero de recibo.
     *
     * @return the prestamo
     */
    public static Prestamo completarInfoPrestamoConNumRecibo() {
        Prestamo prestamo = new Prestamo();
        prestamo.setNroRecibo("12345555");
        prestamo.setNumeroRecibo("12345555");
        return prestamo;
    }

    /**
     * Completa la informacion del objeto Prestamo.
     *
     * @param claseCuenta
     *            the clase cuenta
     * @param cuenta
     *            the cuenta
     * @param divisa
     *            the divisa
     * @param importeAmortizacion
     *            the importe amortizacion
     * @param importeIntereses
     *            the importe intereses
     * @param importeIVA
     *            the importe IVA
     * @param importeSeguroVida
     *            the importe seguro vida
     * @param importeTotalCuota
     *            the importe total cuota
     * @param importeTotalSeguros
     *            the importe total seguros
     * @param impuestosPendientes
     *            the impuestos pendientes
     * @param numeroCuentaProducto
     *            the numero cuenta producto
     * @param numeroRecibo
     *            the numero recibo
     * @param saldoPrevio
     *            the saldo previo
     * @param tasaAnualEfectiva
     *            the tasa anual efectiva
     * @param tasaPrestamo
     *            the tasa prestamo
     * @param tipoPrestamoEnum
     *            the tipo prestamo enum
     * @return the prestamo
     */
    public static Prestamo completarInfoPrestamo(String claseCuenta, Cuenta cuenta, DivisaEnum divisa,
            BigDecimal importeAmortizacion, BigDecimal importeIntereses, BigDecimal importeIVA,
            BigDecimal importeSeguroVida, BigDecimal importeTotalCuota, BigDecimal importeTotalSeguros,
            String impuestosPendientes, String numeroCuentaProducto, String numeroRecibo, BigDecimal saldoPrevio,
            BigDecimal tasaAnualEfectiva, BigDecimal tasaPrestamo, TipoPrestamoEnum tipoPrestamoEnum) {
        Prestamo prestamo = new Prestamo();
        prestamo.setAjusteCuotaCap(BigDecimal.ZERO);
        prestamo.setAjusteCuotaMora(BigDecimal.ZERO);
        prestamo.setAjusteSaldo(BigDecimal.ZERO);
        prestamo.setCapitalPend(BigDecimal.ZERO);
        prestamo.setClaseCuenta(claseCuenta);
        prestamo.setCobranzaExt(BigDecimal.ZERO);
        prestamo.setComisionesPend(BigDecimal.ZERO);
        prestamo.setComisionesPendientes(BigDecimal.ZERO);
        prestamo.setCostoFinancieroTotal(BigDecimal.ZERO);
        prestamo.setCostoFinancieroTotalSinImpuestos(BigDecimal.ZERO);
        prestamo.setCotizCambio(BigDecimal.ZERO);
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(divisa);
        prestamo.setGastosPend(BigDecimal.ZERO);
        prestamo.setGastosPendientes(BigDecimal.ZERO);
        prestamo.setImporteAjuscap(BigDecimal.ZERO);
        prestamo.setImporteAjussal(BigDecimal.ZERO);
        prestamo.setImporteAjusteCapmor(BigDecimal.ZERO);
        prestamo.setImporteAmortizacion(importeAmortizacion);
        prestamo.setImporteEndeudamiento(BigDecimal.ZERO);
        prestamo.setImporteIntereses(importeIntereses);
        prestamo.setImporteIVA(importeIVA);
        prestamo.setImporteIVAAdicional(BigDecimal.ZERO);
        prestamo.setImporteSeguroDelBien(BigDecimal.ZERO);
        prestamo.setImporteSeguroVida(importeSeguroVida);
        prestamo.setImportesPunitorios(BigDecimal.ZERO);
        prestamo.setImporteTotalCuota(importeTotalCuota);
        prestamo.setImporteTotalSeguros(importeTotalSeguros);
        prestamo.setImpuestosPend(BigDecimal.ZERO);
        prestamo.setImpuestosPendientes(impuestosPendientes);
        prestamo.setIngresosBrutos(BigDecimal.ZERO);
        prestamo.setIntCompensPend(BigDecimal.ZERO);
        prestamo.setInteresCompensatorioPendiente(BigDecimal.ZERO);
        prestamo.setInteresesPend(BigDecimal.ZERO);
        prestamo.setIntMoraPend(BigDecimal.ZERO);
        prestamo.setNumeroCuentaProducto(numeroCuentaProducto);
        prestamo.setNumeroRecibo(numeroRecibo);
        prestamo.setOtrosImpuestos(BigDecimal.ZERO);
        prestamo.setSaldoPrevio(saldoPrevio);
        prestamo.setSegurosPend(BigDecimal.ZERO);
        prestamo.setTasaAnualEfectiva(tasaAnualEfectiva);
        prestamo.setTasaPrestamo(tasaPrestamo);
        prestamo.setTipoPrestamoEnum(tipoPrestamoEnum);
        prestamo.setOtrosImpuestos(BigDecimal.ZERO);
        return prestamo;
    }
}
