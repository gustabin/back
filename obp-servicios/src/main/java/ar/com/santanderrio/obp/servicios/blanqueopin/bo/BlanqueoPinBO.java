package ar.com.santanderrio.obp.servicios.blanqueopin.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;

public interface BlanqueoPinBO {

	/**
	 * Retorna marca y numero de tarjetas banelco (tipo cuenta = 05, estado tarjeta
	 * = 01)
	 * 
	 * @param cliente
	 * @return
	 */
	public List<DetalleTarjetaDTO> obtenerTarjetasBanelco(Cliente cliente);

	/**
	 * Blanqueo de pin numerico, alfanumerico y ambos
	 * 
	 * @param cliente
	 * @param tipoBlanqueo
	 * @return
	 */
	public Boolean blanquearPin(Cliente cliente, BlanqueoPinView blanqueoPin, String ip) throws BusinessException;

}
