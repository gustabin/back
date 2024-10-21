/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

/**
 * The Class SesionTitulosOperaciones.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionTitulosOperaciones {
    
    /** The parametros operaciones view. */
    private ParametrosOperacionesView parametrosOperacionesView;
    
    /** The numero consulta. */
    private Integer numeroConsulta;

    /**
	 * Gets the parametros operaciones view.
	 *
	 * @return the parametros operaciones view
	 */
    public ParametrosOperacionesView getParametrosOperacionesView() {
        return parametrosOperacionesView;
    }

    /**
	 * Sets the parametros operaciones view.
	 *
	 * @param parametrosOperacionesView
	 *            the new parametros operaciones view
	 */
    public void setParametrosOperacionesView(ParametrosOperacionesView parametrosOperacionesView) {
        this.parametrosOperacionesView = parametrosOperacionesView;
    }

    /**
	 * Gets the numero consulta.
	 *
	 * @return the numero consulta
	 */
    public Integer getNumeroConsulta() {
        return numeroConsulta;
    }

    /**
	 * Sets the numero consulta.
	 *
	 * @param numeroConsulta
	 *            the new numero consulta
	 */
    public void setNumeroConsulta(Integer numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
    }

}
