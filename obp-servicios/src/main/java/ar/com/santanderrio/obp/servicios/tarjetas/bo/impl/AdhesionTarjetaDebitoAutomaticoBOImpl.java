/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AdhesionTarjetaDebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.AdhesionTarjetaDebitoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosAdhesionDebitoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;

/**
 * The Class AdhesionTarjetaDebitoAutomaticoBOImpl.
 * 
 * @author mariano.g.pulera
 * 
 */
@Component
public class AdhesionTarjetaDebitoAutomaticoBOImpl implements AdhesionTarjetaDebitoAutomaticoBO {

	/** The adhesion tarjeta debito automatico dao. */
	@Autowired
	AdhesionTarjetaDebitoAutomaticoDAO adhesionTarjetaDebitoAutomaticoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.
	 * AdhesionTarjetaDebitoAutomaticoBO#extraerDatosTarjeta(ar.com.santanderrio
	 * .obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public List<TarjetasAdhesionDebitoView> extraerDatosTarjeta(Cliente cliente) {

		List<TarjetasAdhesionDebitoView> listaTarjetas = new ArrayList<TarjetasAdhesionDebitoView>();

		List<Cuenta> listaCuentas = cliente.getCuentas();

		for (Cuenta cuenta : listaCuentas) {
			if ("07".equals(cuenta.getTipoCuenta())) {
				String nroTarjetaEnmascarado = TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(),
						TipoCuentaTarjeta.TIPOCTA_VISA);
				TarjetasAdhesionDebitoView tarjeta = new TarjetasAdhesionDebitoView();
				tarjeta.setNumeroTarjeta("VISA " + nroTarjetaEnmascarado);
				tarjeta.setTipoTarjeta("07");
				listaTarjetas.add(tarjeta);
			} else if ("42".equals(cuenta.getTipoCuenta())) {
				String nroTarjetaEnmascarado = TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(),
						TipoCuentaTarjeta.TIPOCTA_AMEX);
				TarjetasAdhesionDebitoView tarjeta = new TarjetasAdhesionDebitoView();
				tarjeta.setNumeroTarjeta("AMEX " + nroTarjetaEnmascarado);
				tarjeta.setTipoTarjeta("42");
				listaTarjetas.add(tarjeta);
			}

		}

		return listaTarjetas;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.bo.
	 * AdhesionTarjetaDebitoAutomaticoBO#adherirTarjetaDebitoAutomatico(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.
	 * DatosAdhesionDebitoTarjeta)
	 */
	@Override
	public ComprobanteFeedbackView adherirTarjetaDebitoAutomatico(Cliente cliente,
			DatosAdhesionDebitoTarjeta datosAdhesionDebito) throws DAOException {
		return adhesionTarjetaDebitoAutomaticoDao.adherirTarjetaDebitoAutomatico(cliente, datosAdhesionDebito);
	}

}
