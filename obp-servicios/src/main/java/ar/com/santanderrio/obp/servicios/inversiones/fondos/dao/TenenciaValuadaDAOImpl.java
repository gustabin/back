/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CarteraTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaProductoOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleCustodiaOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetalleTenenciaValuadaPFEntity;

/**
 * The Class TenenciaValuadaDAOImpl.
 */
@Component
public class TenenciaValuadaDAOImpl implements TenenciaValuadaDAO {

	/**
	 * The environment.
	 */
	@Autowired
	private Environment environment;

	/** The path tenencia valuada detalle fondo online. */
	private static String pathTenenciaValuadaDetalleFondoOnline = "/ObtenerTenenciaValuadaDetalleFondoOnline/";

	/** The path tenencia valuada detalle fondo online. */
	private static String pathTenenciaValuadaDetallePFOnline = "/ObtenerTenenciaValuadaDetallePFOnline/";

	/** The path tenencia valuada detalle fondo online. */
	private static String pathTenenciaValuadaCuentaProductoOnline = "/ObtenerTenenciaValuadaCuentaProductoOnline/";

	/** The path tenencia valuada de cartera total online. */
	private static String pathTenenciaValuadaCarteraTotalOnline = "/ObtenerTenenciaValuadaCarteraTotalOnline/";

	/** The path tenencia valuada de cartera total online. */
	private static String pathTenenciaValuadaDetalleTitulosOnline = "/ObtenerTenenciaValuadaDetalleTitulosOnline/";
	
	/** The path tenencia valuada detalle cust online. */
	private static String pathTenenciaValuadaDetalleCustOnline = "/ObtenerTenenciaValuadaDetalleCustOnline/";

	/** The charset */
	private static final String CHARSET_UTF8 = ";charset=UTF-8";

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "INVERSIONES.TENENCIAS";

	/**
	 * The Constant CORE_HOLDING.
	 */
	private static final String CORE_HOLDING = "CORE.HOLDING";

	/**
	 * The Constant CORE_SWITCH_URL.
	 */
	private static final String CORE_SWITCH_URL = "CORE.SWITCH";

	/**
	 * The Constant CORE_SWITCH_OBP_ID.
	 */
	private static final String CORE_SWITCH_OBP_ID = "CORE.SWITCH.OBP.ID";

	/**
	 * The Constant CORE_SWITCH_RESOURCE.
	 */
	private static final String CORE_SWITCH_RESOURCE = "CORE.SWITCH.RESOURCE";

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TenenciaValuadaDAOImpl.class);

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The subcanal tipo. */
	@Value("${FONDOS.SUBCANALTIPO}")
	private String subcanalTipo;

	/** The canal version. */
	@Value("${FONDOS.CANALVERSION}")
	private String canalVersion;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	/** The canal tipo. */
	@Value("${FONDOS.CANALTIPO}")
	private String canalTipo;

	/** The subcanal id. */
	@Value("${FONDOS.SUBCANALID}")
	private String subcanalId;

	/** The canal id. */
	@Value("${FONDOS.CANALID}")
	private String canalId;

	/**
	 * Cargo en el request los datos default que se definen en el properties.
	 *
	 * @param datosServicios
	 *            the datos servicios
	 * @return the datos servicios entity
	 */
	private DatosServiciosEntity cargarDatosDefault(DatosServiciosEntity datosServicios) {
		datosServicios.setCanalTipo(canalTipo);
		datosServicios.setCanalId(canalId);
		datosServicios.setCanalVersion(canalVersion);
		datosServicios.setSubcanalTipo(subcanalTipo);
		datosServicios.setSubcanalId(subcanalId);
		return datosServicios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.
	 * TenenciaValuadaDAO#obtenerTenenciaValuadaDetalleFondoOnline(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.entity.
	 * DetalleFondoRequestEntity)
	 */
	@Override
	public DetalleTenenciaValuadaEntity obtenerTenenciaValuadaDetalleFondoOnline(DetalleFondoRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);
        
		DetalleTenenciaValuadaEntity rta = new DetalleTenenciaValuadaEntity();
		try {
			LOGGER.info("Se procede a consultar PyL para RTL como contingencia");
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaDetalleFondoOnline);
			rta = tenenciaValuadaService.post(request, DetalleTenenciaValuadaEntity.class);
			if (rta == null || rta.getCodigo() != 0 && rta.getCodigo() != 1) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error("Error llamando al servicio de tenencia valuada");
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error("Error llamando al servicio de tenencia valuada");
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	@Override
	public DetalleTenenciaValuadaEntity obtenerTenenciaValuadaDetalleFondoOnlineBP(DetalleFondoRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datos = request.getDatos().getDatosServicios();

		request.getDatos().setDatosServicios(cargarDatosDefault(datos));
		request.setFirma(generarFirma());
		request.setDato(dato);

		DetalleTenenciaValuadaEntity respuesta = new DetalleTenenciaValuadaEntity();

		try {
			LOGGER.info("Se procede a consultar PyL para BP como contingencia");
			WebClient tenenciaValuadaServiceBP = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaServiceBP.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaServiceBP.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaServiceBP.path(pathTenenciaValuadaDetalleFondoOnline);
			respuesta = tenenciaValuadaServiceBP.post(request, DetalleTenenciaValuadaEntity.class);
			if (respuesta == null || respuesta.getCodigo() != 0 && respuesta.getCodigo() != 1) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error("Error llamando al servicio de tenencia valuada para BP");
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error("Error llamando al servicio de tenencia valuada para BP");
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.
	 * TenenciaValuadaDAO#obtenerTenenciaValuadaDetalleFondoOnline(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.entity.
	 * DetalleFondoRequestEntity)
	 */
	@Override
	public CarteraTenenciaValuadaEntity obtenerTenenciaValuadaCarteraTotalOnline(
			TenenciaValuadaCarteraRequestEntity request) throws DAOException {
		String urlPath = request.getDatos().getCodigoProducto().equals("TOD") && (validarPermisos(request.getDatos().getNup())) ? CORE_HOLDING : NOMBRE_WS;
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);

		CarteraTenenciaValuadaEntity rta = new CarteraTenenciaValuadaEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(urlPath);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaCarteraTotalOnline);
			rta = tenenciaValuadaService.post(request, CarteraTenenciaValuadaEntity.class);
			if (rta == null || (rta.getCodigo() != 0 && rta.getCodigo() != 1)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DetalleTenenciaValuadaPFEntity obtenerTenenciaValuadaDetallePFOnline(DetalleFondoRequestEntity request)
			throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);
		DetalleTenenciaValuadaPFEntity rta = new DetalleTenenciaValuadaPFEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);

			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaDetallePFOnline);
			rta = tenenciaValuadaService.post(request, DetalleTenenciaValuadaPFEntity.class);
			if (rta == null || rta.getCodigo() != 0 && rta.getCodigo() != 1) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.
	 * TenenciaValuadaDAO#obtenerTenenciaValuadaCuentaProductoOnline(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.entity.
	 * DetalleFondoRequestEntity)
	 */
	@Override
	public CuentaProductoOnlineEntity obtenerTenenciaValuadaCuentaProductoOnline(DetalleFondoRequestEntity request)
			throws DAOException {
		String urlPath = validarPermisos(request.getDatos().getNup()) ? CORE_HOLDING : NOMBRE_WS;
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);
		CuentaProductoOnlineEntity rta = new CuentaProductoOnlineEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(urlPath);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaCuentaProductoOnline);
			rta = tenenciaValuadaService.post(request, CuentaProductoOnlineEntity.class);
			if (rta == null || rta.getCodigo() < 0 || rta.getCodigo() > 1) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/**
	 * genera la firma para llamar a los servicios de P&L.
	 *
	 * @return the string
	 */
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma para " + pathTenenciaValuadaCuentaProductoOnline, e);
			}
		}
		return firma;
	}

	/**
	 * valida permisos del nup para llamar a los servicios de P&L.
	 *
	 * @return the boolean
	 */
	private boolean validarPermisos(String nup) throws DAOException {
		boolean result;
		try {
			WebClient apiSwitchService = restWebClient.obtenerClienteRest(CORE_SWITCH_URL);
			apiSwitchService.accept(MediaType.APPLICATION_JSON);
			apiSwitchService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			apiSwitchService.path("/access");
			apiSwitchService.query("key", nup);
			apiSwitchService.query("resource", environment.getProperty(CORE_SWITCH_RESOURCE));
			apiSwitchService.query("clientId", environment.getProperty(CORE_SWITCH_OBP_ID));
			Response response = apiSwitchService.get(Response.class);
			result = response.getStatus() == Response.Status.OK.getStatusCode();
		} catch (ResponseProcessingException e) {
			throw new DAOException();
		} catch (ProcessingException e) {
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.
	 * TenenciaValuadaDAO#obtenerTenenciaValuadaDetalleTitulosOnline(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.entity.
	 * DetalleFondoRequestEntity)
	 */
	@Override
	public DetalleTenenciaValuadaTitulosEntity obtenerTenenciaValuadaDetalleTitulosOnline(
			DetalleFondoRequestEntity request) throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);
		DetalleTenenciaValuadaTitulosEntity rta = new DetalleTenenciaValuadaTitulosEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaDetalleTitulosOnline);
			rta = tenenciaValuadaService.post(request, DetalleTenenciaValuadaTitulosEntity.class);
			if (rta.getDatos() == null || rta.getCodigo() < 0 || rta.getCodigo() > 1) {
				LOGGER.info("Error codigo de respuesta del WS INVERSIONES.TENENCIAS Distinto de OK");
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error("Error ResponseProcessingException ", e);
			throw new DAOException(e);
		} catch (ProcessingException e) {
			LOGGER.error("Error ProcessingException ", e);
			throw new DAOException(e);
		} catch (RuntimeException e) {
			LOGGER.error("Error RuntimeException ", e);
			throw new DAOException(e);
		}
		return rta;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TenenciaValuadaDAO#obtenerTenenciaValuadaDetalleCustOnline(ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity)
	 */
	@Override
	public DetalleCustodiaOnlineEntity obtenerTenenciaValuadaDetalleCustOnline(
			DetalleFondoRequestEntity request) throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios));
		request.setFirma(generarFirma());
		request.setDato(dato);
		DetalleCustodiaOnlineEntity respuesta = new DetalleCustodiaOnlineEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET_UTF8)
					.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path(pathTenenciaValuadaDetalleCustOnline);
			respuesta = tenenciaValuadaService.post(request, DetalleCustodiaOnlineEntity.class);
			if (respuesta.getDatos() == null || respuesta.getCodigo() < 0) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			throw new DAOException(e);
		} catch (ProcessingException e) {
			throw new DAOException(e);
		} catch (RuntimeException e) {
			throw new DAOException(e);
		}
		return respuesta;
	}
}
