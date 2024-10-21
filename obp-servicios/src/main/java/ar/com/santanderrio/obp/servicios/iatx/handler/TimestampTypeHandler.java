/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

/**
 * The Class TimestampTypeHandler.
 */
public class TimestampTypeHandler implements TypeHandler {

	/** The sdf parse. */
	SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");

	/** The sdf format. */
	SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#parse(java.lang.String)
	 */
	@Override
	public Object parse(String text) throws TypeConversionException {
		// Si son todos ceros, se devuelve null
		if (StringUtils.isNotEmpty(text) && text.indexOf("-") == -1 && Long.valueOf(text).longValue() == 0) {
			return null;
		}
		// quito los 3 ultimos digitos de los milisegundos.
		if (text != null && text.length() == 26) {
			text = text.substring(0, text.length() - 3);
			try {
				return (Date) sdfParse.parse(text);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new TypeConversionException(e.getMessage());
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#format(java.lang.Object)
	 */
	@Override
	public String format(Object value) {
		if (value == null) {
			return null;
		}
		Date valor = (Date) value;
		return sdfFormat.format(valor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#getType()
	 */
	@Override
	public Class<?> getType() {
		return Date.class;
	}

}
