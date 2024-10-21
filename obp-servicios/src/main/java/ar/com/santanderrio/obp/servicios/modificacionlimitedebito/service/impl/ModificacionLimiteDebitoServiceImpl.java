/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.bo.ModificacionLimiteDebitoBO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto.ConsultaDatosTarjetaDebitoDTO;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.service.ModificacionLimiteDebitoService;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;

/**
 * The Class ModificacionLimiteDebitoServiceImpl.
 */
@Component
public class ModificacionLimiteDebitoServiceImpl implements ModificacionLimiteDebitoService {

	/** The modif limite debito BO. */
	@Autowired
	private ModificacionLimiteDebitoBO modifLimiteDebitoBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.service.
	 * ModificacionLimiteDebitoService#getClaseTarjetaDebito(java.lang.String,
	 * java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ConsultaDatosTarjetaDebitoDTO> getClaseTarjetaDebito(String sucursal, String numTarjeta,
			Cliente cliente) throws BusinessException {
		return modifLimiteDebitoBO.getClaseTarjetaDebito(sucursal, numTarjeta, cliente);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modificacionlimitedebito.service.
	 * ModificacionLimiteDebitoService#modificarLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteModificacionLimiteDebitoView, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<ResultadoTransaccion> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, String numTarjetaBanelco,
			Cliente cliente, String ip, String userAgent) throws BusinessException {
		return modifLimiteDebitoBO.modificarLimitesExtraccion(comprobanteModificacionLimiteDebitoView,
				numTarjetaBanelco, cliente, ip, userAgent);
	}
}
