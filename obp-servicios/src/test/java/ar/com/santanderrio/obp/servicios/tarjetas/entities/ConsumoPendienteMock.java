package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoPendienteView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosPendientesView;

/**
 * The Class ConsumoPendienteMock.
 *
 * @author sabrina.cis
 */
public final class ConsumoPendienteMock {

    private ConsumoPendienteMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Obtener consumos pendientes.
     *
     * @return the consumos pendientes view
     */
    public static ConsumosPendientesView obtenerConsumosPendientes() {
        ConsumosPendientesView consumosPendientesView = new ConsumosPendientesView();
        consumosPendientesView.setConsumosPendientes(obtenerListaConsumoPendienteView());
        return consumosPendientesView;
    }

    /**
     * Obtener lista consumo pendiente view.
     *
     * @return the list
     */
    public static List<ConsumoPendienteView> obtenerListaConsumoPendienteView() {
        List<ConsumoTarjetaDTO> dtoList = obtenerConsumosPendientesList();
        List<ConsumoPendienteView> viewList = new ArrayList<ConsumoPendienteView>();
        viewList.add(new ConsumoPendienteView(dtoList.get(0)));
        return viewList;
    }

    /**
     * Obtener consumos pendientes list.
     *
     * @return the list
     */
    public static List<ConsumoTarjetaDTO> obtenerConsumosPendientesList() {
        List<ConsumoTarjetaDTO> consumosPendientes = new ArrayList<ConsumoTarjetaDTO>();
        consumosPendientes.add(obtenerConsumoPendiente());
        return consumosPendientes;
    }

    /**
     * Obtener consumo pendiente.
     *
     * @return the consumo pendiente
     */
    public static ConsumoTarjetaDTO obtenerConsumoPendiente() {
        ConsumoTarjetaDTO consumoPendiente = new ConsumoTarjetaDTO();
        consumoPendiente.setHasError(false);
        consumoPendiente.setHasError(false);
        consumoPendiente.setHasConsumoDolaresCero(false);
        consumoPendiente.setHasConsumoPesosCero(false);
        consumoPendiente.setIsTitular(true);
        consumoPendiente.setLineas(obtenerlineasPendientes());
        consumoPendiente.setMarca("VISA");
        consumoPendiente.setNombreAdicional("");
        consumoPendiente.setNumero("0000000338501392");
        consumoPendiente.setConsumoDolares(BigDecimal.valueOf(16.80));
        consumoPendiente.setConsumoPesos(BigDecimal.valueOf(16.80));
        return consumoPendiente;
    }

    /**
     * Obtener lineas pendientes.
     *
     * @return the list
     */
    private static List<LineaDetalleConsumoTarjetaDTO> obtenerlineasPendientes() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        lineas.add(LineaDetalleConsumoPendienteMock.obtenerLineaDetalleConsumoPendiente());
        return lineas;
    }

}
