package ar.com.santanderrio.obp.servicios.login.entity;


public enum OperacionesLoginOBEEnum {


	CONSTITUCION_PLAZOFIJO("gotoConstitucionPlazoFijo"),
	LICITACIONESRTL("goToLicitaciones(RTL)"),
	LICITACIONESBP("goToLicitaciones(BP)"),
	TITULOS("goToTitulos()"),
	MFETFRTL("goToMFETF(RTL)"),
	MFETFBP("goToMFETF(BP)"),
	MFETF("goToMFETF()"),
	MFCDA("gotoCentroDeAyudaPrivado()");

    private String operacion;


    OperacionesLoginOBEEnum(String operacion) {
        this.operacion = operacion;
    }


    public String getOperacion() {
        return operacion;
    }

    public static OperacionesLoginOBEEnum getByDescripcion(String operacion) {
        for (OperacionesLoginOBEEnum op : OperacionesLoginOBEEnum.values()) {
            if (op.getOperacion().equalsIgnoreCase(operacion)) {
                return op;
            }
        }
        return null;
    }
}
