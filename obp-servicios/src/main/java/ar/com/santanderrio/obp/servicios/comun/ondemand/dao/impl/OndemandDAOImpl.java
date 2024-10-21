package ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.ondemand.mapper.OnDemandMapper;
import ar.com.santanderrio.obp.servicios.comun.ondemand.utils.WSOnDemandClient;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.Resumen;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODRequest;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODResponse;
import ar.com.santanderrio.obp.servicios.ondemand.entities.XMLFile;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenFinanciacion;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;

/**
 * The Class OndemandDAOImpl.
 */
@Component
public class OndemandDAOImpl implements OndemandDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(OndemandDAOImpl.class);

    /** The Constant EXTENCION_PDF. */
    private static final String EXTENSION_PDF = ".pdf";

    /** The ONDEMAND_WS. */
    private static final String ONDEMAND_WS = "ONDEMAND.ENDPOINT";

    /** The ONDEMAND_ADVANCE. */
    private static final String ONDEMAND_ADVANCE = "ONDEMAND.ENDPOINT_ADVANCE";

    /** The ONDEMAND_RESUMEN_TARJETA. */
    private static final String ONDEMAND_RESUMEN_TARJETA = "RESUMENONDEMANDTRJ";

    /** The ONDEMAND_INV. */
    private static final String ONDEMAND_INV = "RESUMENONDEMANDINV";
    
    /** The ws On Demand Client. */
    @Autowired
    private WSOnDemandClient wsOnDemandClient;

    /** The on demand mapper. */
    @Autowired
    private OnDemandMapper onDemandMapper;
    
    private static final Integer MIN_RESPONSE_OK = 200;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerReporteMensual(ar.com.santanderrio.obp.servicios.resumen.entities.
     * ResumenMensualCuenta,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public ReporteResumenMensualCuenta obtenerReporteMensual(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta) throws WSODException {
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        try {
            WSODRequest request = onDemandMapper.mapearRequestReporteConsultaPuntual(resumenMensualCuenta, cuenta,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_PUNTUAL, OnDemandConstants.VARIABLE_PAQUETE);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            procesar(resumenMensualCuenta, reporteResumenMensualCuenta, wsodResponse, OnDemandConstants.NOMBRE_DE_ARCHIVO);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporteResumenMensualCuenta;
    }

    /**
     * Procesar.
     *
     * @param resumenMensualCuenta
     *            the resumen mensual cuenta
     * @param reporteResumenMensualCuenta
     *            the reporte resumen mensual cuenta
     * @param wsodResponse
     *            the wsod response
     * @throws WSODException
     *             the WSOD exception
     */
    private void procesar(ResumenMensualCuenta resumenMensualCuenta,
            ReporteResumenMensualCuenta reporteResumenMensualCuenta, WSODResponse wsodResponse, String nombreArchivo) throws WSODException {
        try {
            reporteResumenMensualCuenta.setNombre(nombreArchivo + " "
                    + ISBANStringUtils.formatearFecha(resumenMensualCuenta.getFecha(), "dd-MM-yyyy") + EXTENSION_PDF);
            reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
            reporteResumenMensualCuenta.setBytes(IOUtils.toByteArray(wsodResponse.getAttach()));
            
    		if (reporteResumenMensualCuenta.getBytes() == null  || reporteResumenMensualCuenta.getBytes().length < MIN_RESPONSE_OK) {
    			throw new WSODException(new Exception("Error consulta servicio fechas"));
    		}
    		
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

    /**
     * Procesar puntual.
     *
     * @param reporteSeleccionado
     *            the reporte seleccionado
     * @param reporteResumenPuntual
     *            the reporte resumen puntual
     * @param wsodResponse
     *            the wsod response
     * @throws WSODException
     *             the WSOD exception
     */
    private void procesarPuntual(ReporteSeleccionado reporteSeleccionado, ReporteResumenPuntual reporteResumenPuntual,
            WSODResponse wsodResponse) throws WSODException {
        try {
            reporteResumenPuntual.setFechaPuntual(reporteSeleccionado.getFechaPuntual());
            reporteResumenPuntual.setFolder(reporteSeleccionado.getFolder());
            String[] cadenaFolder = reporteSeleccionado.getFolder().split(" ");
            reporteResumenPuntual.setNombre(OnDemandConstants.NOMBRE_DE_ACHIVO_PUNTUAL + " " + cadenaFolder[0] + "-"
                    + ISBANStringUtils.formatearFechaDescarga(reporteSeleccionado.getFechaPuntual()) + EXTENSION_PDF);
            reporteResumenPuntual.setTipoArchivo(TipoArchivoEnum.PDF);
            reporteResumenPuntual.setBytes(IOUtils.toByteArray(wsodResponse.getAttach()));

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

	/**
	 * Procesar inversiones.
	 *
	 * @param resumen
	 *     the resumen
	 * @param reporte
	 *     the reporte
	 * @param wsodResponse
	 *     the wsod response
	 * @param isBP
	 *     the is BP
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void procesarInversiones(ResumenMensualInversiones resumen, ReporteResumenMensualInversiones reporte,
			WSODResponse wsodResponse, boolean isBP) throws WSODException {
		try {
			String nombreArchivo = isBP ? OnDemandConstants.NOMBRE_DE_ACHIVO_RESUMEN_INVERSIONES_BP
					: OnDemandConstants.NOMBRE_DE_ACHIVO_RESUMEN_INVERSIONES;
			reporte.setNombre(nombreArchivo + " " + resumen.getPeriodo() + EXTENSION_PDF);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(IOUtils.toByteArray(wsodResponse.getAttach()));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new WSODException(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Procesar inversiones.
	 *
	 * @param resumen
	 *     the resumen
	 * @param reporte
	 *     the reporte
	 * @param wsodResponse
	 *     the wsod response
	 * @param isBP
	 *     the is BP
	 * @throws WSODException
	 *     the WSOD exception
	 */
	private void procesarResumenFinanciero(ResumenFinancieroDTO dto, ReporteResumenFinanciacion reporte,
			WSODResponse wsodResponse) throws WSODException {
		try {
			String nombreArchivo = dto.isBancaPrivada() ? OnDemandConstants.NOMBRE_DE_ACHIVO_RESUMEN_INVERSIONES_BP
					: OnDemandConstants.NOMBRE_DE_ACHIVO_RESUMEN_INVERSIONES;
			reporte.setNombre(nombreArchivo + " " + dto.getPeriodo() + EXTENSION_PDF);
			reporte.setTipoArchivo(TipoArchivoEnum.PDF);
			reporte.setBytes(IOUtils.toByteArray(wsodResponse.getAttach()));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new WSODException(e.getMessage(), e);
		}
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerReporteMensualAdvance(ar.com.santanderrio.obp.servicios.resumen.
     * entities.ResumenMensualCuenta,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
     */
    @Override
    public ReporteResumenMensualCuenta obtenerReporteMensualAdvance(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta) throws WSODException {
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        try {
            WSODRequest request = onDemandMapper.mapearRequestReporteConsultaPuntual(resumenMensualCuenta, cuenta,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_PUNTUAL_PDE, OnDemandConstants.VARIABLE_PAQUETE_ADVANCE);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_ADVANCE);

            procesar(resumenMensualCuenta, reporteResumenMensualCuenta, wsodResponse, OnDemandConstants.NOMBRE_DE_ARCHIVO);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporteResumenMensualCuenta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerListaResumen(ar.com.santanderrio.obp.servicios.ondemand.entities.
     * ResumenParams)
     */
    @Override
    public List<ResumenMensualCuenta> obtenerListaResumen(ResumenParams params) throws WSODException {
        List<ResumenMensualCuenta> resumenCuentaList;
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenFechas(params,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_POR_FECHAS, OnDemandConstants.VARIABLE_PAQUETE);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            resumenCuentaList = mapearResponseResumenFechas(wsodResponse, false);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return resumenCuentaList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerListaResumenAdvance(ar.com.santanderrio.obp.servicios.ondemand.
     * entities.ResumenParams)
     */
    @Override
    public List<ResumenMensualCuenta> obtenerListaResumenAdvance(ResumenParams params) throws WSODException {
        List<ResumenMensualCuenta> resumenCuentaList;
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenFechas(params,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_POR_FECHAS_PDE, OnDemandConstants.VARIABLE_PAQUETE_ADVANCE);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_ADVANCE);
            resumenCuentaList = invertirListaResumenes(mapearResponseResumenFechas(wsodResponse, true));
            return resumenCuentaList;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }
            
    /**
     * Invertir lista resumenes.
     *
     * Se crea esta funcion para invertir el orden de los resumenes retornados para ADVANCE ya que
     * del servicio vienen ordenados de fecha mas antigua a mas nueva 20-02-19
     * @param resumenCuentaList the resumen cuenta list
     * @return the list
     */
    public List<ResumenMensualCuenta> invertirListaResumenes(List<ResumenMensualCuenta> resumenCuentaList) {
        List<ResumenMensualCuenta> reverse;         
        if (resumenCuentaList != null) {             
            reverse = new ArrayList<ResumenMensualCuenta>();             
            for (int i = resumenCuentaList.size()-1; i>=0; i--) {                 
                reverse.add(resumenCuentaList.get(i));
            }
        } else {
            reverse = null;
        }
        return reverse;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerListaResumenesAnterioresTarjeta(ar.com.santanderrio.obp.servicios.
     * ondemand.entities.ResumenParams)
     */
    @Override
    public List<ResumenMensualTarjetaDTO> obtenerListaResumenesAnterioresTarjeta(ResumenParams params)
            throws WSODException, OnDemandDAOException {
        List<ResumenMensualTarjetaDTO> resumenCuentaList;
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenesAnterioresTC(params,
                    OnDemandConstants.CNS_RESUMEN_POR_FECHAS_TC);
            LOGGER.info("RESUMENONDEMANDTRJ: REQUEST:" + request);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_RESUMEN_TARJETA);
            LOGGER.info("RESUMENONDEMANDTRJ: RESPONSE" + request);
            if (wsodResponseTieneError(wsodResponse)) {
                throw new OnDemandDAOException();
            }
            resumenCuentaList = mapearResponseResumenTarjetas(wsodResponse);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return resumenCuentaList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerResumenPuntualPDF(ar.com.santanderrio.obp.servicios.tarjetas.
     * entities.ReporteSeleccionado,
     * ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams)
     */
    @Override
    public ReporteResumenPuntual obtenerResumenPuntualPDF(ReporteSeleccionado reporteSeleccionado, ResumenParams params)
            throws WSODException, OnDemandDAOException {

        ReporteResumenPuntual reporteResumenPuntual = new ReporteResumenPuntual();
        try {
            WSODRequest request = onDemandMapper.mapearRequestDescargarResumenTC(params,
                    OnDemandConstants.CNS_RESUMEN_PUNTUAL_TC);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_RESUMEN_TARJETA);
            if (wsodResponseTieneError(wsodResponse)) {
                throw new OnDemandDAOException();
            }
            procesarPuntual(reporteSeleccionado, reporteResumenPuntual, wsodResponse);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporteResumenPuntual;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerMarcaImpresion(ar.com.santanderrio.obp.servicios.ondemand.entities
     * .ResumenParams)
     */
    @Override
    public String obtenerMarcaImpresion(ResumenParams params) throws WSODException, OnDemandDAOException {
        LOGGER.info("Consulta marca de Impresion.-");

        try {
            WSODRequest request = onDemandMapper.consultaMarcaImpresion(params, OnDemandConstants.CNS_MARCA_IMPRESION);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_RESUMEN_TARJETA);
            if (!WSODResponse.COD_RET_OK.equals(wsodResponse.getCodRet())) {
                LOGGER.error(
                        "Error Consulta Marca de Impresion :" + wsodResponse.getCodRet() + wsodResponse.getMsjError());
                throw new OnDemandDAOException();
            }
            LOGGER.info("FIN - Consulta marca de Impresion.-");
            return procesarMarcaImpresion(wsodResponse);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
     * obtenerMarcaImpresion(ar.com.santanderrio.obp.servicios.ondemand.entities
     * .ResumenParams)
     */
    @Override
    public void modificarMarcaImpresion(ResumenParams params) throws WSODException, OnDemandDAOException {
        LOGGER.info("Se modifica marca de Impresion:" + params.getSoporte());
        try {
            WSODRequest request = onDemandMapper.modificarMarcaImpresion(params,
                    OnDemandConstants.CAMBIO_MARCA_IMPRESION);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_RESUMEN_TARJETA);
            if (!WSODResponse.COD_RET_OK.equals(wsodResponse.getCodRet())) {
                LOGGER.error(
                        "Error Modificar Marca de Impresion :" + wsodResponse.getCodRet() + wsodResponse.getMsjError());
                throw new OnDemandDAOException();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        LOGGER.info("FIN - Modificacion marca de Impresion.");
    }

    /**
     * Procesa el xml para obtener la marca de impresion.
     *
     * @param wsodResponse
     *            the wsod response
     * @return the string
     * @throws WSODException
     *             the WSOD exception
     */
    private String procesarMarcaImpresion(WSODResponse wsodResponse) throws WSODException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XMLFile xmlFile = (XMLFile) jaxbUnmarshaller.unmarshal(wsodResponse.getAttach());
            return xmlFile.getDatos().getSoporte();
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }

    }

    /**
	 * Mapear response resumen fechas.
	 *
	 * @param wsodResponse
	 *            the wsod response
	 * @param advancePym
	 *            the advance pym
	 * @return the list
	 * @throws WSODException
	 *             the WSOD exception
	 */
    private List<ResumenMensualCuenta> mapearResponseResumenFechas(WSODResponse wsodResponse, boolean advancePym) throws WSODException {
        try {
            List<ResumenMensualCuenta> resumenCuentaList = new ArrayList<ResumenMensualCuenta>();
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XMLFile xmlFile = (XMLFile) jaxbUnmarshaller.unmarshal(wsodResponse.getAttach());
            if (xmlFile.getResponse() != null && xmlFile.getResponse().getResumenes() != null
                    && !xmlFile.getResponse().getResumenes().isEmpty()) {
                for (Resumen resumen : xmlFile.getResponse().getResumenes()) {
                    ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
                    // Ojo aca dale formato.
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    resumenMensualCuenta.setFecha(format.parse(resumen.getFecha()));
                    resumenMensualCuenta.setCarpeta(resumen.getFolder());
                    resumenMensualCuenta.setPaquete(resumen.getPaquete());
                    resumenMensualCuenta.setProducto(resumen.getProducto());
                    resumenMensualCuenta.setReferencia(resumen.getReferencia());
                    resumenMensualCuenta.setVisto(false);
                	resumenMensualCuenta.setAdvance(advancePym);
                	resumenMensualCuenta.setNroLiquidacion(resumen.getNroLiquidacion());
                	resumenCuentaList.add(resumenMensualCuenta);
                }

            }
            return resumenCuentaList;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

    /**
     * Mapear response resumen tarjetas.
     *
     * @param wsodResponse
     *            the wsod response
     * @return the list
     * @throws WSODException
     *             the WSOD exception
     */
    private List<ResumenMensualTarjetaDTO> mapearResponseResumenTarjetas(WSODResponse wsodResponse)
            throws WSODException {
        try {
            List<ResumenMensualTarjetaDTO> resumenTarjetaList = new ArrayList<ResumenMensualTarjetaDTO>();
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XMLFile xmlFile = (XMLFile) jaxbUnmarshaller.unmarshal(wsodResponse.getAttach());
            if (xmlFile.getResponse() != null && xmlFile.getResponse().getResumenes() != null
                    && !xmlFile.getResponse().getResumenes().isEmpty()) {
                for (Resumen resumen : xmlFile.getResponse().getResumenes()) {
                    ResumenMensualTarjetaDTO resumenMensualCuenta = new ResumenMensualTarjetaDTO();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                    resumenMensualCuenta.setFecha(format.parse(resumen.getFecha()));
                    resumenMensualCuenta.setCarpeta(resumen.getFolder());
                    resumenTarjetaList.add(resumenMensualCuenta);
                }

            }
            return resumenTarjetaList;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

    /**
     * Mapear response resumen fechas inversiones.
     *
     * @param wsodResponse
     *             the wsod response
     * @return the list
     * @throws WSODException
     *             the WSOD exception
     */
    private List<ResumenMensualInversiones> mapearResponseResumenFechasInversiones(WSODResponse wsodResponse) throws WSODException {
        try {
            List<ResumenMensualInversiones> resumenInversionesList = new ArrayList<ResumenMensualInversiones>();
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XMLFile xmlFile = (XMLFile) jaxbUnmarshaller.unmarshal(wsodResponse.getAttach());
            if (xmlFile.getResponse() != null && xmlFile.getResponse().getResumenes() != null
                    && !xmlFile.getResponse().getResumenes().isEmpty()) {
                for (Resumen resumen : xmlFile.getResponse().getResumenes()) {
                    ResumenMensualInversiones resumenMensualInversiones = new ResumenMensualInversiones();
                    resumenMensualInversiones.setFechaDesde(resumen.getFechaDesde());
                    resumenMensualInversiones.setFechaHasta(resumen.getFechaHasta());
                    resumenMensualInversiones.setCuenta(resumen.getCuenta());
                    resumenMensualInversiones.setPeriodo(resumen.getPeriodo());
                    resumenInversionesList.add(resumenMensualInversiones);
                }
            }
            return resumenInversionesList;
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
    }

    /**
     * Wsod response tiene error.
     *
     * @param response
     *            the response
     * @return the boolean
     */
    private Boolean wsodResponseTieneError(WSODResponse response) {
        if (StringUtils.equals(response.getCodRet(), WSODResponse.COD_RET_ERROR)) {
            return true;
        }
        return false;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
	 * obtenerListaResumenInversiones(ar.com.santanderrio.obp.servicios.ondemand.
	 * entities.ResumenParams, boolean)
	 */
    public List<ResumenMensualInversiones> obtenerListaResumenInversiones(ResumenParams params, boolean isBP)
            throws WSODException {
        List<ResumenMensualInversiones> resumenes;
        String serviceName = isBP ? OnDemandConstants.SERVICIO_CNS_TITULOS_POR_FECHAS_BP : OnDemandConstants.SERVICIO_CNS_TITULOS_POR_FECHAS;
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenFechasInversiones(params, serviceName);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_INV);
            resumenes = mapearResponseResumenFechasInversiones(wsodResponse);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return resumenes;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO#
	 * obtenerReporteMensualInversiones(ar.com.santanderrio.obp.servicios.resumen.
	 * entities.ResumenMensualInversiones,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta, boolean)
	 */
    @Override
    public ReporteResumenMensualInversiones obtenerReporteMensualInversiones(
            ResumenMensualInversiones resumen, AbstractCuenta cuenta, boolean isBP)
            throws WSODException {
        ReporteResumenMensualInversiones reporte = new ReporteResumenMensualInversiones();
        String serviceName = isBP ? OnDemandConstants.SERVICIO_CNS_TITULOS_PUNTUAL_BP : OnDemandConstants.SERVICIO_CNS_TITULOS_PUNTUAL;
        try {
            WSODRequest request = onDemandMapper.mapearRequestReporteConsultaPuntualInversiones(resumen, cuenta,
            		serviceName);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_INV);
            procesarInversiones(resumen, reporte, wsodResponse, isBP);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporte;
    }

	@Override
	public ReporteResumenFinanciacion obtenerResumenFinancieroTenenciasPDF(ResumenFinancieroDTO dto)
			throws WSODException {
		
		ReporteResumenFinanciacion reporte = new ReporteResumenFinanciacion();
        String serviceName = dto.isBancaPrivada() ? OnDemandConstants.SERVICIO_CNS_TITULOS_PUNTUAL_BP : OnDemandConstants.SERVICIO_CNS_TITULOS_PUNTUAL;
        
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenFinanciero(dto,serviceName);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_INV);
            procesarResumenFinanciero(dto, reporte, wsodResponse);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporte;
	}   
	
    @Override
    public List<ResumenMensualCuenta> obtenerListaResumenBP(ResumenParams params) throws WSODException {
        List<ResumenMensualCuenta> resumenCuentaList;
        try {
            WSODRequest request = onDemandMapper.mapearRequestResumenFechasBP(params, OnDemandConstants.SERVICIO_CNS_RESUMEN_POR_FECHAS_BP);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            resumenCuentaList = mapearResponseResumenFechas(wsodResponse, false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return resumenCuentaList;
    }
        
    @Override
    public List<ResumenMensualCuenta> obtenerListaComprobantesBancaPrivada(ResumenParams params) throws WSODException {
        List<ResumenMensualCuenta> resumenCuentaList;
        try {
            WSODRequest request = onDemandMapper.mapearRequestComprobantesTVBP(params, OnDemandConstants.SERVICIO_CNS_RESUMEN_POR_FECHAS_BSP);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            resumenCuentaList = mapearResponseResumenFechas(wsodResponse, false);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return resumenCuentaList;
    }
    
    @Override
    public ReporteResumenMensualCuenta obtenerReporteMensualBP(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta) throws WSODException {
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        try {
            WSODRequest request = onDemandMapper.mapearRequestReporteConsultaPuntualBP(resumenMensualCuenta, cuenta,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_PUNTUAL_BP);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            procesar(resumenMensualCuenta, reporteResumenMensualCuenta, wsodResponse, OnDemandConstants.NOMBRE_DE_ARCHIVO_BP);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporteResumenMensualCuenta;
    }
    
    @Override
    public ReporteResumenMensualCuenta obtenerReporteComprobantesBP(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta, TipoPDFEnum tipoPDF) throws WSODException {
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        try {
            WSODRequest request = onDemandMapper.mapearRequestComprobantesBP(resumenMensualCuenta, cuenta,
                    OnDemandConstants.SERVICIO_CNS_RESUMEN_PUNTUAL_BSP, tipoPDF);
            WSODResponse wsodResponse = wsOnDemandClient.send(request, ONDEMAND_WS);

            procesar(resumenMensualCuenta, reporteResumenMensualCuenta, wsodResponse, 
            		TipoPDFEnum.TITULOS_VALORES.equals(tipoPDF) ? 
            		OnDemandConstants.NOMBRE_DE_ARCHIVO_COMPROBANTE_TV_BP :
            		OnDemandConstants.NOMBRE_DE_ARCHIVO_COMPROBANTE_FCI_BP);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new WSODException(e.getMessage(), e);
        }
        return reporteResumenMensualCuenta;
    }
    
}
