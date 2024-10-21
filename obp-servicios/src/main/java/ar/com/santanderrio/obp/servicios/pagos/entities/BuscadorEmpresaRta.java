/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase es simplemente un wrapper de la lista de empresas para poder
 * agregar un nuevo atributo que es el mensaje en caso de error el cual se tiene
 * que levantar de la bd.
 * 
 * @author marcelo.ruiz
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BuscadorEmpresaRta implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1001280427381274550L;

    /** The empresas. */
    private List<MedioPagoView> empresas = new ArrayList<MedioPagoView>();

    /** The mensaje error. */
    private String mensajeError;

    /**
     * Mensaje para UI de que una empresa no permite pago automatico. se le
     * establece un valor por defecto
     */
    private String mensajeNoPermitePago = "Esta empresa no permite el pago automatico. Si queres podes adherir el pago de tu factura por debito automatico en cuenta";

    /**
     * Mensaje para UI del importe limite de un debito automatico. se le establece
     * un valor por defecto
     */
    private String mensajeImporteLimite = "Deja el importe en 0 para abonar el total de cualquier factura de esta empresa";

    /** The pago compras ayuda. */
    private String pagoComprasAyuda;
    
    private String mensajeInformacionPagoAdebitar;

    /**
     * Gets the empresas.
     *
     * @return the empresas
     */
    public List<MedioPagoView> getEmpresas() {
        return empresas;
    }

    /**
     * Sets the empresas.
     *
     * @param empresas
     *            the new empresas
     */
    public void setEmpresas(List<MedioPagoView> empresas) {
        this.empresas = empresas;
    }

    /**
     * Gets the mensaje error.
     *
     * @return the mensaje error
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Sets the mensaje error.
     *
     * @param mensajeError
     *            the new mensaje error
     */
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    /**
     * Gets the mensaje no permite pago.
     *
     * @return the mensaje no permite pago
     */
    public String getMensajeNoPermitePago() {
        return mensajeNoPermitePago;
    }

    /**
     * Sets the mensaje no permite pago.
     *
     * @param mensajeNoPermitePago
     *            the new mensaje no permite pago
     */
    public void setMensajeNoPermitePago(String mensajeNoPermitePago) {
        this.mensajeNoPermitePago = mensajeNoPermitePago;
    }

    /**
     * Gets the mensaje importe limite.
     *
     * @return the mensaje importe limite
     */
    public String getMensajeImporteLimite() {
        return mensajeImporteLimite;
    }

    /**
     * Sets the mensaje importe limite.
     *
     * @param mensajeImporteLimite
     *            the new mensaje importe limite
     */
    public void setMensajeImporteLimite(String mensajeImporteLimite) {
        this.mensajeImporteLimite = mensajeImporteLimite;
    }

    /**
	 * Gets the pago compras ayuda.
	 *
	 * @return the pago compras ayuda
	 */
    public String getPagoComprasAyuda() {
        return pagoComprasAyuda;
    }

    /**
	 * Sets the pago compras ayuda.
	 *
	 * @param pagoComprasAyuda
	 *            the new pago compras ayuda
	 */
    public void setPagoComprasAyuda(String pagoComprasAyuda) {
        this.pagoComprasAyuda = pagoComprasAyuda;
    }

	public String getMensajeInformacionPagoAdebitar() {
		return mensajeInformacionPagoAdebitar;
	}

	public void setMensajeInformacionPagoAdebitar(String mensajeInformacionPagoAdebitar) {
		this.mensajeInformacionPagoAdebitar = mensajeInformacionPagoAdebitar;
	}
	
}
