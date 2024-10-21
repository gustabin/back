/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class DivisaEnumDeserializer.
 */
public class DivisaEnumDeserializer extends JsonDeserializer<DivisaEnum> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
	 * .jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	public DivisaEnum deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException {
		DivisaEnum divisa = null;
		if (DivisaEnum.DOLAR.compareTo(DivisaEnum.fromMonedaString(jsonparser.getText())) == 0) {
			divisa = DivisaEnum.DOLAR;
		}
		if (DivisaEnum.PESO.compareTo(DivisaEnum.fromMonedaString(jsonparser.getText())) == 0) {
			divisa = DivisaEnum.PESO;
		}
		return divisa;
	}

}
