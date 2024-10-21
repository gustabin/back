package ar.com.santanderrio.obp.base.iatx.helpers;

import java.util.Random;

import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionUsuarioProvider.
 */
public final class SessionUsuarioProvider {

	/** The first. */
	private static boolean first = true;

	/** The next. */
	private static int next;

	/** The Constant PADD. */
	private static final int PADD = 1000000;

	/**
	 * Instantiates a new session usuario provider.
	 */
	private SessionUsuarioProvider() {
	}

	/**
	 * Gets the session usuario.
	 *
	 * @return the session usuario
	 */
	public static synchronized String getSessionUsuario() {
		ApplicationContext ctx = ContextHolder.getContext();
		SessionUsuarioData request = ctx.getBean(SessionUsuarioData.class);
		if (first) {
			Random random = new Random();
			next = random.nextInt(PADD);
			first = false;
		}
		String i = request.getIatxSessionUserName();
		if (i == null) {
			i = String.valueOf(next++);
			request.setIatxSessionUserName(i);
		}
		return i;
	}

	/**
	 * Gets the nro req.
	 *
	 * @return the nro req
	 */
	public static synchronized String getNroReq() {
		ApplicationContext ctx = ContextHolder.getContext();
		SessionUsuarioData request = ctx.getBean(SessionUsuarioData.class);
		String i = request.getIatxNroReqSessionName();
		if (i == null) {
			i = "0";
		} else {
			int n = Integer.parseInt(i);
			n++;
			i = String.valueOf(n);
		}
		request.setIatxNroReqSessionName(i);
		return i;
	}
}
