/*
 * 
 */
package ar.com.santanderrio.obp.servicios.obe.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;

/**
 * Clase que se encarga del armado del token para el canal OBE.
 */
@Component("obeToken")
public class ObeToken extends AbstractTokenEntesExternos {

    /** The Constant CANAL. */
    private static final String CANAL = "04";

    /** The Constant SUBCANAL. */
    private static final String SUBCANAL = "99";

    /** The Constant TOKEN_TIMESTAMP_FMT. */
    private static final String TOKEN_TIMESTAMP_FMT = "yyyyMMddHHmmss";

    /** The Constant JKS. */
    public static final String JKS = "OBE";

    /** The url. */
    @Value("${OBE.LOGIN.SWITCH.URL}")
    private String url;

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
        SimpleDateFormat sdf = new SimpleDateFormat(TOKEN_TIMESTAMP_FMT);

        String fecha = sdf.format(new Date());

        StringBuilder tkn = new StringBuilder();
        tkn.append(fecha);
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(CANAL);
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(SUBCANAL);
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getUsuarioRacf());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getPasswordRacf());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getNup());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getDni());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getFechaNacimiento());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getTipoPersona());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getTipoDocumento());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getTipoClave());
        tkn.append(AbstractTokenEntesExternos.PIPE);
        tkn.append(cliente.getMarcaANPH());

        return tkn.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternosBuilder#getKeystore()
     */
    @Override
    protected String getKeystore() {
        return JKS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternosBuilder#getUrl()
     */
    @Override
    protected String getUrl() {
        return this.url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos#
     * firmadoConCert()
     */
    @Override
    protected boolean firmadoConCert() {
        return Boolean.FALSE;
    }

}
