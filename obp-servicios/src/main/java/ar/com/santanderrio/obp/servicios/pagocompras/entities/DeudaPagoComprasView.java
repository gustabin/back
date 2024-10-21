package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeudaPagoComprasView extends Factura {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    private static SimpleDateFormat formatIATX = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_IATX);
    private static SimpleDateFormat formatView = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA);

    /** The monto sin formatear. */
    private BigDecimal montoSinFormatear;
    
    private CuentaSeleccionView cuentaSeleccionada;
    
    private String numeroOrden;

    public BigDecimal getMontoSinFormatear() {
        return montoSinFormatear;
    }

    public void setMontoSinFormatear(BigDecimal montoSinFormatear) {
        this.montoSinFormatear = montoSinFormatear;
    }

    public CuentaSeleccionView getCuentaSeleccionada() {
        return cuentaSeleccionada;
    }

    public void setCuentaSeleccionada(CuentaSeleccionView cuentaSeleccionada) {
        this.cuentaSeleccionada = cuentaSeleccionada;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
    
    public void entityAView(DeudaPagoComprasEntity deuda, DivisaEnum divisa) {
        try {
            BigDecimal monto = ISBANStringUtils.convertirStrToBigDecimal(deuda.getImporteSaldoDeuda(), 2);
            setMontoSinFormatear(monto);
            setMonto(ISBANStringUtils.formatearSaldoConDivisa(monto, divisa));
        } catch (ImporteConvertException e1) {
            setMonto(divisa.getSimbolo());
        }
        
        setNumeroFactura(StringUtils.trimToEmpty(deuda.getNroComprobanteDeuda()) + "-"
                + StringUtils.trimToEmpty(deuda.getNroCuotaDeuda()));
        try {
            setFechaVencimiento(formatView.format(formatIATX.parse(deuda.getFechaVencimientoDeuda())));
        } catch (ParseException e) {
            setFechaVencimiento("--/--/--");
        }
    }


    public void comprobanteAView(ComprobanteDeudaEntity comprobanteDeuda, DivisaEnum divisa) {
        try {
            BigDecimal monto = ISBANStringUtils.convertirStrToBigDecimal(comprobanteDeuda.getImpPago(), 2);
            setMontoSinFormatear(monto);
            setMonto(ISBANStringUtils.formatearSaldoConDivisa(monto, divisa));
        } catch (ImporteConvertException e1) {
            setMonto(divisa.getSimbolo());
        }

        setNumeroFactura(StringUtils.trimToEmpty(comprobanteDeuda.getNroCprb()) + "-"
                + StringUtils.trimToEmpty(comprobanteDeuda.getNroCuo()));
        try {
            setFechaVencimiento(formatView.format(formatIATX.parse(comprobanteDeuda.getFecVto())));
        } catch (ParseException e) {
            setFechaVencimiento("--/--/--");
        }
        setNumeroOrden(StringUtils.trim(comprobanteDeuda.getNroCprb()) + "-" + StringUtils.trim(comprobanteDeuda.getNroCuo()));
    }

}
