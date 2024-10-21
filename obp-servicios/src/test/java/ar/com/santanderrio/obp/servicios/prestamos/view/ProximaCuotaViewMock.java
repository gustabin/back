package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;

/**
 * @author florencia.n.martinez
 *
 */
public final class ProximaCuotaViewMock {

    private ProximaCuotaViewMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    public static List<ProximaCuotaView> completarInfoListaProximaCuotaView() {
        List<ProximaCuotaView> cuotas = new ArrayList<ProximaCuotaView>();
         cuotas.add(ProximaCuotaViewMock.completarInfoProximaCuotaView("12", ProximaCuotaViewMock.obtenerDetalleCuotaPrestamoView("123456789875/0", ProximaCuotaViewMock.obtenerCuentaView("Cuenta única", "023-5698754/8"),
                 "12", "24", "CER 10,5%", "$ 12.500,00", "37", "$ 150,00", "$ 1.250,00", ProximaCuotaViewMock.obtenerTasas("12,25%", "12,45%", "14,45%", "52,45%"), "22/03/2016"), "$ 0,00", "$ 1.780,00", "$ 200,00", "$ 1.400,00", "$ 1.550,00", "Súper Préstamo Personal", "10/12/2016"));
         
         cuotas.add(ProximaCuotaViewMock.completarInfoProximaCuotaView("11", ProximaCuotaViewMock.obtenerDetalleCuotaPrestamoView("123456789875/0", ProximaCuotaViewMock.obtenerCuentaView("Cuenta única", "023-5698754/8"),
                 "11", "24", "CER 10,5%", "$ 12.500,00", "37", "$ 150,00", "$ 1.250,00", ProximaCuotaViewMock.obtenerTasas("12,25%", "12,45%", "14,45%", "52,45%"), "22/02/2016"), "$ 0,00", "$ 1.780,00", "$ 200,00", "$ 1.400,00", "$ 1.550,00", "Súper Préstamo Personal", "10/12/2016"));
         cuotas.add(ProximaCuotaViewMock.completarInfoProximaCuotaView("10", ProximaCuotaViewMock.obtenerDetalleCuotaPrestamoView("123456789875/0", ProximaCuotaViewMock.obtenerCuentaView("Cuenta única", "023-5698754/8"),
                 "10", "24", "CER 10,5%", "$ 12.500,00", "37", "$ 150,00", "$ 1.250,00", ProximaCuotaViewMock.obtenerTasas("12,25%", "12,45%", "14,45%", "52,45%"), "22/01/2016"), "$ 0,00", "$ 1.780,00", "$ 200,00", "$ 1.400,00", "$ 1.550,00", "Súper Préstamo Personal", "10/12/2016"));
         cuotas.add(ProximaCuotaViewMock.completarInfoProximaCuotaView("9", ProximaCuotaViewMock.obtenerDetalleCuotaPrestamoView("123456789875/0", ProximaCuotaViewMock.obtenerCuentaView("Cuenta única", "023-5698754/8"),
                 "9", "24", "CER 10,5%", "$ 12.500,00", "37", "$ 150,00", "$ 1.250,00", ProximaCuotaViewMock.obtenerTasas("12,25%", "12,45%", "14,45%", "52,45%"), "22/12/2015"), "$ 0,00", "$ 1.780,00", "$ 200,00", "$ 1.400,00", "$ 1.550,00", "Súper Préstamo Personal", "10/12/2016"));
         cuotas.add(ProximaCuotaViewMock.completarInfoProximaCuotaView("8", ProximaCuotaViewMock.obtenerDetalleCuotaPrestamoView("123456789875/0", ProximaCuotaViewMock.obtenerCuentaView("Cuenta única", "023-5698754/8"),
                 "8", "24", "CER 10,5%", "$ 12.500,00", "37", "$ 150,00", "$ 1.250,00", ProximaCuotaViewMock.obtenerTasas("12,25%", "12,45%", "14,45%", "52,45%"), "22/11/2015"), "$ 0,00", "$ 1.780,00", "$ 200,00", "$ 1.400,00", "$ 1.550,00", "Súper Préstamo Personal", "10/12/2016"));
        return cuotas;
    }
    
    
    
     public static ProximaCuotaView completarInfoProximaCuotaView(String cuota, DetalleCuotaPrestamoView detalleCuota, String saldoAnterior,
            String capital,
            String intereses, String seguro, String total, String tipoPrestamo, String vencimiento) {
        ProximaCuotaView proximaCuotaView = new ProximaCuotaView();
        proximaCuotaView.setCuota(cuota);
        proximaCuotaView.setDetalleCuota(detalleCuota);
        proximaCuotaView.setSaldo(saldoAnterior);
        proximaCuotaView.setCapital(capital);
        proximaCuotaView.setIntereses(intereses);
        proximaCuotaView.setSeguro(seguro);
        proximaCuotaView.setTotal(total);
        proximaCuotaView.setTipoPrestamo(tipoPrestamo);
        proximaCuotaView.setVencimiento(vencimiento);
        return proximaCuotaView;
    }
     
     public static DetalleCuotaPrestamoView obtenerDetalleCuotaPrestamoView(String numeroPrestamo, CuentaView cuenta, String numeroCuota,
                String plazoPrestamo, String coeficiente, String importeCuota, String capital, String interesesCompensatoriosPeriodo,
                String cargoSeguroVida, TasaValorView tasas, String fechaVencimiento) {
            DetalleCuotaPrestamoView detalleCuota = new DetalleCuotaPrestamoView();
            detalleCuota.setNumeroPrestamo(numeroPrestamo);
            detalleCuota.setCuenta(cuenta);
            detalleCuota.setNumeroCuota(numeroCuota);
            detalleCuota.setPlazoPrestamo(plazoPrestamo);
            detalleCuota.setCoeficiente(coeficiente);
            detalleCuota.setImporteCuota(importeCuota);
            detalleCuota.setCapital(capital);
            detalleCuota.setInteresesCompensatoriosPeriodo(interesesCompensatoriosPeriodo);
            detalleCuota.setCargoSeguroVida(cargoSeguroVida);
            detalleCuota.setTasas(tasas);
            detalleCuota.setFechaVencimiento(fechaVencimiento);
            return detalleCuota;
        }
     
     public static TasaValorView obtenerTasas(String tasaNominalAnual, String tasaEfectivaAnual, String costoFinancieroTotalAnualSinImpuestos, String costoFinancieroTotalAnual) {
            TasaValorView tasas = new TasaValorView();
            tasas.setTasaNominalAnual(tasaNominalAnual);
            tasas.setTasaEfectivaAnual(tasaEfectivaAnual);
            tasas.setCostoFinancieroTotalAnualSinImpuestos(costoFinancieroTotalAnualSinImpuestos);
            tasas.setCostoFinancieroTotalAnual(costoFinancieroTotalAnual);
            return tasas;
        }
     
     public static CuentaView obtenerCuentaView(String descripcionTipoCuenta, String numeroFormateado) {
            CuentaView cuentaView = new CuentaView();
            cuentaView.setDescripcionTipoCuenta(descripcionTipoCuenta);
            cuentaView.setNumeroFormateado(numeroFormateado);
            return cuentaView;
        }
}
