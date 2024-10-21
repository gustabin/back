/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaCerradaBO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.CuentaCerradaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaCerrada;

/**
 * The Class CuentaCerradaBOImpl.
 */
@Component
public class CuentaCerradaBOImpl implements CuentaCerradaBO {

	/** The Constant APELLIDO2_MIGRACION. */
	private static final String APELLIDO2_MIGRACION = "M";

	/** The cuenta cerrada dao. */
	@Autowired
	private CuentaCerradaDAO cuentaCerradaDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.bo.CuentaCerradaBO#obtenerCuentasCerradas
	 * (ar.com.santanderrio.obp.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<List<CuentaCerrada>> obtenerCuentasCerradas(Cliente cliente) {
		Respuesta<List<CuentaCerrada>> cuentasCerradas = new Respuesta<List<CuentaCerrada>>();
		List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
		try {
			// condicion para buscar cuentas para el cliente
			if (cliente.getIsCuentaMigrada()) {
				cuentasCerradas = cuentaCerradaDAO.obtenerCuentasCerradas(cliente);
			} else {
				cuentasCerradas = null;
			}
		} catch (DAOException ex) {
			cuentasCerradas.setEstadoRespuesta(EstadoRespuesta.ERROR);
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(ex.getMessage());
			itemsRespuesta.add(itemMensajeRespuesta);
			cuentasCerradas.setItemMensajeRespuesta(itemsRespuesta);
		}

		return cuentasCerradas;
	}

}
