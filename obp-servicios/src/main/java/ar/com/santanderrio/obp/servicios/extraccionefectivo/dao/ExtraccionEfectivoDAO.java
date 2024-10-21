package ar.com.santanderrio.obp.servicios.extraccionefectivo.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.CardlessWithdrawalResponse;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;

@Component("extraccionEfectivoDAO")
public interface ExtraccionEfectivoDAO {

	CardlessWithdrawalResponse ejecutarSolicitud(Cuenta cuenta, Cliente cliente, int monto, String email) throws Exception;
	
	Reporte generarComprobantePDF(DatosComprobanteExtraccionEfectivoView datosComprobante); 
}
