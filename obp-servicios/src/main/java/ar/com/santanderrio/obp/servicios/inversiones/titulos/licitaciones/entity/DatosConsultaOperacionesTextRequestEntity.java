/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaOperacionesTextRequestEntity.
 */
public class DatosConsultaOperacionesTextRequestEntity extends EntityBaseTitulos{
    
    /** The cuentas titulo. */
    @JsonProperty("CuentaTitulo")
    private String cuentasTitulo;
    
    /** The cno. */
    @JsonProperty("Cno")
    private String cno;
    
    /** The fecha desde. */
    @JsonProperty("FechaDesde")
    private String fechaDesde;
    
    /** The fecha hasta. */
    @JsonProperty("FechaHasta")
    private String fechaHasta;
    
    /** The canal. */
    @JsonProperty("Canal")
    private String canal;
    
    /** The sub canal. */
    @JsonProperty("SubCanal")
    private String subCanal;
    
    /** The nup. */
    @JsonProperty("Nup")
    private String nup;
    
    /** The segmento. */
    @JsonProperty("Segmento")
    private String segmento;
    
    /**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
    public String getCuentasTitulo() {
        return cuentasTitulo;
    }

    /**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
    public void setCuentasTitulo(String cuentasTitulo) {
        this.cuentasTitulo = cuentasTitulo;
    }

    /**
	 * Gets the cno.
	 *
	 * @return the cno
	 */
    public String getCno() {
        return cno;
    }

    /**
	 * Sets the cno.
	 *
	 * @param cno
	 *            the new cno
	 */
    public void setCno(String cno) {
        this.cno = cno;
    }

    /**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
    public String getCanal() {
        return canal;
    }

    /**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
    public void setCanal(String canal) {
        this.canal = canal;
    }

    /**
	 * Gets the sub canal.
	 *
	 * @return the sub canal
	 */
    public String getSubCanal() {
        return subCanal;
    }

    /**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the new sub canal
	 */
    public void setSubCanal(String subCanal) {
        this.subCanal = subCanal;
    }

    /**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
    public String getNup() {
        return nup;
    }

    /**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
    public String getSegmento() {
        return segmento;
    }

    /**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

}
