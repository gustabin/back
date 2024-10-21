/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.onboarding.dao.OnboardingDAO;

/**
 * The Class OnboardingBOImpl.
 */
@Component
public class OnboardingBOImpl implements OnboardingBO {

	/** The onboarding DAO. */
	@Autowired
	OnboardingDAO onboardingDAO;

	/** The Constant MOBILE. */
	private static final String MOBILE = "phone";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.bo.OnboardingBO#
	 * obtenerSeccionesActivas(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String)
	 */
	@Override
	public List<String> obtenerSeccionesActivas(Cliente cliente, String dispositivo) {
		try {
			return onboardingDAO.obtenerSeccionesActivas(cliente.getNup(), obtenerValorDispositivo(dispositivo));
		} catch (DAOException e) {
			return new ArrayList<String>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.bo.OnboardingBO#
	 * informarListo(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean informarListo(Cliente cliente, String dispositivo, String seccion) {
		try {
			return onboardingDAO.informarListo(cliente.getNup(), obtenerValorDispositivo(dispositivo), seccion);
		} catch (DAOException e) {
			return false;
		}
	}

	/**
	 * Obtener valor dispositivo.
	 *
	 * @param dispositivo
	 *            the dispositivo
	 * @return the string
	 */
	private String obtenerValorDispositivo(String dispositivo) {
		return MOBILE.equalsIgnoreCase(dispositivo) ? "0" : "1";
	}

}
