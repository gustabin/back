package ar.com.santanderrio.obp.servicios.pagos.entities;

public enum PrestamoNormativoEnum {

    ID3("ID3", "Diferencia de Cuota por Congelamiento DNU 767/20. "),
    GD7("GD7", "Diferencia de Cuota por Congelamiento DNU 767/20. "),
    COV("COV", "Refinanciación Cuota COM A6949 PMO. {0}. "),
    VD7("VD7", "Diferencia de Cuota por Convergencia DNU 767/20. "),
    DNUC("DNUC", "Diferencia por Congelamiento de Cuotas (DNU 319/2020). "),
    BRP("BRP","REF CUOTA BENEF. REPRO II. "),
    NULL_SAFE("NULL_SAFE","");

    private final String codigoNormativo;
    private final String label;

    PrestamoNormativoEnum(String codigoNormativo, String label){
        this.codigoNormativo = codigoNormativo;
        this.label = label;
    }

    public static PrestamoNormativoEnum fromNumeroPropuesta(String numeroPropuesta) {
        PrestamoNormativoEnum response = null;
        for (PrestamoNormativoEnum enumNormativo : PrestamoNormativoEnum.values()) {
            if (numeroPropuesta.startsWith(enumNormativo.getCodigoNormativo())) {
                response = enumNormativo;
            }
        }
        if (response == null) {
            throw new IllegalArgumentException();
        }
        return response;
    }

    public String getCodigoNormativo() {
        return codigoNormativo;
    }

    public String getLabel() {
        return label;
    }
}
