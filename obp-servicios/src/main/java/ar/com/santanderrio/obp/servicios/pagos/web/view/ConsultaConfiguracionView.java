/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;

/**
 * The Class ConsultaConfiguracionView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaConfiguracionView extends PuntoDeAccesoView {

    /** The fiid. */
    private String fiid;

    /** The monto. */
    private String monto;
    
    /** The numero celular. */
    @JsonSerialize(include = Inclusion.NON_NULL)
    private String numeroCelular;

    /**
	 * Instantiates a new consulta configuracion view.
	 */
    public ConsultaConfiguracionView() {
        //
    }

    /**
	 * Instantiates a new consulta configuracion view.
	 *
	 * @param medioPagoView
	 *            the medio pago view
	 */
    public ConsultaConfiguracionView(MedioPagoView medioPagoView) {
        this.fiid = medioPagoView.getFiid();
    }

    /**
     * Gets the fiid.
     *
     * @return the fiid
     */
    public String getFiid() {
        return fiid;
    }

    /**
     * Sets the fiid.
     *
     * @param fiid
     *            the new fiid
     */
    public void setFiid(String fiid) {
        this.fiid = fiid;
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
	 * Gets the numero celular.
	 *
	 * @return the numero celular
	 */
	public String getNumeroCelular() {
		return numeroCelular;
	}

	/**
	 * Sets the numero celular.
	 *
	 * @param numeroCelular the new numero celular
	 */
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
    
    

}
