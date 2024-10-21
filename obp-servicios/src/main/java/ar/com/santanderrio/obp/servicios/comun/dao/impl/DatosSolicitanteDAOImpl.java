/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.monedero.entities.ObtenerTrxTagMedioDeRecargaEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteDAOImpl.
 */
@Component
public class DatosSolicitanteDAOImpl implements DatosSolicitanteDAO {

    /** The Constant MONEDERO_SOLICITANTE_NUP_INDEX. */
    private static final int MONEDERO_SOLICITANTE_NUP_INDEX = 1;

    /** The Constant MONEDERO_SOLICITANTE_APELLIDO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_APELLIDO_INDEX = 3;

    /** The Constant MONEDERO_SOLICITANTE_NOMBRE_INDEX. */
    private static final int MONEDERO_SOLICITANTE_NOMBRE_INDEX = 5;

    /** The Constant MONEDERO_SOLICITANTE_FECHA_NACIMIENTO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_FECHA_NACIMIENTO_INDEX = 11;

    /** The Constant MONEDERO_SOLICITANTE_DOC_TIPO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_DOC_TIPO_INDEX = 51;

    /** The Constant MONEDERO_SOLICITANTE_DOC_INDEX. */
    private static final int MONEDERO_SOLICITANTE_DOC_INDEX = 52;

    /** The Constant MONEDERO_SOLICITANTE_PAIS_NACIMIENTO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_PAIS_NACIMIENTO_INDEX = 15;

    /** The Constant MONEDERO_SOLICITANTE_SEXO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_SEXO_INDEX = 9;

    /** The Constant MONEDERO_SOLICITANTE_ESTADO_CIVIL_INDEX. */
    private static final int MONEDERO_SOLICITANTE_ESTADO_CIVIL_INDEX = 8;

    /** The Constant MONEDERO_SOLICITANTE_NACIONALIDAD_INDEX. */
    private static final int MONEDERO_SOLICITANTE_NACIONALIDAD_INDEX = 22;

    /** The Constant MONEDERO_SOLICITANTE_CALLE_INDEX. */
    private static final int MONEDERO_SOLICITANTE_CALLE_INDEX = 75;

    /** The Constant MONEDERO_SOLICITANTE_CALLE_NRO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_CALLE_NRO_INDEX = 86;

    /** The Constant MONEDERO_SOLICITANTE_PISO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_PISO_INDEX = 104;

    /** The Constant MONEDERO_SOLICITANTE_DEPTO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_DEPTO_INDEX = 105;

    /** The Constant MONEDERO_SOLICITANTE_LOCALIDAD_INDEX. */
    private static final int MONEDERO_SOLICITANTE_LOCALIDAD_INDEX = 88;

    /** The Constant MONEDERO_SOLICITANTE_CP_INDEX. */
    private static final int MONEDERO_SOLICITANTE_CP_INDEX = 91;

    /** The Constant MONEDERO_SOLICITANTE_PROVINCIA_INDEX. */
    private static final int MONEDERO_SOLICITANTE_PROVINCIA_INDEX = 95;

    /** The Constant MONEDERO_SOLICITANTE_TEL_INDEX. */
    private static final int MONEDERO_SOLICITANTE_TEL_INDEX = 149;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatosSolicitanteDAOImpl.class);

    /** The Constant COD_ERROR_CERO. */
    private static final int COD_ERROR_CERO = 00000000;

    /** The Constant COD_ERROR_SUCURSAL_FUERA_HORARIO. */
    private static final int COD_ERROR_SUCURSAL_FUERA_HORARIO = 10000077;

    /** The Constant MONEDERO_SOLICITANTE_ESTADO_TARJETA_INDEX. */
    private static final int MONEDERO_SOLICITANTE_ESTADO_TARJETA_INDEX = 11;

    /** The Constant MONEDERO_SOLICITANTE_NOMBRE_EMBOZADO_INDEX. */
    private static final int MONEDERO_SOLICITANTE_NOMBRE_EMBOZADO_INDEX = 10;

    /** The Constant MONEDERO_SOLICITANTE_NUMERO_TARJETA_INDEX. */
    private static final int MONEDERO_SOLICITANTE_NUMERO_TARJETA_INDEX = 9;

    /** The Constant MONEDERO_SOLICITANTE_TIPO_TARJETA_INDEX. */
    private static final int MONEDERO_SOLICITANTE_TIPO_TARJETA_INDEX = 8;
    
    /** The Constant SOLICITANTE_CODIGO_SUJETO_INDEX. */
    private static final int SOLICITANTE_CODIGO_SUJETO_INDEX = 31;

    /** The Constant SOLICITANTE_CODIGO_PAIS_INDEX. */
    private static final int SOLICITANTE_CODIGO_PAIS_INDEX = 96;
    
    /** The servicio pag tjc. */
    @Value("${SERVICIO.PREFIJO.CNSPERSFIS}")
    private String servicioCnsPersFis;

    /** The version pag tjc. */
    @Value("${SERVICIO.VERSION.CNSPERSFIS}")
    private String versionCnsPersFis;

    /** The servicio CNSCTAMONE. */
    @Value("${SERVICIO.PREFIJO.CNSCTAMONE}")
    private String servicioCNSCTAMONE;

    /** The version CNSCTAMONE. */
    @Value("${SERVICIO.VERSION.CNSCTAMONE}")
    private String versionCNSCTAMONE;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The Constant MONEDERO_SOLICITANTE_NUP. */
    private static final String MONEDERO_SOLICITANTE_NUP = "        ";

    /** The Constant MONEDERO_SECUENCIA. */
    private static final String MONEDERO_SECUENCIA = "01";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.dao.DatosSolicitanteDAO#
     * getDatosDelSolicitante(ar.com.santanderrio.obp.servicios.monedero.web.
     * view.DatosSolicitanteCriterioView,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public DatosSolicitanteEntity getDatosDelSolicitante(DatosSolicitanteCriterioEntity datosSolicitante,
            Cliente cliente) throws DAOException, OperacionFueraHorarioSucursalException {
        LOGGER.info("Invocando Servicio Iatx ");
        IatxResponse iatxResponse = obtenerResponseDatosDelSolicitante(datosSolicitante, cliente);
        return crearResponseDTO(iatxResponse);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.dao.DatosSolicitanteDAO#
     * getDatosTarjetaMonedero(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente, java.lang.String)
     */
    @Override
    public List<MonederoDTO> getDatosTarjetaMonedero(Cliente cliente, String tipoDeConsulta)
            throws OperacionFueraHorarioSucursalException, DAOException {
        LOGGER.info("Invocando Servicio Iatx CNSCTAMONE");
        IatxRequestData requestData = new IatxRequestData(cliente);
        requestData.addBodyValue(tipoDeConsulta);
        IatxResponse iatxResponse = obtenerResponseIatx(servicioCNSCTAMONE, versionCNSCTAMONE, requestData);
        return crearResponseMonederoDTO(iatxResponse);
    }

    /**
     * Obtener tags.
     *
     * @param idBanco
     *            the id banco
     * @param password
     *            the password
     * @param tipoNumDoc
     *            the tipo num doc
     * @param idCuentVirtual
     *            the id cuent virtual
     * @param categoria
     *            the categoria
     * @param idUsuario
     *            the id usuario
     * @param idTag
     *            the id tag
     * @param pagCantReg
     *            the pag cant reg
     * @param pagNum
     *            the pag num
     * @return the list
     * @Override public DatosSolicitanteEntity getDatosDelSolicitante(Cliente
     *           cliente) { LOGGER.info("{Apellido: 'Perez', Nombre: 'Juan',
     *           FechaNacimiento: '23/07/1983', DocTipo: 'DNI', Doc: '35299369',
     *           PaisNacimiento: 'Argentina', Sexo: 'M', EstadoCivil: 'Soltero',
     *           Nacionalidad: 'Argentina', Calle: 'Av. Triunvirato', CalleNro:
     *           '1431', Piso: '1', Depto: 'B', Localidad: 'Capital federal', Cp:
     *           'C1431BFJ', Provincia: 'Buenos Aires', Telefono:
     *           '4312-4423'},'cuentas':{[{Id:'0',Descripcion:'Débito
     *           XXXX-1324'},{Id:'1',Descripcion:'VISA
     *           XXXX-2244'},{Id:'2',Descripcion:'AMEX
     *           XXXX-1974'}]},'importes':{[{Id:'0',Descripcion:'100'},{Id:'1',Descripcion:'500'},{Id:'2',Descripcion:'1000'},{Id:'0',Descripcion:'1500'}]},'limites':{[{Id:'0',Descripcion:'50'},{Id:'1',Descripcion:'100'}]}");
     *           //METODO MOMENTANEO DatosSolicitanteEntity datosSolicitanteEntity =
     *           new DatosSolicitanteEntity();
     *           datosSolicitanteEntity.setApellido("Perez");
     *           datosSolicitanteEntity.setNombre("Juan");
     *           datosSolicitanteEntity.setFechaNacimiento("23/07/1983");
     *           datosSolicitanteEntity.setDocTipo("DNI");
     *           datosSolicitanteEntity.setDoc("35299369");
     *           datosSolicitanteEntity.setPaisNacimiento("Argentina");
     *           datosSolicitanteEntity.setSexo("M");
     *           datosSolicitanteEntity.setEstadoCivil("Soltero");
     *           datosSolicitanteEntity.setNacionalidad("Argentina");
     *           datosSolicitanteEntity.setCalle("Av. Triunvirato");
     *           datosSolicitanteEntity.setCalleNro("1431");
     *           datosSolicitanteEntity.setPiso("1");
     *           datosSolicitanteEntity.setDepto("B");
     *           datosSolicitanteEntity.setLocalidad("Capital federal");
     *           datosSolicitanteEntity.setCp("C1431BFJ");
     *           datosSolicitanteEntity.setProvincia("Buenos Aires");
     *           datosSolicitanteEntity.setTelefono("4312-4423"); return
     *           datosSolicitanteEntity; }
     */

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.dao.DatosSolicitanteDAO#
     * obtenerTags(java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public List<TagEntity> obtenerTags(String idBanco, String password, String tipoNumDoc, String idCuentVirtual,
            String categoria, String idUsuario, String idTag, String pagCantReg, String pagNum) {
        // METODO MOMENTANEAO
        List<TagEntity> obtenerTagsList = new ArrayList<TagEntity>();

        TagEntity obtenerTags = new TagEntity();
        obtenerTags.setApellido("apellido");
        obtenerTags.setBcoLimiteRecargaMensualTAG("bcoLimiteRecargaMensualTAG");
        obtenerTags.setCategoria("categoria");
        obtenerTags.setClteCantFrecuencia("clteCantFrecuencia");
        obtenerTags.setClteCodTipoFrecuencia("clteCodTipoFrecuencia");
        obtenerTags.setClteLimiteMensualRecarga("clteLimiteMensualRecarga");
        obtenerTags.setClteModuloRecarga("clteModuloRecarga");
        obtenerTags.setEstado("estado");
        obtenerTags.setFechaSaldo("fechaSaldo");
        obtenerTags.setIdBanco("idBanco");
        obtenerTags.setIdTag("idTag");
        obtenerTags.setLimiteDisponible("limiteDisponible");
        ObtenerTrxTagMedioDeRecargaEntity medioDeRegarga = new ObtenerTrxTagMedioDeRecargaEntity();
        medioDeRegarga.setIdCuentaVitual("idCuentaVitual");
        medioDeRegarga.setIdMarcaTarjeta("idMarcaTarjeta");
        medioDeRegarga.setIdTarjeta("idTarjeta");
        medioDeRegarga.setTipo("tipo");
        medioDeRegarga.setUlt4DigitosTarjetas("ult4DigitosTarjetas");
        obtenerTags.setMedioDeRecarga(medioDeRegarga);
        obtenerTags.setNombre("nombre");
        obtenerTags.setSaldo("saldo");
        obtenerTags.setTag("tag");

        obtenerTagsList.add(obtenerTags);

        TagEntity obtenerTags2 = new TagEntity();
        obtenerTags2.setApellido("apellido2");
        obtenerTags2.setBcoLimiteRecargaMensualTAG("bcoLimiteRecargaMensualTAG2");
        obtenerTags2.setCategoria("categoria2");
        obtenerTags2.setClteCantFrecuencia("clteCantFrecuencia2");
        obtenerTags2.setClteCodTipoFrecuencia("clteCodTipoFrecuencia2");
        obtenerTags2.setClteLimiteMensualRecarga("clteLimiteMensualRecarga2");
        obtenerTags2.setClteModuloRecarga("clteModuloRecarga2");
        obtenerTags2.setEstado("estado2");
        obtenerTags2.setFechaSaldo("fechaSaldo2");
        obtenerTags2.setIdBanco("idBanco2");
        obtenerTags2.setIdTag("idTag2");
        obtenerTags2.setLimiteDisponible("limiteDisponible2");
        ObtenerTrxTagMedioDeRecargaEntity medioDeRegarga2 = new ObtenerTrxTagMedioDeRecargaEntity();
        medioDeRegarga2.setIdCuentaVitual("idCuentaVitual2");
        medioDeRegarga2.setIdMarcaTarjeta("idMarcaTarjeta2");
        medioDeRegarga2.setIdTarjeta("idTarjeta2");
        medioDeRegarga2.setTipo("tipo2");
        medioDeRegarga2.setUlt4DigitosTarjetas("ult4DigitosTarjetas2");
        obtenerTags2.setMedioDeRecarga(medioDeRegarga);
        obtenerTags2.setNombre("nombre2");
        obtenerTags2.setSaldo("saldo2");
        obtenerTags2.setTag("tag2");

        obtenerTagsList.add(obtenerTags2);

        return obtenerTagsList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.dao.DatosSolicitanteDAO#
     * obtenerTransaccionesTags(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<TransaccionEntity> obtenerTransaccionesTags(String idBanco, String tipoNumDoc, String idTag,
            String fecDesde, String fecHasta, String pagCantReg, String pagNum) {
        // METODO MOMENTANEAO
        List<TransaccionEntity> obtenerTransaccionesTagsList = new ArrayList<TransaccionEntity>();

        TransaccionEntity obtenerTransaccionesTags = new TransaccionEntity();
        obtenerTransaccionesTags.setEstado("estado");
        obtenerTransaccionesTags.setFecha("fecha");
        obtenerTransaccionesTags.setIdTrx("idTrx");
        obtenerTransaccionesTags.setImporte("importe");
        obtenerTransaccionesTags.setLugar("lugar");
        obtenerTransaccionesTags.setOrden("orden");
        obtenerTransaccionesTags.setTipo("tipo");
        obtenerTransaccionesTagsList.add(obtenerTransaccionesTags);

        TransaccionEntity obtenerTransaccionesTags2 = new TransaccionEntity();
        obtenerTransaccionesTags2.setEstado("estado2");
        obtenerTransaccionesTags2.setFecha("fecha2");
        obtenerTransaccionesTags2.setIdTrx("idTrx2");
        obtenerTransaccionesTags2.setImporte("importe2");
        obtenerTransaccionesTags2.setLugar("lugar2");
        obtenerTransaccionesTags2.setOrden("orden2");
        obtenerTransaccionesTags2.setTipo("tipo2");
        obtenerTransaccionesTagsList.add(obtenerTransaccionesTags2);

        return obtenerTransaccionesTagsList;
    }

    /**
     * Crear response DTO.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the datos solicitante entity
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     * @throws DAOException
     *             the DAO exception
     */
    private DatosSolicitanteEntity crearResponseDTO(IatxResponse iatxResponse)
            throws OperacionFueraHorarioSucursalException, DAOException {
        if (esRespuestaOK(iatxResponse)) {
            return crearRespuestaDTO(iatxResponse);
        } else if (esCodigosDeErrorFueraHorario(iatxResponse)) {
            throw new OperacionFueraHorarioSucursalException();
        }
        throw new DAOException();
    }

    /**
     * Crear response monedero DTO.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the list
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    private List<MonederoDTO> crearResponseMonederoDTO(IatxResponse iatxResponse)
            throws OperacionFueraHorarioSucursalException {
        if (esRespuestaOK(iatxResponse)) {
            return crearRespuestaMonederoDTO(iatxResponse);
        } else {
            verTiposDeErrorDesdeIatxResponse(iatxResponse);
        }
        return new ArrayList<MonederoDTO>();
    }

    /**
     * Crear respuesta monedero DTO.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the list
     */
    private List<MonederoDTO> crearRespuestaMonederoDTO(IatxResponse iatxResponse) {

        List<MonederoDTO> monederoDTOList = new ArrayList<MonederoDTO>();
        String cantidadDeTarjetasMonedero = iatxResponse.getData(1);
        int cantidad = 0;
        int indice = 0;

        while (cantidad < Integer.parseInt(cantidadDeTarjetasMonedero)) {
            MonederoDTO monederoDTO = new MonederoDTO();
            monederoDTO.setEstadoTarjetaTag(iatxResponse.getData(indice + MONEDERO_SOLICITANTE_ESTADO_TARJETA_INDEX));
            monederoDTO.setNombreEmbozado(iatxResponse.getData(indice + MONEDERO_SOLICITANTE_NOMBRE_EMBOZADO_INDEX));
            monederoDTO.setNumeroTarjetaTag(iatxResponse.getData(indice + MONEDERO_SOLICITANTE_NUMERO_TARJETA_INDEX));
            monederoDTO.setTipoTarjeta(iatxResponse.getData(indice + MONEDERO_SOLICITANTE_TIPO_TARJETA_INDEX));
            monederoDTOList.add(monederoDTO);
            cantidad++;
            indice = indice + 10;
        }

        return monederoDTOList;
    }

    /**
     * Obtener response datos del solicitante.
     *
     * @param datosSolicitante
     *            the datos solicitante
     * @param cliente
     *            the cliente
     * @return the iatx response
     * @throws DAOException
     *             the DAO exception
     */
    private IatxResponse obtenerResponseDatosDelSolicitante(DatosSolicitanteCriterioEntity datosSolicitante,
            Cliente cliente) throws DAOException {
        IatxRequestData data = crearRequestData(datosSolicitante, cliente);
        return obtenerResponseIatx(servicioCnsPersFis, versionCnsPersFis, data);
    }

    /**
     * Obtener response iatx.
     *
     * @param nombreServicio
     *            the nombre servicio
     * @param version
     *            the version
     * @param data
     *            the data
     * @return the iatx response
     * @throws DAOException
     *             the DAO exception
     */
    public IatxResponse obtenerResponseIatx(String nombreServicio, String version, IatxRequestData data)
            throws DAOException {
        IatxResponse iatxResponse = crearIatxResponse();
        try {
            IatxRequest request = buildIatxRequest(nombreServicio, version, data);
            iatxResponse = ejecutar(request);
            return iatxResponse;
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e, iatxResponse.getErrorMessage());
        }
    }

    /**
     * Builds the iatx request.
     *
     * @param nombreServicio
     *            the nombre servicio
     * @param version
     *            the version
     * @param data
     *            the data
     * @return the iatx request
     */
    public IatxRequest buildIatxRequest(String nombreServicio, String version, IatxRequestData data) {
        IatxRequest iatxRequest = new IatxRequest(nombreServicio, version);
        iatxRequest.setData(data);
        return iatxRequest;
    }

    /**
     * Ejecutar.
     *
     * @param request
     *            the request
     * @return the iatx response
     * @throws IatxException
     *             the iatx exception
     */
    public IatxResponse ejecutar(IatxRequest request) throws IatxException {
        return iatxComm.exec(request);
    }

    /**
     * Crear iatx response.
     *
     * @return the iatx response
     */
    public IatxResponse crearIatxResponse() {
        return new IatxResponse();
    }

    /**
     * Es respuesta OK.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the boolean
     */
    public Boolean esRespuestaOK(IatxResponse iatxResponse) {
        return esCodigoRetornoOK(iatxResponse) && esRespuestaEstadoOK(iatxResponse);
    }

    /**
     * Es codigo retorno OK.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the boolean
     */
    public Boolean esCodigoRetornoOK(IatxResponse iatxResponse) {
        return iatxResponse.getErrorCode() == COD_ERROR_CERO;
    }

    /**
     * Es respuesta estado OK.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the boolean
     */
    public Boolean esRespuestaEstadoOK(IatxResponse iatxResponse) {
        return iatxResponse.getEstadoRespuesta().equals(EstadoRespuesta.OK);
    }

    /**
     * Ver tipos de error desde iatx response.
     *
     * @param iatxResponse
     *            the iatx response
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    public void verTiposDeErrorDesdeIatxResponse(IatxResponse iatxResponse)
            throws OperacionFueraHorarioSucursalException {
        calcularErrorFueraHorario(iatxResponse);
    }

    /**
     * Calcular error fuera horario.
     *
     * @param iatxResponse
     *            the iatx response
     * @throws OperacionFueraHorarioSucursalException
     *             the operacion fuera horario sucursal exception
     */
    private void calcularErrorFueraHorario(IatxResponse iatxResponse) throws OperacionFueraHorarioSucursalException {
        if (esCodigosDeErrorFueraHorario(iatxResponse)) {
            throw new OperacionFueraHorarioSucursalException();
        }
    }

    /**
     * Es codigos de error fuera horario.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the boolean
     */
    private Boolean esCodigosDeErrorFueraHorario(IatxResponse iatxResponse) {
        return iatxResponse.getErrorCode() == COD_ERROR_SUCURSAL_FUERA_HORARIO;
    }

    /**
     * Crear respuesta DTO.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the datos solicitante entity
     */
    private DatosSolicitanteEntity crearRespuestaDTO(IatxResponse iatxResponse) {
        DatosSolicitanteEntity datosSolicitanteEntity = new DatosSolicitanteEntity();
        datosSolicitanteEntity.setNup(iatxResponse.getData(MONEDERO_SOLICITANTE_NUP_INDEX));
        datosSolicitanteEntity.setApellido(iatxResponse.getData(MONEDERO_SOLICITANTE_APELLIDO_INDEX));
        datosSolicitanteEntity.setNombre(iatxResponse.getData(MONEDERO_SOLICITANTE_NOMBRE_INDEX));
        datosSolicitanteEntity.setFechaNacimiento(iatxResponse.getData(MONEDERO_SOLICITANTE_FECHA_NACIMIENTO_INDEX));
        datosSolicitanteEntity.setDocTipo(iatxResponse.getData(MONEDERO_SOLICITANTE_DOC_TIPO_INDEX));
        datosSolicitanteEntity.setDoc(iatxResponse.getData(MONEDERO_SOLICITANTE_DOC_INDEX));
        datosSolicitanteEntity.setPaisNacimiento(iatxResponse.getData(MONEDERO_SOLICITANTE_PAIS_NACIMIENTO_INDEX));
        datosSolicitanteEntity.setSexo(iatxResponse.getData(MONEDERO_SOLICITANTE_SEXO_INDEX));
        datosSolicitanteEntity.setEstadoCivil(iatxResponse.getData(MONEDERO_SOLICITANTE_ESTADO_CIVIL_INDEX));
        datosSolicitanteEntity.setNacionalidad(iatxResponse.getData(MONEDERO_SOLICITANTE_NACIONALIDAD_INDEX));
        datosSolicitanteEntity.setCalle(iatxResponse.getData(MONEDERO_SOLICITANTE_CALLE_INDEX));
        datosSolicitanteEntity.setCalleNro(iatxResponse.getData(MONEDERO_SOLICITANTE_CALLE_NRO_INDEX));
        datosSolicitanteEntity.setPiso(iatxResponse.getData(MONEDERO_SOLICITANTE_PISO_INDEX));
        datosSolicitanteEntity.setDepto(iatxResponse.getData(MONEDERO_SOLICITANTE_DEPTO_INDEX));
        datosSolicitanteEntity.setLocalidad(iatxResponse.getData(MONEDERO_SOLICITANTE_LOCALIDAD_INDEX));
        datosSolicitanteEntity.setCp(iatxResponse.getData(MONEDERO_SOLICITANTE_CP_INDEX));
        datosSolicitanteEntity.setProvincia(iatxResponse.getData(MONEDERO_SOLICITANTE_PROVINCIA_INDEX));
        datosSolicitanteEntity.setTelefono(iatxResponse.getData(MONEDERO_SOLICITANTE_TEL_INDEX));
        datosSolicitanteEntity.setCodigoSujeto(iatxResponse.getData(SOLICITANTE_CODIGO_SUJETO_INDEX));
        datosSolicitanteEntity.setCodigoPais(iatxResponse.getData(SOLICITANTE_CODIGO_PAIS_INDEX));
        return datosSolicitanteEntity;
    }

    /**
     * Crear request data.
     *
     * @param datosSolicitante
     *            the datos solicitante
     * @param cliente
     *            the cliente
     * @return the iatx request data
     */
    private IatxRequestData crearRequestData(DatosSolicitanteCriterioEntity datosSolicitante, Cliente cliente) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        requestData.addBodyValue(datosSolicitante.isAdicional() ? MONEDERO_SOLICITANTE_NUP : cliente.getNup());
        requestData.addBodyValue(org.apache.commons.lang3.StringUtils.rightPad(datosSolicitante.getDocTipo(), 2));
        requestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(datosSolicitante.getDoc(), 11));
        requestData.addBodyValue(MONEDERO_SECUENCIA);
        return requestData;
    }

    /**
	 * Se crea metodo en diferentes ramas para ser utilizado en funcionalidad
	 * pago de tarjeta y marcacion por viaje Se setea el sexo del cliente.
	 *
	 * @param cliente
	 *            the cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
    @Override
    public void obtenerSexoCliente(Cliente cliente) throws DAOException {

        if (cliente.getSexo() == null) {

            DatosSolicitanteCriterioEntity datosSolicitante = new DatosSolicitanteCriterioEntity();
            datosSolicitante.setDocTipo(cliente.getTipoDocumento());
            datosSolicitante.setDoc(cliente.getDni());
            DatosSolicitanteEntity datosSolicitanteEntity = null;
            try {
                IatxResponse iatxResponse = obtenerResponseDatosDelSolicitante(datosSolicitante, cliente);
                datosSolicitanteEntity = crearResponseDTO(iatxResponse);
            } catch (OperacionFueraHorarioSucursalException e) {
                LOGGER.info("Error fuera de horario sucursal en Servicio servicioCnsPersFis.", e);
                throw new DAOException();
            }
            if (datosSolicitanteEntity != null) {
                cliente.setSexo(datosSolicitanteEntity.getSexo());
            } else {
                LOGGER.info("Error Sin Datos de salida con datos de entrada: {}.", datosSolicitante);
                throw new DAOException();
            }
        }
    }
}
