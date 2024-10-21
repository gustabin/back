/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class FondoCitiInEntity.
 */
public class FondoCitiInEntity {

    /** The Constant CERO_CUOTAS_PARTES. */
    private static final String CERO_CUOTAS_PARTES = "00000000000000";
    
    /** The Constant FORMAPAGO. */
    private static final String FORMAPAGO = "02";
    
    /** The Constant MONEDA. */
    private static final String MONEDA = "0";

    /** The cliente. */
    @NotNull
    private Cliente cliente;

    /** The codigo fondo. */
    @NotNull
    @Pattern(regexp = "[0-9]{5}", message = "{validation.debitoautomatico.importeLimite}")
    private String codigoFondo;

    /** The cuenta titulo. */
    @NotNull
    @Pattern(regexp = "[0-9]{11}")
    private String cuentaTitulo;
    
    /** The forma pago. */
    @NotNull
    @Pattern(regexp = "[0-9]{2}")
    private String formaPago = FORMAPAGO;
    
    /** The importe bruto. */
    @NotNull
    @Pattern(regexp = "[0-9]{14}")
    private String importeBruto;
    
    /** The cantidad cuotas partes. */
    @NotNull
    @Pattern(regexp = "[0-9]{14}")
    private String cantidadCuotasPartes = CERO_CUOTAS_PARTES;
    
    /** The moneda. */
    @NotNull
    @Pattern(regexp = "(0)")
    private String moneda = MONEDA;
    
    /** The tipo cuenta. */
    @NotNull
    @Pattern(regexp = "[0-9]{2}")
    private String tipoCuenta;
    
    /** The suc cuenta. */
    @NotNull
    @Pattern(regexp = "[0-9]{3}")
    private String sucCuenta;
    
    /** The nro cuenta. */
    @NotNull
    @Pattern(regexp = "[0-9]{10}")
    private String nroCuenta;

    /**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
    public Cliente getCliente() {
        return cliente;
    }

    /**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
    public String getCodigoFondo() {
        return codigoFondo;
    }

    /**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
    public void setCodigoFondo(String codigoFondo) {
        this.codigoFondo = codigoFondo;
    }

    /**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
    public String getCuentaTitulo() {
        return cuentaTitulo;
    }

    /**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
    public void setCuentaTitulo(String cuentaTitulo) {
        this.cuentaTitulo = cuentaTitulo;
    }

    /**
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
    public String getImporteBruto() {
        return importeBruto;
    }

    /**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
    public void setImporteBruto(String importeBruto) {
        this.importeBruto = importeBruto;
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
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
	 * Gets the suc cuenta.
	 *
	 * @return the suc cuenta
	 */
    public String getSucCuenta() {
        return sucCuenta;
    }

    /**
	 * Sets the suc cuenta.
	 *
	 * @param sucCuenta
	 *            the new suc cuenta
	 */
    public void setSucCuenta(String sucCuenta) {
        this.sucCuenta = sucCuenta;
    }

    /**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    /**
	 * Gets the forma pago.
	 *
	 * @return the forma pago
	 */
    public String getFormaPago() {
        return formaPago;
    }

    /**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the new forma pago
	 */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
	 * Gets the cantidad cuotas partes.
	 *
	 * @return the cantidad cuotas partes
	 */
    public String getCantidadCuotasPartes() {
        return cantidadCuotasPartes;
    }

    /**
	 * Sets the cantidad cuotas partes.
	 *
	 * @param cantidadCuotasPartes
	 *            the new cantidad cuotas partes
	 */
    public void setCantidadCuotasPartes(String cantidadCuotasPartes) {
        this.cantidadCuotasPartes = cantidadCuotasPartes;
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
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    
}
