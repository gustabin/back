/*
 * 
 */
package ar.com.santanderrio.obp.base.comun;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.exceptions.HostAdressException;
import ar.com.santanderrio.obp.base.exceptions.HostNameException;

/**
 * Created by pablo.martin.gore on 9/2/2016.
 */
public final class NetworkUtil {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * Utilitario de servicios para obtener info del request o del servidor.
     */
    private NetworkUtil() {
        super();
    }

    /**
     * Gets the host name.
     *
     * @return the host name
     * @throws HostNameException
     *             the host name exception
     */
    public static String getHostName() throws HostNameException {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOGGER.error("No fue posible obtener el host name", e);
        }
        throw new HostNameException("No fue posible obtener el host name");
    }

    /**
     * Gets the host address.
     *
     * @return the host address
     * @throws HostAdressException
     *             the host adress exception
     */
    public static String getHostAddress() throws HostAdressException {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error("No fue posible obtener  host address", e);
        }
        throw new HostAdressException("No fue posible obtener  host address");
    }

    /**
	 * Obtener la ip originada en el equipo que realiza el request al servidor
	 * sino devuelve string vacio.
	 *
	 * @param httpServletRequest
	 *            the http servlet request
	 * @return the remote ip
	 */
    public static String getRemoteIp(HttpServletRequest httpServletRequest) {
        if (httpServletRequest != null) {
            String ipByBalancer = httpServletRequest.getHeader("x-forwarded-for");
            if (StringUtils.isNotEmpty(ipByBalancer)) {
                return getFirstIp(ipByBalancer);
            } else {
                return httpServletRequest.getRemoteAddr();
            }
        }
        return StringUtils.EMPTY;
    }
    
    /**
	 * The general format of the field is:<br/>
	 * 
	 * X-Forwarded-For: client, proxy1, proxy2[3] where the value is a
	 * comma+space separated list of IP addresses, the left-most being the
	 * original client, and each successive proxy that passed the request adding
	 * the IP address where it received the request from. In this example, the
	 * request passed through proxy1, proxy2, and then proxy3 (not shown in the
	 * header). proxy3 appears as remote address of the request.<br/>
	 * <br/>
	 * Since it is easy to forge an X-Forwarded-For field the given information
	 * should be used with care. The last IP address is always the IP address
	 * that connects to the last proxy, which means it is the most reliable
	 * source of information. X-Forwarded-For data can be used in a forward or
	 * reverse proxy scenario.<br/>
	 * <br/>
	 * Just logging the X-Forwarded-For field is not always enough as the last
	 * proxy IP address in a chain is not contained within the X-Forwarded-For
	 * field, it is in the actual IP header. A web server should log BOTH the
	 * request's source IP address and the X-Forwarded-For field information for
	 * completeness.
	 *
	 * @param ipByBalancer
	 *            the ip by balancer
	 * @return the first ip
	 */
    private static String getFirstIp(String ipByBalancer) {
        String[] ips = StringUtils.split(ipByBalancer, ',');
        if (ips.length > 1) {
            LOGGER.info("Se reciben las siguientes ips {} y me quedo con {}", ipByBalancer, ips[0]);
            return ips[0];
        }
        return ipByBalancer;
    }

    /**
	 * Obtener informacion relativa al browser.
	 *
	 * @param httpServletRequest
	 *            the http servlet request
	 * @return the user agent
	 */
    public static String getUserAgent(HttpServletRequest httpServletRequest) {
        if (httpServletRequest != null) {
            return httpServletRequest.getHeader("User-Agent");
        }
        return StringUtils.EMPTY;
    }
}
