/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteAltaBase;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.OperacionEstado;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ScomServicioNombreEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class AltaComprobanteRequestBuilder.
 */
public abstract class AltaComprobanteRequestBuilder {

    /** The cliente. */
    protected ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente cliente;

    /**
     * Builds the comprobante request.
     *
     * @return the alta comprobante request
     */
    public abstract AltaComprobanteRequest buildComprobanteRequest();

    /**
     * Instantiates a new alta comprobante request builder.
     *
     * @param cliente
     *            the cliente
     */
    public AltaComprobanteRequestBuilder(Cliente cliente) {
        setCliente(cliente);
    }

    /**
     * Sets the cliente.
     *
     * @param cliente
     *            the cliente
     * @return the alta comprobante request builder
     */
    private AltaComprobanteRequestBuilder setCliente(Cliente cliente) {
        ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente clienteScomp = new ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente();
        clienteScomp.setNup(cliente.getNup());
        clienteScomp.setTpoDoc(cliente.getTipoDocumento());
        clienteScomp.setNroDoc(obtenerDniSinCeros(cliente));
        this.cliente = clienteScomp;
        return this;
    }

    /**
     * Obtener alta base.
     *
     * @param tpoComp
     *            the tpo comp
     * @param subTpoComp
     *            the sub tpo comp
     * @return the alta comprobante request
     */
    protected AltaComprobanteRequest obtenerAltaBase(String tpoComp, String subTpoComp) {
        AltaComprobanteRequest request = new AltaComprobanteRequest();
        request.setVersion("200");
        request.setCanal("04");
        request.setSubcanal("99");
        request.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
        request.setTipoComprobante(tpoComp);
        request.setSubTipoComprobante(subTpoComp);
        return request;
    }

    /**
     * Obtener alta pago serv con TC.
     *
     * @param subTipoComprobante
     *            the sub tipo comprobante
     * @return the alta comprobante request
     */
    protected AltaComprobanteRequest obtenerAltaPagoServConTC(String subTipoComprobante) {
        AltaComprobanteRequest request = new AltaComprobanteRequest();
        request.setVersion("100");
        request.setCanal("04");
        request.setSubcanal("11");
        request.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
        request.setTipoComprobante("2");
        request.setSubTipoComprobante(subTipoComprobante);
        return request;
    }

    /**
     * Rellenar comprobante base.
     *
     * @param comprobante
     *            the comprobante
     * @param tpoComp
     *            the tpo comp
     * @param subTpoComp
     *            the sub tpo comp
     */
    protected void rellenarComprobanteBase(ComprobanteAltaBase comprobante, String tpoComp, String subTpoComp) {
        comprobante.setCanal("04");
        comprobante.setSubCanal("99");
        comprobante.setTpoComprobante(tpoComp);
        comprobante.setSubTpoComprobante(subTpoComp);
        comprobante.setEstadoOper(OperacionEstado.A);
        comprobante.setDescEstado("Aceptada");
        comprobante.setIdMedioPago("");
        comprobante.setDescMedioPago("");
        comprobante.setFechaGen(obtenerFechaActual());
        comprobante.setCliente(cliente);
    }

    /**
     * Rellenar comprobante pago serv con TC.
     *
     * @param comprobante
     *            the comprobante
     * @param subTpoComp
     *            the sub tpo comp
     */
    protected void rellenarComprobantePagoServConTC(ComprobanteAltaBase comprobante, String subTpoComp) {
        comprobante.setCanal("04");
        comprobante.setSubCanal("11");
        comprobante.setTpoComprobante("2");
        comprobante.setSubTpoComprobante(subTpoComp);
        comprobante.setEstadoOper(OperacionEstado.A);
        comprobante.setDescEstado("Aceptada");
        comprobante.setIdMedioPago("");
        comprobante.setDescMedioPago("");
        comprobante.setFechaGen(obtenerFechaActual());
        comprobante.setCliente(cliente);
    }

    /**
     * Obtener dni sin ceros.
     *
     * @param cliente
     *            the cliente
     * @return the string
     */
    private String obtenerDniSinCeros(Cliente cliente) {
        String dni = cliente.getDni();
        while (dni.startsWith("0")) {
            dni = StringUtils.removeStart(dni, "0");
        }
        return dni;
    }

    /**
     * Formatear importe PMC.
     *
     * @param importe
     *            the importe
     * @return the string
     */
    protected String formatearImportePMC(String importe) {
        if (!importe.contains(",")) {
            String importeSinEspacios = importe.trim();
            String entero = importeSinEspacios.substring(0, importeSinEspacios.length() - 2);
            String decimal = importeSinEspacios.substring(importeSinEspacios.length() - 2);
            return entero + "," + decimal;
        } else {
            return importe;
        }
    }

    /**
     * Formatear fecha.
     *
     * @param fechaOperacion
     *            the fecha operacion
     * @return the string
     */
    protected String formatearFecha(String fechaOperacion) {
        StringBuilder fecha = new StringBuilder();
        fecha.append(StringUtils.substring(fechaOperacion, 6, 10)).append("/");
        fecha.append(StringUtils.substring(fechaOperacion, 3, 5)).append("/");
        fecha.append(StringUtils.substring(fechaOperacion, 0, 2));
        fecha.append("T00:00:00Z");
        return fecha.toString();
    }

    /**
     * Formatear fecha.
     *
     * @param fechaOperacion
     *            the fecha operacion
     * @return the string
     */
    protected String formatearFechaPMC(String fechaOperacion) {
        StringBuilder fecha = new StringBuilder();
        fecha.append(StringUtils.substring(fechaOperacion, 0, 4)).append("-");
        fecha.append(StringUtils.substring(fechaOperacion, 4, 6)).append("-");
        fecha.append(StringUtils.substring(fechaOperacion, 6, 8));
        fecha.append("T00:00:00Z");
        return fecha.toString();
    }

    /**
     * Formatear hora.
     *
     * @param fechaOperacion
     *            the fecha operacion
     * @return the string
     */
    protected String formatearHora(String fechaOperacion) {
        StringBuilder hora = new StringBuilder();
        hora.append(StringUtils.right(fechaOperacion, 5));
        hora.append(":00");
        return hora.toString();
    }

    /**
     * Formatear hora.
     *
     * @param fechaOperacion
     *            the fecha operacion
     * @return the string
     */
    protected String formatearHoraConSegundos(String fechaOperacion) {
        StringBuilder hora = new StringBuilder();
        hora.append(StringUtils.substring(fechaOperacion, 0, 2)).append(":");
        hora.append(StringUtils.substring(fechaOperacion, 2, 4)).append(":");
        hora.append(StringUtils.substring(fechaOperacion, 4, 6));
        return hora.toString();
    }

    /**
     * Obtener fecha actual.
     *
     * @return the string
     */
    protected String obtenerFechaActual() {
        Calendar fecha = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(fecha.getTime()));
        sb.append("T00:00:00Z");
        return sb.toString();
    }

    /**
     * Obtener fecha actual.
     *
     * @return the string
     */
    protected String obtenerFechaActualConHora() {
        Calendar fecha = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(fecha.getTime()));
        sb.append("T");
        sb.append(sdf2.format(fecha.getTime()));
        sb.append("Z");
        return sb.toString();
    }

    /**
     * Obtener importe.
     *
     * @param transferenciaView
     *            the transferencia view
     * @return the string
     */
    protected String obtenerImporte(TransferenciaView transferenciaView) {
        String importe = transferenciaView.getImporte();
        if ("peso".equals(transferenciaView.getMoneda())) {
            importe = importe.substring(2);
        } else {
            importe = importe.substring(4);
        }
        return importe.replace(".", "").replace(",", ".");
    }

    /**
     * Obtener tipo cuenta.
     *
     * @param descripcionTipoCuenta
     *            the descripcion tipo cuenta
     * @param moneda
     *            the moneda
     * @return the string
     */
    protected String obtenerTipoCuenta(String descripcionTipoCuenta, String moneda) {
        TipoCuenta tipoCuentaEnum = TipoCuenta.fromDescripcionConMoneda(descripcionTipoCuenta);
        String codigoTipoCuenta = "";
        if (tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA) || tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_PESOS)
                || tipoCuentaEnum.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            if (StringUtils.equals(moneda, "peso")) {
                codigoTipoCuenta = TipoCuenta.CUENTA_UNICA_PESOS.getCodigo().toString();
            } else {
                codigoTipoCuenta = TipoCuenta.CUENTA_UNICA_DOLARES.getCodigo().toString();
            }
        } else {
            codigoTipoCuenta = tipoCuentaEnum.getCodigo().toString();
        }
        return StringUtils.leftPad(codigoTipoCuenta, 2, "0");
    }

    /**
     * Obtener numero cuenta sin sucursal.
     *
     * @param nroCuenta
     *            the nro cuenta
     * @return the string
     */
    protected String obtenerNumeroCuentaSinSucursal(String nroCuenta) {
        String nroCuentaSinSucursal = StringUtils.right(nroCuenta, 8);
        return StringUtils.remove(nroCuentaSinSucursal, "/");
    }

}
