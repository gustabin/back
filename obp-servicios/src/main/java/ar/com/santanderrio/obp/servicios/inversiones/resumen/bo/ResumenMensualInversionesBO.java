package ar.com.santanderrio.obp.servicios.inversiones.resumen.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenFinanciacion;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;

/**
 * The Interface ResumenMensualInversionesBO.
 */
public interface ResumenMensualInversionesBO {

    /**
     * Obtener lista resumen.
     *
     * @param cuenta
     *            the cuenta
     * @param isBP
     *            the is BP
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<List<ResumenMensualInversiones>> obtenerListaResumen(AbstractCuenta cuenta, boolean isBP) throws BusinessException;

    /**
     * Obtener resumen descarga multiple.
     *
     * @param resumenesSeleccionados
     *            the resumenes seleccionados
     * @param cuenta
     *            the cuenta
     * @param isBP
     *            the is BP
     * @param cantidadADescargar
     *            the cantidad A descargar
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<ReporteResumenMensualInversiones> obtenerResumenDescargaMultiple(
            List<ResumenMensualInversiones> resumenesSeleccionados, AbstractCuenta cuenta, boolean isBP, int cantidadADescargar)
            throws BusinessException;

    /**
     * Obtener resumenes PDF.
     *
     * @param resumenesSeleccionados
     *            the resumenes seleccionados
     * @param cuenta
     *            the cuenta
     * @param isBP
     *            the is BP
     * @return the respuesta
     */
    Respuesta<ReporteResumenMensualInversiones> obtenerResumenesPDF(
            List<ResumenMensualInversiones> resumenesSeleccionados, AbstractCuenta cuenta, boolean isBP);
    
    
    
    Respuesta<ReporteResumenFinanciacion> obtenerResumenFinancieroTenenciasPDF(ResumenFinancieroDTO dto);
    

}
