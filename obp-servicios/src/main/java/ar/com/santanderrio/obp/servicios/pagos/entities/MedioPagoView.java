/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import static org.apache.commons.lang3.StringUtils.trimToNull;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Clase de representacion de Medio de Pago que se utilizara como respuesta.
 *
 * @author pablo.martin.gore
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class MedioPagoView implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 8737343508560779893L;

    /** Variable rubro fantasia. */
    private String rubroFantasia;

    /** Variable nombre fantasia. */
    private String nombreFantasia;

    /** Variable tipo empresa. */
    private String tipoEmpresa;

    /** Variable datos adicionales. */
    private String datosAdicionales;

    /** Variable pes prepago. */
    private String pesPrepago;

    /** Variable pes gif factura. */
    private String pesGifFactura;

    /** Variable pes identificacion. */
    private String pesIdentificacion;

    /** Variable codigo pago electronico. */
    private String codigoPagoElectronico;

    /** The codigo pago electronico 2. */
    private String codigoPagoElectronico2;

    /** Variable pes tipo pago. */
    private Integer pesTipoPago;

    /** Variable pre pago. */
    private boolean prePago;

    /** Variable pes Tipo Importe. */
    private Integer pesTipoImporte;

    /** The fiid. */
    private String fiid;

    /** The pes PA habilitado. */
    private String pesPAHabilitado;

    /** The addi habilitado. */
    private String addiHabilitado;

    /** The cuit. */
    private String cuit;

    /** The servicio. */
    private String servicio;

    /** The addi leyenda identificacion. */
    private String addiLeyendaIdentificacion;

    /** The addi longitud. */
    private String addiLongitud;

    /** The addi gif factura. */
    private String addiGifFactura;

    /** The marca pago tc. */
    private String marcaPagoTc;

    /** The monto maximo pago tc. */
    private String montoMaximoPagoTc;

    /** The monto minimo pago tc. */
    private String montoMinimoPagoTc;

    /** The tipo nuevo pago. */
    private String tipoNuevoPago;

    /** The visa cod establecimiento. */
    private String visaCodEstablecimiento;

    /** The pago compras id. */
    private String pagoComprasId;

    /** The pago compras leyenda identificacion. */
    private String pagoComprasLeyendaIdentificacion;
    

    /**
     * Instancia un nuevo medio pago view.
     */
    public MedioPagoView() {
        super();
    }

    /**
	 * Instantiates a new medio pago view.
	 *
	 * @param medioPago
	 *            the medio pago
	 */
    public MedioPagoView(MedioPago medioPago) {
        this.rubroFantasia = trimToNull(medioPago.getRubroFantasia());
        this.nombreFantasia = trimToNull(medioPago.getNombreFantasia());
        this.tipoEmpresa = trimToNull(medioPago.getTipoEmpresa());
        this.datosAdicionales = trimToNull(medioPago.getDatosAdicionales());
        this.pesPrepago = trimToNull(medioPago.getPesPrepago());
        this.pesGifFactura = trimToNull(medioPago.getPesGifFactura());
        this.fiid = trimToNull(medioPago.getFiid());
        this.pesPAHabilitado = trimToNull(medioPago.getPesPAHabilitado());
        this.addiHabilitado = trimToNull(medioPago.getAddiHabilitado());
        this.cuit = trimToNull(medioPago.getCuit());
        this.servicio = trimToNull(medioPago.getServicio());
        this.addiLongitud = trimToNull(medioPago.getAddiLongitud());
        this.addiGifFactura = trimToNull(medioPago.getAddiGifFactura());
        this.marcaPagoTc = trimToNull(medioPago.getMarcaPagoTc());
        this.montoMaximoPagoTc = trimToNull(medioPago.getMontoMaximoPagoTc());
        this.montoMinimoPagoTc = trimToNull(medioPago.getMontoMinimoPagoTc());
        this.visaCodEstablecimiento = trimToNull(medioPago.getVisaCodEstablecimiento());
        this.tipoNuevoPago = "0";
        this.pesTipoPago = medioPago.getTipoPago();
        this.addiLeyendaIdentificacion = StringUtils
                .capitalize(StringUtils.lowerCase(trimToNull(medioPago.getAddiLeyendaIdentificacion())));
        this.pesIdentificacion = StringUtils
                .capitalize(StringUtils.lowerCase(trimToNull(medioPago.getPesIdentificacion())));
        try {
            this.pesTipoImporte = Integer.valueOf(medioPago.getTipoImporte());
        } catch (NumberFormatException e) {
            this.pesTipoImporte = 0;
        }
        this.pagoComprasId = trimToNull(StringUtils.trimToEmpty(medioPago.getPpdctaIdEmpAcuerdo())
                + StringUtils.trimToEmpty(medioPago.getPpdctaNroInstaCuerdo()));
        this.pagoComprasLeyendaIdentificacion = StringUtils.defaultIfBlank(
                StringUtils.capitalize(StringUtils.lowerCase(medioPago.getPpdctaIdentificadorPyrip())),
                "Identificación cliente");
    }
    /**
     * Getter para rubro fantasia.
     *
     * @return el rubro fantasia
     */
    public String getRubroFantasia() {
        return rubroFantasia;
    }

    /**
     * Setter para rubro fantasia.
     *
     * @param rubroFantasia
     *            el nuevo rubro fantasia
     */
    public void setRubroFantasia(String rubroFantasia) {
        this.rubroFantasia = rubroFantasia;
    }

    /**
     * Getter para nombre fantasia.
     *
     * @return el nombre fantasia
     */
    public String getNombreFantasia() {
        return nombreFantasia;
    }

    /**
     * Setter para nombre fantasia.
     *
     * @param nombreFantasia
     *            el nuevo nombre fantasia
     */
    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    /**
     * Getter para tipo empresa.
     *
     * @return el tipo empresa
     */
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    /**
     * Setter para tipo empresa.
     *
     * @param tipoEmpresa
     *            el nuevo tipo empresa
     */
    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    /**
     * Getter para datos adicionales.
     *
     * @return los datos adicionales
     */
    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    /**
     * Setter para datos adicionales.
     *
     * @param datosAdicionales
     *            los nuevos datos adicionales
     */
    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    /**
     * Getter para pes prepago.
     *
     * @return el pes prepago
     */
    public String getPesPrepago() {
        return pesPrepago;
    }

    /**
     * Setter para pes prepago.
     *
     * @param pesPrepago
     *            el nuevo pes prepago
     */
    public void setPesPrepago(String pesPrepago) {
        this.pesPrepago = pesPrepago;
    }

    /**
     * Getter para pes gif factura.
     *
     * @return el pes gif factura
     */
    public String getPesGifFactura() {
        return pesGifFactura;
    }

    /**
     * Setter para pes gif factura.
     *
     * @param pesGifFactura
     *            el nuevo pes gif factura
     */
    public void setPesGifFactura(String pesGifFactura) {
        this.pesGifFactura = pesGifFactura;
    }

    /**
     * Getter para pes identificacion.
     *
     * @return el pes identificacion
     */
    public String getPesIdentificacion() {
        return pesIdentificacion;
    }

    /**
     * Setter para pes identificacion.
     *
     * @param pesIdentificacion
     *            el nuevo pes identificacion
     */
    public void setPesIdentificacion(String pesIdentificacion) {
        this.pesIdentificacion = pesIdentificacion;
    }

    /**
     * Getter para codigo pago electronico.
     *
     * @return el codigo pago electronico
     */
    public String getCodigoPagoElectronico() {
        return codigoPagoElectronico;
    }

    /**
     * Setter para codigo pago electronico.
     *
     * @param codigoPagoElectronico
     *            el nuevo codigo pago electronico
     */
    public void setCodigoPagoElectronico(String codigoPagoElectronico) {
        this.codigoPagoElectronico = codigoPagoElectronico;
    }

    /**
     * Getter para pes tipo pago.
     *
     * @return el pes tipo pago
     */
    public Integer getPesTipoPago() {
        return pesTipoPago;
    }

    /**
     * Setter para pes tipo pago.
     *
     * @param pesTipoPago
     *            el nuevo pes tipo pago
     */
    public void setPesTipoPago(Integer pesTipoPago) {
        this.pesTipoPago = pesTipoPago;
    }

    /**
     * Chequea si es pre pago.
     *
     * @return true, si es pre pago
     */
    public boolean isPrePago() {
        return prePago;
    }

    /**
     * Setter para pre pago.
     *
     * @param prePago
     *            el nuevo pre pago
     */
    public void setPrePago(boolean prePago) {
        this.prePago = prePago;
    }

    /**
     * Getter para pes tipo importe.
     *
     * @return el pes tipo importe
     */
    public Integer getPesTipoImporte() {
        return pesTipoImporte;
    }

    /**
     * Setter para pes tipo importe.
     *
     * @param pesTipoImporte
     *            the new pes tipo importe
     */
    public void setPesTipoImporte(Integer pesTipoImporte) {
        this.pesTipoImporte = pesTipoImporte;
    }

    /**
     * Sets the tipo importe.
     *
     * @param tipoImporte
     *            the new tipo importe
     */
    public void setTipoImporte(String tipoImporte) {
        if (NumberUtils.isDigits(tipoImporte)) {
            this.pesTipoImporte = Integer.parseInt(tipoImporte);
        }
    }

    /**
     * Sets the tipo pago.
     *
     * @param tipoPago
     *            the new tipo pago
     */
    public void setTipoPago(String tipoPago) {
        if (NumberUtils.isDigits(tipoPago)) {
            this.pesTipoPago = Integer.parseInt(tipoPago);
        }
    }

    /**
     * Gets the fiid.
     *
     * @return the fiid
     */
    public String getFiid() {
        return fiid;
    }

    /**
     * Sets the fiid.
     *
     * @param fiid
     *            the new fiid
     */
    public void setFiid(String fiid) {
        this.fiid = fiid;
    }

    /**
     * Gets the codigo pago electronico 2.
     *
     * @return the codigo pago electronico 2
     */
    public String getCodigoPagoElectronico2() {
        return codigoPagoElectronico2;
    }

    /**
     * Sets the codigo pago electronico 2.
     *
     * @param codigoPagoElectronico2
     *            the new codigo pago electronico 2
     */
    public void setCodigoPagoElectronico2(String codigoPagoElectronico2) {
        this.codigoPagoElectronico2 = codigoPagoElectronico2;
    }

    /**
     * Gets the pes PA habilitado.
     *
     * @return the pes PA habilitado
     */
    public String getPesPAHabilitado() {
        return pesPAHabilitado;
    }

    /**
     * Sets the pes PA habilitado.
     *
     * @param pesPAHabilitado
     *            the new pes PA habilitado
     */
    public void setPesPAHabilitado(String pesPAHabilitado) {
        this.pesPAHabilitado = pesPAHabilitado;
    }

    /**
     * Gets the addi habilitado.
     *
     * @return the addi habilitado
     */
    public String getAddiHabilitado() {
        return addiHabilitado;
    }

    /**
     * Sets the addi habilitado.
     *
     * @param addiHabilitado
     *            the new addi habilitado
     */
    public void setAddiHabilitado(String addiHabilitado) {
        this.addiHabilitado = addiHabilitado;
    }

    /**
     * Gets the cuit.
     *
     * @return the cuit
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the cuit.
     *
     * @param cuit
     *            the new cuit
     */
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    /**
     * Gets the servicio.
     *
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Sets the servicio.
     *
     * @param servicio
     *            the new servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Gets the addi leyenda identificacion.
     *
     * @return the addi leyenda identificacion
     */
    public String getAddiLeyendaIdentificacion() {
        return addiLeyendaIdentificacion;
    }

    /**
     * Sets the addi leyenda identificacion.
     *
     * @param addiLeyendaIdentificacion
     *            the new addi leyenda identificacion
     */
    public void setAddiLeyendaIdentificacion(String addiLeyendaIdentificacion) {
        this.addiLeyendaIdentificacion = addiLeyendaIdentificacion;
    }

    /**
     * Gets the addi longitud.
     *
     * @return the addi longitud
     */
    public String getAddiLongitud() {
        return addiLongitud;
    }

    /**
     * Sets the addi longitud.
     *
     * @param addiLongitud
     *            the new addi longitud
     */
    public void setAddiLongitud(String addiLongitud) {
        this.addiLongitud = addiLongitud;
    }

    /**
     * Gets the addi gif factura.
     *
     * @return the addi gif factura
     */
    public String getAddiGifFactura() {
        return addiGifFactura;
    }

    /**
     * Sets the addi gif factura.
     *
     * @param addiGifFactura
     *            the new addi gif factura
     */
    public void setAddiGifFactura(String addiGifFactura) {
        this.addiGifFactura = addiGifFactura;
    }

    /**
     * Gets the marca pago tc.
     *
     * @return the marca pago tc
     */
    public String getMarcaPagoTc() {
        return marcaPagoTc;
    }

    /**
     * Sets the marca pago tc.
     *
     * @param marcaPagoTc
     *            the new marca pago tc
     */
    public void setMarcaPagoTc(String marcaPagoTc) {
        this.marcaPagoTc = marcaPagoTc;
    }

    /**
     * Gets the monto maximo pago tc.
     *
     * @return the monto maximo pago tc
     */
    public String getMontoMaximoPagoTc() {
        return montoMaximoPagoTc;
    }

    /**
     * Sets the monto maximo pago tc.
     *
     * @param montoMaximoPagoTc
     *            the new monto maximo pago tc
     */
    public void setMontoMaximoPagoTc(String montoMaximoPagoTc) {
        this.montoMaximoPagoTc = montoMaximoPagoTc;
    }

    /**
     * Gets the monto minimo pago tc.
     *
     * @return the monto minimo pago tc
     */
    public String getMontoMinimoPagoTc() {
        return montoMinimoPagoTc;
    }

    /**
     * Sets the monto minimo pago tc.
     *
     * @param montoMinimoPagoTc
     *            the new monto minimo pago tc
     */
    public void setMontoMinimoPagoTc(String montoMinimoPagoTc) {
        this.montoMinimoPagoTc = montoMinimoPagoTc;
    }

    /**
     * Gets the tipo nuevo pago.
     *
     * @return the tipo nuevo pago
     */
    public String getTipoNuevoPago() {
        return tipoNuevoPago;
    }

    /**
     * Sets the tipo nuevo pago.
     *
     * @param tipoNuevoPagoId
     *            the new tipo nuevo pago
     */
    public void setTipoNuevoPago(String tipoNuevoPagoId) {
        this.tipoNuevoPago = tipoNuevoPagoId;
    }

    /**
     * Gets the visa cod establecimiento.
     *
     * @return the visa cod establecimiento
     */
    public String getVisaCodEstablecimiento() {
        return visaCodEstablecimiento;
    }

    /**
     * Sets the visa cod establecimiento.
     *
     * @param visaCodEstablecimiento
     *            the new visa cod establecimiento
     */
    public void setVisaCodEstablecimiento(String visaCodEstablecimiento) {
        this.visaCodEstablecimiento = visaCodEstablecimiento;
    }

    /**
	 * Gets the pago compras id.
	 *
	 * @return the pago compras id
	 */
    public String getPagoComprasId() {
        return pagoComprasId;
    }

    /**
	 * Sets the pago compras id.
	 *
	 * @param pagoComprasId
	 *            the new pago compras id
	 */
    public void setPagoComprasId(String pagoComprasId) {
        this.pagoComprasId = pagoComprasId;
    }

    /**
	 * Gets the pago compras leyenda identificacion.
	 *
	 * @return the pago compras leyenda identificacion
	 */
    public String getPagoComprasLeyendaIdentificacion() {
        return pagoComprasLeyendaIdentificacion;
    }

    /**
	 * Sets the pago compras leyenda identificacion.
	 *
	 * @param pagoComprasLeyendaIdentificacion
	 *            the new pago compras leyenda identificacion
	 */
    public void setPagoComprasLeyendaIdentificacion(String pagoComprasLeyendaIdentificacion) {
        this.pagoComprasLeyendaIdentificacion = pagoComprasLeyendaIdentificacion;
    }


}