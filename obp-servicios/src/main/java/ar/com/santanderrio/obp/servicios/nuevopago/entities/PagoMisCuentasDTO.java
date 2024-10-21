/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;

/**
 * The Class PagoMisCuentasDTO.
 */
public class PagoMisCuentasDTO {
    /** The codigo empresa. */
    @Pattern(regexp = "^[a-zA-Z0-9 ]{4}$")
    private final String codigoEmpresa;

    /** The tipo empresa. */
    @Pattern(regexp = "[F ]")
    private final String tipoEmpresa;

    /** The tipo pago. */
    @Pattern(regexp = "^[123]{1}$")
    private final String tipoPago;

    /** The cuit cliente. */
    @Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
    private final String cuitCliente;

    /** The cuit empleador. */
    @Pattern(regexp = "[0-9]{2}-[0-9]{8}-[0-9]")
    private final String cuitEmpleador;

    /** variable reintentar. */
    private final String reintentar;

    /** The periodo pago. */
    @Pattern(regexp = "^[0-9]{6}$")
    private final String periodoPago;

    /** simbolo moneda. */
    private final String simboloMoneda;

    /** The identificacion cliente. */
    @Pattern(regexp = "^[0-9 ]{1,19}$")
    private final String identificacionCliente;

    /** The sucursal. */
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$")
    private final String sucursal;

    /** nombre de la empresa o servicio a pagar. */
    private final String descripcionServicioPago;

    /** The numero cuenta. */
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$")
    private final String numeroCuenta;

    /** The cuit empleador. */
    @Pattern(regexp = "[01][09]")
    private final String tipoCuenta;

    /** The monto. Desde la grilla. */
    @Pattern(regexp = "^[0-9]{1,20}$")
    private final String monto;

    /** variable como viene de FE. */
    private final String montoSinFormatear;

    /** The moneda. */
    @Pattern(regexp = "ARS|USD")
    private final String moneda;

    /** The tipo pago empresa. */
    @Pattern(regexp = "[123]{1}")
    private final String tipoPagoEmpresa;

    /** The numero factura. */
    @Pattern(regexp = "^[0-9 ]{1,20}$")
    private final String numeroFactura;

    /** nro Anticipo. */
    private final String numeroDeAnticipo;

    /** nro cuota. */
    private final String numeroDeCuota;

    /** The numero referencia pago. */
    private final String numeroReferenciaPago;

    /** The validacion importe. */
    private boolean validacionImporte;

    /** The isFromCalendario. */
    private Boolean isFromCalendario;

    /** The fecha vencimiento. */
    private String fechaVencimiento;

    /** El monto recuperado de backend *. */
    private String montoDeGrilla;

    /** The nro tarjeta. */
    private String nroTarjeta;

    /** The tipo cuenta TC. */
    private TipoCuenta tipoCuentaTC;
    
    /** The vencimiento tarjeta. */
    private String vencimientoTarjeta;

    /**
     * Gets the checks if is from calendario.
     *
     * @return the checks if is from calendario
     */
    public Boolean getIsFromCalendario() {
        return isFromCalendario;
    }

    /**
     * Instantiates a new pago mis cuentas DTO.
     *
     * @param pago
     *            the pago
     * @param cuenta
     *            the cuenta
     * @param medioPago
     *            the medio pago
     * @param validarImporte
     *            the validar importe
     */
    public PagoMisCuentasDTO(NuevoPago pago, Cuenta cuenta, MedioPago medioPago, boolean validarImporte) {
        this.codigoEmpresa = StringUtils.rightPad(String.valueOf(pago.getFiid()), 4, " ");
        this.cuitCliente = pago.getCodigoPagoElectronico();
        this.cuitEmpleador = pago.getCodigoPagoElectronico2();
        this.numeroDeAnticipo = pago.getNumeroDeAnticipo();
        this.numeroDeCuota = pago.getNumeroDeCuota();
        this.numeroReferenciaPago = pago.getNumeroReferenciaPago();
        this.monto = StringUtils.isNotBlank(pago.getMonto()) ? pago.obtenerMontoFormateado(pago.getMonto()) : null;
        this.montoSinFormatear = StringUtils.isNotBlank(pago.getMonto()) ? pago.getMonto() : null;
        this.numeroFactura = pago.getFacturaNumero();
        this.reintentar = pago.getReintentar();
        this.numeroCuenta = cuenta.getNroCuentaProducto().substring(4);
        this.sucursal = cuenta.getNroSucursal();
        this.identificacionCliente = pago.getCodigoPagoElectronico();
        this.moneda = StringUtils.isNotBlank(cuenta.getMonedaAltair()) ? cuenta.getMonedaAltair() : "ARS";
        this.periodoPago = setearPeriodoPago(pago);
        if ("02".equals(cuenta.getTipoCuenta())) {
            this.tipoCuenta = "09";
        } else {
            this.tipoCuenta = cuenta.getTipoCuenta();
        }
        this.setIsFromCalendario(pago.getIsFromCalendario());
        this.simboloMoneda = medioPago.getMoneda();
        this.tipoEmpresa = medioPago.getTipoEmpresa();
        this.tipoPagoEmpresa = medioPago.getTipoPago().toString();
        this.tipoPago = medioPago.getTipoPago().toString();
        this.descripcionServicioPago = medioPago.getNombreFantasia();
        this.validacionImporte = validarImporte;
        this.fechaVencimiento = pago.getFechaVencimiento();
        if (cuenta.esTarjetaDeCredito()) {
            this.nroTarjeta = cuenta.getNroTarjetaCredito();
            this.tipoCuentaTC = cuenta.getTipoCuentaEnum();
            this.vencimientoTarjeta = pago.getVencimientoTarjeta();
        }
    }

    /**
     * setear el periodo q se esta pagando.
     *
     * @param pago
     *            the pago
     * @return the string
     */
    private String setearPeriodoPago(NuevoPago pago) {
        String periodo = null;

        if (pago.getMes() != null && pago.getAnio() != null) {
            if (pago.getMes().length() == 1) {
                periodo = "0" + pago.getMes();
            } else {
                periodo = pago.getMes();
            }

            periodo = periodo + pago.getAnio();
        }
        return periodo;
    }

    /**
     * Gets the codigo empresa.
     *
     * @return the codigo empresa
     */
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * Gets the tipo empresa.
     *
     * @return the tipo empresa
     */
    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    /**
     * Gets the tipo pago.
     *
     * @return the tipo pago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Gets the cuit cliente.
     *
     * @return the cuit cliente
     */
    public String getCuitCliente() {
        return cuitCliente;
    }

    /**
     * Gets the cuit empleador.
     *
     * @return the cuit empleador
     */
    public String getCuitEmpleador() {
        return cuitEmpleador;
    }

    /**
     * Gets the reintentar.
     *
     * @return the reintentar
     */
    public String getReintentar() {
        return reintentar;
    }

    /**
     * Gets the periodo pago.
     *
     * @return the periodo pago
     */
    public String getPeriodoPago() {
        return periodoPago;
    }

    /**
     * Gets the simbolo moneda.
     *
     * @return the simbolo moneda
     */
    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    /**
     * Gets the identificacion cliente.
     *
     * @return the identificacion cliente
     */
    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    /**
     * Gets the sucursal.
     *
     * @return the sucursal
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Gets the descripcion servicio pago.
     *
     * @return the descripcion servicio pago
     */
    public String getDescripcionServicioPago() {
        return descripcionServicioPago;
    }

    /**
     * Gets the numero cuenta.
     *
     * @return the numero cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Gets the tipo cuenta.
     *
     * @return the tipo cuenta
     */
    public String getTipoCuenta() {
        return tipoCuenta;
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
     * Gets the monto sin formatear.
     *
     * @return the monto sin formatear
     */
    public String getMontoSinFormatear() {
        return montoSinFormatear;
    }

    /**
     * Gets the moneda.
     *
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Gets the tipo pago empresa.
     *
     * @return the tipo pago empresa
     */
    public String getTipoPagoEmpresa() {
        return tipoPagoEmpresa;
    }

    /**
     * Gets the numero factura.
     *
     * @return the numero factura
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Gets the numero de anticipo.
     *
     * @return the numero de anticipo
     */
    public String getNumeroDeAnticipo() {
        return numeroDeAnticipo;
    }

    /**
     * Gets the numero de cuota.
     *
     * @return the numero de cuota
     */
    public String getNumeroDeCuota() {
        return numeroDeCuota;
    }

    /**
     * Gets the numero referencia pago.
     *
     * @return the numero referencia pago
     */
    public String getNumeroReferenciaPago() {
        return numeroReferenciaPago;
    }

    /**
     * Checks if is validacion importe.
     *
     * @return true, if is validacion importe
     */
    public boolean isValidacionImporte() {
        return validacionImporte;
    }

    /**
     * Sets the validacion importe.
     *
     * @param validacionImporte
     *            the new validacion importe
     */
    public void setValidacionImporte(boolean validacionImporte) {
        this.validacionImporte = validacionImporte;
    }

    /**
     * Sets the checks if is from calendario.
     *
     * @param isFromCalendario
     *            the new checks if is from calendario
     */
    public void setIsFromCalendario(Boolean isFromCalendario) {
        this.isFromCalendario = isFromCalendario;
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

    /**
     * Gets the monto de grilla.
     *
     * @return the monto de grilla
     */
    public String getMontoDeGrilla() {
        return montoDeGrilla;
    }

    /**
     * Sets the monto de grilla.
     *
     * @param montoDeGrilla
     *            the new monto de grilla
     */
    public void setMontoDeGrilla(String montoDeGrilla) {
        this.montoDeGrilla = montoDeGrilla;
    }

    /**
     * Gets the nro tarjeta.
     *
     * @return the nroTarjeta
     */
    public String getNroTarjeta() {
        return nroTarjeta;
    }

    /**
     * Sets the nro tarjeta.
     *
     * @param nroTarjeta
     *            the nroTarjeta to set
     */
    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    /**
     * Gets the tipo cuenta TC.
     *
     * @return the tipoCuentaTC
     */
    public TipoCuenta getTipoCuentaTC() {
        return tipoCuentaTC;
    }

    /**
     * Sets the tipo cuenta TC.
     *
     * @param tipoCuentaTC
     *            the tipoCuentaTC to set
     */
    public void setTipoCuentaTC(TipoCuenta tipoCuentaTC) {
        this.tipoCuentaTC = tipoCuentaTC;
    }

    /**
	 * Gets the vencimiento tarjeta.
	 *
	 * @return the vencimiento tarjeta
	 */
    public String getVencimientoTarjeta() {
        return vencimientoTarjeta;
    }

    /**
	 * Sets the vencimiento tarjeta.
	 *
	 * @param vencimientoTarjeta
	 *            the new vencimiento tarjeta
	 */
    public void setVencimientoTarjeta(String vencimientoTarjeta) {
        this.vencimientoTarjeta = vencimientoTarjeta;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codigoEmpresa);
        hcb.append(cuitCliente);
        hcb.append(descripcionServicioPago);
        hcb.append(identificacionCliente);
        hcb.append(isFromCalendario);
        hcb.append(moneda);
        hcb.append(monto);
        hcb.append(numeroCuenta);
        hcb.append(reintentar);
        hcb.append(simboloMoneda);
        hcb.append(sucursal);
        hcb.append(tipoCuenta);
        hcb.append(tipoEmpresa);
        hcb.append(tipoPago);
        hcb.append(tipoPagoEmpresa);
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
        PagoMisCuentasDTO other = (PagoMisCuentasDTO) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codigoEmpresa, other.getCodigoEmpresa());
        eb.append(cuitCliente, other.getCuitCliente());
        eb.append(descripcionServicioPago, other.getDescripcionServicioPago());
        eb.append(identificacionCliente, other.getIdentificacionCliente());
        eb.append(isFromCalendario, other.getIsFromCalendario());
        eb.append(moneda, other.getMoneda());
        eb.append(monto, other.getMonto());
        eb.append(numeroCuenta, other.getNumeroCuenta());
        eb.append(reintentar, other.getReintentar());
        eb.append(simboloMoneda, other.getSimboloMoneda());
        eb.append(sucursal, other.getSucursal());
        eb.append(tipoCuenta, other.getTipoCuenta());
        eb.append(tipoEmpresa, other.getTipoEmpresa());
        eb.append(tipoPago, other.getTipoPago());
        eb.append(tipoPagoEmpresa, other.getTipoPagoEmpresa());
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
        return new ToStringBuilder(this).append("codigoEmpresa", codigoEmpresa).append("tipoEmpresa", tipoEmpresa)
                .append("tipoPago", tipoPago).append("cuitCliente", cuitCliente).append("cuitEmpleador", cuitEmpleador)
                .append("reintentar", reintentar).append("periodoPago", periodoPago)
                .append("simboloMoneda", simboloMoneda).append("identificacionCliente", identificacionCliente)
                .append("sucursal", sucursal).append("descripcionServicioPago", descripcionServicioPago)
                .append("numeroCuenta", numeroCuenta).append("tipoCuenta", tipoCuenta).append("monto", monto)
                .append("moneda", moneda).append("tipoPagoEmpresa", tipoPagoEmpresa)
                .append("numeroFactura", numeroFactura).append("numeroDeAnticipo", numeroDeAnticipo)
                .append("numeroDeCuota", numeroDeCuota).append("numeroReferenciaPago", numeroReferenciaPago)
                .append("validacionImporte", validacionImporte).append("isFromCalendario", isFromCalendario)
                .append("fechaVencimiento", fechaVencimiento).append("montoDeGrilla", montoDeGrilla)
                .append("nroTarjeta", nroTarjeta).append("tipoCuentaTC", tipoCuentaTC).toString();
    }

}
