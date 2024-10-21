/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.entities; 
 
import java.util.ArrayList;
import java.util.List; 
 
/**
 * The Class InfoTarjetas.
 */
public class InfoTarjetas { 
 
    /** The tarjeta titular. */
    private String tarjetaTitular; 

    /** The alias cuenta. */
    private String aliasCuenta; 
 
    /** The tarjetas asociadas. */
    private List<Tarjeta> tarjetasAsociadas; 
     
    /** The clave reenganche. */
    private String claveReenganche; 
     
    /** The marca. */
    private String marca; 

    /** The marca codigo. */
    private String marcaCodigo; 
     
    /** The tipo cuenta. */
    private String tipoCuenta; 
 
    /** The numero cuenta producto. */
    private String numeroCuentaProducto; 

    /** The ultimos cuatro digitos. */
    private String ultimosCuatroDigitos; 

    /** The nombre plastico. */
    private String nombrePlastico; 
     
    /** The hay mas datos. */
    private Boolean hayMasDatos; 

    /** The is titular. */
    private Boolean isTitular; 

    /** The numero tarjeta credito. */
    private String numeroTarjetaCredito; 
     
 
    /**
     * Instantiates a new info tarjetas.
     */
    public InfoTarjetas() { 
        
        this.tarjetasAsociadas = new ArrayList<Tarjeta>();

        this.claveReenganche =  "0000000000000000"; 
         
        this.hayMasDatos = Boolean.TRUE; 
    } 
 
    /** 
     * @return the tarjetaTitular 
     */ 
    public String getTarjetaTitular() { 
        return tarjetaTitular; 
    } 
 
    /** 
     * @param tarjetaTitular 
     *            the tarjetaTitular to set 
     */ 
    public void setTarjetaTitular(String tarjetaTitular) { 
        this.tarjetaTitular = tarjetaTitular; 
    } 
 
 
    /** 
     * @return the claveReenganche 
     */ 
    public String getClaveReenganche() { 
        return claveReenganche; 
    } 
 
    /** 
     * @param claveReenganche the claveReenganche to set 
     */ 
    public void setClaveReenganche(String claveReenganche) { 
        this.claveReenganche = claveReenganche; 
    } 
 
    /** 
     * @return the hayMasDatos 
     */ 
    public Boolean getHayMasDatos() { 
        return hayMasDatos; 
    } 
 
    /** 
     * @param hayMasDatos the hayMasDatos to set 
     */ 
    public void setHayMasDatos(Boolean hayMasDatos) { 
        this.hayMasDatos = hayMasDatos; 
    } 
 
    /** 
     * @return the marca 
     */ 
    public String getMarca() { 
        return marca; 
    } 
 
    /** 
     * @param marca the marca to set 
     */ 
    public void setMarca(String marca) { 
        this.marca = marca; 
    } 
 
    /** 
     * @return the tipoCuenta 
     */ 
    public String getTipoCuenta() { 
        return tipoCuenta; 
    } 
 
    /** 
     * @param tipoCuenta the tipoCuenta to set 
     */ 
    public void setTipoCuenta(String tipoCuenta) { 
        this.tipoCuenta = tipoCuenta; 
    } 
 
    /** 
     * @return the numeroCuentaProducto 
     */ 
    public String getNumeroCuentaProducto() { 
        return numeroCuentaProducto; 
    } 
 
    /** 
     * @param numeroCuentaProducto the numeroCuentaProducto to set 
     */ 
    public void setNumeroCuentaProducto(String numeroCuentaProducto) { 
        this.numeroCuentaProducto = numeroCuentaProducto; 
    }

    /**
     * Gets the ultimos cuatro digitos.
     *
     * @return the ultimosCuatroDigitos
     */
    public String getUltimosCuatroDigitos() {
        return ultimosCuatroDigitos;
    }

    /**
     * Sets the ultimos cuatro digitos.
     *
     * @param ultimosCuatroDigitos the ultimosCuatroDigitos to set
     */
    public void setUltimosCuatroDigitos(String ultimosCuatroDigitos) {
        this.ultimosCuatroDigitos = ultimosCuatroDigitos;
    }

    /**
     * Gets the nombre plastico.
     *
     * @return the nombrePlastico
     */
    public String getNombrePlastico() {
        return nombrePlastico;
    }

    /**
     * Sets the nombre plastico.
     *
     * @param nombrePlastico the nombrePlastico to set
     */
    public void setNombrePlastico(String nombrePlastico) {
        this.nombrePlastico = nombrePlastico;
    }

    /**
     * Gets the checks if is titular.
     *
     * @return the isTitular
     */
    public Boolean getIsTitular() {
        return isTitular;
    }

    /**
     * Sets the checks if is titular.
     *
     * @param isTitular the isTitular to set
     */
    public void setIsTitular(Boolean isTitular) {
        this.isTitular = isTitular;
    }

    /**
     * Gets the marca codigo.
     *
     * @return the marcaCodigo
     */
    public String getMarcaCodigo() {
        return marcaCodigo;
    }

    /**
     * Sets the marca codigo.
     *
     * @param marcaCodigo the marcaCodigo to set
     */
    public void setMarcaCodigo(String marcaCodigo) {
        this.marcaCodigo = marcaCodigo;
    }

    /**
     * Gets the tarjetas asociadas.
     *
     * @return the tarjetasAsociadas
     */
    public List<Tarjeta> getTarjetasAsociadas() {
        return tarjetasAsociadas;
    }

    /**
     * Sets the tarjetas asociadas.
     *
     * @param tarjetasAsociadas the tarjetasAsociadas to set
     */
    public void setTarjetasAsociadas(List<Tarjeta> tarjetasAsociadas) {
        this.tarjetasAsociadas = tarjetasAsociadas;
    }

    /**
     * Gets the numero tarjeta credito.
     *
     * @return the numeroTarjetaCredito
     */
    public String getNumeroTarjetaCredito() {
        return numeroTarjetaCredito;
    }

    /**
     * Sets the numero tarjeta credito.
     *
     * @param numeroTarjetaCredito the numeroTarjetaCredito to set
     */
    public void setNumeroTarjetaCredito(String numeroTarjetaCredito) {
        this.numeroTarjetaCredito = numeroTarjetaCredito;
    }

    /**
     * Gets the alias cuenta.
     *
     * @return the aliasCuenta
     */
    public String getAliasCuenta() {
        return aliasCuenta;
    }

    /**
     * Sets the alias cuenta.
     *
     * @param aliasCuenta the aliasCuenta to set
     */
    public void setAliasCuenta(String aliasCuenta) {
        this.aliasCuenta = aliasCuenta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InfoTarjetas [tarjetaTitular=" + tarjetaTitular + ", aliasCuenta=" + aliasCuenta
                + ", tarjetasAsociadas=" + tarjetasAsociadas + ", claveReenganche=" + claveReenganche + ", marca="
                + marca + ", marcaCodigo=" + marcaCodigo + ", tipoCuenta=" + tipoCuenta + ", numeroCuentaProducto="
                + numeroCuentaProducto + ", ultimosCuatroDigitos=" + ultimosCuatroDigitos + ", nombrePlastico="
                + nombrePlastico + ", hayMasDatos=" + hayMasDatos + ", isTitular=" + isTitular
                + ", numeroTarjetaCredito=" + numeroTarjetaCredito + "]";
    } 
    
    
     
} 
