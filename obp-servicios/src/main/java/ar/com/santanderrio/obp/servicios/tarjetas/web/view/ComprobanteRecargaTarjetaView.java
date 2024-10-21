/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ComprobanteRecargaTarjetaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComprobanteRecargaTarjetaView extends RsaDTOParaDesafio {
    /**
     * 
     */
    private static final long serialVersionUID = -3014051819368216308L;

    /** The cuenta origen. */
    private Cuenta cuentaOrigen;

    /** The amount. */
    private Long amount;

    /** The importe recarga pesos. */
    private String importeRecargaPesos;

    /** The sucursal cuenta destino. */
    private String sucursalCuentaDestino;

    /** The tipo cuenta destino. */
    private String tipoCuentaDestino;

    /** The nro cuenta destino. */
    private String nroCuentaDestino;

    /** The sucursal cuenta origen. */
    private String sucursalCuentaOrigen;

    /** The abreviatura tipo cuenta. */
    private String abreviaturaTipoCuenta;

    /** The divisa. */
    private String divisa;

    /** The fecha de operacion. */
    private String fechaDeOperacion;

    /** The tipo comprobante recarga. */
    private TipoComprobanteRecarga tipoComprobanteRecarga;

    /** The tarjeta recargable. */
    private String tarjetaRecargable;

    /** The importe recargado. */
    private String importeRecargado;

    /** The nro cuenta origen. */
    private String nroCuentaOrigen;

    /** The tipo cuenta origen. */
    private String tipoCuentaOrigen;

    /** The fecha de recarga. */
    private String fechaDeRecarga;

    /** The comision total IVA incluido. */
    private String comisionTotalIVAIncluido;

    /** The comision. */
    private String comision;

    /** The iva. */
    private String iva;

    /** The importe acreditado. */
    private String importeAcreditado;

    /** The nro de comprobante. */
    private String nroDeComprobante;

    /** The monto. */
    private String monto;

    /** The fecha hora. */
    private String fechaHora;

    /** The mensaje. */
    private String mensaje;

    /** The legales SEO. */
    private String legalesSEO;

    /** The ceros. */
    private String ceros;

    /** The saldo disponible. */
    private String saldoDisponible;

    /** The comision total. */
    private String comisionTotal;

    /** The saldo final. */
    private String saldoFinal;

    /** The nro comprobante. */
    private String nroComprobante;

    /** The codigo retorno. */
    private String codigoRetorno;

    /** The id sistema. */
    private String idSistema;

    /** The cant desc status resultado. */
    private String cantDescStatusResultado;

    /** The desc status resultado. */
    private String descStatusResultado;

    /** The time stamp. */
    private String timeStamp;

    /** The fecha inicio. */
    private String fechaInicio;

    /** The fecha prox recarga. */
    private String fechaProxRecarga;

    /** The frecuencia. */
    private String frecuencia;

    /**
     * The Enum TipoComprobanteRecarga.
     */
    public enum TipoComprobanteRecarga {
        /** The recargar. */
        RECARGAR,
        /** The agendar. */
        AGENDAR,
        /** The modificar agendar. */
        MODIFICAR_AGENDAR,
        /** The stop debit. */
        STOP_DEBIT
    }

    /**
     * Instantiates a new comprobante recarga tarjeta view.
     */
    public ComprobanteRecargaTarjetaView() {
        super(OperacionesRSAEnum.RECARGA_TARJETA);

    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount
     *            the new amount
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * Gets the cuenta origen.
     *
     * @return the cuenta origen
     */
    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * Sets the cuenta origen.
     *
     * @param cuentaOrigen
     *            the new cuenta origen
     */
    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    /**
     * Gets the fecha de operacion.
     *
     * @return the fecha de operacion
     */
    public String getFechaDeOperacion() {
        return fechaDeOperacion;
    }

    /**
     * Sets the fecha de operacion.
     *
     * @param fechaDeOperacion
     *            the new fecha de operacion
     */
    public void setFechaDeOperacion(String fechaDeOperacion) {
        this.fechaDeOperacion = fechaDeOperacion;
    }

    /**
     * Gets the saldo cuenta origen.
     *
     * @return the saldo cuenta origen
     */
    public String getSaldoCuentaOrigen() {
        return saldoCuentaOrigen;
    }

    /**
     * Sets the saldo cuenta origen.
     *
     * @param saldoCuentaOrigen
     *            the new saldo cuenta origen
     */
    public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
        this.saldoCuentaOrigen = saldoCuentaOrigen;
    }

    /**
     * Gets the numero tarjeta destino.
     *
     * @return the numero tarjeta destino
     */
    public String getNumeroTarjetaDestino() {
        return numeroTarjetaDestino;
    }

    /**
     * Sets the numero tarjeta destino.
     *
     * @param numeroTarjetaDestino
     *            the new numero tarjeta destino
     */
    public void setNumeroTarjetaDestino(String numeroTarjetaDestino) {
        this.numeroTarjetaDestino = numeroTarjetaDestino;
    }

    /**
     * Gets the divisa.
     *
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /** The saldo cuenta origen. */
    private String saldoCuentaOrigen;

    /** The numero tarjeta destino. */
    private String numeroTarjetaDestino;

    /**
     * Gets the importe recarga pesos.
     *
     * @return the importe recarga pesos
     */
    public String getImporteRecargaPesos() {
        return importeRecargaPesos;
    }

    /**
     * Sets the importe recarga pesos.
     *
     * @param importeRecargaPesos
     *            the new importe recarga pesos
     */
    public void setImporteRecargaPesos(String importeRecargaPesos) {
        this.importeRecargaPesos = importeRecargaPesos;
    }

    /**
     * Gets the sucursal cuenta destino.
     *
     * @return the sucursal cuenta destino
     */
    public String getSucursalCuentaDestino() {
        return sucursalCuentaDestino;
    }

    /**
     * Sets the sucursal cuenta destino.
     *
     * @param sucursalCuentaDestino
     *            the new sucursal cuenta destino
     */
    public void setSucursalCuentaDestino(String sucursalCuentaDestino) {
        this.sucursalCuentaDestino = sucursalCuentaDestino;
    }

    /**
     * Gets the tipo cuenta destino.
     *
     * @return the tipo cuenta destino
     */
    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    /**
     * Sets the tipo cuenta destino.
     *
     * @param tipoCuentaDestino
     *            the new tipo cuenta destino
     */
    public void setTipoCuentaDestino(String tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    /**
     * Gets the nro cuenta destino.
     *
     * @return the nro cuenta destino
     */
    public String getNroCuentaDestino() {
        return nroCuentaDestino;
    }

    /**
     * Sets the nro cuenta destino.
     *
     * @param nroCuentaDestino
     *            the new nro cuenta destino
     */
    public void setNroCuentaDestino(String nroCuentaDestino) {
        this.nroCuentaDestino = nroCuentaDestino;
    }

    /**
     * Gets the sucursal cuenta origen.
     *
     * @return the sucursal cuenta origen
     */
    public String getSucursalCuentaOrigen() {
        return sucursalCuentaOrigen;
    }

    /**
     * Sets the sucursal cuenta origen.
     *
     * @param sucursalCuentaOrigen
     *            the new sucursal cuenta origen
     */
    public void setSucursalCuentaOrigen(String sucursalCuentaOrigen) {
        this.sucursalCuentaOrigen = sucursalCuentaOrigen;
    }

    /**
     * Gets the abreviatura tipo cuenta.
     *
     * @return the abreviatura tipo cuenta
     */
    public String getAbreviaturaTipoCuenta() {
        return abreviaturaTipoCuenta;
    }

    /**
     * Sets the abreviatura tipo cuenta.
     *
     * @param abreviaturaTipoCuenta
     *            the new abreviatura tipo cuenta
     */
    public void setAbreviaturaTipoCuenta(String abreviaturaTipoCuenta) {
        this.abreviaturaTipoCuenta = abreviaturaTipoCuenta;
    }

    /**
     * Gets the nro tarjeta.
     *
     * @return the nro tarjeta
     */
    public String getNroTarjeta() {
        return this.getNumeroTarjetaDestino();
    }

    /**
     * Sets the nro tarjeta.
     *
     * @param nroTarjeta
     *            the new nro tarjeta
     */
    public void setNroTarjeta(String nroTarjeta) {
        this.numeroTarjetaDestino = nroTarjeta;
    }

    /**
     * Sets the divisa.
     *
     * @param divisa
     *            the new divisa
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    /**
     * Gets the tarjeta recargable.
     *
     * @return the tarjeta recargable
     */
    public String getTarjetaRecargable() {
        return tarjetaRecargable;
    }

    /**
     * Sets the tarjeta recargable.
     *
     * @param tarjetaRecargable
     *            the new tarjeta recargable
     */
    public void setTarjetaRecargable(String tarjetaRecargable) {
        this.tarjetaRecargable = tarjetaRecargable;
    }

    /**
     * Gets the importe recargado.
     *
     * @return the importe recargado
     */
    public String getImporteRecargado() {
        return importeRecargado;
    }

    /**
     * Sets the importe recargado.
     *
     * @param importeRecargado
     *            the new importe recargado
     */
    public void setImporteRecargado(String importeRecargado) {
        this.importeRecargado = importeRecargado;
    }

    /**
     * Gets the nro cuenta origen.
     *
     * @return the nro cuenta origen
     */
    public String getNroCuentaOrigen() {
        return nroCuentaOrigen;
    }

    /**
     * Sets the nro cuenta origen.
     *
     * @param nroCuentaOrigen
     *            the new nro cuenta origen
     */
    public void setNroCuentaOrigen(String nroCuentaOrigen) {
        this.nroCuentaOrigen = nroCuentaOrigen;
    }

    /**
     * Gets the tipo cuenta origen.
     *
     * @return the tipo cuenta origen
     */
    public String getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    /**
     * Sets the tipo cuenta origen.
     *
     * @param tipoCuentaOrigen
     *            the new tipo cuenta origen
     */
    public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
    }

    /**
     * Gets the fecha de recarga.
     *
     * @return the fecha de recarga
     */
    public String getFechaDeRecarga() {
        return fechaDeRecarga;
    }

    /**
     * Sets the fecha de recarga.
     *
     * @param fechaDeRecarga
     *            the new fecha de recarga
     */
    public void setFechaDeRecarga(String fechaDeRecarga) {
        this.fechaDeRecarga = fechaDeRecarga;
    }

    /**
     * Gets the comision total IVA incluido.
     *
     * @return the comision total IVA incluido
     */
    public String getComisionTotalIVAIncluido() {
        return comisionTotalIVAIncluido;
    }

    /**
     * Sets the comision total IVA incluido.
     *
     * @param comisionTotalIVAIncluido
     *            the new comision total IVA incluido
     */
    public void setComisionTotalIVAIncluido(String comisionTotalIVAIncluido) {
        this.comisionTotalIVAIncluido = comisionTotalIVAIncluido;
    }

    /**
     * Gets the comision.
     *
     * @return the comision
     */
    public String getComision() {
        return comision;
    }

    /**
     * Sets the comision.
     *
     * @param comision
     *            the new comision
     */
    public void setComision(String comision) {
        this.comision = comision;
    }

    /**
     * Gets the iva.
     *
     * @return the iva
     */
    public String getIva() {
        return iva;
    }

    /**
     * Sets the iva.
     *
     * @param iva
     *            the new iva
     */
    public void setIva(String iva) {
        this.iva = iva;
    }

    /**
     * Gets the importe acreditado.
     *
     * @return the importe acreditado
     */
    public String getImporteAcreditado() {
        return importeAcreditado;
    }

    /**
     * Sets the importe acreditado.
     *
     * @param importeAcreditado
     *            the new importe acreditado
     */
    public void setImporteAcreditado(String importeAcreditado) {
        this.importeAcreditado = importeAcreditado;
    }

    /**
     * Gets the nro de comprobante.
     *
     * @return the nro de comprobante
     */
    public String getNroDeComprobante() {
        return nroDeComprobante;
    }

    /**
     * Sets the nro de comprobante.
     *
     * @param nroDeComprobante
     *            the new nro de comprobante
     */
    public void setNroDeComprobante(String nroDeComprobante) {
        this.nroDeComprobante = nroDeComprobante;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fecha hora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the new fecha hora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Gets the ceros.
     *
     * @return the ceros
     */
    public String getCeros() {
        return ceros;
    }

    /**
     * Sets the ceros.
     *
     * @param ceros
     *            the new ceros
     */
    public void setCeros(String ceros) {
        this.ceros = ceros;
    }

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
     * Gets the codigo retorno.
     *
     * @return the codigo retorno
     */
    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Sets the codigo retorno.
     *
     * @param codigoRetorno
     *            the new codigo retorno
     */
    public void setCodigoRetorno(String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    /**
     * Gets the cant desc status resultado.
     *
     * @return the cant desc status resultado
     */
    public String getCantDescStatusResultado() {
        return cantDescStatusResultado;
    }

    /**
     * Sets the cant desc status resultado.
     *
     * @param cantDescStatusResultado
     *            the new cant desc status resultado
     */
    public void setCantDescStatusResultado(String cantDescStatusResultado) {
        this.cantDescStatusResultado = cantDescStatusResultado;
    }

    /**
     * Gets the desc status resultado.
     *
     * @return the desc status resultado
     */
    public String getDescStatusResultado() {
        return descStatusResultado;
    }

    /**
     * Sets the desc status resultado.
     *
     * @param descStatusResultado
     *            the new desc status resultado
     */
    public void setDescStatusResultado(String descStatusResultado) {
        this.descStatusResultado = descStatusResultado;
    }

    /**
     * Gets the fecha inicio.
     *
     * @return the fecha inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the fecha inicio.
     *
     * @param fechaInicio
     *            the new fecha inicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Gets the frecuencia.
     *
     * @return the frecuencia
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * Sets the frecuencia.
     *
     * @param frecuencia
     *            the new frecuencia
     */
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Gets the numero tarjeta.
     *
     * @return the numero tarjeta
     */
    public String getNumeroTarjeta() {
        return this.getNumeroTarjetaDestino();
    }

    /**
     * Sets the numero tarjeta.
     *
     * @param numeroTarjeta
     *            the new numero tarjeta
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjetaDestino = numeroTarjeta;
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
     *            the new monto
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * Gets the mensaje.
     *
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Sets the mensaje.
     *
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the legales SEO.
     *
     * @return the legales SEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * Sets the legales SEO.
     *
     * @param legalesSEO
     *            the new legales SEO
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }

    /**
     * Gets the saldo disponible.
     *
     * @return the saldo disponible
     */
    public String getSaldoDisponible() {
        return saldoDisponible;
    }

    /**
     * Sets the saldo disponible.
     *
     * @param saldoDisponible
     *            the new saldo disponible
     */
    public void setSaldoDisponible(String saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    /**
     * Gets the saldo final.
     *
     * @return the saldo final
     */
    public String getSaldoFinal() {
        return saldoFinal;
    }

    /**
     * Sets the saldo final.
     *
     * @param saldoFinal
     *            the new saldo final
     */
    public void setSaldoFinal(String saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    /**
     * Gets the nro comprobante.
     *
     * @return the nro comprobante
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the nro comprobante.
     *
     * @param nroComprobante
     *            the new nro comprobante
     */
    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    /**
     * Gets the id sistema.
     *
     * @return the id sistema
     */
    public String getIdSistema() {
        return idSistema;
    }

    /**
     * Sets the id sistema.
     *
     * @param idSistema
     *            the new id sistema
     */
    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    /**
     * Gets the time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time stamp.
     *
     * @param timeStamp
     *            the new time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the fecha prox recarga.
     *
     * @return the fecha prox recarga
     */
    public String getFechaProxRecarga() {
        return fechaProxRecarga;
    }

    /**
     * Sets the fecha prox recarga.
     *
     * @param fechaProxRecarga
     *            the new fecha prox recarga
     */
    public void setFechaProxRecarga(String fechaProxRecarga) {
        this.fechaProxRecarga = fechaProxRecarga;
    }

    /**
     * Gets the tipo comprobante recarga.
     *
     * @return the tipo comprobante recarga
     */
    public TipoComprobanteRecarga getTipoComprobanteRecarga() {
        return tipoComprobanteRecarga;
    }

    /**
     * Sets the tipo comprobante recarga.
     *
     * @param tipoComprobanteRecarga
     *            the new tipo comprobante recarga
     */
    public void setTipoComprobanteRecarga(TipoComprobanteRecarga tipoComprobanteRecarga) {
        this.tipoComprobanteRecarga = tipoComprobanteRecarga;
    }

}
