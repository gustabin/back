package ar.com.santanderrio.obp.servicios.oauth.config;

public class APIcConfigConstants {

    //Headers
    public static final String AUTH = "Authorization";
    public static final String X_AUTH = "X-Authorization";
    public static final String APIC_ID = "X-IBM-Client-Id";
    public static final String SAN_CORRELATIONID = "X-SAN-CorrelationId";
    public static final String CHANNEL_NAME = "channel-name";
    
    //API Coordinador Headers
    public static final String SAN_IATX_LOGGED_USER = "X-SAN-Iatx-Logged-User";
    public static final String SAN_IATX_USER_ID = "X-SAN-Iatx-User-Id";
    public static final String SAN_IATX_USER_PASS = "X-SAN-Iatx-User-Pass";
    public static final String SAN_IATX_CHANNEL_ID = "X-SAN-Iatx-Channel-Id";
    public static final String SAN_IATX_CHANNEL_TYPE = "X-SAN-Iatx-Channel-Type";
    public static final String SAN_IATX_SUBCHANNEL_ID = "X-SAN-Iatx-Subchannel-Id";
    public static final String SAN_IATX_SUBCHANNEL_TYPE = "X-SAN-Iatx-Subchannel-Type";

    private APIcConfigConstants(){}
}
