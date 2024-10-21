package ar.com.santanderrio.obp.servicios.loggedinusercache.entities;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthLoginResponse;

public enum CacheServiceEnum {
    
    USER_CACHE("user_cache", ResumenCliente.class),
    SESION_RECORD("session_record", RegistroSesion.class),
    LOGIN_RESPONSE("login_response", ApiAuthLoginResponse.class),
    CLIENTE_CON_SALDO("cliente_saldo", ClienteConSaldoResponse.class),
//    CUENTAS_CUSTODIA("cuentas_custodia", UserCacheEntity.class),
    STATUS_MYA("status_mya", CredencialesMya.class);
//    CUENTAS_TITULO("cuentas_titulo", UserCacheEntity.class),
//    SEGMENTO("segmento", UserCacheEntity.class);
//    EVENTOS_COMERCIALES("eventos_comerciales", UserCacheEntity.class);
    
    private final Class<?> serviceClass;
    private final String serviceName;

    CacheServiceEnum(String serviceName, Class<?> serviceClass) {
        this.serviceClass = serviceClass;
        this.serviceName = serviceName;
    }

    public static CacheServiceEnum get(String serviceName) {
        for (CacheServiceEnum ft : values())
            if (ft.getServiceName().equals(serviceName))
                return ft;
        return null;
    }
    
    public static CacheServiceEnum get(Class<?> class1) {
        for (CacheServiceEnum ft : values())
            if (ft.getServiceClass().equals(class1))
                return ft;
        return null;
    }

    /**
     * @return the serviceClass
     */
    public Class<?> getServiceClass() {
        return serviceClass;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }
}