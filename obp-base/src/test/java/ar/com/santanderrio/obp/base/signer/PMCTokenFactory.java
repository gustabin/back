/**
 * 
 */
package ar.com.santanderrio.obp.base.signer;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.factory.TokenFactory;
import ar.com.santanderrio.obp.base.signer.token.Token;

/**
 * Clase de prueba para validar la generacion del token firmado.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component("PMCTokenFactory")
public class PMCTokenFactory implements TokenFactory {

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.base.signer.factory.TokenFactory#crearToken(java.lang
     * .String, ar.com.santanderrio.base.entities.Entity[])
     */
    @Override
    public Token crearToken(String tokenCode, Entity... entidades) {
        PMCStringToken pmc = new PMCStringToken();
        pmc.setJks("PMC");
        pmc.setRequiereCertificado(Boolean.TRUE);
        pmc.setDireccionCliente("cuchacucha 645");
        pmc.setTipoDocumento("29999111");
        pmc.setErroneoConFormato(10L);
        pmc.setPrueba("valor de prueba");
        return pmc;
    }

}
