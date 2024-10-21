/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasView;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoComprasMultiple;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class DetalleComprobantePagoComprasView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DetalleComprobantePagoComprasView extends DetalleComprobanteView {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The label dinamico. */
    private String labelDinamico;

    /** The nro boleta. */
    private String nroBoleta;
    
    /** The nro orden. */
    private String nroOrden;
    
    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** The pago compras servicio jasper. */
    private final String pagoComprasServicioJasper = "pago-compras.jasper";

    /** The tipo medio pago key. */
    protected final String tipoMedioPagoKey = "TIPO_MEDIO_PAGO";
    
    /** The nro boleta key. */
    protected final String nroBoletaKey = "NUMERO_BOLETA";
    
    /** The nro orden key. */
    protected final String nroOrdenKey = "NUMERO_ORDEN";
    
    public DetalleComprobantePagoComprasView() {
        super();
    }

    public DetalleComprobantePagoComprasView(NuevoPago nuevoPagoView, MedioPago medioPago, Cuenta cuenta,
            DivisaEnum divisa) {
        setDatosComunes(medioPago, cuenta);

        String importe = ISBANStringUtils.formatearSaldoSinAbs(new BigDecimal(nuevoPagoView.getMonto()));
        if (DivisaEnum.PESO.equals(divisa)) {
            setImportePesos(importe);
        } else {
            setImporteDolares(importe);
        }
        setFechaVencimiento(nuevoPagoView.getFechaVencimiento());
        setIdentificacion(nuevoPagoView.getCodigoPagoElectronico());
        setFecha(nuevoPagoView.getFechaPago());
        setNroComprobante(nuevoPagoView.getNroDeComprobante());
        setNroBoleta(nuevoPagoView.getNroBoleta());
        setNroOrden(nuevoPagoView.getFacturaNumero());
        setFechaOperacion(nuevoPagoView.getFechaHora());
    }


    public DetalleComprobantePagoComprasView(PagoComprasMultiple pagoCompras, DeudaPagoComprasView deuda,
            MedioPago medioPago, Cuenta cuenta, DivisaEnum divisa) {
        setDatosComunes(medioPago, cuenta);
        
        String importe = ISBANStringUtils.formatearSaldoSinAbs(deuda.getMontoSinFormatear());
        if (DivisaEnum.PESO.equals(divisa)) {
            setImportePesos(importe);
        } else {
            setImporteDolares(importe);
        }
        setFechaVencimiento(deuda.getFechaVencimiento());
        setIdentificacion(pagoCompras.getCodigoPagoElectronico());
        setFecha(pagoCompras.getFechaPago());
        setNroComprobante(pagoCompras.getNroDeComprobante());
        setNroOrden(deuda.getNumeroOrden());
        setFechaOperacion(pagoCompras.getFechaHora());
        
        setIdComprobante(deuda.getNumeroFactura());
    }
    
    private void setDatosComunes(MedioPago medioPago, Cuenta cuenta) {
        setTituloComprobante(CabeceraComprobantesEnum.COMPROBANTE_DE_PAGO.getDetalle());

        setEmpresa(medioPago.getNombreFantasia());
        
        setLabelDinamico(StringUtils.defaultIfBlank(
                WordUtils.capitalizeFully(medioPago.getPpdctaIdentificadorPyrip(), '.'), "Identificación cliente"));
        
        setNroCuentaOrigen(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));

        if (cuenta.esCuentaUnica()) {
            setTipoCuentaOrigen(cuenta.getTipoCuentaEnum().getDescripcion());
        } else {
            setTipoCuentaOrigen(cuenta.getTipoCuentaEnum().getDescripcionConMoneda());
        }
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerParametrosPDF()
     */
    @Override
    public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(tituloKey, getEmpresa());
        parametros.put(importeKey, obtenerImporte());
        parametros.put(empresaKey, getEmpresa());
        parametros.put(fechaVencimientoKey, getFechaVencimiento());
        parametros.put(tipoIdentificacionKey, getLabelDinamico());
        parametros.put(identificacionKey, getIdentificacion());
        parametros.put(medioPagoKey, getNroCuentaOrigen());
        parametros.put(tipoMedioPagoKey, getTipoCuentaOrigen());
        parametros.put(fechaPagoKey, getFecha());
        parametros.put(numeroComprobanteKey, getNroComprobante());
        parametros.put(nroBoletaKey, getNroBoleta());
        parametros.put(nroOrdenKey, getNroOrden());
        parametros.put(fechaActualKey, getFechaOperacion());
        return parametros;
    }
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#obtenerJasper()
     */
    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(path + pagoComprasServicioJasper).getPath();
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#getLabelDinamico()
     */
    public String getLabelDinamico() {
        return labelDinamico;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView#setLabelDinamico(java.lang.String)
     */
    public void setLabelDinamico(String labelDinamico) {
        this.labelDinamico = labelDinamico;
    }

    /**
	 * Gets the nro boleta.
	 *
	 * @return the nro boleta
	 */
    public String getNroBoleta() {
        return nroBoleta;
    }

    /**
	 * Sets the nro boleta.
	 *
	 * @param nroBoleta
	 *            the new nro boleta
	 */
    public void setNroBoleta(String nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    /**
	 * Gets the nro orden.
	 *
	 * @return the nro orden
	 */
    public String getNroOrden() {
        return nroOrden;
    }

    /**
	 * Sets the nro orden.
	 *
	 * @param nroOrden
	 *            the new nro orden
	 */
    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    /**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
