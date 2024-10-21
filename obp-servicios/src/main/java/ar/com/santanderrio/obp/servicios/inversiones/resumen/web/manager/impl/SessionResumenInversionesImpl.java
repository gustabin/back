/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.resumen.web.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenInversiones;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenesMensualesDisponiblesInversiones;

/**
 * The Class SessionResumenInversionesImpl.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionResumenInversionesImpl implements SessionResumenInversiones {

    /** The resumenes mensuales disponibles. */
    private List<ResumenesMensualesDisponiblesInversiones> resumenesMensualesDisponibles = new ArrayList<ResumenesMensualesDisponiblesInversiones>();

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
     * SessionResumenInversiones#setResumenesMensualesDisponiblesByCuenta(ar.com.
     * santanderrio.obp.base.respuesta.entities.Respuesta,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public void setResumenesMensualesDisponiblesByCuenta(
            Respuesta<List<ResumenMensualInversiones>> resumenesMensualesDisponibles, AbstractCuenta cuenta) {
        ResumenesMensualesDisponiblesInversiones resumenesMensualesDisponiblesInversiones = new ResumenesMensualesDisponiblesInversiones();
        resumenesMensualesDisponiblesInversiones.setCuenta(cuenta);
        resumenesMensualesDisponiblesInversiones.setResumenMensualInversiones(resumenesMensualesDisponibles);
        this.resumenesMensualesDisponibles.add(resumenesMensualesDisponiblesInversiones);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
     * SessionResumenInversiones#getResumenesByCuenta(ar.com.santanderrio.obp.
     * servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public Respuesta<List<ResumenMensualInversiones>> getResumenesByCuenta(AbstractCuenta cuenta) {
        if (resumenesMensualesDisponibles != null) {
            for (ResumenesMensualesDisponiblesInversiones resumenMensualDisponible : resumenesMensualesDisponibles) {
                if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
                    return resumenMensualDisponible.getResumenesMensualesInversiones();
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
     * SessionResumenInversiones#getResumenesByIds(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public List<ResumenMensualInversiones> getResumenesByIds(List<String> ids, AbstractCuenta cuenta) {
        List<ResumenMensualInversiones> resumenesInversionesEnSesion = null;
        List<ResumenMensualInversiones> resumenesMensuales = new ArrayList<ResumenMensualInversiones>();
        if (resumenesMensualesDisponibles != null) {
            // obtengo la lista de resumenes en sesion por cuenta
            for (ResumenesMensualesDisponiblesInversiones resumenMensualDisponible : resumenesMensualesDisponibles) {
                if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
                    resumenesInversionesEnSesion = resumenMensualDisponible.getResumenesMensualesInversiones()
                            .getRespuesta();
                }
            }

            // filtra la lista en sesion segun la cuenta por id
            for (String id : ids) {
                for (ResumenMensualInversiones resumenMensualInversiones : resumenesInversionesEnSesion) {
                    if (resumenMensualInversiones.getId().toString().equals(id)) {
                        resumenesMensuales.add(resumenMensualInversiones);
                        break;
                    }
                }
            }
        }
        return resumenesMensuales;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
     * SessionResumenInversiones#marcarVistos(java.util.List,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public void marcarVistos(List<ResumenMensualInversiones> resumenesVistos, AbstractCuenta cuenta) {
        List<ResumenMensualInversiones> resumenesEnSesion = getResumenesByCuenta(cuenta).getRespuesta();
        // Recorre todos los resumenes que vienen del front
        for (ResumenMensualInversiones resumenVisto : resumenesVistos) {
            // Busca el resumen que coincida con el que esta en sesion y lo
            // marca como visto
            for (ResumenMensualInversiones resumenEnSesion : resumenesEnSesion) {
                if (resumenVisto.getId().equals(resumenEnSesion.getId())) {
                    resumenEnSesion.setVisto(true);
                    break;
                }
            }
        }
    }
}
