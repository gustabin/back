/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobantePagoTarjetaView.
 */
public class DetalleComprobantePagoTarjetaView extends DetalleComprobanteView {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The tipo tarjeta. */
    private String tipoTarjeta;

    /** The nro stop debit. */
    private String nroStopDebit;

    /** The nro cuenta origen doalres. */
    private String nroCuentaOrigenDolares;

    /** The tipo cuenta origen doalres. */
    private String tipoCuentaOrigenDolares;

    /** The tarjeta. */
    private final String tarjetaKey = "TARJETA";

    /** The medio pago tarjeta. */
    private final String medioPagoTarjetaKey = "MEDIO_PAGO_TARJETA";

    /** The fecha pago tarjeta. */
    private final String fechaPagoTarjetaKey = "FECHA_PAGO_TARJETA";

    /** The salto linea. */
    private final String cuentaDebitoPesosKey = "CUENTA_DEBITO_PESOS";

    /** The tipo cuenta debito pesos. */
    private final String tipoCuentaDebitoPesosKey = "TIPO_CUENTA_DEBITO_PESOS";

    /** The cuenta debito dolares. */
    private final String cuentaDebitoDolaresKey = "CUENTA_DEBITO_DOLARES";

    /** The tipo cuenta debito dolares. */
    private final String tipoCuentaDebitoDolaresKey = "TIPO_CUENTA_DEBITO_DOLARES";

    /** The solicitud stop debit. */
    private final String solicitudStopDebitKey = "SOLICITUD_STOP";

    /** The pago tarjeta jasper. */
    private final String pagoTarjetaJasper = "pago-tarjeta.jasper";

    /** The logo visa png. */
    private final String logoVisa = "logo-tarjeta-visa.png";

    /** The logo amex png. */
    private final String logoAmex = "logo-tarjeta-amex.png";

    /** The logo master png. */
    private final String logoMaster = "logo-tarjeta-master.png";

    /**
     * Gets the tipo tarjeta.
     *
     * @return the tipo tarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * Sets the tipo tarjeta.
     *
     * @param tipoTarjeta
     *            the new tipo tarjeta
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * Gets the nro stop debit.
     *
     * @return the nroStopDebit
     */
    public String getNroStopDebit() {
        return nroStopDebit;
    }

    /**
     * Sets the nro stop debit.
     *
     * @param nroStopDebit
     *            the nroStopDebit to set
     */
    public void setNroStopDebit(String nroStopDebit) {
        this.nroStopDebit = nroStopDebit;
    }

    /**
     * Gets the nro cuenta origen dolares.
     *
     * @return the nroCuentaOrigenDolares
     */
    public String getNroCuentaOrigenDolares() {
        return nroCuentaOrigenDolares;
    }

    /**
     * Sets the nro cuenta origen dolares.
     *
     * @param nroCuentaOrigenDolares
     *            the nroCuentaOrigenDolares to set
     */
    public void setNroCuentaOrigenDolares(String nroCuentaOrigenDolares) {
        this.nroCuentaOrigenDolares = nroCuentaOrigenDolares;
    }

    /**
     * Gets the tipo cuenta origen dolares.
     *
     * @return the tipoCuentaOrigenDolares
     */
    public String getTipoCuentaOrigenDolares() {
        return tipoCuentaOrigenDolares;
    }

    /**
     * Sets the tipo cuenta origen dolares.
     *
     * @param tipoCuentaOrigenDolares
     *            the tipoCuentaOrigenDolares to set
     */
    public void setTipoCuentaOrigenDolares(String tipoCuentaOrigenDolares) {
        this.tipoCuentaOrigenDolares = tipoCuentaOrigenDolares;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * DetalleComprobanteView#obtenerParametrosPDF()
     */
    @Override
    public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put(logoTituloKey, obtenerLogoTarjeta());
        parametros.put(importeKey, obtenerImporte());
        parametros.put(tarjetaKey, getDestinatario());
        parametros.put(fechaPagoTarjetaKey, getFecha());
        parametros.put(medioPagoTarjetaKey, getTransaccion());
        parametros.put(cuentaDebitoPesosKey, getNroCuentaOrigen());
        parametros.put(tipoCuentaDebitoPesosKey, getTipoCuentaOrigen());
        parametros.put(cuentaDebitoDolaresKey, getNroCuentaOrigenDolares());
        parametros.put(tipoCuentaDebitoDolaresKey, getTipoCuentaOrigenDolares());
        parametros.put(solicitudStopDebitKey, getNroStopDebit());
        parametros.put(numeroComprobanteKey, getNroComprobante());
        parametros.put(fechaActualKey, getFechaOperacion());
        return parametros;
    }

    /**
     * Obtener logo tarjeta.
     *
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private String obtenerLogoTarjeta() throws IOException {
        File logo;
        if ("7".equals(tipoTarjeta)) {
            logo = ResourceUtils.getFile(path + logoVisa);
        } else if ("42".equals(tipoTarjeta)) {
            logo = ResourceUtils.getFile(path + logoAmex);
        } else {
            logo = ResourceUtils.getFile(path + logoMaster);
        }
        return logo.getPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * DetalleComprobanteView#obtenerJasper()
     */
    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(path + pagoTarjetaJasper).getPath();
    }

}
