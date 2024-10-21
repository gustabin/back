/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

/**
 * The Class DetalleComprobanteRecargaTarjetaView.
 */
public class DetalleComprobanteRecargaTarjetaView extends DetalleComprobanteView {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The comision total. */
    private String comisionTotal;

    /** The tarjeta. */
    private String tarjeta;

    /** The pmc vep. */
    private final String recargaTarjetaJasper = "recarga-tarjeta.jasper";

    /** The logo visa png. */
    private final String logoVisa = "logo-tarjeta-visa.png";

    /** The tarjeta key. */
    private final String tarjetaKey = "TARJETA";

    /** The tipo medio de pago key. */
    private final String tipoMedioDePagoKey = "TIPO_MEDIO_PAGO";

    /** The fecha recarga key. */
    private final String fechaRecargaKey = "FECHA_RECARGA";

    /** The comision key. */
    private final String comisionKey = "COMISION";

    /** The importe acreditado key. */
    private final String importeAcreditadoKey = "IMPORTE_ACREDITADO";

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
     * Gets the tarjeta.
     *
     * @return the tarjeta
     */
    public String getTarjeta() {
        return tarjeta;
    }

    /**
     * Sets the tarjeta.
     *
     * @param tarjeta
     *            the new tarjeta
     */
    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
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
        ponerParametrosTarjetaRecargable(parametros);
        return parametros;
    }

    /**
     * Poner parametros tarjeta recargable.
     *
     * @param parametros
     *            the parametros
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    protected void ponerParametrosTarjetaRecargable(HashMap<String, Object> parametros) throws IOException {
        parametros.put(logoTituloKey, ResourceUtils.getFile(path + logoVisa).getPath());
        parametros.put(importeKey, obtenerImporte());
        parametros.put(tarjetaKey, getTarjeta());
        parametros.put(medioPagoKey, getNroCuentaOrigen());
        parametros.put(tipoMedioDePagoKey, getTipoCuentaOrigen());
        parametros.put(fechaRecargaKey, getFecha());
        parametros.put(comisionKey, getComisionTotal());
        parametros.put(importeAcreditadoKey, getImporteAcreditado());
        parametros.put(numeroComprobanteKey, getNroComprobante());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
     * DetalleComprobanteView#obtenerJasper()
     */
    @Override
    public String obtenerJasper() throws FileNotFoundException {
        return ResourceUtils.getFile(path + recargaTarjetaJasper).getPath();
    }

}
