/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternos;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;

/**
 * The Class ObpToken.
 *
 * @author sergio.e.goldentair
 */
@Component("obpToken")
public class ObpToken extends AbstractTokenEntesExternos {
    /** The Constant TOKEN_TIMESTAMP_FMT. */
    private static final String TOKEN_TIMESTAMP_FMT = "yyyyMMddHHmmss";

    /** The Constant JKS. */
    private static final String JKS_OBP = "OBP";

    /** The url. */
    @Value("${OBP.LOGIN.SWITCH.URL}")
    private String urlObp;

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
        return new StringBuilder(cliente.getUsuarioRacf()).append(AbstractTokenEntesExternos.PIPE)
                .append(cliente.getPasswordRacf()).append(AbstractTokenEntesExternos.PIPE).append(cliente.getNup())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getMarcaANPH())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getFechaNacimiento())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getTipoClave())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getTipoPersona())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getDni())
                .append(AbstractTokenEntesExternos.PIPE).append(cliente.getValorSinonimo())
                .append(AbstractTokenEntesExternos.PIPE).append(fecha).toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#getKeystore()
     */
    @Override
    protected String getKeystore() {
        return JKS_OBP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#getUrl()
     */
    @Override
    protected String getUrl() {
        return urlObp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.token.externos.
     * AbstractTokenEntesExternos#firmadoConCert()
     */
    @Override
    protected boolean firmadoConCert() {
        return false;
    }

}
