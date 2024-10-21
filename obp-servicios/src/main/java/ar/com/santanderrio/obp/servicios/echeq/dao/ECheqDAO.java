package ar.com.santanderrio.obp.servicios.echeq.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.IOperacionECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;

public interface ECheqDAO {

    OperationEcheqResponse ejecutar(Cliente cliente, IOperacionECheqInEntity<?> inEntity) throws DAOException;

	Reporte descargarComprobante(ComprobanteECheqDTO datos);

}
