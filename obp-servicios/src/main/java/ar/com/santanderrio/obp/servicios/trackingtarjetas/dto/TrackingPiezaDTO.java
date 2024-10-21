/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.dto;


import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasPieza;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.utils.TrackingTarjetasUtils;

/**
 * The Class TrackingPiezaDTO.
 * @author Silvina_Luque
 */
public class TrackingPiezaDTO {
    
    /** The Constant TIPOCTA_VISA. */
    public static final String TIPOCTA_VISA = "07";

    /** The Constant TIPOCTA_AMEX. */
    public static final String TIPOCTA_AMEX = "42";
    
    /** The tipo cuenta enum. */
    private TipoCuenta tipoCuentaEnum;
    
    /** The marca tarjeta. */
    private String marcaTarjeta;
    
    /** The numero tarjeta componente. */
    private String numeroTarjetaComponente;
    
    /** The estado. */
    private String estado;

    /** The id estado. */
    private String idEstado;
    
    /** The numero tarjeta mascara. */
    private String numeroTarjetaMascara;
    
    /** The sucursal. */
    private String sucursal;
    
    /** The fecha. */
    private String fecha;

    /** The status. */
    private String statusPieza;
    
    /** The eventoSucursal. */
    private String eventoSucursal;
    
    
	/**
     * Instantiates a new tracking pieza DTO.
     *
     * @param pieza the pieza
     */
    public TrackingPiezaDTO(TrackingTarjetasPieza pieza) {
        this.statusPieza = pieza.getStatusPieza();
        this.tipoCuentaEnum = TipoCuenta.fromCodigo(pieza.getTipoCuenta());
        this.marcaTarjeta = this.tipoCuentaEnum.getDescripcion();
        this.numeroTarjetaComponente = pieza.getNroIdComponente(); 
        this.numeroTarjetaMascara = TrackingTarjetasUtils.crearMascaraNroTarjeta(this.numeroTarjetaComponente);
        if (pieza.getEvento() != null) {
            this.estado = pieza.getEvento().getEstado(); 
            this.idEstado = pieza.getEvento().getIdEstado();
            this.eventoSucursal = pieza.getEvento().getSucursal() != null ?  pieza.getEvento().getSucursal().trim() : "";
        } else {
            this.idEstado = pieza.getCodEstadoActual(); 
        }
        this.sucursal = StringUtils.leftPad(pieza.getSucUbic(), 4, "0");
        this.fecha = pieza.getFecCodEstadoActual();
    }
    

    
    /**
     * Gets the tipo cuenta enum.
     *
     * @return the tipo cuenta enum
     */
    public TipoCuenta getTipoCuentaEnum() {
        return tipoCuentaEnum;
    }

    /**
     * Sets the tipo cuenta enum.
     *
     * @param tipoCuentaEnum the new tipo cuenta enum
     */
    public void setTipoCuentaEnum(TipoCuenta tipoCuentaEnum) {
        this.tipoCuentaEnum = tipoCuentaEnum;
    }

    
    /**
	 * Gets the status pieza.
	 *
	 * @return the status pieza
	 */
    public String getStatusPieza() {
        return statusPieza;
    }

    /**
	 * Sets the status pieza.
	 *
	 * @param statusPieza
	 *            the new status pieza
	 */
    public void setStatusPieza(String statusPieza) {
        this.statusPieza = statusPieza;
    }

    /**
     * Gets the marca tarjeta.
     *
     * @return the marca tarjeta
     */
    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    /**
     * Sets the marca tarjeta.
     *
     * @param marcaTarjeta the new marca tarjeta
     */
    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    /**
     * Gets the numero tarjeta componente.
     *
     * @return the numero tarjeta componente
     */
    public String getNumeroTarjetaComponente() {
        return numeroTarjetaComponente;
    }

    /**
     * Sets the numero tarjeta componente.
     *
     * @param numeroTarjetaComponente the new numero tarjeta componente
     */
    public void setNumeroTarjetaComponente(String numeroTarjetaComponente) {
        this.numeroTarjetaComponente = numeroTarjetaComponente;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the id estado.
     *
     * @return the id estado
     */
    public String getIdEstado() {
        return idEstado;
    }

    /**
     * Sets the id estado.
     *
     * @param idEstado the new id estado
     */
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }
 	/**
     * Gets the numero tarjeta mascara.
     *
     * @return the numero tarjeta mascara
     */
    public String getNumeroTarjetaMascara() {
        return numeroTarjetaMascara;
    }

	/**
     * Sets the numero tarjeta mascara.
     *
     * @param numeroTarjetaMascara the new numero tarjeta mascara
     */
    public void setNumeroTarjetaMascara(String numeroTarjetaMascara) {
        this.numeroTarjetaMascara = numeroTarjetaMascara;
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
     * Sets the sucursal.
     *
     * @param sucursal the new sucursal
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha the new fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    /**
	 * Gets the evento sucursal.
	 *
	 * @return the evento sucursal
	 */
    public String getEventoSucursal() {
        return eventoSucursal;
    }


    /**
	 * Sets the evento sucursal.
	 *
	 * @param eventoSucursal
	 *            the new evento sucursal
	 */
    public void setEventoSucursal(String eventoSucursal) {
        this.eventoSucursal = eventoSucursal;
    }


    
    
    
}
