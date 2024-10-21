/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class RangoFechaEnumDeserializer.
 */
public class RangoFechaEnumDeserializer extends JsonDeserializer<RangoFechaEnum> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
	 * .jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	public RangoFechaEnum deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException {
		return RangoFechaEnum.fromRangoString(jsonparser.getText());
	}

}
