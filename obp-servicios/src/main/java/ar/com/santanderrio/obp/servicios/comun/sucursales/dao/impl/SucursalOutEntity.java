/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

/**
 * @author sergio.e.goldentair
 *
 */
@Record
public class SucursalOutEntity {
    /** The header trama. */
    @Field
    private String headerTrama;

    /** Codigo retorno extendido. */
    @Field(handlerName = "codigoRetornoExtendidoHandler")
    private String codigoRetornoExtendido;

    /** The oficinaRellamada. */
    @Field
    private String oficinaRellamada;

    /** The cantidad ocurrencias. */
    @Field
    private Long cantidadOcurrencias;

    /** The productos. */
    @Segment(occursRef = "cantidadOcurrencias")
    private List<SucursalEntity> sucursales = new ArrayList<SucursalEntity>();

    /**
     * @return the headerTrama
     */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
     * @param headerTrama
     *            the headerTrama to set
     */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
     * @return the codigoRetornoExtendido
     */
    public String getCodigoRetornoExtendido() {
        return codigoRetornoExtendido;
    }

    /**
     * @param codigoRetornoExtendido
     *            the codigoRetornoExtendido to set
     */
    public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
        this.codigoRetornoExtendido = codigoRetornoExtendido;
    }

    /**
     * @return the oficinaRellamada
     */
    public String getOficinaRellamada() {
        return oficinaRellamada;
    }

    /**
     * @param oficinaRellamada
     *            the oficinaRellamada to set
     */
    public void setOficinaRellamada(String oficinaRellamada) {
        this.oficinaRellamada = oficinaRellamada;
    }

    /**
     * @return the cantidadOcurrencias
     */
    public Long getCantidadOcurrencias() {
        return cantidadOcurrencias;
    }

    /**
     * @param cantidadOcurrencias
     *            the cantidadOcurrencias to set
     */
    public void setCantidadOcurrencias(Long cantidadOcurrencias) {
        this.cantidadOcurrencias = cantidadOcurrencias;
    }

    /**
     * @return the sucursales
     */
    public List<SucursalEntity> getSucursales() {
        return sucursales;
    }

    /**
     * @param sucursales
     *            the sucursales to set
     */
    public void setSucursales(List<SucursalEntity> sucursales) {
        this.sucursales = sucursales;
    }

}
