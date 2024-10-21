/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ConfirmarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.EliminarViajeRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Fecha;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerPaisesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerTarjetasDelSocioResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesRequest;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.ObtenerViajesResponse;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Pais;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.TarjetaConFecha;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje.AccionesPermitidas;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje.Paises;
import ar.com.santanderrio.obp.generated.webservices.marcaviajero.Viaje.Tarjetas;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.marcaviajero.dao.MarcaViajeroDAO;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Destino;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.EliminarViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.InfoTarjetas;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.NuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.RangoInhabilitacion;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.Tarjeta;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaAsociada;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaBlackNacional;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaDelSocio;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.TarjetaNuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajeHabilitado;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajesHabilitados;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroErrorDesconocidoException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroErrorInternoException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroException;
import ar.com.santanderrio.obp.servicios.marcaviajero.exceptions.MarcaViajeroNoExisteViajeException;
import ar.com.santanderrio.obp.servicios.marcaviajero.utils.MarcaViajeroUtils;

/**
 * The Class MarcaViajeroBOImpl.
 */
@Component
public class MarcaViajeroBOImpl implements MarcaViajeroBO {

    /** The Constant MARCA_AMEX. */
    private static final String MARCA_AMEX = "AMEX";

    /** The Constant MARCA_VISA_DEBITO. */
    private static final String MARCA_VISA_DEBITO = "VISA Débito";

    /** The Constant PRODUCTO_DEBITO_VISA. */
    private static final String PRODUCTO_DEBITO_VISA = "DEBITO";

    /** The Constant MARCA_VISA. */
    private static final String MARCA_VISA = "VISA";

    /** The Constant PRODUCTO_CREDITO_VISA. */
    private static final String PRODUCTO_CREDITO_VISA = "CREDITO";

    /** The Constant CLASE_CUENTA_NACIONAL. */
    private static final String CLASE_CUENTA_NACIONAL = "N";

    /** The Constant CLASE_CUENTA_BLACK. */
    private static final String CLASE_CUENTA_BLACK = "H";

    /** The Constant TIPO_CUENTA_AMEX. */
    private static final String TIPO_CUENTA_AMEX = "42";

    /** The Constant TIPO_CUENTA_VISA. */
    private static final String TIPO_CUENTA_VISA = "07";

    /** The Constant TIPO_CUENTA_BANELCO. */
    private static final String TIPO_CUENTA_BANELCO = "05";

    /** The Constant ESPACIO. */
    private static final String ESPACIO = " ";

    /** The Constant SEPARADOR_APELLIDO_NOMBRE. */
    private static final String SEPARADOR_APELLIDO_NOMBRE = "/";

    /** The Constant VIAJE_STATUS_ACTIVE. */
    private static final String VIAJE_STATUS_ACTIVE = "ACTIVE";

    /** The Constant CONDICION_ADICIONAL_TARJETA. */
    private static final String CONDICION_ADICIONAL_TARJETA = "ADICIONAL";

    /** The Constant CONDICION_TITULAR_TARJETA. */
    private static final String CONDICION_TITULAR_TARJETA = "TITULAR";

    /** The Constant ERROR_REINTENTO_AL_CONFIRMAR_VIAJE. */
    private static final String ERROR_REINTENTO_AL_CONFIRMAR_VIAJE = "Error reintento al confirmar viaje.";

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MarcaViajeroBOImpl.class);

    /** The Constant ACCION_ELIMINAR. */
    private static final String ACCION_ELIMINAR = "ELIMINAR";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The datos solicitante DAO. */
    @Autowired
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /** The marca viajero DAO. */
    @Autowired
    private MarcaViajeroDAO marcaViajeroDAO;

    /** The datos tarjetas BO. */
    @Autowired
    private DatosTarjetasBO datosTarjetasBO;

    /**
     * Obtener datos solicitante.
     *
     * @param cliente
     *            the cliente
     * @return the datos solicitante entity
     */
    private Respuesta<String> obtenerDatosCliente(Cliente cliente) {

    try {
            datosSolicitanteDAO.obtenerSexoCliente(cliente);
            return respuestaFactory.crearRespuestaOk(cliente.getSexo());
        }

        catch (DAOException ex) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MARCA_VIAJERO_SERVICIO_ERROR);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO#
     * obtenerViajes(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Respuesta<ViajesHabilitados> obtenerViajes(Cliente cliente,
            String mailCliente) {

        Respuesta<String> sexoCliente = obtenerDatosCliente(cliente);

        if (EstadoRespuesta.ERROR.equals(sexoCliente.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MENSAJE_ERROR_HABILITAR_TARJETAS_POR_VIAJE);

        }

        return obtenerViajes(cliente, mailCliente, sexoCliente);
    }

    /**
     * Obtener viajes.
     *
     * @param cliente
     *            the cliente
     * @param email
     *            the email
     * @param sexoCliente
     *            the sexo cliente
     * @return the respuesta
     */
    private Respuesta<ViajesHabilitados> obtenerViajes(Cliente cliente,
            String email, Respuesta<String> sexoCliente) {

        ObtenerViajesRequest request = new ObtenerViajesRequest();
        TarjetaBlackNacional tarjetasBlackNacional = esClienteConTarjetaBlackNacional(cliente);
        if (!tarjetasBlackNacional.getTieneTarjetas()) {
            return respuestaFactory.crearRespuestaWarning(null,
                    TipoError.MARCA_VIAJERO_ERROR_SIN_TARJETAS,
                    StringUtils.EMPTY);
        }
        if (tarjetasBlackNacional.getSoloNacional()) {
            return respuestaFactory.crearRespuestaWarning(null,
                    TipoError.MARCA_VIAJERO_ERROR_SIN_TARJETAS_INTERNACIONALES,
                    StringUtils.EMPTY);
        }
        request.setTipoDocumento(
                MarcaViajeroUtils.getTipoDocumento(cliente.getTipoDocumento()));
        request.setNumeroDocumento(cliente.getDni());
        request.setSexo(MarcaViajeroUtils.getSexo(sexoCliente.getRespuesta()));
        ObtenerViajesResponse response = null;
        request.setEmailSocio(email != null ? email : StringUtils.EMPTY);

        try {
            response = marcaViajeroDAO.obtenerViajesDePrismaWS(request);
        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MARCA_VIAJERO_SERVICIO_ERROR);
        }
        return parsearViaje(cliente, response);
    }

    /**
     * Parsear viaje.
     *
     * @param cliente            the cliente
     * @param response            the response
     * @return the respuesta
     */
    private Respuesta<ViajesHabilitados> parsearViaje(Cliente cliente,
            ObtenerViajesResponse response) {

        ViajesHabilitados viajesHabilitados = new ViajesHabilitados();
        viajesHabilitados.setTieneBlack(
                esClienteConTarjetaBlackNacional(cliente).getTieneBlack());
        viajesHabilitados
                .setViajesHabilitados(getViajesHabilitados(cliente, response));

        if (viajesHabilitados.getViajesHabilitados().isEmpty()) {
            return crearRespuestaSinViaje();
        }
        return respuestaFactory.crearRespuestaOk(viajesHabilitados);

    }

    /**
     * Es cliente con tarjeta black nacional.
     *
     * @param cliente
     *            the cliente
     * @return the tarjeta black nacional
     */
    private TarjetaBlackNacional esClienteConTarjetaBlackNacional(
            Cliente cliente) {
        TarjetaBlackNacional tarjetaBlackNacional = new TarjetaBlackNacional();
        if (CollectionUtils.isEmpty(cliente.getCuentas())) {
            return tarjetaBlackNacional;
        }
        for (Cuenta tarjeta : cliente.getCuentas()) {
            if(TIPO_CUENTA_BANELCO.equals(tarjeta.getTipoCuenta())) {
                tarjetaBlackNacional.setTieneTarjetas(Boolean.TRUE);
                tarjetaBlackNacional.setSoloNacional(Boolean.FALSE);
            }
            if (TIPO_CUENTA_VISA.equals(tarjeta.getTipoCuenta())
                    || TIPO_CUENTA_AMEX.equals(tarjeta.getTipoCuenta())) {
                tarjetaBlackNacional.setTieneTarjetas(Boolean.TRUE);
                
                if (CLASE_CUENTA_BLACK.equals(tarjeta.getClaseCuenta())) {
                    tarjetaBlackNacional.setTieneBlack(true);
                }
                if (CLASE_CUENTA_NACIONAL.equals(tarjeta.getClaseCuenta())) {
                    tarjetaBlackNacional.setTieneNacional(true);
                } else {
                    tarjetaBlackNacional.setSoloNacional(Boolean.FALSE);
                }
            }
        }
        
        return tarjetaBlackNacional;
    }

    /**
     * Gets the viajes habilitados.
     *
     * @param cliente
     *            the cliente
     * @param response
     *            the response
     * @return the viajes habilitados
     */
    private List<ViajeHabilitado> getViajesHabilitados(Cliente cliente,
            ObtenerViajesResponse response) {

        List<ViajeHabilitado> viajesHabilitados = new ArrayList<ViajeHabilitado>();

        ObtenerViajesResponse.ListaViajes viajes = response.getListaViajes();

        List<InfoTarjetas> tarjetasReenganche = datosTarjetasBO
                .preparatInfoTarjetas(cliente.getCuentas());

        if (viajes != null && viajes.getViaje() != null
                && CollectionUtils.isNotEmpty(viajes.getViaje())
                && CollectionUtils.isNotEmpty(tarjetasReenganche)) {

            for (Viaje viaje : viajes.getViaje()) {

                if (VIAJE_STATUS_ACTIVE.equals(viaje.getStatus())) {

                    List<Tarjeta> tarjetasViaje = new ArrayList<Tarjeta>();
                    obtenerDatosTarjeta(cliente, tarjetasReenganche, tarjetasViaje, viaje.getTarjetas());
                    if (!CollectionUtils.isEmpty(tarjetasViaje)) {

                        ordenarTarjetasViaje(tarjetasViaje);
                        ViajeHabilitado viajeHabilitado = new ViajeHabilitado();
                        viajeHabilitado.setCantidadTarjetas(String.valueOf(tarjetasViaje.size()));
                        viajeHabilitado.setIdentificadorViaje(viaje.getIdentificadorViaje());
                        viajeHabilitado.setTarjetas(tarjetasViaje);
                        viajeHabilitado.setDestinos(getDestinos(viaje));
                        viajeHabilitado.setCantidadDestinos(String.valueOf(viajeHabilitado.getDestinos().size()));
                        viajeHabilitado.setFechaDesde(getFecha(viaje.getFechaInicio()));
                        viajeHabilitado.setFechaHasta(getFecha(viaje.getFechaFin()));
                        viajeHabilitado.setFechaViaje(getFecha(viaje.getFechaInicio()));
                        viajeHabilitado.setCantidadDias(
                                getCantidadDias(viajeHabilitado.getFechaDesde(), viajeHabilitado.getFechaHasta()));
                        viajeHabilitado
                                .setPermiteEliminar(permiteAccion(ACCION_ELIMINAR, viaje.getAccionesPermitidas()));
                        viajeHabilitado.setPermiteModificar(Boolean.FALSE);
                        viajesHabilitados.add(viajeHabilitado);
                    }
                }

            }
        }
        LOGGER.debug("viajes habilitados: {}", viajesHabilitados);
        return viajesHabilitados;
    }

    /**
     * Ordenar tarjetas viaje.
     *
     * @param tarjetasViaje the tarjetas viaje
     */
    private void ordenarTarjetasViaje(List<Tarjeta> tarjetasViaje) {

        Collections.sort(tarjetasViaje, new Comparator<Tarjeta>() {
            @Override
            public int compare(Tarjeta tarjeta1, Tarjeta tarjeta2) {
                int i = tarjeta1.getPrioridad().compareTo(tarjeta2.getPrioridad());
                if (i != 0) {
                   return i;
                }
                return tarjeta1.getNombre().compareTo(tarjeta2.getNombre());
            }
        });
    }

    /**
     * Obtener datos tarjeta.
     *
     * @param cliente
     *            the cliente
     * @param tarjetasReenganche
     *            the tarjetas reenganche
     * @param tarjetasViaje
     *            the tarjetas viaje
     * @param tarjetas
     *            the tarjetas
     */
    private void obtenerDatosTarjeta(Cliente cliente,
            List<InfoTarjetas> tarjetasReenganche, List<Tarjeta> tarjetasViaje,
            Tarjetas tarjetas) {

        if (tarjetas != null
                && CollectionUtils.isNotEmpty(tarjetas.getTarjeta())) {

            for (ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS : tarjetas
                    .getTarjeta()) {
                InfoTarjetas infoTarjetas = buscarEnTarjetasReenganche(
                        tarjetasReenganche, tarjetaWS);
                if (infoTarjetas != null) {
                    Tarjeta tarj = cargarTarjeta(infoTarjetas, tarjetaWS);

                    tarjetasViaje.add(tarj);
                } else {

                    Tarjeta tarjetaRio = buscarEnTarjetasRio(tarjetasReenganche,
                            tarjetaWS);
                    Tarjeta tarjetaViaje = null;
                    if (tarjetaRio == null) {
                        Tarjeta datosTarjeta = datosTarjetasBO
                                .obtenerDatosTarjeta(cliente,
                                        tarjetasReenganche, tarjetaWS);
                        if (datosTarjeta != null) {
                            tarjetaViaje = new Tarjeta();
                            tarjetaViaje.setUltimosCuatro(
                                    tarjetaWS.getUltimosCuatro());
                            tarjetaViaje.setNumero(datosTarjeta.getNumero());
                            tarjetaViaje.setNombre(getNombreTitularTarjeta(
                                    tarjetaWS.getApellidoNombre()));
                            tarjetaViaje.setIsTitular(isTitular(tarjetaWS.getCondicion()));
                            tarjetaViaje.setMarca(
                                    getMarcaTarjeta(tarjetaWS.getProducto()));
                            tarjetasViaje.add(tarjetaViaje);
                        }
                    }
                }

            }
        }
    }

    /**
     * Cargar tarjeta.
     *
     * @param infoTarjetas the info tarjetas
     * @param tarjetaWS the tarjeta WS
     * @return the tarjeta
     */
    private Tarjeta cargarTarjeta(InfoTarjetas infoTarjetas,
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {
        if(tarjetaWS.getUltimosCuatro().equals(infoTarjetas.getUltimosCuatroDigitos())) {
            Tarjeta tarj = new Tarjeta();
            tarj.setIsTitular(infoTarjetas.getIsTitular());
            tarj.setMarca(infoTarjetas.getMarca());
            tarj.setAlias(infoTarjetas.getAliasCuenta());
            tarj.setNombre(ISBANStringUtils.convertirStringToCamelcase(
                    infoTarjetas.getNombrePlastico()));
            tarj.setNumero(infoTarjetas.getNumeroTarjetaCredito());
            tarj.setUltimosCuatro(tarjetaWS.getUltimosCuatro());
            tarj.setPrioridad(0);
            return tarj;
        }
        return cargarTarjetaAsociada(infoTarjetas, tarjetaWS);
    }

    /**
     * Cargar tarjeta asociada.
     *
     * @param infoTarjetas the info tarjetas
     * @param tarjetaWS the tarjeta WS
     * @return the tarjeta
     */
    private Tarjeta cargarTarjetaAsociada(InfoTarjetas infoTarjetas,
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {
        Tarjeta tarjeta = new Tarjeta();
        for(Tarjeta tarj: infoTarjetas.getTarjetasAsociadas()) {
            if(tarj.getUltimosCuatro().equals(tarjetaWS.getUltimosCuatro())){
                
                tarjeta.setIsTitular(isTitular(tarjetaWS.getCondicion()));
                tarjeta.setMarca(infoTarjetas.getMarca());
                tarjeta.setAlias(null);
                tarjeta.setNombre(formatearNombre(tarjetaWS.getApellidoNombre()));
                tarjeta.setNumero(tarj.getNumero());
                tarjeta.setUltimosCuatro(tarjetaWS.getUltimosCuatro());    
            }
        }
        return tarjeta;
    }

    /**
     * Buscar en tarjetas reenganche.
     *
     * @param tarjetasReenganche
     *            the tarjetas reenganche
     * @param tarjetaWS
     *            the tarjeta WS
     * @return the info tarjetas
     */
    private InfoTarjetas buscarEnTarjetasReenganche(
            List<InfoTarjetas> tarjetasReenganche,
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {

        for (InfoTarjetas infoTarjeta : tarjetasReenganche) {
            if (infoTarjeta.getUltimosCuatroDigitos()
                    .equals(tarjetaWS.getUltimosCuatro())) {
                return infoTarjeta;
            }
            if (!CollectionUtils.isEmpty(infoTarjeta.getTarjetasAsociadas())) {
                for (Tarjeta tarjeta : infoTarjeta.getTarjetasAsociadas()) {
                    if (tarjeta.getUltimosCuatro()
                            .equals(tarjetaWS.getUltimosCuatro())) {
                        return infoTarjeta;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Buscar en tarjetas rio.
     *
     * @param listaInfoTarjetas
     *            the lista info tarjetas
     * @param tarjetaWS
     *            the tarjeta WS
     * @return the tarjeta
     */
    private Tarjeta buscarEnTarjetasRio(List<InfoTarjetas> listaInfoTarjetas,
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {

        for (InfoTarjetas infoTarjeta : listaInfoTarjetas) {

            Tarjeta tarjeta = contieneTarjeta(infoTarjeta, tarjetaWS);
            if (tarjeta != null) {
                return tarjeta;
            }
        }
        return null;
    }

    /**
     * Contiene tarjeta.
     *
     * @param infoTarjeta
     *            the info tarjeta
     * @param tarjetaWS
     *            the tarjeta WS
     * @return the tarjeta
     */
    private Tarjeta contieneTarjeta(InfoTarjetas infoTarjeta,
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetaWS) {
        for (Tarjeta tarjeta : infoTarjeta.getTarjetasAsociadas()) {
            if (tarjetaWS.getUltimosCuatro()
                    .equals(tarjeta.getUltimosCuatro())) {
                return tarjeta;
            }
        }
        return null;
    }

    /**
     * Crear respuesta sin viaje.
     *
     * @return the respuesta
     */
    public Respuesta<ViajesHabilitados> crearRespuestaSinViaje() {
        return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY,
                TipoError.MARCA_VIAJERO_SIN_VIAJES,
                CodigoMensajeConstantes.MARCA_VIAJERO_SIN_VIAJES_ERROR);
    }

    /**
     * Permite accion.
     *
     * @param accion
     *            the accion
     * @param acciones
     *            the acciones
     * @return the boolean
     */
    private Boolean permiteAccion(String accion, AccionesPermitidas acciones) {

        if (acciones != null
                && CollectionUtils.isNotEmpty(acciones.getAccion())) {
            for (String accionPermitida : acciones.getAccion()) {
                if (accion.equals(accionPermitida)) {

                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Gets the fecha.
     *
     * @param fecha
     *            the fecha
     * @return the fecha
     */
    private Date getFecha(XMLGregorianCalendar fecha) {
        if(fecha != null) {
            return fecha.toGregorianCalendar().getTime();
        }
        return null;
    }

    /**
     * Gets the cantidad dias.
     *
     * @param fechaInicio
     *            the fecha inicio
     * @param fechaFin
     *            the fecha fin
     * @return the cantidad dias
     */
    private String getCantidadDias(Date fechaInicio, Date fechaFin) {

        DateTime fechaInicioDT = new DateTime(fechaInicio);
        DateTime fechaFinDT = new DateTime(fechaFin);
        return String
                .valueOf(Days.daysBetween(fechaInicioDT, fechaFinDT).getDays());
    }

    /**
     * Obtiene el nombre y apellido titular de la tarjeta, en caso de solo recibir
     * el nombre o el apellido retorna lo que recibe en cambio si no recibi valor
     * retorna cadena Empty.
     *
     * @param apellidoNombre
     *            the apellido nombre
     * @return the nombre titular tarjeta
     */
    private String getNombreTitularTarjeta(String apellidoNombre) {
        String[] parts = StringUtils.split(apellidoNombre, SEPARADOR_APELLIDO_NOMBRE);
        if (parts.length > 1) {
            String apellido = parts[0];
            String nombre = parts[1];
            return StringUtils.trimToEmpty(nombre + ESPACIO + apellido);
        } else {
            return StringUtils.trimToEmpty(parts[0]);
        }
    }

    /**
     * Gets the marca tarjeta.
     *
     * @param producto
     *            the producto
     * @return the marca tarjeta
     */
    private String getMarcaTarjeta(String producto) {
        if (PRODUCTO_CREDITO_VISA.equals(producto)) {
            return MARCA_VISA;
        }
        if (PRODUCTO_DEBITO_VISA.equals(producto)) {
            return MARCA_VISA_DEBITO;
        }
        return MARCA_AMEX;
    }

    /**
     * Gets the destinos.
     *
     * @param viaje
     *            the viaje
     * @return the destinos
     */
    private List<Destino> getDestinos(Viaje viaje) {
        List<Destino> destinos = new ArrayList<Destino>();
        try {
            for (Pais pais : viaje.getPaises().getPais()) {
                Destino destino = new Destino();
                destino.setCodigo(pais.getCodPais());
                destino.setDescripcion(
                        ISBANStringUtils.formateoStringPrimeraLetraMayuscula(
                                pais.getDescripcionPais()));
                destinos.add(destino);
            }
        } catch (NullPointerException e) {
            return new ArrayList<Destino>();
        }
        return destinos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO#
     * nuevoViaje(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * java.lang.String)
     */
    @Override
    public Respuesta<NuevoViaje> nuevoViaje(Cliente cliente,
            String mailCliente) {
        Respuesta<String> respuestaSexo = obtenerSexo(cliente);
        if (EstadoRespuesta.ERROR.equals(respuestaSexo.getEstadoRespuesta())) {
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        return nuevoViaje(cliente, mailCliente, respuestaSexo.getRespuesta());
    }

    /**
     * Obtener sexo.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    private Respuesta<String> obtenerSexo(Cliente cliente) {
        if (cliente.getSexo() == null) {
            Respuesta<String> sexoCliente = obtenerDatosCliente(cliente);
            if (EstadoRespuesta.ERROR
                    .equals(sexoCliente.getEstadoRespuesta())) {
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                        TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
            }
            return sexoCliente;
        } else {
            return respuestaFactory.crearRespuestaOk(cliente.getSexo());
        }
    }

    /**
     * Nuevo viaje.
     *
     * @param cliente
     *            the cliente
     * @param email
     *            the email
     * @param sexo
     *            the sexo
     * @return the respuesta
     */
    private Respuesta<NuevoViaje> nuevoViaje(Cliente cliente, String email,
            String sexo) {
        ObtenerTarjetasDelSocioRequest request = new ObtenerTarjetasDelSocioRequest();
        request.setTipoDocumento(
                MarcaViajeroUtils.getTipoDocumento(cliente.getTipoDocumento()));
        request.setNumeroDocumento(cliente.getDni());
        request.setSexo(MarcaViajeroUtils.getSexo(sexo));
        request.setEmailSocio(email != null ? email : StringUtils.EMPTY);
        ObtenerTarjetasDelSocioResponse tarjetas = null;
        ObtenerPaisesResponse paises = null;
        TarjetaBlackNacional tarjetaBlackNacional = esClienteConTarjetaBlackNacional(cliente);
        if (tarjetaBlackNacional.getTieneTarjetas()) {
            try {
                tarjetas = marcaViajeroDAO.obtenerTarjetasDelSocio(request);
                paises = marcaViajeroDAO.obtenerPaises();
                if (paises == null
                        || paises.getPaises() == null || ISBANStringUtils.isNullOrEmpty(paises.getPaises().getPais())) {
                    throw new DAOException();
                }
            } catch (DAOException e) {
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.MARCA_VIAJERO_NUEVO_VIAJE_ERROR);
            }
            if (tarjetas == null || tarjetas.getTarjetas() == null || CollectionUtils.isEmpty(tarjetas.getTarjetas().getTarjeta())) {
                
                return respuestaFactory.crearRespuestaWarning(null, TipoError.MARCA_VIAJERO_ERROR_SIN_TARJETAS,
                        StringUtils.EMPTY);
            }
        }
        List<TarjetaDelSocio> tarjetasSocio = getTarjetasDelSocio(tarjetas);

        try {
            
            datosTarjetasBO.filtrarDatosTarjeta(cliente, tarjetasSocio);
        } catch (BusinessException e) {
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MARCA_VIAJERO_NUEVO_VIAJE_ERROR);
        }
        if (CollectionUtils.isEmpty(tarjetasSocio)) {
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.MARCA_VIAJERO_NUEVO_VIAJE_ERROR);
        }
        List<Destino> destinos = parsearDestinos(paises);
        Respuesta<NuevoViaje> respuestaNuevoViaje = armarRespuestaNuevoViaje(
                tarjetasSocio, destinos, getFecha(tarjetas.getFechaInicioMaxima()), tarjetas.getDuracionMaxima());
        respuestaNuevoViaje.getRespuesta().setTarjetaBlackNacional(
                esClienteConTarjetaBlackNacional(cliente));
        return respuestaNuevoViaje;
    }

    /**
     * Gets the tarjetas del socio.
     *
     * @param tarjetas the tarjetas
     * @return the tarjetas del socio
     */
    private List<TarjetaDelSocio> getTarjetasDelSocio(ObtenerTarjetasDelSocioResponse tarjetas) {
        
        List<TarjetaDelSocio> tarjetasDelSocio = new ArrayList<TarjetaDelSocio>();
        
        if(tarjetas != null && tarjetas.getTarjetas()!= null && !CollectionUtils.isEmpty(tarjetas.getTarjetas().getTarjeta())) {
            
            for(TarjetaConFecha tarjeta: tarjetas.getTarjetas().getTarjeta()) {
                TarjetaDelSocio tarjetaDelSocio = new TarjetaDelSocio();
                tarjetaDelSocio.setApellidoNombre(tarjeta.getApellidoNombre());
                tarjetaDelSocio.setNombreWs(tarjeta.getApellidoNombre());
                tarjetaDelSocio.setCondicion(tarjeta.getCondicion());
                tarjetaDelSocio.setUltimosCuatro(tarjeta.getUltimosCuatro());
                tarjetaDelSocio.setProducto(tarjeta.getProducto());
                tarjetaDelSocio.setFechasInhabilitadas(mapearADateList(tarjeta.getFechasInhabilitadas().getFecha()));
                tarjetasDelSocio.add(tarjetaDelSocio);
            }
        }
        
        return tarjetasDelSocio;
    }

    /**
     * Armar respuesta nuevo viaje.
     *
     * @param tarjetas            the tarjetas
     * @param paises            the paises
     * @param fechaInicio the fecha inicio
     * @param duracionMaxima the duracion maxima
     * @return the respuesta
     */
    private Respuesta<NuevoViaje> armarRespuestaNuevoViaje(
            List<TarjetaDelSocio> tarjetas,
            List<Destino> paises, Date fechaInicio, Integer duracionMaxima) {
        NuevoViaje nuevoViaje = new NuevoViaje();
        nuevoViaje.setFechaMaxInicioViaje(fechaInicio);
        nuevoViaje.setDuracionMaximaViaje(duracionMaxima);
        nuevoViaje.setDestinos(paises);
        nuevoViaje.setTarjetas(
                parsearTarjetas(tarjetas));
        return respuestaFactory.crearRespuestaOk(nuevoViaje);
    }

    /**
     * Parsear destinos.
     *
     * @param obtenerPaisesResponse
     *            the obtener paises response
     * @return the list
     */
    private List<Destino> parsearDestinos(
            ObtenerPaisesResponse obtenerPaisesResponse) {
        List<Destino> destinosList = new ArrayList<Destino>();
        Destino destino = null;
        if(obtenerPaisesResponse!= null && obtenerPaisesResponse.getPaises()!= null
                && obtenerPaisesResponse.getPaises().getPais()!= null) {

            for (Pais pais : obtenerPaisesResponse.getPaises().getPais()) {
                destino = new Destino();
                destino.setCodigo(pais.getCodPais());
                destino.setDescripcion(pais.getDescripcionPais());
                destinosList.add(destino);
            }
        }
        return destinosList;
    }

    /**
     * Parsear tarjetas.
     *
     * @param tarjetasDelSocio the tarjetas del socio
     * @return the list
     */
    private List<TarjetaNuevoViaje> parsearTarjetas(
            List<TarjetaDelSocio> tarjetasDelSocio) {
        formatearNombres(tarjetasDelSocio);
        ordenarTarjetas(tarjetasDelSocio);
        return agruparTarjetas(tarjetasDelSocio);
    }

    /**
     * Formatear nombres.
     *
     * @param tarjetaDelSocio
     *            the tarjeta con fecha list
     * @return the list
     */
    private List<TarjetaDelSocio> formatearNombres(
            List<TarjetaDelSocio> tarjetaDelSocio) {
        for (TarjetaDelSocio tarjetaConFecha : tarjetaDelSocio) {
            tarjetaConFecha.setApellidoNombre(
                    formatearNombre(tarjetaConFecha.getApellidoNombre()));
        }
        return tarjetaDelSocio;
    }

    /**
     * Ordenar tarjetas.
     *
     * @param tarjetas
     *            the tarjetas
     * @return the list
     */
    private List<TarjetaDelSocio> ordenarTarjetas(
            List<TarjetaDelSocio> tarjetas) {
        Collections.sort(tarjetas, new Comparator<TarjetaDelSocio>() {
            @Override
            public int compare(TarjetaDelSocio tarjeta1,
                    TarjetaDelSocio tarjeta2) {
                int i = tarjeta1.getPrioridad().compareTo(tarjeta2.getPrioridad());
                if(i!= 0) {
                    return i;
                }

                i = tarjeta1.getApellidoNombre().compareTo(tarjeta2.getApellidoNombre());
                if(i!= 0) {
                    return i;
                }
                
                i = tarjeta1.getProductoPrioridad()
                        .compareTo(tarjeta2.getProductoPrioridad());
                if (i != 0) {
                    return i;
                }
                
                return tarjeta2.getCondicion()
                        .compareTo(tarjeta1.getCondicion());
            }
        });
        return tarjetas;
    }

    /**
     * Agrupar tarjetas.
     *
     * @param tarjetasDelSocio
     *            the tarjeta con fecha list
     * @return the list
     */
    private List<TarjetaNuevoViaje> agruparTarjetas(
            List<TarjetaDelSocio> tarjetasDelSocio) {
        Map<TarjetaNuevoViaje, List<TarjetaAsociada>> clienteTarjetasMap = new LinkedHashMap<TarjetaNuevoViaje, List<TarjetaAsociada>>();
        for (TarjetaDelSocio tarjetaConFecha : tarjetasDelSocio) {
            TarjetaNuevoViaje tarjetaNuevoViaje = new TarjetaNuevoViaje();
            tarjetaNuevoViaje.setNombre(tarjetaConFecha.getApellidoNombre());
            tarjetaNuevoViaje.setNombreWs(tarjetaConFecha.getNombreWs());
            if (clienteTarjetasMap.containsKey(tarjetaNuevoViaje)) {
                clienteTarjetasMap.get(tarjetaNuevoViaje)
                        .add(crearTarjetaAsociada(tarjetaConFecha));
            } else {
                List<TarjetaAsociada> tarjetaAsociadaList = new ArrayList<TarjetaAsociada>();
                tarjetaAsociadaList.add(crearTarjetaAsociada(tarjetaConFecha));
                clienteTarjetasMap.put(tarjetaNuevoViaje, tarjetaAsociadaList);
            }
        }
        List<TarjetaNuevoViaje> tarjetaNuevoViajeList = new ArrayList<TarjetaNuevoViaje>();
        for (Map.Entry<TarjetaNuevoViaje, List<TarjetaAsociada>> clienteTarjetasEntry : clienteTarjetasMap
                .entrySet()) {
            clienteTarjetasEntry.getKey()
                    .setTarjetasAsociadas(ordenarTarjetasAsociadas(clienteTarjetasEntry.getValue()));
            tarjetaNuevoViajeList.add(clienteTarjetasEntry.getKey());
        }
        return tarjetaNuevoViajeList;
    }

    /**
	 * Ordenar tarjetas asociadas.
	 *
	 * @param tarjetas
	 *            the tarjetas
	 * @return the list
	 */
    private List<TarjetaAsociada> ordenarTarjetasAsociadas(List<TarjetaAsociada> tarjetas) {
        
        Collections.sort(tarjetas, new Comparator<TarjetaAsociada>() {
            @Override
            public int compare(TarjetaAsociada tarjeta1,
                    TarjetaAsociada tarjeta2) {
                
                int i = tarjeta1.getProductoPrioridad()
                        .compareTo(tarjeta2.getProductoPrioridad());
                if (i != 0) {
                    return i;
                }
                
                return tarjeta2.getIsTitular()
                        .compareTo(tarjeta1.getIsTitular());
            }
        });
        return tarjetas;
    }

    /**
     * Crear tarjeta asociada.
     *
     * @param tarjetaDelSocio
     *            the tarjeta con fecha
     * @return the tarjeta asociada
     */
    private TarjetaAsociada crearTarjetaAsociada(
            TarjetaDelSocio tarjetaDelSocio) {

        return new TarjetaAsociada(
                traducirMarcaProducto(tarjetaDelSocio.getProducto()),
                tarjetaDelSocio.getNumero()
                , tarjetaDelSocio.getApellidoNombre(), tarjetaDelSocio.getAlias(),
                !ISBANStringUtils.isNullOrEmpty(crearRangosInhabilitacion(
                        tarjetaDelSocio.getFechasInhabilitadas()))
                                ? crearRangosInhabilitacion(tarjetaDelSocio
                                        .getFechasInhabilitadas())
                                : null, isTitular(tarjetaDelSocio.getCondicion()));
    }

    /**
     * Formatear nombre.
     *
     * @param apellidoNombre
     *            the apellido nombre
     * @return the string
     */
    private String formatearNombre(String apellidoNombre) {

        if (apellidoNombre.contains(SEPARADOR_APELLIDO_NOMBRE)) {
            apellidoNombre = getNombreTitularTarjeta(apellidoNombre);

        }
        return ISBANStringUtils.convertirStringToCamelcase(apellidoNombre);
    }

    /**
     * Checks if is titular.
     *
     * @param condicion
     *            the condicion
     * @return the boolean
     */
    private Boolean isTitular(String condicion) {
        return  CONDICION_TITULAR_TARJETA.equals(StringUtils.deleteWhitespace(condicion));
    }

    /**
     * Traducir marca producto.
     *
     * @param marcaProducto
     *            the marca producto
     * @return the string
     */
    private String traducirMarcaProducto(String marcaProducto) {
        if (PRODUCTO_CREDITO_VISA.equals(marcaProducto)) {
            return MARCA_VISA;
        } else if (MARCA_AMEX.equals(marcaProducto)) {
            return MARCA_AMEX;
        } else if (PRODUCTO_DEBITO_VISA.equals(marcaProducto)) {
            return MARCA_VISA_DEBITO;
        }
        return StringUtils.EMPTY;
    }

    /**
     * Mapear A date list.
     *
     * @param fechaList
     *            the fecha list
     * @return the list
     */
    private List<Date> mapearADateList(List<Fecha> fechaList) {
        List<Date> fechasDateList = new ArrayList<Date>();
        for (Fecha fecha : fechaList) {
            XMLGregorianCalendar xmlGregorianCalendar = null;
            try {
                xmlGregorianCalendar = DatatypeFactory.newInstance()
                        .newXMLGregorianCalendar();
                xmlGregorianCalendar.setDay(fecha.getValue().getDay());
                xmlGregorianCalendar.setMonth(fecha.getValue().getMonth());
                xmlGregorianCalendar.setYear(fecha.getValue().getYear());
                fechasDateList.add(
                        xmlGregorianCalendar.toGregorianCalendar().getTime());
            } catch (DatatypeConfigurationException e) {
                LOGGER.error("Error al obtener Resumen", e);
            }
        }
        Collections.sort(fechasDateList);
        return fechasDateList;
    }

    /**
     * Crear rangos inhabilitacion.
     *
     * @param fechasInhabilitadas the fechas inhabilitadas
     * @return the list
     */
    private List<RangoInhabilitacion> crearRangosInhabilitacion(
            List<Date> fechasInhabilitadas) {
        List<RangoInhabilitacion> rangoInhabilitacionList = new ArrayList<RangoInhabilitacion>();
        RangoInhabilitacion rangoInhabilitacion = new RangoInhabilitacion();
        for (int i = 0; i < fechasInhabilitadas.size(); i++) {
            Date fecha = fechasInhabilitadas.get(i);
            if (rangoInhabilitacion.getDesde() == null) {
                rangoInhabilitacion.setDesde(fecha);
            }
            if (i + 1 < fechasInhabilitadas.size()) {
                Date fechaSiguiente = fechasInhabilitadas.get(i + 1);
                Calendar calFechaSiguiente = Calendar.getInstance();
                calFechaSiguiente.setTime(fecha);
                calFechaSiguiente.add(Calendar.DATE, 1);
                if (!calFechaSiguiente.getTime().toString()
                        .equals(fechaSiguiente.toString())
                        && !fecha.toString()
                                .equals(fechaSiguiente.toString())) {
                    rangoInhabilitacion.setHasta(fecha);
                    rangoInhabilitacionList.add(rangoInhabilitacion);
                    rangoInhabilitacion = new RangoInhabilitacion();
                }
            } else {
                rangoInhabilitacion.setHasta(fecha);
                rangoInhabilitacionList.add(rangoInhabilitacion);
            }
        }
        return rangoInhabilitacionList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO#
     * confirmarNuevoViaje(ar.com.santanderrio.obp.servicios.marcaviajero.
     * entities.ViajeHabilitado,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<Void> confirmarNuevoViaje(ViajeHabilitado viajeHabilitado,
            Cliente cliente) {

        String sexoCliente = MarcaViajeroUtils.getSexo(cliente.getSexo());
        String tipoDocCliente = MarcaViajeroUtils.getTipoDocumento(cliente.getTipoDocumento());
        String nroDocCliente = cliente.getDni();
        String mailCliente = viajeHabilitado.getMail();

        Viaje viaje = new Viaje();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 12);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(viajeHabilitado.getFechaDesde());
        XMLGregorianCalendar fechaDesde;

        try {
            fechaDesde = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(c);
            viaje.setFechaInicio(fechaDesde);
            viaje.getFechaInicio()
                    .setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            viaje.getFechaInicio().setTime(DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED);

        } catch (DatatypeConfigurationException e1) {
            LOGGER.error("Error al parsear fecha inicio:" + e1);
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CONFIRMAR_VIAJE);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 2);
        c.setTime(viajeHabilitado.getFechaHasta());
        XMLGregorianCalendar fechaHasta;

        try {
            fechaHasta = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(c);
            viaje.setFechaFin(fechaHasta);
            viaje.getFechaFin().setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            viaje.getFechaFin().setTime(DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED,
                    DatatypeConstants.FIELD_UNDEFINED);

        } catch (DatatypeConfigurationException e1) {
            LOGGER.error("Error al parsear fecha hasta: {}" + e1);
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CONFIRMAR_VIAJE);
        }

        List<Pais> paisess = new ArrayList<Pais>();
        Paises paises = new Paises();
        for (Destino destino : viajeHabilitado.getDestinos()) {
            Pais pais = new Pais();
            pais.setCodPais(destino.getCodigo());
            pais.setDescripcionPais(destino.getDescripcion());
            paisess.add(pais);
            paises.setPais(paisess);
        }
        viaje.setPaises(paises);

        Tarjetas tarjetasx = new Tarjetas();
        for (Tarjeta tarjeta : viajeHabilitado.getTarjetas()) {
            ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta tarjetass = new ar.com.santanderrio.obp.generated.webservices.marcaviajero.Tarjeta();
            tarjetass.setApellidoNombre(tarjeta.getNombre());
            tarjetass.setUltimosCuatro(tarjeta.getUltimosCuatro().trim());
            tarjetass.setUltimosCuatro(tarjetass.getUltimosCuatro().substring(tarjetass.getUltimosCuatro().length()-4, tarjetass.getUltimosCuatro().length()));
            tarjetass.setCondicion(getCondicionCliente(tarjeta));
            tarjetasx.getTarjeta().add(tarjetass);
        }
        viaje.setTarjetas(tarjetasx);

        ConfirmarViajeRequest request = new ConfirmarViajeRequest();
        request.setTipoDocumento(tipoDocCliente);
        request.setNumeroDocumento(nroDocCliente);
        request.setSexo(sexoCliente);
        request.setEmailSocio(mailCliente);
        request.setReintento(viajeHabilitado.getIsReintento());
        request.setTelefono(StringUtils.EMPTY);
        request.setViaje(viaje);

        try {
            marcaViajeroDAO.confirmarViajeWS(request);
        } catch (MarcaViajeroErrorInternoException e) {
            LOGGER.error("Error al confirmar viaje", e);
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_MARCACION,
                    CodigoMensajeConstantes.ERROR_INTERNO_CONFIRMAR_VIAJE);
        } catch (MarcaViajeroException e) {
            LOGGER.error(ERROR_REINTENTO_AL_CONFIRMAR_VIAJE, e);
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_MARCACION_MANUAL,
                    CodigoMensajeConstantes.ERROR_REINTENTO_CONFIRMAR_VIAJE);
        } catch (MarcaViajeroErrorDesconocidoException e) {
            LOGGER.error("Error al confirmar viaje", e);
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_MARCACION_REINTENTAR,
                    CodigoMensajeConstantes.ERROR_INTERNO_CONFIRMAR_VIAJE);
        } catch (DAOException e) {
            LOGGER.error("Error interno al confirmar viaje", e);
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CONFIRMAR_VIAJE);
        }
        return respuestaFactory.crearRespuestaOk(null ,StringUtils.EMPTY, CodigoMensajeConstantes.MARCA_VIAJERO_ALTA_OK);
    }

    /**
     * Gets the condicion cliente.
     *
     * @param tarjeta
     *            the tarjeta
     * @return the condicion cliente
     */
    private String getCondicionCliente(Tarjeta tarjeta) {

        if (tarjeta.getIsTitular()) {
            return CONDICION_TITULAR_TARJETA;
        } else {
            return CONDICION_ADICIONAL_TARJETA;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.bo.MarcaViajeroBO#
     * eliminarViaje(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public Respuesta<Void> eliminarViaje(EliminarViaje viaje) {

        EliminarViajeRequest request = new EliminarViajeRequest();
        request.setTipoDocumento(
                MarcaViajeroUtils.getTipoDocumento(viaje.getTipoDocumento()));
        request.setNumeroDocumento(viaje.getNumeroDocumento());
        request.setSexo(MarcaViajeroUtils.getSexo(viaje.getSexo()));
        request.setEmailSocio(viaje.getEmailSocio());
        request.setIdentificadorViaje(viaje.getIdentificadorViaje());

        try {
            marcaViajeroDAO.eliminarViaje(request);
        } catch (MarcaViajeroNoExisteViajeException e) {
            LOGGER.error(ERROR_REINTENTO_AL_CONFIRMAR_VIAJE, e);
            return respuestaFactory.crearRespuestaWarning(null,
                    TipoError.ERROR_MARCACION_MANUAL, StringUtils.EMPTY);

        } catch (DAOException e) {
            LOGGER.error(ERROR_REINTENTO_AL_CONFIRMAR_VIAJE, e);
            return respuestaFactory.crearRespuestaError(null,
                    TipoError.ERROR_MARCACION_MANUAL, StringUtils.EMPTY);
        }
        return respuestaFactory.crearRespuestaOk(null);
    }

}
