/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.SucursalBO;

/**
 * @author sergio.e.goldentair
 *
 */
@Configuration
public class SucursalesConfig {

    @Autowired
    private SucursalBO sucursalBO;

    @PostConstruct
    public void getSucursales() {
        sucursalBO.actualizarSucursales();
    }

}
