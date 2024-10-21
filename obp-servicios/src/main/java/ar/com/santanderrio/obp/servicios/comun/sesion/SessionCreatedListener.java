/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

/**
 * Manejar lo necesario al evento de creacion de la sesion.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component
public class SessionCreatedListener implements ApplicationListener<HttpSessionCreatedEvent> {
    /** The pid count. */
    private static int pidCount;

    /** The random generator. */
    private static Random randomGenerator;

    /** The Constant MAX. */
    private static final int MAX = 65535;

    /** The Constant RANDOM. */
    private static final int RANDOM = 32700;

    /** The Constant SUMAUNO. */
    private static final int SUMAUNO = 1;

    /** The session timeout. */
    @Value("${JWT_AND_SESSION_EXPIRATION_TIME:600000}")
    private Integer sessionTimeout;

    static {
        randomGenerator = new Random(System.currentTimeMillis());
        pidCount = Math.abs(getFirstRandom() % MAX);
    }

    /**
     * Gets the first random.
     *
     * @return the first random
     */
    private static int getFirstRandom() {
        return randomGenerator.nextInt();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(HttpSessionCreatedEvent event) {
        Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(sessionTimeout);
        event.getSession().setMaxInactiveInterval(milisegEnSegundos.intValue());
        String pid = getNextPid();
        String fileName = nextFileName(pid);
        event.getSession().setAttribute("logFileName", fileName);
        event.getSession().setAttribute("pid", pid);
    }

    /**
	 * Next file name.
	 *
	 * @param pid
	 *            the pid
	 * @return the string
	 */
    private String nextFileName(String pid) {
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat fmt = new SimpleDateFormat("yyMMddHHmmss");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return pid + "-" + dateFormatted;
    }

    /**
	 * Gets the next pid.
	 *
	 * @return the next pid
	 */
    private String getNextPid() {
        return String.valueOf((incrementPidCount() % RANDOM) + SUMAUNO);
    }

    /**
     * Increment pid count.
     *
     * @return the int
     */
    private static int incrementPidCount() {
        return pidCount++;
    }

}
