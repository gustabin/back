package ar.com.santanderrio.obp.servicios.nuevopago.manager.impl;

public class NuevoPagoUtils {
	
	public static final String PAGOS_AFIP_VEP = "AFIP - PAGO DE IMPUESTOS AFIP (VEP)";

	public static final String PAGOS_AFIP_VEP_REPLACE = "AFIP VEP";
	
	public static final String PAGOS_AFIP_VEP_SIN_PARENTESIS = "AFIP - PAGO DE IMPUESTOS AFIP VEP";
	
	
	private NuevoPagoUtils() {
		
	}
	
	public static String nombreEmpresa(String nombreFantasia) {
		String empresa = nombreFantasia;
		
		if (nombreFantasia != null && nombreFantasia.startsWith(PAGOS_AFIP_VEP)){
			empresa = nombreFantasia.replace("(", "");
			empresa = empresa.replace(")", "");
		}
		
		return empresa;
	}

}
