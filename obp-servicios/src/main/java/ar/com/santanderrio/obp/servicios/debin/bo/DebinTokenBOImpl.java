package ar.com.santanderrio.obp.servicios.debin.bo;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.http.web.util.NetworkUtil;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinTokenEntity;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSSolicitudesBO;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;

/**
 * The Class DebinTokenBOImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("debinTokenBO")
public class DebinTokenBOImpl extends AbstractTokenEntesExternos {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinTokenBOImpl.class);

    /** The Constant JKS. */
    private static final String JKS_DEBIN = "DEBIN";

    /** The nup DAO. */
    @Autowired
    private NUPDAO nupDAO;

    /** The solicitudesDebinWSBO. */
    @Autowired
    @Qualifier("debinwsSolicitudesBO")
    private DebinWSSolicitudesBO solicitudesDebinWSBO;

    /** The url. */
    @Value("${DEBIN.URL}")
    private String debinUrl;

    /** The Constant TRUE_TOKEN_DEBIN. */
    private static final String TRUE_TOKEN_DEBIN = "true";

    /** The Constant FALSE_TOKEN_DEBIN. */
    private static final String FALSE_TOKEN_DEBIN = "false";

    /** The categoria limite default. */
    @Value("${DEBIN.CATEGORIA_LIMITE_DEFAULT}")
    private int categoriaLimiteDefault;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#obtenerDatosAFirmar(org.apache.cxf.jaxrs.ext.
     * MessageContext,
     * ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO)
     */
    @Override
    protected String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
        Cliente cliente = tokenExternoDTO.getCliente();
        DebinTokenEntity token = new DebinTokenEntity();
        token.setTipoDocumento(getTipoDocDebin(cliente.getTipoDocumento()));
        token.setNroDocumento(StringUtils.right(cliente.getDni(), 8));
        token.setCuit(obtenerCuit(cliente));
        token.setNroTarjeta(obtenerNroTarjeta(cliente));
        token.setCanal("I");
        token.setIp(NetworkUtil.getRemoteIp(mc.getHttpServletRequest()));
        token.setTerminal(NetworkUtil.getUserAgent(mc.getHttpServletRequest()));
        token.setDobleFactorVerificado(TRUE_TOKEN_DEBIN);
        token.setCategoriaLimite(String.valueOf(categoriaLimiteDefault));
        token.setPermitePreautorizado(FALSE_TOKEN_DEBIN);
        token.setMostrarSoloTransferencias(TRUE_TOKEN_DEBIN);
        return getTokenAsString(token);
    }


    /**
	 * Gets the tipo doc debin.
	 *
	 * @param tipoDocumento
	 *            the tipo documento
	 * @return the tipo doc debin
	 */
    private String getTipoDocDebin(String tipoDocumento) {
        String docDebin = StringUtils.EMPTY;
        switch (tipoDocumento.charAt(0)) {
        case 'N':
            docDebin = "1";
            break;
        case 'X':
            docDebin = "1";
            break;
        case 'I':
            docDebin = "2";
            break;
        case 'P':
            docDebin = "3";
            break;
        case 'C':
            docDebin = "4";
            break;
        case 'E':
            docDebin = "5";
            break;
        default:
            throw new RobotException("Tipo de documento invalido");
        }
        return docDebin;
    }

    /**
	 * Obtener el numero de la tarjeta de debito.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the string
	 */
    private String obtenerNroTarjeta(Cliente cliente) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.esBanelco() && StringUtils.isNotEmpty(cuenta.getNroTarjetaCredito())) {
                return ISBANStringUtils.sacarCerosYBlancosIzq(cuenta.getNroTarjetaCredito());
            }
        }
        return StringUtils.EMPTY;
    }

    /**
	 * Obtener cuit.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the string
	 */
    private String obtenerCuit(Cliente cliente) {
        try {
            NupDTO nupDTO = nupDAO.obtenerNUP(cliente);
            return nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
        } catch (DAOException e) {
            LOGGER.error("No se puede obtener el cuit para el cliente {}.", cliente.getNup(), e);
        }
        return StringUtils.EMPTY;
    }

    /**
	 * Obtener el json para el token a enviar a debin.<br/>
	 * Ej:<br/>
	 * 
	 * <pre>
	 * <code>
	 * { 
	 * "tipoDocumento": "1", 
	 * "nroDocumento": "20202020", 
	 * "cuit": "20202020202", 
	 * "nroTarjeta": â€â€, 
	 * "canal": "I", 
	 * "ip": "10.11.12.13", 
	 * "terminal": "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36", 
	 * "dobleFactorVerificado": "true" 
	 * }
	 * </code>
	 * </pre>
	 *
	 * @param token
	 *            the token
	 * @return the token as string
	 */
    private String getTokenAsString(DebinTokenEntity token) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonDebin = mapper.writeValueAsString(token);
            LOGGER.info("Json {} firmar para enviar a debin como token.", jsonDebin);
            return jsonDebin;
        } catch (IOException e) {
            LOGGER.error("No se puede generar el json correspondiente al token debin.", e);
        }
        return StringUtils.EMPTY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#getKeystore()
     */
    @Override
    protected String getKeystore() {
        return JKS_DEBIN;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#getUrl()
     */
    @Override
    protected String getUrl() {
        return debinUrl;
    }

}
