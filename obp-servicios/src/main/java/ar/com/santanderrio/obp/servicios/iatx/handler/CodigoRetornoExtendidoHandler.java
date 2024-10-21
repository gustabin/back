/*
 * 
 */
package ar.com.santanderrio.obp.servicios.iatx.handler;

import org.apache.commons.lang3.StringUtils;
import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

/**
 * Formatea el codido de retorno de iatx, quedandose solo con los digitos del
 * codigo de retorno por ej: 0020000000000 -> 00000000.
 *
 * @author marcelo.ruiz
 */
public class CodigoRetornoExtendidoHandler implements TypeHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#parse(java.lang.String)
	 */
	@Override
	public Object parse(String text) throws TypeConversionException {
		return StringUtils.substring(text, 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#format(java.lang.Object)
	 */
	@Override
	public String format(Object value) {
		return value.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beanio.types.TypeHandler#getType()
	 */
	@Override
	public Class<?> getType() {
		return String.class;
	}

}
