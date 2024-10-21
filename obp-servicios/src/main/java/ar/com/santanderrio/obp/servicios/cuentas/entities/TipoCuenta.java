/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoCuenta.
 */
public enum TipoCuenta {
 
    /** The cuenta corriente pesos. */
    CUENTA_CORRIENTE_PESOS(0, "Cuenta Corriente en Pesos", "CCP", "Cuenta corriente en $"),
    /** The caja ahorro pesos. */
    CAJA_AHORRO_PESOS(1, "Caja de Ahorro en Pesos", "CAP", "Caja de ahorro en $"),
    /** The cuenta unica. */
    CUENTA_UNICA(2, "Cuenta Única", "CU", "Cuenta única"),
    /** The cuenta corriente dolares. */
    CUENTA_CORRIENTE_DOLARES(3, "Cuenta Corriente en Dólares", "CCD", "Cuenta corriente en u$s"),
    /** The caja ahorro dolares. */
    CAJA_AHORRO_DOLARES(4, "Caja de Ahorro en Dólares", "CAD", "Caja de ahorro en u$s"),
    /** The banelco. */
    BANELCO(5, "Banelco", "Banelco"),
    /** The mastercard. */
    MASTERCARD(6, "Mastercard", "Master"), 
    /** The visa. */
    VISA(7, "Visa", "Visa"),
    /** The visa recargable. */
    VISA_RECARGABLE(77, "Visa Recargable", "Visa Recargable"),
    /** The titulos. */
    TITULOS(8, "Títulos", "Títulos"),
    /** The cuenta unica pesos. */
    CUENTA_UNICA_PESOS(9, "Cuenta única", "CUP", "Cuenta única en $"),
    /** The cuenta unica dolares. */
    CUENTA_UNICA_DOLARES(10, "Cuenta única", "CUD", "Cuenta única en u$s"),
    /** The cuenta recaudadora pesos. */
    CUENTA_RECAUDADORA_PESOS(11, "Cuenta recaudadora en pesos", "Cuenta recaudadora $"),
    /** The cuenta recaudadora dolares. */
    CUENTA_RECAUDADORA_DOLARES(12, "Cuenta recaudadora en dólares", "Cuenta recaudadora U$S"),
    /** The fondo de desempleo. */
    FONDO_DE_DESEMPLEO(14, "Fondo de desempleo", "AFON"),
    /** The inversor de plazo fijo. */
    INVERSOR_DE_PLAZO_FIJO(15, "Inversor de plazo fijo", "ADPF"),
    /** The adee. */
    ADEE(16, "ADEE", "ADEE"),
    /** The cheques secionados. */
    CHEQUES_SECIONADOS(20, "Cheques secionados", "ADES"),
    /** The abon. */
    ABON(21, "ABON", "ABON"),
    /** The lecop. */
    LECOP(22, "LECOP", "LECOP"),
    /** The lecor. */
    LECOR(23, "LECOR", "LECOR"),
    /** The pata. */
    PATA(24, "PATA", "PATA"),
    /** The patb. */
    PATB(25, "PATB", "PATB"),
    /** The patc. */
    PATC(26, "PATC", "PATC"),
    /** The prestamo. */
    PRESTAMO(30, "Préstamo", "APTM"),
    /** The prestamo prendario. */
    PRESTAMO_PRENDARIO(31, "Préstamo prendario", "AFCO"),
    /** The prestamo discontinuado apre. */
    PRESTAMO_DISCONTINUADO_APRE(32, "Préstamo discontinuado (APRE)", "APRE"),
    /** The prestamo discontinuado adim. */
    PRESTAMO_DISCONTINUADO_ADIM(33, "Préstamo discontinuado (ADIM)", "ADIM"),
    /** The amex. */
    AMEX(42, "Amex", "Amex"),
    /** The Tarjeta Monedero. */
    TARJETA_MONEDERO(43, "Tarjeta Monedero", "MONEDERO"),
    /** The bank trade. */
    BANK_TRADE(50, "Bank trade", "ADEX"),
    /** The ames. */
    AMES(61, "AMES", "AMES"),
    /** The gestion y mora. */
    GESTION_Y_MORA(70, "Gestión y mora", "AGMO"),
    /** The caja de seguridad. */
    CAJA_DE_SEGURIDAD(80, "Caja de seguridad", "ACSE"),
    /** The domiciliaciones. */
    DOMICILIACIONES(82, "Domiciliaciones", "ADOM");

    /** The codigo. */
    private Integer codigo;

    /** The descripcion. */
    private String descripcion;

    /** The abreviatura. */
    private String abreviatura;

    /** The descripcion con moneda. */
    private String descripcionConMoneda;

    /**
     * Instantiates a new tipo cuenta.
     *
     * @param codigo
     *            the codigo
     * @param descripcion
     *            the descripcion
     * @param abreviatura
     *            the abreviatura
     */
    TipoCuenta(Integer codigo, String descripcion, String abreviatura) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
    }

    /**
     * Instantiates a new tipo cuenta.
     *
     * @param codigo
     *            the codigo
     * @param descripcion
     *            the descripcion
     * @param abreviatura
     *            the abreviatura
     * @param descripcionConMoneda
     *            the descripcion con moneda
     */
    TipoCuenta(Integer codigo, String descripcion, String abreviatura, String descripcionConMoneda) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.descripcionConMoneda = descripcionConMoneda;
    }

    /**
     * From codigo.
     *
     * @param codigo
     *            the codigo
     * @return the tipo cuenta
     */
    public static TipoCuenta fromCodigo(String codigo) {
        Integer valor = Integer.parseInt(codigo);
        return TipoCuenta.fromCodigo(valor);
    }

    /**
     * From descripcion con moneda.
     *
	 * @param descripcionConMoneda
	 *            the descripcion con moneda
     * @return the tipo cuenta
     */
    public static TipoCuenta fromDescripcionConMoneda(String descripcionConMoneda) {
        TipoCuenta[] values = TipoCuenta.values();

        TipoCuenta response = null;

        for (TipoCuenta tipoCuenta : values) {
        	if (null != tipoCuenta.getDescripcionConMoneda()) {
        		if (tipoCuenta.getDescripcionConMoneda().equalsIgnoreCase(descripcionConMoneda)) {
        			response = tipoCuenta;
        			break;
        		}
        	}
        }
        return response;
    }

    /**
     * From descripcion.
     *
	 * @param descripcion
	 *            the descripcion
     * @return the tipo cuenta
     */
    public static TipoCuenta fromDescripcion(String descripcion) {
        TipoCuenta[] values = TipoCuenta.values();

        TipoCuenta response = null;

        for (TipoCuenta tipoCuenta : values) {
            if (tipoCuenta.getDescripcion().equalsIgnoreCase(descripcion)) {
                response = tipoCuenta;
                break;
            }
        }
        return response;
    }

    /**
     * From abreviatura.
     *
     * @param abreviatura
     *            the abreviatura
     * @return the tipo cuenta
     */
    public static TipoCuenta fromAbreviatura(String abreviatura) {
        TipoCuenta[] values = TipoCuenta.values();

        TipoCuenta response = null;

        for (TipoCuenta tipoCuenta : values) {
            if (tipoCuenta.getAbreviatura().equals(abreviatura)) {
                response = tipoCuenta;
            }
        }
        return response;
    }

    /**
     * From codigo.
     *
     * @param codigo
     *            the codigo
     * @return the tipo cuenta
     */
    public static TipoCuenta fromCodigo(Integer codigo) {
        TipoCuenta[] values = TipoCuenta.values();

        TipoCuenta response = null;

        for (TipoCuenta tipoCuenta : values) {
            if (tipoCuenta.getCodigo().equals(codigo)) {
                response = tipoCuenta;
            }
        }
        return response;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
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
     * Gets the abreviatura.
     *
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Gets the descripcion con moneda.
     *
     * @return the descripcionConMoneda
     */
    public String getDescripcionConMoneda() {
        return descripcionConMoneda;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Name: ").append(this.name()).append(" - Codigo: ").append(this.codigo)
                .append(" - Descripcion: ").append(this.descripcion).append(" - Abbr: ").append(this.abreviatura)
                .append(" - Descripcion con moneda: ").append(this.descripcionConMoneda).toString();
    }

}
