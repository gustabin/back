/**
 * 
 */
package ar.com.santanderrio.obp.base.types;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Enum ExternalPropertyType.
 *
 * @author pablo.martin.gore
 */
public enum ExternalPropertyType {

	// ----------------------------------------------------------------------
	// Enums
	/** The file sucursales. */
	// ----------------------------------------------------------------------
	FILE_SUCURSALES("sucursales.xml"),
	/** The file mediosdepago. */
	FILE_MEDIOSDEPAGO("MediosDePago.txt"),
	/** The file rsa. */
	FILE_RSA("obp-rsa.properties"),
	/** The file autentification. */
	FILE_AUTENTIFICATION("autentification.properties"),
	/** The file filtro agenda. */
	FILE_FILTRO_AGENDA("filtroaddiagenda.txt"),
	/** The file terminos condiciones. */
	FILE_TERMINOS_CONDICIONES("TerminosCondiciones.txt"),

	/** destinos BCRA. */
	FILE_DESTINO_PRESTAMO("DESTINOS-BCRA.TXT"),

	/** The file localidades. */
	FILE_LOCALIDADES("localidades.xml"),

	/** The file nacionalidades. */
	FILE_NACIONALIDADES("nacionalidades.xml"),

	/** The file provincias. */
	FILE_PROVINCIAS("provincias.xml"),

	/** The file clasesbanelco. */
	FILE_CLASES_BANELCO("clasesbanelco.txt"),

	/** The file clasesbanelco. */
	FILE_PES_SUBEMPRESAS("pessubempresas.txt"),

	/** The file parametros mya suscripciones. */
	FILE_PARAMETROS_MYA_SUSCRIPCIONES("myaParametros.xml"),

	/** The file codigos bancarios. */
	FILE_CODIGOS_BANCARIOS("CodigosBancarios.txt"),

	/** The file Entidades. */
	FILE_ENTIDADES("Entidades.txt"),
	
	/** The file ListadoNupAutogestion. */
	FILE_LISTADO_NUP_AUTOGESTION("ListadoNupAutogestion.txt"),
	
	/** The file whatsapp habilitado. */
	FILE_WHATSAPP_HABILITADO("Flujo_WhatsApp.txt"),

	/** The file nups vencimiento tarjeta coord. */
	FILE_NUPS_VENC_TARJ_COORD("nupsVencTarjetaCoord.txt"),
	
	/** The file ChargebacksAllowedNups. */
	FILE_NUPS_CHARGEBACKS("ChargebacksAllowedNups.txt"),
	
	/** The file nups vencimiento tarjeta coord. */
	FILE_NUPS_PROX_VENC_TARJ_COORD("nupsProxBajaTarjCoord.txt"),
	
	FILE_NUPS_PROX_VENC_TARJ_COORD_240621("nupsVencTarjetaCoord240621.txt"),

	FILE_NUPS_FLUJO_BAJA_CUENTA("ListadoNupsBajaCuentaCentralizado.txt"),
	
	FILE_PRESTAMOS_COM_12123("prestamosCom12123.txt");
	
	// ----------------------------------------------------------------------
	// Fields
	/** The Constant map. */
	// ----------------------------------------------------------------------
	private static final Map<String, ExternalPropertyType> map = new HashMap<String, ExternalPropertyType>();

	/** The name. */
	private String name;

	// ----------------------------------------------------------------------
	// constructors
	// ----------------------------------------------------------------------
	static {
		for (ExternalPropertyType type : ExternalPropertyType.values()) {
			map.put(type.name, type);
		}
	}

	// ----------------------------------------------------------------------
	// static method
	// ----------------------------------------------------------------------

	/**
	 * Instantiates a new external property type.
	 *
	 * @param name
	 *            the name
	 */
	private ExternalPropertyType(String name) {
		this.name = name;
	}

	/**
	 * From string.
	 *
	 * @param name
	 *            the name
	 * @param code
	 *            the code
	 * @return the external property type
	 */
	public static ExternalPropertyType fromString(String name, String code) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;
	}

	// ----------------------------------------------------------------------
	// public method
	// ----------------------------------------------------------------------

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
