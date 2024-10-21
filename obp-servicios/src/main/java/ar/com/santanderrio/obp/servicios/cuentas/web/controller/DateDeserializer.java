/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;

/**
 * The Class DateDeserializer.
 */
public class DateDeserializer extends JsonDeserializer<Date> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
	 * .jackson.core.JsonParser,
	 * com.fasterxml.jackson.databind.DeserializationContext)
	 */
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date result = null;
		String date = jsonparser.getText();
		try {
			if (date != null && !"".equalsIgnoreCase(date)) {
				result = formatter.parse(date);
			}
			return result;
		} catch (ParseException e) {
			throw new ISBANRuntimeException(e);
		}

	}

}
