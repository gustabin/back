/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.view;


/**
 * ProductoTrackingView.
 *
 * @author Silvina_Luque
 */
public enum ProductoTrackingView {

    /** The visa. */
    VISA(7,"VISA" ,"Santander Visa"),
    
    /** The mastercard. */
    MASTERCARD(6, "Mastercard", "Santander Mastercard"),
    
    /** The banelco. */
    BANELCO(5, "VISA", "Santander Débito"),
    
    /** The visa recargable. */
    VISA_RECARGABLE(77 ,"VISA", "Tarjeta Santander Visa Recargable"),

    /** The amex. */
    AMEX(42, "AMEX", "Santander American Express"),
    
    /** The monedero. */
    MONEDERO(43,"MONEDERO", "Monedero Tag");
    

    /** The tipo cuenta. */
    private Integer tipoCuenta;
    
    /** The titulo. */
    private String titulo;
    
    /** The descripcion. */
    private String descripcion;
    
    /**
	 * Instantiates a new producto tracking view.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param titulo
	 *            the titulo
	 * @param descripcion
	 *            the descripcion
	 */
    private ProductoTrackingView(Integer tipoCuenta, String titulo, String descripcion) {
        this.tipoCuenta = tipoCuenta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        
    }

    /**
	 * obtenerPorTipoCuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 * @returnProductoTrackingView
	 */
    public static String obtenerDescPorTipoCuenta(Integer tipoCuenta) {
        for (ProductoTrackingView productoTrackingView : values()) {
            if (productoTrackingView.getTipoCuenta().equals(tipoCuenta)) {
                return productoTrackingView.getDescripcion();
            }
        }
        return "";
    }
    
    /**
	 * obtenerPorTipoCuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 * @returnProductoTrackingView
	 */
    public static String obtenerTituloPorTipoCuenta(Integer tipoCuenta) {
        for (ProductoTrackingView productoTrackingView : values()) {
            if (productoTrackingView.getTipoCuenta().equals(tipoCuenta)) {
                return productoTrackingView.getTitulo();
            }
        }
        return "";
    }
    
    
    /**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
    public Integer getTipoCuenta() {
        return tipoCuenta;
    }


    /**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
    public String getDescripcion() {
        return descripcion;
    }

    
    /**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
    public String getTitulo() {
        return titulo;
    }
    
}
