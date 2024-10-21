/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class PagoComprasInDTO.
 *
 * @author florencia.n.martinez
 */
public class PagoComprasInDTO {

    /** The Constant ZERO_STRING. */
    private static final String ZERO_STRING = "0";

    /** The id empresa. */
    private String idEmpresa;

    /** The cod producto acuerdo empresa. */
    private String codProductoAcuerdoEmpresa;

    /** The nro instancia acuerdo empresa. */
    private String nroInstanciaAcuerdoEmpresa;

    /** The pid. */
    private String pid;

    /** The monto. */
    private String monto;

    /** The forma pago deuda. */
    private String formaPagoDeuda;

    /** The sucursal cuenta. */
    private String sucursalCuenta;

    /** The tipo cuenta. */
    private String tipoCuenta;

    /** The nro cuenta generico. */
    private String nroCuentaGenerico;

    /** The numero identificacion. */
    private String numeroIdentificacion;

    /**
     * Instantiates a new pago compras in DTO.
     */
    public PagoComprasInDTO() {
        super();
    }

    /**
     * Instantiates a new pago compras in DTO.
     *
     * @param cuenta
     *            the cuenta
     * @param medioPago
     *            the medio pago
     * @param pagoCompras
     *            the pago compras
     * @param pid
     *            the pid
     * @param divisa
     *            the divisa
     */
    public PagoComprasInDTO(Cuenta cuenta, MedioPago medioPago, NuevoPago pagoCompras, String pid, DivisaEnum divisa) {
        this.idEmpresa = medioPago.getPpdctaIdEmpAcuerdo();
        this.codProductoAcuerdoEmpresa = medioPago.getPpdctaCodProdAcuerdo();
        this.nroInstanciaAcuerdoEmpresa = medioPago.getPpdctaNroInstaCuerdo();
        this.pid = obtenerNumeroBoleta(pid);
        this.monto = StringUtils.leftPad(pagoCompras.obtenerMontoFormateado(pagoCompras.getMonto()), 14, ZERO_STRING);
        this.formaPagoDeuda = DivisaEnum.DOLAR.equals(divisa) ? "37" : "11";
        this.sucursalCuenta = StringUtils.right(cuenta.getNroSucursal(), 3);
        this.tipoCuenta = obtenerTipoCuenta(cuenta.getTipoCuentaEnum());
        this.nroCuentaGenerico = StringUtils.rightPad(cuenta.getNroCuentaProducto(), 12);
        this.numeroIdentificacion = StringUtils.leftPad(pagoCompras.getCodigoPagoElectronico(), 15, ZERO_STRING);
    }

    /**
     * Obtener numero boleta.
     *
     * @param pid
     *            the pid
     * @return the string
     */
    private String obtenerNumeroBoleta(String pid) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(Calendar.YEAR, 2003);
        cal.set(Calendar.MONTH, 05);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        cal.set(Calendar.HOUR_OF_DAY, 17);
        Long segs = cal.getTime().getTime() / 1000;
        return pid + (System.currentTimeMillis() / 1000 - segs);
    }

    /**
     * Obtener tipo cuenta.
     *
     * @param tipoCuentaEnum
     *            the tipo cuenta enum
     * @return the string
     */
    private String obtenerTipoCuenta(TipoCuenta tipoCuentaEnum) {
        switch (tipoCuentaEnum.getCodigo()) {
        case 0:
        case 1:
        case 3:
        case 2:
        case 4:
            return String.valueOf(tipoCuentaEnum.getCodigo());
        case 9:
            return "2";
        default:
            return "3";
        }
    }

    /**
     * Gets the id empresa.
     *
     * @return the idEmpresa
     */
    public String getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Sets the id empresa.
     *
     * @param idEmpresa
     *            the idEmpresa to set
     */
    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * Gets the cod producto acuerdo empresa.
     *
     * @return the codProductoAcuerdoEmpresa
     */
    public String getCodProductoAcuerdoEmpresa() {
        return codProductoAcuerdoEmpresa;
    }

    /**
     * Sets the cod producto acuerdo empresa.
     *
     * @param codProductoAcuerdoEmpresa
     *            the codProductoAcuerdoEmpresa to set
     */
    public void setCodProductoAcuerdoEmpresa(String codProductoAcuerdoEmpresa) {
        this.codProductoAcuerdoEmpresa = codProductoAcuerdoEmpresa;
    }

    /**
     * Gets the nro instancia acuerdo empresa.
     *
     * @return the nroInstanciaAcuerdoEmpresa
     */
    public String getNroInstanciaAcuerdoEmpresa() {
        return nroInstanciaAcuerdoEmpresa;
    }

    /**
     * Sets the nro instancia acuerdo empresa.
     *
     * @param nroInstanciaAcuerdoEmpresa
     *            the nroInstanciaAcuerdoEmpresa to set
     */
    public void setNroInstanciaAcuerdoEmpresa(String nroInstanciaAcuerdoEmpresa) {
        this.nroInstanciaAcuerdoEmpresa = nroInstanciaAcuerdoEmpresa;
    }

    /**
     * Gets the pid.
     *
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * Sets the pid.
     *
     * @param pid
     *            the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
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
     * Gets the forma pago deuda.
     *
     * @return the formaPagoDeuda
     */
    public String getFormaPagoDeuda() {
        return formaPagoDeuda;
    }

    /**
     * Sets the forma pago deuda.
     *
     * @param formaPagoDeuda
     *            the formaPagoDeuda to set
     */
    public void setFormaPagoDeuda(String formaPagoDeuda) {
        this.formaPagoDeuda = formaPagoDeuda;
    }

    /**
     * Gets the sucursal cuenta.
     *
     * @return the sucursalCuenta
     */
    public String getSucursalCuenta() {
        return sucursalCuenta;
    }

    /**
     * Sets the sucursal cuenta.
     *
     * @param sucursalCuenta
     *            the sucursalCuenta to set
     */
    public void setSucursalCuenta(String sucursalCuenta) {
        this.sucursalCuenta = sucursalCuenta;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipoCuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the tipo cuenta.
     *
     * @param tipoCuenta
     *            the tipoCuenta to set
     */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Gets the nro cuenta generico.
     *
     * @return the nroCuentaGenerico
     */
    public String getNroCuentaGenerico() {
        return nroCuentaGenerico;
    }

    /**
     * Sets the nro cuenta generico.
     *
     * @param nroCuentaGenerico
     *            the nroCuentaGenerico to set
     */
    public void setNroCuentaGenerico(String nroCuentaGenerico) {
        this.nroCuentaGenerico = nroCuentaGenerico;
    }

    /**
     * Gets the numero identificacion.
     *
     * @return the numeroIdentificacion
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Sets the numero identificacion.
     *
     * @param numeroIdentificacion
     *            the numeroIdentificacion to set
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codProductoAcuerdoEmpresa);
        hcb.append(formaPagoDeuda);
        hcb.append(idEmpresa);
        hcb.append(monto);
        hcb.append(nroCuentaGenerico);
        hcb.append(nroInstanciaAcuerdoEmpresa);
        hcb.append(numeroIdentificacion);
        hcb.append(pid);
        hcb.append(sucursalCuenta);
        hcb.append(tipoCuenta);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PagoComprasInDTO other = (PagoComprasInDTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codProductoAcuerdoEmpresa, other.getCodProductoAcuerdoEmpresa());
        eb.append(formaPagoDeuda, other.getFormaPagoDeuda());
        eb.append(idEmpresa, other.getIdEmpresa());
        eb.append(monto, other.getMonto());
        eb.append(nroCuentaGenerico, other.getNroCuentaGenerico());
        eb.append(nroInstanciaAcuerdoEmpresa, other.getNroInstanciaAcuerdoEmpresa());
        eb.append(numeroIdentificacion, other.getNumeroIdentificacion());
        eb.append(pid, other.getPid());
        eb.append(sucursalCuenta, other.getSucursalCuenta());
        eb.append(tipoCuenta, other.getTipoCuenta());
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
        return new ToStringBuilder(this).append("idEmpresa", idEmpresa)
                .append("codProductoAcuerdoEmpresa", codProductoAcuerdoEmpresa)
                .append("nroInstanciaAcuerdoEmpresa", nroInstanciaAcuerdoEmpresa).append("pid", pid)
                .append("monto", monto).append("formaPagoDeuda", formaPagoDeuda)
                .append("sucursalCuenta", sucursalCuenta).append("tipoCuenta", tipoCuenta)
                .append("nroCuentaGenerico", nroCuentaGenerico).append("numeroIdentificacion", numeroIdentificacion)
                .toString();
    }

}
