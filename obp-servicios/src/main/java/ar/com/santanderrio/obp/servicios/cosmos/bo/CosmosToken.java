package ar.com.santanderrio.obp.servicios.cosmos.bo;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.cosmos.entitiy.DatosCosmosIn;
import ar.com.santanderrio.obp.servicios.cosmos.entitiy.DatosCosmosOut;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

/**
 * The Class CosmosToken.
 */
@Component
public class CosmosToken extends AbstractTokenEntesExternos {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenEntesExternos.class);

    /** The Constant CANAL. */
    private static final String CANAL = "HB";

    /** The Constant TIPO_CANAL. */
    private static final String TIPO_CANAL = "04";

    /** The Constant ID_CANAL. */
    private static final String ID_CANAL = "HTML";

    /** The Constant VERSION_CANAL. */
    private static final String VERSION_CANAL = "000";

    /** The Constant TIPO_SUBCANAL. */
    private static final String TIPO_SUBCANAL = "99";

    /** The Constant ID_SUBCANAL. */
    private static final String ID_SUBCANAL = "0001";

    /** The Constant JKS. */
    private static final String JKS = "COSMOS";

    /** The Constant CODIGO_WEB_SERVICE. */
    private static final String CODIGO_WEB_SERVICE = "COSMOS.REGISTRAR";

    /** The Constant FLAG_TBANCO. */
    private static final String FLAG_TBANCO = "S";

    /** The Constant CODIGO_COSMOS_DUO. */
    private static final String CODIGO_COSMOS_DUO = "01";

    /** The Constant CODIGO_COSMOS_SELECT. */
    private static final String CODIGO_COSMOS_SELECT = "02";

    /** The Constant CODIGO_COSMOS_UNIVERSIDAD. */
    private static final String CODIGO_COSMOS_UNIVERSIDAD = "03";

    /** The Constant CODIGO_COSMOS_PYME. */
    private static final String CODIGO_COSMOS_PYME = "04";

    /** The Constant CODIGO_TITULARIDAD. */
    private static final String CODIGO_TITULARIDAD = "TI";

    /** The url. */
    @Value("${COSMOS.URL}")
    private String url;

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.bo.
     * TokenEntesExternosBuilder#getKeystore()
     */
    @Override
    protected String getKeystore() {
        return JKS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#obtenerDatosAFirmar(org.apache.cxf.jaxrs.ext.
     * MessageContext,
     * ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO)
     * 21-06-2019 Se agrega filtro para enviar unicamente las cuentas titulares.
     */
    @Override
    protected String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
        String habilitadoCosmos = "S";
        String firmanteRequerido = "N";
        Cliente cliente = tokenExternoDTO.getCliente();
        List<Cuenta> cuentas = cliente.getCuentasTarjetaParaFecp();
        int cantTarjetas = 0;
        String cantTar = "";
        StringBuilder lstTar = new StringBuilder();
        String operacionCosmos = sesionParametros.getOperacionParaToken() != null ? sesionParametros.getOperacionParaToken(): "";
        sesionParametros.setOperacionParaToken("");

        for (int n = 0; n < cuentas.size(); ++n) {
            Cuenta cuenta = cuentas.get(n);
            String tipo = cuenta.getTipoCuenta();
            String producto = cuenta.getProductoAltair();
            String subProducto = cuenta.getSubproductoAltair();
            String numero = cuenta.getNroTarjetaCredito();
            String codTit = cuenta.getCodigoTitularidad();
            String oficina = cuenta.getOficinaAltair();
            String contrato = cuenta.getContratoAltair();
            if (CODIGO_TITULARIDAD.equals(codTit)) {
            	cantTarjetas = cantTarjetas + 1;
                lstTar.append(AbstractTokenEntesExternos.PIPE).append(tipo).append(AbstractTokenEntesExternos.PIPE)
                .append(producto).append(AbstractTokenEntesExternos.PIPE).append(subProducto)
                .append(AbstractTokenEntesExternos.PIPE).append(numero).append(AbstractTokenEntesExternos.PIPE)
                .append(codTit).append(AbstractTokenEntesExternos.PIPE).append(oficina)
                .append(AbstractTokenEntesExternos.PIPE).append(contrato);
            }
        }

        cantTar = String.valueOf(cantTarjetas);

        Calendar calendar = new GregorianCalendar();
        String anio = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.YEAR)), 4, "0");
        String mes = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.MONTH) + 1), 2, "0");
        String dia = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0");
        String hora = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0");
        String minutos = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.MINUTE)), 2, "0");
        String segundos = StringUtils.rightPad(Integer.toString(calendar.get(Calendar.SECOND)), 2, "0");
        String fechaHora = anio + mes + dia + hora + minutos + segundos;

        StringBuilder token = new StringBuilder();
        token.append(fechaHora).append(AbstractTokenEntesExternos.PIPE);
        token.append(CANAL).append(AbstractTokenEntesExternos.PIPE);
        token.append(TIPO_CANAL).append(AbstractTokenEntesExternos.PIPE);
        token.append(ID_CANAL).append(AbstractTokenEntesExternos.PIPE);
        token.append(VERSION_CANAL).append(AbstractTokenEntesExternos.PIPE);
        token.append(TIPO_SUBCANAL).append(AbstractTokenEntesExternos.PIPE);
        token.append(ID_SUBCANAL).append(AbstractTokenEntesExternos.PIPE);
		token.append(operacionCosmos).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getValorSinonimo()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getTipoPersona()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getTipoDocumento()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getDni()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getFechaNacimiento()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getNup()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getUsuarioRacf()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getPasswordRacf()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getNombre()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getApellido1()).append(AbstractTokenEntesExternos.PIPE);
        token.append(cliente.getApellido2()).append(AbstractTokenEntesExternos.PIPE);
        token.append(AbstractTokenEntesExternos.PIPE).append(AbstractTokenEntesExternos.PIPE)
                .append(AbstractTokenEntesExternos.PIPE).append(AbstractTokenEntesExternos.PIPE)
                .append(AbstractTokenEntesExternos.PIPE);
        token.append(habilitadoCosmos).append(AbstractTokenEntesExternos.PIPE);
        token.append(firmanteRequerido).append(AbstractTokenEntesExternos.PIPE);
        String idSesionContextuales = "";
        if (sesionParametros.getOfertasComerciales() != null
                && StringUtils.isNotEmpty(sesionParametros.getOfertasComerciales().getJsessionId())) {
            idSesionContextuales = sesionParametros.getOfertasComerciales().getJsessionId();
        }
        token.append(idSesionContextuales).append(AbstractTokenEntesExternos.PIPE);
        token.append(FLAG_TBANCO).append(AbstractTokenEntesExternos.PIPE);
        String segmento = getSegmentoReclamos(cliente.getSegmento());
        token.append(segmento).append(AbstractTokenEntesExternos.PIPE);
        token.append(AbstractTokenEntesExternos.PIPE);
        token.append(cantTar).append(lstTar);
        token.append(AbstractTokenEntesExternos.PIPE);

        return token.toString();
    }

    /**
     * Gets the segmento reclamos.
     *
     * @param segmento the segmento
     * @return the segmento reclamos
     */
    private String getSegmentoReclamos(Segmento segmento) {
		// 01: Duo
		// 02: Select
		// 03: Universidad
    	// 04: Pyme
		// Vacío: Si el cliente no tiene segmento
        if (segmento == null) {
            return "";
        } else if (segmento.isDuo()) {
            return CODIGO_COSMOS_DUO;
        } else if (segmento.isPyme()) {
            return CODIGO_COSMOS_PYME;
        } else if (segmento.isSelect()) {
            return CODIGO_COSMOS_SELECT;
        } else if (segmento.isUniversidad()) {
            return CODIGO_COSMOS_UNIVERSIDAD;
        }
        return "";
    }

    /**
     * Obtener hash.
     *
     * @param sesionCliente
     *            the sesion cliente
     * @return the respuesta
     */
    public Respuesta<TokenDTO> obtenerHash(SesionCliente sesionCliente) {
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenDTO> respuestaTokenFirmado = this.armarToken(null, tokenExternoDTO);

        if (EstadoRespuesta.ERROR.equals(respuestaTokenFirmado.getEstadoRespuesta())) {
            return respuestaTokenFirmado;
        }

        @SuppressWarnings("rawtypes")
        Respuesta<DatosCosmosOut> respuestaHash = obtenerHash(respuestaTokenFirmado.getRespuesta());

        if ((respuestaHash.getRespuesta().getTokenHash()!= null) && !"".equals(respuestaHash.getRespuesta().getTokenHash())) {
        	Respuesta<TokenDTO> respuesta = new Respuesta<TokenDTO>();
        	respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setTokenFirmado(respuestaHash.getRespuesta().getTokenHash().toString());
            tokenDTO.setUrl(this.getUrl());
            respuesta.setRespuesta(tokenDTO);
            return respuesta;
        }

        return Respuesta.copy(TokenDTO.class, respuestaHash);
    }

    /**
     * Obtener hash.
     *
     * @param tokenDTO
     *            the token DTO
     * @return the respuesta
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Respuesta<DatosCosmosOut> obtenerHash(TokenDTO tokenDTO) {    	
    	try {
            WebClient cosmosWS = restWebClient.obtenerClienteRest(CODIGO_WEB_SERVICE);
            cosmosWS.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
            DatosCosmosIn datos = new DatosCosmosIn(tokenDTO.getTokenFirmado());
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            om.getSerializationConfig().isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            String result = om.writeValueAsString(datos);            
            DatosCosmosOut cosmosout = cosmosWS.post(result, DatosCosmosOut.class);            
            return respuestaFactory.crearRespuestaOk(cosmosout);
        } catch (DAOException e) {
            LOGGER.error("Error al encriptar el token", e);
            return respuestaFactory.crearRespuestaError(DatosCosmosOut.class, "", "");
        } catch (JsonGenerationException e) {
            LOGGER.error("Error al encriptar el token", e);
            return respuestaFactory.crearRespuestaError(DatosCosmosOut.class, "", "");
        } catch (JsonMappingException e) {
            LOGGER.error("Error al encriptar el token", e);
            return respuestaFactory.crearRespuestaError(DatosCosmosOut.class, "", "");
        } catch (IOException e) {
            LOGGER.error("Error al encriptar el token", e);
            return respuestaFactory.crearRespuestaError(DatosCosmosOut.class, "", "");
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#getUrl()
     */
    @Override
    protected String getUrl() {
        return this.url;
    }

}
