package ar.com.santanderrio.obp.servicios.adhesionwomen.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.DatosTarjetaWomen;

public interface AdhesionWomenBO {

	List<DatosTarjetaWomen> obtenerListadoWomen(List<Cuenta> listaTarjetasCliente) throws BusinessException;
	
	AdhesionWomenView ejecutarAltaBajaWomen(List<DatosTarjetaWomen> tarjetasParaHabilitar, List<DatosTarjetaWomen> tarjetasNoHabilitadas, Cliente cliente, String motivoReimpresion);
}
