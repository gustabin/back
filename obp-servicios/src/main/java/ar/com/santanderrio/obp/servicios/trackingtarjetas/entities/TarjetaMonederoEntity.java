/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * TarjetaMonederoEntity.
 *
 * @author Silvina_Luque
 */

@Record
public class TarjetaMonederoEntity {
    
    /** Sucursal. */
    @Field
    private String sucursal;
    
    /** tipoCuenta. */
    @Field
    private String tipoCuenta;
    
    /** numeroCuenta. */
    @Field
    private String numeroCuenta;
    
    /** digitoVerificador. */
    @Field
    private String digitoVerificador;
    
    /** codigoProducto. */
    @Field
    private String codigoProducto;
    
    /** descripcion porducto. */
    @Field
    private String descripcionProducto;
    
    /** tipoTarjeta. */
    @Field
    private String tipoTarjeta;
    
    /** numeroTarjetaTag. */
    @Field
    private String numeroTarjetaTag;
    
    /** nombreEmbozado. */
    @Field
    private String nombreEmbozado;
    
    /** estadoTarjetaTag. */
    @Field
    private String estadoTarjetaTag;

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
	 * @param sucursal
	 *            the new sucursal
	 */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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
	 * Gets the digito verificador.
	 *
	 * @return the digito verificador
	 */
    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    /**
	 * Sets the digito verificador.
	 *
	 * @param digitoVerificador
	 *            the new digito verificador
	 */
    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    /**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
	 * Sets the codigo producto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    /**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
	 * Gets the numero tarjeta tag.
	 *
	 * @return the numero tarjeta tag
	 */
    public String getNumeroTarjetaTag() {
        return numeroTarjetaTag;
    }

    /**
	 * Sets the numero tarjeta tag.
	 *
	 * @param numeroTarjetaTag
	 *            the new numero tarjeta tag
	 */
    public void setNumeroTarjetaTag(String numeroTarjetaTag) {
        this.numeroTarjetaTag = numeroTarjetaTag;
    }

    /**
	 * Gets the nombre embozado.
	 *
	 * @return the nombre embozado
	 */
    public String getNombreEmbozado() {
        return nombreEmbozado;
    }

    /**
	 * Sets the nombre embozado.
	 *
	 * @param nombreEmbozado
	 *            the new nombre embozado
	 */
    public void setNombreEmbozado(String nombreEmbozado) {
        this.nombreEmbozado = nombreEmbozado;
    }

    /**
	 * Gets the estado tarjeta tag.
	 *
	 * @return the estado tarjeta tag
	 */
    public String getEstadoTarjetaTag() {
        return estadoTarjetaTag;
    }

    /**
	 * Sets the estado tarjeta tag.
	 *
	 * @param estadoTarjetaTag
	 *            the new estado tarjeta tag
	 */
    public void setEstadoTarjetaTag(String estadoTarjetaTag) {
        this.estadoTarjetaTag = estadoTarjetaTag;
    }

    /**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcion producto
	 */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto
	 *            the new descripcion producto
	 */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    
    

}
