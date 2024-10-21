/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.rsa.web.controller.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;

/**
 * The Class RSAControllerUtil.
 */
public final class RSAControllerUtil {

    /**
     * Instantiates a new RSA controller util.
     */
    private RSAControllerUtil() {

    }

    /** The Constant HEADER_ACCEPT. */
    // HTTP headers a procesar
    public static final String HEADER_ACCEPT = "Accept";

    /** The Constant HEADER_ACCEPT_CHARSET. */
    public static final String HEADER_ACCEPT_CHARSET = "Accept-Charset";

    /** The Constant HEADER_ACCEPT_ENCODING. */
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    /** The Constant HEADER_ACCEPT_LANGUAGE. */
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";

    /** The Constant HEADER_REFERER. */
    public static final String HEADER_REFERER = "Referer";

    /** The Constant HEADER_USER_AGENT. */
    public static final String HEADER_USER_AGENT = "User-Agent";

    /**
     * Genera el device para RSA.
     *
     * @param request
     *            the request
     * @param devicePrint
     *            the device print
     * @return the rsa generic request data
     */
    public static RsaGenericRequestData getRsaGenericRequestData(HttpServletRequest request, String devicePrint) {
        RsaGenericRequestData genericRequestData = new RsaGenericRequestData();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String header = request.getHeader(headerName);
            if (HEADER_ACCEPT.equalsIgnoreCase(headerName)) {
                genericRequestData.setHttpAccept(header);
            } else if (HEADER_REFERER.equalsIgnoreCase(headerName)) {
                genericRequestData.setHttpReferrer(header);
            } else if (HEADER_ACCEPT_LANGUAGE.equalsIgnoreCase(headerName)) {
                genericRequestData.setHttpAcceptLanguage(header);
            } else if (HEADER_ACCEPT_ENCODING.equalsIgnoreCase(headerName)) {
                genericRequestData.setHttpAcceptEncoding(header);
            } else if (HEADER_ACCEPT_CHARSET.equalsIgnoreCase(headerName)) {
                genericRequestData.setHttpAcceptChars(header);
            } else if (HEADER_USER_AGENT.equalsIgnoreCase(headerName)) {
                genericRequestData.setUserAgent(header);
            }
        }
        genericRequestData.setRemoteIp(NetworkUtil.getRemoteIp(request));
        genericRequestData.setDevicePrint(devicePrint);
        return genericRequestData;
    }

}
