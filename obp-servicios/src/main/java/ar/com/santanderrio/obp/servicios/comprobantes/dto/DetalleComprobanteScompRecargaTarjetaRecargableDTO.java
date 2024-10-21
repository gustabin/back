/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteRecargaTarjetaView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class DetalleComprobanteScompRecargaTarjetaRecargableDTO.
 */
public class DetalleComprobanteScompRecargaTarjetaRecargableDTO extends DetalleComprobanteDTO {
    /** The comision total. */
    private String comisionTotal;

    /** The importe acreditado. */
    private String importeAcreditado;

    /**
     * Gets the comision total.
     *
     * @return the comision total
     */
    public String getComisionTotal() {
        return comisionTotal;
    }

    /**
     * Sets the comision total.
     *
     * @param comisionTotal
     *            the new comision total
     */
    public void setComisionTotal(String comisionTotal) {
        this.comisionTotal = comisionTotal;
    }

    /**
     * Gets the importe acreditado.
     *
     * @return the importe acreditado
     */
    public String getImporteAcreditado() {
        return importeAcreditado;
    }

    /**
     * Sets the importe acreditado.
     *
     * @param importeAcreditado
     *            the new importe acreditado
     */
    public void setImporteAcreditado(String importeAcreditado) {
        this.importeAcreditado = importeAcreditado;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder();
        hb.append(comisionTotal);
        hb.append(importeAcreditado);
        return hb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }

        if (a == null) {
            return false;
        }

        if (getClass() != a.getClass()) {
            return false;
        }
        DetalleComprobanteScompRecargaTarjetaRecargableDTO detalle = new DetalleComprobanteScompRecargaTarjetaRecargableDTO();
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(comisionTotal, detalle.getComisionTotal());
        eb.append(importeAcreditado, detalle.getImporteAcreditado());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleComprobanteScompRecargaTarjetaRecargableDTO [comisionTotal=");
        sb.append(comisionTotal);
        sb.append(", importeAcreditado= ");
        sb.append(importeAcreditado);
        sb.append("]");
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO#
     * getView(ar.com.santanderrio.obp.servicios.comprobantes.dto. ComprobanteDTO)
     */
    @Override
    public DetalleComprobanteView getView(ComprobanteDTO comprobante) {
        DetalleComprobanteRecargaTarjetaView view = new DetalleComprobanteRecargaTarjetaView();
        view.setTarjeta(comprobante.getDestinatario());
        view.setFecha(setearHora(comprobante.getFecha(), false));
        setearImportes(view, comprobante);
        setearNumeroTipoYTitulo(view);
        setearNumeroYTipoCuentaOrigen(view, comprobante.getCtaMedioDePagoPesos(), ISBANStringUtils
                .formatearFraseInicialMayuscula(comprobante.getTipoCtaMedioDePagoPesos().getDescripcion()));
        view.setComisionTotal(getComisionTotal());
        view.setEfectuada(true);
        view.setImporteAcreditado(getImporteAcreditado());
        return view;

    }
}
