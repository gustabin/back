/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.tenencias.entity.FirmantesOutEntity;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasOutEntity;

/**
 * The Interface TenenciasDAO.
 */
public interface TenenciasDAO {

    /**
     * Se invoca al SP <br>
     * 
     * PROCEDURE PRIN ( <br>
     * cue_cv IN OUT cuecurtyp, <br>
     * cus_cv IN OUT cuscurtyp, <br>
     * fco_cv IN OUT fcocurtyp, <br>
     * fre_cv IN OUT frecurtyp, <br>
     * imp_cv IN OUT impcurtyp, <br>
     * pft_cv IN OUT pftcurtyp, <br>
     * pre_cv IN OUT precurtyp, <br>
     * p_nup IN VARCHAR2, <br>
     * p_anio_desde IN VARCHAR2, <br>
     * p_anio_hasta IN VARCHAR2, <br>
     * coti_afip_usd OUT NUMBER); .
     *
     * @param nup
     *            the nup
     * @param anioDesde
     *            the anio desde
     * @param anioHasta
     *            the anio hasta
     * @return the tenencias out entity
     * @throws DAOException
     *             the DAO exception
     */
    TenenciasOutEntity obtenerTenecias(String nup, String anioDesde, String anioHasta) throws DAOException;

    /**
     * Se invoca al SP <br>
     * 
     * PROCEDURE FIRDET ( <br>
     * fir_cue_cv IN OUT fircuecurtyp, <br>
     * fir_cus_cv IN OUT fircuscurtyp, <br>
     * fir_fco_cv IN OUT firfcocurtyp, <br>
     * fir_pfi_cv IN OUT firfrecurtyp, <br>
     * fir_pre_cv IN OUT firprecurtyp, <br>
     * p_nup IN VARCHAR2, <br>
     * p_anio_desde IN VARCHAR2, <br>
     * p_anio_hasta IN VARCHAR2); .
     *
     * @param nup
     *            the nup
     * @param anioDesde
     *            the anio desde
     * @param anioHasta
     *            the anio hasta
     * @return the firmantes out entity
     * @throws DAOException
     *             the DAO exception
     */
    FirmantesOutEntity obtenerFirmantes(String nup, String anioDesde, String anioHasta) throws DAOException;
    
}
