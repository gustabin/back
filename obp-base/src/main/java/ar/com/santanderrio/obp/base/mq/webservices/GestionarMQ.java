package ar.com.santanderrio.obp.base.mq.webservices;

// TODO: Auto-generated Javadoc
/**
 * The Interface MQInterface.
 */
public interface GestionarMQ {

	/**
	 * Envia el mensaje a la cola de MQ.
	 *
	 * @param operacion
	 *            the operacion
	 * @param msgFinal
	 *            the msg final
	 * @throws Exception
	 *             the exception
	 */
	void sendMessage(ISBANMQOperacion operacion, final String msgFinal) throws Exception;

}
