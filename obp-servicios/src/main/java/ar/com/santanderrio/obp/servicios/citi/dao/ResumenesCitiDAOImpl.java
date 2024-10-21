/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.citi.entities.ResumenFechaOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandConstants;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ResumenesCitiDAOImpl.
 */
@Component
public class ResumenesCitiDAOImpl implements ResumenesCitiDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResumenesCitiDAOImpl.class);

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

	/** The resumen ondemand citi master fecha. */
	private static String RESUMEN_ONDEMAND_TARJETA_CITI = "RESUMEN.ONDEMAND.TARJETA.CITI";

	/** The Constant CANAL_OBP. */
    private static final String CANAL_OBP = "OBP";

	/** The Constant SUBCANAL_OBP. */
    private static final String SUBCANAL_OBP = "99";

	/** The Constant SUCURSAL. */
	private static final String SUCURSAL = "999";

	/** The resumen ondemand citi. */
	private static String RESUMEN_ONDEMAND_CITI = "RESUMEN.ONDEMAND.CITI";

	/** The Constant EXTENCION_PDF. */
	private static final String EXTENCION_PDF = ".pdf";

    /** The Constant MIN_RESPONSE_OK. */
    private static final Integer MIN_RESPONSE_OK = 200;

	/** The Constant RESUMEN_TARJETA_MASTERCARD. */
	private static final String RESUMEN_TARJETA_MASTERCARD = "Resumen de tarjeta de crédito (Mastercard)";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO#
	 * consultarFechasTarjetasPorCuenta(ar.com.santanderrio.obp.servicios.citi.
	 * entities.FechasResumenCitiIn)
	 */
	@Override
	public List<ResumenMensualTarjetaDTO> consultarFechasTarjetas(FechasResumenCitiIn fechasResumenCitiIn, String marca)
			throws DAOException {

		Map<String, String> request = cargaParametrosTarjeta(fechasResumenCitiIn);
		List<ResumenMensualTarjetaDTO> fechasResumenCitiOut = null;
		LOGGER.info("Invocacion WS: {}.", request);
		String path = obtenerPathSegunMarca(marca);
		WebClient service = this.obtenerClienteRestList(path);

		service.path("fechas");

		try {
			Response fechasResumenCitiOutr = service.post(request);
			Object entity = fechasResumenCitiOutr.getEntity();

			InputStream is = InputStream.class.cast(entity);
			if (is != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				StringBuilder stringBuilder = new StringBuilder();
				String str = null;
				while ((str = br.readLine()) != null) {
					if (!(stringBuilder.length() == 0 && str.trim().isEmpty())) {
						stringBuilder.append(str);
					}
				}
				String json = stringBuilder.toString();
				Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy").create();

				fechasResumenCitiOut = Arrays.asList(gson.fromJson(json, ResumenMensualTarjetaDTO[].class));
			}
			LOGGER.info("Respuesta WS: {}.", fechasResumenCitiOut);
		} catch (WebApplicationException e) {
			LOGGER.error("Respuesta ER del WS: {}.", e);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error("Respuesta ER del WS: {}.", e); 
			throw new DAOException();
		} catch (IOException e) {
			LOGGER.error("Respuesta ER del WS: {}.", e);
			throw new DAOException();
		} catch (Exception e) {
			LOGGER.error("Respuesta ER del WS: {}.", e);
			throw new DAOException(e);
		}
		return fechasResumenCitiOut;
	}

	/**
	 * Obtener path segun marca.
	 *
	 * @param marca
	 *            the marca
	 * @return the string
	 */
	private String obtenerPathSegunMarca(String marca) {
		if (marca.equals(TarjetaUtils.MARCA_MASTERCARD_AUX)) {
			return ".MASTER";
		} else {
			return ".VISA";
		}
	}

	/**
	 * Obtener cliente rest.
	 *
	 * @param path
	 *            the path
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */

	private WebClient obtenerClienteRestList(String path) throws DAOException {
		WebClient cliente = restWebClient.obtenerClienteRest(RESUMEN_ONDEMAND_TARJETA_CITI + path);
		cliente.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		return cliente;
	}

	/**
	 * Carga parametros tarjeta.
	 *
	 * @param fechasResumenCitiIn
	 *            the fechas resumen citi in
	 * @return the map
	 */
	private Map<String, String> cargaParametrosTarjeta(FechasResumenCitiIn fechasResumenCitiIn) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("canal", CANAL_OBP);
		parametros.put("subCanal", SUBCANAL_OBP);
		//parametros.put("sucursal", SUCURSAL); US 134740
		parametros.put("sucursal", StringUtils.EMPTY);
		parametros.put("documento", StringUtils.EMPTY);
		parametros.put("tarjeta", fechasResumenCitiIn.getTarjeta());
		parametros.put("vencimiento", fechasResumenCitiIn.getFechaCierreDesde());
		parametros.put("vencimiento2", fechasResumenCitiIn.getFechaCierreHaste());
		parametros.put("cuenta", StringUtils.EMPTY);

		return parametros;
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO#consultarFechasPorCuenta(ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn)
     */
    @Override
    public List<ResumenFechaOutEntity> consultarFechasPorCuenta(FechasResumenCitiIn fechasResumenCitiIn)
            throws DAOException {

        Map<String, String> request = cargaParametrosCuenta(fechasResumenCitiIn);
        List<ResumenFechaOutEntity> fechasResumenCitiOut = null;
        LOGGER.info("Invocacion WS: {}.", request);

        WebClient service = this.obtenerClienteRest();

        service.path("fechas");

        try {
            Response fechasResumenCitiOutr = service.post(request);
            Object entity = fechasResumenCitiOutr.getEntity();

            InputStream is = InputStream.class.cast(entity);
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder stringBuilder = new StringBuilder();
                String str = null;
                while ((str = br.readLine()) != null) {
                    if (!(stringBuilder.length() == 0 && str.trim().isEmpty())) {
                        stringBuilder.append(str);
                    }
                }
                String json = stringBuilder.toString();
                Gson gson = new Gson();
                fechasResumenCitiOut = Arrays.asList(gson.fromJson(json, ResumenFechaOutEntity[].class));
            }
            LOGGER.info("Respuesta WS: {}.", fechasResumenCitiOut);
        } catch (WebApplicationException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (ProcessingException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (IOException e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException();
        } catch (Exception e) {
            LOGGER.error("Respuesta ER del WS: {}.", e);
            throw new DAOException(e);
        }
        return fechasResumenCitiOut;
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO#consultarResumenCityPorId(ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta)
     */
    @Override
    public ReporteResumenMensualCuenta consultarResumenCityPorId(ResumenMensualCuenta resumenMensualCuenta)
            throws DAOException, WSODException {

        Map<String, String> request = cargaParametrosResumen(resumenMensualCuenta.getDocId());
        LOGGER.info("Invocacion WS: {}.", request);
        WebClient service = restWebClient.obtenerClienteRest(RESUMEN_ONDEMAND_CITI);
        service.type(MediaType.APPLICATION_JSON);
        service.path("puntual");
        try {
            Response is = service.post(request);
			String nombreArchivo = obtenerNombre(resumenMensualCuenta);
			return procesar(nombreArchivo, (SequenceInputStream) is.getEntity());
        } catch (WebApplicationException e) {
            LOGGER.info("Respuesta OK del WS: {}.", e);
        } catch (ProcessingException e) {
            LOGGER.info("Respuesta OK del WS: {}.", e);
        }
        throw new DAOException("");
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO#consultarResumenesTarjetaMastercard(ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado, java.lang.String)
	 */
	@Override
	public ReporteResumenPuntual consultarResumenesTarjetaMastercard(ReporteSeleccionado reporteSeleccionado, String marca)
			throws OnDemandDAOException, WSODException {

		Map<String, String> request = cargaParametrosResumen(reporteSeleccionado.getDocId());
		LOGGER.info("Invocacion WS: {}.", request);
        try {
			String path = obtenerPathSegunMarca(marca);
			WebClient service = restWebClient.obtenerClienteRest(RESUMEN_ONDEMAND_TARJETA_CITI + path);
			service.type(MediaType.APPLICATION_JSON);
			service.path("puntual");
			Response is = service.post(request);
			String nombreArchivo = obtenerNombre(reporteSeleccionado);
			return procesarResumenTarjeta(nombreArchivo, (SequenceInputStream) is.getEntity());
		} catch (WebApplicationException e) {
			LOGGER.info("Respuesta OK del WS: {}.", e);
		} catch (ProcessingException e) {
			LOGGER.info("Respuesta OK del WS: {}.", e);
		} catch (DAOException e) {
			LOGGER.info("Respuesta OK del WS: {}.", e);
    }
		throw new OnDemandDAOException();
    }

    /**
	 * Carga parametros cuenta.
	 *
	 * @param fechasResumenCitiIn
	 *            the fechas resumen citi in
	 * @return the map
	 */
    private Map<String, String> cargaParametrosCuenta(FechasResumenCitiIn fechasResumenCitiIn) {
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("sucursal", fechasResumenCitiIn.getSucursal());
        parametros.put("documento", fechasResumenCitiIn.getDocumento());
        parametros.put("cuenta", fechasResumenCitiIn.getCuenta());
        parametros.put("fecha_de_cierre_de_periodo", fechasResumenCitiIn.getFechaCierreDesde());
        parametros.put("fecha_de_cierre_de_periodo2", fechasResumenCitiIn.getFechaCierreHaste());
        return parametros;
    }

	/**
	 * Obtener cliente rest.
	 *
	 * @return the web client
	 * @throws DAOException
	 *             the DAO exception
	 */
	private WebClient obtenerClienteRest() throws DAOException {
		WebClient cliente = restWebClient.obtenerClienteRest(RESUMEN_ONDEMAND_CITI);
		cliente.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		return cliente;
	}

    /**
	 * Carga parametros resumen.
	 *
	 * @param docId
	 *            the doc id
	 * @return the map
	 */
    private Map<String, String> cargaParametrosResumen(String docId) {
        Map<String, String> parametros = new HashMap<String, String>();
        parametros.put("docId", docId);
        parametros.put("subCanal", SUBCANAL_OBP);
        parametros.put("canal", CANAL_OBP);
        return parametros;
    }

	/**
	 * Procesar.
	 *
	 * @param nombreArchivo
	 *            the nombre archivo
	 * @param is
	 *            the is
	 * @return the reporte resumen mensual cuenta
	 * @throws WSODException
	 *             the WSOD exception
	 */
	@SuppressWarnings("null")
	private ReporteResumenMensualCuenta procesar(String nombreArchivo, InputStream is) throws WSODException {

		ReporteResumenMensualCuenta rep = null;
		try {

			rep = new ReporteResumenMensualCuenta();
			rep.setNombre(nombreArchivo + EXTENCION_PDF);
			rep.setTipoArchivo(TipoArchivoEnum.PDF);
			rep.setBytes(IOUtils.toByteArray(is));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new WSODException(e.getMessage(), e);
		}
		if (rep.getBytes() == null || rep.getBytes().length < MIN_RESPONSE_OK) {
			throw new WSODException(new Exception("Error consulta servicio fechas"));
		}
		return rep;
	}

	/**
	 * Procesar resumen tarjeta.
	 *
	 * @param nombreArchivo
	 *            the nombre archivo
	 * @param is
	 *            the is
	 * @return the reporte resumen puntual
	 * @throws WSODException
	 *             the WSOD exception
	 */
	@SuppressWarnings("null")
	private ReporteResumenPuntual procesarResumenTarjeta(String nombreArchivo, InputStream is) throws WSODException {

		ReporteResumenPuntual rep = null;
		try {

			rep = new ReporteResumenPuntual();
			rep.setNombre(nombreArchivo + EXTENCION_PDF);
			rep.setTipoArchivo(TipoArchivoEnum.PDF);
			rep.setBytes(IOUtils.toByteArray(is));
		} catch (IOException e) { 
			LOGGER.error(e.getMessage(), e);
			throw new WSODException(e.getMessage(), e);
		}
		if (rep.getBytes() == null || rep.getBytes().length < MIN_RESPONSE_OK) {
			throw new WSODException(new Exception("Error consulta servicio fechas"));
		}
		return rep;
	}

    /**
     * Obtener nombre.
     *
     * @param resumenMensualCuenta the resumen mensual cuenta
     * @return the string
     */
    private String obtenerNombre(ReporteSeleccionado resumenMensualCuenta) {

        String fechaString = "";
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formatoFechaSalida = new SimpleDateFormat(
                    "dd-MM-yyyy");
            if(ISBANStringUtils.isEmptyOrNull(resumenMensualCuenta.getFechaPuntual())) {
                throw new ParseException("Error consulta servicio fechas", 0);
            }
            Date fecha = formatoFecha.parse(resumenMensualCuenta
                    .getFechaPuntual().replaceAll("/", "-"));
            fechaString = formatoFechaSalida.format(fecha);
        } catch (ParseException e) {
            LOGGER.error("Error al formatear la fecha.", e);
        }
        return RESUMEN_TARJETA_MASTERCARD + " " + fechaString;
    }

    /**
	 * Obtener nombre.
	 *
	 * @param resumenMensualCuenta
	 *            the resumen mensual cuenta
	 * @return the string
	 */
    private String obtenerNombre(ResumenMensualCuenta resumenMensualCuenta) {
		return OnDemandConstants.NOMBRE_DE_ARCHIVO + " "
				+ ISBANStringUtils.formatearFecha(resumenMensualCuenta.getFecha(), "dd-MM-yyyy");
	}

}
