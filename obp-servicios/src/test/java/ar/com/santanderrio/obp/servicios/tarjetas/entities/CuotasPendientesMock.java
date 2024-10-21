package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesLineaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;

/**
 * The Class CuotasPendientesMock.
 *
 * @author florencia.n.martinez
 */
public final class CuotasPendientesMock {

    private CuotasPendientesMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completa la información de cuotas pendientes.
     *
     * @return the cuotas pendientes view
     */
    public static CuotasPendientesView completarInfoCuotasPendientes() {
        CuotasPendientesView cuotasPendientesView = new CuotasPendientesView();
        cuotasPendientesView.setTotalCuotasPendientes(new BigDecimal(1500));
        cuotasPendientesView.setTarjetasCuotasPendientes(completarInfoListaCuotaspendientesTarjetaView());
        return cuotasPendientesView;
    }

    /**
     * Completa la información de la lista de cuotas pendientes tarjeta view.
     *
     * @return the list
     */
    private static List<CuotasPendientesTarjetaView> completarInfoListaCuotaspendientesTarjetaView() {
        List<CuotasPendientesTarjetaView> cuotasPendientesTarjetaViewList = new ArrayList<CuotasPendientesTarjetaView>();
        cuotasPendientesTarjetaViewList.add(completarInfoCuotasPendientesTarjetaView());
        return cuotasPendientesTarjetaViewList;
    }

    /**
     * Completa la información de cuotas pendientes tarjeta view.
     *
     * @return the cuotas pendientes tarjeta view
     */
    private static CuotasPendientesTarjetaView completarInfoCuotasPendientesTarjetaView() {
        CuotasPendientesTarjetaView cuotasPendientesTarjetaView = new CuotasPendientesTarjetaView();
        cuotasPendientesTarjetaView.setEsTitular(Boolean.TRUE);
        cuotasPendientesTarjetaView.setLineasCuotasPendientes(completarInfoListaCuotasPendientesLineaView());
        cuotasPendientesTarjetaView.setMarca("VISA");
        cuotasPendientesTarjetaView.setMostrarNombreAdicional(Boolean.FALSE);
        cuotasPendientesTarjetaView.setNumero("XXXX-8569");
        cuotasPendientesTarjetaView.setTotal(new BigDecimal("1.500,00"));
        return cuotasPendientesTarjetaView;
    }

    /**
     * Completa la información de una lista de lineas de cuotas pendientes view.
     *
     * @return the list
     */
    private static List<CuotasPendientesLineaView> completarInfoListaCuotasPendientesLineaView() {
        List<CuotasPendientesLineaView> lineasCuotas = new ArrayList<CuotasPendientesLineaView>();
        lineasCuotas.add(completarInfoCuotasPendientesLineaView(6, 3, "Paurulo", "28/06/2016", "0123456789", "900,00"));
        lineasCuotas.add(
                completarInfoCuotasPendientesLineaView(6, 4, "El Rápido S.A.", "31/08/2016", "9876543210", "600,00"));
        return lineasCuotas;
    }

    /**
     * Completa la información de una linea de cuotas pendientes.
     *
     * @param cantidadCuotas
     *            the cantidad cuotas
     * @param cuotasPendientes
     *            the cuotas pendientes
     * @param establecimiento
     *            the establecimiento
     * @param fecha
     *            the fecha
     * @param operacion
     *            the operacion
     * @param restante
     *            the restante
     * @return the cuotas pendientes linea view
     */
    private static CuotasPendientesLineaView completarInfoCuotasPendientesLineaView(Integer cantidadCuotas,
            Integer cuotasPendientes, String establecimiento, String fecha, String operacion, String restante) {
        CuotasPendientesLineaView lineaCuota = new CuotasPendientesLineaView();
        lineaCuota.setCantidadCuotas(cantidadCuotas);
        lineaCuota.setCuotasPendientes(cuotasPendientes);
        lineaCuota.setEstablecimiento(establecimiento);
        lineaCuota.setFecha(fecha);
        lineaCuota.setOperacion(operacion);
        lineaCuota.setRestante(restante);
        return lineaCuota;
    }
}
