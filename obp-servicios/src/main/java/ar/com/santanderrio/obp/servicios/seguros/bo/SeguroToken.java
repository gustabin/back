/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.bo;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.seguros.dao.SeguroTenenciasDAO;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

/**
 * Clase que se encarga del armado del token para seguros.
 */
@Component
public class SeguroToken extends AbstractTokenEntesExternos {

    /** The ramo. */
    @Value("${SEGUROS.RAMO}")
    private String ramo;

    /** The usuario. */
    @Value("${SEGUROS.USUARIO}")
    private String usuario;

    /** The password. */
    @Value("${SEGUROS.PASSWORD}")
    private String password;

    /** The canal. */
    @Value("${SEGUROS.CANAL}")
    private String canal;

    /** The url. */
    @Value("${SEGUROS.URL}")
    private String url;

    /** The Constant JKS. */
    private static final String JKS = "SEGUROS";

    /** The seguro tenencias DAO. */
    @Autowired
    private SeguroTenenciasDAO seguroTenenciasDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

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
     * @see ar.com.santanderrio.obp.servicios.token.externos.bo.
     * TokenEntesExternosBuilder#obtenerDatosAFirmar(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    protected String obtenerDatosAFirmar(MessageContext mc, TokenExternoDTO tokenExternoDTO) {
        Cliente cliente = tokenExternoDTO.getCliente();
        StringBuilder token = new StringBuilder();

        String tipoDoc = cliente.getTipoDocumento();
        String nroDoc = cliente.getDni();
        String fnac = cliente.getFechaNacimiento();
        String nup = cliente.getNup();

        token.append(usuario).append(AbstractTokenEntesExternos.PIPE);

        token.append(canal).append(AbstractTokenEntesExternos.PIPE);
        token.append(tipoDoc).append(AbstractTokenEntesExternos.PIPE);
        token.append(nroDoc).append(AbstractTokenEntesExternos.PIPE);
        token.append(fnac).append(AbstractTokenEntesExternos.PIPE);
        token.append(nup).append(AbstractTokenEntesExternos.PIPE);
        token.append(ramo).append(AbstractTokenEntesExternos.PIPE);

        return token.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#armarToken(org.apache.cxf.jaxrs.ext.
     * MessageContext,
     * ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO)
     */
    @Override
    public Respuesta<TokenDTO> armarToken(MessageContext mc, TokenExternoDTO tokenExternoDT) {

        Respuesta<TokenDTO> tokenConBeginEnd = super.armarToken(mc, tokenExternoDT);

        String tokenFirmado = tokenConBeginEnd.getRespuesta().getTokenFirmado();

        String token = StringUtils.substringAfter(tokenFirmado, "-----BEGIN PKCS7-----\n");
        token = StringUtils.substringBefore(token, "-----END PKCS7-----\n");

        tokenConBeginEnd.getRespuesta().setTokenFirmado(token);

        return tokenConBeginEnd;
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

    /**
	 * Obtiene el token de login del endpoint de seguros.
	 *
	 * @param tokenFirmado
	 *            the token firmado
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    public Respuesta<TokenDTO> obtenerUrlConexion(String tokenFirmado, Cliente cliente) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setTokenFirmado(seguroTenenciasDAO.callLoginSeguros(tokenFirmado));
            tokenDTO.setUrl(
                    MessageFormat.format(this.getUrl(), TipoUsuario.getTipoUsuario(cliente.getSegmento()).getTipo()));
            return respuestaFactory.crearRespuestaOk(TokenDTO.class, tokenDTO);
        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaError(TokenDTO.class, "", "");
        }
    }

    /**
	 * The Enum TipoUsuario.
	 */
    private enum TipoUsuario {
    	
		/** The select. */
		SELECT("sel"),
		
		/** The universidad. */
		UNIVERSIDAD("uni"),
		
		/** The duo. */
		DUO("duo"),
		
		/** The pyme. */
		PYME("pym"),
		
		/** The default. */
		DEFAULT("sr");

        /** The tipo. */
        private String tipo;

        /**
		 * Instantiates a new tipo usuario.
		 *
		 * @param tipo
		 *            the tipo
		 */
        TipoUsuario(String tipo) {
            this.tipo = tipo;
        }

        /**
		 * Gets the tipo.
		 *
		 * @return the tipo
		 */
        public String getTipo() {
            return tipo;
        }

        /**
		 * Gets the tipo usuario.
		 *
		 * @param segmento
		 *            the segmento
		 * @return the tipo usuario
		 */
        public static TipoUsuario getTipoUsuario(Segmento segmento) {
            if (segmento.isDuo()) {
                return TipoUsuario.DUO;
            } else if (segmento.isPyme()) {
                return TipoUsuario.PYME;
            } else if (segmento.isSelect()) {
                return TipoUsuario.SELECT;
            } else if (segmento.isUniversidad()) {
                return TipoUsuario.UNIVERSIDAD;
            }
            return TipoUsuario.DEFAULT;
        }
    }

}
