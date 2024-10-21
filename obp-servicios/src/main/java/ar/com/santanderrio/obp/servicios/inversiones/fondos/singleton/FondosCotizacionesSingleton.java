package ar.com.santanderrio.obp.servicios.inversiones.fondos.singleton;


import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoOutEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FondosCotizacionesSingleton {

    private FondoOutEntity outEntity;

    private boolean cleanCache;

    public FondoOutEntity getOutEntity() {
        return outEntity;
    }

    public boolean getCleanCache() {
        return cleanCache;
    }

    public void setCleanCache(boolean cleanCache) {
        this.cleanCache = cleanCache;
    }

    public void setOutEntity(FondoOutEntity outEntity) {
        this.outEntity = outEntity;
    }
}
