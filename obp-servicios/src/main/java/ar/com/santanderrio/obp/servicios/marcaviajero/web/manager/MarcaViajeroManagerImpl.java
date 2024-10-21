/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Destino;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.EliminarViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.NuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.RangoInhabilitacion;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Tarjeta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaAsociada;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaNuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajeHabilitado;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajesHabilitados;
import ar.com.santanderrio.obp.servicios.marcaviajero.utils.MarcaViajeroUtils;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.DestinoView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.EliminarViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.MarcaViajeroView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeTarjetaView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.RangoInhabilitacionView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.TarjetaAsociadaView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.TarjetaNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.TarjetaView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ViajeHabilitadoView;

/**
 * The Class MarcaViajeroManagerImpl.
 */
@Component
public class MarcaViajeroManagerImpl implements MarcaViajeroManager {

    /** The Constant MASCARA_TARJETA. */
    private static final String MASCARA_TARJETA = "XXXX-";

    /** The Constant MARCA_AMEX. */
    private static final String MARCA_AMEX = "AMEX";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MarcaViajeroManagerImpl.class);

    /** The marca viajero BO. */
    @Autowired
    MarcaViajeroBO marcaViajeroBO;

    /** The respuestafactory. */
    @Autowired
    RespuestaFactory respuestafactory;

    /** The mensaje BO. */
    @Autowired
    MensajeBO mensajeBO;

    /** The legal BO. */
    @Autowired
    LegalBO legalBO;

    /** The sesion cliente. */
    @Autowired
    SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.web.manager.
     * MarcaViajeroManager#obtenerViajes()
     */
    @Override
    public Respuesta<MarcaViajeroView> obtenerViajes() {
        sesionParametros.setContador(new ContadorIntentos(2));
        Respuesta<ViajesHabilitados> respuesta = marcaViajeroBO
                .obtenerViajes(sesionCliente.getCliente(), getMailCliente());
        if(!EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.HABILITAR_TARJETAS_POR_VIAJE_ACCESO_TENENCIA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(
                    EstadisticasConstants.HABILITAR_TARJETAS_POR_VIAJE_ACCESO_TENENCIA,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) { 
            return Respuesta.copy(MarcaViajeroView.class, respuesta);
        }
        return armarView(respuesta);
    }

    /**
     * Armar view.
     *
     * @param respuestaViajes
     *            the respuesta viajes
     * @return the respuesta
     */
    private Respuesta<MarcaViajeroView> armarView(
            Respuesta<ViajesHabilitados> respuestaViajes) {
        Respuesta<MarcaViajeroView> respuestaView = Respuesta
                .copy(MarcaViajeroView.class, respuestaViajes);
        ViajesHabilitados viajesHabilitados = respuestaViajes.getRespuesta();

        MarcaViajeroView marcaViajeroView = new MarcaViajeroView();
        if (EstadoRespuesta.OK.equals(respuestaViajes.getEstadoRespuesta())) {
            marcaViajeroView = getViajesHabilitados(
                    viajesHabilitados.getViajesHabilitados());
        }
        marcaViajeroView.setMensajeAyuda(getMensajeAyuda());
//        if (viajesHabilitados.getTieneBlack() != null
//                && viajesHabilitados.getTieneBlack()) {
//            marcaViajeroView.setMensajeBlack(getMensaje(
//                    CodigoMensajeConstantes.MARCA_VIAJERO_BLACK_LEGALES));
//        }
        respuestaView.setRespuesta(marcaViajeroView);
        return respuestaView;
    }

    /**
     * Gets the viajes habilitados.
     *
     * @param viajeHabilitadoList
     *            the viaje habilitado list
     * @return the viajes habilitados
     */
    private MarcaViajeroView getViajesHabilitados(
            List<ViajeHabilitado> viajeHabilitadoList) {
        MarcaViajeroView marcaViajeroView = new MarcaViajeroView();

        List<ViajeHabilitadoView> viajesHabilitadosView = new ArrayList<ViajeHabilitadoView>();
        for (ViajeHabilitado viajeHabilitado : viajeHabilitadoList) {
            ViajeHabilitadoView viajeHabilitadoView = new ViajeHabilitadoView();
            viajeHabilitadoView
                    .setCantidadDestinos(viajeHabilitado.getCantidadDestinos());
            viajeHabilitadoView
                    .setCantidadDias(viajeHabilitado.getCantidadDias());
            viajeHabilitadoView
                    .setCantidadTarjetas(viajeHabilitado.getCantidadTarjetas());
            viajeHabilitadoView.setIdentificadorViaje(
                    String.valueOf(viajeHabilitado.getIdentificadorViaje()));
            if (viajeHabilitado.getDestinos() != null) {
                viajeHabilitadoView.setDestinos(new ArrayList<DestinoView>());
                for (Destino destino : viajeHabilitado.getDestinos()) {
                    DestinoView destinoView = new DestinoView();
                    destinoView.setCodigo(destino.getCodigo());
                    destinoView.setDescripcion(
                            MarcaViajeroUtils.formatearDestinoCamelCase(
                                    destino.getDescripcion()));
                    viajeHabilitadoView.getDestinos().add(destinoView);
                }
            }
            if (viajeHabilitado.getTarjetas() != null) {

                List<TarjetaView> listaTarjetaView = new ArrayList<TarjetaView>();
                String nombre = null;
                TarjetaView tarjetaView = null;
                TarjetaView primerTarjetaView = null;
                Boolean isPrimero = Boolean.TRUE;
                for (Tarjeta tarjeta : viajeHabilitado.getTarjetas()) {
                    Boolean isAsociada = (tarjeta.getNombre().equalsIgnoreCase(nombre));
                    if (!isAsociada) {
                        if(tarjetaView!= null) {
                            ordenarTarjetas(tarjetaView.getTarjetasAsociadas());
                            listaTarjetaView.add(tarjetaView);
                        }
                        tarjetaView = new TarjetaView();
                        tarjetaView.setIsTitular(tarjeta.getIsTitular());
                        tarjetaView.setNombre(ISBANStringUtils.convertirStringToCamelcase(tarjeta.getNombre()));
                        nombre = tarjeta.getNombre();
                        tarjetaView.setTarjetasAsociadas(
                                new ArrayList<TarjetaAsociadaView>());
                        if(isPrimero) {
                            primerTarjetaView = tarjetaView;
                            isPrimero = Boolean.FALSE;
                        }
                    }
                    TarjetaAsociadaView tarjetaAsociadaView = new TarjetaAsociadaView();
                    tarjetaAsociadaView.setAlias(tarjeta.getAlias());
                    tarjetaAsociadaView.setIsTitular(tarjeta.getIsTitular());
                    tarjetaAsociadaView
                            .setMarca(getMarca(tarjeta.getMarca()));
                    tarjetaAsociadaView.setNumero(formatNumeroTarjeta(tarjeta));
                    if(primerTarjetaView!= null && tarjetaView.getNombre().equals(primerTarjetaView.getNombre())) {
                        primerTarjetaView.getTarjetasAsociadas().add(tarjetaAsociadaView);
                    }else {
                        tarjetaView.getTarjetasAsociadas().add(tarjetaAsociadaView);
                    }
                }
                if(!CollectionUtils.isEmpty(tarjetaView.getTarjetasAsociadas())) {
                    ordenarTarjetas(tarjetaView.getTarjetasAsociadas());    
                    listaTarjetaView.add(tarjetaView);
                }
                viajeHabilitadoView.setTarjetas(listaTarjetaView);
                LOGGER.debug("viaje {}", viajeHabilitadoView);
                if(primerTarjetaView != null) {
                    ordenarTarjetas(primerTarjetaView.getTarjetasAsociadas());    
                }
            }
            viajeHabilitadoView.setFechaViaje(MarcaViajeroUtils
                    .aplicarFormatoFechaDDMMMM(viajeHabilitado.getFechaViaje()));
            viajeHabilitadoView.setFechaDesde(
                    MarcaViajeroUtils.aplicarFormatoFechaDDMMYYYY(
                            viajeHabilitado.getFechaDesde()));
            viajeHabilitadoView.setFechaHasta(
                    MarcaViajeroUtils.aplicarFormatoFechaDDMMYYYY(
                            viajeHabilitado.getFechaHasta()));
            viajeHabilitadoView.setMail(viajeHabilitado.getMail());
            viajeHabilitadoView
                    .setPermiteEliminar(viajeHabilitado.getPermiteEliminar());
            viajeHabilitadoView
                    .setPermiteModificar(viajeHabilitado.getPermiteModificar());
            viajesHabilitadosView.add(viajeHabilitadoView);
        }
        marcaViajeroView.setViajesHabilitados(viajesHabilitadosView);
        return marcaViajeroView;
    }

    /**
	 * Ordenar tarjetas.
	 *
	 * @param tarjetasAsociadas
	 *            the tarjetas asociadas
	 */
    private void ordenarTarjetas(List<TarjetaAsociadaView> tarjetasAsociadas) {
        
        Collections.sort(tarjetasAsociadas, new Comparator<TarjetaAsociadaView>() {
            @Override
            public int compare(TarjetaAsociadaView tarjeta1, TarjetaAsociadaView tarjeta2) {
                int i = tarjeta1.getMarcaPrioridad().compareTo(tarjeta2.getMarcaPrioridad());
                if (i != 0) {
                    return i;
                }
                return tarjeta2.getIsTitular()
                        .compareTo(tarjeta1.getIsTitular());
            }
        });
        
    }

    /**
	 * Gets the marca.
	 *
	 * @param marca
	 *            the marca
	 * @return the marca
	 */
    private String getMarca(String marca) {
        if("BANELCO".equalsIgnoreCase(marca)) {
            return "VISA D\u00E9bito";
        }
        return marca.toUpperCase();
    }

    /**
     * Format numero tarjeta.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the string
     */
    private String formatNumeroTarjeta(Tarjeta tarjeta) {

        if (MARCA_AMEX.equalsIgnoreCase(tarjeta.getMarca())) {
            return MASCARA_TARJETA + getUltimosCinco(tarjeta.getNumero());
        }
        return MASCARA_TARJETA + tarjeta.getUltimosCuatro();
    }

    /**
     * Gets the ultimos cingo.
     *
     * @param numero
     *            the numero
     * @return the ultimos cingo
     */
    private String getUltimosCinco(String numero) {
        return numero.substring(numero.length() - 6, numero.length() - 1);
    }

    /**
	 * Gets the ultimos cuatro.
	 *
	 * @param numero
	 *            the numero
	 * @return the ultimos cuatro
	 */
    private String getUltimosCuatro(String numero) {
        return numero.substring(numero.length() - 4);
    }

    /**
     * Gets the mensaje ayuda.
     *
     * @return the mensaje ayuda
     */
    private String getMensajeAyuda() {
        String mensaje = null;
        try {
            mensaje = legalBO.obtenerLegal(
                    CodigoMensajeConstantes.MARCA_VIAJERO_MENSAJE_AYUDA);
        } catch (DAOException e) {
            LOGGER.info("Error al obtener legales: MARCA_VIAJERO_MENSAJE_AYUDA",
                    e);
        }
        return mensaje;
    }

    /**
     * Gets the mensaje.
     *
     * @param codigoMensaje
     *            the codigo mensaje
     * @return the mensaje
     */
    private String getMensaje(String codigoMensaje) {
        Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
        return StringUtils.isEmpty(mensaje.getMensaje()) ? null
                : mensaje.getMensaje();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.web.manager.
     * MarcaViajeroManager#nuevoViaje()
     */
    public Respuesta<NuevoViajeView> nuevoViaje() {
        Respuesta<NuevoViaje> respuestaNuevoViaje = marcaViajeroBO
                .nuevoViaje(sesionCliente.getCliente(), getMailCliente());
        sesionParametros.setContador(new ContadorIntentos(1));
        if (EstadoRespuesta.ERROR
                .equals(respuestaNuevoViaje.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.HABILITAR_TARJETAS_POR_VIAJE_NUEVO_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            return Respuesta.copy(NuevoViajeView.class, respuestaNuevoViaje);
        }
        estadisticaManager.add(
                EstadisticasConstants.HABILITAR_TARJETAS_POR_VIAJE_NUEVO_VIAJE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        if (EstadoRespuesta.WARNING
                .equals(respuestaNuevoViaje.getEstadoRespuesta())) {
            return Respuesta.copy(NuevoViajeView.class, respuestaNuevoViaje);
        }
        return armarNuevoViajeView(respuestaNuevoViaje);
    }

    /**
     * Armar nuevo viaje view.
     *
     * @param respuestaNuevoViaje
     *            the respuesta nuevo viaje
     * @return the respuesta
     */
    private Respuesta<NuevoViajeView> armarNuevoViajeView(
            Respuesta<NuevoViaje> respuestaNuevoViaje) {
        Respuesta<NuevoViajeView> respuestaNuevoViajeView = Respuesta
                .copy(NuevoViajeView.class, respuestaNuevoViaje);
        NuevoViajeView nuevoViajeView = new NuevoViajeView();
        nuevoViajeView.setFechaMaxInicioViaje(MarcaViajeroUtils
                .aplicarFormatoFechaDDMMYYYY(respuestaNuevoViaje.getRespuesta()
                        .getFechaMaxInicioViaje()));
        nuevoViajeView.setDuracionMaximaViaje(
                respuestaNuevoViaje.getRespuesta().getDuracionMaximaViaje());
        nuevoViajeView.setEmail(getMailCliente());
        nuevoViajeView
                .setDestinos(copiarDestinosNuevoViaje(respuestaNuevoViaje));
        nuevoViajeView
                .setTarjetas(copiarTarjetasNuevoViaje(respuestaNuevoViaje));
        nuevoViajeView.setMensajeNacional(respuestaNuevoViaje.getRespuesta()
                .getTarjetaBlackNacional().getTieneNacional()
                        ? getMensaje(
                                CodigoMensajeConstantes.MARCA_VIAJERO_TARJETA_NACIONAL)
                        : null);
//        nuevoViajeView.setMensajeBlack(respuestaNuevoViaje.getRespuesta()
//                .getTarjetaBlackNacional().getTieneBlack()
//                        ? getMensaje(
//                                CodigoMensajeConstantes.MARCA_VIAJERO_BLACK_LEGALES)
//                        : null);
        respuestaNuevoViajeView.setRespuesta(nuevoViajeView);
        return respuestaNuevoViajeView;
    }

    /**
     * Copiar destinos nuevo viaje.
     *
     * @param respuestaNuevoViaje
     *            the respuesta nuevo viaje
     * @return the list
     */
    private List<DestinoView> copiarDestinosNuevoViaje(
            Respuesta<NuevoViaje> respuestaNuevoViaje) {
        List<DestinoView> destinoViewList = new ArrayList<DestinoView>();
        DestinoView destinoView;
        for (Destino destino : respuestaNuevoViaje.getRespuesta()
                .getDestinos()) {
            destinoView = new DestinoView();
            destinoView.setCodigo(destino.getCodigo());
            destinoView.setDescripcion(MarcaViajeroUtils
                    .formatearDestinoCamelCase(destino.getDescripcion()));
            destinoViewList.add(destinoView);
        }
        return destinoViewList;
    }

    /**
     * Copiar tarjetas nuevo viaje.
     *
     * @param respuestaNuevoViaje
     *            the respuesta nuevo viaje
     * @return the list
     */
    private List<TarjetaNuevoViajeView> copiarTarjetasNuevoViaje(
            Respuesta<NuevoViaje> respuestaNuevoViaje) {
        List<TarjetaNuevoViajeView> tarjetasList = new ArrayList<TarjetaNuevoViajeView>();
        TarjetaNuevoViajeView tarjetaNuevoViajeView;
        for (TarjetaNuevoViaje tarjetaNuevoViaje : respuestaNuevoViaje
                .getRespuesta().getTarjetas()) {
            tarjetaNuevoViajeView = new TarjetaNuevoViajeView();
            tarjetaNuevoViajeView.setNombre(tarjetaNuevoViaje.getNombre());
            tarjetaNuevoViajeView.setNombreWs(tarjetaNuevoViaje.getNombreWs());
            tarjetaNuevoViajeView
                    .setIsTitular(tarjetaNuevoViaje.getIsTitular());
            if (!CollectionUtils
                    .isEmpty(tarjetaNuevoViaje.getTarjetasAsociadas())) {
                tarjetaNuevoViajeView.setTarjetasAsociadas(
                        copiarTarjetaAsociadaView(tarjetaNuevoViaje));
            }
            tarjetasList.add(tarjetaNuevoViajeView);
        }
        return tarjetasList;
    }

    /**
     * Copiar tarjeta asociada view.
     *
     * @param tarjetaNuevoViaje
     *            the tarjeta nuevo viaje
     * @return the list
     */
    private List<TarjetaAsociadaView> copiarTarjetaAsociadaView(
            TarjetaNuevoViaje tarjetaNuevoViaje) {
        TarjetaAsociadaView tarjetaAsociadaView;
        List<TarjetaAsociadaView> tarjetaAsociadaViewList = new ArrayList<TarjetaAsociadaView>();
        for (TarjetaAsociada tarjetaAsociada : tarjetaNuevoViaje
                .getTarjetasAsociadas()) {
            tarjetaAsociadaView = new TarjetaAsociadaView();
            tarjetaAsociadaView.setMarca(tarjetaAsociada.getMarca());
            tarjetaAsociadaView.setNumero(formatearNumero(
                    tarjetaAsociada.getMarca(), tarjetaAsociada.getNumero()));
            tarjetaAsociadaView.setAlias(tarjetaAsociada.getAlias());
            tarjetaAsociadaView.setIsTitular(tarjetaAsociada.getIsTitular());
            if (!CollectionUtils
                    .isEmpty(tarjetaAsociada.getFechaInhabilitada())) {
                tarjetaAsociadaView
                        .setFechaInhabilitada(copiarRangoInhabilitacionViewList(
                                tarjetaAsociada.getFechaInhabilitada()));
            }
            tarjetaAsociadaViewList.add(tarjetaAsociadaView);
        }
        return tarjetaAsociadaViewList;
    }

    /**
     * Formatear numero.
     *
     * @param marca
     *            the marca
     * @param numero
     *            the numero
     * @return the string
     */
    private String formatearNumero(String marca, String numero) {
        if (MARCA_AMEX.equals(marca)) { 
            return MASCARA_TARJETA + getUltimosCinco(numero);
        } else {
            return MASCARA_TARJETA + getUltimosCuatro(numero);
        }
    }

    /**
     * Copiar rango inhabilitacion view list.
     *
     * @param rangoInhabilitacionList
     *            the rango inhabilitacion list
     * @return the list
     */
    private List<RangoInhabilitacionView> copiarRangoInhabilitacionViewList(
            List<RangoInhabilitacion> rangoInhabilitacionList) {
        RangoInhabilitacionView rangoInhabilitacionView;
        List<RangoInhabilitacionView> rangoInhabilitacionViewList = new ArrayList<RangoInhabilitacionView>();
        for (RangoInhabilitacion rangoInhabilitacion : rangoInhabilitacionList) {
            rangoInhabilitacionView = new RangoInhabilitacionView();
            rangoInhabilitacionView
                    .setDesde(MarcaViajeroUtils.aplicarFormatoFechaDDMMYYYY(
                            rangoInhabilitacion.getDesde()));
            rangoInhabilitacionView
                    .setHasta(MarcaViajeroUtils.aplicarFormatoFechaDDMMYYYY(
                            rangoInhabilitacion.getHasta()));
            rangoInhabilitacionViewList.add(rangoInhabilitacionView);
        }
        return rangoInhabilitacionViewList;
    }

    /**
     * Gets the mail cliente.
     *
     * @return the mail cliente
     */
    private String getMailCliente() {
        if (sesionParametros.getCredencialesMya() != null
                && sesionParametros.getCredencialesMya().getEmail() != null) {
            return sesionParametros.getCredencialesMya().getEmail();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.web.manager.
     * MarcaViajeroManager#confirmarNuevoViaje(ar.com.santanderrio.obp.servicios
     * .marcaviajero.web.view.ConfirmarNuevoViajeView)
     */
    @Override
    public Respuesta<Void> confirmarNuevoViaje(
            ConfirmarNuevoViajeView nuevoViaje) {
        Boolean permiteReintentar = sesionParametros.getContador().permiteReintentar();
        Respuesta<Void> respuesta = marcaViajeroBO.confirmarNuevoViaje(
                parsearView(nuevoViaje, permiteReintentar), sesionCliente.getCliente());

        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.CONFIRMACION_ALTA_TARJETA_NUEVO_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(
                    EstadisticasConstants.CONFIRMACION_ALTA_TARJETA_NUEVO_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

            if (!permiteReintentar) {
                respuesta = respuestafactory.crearRespuestaError(null,
                        TipoError.ERROR_MARCACION_MANUAL,
                        CodigoMensajeConstantes.ERROR_AL_REINTENTAR_CONFIRMAR_VIAJE);
            }
        }
        return respuesta;
    }

    /**
	 * Parsear view.
	 *
	 * @param viajeHabilitadoView
	 *            the viaje habilitado view
	 * @param permiteReintentar
	 *            the permite reintentar
	 * @return the viaje habilitado
	 */
    private ViajeHabilitado parsearView(
            ConfirmarNuevoViajeView viajeHabilitadoView, Boolean permiteReintentar) {
        ViajeHabilitado viajeHabilitado = new ViajeHabilitado();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            viajeHabilitado.setFechaDesde(
                    sdf.parse(viajeHabilitadoView.getFechaDesde()));
            viajeHabilitado.setFechaHasta(
                    sdf.parse(viajeHabilitadoView.getFechaHasta()));
            viajeHabilitado.setMail(viajeHabilitadoView.getEmail());
            List<Destino> paisess = new ArrayList<Destino>();

            for (DestinoView destino : viajeHabilitadoView
                    .getDestinosSeleccionados()) {
                Destino pais = new Destino();
                pais.setCodigo(destino.getCodigo());
                pais.setDescripcion(MarcaViajeroUtils
                        .formatearDestinoMayusculas(destino.getDescripcion())
                        .toUpperCase());
                paisess.add(pais);
            }
            viajeHabilitado.setDestinos(paisess);
            List<Tarjeta> tarjetaxx = new ArrayList<Tarjeta>();
            for (NuevoViajeTarjetaView tarjeta : viajeHabilitadoView
                    .getTarjetasSeleccionadas()) {
                for (TarjetaAsociadaView tarjetax : tarjeta.getTarjeta()) {
                    Tarjeta tarjetass = new Tarjeta();
                    tarjetass.setNombre(tarjeta.getNombreWs());
                    tarjetass.setUltimosCuatro(
                            quitarXVista(tarjetax.getNumero()));
                    tarjetass.setIsTitular(tarjetax.getIsTitular());
                    tarjetass.setMarca(tarjetax.getMarca());
                    tarjetaxx.add(tarjetass);
                }
            }
            viajeHabilitado.setTarjetas(tarjetaxx);
        } catch (ParseException e) {
            LOGGER.error("Error de parseo{}", e);
        }
        viajeHabilitado.setIsReintento(!permiteReintentar);
        return viajeHabilitado;
    }

    /**
     * Quitar X vista.
     *
     * @param numero
     *            the numero
     * @return the string
     */
    private String quitarXVista(String numero) {
        return numero.replaceAll("X", StringUtils.EMPTY).replaceAll("-", StringUtils.EMPTY);
    }

    /**
     * Elimnar viaje.
     *
     * @param eliminarViajeView
     *            the eliminar viaje view
     * @return the respuesta
     */
    @Override
    public Respuesta<Void> eliminarViaje(EliminarViajeView eliminarViajeView) {
        Respuesta<Void> respuesta = marcaViajeroBO.eliminarViaje(
                new EliminarViaje(sesionCliente.getCliente().getTipoDocumento(),
                        sesionCliente.getCliente().getDni(),
                        sesionCliente.getCliente().getSexo(), getMailCliente(),
                        Long.parseLong(
                                eliminarViajeView.getIdentificadorViaje())));
        if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.MARCA_VIAJERO_ELIMINAR_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.MARCA_VIAJERO_ELIMINAR_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            if (sesionParametros.getContador().permiteReintentar()) {
                return respuestafactory.crearRespuestaError(null,
                        TipoError.ERROR_ELIMINACION_REINTENTAR, StringUtils.EMPTY);
            }
        }
        if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())) {
            estadisticaManager.add(
                    EstadisticasConstants.MARCA_VIAJERO_ELIMINAR_VIAJE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuesta;
    }

}
