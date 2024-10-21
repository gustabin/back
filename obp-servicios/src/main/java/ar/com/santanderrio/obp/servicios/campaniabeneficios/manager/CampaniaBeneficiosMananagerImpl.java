package ar.com.santanderrio.obp.servicios.campaniabeneficios.manager;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.bo.CampaniaBeneficiosBO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.dto.SuscCampaniaBeneficiosInDTO;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.entities.RepuestaSPSuscCampaniaBeneficiosEntity;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosOutView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaSuscripcionOutView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.ServiosCampaniaTextosView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.impl.LegalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;

/**
 * The Class CampaniaBeneficiosMananagerImpl.
 */
@Component
public class CampaniaBeneficiosMananagerImpl implements CampaniaBeneficiosMananager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaniaBeneficiosMananagerImpl.class);

    /** The cliente CampaniaBeneficios BO. */
    @Autowired
    private CampaniaBeneficiosBO campaniaBeneficiosBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaMananger;

    /** The environment. */
    @Autowired
    private Environment environment;
    
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	@Autowired
	private MensajeBOImpl mensajeBO;
	@Autowired
	private LegalBOImpl legalBO;
	
	   /** The property map. */
    @Autowired
    private PropertyMap propertyMap;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.campaniabeneficios.manager.
     * CampaniaBeneficiosMananager#suscripcionCampaniaBeneficios(ar.com.santanderrio
     * .obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView)
     */
    @Override
    public Respuesta<CampaniaSuscripcionOutView> suscripcionCampaniaBeneficios(CampaniaBeneficiosInView inView) {
        LOGGER.debug("Suscribir a la campania {}", inView.getIdcampania());
        SuscCampaniaBeneficiosInDTO inDTO = generarDtoIn(inView);
        Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> resp;
        resp = campaniaBeneficiosBO.suscripcionCampaniaBeneficios(inDTO);
        CampaniaSuscripcionOutView respuesta = new CampaniaSuscripcionOutView();
        CampaniaView campaniaSolicitada = getCampania(obtenerCampanias(), inView.getPrograma());
        if (EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())) {
        	
			OfertaComercialDTO oferta = getOfertaAInformar(inView);
			Boolean informarAC = campaniaBeneficiosBO.informarGestionAC(sesionCliente.getCliente(), oferta);
			/*4.1	Si el SP  respondió OK y el método informarGestionAC 
			 * devolvió error código de error <> de ceros. se debe grabar 
			 * estadísticas código: XX.XXX  con código de error 2   */
			if (!informarAC) {				
				estadisticaMananger.add(EstadisticasConstants.OFERTAS_INFORMAR_GESTION_AC,
	                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
        	estadisticaMananger.add(EstadisticasConstants.BENEFICIOS_CAMPANIAS_SUSCRIPCION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            respuesta.setMensajeFeedback(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_CAMBIO_CAMPANIA_SUSCRIPCION_EXITOSA).getMensaje());
            if (StringUtils.isNotEmpty(campaniaSolicitada.getUrlQr())) {
                respuesta.setUrlQr(campaniaSolicitada.getUrlQr());
            } else {
                respuesta.setUrlQr(null);
            }
            if (StringUtils.isNotEmpty(campaniaSolicitada.getUrlTodoPago())) {
                respuesta.setUrlTodoPago(campaniaSolicitada.getUrlTodoPago());
            } else {
                respuesta.setUrlTodoPago(null);
            }
            return respuestaFactory.crearRespuestaOk(respuesta);
        } else {
            estadisticaMananger.add(EstadisticasConstants.BENEFICIOS_CAMPANIAS_SUSCRIPCION,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CAMPANIA_SUSCRIPCION,
                    CodigoMensajeConstantes.CAMPANIA_SUSCRIPCION_ERROR);
        }
    }

    /**
     * Gets the oferta A informar.
     *
     * @param inView the in view
     * @return the oferta A informar
     */
    private OfertaComercialDTO getOfertaAInformar(CampaniaBeneficiosInView inView) {
    	for (OfertaComercialDTO oferta : sesionParametros.getOfertasComerciales().getOfertas()) {
			if (StringUtils.equalsIgnoreCase(inView.getVariable1Char(), oferta.getVariable1Char())) {
				return oferta;
			}
		}
    	return null;
	}

	/**
	 * Generar dto in.
	 *
	 * @param inView the in view
	 * @return the susc campania beneficios in DTO
	 */
    private SuscCampaniaBeneficiosInDTO generarDtoIn(CampaniaBeneficiosInView inView) {
        SuscCampaniaBeneficiosInDTO inDTO = new SuscCampaniaBeneficiosInDTO();
        inDTO.setIdcampania(inView.getIdcampania());
        inDTO.setPrograma(inView.getPrograma());
        inDTO.setRespuesta(inView.getRespuesta());
        inDTO.setNup(sesionCliente.getCliente().getNup());
        inDTO.setVariable1Char(inView.getVariable1Char());
        return inDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.campaniabeneficios.manager.
     * CampaniaBeneficiosMananager#consSuscCampaniaBeneficios(ar.com.santanderrio.
     * obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView)
     */
    @Override
	public Respuesta<CampaniaBeneficiosOutView> consSuscCampaniaBeneficios(CampaniaBeneficiosInView inView)  {
		LOGGER.debug("Consultar la campania {}", inView.getIdcampania());
		SuscCampaniaBeneficiosInDTO inDTO = new SuscCampaniaBeneficiosInDTO();
		inDTO.setIdcampania(inView.getIdcampania());
		inDTO.setNup(sesionCliente.getCliente().getNup());
		Respuesta<RepuestaSPSuscCampaniaBeneficiosEntity> resp = campaniaBeneficiosBO
				.consSuscripcionCampaniaBeneficios(inDTO);
		if (EstadoRespuesta.OK.equals(resp.getEstadoRespuesta())) {
			estadisticaMananger.add(EstadisticasConstants.BENEFICIOS_CAMPANIAS_CNS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			CampaniaBeneficiosOutView respuesta = new CampaniaBeneficiosOutView();
			respuesta.setSuscripto(resp.getRespuesta().getSuscripto());
			ProgramaLegalEnum campaniaLegal = ProgramaLegalEnum.obtenerCampaniaLegalDesdePrograma(inView.getPrograma());
			if (!resp.getRespuesta().getSuscripto().equals("1")) {
				CampaniaView campaniaSolicitada = getCampania(obtenerCampanias(), inView.getPrograma());
				Mensaje mensajeCampaniaDescripcion = mensajeBO
						.obtenerMensajePorCodigo(campaniaSolicitada.getCodDescripcionCampania());
				Mensaje mensajeCampaniaBeneficios = mensajeBO
						.obtenerMensajePorCodigo(campaniaSolicitada.getCodBeneficiosCampania());
				Mensaje mensajeCampaniaRequisitos = mensajeBO
						.obtenerMensajePorCodigo(campaniaSolicitada.getCodRequisitosCampania());
				String textoLegales = legalBO.obtenerLegalesPorCodigo(campaniaSolicitada.getCodLegalesCampania());
				ArrayList<ServiosCampaniaTextosView> serviosCampaniaBeneficiosView = new ArrayList<ServiosCampaniaTextosView>();
				ArrayList<ServiosCampaniaTextosView> serviosCampaniaRequisitosView = new ArrayList<ServiosCampaniaTextosView>();
				serviosCampaniaBeneficiosView = procesarTextoCampania(mensajeCampaniaBeneficios);
				serviosCampaniaRequisitosView = procesarTextoCampania(mensajeCampaniaRequisitos);
				respuesta.setServiciosCampania(serviosCampaniaBeneficiosView);
				respuesta.setRequisitosCampania(serviosCampaniaRequisitosView);
				respuesta.setDescripcionCampania(mensajeCampaniaDescripcion.getMensaje());
				respuesta.setTextoLegales(textoLegales);
				if (!campaniaLegal.equals(ProgramaLegalEnum.EMPTY)) {
					respuesta.setLegalTyC(environment.getProperty(campaniaLegal.getLinkTyc()));
					respuesta.setLegalComisiones(environment.getProperty(campaniaLegal.getLinkComisiones()));
				}
			}
			return respuestaFactory.crearRespuestaOk(respuesta);


		} else {
			estadisticaMananger.add(EstadisticasConstants.BENEFICIOS_CAMPANIAS_CNS,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CAMPANIA_CNS_ERROR);
		}
	}
    private CampaniaView getCampania(ArrayList<CampaniaView> campanias, String idCampania) {
    	for (CampaniaView campania : campanias) {
    	      if (campania.getKey().equalsIgnoreCase(idCampania)) {
                  return campania;
              }
		}
    	return null;
	}

	private ArrayList<ServiosCampaniaTextosView> procesarTextoCampania(Mensaje mensajeCampania) {
		String[] mensajeSplit = mensajeCampania.getMensaje().split("!SPLIT!");
		ArrayList<ServiosCampaniaTextosView> serviosCampaniaTextosView = new ArrayList<ServiosCampaniaTextosView>();
		for (String textoAIdentificar : mensajeSplit) {
			ArrayList<String> textos = new ArrayList<String>();
			String titulo = null;
			if (textoAIdentificar.contains("!TIT!")) {
				String[] listaARecorrer = textoAIdentificar.split("!TIT!");
				titulo = listaARecorrer[0];
				if(listaARecorrer.length > 1) {
					for (String valores : listaARecorrer[1].split("!LI!")) {
						if (!valores.equals(titulo)) {
							textos.add(valores);
						}
					}
				} else {
					textos.add(null); 
				}
			} else {
				String[] listaARecorrer = textoAIdentificar.split("!LI!");
				for (String valores : listaARecorrer) {
					if (!valores.equals(titulo)) {
						textos.add(valores);
					}
				}
			}
			ServiosCampaniaTextosView b = new ServiosCampaniaTextosView();
			b.setTextos(textos);
			b.setTitulo(titulo);
			serviosCampaniaTextosView.add(b);
		}
		return serviosCampaniaTextosView;
	}

	private ArrayList<CampaniaView> obtenerCampanias() {
		Map<String, Object> allData = propertyMap.getProperties();
		ArrayList<CampaniaView> campanias = new ArrayList<CampaniaView>();
		for (String key : allData.keySet()) {
			CampaniaView campania = new CampaniaView();

			if (key.startsWith("CAMPANIA.CARRUSEL.")) {
				String value = (String) allData.get(key);
				String[] valueSplit = value.split("\\|");
				if (valueSplit[0].equals("1")) {
					String[] keySplit = key.split("\\.");
					campania.setKey(keySplit[2]);
					campania.setCodDescripcionCampania(valueSplit[1]);
					campania.setCodBeneficiosCampania(valueSplit[2]);
					campania.setCodRequisitosCampania(valueSplit[3]);
					campania.setCodLegalesCampania(valueSplit[4]);
					if (!valueSplit[5].contains("0")) {
						campania.setUrlTodoPago(valueSplit[5]);
					} else {
						campania.setUrlTodoPago(null);
					}
					if (!valueSplit[6].contains("0")) {
						campania.setUrlQr(valueSplit[6]);
					} else {
						campania.setUrlTodoPago(null);
					}
					campanias.add(campania);
				}
			}
		}
		return campanias;
	}
}
