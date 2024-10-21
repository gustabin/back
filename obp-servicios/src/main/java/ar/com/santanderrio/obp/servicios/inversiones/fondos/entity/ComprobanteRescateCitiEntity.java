/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class ComprobanteRescateCitiEntity.
 */
@Record
public class ComprobanteRescateCitiEntity {

    /** The header trama. */
    @Field
    private String headerTrama;

    /** The nroSolicitudRescate. */
    @Field
    private String nroSolicitudRescate;

    /** The importeRescateBruto. */
    @Field
    private String importeRescateBruto;

    /** The totalCuotasPartes. */
    @Field
    private String totalCuotasPartes;

    /** The id sistema. */
    // @Field(nillable=true)
    private String idSistema;

    /** The cant desc status resultado. */
    // @Field(nillable=true)
    private String cantDescStatusResultado;

    /** The descripcion status resultado. */
    // @Field(nillable=true)
    private String descripcionStatusResultado;

    /**
     * Gets the header trama.
     *
     * @return the header trama
     */
    public String getHeaderTrama() {
        return headerTrama;
    }

    /**
     * Sets the header trama.
     *
     * @param headerTrama
     *            the new header trama
     */
    public void setHeaderTrama(String headerTrama) {
        this.headerTrama = headerTrama;
    }

    /**
     * Gets the id sistema.
     *
     * @return the id sistema
     */
    public String getIdSistema() {
        return idSistema;
    }

    /**
     * Sets the id sistema.
     *
     * @param idSistema
     *            the new id sistema
     */
    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    /**
     * Gets the cant desc status resultado.
     *
     * @return the cant desc status resultado
     */
    public String getCantDescStatusResultado() {
        return cantDescStatusResultado;
    }

    /**
     * Sets the cant desc status resultado.
     *
     * @param cantDescStatusResultado
     *            the new cant desc status resultado
     */
    public void setCantDescStatusResultado(String cantDescStatusResultado) {
        this.cantDescStatusResultado = cantDescStatusResultado;
    }

    /**
     * Gets the descripcion status resultado.
     *
     * @return the descripcion status resultado
     */
    public String getDescripcionStatusResultado() {
        return descripcionStatusResultado;
    }

    /**
     * Sets the descripcion status resultado.
     *
     * @param descripcionStatusResultado
     *            the new descripcion status resultado
     */
    public void setDescripcionStatusResultado(String descripcionStatusResultado) {
        this.descripcionStatusResultado = descripcionStatusResultado;
    }

    /**
	 * Gets the nro solicitud rescate.
	 *
	 * @return the nroSolicitudRescate
	 */
    public String getNroSolicitudRescate() {
        return nroSolicitudRescate;
    }

    /**
	 * Sets the nro solicitud rescate.
	 *
	 * @param nroSolicitudRescate
	 *            the nroSolicitudRescate to set
	 */
    public void setNroSolicitudRescate(String nroSolicitudRescate) {
        this.nroSolicitudRescate = nroSolicitudRescate;
    }

    /**
	 * Gets the importe rescate bruto.
	 *
	 * @return the importeRescateBruto
	 */
    public String getImporteRescateBruto() {
        return importeRescateBruto;
    }

    /**
	 * Sets the importe rescate bruto.
	 *
	 * @param importeRescateBruto
	 *            the importeRescateBruto to set
	 */
    public void setImporteRescateBruto(String importeRescateBruto) {
        this.importeRescateBruto = importeRescateBruto;
    }

    /**
	 * Gets the total cuotas partes.
	 *
	 * @return the totalCuotasPartes
	 */
    public String getTotalCuotasPartes() {
        return totalCuotasPartes;
    }

    /**
	 * Sets the total cuotas partes.
	 *
	 * @param totalCuotasPartes
	 *            the totalCuotasPartes to set
	 */
    public void setTotalCuotasPartes(String totalCuotasPartes) {
        this.totalCuotasPartes = totalCuotasPartes;
    }

}
