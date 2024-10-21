/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock;

import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * @author florencia.n.martinez
 *
 */
public class ClienteDebitoTarjetaInEntityMock {

    public static ClienteDebitoTarjetaInEntity completarInfoOK() {
        ClienteDebitoTarjetaInEntity inEntity = new ClienteDebitoTarjetaInEntity();
        inEntity.setCliente(ClienteMock.infoCliente());
        inEntity.setNroCuentaVisa("0000000004216184");
        inEntity.setNroTarjetaVisa("4509808001970650");
        inEntity.setRubro("");
        inEntity.setEmpresaServicio("CLARO");
        inEntity.setIdentificador("123456789123456");
        inEntity.setCodAreaTelefono("0");
        inEntity.setNroTelefono("0");
        inEntity.setMontoMaximo("0");
        inEntity.setEmail("obp@gmail.com");
        return inEntity;
    }
}
