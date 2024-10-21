package ar.com.santanderrio.obp.servicios.prestamos.utils;

import org.apache.commons.collections.map.MultiKeyMap;

public enum EnumPrestamosCuotasRefinanciadas {

    PRESTAMO_35_13("35", "0013"),
    PRESTAMO_35_37("35", "0037"),
    PRESTAMO_35_122("35", "0122"),
    PRESTAMO_35_123("35", "0123"),
    PRESTAMO_37_77("37", "0077"),
    PRESTAMO_37_81("37", "0081"),
    PRESTAMO_37_121("37", "0121"),
    PRESTAMO_37_122("37", "0122"),
    PRESTAMO_37_123("37", "0123"),
    PRESTAMO_38_25("38", "0025"),
    PRESTAMO_38_26("38", "0026"),
    PRESTAMO_38_46("38", "0046"),
    PRESTAMO_38_47("38", "0047"),
    PRESTAMO_38_49("38", "0049"),
    PRESTAMO_38_50("38", "0050"),
    PRESTAMO_71_591("71", "0591"),
    PRESTAMO_71_592("71", "0592"),
    PRESTAMO_71_594("71", "0594"),
    PRESTAMO_71_596("71", "0596"),
    PRESTAMO_71_597("71", "0597");

    private final String producto;

    private final String subproducto;

    EnumPrestamosCuotasRefinanciadas(String producto, String subproducto) {
        this.producto = producto;
        this.subproducto = subproducto;
    }

    public String getProducto() {
        return producto;
    }

    public String getSubproducto() {
        return subproducto;
    }

    private static final MultiKeyMap map;

    static {
        map = new MultiKeyMap();
        for (EnumPrestamosCuotasRefinanciadas v : EnumPrestamosCuotasRefinanciadas.values()) {
            map.put(v.getProducto(), v.getSubproducto(), v);
        }
    }

    /**
     * Checkea si existe, por producto y subproducto.
     */
    public static boolean contiene(String producto, String subproducto) {
        return map.containsKey(producto, subproducto);
    }

}
