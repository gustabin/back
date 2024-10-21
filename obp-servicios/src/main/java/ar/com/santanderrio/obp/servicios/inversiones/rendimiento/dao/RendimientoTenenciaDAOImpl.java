/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DetalleRentabilidadTotalEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendimientoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoTenenciaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity;

/**
 * The Class TenenciaValuadaDAOImpl.
 */
@Component
public class RendimientoTenenciaDAOImpl implements RendimientoTenenciaDAO {

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "INVERSIONES.RENDIMIENTO";

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RendimientoTenenciaDAOImpl.class);

	/** The canal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALTIPO}")
	private String canalTipo;

	/** The canal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALTIPO.BP}")
	private String canalTipoBP;

	/** The canal id. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALID}")
	private String canalId;

	/** The canal version. */
	@Value("${INVERSIONES.RENDIMIENTO.CANALVERSION}")
	private String canalVersion;

	/** The subcanal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.SUBCANALTIPO}")
	private String subcanalTipo;

	/** The subcanal tipo. */
	@Value("${INVERSIONES.RENDIMIENTO.SUBCANALTIPO.BP}")
	private String subcanalTipoBP;

	/** The subcanal id. */
	@Value("${INVERSIONES.RENDIMIENTO.SUBCANALID}")
	private String subcanalId;

	/** The usuarioTipo. */
	@Value("${INVERSIONES.RENDIMIENTO.USUARIOTIPO}")
	private String usuarioTipo;

	/** The tipoPersona. */
	@Value("${INVERSIONES.RENDIMIENTO.TIPOPERSONA}")
	private String tipoPersona;

	/** The usuarioAtrib. */
	@Value("${INVERSIONES.RENDIMIENTO.USUARIOATRIB}")
	private String usuarioAtrib;

	/** The dato. */
	@Value("${INVERSIONES.RENDIMIENTO.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.RENDIMIENTO.FIRMAR:true}")
	private String firmar;

	/** The Constant ERROR_OBTENER_RENDIMIENTABILIDAD_PERIODO. */
	private static final String ERROR_OBTENER_RENDIMIENTABILIDAD_PERIODO = "Error llamando al servicio de Rentabilidad por periodo.";

	/** The Constant ERROR_OBTENER_RENDIMIENTO_TENENCIA. */
	private static final String ERROR_OBTENER_RENDIMIENTO_TENENCIA = "Error llamando al servicio de rendimiento tenencia.";

	/** The Constant ERROR_OBTENER_RENTABILIDAD_TOTAL. */
	private static final String ERROR_OBTENER_RENTABILIDAD_TOTAL = "Error llamando al servicio de Rentabilidad Total.";

	/** The Constant ERROR_OBTENER_FILTRO_POR_FECHA. */
	private static final String ERROR_OBTENER_FILTRO_POR_FECHA = "Error llamando al servicio de Filtro por fecha.";

	/** The Constant ERROR_OBTENER_DETALLE_SUBCLASIFICACION. */
	private static final String ERROR_OBTENER_DETALLE_SUBCLASIFICACION = "Error llamando al servicio de Detalle Subclasificacion.";

	/** The Constant ERROR_OBTENER_DETALLE_MOVIMIENTOS_PERIODO. */
	private static final String ERROR_OBTENER_DETALLE_MOVIMIENTOS_PERIODO = "Error llamando al servicio de Detalle Movimientos Periodo.";

	/** The Constant CHARSET. */
	private static final String CHARSET = ";charset=UTF-8";

	/** The Constant BANCA_PRIVADA. */
	private static final String BANCA_PRIVADA = "BP";

	/**
	 * Cargo en el request los datos default que se definen en el properties.
	 *
	 * @param datosServicios
	 *            the datos servicios
	 * @param isBancaPrivada
	 *            the is banca privada
	 * @return the datos servicios entity
	 */
	private DatosServiciosEntity cargarDatosDefault(DatosServiciosEntity datosServicios, boolean isBancaPrivada) {
		datosServicios.setCanalTipo(isBancaPrivada ? canalTipoBP : canalTipo);
		datosServicios.setCanalId(canalId);
		datosServicios.setCanalVersion(canalVersion);
		datosServicios.setSubcanalTipo(isBancaPrivada ? subcanalTipoBP : subcanalTipo);
		datosServicios.setSubcanalId(subcanalId);
		datosServicios.setUsuarioTipo(usuarioTipo);
		datosServicios.setUsuarioAtrib(usuarioAtrib);
		datosServicios.setTipoPersona(tipoPersona);

		return datosServicios;
	}

	/**
	 * Genera la firma para llamar a los servicios de P&L.
	 * 
	 * @return the string
	 */
	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma" , e);
			}
		}
		return firma;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerRendimientoConsolidado(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoConsolidadoRequestEntity)
	 */
	/*
	 * Obtener rendimiento de la tenencia segun producto solicitado
	 */
	@Override
	public RendimientoTenenciaEntity obtenerRendimientoConsolidado(RendimientoConsolidadoRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		RendimientoTenenciaEntity rta = new RendimientoTenenciaEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerRendimientoConsolidado/");
			rta = tenenciaValuadaService.post(request, RendimientoTenenciaEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerRentabilidadTotal(ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity)
	 */
	@Override
	public DetalleRentabilidadTotalEntity obtenerRentabilidadTotal(RentabilidadTotalRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		DetalleRentabilidadTotalEntity rta = new DetalleRentabilidadTotalEntity();
		try {
			WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
			rendimientoConsolidadoService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			rendimientoConsolidadoService.path("/ObtenerRentabilidadTotal/");
			rta = rendimientoConsolidadoService.post(request, DetalleRentabilidadTotalEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getRentTotalPeriodoMoneda() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3 && rta.getCodigo() != 4)) {
				throw new DAOException();
			} else if (rta.getCodigo() == 4) {
				throw new DAOException("NO_HAY_INVERSIONES");
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENTABILIDAD_TOTAL);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENTABILIDAD_TOTAL);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerFiltroPorFecha(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity)
	 */
	@Override
	public FiltroPorFechaEntity obtenerFiltroPorFecha(ObtenerFiltroPorFechaRequestEntity request) throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		FiltroPorFechaEntity rta = new FiltroPorFechaEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerFiltroPorFecha/");
			rta = tenenciaValuadaService.post(request, FiltroPorFechaEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_FILTRO_POR_FECHA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_FILTRO_POR_FECHA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerRentabilidadPeriodo(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity)
	 */
	@Override
	public RentabilidadPeriodoEntity obtenerRentabilidadPeriodo(RentabilidadPeriodoRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		RentabilidadPeriodoEntity rta = new RentabilidadPeriodoEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerRentabilidadPeriodo/");
			rta = tenenciaValuadaService.post(request, RentabilidadPeriodoEntity.class);
			if (rta == null || rta.getDatos().getResultado() == null
					|| rta.getDatos().getResultado().getResultadoPorClasificacion() == null || rta.getDatos() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTABILIDAD_PERIODO);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTABILIDAD_PERIODO);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerFiltroCartera(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity)
	 */
	@Override
	public FiltroCarteraEntity obtenerFiltroCartera(ObtenerFiltroCarteraRequestEntity request) throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		FiltroCarteraEntity rta = new FiltroCarteraEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerFiltroCartera/");
			rta = tenenciaValuadaService.post(request, FiltroCarteraEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerDetalleRentabilidad(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadRequestEntity)
	 */
	@Override
	public DetalleRentabilidadEntity obtenerDetalleRentabilidad(DetalleRentabilidadRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		DetalleRentabilidadEntity rta = new DetalleRentabilidadEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerDetalleRentabilidad/");
			rta = tenenciaValuadaService.post(request, DetalleRentabilidadEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 )) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerAperturaGrafica(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity)
	 */
	@Override
	public AperturaGraficaEntity obtenerAperturaGrafica(AperturaGraficaRequestEntity request) throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		AperturaGraficaEntity rta = new AperturaGraficaEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerAperturaGrafica/");
			rta = tenenciaValuadaService.post(request, AperturaGraficaEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerGraficoRendimiento(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity)
	 */
	@Override
	public GraficoRendimientoEntity obtenerGraficoRendimiento(AperturaGraficaRequestEntity request)
			throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		GraficoRendimientoEntity rta = new GraficoRendimientoEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerGraficoRendimiento/");
			rta = tenenciaValuadaService.post(request, GraficoRendimientoEntity.class);
			if (rta == null || rta.getCodigo() != 0) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerGraficoRentabilidad(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRequestEntity)
	 */
	@Override
	public GraficoRentabilidadEntity obtenerGraficoRentabilidad(GraficoRentabilidadRequestEntity request)
			throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		GraficoRentabilidadEntity rta = new GraficoRentabilidadEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerGraficoRentabilidad/");
			rta = tenenciaValuadaService.post(request, GraficoRentabilidadEntity.class);
			if (rta == null || rta.getCodigo() != 0) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerDetalleSubclasificacion(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionRequestEntity)
	 */
	@Override
	public DetalleSubclasificacionEntity obtenerDetalleSubclasificacion(DetalleSubclasificacionRequestEntity request)
			throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		DetalleSubclasificacionEntity rta = new DetalleSubclasificacionEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerDetalleSubclasificacion/");
			rta = tenenciaValuadaService.post(request, DetalleSubclasificacionEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3 )) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_DETALLE_SUBCLASIFICACION);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_DETALLE_SUBCLASIFICACION);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerDetalleMovimientosPeriodo(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoRequestEntity)
	 */
	@Override
	public DetalleMovimientosPeriodoEntity obtenerDetalleMovimientosPeriodo(
			DetalleMovimientosPeriodoRequestEntity request) throws DAOException {
		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		DetalleMovimientosPeriodoEntity rta = new DetalleMovimientosPeriodoEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerDetalleMovimientosPeriodo/");
			rta = tenenciaValuadaService.post(request, DetalleMovimientosPeriodoEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_DETALLE_MOVIMIENTOS_PERIODO);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_DETALLE_MOVIMIENTOS_PERIODO);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerFiltroComparativo(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoRequestEntity)
	 */
	@Override
	public FiltroComparativoEntity obtenerFiltroComparativo(FiltroComparativoRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		FiltroComparativoEntity rta = new FiltroComparativoEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerFiltroComparativo/");
			rta = tenenciaValuadaService.post(request, FiltroComparativoEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAO#obtenerComparativaCartera(ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraRequestEntity)
	 */
	@Override
	public ComparativaCarteraEntity obtenerComparativaCartera(ComparativaCarteraRequestEntity request)
			throws DAOException {

		DatosServiciosEntity datosServicios = request.getDatos().getDatosServicios();
		request.getDatos().setDatosServicios(cargarDatosDefault(datosServicios,
				BANCA_PRIVADA.equalsIgnoreCase(request.getDatos().getSegmento()) ? true : false));
		request.setFirma(generarFirma());
		request.setDato(dato);

		ComparativaCarteraEntity rta = new ComparativaCarteraEntity();
		try {
			WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest(NOMBRE_WS);
			tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.type(MediaType.APPLICATION_JSON + CHARSET).accept(MediaType.APPLICATION_JSON);
			tenenciaValuadaService.path("/ObtenerComparativaCartera/");
			rta = tenenciaValuadaService.post(request, ComparativaCarteraEntity.class);
			if (rta == null || rta.getDatos() == null || rta.getDatos().getResultado() == null
					|| (rta.getCodigo() != 0 && rta.getCodigo() != 1 && rta.getCodigo() != 3)) {
				throw new DAOException();
			}
		} catch (ResponseProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (ProcessingException e) {
			LOGGER.error(ERROR_OBTENER_RENDIMIENTO_TENENCIA);
			throw new DAOException();
		} catch (RuntimeException e) {
			throw new DAOException();
		}
		return rta;
	}

}
