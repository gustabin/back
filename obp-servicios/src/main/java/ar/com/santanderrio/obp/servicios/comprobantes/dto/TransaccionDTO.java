/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;

/**
 * The Class TransaccionDTO.
 */
public class TransaccionDTO {

    /** The fecha desde. */
    private Date fechaDesde;

    /** The fecha hasta. */
    private Date fechaHasta;

    /** The transaccion. */
    TransaccionEnum transaccion;

    /** The empresa. */
    private String empresa;

    /** The importe desde. */
    private BigDecimal importeDesde;

    /** The importe hasta. */
    private BigDecimal importeHasta;

    /**
     * Instantiates a new transaccion DTO.
     *
     * @param idTransaccion
     *            the id transaccion
     * @param fechaDesdeDate
     *            the fecha desde date
     * @param fechaHastaDate
     *            the fecha hasta date
     * @param empresa
     *            the empresa
     * @param importeDesde
     *            the importe desde
     * @param importeHasta
     *            the importe hasta
     */
    public TransaccionDTO(Integer idTransaccion, Date fechaDesdeDate, Date fechaHastaDate, String empresa,
            BigDecimal importeDesde, BigDecimal importeHasta) {
        transaccion = TransaccionEnum.getTransaccionByID(idTransaccion);
        fechaDesde = fechaDesdeDate;
        fechaHasta = fechaHastaDate;
        this.empresa = empresa;
        this.importeDesde = importeDesde;
        this.importeHasta = importeHasta;
    }

    /**
     * Instantiates a new transaccion DTO.
     *
     * @param idTransaccion
     *            the id transaccion
     * @param fechaDesdeDate
     *            the fecha desde date
     * @param fechaHastaDate
     *            the fecha hasta date
     * @param empresa
     *            the empresa
     */
    public TransaccionDTO(Integer idTransaccion, Date fechaDesdeDate, Date fechaHastaDate, String empresa) {
        transaccion = TransaccionEnum.getTransaccionByID(idTransaccion);
        fechaDesde = fechaDesdeDate;
        fechaHasta = fechaHastaDate;
        this.empresa = empresa;
    }

    /**
     * Instantiates a new transaccion DTO.
     */
    public TransaccionDTO() {
        super();
    }

    /**
     * Gets the fecha desde.
     *
     * @return the fecha desde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the fecha desde.
     *
     * @param fechaDesde
     *            the new fecha desde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fecha hasta.
     *
     * @return the fecha hasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the fecha hasta.
     *
     * @param fechaHasta
     *            the new fecha hasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the transaccion.
     *
     * @return the transaccion
     */
    public TransaccionEnum getTransaccion() {
        return transaccion;
    }

    /**
     * Sets the transaccion.
     *
     * @param transaccion
     *            the new transaccion
     */
    public void setTransaccion(TransaccionEnum transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * Gets the empresa.
     *
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the empresa.
     *
     * @param empresa
     *            the new empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Gets the importe desde.
     *
     * @return the importe desde
     */
    public BigDecimal getImporteDesde() {
        return importeDesde;
    }

    /**
     * Sets the importe desde.
     *
     * @param importeDesde
     *            the new importe desde
     */
    public void setImporteDesde(BigDecimal importeDesde) {
        this.importeDesde = importeDesde;
    }

    /**
     * Gets the importe hasta.
     *
     * @return the importe hasta
     */
    public BigDecimal getImporteHasta() {
        return importeHasta;
    }

    /**
     * Sets the importe hasta.
     *
     * @param importeHasta
     *            the new importe hasta
     */
    public void setImporteHasta(BigDecimal importeHasta) {
        this.importeHasta = importeHasta;
    }

    /**
	 * Tiene filtro importe.
	 *
	 * @return true, if successful
	 */
    public boolean tieneFiltroImporte() {
        return this.importeDesde != null && this.importeHasta != null;
    }
}
