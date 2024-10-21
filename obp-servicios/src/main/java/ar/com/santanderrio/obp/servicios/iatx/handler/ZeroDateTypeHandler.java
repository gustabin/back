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
 * The Class ZeroDateTypeHandler.
 */
public class ZeroDateTypeHandler implements TypeHandler {

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#parse(java.lang.String)
	 */
	@Override
	public Object parse(String text) throws TypeConversionException {
		if (StringUtils.isNotEmpty(text) && Long.parseLong(text) == 0) {
			return null;
		}
		try {
			return sdf.parse(text);
		} catch (ParseException e) {
			throw new TypeConversionException(e.getMessage());
		}

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
		return sdf.format(valor);
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