/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.externos;

import java.io.UnsupportedEncodingException;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

/**
 * Clase abstracta que tiene el manejo del armado del token a los diferentes
 * entes externos.
 */
public abstract class AbstractTokenEntesExternos {

    /** Separador pipe. */
    protected static final String PIPE = "|";

    /** The sign. */
    @Autowired
    private Sign sign;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenEntesExternos.class);

    /**
	 * Obtiene los datos A firmar.
	 *
	 * @param mc
	 *            the mc
	 * @param tokenExternoDTO
	 *            the token externo DTO
	 * @return the string
	 */
    protected abstract String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO);

    /**
	 * Armar token.
	 *
	 * @param mc
	 *            the mc
	 * @param tokenExternoDTO
	 *            the token externo DTO
	 * @return the string
	 */
    public Respuesta<TokenDTO> armarToken(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
        String dataToSign = obtenerDatosAFirmar(mc, tokenExternoDTO);
        LOGGER.info("Datos a firmar {}", dataToSign);
        if (firmadoConCert()) {
            return firmarTokenPem(dataToSign);
        } else {
            return firmarTokenB64SinCert(dataToSign);
        }
    }

    /**
     * Habilitar Comportamiento default para la integracion de todos los canales
     * salvo TBANCO-OBP.<br/>
     * Sobrecargar si se requiere otro comportamiento.
     *
     * @return true, if successful
     */
    protected boolean firmadoConCert() {
        return true;
    }

    /**
     * Comportamiento default para la integracion de todos los canales salvo
     * TBANCO-OBP.
     *
     * @param dataToSign
     *            the data to sign
     * @return the respuesta
     */
    private Respuesta<TokenDTO> firmarTokenPem(String dataToSign) {
        try {
            return armarRespuestaOk(sign.buildPemSignature(dataToSign.getBytes(), getKeystore(), true));
        } catch (DAOException e) {
            LOGGER.error("Error al encriptar el token", e);
            return respuestaFactory.crearRespuestaError(TokenDTO.class, "", "");
        }
    }

    /**
     * Para integrar TBANCO - OBP.
     *
     * @param dataToSign
     *            the data to sign
     * @return the respuesta
     */
    private Respuesta<TokenDTO> firmarTokenB64SinCert(String dataToSign) {
        String tokenFirmado = null;
        byte[] tokenFirmadoByte = null;
        try {
            tokenFirmadoByte = sign.buildB64Signature(dataToSign.getBytes(), getKeystore());
            tokenFirmado = new String(tokenFirmadoByte, "UTF-8");
            return armarRespuestaOk(tokenFirmado);
        } catch (DAOException e) {
            LOGGER.error("Error al encriptar el token", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error al encriptar el token a UTF-8", e);
        }
        return respuestaFactory.crearRespuestaError(TokenDTO.class, "", "");
    }

    /**
     * Arma la respuesta OK que incluye la url y el token encriptado.
     *
     * @param tokenFirmado
     *            the token firmado
     * @return the respuesta
     */
    protected Respuesta<TokenDTO> armarRespuestaOk(String tokenFirmado) {
        TokenDTO tokenDTO = new TokenDTO();

        tokenDTO.setTokenFirmado(tokenFirmado);
        tokenDTO.setUrl(this.getUrl());

        return respuestaFactory.crearRespuestaOk(TokenDTO.class, tokenDTO);
    }

    /**
     * Gets the keystore.
     *
     * @return the keystore
     */
    protected abstract String getKeystore();

    /**
     * Gets the url.
     *
     * @return the url
     */
    protected abstract String getUrl();

}
