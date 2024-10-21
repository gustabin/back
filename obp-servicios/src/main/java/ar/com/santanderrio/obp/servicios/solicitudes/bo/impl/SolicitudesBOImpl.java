package ar.com.santanderrio.obp.servicios.solicitudes.bo.impl;

import static ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesSolicitudesEnum.CONSENTIMIENTO_BILLETERAS;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.solicitudes.dao.impl.TransferiSueldoApiDAO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.adhesionwomen.manager.AdhesionWomenManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.getnet.manager.GetnetManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.impl.DatosSolicitanteManagerImpl;
import ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO;
import ar.com.santanderrio.obp.servicios.solicitudes.dao.SolicitudCtaTitDao;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesSolicitudesEnum;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.TipoProductoEnum;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.impl.CreditCardsServiceConnectorImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardDto;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;
import static ar.com.santanderrio.obp.servicios.solicitudes.entities.GestionesSolicitudesEnum.CONSENTIMIENTO_BILLETERAS;

/**
 * The Class SolicitudesBOImpl.
 */
@Component
public class SolicitudesBOImpl implements SolicitudesBO {

	/** The Constant NRO_ORDEN_FIRMANTE. */
	public static final String NRO_ORDEN_FIRMANTE = "001";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudesBOImpl.class);

    /** The Constant SUBPRODUCTO_ALTAIR_MONEDERO. */
    private static final String SUBPRODUCTO_ALTAIR_MONEDERO = "MONE";

    /** The Constant PRODUCTO_ALTAIR_MONEDERO. */
    private static final String PRODUCTO_ALTAIR_MONEDERO = "43";

    /** The Constant CALIDAD_PARTICIPACION_TITULAR. */
    private static final String CALIDAD_PARTICIPACION_TITULAR = "TI";

	/** The modulo permiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/** The administrador permisos. */
	@Autowired
	private AdministradorPermisos administradorPermisos;

	/** The consulta tarjeta titular DAO. */
	@Autowired
	private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO;
	
	@Autowired
	private AdhesionWomenManager adhesionWomenManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/** The getnet manager. */
	@Autowired
	private GetnetManager getnetManager;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;
	
	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/** The valor trj coord. */
	@Value("${TRJCOORD.OPERAINDISTINTO.REIMPRESIONTRJ}")
	private String valorTrjCoord;

	/** The Constant isOTP. */
	private static final Integer IS_OTP = 3;

	/** The Constant isOTP. */
	private static final Integer IS_COORDENADAS = 1;

    /** The Constant TIPO_TARJETA_VISA. */
    public static final String TIPO_TARJETA_VISA = "1";

    /** The Constant TIPO_TARJETA_AMEX. */
    public static final String TIPO_TARJETA_AMEX = "3";
    
    /** The Constant TIPO_TARJETA_MASTER. */
    public static final String TIPO_TARJETA_MASTER = "3";
    
    /** The Constant TIPOCTA_VISA. */
    public static final String TIPOCTA_VISA = "07";
    
    /** The Constant TIPOCTA_AMEX. */
    public static final String TIPOCTA_AMEX = "42";
    
    /** The Constant TARJETA_ADICIONAL. */
    public static final String TARJETA_ADICIONAL = "tarjetaCreditoAdicional";
    
    /** The Constant COD_RETORNO_EXTENDIDO_CERO. */
    private static final String COD_RETORNO_EXTENDIDO_CERO = "0";
    
    /** The getnet. */
    private static final String GETNET = "getnet";

    
    @Value("${HABILITACIONWOMEN}")
	private String habilitacionWomen;
    
    @Autowired
	private SesionCliente sesionCliente;
    
    @Autowired
	private SolicitudCtaTitDao solicitudCtaTitDao;
    
    @Autowired
    private LegalBO legalBO;
    
    @Autowired
    private CreditCardsServiceConnectorImpl creditCardApiService;
    
    public static final String ENRI_RIESGO_ALTO = "RA";
    
    public static final String ENRI_RIESGO_MEDIO = "RM";

	/** The Constant CODIGO_MARCA_AMEX. */
	private static final String CODIGO_MARCA_AMEX = "4";

	/** The Constant CODIGO_MARCA_AMEX. */
	private static final String CODIGO_MARCA_DEBITO = "0";

	/** The Constant CODIGO_MARCA_VISA. */
	private static final String CODIGO_MARCA_VISA = "1";
	
	@Autowired
	private CredentialSecurityFactory credentialSecurityFactory;

	/** The Constant CODIGO_MARCA_MASTER. */
	private static final String CODIGO_MARCA_MASTER = "3";

	@Autowired
	private TransferiSueldoApiDAO transferiSueldoApiDAO;
	
	/**
	 * Sets the app encoding.
	 *
	 * @param appEncoding
	 *            the new app encoding
	 */
	public void setAppEncoding(String appEncoding) {
		this.appEncoding = appEncoding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO#
	 * obtenerCuentasYPaquetes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SolicitudesDTO obtenerCuentasYPaquetes(List<Cuenta> cuentas) {
		LOGGER.info("Entro al BO para obtener Cuentas y Paquetes");
		List<String> solicitudes = solicitudes(cuentas, GestionesSolicitudesEnum.obtenerMapaInicialCuentasPaquetes());
		List<String> actionSheet = (ArrayList<String>) ((ArrayList<String>) solicitudes).clone();
		solicitudes.remove(GestionesSolicitudesEnum.BAJAPRODUCTOCUENTA.getTipo());
		return new SolicitudesDTO(solicitudes, actionSheet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO#
	 * obtenerTarjetas()
	 */
	@Override
	public SolicitudesDTO obtenerTarjetas(Cliente cliente, List<Cuenta> cuentas) {
		LOGGER.info("Entro al BO para obtener Tarjetas");

		List<String> gestiones = this.solicitudes(cuentas, GestionesSolicitudesEnum.obtenerMapaInicialTarjetas());
		List<String> actionSheet = new ArrayList<String>();
		actionSheet.addAll(gestiones);

		boolean seRemovioVisa = actionSheet.remove(GestionesSolicitudesEnum.TARJETAVISA.getTipo());
		boolean seRemovioAMEX = actionSheet.remove(GestionesSolicitudesEnum.TARJETAAMEX.getTipo());
		boolean seRemovioMaster = actionSheet.remove(GestionesSolicitudesEnum.TARJETAMASTER.getTipo());
		gestiones.remove(GestionesSolicitudesEnum.BAJAPRODUCTOTARJETA.getTipo());
		if (seRemovioAMEX || seRemovioVisa || seRemovioMaster) {
			actionSheet.add(GestionesSolicitudesEnum.TARJETACREDITO.getTipo());
		}
		revisarSiCorrespondeMostrarWomen(gestiones, cuentas);
		revisarSiCorrespondeMostrarTarjetaAdicional(cuentas, gestiones, actionSheet, cliente);
		revisarNoTitularOSoloAdicional(cuentas, gestiones, actionSheet);
		revisarSiCorrespondeMostrarClaveBanelco(gestiones, actionSheet);
		revisarSiCorrespondeMostrarBajaTarjetaAdicional(cuentas, gestiones, actionSheet, cliente);
		return new SolicitudesDTO(gestiones, actionSheet);
	}

	
	private void revisarNoTitularOSoloAdicional(List<Cuenta> cuentas, List<String> gestiones,
			List<String> actionSheet) {
		boolean esTitular = false;
		boolean esSoloAdicional = false;
		
		for (Cuenta cuenta : cuentas) {
			if (CALIDAD_PARTICIPACION_TITULAR.equals(cuenta.getCalidadDeParticipacion())) {
				esTitular = true;
			}
		}
		if (gestiones.contains(TARJETA_ADICIONAL) && gestiones.size() == 1) {
			esSoloAdicional = true;
		}

		// si no es titular ,o solo posee targeta adicional
		if ((!esTitular) || esSoloAdicional) {
			gestiones.add(GestionesSolicitudesEnum.TARJETAVISA.getTipo());
			gestiones.add(GestionesSolicitudesEnum.TARJETAAMEX.getTipo());
			actionSheet.add(GestionesSolicitudesEnum.TARJETAVISA.getTipo());
			actionSheet.add(GestionesSolicitudesEnum.TARJETAAMEX.getTipo());
		}

    }


	/**
	 * Solicitudes.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param gestionesMap
	 *            the gestiones map
	 * @return the list
	 */
	private List<String> solicitudes(List<Cuenta> cuentas, Map<GestionesSolicitudesEnum, Boolean> gestionesMap) {
		
		Integer valor = Integer.parseInt(this.valorTrjCoord);
		for (Cuenta cuenta : cuentas) {
			for (GestionesSolicitudesEnum gestionEnum : gestionesMap.keySet()) {
				if (!GestionesSolicitudesEnum.REIMPRESION_TARJETAS.equals(gestionEnum)
						&& !GestionesSolicitudesEnum.REIMPRESIONTARJETAS.equals(gestionEnum)) {
					gestionEnum.setHabilitacionWomen(habilitacionWomen);
					if (gestionEnum.cumpleCondicionesCuenta(cuenta)) {
						gestionesMap.put(gestionEnum, !gestionEnum.getValorInicial());
					}
				} else {
					if (gestionEnum.cumpleCondicionesCuenta(cuenta) && (valor == IS_OTP || valor == IS_COORDENADAS)) {
						gestionesMap.put(gestionEnum, !gestionEnum.getValorInicial());
					}
				}
			}
		}
		
		List<String> solicitudes = new ArrayList<String>();
		for (Entry<GestionesSolicitudesEnum, Boolean> entry : gestionesMap.entrySet()) {
			ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(entry.getKey().getAccion());
			if (moduloPermiso.tienePermisoDeVisibilidad() && entry.getValue()) {
				// GetNet - Validaciones
				if (entry.getKey().getTipo().contains(GETNET)
						&& !sesionParametros.getGetnetAgregado()
						&& !sesionParametros.getRecorrioCuentasGetnet()
						&& getnetManager.validarCard()) {
					entry.getKey().setTipo(sesionParametros.getEstadoGetNet());
					solicitudes.add(entry.getKey().getTipo());
					administradorPermisos.addNewGrant(entry.getKey().getAccion());
					getnetManager.grabarEstadisticas(COD_RETORNO_EXTENDIDO_CERO);
				} else if (!entry.getKey().getTipo().contains(GETNET)) {
					solicitudes.add(entry.getKey().getTipo());
					administradorPermisos.addNewGrant(entry.getKey().getAccion());
				}
			} else {
 				administradorPermisos.removeGrant(entry.getKey().getAccion());
			}
		}
		
		solicitudes=validarRepatriacion(sesionCliente.getCliente(),solicitudes);
		solicitudes = filtroFlujoCtaTit(solicitudes); 
		
		// Se resetean parametros en Sesion por si hay que actualizar la Card
		sesionParametros.setRecorrioCuentasGetnet(false);
		sesionParametros.setGetnetAgregado(false);
		return solicitudes;
	}


	private List<String> filtroFlujoCtaTit(List<String> solicitudes) {
		boolean flagEnri = inversionWSHelper.enriFlag();
		if(flagEnri){ 
			solicitudes.remove(GestionesSolicitudesEnum.CUENTATITULOS.getTipo());
		}else {
			solicitudes.remove(GestionesSolicitudesEnum.CUENTATITULOSENRI.getTipo());
		}
		return solicitudes;
	}

	private List<String> validarRepatriacion(Cliente cliente, List<String> solicitudes) {
		List<Cuenta> ctasTitRtlRep=cliente.getCuentasTitRtlRepatriacion();
		//List<Cuenta> ctasTitBpRep=cliente.getCuentasTitBPRepatriacion();
		int cantCuentasRep=0;
		for(Cuenta cta : cliente.getCuentas()) {
			if(cta.isRepatriacion()) {
				cantCuentasRep++;
			}
		}
//		for(Cuenta cta : cliente.getCuentasPrivadas()) {
//			if(cta.isRepatriacion()) {
//				cantCuentasRep++;
//			}
//		}
		
	//	if(cantCuentasRep<=(ctasTitRtlRep.size()+ctasTitBpRep.size())){
		if(cantCuentasRep<=ctasTitRtlRep.size()){	
			solicitudes.remove(GestionesSolicitudesEnum.CUENTATITULOSREP.getTipo());
		}
		
		
		
		
		return solicitudes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO#
	 * calcularMonoTC(java.util.List)
	 */
	@Override
	public boolean soloUnaTarjetaDeCredito(List<Cuenta> cuentas) {
		return cuentas != null && cuentas.size() == 1 && cuentas.get(0).esTitularTarjeta();
	}

	/**
	 * Revisar si corresponde mostrar tarjeta adicional.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @param gestiones
	 *            the gestiones
	 * @param actionSheet
	 *            the action sheet
	 * @param cliente
	 *            the cliente
	 */
	private void revisarSiCorrespondeMostrarTarjetaAdicional(List<Cuenta> cuentas, List<String> gestiones, 
	        List<String> actionSheet, Cliente cliente) {
	    int contadorDeTarjetasHabilitadas = 0;
	    for (Cuenta cuenta : cuentas) {
            ConsultaDetalleDatosTarjetaInEntity consultaDetalleDatosTarjetaInEntity = new ConsultaDetalleDatosTarjetaInEntity();
	        if (cuenta.esTarjetaDeCredito()) {
	            int diezEspacios = 10;
	            consultaDetalleDatosTarjetaInEntity.setNroCuentaTarjeta(cuenta.getNroCuentaProducto().substring(cuenta.getNroCuentaProducto().length() - diezEspacios));
	            consultaDetalleDatosTarjetaInEntity.setCliente(cliente);
	            String tipoTarjeta = setearTarjeta(cuenta.getTipoCuenta());
	            consultaDetalleDatosTarjetaInEntity.setTipoTarjeta(tipoTarjeta);
	       
	            try {
	                ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity = this.consultaTarjetaTitularDAO
	                        .obtenerDetalleDatosTarjeta(consultaDetalleDatosTarjetaInEntity);
	                if (isTarjetaHabilitado(consultaDetalleDatosTarjetaOutEntity)) {
	                    contadorDeTarjetasHabilitadas++;                    
	                }
	            } catch (DAOException e) {
	                LOGGER.error("Error al consultar tarjeta", e);
	            }
	        }
	    }
	    if (contadorDeTarjetasHabilitadas == 0) {
	        if (gestiones.contains(TARJETA_ADICIONAL)) {
	            gestiones.remove(TARJETA_ADICIONAL);
	        }
	        if (actionSheet.contains(TARJETA_ADICIONAL)) {
	            actionSheet.remove(TARJETA_ADICIONAL);
	        }
	    }
	    
	    if (sesionCliente.getCliente().isNova()) {
	    	actionSheet.remove(TARJETA_ADICIONAL);
	    	gestiones.remove(TARJETA_ADICIONAL);
	    }
	    
	}
	
	private String setearTarjeta(String tipoCuenta) {
		if(TIPOCTA_VISA.equals(tipoCuenta)) {
			return TIPO_TARJETA_VISA;
		}else if(TIPOCTA_AMEX.equals(tipoCuenta)) {
			return TIPO_TARJETA_AMEX;
		}else {
			return TIPO_TARJETA_MASTER;
		}
	}
	
	private boolean isTarjetaHabilitado(ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity) {
		return consultaDetalleDatosTarjetaOutEntity != null && 
                Integer.parseInt(consultaDetalleDatosTarjetaOutEntity.getLimiteCredito()) > 1 && 
                Integer.parseInt(consultaDetalleDatosTarjetaOutEntity.getLimiteDeCompra()) > 1;
	}

	/*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO#
     * obtenerOtrosMediosPago()
     */
    @SuppressWarnings("unchecked")
    @Override
    public SolicitudesDTO obtenerOtrosMediosPago(List<Cuenta> cuentas, List<MonederoDTO> monederosInactivos) {
        LOGGER.info("Entro al BO para obtener Otros Medios de Pago");
        Map<GestionesSolicitudesEnum, Boolean> gestiones = new LinkedHashMap<GestionesSolicitudesEnum, Boolean>();
        configurarGestionesMonederoTag(gestiones, cuentas, monederosInactivos);
        gestiones.putAll(GestionesSolicitudesEnum.obtenerMapaInicialOtrosMediosPago());
		gestiones.put(CONSENTIMIENTO_BILLETERAS, CONSENTIMIENTO_BILLETERAS.getValorInicial());
		List<String> solicitudes = solicitudes(cuentas, gestiones);
        List<String> actionSheet = (ArrayList<String>) ((ArrayList<String>) solicitudes).clone();
        return new SolicitudesDTO(solicitudes, actionSheet);
    }

    /**
     * Configurar gestiones monedero tag.
     *
     * @param gestionesMap the gestiones map
     * @param cuentas the cuentas
     * @param monederosInactivos the monederos inactivos
     */
    private static void configurarGestionesMonederoTag(Map<GestionesSolicitudesEnum, Boolean> gestionesMap,
                                                       List<Cuenta> cuentas, List<MonederoDTO> monederosInactivos) {
        boolean tieneMonederoTitular = false;

        for (Cuenta cuenta : cuentas) {
            if (CALIDAD_PARTICIPACION_TITULAR.equals(cuenta.getCalidadDeParticipacion())
                    && PRODUCTO_ALTAIR_MONEDERO.equals(cuenta.getProductoAltair())
                    && SUBPRODUCTO_ALTAIR_MONEDERO.equals(cuenta.getSubproductoAltair())) {
                tieneMonederoTitular = true;
                break;
            }
        }

        if (!tieneMonederoTitular) {
            gestionesMap.put(GestionesSolicitudesEnum.MONEDEROALTA, true);
        }
        if (tieneMonederoTitular && tieneMonederosParaActivar(monederosInactivos)) {
            gestionesMap.put(GestionesSolicitudesEnum.MONEDEROACTIVAR, true);
        }
        if (tieneMonederoTitular) {
            gestionesMap.put(GestionesSolicitudesEnum.MONEDEROALTAADICIONAL, true);
        }
    }

    /**
     * Tiene monederos para activar.
     *
     * @param monederosInactivos the monederos inactivos
     * @return true, if successful
     */
    private static boolean tieneMonederosParaActivar(List<MonederoDTO> monederosInactivos) {
        for (MonederoDTO monederoInactivo : monederosInactivos) {
            if (DatosSolicitanteManagerImpl.ESTADO_ENTREGADO.equals(monederoInactivo.getEstadoTarjetaTag())
                    && DatosSolicitanteManagerImpl.TARJETA_TIPO.equals(monederoInactivo.getTipoTarjeta())) {
                return true;
            }
        }
        return false;
    }

	private void revisarSiCorrespondeMostrarWomen(List<String> gestiones, List<Cuenta> cuentas) {
		boolean correspondeWomen = Boolean.FALSE;

		AdhesionWomenView respuestaWomen = adhesionWomenManager.recuperarTarjetasAdhesionWomen();
		boolean tarjetasAdheridas = StringUtils.isEmpty(respuestaWomen.getErrorServicio()) && !respuestaWomen.getListaTarjetasAdheridas().isEmpty();

		if(tarjetasAdheridas) {
			correspondeWomen = Boolean.TRUE;
		} else {
			if (Boolean.TRUE.equals(!gestiones.isEmpty()) && gestiones.contains("adhesionWomen")) {
				for (Cuenta cuenta : cuentas) {
					if(!TarjetaUtils.ESTADO_TARJETA_CREDITO_CERRADA.equals(cuenta.getEstadoTarjetaCredito()) &&
							cuenta.esTitularTarjeta() &&
							TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum()) &&
							correspondeAlTipoProducto(cuenta)) {
						correspondeWomen = Boolean.TRUE;
						break;
					}
				}
			}
		}

		if (isNovaAndCorrespondeWomen(correspondeWomen, sesionCliente.getCliente().isNova())) {
			gestiones.remove(GestionesSolicitudesEnum.ADHESIONWOMEN.getTipo());
		}
	}
	
	private boolean isNovaAndCorrespondeWomen(boolean correspondeWomen, boolean isNova) {
		return !correspondeWomen || isNova; 
	}

	private boolean correspondeAlTipoProducto(Cuenta cuenta) {
		return 	TipoProductoEnum.INTERNACIONAL.getCodigo().equals(cuenta.getClaseCuenta()) ||
				TipoProductoEnum.ORO.getCodigo().equals(cuenta.getClaseCuenta()) ||
				TipoProductoEnum.PLATINUM.getCodigo().equals(cuenta.getClaseCuenta()) ||
				TipoProductoEnum.SIGNATURE.getCodigo().equals(cuenta.getClaseCuenta());
	}
    
    public ValidaAltaView solicitudCtaTit() {
    	Cliente cliente = sesionCliente.getCliente();
    	ValidaAltaView altaProducto= solicitudCtaTitDao.validaAltaProducto(cliente);
    	if(altaProducto.getRiesgo().equals(ENRI_RIESGO_MEDIO) || altaProducto.getRiesgo().equals(ENRI_RIESGO_ALTO)) {
    		altaProducto.setBloqueado(true);
    	}
    	
    	//if(altaProducto.isHacerEncuesta()) {
    		altaProducto.setMensaje(buscarMensaje(CodigoMensajeConstantes.HACER_ENCUESTA_ENRI));
    	//}
    	if(altaProducto.isBloqueado()) {
    		altaProducto.setMensaje(buscarMensaje(CodigoMensajeConstantes.BLOQUEO_ALTA_CTA_TITULO));
    	}
    	
    	return altaProducto;
    }



	private String buscarMensaje(String codigoMensaje) {
        return legalBO.obtenerLegalesPorCodigo(codigoMensaje);
    }

    private void revisarSiCorrespondeMostrarClaveBanelco(List<String> gestiones, List<String> acciones) {
		Cliente cliente = sesionCliente.getCliente();
		if (cliente != null && !cliente.tieneTarjetasDeDebito()) {
			gestiones.remove(GestionesSolicitudesEnum.CLAVEBANELCO.getTipo());
			acciones.remove(GestionesSolicitudesEnum.CLAVEBANELCO.getTipo());
		}
	}
    
    private void revisarSiCorrespondeMostrarBajaTarjetaAdicional(List<Cuenta> cuentas, List<String> gestiones, List<String> actionSheet, Cliente cliente) {
    	boolean otorgoORecibioAlgunaTarjetaDeCreditoAdicional = false;
    	try {
    		// primero valido si el usuario OTOROGO alguna tarjeta adicional desde sus cuentas
    		for (CreditCardDto tarjetaDeCredito : creditCardApiService.getAllCreditCardsByAccountNup(cliente.getNup())) {
    			if (!cliente.getNup().equals(tarjetaDeCredito.getCardOwner().getUniquePersonIdentifier())) {
    				otorgoORecibioAlgunaTarjetaDeCreditoAdicional = true;
    			}
    		}
    		if (!otorgoORecibioAlgunaTarjetaDeCreditoAdicional) {
    			// en caso que no haya otorgado una adicional, se valida que se le haya otorgado una a el
        		for (CreditCardDto tarjetaDeCredito : creditCardApiService.getAllCreditCardsByNup(cliente.getNup())) {
        			if (!cliente.getNup().equals(
        					tarjetaDeCredito.getCreditAccount().getAccountOwner().getUniquePersonIdentifier())) {
        				otorgoORecibioAlgunaTarjetaDeCreditoAdicional = true;
        			}
        		}
    		}
    	} catch (Exception e) {
    		otorgoORecibioAlgunaTarjetaDeCreditoAdicional = false;
    		LOGGER.warn("No se pudo recuperar las tarjetas de credit-cards-api. Se eliminan opciones de baja tarjeta adicional.", e);
    	}
    	if (!otorgoORecibioAlgunaTarjetaDeCreditoAdicional || sesionCliente.getCliente().isNova()) {
    		gestiones.remove(GestionesSolicitudesEnum.BAJATARJETAS.getTipo());
			actionSheet.remove(GestionesSolicitudesEnum.BAJATARJETAS.getTipo());
    	}
    }

	@Override
	public Boolean transferiSueldo(TransferiSueldoRequestDTO transferiSueldoRequestDTO) {
		try {
			TransferiSueldoResponseDTO response = transferiSueldoApiDAO.transferiSueldo(transferiSueldoRequestDTO);
			return response != null && response.getId() != null && !"".equals(response.getId());
		} catch (DAOException e) {
			LOGGER.info("Fallo en la respuesta ", e);
			return Boolean.FALSE;
		}
	}
    
}
