package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PagoComprasMultiple {
    
    private static final SimpleDateFormat fechaHoraFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
    
    /** Variable lista facturas. */
    private List<DeudaPagoComprasView> deudas;
    
    /** The pago compras id. */
    private String pagoComprasId;

    /** The codigo pago electronico. */
    private String codigoPagoElectronico;

    /** The nombre empresa. */
    private String nombreEmpresa;

    /** The desafio. */
    private AutentificacionDTO desafio;

    /** variable q maneja los reintentos. */
    private boolean reintentar;
    
    /** The mensaje feedback. */
    private String mensajeFeedback;
    
    /** The nro de comprobante. */
    private String nroDeComprobante;
    
    /** The fecha pago. */
    private String fechaPago;
    
    /** The fecha hora. */
    private String fechaHora;
    
    public List<DeudaPagoComprasView> getDeudas() {
        return deudas;
    }

    public void setDeudas(List<DeudaPagoComprasView> deudas) {
        this.deudas = deudas;
    }

    public String getPagoComprasId() {
        return pagoComprasId;
    }

    public void setPagoComprasId(String pagoComprasId) {
        this.pagoComprasId = pagoComprasId;
    }

    public String getCodigoPagoElectronico() {
        return codigoPagoElectronico;
    }

    public void setCodigoPagoElectronico(String codigoPagoElectronico) {
        this.codigoPagoElectronico = codigoPagoElectronico;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

    public boolean isReintentar() {
        return reintentar;
    }

    public void setReintentar(boolean reintentar) {
        this.reintentar = reintentar;
    }

    public String getMensajeFeedback() {
        return mensajeFeedback;
    }

    public void setMensajeFeedback(String mensajeFeedback) {
        this.mensajeFeedback = mensajeFeedback;
    }

    public String getNroDeComprobante() {
        return nroDeComprobante;
    }

    public void setNroDeComprobante(String nroDeComprobante) {
        this.nroDeComprobante = nroDeComprobante;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal montoTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (deudas != null) {
            for (DeudaPagoComprasView deuda : deudas) {
                total = total.add(deuda.getMontoSinFormatear());
            }
        }
        return total;
    }

    public void cargarComprobantes(PagoComprasOutDTO pagoComprasDTO, DivisaEnum divisa) {
        this.fechaHora = fechaHoraFormat.format(new Date());
        this.nroDeComprobante = pagoComprasDTO.getComprobante();
        this.fechaPago = StringUtils.left(this.fechaHora, 10);

        if (CollectionUtils.isNotEmpty(this.deudas)) {
            for (DeudaPagoComprasView deudaView : this.deudas) {
                ComprobanteDeudaEntity comprobanteDeuda = pagoComprasDTO
                        .obtenerDeudaPorId(deudaView.getNumeroFactura());
                deudaView.comprobanteAView(comprobanteDeuda, divisa);
            }
        }
    }
}
