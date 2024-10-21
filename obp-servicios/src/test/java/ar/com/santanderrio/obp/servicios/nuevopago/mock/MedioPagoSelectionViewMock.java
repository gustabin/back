/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.mock;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;

/**
 * The Class MedioPagoSelectionViewMock.
 *
 * @author florencia.n.martinez
 */
public class MedioPagoSelectionViewMock {

    /**
     * Completar info.
     *
     * @return the medio pago selection view
     */
    public static MedioPagoSelectionView completarInfo() {
        MedioPagoSelectionView view = new MedioPagoSelectionView();
        view.setCantidadFacturas(1);
        view.setCantidadCuentas(1);
        view.setListaFacturas(obtenerFactura());
        view.setListaCuentas(obtenerCuentas());
        view.setFechaPagoHoy(Boolean.TRUE);
        view.setCuentaSeleccionada(0);
        view.setFacturaSeleccionada(0);
        view.setFechaPago("04/09/2017");
        view.setTextoSeleccionMultiplesFacturas(
                "Elegí la factura que deseas pagar ahora. Tus otras facturas estarán disponibles para pagar cuando lo desees desde el calendario de pagos.");
        return view;
    }

    /**
     * Obtener cuentas.
     *
     * @return the list
     */
    private static List<CuentaSeleccionView> obtenerCuentas() {
        List<CuentaSeleccionView> cuentas = new ArrayList<CuentaSeleccionView>();
        CuentaSeleccionView cuentaView = new CuentaSeleccionView();
        cuentaView.setAlias("");
        cuentaView.setCbu("1234567890123456789012");
        cuentaView.setDescripcionTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda());
        cuentaView.setIsSaldoDolaresNegativo(Boolean.FALSE);
        cuentaView.setIsSaldoPesosNegativo(Boolean.FALSE);
        cuentaView.setLineaDisponible("123.456,78");
        cuentaView.setLineaTotal("123.456,78");
        cuentaView.setNumero("123-123456/7");
        cuentaView.setSaldoPesos("23.345,45");
        cuentas.add(cuentaView);
        return cuentas;
    }

    /**
     * Obtener factura.
     *
     * @return the list
     */
    private static List<Factura> obtenerFactura() {
        List<Factura> facturas = new ArrayList<Factura>();
        Factura factura = new Factura();
        factura.setEmpresa("Movistar");
        factura.setFechaVencimiento("23/09/2017");
        factura.setMonto("200,00");
        factura.setNumeroFactura("1234456788");
        factura.setNumeroReferenciaPago("1234456788");
        factura.setPrePago(Boolean.TRUE);
        facturas.add(factura);
        return facturas;
    }
}
