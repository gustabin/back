package ar.com.santanderrio.obp.servicios.extraccionefectivo.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.DatosComprobanteExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;

public interface ExtraccionEfectivoBO {

	Respuesta<ExtraccionEfectivoView> ejecutarSolicitud(Cuenta cuenta, Cliente cliente, int monto, String email);

	Respuesta<Reporte> generarComprobantePDF(DatosComprobanteExtraccionEfectivoView datosComprobante);

}
