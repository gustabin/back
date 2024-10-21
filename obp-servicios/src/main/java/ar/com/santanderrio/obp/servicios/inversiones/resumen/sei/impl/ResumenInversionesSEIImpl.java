package ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.ResumenInversionesSEI;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.ResumenInversionesManager;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.resumen.web.view.ResumenesMensualesInversionesView;

/**
 * The Class ResumenInversionesSEIImpl.
 */
@Component("resumenInversionesSEI")
public class ResumenInversionesSEIImpl implements ResumenInversionesSEI {

    /** The resumen manager. */
    @Autowired
    private ResumenInversionesManager resumenManager;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.
     * ResumenInversionesSEI#obtenerListaResumen(ar.com.santanderrio.obp.servicios.
     * inversiones.resumen.web.view.ResumenInversionesView)
     */
    @Override
    public Respuesta<ResumenesMensualesInversionesView> obtenerListaResumen(ResumenInversionesView view) {
        return resumenManager.obtenerListaResumen(view.getNumeroCuenta(), view.getBancaPrivada());
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.
     * ResumenInversionesSEI#obtenerResumenesPDF(ar.com.santanderrio.obp.servicios.
     * inversiones.resumen.web.view.ResumenInversionesView)
     */
    @Override
    public Respuesta<ReporteView> obtenerResumenesPDF(ResumenInversionesView view) {
        return resumenManager.obtenerResumenesPDF(view);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.
     * ResumenInversionesSEI#obtenerResumenesMultiple(ar.com.santanderrio.obp.
     * servicios.inversiones.resumen.web.view.ResumenInversionesView)
     */
    @Override
    public Respuesta<ReporteView> obtenerResumenesMultiple(ResumenInversionesView view) {
        return resumenManager.obtenerResumenDescargaMultiple(Arrays.asList(view.getId()), view.getNumeroCuenta(),
        		view.getBancaPrivada(), view.getCantidadADescargar());
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.resumen.sei.
	 * ResumenInversionesSEI#obtenerCuentas(ar.com.santanderrio.obp.servicios.
	 * inversiones.resumen.web.view.ResumenInversionesView)
	 */
	@Override
	public Respuesta<ResumenesInversionesView> obtenerCuentas() {
		return resumenManager.obtenerCuentas();
	}

}
