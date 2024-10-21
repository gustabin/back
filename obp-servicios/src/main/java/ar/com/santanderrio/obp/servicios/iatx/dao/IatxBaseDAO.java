package ar.com.santanderrio.obp.servicios.iatx.dao;

import org.beanio.StreamFactory;
import org.beanio.Unmarshaller;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.StreamBuilder;

import ar.com.santanderrio.obp.servicios.iatx.handler.CodigoRetornoExtendidoHandler;
import ar.com.santanderrio.obp.servicios.iatx.handler.TimestampTypeHandler;
import ar.com.santanderrio.obp.servicios.iatx.handler.ZeroDateTypeHandler;

/**
 * The Class IatxBaseDAO.
 *
 * @author marcelo.ruiz
 */
public class IatxBaseDAO {

	/** The Constant TIMEOUT_EXCEPTION. */
	public static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

	/** caracter delimitador. */
	private static final char DELIMITADOR = 'õ';

	/**
	 * Hace un unmarshall de la trama a un bean .
	 *
	 * @param <T>
	 *            the generic type
	 * @param trama
	 *            the trama
	 * @param clazz
	 *            the clazz
	 * @return the t
	 */
	@SuppressWarnings({ "unchecked" })
	public <T> T processTrama(String trama, Class<T> clazz) {
		StreamFactory factory;
		StreamBuilder builder = new StreamBuilder(clazz.getName()).format("delimited")
				.addTypeHandler("timestampTypeHandler", new TimestampTypeHandler())
				.addTypeHandler("codigoRetornoExtendidoHandler", new CodigoRetornoExtendidoHandler())
				.addTypeHandler("zeroDateTypeHandler", new ZeroDateTypeHandler()).addRecord(clazz);
		builder.parser(new DelimitedParserBuilder().delimiter(DELIMITADOR));
		factory = StreamFactory.newInstance();
		factory.define(builder);
		Unmarshaller unmarshaller = factory.createUnmarshaller(clazz.getName());
		return (T) unmarshaller.unmarshal(trama);

	}

}
