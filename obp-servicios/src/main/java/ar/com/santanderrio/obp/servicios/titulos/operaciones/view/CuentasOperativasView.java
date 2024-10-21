/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.view;

import java.util.List;

/**
 * The Class CuentasOperativasView.
 */
public class CuentasOperativasView {

    /** The numero cuenta. */
    private String numeroCuenta;
    
    /** The intervinientes. */
    private List<IntervinienteView> intervinientes;
    
    /**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    /**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    /**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
    public List<IntervinienteView> getIntervinientes() {
        return intervinientes;
    }
    
    /**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
    public void setIntervinientes(List<IntervinienteView> intervinientes) {
        this.intervinientes = intervinientes;
    }
}
