package ar.com.santanderrio.obp.servicios.getnet.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.getnet.bo.GetnetBO;
import ar.com.santanderrio.obp.servicios.getnet.dao.GetnetDAO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetConfiguracionOutDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetOutEntity;
import ar.com.santanderrio.obp.servicios.getnet.manager.GetnetManager;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetOutView;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;

/**
 * The Class GetnetManagerImpl.
 */
@Component
public class GetnetManagerImpl implements GetnetManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GetnetManagerImpl.class);

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The getnet BO. */
	@Autowired
	private GetnetBO getnetBO;
    
    /** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;
	
	/** The datos solicitante DAO. */
    @Autowired
    protected DatosSolicitanteDAO datosSolicitanteDAO;
    
    /** The getnet DAO. */
	@Autowired
	private GetnetDAO getnetDAO;
	
    /** The property map. */
	@Autowired
	private PropertyMap propertyMap;
	
	/** The edad minima. */
	@Value("${GETNET.EDAD.MINIMA}")
	private int getnetEdadMinima;
	
	/** The nacionalidad. */
	@Value("${GETNET.NACIONALIDAD}")
	private String getnetNacionalidad;
	
	/** The Constant COD_RETORNO_EXTENDIDO_CEROS. */
    private static final String COD_RETORNO_EXTENDIDO_CEROS = "00000000";
    
    /** The Constant COD_RETORNO_EXTENDIDO_CERO. */
    private static final String COD_RETORNO_EXTENDIDO_CERO = "0";
    
    /** The tipo cuentas habilitadas prefix. */
	private static String tipoCuentasHabilitadasGetnetPrefix = "GETNET.TIPO.CUENTAS.HABILITADAS.SOLICITUD";
	
	/** The Constant GETNET_ESTADO_ACTIVO. */
    public static final String GETNET_ESTADO_ACTIVO = "ACTIVE";
    
    /** The Constant GETNET_ESTADO_INACTIVO. */
    public static final String GETNET_ESTADO_INACTIVO = "INACTIVE";
    
	/** The Constant GETNET_BOTON_SOLICITUD. */
    public static final String GETNET_BOTON_SOLICITUD = "getnetSolicitud";

    /** The Constant GETNET_BOTON_ACTIVO. */
    public static final String GETNET_BOTON_ACTIVO = "getnetActivo";
    
    /** The Constant GETNET_BOTON_INACTIVO. */
    public static final String GETNET_BOTON_INACTIVO = "getnetInactivo";
    
    public static final String TODAS_LAS_NACIONALIDADES = "TODAS";
    
	/**
     * Devuelve los datos a mostrar en el stack de GetNet
     * Puede ser: solicitud / activo / inactivo
     *
     * @return the getnet out view
     */
	@Override
	public Respuesta<GetnetOutView> configuracion() {
		Respuesta<GetnetConfiguracionOutDTO> respuesta = new Respuesta<GetnetConfiguracionOutDTO>();
		GetnetOutView getNetView = new GetnetOutView();
		Respuesta<GetnetOutView> respuestaView = new Respuesta<GetnetOutView>();
		
		respuesta = getnetBO.configuracion();
		getNetView = parseRespuesta(respuesta.getRespuesta());
		respuestaView.setRespuesta(getNetView);
		respuestaView.setEstadoRespuesta(respuesta.getEstadoRespuesta());
		return respuestaView;
	}

	private GetnetOutView parseRespuesta(GetnetConfiguracionOutDTO getNetDTO) {
		GetnetOutView respuestaView = new GetnetOutView();
		String estado = sesionParametros.getEstadoGetNet();
		if (GETNET_BOTON_SOLICITUD.equalsIgnoreCase(estado)) {
			respuestaView.setEmail(getNetDTO.getEmail());
			respuestaView.setCodigoArea(getNetDTO.getCodigoArea());
			respuestaView.setCelular(getNetDTO.getCelular());
			respuestaView.setCuentas(getNetDTO.getCuentas());
			respuestaView.setActividades(getNetDTO.getActividades());
			respuestaView.setIngresos(getNetDTO.getIngresos());
			respuestaView.setLegal(getNetDTO.getLegal());
		} else if (GETNET_BOTON_ACTIVO.equalsIgnoreCase(estado)) {
			respuestaView.setUrl(getNetDTO.getSaltoURL());
			respuestaView.setEsGetnet(true);
		} else if (GETNET_BOTON_INACTIVO.equalsIgnoreCase(estado)) {
			respuestaView.setEmail(getNetDTO.getEmail());
			respuestaView.setLegal(getNetDTO.getLegal());
		}
		return respuestaView;
	}
    
	@Override
	public void loguearEstadisticas() {
		grabarEstadisticas(EstadisticasConstants.CODIGO_TRANSACCION_GETNET_SALTO_URL);
	}
    
    /**
     * Confirma la adhesion a GetNet
     *
     * @return the getnet adhesion out view
     */
    @Override
	public Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionInView view) {
    	GetnetAdhesionDTO dto = new GetnetAdhesionDTO(view);
    	Respuesta<GetnetAdhesionOutView> getNetAdhesionOutView = getnetBO.confirmarAdhesion(dto);
    	sesionParametros.setComprobanteGetnetDTO(dto);
		return getNetAdhesionOutView;
	}
    
    /**
     * Descarga comprobante adhesion
     *
     * @return the reporte view
     */
    @Override
    public Respuesta<ReporteView> descargaComprobanteAdhesion() {
    	Respuesta<Reporte> reporteRespuesta = getnetBO.descargaComprobanteAdhesion();
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }
        return respuestaView;
    }
	
    @Override
	public Boolean validarCard() {
		List<Cuenta> cuentasHabilitadas;
		Cliente cliente = sesionCliente.getCliente();
		Boolean mostrarCard = false;
		
		try {
			// Si no tiene las cuentas habilitadas en sesion, las intenta obtener y las setea si corresponde.
			if (sesionParametros.getCuentasHabilitadasGetnet() == null || sesionParametros.getCuentasHabilitadasGetnet().isEmpty()) {
				cuentasHabilitadas = obtenerCuentasHabilitadas(cliente, 
						getParamsCuentasHabilitadas(tipoCuentasHabilitadasGetnetPrefix, propertyMap), cuentaBO);
				sesionParametros.setCuentasHabilitadasGetnet(cuentasHabilitadas);		
			}		
			// Valida si tiene cuentas habilitadas.
			if (ISBANStringUtils.isNullOrEmpty(sesionParametros.getCuentasHabilitadasGetnet())) {
				grabarEstadisticas(COD_RETORNO_EXTENDIDO_CERO);
				return false;
			}
			// Valida si es mayor de edad.
			if (!esMayor18(cliente, getnetEdadMinima)) {
				grabarEstadisticas(COD_RETORNO_EXTENDIDO_CERO);
				return false;
			}
			
			DatosSolicitanteEntity datosSolicitante = new DatosSolicitanteEntity();
			// Busca la nacionalidad en sesion. Si no esta, la busca en el servicio CNSPERSFIS.
			datosSolicitante = buscarNacionalidad(cliente);
			// Valida si tiene la nacionalidad requerida
			String[] listaCodigosNacionalidades = getnetNacionalidad.split("\\|");
			for (String codigoNacionalidad : listaCodigosNacionalidades) {
				if (TODAS_LAS_NACIONALIDADES.equals(codigoNacionalidad) || datosSolicitante.getNacionalidad().equals(codigoNacionalidad)) {
					String nup = cliente.getNup();
					GetnetOutEntity respuestaCheck = new GetnetOutEntity();
					List<GetnetOutEntity> respuestaPersons = new ArrayList<GetnetOutEntity>();
					try {
						respuestaCheck = getnetDAO.check(nup);
						if (respuestaCheck.isEnabled()) {
							respuestaPersons = getnetDAO.getPersons(nup);
						}
						String estado = "";
						if (respuestaPersons != null && !respuestaPersons.isEmpty()) {
							// respuestaPersons recibe una lista de 1 solo elemento
							estado = respuestaPersons.get(0).getState();
						}
						if (!respuestaCheck.isEnabled()) {
							grabarEstadisticas(COD_RETORNO_EXTENDIDO_CERO);
						} else if (respuestaPersons == null) {
							grabarEstadisticas("");
						} else if (respuestaCheck.isEnabled() && respuestaPersons.isEmpty()) {
							// Si isEnabled y el respuestaPersons es vacio se muestra la Solicitud
							mostrarCard = true;
							sesionParametros.setEstadoGetNet(GETNET_BOTON_SOLICITUD);
						} else if (GETNET_ESTADO_ACTIVO.equals(estado)) {
							mostrarCard = true;
							sesionParametros.setEstadoGetNet(GETNET_BOTON_ACTIVO);
						} else if (GETNET_ESTADO_INACTIVO.equals(estado)) {
							mostrarCard = true;
							sesionParametros.setEstadoGetNet(GETNET_BOTON_INACTIVO);
							sesionParametros.setEmailGetNet(respuestaPersons.get(0).getEmail());
						}
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
						grabarEstadisticas("");
					}
				} else {
					grabarEstadisticas(COD_RETORNO_EXTENDIDO_CERO);
				}
			}
			return mostrarCard;
		} catch (Exception e) {
			grabarEstadisticas("");
            LOGGER.error("Error al validar usuario en GetNet", e);
            return false;
		}
	}
    
    public static void main(String[] args) {
		
    	String codigos = "TODAS";
    	
    	String[] listaCodigos = codigos.split("\\|");
    	
    	for (String codigo : listaCodigos) {
    		System.out.println("Codigo pais: " + codigo);
    	}
    	
	}
	
	public boolean esMayor18 (Cliente cliente, int edadMinima){
	    DateTime diaActual = new DateTime();
	    Date fechaNacimientoAux = FechaUtils.parsearFechaNacimiento(cliente.getFechaNacimiento(), "yyyyMMdd");
		DateTime fechaNacimiento = new DateTime(fechaNacimientoAux);
		Years years = Years.yearsBetween(fechaNacimiento, diaActual);
	    return years.getYears() >= edadMinima;
	}
	
	@Override
	public DatosSolicitanteEntity buscarNacionalidad(Cliente cliente) {
		DatosSolicitanteEntity datosSolicitante = new DatosSolicitanteEntity();
		if (sesionParametros.getDatosSolicitante() != null) {
			datosSolicitante.setNacionalidad(sesionParametros.getDatosSolicitante().getPaisNacimiento());
		} else {
			try {
	            datosSolicitante = obtenerDatosSolicitante(cliente);
	            datosSolicitante.setCalle(datosSolicitante.getCalle().trim());
	            datosSolicitante.setCalleNro(datosSolicitante.getCalleNro().trim());
	            datosSolicitante.setPiso(datosSolicitante.getPiso().trim());
	            datosSolicitante.setDepto(datosSolicitante.getDepto().trim());
	            datosSolicitante.setCp(datosSolicitante.getCp().trim());
	            datosSolicitante.setLocalidad(datosSolicitante.getLocalidad().trim());
	            sesionParametros.setDatosSolicitante(datosSolicitante);
	        } catch (Exception e) {
	        	grabarEstadisticas(datosSolicitante.getCodError().toString().trim());
	            LOGGER.error("Error al consultar datos del solicitante.", e);
	        }
		}
		return datosSolicitante;
	}
	
	/**
	 * Obtener cuentas habilitadas.
	 *
	 * @param cliente the cliente
	 * @param cuentaParamsHab the cuenta params hab
	 * @param cuentaBO2 
	 * @return the list
	 */
	public List<Cuenta> obtenerCuentasHabilitadas(Cliente cliente, List<String> cuentaParamsHab, CuentaBO cuentaBOAux) {
		List<Cuenta> cuentasHabilitadasOperacion = new ArrayList<Cuenta>();
		Integer contador = 0;
		if (cuentaParamsHab != null) {
			for (Cuenta cuenta : cliente.getCuentas()) {
				contador = contador + 1;
				if (evaluaCuenta(cliente, cuenta, cuentaParamsHab, cuentaBOAux)) {
					cuentasHabilitadasOperacion.add(cuenta);
				}
			}
			if (contador.equals(cliente.getCuentas().size())) {
				sesionParametros.setRecorrioCuentasGetnet(true);
			}
		}
		return cuentasHabilitadasOperacion;
	}
	
	/**
	 * Evalua cuenta.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaParamsHab the cuenta params hab
	 * @param cuentaBOAux 
	 * @return true, if successful
	 */
	
	private boolean evaluaCuenta(Cliente cliente, Cuenta cuenta, List<String> cuentaParamsHab, CuentaBO cuentaBOAux) {
		return !cuenta.isCuentaCerrada()
				&& matchCuentaParams(cliente, cuenta, cuentaParamsHab, cuentaBOAux);
	}
	
	/**
	 * Match cuenta params.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBO the cuenta BO
	 * @param cuentaParamsHab the cuenta params hab
	 * @param cuentaBOAux 
	 * @return true, if successful
	 */
	private boolean matchCuentaParams(Cliente cliente, Cuenta cuenta, List<String> cuentaParamsHab, CuentaBO cuentaBOAux) {
		boolean match = false;
		String monedaAltair = cuenta.getMonedaAltair();
		String codTitularidad = cuenta.getCodigoTitularidad();
		for (String ctaParHab : cuentaParamsHab) {
			StringTokenizer st = new StringTokenizer(ctaParHab, "\\|");
			String monedaAltairHab = st.nextToken().trim();
			String codTitularidadHab = st.nextToken().trim();
			String indSobregiroHab = "*";
			if (st.hasMoreTokens()) {
				indSobregiroHab = st.nextToken();
			}
			if (monedaAltairHab.equals(monedaAltair) && codTitularidadHab.equals(codTitularidad) 
					&& ("*".equals(indSobregiroHab) || evaluarIndSobregiro(cliente, cuenta, cuentaBOAux, indSobregiroHab))) {
				match = true;
				break;
			}
		}
		return match;
	}
	
	/**
	 * Evaluar ind sobregiro.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBOAux 
	 * @param indSobregiroHab the ind sobregiro hab
	 * @return true, if successful
	 */
	private boolean evaluarIndSobregiro(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBOAux, String indSobregiroHab) {
		return indSobregiroHab.equalsIgnoreCase(obtenerIndSobregiro(cliente, cuenta, cuentaBOAux));
	}
	
	/**
	 * Obtener ind sobregiro.
	 *
	 * @param cliente the cliente
	 * @param cuenta the cuenta
	 * @param cuentaBOAux 
	 * @return the string
	 */
	private String obtenerIndSobregiro(Cliente cliente, Cuenta cuenta, CuentaBO cuentaBOAux) {
		Respuesta<ResumenDetalleCuenta> respuestaDetalle = new Respuesta<ResumenDetalleCuenta>();
		ResumenDetalleCuenta respuestaDetalleAux = new ResumenDetalleCuenta();
		if (sesionParametros.getRespuestaDetalleCNSCTADATO() != null && sesionParametros.getRespuestaDetalleCNSCTADATO().size() > 0) {
			for (ResumenDetalleCuenta respuestaDetalleCheck : sesionParametros.getRespuestaDetalleCNSCTADATO()) {
				if (respuestaDetalleCheck.getCbu().equals(cuenta.getCbu())) {
					return respuestaDetalleCheck.getIndicadorSobregiro();	
				} 
			}	
		}
		try {
			respuestaDetalle = cuentaBOAux.obtenerCuenta(cliente,
					ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
		} catch (BusinessException e) {
            LOGGER.error("Error al obtener Indice de Sobregiro.", e);
			return "";
		}
		respuestaDetalleAux.setCbu(respuestaDetalle.getRespuesta().getCbu());
		respuestaDetalleAux.setIndicadorSobregiro(respuestaDetalle.getRespuesta().getIndicadorSobregiro());	
		sesionParametros.getRespuestaDetalleCNSCTADATO().add(respuestaDetalleAux);
		if (EstadoRespuesta.OK.equals(respuestaDetalle.getEstadoRespuesta())) {
			ResumenDetalleCuenta detalleCuenta = respuestaDetalle.getRespuesta();
			return detalleCuenta.getIndicadorSobregiro();
		} else {
			LOGGER.error("Error al obtener Indice de Sobregiro.");
		}
		return "";
	}
	
	/**
	 * Gets the params cuentas habilitadas.
	 *
	 * @param prefix the prefix
	 * @param propertyMap the property map
	 * @return the params cuentas habilitadas
	 */
	public static List<String> getParamsCuentasHabilitadas(String prefix, PropertyMap propertyMap) {
		StringBuilder tipos = new StringBuilder();
		Map<String, Object> props = propertyMap.getProperties();
        for (String key : props.keySet()) {
        	StringBuilder tipo = new StringBuilder();
        	String propValue = props.get(key).toString().trim();
        	if (key.contains(prefix) && "1".equals(propValue)) {
        		if (tipos.length() > 0) {
        			tipos.append(",");
        		}
        		String params = key.substring(prefix.length() + 1).trim();
        		tipo.append(params.replaceAll("\\.", "\\|"));
        		tipos.append(tipo);
        	}
        }
        return Arrays.asList(StringUtils.split(tipos.toString(), ','));
	}
	
    /**
     * Obtener datos solicitante.
     *
     * @return the datos solicitante entity
     * @throws DAOException
     *             the DAO exception
     * @throws SinAccesoALaInformacionException
     *             the sin acceso A la informacion exception
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    private DatosSolicitanteEntity obtenerDatosSolicitante(Cliente cliente)
            throws DAOException, SinAccesoALaInformacionException, OperacionFueraHorarioSucursalException {
        DatosSolicitanteCriterioEntity datosSolicitanteCriterio = new DatosSolicitanteCriterioEntity();
        datosSolicitanteCriterio.setDocTipo(cliente.getTipoDocumento());
        datosSolicitanteCriterio.setDoc(cliente.getDni());
        return datosSolicitanteDAO.getDatosDelSolicitante(datosSolicitanteCriterio, cliente);
    }
    
    /**
     * Grabar estadisticas de getNet.
     *
     * @param codigoError
     *            the codigo error
     * @param tipoOperacion
     *            the tipo operacion
     */
    @Override
	public void grabarEstadisticas(String codigoError) {
        if (COD_RETORNO_EXTENDIDO_CERO.equals(codigoError) || COD_RETORNO_EXTENDIDO_CEROS.equals(codigoError)) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_GETNET, 
            		EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_GETNET, 
            		EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
    }

	@Override
	public Respuesta<GetnetOutView> obtenerDatosGetnet() {
		this.validarCard();
		// Se resetean parametros en Sesion por si hay que actualizar la Card
		sesionParametros.setRecorrioCuentasGetnet(false);
		sesionParametros.setGetnetAgregado(false);
		Respuesta<GetnetOutView> config = this.configuracion();
		config.getRespuesta().setTipo(sesionParametros.getEstadoGetNet());
		return config;
	}
}
