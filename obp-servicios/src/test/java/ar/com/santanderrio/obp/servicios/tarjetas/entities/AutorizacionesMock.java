package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumosCuentasAuthEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ImporteEntity;

/**
 * The Class AutorizacionesMock.
 *
 * @author florencia.n.martinez
 */
public final class AutorizacionesMock {

    private AutorizacionesMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info autorizaciones.
     *
     * @return the autorizaciones
     */
    public static AutorizacionesEntity completarInfoAutorizaciones() {
        AutorizacionesEntity autorizaciones = new AutorizacionesEntity();
        autorizaciones.setTotales(completarTotales());
        autorizaciones.setTarjetaPendienteConfirmacionList(completarInfoConsumosPendientes());
        return autorizaciones;
    }

    /**
     * Completar info consumos pendientes.
     *
     * @return the list
     */
    public static List<ConsumoPendienteConfirmacionEntity> completarInfoConsumosPendientes() {
        List<ConsumoPendienteConfirmacionEntity> consumos = new ArrayList<ConsumoPendienteConfirmacionEntity>();
        consumos.add(completarInfoConsumo());
        return consumos;
    }

    /**
     * Completar info consumo.
     *
     * @return the consumo pendiente confirmacion
     */
    private static ConsumoPendienteConfirmacionEntity completarInfoConsumo() {
        ConsumoPendienteConfirmacionEntity consumo = new ConsumoPendienteConfirmacionEntity();
        consumo.setCodigoTarjeta("XXXX XXXX XXXX1728");
        consumo.setDolares("0,00");
        consumo.setPesos("1.500,00");
        consumo.setAutorizacionList(completarInfoListaAutorizacion());

        return consumo;
    }

    /**
     * Completar info lista autorizacion.
     *
     * @return the list
     */
    public static List<AutorizacionEntity> completarInfoListaAutorizacion() {
        List<AutorizacionEntity> autorizaciones = new ArrayList<AutorizacionEntity>();
        autorizaciones.add(completarInfoAutorizacion());
        return autorizaciones;
    }

    /**
     * Completar info autorizacion.
     *
     * @return the autorizacion
     */
    public static AutorizacionEntity completarInfoAutorizacion() {
        AutorizacionEntity autorizacion = new AutorizacionEntity();
        autorizacion.setEstablecimiento(completarInfoEstablecimiento());
        autorizacion.setCodigo("03160");
        autorizacion.setDescripcion("CONS. $     ");
        autorizacion.setFecha("12/03/2016");
        autorizacion.setImporte(completarInfoImporte());
        autorizacion.setInternacional("");
        autorizacion.setMoneda("pesos");
        autorizacion.setTipo("C");
        return autorizacion;
    }

    /**
     * Completar info importe.
     *
     * @return the importe entity
     */
    private static ImporteEntity completarInfoImporte() {
        ImporteEntity importe = new ImporteEntity();
        importe.setValor("1.500,00");
        return importe;
    }

    /**
     * Completar info establecimiento.
     *
     * @return the establecimiento entity
     */
    public static EstablecimientoEntity completarInfoEstablecimiento() {
        EstablecimientoEntity establecimiento = new EstablecimientoEntity();
        establecimiento.setCodigo("0014866776");
        establecimiento.setDescripcion("PAULA CAHEN D&apos;ANVERS");
        return establecimiento;
    }

    /**
     * Completar totales.
     *
     * @return the consumos cuentas auth
     */
    private static ConsumosCuentasAuthEntity completarTotales() {
        ConsumosCuentasAuthEntity auth = new ConsumosCuentasAuthEntity();
        auth.setPesos("1.500,00");
        auth.setDolares("0,00");
        return auth;
    }
}