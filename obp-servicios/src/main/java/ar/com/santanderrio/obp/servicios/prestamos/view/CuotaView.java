/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.PrestamoCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;

/**
 * The Class CuotaView.
 */
public class CuotaView extends ReportComprobanteView {

    /** The cuota. */
    private String cuota;

    /** The vencimiento. */
    private String vencimiento;

    /** The deuda. */
    private String deuda;

    /** The saldo. */
    private String saldo;

    /** The capital. */
    private String capital;

    /** The intereses. */
    private String intereses;

    /** The seguro. */
    private String seguro;

    /** The total. */
    private String total;

    /** The legales. */
    private String legales;

    /** The tipo prestamo. */
    private String tipoPrestamo;

    /** The detalle cuota. */
    private DetalleCuotaPrestamoView detalleCuota;

    /** The impuestos. */
    private String impuestos;

    /** The is impuestos. */
    private String isImpuestos;

    /** The is seguro. */
    private String isSeguro;

    /** The Constant CEROS. */
    private static final String CEROS = "00000000000000000";

    /** The Constant CEROCONFORMATO. */
    private static final String CEROCONFORMATO = "$ 0,00";

    /** The prestamos jasper. */
    protected final String prestamosJasper = "detalle-prestamo.jasper";

    /** The titulo prestamo. */
    protected final String tituloPrestamo = "Detalle de cuota";

    /** The importe acreditar key. */
    protected final String importeAcreditarKey = "IMPORTE_ACREDITAR";

    /** The importe cuota key. */
    protected final String importeCuotaKey = "IMPORTE_CUOTA";

    /** The tipo prestamo key. */
    protected final String tipoPrestamoKey = "TIPO_PRESTAMO";

    /** The numero prestamo key. */
    protected final String numeroPrestamoKey = "NUMERO_PRESTAMO";

    /** The titular key. */
    protected final String titularKey = "TITULAR";

    /** The cuil key. */
    protected final String cuilKey = "CUIL";

    /** The cuenta debito key. */
    protected final String cuentaDebitoKey = "CUENTA_DEBITO";

    /** The nro cuota key. */
    protected final String nroCuotaKey = "NRO_CUOTA";

    /** The capital key. */
    protected final String capitalKey = "CAPITAL";

    /** The intereses compensatorios key. */
    protected final String interesesCompensatoriosKey = "INTERESES_COMPENSATORIOS";

    /** The seguro key. */
    protected final String seguroKey = "SEGURO";

    /** The titulo seguro key. */
    protected final String tituloSeguroKey = "TITULO_SEGURO";

    /** The otros impuestos key. */
    protected final String otrosImpuestosKey = "OTROS_IMPUESTOS";

    /** The titulo otros impuestos key. */
    protected final String tituloOtrosImpuestosKey = "TITULO_OTROS_IMPUESTOS";

    /** The gastos administrativos key. */
    protected final String gastosAdministrativosKey = "GASTOS_ADMINISTRATIVOS";

    /** The tasa efectiva anual key. */
    protected final String tasaEfectivaAnualKey = "TASA_EFECTIVA_ANUAL";

    /** The tasa nominal anual key. */
    protected final String tasaNominalAnualKey = "TASA_NOMINAL_ANUAL";

    /** The fecha vencimiento key. */
    protected final String fechaVencimientoKey = "FECHA_VENCIMIENTO";

    /** The costo efectivo key. */
    protected final String costoEfectivoKey = "COSTO_EFECTIVO_CON_IMPUESTOS";

    /** The costo efectivo sin impuestos key. */
    protected final String costoEfectivoSinImpuestosKey = "COSTO_EFECTIVO_SIN_IMPUESTOS";

    /** The legal 1 key. */
    protected final String legal1Key = "LEGAL1";

    /** The legal 2 key. */
    protected final String legal2Key = "LEGAL2";

    /** The legal 3 key. */
    protected final String legal3Key = "LEGAL3";

    /**
     * Instantiates a new cuota view.
     */
    public CuotaView() {
        super();
    }

    /**
     * Instantiates a new proxima cuota view.
     *
     * @param cuotaPrestamoEntity
     *            the cuota prestamo entity
     * @param tipoPrestamo
     *            the tipo prestamo
     */
    public CuotaView(CuotaPrestamoEntity cuotaPrestamoEntity, String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
        this.cuota = Integer.toString(Integer.parseInt(cuotaPrestamoEntity.getNumrecRec()));
        this.vencimiento = ISBANStringUtils
                .formatearFechaSinHora(cuotaPrestamoEntity.getFeliqRec().replaceAll("-", ""));
        this.deuda = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getSaldoPrev(), false);
        this.capital = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getCapRec(), true);
        this.intereses = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getIntOrdRec(), true);
        this.seguro = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getSegRec(), true);
        this.impuestos = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImptRec(), true);
        this.total = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImpTotRec(), true);
    }

    /**
     * Instantiates a new proxima cuota view.
     *
     * @param cuotaPrestamoEntity
     *            the cuota prestamo entity
     * @param tipoPrestamo
     *            the tipo prestamo
     */
    public CuotaView(PrestamoCuotaPagaOutEntity cuotaPrestamoEntity, String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
        this.cuota = Integer.toString(Integer.parseInt(cuotaPrestamoEntity.getNumrec()));
        this.vencimiento = ISBANStringUtils.formatearFechaSinHora(cuotaPrestamoEntity.getFeliq().replaceAll("-", ""));
        this.deuda = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getSaldoant(), false);
        this.capital = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getCapinire(), false);
        this.intereses = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getIntinire(), false);
        this.seguro = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getSeginire(), false);
        this.impuestos = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImpinire(), false);
        this.total = formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImporteCargo(), false);
    }

    /**
     * Armar detalle cuota.
     *
     * @param cuotaPrestamoEntity
     *            the cuota prestamo entity
     * @param prestamo
     *            the prestamo
     * @param consultaPrestamo
     *            the consulta prestamo
     * @param proximasCuotasView
     *            the proximas cuotas view
     * @param cuenta
     *            the cuenta
     */
    public void armarDetalleCuota(CuotaPrestamoEntity cuotaPrestamoEntity, Prestamo prestamo,
            ConsultaPrestamo consultaPrestamo, ProximasCuotasView proximasCuotasView, CuentaView cuenta) {

        this.detalleCuota = new DetalleCuotaPrestamoView();
        this.detalleCuota.setTipoPrestamo("Súper " + proximasCuotasView.getTitulo());
        this.detalleCuota.setNumeroPrestamo(proximasCuotasView.getNumero());
        this.detalleCuota.setNumeroCuota(this.getCuota());
        this.detalleCuota.setCuenta(cuenta);
        this.detalleCuota.setTasas(new TasaValorView(cuotaPrestamoEntity));

        if (prestamo != null && ISBANStringUtils.eliminarCeros(prestamo.getNumeroRecibo()).equals(this.getCuota())) {

            if (StringUtils.isNotBlank(prestamo.getIndex())) {
                String valorCoeficienteDecimal = prestamo.getFactorIndex().substring(3, 5);
                String valorCoeficienteEntero = ISBANStringUtils
                        .eliminarCeros(prestamo.getFactorIndex().substring(0, 3));
                this.detalleCuota.setCoeficiente(
                        prestamo.getIndex() + " " + valorCoeficienteEntero + "," + valorCoeficienteDecimal + "%");
            }
            if ("-".equals(consultaPrestamo.getMontoPrestamo())) {
                this.detalleCuota.setImporteCuota(consultaPrestamo.getMontoPrestamo());
            }

            this.detalleCuota.setCapital(this.getCapital());
            this.detalleCuota.setInteresesCompensatoriosPeriodo(this.getIntereses());
            this.detalleCuota.setCargoSeguroVidaSaldoDeudor(this.getSeguro());
            this.detalleCuota.setOtrosImpuestos(formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImptRec(), true));
            this.detalleCuota.setSaldoAnterior(this.getDeuda());
            this.detalleCuota.setFechaVencimiento(this.vencimiento);
        }

    }

    /**
     * Armar detalle cuota paga.
     *
     * @param cuotaPrestamoEntity
     *            the cuota prestamo entity
     * @param proximasCuotasView
     *            the proximas cuotas view
     * @param interviniente
     *            the interviniente
     * @param cuentaDebitoView
     *            the cuenta debito view
     */
    public void armarDetalleCuotaPaga(PrestamoCuotaPagaOutEntity cuotaPrestamoEntity,
            ProximasCuotasView proximasCuotasView, Interviniente interviniente, CuentaView cuentaDebitoView) {

        this.detalleCuota = new DetalleCuotaPrestamoView();
        this.detalleCuota.setTipoPrestamo("Súper " + proximasCuotasView.getTitulo());
        this.detalleCuota.setNumeroPrestamo(proximasCuotasView.getNumero());
        this.detalleCuota.setCuenta(cuentaDebitoView);
        this.detalleCuota.setTasas(new TasaValorView(cuotaPrestamoEntity));

        if (!CEROS.equals(cuotaPrestamoEntity.getImpinire())) {
            this.detalleCuota.setOtrosImpuestos(formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getImpinire(), false));
        }
        BigDecimal gastosMovimiento = CEROS.equals(cuotaPrestamoEntity.getGasinire()) ? BigDecimal.ZERO
                : ISBANStringUtils.stringToBigDecimal(cuotaPrestamoEntity.getGasinire(), 13, 4, false);
        BigDecimal comisionMovimiento = CEROS.equals(cuotaPrestamoEntity.getCominire()) ? BigDecimal.ZERO
                : ISBANStringUtils.stringToBigDecimal(cuotaPrestamoEntity.getCominire(), 13, 4, false);
        if (!BigDecimal.ZERO.equals(gastosMovimiento.add(comisionMovimiento))) {
            this.detalleCuota.setGastosAdministrativos(
                    "$ " + ISBANStringUtils.formatearSaldo(gastosMovimiento.add(comisionMovimiento)));
        }

        if (!CEROS.equals(cuotaPrestamoEntity.getSaldopost())) {
            this.detalleCuota.setSaldoPosterior(formatearSaldoDeTramaCuota(cuotaPrestamoEntity.getSaldopost(), false));
        }

        this.detalleCuota.setNumeroCuota(this.getCuota());
        this.detalleCuota.setFechaVencimiento(this.vencimiento);
        this.detalleCuota.setImporteCuota(this.getSaldo());
        this.detalleCuota.setCapital(this.getCapital());
        if (!CEROCONFORMATO.equals(this.getIntereses())) {
            this.detalleCuota.setInteresesCompensatoriosPeriodo(this.getIntereses());
        }
        if (!CEROCONFORMATO.equals(this.getSeguro())) {
            this.detalleCuota.setCargoSeguroVidaSaldoDeudor(this.getSeguro());
        }

        if (cuotaPrestamoEntity != null) {
            if (StringUtils.isNotBlank(cuotaPrestamoEntity.getFactorIndex())) {
                String valorCoeficienteDecimal = cuotaPrestamoEntity.getFactorIndex().substring(3, 5);
                String valorCoeficienteEntero = ISBANStringUtils
                        .eliminarCeros(cuotaPrestamoEntity.getFactorIndex().substring(0, 3));
                this.detalleCuota.setCoeficiente(cuotaPrestamoEntity.getFactorIndex() + " " + valorCoeficienteEntero
                        + "," + valorCoeficienteDecimal + "%");
            }
        }

        // Datos de servicio CNSINTERVI
        if (interviniente.tieneDatos() && "PRENDARIO".equals(this.getTipoPrestamo())) {
            this.detalleCuota.setTitular(ISBANStringUtils.formateoStringPrimeraLetraMayuscula(
                    interviniente.getNombre() + " " + interviniente.getApellido()));
            String cuil = interviniente.getCuitcuil().substring(0, 2) + "-"
                    + interviniente.getCuitcuil().substring(2, 10) + "-"
                    + interviniente.getCuitcuil().substring(10, interviniente.getCuitcuil().length());
            this.detalleCuota.setCuil(cuil);
        }
    }

    /**
     * Obtener cuenta debito.
     *
     * @param cuentaDebito
     *            the cuenta debito
     * @param numeroSucursal
     *            the numero sucursal
     * @param numeroCuenta
     *            the numero cuenta
     * @return the cuenta view
     */
    public static CuentaView obtenerCuentaDebito(Cuenta cuentaDebito, String numeroSucursal, String numeroCuenta) {
        CuentaView cuentaView = new CuentaView();
        if (cuentaDebito != null) {
            cuentaView.setDescripcionTipoCuenta(cuentaDebito.getTipoCuentaEnum().getDescripcionConMoneda());
            if (StringUtils.isNotBlank(cuentaDebito.getAlias())) {
                cuentaView.setAlias(cuentaDebito.getAlias());
            }
            cuentaView.setNumeroFormateado(ISBANStringUtils.formatearSucursal(cuentaDebito.getNroSucursal()) + "-"
                    + ISBANStringUtils.agregarBarraNumeroPrestamo(
                            ISBANStringUtils.formateadorConCerosIzq(cuentaDebito.getNroCuentaProducto(), 7)));
        } else {
            cuentaView.setNumeroFormateado(ISBANStringUtils.formatearSucursal(numeroSucursal) + "-" + ISBANStringUtils
                    .agregarBarraNumeroPrestamo(ISBANStringUtils.formateadorConCerosIzq(numeroCuenta, 7)));
        }
        return cuentaView;
    }

    /**
     * Formatear saldo de trama cuota.
     *
     * @param tramaSaldo
     *            the trama saldo
     * @param tieneSigno
     *            the tiene signo
     * @return the string
     */
    private String formatearSaldoDeTramaCuota(String tramaSaldo, Boolean tieneSigno) {
        return "$ "
                + ISBANStringUtils.formatearSaldo(ISBANStringUtils.stringToBigDecimal(tramaSaldo, 13, 4, tieneSigno));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * DetalleComprobanteTransferenciaView#obtenerJasper()
     */
    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(path + prestamosJasper).getPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * ReportComprobanteView#obtenerParametrosPDF()
     */
    @Override
    public HashMap<String, Object> obtenerParametrosPDF() {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        tituloComprobante = tituloPrestamo;
        parametros.put(tituloComprobanteKey, tituloPrestamo);
        parametros.put(importeAcreditarKey, total);
        parametros.put(importeCuotaKey, total);
        parametros.put(tipoPrestamoKey, detalleCuota.getTipoPrestamo());

        if ("PRENDARIO".equals(tipoPrestamo)) {
            if (detalleCuota.getCuil() != null) {
                parametros.put(cuilKey, detalleCuota.getCuil());
            } else {
                parametros.put(cuilKey, "-");
            }
            if (detalleCuota.getTitular() != null) {
                parametros.put(titularKey, detalleCuota.getTitular());
            } else {
                parametros.put(titularKey, "-");
            }

        }
        parametros.put(numeroPrestamoKey, detalleCuota.getNumeroPrestamo());
        String cuentaDebito = StringUtils.EMPTY;
        if (detalleCuota.getCuenta() != null) {
            if (detalleCuota.getCuenta().getAlias() != null) {
                cuentaDebito = "<b>Cuenta \"" + detalleCuota.getCuenta().getAlias() + "\"</b>" + "<br/>";
            }
            if (StringUtils.isNotBlank(cuentaDebito)) {
                cuentaDebito = cuentaDebito + "<b>" + detalleCuota.getCuenta().getDescripcionTipoCuenta() + "</b><br/>";
            } else {
                cuentaDebito = cuentaDebito + detalleCuota.getCuenta().getDescripcionTipoCuenta() + "<br/>";
            }
            cuentaDebito = cuentaDebito + detalleCuota.getCuenta().getNumeroFormateado();
        }
        parametros.put(cuentaDebitoKey, cuentaDebito);
        parametros.put(nroCuotaKey, cuota);
        parametros.put(capitalKey, detalleCuota.getCapital());
        parametros.put(interesesCompensatoriosKey, detalleCuota.getInteresesCompensatoriosPeriodo());
        parametros.put(otrosImpuestosKey, detalleCuota.getOtrosImpuestos());
        parametros.put(gastosAdministrativosKey, detalleCuota.getGastosAdministrativos());
        parametros.put(tasaEfectivaAnualKey, detalleCuota.getTasas().getTasaEfectivaAnual());
        parametros.put(tasaNominalAnualKey, detalleCuota.getTasas().getTasaNominalAnual());
        parametros.put(fechaVencimientoKey, detalleCuota.getFechaVencimiento());
        parametros.put(costoEfectivoKey,
                "CFTEA: " + obtenerCampoOVacio(detalleCuota.getTasas().getCostoFinancieroTotalAnual()));
        parametros.put(costoEfectivoSinImpuestosKey,
                "CFTEA: " + obtenerCampoOVacio(detalleCuota.getTasas().getCostoFinancieroTotalAnualSinImpuestos()));
        parametros.put(legal1Key,
                "(1) De existir diferencia entre la sumatoria de los rubros que se detalla y el \"Importe de la cuota\", la misma corresponde a los intereses punitorios.");
        int legalActual = 2;
        if (!"$ 0,00".equals(seguro)) {
            parametros.put(seguroKey, seguro);
            parametros.put(legal2Key, "(" + legalActual + ") " + legales);
            parametros.put(tituloSeguroKey, "Seguro (" + legalActual + ")");
            legalActual++;
        }
        if (!"$ 0,00".equals(impuestos)) {
            parametros.put(legal3Key, "(" + legalActual + ") IVA y Sellados");
            parametros.put(tituloOtrosImpuestosKey, "Otros impuestos (" + legalActual + ")");
        }
        parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        return parametros;
    }

    /**
     * Obtener campo O vacio.
     *
     * @param valor
     *            the valor
     * @return the string
     */
    private String obtenerCampoOVacio(String valor) {
        if (valor == null) {
            return "";
        }
        return valor;
    }

    /**
     * Instantiates a new proxima cuota view.
     *
     * @return the legales
     */

    public String getLegales() {
        return legales;
    }

    /**
     * Sets the legales.
     *
     * @param legales
     *            the new legales
     */
    public void setLegales(String legales) {
        this.legales = legales;
    }

    /**
     * Gets the checks if is impuestos.
     *
     * @return the checks if is impuestos
     */
    public String getIsImpuestos() {
        return isImpuestos;
    }

    /**
     * Sets the checks if is impuestos.
     *
     * @param isImpuestos
     *            the new checks if is impuestos
     */
    public void setIsImpuestos(String isImpuestos) {
        this.isImpuestos = isImpuestos;
    }

    /**
     * Gets the checks if is seguro.
     *
     * @return the checks if is seguro
     */
    public String getIsSeguro() {
        return isSeguro;
    }

    /**
     * Sets the checks if is seguro.
     *
     * @param isSeguro
     *            the new checks if is seguro
     */
    public void setIsSeguro(String isSeguro) {
        this.isSeguro = isSeguro;
    }

    /**
     * Sets the impuestos.
     *
     * @param impuestos
     *            the new impuestos
     */
    public void setImpuestos(String impuestos) {
        this.impuestos = impuestos;
    }

    /**
     * Gets the impuestos.
     *
     * @return the impuestos
     */
    public String getImpuestos() {
        return impuestos;
    }

    /**
     * Gets the cuota.
     *
     * @return the cuota
     */
    public String getCuota() {
        return cuota;
    }

    /**
     * Sets the cuota.
     *
     * @param cuota
     *            the new cuota
     */
    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    /**
     * Gets the vencimiento.
     *
     * @return the vencimiento
     */
    public String getVencimiento() {
        return vencimiento;
    }

    /**
     * Sets the vencimiento.
     *
     * @param vencimiento
     *            the new vencimiento
     */
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    /**
     * Gets the deuda.
     *
     * @return the deuda
     */
    public String getDeuda() {
        return deuda;
    }

    /**
     * Sets the deuda.
     *
     * @param deuda
     *            the new deuda
     */
    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }

    /**
     * Gets the saldo.
     *
     * @return the saldo
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * Sets the saldo.
     *
     * @param saldo
     *            the new saldo
     */
    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    /**
     * Gets the capital.
     *
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Sets the capital.
     *
     * @param capital
     *            the new capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * Gets the intereses.
     *
     * @return the intereses
     */
    public String getIntereses() {
        return intereses;
    }

    /**
     * Sets the intereses.
     *
     * @param intereses
     *            the new intereses
     */
    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    /**
     * Gets the seguro.
     *
     * @return the seguro
     */
    public String getSeguro() {
        return seguro;
    }

    /**
     * Sets the seguro.
     *
     * @param seguro
     *            the new seguro
     */
    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total
     *            the new total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * Gets the detalle cuota.
     *
     * @return the detalle cuota
     */
    public DetalleCuotaPrestamoView getDetalleCuota() {
        return detalleCuota;
    }

    /**
     * Sets the detalle cuota.
     *
     * @param detalleCuota
     *            the new detalle cuota
     */
    public void setDetalleCuota(DetalleCuotaPrestamoView detalleCuota) {
        this.detalleCuota = detalleCuota;
    }

    /**
     * Gets the tipo prestamo.
     *
     * @return the tipo prestamo
     */
    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    /**
     * Sets the tipo prestamo.
     *
     * @param tipoPrestamo
     *            the new tipo prestamo
     */
    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

}
