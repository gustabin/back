/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.obp.bo.ObpToken;
import ar.com.santanderrio.obp.servicios.token.externos.AbstractTokenEntesExternosManager;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Class ObpManagerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("obpManager")
public class ObpManagerImpl extends AbstractTokenEntesExternosManager implements ObpManager {

    /** The obe token. */
    @Autowired
    private ObpToken obpToken;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.obp.manager.ObpManager#
     * obtenerTokenEncriptado(ar.com.santanderrio.obp.base.clientes.entities.
     * ResumenCliente)
     */
    @Override
    public Respuesta<TokenView> obtenerTokenEncriptado(ResumenCliente resumenCliente) {
        Cliente cliente = new Cliente(resumenCliente);
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
        Respuesta<TokenView> respuestaToken = this.armarRespuesta(null, obpToken, tokenExternoDTO);

        if (EstadoRespuesta.OK.equals(respuestaToken.getEstadoRespuesta())) {
            respuestaToken.setEstadoRespuesta(EstadoRespuesta.WARNING);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            item.setMensaje("Obp");
            item.setTag("Obp");
            item.setTipoError(TipoError.WARNING_TBANCO_OBP.toString());
            respuestaToken.add(item);
        }
        return respuestaToken;
    }

}
