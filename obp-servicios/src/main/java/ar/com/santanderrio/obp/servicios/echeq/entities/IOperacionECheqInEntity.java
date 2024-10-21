package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

public interface IOperacionECheqInEntity<T> {

    /**
     * @return nombre servicio
     */
    String getNombreServicio();

    /**
     * @return version servicio
     */
    String getVersionServicio();

    /**
     * @param cliente
     * @param operacionesECheqInEntity
     * @return IatxRequestData
     */
    T generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity);

}
