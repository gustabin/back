package ar.com.santanderrio.obp.base.mq.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

// TODO: Auto-generated Javadoc
/**
 * The Class GestionarMQImpl.
 */
@Component
public class GestionarMQImpl implements GestionarMQ {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** The Constant PREFIJO_MQ. */
	private static final String PREFIJO_MQ = "MQ.";

	/** The Constant SUFIJO_HOSTNAME. */
	private static final String SUFIJO_HOSTNAME = ".HOSTNAME";

	/** The Constant SUFIJO_PORT. */
	private static final String SUFIJO_PORT = ".PORT";

	/** The Constant SUFIJO_QUEUENAME. */
	private static final String SUFIJO_QUEUENAME = ".QUEUENAME";

	/** The Constant SUFIJO_CHANNEL. */
	private static final String SUFIJO_CHANNEL = ".CHANNEL";

	/** The Constant SUFIJO_QMANAGER. */
	private static final String SUFIJO_QMANAGER = ".QMANAGER";

	/** The environment. */
	@Autowired
	private Environment environment;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.mq.webservices.GestionarMQ#sendMessage(ar.
	 * com.santanderrio.obp.base.mq.webservices.ISBANMQOperacion,
	 * java.lang.String)
	 */
	@Override
	public void sendMessage(ISBANMQOperacion operacion, final String msgFinal) throws Exception {
		logger.info("Inicio conexion a la cola de MQ");
		MQEnvironment.hostname = environment.getProperty(PREFIJO_MQ + operacion + SUFIJO_HOSTNAME);
		String port = environment.getProperty(PREFIJO_MQ + operacion + SUFIJO_PORT);
		MQEnvironment.port = Integer.parseInt(port);
		MQEnvironment.channel = environment.getProperty(PREFIJO_MQ + operacion + SUFIJO_CHANNEL);

		MQQueueManager qMangr = null;
		MQQueue queue = null;
		try {
			// Acceso al queue manager...
			qMangr = new MQQueueManager(environment.getProperty(PREFIJO_MQ + operacion + SUFIJO_QMANAGER));
			queue = getMQQueue(environment.getProperty(PREFIJO_MQ + operacion + SUFIJO_QUEUENAME), qMangr);
			MQMessage mQmessag = getMQMessage();

			mQmessag.writeString(msgFinal);
			queue.put(mQmessag, new MQPutMessageOptions());
			qMangr.commit();

		} catch (Exception e) {
			logger.error("Error al generar la conexion de MQ", e);
			throw e;
		} finally {
			if (qMangr != null) {
				qMangr.disconnect();
			}

			if (queue != null) {
				queue.close();
			}
		}
	}

	/**
	 * Gets the MQ queue.
	 *
	 * @param queuename
	 *            the queuename
	 * @param qMgr
	 *            the q mgr
	 * @return the MQ queue
	 * @throws MQException
	 *             the MQ exception
	 */
	/*
	 * Obtener la referencia a la cola MQ
	 */
	private MQQueue getMQQueue(String queuename, MQQueueManager qMgr) throws MQException {
		int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
		return qMgr.accessQueue(queuename, openOptions, null, null, null);
	}

	/**
	 * Gets the MQ message.
	 *
	 * @return the MQ message
	 */
	/*
	 * Creaci�n del mensaje
	 */
	private MQMessage getMQMessage() {
		MQMessage mqmsg = new MQMessage();
		mqmsg.format = MQC.MQFMT_STRING;
		return mqmsg;
	}

}
