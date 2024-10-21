/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.CodigoSistemaLoadOrdenesEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.EnumFondosDisponiblesTipoOperacion;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao.OrdenDAOBpriv;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.Orden;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenBaseDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenPlazoFijoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.ConfirmarOrdenPFView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;

/**
 * The Class OrdenesBOImpl.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @see {@link OrdenesBO}
 * @since Mon 23, 2017
 */
@Component
public class OrdenesBOImpl extends InversionesAbstractBO implements OrdenesBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesBOImpl.class);

    /** The Constant ERROR_SP. */
    private static final String ERROR_SP = "Ha ocurrido un error al ejecutar el SP BCAINV.PKG_BP_ONLINE_BANKING.load_ordenes";

    /** The Constant ERROR_SP. */
    private static final String ERROR_CUENTAS = "No se pudieron cargar las cuentas privadas del usuario";

    /** The Constant ESTADO_EJECUTADAS. */
    private static final String ESTADO_EJECUTADA = "EJECUTADA";

    /** The Constant STRING_VACIO. */
    private static final String STRING_VACIO = "";

    /** The Constant OPERACIONES_EJECUTADAS. */
    private static final String OPERACIONES_EJECUTADAS = "E";
    
    /** The Constant FC. */
    private static final String FC = "FC";

    /** The Constant CODIGOS_SINCERAMIENTO. */
    private static final ArrayList<String> CODIGOS_SINCERAMIENTO = new ArrayList<String>(
            Arrays.asList("2011", "2016", "2017", "2018", "2019", "2021"));

    /** The Constant ATIT. */
    private static final String ATIT = "ATIT";

    /** The Constant LARGO_COD_FONDO. */
    private static final int LARGO_COD_FONDO = 3;

	/** The Constant PUEDE_CONFIRMAR. */
	private static final String PUEDE_CONFIRMAR = "S";
	
    /** The Constant CONFIRMACION_ORDEN_CORRECTA. */
	private static final String CONFIRMACION_ORDEN_CORRECTA = "10537";
	
	/** The Constant ERROR_NO_CONTEMPLADO_CONFIRMACION_ORDEN. */
	private static final String ERROR_NO_CONTEMPLADO_CONFIRMACION_ORDEN = "10538";
	
	/** The Constant ORDENES. */
	private static final String ORDENES = "O";
	
	/** The Constant PATTERN_FECHA. */
	private static final String PATTERN_FECHA = "dd-MM-yyyy";
	

	/** The ordenes DAO. */
	@Autowired
	OrdenDAOBpriv ordenesDAO;

    /** The respuesta factory. */
    @Autowired
    RespuestaFactory respuestaFactory;

    /** The mensaje BO. */
    @Autowired
    MensajeBO mensajeBO;

	/** The consulta fondo DAO. */
	@Autowired
	ConsultaFondoDAO consultaFondoDAO;
	
	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The formatter. */
	SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_FECHA);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO#
     * iniciarOrdenesOperaciones()
     */
    @Override
    public Respuesta<OrdenesDTO> iniciarOrdenesOperaciones(Cliente cliente) {

		Iterator<Cuenta> iteratorCuentas = cliente.getCuentasPrivadas().iterator();
		Cuenta cuenta = iteratorCuentas.next();
		OrdenesDTO ordenesDTO = new OrdenesDTO();
		Respuesta<OrdenesDTO> respuesta = null;
		try {
			cargarCuentasEnDTO(ordenesDTO, cliente);
			OrdenInEntity ordenInEntity = new OrdenInEntity();
			ordenInEntity.setCuenta(obtenerNroCuentaOperativaFormateado(cuenta.getNroCuentaProducto()));
			ordenInEntity.setCodigoSis(FC);
			ordenInEntity.setFechaDesde(formatter.format(obtenerFechaDesde(29)));
			ordenInEntity.setFechaHasta(formatter.format(new Date()));
			ordenInEntity.setOperaciones(STRING_VACIO);
			OrdenOutEntity resultado = ordenesDAO.cargarOrdenes(ordenInEntity);
			if (resultado.getOrdenes().isEmpty()) {
				return respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
						CodigoMensajeConstantes.INVERSIONES_SIN_ORDENES_Y_OPERACIONES);
			}
			ordenesDTO.setOrdenes(cargarOrdenesDesdeLaRespuesta(resultado));
			respuesta = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
			Collections.sort(ordenesDTO.getOrdenes());
			respuesta.setRespuesta(ordenesDTO);
			respuesta.setRespuestaVacia(false);

        } catch (DAOException e) {
            LOGGER.error(ERROR_SP, e);
            return getRespuestaConErrorGenerico();

        }

        return respuesta;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO#
	 * buscarOrdenesOperacionesPorCuenta(java.lang.String)
	 */
	@Override
	public Respuesta<OrdenesDTO> buscarOrdenesOperacionesPorCuenta(String numeroCuenta, String codigoSist) {
		FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
		filtrosOrdenesView.setCuentaSeleccionada(obtenerNroCuentaOperativaFormateado(numeroCuenta));
		filtrosOrdenesView.setTipoSeleccionado(STRING_VACIO);
		int diasAtras = CodigoSistemaLoadOrdenesEnum.fromCodigoSistema(codigoSist).getDiasAtrasPorDefecto();
		filtrosOrdenesView.setFechaDesde(formatter.format(obtenerFechaDesde(diasAtras)));
		filtrosOrdenesView.setFechaHasta(formatter.format(new Date()));
		return buscarOrdenesOperaciones(filtrosOrdenesView, codigoSist);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO#
	 * buscarOrdenesOperaciones(ar.com.santanderrio.obp.servicios.inversiones.
	 * ordenes.web.view.FiltrosOrdenesView)
	 */
	@Override
	public Respuesta<OrdenesDTO> buscarOrdenesOperaciones(FiltrosOrdenesView filtrosOrdenesView, String codigoSist) {
		OrdenesDTO ordenesDTO = new OrdenesDTO();
		Respuesta<OrdenesDTO> respuesta = null;
		try {
			OrdenInEntity ordenInEntity = new OrdenInEntity();
			ordenInEntity.setCuenta(obtenerNroCuentaOperativaFormateado(filtrosOrdenesView.getCuentaSeleccionada()));
			ordenInEntity.setCodigoSis(codigoSist);
			cargarFechasDesdeLaVista(ordenInEntity, filtrosOrdenesView);
			ordenInEntity.setOperaciones(ORDENES.equals(filtrosOrdenesView.getTipoSeleccionado()) ? STRING_VACIO : filtrosOrdenesView.getTipoSeleccionado());
			OrdenOutEntity resultado = ordenesDAO.cargarOrdenes(ordenInEntity);
			ordenesDTO.setOrdenes(obtenerOrdenesSegunCodigoSist(codigoSist, resultado, filtrosOrdenesView.getTipoSeleccionado()));
			respuesta = respuestaFactory.crearRespuestaOk(OrdenesDTO.class);
			Collections.sort(ordenesDTO.getOrdenes());
			respuesta.setRespuesta(ordenesDTO);
			respuesta.setRespuestaVacia(false);

        } catch (DAOException e) {
            LOGGER.error(ERROR_SP, e);
            return getRespuestaConErrorGenerico();

        }
        return respuesta;
    }

    /**
     * Cargar fechas desde la vista en base a los filtros seleccionados. Solo se
     * admite un filtro por vez, o periodo o fechas.
     *
     * @param ordenInEntity
     *            the orden in entity
     * @param filtrosOrdenesView
     *            the filtros ordenes view
     */
    private void cargarFechasDesdeLaVista(OrdenInEntity ordenInEntity, FiltrosOrdenesView filtrosOrdenesView) {
        String periodoSeleccionado = filtrosOrdenesView.getPeriodoSeleccionado();
        if (periodoSeleccionado != null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, Integer.parseInt(periodoSeleccionado));
            Date desdeFecha = cal.getTime();
            ordenInEntity.setFechaDesde(formatter.format(desdeFecha));
            ordenInEntity.setFechaHasta(formatter.format(new Date()));
        } else if (filtrosOrdenesView.getFechaDesde() != null && filtrosOrdenesView.getFechaHasta() != null){
            ordenInEntity.setFechaDesde(filtrosOrdenesView.getFechaDesde());
            ordenInEntity.setFechaHasta(filtrosOrdenesView.getFechaHasta());
        } else {
    		DateTime fecha = new DateTime();
    		DateTimeFormatter fmt = DateTimeFormat.forPattern(PATTERN_FECHA);
    		DateTime fechaTreintaDiasMenos = fecha.minusDays(30);
    		ordenInEntity.setFechaDesde(fmt.print(fechaTreintaDiasMenos));
    		ordenInEntity.setFechaHasta(fmt.print(fecha));
        }
    }

	/**
	 * Cargar ordenes desde respuesta.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the list
	 */
	private List<OrdenBaseDTO> cargarOrdenesDesdeLaRespuesta(OrdenOutEntity respuesta) {
		return entityToDtoOrdenesFondos(respuesta, null);
	}

	/**
	 * Cargar ordenes desde respuesta.
	 * 
	 * Si el tipo es un STRING_VACIO, se filtran las ordenes cuyo estado sea
	 * "EJECUTADA"
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param filtroEstado
	 *            the tipo
	 * @return the list
	 */
	private List<OrdenBaseDTO> entityToDtoOrdenesFondos(OrdenOutEntity respuesta, String filtroEstado) {
		List<OrdenBaseDTO> ordenesBase = new ArrayList<OrdenBaseDTO>();

		List<Orden> ordenesFiltradas = respuesta.getOrdenes();
		if (filtroEstado != null && !filtroEstado.equals(OPERACIONES_EJECUTADAS)) {
			filtraDeLaListaLosEjecutados(ordenesFiltradas, ORDENES);
		} else if (filtroEstado != null) {
			filtraDeLaListaLosEjecutados(ordenesFiltradas, OPERACIONES_EJECUTADAS);
		}
		for (Orden orden : ListUtil.safe(ordenesFiltradas)) {
			String nombreFondo = null;
			try {
				nombreFondo = consultaFondoDAO.obtenerPorCodigo(StringUtils.leftPad(
						StringUtils.right(orden.getNroFondo().toString(), LARGO_COD_FONDO), LARGO_COD_FONDO, "0"))
						.getNombreFondo();
			} catch (DAOException e) {
				LOGGER.error("Error recuperando datos del fondo", e);
			}
			OrdenDTO ordenDTO = new OrdenDTO(orden.getNroOrden(), orden.getFechaOrden(), orden.getEstado().trim(),
					EnumFondosDisponiblesTipoOperacion.buscarPorCodigo(orden.getTipoOrden()).getDescripcion(),
					nombreFondo, orden.getCapital().toString());
			ordenesBase.add((OrdenBaseDTO) ordenDTO);
		}
		return ordenesBase;
	}

	/**
	 * Convierte la respuesta de ordenes de entity a dto segun si son ordenes de
	 * fondos u ordenes de plazos fijos, de acuerdo al codigo de sistema
	 * indicado.
	 *
	 * @param codigoSist
	 *            the codigo sist
	 * @param respuesta
	 *            the respuesta
	 * @param filtroEstado
	 *            the filtro estado
	 * @return the list
	 */
	private List<OrdenBaseDTO> obtenerOrdenesSegunCodigoSist(String codigoSist, OrdenOutEntity respuesta, String filtroEstado){
		if(CodigoSistemaLoadOrdenesEnum.FONDO.getCodigoSistema().equals(codigoSist)){
			return entityToDtoOrdenesFondos(respuesta, filtroEstado);
		}else if (CodigoSistemaLoadOrdenesEnum.PLAZO_FIJO.getCodigoSistema().equals(codigoSist)) {
			return entiyToDtoOrdenesPlazosFijos(respuesta);
		}
		String logError = "Codigo sistema servicio load ordenes desconocido."; 
		LOGGER.error(logError);
		return null;
	}

	/**
	 * Entiy to dto ordenes plazos fijos.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the list
	 */
	private List<OrdenBaseDTO> entiyToDtoOrdenesPlazosFijos(OrdenOutEntity respuesta) {
		
		List<OrdenBaseDTO> listaOrdenes = new ArrayList<OrdenBaseDTO>();
		
		for (Orden ordenEntity : respuesta.getOrdenes()) {
			
			OrdenPlazoFijoDTO ordenPFDto = new OrdenPlazoFijoDTO();
			ordenPFDto.setAccionAlVencimiento(ordenEntity.getAccionVto());
			ordenPFDto.setCapital(ISBANStringUtils.formatearConComaYDosDecimales(ordenEntity.getCapital().toString()));
			ordenPFDto.setEstado(ordenEntity.getEstado());
			ordenPFDto.setFecha(ordenEntity.getFechaOrden());
			ordenPFDto.setFechaLiquidacion(ordenEntity.getFechaLiq());
			ordenPFDto.setMonedaLiq(ordenEntity.getMonedaLiq());
			ordenPFDto.setNumero(ordenEntity.getNroOrden());
			ordenPFDto.setPlazo(ordenEntity.getPlazo());
			ordenPFDto.setTipoPlazoFijo(ordenEntity.getDescripcionEspecie());
			ordenPFDto.setTna(ordenEntity.getTna());
			
			if(PUEDE_CONFIRMAR.equalsIgnoreCase(ordenEntity.getRespaldable())){
				ordenPFDto.setPuedeConfirmar(true);
			}else{
				ordenPFDto.setPuedeConfirmar(false);
			}
			
			listaOrdenes.add((OrdenBaseDTO) ordenPFDto);
		}
		return listaOrdenes;
	}

    /**
	 * Filtra de la lista los ejecutados.
	 *
	 * @param lista
	 *            the lista
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the list
	 */
    private void filtraDeLaListaLosEjecutados(List<Orden> lista, String tipoOperacion) {
        Iterator<Orden> it = lista.iterator();
        while (it.hasNext()) {
            Orden orden = it.next();
            if (ORDENES.equals(tipoOperacion) && orden.getEstado().trim().equalsIgnoreCase(ESTADO_EJECUTADA) ||
            	OPERACIONES_EJECUTADAS.equals(tipoOperacion) && !orden.getEstado().trim().equalsIgnoreCase(ESTADO_EJECUTADA)) {
                it.remove();
            }
        }
    }
    
    /**
     * Cargar cuentas en DTO.
     *
     * @param ordenesDTO
     *            the ordenes DTO
     * @param cliente
     *            the cliente
     */
    private void cargarCuentasEnDTO(OrdenesDTO ordenesDTO, Cliente cliente) {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        for (Cuenta cuenta : ListUtil.safe(cliente.getCuentasPrivadas())) {
            if (!CODIGOS_SINCERAMIENTO.contains(cuenta.getCodigoPaquete())
                    && !cuenta.getCodigoAplicacion().equalsIgnoreCase(ATIT)) {
                cuentas.add(cuenta);
                obtenerIntervinientes(cuenta);
            }
        }
        ordenesDTO.setCuentas(cuentas);
        if (CollectionUtils.isEmpty(cuentas)) {
            ordenesDTO.setNumeroCuentaSeleccionada(cuentas.get(0).getNroCuentaProducto());
        } else {
            LOGGER.debug(ERROR_CUENTAS);
        }

    }

    /**
     * Obtener nro cuenta operativa formateado para consumir el SP. Ej: de una
     * cuenta 250-354880/2 se obtiene 7003547236
     * 
     * Formateo: "7" + llenarConCerosIzqOTruncar(NroCuentaProducto(),9);
     *
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @return the string
     */
    private String obtenerNroCuentaOperativaFormateado(String nroCuentaProducto) {
    	String logInfoObtenerNroCuenta = "Formateando numero de cuenta del cliente"; 
    	LOGGER.info(logInfoObtenerNroCuenta);
        BigDecimal nro = new BigDecimal(nroCuentaProducto);
        return "7" + llenarConCerosIzqOTruncar(nro.toString(), 9);
    }

	/**
	 * Obtener fecha "desde" por defecto.
	 * 
	 * Se debe mostrar por default, los ultimos 60 dias
	 *
	 * @param diasAtras
	 *            the dias atras
	 * @return the date
	 */
	private Date obtenerFechaDesde(int diasAtras) {
		Calendar cal = Calendar.getInstance();
		diasAtras = diasAtras * -1;
		cal.add(Calendar.DATE, diasAtras);
		return cal.getTime();
	}

    /**
     * Gets the respuesta con error generico.
     *
     * @return the respuesta con error generico
     */
    private Respuesta<OrdenesDTO> getRespuestaConErrorGenerico() {
        Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(mensaje.getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        Respuesta<OrdenesDTO> respuesta = new Respuesta<OrdenesDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.add(itemMensajeRespuesta);
        return respuesta;
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.
	 * InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return null;
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.ordenes.bo.OrdenesBO#confirmarOrden(ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity)
	 */
	@Override
	public Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity confirmarOrdenInEntity) {
		
		boolean permiteReintentar;
		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			String errorContador = "Error, Contador no inicializado!!";
			LOGGER.debug(errorContador);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}		
		ConfirmarOrdenOutEntity rtaDAO;		
		try {
			ConfirmarOrdenInEntity confOrdenInEntity = new ConfirmarOrdenInEntity();
			confOrdenInEntity.setCuenta(obtenerNroCuentaOperativaFormateado(confirmarOrdenInEntity.getCuenta()));
			confOrdenInEntity.setNumeroOrden(confirmarOrdenInEntity.getNumeroOrden());
			confOrdenInEntity.setCodigoSist(confirmarOrdenInEntity.getCodigoSist());
			confOrdenInEntity.setRegMant(confirmarOrdenInEntity.getRegMant());
			confOrdenInEntity.setCapital(confirmarOrdenInEntity.getCapital());
			confOrdenInEntity.setTipoPlazoFijo(confirmarOrdenInEntity.getTipoPlazoFijo());
			confOrdenInEntity.setPlazo(confirmarOrdenInEntity.getPlazo());
			rtaDAO = ordenesDAO.confirmarOrden(confOrdenInEntity);

		} catch (DAOException e) {
			return manejarReintento(confirmarOrdenInEntity, permiteReintentar, e);
		}
		return manejarCasoFeliz(confirmarOrdenInEntity, rtaDAO);
	}

	
	/**
	 * Manejar caso feliz.
	 *
	 * @param confirmarOrdenInEntity
	 *            the confirmar orden in entity
	 * @param rtaDAO
	 *            the rta DAO
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenPFView> manejarCasoFeliz(ConfirmarOrdenInEntity confirmarOrdenInEntity, ConfirmarOrdenOutEntity  rtaDAO) {
		ConfirmarOrdenPFView confirmarOrdenPFView = cargarDatos(confirmarOrdenInEntity, rtaDAO);
		return respuestaFactory.crearRespuestaOk(ConfirmarOrdenPFView.class, confirmarOrdenPFView);
	}
	
	/**
	 * Cargar datos.
	 *
	 * @param confirmarOrdenInEntity
	 *            the confirmar orden in entity
	 * @param rtaDAO
	 *            the rta DAO
	 * @return the confirmar orden PF view
	 */
	public ConfirmarOrdenPFView cargarDatos(ConfirmarOrdenInEntity confirmarOrdenInEntity, ConfirmarOrdenOutEntity rtaDAO) {	
		ConfirmarOrdenPFView dtoResponse = new ConfirmarOrdenPFView();
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CONFIRMACION_ORDEN_CORRECTA).getMensaje();
		String mensajeConfirmarOrden = MessageFormat.format(mensaje, confirmarOrdenInEntity.getTipoPlazoFijo(), confirmarOrdenInEntity.getCapital(), confirmarOrdenInEntity.getPlazo());
		dtoResponse.setMensajeConfirmacion(mensajeConfirmarOrden);
		return dtoResponse;

	}
	
	/**
	 * Manejar reintento.
	 *
	 * @param confirmarOrdenInEntity
	 *            the confirmar orden in entity
	 * @param permiteReintentar
	 *            the permite reintentar
	 * @param e
	 *            the e
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenPFView> manejarReintento(ConfirmarOrdenInEntity confirmarOrdenInEntity, boolean permiteReintentar, DAOException e) {
		Respuesta<ConfirmarOrdenPFView> respuesta;	
		LOGGER.error("Error en OrdenDAOBprivImpl metodo ejecutarStoredConfirmarOrden. ", e);
		String mensaje = mensajeBO.obtenerMensajePorCodigo(ERROR_NO_CONTEMPLADO_CONFIRMACION_ORDEN).getMensaje();
		String mensajeError = MessageFormat.format(mensaje, confirmarOrdenInEntity.getTipoPlazoFijo(), confirmarOrdenInEntity.getCapital(), confirmarOrdenInEntity.getPlazo());
		if (permiteReintentar) {
			respuesta = respuestaFactory.crearRespuestaError(ConfirmarOrdenPFView.class, mensajeError,
					TipoError.ERROR_CONFIRMAR_ORDEN_CON_REINTENTO.toString());

		} else {
			respuesta = respuestaFactory.crearRespuestaError(ConfirmarOrdenPFView.class, mensajeError,
					TipoError.ERROR_CONFIRMAR_ORDEN_SIN_REINTENTO.toString());
		}
		return respuesta;
	}
	
}
