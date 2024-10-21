/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class PagoCompraInEntity.
 *
 * @author florencia.n.martinez
 */
public class PagoCompraConDeudaInEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant ZERO_STRING. */
    private static final String ZERO_STRING = "0";

    /** The nro cuit empresa. */
    private String nroCuitEmpresa;

    /** The nro dig empresa. */
    private String nroDigEmpresa;

    /** The codigo prod ac empresa. */
    private String codigoProdAcEmpresa;

    /** The nro instancia ac empresa. */
    private String nroInstanciaAcEmpresa;

    /** The id cliente terceros. */
    private String idClienteTerceros;

    /** The cuit cliente terceros. */
    private String cuitClienteTerceros;

    /** The indicador excepcion comision. */
    private String indicadorExcepcionComision;

    /** The indicador cliente propio. */
    private String indicadorClientePropio;

    /** The monto. */
    private String monto;

    /** The condicion iva. */
    private String condicionIva;

    /** The tipo ingresos brutos. */
    private String tipoIngresosBrutos;

    private List<InstrumentoPagoEntity> instrumentosPago = new ArrayList<InstrumentoPagoEntity>();

    private List<PagoCompraDeudaInEntity> deudasPago = new ArrayList<PagoCompraDeudaInEntity>();

    /**
     * Instantiates a new pago compra in entity.
     *
     * @param pagoCompras
     *            the pago compras
     * @param medioPago
     *            the medio pago
     * @param deudas
     *            the deudas
     * @param divisa
     *            the divisa
     * @param cuenta
     *            the cuenta
     */
    public PagoCompraConDeudaInEntity(NuevoPago pagoCompras, MedioPago medioPago, DeudasPagoComprasEntity deudas,
            DivisaEnum divisa, Cuenta cuenta) {
        setEmpresa(medioPago);
        setDatosComunesDeudas(deudas);

        this.monto = obtenerMontoFormateado(pagoCompras.getMonto());
        DeudaPagoComprasEntity deuda = deudas.obtenerDeudaPorId(pagoCompras.getFacturaNumero());
        this.instrumentosPago.add(new InstrumentoPagoEntity(this.monto, divisa, cuenta.getCbu()));
        this.deudasPago.add(new PagoCompraDeudaInEntity(deuda, this.monto));
    }

    /**
     * Instantiates a new pago compra con deuda in entity.
     */
    public PagoCompraConDeudaInEntity() {
        super();
    }

    public PagoCompraConDeudaInEntity(Cliente cliente, PagoComprasMultiple pagoCompras, MedioPago medioPago,
            DeudasPagoComprasEntity deudas, DivisaEnum divisa) {
        setEmpresa(medioPago);
        setDatosComunesDeudas(deudas);
        BigDecimal montoTotal = BigDecimal.ZERO;
        
        Map<String, BigDecimal> montosCuentas = new HashMap<String, BigDecimal>();
        
        for (DeudaPagoComprasView deudaView : pagoCompras.getDeudas()) {
            DeudaPagoComprasEntity deuda = deudas.obtenerDeudaPorId(deudaView.getNumeroFactura());
            String monto = obtenerMontoFormateado(deudaView.getMontoSinFormatear());
            montoTotal = montoTotal.add(deudaView.getMontoSinFormatear());

            this.deudasPago.add(new PagoCompraDeudaInEntity(deuda, monto));
    
            String cbu = deudaView.getCuentaSeleccionada().getCbu();
            BigDecimal montoCuenta = deudaView.getMontoSinFormatear();
            if (montosCuentas.containsKey(cbu)) {
                montoCuenta = montoCuenta.add(montosCuentas.get(cbu));
            }
            montosCuentas.put(cbu, montoCuenta);
        }
        for (Map.Entry<String, BigDecimal> cuentaMonto : montosCuentas.entrySet()) {
            this.instrumentosPago.add(new InstrumentoPagoEntity(obtenerMontoFormateado(cuentaMonto.getValue()), divisa,
                    cuentaMonto.getKey()));
        }

        this.monto = obtenerMontoFormateado(montoTotal.toString());
    }
    

    private void setEmpresa(MedioPago medioPago) {
        this.nroCuitEmpresa = StringUtils.substring(medioPago.getPpdctaIdEmpAcuerdo(), 0, 11);
        this.nroDigEmpresa = StringUtils.substring(medioPago.getPpdctaIdEmpAcuerdo(), 11, 12);
        this.codigoProdAcEmpresa = medioPago.getPpdctaCodProdAcuerdo();
        this.nroInstanciaAcEmpresa = medioPago.getPpdctaNroInstaCuerdo();
    }

    private void setDatosComunesDeudas(DeudasPagoComprasEntity deudas) {
        this.idClienteTerceros = StringUtils.leftPad(deudas.getIdClienteTerceros(), 15, ZERO_STRING);
        this.cuitClienteTerceros = deudas.getCuitClienteTerceros();
        this.indicadorExcepcionComision = deudas.getIndicadorExcepcionComision();
        this.indicadorClientePropio = deudas.getIndicadorClientePropio();

        this.condicionIva = deudas.getCondicionIva();
        this.tipoIngresosBrutos = deudas.getTipoIngresosBrutos();
    }

    /**
     * Gets the nro cuit empresa.
     *
     * @return the nroCuitEmpresa
     */
    public String getNroCuitEmpresa() {
        return nroCuitEmpresa;
    }

    /**
     * Sets the nro cuit empresa.
     *
     * @param nroCuitEmpresa
     *            the nroCuitEmpresa to set
     */
    public void setNroCuitEmpresa(String nroCuitEmpresa) {
        this.nroCuitEmpresa = nroCuitEmpresa;
    }

    /**
     * Gets the nro dig empresa.
     *
     * @return the nroDigEmpresa
     */
    public String getNroDigEmpresa() {
        return nroDigEmpresa;
    }

    /**
     * Sets the nro dig empresa.
     *
     * @param nroDigEmpresa
     *            the nroDigEmpresa to set
     */
    public void setNroDigEmpresa(String nroDigEmpresa) {
        this.nroDigEmpresa = nroDigEmpresa;
    }

    /**
     * Gets the codigo prod ac empresa.
     *
     * @return the codigoProdAcEmpresa
     */
    public String getCodigoProdAcEmpresa() {
        return codigoProdAcEmpresa;
    }

    /**
     * Sets the codigo prod ac empresa.
     *
     * @param codigoProdAcEmpresa
     *            the codigoProdAcEmpresa to set
     */
    public void setCodigoProdAcEmpresa(String codigoProdAcEmpresa) {
        this.codigoProdAcEmpresa = codigoProdAcEmpresa;
    }

    /**
     * Gets the nro instancia ac empresa.
     *
     * @return the nroInstanciaAcEmpresa
     */
    public String getNroInstanciaAcEmpresa() {
        return nroInstanciaAcEmpresa;
    }

    /**
     * Sets the nro instancia ac empresa.
     *
     * @param nroInstanciaAcEmpresa
     *            the nroInstanciaAcEmpresa to set
     */
    public void setNroInstanciaAcEmpresa(String nroInstanciaAcEmpresa) {
        this.nroInstanciaAcEmpresa = nroInstanciaAcEmpresa;
    }

    /**
     * Gets the id cliente terceros.
     *
     * @return the idClienteTerceros
     */
    public String getIdClienteTerceros() {
        return idClienteTerceros;
    }

    /**
     * Sets the id cliente terceros.
     *
     * @param idClienteTerceros
     *            the idClienteTerceros to set
     */
    public void setIdClienteTerceros(String idClienteTerceros) {
        this.idClienteTerceros = idClienteTerceros;
    }

    /**
     * Gets the cuit cliente terceros.
     *
     * @return the cuitClienteTerceros
     */
    public String getCuitClienteTerceros() {
        return cuitClienteTerceros;
    }

    /**
     * Sets the cuit cliente terceros.
     *
     * @param cuitClienteTerceros
     *            the cuitClienteTerceros to set
     */
    public void setCuitClienteTerceros(String cuitClienteTerceros) {
        this.cuitClienteTerceros = cuitClienteTerceros;
    }

    /**
     * Gets the indicador excepcion comision.
     *
     * @return the indicadorExcepcionComision
     */
    public String getIndicadorExcepcionComision() {
        return indicadorExcepcionComision;
    }

    /**
     * Sets the indicador excepcion comision.
     *
     * @param indicadorExcepcionComision
     *            the indicadorExcepcionComision to set
     */
    public void setIndicadorExcepcionComision(String indicadorExcepcionComision) {
        this.indicadorExcepcionComision = indicadorExcepcionComision;
    }

    /**
     * Gets the indicador cliente propio.
     *
     * @return the indicadorClientePropio
     */
    public String getIndicadorClientePropio() {
        return indicadorClientePropio;
    }

    /**
     * Sets the indicador cliente propio.
     *
     * @param indicadorClientePropio
     *            the indicadorClientePropio to set
     */
    public void setIndicadorClientePropio(String indicadorClientePropio) {
        this.indicadorClientePropio = indicadorClientePropio;
    }

    /**
     * Gets the monto.
     *
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Sets the monto.
     *
     * @param monto
     *            the monto to set
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Gets the condicion iva.
     *
     * @return the condicionIva
     */
    public String getCondicionIva() {
        return condicionIva;
    }

    /**
     * Sets the condicion iva.
     *
     * @param condicionIva
     *            the condicionIva to set
     */
    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    /**
     * Gets the tipo ingresos brutos.
     *
     * @return the tipoIngresosBrutos
     */
    public String getTipoIngresosBrutos() {
        return tipoIngresosBrutos;
    }

    /**
     * Sets the tipo ingresos brutos.
     *
     * @param tipoIngresosBrutos
     *            the tipoIngresosBrutos to set
     */
    public void setTipoIngresosBrutos(String tipoIngresosBrutos) {
        this.tipoIngresosBrutos = tipoIngresosBrutos;
    }

    public List<InstrumentoPagoEntity> getInstrumentosPago() {
        return instrumentosPago;
    }

    public void setInstrumentosPago(List<InstrumentoPagoEntity> instrumentosPago) {
        this.instrumentosPago = instrumentosPago;
    }

    public List<PagoCompraDeudaInEntity> getDeudasPago() {
        return deudasPago;
    }

    public void setDeudasPago(List<PagoCompraDeudaInEntity> deudasPago) {
        this.deudasPago = deudasPago;
    }

    public String cantidadInstrumentosPago() {
        return StringUtils.leftPad(String.valueOf(this.instrumentosPago.size()), 2, ZERO_STRING);
    }
    
    public String cantidadDeudasPago() {
        return StringUtils.leftPad(String.valueOf(this.deudasPago.size()), 2, ZERO_STRING);
    }
    
    private String obtenerMontoFormateado(BigDecimal monto) {
        return obtenerMontoFormateado(monto.toString());
    }
    
    private String obtenerMontoFormateado(String monto) {
        if (monto.contains(".")) {
            int posPunto = monto.indexOf('.');
            int agregarCeros = monto.length() - posPunto;

            monto = monto.replace(".", "");
            if (agregarCeros == 2) {
                monto += "0";
            }

        } else {
            monto += "00";
        }

        return StringUtils.leftPad(monto, 14, ZERO_STRING);
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codigoProdAcEmpresa);
        hcb.append(cuitClienteTerceros);
        hcb.append(idClienteTerceros);
        hcb.append(indicadorClientePropio);
        hcb.append(indicadorExcepcionComision);
        hcb.append(monto);
        hcb.append(nroCuitEmpresa);
        hcb.append(nroDigEmpresa);
        hcb.append(nroInstanciaAcEmpresa);
        hcb.append(tipoIngresosBrutos);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PagoCompraConDeudaInEntity other = (PagoCompraConDeudaInEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codigoProdAcEmpresa, other.getCodigoProdAcEmpresa());
        eb.append(cuitClienteTerceros, other.getCuitClienteTerceros());
        eb.append(idClienteTerceros, other.getIdClienteTerceros());
        eb.append(indicadorClientePropio, other.getIndicadorClientePropio());
        eb.append(indicadorExcepcionComision, other.getIndicadorExcepcionComision());
        eb.append(monto, other.getMonto());
        eb.append(nroCuitEmpresa, other.getNroCuitEmpresa());
        eb.append(nroDigEmpresa, other.getNroDigEmpresa());
        eb.append(nroInstanciaAcEmpresa, other.getNroInstanciaAcEmpresa());
        eb.append(tipoIngresosBrutos, other.getTipoIngresosBrutos());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("nroCuitEmpresa", nroCuitEmpresa).append("nroDigEmpresa", nroDigEmpresa)
                .append("codigoProdAcEmpresa", codigoProdAcEmpresa)
                .append("nroInstanciaAcEmpresa", nroInstanciaAcEmpresa).append("idClienteTerceros", idClienteTerceros)
                .append("cuitClienteTerceros", cuitClienteTerceros)
                .append("indicadorExcepcionComision", indicadorExcepcionComision)
                .append("indicadorClientePropio", indicadorClientePropio).append("monto", monto)
                .append("condicionIva", condicionIva).append("tipoIngresosBrutos", tipoIngresosBrutos).toString();
    }

}
